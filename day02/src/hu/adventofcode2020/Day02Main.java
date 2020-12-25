package hu.adventofcode2020;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day02Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day02.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        long validPasswords = bufferedReader.lines().map(PasswordWithRule::new).filter(PasswordWithRule::isValidSecondTask).count();
        System.out.println(validPasswords);
    }
}
