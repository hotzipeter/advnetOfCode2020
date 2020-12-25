package hu.adventofcode2020;

import java.util.ArrayList;
import java.util.List;

public class ComplexExpression extends Expression{
    private List<Expression> expressionList;
    private List<Character> operationList;

    public ComplexExpression(String stringExpression) {
        expressionList = new ArrayList<>();
        operationList = new ArrayList<>();
        boolean paranthesisFound = false;
        StringBuilder expression = new StringBuilder();
        int paranthesiscounter = 0;
        for (char c : stringExpression.toCharArray()) {
            switch (c) {
                case '(':
                    if (paranthesiscounter>0) expression.append(c);
                    paranthesiscounter++;
                    paranthesisFound = true;
                    break;
                case ')':
                    paranthesiscounter--;
                    if (paranthesiscounter>0) expression.append(c);
                    break;
                case '+':
                case '*':
                    if (paranthesiscounter == 0) {
                        if (paranthesisFound) {
                            expressionList.add(new ComplexExpression(expression.toString()));
                        } else {
                            expressionList.add(new LongExpression(expression.toString()));
                        }
                        operationList.add(c);
                        paranthesisFound=false;
                        expression = new StringBuilder();
                    } else {
                        expression.append(c);
                    }
                    break;
                default:
                    if (c != ' ') {
                        expression.append(c);
                    }
                    break;
            }
        }

        if (paranthesisFound) {
            expressionList.add(new ComplexExpression(expression.toString()));
        } else {
            expressionList.add(new LongExpression(expression.toString()));
        }
    }

    @Override
    public long getValue() {
        while (expressionList.size()>1) {
            Long firstValue = expressionList.get(0).getValue();
            Long secondValue = expressionList.get(1).getValue();
            switch (operationList.get(0)) {
                case '+': secondValue+=firstValue;
                break;
                case '*': secondValue*=firstValue;
                    break;
            }
            expressionList.remove(1);
            expressionList.remove(0);
            operationList.remove(0);
            expressionList.add(0,new LongExpression(secondValue));
        }
        return expressionList.get(0).getValue();
    }

    @Override
    public long getValueWithPrecedence() {
        int operationIndex=0;
        while (operationList.contains('+')){
            if (operationList.get(operationIndex) == '+') {
                long firstValue = expressionList.get(operationIndex).getValueWithPrecedence();
                long secondValue = expressionList.get(operationIndex+1).getValueWithPrecedence();
                secondValue+=firstValue;
                expressionList.remove(operationIndex+1);
                expressionList.remove(operationIndex);
                operationList.remove(operationIndex);
                expressionList.add(operationIndex,new LongExpression(secondValue));
            } else {
                operationIndex++;
            }
        }
        while (expressionList.size()>1) {
            long firstValue = expressionList.get(0).getValueWithPrecedence();
            long secondValue = expressionList.get(1).getValueWithPrecedence();
            secondValue*=firstValue;
            expressionList.remove(1);
            expressionList.remove(0);
            operationList.remove(0);
            expressionList.add(0,new LongExpression(secondValue));
        }
        return expressionList.get(0).getValueWithPrecedence();
    }
}
