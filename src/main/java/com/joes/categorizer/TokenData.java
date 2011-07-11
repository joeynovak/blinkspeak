package com.joes.categorizer;

import java.util.HashMap;
import java.util.Map.Entry;

public class TokenData {
	private HashMap<String, Integer> tokenCounts = new HashMap<String, Integer>();
	private HashMap<String, Double> numbers = null;
	public void incrementCategory(String category) {
		numbers = null;
		Integer count = tokenCounts.get(category);
		if(count == null){
			count = 0;
		}
		count++;
		tokenCounts.put(category, count);
	}
	public HashMap<String, Double> getOdds() {
		
		
		if(numbers == null){
			numbers = new HashMap<String, Double>();
			HashMap<String, Double> myOdds = new HashMap<String, Double>();
			for(Entry<String, Integer> aCategory : tokenCounts.entrySet()){
				Integer thisCount = aCategory.getValue();
				Integer otherCount = 1;
				//This category divided by the sum of all others...
				for(Entry<String, Integer> otherCategory : tokenCounts.entrySet()){
					if(!otherCategory.getKey().equals(aCategory.getKey())){
						otherCount += otherCategory.getValue();
					}
				}
				myOdds.put(aCategory.getKey(), thisCount / new Double(otherCount));
			}
			
		
			for(Entry<String, Double> aCategory : myOdds.entrySet()){
				Double thisOdds = aCategory.getValue();
				Double otherOdds = new Double(0);
				//This category divided by the sum of all others...
				for(Entry<String, Double> otherCategory : myOdds.entrySet()){
					if(!otherCategory.getKey().equals(aCategory.getKey())){
						otherOdds += otherCategory.getValue();
					}
				}
				if(otherOdds <=0) otherOdds = .1;
				numbers.put(aCategory.getKey(), thisOdds / otherOdds);
			}
		}
		return numbers;
	}
}
