package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day06Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day06.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());
        List<String> customsDeclarationFormLines = new ArrayList<>();
        long sumYesAnswers = 0;
        for (String line : lines) {
            if (line.isEmpty()) {
                CustomsDeclarationForm customsDeclarationForm = new CustomsDeclarationForm(customsDeclarationFormLines);
                sumYesAnswers += customsDeclarationForm.yesAnswers;
                customsDeclarationFormLines = new ArrayList<>();
            } else {
                customsDeclarationFormLines.add(line);
            }
        }
        CustomsDeclarationForm customsDeclarationForm = new CustomsDeclarationForm(customsDeclarationFormLines);
        sumYesAnswers += customsDeclarationForm.yesAnswers;
        System.out.println(sumYesAnswers);
    }
}
