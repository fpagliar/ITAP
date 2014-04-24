package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InputDoubleWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField scalar;
	private Window window;
	private InputDoubleAction action;

	public InputDoubleWindow(Window window, String label, Double defaultValue, InputDoubleAction action) {
		this.window = window;
		this.action = action;

		final JPanel panel = new JPanel();

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(1, 1, 130, 55);
		this.setVisible(true);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((size.width - getWidth()) / 2,
				(size.height - getHeight()) / 2);
		add(panel);

		GridLayout layout = new GridLayout(1, 1);
		panel.setLayout(layout);

		Label xLabel = new Label(label);
		panel.add(xLabel);
		scalar = new TextField(5);
		scalar.setText(defaultValue.toString());
		scalar.addActionListener(this);
		panel.add(scalar);
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			double ret = Double.parseDouble(scalar.getText());
			action.performAction(window, ret);
		} catch (NumberFormatException e) {
			new ErrorWindow("Invalid value");
		}
		this.setVisible(false);
	}
}