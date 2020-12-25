package hu.adventofcode2020;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day11Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day24.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<Coordinate> blackTiles = new ArrayList<>();
        for (String line : bufferedReader.lines().collect(Collectors.toList())) {
            Coordinate reference = new Coordinate(0, 0);
            char[] route = line.toCharArray();
            int index = 0;
            while (index < route.length) {
                switch (route[index]) {
                    case 'e':
                        reference.addXY(3, 0);
                        index++;
                        break;
                    case 'w':
                        reference.addXY(-3, 0);
                        index++;
                        break;
                    case 's':
                        if (route[index+1]=='e') {
                            reference.addXY(2,-1);
                        } else {
                            reference.addXY(-1,-1);
                        }
                        index +=2;
                        break;
                    case 'n':
                        if (route[index+1]=='e') {
                            reference.addXY(1,1);
                        } else {
                            reference.addXY(-2,1);
                        }
                        index +=2;
                        break;
                }
            }
            if (blackTiles.contains(reference)) {
                blackTiles.remove(reference);
            } else {
                blackTiles.add(reference);
            }
        }
        blackTiles = doFlip(blackTiles, 100);
//        blackTiles = new Coordinate(0,0).getWithNeighborsForHex();
        System.out.println(blackTiles.size());
    }

    public static List<Coordinate> doFlip(List<Coordinate> blackTiles, int numberOfFlipping) {
        List<Coordinate> oldBlackTiles = blackTiles;
        List<Coordinate> newBlackTiles = new ArrayList<>();

        for (int index =0;index<numberOfFlipping;index++) {
            List<Coordinate> neighbors= new ArrayList<>();
            for (Coordinate blackTile: oldBlackTiles) {
                neighbors.addAll(blackTile.getWithNeighborsForHex());
            }
            List<Coordinate> finalNewBlackTiles = newBlackTiles;
            List<Coordinate> finalOldBlackTiles = oldBlackTiles;
            neighbors.stream().forEach(coordinate -> {
                int blackNeighbors = getNumberOfBlackNeighbors(coordinate, finalOldBlackTiles);
                if (finalOldBlackTiles.contains(coordinate) && (blackNeighbors==1 || blackNeighbors==2) && !finalNewBlackTiles.contains(coordinate)){
                    finalNewBlackTiles.add(coordinate);
                } else if(blackNeighbors==2 && !finalNewBlackTiles.contains(coordinate)){
                    finalNewBlackTiles.add(coordinate);
                }
            });
            oldBlackTiles = newBlackTiles;
            newBlackTiles = new ArrayList<>();
        }
        return oldBlackTiles;
    }

    public static int getNumberOfBlackNeighbors(Coordinate coordinate, List<Coordinate> blackTiles) {
        List<Coordinate> neighbors = coordinate.getNeighborsForHex();
        int counter = 0;
        for (Coordinate neighbor: neighbors) {
            if (blackTiles.contains(neighbor)) {
                counter++;
            }
        }
        return counter;
    }
}
