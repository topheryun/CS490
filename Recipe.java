package ProjectRF;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.google.common.base.MoreObjects;

public class Recipe {
    public final String name;
    public final List<String> ingredients;
    //public final Nutrition nutrition;
    public final ByteBuffer image;
    public final Grade grade;
    public final String url;
    public final String urlId;
    public final String prepTime;
    public final String cookingTime;
    public final String totalTime;    

//    public Recipe()
//    {
//        this.name = "";
//        //this.nutrition = Objects.requireNonNull(nutrition);
//        this.ingredients = new ArrayList<>();
//        this.grade = new Grade(0, 0);
//        this.image = null;
//        this.url = "";
//        this.urlId = "";
//        this.prepTime = "";
//        this.cookingTime = "";
//        this.totalTime = "";
//    }

    
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

    public Optional<ByteBuffer> getImage() {
        return Optional.of(image);
    }

    public List<String> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

   public Nutrition getNutrition() {
        return nutrition;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("ingredients", ingredients)
                .add("nutrition", nutrition)
                .add("image", image)
                .add("grade", grade)
                .toString();
    }

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

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("rating", rating)
                    .add("numReviews", numReviews)
                    .toString();
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
     */
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
}