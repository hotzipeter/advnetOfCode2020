package hu.adventofcode2020;

import java.util.List;

public class Slope {
    char[][] slopeWithChars;
    int width, height;
    int directionX, directionY;
    int startPointX, startPointY;

    Slope(List<String> lines, int startPointX, int startPointY) {
        this.height = lines.size();
        this.width = lines.get(0).length();
        this.directionX = directionX;
        this.directionY = directionY;
        this.startPointX = startPointX;
        this.startPointY = startPointY;
        System.out.println(this.width+" "+this.height);
        this.slopeWithChars = new char[height][width];

        for(int row=0; row< lines.size(); row++) {
            slopeWithChars[row] = lines.get(row).toCharArray();
        }
    }

    public int getTreesInDirection (int directionX, int directionY) {
        int cX=startPointX, cY=startPointY, numberOfTrees = 0;
        while (cY < height-1) {
            cX = (cX + directionX) % width;
            cY = (cY + directionY);
            if (slopeWithChars[cY][cX]== '#') {
                numberOfTrees++;
            }
        }
        return numberOfTrees;
    }
}
