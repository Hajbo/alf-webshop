package whyarewestillalive.resourceserver.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
	public ResponseEntity<User> getByid(@PathVariable long id, @AuthenticationPrincipal Jwt jwt){
		User user=userRepository.findById(id);
		String authorities=jwt.getClaims().get("authorities").toString();

		if (authorities.equals("ROLE_ADMIN")) {
			if (user == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(user);
			}
		}
		return ResponseEntity.badRequest().build();
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
	public ResponseEntity<?> update(@RequestBody User user, @AuthenticationPrincipal Jwt jwt){
		User temp=userRepository.findById(user.getId());
		String authorities=jwt.getClaims().get("authorities").toString();
		String name=jwt.getClaims().get("client_id").toString();

		if ((user.getName().equals(name)) || (authorities.equals("ROLE_ADMIN"))) {

			if (temp == null) {
				return ResponseEntity.notFound().build();
			} else {
				temp = userRepository.save(user);
				return ResponseEntity.ok().build();
			}
		}

		return ResponseEntity.badRequest().build();
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody long id, @AuthenticationPrincipal Jwt jwt){
		User user=userRepository.findById(id);
		String authorities=jwt.getClaims().get("authorities").toString();
		String name=jwt.getClaims().get("client_id").toString();

		if((user.getName().equals(name)) || (authorities.equals("ROLE_ADMIN"))) {

			if (user == null) {
				return ResponseEntity.notFound().build();
			} else {
				userRepository.deleteById(id);
				return ResponseEntity.ok().build();
			}
		}

		return ResponseEntity.badRequest().build();
	}
	
}
