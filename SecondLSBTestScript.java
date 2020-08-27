import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class SecondLSBTestScript {

    ////This goes through and runs tests on all combinations of color channel 2nd LSBs and prints

    public static void main(String[] args) throws Exception {
        LinkedList<Integer> bits;
        LinkedList<Integer> bytes;
        BufferedImage orig;
        File dir = new File("Images/");

        File[] images = dir.listFiles();

        for (File image : images) {
            if (image.getName().contains(".png")){
                System.out.println("===============================================================================================");
                System.out.println("===============================================================================================");
                System.out.println("Images/" + image.getName());
                orig = ImageIO.read(image);

                System.out.println("Checkin All LSB's RGB...");
                bits = Helpers.pullSpecifiedBitsOfSpecificColors(orig, new int[] { 0, 1, 2 }, new int[] { 2 });
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Checkin All LSB's BGR...");
                bits = Helpers.pullSpecifiedBitsOfSpecificColors(orig, new int[] { 2, 1, 0 }, new int[] { 2 });
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Red LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColors(orig, new int[] { 0 }, new int[] { 2 });
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Green LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColors(orig, new int[] { 1 }, new int[] { 2 });
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Blue LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColors(orig, new int[] { 2 }, new int[] { 2 });
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Removing 1000 bits and rechecking..");

                System.out.println("Checkin All LSB's RGB...");
                bits = Helpers.pullSpecifiedBitsOfSpecificColors(orig, new int[] { 0, 1, 2 }, new int[] { 2 });
                for (int i = 0; i < 1001; i++) {
                    if (!bits.isEmpty())
                        bits.removeFirst();
                }
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Checkin All LSB's BGR...");
                bits = Helpers.pullSpecifiedBitsOfSpecificColors(orig, new int[] { 2, 1, 0 }, new int[] { 2 });
                for (int i = 0; i < 1001; i++) {
                    if (!bits.isEmpty())
                        bits.removeFirst();
                }
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Red LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColors(orig, new int[] { 0 }, new int[] { 2 });
                for (int i = 0; i < 1001; i++) {
                    if (!bits.isEmpty())
                        bits.removeFirst();
                }
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Green LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColors(orig, new int[] { 1 }, new int[] { 2 });
                for (int i = 0; i < 1001; i++) {
                    if (!bits.isEmpty())
                        bits.removeFirst();
                }
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Blue LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 2 }, new int[] { 2 });
                for (int i = 0; i < 1001; i++) {
                    if (!bits.isEmpty())
                        bits.removeFirst();
                }
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();
                System.out.println("Now check VERTICAL...");
                System.out.println("Checkin Vertical All LSB's RGB...");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 0, 1, 2 }, new int[] { 2 });
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Checkin Vertical All LSB's BGR...");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 2, 1, 0 }, new int[] { 2 });
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Vertical Red LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 0 }, new int[] { 2 });
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Vertical Green LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 1 }, new int[] { 2 });
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Vertical Blue LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 2 }, new int[] { 2 });
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Removing 1000 bits and rechecking..");

                System.out.println("Checkin Vertical All LSB's RGB...");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 0, 1, 2 }, new int[] { 2 });
                for (int i = 0; i < 1001; i++) {
                    if (!bits.isEmpty())
                        bits.removeFirst();
                }
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Checkin Vertical All LSB's BGR...");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 2, 1, 0 }, new int[] { 2 });
                for (int i = 0; i < 1001; i++) {
                    if (!bits.isEmpty())
                        bits.removeFirst();
                }
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Vertical Red LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 0 }, new int[] { 2 });
                for (int i = 0; i < 1001; i++) {
                    if (!bits.isEmpty())
                        bits.removeFirst();
                }
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Vertical Green LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 1 }, new int[] { 2 });
                for (int i = 0; i < 1001; i++) {
                    if (!bits.isEmpty())
                        bits.removeFirst();
                }
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();

                System.out.println("Check Vertical Blue LSB's");
                bits = Helpers.pullSpecifiedBitsOfSpecificColorsVertical(orig, new int[] { 2 }, new int[] { 2 });
                for (int i = 0; i < 1001; i++) {
                    if (!bits.isEmpty())
                        bits.removeFirst();
                }
                bytes = Helpers.convertBitsToBytes(bits);
                Helpers.checkForHeader(bytes);
                System.out.println(Creation.convertBytesToStringOriginal(bytes, 500));
                System.out.println();
                System.out.println(
                        "===============================================================================================");
                System.out.println(
                        "===============================================================================================");
                System.out.println(
                        "===============================================================================================");
                System.out.println(
                        "===============================================================================================");
            }
        }
    }

}