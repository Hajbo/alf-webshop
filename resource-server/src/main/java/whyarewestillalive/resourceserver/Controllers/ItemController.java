package whyarewestillalive.resourceserver.Controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import whyarewestillalive.resourceserver.Entities.*;
import whyarewestillalive.resourceserver.Repositories.*;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired 
	private UserRepository userRepository;

	
	//Returns every item in database
	@GetMapping
	public ResponseEntity<List<Item>> getAll(){
		return ResponseEntity.ok(itemRepository.findAll());
	}
	
	//Returns item with id of "id"
	@GetMapping("{id}")
	public ResponseEntity<Item> getByid(@PathVariable long id){
		Item item=itemRepository.findById(id);
		if(item==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(item);
		}
	}
	
	///Creates new item
	@PostMapping
	public ResponseEntity<?> create(@AuthenticationPrincipal Jwt jwt,@RequestBody Item item) {
		User user=userRepository.findByName(jwt.getSubject());
		if(user!=null) {
			item.setUserId(user.getId());
			itemRepository.save(item);
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//Updates item
	@PutMapping
	public ResponseEntity<?> update(@AuthenticationPrincipal Jwt jwt,@RequestBody Item updateditem){
		Item item=itemRepository.findById(updateditem.getId()).orElse(null);
		User user=userRepository.findByName(jwt.getSubject());
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		else if (item.getUserId()==user.getId() || (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN"))) {
			itemRepository.save(item);
			return ResponseEntity.ok().build();
		}
		else return (ResponseEntity<?>) ResponseEntity.badRequest();
	}
	
	//Deletes item
	@DeleteMapping
	public ResponseEntity<?> delete(@AuthenticationPrincipal Jwt jwt,@RequestBody long id){
		Item item=itemRepository.findById(id);
		User user=userRepository.findByName(jwt.getSubject());
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		else if (item.getUserId()==user.getId() || (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN"))) {
			itemRepository.delete(item);
			return ResponseEntity.ok().build();
		}
		else return (ResponseEntity<?>) ResponseEntity.badRequest();
	}

}
