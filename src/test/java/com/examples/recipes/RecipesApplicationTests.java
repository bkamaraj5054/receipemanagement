package com.examples.recipes;



import com.examples.recipes.model.Recipe;
import com.examples.recipes.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RecipesApplicationTests {

	@Autowired
	private RecipeRepository recipeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRecipeCreation() {
		Recipe recipe = new Recipe();
		recipe.setName("Test Recipe");
		recipe.setCreationDateTime(LocalDateTime.now());
		recipe.setVegetarian(true);
		recipe.setServings(2);
		recipe.setIngredients("Test Ingredients");
		recipe.setInstructions("Test Instructions");

		Recipe savedRecipe = recipeRepository.save(recipe);

		assertThat(savedRecipe).isNotNull();
		assertThat(savedRecipe.getId()).isNotNull();
		assertThat(savedRecipe.getName()).isEqualTo("Test Recipe");
	}
}
