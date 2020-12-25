package hu.adventofcode2020;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day23Main {

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<Integer> cups = new LinkedList<>(Arrays.asList(9, 6, 2, 7, 1, 3, 8, 5, 4));
//        List<Integer> cups = new ArrayList<>(Arrays.asList(3,8,9,1,2,5,4,6,7));
        Map<Integer, ListIterator<Integer>> cupIteratorMap = new HashMap<>();


        int max = 9;
        for (int index = 10; index <= max; index++) {
            cups.add(index);
        }
        for (int index=0;index<cups.size();index++) {
            ListIterator<Integer> iterator = cups.listIterator(index);
            cupIteratorMap.put(iterator.next(), iterator);
        }
        int numberOfRounds = 100;
        int originalSize = cups.size();

        for (int roundCounter = 1; roundCounter <= numberOfRounds; roundCounter++) {

            int indexOfCurrentLabel = 0;
            int currentLabel = cups.getFirst();
            List<Integer> choosenCups = new LinkedList<>();
            ListIterator<Integer> iterator = cups.listIterator();
            iterator.next();
            for (int index = 0; index < 3; index++) {
                      choosenCups.add(iterator.next());
                      iterator.remove();
            }

            int destinationLabel = currentLabel - 1;
            while (choosenCups.contains(destinationLabel) || destinationLabel == 0) {
                destinationLabel--;
                if (destinationLabel <= 0) {
                    destinationLabel = originalSize;
                }
            }


//            int destIndex = cups.indexOf(destinationLabel);
//            cups.addAll((destIndex + 1) % originalSize, choosenCups);
            ListIterator<Integer> destIterator = cupIteratorMap.get(destinationLabel);
            ListIterator<Integer> choosenIterator = choosenCups.listIterator();
            for (int index = 0; index < 3; index++) {
                int value = choosenIterator.next();
                destIterator.add(value);
                destIterator.next();
            }
            cups.addLast(cups.removeFirst());

        }
        for (Integer cup : cups) {
            System.out.print(cup);
        }
        System.out.println();
    }

}
