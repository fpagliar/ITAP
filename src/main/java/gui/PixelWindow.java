package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Image;

public class PixelWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField xField, yField, rField, gField, bField;
	private Panel imagePanel;

	public PixelWindow(int x, int y, Color value, Panel imagePanel) {
		this.imagePanel = imagePanel;

		final JPanel panel = new JPanel();
		panel.setBackground(value);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(1, 1, 100, 300);
		this.setVisible(true);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((size.width - getWidth()) / 2,
				(size.height - getHeight()) / 2);
		add(panel);

		GridLayout layout = new GridLayout(5, 1);
		panel.setLayout(layout);

		Label xLabel = new Label("X: ");
		panel.add(xLabel);
		xField = new TextField(3);
		xField.setText("" + x);
		xField.addActionListener(this);
		panel.add(xField);

		Label yLabel = new Label("Y: ");
		panel.add(yLabel);
		yField = new TextField(3);
		yField.setText("" + y);
		yField.addActionListener(this);
		panel.add(yField);

		Label rLabel = new Label("R: ");
		panel.add(rLabel);
		rField = new TextField(3);
		rField.setText("" + value.getRed());
		rField.addActionListener(this);
		panel.add(rField);

		Label gLabel = new Label("G: ");
		panel.add(gLabel);
		gField = new TextField(3);
		gField.setText("" + value.getGreen());
		gField.addActionListener(this);
		panel.add(gField);

		Label bLabel = new Label("B: ");
		panel.add(bLabel);
		bField = new TextField(3);
		bField.setText("" + value.getBlue());
		bField.addActionListener(this);
		panel.add(bField);

	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			imagePanel.setPixel(getValue("X", xField), getValue("Y", yField),
					getColor());
		} catch (Exception e) {
			new ErrorWindow(e.getMessage());
		}
	}

	private int getValue(String name, TextField field) throws Exception {
		int ret;
		try {
			ret = Integer.parseInt(field.getText());
			return ret;
		} catch (Exception e) {
			throw new Exception("Invalid value: " + name);
		}
	}

	private int getRGB(String name, TextField field) throws Exception {
		int value = getValue(name, field);
		if (value > 256)
			throw new Exception("Invalid value: " + name);
		return value;
	}

	private Color getColor() throws Exception {
		return new Color(getRGB("R", rField), getRGB("G", gField), getRGB("B",
				bField));
	}
}