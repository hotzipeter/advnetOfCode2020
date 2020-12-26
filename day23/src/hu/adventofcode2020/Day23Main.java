package hu.adventofcode2020;

import java.io.FileNotFoundException;
import java.util.*;

public class Day23Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> cups = new ArrayList<>(Arrays.asList(9, 6, 2, 7, 1, 3, 8, 5, 4));
//        ArrayList<Integer> cups = new ArrayList<>(Arrays.asList(3,8,9,1,2,5,4,6,7));

        Map<Integer, Integer> cupIteratorMap = new HashMap<>();

        int max = 1000000;
        for (int index = 1; index <= max; index++) {
            if (index<=8) {
                cupIteratorMap.put(cups.get(index-1),cups.get(index));
            } else if (index == 9) {
                cupIteratorMap.put(cups.get(index-1),max<=9 ? cups.get(0):index+1);
            } else {
                cupIteratorMap.put(index,index==max ? cups.get(0):index+1);
            }
        }


        int numberOfRounds = 10000000;
        int currentLabel = cups.get(0);
        for (int roundCounter = 1; roundCounter <= numberOfRounds; roundCounter++) {

            long begin = new Date().getTime();

            List<Integer> choosenCups = new LinkedList<>();
            int iteratorNum = currentLabel;
            for (int index = 0; index < 3; index++) {
                      iteratorNum= cupIteratorMap.get(iteratorNum);
                      choosenCups.add(iteratorNum);
            }
            cupIteratorMap.put(currentLabel, cupIteratorMap.get(iteratorNum));
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
                    destinationLabel = max;
                }
            }

            int destNum = cupIteratorMap.get(destinationLabel);
            cupIteratorMap.put(destinationLabel,choosenCups.get(0));
            cupIteratorMap.put(choosenCups.get(2),destNum);
            currentLabel = cupIteratorMap.get(currentLabel);

        }
        System.out.println("-- final --");
        int num = 1;
        int num2 = cupIteratorMap.get(num);
        int num3 = cupIteratorMap.get(num2);
        long multyp = (long) num2 * (long) num3;

        System.out.println(num2+" "+num3);
        System.out.println(multyp);
//        for (int index=1;index<=max;index++) {
//            num = cupIteratorMap.get(num);
//            System.out.print(num);
//        }

        System.out.println();
    }

}
