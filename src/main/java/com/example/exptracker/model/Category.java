package com.example.exptracker.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Category {
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonIgnore
	private List<Expense> expenses;

	
	public Category() {
		
	}
	
	public Category(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setCatId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List <Expense> getExpense() {
		return expenses;
	}
	
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	



}