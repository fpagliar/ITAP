package utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import model.ColorImage;
import model.Image;

import org.apache.sanselan.ImageFormat;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.Sanselan;

public class ImageManager {

	public static Image loadImage(File file) throws ImageReadException,
			IOException {

		// Will throw an error if the file is a format it can't understand.
		BufferedImage bufferedImage = Sanselan.getBufferedImage(file);
		ImageInfo info = Sanselan.getImageInfo(file);
		Image.ImageFormat format;

		if (info.getFormat() == ImageFormat.IMAGE_FORMAT_BMP) {
			format = Image.ImageFormat.BMP;
		} else if (info.getFormat() == ImageFormat.IMAGE_FORMAT_PGM) {
			format = Image.ImageFormat.PGM;
		} else if (info.getFormat() == ImageFormat.IMAGE_FORMAT_PPM) {
			format = Image.ImageFormat.PPM;
		} else {
			throw new IllegalStateException("Image format not supported");
		}

		if (bufferedImage.getType() == BufferedImage.TYPE_INT_RGB) {
			return new ColorImage(bufferedImage, format, Image.ImageType.RGB);
		} else if (bufferedImage.getType() == BufferedImage.TYPE_BYTE_GRAY) {
			return new ColorImage(bufferedImage, format,
					Image.ImageType.GRAYSCALE);
		} else {
			throw new IllegalStateException("Image wasn't RGB nor Grayscale");
		}
	}

	public static void saveImage(File arch, Image image)
			throws ImageWriteException, IOException {

		ImageFormat format;

		BufferedImage bi = ImageManager.populateEmptyBufferedImage(image);
		format = ImageManager.toSanselanImageFormat(image
				.getImageFormat());
		Sanselan.writeImage(bi, arch, format, null);
		//TODO: add a method to save in raw format
	}
	
	private static BufferedImage populateEmptyBufferedImage(
			Image image) {
        ColorModel cm = image.getImage().getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.getImage().copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	private static ImageFormat toSanselanImageFormat(Image.ImageFormat fmt) {
		if (fmt == Image.ImageFormat.BMP) {
			return ImageFormat.IMAGE_FORMAT_BMP;
		}
		if (fmt == Image.ImageFormat.PGM) {
			return ImageFormat.IMAGE_FORMAT_PGM;
		}
		if (fmt == Image.ImageFormat.PPM) {
			return ImageFormat.IMAGE_FORMAT_PPM;
		}
		if (fmt == Image.ImageFormat.RAW) {
			return ImageFormat.IMAGE_FORMAT_UNKNOWN;
		}
		throw new IllegalArgumentException();
	}
}
