package com.joes.blinkspeak;
import java.util.ArrayList;

import com.joes.blinkspeak.programs.MainMenu;
import com.joes.blinkspeak.programs.SaySomething;
import com.joes.eyeTrack.EyeTrack;
import com.joes.eyeTrack.EyeTrackInterface;
import com.joes.eyeTrack.Speaker;


public class BlinkSpeak implements BlinkSpeakProgram{

	public static void main(String[] args) throws BlinkSpeakException{
		BlinkSpeak mySpeaker = new BlinkSpeak();
		if(args.length >= 1){
			try {
				Class theClass  = Class.forName(args[0]);
				mySpeaker.setMyInterface((SimpleInterface)theClass.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		else{
			mySpeaker.setMyInterface(new EyeTrackInterface());
		}		
		BlinkSpeakContext myContext = new BlinkSpeakContext(mySpeaker, mySpeaker.getMyInterface(), new Speaker());
		myContext.setMapType(BlinkSpeakContext.MapType.original);
		while(!mySpeaker.getMyInterface().isReady()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new MainMenu().go(myContext);
		//mySpeaker.go(myContext);
	}
	/**
	 * @param args
	 */
	SimpleInterface myInterface;
	
	public BlinkSpeak(){
		
	}

	public void go(BlinkSpeakContext myContext) throws BlinkSpeakException{
		String out = null;
		while(out == null){
			out = myContext.getInputFromUser(BlinkSpeakMap.getAlphabet(myContext));
		}
		myContext.getSpeaker().speak(out);
		
	}
	
	public SimpleInterface getMyInterface() {
		return myInterface;
	}

	public void setMyInterface(SimpleInterface myInterface) {
		this.myInterface = myInterface;
	}
}
