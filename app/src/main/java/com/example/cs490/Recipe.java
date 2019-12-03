package com.example.cs490;

//import com.google.common.base.MoreObjects;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Recipe {
    public String name;
    public List<String> ingredients;
    //public final Nutrition nutrition;
    public ByteBuffer image;
    public Grade grade;
    public String url;
    public String urlId;
    public String prepTime;
    public String cookingTime;
    public String totalTime;

    public Recipe(String name,  List<String> ingredients, double score,int count, ByteBuffer image, String url, String urlId, String prepTime, String cookingTime, String totalTime)
    {
        this.name = Objects.requireNonNull(name);
        //this.nutrition = Objects.requireNonNull(nutrition);
        this.ingredients = Objects.requireNonNull(ingredients);
        this.grade = new Grade(score,count);
        this.image = image;
        this.url = url;
        this.urlId = urlId;
        this.prepTime = prepTime;
        this.cookingTime = cookingTime;
        this.totalTime = totalTime;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Optional<ByteBuffer> getImage() {
        return Optional.of(image);
    }

    public List<String> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    //public Nutrition getNutrition() { return nutrition; }


    /**
     * A grade is composed of the number of reviews for the recipe
     * as well as the rating of the recipe. This is used to create
     * a "grading" system to rank recipes.
     */
    public static class Grade {
        public final double rating;
        public final int numReviews;

        public Grade(double rating, int numReviews) {
            this.rating = Objects.requireNonNull(rating);
            this.numReviews = Objects.requireNonNull(numReviews);
        }

    }

    /**
     * Nutrition is the nutrition information of the recipe which includes
     * the calories as well as the caloric breakdown. It also includes
     * the amount of dietary cholesterol and the sodium level.
     *
     * Units are as follows (plans to update double type to unit class)
     * calories    : Calories
     * fat         : grams
     * carbs       : grams
     * protein     : grams
     * cholesterol : milligrams
     * sodium      : milligrams
     *
     *
    public static class Nutrition {
        private final double calories;
        private final double fat;
        private final double carbohydrates;
        private final double protein;
        private final double cholesterol;
        private final double sodium;

        public Nutrition(double calories, double fat, double carbohydrates, double protein, double cholesterol, double sodium) {
            this.calories = Objects.requireNonNull(calories);
            this.fat = Objects.requireNonNull(fat);
            this.carbohydrates = Objects.requireNonNull(carbohydrates);
            this.protein = Objects.requireNonNull(protein);
            this.cholesterol = Objects.requireNonNull(cholesterol);
            this.sodium = Objects.requireNonNull(sodium);
        }

        public double getCalories() {
            return calories;
        }

        public double getFat() {
            return fat;
        }

        public double getCarbohydrates() {
            return carbohydrates;
        }

        public double getProtein() {
            return protein;
        }

        public double getCholesterol() {
            return cholesterol;
        }

        public double getSodium() {
            return sodium;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("calories", calories)
                    .add("fat", fat)
                    .add("carbohydrates", carbohydrates)
                    .add("protein", protein)
                    .add("cholesterol", cholesterol)
                    .add("sodium", sodium)
                    .toString();
        }
    }
        */
}

