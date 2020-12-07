package com.example.exptracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.exptracker.model.Category;
import com.example.exptracker.model.CategoryRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository crepository;
	
	 @Test
	    public void findCategory() {
	        List<Category> categories = crepository.findByName("Health");
	        
	        assertThat(categories).hasSize(1);
	        assertThat(categories.get(0).getName()).isEqualTo("Health");
	    }
	 
	 @Test
	    public void createNewCategory() {
	    	Category category = new Category("Travelling");
	    	crepository.save(category);
	    	assertThat(category.getName()).isNotNull();
	    }    
	 
	 @Test
	  public void deleteCategory() {

	    Category category = new Category("Travelling");
	    crepository.save(category);
	    crepository.deleteById(category.getId());
	    assertThat(crepository.count()).isEqualTo(2);
	    
	 }
} 