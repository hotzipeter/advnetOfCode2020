package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day11Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day11.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<String>  lines = bufferedReader.lines().collect(Collectors.toList());
        Seats seats = new Seats(lines);
        System.out.println(seats.getOccupiedSeats());

    }
}
