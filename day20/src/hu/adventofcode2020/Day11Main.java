package hu.adventofcode2020;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day11Main {

    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("day20.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
        List<String>  lines = bufferedReader.lines().collect(Collectors.toList());
        List<String> imagepartStringLines = new ArrayList<>();
        List<ImagePart> imagePartList = new ArrayList<>();

        for (String line: lines) {
            if (line.isEmpty()) {
                imagePartList.add(new ImagePart(imagepartStringLines));
                imagepartStringLines = new ArrayList<>();
            } else {
                imagepartStringLines.add(line);
            }
        }
        imagePartList.add(new ImagePart(imagepartStringLines));

        long multiplyCorners = 1;
        ImagePart corner= null;
        for (ImagePart imagePart: imagePartList) {
            imagePart.setMatchingBordersByImagePartList(imagePartList);
            if (imagePart.getMatchingBorders() == 2) {
                corner = imagePart;
//                System.out.println(imagePart.getId());
                multiplyCorners *= imagePart.getId();
            }
        }
        Image image = new Image(imagePartList, corner);

//        System.out.println(multiplyCorners);
        System.out.println(image.getNonMonsterPixels());
    }
}
