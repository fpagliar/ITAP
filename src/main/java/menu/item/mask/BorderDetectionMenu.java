package menu.item.mask;

import gui.Window;

import javax.swing.JMenu;

import menu.item.mask.borderdetection.PrewittBorderMenuItem;
import menu.item.mask.borderdetection.RobertsBorderMenuItem;
import menu.item.mask.borderdetection.SobelBorderMenuItem;

public class BorderDetectionMenu extends JMenu  {

		private static final long serialVersionUID = 1L;

		public BorderDetectionMenu(Window window) {
			super("Border Detection");
			this.setEnabled(true);
			
			this.add(new RobertsBorderMenuItem(window));
			this.add(new PrewittBorderMenuItem(window));
			this.add(new SobelBorderMenuItem(window));
		}
}
