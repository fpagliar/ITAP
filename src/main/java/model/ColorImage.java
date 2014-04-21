package model;

import gui.ErrorWindow;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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

	public Image getImagePart(Rectangle selection) {

		return new ColorImage(image.getSubimage(selection.x, selection.y,
				selection.width, selection.height), this.format, this.type);
	}

	public void pasteImagePart(BufferedImage other, Rectangle selection) {
		// Point check
		if (selection.x < 0 || selection.x > image.getWidth()
				|| selection.y < 0 || selection.y > image.getHeight())
			new ErrorWindow("invalid location, out of bounds!");
		// Rectangle check
		if (selection.x + selection.width > image.getWidth()
				|| selection.y + selection.height > image.getHeight())
			new ErrorWindow("invalid location rectangle gets out of bounds");

		// Gets the array of pixels in the image
		int[] rgbData = other.getRGB(0, 0, selection.width, selection.height,
				null, 0, selection.width);

		int colorRed;
		int colorGreen;
		int colorBlue;
		// Loads the pixels in each one of the channels
		for (int xpos = selection.x; xpos < selection.x + selection.width; xpos++) {
			for (int ypos = selection.y; ypos < selection.y + selection.height; ypos++) {
				//Changing from the image coordinates to the other's coordinates
				colorRed = (rgbData[((ypos - selection.y) * selection.width) + (xpos - selection.x)] >> 16) & 0xFF;
				colorGreen = (rgbData[((ypos - selection.y) * selection.width) + (xpos - selection.x)] >> 8) & 0xFF;
				colorBlue = (rgbData[((ypos - selection.y) * selection.width) + (xpos - selection.x)]) & 0xFF;

				red.setPixel(xpos, ypos, colorRed);
				green.setPixel(xpos, ypos, colorGreen);
				blue.setPixel(xpos, ypos, colorBlue);
				image.setRGB(xpos, ypos, other.getRGB(xpos - selection.x, ypos- selection.y));
			}
		}
	}
}
