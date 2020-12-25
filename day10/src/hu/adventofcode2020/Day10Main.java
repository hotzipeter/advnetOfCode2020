package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day10Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day10.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<Long>  numbers = bufferedReader.lines().map(Long::parseLong).sorted().collect(Collectors.toList());
        int oneDiff = numbers.get(0) == 1 ? 1 : 0, threeDiff = numbers.get(0) == 3 ? 2 : 1;
        for(int index = 0; index<numbers.size()-1; index++) {
            Long actual = numbers.get(index);
            Long next = numbers.get(index + 1);
            if (next - actual == 1) {
                oneDiff++;
            } else if (next - actual == 3) {
                threeDiff++;
            }
        }
        int solution = oneDiff*threeDiff;
        System.out.println(solution);
        long possibleWays = Adapter.getAllPossibleWays(numbers);
        System.out.println(possibleWays);
    }
}
