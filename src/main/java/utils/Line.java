package utils;

public class Line {

	private double ro;
	private double tita;

	public Line(double ro, double tita) {
		this.ro = ro;
		this.tita = tita;
	}

	public int getX0() {
		return 0;
	}

	public int getY0() {
		return (int) (ro / Math.sin(tita));
	}

	public int getX1() {
		return 1;
	}

	public int getY1() {
		return (int) ((ro - Math.cos(tita))/ Math.sin(tita));
	}
}
