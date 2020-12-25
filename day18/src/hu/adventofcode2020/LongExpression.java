package hu.adventofcode2020;

import lombok.Getter;

@Getter
public class LongExpression extends Expression{

    private final long expressionValue;

    public LongExpression(String expressionString) {
        this.expressionValue = Long.parseLong(expressionString);
    }

    public LongExpression(long expressionValue) {
        this.expressionValue = expressionValue;
    }

    @Override
    public long getValue() {
        return expressionValue;
    }

    @Override
    public long getValueWithPrecedence() {
        return expressionValue;
    }
}
