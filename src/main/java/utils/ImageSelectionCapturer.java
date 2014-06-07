package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Image;

/**
 * IMPORTANT: Easily made, not thread safe because it is using class variables. Only use one at a time!
 * @author Andrew Thompson ->
 *         http://stackoverflow.com/questions/11006496/select-
 *         an-area-to-capture-using-the-mouse -- Small modifications made by me.
 */
public class ImageSelectionCapturer {

	//TODO: use singleton?
	private Rectangle captureRect;
	private static Rectangle lastSelection;
	private static Image image;

	public ImageSelectionCapturer(final Image image) {
		ImageSelectionCapturer.image = image;

		final BufferedImage bufferedImage = image.getImage();
		final BufferedImage screenCopy = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), bufferedImage.getType());
		final JLabel screenLabel = new JLabel(new ImageIcon(screenCopy));
		JScrollPane screenScroll = new JScrollPane(screenLabel);

		screenScroll.setPreferredSize(new Dimension(
				(int) (bufferedImage.getWidth() + 20), (int) (bufferedImage.getHeight() + 20)));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(screenScroll, BorderLayout.CENTER);

		final JLabel selectionLabel = new JLabel("Select the required area");
		panel.add(selectionLabel, BorderLayout.SOUTH);

		repaint(bufferedImage, screenCopy);
		screenLabel.repaint();

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
				Point end = me.getPoint();
				captureRect = new Rectangle(start, new Dimension(end.x
						- start.x, end.y - start.y));
				repaint(bufferedImage, screenCopy);
				screenLabel.repaint();
				selectionLabel.setText("Rectangle - Width: " + captureRect.width + " Height: " + captureRect.height);
			}
		});

		JOptionPane.showMessageDialog(null, panel);
		lastSelection = captureRect;
	}

	public void repaint(BufferedImage orig, BufferedImage copy) {
		Graphics2D g = copy.createGraphics();
		g.drawImage(orig, 0, 0, null);
		if (captureRect != null) {
			g.setColor(Color.RED);
			g.draw(captureRect);
			g.setColor(new Color(255, 255, 255, 150));
			g.fill(captureRect);
		}
		g.dispose();
	}

	public static Rectangle getLastSelection() {
		return lastSelection;
	}
	
	public static List<Point> getPoints() {
		List<Point> points = new ArrayList<Point>();
		for (int x = lastSelection.x; x < lastSelection.x + lastSelection.width; x++)
			for (int y = lastSelection.y; y < lastSelection.y + lastSelection.height; y++)
				points.add(new Point(x, y));
		return points;
	}
	
	public static Image getImage() {
		return image;
	}
}