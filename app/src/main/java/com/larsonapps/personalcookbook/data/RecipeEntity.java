package com.larsonapps.personalcookbook.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Locale;

@Entity(tableName = "recipes")
public class RecipeEntity implements Parcelable {
    // Declare Variables
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "short_description")
    private String shortDescription;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "servings")
    private int servings;
    @ColumnInfo(name = "prep_time")
    private int prepTime;
    @ColumnInfo(name = "cook_time")
    private int cookTime;
    @ColumnInfo(name = "total_time")
    private int totalTime;
    @ColumnInfo(name = "cook_notes")
    private String cookNotes;
    @ColumnInfo(name = "copyright")
    private String copyright;
    @ColumnInfo(name = "personal_note")
    private String personalNote;

    /**
     * Default Constructor
     */
    @Ignore
    public RecipeEntity() {}

    /**
     * Constructor for all arguments
     * @param id to set
     * @param name to set
     * @param shortDescription to set
     * @param description to set
     * @param servings to set
     * @param prepTime to set
     * @param cookTime to set
     * @param totalTime to set
     * @param cookNotes to set
     * @param copyright to set
     * @param personalNote to set
     */
    public RecipeEntity(int id, String name, String shortDescription, String description,
                        int servings, int prepTime, int cookTime, int totalTime, String cookNotes,
                        String copyright, String personalNote) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.servings = servings;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.cookNotes = cookNotes;
        this.copyright = copyright;
        this.personalNote = personalNote;
    }

    /**
     * Method to create a parcel
     */
    public static final Creator<RecipeEntity> CREATOR = new Creator<RecipeEntity>() {
        @Override
        public RecipeEntity createFromParcel(Parcel in) {
            return new RecipeEntity(in);
        }

        @Override
        public RecipeEntity[] newArray(int size) {
            return new RecipeEntity[size];
        }
    };

    /**
     * Getter for id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id to set
     */
    public void setId(int id) {this.id = id;}

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for short description
     * @return short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Setter for short description
     * @param shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Getter for description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for servings
     * @return servings
     */
    public int getServings() {
        return servings;
    }

    public String getServingsString() {
        return String.format(Locale.getDefault(), "%d Servings", servings);
    }

    /**
     * Setter for servings
     * @param servings to set
     */
    public void setServings(int servings) {
        this.servings = servings;
    }

    /**
     * Getter for prepTime in minutes
     * @return prepTime in minutes
     */
    public int getPrepTime() {
        return prepTime;
    }

    /**
     * Getter for preparation time string
     * @return preparation time string
     */
    public String getPrepTimeString() {
        return getString(prepTime);
    }

    /**
     * Getter for preparation time in minutes
     * @return preparation time string
     */
    public String getPrepTimeMinutesString() {return getString(prepTime, "Prep");}

    /**
     * Setter for prepTime in minutes
     * @param prepTime to set
     */
    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    /**
     * Getter for cookTime in minutes
     * @return cookTime in minutes
     */
    public int getCookTime() {
        return cookTime;
    }

    /**
     * Getter for cook time string
     * @return cook time string
     */
    public String getCookTimeString() {
        return getString(cookTime);
    }

    /**
     * Getter for cook time in minutes
     * @return cook time string
     */
    public String getCookTimeMinutesString() {return getString(cookTime, "Cook");}

    /**
     * Method to convert a time in minutes to a string
     * @param time to convert
     * @return string representation of the time
     */
    private String getString(int time) {
        String temp;
        int days = time / 1440;
        int hours;
        if (time > 1440) {
            hours = (time - (days * 1440)) / 60;
        } else {
            hours = time / 60;
        }
        int mins = time % 60;
        String day = "days";
        String hour = "hours";
        String min = "mins";
        if (days == 1) {
            day = "day";
        }
        if (hours == 1) {
            hour = "hour";
        }
        if (hours == 0 && days == 0) {
            if (mins == 1) {
                min = "minute";
            } else {
                min = "minutes";
            }
        } else {
            if (mins == 1) {
                min = "min";
            }
        }
        if (days > 0) {
            temp = String.format(Locale.getDefault(), "%d %s %d %s %d %s", days, day,
                    hours, hour, mins, min);
        } else if (hours > 0) {
            temp = String.format(Locale.getDefault(), "%d %s %d %s", hours, hour, mins, min);
        } else {
            temp = String.format(Locale.getDefault(), "%d %s", mins, min);
        }
        return temp;
    }

    /**
     * Method to get time string in minutes
     * @param time in minutes
     * @param string for type of time
     * @return string representation
     */
    private String getString(int time, String string) {
        return String.format(Locale.getDefault(), "%d %s mins", time, string);
    }

    /**
     * Setter for cookTime in minutes
     * @param cookTime to set
     */
    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    /**
     * Getter for totalTime in minutes
     * @return totalTime in minutes
     */
    public int getTotalTime() {
        return totalTime;
    }

    /**
     * Getter for total time string
     * @return total time string
     */
    public String getTotalTimeString() {
        return getString(totalTime);
    }

    /**
     * Getter for total time in minutes
     * @return total time string
     */
    public String getTotalTimeMinutesString() {return getString(totalTime, "Total");}

    /**
     * Setter for totalTime in minutes
     * @param totalTime to set
     */
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * Getter for notes
     * @return to set
     */
    public String getCookNotes() {
        return cookNotes;
    }

    /**
     * Setter for notes
     * @param cookNotes to set
     */
    public void setCookNotes(String cookNotes) {
        this.cookNotes = cookNotes;
    }

    /**
     * Getter for copyright
     * @return copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Setter for copyright
     * @param copyright to set
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Constructor to read a parcel
     * @param in parcel of data
     */
    @Ignore
    public RecipeEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.servings = in.readInt();
        this.prepTime = in.readInt();
        this.cookTime = in.readInt();
        this.totalTime = in.readInt();
        this.cookNotes = in.readString();
        this.copyright = in.readString();
        this.personalNote = in.readString();
    }

    /**
     * Method to descripe contents of the parce
     * @return parcel description
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Method to write to a parcel
     * @param dest parcel
     * @param flags for parcel
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeInt(this.servings);
        dest.writeInt(this.prepTime);
        dest.writeInt(this.cookTime);
        dest.writeInt(this.totalTime);
        dest.writeString(this.cookNotes);
        dest.writeString(this.copyright);
        dest.writeString(this.personalNote);
    }

    /**
     * Getter for personal note
     * @return personal note
     */
    public String getPersonalNote() {
        return personalNote;
    }

    /**
     * Setter for personal note
     * @param personalNote to set
     */
    public void setPersonalNote(String personalNote) {
        this.personalNote = personalNote;
    }
}
