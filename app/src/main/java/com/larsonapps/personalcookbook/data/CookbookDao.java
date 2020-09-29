package com.larsonapps.personalcookbook.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CookbookDao {
    /**
     * Method to Insert a recipe
     * @param recipe to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(RecipeEntity recipe);

    /**
     * Method to Insert a recipe update
     * @param recipeUpdate to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipeUpdate(RecipeUpdateEntity recipeUpdate);

    /**
     * Method to insert an ingredient
     * @param ingredient to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(IngredientEntity ingredient);

    /**
     * Method to insert an ingredient update
     * @param ingredientUpdate to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredientUpdate(IngredientUpdateEntity ingredientUpdate);

    /**
     * Method to insert a list of ingredients
     * @param ingredients to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllIngredients(List<IngredientEntity> ingredients);

    /**
     * Method to insert a step
     * @param step to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStep(StepEntity step);

    /**
     * Method to insert a step update
     * @param stepUpdate to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStepUpdate(StepUpdateEntity stepUpdate);

    /**
     * Method to insert a list of steps
     * @param steps to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllSteps(List<StepEntity> steps);

    /**
     * Method to insert an image
     * @param image to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImage(ImageEntity image);

    /**
     * Method to insert a list of images
     * @param images to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllImages(List<ImageEntity> images);

    /**
     * Method to insert a keyword
     * @param keyword to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertKeyword(KeywordEntity keyword);

    /**
     * Method to insert a list of keywords
     * @param keywords to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllKeywords(List<KeywordEntity> keywords);

    /**
     * Method to insert a category
     * @param category to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(CategoryEntity category);

    /**
     * Method to delete a recipe
     * @param recipe to delete
     */
    @Delete
    void deleteRecipe(RecipeEntity recipe);

    /**
     * Method to delete a recipe update
     * @param recipeUpdate to delete
     */
    @Delete
    void deleteRecipeUpdate(RecipeUpdateEntity recipeUpdate);

    /**
     * Method to delete an ingredient
     * @param ingredient to delete
     */
    @Delete
    void deleteIngredient(IngredientEntity ingredient);

    /**
     * Method to delete an ingredient update
     * @param ingredientUpdate to delete
     */
    @Delete
    void deleteIngredientUpdate(IngredientUpdateEntity ingredientUpdate);

    /**
     * Method to delete a step
     * @param step to delete
     */
    @Delete
    void deleteStep(StepEntity step);

    /**
     * Method to delete a step update
     * @param stepUpdate to delete
     */
    @Delete
    void deleteStepUpdate(StepUpdateEntity stepUpdate);

    /**
     * Method to delete an image
     * @param image to delete
     */
    @Delete
    void deleteImage(ImageEntity image);

    /**
     * Method to delete a keyword
     * @param keyword to delete
     */
    @Delete
    void deleteKeyword(KeywordEntity keyword);

    /**
     * Method to delete a cayegory
     */
    @Delete
    void deleteCategory(CategoryEntity category);

    /**
     * Method to update a recipe
     * @param recipe to update
     */
    @Update
    void updateRecipe(RecipeEntity recipe);

    /**
     * Method to update a recipe update
     * @param recipeUpdate to update
     */
    @Update
    void updateRecipeUpdate(RecipeUpdateEntity recipeUpdate);

    /**
     * Method to update an ingredient
     * @param ingredient to update
     */
    @Update
    void updateIngredient(IngredientEntity ingredient);

    /**
     * Method to update an ingredient update
     * @param ingredientUpdate to update
     */
    @Update
    void updateIngredientUpdate(IngredientUpdateEntity ingredientUpdate);

    /**
     * Method to update a step
     * @param step to update
     */
    @Update
    void updateStep(StepEntity step);

    /**
     * Method to update a step update
     * @param stepUpdate to update
     */
    @Update
    void updateStepUpdate(StepUpdateEntity stepUpdate);

    /**
     * Method to update an image
     * @param image to update
     */
    @Update
    void updateImage(ImageEntity image);

    /**
     * Method to update a keyword
     * @param keyword to update
     */
    @Update
    void updateKeyword(KeywordEntity keyword);

    /**
     * Method to update a category
     * @param category to update
     */
    @Update
    void updateCategory(CategoryEntity category);

    /**
     * Method to retrieve all recipes
     * @return list of recipes
     */
    @Query("SELECT * FROM recipes")
    LiveData<List<RecipeEntity>> getAllRecipes();

    /**
     * Method to retrieve all recipes of one keyword
     * @return list of recipes
     */
    @Query("SELECT recipes.* FROM recipes INNER JOIN keywords ON recipes.recipe_id = keywords.recipe_id WHERE keyword = :keyword")
    LiveData<List<RecipeEntity>> getAllRecipes(String keyword);

    /**
     * Method to retrieve all recipes with a keyword from a list of keywords
     * @return list of recipes
     */
    @Query("SELECT recipes.* FROM recipes INNER JOIN keywords ON recipes.recipe_id = keywords.recipe_id WHERE keyword IN (:keywords)")
    LiveData<List<RecipeEntity>> getAllRecipes(String[] keywords);

    /**
     * Method to retrieve a recipe by id
     * @param recipeId of recipe to retrieve
     * @return recipe
     */
    @Query("SELECT * FROM recipes WHERE recipe_id = :recipeId")
    RecipeEntity getRecipe(int recipeId);

    /**
     * Method to retrieve a recipe update by recipe id
     * @param recipeId of recipe to retrieve the update for
     * @return recipe update
     */
    @Query("SELECT * FROM recipe_updates WHERE recipe_id = :recipeId")
    RecipeUpdateEntity getRecipeUpdate(int recipeId);

    /**
     * Method to retrieve all of a recipes ingredients
     * @param recipeId of the recipe to retrieve
     * @return list of ingredients
     */
    @Query("SELECT * FROM ingredients WHERE recipe_id = :recipeId")
    LiveData<List<IngredientEntity>> getAllIngredients(int recipeId);

    /**
     * Method to retrieve an ingredient update by ingredient id
     * @param ingredientId of ingredient to retrieve the update
     * @return ingredient update
     */
    @Query("SELECT * FROM ingredient_updates WHERE ingredient_id = :ingredientId")
    IngredientUpdateEntity getIngredientUpdate(int ingredientId);

    /**
     * Method to retrieve all of a recipes steps
     * @param recipeId of the recipe to retrieve
     * @return list of steps
     */
    @Query("SELECT * FROM steps WHERE recipe_id = :recipeId")
    LiveData<List<StepEntity>> getAllSteps(int recipeId);

    /**
     * Method to retrieve a step update by step id
     * @param stepId of step to retrieve the update
     * @return step update
     */
    @Query("SELECT * FROM step_updates WHERE step_id = :stepId")
    StepUpdateEntity getStepUpdate(int stepId);

    /**
     * Method to retrieve all of a recipes images
     * @param recipeId of the recipe to retrieve
     * @return list of images
     */
    @Query("SELECT * FROM images WHERE recipe_id = :recipeId")
    LiveData<List<ImageEntity>> getAllImages(int recipeId);

    /**
     * Method to retrieve all keywords by recipe id
     * @param recipeId of the recipe to retrieve keywords for
     * @return list of keywords
     */
    @Query("SELECT * FROM keywords WHERE recipe_id = :recipeId")
    LiveData<List<KeywordEntity>> getAllKeywords(int recipeId);

    /**
     * Method to get all categories
     * @return a list of categories
     */
    @Query("SELECT * FROM categories")
    LiveData<List<CategoryEntity>> getAllCategories();

    /**
     * Method to get a category by category id
     * @param categoryId of the category to get
     * @return category
     */
    @Query("SELECT * FROM categories WHERE category_id = :categoryId")
    CategoryEntity getCategory(int categoryId);

    /**
     * Method to get recipe id by recipe name
     * @param name of recipe to get id
     * @return recipe id
     */
    @Query("SELECT recipe_id FROM recipes WHERE name = :name")
    int getRecipeIdByName(String name);

    /**
     * Method to get image id by caption
     * @param recipeId of image to get
     * @param caption of image to get
     * @return image id
     */
    @Query("SELECT image_id FROM images WHERE caption = :caption AND recipe_id = :recipeId")
    int getImageIdByCaption(int recipeId, String caption);

    /**
     * Method to get ingredient id by name
     * @param recipeId recipe of ingredient to get
     * @param name of ingredient to get
     * @return ingredient id
     */
    @Query("SELECT ingredient_id FROM ingredients WHERE name = :name AND recipe_id = :recipeId")
    int getIngredientIdByName(int recipeId, String name);

    /**
     * Method to get keyword id by keyword
     * @param recipeId of recipe to get id for
     * @param keyword to get id for
     * @return keyword id
     */
    @Query("SELECT keyword_id FROM keywords WHERE keyword = :keyword AND recipe_id = :recipeId")
    int getKeywordIdByKeyword(int recipeId, String keyword);

    /**
     * Method to get step id by number
     * @param recipeId of step to get id for
     * @param number of step to get id for
     * @return step id
     */
    @Query("SELECT step_id FROM steps WHERE number = :number AND recipe_id = :recipeId")
    int getStepIdByNumber(int recipeId, int number);

    /**
     * Method to get category id
     * @param name of category to get category id for
     * @return category id
     */
    @Query("SELECT category_id FROM categories WHERE category_name = :name")
    int getCategoryIdByName(String name);
}
