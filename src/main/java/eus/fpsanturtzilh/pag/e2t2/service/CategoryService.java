package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import eus.fpsanturtzilh.pag.e2t2.model.Category;
import eus.fpsanturtzilh.pag.e2t2.repository.CategoryRepository;

public class CategoryService {
private final CategoryRepository repository;
	
	public CategoryService(CategoryRepository repository) {
		this.repository = repository;
	}
	
	public List<Category> getAllCategories(){
		return repository.findAll();
	}
	public Category saveCategories(Category talde) {
		return repository.save(talde);
	}
	public void deleteCategory(Long id) {
		repository.deleteById(id);
	}
	public Category updateCategor(Long id, Category updateCategory) {
		Category existingCategory = repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id" + id));
		
		existingCategory.setName(updateCategory.getName());
		
		return repository.save(existingCategory);
	}

	public static Category findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
