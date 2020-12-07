package com.example.exptracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.exptracker.model.Category;
import com.example.exptracker.model.Expense;
import com.example.exptracker.model.ExpenseRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class ExpenseRepositoryTest {
	
	@Autowired
	private ExpenseRepository erepository;
	
	 @Test
	    public void findDescriptionShouldRetrunExpense() {
	        List<Expense> expenses = erepository.findByDescription("Bus");
	        
	        assertThat(expenses).hasSize(1);
	        assertThat(expenses.get(0).getExpDate()).isEqualTo("10-20-2020");
	    }
	 
	 @Test
	    public void createNewExpense() {
	    	Expense expense = new Expense("Tennis Rocket", 3.0, "10-20-2020", new Category("Sport"));
	    	erepository.save(expense);
	    	assertThat(expense.getId()).isNotNull();
	    }    
	 
	 @Test
	  public void deleteExpense() {

	    Expense expense = new Expense("Tennis Rocket", 3.0, "10-20-2020", new Category("Sport"));
	    erepository.save(expense);
	    erepository.deleteById(expense.getId());
	    assertThat(erepository.count()).isEqualTo(2);
	    
	  }

}
