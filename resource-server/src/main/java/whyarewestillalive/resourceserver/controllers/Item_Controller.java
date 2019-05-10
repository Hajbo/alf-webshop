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

import whyarewestillalive.resourceserver.Item;
import whyarewestillalive.resourceserver.ItemRepository;

@RestController
@RequestMapping("/items")
public class Item_Controller {

	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping
	public ResponseEntity<List<Item>> getAll(){
		return ResponseEntity.ok(itemRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Item> getByid(@PathVariable long id){
		Item item=itemRepository.findById(id);
		if(item==null) {
			return ResponseEntity.nofFound().build();
		}
		else {
			return ResponseEntity.ok(item);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Item item) {
		Item temp=itemRepository.findById(item.getId());
		if(temp==null) {
			temp=itemRepository.save(item);
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Item item){
		Item item=itemRepository.findById(item.getId());
		if(temp==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			temp=itemRepository.save(item);
			return ResponseEntity.ok().build();
		}
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody long id){
		Item item=Repository.findById(id);
		if(item==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			itemRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable long id){
		Item item=itemRepository.findById(id);
		if(item==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			itemRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}
}
