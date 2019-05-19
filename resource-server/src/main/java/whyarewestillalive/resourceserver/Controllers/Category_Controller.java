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
@RequestMapping("/category")
public class Category_Controller {

	@Autowired
	private CategoryRepository categoryRepository;
	
	//Returns item with id of "id"
	@GetMapping("{category}")
	public ResponseEntity<List<Item>> getByid(@PathVariable String category){
		Category temp=categoryRepository.findByName(category);
		if(temp==null) {
			return (ResponseEntity<List<Item>>) ResponseEntity.notFound();
		}
		else {
			return ResponseEntity.ok(temp.getItems());
		}
	}
}
