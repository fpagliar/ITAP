package menu.item.file;

import gui.ErrorWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import model.Image;
import utils.ImageManager;

public class SaveImageMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SaveImageMenuItem(final Window window)
	{
		super("Save image");
		
		this.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setApproveButtonText("Save");
				chooser.showOpenDialog(null);

				File file = chooser.getSelectedFile();

				if (file != null) {
					Image image = window.getFocusedPanel().getImage();
					try {
						ImageManager.saveImage(file, image);
					} catch (Exception ex) {
						new ErrorWindow("Error while saving the image");
						ex.printStackTrace();
					}
				}
			}
		});
	}
}