package model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface Image {

	/**
	 * Color channels
	 */
	public static enum ColorChannel {
		RED, GREEN, BLUE
	}

	/**
	 * Image color type
	 */
	public static enum ImageType {
		RGB, GRAYSCALE
	}

	/**
	 * Supported image format
	 */
	public static enum ImageFormat {
		BMP, PGM, PPM, RAW
	}

	public static final int GRAY_LEVEL_AMOUNT = 256;

	/**
	 * Returns the height of the image
	 * 
	 * @return
	 */
	public int getHeight();

	/**
	 * Returns the width of the image
	 * 
	 * @return
	 */
	public int getWidth();

	/**
	 * Returns the image color type
	 * 
	 * @return RGB or GRAYSCALE
	 */
	public ImageType getType();

	/**
	 * Returns the format of the image
	 * 
	 * @return BMP, PGM, PPM or RAW
	 */
	public ImageFormat getImageFormat();

	/**
	 * Gets the buffered image represented by this instance.
	 * 
	 * @return the buffered image.
	 */
	public BufferedImage getImage();

	public Color getRGBPixel(int x, int y);

	public void setPixel(int x, int y, Color color);

	public Image getImagePart(Rectangle selection);

	public void pasteImagePart(BufferedImage other, Rectangle selection);

	public Color calculateAverage(Rectangle selection);

	public Image add(Image img);

	public Image substract(Image img);

	public Image multiply(Image img);

	public Image multiply(double scalar);

	public void dynamicRangeCompression();

	public void toGrayscale();

	public void negative();

	public int[] getPixelArray();

	public Image clone();

}
