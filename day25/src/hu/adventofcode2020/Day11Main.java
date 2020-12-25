package hu.adventofcode2020;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day11Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day25.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());
        long cardPublicKey = Long.parseLong(lines.get(0));
        long doorPublicKey = Long.parseLong(lines.get(1));
        long cardLoopSize = getLoopSize(cardPublicKey);
        long doorLoopSize = getLoopSize(doorPublicKey);
        System.out.println(doLoop(cardPublicKey,doorLoopSize));
    }

    public static long getLoopSize(long publicKey) {
        int value= 1;
        int base = 7;
        long loopSize = 0;
        while (value!= publicKey) {
            value = (value*base)%20201227;
            loopSize++;
        }
        return loopSize;
    }

    public static long doLoop(long publicKey, long times) {
        long value= 1;
        long loopSize = 0;
        while (times != loopSize) {
            value = (value* publicKey)%20201227;
            loopSize++;
        }
        return value;
    }
}
