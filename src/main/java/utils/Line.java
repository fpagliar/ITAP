package utils;

public class Line {

//	private double ro;
//	private double tita;
	private int x0, x1, y0, y1;

	public Line(double ro, double tita, int width, int height) throws Exception {
//		this.ro = ro;
//		this.tita = tita;
		int i = 0;
		if (tita == 0) {
			x0 = (int) ro;
			y0 = 10;
			x1 = (int) ro;
			y1 = 20;
		} else {
			boolean flag = false;
			int val = 0;
			for (i = 0; i < width && !flag; i++) {
				val = (int) ((ro - i * Math.cos(tita)) / Math.sin(tita));
				if(val > 0 && val < height)
					flag = true;
			}
			this.x0 = i;
			this.y0 = val;
			i++;
			flag  = false;
			for (; i < width && !flag; i++) {
				val = (int) ((ro - i * Math.cos(tita)) / Math.sin(tita));
				if(val > 0 && val < height)
					flag = true;
			}
			this.x1 = i;
			this.y1 = val;
			if(i >= width)
				throw new Exception("Invalid line");

			int prevVal = val;
			int prevI = i;
			for(; i < width; i++){
				val = (int) ((ro - i * Math.cos(tita)) / Math.sin(tita));
				if(val > 0 && val < height) {
					prevI = i;
					prevVal = val;
				}
			}
			this.x1 = prevI;
			this.y1 = prevVal;
		}
	}

	public int getX0() {
		return x0;
//		return 0;
	}

	public int getY0() {
		return y0;
//		if (tita == 0)
//			return 0;
//		return (int) (ro / Math.sin(tita));
	}

	public int getX1() {
//		return 1;
		return x1;
	}

	public int getY1() {
		return y1;
//		if (tita == 0)
//			return 0;
//		return (int) ((ro - Math.cos(tita)) / Math.sin(tita));
	}
}
