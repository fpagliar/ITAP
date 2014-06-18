package menu.item.analysis;

import gui.Window;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import model.Image;
import model.Tracker;

import org.apache.sanselan.ImageReadException;

import utils.ImageSelectionCapturer;
import utils.VideoManager;

public class VideoTrackingMenuItem extends JMenuItem {

		private static final long serialVersionUID = 1L;

		public VideoTrackingMenuItem(final Window window) {
			super("Video tracking");

			setEnabled(true);

			this.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
//					JFileChooser chooser = new JFileChooser();
//					chooser.showOpenDialog(null);
					
//					Runtime run = Runtime.getRuntime() ;
//					String path = "/home/sheetah/ati_videos/" + chooser.getSelectedFile().getName();
					try {
//						Process pr = run.exec("mkdir " + path);
//						pr.waitFor();
//						pr = run.exec("/home/sheetah/Downloads/ffmpeg-git-20140609-64bit-static/ffmpeg -i " + chooser.getSelectedFile().getPath() + " -r 20 -f image2 " + path + "/image-%3d.jpeg");
//						pr.waitFor();
//						pr = run.exec("mogrify -format bmp " + path + "/*");
//						pr.waitFor();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.showOpenDialog(null);
//					File folder = new File(path);

					List<Image> images;
					try {
						images = VideoManager.getImages(chooser.getSelectedFile());
//						images = VideoManager.getImages(folder);
					} catch (ImageReadException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						return;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						return;
					}

					Image first = images.get(0);
					new ImageSelectionCapturer(first);
					List<Point> selection = ImageSelectionCapturer.getPoints();
					Tracker tracker = new Tracker(selection, first.getHeight(),
							first.getWidth());

					int times = (int) (1.5 * Math.max(first.getHeight(),
							first.getWidth()));
					boolean changes = true;
					for(Image actual : images){
						for (int i = 0; i < times && changes; i++) {
							changes = actual.tracking(tracker);
//							if(i%10 == 9) {
//								Image shown = actual.clone();
//								tracker.markImage(shown);
//								window.getUnfocusedPanel().setImage(shown);
//								window.getUnfocusedPanel().paint(window.getUnfocusedPanel().getGraphics());
//							}
						}
						Image shown = actual.clone();
						tracker.markImage(shown);
						window.getUnfocusedPanel().setImage(shown);
						window.getUnfocusedPanel().paint(window.getUnfocusedPanel().getGraphics());
//						if(!changes)
//							System.out.println("No changes in total:" + times);
						changes = true;
						tracker = new Tracker(tracker.getSuperInner(), actual.getHeight(), actual.getWidth());
//						tracker = new Tracker(tracker.getInner(), actual.getHeight(), actual.getWidth());
					}
					try {
//						Process pr = run.exec("rm -rf " + path);
//						pr.waitFor();					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
	}
