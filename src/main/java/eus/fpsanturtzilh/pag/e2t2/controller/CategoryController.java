package eus.fpsanturtzilh.pag.e2t2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import eus.fpsanturtzilh.pag.e2t2.service.CategoryService;
import eus.fpsanturtzilh.pag.e2t2.model.Category;

@RestController 
@RequestMapping("/api/categories")
//@CrossOrigin(origins = "http://localhost:8100", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class CategoryController {
	
	private final CategoryService service;
	
	public CategoryController (CategoryService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Category> getCategories(){
		return service.getAllCategories();
	}
	
	@PostMapping
	public Category addCategory (@RequestBody Category category) {
		return service.saveCategory(category);
	}
	
	@PutMapping("/{id}")
	public Category updateCategory (@PathVariable Long id, @RequestBody Category updatedCategory) {
		return service.updateCategory (id, updatedCategory);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory (@PathVariable Long id) {
		service.deleteCategory(id);
	}
}
