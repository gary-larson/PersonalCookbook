package com.larsonapps.personalcookbook.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Interface for database methods
 */
@Dao
public interface CookbookDao {
    /**
     * Method to Insert a recipe
     * @param recipe to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(RecipeEntity recipe);

    /**
     * Method to insert an ingredient
     * @param ingredient to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(IngredientEntity ingredient);

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
     * Method to insert cook's note
     * @param cookNote to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCookNote(CookNoteEntity cookNote);

    /**
     * Method to insert all cook's notes
     * @param cookNotes to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllNotes(List<CookNoteEntity> cookNotes);

    /**
     * Method to delete a recipe
     * @param recipe to delete
     */
    @Delete
    void deleteRecipe(RecipeEntity recipe);

    /**
     * Method to delete an ingredient
     * @param ingredient to delete
     */
    @Delete
    void deleteIngredient(IngredientEntity ingredient);

    /**
     * Method to delete  ingredients
     * @param ingredients to delete
     */
    @Delete
    void deleteIngredient(List<IngredientEntity> ingredients);

    /**
     * Method to delete a step
     * @param step to delete
     */
    @Delete
    void deleteStep(StepEntity step);

    /**
     * Method to delete steps
     * @param steps to delete
     */
    @Delete
    void deleteStep(List<StepEntity> steps);

    /**
     * Method to delete an image
     * @param image to delete
     */
    @Delete
    void deleteImage(ImageEntity image);

    /**
     * Method to delete images
     * @param images to delete
     */
    @Delete
    void deleteImage(List<ImageEntity> images);

    /**
     * Method to delete a keyword
     * @param keyword to delete
     */
    @Delete
    void deleteKeyword(KeywordEntity keyword);

    /**
     * Method to delete keywords
     * @param keywords to delete
     */
    @Delete
    void deleteKeyword(List<KeywordEntity> keywords);

    /**
     * Method to delete a category
     */
    @Delete
    void deleteCategory(CategoryEntity category);

    /**
     * Method to delete a cook's note
     * @param cookNote to delete
     */
    @Delete
    void deleteCookNote(CookNoteEntity cookNote);

    /**
     * Method to delete cook's notes
     * @param cookNotes to delete
     */
    @Delete
    void deleteCookNote(List<CookNoteEntity> cookNotes);

    /**
     * Method to update a recipe
     * @param recipe to update
     */
    @Update
    void updateRecipe(RecipeEntity recipe);

    /**
     * Method to update an ingredient
     * @param ingredient to update
     */
    @Update
    void updateIngredient(IngredientEntity ingredient);

    /**
     * Method to update a step
     * @param step to update
     */
    @Update
    void updateStep(StepEntity step);

    /**
     * Method to update an image
     * @param image to update
     */
    @Update
    void updateImage(ImageEntity image);

    /**
     * Method to Update a cook's note
     * @param cookNote to update
     */
    @Update
    void updateCoolNote(CookNoteEntity cookNote);

    /**
     * Method to retrieve all recipes with live data
     * @return list of recipes
     */
    @Query("SELECT * FROM recipes")
    LiveData<List<RecipeEntity>> getAllRecipes();

    /**
     * Method to retrieve all recipes of one keyword with live data
     * @return list of recipes
     */
    @Query("SELECT recipes.* FROM recipes INNER JOIN keywords ON recipes.recipe_id = keywords.recipe_id WHERE keyword = :keyword")
    LiveData<List<RecipeEntity>> getAllRecipes(String keyword);

    /**
     * Method to retrieve all recipes with a keyword from a list of keywords with live data
     * @return list of recipes
     */
    @Query("SELECT recipes.* FROM recipes INNER JOIN keywords ON recipes.recipe_id = keywords.recipe_id WHERE keyword IN (:keywords)")
    LiveData<List<RecipeEntity>> getAllRecipes(String[] keywords);

    /**
     * Method to get all recipes for widget
     * @return list of recipes
     */
    @Query("SELECT * FROM recipes")
    List<RecipeEntity> getWidgetRecipes();

    /**
     * Method to retrieve a recipe by id with live data
     * @param recipeId of recipe to retrieve
     * @return recipe
     */
    @Query("SELECT * FROM recipes WHERE recipe_id = :recipeId")
    LiveData<RecipeEntity> getRecipe(int recipeId);

