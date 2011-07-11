package com.joes.imageutils;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageDescriptionService {
	public static ImageDescription getRGBDescription(Image origImage, int width, int height){
		Image anImage = origImage.getScaledInstance(width, height, 0);
		BufferedImage bufferedimage = new BufferedImage(40, 30, BufferedImage.TYPE_INT_RGB);
		bufferedimage.createGraphics().drawImage(anImage, 0, 0, null);
		
		ImageDescription retVal = new ImageDescription();
		String[] descriptor = new String[width * height];
		int[] rgbs = new int[width*height];
		bufferedimage.getRGB(0, 0, width, height, rgbs, 0, width);
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				descriptor[y*width + x] = "Pixel(" + x + "," + y + "): " + (rgbs[y*width + x] & 255);  
			}
		}
		retVal.setDescriptors(descriptor);
		return retVal;
	}
}
