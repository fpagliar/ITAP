package menu.item;

import gui.Window;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.ImageSelectionCapturer;
import utils.ImageSelectionPaster;

public class PasteImageSelectionMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PasteImageSelectionMenuItem(final Window window){
		super("Paste selection into image");
		
		this.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Rectangle selection = ImageSelectionCapturer.getLastSelection();
				Image original = ImageSelectionCapturer.getImage();
				Image fraction = original.getImagePart(selection);
				new ImageSelectionPaster(window.getFocusedPanel().getImage().getImage(), selection, fraction.getImage());
				if(ImageSelectionPaster.isConfirmed()){					
					original.pasteImagePart(fraction.getImage(), ImageSelectionPaster.getLastSelection());
					window.getFocusedPanel().setImage(original);
					window.repaint();
				}
			}
		});
	}
}
