import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class VisualInspection{

    public static BufferedImage amplifyLSBs(BufferedImage image, int[] colors, int whichBit){
        //whichBit is 1 for LSB, 2 for 2nd LSB
        //Given a list of colors (R=0, G=1, B=2) this amplifies the LSB of those channels
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                for (int i=0; i<3; i++){
                    //Basically 0's all pixels, but leaves LSB because we care about it
                    //for the next loop
                    pixels[i]=pixels[i]& (int) Math.pow(2,whichBit-1);
                    pixels[i] = pixels[i] >> (whichBit-1);
                }
                for (int i: colors){
                    //Does the amplification
                    pixels[i]=(pixels[i] *255);
                }
                for (int i=0; i<3; i++){
                    //If it wasn't amplified (because not requested at method call)
                    //it would be 1 bc of the first loop, so we can zero that
                    if (pixels[i]!=255){
                        pixels[i]=0;
                    }
                }
                raster.setPixel(xx, yy, pixels);
            }
        }
        return image;
    }
    

}