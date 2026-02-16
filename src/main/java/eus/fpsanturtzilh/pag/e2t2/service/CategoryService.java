package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.e2t2.model.Category;
import eus.fpsanturtzilh.pag.e2t2.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

        existingCategory.setName(updatedCategory.getName());

        return repository.save(existingCategory);
    }

    public Category getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }
}
