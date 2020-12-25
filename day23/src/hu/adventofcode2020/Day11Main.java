package hu.adventofcode2020;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day11Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> cups = new LinkedList<>(Arrays.asList(9, 6, 2, 7, 1, 3, 8, 5, 4));
//        List<Integer> cups = new ArrayList<>(Arrays.asList(3,8,9,1,2,5,4,6,7));


        int max = 9;
        for (int index = 10; index <= max; index++) {
            cups.add(index);
        }
        int numberOfRounds = 100;
        int originalSize = cups.size();
        int indexOfCurrentLabel = 0;
        for (int roundCounter = 1; roundCounter <= numberOfRounds; roundCounter++) {


            int currentLabel = cups.get(indexOfCurrentLabel);

            List<Integer> choosenCups = new LinkedList<>();
            for (int index = 0; index < 3; index++) {
                if ((indexOfCurrentLabel + 1) < originalSize - choosenCups.size()) {
                    choosenCups.add(cups.remove((indexOfCurrentLabel + 1)));
                } else {
                    choosenCups.add(cups.remove(0));
                    indexOfCurrentLabel--;
                }
            }

            int destinationLabel = currentLabel - 1;
            while (choosenCups.contains(destinationLabel) || destinationLabel == 0) {
                destinationLabel--;
                if (destinationLabel <= 0) {
                    destinationLabel = originalSize;
                }
            }


            int destIndex = cups.indexOf(destinationLabel);
            cups.addAll((destIndex + 1) % originalSize, choosenCups);
            if (destIndex < indexOfCurrentLabel) indexOfCurrentLabel += 3;
            indexOfCurrentLabel = (indexOfCurrentLabel + 1) % originalSize;

        }
        for (Integer cup : cups) {
            System.out.print(cup);
        }
        System.out.println();
    }

}
