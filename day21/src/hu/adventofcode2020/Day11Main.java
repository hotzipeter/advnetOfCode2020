package hu.adventofcode2020;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day11Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day21.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        Map<String,String> allergenIngredientMap = new HashMap<>();
        List<Food> foods = bufferedReader.lines().map(Food::new).collect(Collectors.toList());
        for (Food food: foods) {
            for (String allergen: food.getAllergens()) {
                    allergenIngredientMap.put(allergen, "");
            }
        }

        while(allergenIngredientMap.containsValue("")) {
            List<String> searchAllergenList = allergenIngredientMap.keySet().stream().filter(s -> allergenIngredientMap.get(s).isEmpty()).collect(Collectors.toList());
            for (String allergen: searchAllergenList) {
                List<String> allMatchingIngredients = new ArrayList<>();
                List<Food> hasAllergenFoods = foods.stream().filter(food -> food.hasAllergen(allergen)).collect(Collectors.toList());
                Food firstFoodWithAllergen = hasAllergenFoods.get(0);
                hasAllergenFoods.remove(0);
                for (String ingredient: firstFoodWithAllergen.getIngredients()) {
                    int counter = 0;
                    for (Food food: hasAllergenFoods) {
                        if (food.hasIngredient(ingredient)) {
                            counter++;
                        }
                    }
                    if (counter == hasAllergenFoods.size()) {
                        allMatchingIngredients.add(ingredient);
                    }
                }
                if (allMatchingIngredients.size() == 1) {
                    allergenIngredientMap.put(allergen, allMatchingIngredients.get(0));
                    for (Food food: foods) {
                        food.removeIngredient(allMatchingIngredients.get(0));
                        food.removeAllergen(allergen);
                    }
                }
            }
        }

        int counter = 0;
        for (Food food: foods) {
            counter += food.getIngredients().size();
        }

        System.out.println(allergenIngredientMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(Map.Entry::getValue).collect(Collectors.joining(",")));

        System.out.println(counter);
    }
}
