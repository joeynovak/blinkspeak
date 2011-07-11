package com.joes.eyeTrack;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class EyeTrackFromFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Frame f = new Frame("SwingCapture");

		String path_name = "/D:/Projects/OriginalMelindaFiles/Malinda/videos/";
	    final EyeTrack cf = new EyeTrack(); //"file://" + path_name + "left_eye3.avi");
	    
	    f.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	      cf.playerclose();
	      System.exit(0);}});
	    
	    f.add("Center",cf);
	    f.pack();
	    f.setSize(new Dimension(320,550));
	    f.setVisible(true);
	}

}
