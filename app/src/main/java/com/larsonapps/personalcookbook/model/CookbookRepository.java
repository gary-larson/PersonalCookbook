package com.larsonapps.personalcookbook.model;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.data.CategoryEntity;
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

    public CookbookRepository(Context context) {
        // Initialize variables
        CookbookRoomDatabase cookbookRoomDatabase = CookbookRoomDatabase.getDatabase(context);
        mExecutor = new CookbookExecutor();
        mDao = cookbookRoomDatabase.cookbookDao();
        mContext = context;
    }

    public LiveData<List<RecipeEntity>> getRecipes () {
        return mDao.getAllRecipes();
    }

    public LiveData<List<RecipeEntity>> getRecipes(String keyword) {
        return mDao.getAllRecipes(keyword);
    }

    public LiveData<List<RecipeEntity>> getRecipes(String[] keywords) {
        return mDao.getAllRecipes(keywords);
    }

    public LiveData<List<IngredientEntity>> getIngredients(int recipeId) {
        return mDao.getAllIngredients(recipeId);
    }

    public LiveData<List<StepEntity>> getSteps(int recipeId) {
        return mDao.getAllSteps(recipeId);
    }

    public LiveData<List<ImageEntity>> getImages(int recipeId) {
        return mDao.getAllImages(recipeId);
    }

    public LiveData<List<KeywordEntity>> getKeywords(int recipeId) {
        return mDao.getAllKeywords(recipeId);
    }

    public LiveData<List<CategoryEntity>> getCategories() {
        return mDao.getAllCategories();
    }

    public void addCategory(String category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName(category);
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> {
            if (mDao.categoryExists(category) == 0) {
                mDao.insertCategory(categoryEntity);
            }
        });
    }

    public LiveData<RecipeEntity> getRecipe(int recipeId) {
        return mDao.getRecipe(recipeId);
    }

    public void addRwcipe(RecipeEntity recipe) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertRecipe(recipe));
    }

    public int getRecipeId(String name) {
        return mDao.getRecipeIdByName(name);
    }

    public void updateRecipe(RecipeEntity recipe) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateRecipe(recipe));
    }

    public void insertIngredient(IngredientEntity ingredient) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertIngredient(ingredient));
    }

    public void insertStep(StepEntity step) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertStep(step));
    }

    public void insertImage(ImageEntity image) {
        if (image.getType().equals(INTERNET_TYPE)) {
            mExecutor.execute(() -> {
                try {
                    URL url = new URL(image.getImageUrl());
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    image.setHeight(bmp.getHeight());
                    image.setWidth(bmp.getWidth());
                } catch (IOException e) {
                    Log.e(TAG, "Exception for image: " + e.getMessage());
                }
                CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertImage(image));
            });
        } else {
            CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertImage(image));
        }
    }

    public void updateIngredient(IngredientEntity ingredient) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateIngredient(ingredient));
    }

    public void updateStep(StepEntity step) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateStep(step));
    }

    public void insertAll(RecipeEntity recipe, List<IngredientEntity> ingredients,
                          List<StepEntity> steps, List<ImageEntity> images,
                          List<KeywordEntity> keywords) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDao.insertRecipe(recipe);
            int recipeId = mDao.getRecipeIdByName(recipe.getName());
            if (ingredients.size() > 0) {
                for (IngredientEntity ingredient : ingredients) {
                    ingredient.setRecipeId(recipeId);
                }
                mDao.insertAllIngredients(ingredients);
            }
            if (steps.size() > 0) {
                for (StepEntity step : steps) {
                    step.setRecipeId(recipeId);
                }
                mDao.insertAllSteps(steps);
            }
            if (images.size() > 0) {
                for (ImageEntity image : images) {
                    image.setRecipeId(recipeId);
                }
                mDao.insertAllImages(images);
            }
            if (keywords.size() > 0) {
                for (KeywordEntity keyword : keywords) {
                    keyword.setRecipeId(recipeId);
                }
                mDao.insertAllKeywords(keywords);
            }
        });
    }

    public void insertKeyword(KeywordEntity keyword) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertKeyword(keyword));
    }

    public void deleteIngredient(IngredientEntity ingredient) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteIngredient(ingredient));
    }

    public void deleteStep(StepEntity step) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteStep(step));
    }

    public void deleteImage(ImageEntity image) {
        if (image.getType().equals(FILE_TYPE)) {
            // remove image file and database entry
            removeImage(image);
        } else {
            CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteImage(image));
        }
    }

    public void deleteKeyword(KeywordEntity keyword) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.deleteKeyword(keyword));
    }

    public void deleteCategory(CategoryEntity category) {
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
}
