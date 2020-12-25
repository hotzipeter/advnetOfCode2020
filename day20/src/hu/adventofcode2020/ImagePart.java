package hu.adventofcode2020;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public class ImagePart {
    private Long id;
    private char[][] pixels;
    private long matchingBorders;
    private int N;

    ImagePart(List<String> imagePartString) {
        this.pixels = new char[10][10];
        this.matchingBorders = 0;
        N = 10;
        String idString = imagePartString.get(0).split(" ")[1];
        this.id = Long.parseLong(idString.substring(0, idString.length() - 1));
        imagePartString.remove(0);
        for (int index = 0; index < 10; index++) {
            this.pixels[index] = imagePartString.get(index).toCharArray();
        }
    }

    public void setMatchingBordersByImagePartList(List<ImagePart> imagePartList) {
        this.matchingBorders = imagePartList.stream().filter(imagePart -> {
            if (!imagePart.getId().equals(this.id)) {
                AtomicBoolean isMatching = new AtomicBoolean(false);
                imagePart.getAllBorders().forEach(border -> {
                    if (this.getAllBorders().contains(border)) {
                        isMatching.set(true);
                    }
                });
                return isMatching.get();
            }
            return false;
        }).count();
    }

    public static void rotate(char[][] needRotate, int size) {
        // Traverse each cycle
        for (int i = 0; i < size / 2; i++) {
            for (int j = i; j < size - i - 1; j++) {

                // Swap elements of each cycle
                // in clockwise direction
                char temp = needRotate[i][j];
                needRotate[i][j] = needRotate[size - 1 - j][i];
                needRotate[size - 1 - j][i] = needRotate[size - 1 - i][size - 1 - j];
                needRotate[size - 1 - i][size - 1 - j] = needRotate[j][size - 1 - i];
                needRotate[j][size - 1 - i] = temp;
            }
        }
    }

    public static void flip(char[][] needFlip, int size) {
        for (int x = 0; x < size / 2; x++) {
            char[] tmp = needFlip[x];
            needFlip[x] = needFlip[size - 1 - x];
            needFlip[size - 1 - x] = tmp;
        }
    }

    public List<String> getAllBorders() {
        List<String> borders = new ArrayList<>();
        borders.add(getTopBorder(pixels, N));
        borders.add(getBottomBorder(pixels, N));
        borders.add(getLeftBorder(pixels, N));
        borders.add(getRightBorder(pixels, N));
        for (int index = 0; index < 4; index++) {
            borders.add(new StringBuilder().append(borders.get(index)).reverse().toString());
        }
        return borders;
    }

    public static String getTopBorder(char[][] array, int size) {
        return String.valueOf(array[0]);
    }

    public static String getBottomBorder(char[][] array, int size) {
        return String.valueOf(array[size - 1]);
    }

    public static String getLeftBorder(char[][] array, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < size; index++) {
            stringBuilder.append(array[index][0]);
        }
        return stringBuilder.toString();
    }

    public static String getRightBorder(char[][] array, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < size; index++) {
            stringBuilder.append(array[index][size - 1]);
        }
        return stringBuilder.toString();
    }

    public static List<char[][]> getAllVariation(char[][] modifiableArray) {
        int size = modifiableArray[0].length;
        List<char[][]> variations = new ArrayList<>();
        for (int index = 0; index < 4; index++) {
            variations.add(copy(modifiableArray, size));
            rotate(modifiableArray, size);
        }
        flip(modifiableArray, size);
        for (int index = 0; index < 4; index++) {
            variations.add(copy(modifiableArray,size));
            rotate(modifiableArray, size);
        }
        return variations;
    }

    public static boolean canConnectRightToLeft(char[][] from, char[][] to, int size) {
        return getRightBorder(from, size).equals(getLeftBorder(to, size));
    }

    public static boolean canConnectTopToBottom(char[][] from, char[][] to, int size) {
        return getTopBorder(from, size).equals(getBottomBorder(to, size));
    }

    public static char[][] copy(char[][] needCopy, int size) {
        char[][] newArray = new char[size][size];
        for (int index = 0; index < size; index++) {
            for (int index2 = 0; index2 < size; index2++) {
                newArray[index][index2] = needCopy[index][index2];
            }
        }
        return newArray;
    }

    public char[][] getPixelsWithoutBorders(){
        char[][] withoutBorders = new char[N-2][N-2];
        for(int index=1; index<N-1;index++){
            for(int index2=1; index2<N-1;index2++) {
                withoutBorders[index-1][index2-1] =pixels[index][index2];
            }
        }
        return withoutBorders;
    }
}
