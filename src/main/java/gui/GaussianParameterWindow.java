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

public class GaussianParameterWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private TextField mean, deviation;
	private Window window;

	public GaussianParameterWindow(Window window) {
		this.window = window;

		final JPanel panel = new JPanel();

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(1, 1, 250, 80);
		this.setVisible(true);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((size.width - getWidth()) / 2,
				(size.height - getHeight()) / 2);
		add(panel);

		GridLayout layout = new GridLayout(1, 1);
		panel.setLayout(layout);

		Label meanLabel = new Label("Mean:");
		panel.add(meanLabel);
		mean = new TextField(5);
		mean.setText("1");
		mean.addActionListener(this);
		panel.add(mean);

		Label deviationLabel = new Label("Deviation:");
		panel.add(deviationLabel);
		deviation = new TextField(5);
		deviation.setText("0.5");
		deviation.addActionListener(this);
		panel.add(deviation);
	
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			double meanValue = Double.parseDouble(mean.getText());
			double deviationValue = Double.parseDouble(deviation.getText());
			window.getUnfocusedPanel().setImage(window.getFocusedPanel().getImage().clone());
			window.getUnfocusedPanel().getImage().gausseanNoise(meanValue, deviationValue);
			window.repaint();
		} catch (NumberFormatException e) {
			new ErrorWindow("Invalid value");
		}
		this.setVisible(false);
	}
}