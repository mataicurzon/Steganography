import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.ArrayList;
public class Stats{
    /*This class should consist of various algorithms we can
    * run on images/lists of bits or bytes 
    * to determine if/where an image is hidden
    */

    public static void computeOffByOnes(BufferedImage image, int messageLength){
        //Prints out the number of times where two pixels differ by amounts that are achievable
        //by chaning the LSB of color channels. (When total of RGB is >0 and <=3)
        //For example, if a pixel 145,130,120 borders 146,131,120, the pixel sums would be  
        //395 and 397 respectively. This would count that
        //If you run this method on the pixels of hide_text with message length 3*(572*8)
        //it outputs 468, 134, 161 which shows there are more offByOnes when there is a message
        //if you do it with length 1000, you can clearly see where the image is


        LinkedList<Integer> pixels = Helpers.getPixelTotalValues(image);
        ArrayList<Integer> pixelsArray = new ArrayList<Integer>();
        for (Integer i: pixels){
            pixelsArray.add(i);
        }//I totally used data structures knowledge and switched from a LL to an Array
        // because I wanted O(1) indexing later and I think that's cool lol <- noice

        int[] offByOnes = new int[50];
        int pixelDiffLeftRight;
        int pixelDiffUpDown;
        for (int i = 0; i<50; i++){
            for (int j = messageLength*i; j<messageLength*(i+1); j++){
                pixelDiffLeftRight = pixelsArray.get(j)-pixelsArray.get(j+1);
                if (pixelDiffLeftRight<=3 && pixelDiffLeftRight >0 ){
                    offByOnes[i]++;
                }
                pixelDiffUpDown = pixelsArray.get(j)-pixelsArray.get(j+image.getWidth());
                if (pixelDiffUpDown<=3 && pixelDiffUpDown >0 ){
                    offByOnes[i]++;
                }

            }
        }
        for (int i : offByOnes){
            System.out.println(i);}

    }
    public static void computeOffByOnes(BufferedImage image){
        //if no message length specifed, do it with 1000
        computeOffByOnes(image, 1000);
    }
    public static void printRGBSums(BufferedImage image){
        ArrayList<Integer> rgbSums= new ArrayList<Integer>();//for O(1) indexing in later loop
        for (Integer i : Helpers.getPixelTotalValues(image)){
            rgbSums.add(i);
        }
        for (int i=0; i<10000; i++){
            //length can be changed
            System.out.println(rgbSums.get(i));
        }
        /*
        Basically, this prints out the sum of the RGB channels for the first 10,000
        pixels in the image. If you look at the image, the top row (the first several hundred pixels)
        appears to be all 0's (it's transparent so you see the background) but when we run this,
        it shows up as not all 0, which means that the LSBs are one's instead of the
        0's that it looks like they should be. That also happens to be where we know there's a message hidden
        */
    }
    public static int numZeroBytes(LinkedList<Integer> bytes){
        int num = 0;
        for (Integer i : bytes){
            if (i==0){
                num++;
            }
        }
        return num;
    }
}