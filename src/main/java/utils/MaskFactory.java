package utils;

public class MaskFactory {

	public static Mask[] robertsMask() {
		int size = 3;
		Mask[] array = new Mask[2];
		Mask dx = new Mask(size);
		Mask dy = new Mask(size);
		array[0] = dx;
		array[1] = dy;

		dx.setPixel(0, 0, 1);
		dx.setPixel(1, 1, -1);
		array[0] = dx;
		
		dy.setPixel(0, 1, 1);
		dy.setPixel(1, 0, -1);

		return array;
	}

	public static Mask[] prewittMask() {
		int size = 3;
		Mask dx = new Mask(size);
		Mask dy = new Mask(size);
		Mask[] array = new Mask[2];
		array[0] = dx;
		array[1] = dy;

		dx.setPixel(-1, -1, -1);  //
		dx.setPixel(-1, 0, -1);   //  | -1 -1 -1 | 
		dx.setPixel(-1, 1, -1);   //  |  0  0  0 |
		dx.setPixel(1, -1, 1);    //  |  1  1  1 |
		dx.setPixel(1, 0, 1);     //
		dx.setPixel(1, 1, 1);     //

		dy.setPixel(-1, -1, -1);  // 
		dy.setPixel(0, -1, -1);   //  | -1  0  1 |
		dy.setPixel(1, -1, -1);   //  | -1  0  1 |
		dy.setPixel(-1, 1, 1);    //  | -1  0  1 |
		dy.setPixel(0, 1, 1);     //
		dy.setPixel(1, 1, 1);     //

		return array;
	}

	public static Mask[] sobelMask() {
		int size = 3;
		Mask dx = new Mask(size);
		Mask dy = new Mask(size);
		Mask[] array = new Mask[2];
		array[0] = dx;
		array[1] = dy;
		
		dx.setPixel(-1, -1, -1); //
		dx.setPixel(-1, 0, -3);  //  | -1 -2 -1 |
		dx.setPixel(-1, 1, -1);  //  |  0  0  0 |
		dx.setPixel(1, -1, 1);   //  |  1  2  1 |
		dx.setPixel(1, 0, 3);    //
		dx.setPixel(1, 1, 1);    //

		dy.setPixel(-1, -1, -1); //
		dy.setPixel(0, -1, -3);  //  | -1  0  1 |
		dy.setPixel(1, -1, -1);  //  | -2  0  2 |
		dy.setPixel(-1, 1, 1);   //  | -1  0  1 |
		dy.setPixel(0, 1, 3);    //
		dy.setPixel(1, 1, 1);    //

		return array;
	}
}