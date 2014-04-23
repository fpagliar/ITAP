package menu.item.operations;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class DynamicRangeCompressionMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public DynamicRangeCompressionMenuItem(final Window window) {
		super("Dynamic range compression");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				window.getFocusedPanel().getImage().dynamicRangeCompression();
				window.repaint();
			}
		});
	}
}
