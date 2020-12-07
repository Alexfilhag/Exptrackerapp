package com.example.exptracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.example.exptracker.model.Category;
import com.example.exptracker.model.CategoryRepository;
import com.example.exptracker.model.Expense;
import com.example.exptracker.model.ExpenseRepository;
import com.example.exptracker.model.User;
import com.example.exptracker.model.UserRepository;



@SpringBootApplication
public class ExptrackerApplication {
	private static final Logger log = LoggerFactory.getLogger(ExptrackerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExptrackerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(ExpenseRepository erepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("");
			
			crepository.save(new Category("Housing"));
			crepository.save(new Category("Food"));
			crepository.save(new Category("Entertainment"));
			crepository.save(new Category("Transport"));
			crepository.save(new Category("Taxes"));
			crepository.save(new Category("Health"));
			crepository.save(new Category("Bills"));
			crepository.save(new Category("Clothing")); 
			crepository.save(new Category("Household Items")); 
			crepository.save(new Category("Savings"));
			crepository.save(new Category("Personal"));
			
			
		    erepository.save(new Expense("Bus",20,"10-20-2020", crepository.findByName("Transport").get(0)));
		    erepository.save(new Expense("Bread, milk",5,"11-11-2020", crepository.findByName("Food").get(0)));
		    erepository.save(new Expense("Movie",25,"10-5-2020", crepository.findByName("Entertainment").get(0)));
		    erepository.save(new Expense("Bus",20,"10-20-2020", crepository.findByName("Entertainment").get(0)));
		    erepository.save(new Expense("Bus",20,"10-20-2020", crepository.findByName("Housing").get(0)));
		    erepository.save(new Expense("Bus",20,"10-20-2020", crepository.findByName("Food").get(0)));
		    erepository.save(new Expense("Tram",20,"10-20-2020", crepository.findByName("Transport").get(0)));
		    erepository.save(new Expense("Oranges",20,"10-20-2020", crepository.findByName("Food").get(0)));
		    erepository.save(new Expense("Bus",20,"10-20-2020", crepository.findByName("Transport").get(0)));
		    erepository.save(new Expense("Bananas",20,"10-20-2020", crepository.findByName("Food").get(0)));
		    erepository.save(new Expense("Internet",20,"10-20-2020", crepository.findByName("Bills").get(0)));
		    erepository.save(new Expense("Gym",20,"10-20-2020", crepository.findByName("Bills").get(0)));
		    erepository.save(new Expense("Metro",20,"10-20-2020", crepository.findByName("Transport").get(0)));
		    erepository.save(new Expense("Bus",20,"10-20-2020", crepository.findByName("Transport").get(0)));
		    erepository.save(new Expense("Eggs",20,"10-20-2020", crepository.findByName("Food").get(0)));
		    erepository.save(new Expense("VideoGame",20,"10-20-2020", crepository.findByName("Entertainment").get(0)));
		    
		    
		 
			User user1 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@gmail.com", "ADMIN");
			urepository.save(user1);
			
			
			log.info("");
			for (Expense expense : erepository.findAll()) {
				log.info(expense.toString());
			}

		};
	}
}
 