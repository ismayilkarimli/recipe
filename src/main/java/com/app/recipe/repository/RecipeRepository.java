package com.app.recipe.repository;

import com.app.recipe.bean.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    Optional<List<Recipe>> findAllByNameContainingIgnoreCaseOrderByDateTimeDesc(String name);

    Optional<List<Recipe>> findRecipesByCategoryIgnoreCaseOrderByDateTimeDesc(String category);
}
