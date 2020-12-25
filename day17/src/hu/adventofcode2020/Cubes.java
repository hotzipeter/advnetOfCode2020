package hu.adventofcode2020;

import java.util.List;

public class Cubes {
    char[][][][] cubes;
    int rows, cols;

    Cubes(List<String> seatList) {
        rows = seatList.size();
        cols = seatList.get(0).length();
        cubes = new char[rows + 12][cols + 12][13][13];
        for (int index = 0; index < rows+12; index++) {
            for (int index2 = 0; index2 < cols+12; index2++) {
                for (int index3 = 0; index3 < 13; index3++) {
                    for (int index4 = 0; index4 < 13; index4++) {
                        cubes[index][index2][index3][index4] = '.';
                    }
                }
            }
        }
        for (int index = 0; index < rows; index++) {
            char[] row = seatList.get(index).toCharArray();
            for (int index2 = 0; index2 < cols; index2++) {
                cubes[index + 6][index2 + 6][6][6] = row[index2];
            }
        }
    }

    public int getOccupiedSeats() {


        for (int cycles = 0; cycles < 6; cycles++) {
            char[][][][] newSeats = new char[rows + 12][cols + 12][13][13];
            for (int rowIndex = 0; rowIndex < rows + 12; rowIndex++) {
                for (int colIndex = 0; colIndex < cols + 12; colIndex++) {
                    for (int zIndex = 0; zIndex < 13; zIndex++) {
                        for (int index4 = 0; index4 < 13; index4++) {
                            newSeats[rowIndex][colIndex][zIndex][index4] = cubes[rowIndex][colIndex][zIndex][index4];
                        }
                    }
                }
            }
            for (int rowIndex = 0; rowIndex < rows + 12; rowIndex++) {
                for (int colIndex = 0; colIndex < cols + 12; colIndex++) {
                    for (int zIndex = 0; zIndex < 13; zIndex++) {
                        for (int index4 = 0; index4 < 13; index4++) {
                            int activeCubesAround = getOccupiedSeatsAround(rowIndex, colIndex, zIndex, index4);
                            if (cubes[rowIndex][colIndex][zIndex][index4] == '.' && activeCubesAround == 3) {
                                newSeats[rowIndex][colIndex][zIndex][index4] = '#';
                            } else if (cubes[rowIndex][colIndex][zIndex][index4] == '#' && (activeCubesAround != 3 && activeCubesAround != 2)) {
                                newSeats[rowIndex][colIndex][zIndex][index4] = '.';
                            }
                        }
                    }
                }
            }
            prinntZ0();
            cubes = newSeats;
        }

        int occSeats = 0;
        for (int rowIndex = 0; rowIndex < rows+12; rowIndex++) {
            for (int colIndex = 0; colIndex < cols+12; colIndex++) {
                for (int zIndex = 0; zIndex < 13; zIndex++) {
                    for (int index4 = 0; index4 < 13; index4++) {
                        if (cubes[rowIndex][colIndex][zIndex][index4] == '#') occSeats++;
                    }
                }
            }
        }
        return occSeats;
    }

    public int getOccupiedSeatsAround(int seatRow, int seatCol, int zIndex, int wIndex) {
        int occupiedSeats = 0;
        for (int rowIndex = -1; rowIndex <=1; rowIndex++) {
            for (int colIndex = -1; colIndex <= 1; colIndex++) {
                for (int dirZ = -1; dirZ <= 1; dirZ++) {
                    for (int dirW = -1; dirW <= 1; dirW++) {
                        if (rowIndex != 0 || colIndex != 0 || dirZ != 0 || dirW != 0) {
                            if (isCubeActiveWithVector(seatRow, seatCol, zIndex, wIndex,rowIndex, colIndex, dirZ, dirW))
                                occupiedSeats++;
                        }
                    }
                }
            }
        }
        return occupiedSeats;
    }

    public boolean isCubeActiveWithVector(int x, int y, int z,int w, int dirX, int dirY, int dirZ, int dirW) {
        int newX = x+dirX;
        int newY = y+dirY;
        int newZ = z+dirZ;
        int newW = w+dirW;
        boolean xInRange = newX>=0 && newX<rows+12;
        boolean yInRange = newY>=0 && newY<cols+12;
        boolean zInRange = newZ>=0 && newZ<=12;
        boolean wInRange = newW>=0 && newW<=12;
        if (xInRange && zInRange && yInRange && wInRange) {
            return cubes[newX][newY][newZ][newW] == '#';
        }
        return false;
    }

    public void prinntZ0(){
        for (int index = 0; index < rows+12; index++) {
            for (int index2 = 0; index2 < cols + 12; index2++) {
                System.out.print(cubes[index][index2][6]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
