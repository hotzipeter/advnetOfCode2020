package hu.adventofcode2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day08Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day08.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<ExecutableCommand> executableCommands = bufferedReader.lines().map(ExecutableCommand::new).collect(Collectors.toList());
        for (ExecutableCommand command: executableCommands) {
            executableCommands = ExecutableCommand.refreshCommands(executableCommands);
            if (!command.getCommand().equals("acc")) {
                command.setCommand(getOppositCommand(command.getCommand()));
                int acc = ExecutableCommand.getAccBeforeInfinitLoop(executableCommands);
                command.setCommand(getOppositCommand(command.getCommand()));
                if (acc != 0) {
                    System.out.println(acc);
                    break;
                }
            }
        }
    }

    public static String getOppositCommand(String command) {
        return "nop".equals(command) ? "jmp" : "nop";
    }
}
