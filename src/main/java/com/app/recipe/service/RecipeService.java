package com.app.recipe.service;

import com.app.recipe.bean.Recipe;
import com.app.recipe.exception.IllegalRecipeParameterException;
import com.app.recipe.exception.IllegalRequestException;
import com.app.recipe.exception.RecipeNotFoundException;
import com.app.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public Recipe save(Recipe toSave) {
        return repository.save(toSave);
    }

    public Recipe findRecipeById(Integer recipeId) {
        Optional<Recipe> optionalRecipe = repository.findById(recipeId);
        return optionalRecipe.orElseThrow(() -> new RecipeNotFoundException("No such recipe"));
    }

    public void deleteRecipeById(Integer toDelete, Principal principal) {
        Optional<Recipe> optionalRecipe = repository.findById(toDelete);
        Recipe recipe = optionalRecipe.orElseThrow(() -> new RecipeNotFoundException("No such recipe"));
        String requestEmail = principal.getName();
        String recipeEmail = recipe.getUser().getEmail();
        if (!recipeEmail.equals(requestEmail)) {
            throw new IllegalRequestException("unauthorized to delete the recipe");
        }
        repository.deleteById(toDelete);
    }

    public Recipe updateRecipeById(Integer toUpdate, Recipe updatedRecipe, Principal principal) {
        Optional<Recipe> optionalRecipe = repository.findById(toUpdate);
        if (optionalRecipe.isEmpty()) throw new RecipeNotFoundException("No such recipe");
        String requestEmail = principal.getName();
        String recipeOwnerEmail = optionalRecipe.get().getUser().getEmail();
        if (!recipeOwnerEmail.equals(requestEmail)) {
            throw new IllegalRequestException("unauthorized to modify the recipe");
        }
        Recipe recipe = optionalRecipe.get();
        recipe.setName(updatedRecipe.getName());
        recipe.setCategory(updatedRecipe.getCategory());
        recipe.setDateTime(updatedRecipe.getDateTime());
        recipe.setDescription(updatedRecipe.getDescription());
        recipe.setIngredients(updatedRecipe.getIngredients());
        recipe.setDirections(updatedRecipe.getDirections());

        return repository.save(recipe);
    }

    public List<Recipe> searchRecipe(String category, String name) {
        if (category != null && name != null
                || category == null && name == null)
            throw new IllegalRecipeParameterException("category and name are mutually exclusive parameters");

        Optional<List<Recipe>> optionalRecipes;
        if (category != null) {
            optionalRecipes = repository.findRecipesByCategoryIgnoreCaseOrderByDateTimeDesc(category);
        } else {
            optionalRecipes = repository.findAllByNameContainingIgnoreCaseOrderByDateTimeDesc(name);
        }

        return optionalRecipes.orElse(Collections.emptyList());
    }
}
