package menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Window;

import javax.swing.JMenuItem;

import utils.ImageSelectionCapturer;

public class CopyImageSelection extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CopyImageSelection(final Window window){
		super("Copy selection from image");
		
		this.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ImageSelectionCapturer(window.getFocusedPanel().getImage().getImage());
			}
		});
	}
}
