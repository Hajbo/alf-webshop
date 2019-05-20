package whyarewestillalive.resourceserver.Controllers;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ItemRepository itemRepository;
  
	@Autowired 
	private UserRepository userRepository;
	
	//Returns every item in database
	@GetMapping
	public ResponseEntity<List<Item>> getAll(){
		log.debug("Get/Return every item");
		return ResponseEntity.ok(itemRepository.findAll());
	}
	
	//Returns item with id of "id"
	@GetMapping("{id}")
	public ResponseEntity<Item> getByid(@PathVariable ID id){
		log.debug("Get/Return Item:"+id.getId());
		Item item=itemRepository.findById(id.getId());
		if(item==null) {
			log.warn("Item:"+id+" was not found");
			return ResponseEntity.notFound().build();
		}
		else {
			log.info("Item:"+id+" was found");
			return ResponseEntity.ok(item);
		}
	}
	
	///Creates new item
	@PostMapping
	public ResponseEntity<?> create(@AuthenticationPrincipal Jwt jwt,@RequestBody Item item) {
		log.debug("Post/Create new item");
		User user=userRepository.findByName(jwt.getSubject());
		if(user!=null) {
			item.setUserId(user.getId());
			itemRepository.save(item);

			log.info("New Item created with ID:"+item.getId()+" and added to User:"+jwt.getSubject());
			return ResponseEntity.ok().build();
		}
		else {
			log.warn("User:"+jwt.getSubject()+" was not found");
			return ResponseEntity.notFound().build();
		}
	}
	
	//Updates item
	@PutMapping
	public ResponseEntity<?> update(@AuthenticationPrincipal Jwt jwt,@RequestBody Item updateditem){
		log.debug("Put/Update User:"+jwt.getSubject()+"'s item");
		Item item=itemRepository.findById(updateditem.getId()).orElse(null);
		User user=userRepository.findByName(jwt.getSubject());
		if(user==null) {
			log.warn("User:"+jwt.getSubject()+" was not found");
			return ResponseEntity.notFound().build();
		}
		else if (item.getUserId()==user.getId() || (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN"))) {
			itemRepository.save(item);
			log.info("Item:"+item.getId()+" updated");
			return ResponseEntity.ok().build();
		}
		else {
			log.warn("User:"+jwt.getSubject()+" was not found");
			return ResponseEntity.badRequest().build();
		}
	}
	
	//Deletes item
	@DeleteMapping
	public ResponseEntity<?> delete(@AuthenticationPrincipal Jwt jwt,@RequestBody ID id){
		log.debug("Delete/Remove Item:"+id.getId());
		Item item=itemRepository.findById(id.getId());
		User user=userRepository.findByName(jwt.getSubject());
		if(user==null) {
			log.warn("User:"+jwt.getSubject()+" is not the owner of Item:"+id+" ,or was not found");
			return ResponseEntity.notFound().build();
		}
		else if (item.getUserId()==user.getId() || (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN"))) {
			itemRepository.delete(item);
			log.info("User:"+jwt.getSubject()+"'s Item:"+id+" was successfully deleted");
			return ResponseEntity.ok().build();
		}
		else {
			log.warn("Invalid Item:"+id);
			return ResponseEntity.badRequest().build();
		}
	}

}
