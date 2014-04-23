package menu.item.file;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import utils.BinaryImageManager;

public class CreateSquareMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CreateSquareMenuItem(final Window window){
		super("New binay square");
		
		this.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				window.getFocusedPanel().setImage(BinaryImageManager.createSquare(200, 200));
				window.repaint();
			}
		});
	}
}