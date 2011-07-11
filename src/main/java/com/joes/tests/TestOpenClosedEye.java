package com.joes.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.joes.categorizer.Categorizer;


public class TestOpenClosedEye {
	Categorizer myCategorizer = new Categorizer();
	String base_path = "D:\\Projects\\OriginalMelindaFiles\\Malinda\\images\\eyes";
	@Before
	public void setUp() throws Exception {
		TestUtils.learnFromImagesInDirectory(myCategorizer, "up", base_path + "\\up");
		TestUtils.learnFromImagesInDirectory(myCategorizer, "down", base_path + "\\down");
	}

	@Test
	public void testImage(){
		TestUtils.printImageCategoriesInDirectory(myCategorizer, base_path + "\\unknown");
	}
	@After
	public void tearDown() throws Exception {
	}
}
