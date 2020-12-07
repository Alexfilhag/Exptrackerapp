package com.example.exptracker.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.exptracker.model.CategoryRepository;
import com.example.exptracker.model.Expense;
import com.example.exptracker.model.ExpenseRepository;
import com.example.exptracker.model.SignUpForm;
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
	
	
	
	 @RequestMapping(value = "signup")
	    public String addStudent(Model model){
	    	model.addAttribute("signupform", new SignUpForm());
	        return "signup";
	    }	
	 
	  
	 // saving new user
	    
	    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
	    public String save(@Validated @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
	    	if (!bindingResult.hasErrors()) { // validation errors
	    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
		    		String pass = signupForm.getPassword();
			    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			    	String hashPass = bc.encode(pass);
		
			    	User newUser = new User();
			    	newUser.setPasswordHash(hashPass);
			    	newUser.setUsername(signupForm.getUsername());
			    	newUser.setRole("USER");
			    	newUser.setEmail(signupForm.getEmail());
			    	if (urepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
			    		urepository.save(newUser);
			    	}
			    	else {
		    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
		    			return "signup";		    		
			    	}
	    		}
	    		else {
	    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
	    			return "signup";
	    		}
	    	}
	    	else {
	    		return "signup";
	    	}
	    	return "registration_success";    	
	    }    
	    
	    
	 
	
	// return list starting page 1
	
	 @RequestMapping(value="explist")
	    public String expList(Model model) {
		 
		return listByPage(model, 1);
	    }
	 
	 
	   //Rest
	  
	    @RequestMapping(value="/expenses", method = RequestMethod.GET)
	    public @ResponseBody List<Expense> expListRest() {	
	        return (List<Expense>) erepository.findAll();
	    }  
	 
	   //Rest
	  
	    @RequestMapping(value="user{id}/expense/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Expense> findExpRest(@PathVariable("id") Long expenseId) {	
	    	return erepository.findById(expenseId);
	    }       
	    
	 // return the list of expenses according to the number of the page
	 
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
