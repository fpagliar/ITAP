package menu.item.file;

import gui.Panel;
import gui.Window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class SelectPanelMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public SelectPanelMenuItem(final Window window, final Panel panel) {
		super("Select panel " + panel.getId());

		setMaximumSize(new Dimension(130, 40));

		panel.setMenuItem(this);
		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				window.focus(panel);
			}
		});
	}
}
