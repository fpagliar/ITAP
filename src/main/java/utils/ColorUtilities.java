package utils;

import java.awt.Color;

import model.ColorImage;
import model.Image;
import model.Image.ImageFormat;
import model.Image.ImageType;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

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
	
	public static Image generateHistogram(Image image) {

		HistogramDataset dataset = new HistogramDataset();
		dataset.setType(HistogramType.FREQUENCY);

		int[] pixelArray = image.getPixelArray();

		// Getting the gray value of each pixel (gray means r = g = b).
		double[] histogramValues = new double[pixelArray.length+1];
		for(int i=0; i < pixelArray.length; i++)
			histogramValues[i] = (double) (pixelArray[i] & 0xFF);

		//Added a -10 value to move the scale from 0!
		histogramValues[pixelArray.length] = -10;
		dataset.addSeries("Histogram", histogramValues, histogramValues.length);

		// createHistogram(java.lang.String title, java.lang.String xAxisLabel,
		// java.lang.String yAxisLabel, IntervalXYDataset dataset,
		// PlotOrientation orientation, boolean legend, boolean tooltips,
		// boolean urls)
		// Creates a histogram chart.
		JFreeChart chart = ChartFactory.createHistogram("Histogram",
				"Grayscale", "Frequency", dataset, PlotOrientation.VERTICAL, true,
				true, true);
		return new ColorImage(chart.createBufferedImage(400, 200), ImageFormat.BMP, ImageType.RGB);
	}

}
