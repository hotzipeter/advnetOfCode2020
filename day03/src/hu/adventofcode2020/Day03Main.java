package hu.adventofcode2020;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day03Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day03.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());
        Slope slope = new Slope(lines,0,0);
        System.out.println(slope.getTreesInDirection(1,1));
        System.out.println(slope.getTreesInDirection(3,1));
        System.out.println(slope.getTreesInDirection(5,1));
        System.out.println(slope.getTreesInDirection(7,1));
        System.out.println(slope.getTreesInDirection(1,2));
        System.out.println(slope.getTreesInDirection(1,1)*slope.getTreesInDirection(3,1)*slope.getTreesInDirection(5,1)*slope.getTreesInDirection(7,1)*slope.getTreesInDirection(1,2));
    }
}
