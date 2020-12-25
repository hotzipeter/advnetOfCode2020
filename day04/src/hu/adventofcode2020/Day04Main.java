package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day04Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day04.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());
        List<String> passportLines = new ArrayList<>();
        int validPassportsCount = 0;
        for (String line : lines) {
            if (line.isEmpty()) {
                Passport passport = new Passport(passportLines);
                if (passport.isValid()) {
                    validPassportsCount++;
                }
                passportLines = new ArrayList<>();
            } else {
                passportLines.add(line);
            }
        }
        Passport passport = new Passport(passportLines);
        if (passport.isValid()) {
            validPassportsCount++;
        }
        System.out.println(validPassportsCount);
    }
}
