package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import menu.item.SelectPanelMenuItem;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	private Panel panel1 = new Panel(this);
	private Panel panel2 = new Panel(this);
	private int focused = panel1.getId();

	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(1, 1, 1800, 800);
		setResizable(false);
		center();

		panel1.setBackground(Color.GRAY);
		panel2.setBackground(Color.DARK_GRAY);
		panel1.setSize(900, 800);
		panel2.setSize(900, 800);

		setJMenuBar(new MainMenu(this, panel1, panel2));

		GridLayout layout = new GridLayout(1, 2);
		setLayout(layout);
		add(panel1);
		add(panel2);
	}

	public Panel getFocusedPanel() {
		if(focused == panel1.getId())
			return panel1;
		else
			return panel2;
	}

	// public void resize()
	// {
	// this.setSize(panel1.getImage().getWidth(),
	// panel1.getImage().getHeight());
	// }

	public void focus(Panel panel) {
		panel1.getMenuItem().setEnabled(true);
		panel2.getMenuItem().setEnabled(true);

		//Both panel selection is activated and then the selected panel selection is deactivated.
		focused = panel.getId();
		panel.getMenuItem().setEnabled(false);
	}

	public void center() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((size.width - getWidth()) / 2,
				(size.height - getHeight()) / 2);
	}

	public void repaint() {
		panel1.repaint();
		panel2.repaint();
	}
}
