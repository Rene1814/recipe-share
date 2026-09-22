package com.rsolucoes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rsolucoes.model.Recipe;
import com.rsolucoes.model.User;
import com.rsolucoes.repository.RecipeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService{
	
	private RecipeRepository recipeRepository;

	@Override
	public Recipe createRecipe(Recipe recipe, User user) {
		Recipe createdRecipe = new Recipe();
		createdRecipe.setTitle(recipe.getTitle());
		createdRecipe.setUser(user);
		createdRecipe.setImage(recipe.getImage());
		createdRecipe.setDescription(recipe.getDescription());
		createdRecipe.setVegetarian(recipe.getVegetarian());
		createdRecipe.setCreatedAt(LocalDateTime.now());
		return recipeRepository.save(createdRecipe);
	}

	@Override
	public Recipe findRecipeById(Long id) throws Exception {
		Optional<Recipe> opt = recipeRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("Recipe not found.");
	}

	@Override
	public void deleteRecipe(Long id) throws Exception {
		findRecipeById(id);
		recipeRepository.deleteById(id);
	}

	@Override
	public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
		Recipe old = findRecipeById(id);
		if (recipe.getTitle() != null) {
			old.setTitle(recipe.getTitle());
		}
		if (recipe.getImage() != null) {
			old.setImage(recipe.getImage());
		}
		if (recipe.getDescription() != null) {
			old.setDescription(recipe.getDescription());
		}
		return recipeRepository.save(old);
	}

	@Override
	public List<Recipe> findAllRecipe() {
		
		return recipeRepository.findAll();
	}

	@Override
	public Recipe likeRecipe(Long recipeId, User user) throws Exception {
		Recipe recipe = findRecipeById(recipeId);
		if (recipe.getLikes().contains(user.getId())) {
			recipe.getLikes().remove(user.getId());
		}
		else {
			recipe.getLikes().add(user.getId());
		}
		return recipeRepository.save(recipe);
	}

}
