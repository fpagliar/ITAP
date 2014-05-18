package model;

import gui.ErrorWindow;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import utils.Mask;
import utils.RandomNumberGenerator;

public class ColorImage implements Image, Cloneable {

	private ImageType type;
	private ImageFormat format;
	private Channel red;
	private Channel green;
	private Channel blue;
	private BufferedImage image;

	public ColorImage(int height, int width, ImageFormat format, ImageType type) {
		// Initialize a channel for each RGB color
		this.red = new Channel(width, height);
		this.green = new Channel(width, height);
		this.blue = new Channel(width, height);
		this.format = format;
		this.type = type;
		this.image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
	}

	public ColorImage(BufferedImage bufferedImage, ImageFormat format,
			ImageType type) {
		this(bufferedImage.getHeight(), bufferedImage.getWidth(), format, type);

		image = bufferedImage;

		// Gets the array of pixels in the image
		int[] rgbData = bufferedImage.getRGB(0, 0, bufferedImage.getWidth(),
				bufferedImage.getHeight(), null, 0, bufferedImage.getWidth());

		int colorRed;
		int colorGreen;
		int colorBlue;
		// Loads the pixels in each one of the channels
		for (int x = 0; x < bufferedImage.getWidth(); x++) {
			for (int y = 0; y < bufferedImage.getHeight(); y++) {
				colorRed = (rgbData[(y * bufferedImage.getWidth()) + x] >> 16) & 0xFF;
				colorGreen = (rgbData[(y * bufferedImage.getWidth()) + x] >> 8) & 0xFF;
				colorBlue = (rgbData[(y * bufferedImage.getWidth()) + x]) & 0xFF;

				red.setPixel(x, y, colorRed);
				green.setPixel(x, y, colorGreen);
				blue.setPixel(x, y, colorBlue);
			}
		}
	}

	public int getHeight() {
		return red.getHeight();
	}

	public int getWidth() {
		return red.getWidth();
	}

	public ImageType getType() {
		return type;
	}

	public ImageFormat getImageFormat() {
		return format;
	}

	public BufferedImage getImage() {
		this.applyChanges();
		return image;
	}

	public Color getRGBPixel(int x, int y) {
		int red = this.red.truncatePixel(getPixelFromChannel(x, y,
				ColorChannel.RED));
		int green = this.green.truncatePixel(getPixelFromChannel(x, y,
				ColorChannel.GREEN));
		int blue = this.blue.truncatePixel(getPixelFromChannel(x, y,
				ColorChannel.BLUE));
		return new Color(red, green, blue);
	}

	public double getPixelFromChannel(int x, int y, ColorChannel channel) {
		if (channel == ColorChannel.RED) {
			return red.getPixel(x, y);
		}
		if (channel == ColorChannel.GREEN) {
			return green.getPixel(x, y);
		}
		if (channel == ColorChannel.BLUE) {
			return blue.getPixel(x, y);
		}
		throw new IllegalStateException();
	}

	public void setPixel(int x, int y, Color color) {
		red.setPixel(x, y, color.getRed());
		green.setPixel(x, y, color.getGreen());
		blue.setPixel(x, y, color.getBlue());
		image.setRGB(x, y, color.getRGB());
	}

	public Image getImagePart(Rectangle selection) {

		return new ColorImage(image.getSubimage(selection.x, selection.y,
				selection.width, selection.height), this.format, this.type);
	}

