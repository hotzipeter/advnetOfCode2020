package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day07Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day07.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<String> searchColors = new ArrayList<>();
        int sum = 0;
        searchColors.add("shiny gold");
        List<Bag> bagRule = bufferedReader.lines().map(Bag::new).collect(Collectors.toList());
        Bag gold =searchBag(bagRule, "shiny gold");
        gold.fillBag(bagRule);
        gold.amount = 0;
        System.out.println(gold.calculateBagCount());
    }

    public static List<String> searchColors(List<Bag> bags, List<String> colors) {
        return bags.stream().filter(bag ->
                bag.containList.stream().map(Bag::getColor).anyMatch(colors::contains)
        ).map(Bag::getColor).collect(Collectors.toList());
    }

    public static Bag searchBag(List<Bag> bags,String color) {
        return bags.stream().filter(bag -> bag.getColor().equals(color)).findFirst().orElse(null);
    }
}
