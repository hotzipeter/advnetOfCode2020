package hu.adventofcode2020;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Food {
    private List<String> ingredients;
    private List<String> allergens;

    Food(String foodWithAllergens) {
        String[] splittedFoodWithAllergies = foodWithAllergens.split("\\(");
        ingredients = new ArrayList<>(Arrays.asList(splittedFoodWithAllergies[0].trim().split(" ")));
        String allergensString = splittedFoodWithAllergies[1];
        allergensString = allergensString.substring(9, allergensString.length()-1);
        allergens = new ArrayList<>(Arrays.asList(allergensString.split(", ")));
    }

    public boolean hasAllergen(String allergen) {
        return allergens.contains(allergen);
    }

    public boolean hasIngredient(String ingredient) {
        return ingredients.contains(ingredient);
    }

    public void removeIngredient(String ingredient) {
        ingredients.remove(ingredient);
    }

    public void removeAllergen(String allergen) {
        allergens.remove(allergen);
    }
}
