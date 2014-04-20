package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.Image;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image;
	private Window window;
	private int id;
	JMenuItem menuItem;
	private static int id_seq = 1;

	public Panel(Window window) {
		this.window = window;
		this.id = id_seq;
		id_seq++;

		addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseClicked(MouseEvent arg0) {
				clicked();
				if (image != null)
					selectPixel(arg0.getX(), arg0.getY());
			}
		});
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

	public void loadImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void selectPixel(int x, int y) {
		if (x > 0 && x < image.getHeight() && y > 0 && y < image.getWidth())
			new PixelWindow(x, y, image.getRGBPixel(x, y), this);
	}

	public void setPixel(int x, int y, Color color) {
		if (x > 0 && x < image.getHeight() && y > 0 && y < image.getWidth()) {
			image.setPixel(x, y, color);
			repaint();
		}
	}

	private void clicked() {
		window.focus(this);
	}

	public int getId() {
		return id;
	}

	public void setMenuItem(JMenuItem menuItem) {
		if (this.menuItem == null)
			this.menuItem = menuItem;
	}

	public JMenuItem getMenuItem() {
		return menuItem;
	}
}
