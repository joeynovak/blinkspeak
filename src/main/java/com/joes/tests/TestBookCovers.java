package com.joes.tests;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.joes.categorizer.Categorizer;
import com.joes.imageutils.ImageDescriptionService;

public class TestBookCovers {
	Categorizer myCategorizer = new Categorizer();
	String base_path = "D:\\Projects\\OriginalMelindaFiles\\Malinda\\images\\books";
	@Before
	public void setUp() throws Exception {
		TestUtils.learnFromImagesInDirectory(myCategorizer, "booka", base_path + "\\booka");
		TestUtils.learnFromImagesInDirectory(myCategorizer, "bookb", base_path + "\\bookb");
	}

	@Test
	public void testImage(){
		TestUtils.printImageCategoriesInDirectory(myCategorizer, base_path + "\\unknown");
	}
	@After
	public void tearDown() throws Exception {
	}
}
