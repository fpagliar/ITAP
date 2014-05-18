package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Image;
import utils.ImageManager;

public class RawImageLoaderWindow extends JFrame implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private TextField widthValue;
		private TextField heightValue;
		private Window window;

		public RawImageLoaderWindow(Window window) {
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

			Label widthLabel = new Label("width:");
			panel.add(widthLabel);
			widthValue = new TextField(5);
			widthValue.addActionListener(this);
			panel.add(widthValue);

			Label heightLabel = new Label("height");
			panel.add(heightLabel);
			heightValue = new TextField(5);
			heightValue.addActionListener(this);
			panel.add(heightValue);
		}

		public void actionPerformed(ActionEvent arg0) {
			try {
				double width = Double.parseDouble(widthValue.getText());
				double height = Double.parseDouble(heightValue.getText());
				
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				
				File file = chooser.getSelectedFile();
	
				Panel panel = window.getFocusedPanel();
				if (file != null) {
					Image image = null;
	
					try {
						image = ImageManager.loadRaw(file, (int)width, (int)height);
					} catch (Exception ex) {
						new ErrorWindow("Could not load the image: " + ex.getMessage());
	                    ex.printStackTrace();
					}
	
					//Image may be null if it failed to load or if the dialog was closed
					if (image != null) {
						// Loads the image to the panel
						panel.loadImage(image);
	
						// This will repaint the panel with the new image
						panel.repaint();
					}
				}
			} catch (NumberFormatException e) {
				new ErrorWindow("Invalid value");
			}
			this.setVisible(false);
		}
	}
