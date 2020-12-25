package hu.adventofcode2020;

import java.util.ArrayList;
import java.util.List;

public class Seats {
    char[][] seats;
    int rows, cols;

    Seats(List<String> seatList) {
        rows = seatList.size();
        cols = seatList.get(0).length();
        seats = new char[rows][cols];
        for(int index=0; index<rows;index++) {
            seats[index] = seatList.get(index).toCharArray();
        }
    }

    public int getOccupiedSeats() {

        boolean changed = true;
        while(changed) {
            changed = false;
            char[][] newSeats = new char[rows][cols];
            for (int rowIndex=0;rowIndex<rows; rowIndex++) {
                for (int colIndex = 0; colIndex < cols; colIndex++) {
                    newSeats[rowIndex][colIndex] = seats[rowIndex][colIndex];
                }

            }
            for (int rowIndex=0;rowIndex<rows; rowIndex++) {
                for (int colIndex=0;colIndex<cols;colIndex++) {
                    if (seats[rowIndex][colIndex]=='L' && getOccupiedSeatsAround(rowIndex,colIndex)==0) {
                        newSeats[rowIndex][colIndex]='#';
                        changed=true;
                    } else if (seats[rowIndex][colIndex]=='#' && getOccupiedSeatsAround(rowIndex,colIndex)>=5) {
                        newSeats[rowIndex][colIndex]='L';
                        changed=true;
                    }

                }
            }
            seats = newSeats;
//            for (int rowIndex=0;rowIndex<rows; rowIndex++) {
//                for (int colIndex = 0; colIndex < cols; colIndex++) {
//                    System.out.print(seats[rowIndex][colIndex]);
//                }
//                System.out.println("");
//            }
//            System.out.println("");
        }
        int occSeats = 0;
        for (int rowIndex=0;rowIndex<rows; rowIndex++) {
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                if (seats[rowIndex][colIndex]=='#') occSeats++;
            }
        }
        return occSeats;
    }

    public int getOccupiedSeatsAround(int seatRow, int seatCol) {
        int occupiedSeats = 0;
        if (isOccupiedSeatLeftUp(seatRow, seatCol)) occupiedSeats++;
        if (isOccupiedSeatUp(seatRow, seatCol)) occupiedSeats++;
        if (isOccupiedSeatRightUp(seatRow, seatCol)) occupiedSeats++;
        if (isOccupiedSeatLeft(seatRow, seatCol)) occupiedSeats++;
        if (isOccupiedSeatRight(seatRow, seatCol)) occupiedSeats++;
        if (isOccupiedSeatRightDown(seatRow, seatCol)) occupiedSeats++;
        if (isOccupiedSeatDown(seatRow, seatCol)) occupiedSeats++;
        if (isOccupiedSeatLeftDown(seatRow, seatCol)) occupiedSeats++;
        return occupiedSeats;
    }

    public boolean isOccupiedSeatLeftUp(int seatRow, int seatCol) {
        while (seatRow != 0 && seatCol !=0) {
            seatRow--;
            seatCol--;
            if (seats[seatRow] [seatCol] == '#') {
                return true;
            } else if (seats[seatRow] [seatCol] == 'L') {
                return false;
            }
        }
        return false;
    }

    public boolean isOccupiedSeatUp(int seatRow, int seatCol) {
        while (seatRow != 0) {
            seatRow--;
            if (seats[seatRow] [seatCol] == '#') {
                return true;
            } else if (seats[seatRow] [seatCol] == 'L') {
                return false;
            }
        }
        return false;
    }

    public boolean isOccupiedSeatRightUp(int seatRow, int seatCol) {
        while (seatRow != 0 && seatCol < cols-1) {
            seatRow--;
            seatCol++;
            if (seats[seatRow] [seatCol] == '#') {
                return true;
            } else if (seats[seatRow] [seatCol] == 'L') {
                return false;
            }
        }
        return false;
    }

    public boolean isOccupiedSeatLeft(int seatRow, int seatCol) {
        while (seatCol != 0) {
            seatCol--;
            if (seats[seatRow] [seatCol] == '#') {
                return true;
            } else if (seats[seatRow] [seatCol] == 'L') {
                return false;
            }
        }
        return false;
    }

    public boolean isOccupiedSeatRight(int seatRow, int seatCol) {
        while (seatCol < cols-1) {
            seatCol++;
            if (seats[seatRow] [seatCol] == '#') {
                return true;
            } else if (seats[seatRow] [seatCol] == 'L') {
                return false;
            }
        }
        return false;
    }

    public boolean isOccupiedSeatLeftDown(int seatRow, int seatCol) {
        while (seatRow < rows-1 && seatCol != 0) {
            seatRow++;
            seatCol--;
            if (seats[seatRow] [seatCol] == '#') {
                return true;
            } else if (seats[seatRow] [seatCol] == 'L') {
                return false;
            }
        }
        return false;
    }

    public boolean isOccupiedSeatDown(int seatRow, int seatCol) {
        while (seatRow < rows-1) {
            seatRow++;
            if (seats[seatRow] [seatCol] == '#') {
                return true;
            } else if (seats[seatRow] [seatCol] == 'L') {
                return false;
            }
        }
        return false;
    }

    public boolean isOccupiedSeatRightDown(int seatRow, int seatCol) {
        while (seatRow < rows-1  && seatCol < cols-1) {
            seatRow++;
            seatCol++;
            if (seats[seatRow] [seatCol] == '#') {
                return true;
            } else if (seats[seatRow] [seatCol] == 'L') {
                return false;
            }
        }
        return false;
    }
}
