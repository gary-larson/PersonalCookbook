package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.RecipeEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecipeEntityUnitTests {
    // recipe data
    private static final int ID_VALUE = 1524;
    private static final String NAME_VALUE = "Chocolate Cookies";
    private static final String SHORT_DESCRIPTION_VALUE = "Short description";
    private static final String DESCRIPTION_VALUE = "Sufficient description";
    private static final int SERVINGS_VALUE = 12;
    private static final int PREP_TIME_VALUE = 22;
    private static final int COOK_TIME_VALUE = 142;
    private static final int TOTAL_TIME_VALUE = 1501;
    private static final String COPYRIGHT_VALUE = "copyright 5201";
    private static final String PREP_TIME_STRING_VALUE = "22 minutes";
    private static final String COOK_TIME_STRING_VALUE = "2 hours 22 mins";
    private static final String TOTAL_TIME_STRING_VALUE = "1 day 1 hour 1 min";
    private static final String SERVINGS_STRING_VALUE = "12 Servings";
    private static final String PREP_TIME_MINUTES_STRING_VALUE = "22 Prep mins";
    private static final String COOK_TIME_MINUTES_STRING_VALUE = "142 Cook mins";
    private static final String TOTAL_TIME_MINUTES_STRING_VALUE = "1501 Total mins";
    private static final String PERSONAL_NOTE_VALUE = "Personal note";




    // create recipe
    RecipeEntity recipe = new RecipeEntity(ID_VALUE, NAME_VALUE, SHORT_DESCRIPTION_VALUE,
            DESCRIPTION_VALUE, SERVINGS_VALUE, PREP_TIME_VALUE, COOK_TIME_VALUE, TOTAL_TIME_VALUE,
            COPYRIGHT_VALUE, PERSONAL_NOTE_VALUE);

    // Tests for the constructor and Getters

    /**
     * Test for recipe id getter
     */
    @Test
    public void testRecipeIdGetter() {
        assertEquals(ID_VALUE, recipe.getId());
    }

    /**
     * Test for recipe name getter
     */
    @Test
    public void testRecipeNameGetter() {
        assertEquals(NAME_VALUE, recipe.getName());
    }

    /**
     * Test for Recipe short description getter
     */
    @Test
    public void testRecipeShortDescriptionGetter() {
        assertEquals(SHORT_DESCRIPTION_VALUE, recipe.getShortDescription());
    }

    /**
     * Test for recipe description getter
     */
    @Test
    public void testRecipeDescriptionGetter() {
        assertEquals(DESCRIPTION_VALUE, recipe.getDescription());
    }

    /**
     * Test for recipe servings getter
     */
    @Test
    public void testRecipeServingsGetter() {
        assertEquals(SERVINGS_VALUE, recipe.getServings());
    }

    /**
     * Test for recipe prep time getter
     */
    @Test
    public void testRecipePrepTimeGetter() {
        assertEquals(PREP_TIME_VALUE, recipe.getPrepTime());
    }

    /**
     * Test for recipe cook time getter
     */
    @Test
    public void testRecipeCookTimeGetter() {
        assertEquals(COOK_TIME_VALUE, recipe.getCookTime());
    }

    /**
     * Test for recipe total time getter
     */
    @Test
    public void testRecipeTotalTimeGetter() {
        assertEquals(TOTAL_TIME_VALUE, recipe.getTotalTime());
    }

    /**
     * Test for recipe copyright getter
     */
    @Test
    public void testRecipeCopyrightGetter() {
        assertEquals(COPYRIGHT_VALUE, recipe.getCopyright());
    }

    /**
     * Test for Recipe personal note getter
     */
    @Test
    public void testRecipePersonalNoteGetter() {
        assertEquals(PERSONAL_NOTE_VALUE, recipe.getPersonalNote());
    }

    // Test setters

    /**
     * Test for recipe id setter
     */
    @Test
    public void testRecipeIdSetter() {
        int temp = 37;
        recipe.setId(temp);
        assertEquals(temp, recipe.getId());
    }

    /**
     * Test for recipe name setter
     */
    @Test
    public void testRecipeNameSetter() {
        String temp = "Apple Pie";
        recipe.setName(temp);
        assertEquals(temp, recipe.getName());
    }

    /**
     * Test for recipe short description setter
     */
    @Test
    public void testReciprShortDescriptionSetter() {
        String temp = "this is another short description";
        recipe.setShortDescription(temp);
        assertEquals(temp, recipe.getShortDescription());
    }

    /**
     * Test for recipe description setter
     */
    @Test
    public void testRecipeDescriptionSetter() {
        String temp = "this is another description";
        recipe.setDescription(temp);
        assertEquals(temp, recipe.getDescription());
    }

    /**
     * Test for recipe servings setter
     */
    @Test
    public void testRecipeServingsSetter() {
        int temp = 446;
        recipe.setServings(temp);
        assertEquals(temp, recipe.getServings());
    }

    /**
     * Test for recipe prep time setter
     */
    @Test
    public void testRecipePrepTimeSetter() {
        int temp = 1000;
        recipe.setPrepTime(temp);
        assertEquals(temp, recipe.getPrepTime());
    }

    /**
     * Test for recipe cook time setter
     */
    @Test
    public void testRecipeCookTimeSetter() {
        int temp = 1515;
        recipe.setCookTime(temp);
        assertEquals(temp, recipe.getCookTime());
    }

    /**
     * Test for recipe total time setter
     */
    @Test
    public void testRecipeTotalTimeSetter() {
        int temp = 151515;
        recipe.setTotalTime(temp);
        assertEquals(temp, recipe.getTotalTime());
    }

    /**
     * Test for recipe copyright setter
     */
    @Test
    public void testRecipeCopyrightSetter() {
        String temp = "copyright of something";
        recipe.setCopyright(temp);
        assertEquals(temp, recipe.getCopyright());
    }

    /**
     * Test for personal note setter
     */
    @Test
    public void testRecipePersonalNoteSetter() {
        String temp = "another personal note";
        recipe.setPersonalNote(temp);
        assertEquals(temp, recipe.getPersonalNote());
    }

    /**
     * Test for recipe prep time string getter
     */
    @Test
    public void testRecipePreptimeStringGetter() {
        assertEquals(PREP_TIME_STRING_VALUE, recipe.getPrepTimeString());
    }

    /**
     * Test for recipe cook time string getter
     */
    @Test
    public void testRecipeCooktimeStringGetter() {
        assertEquals(COOK_TIME_STRING_VALUE, recipe.getCookTimeString());
    }

    /**
     * Test for recipe total time string getter
     */
    @Test
    public void testRecipeTotalTimeStringGetter() {
        assertEquals(TOTAL_TIME_STRING_VALUE, recipe.getTotalTimeString());
    }

    /**
     * Test for recipe servings string getter
     */
    @Test
    public void testRecipeServingsStringGetter() {
        assertEquals(SERVINGS_STRING_VALUE, recipe.getServingsString());
    }

    /**
     * Test for recipe prep time minutes string getter
     */
    @Test
    public void testRecipePrepTimeMinutesStringGetter() {
        assertEquals(PREP_TIME_MINUTES_STRING_VALUE, recipe.getPrepTimeMinutesString());
    }

    /**
     * Test for recipe cook time minutes string getter
     */
    @Test
    public void testRecipeCookTimeMinutesStringGetter() {
        assertEquals(COOK_TIME_MINUTES_STRING_VALUE, recipe.getCookTimeMinutesString());
    }

    /**
     * Test for recipe total time minutes string getter
     */
    @Test
    public void testRecipeTotalTimeMinutesStringGetter() {
        assertEquals(TOTAL_TIME_MINUTES_STRING_VALUE, recipe.getTotalTimeMinutesString());
    }
}
