package com.joes.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.joes.categorizer.CategorizeResult;
import com.joes.categorizer.CategorizeableString;
import com.joes.categorizer.Categorizer;

public class TestCategorizer {

	@Test
	public void testLearn() {
		Categorizer myCategorizer = new Categorizer();
		CategorizeableString myCategorizeableStringA = new CategorizeableString("A A A A");
		CategorizeableString myCategorizeableStringB = new CategorizeableString("B B B B");
		CategorizeableString myCategorizeableStringC = new CategorizeableString("A A A A");
		myCategorizer.learn("A", myCategorizeableStringA);
		myCategorizer.learn("A", myCategorizeableStringA);
		myCategorizer.learn("B", myCategorizeableStringB);
		CategorizeResult myResults = myCategorizer.categorize(myCategorizeableStringC);
		System.out.println(myResults.getCategory());
		fail("Not yet implemented");
	}

	@Test
	public void testCategorize() {
		fail("Not yet implemented");
	}
}
