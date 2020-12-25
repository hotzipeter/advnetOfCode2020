package hu.adventofcode2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bag {
    String color;
    int amount;
    List<Bag> containList;
    int bagcount;
    boolean isLast = false;

    Bag(String rule) {
        containList = new ArrayList<>();
        String[] splittedRule = rule.split( " bags contain");
        color= splittedRule[0];
        amount = 0;
        String containment = splittedRule[1];
        String[] bags = containment.split(", ");
        for (String bag: bags) {
            if (!bag.contains("no other bags")) {
                String[] splittedBag = bag.trim().split(" ");
                containList.add(new Bag(splittedBag[1].concat(" ").concat(splittedBag[2]), Integer.parseInt(splittedBag[0])));
            } else {
                isLast = true;
            }
        }
    }
    Bag(String color, int amount) {
        this.amount=amount;
        this.color= color;
        containList = new ArrayList<>();
    }

    Bag(String color, int amount, List<Bag> containList) {
        this.amount=amount;
        this.color= color;
        this.containList = containList;
    }

    public String getColor() {
        return color;
    }

    public int calculateBagCount() {
        bagcount = 0;
        if (isLast) {
            return 0;
        } else {
            for (Bag bag : containList) {
                bagcount += (bag.amount + bag.amount * bag.calculateBagCount());
                System.out.println(bag.amount +"*"+ bag.calculateBagCount());
            }
            return bagcount;
        }
    }

    public void fillBag(List<Bag> bags) {
        if (!isLast) {
            List<Bag> newContainList = new ArrayList<>();
            for (Bag bag : containList) {
                Bag foundBag = searchBag(bags, bag.color);
                foundBag.fillBag(bags);
                newContainList.add(new Bag(foundBag.getColor(), bag.amount, foundBag.containList));
            }
            containList = newContainList;
        }
    }

    public Bag searchBag(List<Bag> bags,String color) {
        return bags.stream().filter(bag -> bag.getColor().equals(color)).findFirst().orElse(null);
    }
}
