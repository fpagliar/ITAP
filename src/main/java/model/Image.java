package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Set;

import model.Channel.Point3D;
import utils.Mask;

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

	public void applyThreshold(double value);

	public void exponentialNoise(double u);

	public void rayleighNoise(double epsilon);

	public void gausseanNoise(double mean, double standardDeviation);

	public void saltAndPepperNoise(double percentage, double po, double p1);

	public Color applyMeanFilter(int pixelX, int pixelY, int rectangleSide);
	public Color applyMedianFilter(int pixelX, int pixelY, int rectangleSide);
	public Color applyGaussianFilter(int pixelX, int pixelY, int rectangleSide, double sigma);
	public void applyAnisotropicDiffusion(BorderDetector bd);
	public void applyMask(Mask mask);
	public void applyMasksAndSynth(SynthesisFunction func, Mask mask1, Mask mask2);
	public void synthesize(SynthesisFunction func, Image... imgs);
	public void contrast(double r1, double r2, double y1, double y2);
	public Set<Point> getWhites();
	public Set<Point> getBlacks();
	public double getGlobalThreshold();
	public int[][] getDerivationDirections();
	public void borderWithNoMaximumsDeletion(int[][] derivationDirections);
	public void markZeroCrossers();
	public void markCrossersWithThreshold(int threshold);
	public void histeresisThreshold(double t1, double t2);
	public void applySusan(Mask mask, double threshold);
	public HashMap<Double, Double> applyHough(int granularityTita, int granularityRo, double threshold, int totalLines);
	public void drawHoughLines(HashMap<Double, Double> roTitas, double threshold);
	public Set<Point3D> applyCircleHough(int granularityA, int granularityB, int granularityR,
			double threshold, int totalLines);
	public void drawHoughCircles(Set<Point3D> abr, double threshold);
	public double otsuThreshold();
}
