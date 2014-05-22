package menu.item.mask.direction;

import gui.InputDoubleAction;
import gui.InputDoubleWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.Mask;
import utils.MaskFactory;

public class DirectedPrewittMaskMenuItem extends JMenuItem {
	private static final long serialVersionUID = 1L;

	public DirectedPrewittMaskMenuItem(final Window window) {
		super("Prewitt");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new InputDoubleWindow(window, "Turns: ", 0.0, new InputDoubleAction() {
					
					public void performAction(Window window, double input) {
						Image result = window.getFocusedPanel().getImage().clone();
						Mask mask = MaskFactory.prewittMask();
						for(int i = 0; i < input ; i++){
							mask = mask.turn();
						}
						Mask[] array = new Mask[1];
						array[0] = mask;
						result.applyMasksAndSynth(array);
		
						window.getUnfocusedPanel().setImage(result);
						window.repaint();
						
					}
				});
			}
		});
	}

}
