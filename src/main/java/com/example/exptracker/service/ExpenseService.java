package com.example.exptracker.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.exptracker.model.Expense;
import com.example.exptracker.model.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired 
	private ExpenseRepository erepository;
	
	public Page<Expense> listAll(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 5);
		
		return erepository.findAll(pageable);
	}

}
