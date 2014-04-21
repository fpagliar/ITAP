package menu.item;

import gui.Panel;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

public class MouseCoordinatesLabel extends JLabel implements
		MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates the label to track the mouse.
	 * The panels are necesary to set the mouse listener over them
	 * @param panel1
	 * @param panel2
	 */
	public MouseCoordinatesLabel(Panel panel1, Panel panel2) {
		super("Mouse position X: 0 Y:0");

		setMaximumSize(new Dimension(200, 40));
		panel1.addMouseMotionListener(this);
		panel2.addMouseMotionListener(this);
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseMoved(MouseEvent e) {
		setText("Mouse position X:" + e.getX() + " Y:" + e.getY());
	}
}
