package com.joes.tests;

import org.junit.Test;

import com.joes.eyeTrack.Speaker;


public class TestSpeaker {
	@Test
	public void testSpeak(){
    	Speaker.getDefault().speak("Everything works GREAT!");
	}
}
