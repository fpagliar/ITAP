package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Tracker {

	public final static int OUTER = 3;
	public final static int OUTER_BORDER = 1;
	public final static int INNER = -3;
	public final static int INNER_BORDER = -1;
	
	private int[][] values;
	private int height;
	private int width;

	public Tracker(List<Point> selection, int height, int width) {
		values = new int[width][height];
		this.height = height;
		this.width = width;

		for (int x = 0; x < height; x++)
			for (int y = 0; y < width; y++)
				setOuter(x, y);
		
		for(Point p: selection)
			setInner(p.x, p.y);
		
		//Inner borders
		for(Point p: selection)
			for(Point neighbour: neighbours(p))
				if(validPixel(neighbour) && isOuter(neighbour))
					setInnerBorder(p.x, p.y);
				
			

		//Outer borders
		for(Point p: selection)
			for(Point neighbour: neighbours(p))
				if(validPixel(neighbour) && isInnerBorder(p) && isOuter(neighbour))
					setOuterBorder(neighbour.x, neighbour.y);
	
	}

	public List<Point> neighbours(Point p) {
		List<Point> resp = new ArrayList<Point>();
		Point n = new Point(p.x, p.y - 1);
		if(validPixel(n))
			resp.add(n);
		
		n = new Point(p.x - 1, p.y);
		if(validPixel(n))
			resp.add(n);
		
		n = new Point(p.x + 1, p.y);
		if(validPixel(n))
			resp.add(n);
		
		n = new Point(p.x, p.y + 1);
		if(validPixel(n))
			resp.add(n);
		return resp;
	}

	public boolean validPixel(int x, int y) {
		boolean validX = (x >= 0) && (x < height);
		boolean validY = (y >= 0) && (y < width);
		return validX && validY;
	}

	public boolean validPixel(Point p) {
		return validPixel(p.x, p.y);
	}

	public boolean isOuter(Point p) {
		return isOuter(p.x, p.y);
	}

	public boolean isOuter(int x, int y) {
		return values[x][y] == OUTER;
	}

	public boolean isOuterBorder(Point p) {
		return isOuterBorder(p.x, p.y);
	}

	public boolean isOuterBorder(int x, int y) {
		return values[x][y] == OUTER_BORDER;
	}

	public boolean isInner(Point p) {
		return isInner(p.x, p.y);
	}

	public boolean isInner(int x, int y) {
		return values[x][y] == INNER;
	}

	public boolean isInnerBorder(Point p) {
		return isInnerBorder(p.x, p.y);
	}

	public boolean isInnerBorder(int x, int y) {
		return values[x][y] == INNER_BORDER;
	}

	public List<Point> getOuterBorder() {
		List<Point> border = new ArrayList<Point>();
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++)
				if (isOuterBorder(x, y)) {
					border.add(new Point(x, y));
				}
		}
		return border;
	}

	public List<Point> getInnerBorder() {
		List<Point> border = new ArrayList<Point>();
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++)
				if (isInnerBorder(x, y)) {
					border.add(new Point(x, y));
				}
		}
		return border;
	}

	public List<Point> getInner() {
		List<Point> list = new ArrayList<Point>();
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++)
				if (isInner(x, y)) {
					list.add(new Point(x, y));
				}
		}
		return list;
	}

	public List<Point> getOuter() {
		List<Point> list = new ArrayList<Point>();
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++)
				if (isOuter(x, y)) {
					list.add(new Point(x, y));
				}
		}
		return list;
	}

	public void switchIn(int x, int y){
		if(!isOuterBorder(x, y))
			throw new RuntimeException("Pixel x:" + x + " y:" + y + " not in border, value:" + values[x][y]);
		setInnerBorder(x, y);
		for(Point p : neighbours(new Point(x, y)))
			if(isOuter(p))
				setOuterBorder(p.x, p.y);
	}
	
	public void switchOut(int x, int y){
		if(!isInnerBorder(x, y))
			throw new RuntimeException("Pixel x:" + x + " y:" + y + " not in border, value:" + values[x][y]);
		setOuterBorder(x, y);
		for(Point p : neighbours(new Point(x, y)))
			if(isInner(p))
				setInnerBorder(p.x, p.y);
	}	
	
	public void setInner(int x, int y) {
		values[x][y] = INNER;
	}

	public void setInnerBorder(int x, int y) {
		values[x][y] = INNER_BORDER;
	}

	public void setOuter(int x, int y) {
		values[x][y] = OUTER;
	}

	public void setOuterBorder(int x, int y) {
		values[x][y] = OUTER_BORDER;
	}
	
	public void markImage(Image image) {
		for(Point p : getInnerBorder())
			image.setPixel(p.x, p.y, Color.RED);
		for(Point p : getOuterBorder())
			image.setPixel(p.x, p.y, Color.BLUE);
	}
}
