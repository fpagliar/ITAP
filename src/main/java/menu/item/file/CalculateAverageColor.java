package menu.item.file;

import gui.PixelWindow;
import gui.Window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import utils.ImageSelectionCapturer;

public class CalculateAverageColor extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CalculateAverageColor(final Window window){
		super("Calculate average color");
		
		this.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ImageSelectionCapturer(window.getFocusedPanel().getImage());
				Color avg = ImageSelectionCapturer.getImage().calculateAverage(ImageSelectionCapturer.getLastSelection());
				new PixelWindow(0, 0, avg, window.getFocusedPanel());
			}
		});
	}
}
