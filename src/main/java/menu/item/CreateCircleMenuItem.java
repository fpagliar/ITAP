package menu.item;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import utils.BinaryImageManager;

public class CreateCircleMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CreateCircleMenuItem(final Window window){
		super("New binay circle");
		
		this.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				window.getFocusedPanel().setImage(BinaryImageManager.createCircle(200, 200));
				window.repaint();
			}
		});
	}
}