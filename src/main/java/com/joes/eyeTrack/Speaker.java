package com.joes.eyeTrack;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class Speaker {
	private static Speaker speaker;
	
	private VoiceManager voiceManager;
	private Voice helloVoice;
	
	public static Speaker getDefault(){
		if(speaker == null){
			speaker = new Speaker();
		}
		return speaker;
	}
	
	public Speaker(){
		this("kevin16");
	}
	
	public Speaker(String voiceName){
		voiceManager = VoiceManager.getInstance();
		helloVoice = voiceManager.getVoice(voiceName);
		if (helloVoice == null) {
            System.err.println(
                "Cannot find a voice named "
                + voiceName + ".  Please specify a different voice.");
            System.exit(1);
        }
		helloVoice.allocate();
	    helloVoice.speak("Hello! I am Kevin.");
	}
	
	public void speak(String words){
		helloVoice.speak(words);
	}
	
    public void listAllVoices() {
        System.out.println();
        System.out.println("All voices available:");        
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();
        for (int i = 0; i < voices.length; i++) {
            System.out.println("    " + voices[i].getName()
                               + " (" + voices[i].getDomain() + " domain)");
        }
    }
}
