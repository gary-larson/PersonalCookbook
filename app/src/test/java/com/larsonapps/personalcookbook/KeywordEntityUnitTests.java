package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.KeywordEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class for keyword entity tests
 */
public class KeywordEntityUnitTests {
    // keyword update data
    private static final int KEYWORD_ID_VALUE = 48;
    private static final int RECIPE_ID_VALUE = 947;
    private static final String KEYWORD_VALUE = "rice";

    // create keyword
    KeywordEntity keywordEntity = new
            KeywordEntity(KEYWORD_ID_VALUE, RECIPE_ID_VALUE, KEYWORD_VALUE);

    // test constructor and getters

    /**
     * Test for keyword id getter
     */
    @Test
    public void testKeywordIdGetter() {
        assertEquals(KEYWORD_ID_VALUE, keywordEntity.getKeywordId());
    }

    /**
     * Test for keyword recipe id getter
     */
    @Test
    public void testKeywordRecipeIdGetter() {
        assertEquals(RECIPE_ID_VALUE, keywordEntity.getRecipeId());
    }

    /**
     * Test for keyword getter
     */
    @Test
    public void testKeywordGetter() {
        assertEquals(KEYWORD_VALUE, keywordEntity.getKeyword());
    }

    // test setters

    /**
     * Test for keyword id setter
     */
    @Test
    public void testKeywordIdSetter() {
        int temp = 46;
        keywordEntity.setKeywordId(temp);
        assertEquals(temp, keywordEntity.getKeywordId());
    }

    /**
     * Test for keyword recipe id setter
     */
    @Test
    public void testKeywordResipeIdSetter() {
        int temp = 91;
        keywordEntity.setRecipeId(temp);
        assertEquals(temp, keywordEntity.getRecipeId());
    }

    /**
     * Test for keyword setter
     */
    @Test
    public void testKeywordSetter() {
        String temp = "spaghetti";
        keywordEntity.setKeyword(temp);
        assertEquals(temp, keywordEntity.getKeyword());
    }
}
