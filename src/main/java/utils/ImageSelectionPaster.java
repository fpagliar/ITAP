package utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * IMPORTANT: Easily made, not thread safe because it is using class variables.
 * Only use one at a time!
 * 
 * @author using parts of the code from ImageSelectionCapturer.
 */
public class ImageSelectionPaster {

	// TODO: use singleton?
	private static Rectangle lastSelection;
	private static Boolean confirmed;

	public ImageSelectionPaster(final BufferedImage image,
			final Rectangle rectangle, final BufferedImage rectangleImage) {

		final BufferedImage screenCopy = new BufferedImage(image.getWidth(),
				image.getHeight(), image.getType());

		final JLabel screenLabel = new JLabel(new ImageIcon(screenCopy));
		JScrollPane screenScroll = new JScrollPane(screenLabel);

		screenScroll.setPreferredSize(new Dimension(
				(int) (image.getWidth()) + 20, (int) (image.getHeight() + 20)));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(screenScroll, BorderLayout.CENTER);

		repaint(image, screenCopy, null);
		screenLabel.repaint();
		final JLabel selectionLabel = new JLabel("Select the required area");
		panel.add(selectionLabel, BorderLayout.SOUTH);

		screenLabel.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent me) {
			}

			@Override
			public void mouseDragged(MouseEvent me) {
				// me is going to carry the center of the rectangle, cause the
				// rectangle is built as if me was the center
				// by using the variable edge calculated that way

				Point edge = new Point(me.getPoint().x - (rectangle.width / 2),
						me.getPoint().y - (rectangle.height / 2));

				// --- This checks maintain the rectangle inside the image
				if (edge.x < 0)
					edge.x = 0;
				if (edge.x + rectangle.width > image.getWidth())
					edge.x = image.getWidth() - rectangle.width;
				if (edge.y < 0)
					edge.y = 0;
				if (edge.y + rectangle.height > image.getHeight())
					edge.y = image.getHeight() - rectangle.height;
				// ---

				lastSelection = new Rectangle(edge, new Dimension(
						rectangle.width, rectangle.height));
				repaint(image, screenCopy, rectangleImage);
				screenLabel.repaint();
			}
		});

		confirmed = false;

		int ans = JOptionPane.showConfirmDialog(null, panel,
				"Select paste location", JOptionPane.OK_CANCEL_OPTION);
		confirmed = (ans == JOptionPane.OK_OPTION);
	}

	public void repaint(BufferedImage orig, BufferedImage copy,
			BufferedImage rectangleImage) {
		Graphics2D g = copy.createGraphics();
		g.drawImage(orig, 0, 0, null);
		if (lastSelection != null) {
			g.drawImage(rectangleImage, lastSelection.x, lastSelection.y,
					lastSelection.width, lastSelection.height, null);
		}
		g.dispose();
	}

	public static Rectangle getLastSelection() {
		return lastSelection;
	}

	public static boolean isConfirmed() {
		return confirmed;
	}
}