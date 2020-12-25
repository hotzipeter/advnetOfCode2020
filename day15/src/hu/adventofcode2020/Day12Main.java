package hu.adventofcode2020;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day12Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day14.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<Long> numbers = Arrays.stream(bufferedReader.lines().collect(Collectors.toList()).get(0).split(",")).map(Long::parseLong).collect(Collectors.toList());
        Map<Long, Integer> occuranceMap = new HashMap<>();
        int numbersSize = numbers.size();
        for (int index=0;index<30000000;index++) {
            if (index<numbersSize-1) {
                occuranceMap.put(numbers.get(index),index);
            } else {
                if (occuranceMap.containsKey(numbers.get(index))) {
                    numbers.add((long)(index-occuranceMap.get(numbers.get(index))));
                } else {
                    numbers.add(0L);
                }
                occuranceMap.put(numbers.get(index),index);
            }
        }
        System.out.println(numbers.get(30000000-1));
    }
}
