package hu.adventofcode2020;

import java.util.ArrayList;
import java.util.List;

public class Adapter {
    long joltage;
    long possibleWaysToThis;

    Adapter(long joltage) {
        this.joltage = joltage;
        possibleWaysToThis = 1;
    }

    Adapter(long joltage, List<Adapter> adapters) {
        this.joltage = joltage;
        this.possibleWaysToThis = 0;
        for (Adapter adapter : adapters) {
            if (adapter.joltage + 3 >= joltage) {
                this.possibleWaysToThis += adapter.possibleWaysToThis;
            }
        }
    }

    public static long getAllPossibleWays(List<Long> numbers) {
        List<Adapter> adapters = new ArrayList<>();
        adapters.add(new Adapter(0));
        for (Long number: numbers) {
            Adapter newAdapter = new Adapter(number, adapters);
                    System.out.println(newAdapter.joltage+" "+newAdapter.possibleWaysToThis);
            adapters.add(newAdapter);
        }
        return adapters.get(adapters.size()-1).possibleWaysToThis;
    }
}
