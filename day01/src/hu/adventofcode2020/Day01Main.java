package hu.adventofcode2020;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day01Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day01.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<Integer> listOfNumbers = bufferedReader.lines().map(Integer::valueOf).collect(Collectors.toList());
        int searchSum = 2020;
        int listSize = listOfNumbers.size();
        boolean matchFound = false;
        for (int index1 = 0; index1 < listSize && !matchFound; index1++) {
            int firstNumber = listOfNumbers.get(index1);
            for (int index2 = 0; index2 < listSize && !matchFound; index2++) {
                int secondNumber = listOfNumbers.get(index2);
                for (int index3 = 0; index3 < listSize && !matchFound; index3++) {
                    int thirdNumber = listOfNumbers.get(index3);
                    if (firstNumber + secondNumber + thirdNumber == 2020) {
                        matchFound = true;
                        System.out.println(index1+" "+index2+" "+index3);
                        System.out.println(firstNumber * secondNumber * thirdNumber);
                    }
                }
            }
        }
    }
}
