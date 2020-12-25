package hu.adventofcode2020;

public class BoardingCard {
    int seatNumber, rowNumber, seatID;

    BoardingCard(String card) {
        String row = card.substring(0, 7);
        String seat = card.substring(7);
        row= row.replace('F','0');
        row= row.replace('B','1');
        rowNumber = Integer.parseInt(row,2);
        seat = seat.replace('L','0');
        seat= seat.replace('R','1');
        seatNumber = Integer.parseInt(seat,2);
        seatID = rowNumber * 8 + seatNumber;
    }

    public int getSeatID() {
        return seatID;
    }
}
