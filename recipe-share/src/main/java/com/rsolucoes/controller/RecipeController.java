package com.rsolucoes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsolucoes.model.Recipe;
import com.rsolucoes.model.User;
import com.rsolucoes.service.RecipeService;
import com.rsolucoes.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/recipe")
public class RecipeController {
	
	private RecipeService recipeService;
	private UserService userService;
	
	@PostMapping("/user/{id}")
	public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
		User user = userService.findUserById(id);
		Recipe createdRecipe = recipeService.createRecipe(recipe, user);
		return createdRecipe;
	}
	
	@GetMapping
	public List<Recipe> getAllRecipe() throws Exception {
		List <Recipe> recipes = recipeService.findAllRecipe();
		return recipes;
	}
	
	@DeleteMapping("/{id}")
	public String deleteRecipe( @PathVariable Long id) throws Exception {
		recipeService.deleteRecipe(id);
		return "Recipe deleted successfully.";
	}
	
	@PutMapping("/{id}")
	public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
		
		Recipe updatedRecipe = recipeService.updateRecipe(recipe, id);
		return updatedRecipe;
	}
	
	@PutMapping("/{id}/like/user/{userId}")
	public Recipe likeRecipe(@PathVariable Long id, @PathVariable Long userId) throws Exception {
		User user = userService.findUserById(userId);
		Recipe updatedRecipe = recipeService.likeRecipe(id, user);
		return updatedRecipe;
	}

}
