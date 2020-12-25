package hu.adventofcode2020;

import lombok.Getter;

@Getter
public class Turning implements Movement{
    private long turnAmount;
    private String command;

    Turning(String turningString) {
        this.command = turningString;
        char movementCode = turningString.charAt(0);
        long movementAmount = Long.parseLong(turningString.substring(1));
        switch (movementCode) {
            case 'L':
                this.turnAmount = -1 * (movementAmount / 90);
                break;
            case 'R':
                this.turnAmount = (movementAmount / 90);
                break;
        }
    }


    @Override
    public void doMovement(Boat boat) {
        boat.getWaypoint().rotateGivenTimesWithNinetyDegree(turnAmount);
    }

    @Override
    public void print() {
        System.out.println("Command: "+ command);
    }
}
