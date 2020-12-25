package hu.adventofcode2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Rule {
    private final List<Rule> rules;
    private String charString;
    private boolean isOrRelation;

    public Rule(String ruleString, Map<Long, String> ruleMap) {
        rules = new ArrayList<>();
        isOrRelation = false;
        charString = "";
        if (ruleString.contains("|")) {
            isOrRelation = true;
            for (String rule: ruleString.split("\\|")){
                rules.add(new Rule(rule.trim(), ruleMap));
            }
        } else if (ruleString.contains("\"")) {
            charString = String.valueOf(ruleString.charAt(1));
        } else {
            String[] splittedRuleList = ruleString.split(" ");
            for (String splittedRule:splittedRuleList) {
                rules.add(new Rule(ruleMap.get(Long.parseLong(splittedRule)), ruleMap));
            }
        }
    }

    public List<String> getMatchingValues() {
        if (!charString.isEmpty()) {
            return Collections.singletonList(charString);
        } else if (isOrRelation) {
            List<String> matchingValueList = new ArrayList<>();
            for (Rule rule: rules) {
                matchingValueList.addAll(rule.getMatchingValues());
            }
            return matchingValueList;
        } else {
            List<String> matchingValueList = rules.get(0).getMatchingValues();
            for (int index = 1; index<rules.size(); index++) {
                List<String> newMatchingValueList = new ArrayList<>();
                for (String newMatchingValue: rules.get(index).getMatchingValues()) {
                    for (String foundMatchingValue: matchingValueList) {
                        newMatchingValueList.add(foundMatchingValue.concat(newMatchingValue));
                    }
                }
                matchingValueList = newMatchingValueList;
            }
            return matchingValueList;
        }
    }
}
