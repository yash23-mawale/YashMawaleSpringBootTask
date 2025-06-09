package com.yash.task;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	public Page<Product> findByPnameContainingIgnoreCase(String pname, Pageable pageable);
	
	public Page<Product> findByPpriceBetween(double minPrice, double maxPrice, Pageable pageable);
	
	public Page<Product> findBycategory_cid(int categoryid, Pageable pageable);
	
	public Page<Product> findByCategory_cnameContainingIgnoreCase(String cname, Pageable pageable);
}

