package whyarewestillalive.resourceserver.Controllers;
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

import whyarewestillalive.resourceserver.Entities.*;
import whyarewestillalive.resourceserver.Repositories.*;

@RestController
@RequestMapping("/users")
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
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(user);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {
		User temp=userRepository.findById(user.getId());
		if(temp==null) {
			temp=userRepository.save(user);
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody User user){
		User temp=userRepository.findById(user.getId());
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
	
}
