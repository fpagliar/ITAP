package menu.item.mask;

import gui.InputDoubleAction;
import gui.InputDoubleWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.MaskFactory;

public class SusanMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public SusanMenuItem(final Window window) {
		super("Susan");

		setEnabled(true);
		System.out.println(MaskFactory.susanMask());

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new InputDoubleWindow(window, "threshold", 10.0, new InputDoubleAction() {
					
					public void performAction(Window window, double input) {
						Image result = window.getFocusedPanel().getImage().clone();
						result.applySusan(MaskFactory.susanMask(), input);
						window.getUnfocusedPanel().setImage(result);
						window.repaint();
					}
				});
			}
		});
	}
}