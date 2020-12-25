package hu.adventofcode2020;

import java.util.*;
import java.util.stream.Collectors;

public class Ticket {
    List<Long> fieldValues;

    Ticket() {
        fieldValues = null;
    }

    Ticket(String stringFieldValues) {
        fieldValues = Arrays.stream(stringFieldValues.split(",")).map(Long::parseLong).collect(Collectors.toList());
    }

    public long getInvalidFieldsSumByRuleList(List<Rule> rules) {
        long sumOfInvalidFileds = 0;
        for (Long fieldValue: fieldValues) {
            boolean isValid = false;
            for (Rule rule: rules) {
                if (rule.isValueValidForRule(fieldValue)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                sumOfInvalidFileds += 1;
            }
        }
        return sumOfInvalidFileds>0 ? 1: 0;
    }

    public static List<String> getRuleOrder(List<Ticket> tickets, List<Rule> rules) {
        int ticketValueSize = tickets.get(0).fieldValues.size();
        Map<Integer,ArrayList<String>> orderPos = new HashMap<>();
        Map<Integer,String> orderPosReduced = new HashMap<>();
        for (int index = 0; index<ticketValueSize; index++ ) {
            orderPos.put(index,new ArrayList<>());
            for (Rule rule : rules) {
                int finalIndex = index;
                boolean isValidForAll = tickets.stream().allMatch(ticket -> rule.isValueValidForRule(ticket.fieldValues.get(finalIndex)));
                if (isValidForAll) {
                        List<String> names = orderPos.get(finalIndex);
                        names.add(rule.name);
                }
            }
        }

        while (orderPosReduced.size() != ticketValueSize) {
            for (int index = 0; index < ticketValueSize; index++) {
                List<String> names = orderPos.get(index);
                names.removeIf(orderPosReduced::containsValue);
                if (names.size() == 1) {
                    orderPosReduced.put(index, names.get(0));
                }
            }
        }
        return new ArrayList<>(orderPosReduced.values());
    }
}
