package whyarewestillalive.resourceserver.controllers;
import java.util.List;

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

import whyarewestillalive.resourceserver.Cart;
import whyarewestillalive.resourceserver.CartRepository;
import whyarewestillalive.resourceserver.Item;

@RestController
@RequestMapping("/cart/")
public class Cart_Controller {

	@Autowired
	private CartRepository cartRepository;
	

	@GetMapping
	public ResponseEntity<cart> getByid(@RequestBody long id){
		Cart cart=cartRepository.findById(id);
		if(cart==null) {
			return ResponseEntity.nofFound().build();
		}
		else {
			return ResponseEntity.ok(cart);
		}
	}
	@GetMapping("{id}")
	public ResponseEntity<cart> getByid(@PathVariable long id){
		Cart cart=cartRepository.findById(id);
		if(cart==null) {
			return ResponseEntity.nofFound().build();
		}
		else {
			return ResponseEntity.ok(cart);
		}
	}
	@PostMapping("{id}")
	public ResponseEntity<cart> getByid(@PathVariable long int,@RequestBody Item item){
		Cart cart=cartRepository.findById(id);
		if(cart==null) {
			return ResponseEntity.nofFound().build();
		}
		else {
			cart.AddItem(item);
			cartRepository.save(cart);
			return ResponseEntity.ok(cart);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Cart cart) {
		Cart temp=cartRepository.findById(cart.getUserID());
		if(temp==null) {
			temp=cartRepository.save(cart);
			return ResponseEntity.ok().buid();
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Cart cart){
		Cart temp=cartRepository.findById(cart.getUserID());
		if(temp==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			temp=cartRepository.save(cart);
			return ResponseEntity.ok().build();
		}
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody long id){
		Cart cart=cartRepository.findById(id);
		if(cart==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			cartRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}
	@DeleteMapping("{id}")
	public ResponseEntity<cart> getByid(@PathVariable long int,@RequestBody Item item){
		Cart cart=cartRepository.findById(id);
		if(cart==null) {
			return ResponseEntity.nofFound().build();
		}
		else {
			cart.RemoveItem(item);
			cartRepository.save(cart);
			return ResponseEntity.ok(cart);
		}
	}
}
