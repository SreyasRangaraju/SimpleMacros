package com.sreyas.simplemacros;

import org.joda.time.LocalDate;

import java.io.Serializable;

public class DayMacroRecord implements Serializable {
    private final LocalDate date;
    private int calories;
    private int proteinGrams;

    public DayMacroRecord() {
        this.date = new LocalDate();
    }

    @Override
    public String toString() {
        return "DayMacroRecord{" +
                "date=" + date +
                ", calories=" + calories +
                ", proteinGrams=" + proteinGrams +
                '}';
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        return date.toString("E MMM dd");
    }

    public int getCalories() {
        return calories;
    }

    public String getCaloriesString() {
        return String.valueOf(calories);
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteinGrams() {
        return proteinGrams;
    }

    public String getProteinGramsString() {
        return String.valueOf(proteinGrams);
    }

    public void setProteinGrams(int proteinGrams) {
        this.proteinGrams = proteinGrams;
    }

    public void addMeal(int calories, int proteinGrams) {
        this.calories += calories;
        this.proteinGrams += proteinGrams;
    }

    public void reset() {
        this.calories = 0;
        this.proteinGrams = 0;
    }

}
