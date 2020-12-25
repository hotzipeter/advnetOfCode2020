package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day04Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day05.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<Integer> seatIds = bufferedReader.lines().map(BoardingCard::new).map(BoardingCard::getSeatID).sorted().collect(Collectors.toList());
        for (Integer seatId : seatIds) {
            if (!seatIds.contains(seatId+1))  {
                System.out.println(seatId+1);
                break;
            }
        }
    }
}
