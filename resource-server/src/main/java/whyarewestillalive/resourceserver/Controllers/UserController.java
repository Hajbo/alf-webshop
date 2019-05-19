package whyarewestillalive.resourceserver.Controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll( @AuthenticationPrincipal Jwt jwt){
		log.debug("Get/Return users");
		if (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN")) {
			log.info("Admin accessed:Return every user");
			return ResponseEntity.ok(userRepository.findAll());
		}
		else {
			User user=userRepository.findByName(jwt.getSubject());
			if(user!=null) {
				log.info("User:"+jwt.getSubject()+" accessed:Return own profile");
				List<User> returned=new ArrayList<User>();
				returned.add(user);
				return ResponseEntity.ok(returned);
			}
			
			else {
				log.warn("Requester User:"+jwt.getSubject()+" is invalid");
				return ResponseEntity.badRequest().build();
			}
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getByid(@PathVariable long id, @AuthenticationPrincipal Jwt jwt){
		log.debug("Get/User with ID:"+id+" requested");
		User user=userRepository.findById(id);
		if(user==null) {
			log.warn("User:"+id+" was not found");
			return ResponseEntity.notFound().build();
		}
		if (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN") || user.getName()==jwt.getSubject()) {
			log.info("Valid access,User:"+user.getName()+" returned");
			return ResponseEntity.ok(user);

		}
		else {
			log.warn("Source does not match requested user");
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create( @AuthenticationPrincipal Jwt jwt,@RequestBody User user) {
		log.debug("Post/Create new User:"+user.getName());
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<?> update( @AuthenticationPrincipal Jwt jwt,@RequestBody User newuser){
		log.debug("Put/Update user profile:"+newuser.getName());
		Long Id=newuser.getId();
		User user=userRepository.findById(Id).orElse(null);
		if(user==null) {
			log.warn("User:"+newuser.getName()+" was not found");
			return ResponseEntity.notFound().build();
		}
		else if ((newuser.getName().equals(user.getName())) || (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN"))) {
			userRepository.save(newuser);
			log.info("User:"+user.getName()+" updated");
			return ResponseEntity.ok().build();
		}
		else {
			log.warn("Update request source does not match account holder");
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody long id, @AuthenticationPrincipal Jwt jwt){
		log.debug("Delete/User:"+id+" requested for removal");
		User user=userRepository.findById(id);

		if(user==null) {
			log.warn("User:"+id+" was not found");
			return ResponseEntity.notFound().build();
		}
		else if((user.getName().equals(jwt.getSubject())) || (jwt.getClaims().get("authorities").toString().equals("ROLE_ADMIN"))) {
			userRepository.delete(user);
			log.info("User:"+id+" deleted");
			return ResponseEntity.ok().build();
		}
		else {
			log.warn("Delete request source does not match account holder");
			return ResponseEntity.badRequest().build();
		}
	}
	
}
