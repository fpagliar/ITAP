package menu.item.operations;

import gui.ScalarProductWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ScalarProductMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public ScalarProductMenuItem(final Window window) {
		super("Scalar product");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new ScalarProductWindow(window);
			}
		});
	}
}
