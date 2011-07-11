package com.joes.categorizer;

import java.util.StringTokenizer;

public class CategorizeableString implements Categorizable {

	private StringTokenizer myTokenizer = null;
	
	public CategorizeableString(){
		setData("");
	}
	
	public CategorizeableString(String data){
		setData(data);
	}
	@Override
	public boolean hasMoreTokens() {
		return myTokenizer.hasMoreTokens();
	}

	@Override
	public String nextToken() {
		return myTokenizer.nextToken();
	}
	
	public void setData(String data){
		myTokenizer = new StringTokenizer(data);
	}
	
	public void setData(String[] data){
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<data.length;i++){
			sb.append(data[i]).append("\n");
		}
		myTokenizer = new StringTokenizer(sb.toString(), "\n");
	}

}
