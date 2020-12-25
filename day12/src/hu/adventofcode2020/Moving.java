package hu.adventofcode2020;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Moving implements Movement{
    private char movementCode;
    private Coordinate movementCoordinate;
    private String command;

    Moving(String movingString) {
        this.command = movingString;
        this.movementCode = movingString.charAt(0);
        long movementAmount = Long.parseLong(movingString.substring(1));
        switch (this.movementCode) {
            case 'N':
            case 'S':
            case 'W':
            case 'E':
                this.movementCoordinate = Coordinate
                        .multiplyCoordinateWithAmount(Direction.valueOf(String.valueOf(this.movementCode))
                                .getBaseMovingCoordinate(), movementAmount);
                break;
            default:
                this.movementCoordinate = new Coordinate(movementAmount, movementAmount);
        }
    }

    @Override
    public void doMovement(Boat boat) {
        switch (this.movementCode) {
            case 'N':
            case 'S':
            case 'W':
            case 'E':
                boat.setWaypoint(Coordinate.addCoordinates(boat.getWaypoint(), this.movementCoordinate));
                break;
            default:
                Coordinate forwardMovementCoordinate = Coordinate.multiplyCoordinates(boat.getWaypoint(), this.movementCoordinate);
                boat.setPosition(Coordinate.addCoordinates(boat.getPosition(), forwardMovementCoordinate));
                break;
        }
    }

    @Override
    public void print() {
        System.out.println("Command: "+ command);
    }
}
