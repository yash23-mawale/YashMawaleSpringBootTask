package com.yash.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	@Id
	@Column(name="pid")
	private Long pid;

	@Column(name="pname")
    private String pname;
    
	@Column(name="pprice")
    private double pprice;
    
    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_cid")
    private Category category;
    
    public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(Long pid, String pname, double pprice, Category category) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pprice = pprice;
		this.category = category;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getPprice() {
		return pprice;
	}

	public void setPprice(double pprice) {
		this.pprice = pprice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", pprice=" + pprice + ", category=" + category + "]";
	}
	
}
