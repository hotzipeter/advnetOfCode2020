package hu.adventofcode2020;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class IndexResolver {
    private final Map<Integer, ListIterator<Integer>> indexValueMap;

    IndexResolver() {
       indexValueMap = new HashMap<>();
    }

    public void add(int value, ListIterator<Integer> iterator) {
        indexValueMap.put(value,iterator);
    }
}
