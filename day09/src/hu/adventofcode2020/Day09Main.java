package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day09Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day09.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<Long>  numbers = bufferedReader.lines().map(Long::parseLong).collect(Collectors.toList());
        long invalid = getInvalid(numbers);
        long weaknes = getWeaknes(invalid, numbers);
        System.out.println(weaknes);

    }

    public static long getInvalid(List<Long> numbers) {
        List<Long> premble = new ArrayList<>();
        for (int index = 0; index < numbers.size(); index++) {
            if (index < 25) {
                premble.add(numbers.get(index));
            } else {
                boolean foundPair = false;
                for (Long firstOfTheSum : premble) {
                    long secondOfTheSum = numbers.get(index) - firstOfTheSum;
                    if (firstOfTheSum != secondOfTheSum && premble.contains(secondOfTheSum)) {
                        foundPair = true;
                        break;
                    }
                }
                if (foundPair) {
                    premble.remove(0);
                    premble.add(numbers.get(index));
                } else {
                    return numbers.get(index);
                }
            }
        }
        return 0L;
    }

    public static long getWeaknes(long invalid, List<Long> numbers) {
        List<Long> contset = new ArrayList<>();
        long contsetSum = 0L;
        for (int index = 0; index < numbers.size();) {
            if (contsetSum < invalid) {
                contset.add(numbers.get(index));
                contsetSum += numbers.get(index);
                index++;
            } else if (contsetSum > invalid) {
                contsetSum -= contset.get(0);
                contset.remove(0);
            } else {
                long min = contset.stream().min(Long::compareTo).get();
                long max = contset.stream().max(Long::compareTo).get();
                return min + max;
            }
        }
        return 0L;
    }
}
