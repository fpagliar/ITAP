package menu.item.file;

import gui.ErrorWindow;
import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import model.Image;
import utils.ImageManager;

public class LoadFormattedMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public LoadFormattedMenuItem(final Window window)
	{
		super("Formatted Image");
		
		this.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
	
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				
				File file = chooser.getSelectedFile();
				Panel panel = window.getFocusedPanel();
				if (file != null) {
					Image image = null;
	
					try {
						image = ImageManager.loadImage(file);
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
			}
		});
	}
}
