package com.joes.eyeTrack;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.media.Buffer;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

import com.joes.imageutils.ImageDescription;
import com.joes.imageutils.ImageDescriptionService;

public class FrameGrab implements Runnable {
	public Buffer buf = null;
	public BufferToImage btoi = null;
	public Image img = null;
	private boolean save_images = false; 
	public FrameGrab(boolean save_images){
		this.save_images = save_images;
	}
	@Override
	public void run() {
		
		
	}

}
