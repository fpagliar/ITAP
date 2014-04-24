package menu.item.operations;

import gui.Window;

import javax.swing.JMenu;

import menu.item.operations.noise.ExponentialNoiseMenuItem;
import menu.item.operations.noise.GaussianNoiseMenuItem;
import menu.item.operations.noise.RayleighNoiseMenuItem;
import menu.item.operations.noise.SaltAndPepperMenuItem;

public class ApplyNoiseMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public ApplyNoiseMenu(Window window) {
		super("Noise");
		this.setEnabled(true);

		this.add(new ExponentialNoiseMenuItem(window));
		this.add(new RayleighNoiseMenuItem(window));
		this.add(new GaussianNoiseMenuItem(window));
		this.add(new SaltAndPepperMenuItem(window));
	}
}
