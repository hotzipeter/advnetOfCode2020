package hu.adventofcode2020;

public class Range {
    long bottomValue, topValue;

    public Range(String rangeString) {
        String[] splittedRangeString = rangeString.trim().split("-");
        bottomValue = Long.parseLong(splittedRangeString[0].trim());
        topValue = Long.parseLong(splittedRangeString[1].trim());
        System.out.println(bottomValue+" "+topValue);
    }

    public Range(long bottomValue, long topValue) {
        this.bottomValue = bottomValue;
        this.topValue = topValue;
    }

    public boolean isValueValid(long value) {
        return value>=bottomValue && value<=topValue;
    }
}
