package menu.item.mask;

import gui.InputDoubleAction;
import gui.InputDoubleWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.MaskFactory;

public class GaussianLaplacianMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public GaussianLaplacianMenuItem(final Window window) {
		super("Gaussian Laplacian");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new InputDoubleWindow(window, "threshold m:", 30.0, new InputDoubleAction() {
					
					public void performAction(Window window, double input) {
						Image result = window.getFocusedPanel().getImage().clone();
						result.applyMask(MaskFactory.logMask());
						result.markCrossersWithThreshold((int) input);
						window.getUnfocusedPanel().setImage(result);
						window.repaint();						
					}
				});
			}
		});
	}
}