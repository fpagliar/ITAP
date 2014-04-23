package menu.item.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Window;

import javax.swing.JMenuItem;

import utils.ImageSelectionCapturer;

public class CopyImageSelectionMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CopyImageSelectionMenuItem(final Window window){
		super("Copy selection from image");
		
		this.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ImageSelectionCapturer(window.getFocusedPanel().getImage());
			}
		});
	}
}
