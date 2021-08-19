package com.app.recipe.controller;

import com.app.recipe.bean.Recipe;
import com.app.recipe.bean.User;
import com.app.recipe.service.AuthenticationService;
import com.app.recipe.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final AuthenticationService authenticationService;

    public RecipeController(RecipeService recipeService, AuthenticationService authenticationService) {
        this.recipeService = recipeService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Integer>> addRecipe(@Valid @RequestBody Recipe recipe, Principal principal) {
        User user = authenticationService.findUserByEmail(principal.getName());
//        System.out.println(principal.getName());
        recipe.setUser(user);
//        System.out.println(user);
        Recipe savedRecipe = recipeService.save(recipe);
        return ResponseEntity.ok(Collections.singletonMap("id", savedRecipe.getId()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable(value = "id") @Min(1) Integer recipeId) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") @Min(1) Integer recipeId, Principal principal) {
        recipeService.deleteRecipeById(recipeId, principal);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") @Min(1) Integer id,
                                          @Valid @RequestBody Recipe recipe, Principal principal) {
        Recipe updatedRecipe = recipeService.updateRecipeById(id, recipe, principal);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedRecipe);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipe(@RequestParam(required = false) String category,
                                          @RequestParam(required = false, name = "name") String recipeName) {
        List<Recipe> recipes = recipeService.searchRecipe(category, recipeName);
        return ResponseEntity.ok(recipes);
    }
}
