package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.KeywordEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeywordEntityUnitTests {
    // step update data
    private static final int KEYWORD_ID_VALUE = 48;
    private static final int RECIPE_ID_VALUE = 947;
    private static final String KEYWORD_VALUE = "rice";

    // create step update
    KeywordEntity keywordEntity = new
            KeywordEntity(KEYWORD_ID_VALUE, RECIPE_ID_VALUE, KEYWORD_VALUE);

    // test constructor and getters
    @Test
    public void testKeywordIdGetter() {
        assertEquals(KEYWORD_ID_VALUE, keywordEntity.getKeywordId());
    }

    @Test
    public void testRecipeIdGetter() {
        assertEquals(RECIPE_ID_VALUE, keywordEntity.getRecipeId());
    }

    @Test
    public void testKeywordGetter() {
        assertEquals(KEYWORD_VALUE, keywordEntity.getKeyword());
    }

    // test setters
    @Test
    public void testKeywordIdSetter() {
        int temp = 46;
        keywordEntity.setKeywordId(temp);
        assertEquals(temp, keywordEntity.getKeywordId());
    }

    @Test
    public void testResipeIdSetter() {
        int temp = 91;
        keywordEntity.setRecipeId(temp);
        assertEquals(temp, keywordEntity.getRecipeId());
    }

    @Test
    public void testKeywordSetter() {
        String temp = "spaghetti";
        keywordEntity.setKeyword(temp);
        assertEquals(temp, keywordEntity.getKeyword());
    }
}