package menu;

import gui.Window;

import javax.swing.JMenu;

import menu.item.CopyImageSelection;
import menu.item.LoadImageMenuItem;
import menu.item.SaveImageMenuItem;

public class FileMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public FileMenu(Window window) {
		super("File");
		this.setEnabled(true);

		this.add(new LoadImageMenuItem(window));
		this.add(new SaveImageMenuItem(window));
		this.add(new CopyImageSelection(window));
	}

}
