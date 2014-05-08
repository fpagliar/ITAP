package utils;

public class Mask {

	private double[][] values;

	public Mask(int squareSide) {
		this.values = new double[squareSide][squareSide];
	}

	public double accessDirectly(int x, int y) {
		return values[x][y];
	}

	void setPixel(int x, int y, double value) {
		if (!validPixel(x, y)) {
			throw new IndexOutOfBoundsException();
		}

		this.values[x + getWidth() / 2][y + getHeight() / 2] = value;
	}

	public double getValue(int x, int y) {
		if (!validPixel(x, y)) {
			throw new IndexOutOfBoundsException();
		}

		return this.values[x + getWidth() / 2][y + getHeight() / 2];
	}

	private boolean validPixel(int x, int y) {
		boolean validX = x >= -this.getWidth() / 2 && x <= this.getWidth() / 2;
		boolean validY = y >= -this.getHeight() / 2
				&& y <= this.getHeight() / 2;
		return validX && validY;
	}

	public int getHeight() {
		return this.values[0].length;
	}

	public int getWidth() {
		return this.values.length;
	}

}