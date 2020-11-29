package com.example.exptracker.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.exptracker.model.CategoryRepository;
import com.example.exptracker.model.Expense;
import com.example.exptracker.model.ExpenseRepository;
import com.example.exptracker.model.User;
import com.example.exptracker.model.UserRepository;
import com.example.exptracker.service.ExpenseService;

@Controller
public class ExpenseController {
	@Autowired
	private ExpenseRepository erepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired 
	ExpenseService service;
	
	@Autowired
	UserRepository urepository;
	
	@GetMapping(value="/login")
	public String viewLoginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegisterFrom(Model model) {
		model.addAttribute("user", new User());
		return "registrationForm";
	}
	
	@PostMapping("/process_registration")
	public String processReg(User user) {
		urepository.save(user);
		
		return "registration_success";
	}
	
	 @RequestMapping(value="explist")
	    public String expList(Model model) {
		 
		return listByPage(model, 1);
	    }
	 
	 
	    @GetMapping("/page/{pageNumber}")
	    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {
			 Page<Expense> page = service.listAll(currentPage);
			 
			 int totalPages = page.getTotalPages();
			 long totalExpenses = page.getTotalElements();
			 
			
			 List<Expense> expenses = page.getContent();
		
			 model.addAttribute("currentPage", currentPage) ;
			    model.addAttribute("expenses", expenses) ;
		        model.addAttribute("totalExpenses", totalExpenses) ;
		        model.addAttribute("totalPages", totalPages) ;
		        return "explist";
		    }
	    
	 
		 
	   
	    @RequestMapping(value = "/add")
	    public String addExp(Model model){
	    	model.addAttribute("expense", new Expense());
	    	model.addAttribute("categories", crepository.findAll());
	        return "addexp";
	    }     
	    
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(Expense expense){
	        erepository.save(expense);
	        return "redirect:explist";
	    }    
	    
	    
		@RequestMapping(value = "/process", method = RequestMethod.GET)
		public String expSubmit(@ModelAttribute Expense expense) {
			erepository.save(expense);
			

			return "redirect:/explist";
		}


	  
	    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteExp(@PathVariable("id") Long expId, Model model) {
	    	erepository.deleteById(expId);
	        return "redirect:../explist";
	    }     
	    
	
	  
	    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	    public String editExpense(@PathVariable("id") Long expId, Model model) {
	    	model.addAttribute("expense", erepository.findById(expId));
	    	model.addAttribute("categories", crepository.findAll());
	    	
	    	
	    	return "modifyexp";
	    }   
	 }
