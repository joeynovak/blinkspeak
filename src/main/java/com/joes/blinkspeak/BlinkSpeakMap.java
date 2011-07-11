package com.joes.blinkspeak;

import java.util.LinkedHashMap;

public class BlinkSpeakMap {
	private LinkedHashMap<String, LinkedHashMap<String, String>> myMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
	
	public BlinkSpeakMap(){
		
	}
	
	public LinkedHashMap<String, LinkedHashMap<String, String>> getMyMap() {
		return myMap;
	}

	public void setMyMap(LinkedHashMap<String, LinkedHashMap<String, String>> myMap) {
		this.myMap = myMap;
	}

	public static BlinkSpeakMap getAlphabet(BlinkSpeakContext myContext){
		BlinkSpeakMap newMap = new BlinkSpeakMap();
		newMap.setMyMap(getAlphabetHashMap(myContext));
		return newMap;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, String>> getAlphabetHashMap(BlinkSpeakContext myContext){
		LinkedHashMap<String, LinkedHashMap<String, String>> menu = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		switch(myContext.getMapType()){
			case original:
				menu.put("1", getRangeList('a', 'e'));
				menu.put("2", getRangeList('f', 'j'));
				menu.put("3", getRangeList('k', 'o'));
				menu.put("4", getRangeList('p', 't'));
				menu.put("5", getRangeList('u', 'z'));
				break;
			case optimized:
				menu.put("first", getItemizedList('e', 't'));
				menu.put("second", getItemizedList('a', 'i', 'n', 'o', 's'));
				menu.put("third", getItemizedList('h', 'r', 'd', 'l', 'u'));
				menu.put("fourth", getItemizedList('c', 'm', 'f', 'w', 'y'));
				menu.put("fifth", getItemizedList('g', 'p', 'b', 'v', 'k', 'q', 'j', 'x', 'z'));
				break;
		}

		return menu;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, String>> getAlphabetOptomized(){
		LinkedHashMap<String, LinkedHashMap<String, String>> menu = new LinkedHashMap<String, LinkedHashMap<String, String>>();	

		return menu;
	}
	
	public static LinkedHashMap<String, String> getItemizedList(char...letters){
		LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();
		for(char letter: letters){
			list.put(Character.toString(letter), Character.toString(letter));
		}
		return list;
	}
	
	public static LinkedHashMap<String, String> getRangeList(char start, char end){
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		char current = start;
		while(current <= end){
			out.put(Character.toString(current), Character.toString(current));
			current++;
		}
		return out;
	}

	public void addOption(String menu, String title, String returnValue) {
		LinkedHashMap<String, String> menuToModify;
		menuToModify = myMap.get(menu);
		boolean addWhenFinished = false;
		if(menuToModify == null){
			if(menu == "1") menuToModify = myMap.get("first"); 
			if(menu == "2") menuToModify = myMap.get("second");
			if(menu == "3") menuToModify = myMap.get("third");
			if(menu == "4") menuToModify = myMap.get("fourth");
			if(menu == "5") menuToModify = myMap.get("fifth");
			if(menu == "6") menuToModify = myMap.get("sixth");
		}
		if(menuToModify == null){
			addWhenFinished = true;
			menuToModify = new LinkedHashMap<String, String>();
		}
		menuToModify.put(title, returnValue);
		if(addWhenFinished) myMap.put(menu, menuToModify);
	}

	public void addOption(String menu, String option) {
		addOption(menu, option, option);
	}
	
	public void addEmptyMenu(String menu){
		myMap.put(menu, new LinkedHashMap<String, String>());
	}
}
