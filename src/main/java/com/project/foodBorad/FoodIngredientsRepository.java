package com.project.foodBorad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodIngredientsRepository extends JpaRepository<FoodIngredients, String>{
	List<FoodIngredients> findByIngredientsName(String ingredientsName);
	List<FoodIngredients> findByIngredientsContaining(String ingredients);
	
	
}
