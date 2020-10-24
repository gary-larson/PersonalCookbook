package com.larsonapps.personalcookbook.model;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.data.CategoryEntity;
import com.larsonapps.personalcookbook.data.CookNoteEntity;
import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.utilities.CookbookExecutor;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

/**
 * Class to handle room database access
 */
public class CookbookRepository {
    // Declare constants
    private static final String INTERNET_TYPE = "Internet";
    private static final String TAG = CookbookRepository.class.getSimpleName();
    public final String IMAGE_DIRECTORY = "images";
    public static final String FILE_TYPE = "File";
    // Declare variables
    private CookbookDao mDao;
    private CookbookExecutor mExecutor;
    private Context mContext;

    /**
     * Constructor to initialize variables
     * @param context to access
     */
    public CookbookRepository(Context context) {
        // Initialize variables
        CookbookRoomDatabase cookbookRoomDatabase = CookbookRoomDatabase.getDatabase(context);
        mExecutor = new CookbookExecutor();
        mDao = cookbookRoomDatabase.cookbookDao();
        mContext = context;
    }

    /**
     * Method to get all recipes
     * @return list of recipes through live data
     */
    public LiveData<List<RecipeEntity>> getRecipes () {
        return mDao.getAllRecipes();
    }

    /**
     * method to get all recipes with keyword
     * @param keyword to filter
     * @return list of recipes through live data
     */
    public LiveData<List<RecipeEntity>> getRecipes(String keyword) {
        return mDao.getAllRecipes(keyword);
    }

    /**
     * Method to get all recipes with any of the keywords
     * @param keywords to filer with
     * @return list of recipes through live date
     */
    public LiveData<List<RecipeEntity>> getRecipes(String[] keywords) {
        return mDao.getAllRecipes(keywords);
    }

    /**
     * Method to get all ingredients for a recipe
     * @param recipeId to get ingredients for
     * @return list of imngredients through live data
     */
    public LiveData<List<IngredientEntity>> getIngredients(int recipeId) {
        return mDao.getAllIngredients(recipeId);
    }

    /**
     * Method to get all steps for a recipe
     * @param recipeId to get steps for
     * @return list of steps through live data
     */
    public LiveData<List<StepEntity>> getSteps(int recipeId) {
        return mDao.getAllSteps(recipeId);
    }

    /**
     * Method to get all Images for a recipe
     * @param recipeId to get images for
     * @return list of images through live data
     */
    public LiveData<List<ImageEntity>> getImages(int recipeId) {
        return mDao.getAllImages(recipeId);
    }

    /**
     * Method to get all keyword for a recipe
     * @param recipeId to get keywords for
     * @return list of keywords through live data
     */
    public LiveData<List<KeywordEntity>> getKeywords(int recipeId) {
        return mDao.getAllKeywords(recipeId);
    }

    /**
     * Method to get all categories
     * @return list of categories through live data
     */
    public LiveData<List<CategoryEntity>> getCategories() {
        return mDao.getAllCategories();
    }

