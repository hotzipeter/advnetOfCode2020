package hu.adventofcode2020;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class MemoryProcessor {
    char[] mask;
    Map<Long, Long> memory;

    MemoryProcessor(List<String> commands) {
        memory = new HashMap<>();
        for (String command: commands) {
            if (command.startsWith("mask")) {
                this.mask = command.split("=")[1].trim().toCharArray();
            } else {
                String[] splittedCommand = command.split(" ");
                long value = Long.parseLong(splittedCommand[2]);
                long address = Long.parseLong(splittedCommand[0].substring(splittedCommand[0].indexOf("[")+1,splittedCommand[0].indexOf("]")));
                processMemoryCommand(address, value);
            }
        }
    }

    public long getSumOfMemory() {
        return memory.values().stream().reduce(Long::sum).get();
    }

    private void processMemoryCommand(long address, long value) {
        long newValue = doMaskForValue(toBinaryString(value));
        List<Long> addresses = doMaskForAddress(toBinaryString(address));
        addresses.forEach(address1->memory.put(address1,value));
    }

    private char[] toBinaryString(long value) {
        long lastBitDivider = (long) Math.pow(2,35);
        char[] bits = new char[36];
        for (int index = 0; index<36;index++) {
            bits[index] = value/lastBitDivider==0 ? '0': '1';
            value = value%lastBitDivider;
            lastBitDivider = lastBitDivider/2;
        }
        return bits;
    }

    private long doMaskForValue(char[] binaryValue){
        for (int index = 0; index<36;index++) {
            if (mask[index]!='X') binaryValue[index]=mask[index];
        }
        return Long.valueOf(String.valueOf(binaryValue),2);
    }

    private List<Long> doMaskForAddress(char[] binaryValue){
        for (int index = 0; index<36;index++) {
            if (mask[index]=='X') {
                binaryValue[index]='X';
            } else if (mask[index]=='1') {
                binaryValue[index]='1';
            }
        }
        List<char[]> addresses = Collections.singletonList(binaryValue);
        for (int index = 0; index<36;index++) {
            if (mask[index] == 'X') {
                List<char[]> newAddresses = new ArrayList<>();
                for (char[] address: addresses) {
                    char[] nullAddress = address.clone(), oneAddress = address.clone();
                    nullAddress[index]='0';
                    oneAddress[index]='1';
                    newAddresses.add(nullAddress);
                    newAddresses.add(oneAddress);
                }
                addresses = newAddresses;
            }
        }
        return addresses.stream().map(chars -> Long.parseLong(String.valueOf(chars),2)).collect(Collectors.toList());
    }

}
