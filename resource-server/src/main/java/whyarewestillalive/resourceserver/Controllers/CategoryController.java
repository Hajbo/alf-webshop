package whyarewestillalive.resourceserver.Controllers;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import whyarewestillalive.resourceserver.Entities.*;
import whyarewestillalive.resourceserver.Repositories.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//Returns item with id of "id"
	@GetMapping("{category}")
	public ResponseEntity<List<Item>> getByid(@PathVariable String categoryname){
		log.debug("Get/Return items in "+categoryname);
		Category category=categoryRepository.findByName(categoryname);
		if(category==null) {
			log.info("Category:"+categoryname+" was found");
			return ResponseEntity.notFound().build();
		}
		else {
			log.warn("Category:"+categoryname+" was not found");
			return ResponseEntity.ok(category.getItems());
		}
	}

	@PostMapping
	public ResponseEntity<List<Item>> post(@RequestBody Category category){
		Category temp=categoryRepository.findByName(category.getName());
		if(temp==null) {
			categoryRepository.save(category);
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}

}
