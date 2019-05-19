package whyarewestillalive.resourceserver.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import whyarewestillalive.resourceserver.Entities.*;
import whyarewestillalive.resourceserver.Repositories.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	//Returns item with id of "id"
	@GetMapping("{category}")
	public ResponseEntity<List<Item>> getByid(@PathVariable String categoryname){
		Category category=categoryRepository.findByName(categoryname);
		if(category==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(category.getItems());
		}
	}
}
