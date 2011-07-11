package com.joes.categorizer;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class Categorizer {
	public HashMap<String, Integer> learnedCounts = new HashMap<String, Integer>();
	
	public HashMap<String, TokenData> myData = new HashMap<String, TokenData>();
	public HashMap<String, Double> randomCategoryOdds = null;
	public void learn(String category, Categorizable something){
		//Reset categoryOdds in case they've been computed already...
		randomCategoryOdds = null;
		//Update the category count...
		Integer categoryThingCount = learnedCounts.get(category);
		if(categoryThingCount == null){
			categoryThingCount = 1;
		}
		else{
			categoryThingCount++;
		}
		learnedCounts.put(category, categoryThingCount);
		
		//Count each token...
		String nextToken = "";
		while(something.hasMoreTokens()){
			nextToken = something.nextToken();
			TokenData thisTokensData = myData.get(nextToken);
			if(thisTokensData == null){
				thisTokensData = new TokenData();
			}
			thisTokensData.incrementCategory(category);
			myData.put(nextToken, thisTokensData);
		}
	}
	
	public CategorizeResult categorize(Categorizable something){
		CategorizeResult myResult = new CategorizeResult(getRawOddsHashMap());
		String nextToken = "";
		while(something.hasMoreTokens()){
			nextToken = something.nextToken();
			TokenData thisTokensData = myData.get(nextToken);
			if(thisTokensData == null){
				thisTokensData = new TokenData();
			}
			HashMap<String, Double> myOdds = thisTokensData.getOdds();
			myResult.addToOdds(myOdds.entrySet());
		}
		//for each token...
			//Get the counts for each category vs all others for this token...
		return myResult;
	}

	private HashMap<String, Double> getRawOddsHashMap() {
		if(randomCategoryOdds == null){
			randomCategoryOdds = new HashMap<String, Double>();
			for(Entry<String, Integer> aCategory : learnedCounts.entrySet()){
				Integer thisCount = aCategory.getValue();
				Integer otherCount = 1;
				//This category divided by the sum of all others...
				for(Entry<String, Integer> otherCategory : learnedCounts.entrySet()){
					if(!otherCategory.getKey().equals(aCategory.getKey())){
						otherCount += otherCategory.getValue();
					}
				}
				randomCategoryOdds.put(aCategory.getKey(), thisCount / new Double(otherCount));
			}
		}
		return randomCategoryOdds;
	}
}
