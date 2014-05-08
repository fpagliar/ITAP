package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Image;

public class ApplyFilterWindow {

	private Rectangle captureRect;
	private Boolean rectangleDefined = false;
	private static Image image;
	private Map<Point, Color> points = new HashMap<Point, Color>();
	private int size;
	private int type;
	private double sigma;
	private Window window;

	// TYPE = 1 for mean
	// TYPE = 2 for median
	// TYPE = 3 for gaussian
	//SIGMA IS ONLY USED IN GAUSSIAN MODE
	public ApplyFilterWindow(Window window, final Image image, int size, double sigma, int type) {
		ApplyFilterWindow.image = image;
		this.size = size;
		this.type = type;
		this.sigma = sigma;
		this.window = window;
		
		final BufferedImage bufferedImage = image.getImage();
		final BufferedImage screenCopy = new BufferedImage(
				bufferedImage.getWidth(), bufferedImage.getHeight(),
				bufferedImage.getType());
		final JLabel screenLabel = new JLabel(new ImageIcon(screenCopy));
		JScrollPane screenScroll = new JScrollPane(screenLabel);

		screenScroll.setPreferredSize(new Dimension((int) (bufferedImage
				.getWidth() + 20), (int) (bufferedImage.getHeight() + 20)));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(screenScroll, BorderLayout.CENTER);

		final JLabel selectionLabel = new JLabel("Select the required area");
		panel.add(selectionLabel, BorderLayout.SOUTH);

		repaint(bufferedImage, screenCopy);
		screenLabel.repaint();

		screenLabel.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				rectangleDefined = true;
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
				// TODO Auto-generated method stub

			}
		});
		screenLabel.addMouseMotionListener(new MouseMotionAdapter() {

			Point start = new Point();

			@Override
			public void mouseMoved(MouseEvent me) {
				start = me.getPoint();
				repaint(bufferedImage, screenCopy);
				selectionLabel.setText("Start X:" + start.x + " Y:" + start.y);
				screenLabel.repaint();
			}

			@Override
			public void mouseDragged(MouseEvent me) {
				if (!rectangleDefined) {
					Point end = me.getPoint();
					captureRect = new Rectangle(start, new Dimension(end.x
							- start.x, end.y - start.y));
					repaint(bufferedImage, screenCopy);
					screenLabel.repaint();
					selectionLabel.setText("Start X:" + start.x + " Y:"
							+ start.y);
				} else {
					// me is going to carry the center of the rectangle, cause
					// the
					// rectangle is built as if me was the center
					// by using the variable edge calculated that way

					Point edge = new Point(me.getPoint().x
							- (captureRect.width / 2), me.getPoint().y
							- (captureRect.height / 2));

					// --- This checks maintain the rectangle inside the image
					if (edge.x < 0)
						edge.x = 0;
					if (edge.x + captureRect.width > image.getWidth())
						edge.x = image.getWidth() - captureRect.width;
					if (edge.y < 0)
						edge.y = 0;
					if (edge.y + captureRect.height > image.getHeight())
						edge.y = image.getHeight() - captureRect.height;
					// ---

					captureRect = new Rectangle(edge, new Dimension(
							captureRect.width, captureRect.height));
					repaint(bufferedImage, screenCopy);
					screenLabel.repaint();
					applyFilter();
				}

				// selectionLabel.setText("Rectangle - Width: "
				// + captureRect.width + " Height: " + captureRect.height);
			}
		});

		JOptionPane.showMessageDialog(null, panel);
		Image filteredCopy = image.clone(); 
		for(Point p : points.keySet())
			filteredCopy.setPixel(p.x, p.y, points.get(p));
		window.getUnfocusedPanel().setImage(filteredCopy);
		window.repaint();
		points = new HashMap<Point, Color>();
	}

	public void repaint(BufferedImage orig, BufferedImage copy) {
		Graphics2D g = copy.createGraphics();
		
		Image filteredCopy = image.clone(); 
		for(Point p : points.keySet())
			filteredCopy.setPixel(p.x, p.y, points.get(p));
		
//		g.drawImage(orig, 0, 0, null);
		g.drawImage(filteredCopy.getImage(), 0, 0, null);
		if (captureRect != null) {
			g.setColor(Color.RED);
			g.draw(captureRect);
			g.setColor(new Color(255, 255, 255, 150));
			g.fill(captureRect);
		}
		g.dispose();
	}

	private void applyFilter() {
//		Color[] filteredValues = new Color[captureRect.width
//				* captureRect.height];
		int i = 0;
		for (int x = captureRect.x; x < captureRect.x + captureRect.width; x++)
			for (int y = captureRect.y; y < captureRect.y + captureRect.height; y++) {
				if(!points.containsKey(new Point(x,y))){
					if (type == 1)
						points.put(new Point(x,y), image.applyMeanFilter(x, y, size));
					else if(type == 2)
						points.put(new Point(x,y), image.applyMedianFilter(x, y, size));
					else 
						points.put(new Point(x,y), image.applyGaussianFilter(x, y, size, sigma));					
//					if (type == 1)
//						filteredValues[i++] = image.applyMeanFilter(x, y, size);
//					else if(type == 2)
//						filteredValues[i++] = image.applyMedianFilter(x, y, size);
//					else 
//						filteredValues[i++] = image.applyGaussianFilter(x, y, size, sigma);
				}
			}

//		i = 0;
//		for (int x = captureRect.x; x < captureRect.x + captureRect.width; x++)
//			for (int y = captureRect.y; y < captureRect.y + captureRect.height; y++)
//				points.put(new Point(x, y),  filteredValues[i++]);
////				image.setPixel(x, y, filteredValues[i++]);
	}
}
