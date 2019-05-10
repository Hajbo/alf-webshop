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

import whyarewestillalive.resourceserver.User;
import whyarewestillalive.resourceserver.UserRepository;

@RestController
@RequestMapping("/users/")
public class User_Controller {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getByid(@PathVariable long id){
		User user=userRepository.findById(id);
		if(user==null) {
			return ResponseEntity.nofFound().build();
		}
		else {
			return ResponseEntity.ok(user);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {
		User temp=userRepository.findById(user.getID());
		if(temp==null) {
			temp=userRepository.save(user);
			return ResponseEntity.ok().buid();
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	@PutMapping("{id}/balance/{value}")
	public ResponseEntity<?> changebalance(@PathVariable long id,@PathVariable long value){
		User user=userRepository.findById(id);
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			user.ChangeBalance(value);
			user=userRepository.save(user);
			return ResponseEntity.ok().build();
		}
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody User user){
		User temp=userRepository.findById(user.getID());
		if(temp==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			temp=userRepository.save(user);
			return ResponseEntity.ok().build();
		}
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody long id){
		User user=userRepository.findById(id);
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable long id){
		User user=userRepository.findById(id);
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}
}
