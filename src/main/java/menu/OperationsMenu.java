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
import menu.item.operations.AddImagesMenuItem;

public class OperationsMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public OperationsMenu(Window window) {
		super("Operations");
		this.setEnabled(true);

		this.add(new AddImagesMenuItem(window));
	}
}
