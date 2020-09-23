package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.RecipeEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RecipeEntityUnitTests {
    // recipe data
    private static final int ID_VALUE = 1524;
    private static final String NAME_VALUE = "Chocolate Cookies";
    private static final String DESCRIPTION_VALUE = "Sufficient description";
    private static final int SERVINGS_VALUE = 12;
    private static final int PREP_TIME_VALUE = 40;
    private static final int COOK_TIME_VALUE = 12;
    private static final int TOTAL_TIME_VALUE = 52;
    private static final String NOTES_VALUE = "These are the notes by the cook.";
    private static final String COPYRIGHT_VALUE = "copyright 5201";



    // Tests for the constructor and Getters
    RecipeEntity recipe = new RecipeEntity(ID_VALUE, NAME_VALUE, DESCRIPTION_VALUE,
            SERVINGS_VALUE, PREP_TIME_VALUE, COOK_TIME_VALUE, TOTAL_TIME_VALUE, NOTES_VALUE,
            COPYRIGHT_VALUE);

    @Test
    public void testRecipeIdGetter() {
        assertEquals(ID_VALUE, recipe.getId());
    }

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
    public void testRecipeCookTimeGetter() {
        assertEquals(COOK_TIME_VALUE, recipe.getCookTime());
    }

    @Test
    public void testRecipeTotalTimeGetter() {
        assertEquals(TOTAL_TIME_VALUE, recipe.getTotalTime());
    }

    @Test
    public void testRecipeNotesGetter() {
        assertEquals(NOTES_VALUE, recipe.getNotes());
    }

    @Test
    public void testRecipeCopyrightGetter() {
        assertEquals(COPYRIGHT_VALUE, recipe.getCoypright());
    }

    // Test setters

    @Test
    public void testRecipeNameSetter() {
        String temp = "Apple Pie";
        recipe.setName(temp);
        assertEquals(temp, recipe.getName());
    }

    @Test
    public void testRecipeDescriptionSetter() {
        String temp = "this is another description";
        recipe.setDescription(temp);
        assertEquals(temp, recipe.getDescription());
    }

    @Test
    public void testRecipeServingsSetter() {
        int temp = 446;
        recipe.setServings(temp);
        assertEquals(temp, recipe.getServings());
    }

    @Test
    public void testRecipePrepTimeSetter() {
        int temp = 1000;
        recipe.setPrepTime(temp);
        assertEquals(temp, recipe.getPrepTime());
    }

    @Test
    public void testRecipeCookTimeSetter() {
        int temp = 1515;
        recipe.setCookTime(temp);
        assertEquals(temp, recipe.getCookTime());
    }

    @Test
    public void testRecipeTotalTimeSetter() {
        int temp = 151515;
        recipe.setTotalTime(temp);
        assertEquals(temp, recipe.getTotalTime());
    }

    @Test
    public void testRecipeNotesSetter() {
        String temp = "This is another note";
        recipe.setNotes(temp);
        assertEquals(temp, recipe.getNotes());
    }

    @Test
    public void testRecipeCopyrightSetter() {
        String temp = "copyright of something";
        recipe.setCoypright(temp);
        assertEquals(temp, recipe.getCoypright());
    }
}
