package menu.item.file;

import gui.Window;

import javax.swing.JMenu;


public class LoadImageMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public LoadImageMenu(Window window) {
		super("Load image");
		this.setEnabled(true);
		add(new LoadFormattedMenuItem(window));
		add(new LoadRawMenuItem(window));
	}
}
