package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Image;
import mpi.cbg.fly.Feature;

public class SiftWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Map<String, List<Feature>> hits;
	private final int offset;
	private final int MENU_BAR_HEIGHT = 28;
	
	public SiftWindow(Image image1, Image image2, Map<String, List<Feature>> hits){
		this.setVisible(true);
		this.hits = hits;
		offset = image1.getWidth();
		setBounds(1, 1, image1.getWidth() + image2.getWidth(), MENU_BAR_HEIGHT + Math.max(image1.getHeight(), image2.getHeight()));
		setResizable(true);
	
		JPanel panel1 = new ImagePanel(image1);
		JPanel panel2 = new ImagePanel(image2);
	
		GridLayout layout = new GridLayout(1, 2);
		setLayout(layout);
		add(panel1);
		add(panel2);
	}
	
    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.RED);
//        Line2D lin = new Line2D.Float(100, 100, 250, 260);
    	Line2D lin2 = new Line2D.Float(100, 0, 200, 0);
    	g2.draw(lin2);
        for(int i = 0 ; i < hits.get("f1").size(); i++){
        	int x1 = (int) hits.get("f1").get(i).location[0];
        	int y1 = MENU_BAR_HEIGHT + (int) hits.get("f1").get(i).location[1];
        	int x2 = offset + (int) hits.get("f2").get(i).location[0];
        	int y2 = MENU_BAR_HEIGHT + (int) hits.get("f2").get(i).location[1];
        	Line2D lin = new Line2D.Float(x1, y1, x2, y2);
        	g2.draw(lin);
        }
    }
}