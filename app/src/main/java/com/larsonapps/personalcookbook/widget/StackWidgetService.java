package com.larsonapps.personalcookbook.widget;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.CookbookWidgetRecipe;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for stack widget remote views
 */
public class StackWidgetService extends RemoteViewsService {
    /**
     * Method to call stack remote views factory
     * @param intent to pass
     * @return new instance of stack remote views factory
     */
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new StackRemoteViewsFactory(getApplication(), getApplicationContext());
    }
}

/**
 * Class to handle stack remote views factory
 */
class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    // Declare variables
    private Context mContext;
    private List<CookbookWidgetRecipe> mRecipes;
    private Application mApplication;
    private CookbookDao mCookbookDao;

    /**
     * Constructor for list remote views factory
     * @param context t0 use
     */
    public StackRemoteViewsFactory(Application application, Context context) {
        mContext = context;
        mApplication = application;
    }

    /**
     * Method to handle create initializations
     */
    @Override
    public void onCreate() {
        CookbookRoomDatabase cookbookRoomDatabase = CookbookRoomDatabase.getDatabase(mApplication);
        mCookbookDao = cookbookRoomDatabase.cookbookDao();
    }

    /**
     * Method to update widget when the data changes
     */
    @Override
    public void onDataSetChanged() {
        // Declare and initialize variables
        List<CookbookWidgetRecipe> cookbookWidgetRecipeList = new ArrayList<>();
        // get recipes from room database
        List<RecipeEntity> recipes = mCookbookDao.getWidgetRecipes();
        // loop through recipes
        if (recipes != null) {
            for (RecipeEntity recipe : recipes) {
                // declare and initialize variables
                CookbookWidgetRecipe cookbookWidgetRecipe = new CookbookWidgetRecipe();
                cookbookWidgetRecipe.setRecipeName(recipe.getName());
                // get list of ingredients for this recipe
                List<IngredientEntity> ingredientList =
                        mCookbookDao.getWidgetIngredients(recipe.getId());
                List<String> stringList = new ArrayList<>();
                // add ingredients names to list
                if (ingredientList != null) {
                    for (IngredientEntity ingredient : ingredientList) {
                        String tempString;
                        if (ingredient.getAmount() == null || ingredient.getAmount().isEmpty()) {
                            if (ingredient.getMeasure() == null ||
                                    ingredient.getMeasure().isEmpty()) {
                                tempString = ingredient.getName();
                            } else {
                                tempString = ingredient.getMeasure() + " " + ingredient.getName();
                            }
                        } else if (ingredient.getMeasure() == null ||
                                ingredient.getMeasure().isEmpty()) {
                            tempString = ingredient.getAmount() + ingredient.getName();
                        } else {
                            tempString = ingredient.getAmount() + " " + ingredient.getMeasure() +
                                    " " + ingredient.getName();
                        }
                        stringList.add(tempString);
                    }
                    // add list of names to baking recipes
                    cookbookWidgetRecipe.setIngredientList(stringList);
                    // add recipe to recipe list
                    cookbookWidgetRecipeList.add(cookbookWidgetRecipe);
                }
            }
        }
        mRecipes = cookbookWidgetRecipeList;
    }

    /**
     * clean up when destroyed
     */
    @Override
    public void onDestroy() {
        mRecipes = null;
    }

    /**
     * method to get count of baking recipes
     * @return count of baking recipes
     */
    @Override
    public int getCount() {
        if (mRecipes == null) {
            return 0;
        }
        return mRecipes.size();
    }

    /**
     * Method to bind data to the widget views
     * @param position position in baking recipe list
     * @return bound view
     */
    @Override
    public RemoteViews getViewAt(int position) {
        // declare and initialize variables
        RemoteViews stackView = new RemoteViews(mContext.getPackageName(),
                R.layout.cookbook_widget_item);
        // set recipe name
        stackView.setTextViewText(R.id.widget_recipe_name_text_view,
                mRecipes.get(position).getRecipeName() );
        // create a string of the ingredients
        StringBuilder temp = new StringBuilder();
        for (String string : mRecipes.get(position).getIngredientList()) {
            if (temp.toString().equals("")) {
                temp = new StringBuilder("• " + string);
            } else {
                temp.append("\n").append("• ").append(string);
            }
        }
        // set ingredients
        stackView.setTextViewText(R.id.widget_ingredients_text_view, temp);
        return stackView;
    }

    // required overrides and basic returns
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
