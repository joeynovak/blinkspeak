package com.joes.categorizer;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class CategorizeResult {
	private HashMap<String, Double> rawCategoryOdds = null;
	private HashMap<String, Double> categoryOdds = new HashMap<String, Double>();
	
	public CategorizeResult(HashMap<String, Double> rawCategoryOdds){
		this.rawCategoryOdds = rawCategoryOdds;
	}
	
	public void addToOdds(String category, Double odds){
		Double myOdds = categoryOdds.get(category);
		if(myOdds == null) myOdds = 1.0;
		myOdds = myOdds * odds;
		categoryOdds.put(category, myOdds);
	}
	
	public void addToOdds(Set<Entry<String, Double>> odds){
		for(Entry<String, Double> myOdds2 : odds){
			addToOdds(myOdds2.getKey(), myOdds2.getValue() / rawCategoryOdds.get(myOdds2.getKey()));
		}
	}

	public String getCategory() {
		Double max = new Double(0);
		String category = "";
		for(Entry<String, Double> aCategory : categoryOdds.entrySet()){
			if(aCategory.getValue() > max){
				max = aCategory.getValue();
				category = aCategory.getKey();
			}
		}
		return category;
	}
	
}
