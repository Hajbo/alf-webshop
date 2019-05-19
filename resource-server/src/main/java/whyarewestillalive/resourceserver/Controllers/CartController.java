package whyarewestillalive.resourceserver.Controllers;

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

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartRepository cartRepository;
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	//Returns user's cart
	@GetMapping
	public ResponseEntity<Cart> getByid(@AuthenticationPrincipal Jwt jwt){
		User user=userRepository.findByName(jwt.getSubject());
		if(user!=null) {
			Cart cart=cartRepository.findByUserid(user.getId());
			return ResponseEntity.ok(cart);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//Adds item to user's cart
	@PostMapping
	public ResponseEntity<?> getByid(@AuthenticationPrincipal Jwt jwt,@RequestBody long  id){
		Item item=itemRepository.findById(id);
		User user=userRepository.findByName(jwt.getSubject());
		if(item!=null && user!=null) {
			Cart cart=cartRepository.findByUserid(user.getId());
			cart.addItem(item);
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//Buys the items in the user's cart
	@PutMapping
	public ResponseEntity<?> update(@AuthenticationPrincipal Jwt jwt){
		User user=userRepository.findByName(jwt.getSubject());
		if(user!=null) {
			Cart cart=cartRepository.findByUserid(user.getId());
			if(user.Pay(cart.getPrice())) {
				cartRepository.save(cart);
				return ResponseEntity.ok().build();
			}
			else {
				return ResponseEntity.badRequest().build();
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//Deletes an item in User's cart
	@DeleteMapping
	public ResponseEntity<?> delete(@AuthenticationPrincipal Jwt jwt,@RequestBody long id){
		User user=userRepository.findByName(jwt.getSubject());
		Item item=itemRepository.findById(id);
		if(user!=null) {
			Cart cart=cartRepository.findByUserid(user.getId());
			cart.removeItem(item);
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
