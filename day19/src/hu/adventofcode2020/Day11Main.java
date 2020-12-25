package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day11Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day19.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<String>  lines = bufferedReader.lines().collect(Collectors.toList());
        Map<Long, String> ruleMap = new HashMap<>();
        List<String> arrivedMessages = new ArrayList<>();
        int emtpyLineCounter = 0;
        for (String line: lines) {
            if (line.isEmpty()) {
                emtpyLineCounter++;
            }
            if (emtpyLineCounter == 0) {
                String[] splittedRule = line.split(":");
                ruleMap.put(Long.parseLong(splittedRule[0]),splittedRule[1].trim());
            } else {
                arrivedMessages.add(line);
            }
        }
        Rule rule31 = new Rule(ruleMap.get(31L), ruleMap);
        Rule rule42 = new Rule(ruleMap.get(42L), ruleMap);
        List<String> matchingValues31 = rule31.getMatchingValues();
        List<String> matchingValues42 = rule42.getMatchingValues();
        long matchingValueCount = arrivedMessages.stream().filter(s -> {
            String modifiedString = s;
            int match42 = 0;
            int match31 = 0;
            while (modifiedString.length()> 0 && matchingValues42.contains(modifiedString.substring(0,8))) {
                match42++;
                modifiedString = modifiedString.substring(8);
            }
            while (modifiedString.length()> 0 && matchingValues31.contains(modifiedString.substring(0,8))) {
                match31++;
                modifiedString = modifiedString.substring(8);
            }
            return match42 >match31 && match42>0 && match31>0 && modifiedString.length()==0 ;
        }).count();
        System.out.println(matchingValueCount);
    }
}
