package utils;

public class MaskFactory {

	public static Mask[] robertsBorderMask() {
		Mask[] array = new Mask[2];
		array[0] = robertsMask();
		array[1] = robertsMask().turn().turn();

		return array;
	}

	public static Mask robertsMask(){
		Mask mask = new Mask(3);
		mask.setPixel(0, 0, 1);
		mask.setPixel(1, 1, -1);
		return mask;
	}
	
	public static Mask[] prewittBorderMask() {
		Mask[] array = new Mask[2];
		array[0] = prewittMask();
		array[1] = prewittMask().turn().turn();
		return array;
	}
	
	public static Mask prewittMask(){
		Mask dx = new Mask(3);
		dx.setPixel(-1, -1, -1); 	//
		dx.setPixel(-1, 0, -1); 	// | -1 -1 -1 |
		dx.setPixel(-1, 1, -1); 	// |  0  0  0 |
		dx.setPixel(1, -1, 1); 		// |  1  1  1 |
		dx.setPixel(1, 0, 1); 		//
		dx.setPixel(1, 1, 1); 		//
		return dx;
	}

	public static Mask[] sobelBorderMask() {
		Mask[] array = new Mask[2];
		array[0] = sobelMask();
		array[1] = sobelMask().turn().turn();
		return array;
	}

	public static Mask sobelMask() {
		Mask mask = new Mask(3);
		mask.setPixel(-1, -1, -1); 	//
		mask.setPixel(-1, 0, -2); 	// | -1 -2 -1 |
		mask.setPixel(-1, 1, -1); 	// |  0  0  0 |
		mask.setPixel(1, -1, 1); 	// |  1  2  1 |
		mask.setPixel(1, 0, 2); 	//
		mask.setPixel(1, 1, 1); 	//
		return mask;
	}
	
	public static Mask susanMask(){
		Mask mask = new Mask(7);
		for(int j = -1; j <= 1; j++){
			mask.setPixel(-3, j, 1);
			mask.setPixel(3, j, 1);
		}
		for(int j = -2; j <= 2; j++){
			mask.setPixel(-2, j, 1);
			mask.setPixel(2, j, 1);
		}
		for(int i = -1 ; i <= 1 ; i ++)
			for(int j = -3 ; j <= 3 ; j++)
				mask.setPixel(i, j, 1);
		return mask;
	}
}