package com.joes.blinkspeak.programs;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import com.joes.blinkspeak.BlinkSpeakContext;
import com.joes.blinkspeak.BlinkSpeakException;
import com.joes.blinkspeak.BlinkSpeakMap;
import com.joes.blinkspeak.BlinkSpeakProgram;

public class MainMenu implements BlinkSpeakProgram {

	@Override
	public void go(BlinkSpeakContext myContext) throws BlinkSpeakException {
		BlinkSpeakMap myMenu = new BlinkSpeakMap();
		myMenu.addEmptyMenu("say something");
		myMenu.addEmptyMenu("email");
		//myMenu.addEmptyMenu("facebook")
		//myMenu.addEmptyMenu("wikipedia");
		//myMenu.addEmptyMenu("google");
		while(true){
			String out = myContext.getInputFromUser(myMenu);
			if(out.equals("say something")){
				new SaySomething().go(myContext);
			}
			else if(out.equals("email")){
				new Email().go(myContext);
			}
		}
	}

}
