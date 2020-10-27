package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.CookNoteEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CookNoteEntityUnitTests {
    // cook note data
    private static final int COOK_NOTE_ID_VALUE = 15;
    private static final int RECIPE_ID_VALUE = 37;
    private static final String COOK_NOTE_VALUE = "Cook's note";
    private static final int NUMBER_VALUE = 64;

    // Create cook note
    CookNoteEntity cookNote = new CookNoteEntity(COOK_NOTE_ID_VALUE, RECIPE_ID_VALUE,
            COOK_NOTE_VALUE, NUMBER_VALUE);

    // Tests for the constructor and Getters

    /**
     * Test for cook note id getter
     */
    @Test
    public void testCookNoteIdGetter() {
        assertEquals(COOK_NOTE_ID_VALUE, cookNote.getCookNoteId());
    }

    /**
     * test for cook note recipe id getter
     */
    @Test
    public void testCookNoteRecipeIdGetter() {
        assertEquals(RECIPE_ID_VALUE, cookNote.getRecipeId());
    }

    /**
     * test for cook note getter
     */
    @Test
    public void testCookNoteGetter() {
        assertEquals(COOK_NOTE_VALUE, cookNote.getNote());
    }

    /**
     * test for cook note number getter
     */
    @Test
    public void testCookNoteNumberGetter() {
        assertEquals(NUMBER_VALUE, cookNote.getNumber());
    }

    // Test default constructor and setters

    /**
     * test for cook note id setter
     */
    @Test
    public void testCookNoteIdSetter() {
        int temp = 48;
        cookNote.setCookNoteId(temp);
        assertEquals(temp, cookNote.getCookNoteId());
    }

    /**
     * test for cook note recipe id setter
     */
    @Test
    public void testCookNoteRecipeIdSetter() {
        int temp = 64;
        cookNote.setRecipeId(temp);
        assertEquals(temp, cookNote.getRecipeId());
    }

    /**
     * test for cook note setter
     */
    @Test
    public void testCookNoteSetter() {
        String temp = "season to taste";
        cookNote.setNote(temp);
        assertEquals(temp, cookNote.getNote());
    }

    /**
     * test for cook note number setter
     */
    @Test
    public void testCookNoteNumberSetter() {
        int temp = 66;
        cookNote.setNumber(temp);
        assertEquals(temp, cookNote.getNumber());
    }
}
