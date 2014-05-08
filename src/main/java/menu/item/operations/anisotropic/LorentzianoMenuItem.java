package menu.item.operations.anisotropic;

import gui.DiffusionWindow;
import gui.InputDoubleAction;
import gui.InputDoubleWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.BorderDetector;

public class LorentzianoMenuItem  extends JMenuItem {

		private static final long serialVersionUID = 1L;

		public LorentzianoMenuItem(final Window window) {
			super("Lorentziano");

			setEnabled(true);

			this.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {				
					new InputDoubleWindow(window, "Sigma", 10.0,
							new InputDoubleAction() {
						public void performAction(Window window, final double input) {
							new DiffusionWindow(
									window.getFocusedPanel().getImage(),
									new BorderDetector() {
										
										public double g(double x) {
											return 1/((Math.pow(x, 2) / Math.pow(input, 2)) + 1);
//											return Math.exp(-Math.pow(x, 2)
//													/ Math.pow(input, 2));
										}
									});
						}
					});
				}
			});
		}
	}
