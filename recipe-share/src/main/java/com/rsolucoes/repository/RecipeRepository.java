package com.rsolucoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rsolucoes.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
