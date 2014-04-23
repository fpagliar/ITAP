package menu;

import gui.Window;

import javax.swing.JMenu;

import menu.item.file.CalculateAverageColor;
import menu.item.file.CopyImageSelectionMenuItem;
import menu.item.file.CreateCircleMenuItem;
import menu.item.file.CreateSquareMenuItem;
import menu.item.file.LoadImageMenuItem;
import menu.item.file.PasteImageSelectionMenuItem;
import menu.item.file.SaveImageMenuItem;

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
		this.add(new CalculateAverageColor(window));
	}
}
