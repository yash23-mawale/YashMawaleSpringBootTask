package com.yash.task;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category")
public class Category {

	@Id 
	@Column(name="Cid")
	private Long cid;

	@Column(name="Cname")
	private String cname;
	 
	@OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL, mappedBy = "category")
	@JsonIgnore
	private List<Product> products;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", products=" + products + "]";
	}
	
}
