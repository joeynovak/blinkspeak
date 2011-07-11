package com.joes.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.joes.categorizer.Categorizer;
import com.joes.imageutils.ImageDescriptionService;

public class TestUtils {
	public static void learnFromImage(Categorizer myCategorizer, String category, String fileName){
		System.out.println("Learning form file: " + fileName);
		try {
			BufferedImage anImage = ImageIO.read(new File(fileName));
			myCategorizer.learn(category, ImageDescriptionService.getRGBDescription(anImage, 40, 30).getCategorizable());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getImageCategory(Categorizer myCategorizer, String fileName){
		System.out.println("Getting Category of: " + fileName);
		try {
			BufferedImage anImage = ImageIO.read(new File(fileName));
			return myCategorizer.categorize(ImageDescriptionService.getRGBDescription(anImage, 40, 30).getCategorizable()).getCategory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	public static void learnFromImagesInDirectory(Categorizer myCategorizer, String category, String dirName){
		File aDir = new File(dirName);
		String files[] = aDir.list();
		for(int i=0;i<files.length;i++){
			File aFile = new File(dirName + "\\" + files[i]);
			if(aFile.isFile() && aFile.getName().toLowerCase().endsWith("jpg")){
				learnFromImage(myCategorizer, category, aFile.getAbsolutePath());
			}
		}
	}
	
	public static void printImageCategoriesInDirectory(Categorizer myCategorizer, String dirName){
		File aDir = new File(dirName);
		String files[] = aDir.list();
		for(int i=0;i<files.length;i++){
			File aFile = new File(dirName + "\\" + files[i]);
			if(aFile.isFile() && aFile.getName().toLowerCase().endsWith("jpg")){
				System.out.println(getImageCategory(myCategorizer, aFile.getAbsolutePath()));
			}
		}	
	}
}
