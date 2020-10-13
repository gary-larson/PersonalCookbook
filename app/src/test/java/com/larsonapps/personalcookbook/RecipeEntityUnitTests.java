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
    private static final String NOTES_VALUE = "These are the notes by the cook.";
    private static final String COPYRIGHT_VALUE = "copyright 5201";
    private static final String PREP_TIME_STRING_VALUE = "22 minutes";
    private static final String COOK_TIME_STRING_VALUE = "2 hours 22 mins";
    private static final String TOTAL_TIME_STRING_VALUE = "1 day 1 hour 1 min";
    private static final String SERVINGS_STRING_VALUE = "12 Servings";
    private static final String PREP_TIME_MINUTES_STRING_VALUE = "22 mins";
    private static final String COOK_TIME_MINUTES_STRING_VALUE = "142 mins";
    private static final String TOTAL_TIME_MINUTES_STRING_VALUE = "1501 min";



    // Tests for the constructor and Getters
    RecipeEntity recipe = new RecipeEntity(ID_VALUE, NAME_VALUE, SHORT_DESCRIPTION_VALUE,
            DESCRIPTION_VALUE, SERVINGS_VALUE, PREP_TIME_VALUE, COOK_TIME_VALUE, TOTAL_TIME_VALUE,
            NOTES_VALUE, COPYRIGHT_VALUE);

    @Test
    public void testRecipeIdGetter() {
        assertEquals(ID_VALUE, recipe.getId());
    }

    @Test
    public void testRecipeNameGetter() {
        assertEquals(NAME_VALUE, recipe.getName());
    }

    @Test
    public void testRecipeShortDescriptionGetter() {
        assertEquals(SHORT_DESCRIPTION_VALUE, recipe.getShortDescription());
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
        assertEquals(COPYRIGHT_VALUE, recipe.getCopyright());
    }

    // Test setters
    @Test
    public void testRecipeIdSetter() {
        int temp = 37;
        recipe.setId(temp);
        assertEquals(temp, recipe.getId());
    }

    @Test
    public void testRecipeNameSetter() {
        String temp = "Apple Pie";
        recipe.setName(temp);
        assertEquals(temp, recipe.getName());
    }

    @Test
    public void testReciprShortDescriptionSetter() {
        String temp = "this is another short description";
        recipe.setShortDescription(temp);
        assertEquals(temp, recipe.getShortDescription());
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
        recipe.setCopyright(temp);
        assertEquals(temp, recipe.getCopyright());
    }

    @Test
    public void testRecipePreptimeStringGetter() {
        assertEquals(PREP_TIME_STRING_VALUE, recipe.getPrepTimeString());
    }

    @Test
    public void testRecipeCooktimeStringGetter() {
        assertEquals(COOK_TIME_STRING_VALUE, recipe.getCookTimeString());
    }

    @Test
    public void testRecipeTotalTimeStringGetter() {
        assertEquals(TOTAL_TIME_STRING_VALUE, recipe.getTotalTimeString());
    }

//    @Test
//    public void testRecipeServingsStringGetter() {
//        assertEquals(SERVINGS_STRING_VALUE, recipe.getServingsString());
//    }
//
//    @Test
//    public void testRecipePrepTimeMinutesStringGetter() {
//        assertEquals(PREP_TIME_MINUTES_STRING_VALUE, recipe.getPrepTimeMinutesString());
//    }
//
//    @Test
//    public void testRecipeCookTimeMinutesStringGetter() {
//        assertEquals(COOK_TIME_MINUTES_STRING_VALUE, recipe.getCookTimeMinutesString());
//    }
//
//    @Test
//    public void testRecipeTotalTimeMinutesStringGetter() {
//        assertEquals(TOTAL_TIME_MINUTES_STRING_VALUE, recipe.getTotalTimeMinutesString());
//    }
}
