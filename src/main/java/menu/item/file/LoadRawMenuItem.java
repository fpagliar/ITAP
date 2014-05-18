package menu.item.file;

import gui.RawImageLoaderWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class LoadRawMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public LoadRawMenuItem(final Window window) {
		super("Raw Image");

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new RawImageLoaderWindow(window);
			}

		});
	}
}
