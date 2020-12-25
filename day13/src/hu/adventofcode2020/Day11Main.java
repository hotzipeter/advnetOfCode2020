package hu.adventofcode2020;

import jdk.dynalink.linker.ConversionComparator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day11Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day13.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        String[] timeTable= bufferedReader.lines().collect(Collectors.toList()).get(1).split(",");
        List<Bus> buses= new ArrayList<>();
        for (int index=0;index<timeTable.length;index++) {
            if (!"x".equals(timeTable[index])) {
                Bus bus = new Bus(Long.parseLong(timeTable[index]),index);
                buses.add(bus);
            }
        }
        boolean found = false;
        long multiplier = 1;
        long firstTimeStamp = 0;
        Bus firstBus = buses.get(0);
        buses.remove(0);
        buses.sort(Comparator.comparingLong(o -> o.busId));
//        System.out.println(getBusesFirstTimeStamp2(buses));
        for (int index= buses.size()-1;index>=0; index--) {
            getOffsetForBus(firstBus,buses.get(index));
            System.out.println("index : "+index);
        }
        System.out.println(firstBus.offset);
    }

    public static long lkkt(long first, long second) {
        long max = Math.max(first,second);
        long min = Math.min(first,second);
        long multiplier = 1;
        while ((multiplier*max)%min !=0) multiplier++;
        return max*multiplier;
    }

    public static void getOffsetForBus(Bus bus1, Bus bus2) {
        long multiplier =1;
        long offset = bus2.offset ==0 ? bus2.offset:bus2.busId- (bus2.offset% bus2.busId);
        while ((bus1.busId*multiplier+ bus1.offset)% bus2.busId != offset) {
            multiplier++;
        }
        bus1.offset = bus1.busId*multiplier+ bus1.offset;
        bus1.busId= bus1.busId*bus2.busId;

    }
}
