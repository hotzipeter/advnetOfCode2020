package hu.adventofcode2020;

public class PasswordWithRule {
    private char[] password;
    private final int minCharConainment;
    private final int maxCharConainment;
    private int charContainmentNumber = 0;
    private char searchChar;

    PasswordWithRule(String passwordWithRuleLine) {
        String[] passwordWithRuleLinePieces = passwordWithRuleLine.split(" ");
        String[] rulePieces = passwordWithRuleLinePieces[0].split("-");
        this.password = passwordWithRuleLinePieces[2].toCharArray();
        this.searchChar = passwordWithRuleLinePieces[1].charAt(0);
        this.minCharConainment = Integer.parseInt(rulePieces[0]);
        this.maxCharConainment = Integer.parseInt(rulePieces[1]);
        for(char actualChar: password) {
            if (actualChar == searchChar) {
                charContainmentNumber++;
            }
        }
    }

    boolean isValid() {
        return charContainmentNumber >= minCharConainment && charContainmentNumber<= maxCharConainment;
    }

    boolean isValidSecondTask() {
        return searchChar == password[minCharConainment -1] ^ searchChar == password[maxCharConainment-1];
    }
}