    /**
     * Method to retrieve all of a recipes ingredients with live data
     * @param recipeId of the recipe to retrieve
     * @return list of ingredients
     */
    @Query("SELECT * FROM ingredients WHERE recipe_id = :recipeId")
    LiveData<List<IngredientEntity>> getAllIngredients(int recipeId);

    /**
     * Method to retrieve all of a recipes ingredients
     * @param recipeId of the recipe to retrieve
     * @return list of ingredients
     */
    @Query("SELECT * FROM ingredients WHERE recipe_id = :recipeId")
    List<IngredientEntity> getIngredients(int recipeId);

    /**
     * Method to get all ingredients for widget
     * @param recipeId to get recipes for
     * @return list of ingredients
     */
    @Query("SELECT * FROM ingredients WHERE recipe_id = :recipeId")
    List<IngredientEntity> getWidgetIngredients(int recipeId);

    /**
     * Method to retrieve all of a recipes steps with live data
     * @param recipeId of the recipe to retrieve
     * @return list of steps
     */
    @Query("SELECT * FROM steps WHERE recipe_id = :recipeId ORDER BY number")
    LiveData<List<StepEntity>> getAllSteps(int recipeId);

    /**
     * Method to retrieve all of a recipes steps
     * @param recipeId of the recipe to retrieve
     * @return list of steps
     */
    @Query("SELECT * FROM steps WHERE recipe_id = :recipeId ORDER BY number")
    List<StepEntity> getSteps(int recipeId);

    /**
     * Method to retrieve all of a recipes images with live data
     * @param recipeId of the recipe to retrieve
     * @return list of images
     */
    @Query("SELECT * FROM images WHERE recipe_id = :recipeId")
    LiveData<List<ImageEntity>> getAllImages(int recipeId);

    /**
     * Method to retrieve all of a recipes images
     * @param recipeId of the recipe to retrieve
     * @return list of images
     */
    @Query("SELECT * FROM images WHERE recipe_id = :recipeId")
    List<ImageEntity> getImages(int recipeId);

    /**
     * Method to retrieve all keywords by recipe id with live data
     * @param recipeId of the recipe to retrieve keywords for
     * @return list of keywords
     */
    @Query("SELECT * FROM keywords WHERE recipe_id = :recipeId")
    LiveData<List<KeywordEntity>> getAllKeywords(int recipeId);

    /**
     * Method to retrieve all keywords by recipe id
     * @param recipeId of the recipe to retrieve keywords for
     * @return list of keywords
     */
    @Query("SELECT * FROM keywords WHERE recipe_id = :recipeId")
    List<KeywordEntity> getKeywords(int recipeId);

    /**
     * Method to get all categories
     * @return a list of categories
     */
    @Query("SELECT * FROM categories")
    LiveData<List<CategoryEntity>> getAllCategories();

    /**
     * Method to see if category exists by category name
     * @param name to check
     * @return true if exists false otherwise
     */
    @Query("SELECT category_id FROM categories WHERE category_name = :name")
    int categoryExists(String name);

    /**
     * Method to get recipe id by recipe name
     * @param name of recipe to get id
     * @return recipe id
     */
    @Query("SELECT recipe_id FROM recipes WHERE name = :name")
    int getRecipeId(String name);

    /**
     * Method to get image id by url
     * @param recipeId of the image to get
     * @param url of the image
     * @return image id
     */
    @Query("SELECT image_id FROM images WHERE image_url = :url AND recipe_id = :recipeId")
    int getImageIdByUrl(int recipeId, String url);

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

    /**
     * Method to get all cook notes by recipe id with live data
     * @param recipeId to get the cook's notes for
     * @return list of cook's notes
     */
    @Query("SELECT * FROM cook_notes WHERE recipe_id = :recipeId")
    LiveData<List<CookNoteEntity>> getAllCookNotes(int recipeId);

    /**
     * Method to get all cook notes by recipe id
     * @param recipeId to get the cook's notes for
     * @return list of cook's notes
     */
    @Query("SELECT * FROM cook_notes WHERE recipe_id = :recipeId")
    List<CookNoteEntity> getCookNotes(int recipeId);

    /**
     * Method to ger cook's note by number
     * @param recipeId of the recipe
     * @param number of the note
     */
    @Query("SELECT note_id FROM cook_notes WHERE recipe_id = :recipeId AND number = :number")
    int getCookNoteByNumber(int recipeId, int number);
}
