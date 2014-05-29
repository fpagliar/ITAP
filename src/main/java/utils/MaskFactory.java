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
	
	public static Mask laplacianMask(){
		Mask mask = new Mask(3);
		mask.setPixel(-1, 0, -1); 	// |  0 -1  0 |
		mask.setPixel(1, 0, -1); 	// | -1  4 -1 |
		mask.setPixel(0, -1, -1); 	// |  0 -1  0 |
		mask.setPixel(0, 1, -1); 	//
		mask.setPixel(0, 0, 4); 	//
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
	
	public static Mask gaussianMask() {
		Mask mask = new Mask(5);
		mask.setPixel(-2, -2, 2.0/159);
		mask.setPixel(-2, -1, 4.0/159);
		mask.setPixel(-2, 0, 5.0/159);
		mask.setPixel(-2, 1, 4.0/159);
		mask.setPixel(-2, 2, 2.0/159);

		mask.setPixel(-1, -2, 4.0/159);
		mask.setPixel(-1, -1, 9.0/159);
		mask.setPixel(-1, 0, 12.0/159);
		mask.setPixel(-1, 1, 9.0/159);
		mask.setPixel(-1, 2, 4.0/159);

		mask.setPixel(0, -2, 5.0/159);
		mask.setPixel(0, -1, 12.0/159);
		mask.setPixel(0, 0, 15.0/159);
		mask.setPixel(0, 1, 12.0/159);
		mask.setPixel(0, 2, 5.0/159);

		mask.setPixel(1, -2, 4.0/159);
		mask.setPixel(1, -1, 9.0/159);
		mask.setPixel(1, 0, 12.0/159);
		mask.setPixel(1, 1, 9.0/159);
		mask.setPixel(1, 2, 4.0/159);

		mask.setPixel(2, -2, 2.0/159);
		mask.setPixel(2, -1, 4.0/159);
		mask.setPixel(2, 0, 5.0/159);
		mask.setPixel(2, 1, 4.0/159);
		mask.setPixel(2, 2, 2.0/159);
		
		return mask;
	}
	
	public static Mask logMask() {
		Mask mask = new Mask(7);
		mask.setPixel(-3, -3, 0.0008);
		mask.setPixel(-3, -2, 0.0066);
		mask.setPixel(-3, -1, 0.0215);
		mask.setPixel(-3, 0, 0.031);
		mask.setPixel(-3, 1, 0.0215);
		mask.setPixel(-3, 2, 0.0066);
		mask.setPixel(-3, 3, 0.0008);
		
		mask.setPixel(-2, -3, 0.0066);
		mask.setPixel(-2, -2, 0.0438);
		mask.setPixel(-2, -1, 0.0982);
		mask.setPixel(-2, 0, 0.108);
		mask.setPixel(-2, 1, 0.0982);
		mask.setPixel(-2, 2, 0.0438);
		mask.setPixel(-2, 3, 0.0066);

		mask.setPixel(-1, -3, 0.0215);
		mask.setPixel(-1, -2, 0.0982);
		mask.setPixel(-1, -1, 0);
		mask.setPixel(-1, 0, -0.242);
		mask.setPixel(-1, 1, 0);
		mask.setPixel(-1, 2, 0.0982);
		mask.setPixel(-1, 3, 0.0215);

		mask.setPixel(0, -3, 0.031);
		mask.setPixel(0, -2, 0.108);
		mask.setPixel(0, -1, -0.242);
		mask.setPixel(0, 0, -0.7979);
		mask.setPixel(0, 1, -0.242);
		mask.setPixel(0, 2, 0.108);
		mask.setPixel(0, 3, 0.031);

		mask.setPixel(1, -3, 0.0215);
		mask.setPixel(1, -2, 0.0982);
		mask.setPixel(1, -1, 0);
		mask.setPixel(1, 0, -0.242);
		mask.setPixel(1, 1, 0);
		mask.setPixel(1, 2, 0.0982);
		mask.setPixel(1, 3, 0.0215);
		
		mask.setPixel(2, -3, 0.0066);
		mask.setPixel(2, -2, 0.0438);
		mask.setPixel(2, -1, 0.0982);
		mask.setPixel(2, 0, 0.108);
		mask.setPixel(2, 1, 0.0982);
		mask.setPixel(2, 2, 0.0438);
		mask.setPixel(2, 3, 0.0066);

		mask.setPixel(3, -3, 0.0008);
		mask.setPixel(3, -2, 0.0066);
		mask.setPixel(3, -1, 0.0215);
		mask.setPixel(3, 0, 0.031);
		mask.setPixel(3, 1, 0.0215);
		mask.setPixel(3, 2, 0.0066);
		mask.setPixel(3, 3, 0.0008);
		return mask;
	}
}