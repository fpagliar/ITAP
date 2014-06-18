package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import model.Image;

import org.apache.sanselan.ImageReadException;

public class VideoManager {
	
	public static List<Image> getImages(final File folder) throws ImageReadException, IOException {
		HashMap<String, Image> images = new HashMap<String, Image>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory() && fileEntry.getName().endsWith(".bmp")) {
	        	images.put(fileEntry.getName(), ImageManager.loadImage(fileEntry));
	        }
	    }
	    List<String> keys = new ArrayList<String>(images.keySet());
	    Collections.sort(keys);
	    List<Image> ans = new ArrayList<Image>();
	    for(String key : keys){
//	    	System.out.println(key);
	    	ans.add(images.get(key));
	    }
	    return ans;
//		List<Image> images = new ArrayList<Image>();
//	    for (final File fileEntry : folder.listFiles()) {
//	        if (!fileEntry.isDirectory() && fileEntry.getName().endsWith(".bmp")) {
//	        	images.add(ImageManager.loadImage(fileEntry));
//	        }
//	    }
//	    return images;
	}

//    private static Dimension screenBounds;
//    public static int indexVideo = 1;
//    private static final double FRAME_RATE = 50;
//
//    private static final int SECONDS_TO_RUN_FOR = 20;
//    private static final String OUTPUT_FILE = "C:/video/today.mp4";
//
//    public static void main(String[] arguments) {
//        final IMediaWriter writer = ToolFactory.makeWriter(OUTPUT_FILE);
//        screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
//        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4,
//                screenBounds.width / 2, screenBounds.height / 2);
//        long startTime = System.nanoTime();
//
//        for (int index = 0; index < 15; index++) {
//
//            BufferedImage bgrScreen = getVideoImage();
//            System.out.println("time stamp = "+ (System.nanoTime() - startTime));
//            bgrScreen = convertToType(bgrScreen, BufferedImage.TYPE_3BYTE_BGR);
//            // encode the image to stream #0
//            //writer.encodeVideo(0, bgrScreen, (System.nanoTime() - startTime)/2,TimeUnit.NANOSECONDS);
//            // encode the image to stream #0
//            writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime,
//                    TimeUnit.NANOSECONDS);
//            // sleep for frame rate milliseconds
//            try {
//                Thread.sleep((long) (100));
//            } catch (InterruptedException e) {
//                // ignore
//            }
//        }
//        writer.close();
//    }
//
//    private static BufferedImage getVideoImage() {
//
//        File imgLoc = new File("C:/images/" + indexVideo + ".png");
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(imgLoc);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(imgLoc.getName());
//        indexVideo++;
//        return img;
//    }
//
//    public static BufferedImage convertToType(BufferedImage sourceImage,
//            int targetType) {
//
//        BufferedImage image;
//
//        // if the source image is already the target type, return the source
//        // image
//        if (sourceImage.getType() == targetType) {
//            image = sourceImage;
//        }
//        // otherwise create a new image of the target type and draw the new
//        // image
//        else {
//            image = new BufferedImage(sourceImage.getWidth(),
//                    sourceImage.getHeight(), targetType);
//            image.getGraphics().drawImage(sourceImage, 0, 0, null);
//        }
//
//        return image;
//
//    }

}
