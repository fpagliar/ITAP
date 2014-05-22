package menu.item.mask;

import gui.Window;

import javax.swing.JMenu;

import utils.MaskFactory;
import menu.item.mask.direction.DirectedPrewittMaskMenuItem;
import menu.item.mask.direction.DirectedRobertsMaskMenuItem;
import menu.item.mask.direction.DirectedSobelMaskMenuItem;

public class WithDirectionMenu extends JMenu  {

	private static final long serialVersionUID = 1L;

	public WithDirectionMenu(Window window) {
		super("With direction");
		this.setEnabled(true);
		this.add(new DirectedRobertsMaskMenuItem(window));
		this.add(new DirectedPrewittMaskMenuItem(window));
		this.add(new DirectedSobelMaskMenuItem(window));
		System.out.println(MaskFactory.susanMask());
	}
}
