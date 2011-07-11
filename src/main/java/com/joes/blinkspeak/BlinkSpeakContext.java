package com.joes.blinkspeak;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.joes.blinkspeak.BlinkSpeakException.Type;
import com.joes.eyeTrack.Speaker;

public class BlinkSpeakContext {
	enum MapType{
		original, optimized
	}
	
	private BlinkSpeak blinkSpeak;
	private SimpleInterface inputDevice;
	private Speaker speaker;
	private MapType mapType;
	private double StandardPause = .5;
	public BlinkSpeakContext(BlinkSpeak blinkSpeak, SimpleInterface inputDevice, Speaker speaker){
		this.blinkSpeak = blinkSpeak;
		this.inputDevice = inputDevice;
		this.speaker = speaker;
	}
	
	public String getInputFromUser(BlinkSpeakMap myMenu) throws BlinkSpeakException{
		//We try this three times before we bail...
		for(int i=0;i<3;i++){
			for(Entry<String, LinkedHashMap<String, String>> menu : myMenu.getMyMap().entrySet()){
				LinkedHashMap<String, String> currentMap = menu.getValue();
				speaker.speak(menu.getKey());
				if(waitForYes(StandardPause)){
					//Start sub menu...
					speaker.speak(menu.getKey());
					if(currentMap.size() == 0) return menu.getKey();
					for(Entry<String, String> option : currentMap.entrySet()){
						speaker.speak(option.getKey());
						if(waitForYes(StandardPause)){
							return option.getValue();
						}
					}
				}	
			}		
		}
		return "";
	}
	
	public boolean waitForYes(double secondsToWait){
		long waitTime = (long) (secondsToWait * 1000);
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() < waitTime + startTime){
			if(inputDevice.getState() == 1) return true;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	public double getStandardPause() {
		return StandardPause;
	}

	public void setStandardPause(double StandardPause) {
		this.StandardPause = StandardPause;
	}

	public MapType getMapType() {
		return mapType;
	}

	public void setMapType(MapType mapType) {
		this.mapType = mapType;
	}

	public BlinkSpeak getBlinkSpeak() {
		return blinkSpeak;
	}
	
	public void setBlinkSpeak(BlinkSpeak blinkSpeak) {
		this.blinkSpeak = blinkSpeak;
	}
	
	public SimpleInterface getInputDevice() {
		return inputDevice;
	}
	
	public void setInputDevice(SimpleInterface inputDevice) {
		this.inputDevice = inputDevice;
	}
	
	public Speaker getSpeaker() {
		return speaker;
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public void pause() {
		try {
			Thread.sleep((long) (StandardPause * 1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
