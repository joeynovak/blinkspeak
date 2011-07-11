package com.joes.blinkspeak.programs;

import java.util.ArrayList;

import com.joes.blinkspeak.BlinkSpeakContext;
import com.joes.blinkspeak.BlinkSpeakException;
import com.joes.blinkspeak.BlinkSpeakMap;
import com.joes.blinkspeak.BlinkSpeakProgram;
import com.joes.blinkspeak.MenuService;

public class SaySomething implements BlinkSpeakProgram {

	@Override
	public void go(BlinkSpeakContext myContext) throws BlinkSpeakException {
		myContext.getSpeaker().speak("Say Something");
		BlinkSpeakMap myMenu = BlinkSpeakMap.getAlphabet(myContext);
		myMenu.addOption("6", "space", " ");
		myMenu.addOption("6", "speak");
		myMenu.addOption("6", "cancel");
		boolean done = false;
		StringBuilder sb = new StringBuilder();
		while(!done){
			myContext.pause();
			String out = myContext.getInputFromUser(myMenu);
			if(out.equals("speak")){
				myContext.getSpeaker().speak(sb.toString());
				sb = new StringBuilder();
			}
			else if(out.equals("cancel")){
				return;
			}
			else{
				sb.append(out);
			}
		}
	}

}
