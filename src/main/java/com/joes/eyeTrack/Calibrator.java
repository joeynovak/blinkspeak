package com.joes.eyeTrack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Calibrator implements Runnable {
	EyeTrack myTrack = null;
	public Calibrator(EyeTrack myTrack){
		this.myTrack = myTrack;
		myTrack.setMyState(EyeTrack.States.CALIBRATINGPAUSE);
	}
	@Override
	public void run() {
		try{
			Speaker.getDefault().speak("Calibrating");
		//Calibrate Up
			System.out.println("Calibrate Up");//Say "Up"
			Speaker.getDefault().speak("Up");
			Thread.sleep(500);
			myTrack.setMyState(EyeTrack.States.CALIBRATINGUP);
			Thread.sleep(2000);
			myTrack.setMyState(EyeTrack.States.CALIBRATINGPAUSE);
		//Calibrate Down
			System.out.println("Calibrate Down");//Say "Up"
			Speaker.getDefault().speak("Down");
			Thread.sleep(500);
			myTrack.setMyState(EyeTrack.States.CALIBRATINGDOWN);
			Thread.sleep(2000);
		//Calibrated
			myTrack.setMyState(EyeTrack.States.CALIBRATED);
			System.out.println("Calibration Complete");//Say "Up"
			Speaker.getDefault().speak("Calibration Complete");
		}
		catch(Exception e){
			
		}
		
	}

	public void startThread(){
		myTrack.setMyState(EyeTrack.States.CALIBRATINGPAUSE);
		new Thread(this).start();
	}
	
	public static void playSound(String fileName){
		InputStream in;
		AudioStream as = null;
		try {
			in = new FileInputStream(fileName);
			as = new AudioStream(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AudioPlayer.player.start(as);            
		// Similarly, to stop the audio.
		//AudioPlayer.player.stop(as); 

	}
}
