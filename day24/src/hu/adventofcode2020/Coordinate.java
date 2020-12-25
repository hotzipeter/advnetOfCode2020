package hu.adventofcode2020;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Coordinate {
    private long x;
    private long y;

    public static long getManhattanDistanceBetween(Coordinate from, Coordinate to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    public static Coordinate multiplyCoordinateWithAmount(Coordinate coordinate, long amount) {
        return new Coordinate(coordinate.getX() * amount, coordinate.getY() * amount);
    }

    public static Coordinate addCoordinates(Coordinate from, Coordinate to) {
        return new Coordinate(from.getX() + to.getX(), from.getY() + to.getY());
    }

    public void addXY(long x, long y) {
        this.x+=x;
        this.y+=y;
    }

    public static Coordinate multiplyCoordinates(Coordinate from, Coordinate to) {
        return new Coordinate(from.getX() * to.getX(), from.getY() * to.getY());
    }

    public void rotateGivenTimesWithNinetyDegree(long givenTimes) {
        givenTimes = givenTimes<0 ? 4+givenTimes : givenTimes;
        for (int index = 1; index<=givenTimes; index++ ) {
            long temp = this.x;
            this.x = this.y;
            this.y = -1 * temp;
        }
    }

    public List<Coordinate> getWithNeighborsForHex() {
        List<Coordinate> neighbors = new ArrayList<>();
        neighbors.add(new Coordinate(x+1,y+1));
        neighbors.add(new Coordinate(x-1,y-1));
        neighbors.add(new Coordinate(x+2,y-1));
        neighbors.add(new Coordinate(x-2,y+1));
        neighbors.add(new Coordinate(x+3,y));
        neighbors.add(new Coordinate(x-3,y));
        neighbors.add(new Coordinate(x,y));
        return neighbors;
    }

    public List<Coordinate> getNeighborsForHex() {
        List<Coordinate> neighbors = new ArrayList<>();
        neighbors.add(new Coordinate(x+1,y+1));
        neighbors.add(new Coordinate(x-1,y-1));
        neighbors.add(new Coordinate(x+2,y-1));
        neighbors.add(new Coordinate(x-2,y+1));
        neighbors.add(new Coordinate(x+3,y));
        neighbors.add(new Coordinate(x-3,y));
        return neighbors;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return this.y == coordinate.getY() && this.x == coordinate.getX();
        }
        return false;
    }
}
