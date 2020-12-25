package hu.adventofcode2020;

import java.util.List;
import java.util.stream.Collectors;

public class ExecutableCommand {
    private String command;
    private int number;
    private boolean wasExecuted;

    ExecutableCommand(String commandAndNumber) {
        this.command = commandAndNumber.split(" ")[0];
        this.number = Integer.parseInt(commandAndNumber.split(" ")[1]);
        this.wasExecuted = false;
    }

    public static int getAccBeforeInfinitLoop(List<ExecutableCommand> executableCommandList) {
        int acc= 0, cursor = 0, size = executableCommandList.size();
        while(cursor != size && !executableCommandList.get(cursor).wasExecuted) {
            ExecutableCommand executedCommand = executableCommandList.get(cursor);
            switch (executedCommand.command) {
                case "acc":
                    acc += executedCommand.number;
                    cursor++;
                    break;
                case "jmp":
                    cursor += executedCommand.number;
                    break;
                case "nop":
                    cursor++;
                    break;
            }
            executedCommand.wasExecuted = true;
        }
//        System.out.println(cursor);
        return cursor == size ? acc : 0;
    }

    public static List<ExecutableCommand> refreshCommands(List<ExecutableCommand> executableCommandList) {
        return executableCommandList.stream()
                .peek(executableCommand -> executableCommand.setWasExecuted(false)).collect(Collectors.toList());
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isWasExecuted() {
        return wasExecuted;
    }

    public void setWasExecuted(boolean wasExecuted) {
        this.wasExecuted = wasExecuted;
    }
}