	public void pasteImagePart(BufferedImage other, Rectangle selection) {
		// Point check
		if (selection.x < 0 || selection.x > image.getWidth()
				|| selection.y < 0 || selection.y > image.getHeight()) {
			new ErrorWindow("invalid location, out of bounds!");
			return;
		}
		// Rectangle check
		if (selection.x + selection.width > image.getWidth()
				|| selection.y + selection.height > image.getHeight()) {
			new ErrorWindow("invalid location rectangle gets out of bounds");
			return;
		}

		// Gets the array of pixels in the image
		int[] rgbData = other.getRGB(0, 0, selection.width, selection.height,
				null, 0, selection.width);

		int colorRed;
		int colorGreen;
		int colorBlue;
		// Loads the pixels in each one of the channels
		for (int xpos = selection.x; xpos < selection.x + selection.width; xpos++) {
			for (int ypos = selection.y; ypos < selection.y + selection.height; ypos++) {
				// Changing from the image coordinates to the other's
				// coordinates
				colorRed = (rgbData[((ypos - selection.y) * selection.width)
						+ (xpos - selection.x)] >> 16) & 0xFF;
				colorGreen = (rgbData[((ypos - selection.y) * selection.width)
						+ (xpos - selection.x)] >> 8) & 0xFF;
				colorBlue = (rgbData[((ypos - selection.y) * selection.width)
						+ (xpos - selection.x)]) & 0xFF;

				red.setPixel(xpos, ypos, colorRed);
				green.setPixel(xpos, ypos, colorGreen);
				blue.setPixel(xpos, ypos, colorBlue);
				image.setRGB(xpos, ypos,
						other.getRGB(xpos - selection.x, ypos - selection.y));
			}
		}
	}

	public Color calculateAverage(Rectangle selection) {
		// Point check
		if (selection.x < 0 || selection.x > image.getWidth()
				|| selection.y < 0 || selection.y > image.getHeight())
			new ErrorWindow("invalid location, out of bounds!");
		// Rectangle check
		if (selection.x + selection.width > image.getWidth()
				|| selection.y + selection.height > image.getHeight())
			new ErrorWindow("invalid location rectangle gets out of bounds");

		int rSum = 0, gSum = 0, bSum = 0, qty = 0;

		// Loads the pixels in each one of the channels
		for (int xpos = selection.x; xpos < selection.x + selection.width; xpos++) {
			for (int ypos = selection.y; ypos < selection.y + selection.height; ypos++) {
				// Changing from the image coordinates to the other's
				// coordinates
				rSum += red.getPixel(xpos, ypos);
				gSum += green.getPixel(xpos, ypos);
				bSum += blue.getPixel(xpos, ypos);
				qty++;
			}
		}
		return new Color(rSum / qty, gSum / qty, bSum / qty);
	}

	public Image add(Image img) {
		ColorImage ci = (ColorImage) img;
		this.red.add(ci.red);
		this.green.add(ci.green);
		this.blue.add(ci.blue);
		return this;
	}

	public Image substract(Image img) {
		ColorImage ci = (ColorImage) img;
		this.red.substract(ci.red);
		this.green.substract(ci.green);
		this.blue.substract(ci.blue);
		return this;
	}

	public Image multiply(Image img) {
		ColorImage ci = (ColorImage) img;
		this.red.multiply(ci.red);
		this.green.multiply(ci.green);
		this.blue.multiply(ci.blue);
		return this;
	}

	public Image multiply(double scalar) {
		this.red.multiply(scalar);
		this.green.multiply(scalar);
		this.blue.multiply(scalar);
		return this;
	}