    /**
     * Method to add a category to the database
     * @param category to add
     */
    public void addCategory(String category) {
        // create category anf populate
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName(category);
        // start database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> {
            if (mDao.categoryExists(category) == 0) {
                mDao.insertCategory(categoryEntity);
            }
        });
    }

    /**
     * Method to get a recipe by recipe id
     * @param recipeId to get recipe for
     * @return recipe through live data
     */
    public LiveData<RecipeEntity> getRecipe(int recipeId) {
        return mDao.getRecipe(recipeId);
    }

    /**
     * Method to add a recipe to the database
     * @param recipe to add
     */
    public void addRecipe(RecipeEntity recipe) {
        // start database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertRecipe(recipe));
    }

    /**
     * Method to update recipe
     * @param recipe to update
     */
    public void updateRecipe(RecipeEntity recipe) {
        // start database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateRecipe(recipe));
    }

    /**
     * Method to ass an ingredient to the database
     * @param ingredient to add
     */
    public void insertIngredient(IngredientEntity ingredient) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertIngredient(ingredient));
    }

    /**
     * Method to add a step to the database
     * @param step to add
     */
    public void insertStep(StepEntity step) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertStep(step));
    }

    /**
     * Method to add an image to the database
     * @param image to add
     */
    public void insertImage(ImageEntity image) {
        if (image.getType().equals(INTERNET_TYPE)) {
            // start a new thread
            mExecutor.execute(() -> {
                try {
                    // retrieve height and weight of image
                    URL url = new URL(image.getImageUrl());
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    image.setHeight(bmp.getHeight());
                    image.setWidth(bmp.getWidth());
                } catch (IOException e) {
                    Log.e(TAG, "Exception for image: " + e.getMessage());
                }
                // start a database thread
                CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertImage(image));
            });
        } else {
            // start a database thread
            CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertImage(image));
        }
    }

    /**
     * Mehtod to update an ingredient in the database
     * @param ingredient to update
     */
    public void updateIngredient(IngredientEntity ingredient) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateIngredient(ingredient));
    }

    /**
     * Mehtod to update a step in the database
     * @param step to update
     */
    public void updateStep(StepEntity step) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateStep(step));
    }

    /**
     * Method to add a recipe with it's cook's notes, ingredients, steps, images and keywords
     * to the database
     * @param recipe to add
     * @param cookNotes to add
     * @param ingredients to add
     * @param steps to add
     * @param images to add
     * @param keywords to add
     */
    public void insertAll(RecipeEntity recipe, List<CookNoteEntity> cookNotes,
                          List<IngredientEntity> ingredients, List<StepEntity> steps,
                          List<ImageEntity> images, List<KeywordEntity> keywords) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> {
            // add recipe
            if (recipe == null || recipe.getName() == null) {
                return;
            }
            mDao.insertRecipe(recipe);
            // get recipe id
            int recipeId = mDao.getRecipeId(recipe.getName());
            // add recipe id to cook's notes
            if (cookNotes != null && cookNotes.size() > 0 ) {
                for (CookNoteEntity cookNote : cookNotes) {
                    cookNote.setRecipeId(recipeId);
                }
                // add cook's notes to database
                mDao.insertAllNotes(cookNotes);
            }
            // add recipe id to ingredients
            if (ingredients != null && ingredients.size() > 0) {
                for (IngredientEntity ingredient : ingredients) {
                    ingredient.setRecipeId(recipeId);
                }
                // add ingredients to database
                mDao.insertAllIngredients(ingredients);
            }
            // add recipe id to steps
            if (steps != null && steps.size() > 0) {
                for (StepEntity step : steps) {
                    step.setRecipeId(recipeId);
                }
                // add steps to database
                mDao.insertAllSteps(steps);
            }
            // add recipe id to images
            if (images != null && images.size() > 0) {
                for (ImageEntity image : images) {
                    image.setRecipeId(recipeId);
                }
                // add images to database
                mDao.insertAllImages(images);
            }
            // add recipe id to keywords
            if (keywords != null && keywords.size() > 0) {
                for (KeywordEntity keyword : keywords) {
                    keyword.setRecipeId(recipeId);
                }
                // add keywords to database
                mDao.insertAllKeywords(keywords);
            }
        });
    }

    /**
     * Mehtod to add keyword to database
     * @param keyword to add
     */
    public void insertKeyword(KeywordEntity keyword) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertKeyword(keyword));
    }

    /**
     * Method to remove an ingredient from the database
     * @param ingredient to remove
     */
    public void deleteIngredient(IngredientEntity ingredient) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteIngredient(ingredient));
    }

    /**
     * Method to remove a step from the database
     * @param step to remove
     */
    public void deleteStep(StepEntity step) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteStep(step));
    }

    /**
     * Mehtod to remove an image from the database and file storage
     * @param image to remove
     */
    public void deleteImage(ImageEntity image) {
        if (image.getType().equals(FILE_TYPE)) {
            // remove image file and database entry
            removeImage(image);
        } else {
            // start a database thread
            CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteImage(image));
        }
    }

    /**
     * Method to remove a keyword from the database
     * @param keyword to remove
     */
    public void deleteKeyword(KeywordEntity keyword) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteKeyword(keyword));
    }

    /**
     * Method to remove a category from the database
     * @param category to remove
     */
    public void deleteCategory(CategoryEntity category) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteCategory(category));
    }

    /**
     * Method to add image file with picasso
     * @param image to save
     */
    public void addImage(ImageEntity image) {
        if (image == null || image.getImageId() == 0 || image.getImageUrl() == null ||
                image.getImageUrl().isEmpty()) {
            Log.i(TAG, "No id or url");
            return;
        }
        // create url
        String urlString = image.getImageUrl();

        // use picasso to initialize save
        Picasso.get().load(urlString)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(saveImage(image));
    }

    /**
     * Method to remove image and update database
     * @param image to remove
     */
    public void removeImage(ImageEntity image) {
        if (image == null || image.getImageId() == 0 || image.getImageUrl() == null ||
                image.getImageUrl().isEmpty()) {
            Log.i(TAG, "No id or url");
            return;
        }
        // start on database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> {
            // delete image file
            if (deleteFile(image)) {
                // delete image from database
                mDao.deleteImage(image);
            } else {
                Log.e(TAG, "image not deleted");
            }
        });
    }

    /**
     * Method to save image file with picasso and update database entry
     * @param image to save
     * @return target
     */
    private Target saveImage(final ImageEntity image) {
        // Put context in a wrapper
        ContextWrapper contextWrapper = new ContextWrapper(mContext);
        // Get directory
        final File directory = contextWrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE);
        // get image file name
        String imageName = String.format(Locale.getDefault(), "image%d", image.getImageId());
        // save file
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // Create file that will be saved
                final File imageFile = new File(directory, imageName);
                // Create file stream
                FileOutputStream fileOutputStream = null;
                try {
                    // make new file stream
                    fileOutputStream = new FileOutputStream(imageFile);
                    // compress and save image file
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    CookbookRoomDatabase.databaseWriteExecutor.execute(() -> {
                    image.setImageUrl(imageFile.getAbsolutePath());
                    image.setType(FILE_TYPE);
                    mDao.updateImage(image);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fileOutputStream != null) {
                            // close file stream
                            fileOutputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            /**
             * Method to deal with failure
             * @param e exception thrown
             * @param errorDrawable that failed
             */
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            /**
             * Method to deal with placeholder (Not used)
             * @param placeHolderDrawable to set
             */
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
    }

    /**
     * Method to delete image file
     * @param image to delete
     * @return true if deleted false otherwise
     */
    private boolean deleteFile (ImageEntity image) {
        // get context wrapper
        ContextWrapper contextWrapper = new ContextWrapper(mContext);
        // set directory
        File directory = contextWrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE);
        // declare variable
        String fileName = String.format(Locale.getDefault(), "image%d", image.getImageId());
        // get image file
        File imageFile = new File(directory, fileName);
        // delete image file
        return imageFile.delete();
    }

    /**
     * Method to get all Cook's notes for a recipe from the database
     * @param recipeId to get the cook's notes for
     * @return list of cook's notes through live data
     */
    public LiveData<List<CookNoteEntity>> getNotes(int recipeId) {
        return mDao.getAllCookNotes(recipeId);
    }

    /**
     * Method to add a Cook's note to the database
     * @param cookNote to add
     */
    public void insertCookNote(CookNoteEntity cookNote) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertCookNote(cookNote));
    }

    /**
     * Method to update a cook's note in the database
     * @param cookNote to update
     */
    public void updateCookNote(CookNoteEntity cookNote) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateCoolNote(cookNote));
    }

    /**
     * Method to remove a cook's note from the database
     * @param cookNote to remove
     */
    public void deleteCookNote(CookNoteEntity cookNote) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteCookNote(cookNote));
    }

    public void deleteRecipe(RecipeEntity recipe) {
        // start a database thread
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteRecipe(recipe));
    }
}
