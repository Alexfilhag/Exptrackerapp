package com.example.exptracker.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Expense {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;
	private String description;
	private double price;
	
	private String expDate;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "categoryid")
	private Category category;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userid")
	private User user;
	

	
	public Expense() {
		super();
	}


     

	public Expense(String description, double price, String expDate, Category category) {
		super();
		
		this.description = description;
		this.price = price;
		this.expDate = expDate;
		this.category = category;
	}


    

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}




	public String getExpDate() {
		return expDate;
	}




	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}




	public Category getCategory() {
		return category;
	}




	public void setCategory(Category category) {
		this.category = category;
	}


    

	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	@Override
	public String toString() {
		return "Expense [id=" + id + ", description=" + description + ", price=" + price + ", expDate=" + expDate
				+ ", category=" + this.getCategory() + "]";
	}
	
	
	

}

