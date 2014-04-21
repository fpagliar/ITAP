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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * IMPORTANT: Easily made, not thread safe because it is using class variables. Only use one at a time!
 * @author using parts of the code from ImageSelectionCapturer.
 */
public class ImageSelectionPaster {

	//TODO: use singleton?
	private static Rectangle lastSelection;	
	
	public ImageSelectionPaster(final BufferedImage image, final Rectangle rectangle, final BufferedImage rectangleImage) {
		final BufferedImage screenCopy = new BufferedImage(image.getWidth(),
				image.getHeight(), image.getType());
		final JLabel screenLabel = new JLabel(new ImageIcon(screenCopy));
		JScrollPane screenScroll = new JScrollPane(screenLabel);

		screenScroll.setPreferredSize(new Dimension(
				(int) (image.getWidth()), (int) (image.getHeight())));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(screenScroll, BorderLayout.CENTER);

		final JLabel selectionLabel = new JLabel("Select the required area");
		panel.add(selectionLabel, BorderLayout.SOUTH);

		repaint(image, screenCopy, null);
		screenLabel.repaint();

		screenLabel.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent me) {
			}

			@Override
			public void mouseDragged(MouseEvent me) {
				Point end = me.getPoint();
				Point center = new Point(end.x - (rectangle.width/2), end.y - (rectangle.height/2));
				//TODO: recheck this tomorrow, not working.
				//TODO: bug, closing this window, the paste is done the same
				if(end.x + rectangle.width > image.getWidth() || end.y + rectangle.height > image.getHeight() )
					return;
				lastSelection = new Rectangle(center, new Dimension(rectangle.width, rectangle.height));
				repaint(image, screenCopy, rectangleImage);
				screenLabel.repaint();
			}
		});

		JOptionPane.showMessageDialog(null, panel);
	}

	public void repaint(BufferedImage orig, BufferedImage copy, BufferedImage rectangleImage) {
		Graphics2D g = copy.createGraphics();
		g.drawImage(orig, 0, 0, null);
		if (lastSelection != null) {
			g.drawImage(rectangleImage, lastSelection.x, lastSelection.y, lastSelection.width, lastSelection.height, null);
		}
		g.dispose();
	}
	

	public static Rectangle getLastSelection() {
		return lastSelection;
	}
}