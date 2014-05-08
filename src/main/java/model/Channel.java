package model;

import java.awt.Color;

public class Channel implements Cloneable {

	static final int MIN_CHANNEL_COLOR = 0;
	static final int MAX_CHANNEL_COLOR = 255;

	private int width;
	private int height;

	// The matrix is represented by an array, and to get a pixel(x,y) make y *
	// this.getWidth() + x
	private double[] channel;

	public Channel(int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException(
					"Images must have at least 1x1 pixel size");
		}

		this.width = width;
		this.height = height;
		this.channel = new double[width * height];
	}

	/**
	 * Indicates whether a coordinate is valid for a pixel
	 * 
	 * @param x
	 * @param y
	 * @return True if the pixel is valid
	 */
	public boolean validPixel(int x, int y) {
		boolean validX = x >= 0 && x < this.getWidth();
		boolean validY = y >= 0 && y < this.getHeight();
		return validX && validY;
	}

	/**
	 * Returns the Channel height
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the Channel width
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets a pixel(x,y) in the channel
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	public void setPixel(int x, int y, double color) {
		if (!validPixel(x, y)) {
			throw new IndexOutOfBoundsException();
		}

		channel[y * this.getWidth() + x] = color;
	}

	/**
	 * Returns a pixel in the position x,y
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public double getPixel(int x, int y) {
		if (!validPixel(x, y)) {
			throw new IndexOutOfBoundsException();
		}

		return channel[y * this.getWidth() + x];
	}

	int truncatePixel(double originalValue) {
		if (originalValue > Channel.MAX_CHANNEL_COLOR) {
			return Channel.MAX_CHANNEL_COLOR;
		} else if (originalValue < Channel.MIN_CHANNEL_COLOR) {
			return Channel.MIN_CHANNEL_COLOR;
		}
		return (int) originalValue;
	}

	public void add(Channel otherChannel) {
		for (int x = 0; x < width && x < otherChannel.width; x++) {
			for (int y = 0; y < height && y < otherChannel.height; y++) {
				double color = this.getPixel(x, y)
						+ otherChannel.getPixel(x, y);
				this.setPixel(x, y, color);
			}
		}
	}

	public void substract(Channel otherChannel) {
		for (int x = 0; x < width && x < otherChannel.width; x++) {
			for (int y = 0; y < height && y < otherChannel.height; y++) {
				double color = this.getPixel(x, y)
						- otherChannel.getPixel(x, y);
				color = Math.abs(color);
				this.setPixel(x, y, color);
			}
		}
	}

	public void multiply(Channel otherChannel) {
		for (int x = 0; x < width && x < otherChannel.width; x++) {
			for (int y = 0; y < height && y < otherChannel.height; y++) {
				double color = this.getPixel(x, y)
						* otherChannel.getPixel(x, y);
				this.setPixel(x, y, color);
			}
		}
	}

	public void multiply(double scalar) {
		for (int i = 0; i < this.channel.length; i++) {
			this.channel[i] *= scalar;
		}
	}

	public void negative() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				double color = this.getPixel(x, y);
				//T(r) = -r + L - 1 -- (L - 1 is represented by MAX_CHANNEL_COLOR)
				this.setPixel(x, y, - color + MAX_CHANNEL_COLOR);
			}
		}
	}

	@Override
	public Channel clone() {
		Channel newChannel = new Channel(width, height);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				newChannel.setPixel(i, j, this.getPixel(i, j));
			}
		}
		return newChannel;
	}
	
	public void dynamicRangeCompression(double R) {
		double L = MAX_CHANNEL_COLOR;
		double c = (L - 1) / Math.log(1 + R);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				double r = this.getPixel(x, y);
				double color = (double) (c * Math.log(1 + r));
				this.setPixel(x, y, color);
			}
		}
	}
	
	/**
	 * Applies one iteration of anisotropic diffusion.
	 * @param bd
	 * @return
	 */
	public Channel applyAnisotropicDiffusion(BorderDetector bd) {
		Channel newChannel = new Channel(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				double oldValueIJ = getPixel(x, y);

				double DnIij = x > 0 ? getPixel(x - 1, y)
						- oldValueIJ : 0;
				double DsIij = x < width - 1 ? getPixel(x + 1, y)
						- oldValueIJ : 0;
				double DeIij = y < height - 1 ? getPixel(x, y + 1)
						- oldValueIJ : 0;
				double DoIij = y > 0 ? getPixel(x, y - 1)
						- oldValueIJ : 0;

				double Cnij = bd.g(DnIij);
				double Csij = bd.g(DsIij);
				double Ceij = bd.g(DeIij);
				double Coij = bd.g(DoIij);

				double DnIijCnij = DnIij * Cnij;
				double DsIijCsij = DsIij * Csij;
				double DeIijCeij = DeIij * Ceij;
				double DoIijCoij = DoIij * Coij;

				double lambda = 0.25;
				double newValueIJ = oldValueIJ + lambda
						* (DnIijCnij + DsIijCsij + DeIijCeij + DoIijCoij);
				newChannel.setPixel(x, y, newValueIJ);
			}
		}
		return newChannel;
	}
}
