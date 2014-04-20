package model;

import gui.Panel;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;

import model.Image.ColorChannel;
import mpi.cbg.fly.SIFT;
import utils.ColorUtilities;

import com.xuggle.xuggler.IVideoResampler.Feature;

public class ColorImage implements Image, Cloneable {

	private ImageType type;
	private ImageFormat format;
	private Channel red;
	private Channel green;
	private Channel blue;
	private BufferedImage image;

	private ColorImage(int height, int width, ImageFormat format, ImageType type) {
		// Initialize a channel for each RGB color
		this.red = new Channel(width, height);
		this.green = new Channel(width, height);
		this.blue = new Channel(width, height);
		this.format = format;
		this.type = type;
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
	}
}
