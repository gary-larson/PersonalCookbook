package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.Ingredient;
import com.larsonapps.personalcookbook.data.Recipe;
import com.larsonapps.personalcookbook.data.RecipeImage;
import com.larsonapps.personalcookbook.data.Step;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RecipeUnitTests {
    // recipe data
    private static final String NAME_VALUE = "Chocolate Cookies";
    private static final int SERVINGS_VALUE = 12;
    private static final int PREP_TIME_VALUE = 40;
    private static final String PREP_TIME_STRING_VALUE = "0 days 0 hours 40 minutes";
    private static final int COOK_TIME_VALUE = 12;
    private static final String COOK_TIME_STRING_VALUE = "0 days 0 hours 12 minutes";
    private static final int TOTAL_TIME_VALUE = 52;
    private static final String TOTAL_TIME_STRING_VALUE = "0 days 0 hours 52 minutes";
    private static final String NOTES_VALUE = "These are the notes by the cook.";
    private static final String COPYRIGHT_VALUE = "copyright 5201";


    // step data
    private static final String DESCRIPTION_VALUE = "Sufficient description";
    private static final int NUMBER_VALUE = 5;

    // Create step
    Step step = new Step(NUMBER_VALUE, DESCRIPTION_VALUE);
    List<Step> steps = new ArrayList<>();


    // ingredient data
    private static final double QUANTITY_VALUE = 20.45;
    private static final String MEASURE_VALUE = "Tablespoon";
    private static final String INGREDIENT_VALUE = "Milk";
    private static final String PREPARATION_VALUE = "Pour into container";

    // Create ingredient
    Ingredient ingredient = new Ingredient(INGREDIENT_VALUE, QUANTITY_VALUE, MEASURE_VALUE,
            PREPARATION_VALUE);
    List<Ingredient> ingredients = new ArrayList<>();


    // image data
    private static final String TYPE_VALUE = "Internet";
    private static final String IMAGE_URL_VALUE = "https://somewhere.com/5486946.jpg";
    private static final int HEIGHT_VALUE = 1720;
    private static final int WIDTH_VALUE = 2200;
    private static final String CAPTION_VALUE = "picture of chocolate chip cookies";

    // Create RecipeImage
    RecipeImage image = new RecipeImage(TYPE_VALUE, IMAGE_URL_VALUE, HEIGHT_VALUE, WIDTH_VALUE,
            CAPTION_VALUE);
    List<RecipeImage> images = new ArrayList<>();



    // Tests for the constructor and Getters
    Recipe recipe = new Recipe(NAME_VALUE, DESCRIPTION_VALUE, SERVINGS_VALUE, PREP_TIME_VALUE,
            COOK_TIME_VALUE, TOTAL_TIME_VALUE, NOTES_VALUE, COPYRIGHT_VALUE, ingredients,
            steps, images);

    @Test
    public void testRecipeNameGetter() {
        assertEquals(NAME_VALUE, recipe.getName());
    }

    @Test
    public void testRecipeDescriptionGetter() {
        assertEquals(DESCRIPTION_VALUE, recipe.getDescription());
    }

    @Test
    public void testRecipeServingsGetter() {
        assertEquals(SERVINGS_VALUE, recipe.getServings());
    }

    @Test
    public void testRecipePrepTimeGetter() {
        assertEquals(PREP_TIME_VALUE, recipe.getPrepTime());
    }

    @Test
    public void testRecipePrepTimeStringGetter() {
        assertEquals(PREP_TIME_STRING_VALUE, recipe.getPrepTimeString());
    }

    @Test
    public void testRecipeCookTimeGetter() {
        assertEquals(COOK_TIME_VALUE, recipe.getCookTime());
    }

    @Test
    public void testRecipeCookTimeStringGetter() {
        assertEquals(COOK_TIME_STRING_VALUE, recipe.getCookTimeString());
    }

    @Test
    public void testRecipeTotalTimeGetter() {
        assertEquals(TOTAL_TIME_VALUE, recipe.getTotalTime());
    }

    @Test
    public void testRecipeTotalTimeStringGetter() {
        assertEquals(TOTAL_TIME_STRING_VALUE, recipe.getTotalTimeString());
    }

    @Test
    public void testRecipeNotesGetter() {
        assertEquals(NOTES_VALUE, recipe.getNotes());
    }

    @Test
    public void testRecipeCopyrightGetter() {
        assertEquals(COPYRIGHT_VALUE, recipe.getCoypright());
    }

    @Test
    public void testRecipeIngredientsGetter() {
        ingredients.add(ingredient);
        assertEquals(ingredients.get(0).getName(), recipe.getIngredients().get(0).getName());
    }

    @Test
    public void testRecipeStepsGetter() {
        steps.add(step);
        assertEquals(steps.get(0).getInstruction(), recipe.getSteps().get(0).getInstruction());
    }

    @Test
    public void testRecipeImagesGetter() {
        images.add(image);
        assertEquals(images.get(0).getImageUrl(), recipe.getImages().get(0).getImageUrl());
    }

    // This tests both setter and getter
    @Test
    public void testRecipeIngredientsSetter() {
        Ingredient ingredient1 = new Ingredient();
        List<Ingredient> ingredients1 = new ArrayList<>();
        ingredients1.add(ingredient1);
        recipe.setIngredients(ingredients1);
        assertNull(recipe.getIngredients().get(0).getName());
        assertEquals(0.0, recipe.getIngredients().get(0).getAmount(), 0.01);
        assertNull(recipe.getIngredients().get(0).getMeasure());
        assertNull(recipe.getIngredients().get(0).getPreparation());
    }

    // This tests both setter and getter
    @Test
    public void testRecipeStepsSetter() {
        Step step1 = new Step();
        List<Step> steps1 = new ArrayList<>();
        steps1.add(step1);
        recipe.setSteps(steps1);
        assertNull(recipe.getSteps().get(0).getInstruction());
        assertEquals(0, recipe.getSteps().get(0).getNumber());
    }

    // This tests both setter and getter
    @Test
    public void testRecipeImagesSetter() {
        RecipeImage image1 = new RecipeImage();
        List<RecipeImage> images1 = new ArrayList<>();
        images1.add(image1);
        recipe.setImages(images1);
        assertNull(recipe.getImages().get(0).getImageUrl());
        assertNull(recipe.getImages().get(0).getCaption());
        assertEquals(0, recipe.getImages().get(0).getHeight());
        assertEquals(0, recipe.getImages().get(0).getWidth());
        assertNull(recipe.getImages().get(0).getType());
    }

    // Test default constructor and setters
    Recipe recipeDefault = new Recipe();

    @Test
    public void testRecipeNameSetter() {
        String temp = "Apple Pie";
        recipeDefault.setName(temp);
        assertEquals(temp, recipeDefault.getName());
    }

    @Test
    public void testRecipeDescriptionSetter() {
        String temp = "this is another description";
        recipeDefault.setDescription(temp);
        assertEquals(temp, recipeDefault.getDescription());
    }

    @Test
    public void testRecipeServingsSetter() {
        int temp = 446;
        recipeDefault.setServings(temp);
        assertEquals(temp, recipeDefault.getServings());
    }

    @Test
    public void testRecipePrepTimeSetter() {
        int temp = 1000;
        recipeDefault.setPrepTime(temp);
        assertEquals(temp, recipeDefault.getPrepTime());
    }

    @Test
    public void testRecipeCookTimeSetter() {
        int temp = 1515;
        recipeDefault.setCookTime(temp);
        assertEquals(temp, recipeDefault.getCookTime());
    }

    @Test
    public void testRecipeTotalTimeSetter() {
        int temp = 151515;
        recipeDefault.setTotalTime(temp);
        assertEquals(temp, recipeDefault.getTotalTime());
    }

    @Test
    public void testRecipeNotesSetter() {
        String temp = "This is another note";
        recipeDefault.setNotes(temp);
        assertEquals(temp, recipeDefault.getNotes());
    }

    @Test
    public void testRecipeCopyrightSetter() {
        String temp = "copyright of something";
        recipeDefault.setCoypright(temp);
        assertEquals(temp, recipeDefault.getCoypright());
    }
}
