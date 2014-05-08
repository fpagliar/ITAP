package utils;

public class MaskFactory {


	/**
	 * Robert's Mask | 1  0 |
	 *               | 0  1 |
	 *               | 0 -1 |
	 *               |-1  0 |
	 * 
	 * @return
	 */
	public static Mask[] buildRobertsMasks() {
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

	/**
	 * Prewitt's Mask | -1 -1 -1 |
	 * 				  | -1  0  1 |
	 * 				  |  0  0  0 |
	 * 				  | -1  0  1 |
	 * 				  |  1  1  1 |
	 * 				  | -1  0  1 |
	 * 
	 * @return
	 */
	public static Mask[] buildPrewittMasks() {
		int size = 3;
		Mask dx = new Mask(size);
		Mask dy = new Mask(size);
		Mask[] array = new Mask[2];
		array[0] = dx;
		array[1] = dy;

		dx.setPixel(-1, -1, -1);
		dx.setPixel(-1, 0, -1);
		dx.setPixel(-1, 1, -1);
		dx.setPixel(1, -1, 1);
		dx.setPixel(1, 0, 1);
		dx.setPixel(1, 1, 1);

		dy.setPixel(-1, -1, -1);
		dy.setPixel(0, -1, -1);
		dy.setPixel(1, -1, -1);
		dy.setPixel(-1, 1, 1);
		dy.setPixel(0, 1, 1);
		dy.setPixel(1, 1, 1);

		return array;
	}

	/**
	 * Sobel's Mask | -1 -2 -1 |
	 *              | -1  0  1 | 
	 *              |  0  0  0 |
	 *              | -2  0  2 | 
 	 *              |  1  2  1 |
	 *              | -1  0  1 | 
	 * @return
	 */
	public static Mask[] buildSobelMasks() {
		int size = 3;
		Mask dx = new Mask(size);
		Mask dy = new Mask(size);
		Mask[] array = new Mask[2];
		array[0] = dx;
		array[1] = dy;
		
		dx.setPixel(-1, -1, -1);
		dx.setPixel(-1, 0, -2);
		dx.setPixel(-1, 1, -1);
		dx.setPixel(1, -1, 1);
		dx.setPixel(1, 0, 2);
		dx.setPixel(1, 1, 1);

		dy.setPixel(-1, -1, -1);
		dy.setPixel(0, -1, -2);
		dy.setPixel(1, -1, -1);
		dy.setPixel(-1, 1, 1);
		dy.setPixel(0, 1, 2);
		dy.setPixel(1, 1, 1);

		return array;
	}
}