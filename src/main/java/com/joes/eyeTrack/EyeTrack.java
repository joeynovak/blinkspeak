package com.joes.eyeTrack;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.media.*;
import javax.media.format.*;
import javax.media.util.*;
import javax.media.control.*;
import javax.media.protocol.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import com.joes.categorizer.Categorizer;
import com.joes.imageutils.ImageDescription;
import com.joes.imageutils.ImageDescriptionService;
import com.sun.image.codec.jpeg.*;

public class EyeTrack extends Panel implements ActionListener, Runnable{
	public Player player = null;
	//public EyeTrack main = null;
	public CaptureDeviceInfo di = null;
	public MediaLocator ml = null;
	public JButton capture = null;
	public Buffer buf = null;
	public Image img = null;
	public VideoFormat vf = null;
	public BufferToImage btoi = null;
	public ImagePanel imgpanel = null;

	public boolean runLevel1 = false;
	public boolean frameGrab = false;
	public enum States {CALIBRATED, NOTCALIBRATED, CALIBRATINGUP, CALIBRATINGPAUSE, CALIBRATINGDOWN};
	private States myState = States.NOTCALIBRATED;
	private Categorizer myCategorizer = new Categorizer();
	private Thread myThread = null;
	private String lastState = "";
	
	public EyeTrack(){
		this("vfw://0", false, false);
	}

	public EyeTrack(String source, boolean runLevel3, boolean frameGrab){
		this.frameGrab = frameGrab;
		this.runLevel1 = runLevel3;
		
		//Create Window
		
		
		//Do other stuff...
		//main = this;
		setLayout(new BorderLayout());
		setSize(320,550);

		imgpanel = new ImagePanel();
		capture = new JButton("Capture");
		capture.addActionListener(this);   

		
		ml = new MediaLocator(source);

		try 
		{
			player = Manager.createRealizedPlayer(ml);
			player.start();
			Component comp;

			if ((comp = player.getVisualComponent()) != null)
			{
				add(comp,BorderLayout.NORTH);
			}
			add(capture,BorderLayout.CENTER);
			add(imgpanel,BorderLayout.SOUTH);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		//Start the thread
		myThread = new Thread(this);
		myThread.start();
		
		Frame f = new Frame("SwingCapture");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				playerclose();
				System.exit(0);}});

		f.add("Center",this);
		f.pack();
		f.setSize(new Dimension(320,550));
		f.setVisible(true);
	}



	public static void main(String[] args){
		new EyeTrack();
	}


	public void playerclose(){
		player.close();
		player.deallocate();
	}


	public void actionPerformed(ActionEvent e){
		JComponent c = (JComponent) e.getSource();

		if (c == capture){
			// Grab a frame
			FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
			buf = fgc.grabFrame();

			// Convert it to an image
			btoi = new BufferToImage((VideoFormat)buf.getFormat());
			img = btoi.createImage(buf);

			// show the image
			imgpanel.setImage(img);

			// save image
			saveJPG(img,"c:\\test.jpg");
		}
	}

	class ImagePanel extends Panel{
		public Image myimg = null;

		public ImagePanel(){
			setLayout(null);
			setSize(320,240);
		}

		public void setImage(Image img){
			this.myimg = img;
			repaint();
		}

		public void paint(Graphics g){
			if (myimg != null){
				g.drawImage(myimg, 0, 0, this);
			}
		}
	}


	public static void saveJPG(Image img, String s){
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(img, null, null);

		FileOutputStream out = null;
		try{ 
			out = new FileOutputStream(s); 
		}
		catch (java.io.FileNotFoundException io){ 
			System.out.println("File Not Found"); 
		}

		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
		param.setQuality(0.5f,false);
		encoder.setJPEGEncodeParam(param);

		try{ 
			encoder.encode(bi); 
			out.close(); 
		}
		catch (java.io.IOException io){
			System.out.println("IOException"); 
		}
	}

	public void run(){
		FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buf = fgc.grabFrame();
			btoi = new BufferToImage((VideoFormat)buf.getFormat());
			img = btoi.createImage(buf);
			if(img == null){
				continue;
			}
			imgpanel.setImage(img);
			if(this.frameGrab){
				String newFileName = new Long(java.lang.System.currentTimeMillis()).toString();
				EyeTrack.saveJPG(img, "G:\\Joes Stuff\\Test Images\\" + newFileName + ".jpg");
			}
			switch(myState){
				case NOTCALIBRATED:
					System.out.println("Starting Calibrator");
					myCategorizer = new Categorizer();
					new Calibrator(this).startThread();
					break;
				case CALIBRATINGPAUSE:
					//Do nothing
					break;
				case CALIBRATINGUP:
					myCategorizer.learn("up", ImageDescriptionService.getRGBDescription(img, 40, 30).getCategorizable());
					System.out.println("Learning From Up Image");
					break;
				case CALIBRATINGDOWN:
					myCategorizer.learn("down", ImageDescriptionService.getRGBDescription(img, 40, 30).getCategorizable());
					System.out.println("Learning From Down Image");
					break;
				case CALIBRATED:
					String estimatedState = myCategorizer.categorize(ImageDescriptionService.getRGBDescription(img, 40, 30).getCategorizable()).getCategory();
					lastState = estimatedState;
					System.out.println(estimatedState);
					break;
			}
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public CaptureDeviceInfo getDi() {
		return di;
	}

	public void setDi(CaptureDeviceInfo di) {
		this.di = di;
	}

	public MediaLocator getMl() {
		return ml;
	}

	public void setMl(MediaLocator ml) {
		this.ml = ml;
	}

	public JButton getCapture() {
		return capture;
	}

	public void setCapture(JButton capture) {
		this.capture = capture;
	}

	public Buffer getBuf() {
		return buf;
	}

	public void setBuf(Buffer buf) {
		this.buf = buf;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public VideoFormat getVf() {
		return vf;
	}

	public void setVf(VideoFormat vf) {
		this.vf = vf;
	}

	public BufferToImage getBtoi() {
		return btoi;
	}

	public void setBtoi(BufferToImage btoi) {
		this.btoi = btoi;
	}

	public ImagePanel getImgpanel() {
		return imgpanel;
	}

	public void setImgpanel(ImagePanel imgpanel) {
		this.imgpanel = imgpanel;
	}

	public boolean isRunLevel1() {
		return runLevel1;
	}

	public void setRunLevel1(boolean runLevel1) {
		this.runLevel1 = runLevel1;
	}

	public boolean isFrameGrab() {
		return frameGrab;
	}

	public void setFrameGrab(boolean frameGrab) {
		this.frameGrab = frameGrab;
	}

	public States getMyState() {
		return myState;
	}

	public void setMyState(States myState) {
		this.myState = myState;
	}

	public Categorizer getMyCategorizer() {
		return myCategorizer;
	}

	public void setMyCategorizer(Categorizer myCategorizer) {
		this.myCategorizer = myCategorizer;
	}

	public synchronized void resetCalibrator() {
		myCategorizer = new Categorizer();
	}

	public String getLastState() {
		return lastState;
	}
}