package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GenericDoubleInputWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, TextField> textFields;
	private Window window;
	private DoubleArrayInputAction action;

	public GenericDoubleInputWindow(Window window, String[] labels,
			Double[] defaultValues, DoubleArrayInputAction action) {
		this.window = window;
		this.action = action;
		this.textFields = new HashMap<String, TextField>();

		final JPanel panel = new JPanel();

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(1, 1, 130 + 10 * (int) Math.sqrt(labels.length),
				55 + 10 * (int) Math.sqrt(labels.length));
		this.setVisible(true);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((size.width - getWidth()) / 2,
				(size.height - getHeight()) / 2);
		add(panel);

		GridLayout layout = new GridLayout((int) Math.sqrt(labels.length),
				(int) Math.sqrt(labels.length));
		panel.setLayout(layout);

		for (int i = 0; i < labels.length; i++) {
			Label l = new Label(labels[i]);
			panel.add(l);
			TextField t = new TextField(5);
			t.setText(defaultValues[i].toString());
			t.addActionListener(this);
			panel.add(t);
			textFields.put(labels[i], t);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			HashMap<String, Double> values = new HashMap<String, Double>();
			for (String s : textFields.keySet()) {
				TextField t = textFields.get(s);
				values.put(s, Double.parseDouble(t.getText()));
			}
			 action.performAction(window, values);
		} catch (NumberFormatException e) {
			new ErrorWindow("Invalid value: " + e);
		}
		this.setVisible(false);
	}
}