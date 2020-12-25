package hu.adventofcode2020;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day16Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day16.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());
        int state = 0;
        List<Rule> rules = new ArrayList<>();
        List<Ticket> nearbyTickets = new ArrayList<>();
        Ticket myTicket = new Ticket();
        for (String line : lines) {
            if (line.isBlank()) {
                state++;
            } else {
                switch (state) {
                    case 0:
                        rules.add(new Rule(line));
                        break;
                    case 1:
                        if (line.contains(",")) {
                            myTicket = new Ticket(line);
                        }
                        break;
                    case 2:
                        if (line.contains(",")) {
                            nearbyTickets.add(new Ticket(line));
                        }
                        break;
                }
            }
        }
        long sumOfInvalidFields = 0;
        for (Ticket ticket: nearbyTickets) {
            sumOfInvalidFields += ticket.getInvalidFieldsSumByRuleList(rules);
        }
        System.out.println(sumOfInvalidFields);
        nearbyTickets.removeIf(ticket -> ticket.getInvalidFieldsSumByRuleList(rules)>0);
        nearbyTickets.add(myTicket);
        sumOfInvalidFields = 1;
        List<String> fieldOrder = Ticket.getRuleOrder(nearbyTickets,rules);
        for (int index = 0;index< myTicket.fieldValues.size();index++) {
            if (fieldOrder.get(index).startsWith("departure")) {
                sumOfInvalidFields *= myTicket.fieldValues.get(index);
            }
        }
        System.out.println(sumOfInvalidFields);
    }
}
