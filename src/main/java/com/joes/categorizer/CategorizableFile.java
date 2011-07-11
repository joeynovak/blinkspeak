package com.joes.categorizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CategorizableFile extends CategorizeableString{
	private String data = null;
	private StringTokenizer myTokenizer = null;
	
	public CategorizableFile(String fileName){
		//File aFile = new File(fileName);
		StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
	        char[] buf = new char[10240];
	        int numRead=0;
	        while((numRead=reader.read(buf)) != -1){
	            String readData = String.valueOf(buf, 0, numRead);
	            fileData.append(readData);
	            buf = new char[10240];
	        }
	        reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setData(fileData.toString());
	}
}
