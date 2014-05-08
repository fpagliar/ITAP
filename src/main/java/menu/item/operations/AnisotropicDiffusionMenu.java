package menu.item.operations;

import gui.Window;

import javax.swing.JMenu;

import menu.item.operations.anisotropic.LeclercMenuItem;
import menu.item.operations.anisotropic.LorentzianoMenuItem;

public class AnisotropicDiffusionMenu extends JMenu {

		private static final long serialVersionUID = 1L;

		public AnisotropicDiffusionMenu(Window window) {
			super("Anisotropic Diffusion");
			this.setEnabled(true);
			
			this.add(new LeclercMenuItem(window));
			this.add(new LorentzianoMenuItem(window));
	}
}
