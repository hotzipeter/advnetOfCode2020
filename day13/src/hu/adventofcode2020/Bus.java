package hu.adventofcode2020;

import java.util.ArrayList;
import java.util.List;

public class Bus {
    long busId, offset;

    Bus(long busId, long offset) {
        this.busId = busId;
        this.offset = offset;
    }

    public long getTimeWithMultiplier(long multiplier){
        return multiplier*busId - offset;
    }
}
