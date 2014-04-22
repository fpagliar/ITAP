package utils;

import java.awt.Color;

import model.ColorImage;
import model.Image;

public class BinaryImageManager {

	public static Image createSquare(int height, int width) {
		Image binaryImage = new ColorImage(height, width,
				Image.ImageFormat.BMP, Image.ImageType.RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color color = (y > height / 4 && y < height * 3 / 4
						&& x > width / 4 && x < width * 3 / 4) ? Color.WHITE
						: Color.BLACK;
				if(color == Color.WHITE)
					System.out.println("White");
				binaryImage.setPixel(x, y, color);
			}
		}
		return binaryImage;
	}
}
