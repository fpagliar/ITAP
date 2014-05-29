package menu;

import gui.Window;

import javax.swing.JMenu;

import menu.item.mask.BorderDetectionMenu;
import menu.item.mask.GaussianMaskMenuItem;
import menu.item.mask.LaplacianMenuItem;
import menu.item.mask.NotMaximumSupressionMenuItem;
import menu.item.mask.WithDirectionMenu;

public class MaskMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public MaskMenu(Window window) {
		super("Mask");
		this.setEnabled(true);

		this.add(new BorderDetectionMenu(window));
		this.add(new WithDirectionMenu(window));
		this.add(new LaplacianMenuItem(window));
		this.add(new GaussianMaskMenuItem(window));
		this.add(new NotMaximumSupressionMenuItem(window));
	}
}