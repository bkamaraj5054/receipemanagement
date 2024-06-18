package com.examples.recipes.controller;


import com.examples.recipes.model.Recipe;
import com.examples.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.getRecipeById(id);
        return recipe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        recipe.setCreationDateTime(LocalDateTime.now());
        return recipeService.saveRecipe(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipeDetails) {
        Optional<Recipe> recipeOptional = recipeService.getRecipeById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            recipe.setName(recipeDetails.getName());
            recipe.setVegetarian(recipeDetails.isVegetarian());
            recipe.setServings(recipeDetails.getServings());
            recipe.setIngredients(recipeDetails.getIngredients());
            recipe.setInstructions(recipeDetails.getInstructions());
            Recipe updatedRecipe = recipeService.saveRecipe(recipe);
            return ResponseEntity.ok(updatedRecipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
