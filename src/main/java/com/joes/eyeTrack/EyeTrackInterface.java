package com.joes.eyeTrack;

import com.joes.blinkspeak.SimpleInterface;

public class EyeTrackInterface implements SimpleInterface {
	EyeTrack eyeTrack = null;
	public EyeTrackInterface(){
		eyeTrack = new EyeTrack();
	}
	@Override
	public int getState() {
		if(eyeTrack.getLastState().equals("up")) return 1;
		return 0;
	}

	@Override
	public int getStateCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	public boolean isReady(){
		return eyeTrack.getMyState().equals(EyeTrack.States.CALIBRATED);
	}
}
