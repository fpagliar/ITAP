package menu.item.operations;

import gui.Window;

import javax.swing.JMenu;

import menu.item.operations.noise.ExponentialNoiseMenuItem;

public class ApplyNoiseMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public ApplyNoiseMenu(Window window) {
		super("Noise");
		this.setEnabled(true);

		this.add(new ExponentialNoiseMenuItem(window));
	}
}
