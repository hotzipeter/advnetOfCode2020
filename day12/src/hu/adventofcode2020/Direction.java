package hu.adventofcode2020;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Direction {
    N(new Coordinate(0, 1), 0),
    S(new Coordinate(0,-1), 2),
    W(new Coordinate(-1,0), 3),
    E(new Coordinate(1,0), 1);

    private final Coordinate baseMovingCoordinate;
    private final long order;

    Direction(Coordinate baseMovingCoordinate, long order) {
        this.baseMovingCoordinate = baseMovingCoordinate;
        this.order = order;
    }

    public Direction turnWithAmountTimes(long amountTimes) {
        long newOrder = this.order+amountTimes;
        System.out.println(newOrder);
        newOrder = newOrder<0 ? 4+newOrder: newOrder%4;
        return Direction.getByOrder(newOrder);
    }

    private static Direction getByOrder(long givenOrder) {
        return Arrays.stream(Direction.values()).filter(direction -> direction.order == givenOrder).findFirst().get();
    }
}
