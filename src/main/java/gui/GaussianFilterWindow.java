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

public class GaussianFilterWindow extends JFrame implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private TextField scalar;
		private TextField scalar2;
		private Window window;

		public GaussianFilterWindow(Window window, String label, Double defaultValue, String label2, Double defaultValue2) {
			this.window = window;

			final JPanel panel = new JPanel();

			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			setBounds(1, 1, 250, 55);
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

			Label xLabel2 = new Label(label2);
			panel.add(xLabel2);
			scalar2 = new TextField(5);
			scalar2.setText(defaultValue2.toString());
			scalar2.addActionListener(this);
			panel.add(scalar2);
		}

		public void actionPerformed(ActionEvent arg0) {
			try {
				double ret = Double.parseDouble(scalar.getText());
				double ret2 = Double.parseDouble(scalar2.getText());
				new ApplyFilterWindow(window.getFocusedPanel().getImage(), (int)ret, ret2, 3);
			} catch (NumberFormatException e) {
				new ErrorWindow("Invalid value");
			}
			this.setVisible(false);
		}
	}
