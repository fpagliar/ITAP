package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import org.apache.sanselan.ImageFormat;

import model.Image;

public class ColorUtilities {

	public static int getRedFromRGB(int rgb) {
		return new Color(rgb).getRed();
	}

	public static int getGreenFromRGB(int rgb) {
		return new Color(rgb).getGreen();
	}

	public static int getBlueFromRGB(int rgb) {
		return new Color(rgb).getBlue();
	}

}
