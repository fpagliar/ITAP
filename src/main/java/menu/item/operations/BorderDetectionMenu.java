package menu.item.operations;

import gui.Window;

import javax.swing.JMenu;

import menu.item.operations.borderdetection.PrewittMenuItem;
import menu.item.operations.borderdetection.RobertsMenuItem;
import menu.item.operations.borderdetection.SobelMenuItem;

public class BorderDetectionMenu extends JMenu  {

		private static final long serialVersionUID = 1L;

		public BorderDetectionMenu(Window window) {
			super("Border Detection");
			this.setEnabled(true);
			
			this.add(new RobertsMenuItem(window));
			this.add(new PrewittMenuItem(window));
			this.add(new SobelMenuItem(window));
		}
}
