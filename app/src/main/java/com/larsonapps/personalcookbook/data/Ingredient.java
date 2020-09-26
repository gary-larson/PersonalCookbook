package com.larsonapps.personalcookbook.data;


import androidx.annotation.NonNull;

import java.util.Locale;

public class Ingredient {
    private String name;
    private double amount;
    private String measure;
    private String preparation;

    /**
     * Default constructor
     */
    public Ingredient() {
    }

    /**
     * Constructor for all variables
     * @param name to set
     * @param amount to set
     * @param measure to set
     * @param preparation to set
     */
    public Ingredient(String name, double amount, String measure, String preparation) {
        this.name = name;
        this.amount = amount;
        this.measure = measure;
        this.preparation = preparation;
    }

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
     * Getter for amount
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Setter for amount
     * @param amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Getter for measure
     * @return measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * Setter for measure
     * @param measure to set
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * Getter for preparation
     * @return preparation
     */
    public String getPreparation() {
        return preparation;
    }

    /**
     * Setter for preparation
     * @param preparation to set
     */
    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    /**
     * String presentation of Ingredient
     * @return presentation of Ingredient
     */
    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(),"%s %.1f %s\n%s",name, amount, measure, preparation);
    }
}
