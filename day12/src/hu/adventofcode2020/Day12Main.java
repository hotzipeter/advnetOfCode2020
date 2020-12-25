package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day12Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day12.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<Movement>  movements = bufferedReader.lines().map(line -> {
            switch (line.charAt(0)) {
                case 'N':
                case 'S':
                case 'W':
                case 'E':
                case 'F':
                    return new Moving(line);
                case 'L':
                case 'R':
                    return new Turning(line);
                default:
                    return null;
            }
        }).collect(Collectors.toList());
        Boat boat = new Boat(movements);
        boat.doMovements();
        System.out.println(boat.getManhattanDistanceFromStartPoint());

    }
}
