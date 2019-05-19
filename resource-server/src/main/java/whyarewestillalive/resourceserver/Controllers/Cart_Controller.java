package whyarewestillalive.resourceserver.Controllers;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
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
@RequestMapping("/cart")
public class Cart_Controller {

	@Autowired
	private CartRepository cartRepository;
	
	//Returns user's cart
	@GetMapping
	public ResponseEntity<Cart> getByid(@AuthenticationPrincipal Jwt jwt){
		//Find cart
		Cart cart=cartRepository.findByOwner(jwt.getSubject());
		if(cart==null) {
			//Not found
			return ResponseEntity.notFound().build();
		}
		else {
			//Found
			return ResponseEntity.ok(cart);
		}
	}
	
	//Adds item to user's cart
	@PostMapping
	public ResponseEntity<Cart> getByid(@AuthenticationPrincipal Jwt jwt,@RequestBody long  id){
		//Add item to cart
		boolean success=cartRepository.addItem(jwt.getSubject(),id);
		if(success) {
			//Item successfully added
			return ResponseEntity.ok().build();
		}
		else {
			//Item was not found,or user does not exist,return both parameter
			String message="Could not add item to user!User: "+jwt.getSubject()+ " Item id:"+id;
			return (ResponseEntity<Cart>) ResponseEntity.notFound();
		}
	}
	
	//Buys the items in the user's cart
	@PutMapping
	public ResponseEntity<?> update(@AuthenticationPrincipal Jwt jwt){
		//Find user cart
		Cart cart=cartRepository.findByOwner(jwt.getSubject());
		if(cart==null) {
			//Not found,return user name
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}
		else {
			//Find owner
			User owner=cartRepository.findOwner(jwt.getSubject());
			///Owner is null(it should not happen,becouse cart was already found,but i put this here,just to make sure)
			if(owner==null) {
				return (ResponseEntity<?>) ResponseEntity.notFound();
			}
			else if(owner.Pay(cart.getPrice())) {
				//The owner could pay the price,lets save the cart
				cartRepository.save(cart);
				return (ResponseEntity<?>) ResponseEntity.ok().build();
			}
			else {
				//Owner did not have enough money
				return (ResponseEntity<?>) ResponseEntity.badRequest().build();
			}
		}
	}
	
	//Deletes an item in User's cart
	@DeleteMapping
	public ResponseEntity<?> delete(@AuthenticationPrincipal Jwt jwt,@RequestBody long id){
		///Delete item
		boolean success=cartRepository.removeItem(jwt.getSubject(),id);
		if(success) {
			//It was successful
			return ResponseEntity.ok().build();
		}
		else {
			//Item or user does not exist
			String message ="Couldnt find user or item!User:"+jwt.getSubject()+" Item:"+id;
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}
	}
}
