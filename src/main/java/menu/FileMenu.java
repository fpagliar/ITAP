package menu;

import gui.Window;

import javax.swing.JMenu;

import menu.item.CopyImageSelectionMenuItem;
import menu.item.CreateCircleMenuItem;
import menu.item.CreateSquareMenuItem;
import menu.item.LoadImageMenuItem;
import menu.item.PasteImageSelectionMenuItem;
import menu.item.SaveImageMenuItem;

public class FileMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public FileMenu(Window window) {
		super("File");
		this.setEnabled(true);

		this.add(new LoadImageMenuItem(window));
		this.add(new SaveImageMenuItem(window));
		this.add(new CopyImageSelectionMenuItem(window));
		this.add(new PasteImageSelectionMenuItem(window));
		this.add(new CreateSquareMenuItem(window));
		this.add(new CreateCircleMenuItem(window));
	}

}
