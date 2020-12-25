package hu.adventofcode2020;

import java.util.List;
import java.util.spi.AbstractResourceBundleProvider;

public class Image {
    private ImagePart[][] imageParts;
    private char[][] fullImage;

    Image(List<ImagePart> imagePartList,ImagePart topLeftCorner) {
        int size = Double.valueOf(Math.sqrt(imagePartList.size())).intValue();
        int imagePartRowSize = topLeftCorner.getN();
        imageParts = new ImagePart[size][size];
        imagePartList.remove(topLeftCorner);
        ImagePart.getAllVariation(topLeftCorner.getPixels()).forEach(pixels -> {
            String rightBorder = ImagePart.getRightBorder(pixels, imagePartRowSize);
            String bottomBorder = ImagePart.getBottomBorder(pixels, imagePartRowSize);
//            System.out.println("Right:");
            boolean rightMatchFound = imagePartList.stream().anyMatch(imagePart -> {
                boolean found = ImagePart.getAllVariation(imagePart.getPixels()).stream().anyMatch(chars -> ImagePart.canConnectRightToLeft(pixels,chars, imagePartRowSize));
//                if (found)System.out.println(imagePart.getId());
                return found;
            });
//            System.out.println("Bottom:");
            boolean bottomMatchFound = imagePartList.stream().anyMatch(imagePart -> {
                boolean found = ImagePart.getAllVariation(imagePart.getPixels()).stream().anyMatch(chars -> ImagePart.canConnectTopToBottom(chars,pixels, imagePartRowSize));
                if (found && rightMatchFound)System.out.println(imagePart.getId());
                return found;
            });
            if (rightMatchFound && bottomMatchFound) {
                topLeftCorner.setPixels(pixels);
            }
        });
        imageParts[0][0] = topLeftCorner;
        for (int index=0; index<size; index++) {
            for (int index2=0; index2<size; index2++) {
                if (index != 0 || index2 != 0) {
                    ImagePart foundImagePart = getImagePartToXY(index,index2,imagePartRowSize,imagePartList);
                    imageParts[index][index2] = foundImagePart;
                    imagePartList.remove(foundImagePart);
                }
                System.out.print(imageParts[index][index2].getId()+" ");
            }
            System.out.println("");
        }
        initFullImage(imagePartRowSize-2,size);
    }

    public ImagePart getImagePartToXY(int x, int y,int size, List<ImagePart> imagePartList) {
        for (ImagePart imagePart: imagePartList) {
            if (canPlaceToXY(x,y,imagePart,size)) {
                return imagePart;
            }
        }
        return null;
    }

    public boolean canPlaceToXY(int x, int y, ImagePart imagePart, int size) {
        boolean leftSuccess;
        boolean topSuccess = false;

        if (y>0) {
            ImagePart leftNeighbor = imageParts[x][y-1];
            leftSuccess = ImagePart.getAllVariation(imagePart.getPixels()).stream().anyMatch(chars -> {
                boolean found = ImagePart.canConnectRightToLeft(leftNeighbor.getPixels(), chars, size);
                if (found) {
                    imagePart.setPixels(chars);
                }
                return found;
            });
        } else {
            leftSuccess = true;
        }

        if (x>0) {
            ImagePart topNeighbor = imageParts[x-1][y];
            for (char[][] variation : ImagePart.getAllVariation(imagePart.getPixels())) {
                if (ImagePart.canConnectTopToBottom(variation,topNeighbor.getPixels(),size)) {
                    imagePart.setPixels(variation);
                    topSuccess = true;
                    break;
                }
            }
        } else {
            topSuccess = true;
        }
        return leftSuccess && topSuccess;
    }

    public void initFullImage(int imagePartRowSize, int listRowSize) {
        fullImage = new char[imagePartRowSize*listRowSize][imagePartRowSize*listRowSize];
        for (int index=0; index<listRowSize; index++) {
            for (int index2 = 0; index2 < listRowSize; index2++) {
                char[][] withoutBorders = imageParts[index][index2].getPixelsWithoutBorders();
                for (int index3=0; index3<imagePartRowSize; index3++) {
                    for (int index4 = 0; index4 < imagePartRowSize; index4++) {
                        fullImage[index*imagePartRowSize+index3][index2*imagePartRowSize+index4]= withoutBorders[index3][index4];
                    }
                }
            }
        }
    }

    public int getNonMonsterPixels() {
        return ImagePart.getAllVariation(fullImage).stream().map(this::getNonMonsterPixelsInImage).filter(integer -> integer>0).findFirst().get();
    }

    public int getNonMonsterPixelsInImage(char[][] image){
        int fullImageRowSize = image[0].length;
        char[][] newImage = new char[fullImageRowSize][fullImageRowSize];
        for (int index=0; index<fullImageRowSize; index++) {
            for (int index2 = 0; index2 < fullImageRowSize; index2++) {
                newImage[index][index2] = image[index][index2];
            }
        }
        boolean found = false;
        for (int index=0; index<fullImageRowSize-3; index++) {
            for (int index2 = 0; index2 < fullImageRowSize-20; index2++) {
                if (searchMonsterFromXY(index,index2,image,newImage)) {
                    found= true;
                }
            }
        }

        int counter=0;
        for (int index=0; index<fullImageRowSize; index++) {
            for (int index2 = 0; index2 < fullImageRowSize; index2++) {
                if (newImage[index][index2]=='#') {
                    counter++;
                }
            }
        }
        return found ? counter:0;
    }

    public boolean searchMonsterFromXY(int x, int y, char[][] image, char[][] newImage) {
        if (image[x+1][y]=='#' && image[x+2][y+1]=='#'
                && image[x+2][y+4]=='#' && image[x+1][y+5]=='#' && image[x+1][y+6]=='#' && image[x+2][y+7]=='#'
                && image[x+2][y+10]=='#' && image[x+1][y+11]=='#' && image[x+1][y+12]=='#' && image[x+2][y+13]=='#'
                && image[x+2][y+16]=='#' && image[x+1][y+17]=='#' && image[x+1][y+18]=='#' && image[x+1][y+19]=='#' && image[x][y+18]=='#') {
            newImage[x+1][y]='O';
            newImage[x+2][y+1]='O';
            newImage[x+2][y+4]='O';
            newImage[x+1][y+5]='O';
            newImage[x+1][y+6]='O';
            newImage[x+2][y+7]='O';
            newImage[x+2][y+10]='O';
            newImage[x+1][y+11]='O';
            newImage[x+1][y+12]='O';
            newImage[x+2][y+13]='O';
            newImage[x+2][y+16]='O';
            newImage[x+1][y+17]='O';
            newImage[x+1][y+18]='O';
            newImage[x+1][y+19]='O';
            newImage[x][y+18]='O';
            return true;
        }
        return false;
    }
}
