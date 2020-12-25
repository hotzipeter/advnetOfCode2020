package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day12Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day14.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        MemoryProcessor memoryProcessor = new MemoryProcessor(bufferedReader.lines().collect(Collectors.toList()));
        System.out.println(memoryProcessor.getSumOfMemory());
    }
}
