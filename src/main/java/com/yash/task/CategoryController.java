package com.yash.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all categories from database ;
    @GetMapping
    public Page<Category> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        
        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    // Create a new category in database ;
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // Select or fetch category by its id (primary key) ;
    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id);
    }

    // Update category by its id (primary key) ;
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {
            category.setCname(updatedCategory.getCname());
            return categoryRepository.save(category);
        }).orElseThrow();
    }

    // Delete category by its id (primary key) from the database ;
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
    
    // Extra API's For Searching Category By Its's Name.
    
    // Select or fetch category by its name ;
    @GetMapping("/name/{cname}")
    public Page<Category> searchCategoriesByName(@PathVariable String cname,  
    		@RequestParam(defaultValue = "0") int page,
    		@RequestParam(defaultValue = "5") int size) {
    	
        return categoryRepository.findByCnameContainingIgnoreCase(cname, PageRequest.of(page, size));
    }
    
}

