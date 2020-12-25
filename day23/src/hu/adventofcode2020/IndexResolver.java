package hu.adventofcode2020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexResolver {
    private final Map<Integer,Integer> indexValueMap;
    private final Map<Integer,Integer> valueIndexMap;
    private final int max;

    IndexResolver(List<Integer> startList, int max) {
       indexValueMap = new HashMap<>(1 << 20);
        valueIndexMap = new HashMap<>(1 << 20);
        this.max = max;
        for (int index = 0;index<startList.size();index++) {
            indexValueMap.put(index, startList.get(index));
            valueIndexMap.put(startList.get(index),index);
        }

        for (int index = startList.size();index<max;index++) {
           indexValueMap.put(index, index);
            valueIndexMap.put(index, index);
        }
    }

    public int getValueByIndex(int index) {
       return indexValueMap.get(index);
    }

    public int getIndexByValue(int value) {

        return valueIndexMap.get(value);
    }

    public int getDestBeginIndex(int originalCurrentIndex, int originalDestIndex) {
        if (originalDestIndex > originalCurrentIndex) {
            return originalDestIndex-2;
        } else {
            if (max-(originalCurrentIndex+1)<=2) {
                return originalDestIndex - (3 - (max - (originalCurrentIndex + 1)))+1;
            } else {
                return originalDestIndex+1;
            }
        }
    }

    public void makeMove(int originalCurrentIndex, int originalDestIndex) {
        int destBeginIndex = getDestBeginIndex(originalCurrentIndex,originalDestIndex);
        Map<Integer,Integer> newIndexValueMap = new HashMap<>();
        if (originalDestIndex > originalCurrentIndex) {
            for (int index = originalCurrentIndex+4;index<=originalDestIndex;index++) {
                int value = getValueByIndex(index);
                valueIndexMap.put(value, index-3);
               newIndexValueMap.put(index-3, value);
            }
            for (int index=0;index<3;index++) {
                int value = getValueByIndex(originalCurrentIndex+index+1);
                valueIndexMap.put(value,destBeginIndex+index);
               newIndexValueMap.put(destBeginIndex+index,value);
            }
        } else {
            if (max-(originalCurrentIndex+1)<=2) {
                int overflow = 3 - (max - (originalCurrentIndex + 1));
                for (int index = originalDestIndex+1;index<=originalCurrentIndex;index++) {
                    int value = getValueByIndex(index);
                    valueIndexMap.put(value, index+3-overflow);
                    newIndexValueMap.put(index+3-overflow, value);
                }
                for (int index = overflow;index<=originalDestIndex;index++) {
                    int value = getValueByIndex(index);
                    valueIndexMap.put(value, index-overflow);
                    newIndexValueMap.put(index-overflow, value);
                }
                for (int index=0;index<3;index++) {
                    int value = getValueByIndex((originalCurrentIndex+index+1)%max);
                    valueIndexMap.put(value,destBeginIndex+index);
                    newIndexValueMap.put(destBeginIndex+index,value);
                }
            } else {
                for (int index = originalDestIndex+1;index<=originalCurrentIndex;index++) {
                    int value = getValueByIndex(index);
                    valueIndexMap.put(value, index+3);
                    newIndexValueMap.put(index+3, value);
                }
                for (int index=0;index<3;index++) {
                    int value = getValueByIndex(originalCurrentIndex+index+1);
                    valueIndexMap.put(value,destBeginIndex+index);
                    newIndexValueMap.put(destBeginIndex+index,value);
                }
            }
        }
        System.out.println(newIndexValueMap.size());
        for (int key: newIndexValueMap.keySet()) {
            indexValueMap.put(key,newIndexValueMap.get(key));
        }

    }
}