	private void applyChanges() {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				this.setPixel(x, y, this.getRGBPixel(x, y));
			}
		}
	}

	public void dynamicRangeCompression() {
		double maxRed = -Double.MAX_VALUE;
		double maxGreen = -Double.MAX_VALUE;
		double maxBlue = -Double.MAX_VALUE;

		// Calculates R
		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
				double redPixel = red.getPixel(x, y);
				double greenPixel = green.getPixel(x, y);
				double bluePixel = blue.getPixel(x, y);

				maxRed = Math.max(maxRed, redPixel);
				maxGreen = Math.max(maxGreen, greenPixel);
				maxBlue = Math.max(maxBlue, bluePixel);
			}
		}

		this.red.dynamicRangeCompression(maxRed);
		this.green.dynamicRangeCompression(maxGreen);
		this.blue.dynamicRangeCompression(maxBlue);
	}

	public void toGrayscale() {
		// TODO: add the grayscale depth (ex 16 possiblities and use a kind of
		// casting to get the real value)
		type = ImageType.GRAYSCALE;
		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
				double redPixel = red.getPixel(x, y);
				double greenPixel = green.getPixel(x, y);
				double bluePixel = blue.getPixel(x, y);
				double avg = (redPixel + greenPixel + bluePixel) / 3;

				red.setPixel(x, y, avg);
				green.setPixel(x, y, avg);
				blue.setPixel(x, y, avg);
			}
		}
	}

	public void negative() {
		this.red.negative();
		this.blue.negative();
		this.green.negative();
	}

	public int[] getPixelArray() {
		return image.getRGB(0, 0, this.getWidth(), this.getHeight(), null, 0,
				this.getWidth());
	}

	public Image clone() {
		ColorImage other = new ColorImage(getHeight(), getWidth(), format, type);
		other.red = red.clone();
		other.green = green.clone();
		other.blue = blue.clone();
		other.image.setRGB(0, 0, getWidth(), getHeight(), getPixelArray(), 0,
				getWidth());
		return other;
	}

	// TODO: fix this! Not working
	public void applyThreshold(double value) {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				double pixelColor = (int) red.getPixel(x, y);
				Color newColor = (pixelColor < value) ? Color.WHITE
						: Color.BLACK;
				// Red green and blue are the same if it is grayscale
				// TODO: should this be available for color images?
				red.setPixel(x, y, newColor.getRed());
				green.setPixel(x, y, newColor.getGreen());
				blue.setPixel(x, y, newColor.getBlue());
			}
		}
	}

	public void exponentialNoise(double u) {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				double noise = RandomNumberGenerator.exponential(u);
				// f(i,j) = s(i,j) + n(i,j) --- s is the image, n is the noise
				// n(i,j) = s(i,j) * yk ------- yk is the exponential variable
				red.setPixel(x, y, red.getPixel(x, y) + red.getPixel(x, y)
						* noise);
				green.setPixel(x, y,
						green.getPixel(x, y) + green.getPixel(x, y) * noise);
				blue.setPixel(x, y, blue.getPixel(x, y) + blue.getPixel(x, y)
						* noise);
			}
		}
	}

	public void rayleighNoise(double epsilon) {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				double random = Math.random();
				if(random*100 < 20){
					double noise = RandomNumberGenerator.rayleigh(epsilon);
					// f(i,j) = s(i,j) + n(i,j) --- s is the image, n is the noise
					// n(i,j) = s(i,j) * yk ------- yk is the rayleigh variable
					red.setPixel(x, y, red.getPixel(x, y) + red.getPixel(x, y)
							* noise);
					green.setPixel(x, y,
							green.getPixel(x, y) + green.getPixel(x, y) * noise);
					blue.setPixel(x, y, blue.getPixel(x, y) + blue.getPixel(x, y)
							* noise);
				}
			}
		}
	}

	public void gausseanNoise(double mean, double standardDeviation) {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				double noise = RandomNumberGenerator.gaussian(mean,
						standardDeviation);
				// f(i,j) = s(i,j) + n(i,j) --- s is the image, n is the noise
				// n(i,j) = s(i,j) * yk ------- yk is the gaussian variable
				red.setPixel(x, y, red.getPixel(x, y) + noise);
				green.setPixel(x, y, green.getPixel(x, y) + noise);
				blue.setPixel(x, y, blue.getPixel(x, y) + noise);
			}
		}
	}

	public void saltAndPepperNoise(double percentage, double po, double p1) {
		// TODO: ask if the random should be calculated for every channel
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				double random = Math.random();
				if(random*100 < percentage){
					random = RandomNumberGenerator.uniform(0, 1);
					if (random <= po) {
						red.setPixel(x, y, Channel.MIN_CHANNEL_COLOR);
						green.setPixel(x, y, Channel.MIN_CHANNEL_COLOR);
						blue.setPixel(x, y, Channel.MIN_CHANNEL_COLOR);
					} else if (random >= p1) {
						red.setPixel(x, y, Channel.MAX_CHANNEL_COLOR);
						green.setPixel(x, y, Channel.MAX_CHANNEL_COLOR);
						blue.setPixel(x, y, Channel.MAX_CHANNEL_COLOR);
					}
				}
			}
		}
	}

	public Color applyMeanFilter(int pixelX, int pixelY, int rectangleSide) {
		// TODO: check if it should send out of bounds exception?
		if (pixelX < 0 || pixelX > getWidth() || pixelY < 0
				|| pixelY > getHeight())
			return null;

		double valueR = 0, valueG = 0, valueB = 0, pixelQty = 0;
		for (int x = pixelX - rectangleSide / 2; x < pixelX + rectangleSide / 2; x++)
			for (int y = pixelY - rectangleSide / 2; y < pixelY + rectangleSide
					/ 2; y++) {
				try {
					valueR += getPixelFromChannel(x, y, ColorChannel.RED);
					valueG += getPixelFromChannel(x, y, ColorChannel.GREEN);
					valueB += getPixelFromChannel(x, y, ColorChannel.BLUE);
					pixelQty++;
				} catch (IndexOutOfBoundsException e) {
					// Ignore
				}
			}
		valueR /= pixelQty;
		valueG /= pixelQty;
		valueB /= pixelQty;
		return new Color((int) valueR, (int) valueG, (int) valueB);
	}

	public Color applyGaussianFilter(int pixelX, int pixelY, int rectangleSide,
			double sigma) {
		 if (rectangleSide % 2 == 0) {
			 rectangleSide++;
		 }
		// Mask mask = new Mask(size);
		double rTotal = 0;
		double gTotal = 0;
		double bTotal = 0;
		for (int x = pixelX - rectangleSide / 2; x <= pixelX + rectangleSide / 2; x++)
			for (int y = pixelY - rectangleSide / 2; y <= pixelY + rectangleSide
					/ 2; y++) {
				double relativeX = pixelX - x;
				double relativeY = pixelY - y;
				
				double gaussianFunction = (1.0 / (2.0 * Math.PI * Math.pow(
						sigma, 2)))
						* Math.exp(-((Math.pow(relativeX, 2) + Math.pow(relativeY, 2)) / (Math
								.pow(sigma, 2))));
//				double gaussianFunction = (1.0 / (2.0 * Math.PI * Math.pow(
//						sigma, 2)))
//						* Math.exp(-((Math.pow(x, 2) + Math.pow(y, 2)) / (Math
//								.pow(sigma, 2))));
				// System.out.println("pi pow:"
				// + (2.0 * Math.PI * Math.pow(sigma, 2)));
				// System.out.println("1/pipow: "
				// + (1.0 / (2.0 * Math.PI * Math.pow(sigma, 2))));
//				System.out.println("pows:" + (Math.pow(x, 2) + Math.pow(y, 2)));
//				System.out.println("sigma pow:" + (Math.pow(sigma, 2)));
//				System.out.println("function:" + gaussianFunction);
				try{
					rTotal += getRGBPixel(x, y).getRed() * gaussianFunction * 2;
					gTotal += getRGBPixel(x, y).getGreen() * gaussianFunction * 2;
					bTotal += getRGBPixel(x, y).getBlue() * gaussianFunction * 2;
				} catch (IndexOutOfBoundsException e) {
					// Ignore
				}
				// total += gaussianFunction * getP;
				// mask.setPixel(i, j, gaussianFunction);
			}
		// for (int i = -mask.getWidth() / 2; i <= mask.getWidth() / 2; i++) {
		// for (int j = -mask.getHeight() / 2; j <= mask.getHeight() / 2; j++) {
		// double oldPixel = mask.getValue(i, j);
		// mask.setPixel(i, j, oldPixel / total);
		// }
		// }
		// System.out.println("TOTAL:" + total);
		// return new Color((int) (getRGBPixel(pixelX, pixelY).getRGB() /
		// total));
//		return new Color((int) (rTotal*total), (int) (gTotal*total), (int) (bTotal*total));
		return new Color((int) (rTotal), (int) (gTotal), (int) (bTotal));
	}

	public Color applyMedianFilter(int pixelX, int pixelY, int rectangleSide) {
		// TODO: check if it should send out of bounds exception?
		if (pixelX < 0 || pixelX > getWidth() || pixelY < 0
				|| pixelY > getHeight())
			return null;

		int length = 0;
		for (int x = pixelX - rectangleSide / 2; x < pixelX + rectangleSide / 2; x++)
			for (int y = pixelY - rectangleSide / 2; y < pixelY + rectangleSide
					/ 2; y++) {
				try {
					getPixelFromChannel(x, y, ColorChannel.RED);
					length++;
				} catch (IndexOutOfBoundsException e) {
					// Ignore
				}
			}
		double[] rValues = new double[length];
		double[] gValues = new double[length];
		double[] bValues = new double[length];
		int i = 0;

		for (int x = pixelX - rectangleSide / 2; x < pixelX + rectangleSide / 2; x++)
			for (int y = pixelY - rectangleSide / 2; y < pixelY + rectangleSide
					/ 2; y++) {
				try {
					rValues[i] = getPixelFromChannel(x, y, ColorChannel.RED);
					gValues[i] = getPixelFromChannel(x, y, ColorChannel.GREEN);
					bValues[i] = getPixelFromChannel(x, y, ColorChannel.BLUE);
					i++;
				} catch (IndexOutOfBoundsException e) {
					// Ignore
				}
			}

		Arrays.sort(rValues);
		Arrays.sort(gValues);
		Arrays.sort(bValues);

		if (length % 2 != 0) {
			// Ex If length is 5, the index is 2 (0,1,2,3,4)
			return new Color((int) rValues[length / 2],
					(int) gValues[length / 2], (int) bValues[length / 2]);
		} else if (length == 2) {
			return new Color((int) (rValues[0] + rValues[1]) / 2,
					(int) (gValues[0] + gValues[1]) / 2,
					(int) (bValues[0] + bValues[1]) / 2);
		} else {
			// Ex If length is 6, the index is 2 and 3 (0,1,2,3,4,5)
			return new Color(
					(int) (rValues[length / 2] + rValues[length / 2 + 1]) / 2,
					(int) (gValues[length / 2] + gValues[length / 2 + 1]) / 2,
					(int) (bValues[length / 2] + bValues[length / 2 + 1]) / 2);
		}
	}
	
	public void applyAnisotropicDiffusion(BorderDetector bd) {
		red = this.red.applyAnisotropicDiffusion(bd);
		green = this.green.applyAnisotropicDiffusion(bd);
		blue = this.blue.applyAnisotropicDiffusion(bd);
	}
	
	public void applyMask(Mask mask) {
		this.red.applyMask(mask);
		this.green.applyMask(mask);
		this.blue.applyMask(mask);
	}
	
	public void applyMasksAndSynth(Mask[] maskArray) {
		Image copy = clone();

		this.applyMask(maskArray[0]);
		copy.applyMask(maskArray[1]);

		this.synthesize(copy);
	}
	
	public void synthesize(Image... imgs) {
		Image[] cimgs = imgs;

		Channel[] redChnls = new Channel[cimgs.length];
		Channel[] greenChnls = new Channel[cimgs.length];
		Channel[] blueChnls = new Channel[cimgs.length];

		for (int i = 0; i < cimgs.length; i++) {
			redChnls[i] = ((ColorImage) cimgs[i]).red;
			greenChnls[i] = ((ColorImage) cimgs[i]).green;
			blueChnls[i] = ((ColorImage) cimgs[i]).blue;
		}

		this.red.synthesize(redChnls);
		this.green.synthesize(greenChnls);
		this.blue.synthesize(blueChnls);
	}
	
	public void contrast(double r1, double r2, double y1, double y2) {
		red.contrast(r1, r2, y1, y2);
		green.contrast(r1, r2, y1, y2);
		blue.contrast(r1, r2, y1, y2);
	}

}