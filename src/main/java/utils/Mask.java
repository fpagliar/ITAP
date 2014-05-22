package utils;

public class Mask {

	public enum Direction {
		TOP(0), TOPRIGHT(1), RIGHT(2), BOTTOMRIGHT(3), BOTTOM(4), BOTTOMLEFT(5), LEFT(
				6), TOPLEFT(7);

		private int turns;

		private Direction(int c) {
			turns = c;
		}

		public int getTurns() {
			return turns;
		}
	};

	private double[][] values;
	private Direction direction;

	public Mask(int squareSide) {
		this.values = new double[squareSide][squareSide];
	}

	public Mask(int squareSide, Direction direction) {
		this(squareSide);
		this.direction = direction;
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

	public Mask changeDirection(Direction direction) {
		Mask ret = this.clone();
		for (int i = this.direction.turns; i < direction.turns; i++)
			ret = ret.turn();
		return ret;
	}

	// FIXME: works only for 3x3 matrixes
	public Mask turn() {
		Mask ret = new Mask(values.length, direction);
		int i = 0;
		int j = 0;
		// Row 1 ->
		for (; j < values[i].length - 1; j++)
			ret.values[i][j + 1] = this.values[i][j];
		// Col 3 down
		j = this.values.length - 1;
		for (i = 0; i < this.values.length - 1; i++)
			ret.values[i + 1][j] = this.values[i][j];
		// Row 3 <-
		i = values.length - 1;
		for (j = values.length - 1; j > 0; j--)
			ret.values[i][j - 1] = this.values[i][j];
		// Col 1 up
		j = 0;
		for (i = values.length - 1; i > 0; i--)
			ret.values[i - 1][j] = this.values[i][j];
		// CENTER
		ret.values[1][1] = this.values[1][1];
		return ret;
	}

	@Override
	protected Mask clone() {
		Mask ret = new Mask(values.length, direction);
		for (int i = 0; i < values.length; i++)
			for (int j = 0; j < values[i].length; j++)
				ret.values[i][j] = this.values[i][j];
		return ret;
	}

	public Direction getDirection() {
		return direction;
	}

	public String toString() {
		String ans = "";
		for (int i = 0; i < values.length; i++) {
			ans += "| ";
			for (int j = 0; j < values[i].length; j++) {
				ans += this.values[i][j];
				ans += "| ";
			}
			ans += "\n";
		}
		return ans;
	}

}