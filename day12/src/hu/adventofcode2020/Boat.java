package hu.adventofcode2020;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Boat {
    private List<Movement> movements;
    @Getter
    @Setter
    private Direction direction;
    @Getter
    @Setter
    private Coordinate position;
    @Getter
    @Setter
    private Coordinate waypoint;


    public Boat(List<Movement> movements) {
        this.movements = movements;
        this.direction = Direction.E;
        this.position = new Coordinate(0, 0);
        this.waypoint = new Coordinate(10, 1);
    }

    public void doMovements() {
        System.out.println("position: "+position.getX()+" "+position.getY());
        System.out.println("direction: "+direction);
        System.out.println("");

        for (Movement movement: movements) {
            movement.doMovement(this);
            movement.print();
            System.out.println("position: "+position.getX()+" "+position.getY());
            System.out.println("direction: "+direction);
            System.out.println("");
        }
    }

    public long getManhattanDistanceFromStartPoint() {
        return Coordinate.getManhattanDistanceBetween(new Coordinate(0,0), this.position);
    }
}
