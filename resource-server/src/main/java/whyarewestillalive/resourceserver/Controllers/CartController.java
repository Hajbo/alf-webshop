package whyarewestillalive.resourceserver.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import whyarewestillalive.resourceserver.Entities.*;
import whyarewestillalive.resourceserver.Repositories.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	//Returns user's cart
	@GetMapping
	public ResponseEntity<List<Item>> getByid(@AuthenticationPrincipal Jwt jwt){
		log.debug("Get/Return User:"+jwt.getSubject()+"'s cart.");
		User user=userRepository.findByName(jwt.getSubject());
		if(user!=null) {
			Cart cart=cartRepository.findByUserid(user.getId());
			log.info("User:"+jwt.getSubject()+"'s cart was found.");
			return ResponseEntity.ok(cart.getItems());
		}
		else {
			log.warn("User:"+jwt.getSubject()+"'s cart was not found!");
			return ResponseEntity.notFound().build();
		}
	}
	
	//Adds item to user's cart
	@PostMapping
	public ResponseEntity<?> getByid(@AuthenticationPrincipal Jwt jwt,@RequestBody ID  id){
		log.debug("Post/Add Item:"+id.getId()+" to User:"+jwt.getSubject()+"'s cart.");
		Item item=itemRepository.findById(id.getId());
		User user=userRepository.findByName(jwt.getSubject());
		if(item!=null && user!=null) {
			Cart cart=cartRepository.findByUserid(user.getId());
			cart.addItem(item);
			cartRepository.save(cart);
			log.info("Item:"+id+" succeessfully added to User:"+jwt.getSubject()+"'s cart");
			return ResponseEntity.ok().build();
		}
		else {
			log.warn("Couldn't add Item:"+id+" to User:"+jwt.getSubject()+"'s cart");
			return ResponseEntity.notFound().build();
		}
	}
	
	//Buys the items in the user's cart
	@PutMapping
	public ResponseEntity<?> update(@AuthenticationPrincipal Jwt jwt){
		log.debug("Put/Buy items in User:"+jwt.getSubject()+"'s cart");
		User user=userRepository.findByName(jwt.getSubject());
		if(user!=null) {
			Cart cart=cartRepository.findByUserid(user.getId());
			log.info("User:"+jwt.getSubject()+"'s cart was found");
			if(user.Pay(cart.getPrice())) {
				cart.clear();
				cartRepository.save(cart);
				log.info("Items successfully bought, User:"+jwt.getSubject()+"'s cart emptied");
				return ResponseEntity.ok().build();
			}
			else {
				log.warn("User:"+jwt.getSubject()+" does not have enough money!");
				return ResponseEntity.badRequest().build();
			}
		}
		else {
			log.warn("User:"+jwt.getSubject()+" was not found");
			return ResponseEntity.notFound().build();
		}
	}
	
	//Deletes an item in User's cart
	@DeleteMapping
	public ResponseEntity<?> delete(@AuthenticationPrincipal Jwt jwt,@RequestBody ID id){
		log.debug("Delete/Remove Item:"+id.getId()+" from User:"+jwt.getSubject()+"'s cart");
		User user=userRepository.findByName(jwt.getSubject());
		Item item=itemRepository.findById(id.getId());
		if(user!=null) {
			Cart cart=cartRepository.findByUserid(user.getId());
			cart.removeItem(item);
			cartRepository.save(cart);
			log.info("Item:"+id+" successfully removed from User:"+jwt.getSubject()+"'s cart");
			return ResponseEntity.ok().build();
		}
		else {
			log.warn("User:"+jwt.getSubject()+" was not found");
			return ResponseEntity.notFound().build();
		}
	}
}
