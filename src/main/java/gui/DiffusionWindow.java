package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.BorderDetector;
import model.Image;

public class DiffusionWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DiffusionWindow(Image image, BorderDetector bd){
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		setBounds(1, 1, 1800, 1000);
		setResizable(true);
	
		//Original
		JPanel panel1 = new ImagePanel(image);
		image = image.clone();
		image.applyAnisotropicDiffusion(bd);
		//1st iteration
		JPanel panel2 = new ImagePanel(image);
		image = image.clone();
		image.applyAnisotropicDiffusion(bd);
		image.applyAnisotropicDiffusion(bd);
		image.applyAnisotropicDiffusion(bd);
		//4rd iteration
		JPanel panel3 = new ImagePanel(image);
		image = image.clone();
		image.applyAnisotropicDiffusion(bd);
		image.applyAnisotropicDiffusion(bd);
		image.applyAnisotropicDiffusion(bd);
		image.applyAnisotropicDiffusion(bd);
		image.applyAnisotropicDiffusion(bd);
		//10th iteration
		JPanel panel4 = new ImagePanel(image);

		panel1.setBackground(Color.GRAY);
		panel2.setBackground(Color.GRAY);
		panel3.setBackground(Color.GRAY);
		panel4.setBackground(Color.GRAY);

		panel1.setSize(900/4, 800/4);
		panel2.setSize(900/4, 800/4);
		panel3.setSize(900/4, 800/4);
		panel4.setSize(900/4, 800/4);
	
		GridLayout layout = new GridLayout(2, 2);
		setLayout(layout);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
	}
}
