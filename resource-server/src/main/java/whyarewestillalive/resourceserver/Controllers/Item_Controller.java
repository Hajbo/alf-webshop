package whyarewestillalive.resourceserver.Controllers;
import java.util.List;

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
public class Item_Controller {

	@Autowired
	private ItemRepository itemRepository;
	
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
		//Search,to make sure it doesnt exist
		Item temp=itemRepository.findById(item.getId());
		if(temp==null) {
			//Id is unique,lets save it
			itemRepository.create(jwt.getSubject(),item);
			return ResponseEntity.ok().build();
		}
		else {
			//Already exists
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}
	}
	
	//Updates item
	@PutMapping
	public ResponseEntity<?> update(@AuthenticationPrincipal Jwt jwt,@RequestBody Item item){
		//Lets find the item
		Item temp=itemRepository.findById(item.getId());
		long id = temp.getUserId();
		User user=itemRepository.findOwner(jwt.getClaims().get("client_id").toString());
		String authorities=jwt.getClaims().get("authorities").toString();
		long jwtID=user.getId();

		if ((jwtID!=id) || (authorities.equals("ROLE_ADMIN"))) {

			if (temp == null) {
				//It did not exists
				return (ResponseEntity<?>) ResponseEntity.notFound();
			} else {
				//Update item
				if (itemRepository.update(jwt.getSubject(), item)) {
					return ResponseEntity.ok().build();
				} else {
					return (ResponseEntity<?>) ResponseEntity.badRequest();
				}
			}
		}
		return (ResponseEntity<?>) ResponseEntity.badRequest();
	}
	
	//Deletes item
	@DeleteMapping
	public ResponseEntity<?> delete(@AuthenticationPrincipal Jwt jwt,@RequestBody long id){
		Item temp=itemRepository.findById(id);
		long userId = temp.getUserId();
		User user=itemRepository.findOwner(jwt.getClaims().get("client_id").toString());
		String authorities=jwt.getClaims().get("authorities").toString();
		long jwtID=user.getId();

		if ((jwtID!=userId) || (authorities.equals("ROLE_ADMIN"))) {

			//deletes item if user and item is valid
			boolean success = itemRepository.deleteItem(jwt.getSubject(), id);
			if (success) {
				//User and item is valid,user is the item owner
				return ResponseEntity.ok().build();
			} else {
				//User or item not found,or invalid access
				return (ResponseEntity<?>) ResponseEntity.notFound();
			}
		}

		return (ResponseEntity<?>) ResponseEntity.badRequest();
	}

}
