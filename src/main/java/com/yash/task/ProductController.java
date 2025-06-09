package com.yash.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    // Get all products from database ;
    @GetMapping
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return productRepository.findAll(PageRequest.of(page, size));
    }

    // Create a new product in database ;
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Select or fetch product by its id (primary key) ;
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    // Update product by its id (primary key) ;
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
    	
        return productRepository.findById(id).map(product -> {
        	
        	if(updatedProduct.getPname() != null)
            	product.setPname(updatedProduct.getPname());
        	if(updatedProduct.getPprice() != 0.0)
            	product.setPprice(updatedProduct.getPprice());
        	if(updatedProduct.getCategory() != null)
            	product.setCategory(updatedProduct.getCategory());
            return productRepository.save(product);
        }).orElseThrow();
    }

    // Delete product by its id (primary key) from the database ;
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
    
    // Extra API's For Searching Products By Its's Name, Price Range, Category_Id and Category_Name.
    
    // Select or fetch product by its name ;
    @GetMapping("/name/{pname}")
    public Page<Product> searchProductsByName(@PathVariable String pname,
    		 @RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "5") int size) {
    	
        return productRepository.findByPnameContainingIgnoreCase(pname, PageRequest.of(page, size));
    }
    
    // Select or fetch product by its price_range ;
    @GetMapping("/productspricerange")
    public Page<Product> getProductsByPriceRange(@RequestParam double min, @RequestParam double max,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
    
    	return productRepository.findByPpriceBetween(min, max, PageRequest.of(page, size));

    }
    
    // Select or fetch product by its category_id ;
    @GetMapping("/categoryid/{cid}")
    public Page<Product> searchProductsByCategoryId(@PathVariable int cid,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
    	
        return productRepository.findBycategory_cid(cid, PageRequest.of(page, size));
    }
    
    // Select or fetch product by its category_name ;
    @GetMapping("/categoryname/{cname}")
    public Page<Product> searchProductsByCategoryName(@PathVariable String cname,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
    	
        return productRepository.findByCategory_cnameContainingIgnoreCase(cname, PageRequest.of(page, size));
    }
}

