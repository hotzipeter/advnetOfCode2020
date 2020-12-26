package hu.adventofcode2020;

import org.apache.commons.collections4.list.CursorableLinkedList;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day23Main {

    public static void main(String[] args) throws FileNotFoundException {
        CursorableLinkedList<Integer> cups = new CursorableLinkedList<>(Arrays.asList(9, 6, 2, 7, 1, 3, 8, 5, 4));
//        CursorableLinkedList<Integer> cups = new CursorableLinkedList<>(Arrays.asList(3,8,9,1,2,5,4,6,7));
//        List<Integer> cups = new ArrayList<>(Arrays.asList(3,8,9,1,2,5,4,6,7));
        Map<Integer, ListIterator<Integer>> cupIteratorMap = new HashMap<>();


        int max = 9;
        for (int index = 10; index <= max; index++) {
            cups.add(index);
        }
        for (int index=0;index<cups.size();index++) {
            ListIterator<Integer> iterator = cups.listIterator(index);
            cupIteratorMap.put(iterator.next(), iterator);
            long end = new Date().getTime();
        }
        int numberOfRounds = 100;
        int originalSize = cups.size();

        for (int roundCounter = 1; roundCounter <= numberOfRounds; roundCounter++) {
//            System.out.println("-- move "+roundCounter+" --");
//            System.out.print("cups: ");
//            for (Integer cup : cups) {
//                System.out.print(cup);
//            }
//            System.out.println();
            int indexOfCurrentLabel = 0;
            long begin = new Date().getTime();
            int currentLabel = cups.getFirst();
            List<Integer> choosenCups = new LinkedList<>();
            ListIterator<Integer> iterator = cups.listIterator();
            iterator.next();
            for (int index = 0; index < 3; index++) {
                      choosenCups.add(iterator.next());
                      iterator.remove();
            }
//            System.out.print("pick up: ");
//            for (int index=0;index<3;index++) {
//                System.out.print(choosenCups.get(index));
//            }
//            System.out.println();
//            System.out.print("destination: ");
            int destinationLabel = currentLabel - 1;
            while (choosenCups.contains(destinationLabel) || destinationLabel == 0) {
                destinationLabel--;
                if (destinationLabel <= 0) {
                    destinationLabel = originalSize;
                }
            }

//            System.out.print(destinationLabel);
//            System.out.println();
//            int destIndex = cups.indexOf(destinationLabel);
//            cups.addAll((destIndex + 1) % originalSize, choosenCups);
            ListIterator<Integer> choosenIterator = choosenCups.listIterator();
            ListIterator<Integer> destIterator = cupIteratorMap.get(destinationLabel);
            int fistValue = choosenIterator.next();
            int secondValue = choosenIterator.next();
            int thirdValue = choosenIterator.next();
            destIterator.previous();
            int destIndex=destIterator.nextIndex();
            destIterator.next();
            destIterator.add(thirdValue);
            destIterator.previous();
            destIterator.add(secondValue);
            destIterator.previous();
            destIterator.add(fistValue);
            cupIteratorMap.put(destinationLabel,cups.listIterator(destIndex+1));
            cupIteratorMap.put(fistValue,cups.listIterator(destIndex+2));
            cupIteratorMap.put(secondValue,cups.listIterator(destIndex+3));
            cupIteratorMap.put(thirdValue,cups.listIterator(destIndex+4));
//            for (int index = 0; index < 3; index++) {
//                ListIterator<Integer> destIterator = cupIteratorMap.get(destinationLabel);
//                int value = choosenIterator.previous();
//                destIterator.add(value);
//                destIterator.previous();
//            }
            int value =cups.removeFirst();
            cups.addLast(value);
            cupIteratorMap.put(value,cups.listIterator(cups.size()));

            long end = new Date().getTime();
            System.out.println(end-begin);
        }
        System.out.println("-- final --");
        for (Integer cup : cups) {
            System.out.print(cup);
        }

        System.out.println();
    }

}
