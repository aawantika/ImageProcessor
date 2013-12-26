/**
 * This class processes an image and produces its greyscale, invert, watermark, onlyRed, onlyBlue
 * onlyGreen and maximized versions.
 *
 * @author Aawantika Sahu
 * @version 1.0
 */
public class ImageProcessor {
    private String imageName;
    private Pic firstPic;

    /**
     * Constructor for ImageProcessor. Creates a new imageprocessor that processes a given image.
     *
     * @param imageName The name of the image that will be changed.
     */
    public ImageProcessor(String name) {
        imageName = name;
        firstPic = new Pic(imageName);
    }

    /**
     * Gets the name of the image being processed.
     * 
     * @return imageName The name of the image as a String.
     */
    public String getName() {
        return imageName;
    }
    
    /**
     * Gets the name of the image being processed.
     * 
     * @return firstPic The picture for that imageprocessor.
     */
    public Pic getPic() {
        return firstPic;
    }

    /**
     * Displays the greyscale of an image.  
     * For each pixel, average the RGB values and set each channel to the average value.
     * 
     * @return newPic A greyscale copy of the original picture is returned.
     */
    public Pic greyscale() {
        firstPic = firstPic.deepCopy();
        Pixel p;
        int channel = 0;

        for (int row = 0; row < firstPic.getWidth(); row++) {
            for (int col = 0; col < firstPic.getHeight(); col++) {
                p = firstPic.getPixel(row, col);
                channel = (p.getRed() + p.getBlue() + p.getGreen()) / 3;
                p.setRed(channel);
                p.setBlue(channel);
                p.setGreen(channel);
            }
        }
        return firstPic;
    }

    /**
     * Displays an inverted image.  
     * For each pixle, it subtracts the RGB channle values from 255 and sets each channel to the respective difference.
     * 
     * @return newPic A inverted copy of the original picture is returned.
     */
    public Pic invert() {
        firstPic = firstPic.deepCopy();
        Pixel p;

        for (int row = 0; row < firstPic.getWidth(); row++) {
            for (int col = 0; col < firstPic.getHeight(); col++) {
                p = firstPic.getPixel(row, col);
                p.setRed(Math.abs(p.getRed()-255));
                p.setBlue(Math.abs(p.getBlue()-255));
                p.setGreen(Math.abs(p.getGreen()-255));
            }
        }
        return firstPic;
    }

    /**
     * Displays the watermark of two images; the new image is watermarked over the original image.
     * The original image is always displayed regardless of the size of the second image.
     * Sets each pixel as the average of each RGB value.
     *
     * @param secondImage The secondImage that is being combined with the first image for the watermark.
     * @return firstPic A watermark copy of the original picture is returned.
     */
    public Pic watermark(ImageProcessor secondImage) {
        firstPic = firstPic.deepCopy();
        Pic secondPic = secondImage.getPic();
        secondPic = secondPic.deepCopy();
        int firstArea = firstPic.getHeight() * firstPic.getWidth();
        int secondArea = secondPic.getHeight() * secondPic.getWidth();
        Pixel firstPixel, secondPixel;

        if (firstArea > secondArea) {
            for (int row = 0; row < secondPic.getWidth(); row++) {
                for (int col = 0; col < secondPic.getHeight(); col++) {
                    firstPixel = firstPic.getPixel(row, col);
                    secondPixel = secondPic.getPixel(row, col);
                    firstPixel.setRed((firstPixel.getRed() + secondPixel.getRed()) / 2);
                    firstPixel.setBlue((firstPixel.getBlue() + secondPixel.getBlue()) / 2);
                    firstPixel.setGreen((firstPixel.getGreen() + secondPixel.getGreen()) / 2);
                }
            }
        } else {
            for (int row = 0; row < firstPic.getWidth(); row++) {
                for (int col = 0; col < firstPic.getHeight(); col++) {
                    firstPixel = firstPic.getPixel(row, col);
                    secondPixel = secondPic.getPixel(row, col);
                    firstPixel.setRed((firstPixel.getRed() + secondPixel.getRed()) / 2);
                    firstPixel.setBlue((firstPixel.getBlue() + secondPixel.getBlue()) / 2);
                    firstPixel.setGreen((firstPixel.getGreen() + secondPixel.getGreen()) / 2);
                }
            }
        }
        return firstPic;
    }

    /**
     * Displays the red channel only.
     * Sets the blue and green channel to 0.
     * 
     * @return firstPic A only red copy of the original picture is returned.
     */
    public Pic onlyRed() {
        firstPic = firstPic.deepCopy();
        Pixel p;

        for (int row = 0; row < firstPic.getWidth(); row++) {
            for (int col = 0; col < firstPic.getHeight(); col++) {
                p = firstPic.getPixel(row, col);
                p.setBlue(0);
                p.setGreen(0);
            }
        }
        return firstPic;
    }

    /**
     * Displays the blue channel only.
     * Sets the red and green channel to 0.
     * 
     * @return firstPic A only blue copy of the original picture is returned.
     */
    public Pic onlyBlue() {
        firstPic = firstPic.deepCopy();
        Pixel p;

        for (int row = 0; row < firstPic.getWidth(); row++) {
            for (int col = 0; col < firstPic.getHeight(); col++) {
                p = firstPic.getPixel(row, col);
                p.setRed(0);
                p.setGreen(0);
            }
        }
        return firstPic;
    }

    /**
     * Displays the green channel only.
     * Sets the blue and red channel to 0.
     * 
     * @return firstPic A only green copy of the original picture is returned.
     */
    public Pic onlyGreen() {
        firstPic = firstPic.deepCopy();
        Pixel p;

        for (int row = 0; row < firstPic.getWidth(); row++) {
            for (int col = 0; col < firstPic.getHeight(); col++) {
                p = firstPic.getPixel(row, col);
                p.setRed(0);
                p.setBlue(0);
            }
        }
        return firstPic;
    }

    /**
     * Displays the posterized image.
     * Finds out which channel is the highest and then sets the other two channels to 0.
     * 
     * @return firstPic A posterized copy of the original picture is returned.
     */
    public Pic posterize() {
        firstPic = firstPic.deepCopy();
        Pixel p;
        int red = 0;
        int blue = 0;
        int green = 0;

        for (int row = 0; row < firstPic.getWidth(); row++) {
            for (int col = 0; col < firstPic.getHeight(); col++) {
                p = firstPic.getPixel(row, col);
                red = p.getRed();
                blue = p.getBlue();
                green = p.getGreen();
                if (red > blue && red > green) {
                    p.setBlue(0);
                    p.setGreen(0);
                }
                if (blue > red && blue > green) {
                    p.setRed(0);
                    p.setGreen(0);
                }
                if (green > red && green > blue) {
                    p.setBlue(0);
                    p.setRed(0);
                }
            }
        }
        return firstPic;
    }

    public static void main(String[] args) {
        ImageProcessor firstImage = new ImageProcessor(args[0]);
        firstImage.greyscale().show();
        firstImage.invert().show();
        firstImage.onlyRed().show();
        firstImage.onlyBlue().show();
        firstImage.onlyGreen().show();
        firstImage.posterize().show();
        if (args.length > 1) {
            ImageProcessor secondImage = new ImageProcessor(args[1]);
            firstImage.watermark(secondImage).show();
        }        
    }
}