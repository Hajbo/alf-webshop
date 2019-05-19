package whyarewestillalive.resourceserver.Controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll( @AuthenticationPrincipal Jwt jwt){
		if (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN")) {
			return ResponseEntity.ok(userRepository.findAll());
		}
		else {
			User user=userRepository.findByName(jwt.getSubject());
			if(user!=null) {
				List<User> returned=new ArrayList<User>();
				returned.add(user);
				return ResponseEntity.ok(returned);
			}
			else return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getByid(@PathVariable long id, @AuthenticationPrincipal Jwt jwt){
		User user=userRepository.findById(id);
		if (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN") || user.getName()==jwt.getSubject()) {
			
			if (user == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(user);
			}
		}
		else return ResponseEntity.badRequest().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create( @AuthenticationPrincipal Jwt jwt,@RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<?> update( @AuthenticationPrincipal Jwt jwt,@RequestBody User newuser){
		Long Id=newuser.getId();
		User user=userRepository.findById(Id).orElse(null);
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		else if ((newuser.getName().equals(user.getName())) || (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN"))) {
			userRepository.save(newuser);
			return ResponseEntity.ok().build();
		}
		else return ResponseEntity.badRequest().build();
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody long id, @AuthenticationPrincipal Jwt jwt){
		User user=userRepository.findById(id);

		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		else if((user.getName().equals(jwt.getSubject())) || (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN"))) {
			userRepository.delete(user);
			return ResponseEntity.ok().build();
		}
		else return ResponseEntity.badRequest().build();
	}
	
}
