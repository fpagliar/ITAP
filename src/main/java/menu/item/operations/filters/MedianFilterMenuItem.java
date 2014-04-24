package menu.item.operations.filters;

import gui.ApplyFilterWindow;
import gui.InputDoubleAction;
import gui.InputDoubleWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class MedianFilterMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public MedianFilterMenuItem(final Window window) {
		super("Median");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new InputDoubleWindow(window, "Side:", 3.0,
						new InputDoubleAction() {

							public void performAction(Window window,
									double input) {
								new ApplyFilterWindow(window.getFocusedPanel()
										.getImage(), (int) input, 2);
							}
						});
			}
		});

	}
}
