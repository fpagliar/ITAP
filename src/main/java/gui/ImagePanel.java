package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.Image;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;

	public ImagePanel(Image image){
		this.image = image;
	}
	
	/**
	 * Paints an image in the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Image will be null when no image is loaded (black background).
		if (image != null) {
			BufferedImage bufferedImage = image.getImage();
			g.drawImage(bufferedImage, 0, 0, null);
		}
	}

}
