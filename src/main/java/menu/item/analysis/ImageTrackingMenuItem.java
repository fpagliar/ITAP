package menu.item.analysis;

import gui.Window;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;

import model.Image;
import model.Tracker;
import utils.ImageSelectionCapturer;

public class ImageTrackingMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public ImageTrackingMenuItem(final Window window) {
		super("Image tracking");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image image = window.getFocusedPanel().getImage().clone();
				new ImageSelectionCapturer(image);
				List<Point> selection = ImageSelectionCapturer.getPoints();
				Tracker tracker = new Tracker(selection, image.getHeight(),
						image.getWidth());

				int times = (int) (1.5 * Math.max(image.getHeight(),
						image.getWidth()));
				boolean changes = true;
				for (int i = 0; i < times && changes; i++) {
					changes = image.tracking(tracker);
					Image shown = image.clone();
					tracker.markImage(shown);
					window.getUnfocusedPanel().setImage(shown);
					window.getUnfocusedPanel().paint(window.getUnfocusedPanel().getGraphics());
					System.out.println("i:" + i);
				}
				if(!changes)
					System.out.println("got out for lack of changes, total:" + times);
			}
		});
	}
}
