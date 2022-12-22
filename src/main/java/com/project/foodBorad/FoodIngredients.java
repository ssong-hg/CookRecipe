package com.project.foodBorad;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FoodIngredients {
	
	@Id
	private String ingredientsName;
	@NotNull
	private String ingredients;
	
	
}
