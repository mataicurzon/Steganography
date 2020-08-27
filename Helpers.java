import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class Helpers{

    
    public static LinkedList<Integer> convertBitsToBytes(LinkedList<Integer> bits){
        //Make a copy of bits, so it is not modified outside this method. only alter the copy
        

        LinkedList<Integer> bytes = new LinkedList<Integer>();
        
        

        while (bits.size()>7){
            int nextByte=0;
                for (int i=0; i<8; i++){
                    
                    nextByte+=bits.removeFirst()*((int) Math.pow(2, 7-i));
                    
                }
            bytes.add(nextByte);
        }

        
        return bytes;

    }
    public static void checkForHeader(LinkedList<Integer> bytes){
        //Print out the first three ints in big and little endian. as 32 bit ints
        int[] firstBytes = new int[20];
        for (int i =0; i < 20; i++){
            firstBytes[i]=bytes.get(i);
        }
        int intOneBigEndian= (firstBytes[0]<<24) + (firstBytes[1]<<16)+(firstBytes[2]<<8)+(firstBytes[3]);
        int intTwoBigEndian = (firstBytes[4]<<24) + (firstBytes[5]<<16)+(firstBytes[6]<<8)+(firstBytes[7]);
        int intThreeBigEndian = (firstBytes[8]<<24) + (firstBytes[9]<<16)+(firstBytes[10]<<8)+(firstBytes[11]);
        int intFourBigEndian = (firstBytes[12]<<24) + (firstBytes[13]<<16)+(firstBytes[14]<<8)+(firstBytes[15]);
        int intFiveBigEndian = (firstBytes[16]<<24) + (firstBytes[17]<<16)+(firstBytes[18]<<8)+(firstBytes[19]);


        int intOneLittleEndian = (firstBytes[3]<<24) + (firstBytes[2]<<16)+(firstBytes[1]<<8)+(firstBytes[0]);
        int intTwoLittleEndian = (firstBytes[7]<<24) + (firstBytes[6]<<16)+(firstBytes[5]<<8)+(firstBytes[4]);
        int intThreeLittleEndian= (firstBytes[11]<<24) + (firstBytes[10]<<16)+(firstBytes[9]<<8)+(firstBytes[8]);

        System.out.println("First three ints hidden in given list of Bytes: ");
        System.out.println("Big Endian: " + intOneBigEndian + ", " + intTwoBigEndian+ ", " + intThreeBigEndian + ", " + intFourBigEndian + ", " + intFiveBigEndian);
        System.out.println("Little Endian: " + intOneLittleEndian + ", " + intTwoLittleEndian+ ", " + intThreeLittleEndian);
    }
    public static LinkedList<Integer> pullSpecifiedBitsOfSpecificColors(BufferedImage image, int[] colors, int[] whichBits){
        //int[] bits should be [2,1] for two least sig 2 bits and [1] for LSB
        //[3,2,1] for least sig 3 bits
        //This method takes in a list of color channels and a least of bits (3rd lsb, 2nd lsb, 1st lsb)
        //and returns a list of those bits
        //Pull LSBs of one color channel. R=0, G=1, B=2. Input array must be in increasing order
        //like in pullLSBsOfSpecificColors
        int width = image.getWidth();
        int height = image.getHeight();
       // System.out.println("Height: " + height + " Width: " + width);
        
        LinkedList<Integer> bits = new LinkedList<Integer>();
        WritableRaster raster = image.getRaster();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                for (int i =0; i<colors.length; i++){
                    for (int j: whichBits){
                        bits.add((pixels[colors[i]] & ((int) Math.pow(2,j-1)))>>(j-1)); 
                    }
                }
            }
        }
        return bits;    
    }
    public static LinkedList<Integer> getPixelTotalValues(BufferedImage image){
        //gets total value of RGB channels from and image
        //returns a linked list of ints between 0 and 795
        int width = image.getWidth();
        int height = image.getHeight();
        
        LinkedList<Integer> pixelValues = new LinkedList<Integer>();
        WritableRaster raster = image.getRaster();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                int pixelVal = 0;
                pixelVal += pixels[0];
                pixelVal += pixels[1];
                pixelVal += pixels[2];
                pixelValues.add(pixelVal);
            }
        }
        return pixelValues; 
    }
    public static LinkedList<Integer> getBytesOfImage(BufferedImage image, int howManyBytes, int [] colors){
        //This just pulls the bytes straight from an image. He may give us something that looks
        //like noise but is actually a message
        //Colors is which colors you want pulled and how ManyBytes is the number you want to grab total
        int bytesAdded=0;
        LinkedList<Integer> bytes = new LinkedList<Integer>();
        WritableRaster raster = image.getRaster();
        //Swtich xx, yy, height, width in the loop headers to grab bits vertically
        for (int yy =0; yy< image.getHeight(); yy++) {
            for (int xx = 0; xx< image.getWidth(); xx++){
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                for (int l : colors){
                    bytes.add(pixels[l]);
                    bytesAdded++;
                    if (bytesAdded == howManyBytes){
                        return bytes;
                    }
                }
            }
        }
        return bytes;
    }
    public static LinkedList<Integer> getBytesOfImage(BufferedImage image){
        //if no number of bytes and no colors specirfied, grab all bytes, all colors
        int [] allThree = {0,1,2};
        return getBytesOfImage(image, (getNumberOfPixels(image)*3), allThree);
    }
    public static LinkedList<Integer> getBytesOfImage(BufferedImage image, int[] colors){
        //if no number of bytes specified, grab all of them
        return getBytesOfImage(image, (getNumberOfPixels(image)*3), colors);
    }
    public static int getNumberOfPixels(BufferedImage image){
        return image.getWidth()*image.getHeight();
    }
    public static LinkedList<Integer> bitify(LinkedList<Integer> a){
        //bitifies
        // ex: {255,0,7} -> {1,0,1}
        //might be useful if pixels themselves are the bits
        LinkedList<Integer> localList = new LinkedList<Integer>();
        for (Integer i : a){
            localList.add(i);
        }
        LinkedList<Integer> toReturn = new LinkedList<Integer>();
        while (!localList.isEmpty()){
            int i = localList.removeFirst();
            if (i==0){
                toReturn.add(0);
            }
            else
                toReturn.add(1);
        }
        return toReturn;
    }
    public static LinkedList<Integer> convertBytesToBits(LinkedList<Integer> bytes){
        LinkedList<Integer> bits = new LinkedList<Integer>();
        for (Integer i : bytes){
            for (int j = 7; j >-1; j-- ){
                bits.add( i >> j & 1);
            }
        }
        return bits;

    }

    public static LinkedList<Integer> pullSpecifiedBitsOfSpecificColorsVertical(BufferedImage image, int[] colors, int[] whichBits){
        //same as earlier method but top to bottom, then left to right
        int width = image.getWidth();
        int height = image.getHeight();
        
        LinkedList<Integer> bits = new LinkedList<Integer>();
        WritableRaster raster = image.getRaster();
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                for (int i : colors){
                    for (int j: whichBits){
                        bits.add((pixels[i] & ((int) Math.pow(2,j-1)))>>(j-1)); 
                    }
                }
            }
        }
        return bits;  
    } 
    public static LinkedList<Integer> pullSpecifiedBitsOfSpecificColorsShort(BufferedImage image, int[] colors, int[] whichBits){
        //USE THIS WITH BIG IMAGES
        int bitsCollected=0;
        int width = image.getWidth();
        int height = image.getHeight();
        LinkedList<Integer> bits = new LinkedList<Integer>();
        WritableRaster raster = image.getRaster();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                for (int i : colors){
                    for (int j: whichBits){
                        bits.add((pixels[i] & ((int) Math.pow(2,j-1)))>>(j-1)); 
                        bitsCollected++;
                        //change below to change how many bits you need
                        if (bitsCollected%1000000==0) System.out.println(bitsCollected);
                        if (bitsCollected==75497472){return bits;}
                        if (bitsCollected==18432000){return bits;}
                    }
                }
            }
        }
        return bits;    
    } 
    public static LinkedList<Integer> flipBits(LinkedList<Integer> bits){
        LinkedList<Integer> toReturn = new LinkedList<Integer>();
        for (Integer i : bits){
            if (i==0){
                toReturn.add(1);
            }
            else
                toReturn.add(0);
        }
        return toReturn;
    }
    public static void findHeader(LinkedList<Integer> bytes){
        ArrayList<Integer> localBytes = new ArrayList<Integer>();
        for (Integer i : bytes){
            localBytes.add(i);
        }
        int numFound=0;
        boolean intFound=false;
        for (int i =0; i<localBytes.size()-8; i+=4){
            int intOneBigEndian= (localBytes.get(i)<<24) + (localBytes.get(i+1)<<16)+(localBytes.get(i+2)<<8)+(localBytes.get(i+3));
            if (intOneBigEndian < 4000 && intOneBigEndian>80 && i<600000){
                if (intFound){
                    System.out.println(intOneBigEndian);
                    System.out.println("At pixel: " + i);
                    numFound++;
                }
                intFound=true;
            }
            else
                intFound=false;
        }
        System.out.println(numFound);
    }


    public static void findStringOfZeroPixels(LinkedList<Integer> pixelValues){
        //Finds strings of black pixels (which denote a header) in the first row/column of an image
        int numInARow=0;
        int size = pixelValues.size();
        ArrayList<Integer> localValues = new ArrayList<Integer>();
        for (Integer i: pixelValues){
            localValues.add(i);
        }
        for (int i=0; i< size; i++){
            int pixelVal = localValues.get(i);
            if (pixelVal==0){
                numInARow++;
                if (numInARow ==6 && i < 4000){
                    System.out.println(i);
                }
            }
            else
                numInARow=0;
        }   
    }

    public static LinkedList<Integer> getPixelTotalValuesVertical(BufferedImage image){
        //gets total value of RGB channels from and image
        //returns a linked list of ints between 0 and 795
        //get top to bottom, then left to right
        int width = image.getWidth();
        int height = image.getHeight();
        
        LinkedList<Integer> pixelValues = new LinkedList<Integer>();
        WritableRaster raster = image.getRaster();
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                int pixelVal = 0;
                pixelVal += pixels[0];
                pixelVal += pixels[1];
                pixelVal += pixels[2];
                pixelValues.add(pixelVal);
            }
        }
        return pixelValues; 
    }
}

