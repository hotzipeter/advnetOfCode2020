package hu.adventofcode2020;

import java.util.ArrayList;
import java.util.List;

public class CustomsDeclarationForm {
    long yesAnswers;
    List<Character> answers;

    CustomsDeclarationForm(List<String> lines) {
        answers = new ArrayList<>();
        yesAnswers = 0;
        for (char iterChar= 'a'; iterChar<= 'z'; iterChar++) {
            boolean foundInAll = true;
            for (String line: lines) {
                if (!line.contains(Character.toString(iterChar))) {
                    foundInAll = false;
                    break;
                }
            }
            if (foundInAll) {
                yesAnswers++;
            }
        }
    }
}
