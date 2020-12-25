package hu.adventofcode2020;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day11Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day22.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<Integer> firstDeck = new ArrayList<>();
        List<Integer> secondDeck = new ArrayList<>();
        int state = 0;
        for (String line: bufferedReader.lines().collect(Collectors.toList())) {
            if (line.isEmpty()) {
                state++;
            } else if (state == 0 && !line.startsWith("Player")) {
                firstDeck.add(Integer.parseInt(line));
            } else if (state == 1 && !line.startsWith("Player")) {
                secondDeck.add(Integer.parseInt(line));
            }
        }

        playSubGame(firstDeck,secondDeck);
    }

    public static int playSubGame(List<Integer> firstDeck, List<Integer> secondDeck) {
        List<String> orders = new ArrayList<>();
        while (!firstDeck.isEmpty() && !secondDeck.isEmpty() && !orders.contains(getOrderString(firstDeck,secondDeck))) {
            orders.add(getOrderString(firstDeck,secondDeck));
            int firstTop = firstDeck.get(0);
            int secondTop = secondDeck.get(0);
            firstDeck.remove(0);
            secondDeck.remove(0);
            if (firstTop <= firstDeck.size() && secondTop <= secondDeck.size()) {
                int subGameWinner = playSubGame(new ArrayList<>(firstDeck.subList(0,firstTop)),new ArrayList<>(secondDeck.subList(0,secondTop)));
                if (subGameWinner == 1) {
                    firstDeck.add(firstTop);
                    firstDeck.add(secondTop);
                } else {
                    secondDeck.add(secondTop);
                    secondDeck.add(firstTop);
                }
            } else {
                if (firstTop > secondTop) {
                    firstDeck.add(firstTop);
                    firstDeck.add(secondTop);
                } else {
                    secondDeck.add(secondTop);
                    secondDeck.add(firstTop);
                }
            }
        }

        List<Integer> winnerDeck = firstDeck.isEmpty() ? secondDeck : firstDeck;
        int score = 0;
        for (int index = 0; index<winnerDeck.size(); index++) {
            score = score + winnerDeck.get(index) * (winnerDeck.size()-index);
        }
        System.out.println(score);

        return firstDeck.isEmpty() ? 2 : 1;
    }

    public static String getOrderString(List<Integer> firstList, List<Integer> secondList) {
        String firstListString = firstList.stream().map(String::valueOf).collect(Collectors.joining(","));
        String secondListString = secondList.stream().map(String::valueOf).collect(Collectors.joining(","));
        return firstListString.concat(secondListString);
    }
}
