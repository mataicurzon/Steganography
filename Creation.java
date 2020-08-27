import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
public class Creation {

    public static BufferedImage convertBytesToImage(LinkedList<Integer> bytes, int height, int width, int[] colors){
        //If you use an array for colors, you must be sure that you have the right number of bytes in the list
        //If, for example, you only have red bytes in your list bc you pulled red bytes from another image, then you would but array {0} as an arg
        
        LinkedList<Integer> localBytes = new LinkedList<Integer>();
        for (Integer i : bytes){
            localBytes.add(i);
        }
        //now turn local bytes into a png
        BufferedImage image = new BufferedImage (width, height, 6);//just trust the constant
        WritableRaster raster = image.getRaster();
        for (int yy =0; yy< height; yy++) {
            for (int xx = 0; xx< width; xx++){
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                for (int l:colors){
                    int byte1 = localBytes.removeFirst();
                    pixels[l]=byte1;
                    pixels [3]=255; 
                }
                raster.setPixel(xx, yy, pixels);
            }
        }
        return image;
    }
    public static BufferedImage convertBytesToImage(LinkedList<Integer> bytes, int height, int width){
        //if no specirfic colors specified, do all three
        return convertBytesToImage(bytes,height,width, new int[]{0,1,2});
    }
    public static String convertBytesToStringOriginal(LinkedList<Integer> bytes, int length){
        LinkedList<Integer> localBytes = new LinkedList<Integer>();
        for (Integer i : bytes){localBytes.add(i);}//Copies bytes into a local variable
        //This method should not modify the List called bytes, rather copy it and
        //modify the local version
        String toReturn = "";
        //insert code here to throwaway header/garbage bytes
        for (int i=0; i<length; i++){
            int temp = localBytes.removeFirst().intValue();
            if ((temp > 64 & temp < 91) || (temp > 96 & temp < 123) || temp ==32 || temp == 10 || temp==44 || temp == 46 || temp == 63 || temp == 33 || (temp >47 && temp<58))
                System.out.print((char)temp);
            //toReturn += (char) localBytes.removeFirst().intValue();
        }

        return toReturn;
    }
    public static String convertBytesToStringOriginal(LinkedList<Integer> bytes){
        //if no length specified, do 500
        return convertBytesToStringOriginal(bytes, 500);
    }

    public static BufferedImage convertBytesToImageWithAlpha(LinkedList<Integer> bytes, int height, int width){
        int[] colors = new int[]{0,1,2,3};
        LinkedList<Integer> localBytes = new LinkedList<Integer>();
        for (Integer i : bytes){
            localBytes.add(i);
        }
        //now turn local bytes into a png
        BufferedImage image = new BufferedImage (width, height, 6);//just trust the constant
        WritableRaster raster = image.getRaster();
        for (int yy =0; yy< height; yy++) {
            for (int xx = 0; xx< width; xx++){
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                for (int l:colors){
                    int byte1 = localBytes.removeFirst();
                    pixels[l]=byte1;
                    
                }
                raster.setPixel(xx, yy, pixels);
            }
        }
        return image;
    }
    public static BufferedImage createBrotherImage(LinkedList<Integer> bytes, int height, int width, int[] colors){
        //THIS WAS USED FOR ONE SPECIFIC IMAGE FOR A PROJECT
        LinkedList<Integer> localBytes = new LinkedList<Integer>();

        for (Integer i : bytes){
            localBytes.add(i);
        }

        LinkedList<Integer> firstBytes = new LinkedList<Integer>();
        LinkedList<Integer> secondBytes = new LinkedList<Integer>();
        LinkedList<Integer> thirdBytes = new LinkedList<Integer>();
        LinkedList<Integer> fourthBytes = new LinkedList<Integer>();

        int imageCount = 0; //This is what image we're on


        for (int i = 0; i <bytes.size(); i++){
            if (imageCount%4==0)
                firstBytes.add(localBytes.removeFirst());
            else if (imageCount%4==1)
                secondBytes.add(localBytes.removeFirst());
            else if (imageCount%4==2)
                thirdBytes.add(localBytes.removeFirst());
            else if (imageCount%4==3)
                fourthBytes.add(localBytes.removeFirst());

            if ((i+1)%(512*3)==0)
                imageCount++;
        }
        
        //now turn local bytes into a png
        BufferedImage image = new BufferedImage (width, height, 6);//just trust the constant
        WritableRaster raster = image.getRaster();
        for (int yy =0; yy< height; yy++) {
            for (int xx = 0; xx< width; xx++){
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                for (int l:colors){
                    int byte1=0;
                    if(xx<512){
                        byte1 = firstBytes.removeFirst();
                    }
                    else if (xx<1024){
                        byte1 = secondBytes.removeFirst();
                    }
                    else if (xx<1536){
                        byte1 = thirdBytes.removeFirst();
                    }
                    else{
                        byte1 = fourthBytes.removeFirst();
                    }
                    pixels[l]=byte1;
                    pixels [3]=255; 
                    
                }
                raster.setPixel(xx, yy, pixels);
            }
        }

        
        return image;
    }

}