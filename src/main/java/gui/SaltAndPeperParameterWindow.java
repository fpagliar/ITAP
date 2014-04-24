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

public class SaltAndPeperParameterWindow extends JFrame implements
		ActionListener {

	private static final long serialVersionUID = 1L;
	private TextField p0, p1;
	private Window window;

	public SaltAndPeperParameterWindow(Window window) {
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

		Label meanLabel = new Label("p0:");
		panel.add(meanLabel);
		p0 = new TextField(5);
		p0.setText("10");
		p0.addActionListener(this);
		panel.add(p0);

		Label deviationLabel = new Label("p1:");
		panel.add(deviationLabel);
		p1 = new TextField(5);
		p1.setText("250");
		p1.addActionListener(this);
		panel.add(p1);

	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			double p0Value = Double.parseDouble(p0.getText());
			double p1Value = Double.parseDouble(p1.getText());
			window.getUnfocusedPanel().setImage(
					window.getFocusedPanel().getImage().clone());
			window.getUnfocusedPanel().getImage()
					.saltAndPepperNoise(p0Value, p1Value);
			window.repaint();
		} catch (NumberFormatException e) {
			new ErrorWindow("Invalid value");
		}
		this.setVisible(false);
	}
}
