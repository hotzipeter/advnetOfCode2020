package hu.adventofcode2020;

public class Rule {
    String name;
    Range firstRange, secondRange;

    Rule(String ruleString) {
        name = ruleString.split(":")[0].trim();
        System.out.print(name+" ");
        String ranges = ruleString.split(":")[1].trim();
        String[] rangeArray = ranges.split(" or ");
        firstRange = new Range(rangeArray[0]);
        secondRange = new Range(rangeArray[1]);
    }

    public boolean isValueValidForRule(Long value) {
        return firstRange.isValueValid(value) || secondRange.isValueValid(value);
    }
}
