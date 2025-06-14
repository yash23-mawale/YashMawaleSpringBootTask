package com.yash.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	public Page<Category> findByCnameContainingIgnoreCase(String cname, Pageable pageable);
	  
}

