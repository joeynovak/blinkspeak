package com.joes.blinkspeak;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class EnterKeyInputDevice extends JFrame implements SimpleInterface, KeyListener{
	private boolean enterKeyDown = false;
	
	public EnterKeyInputDevice(){
		super.addKeyListener(this);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
	}
	
	@Override
	public int getState() {
		if(enterKeyDown) return 1;
		return 0;
	}

	@Override
	public int getStateCount() { 
		return 2;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 10){
			System.out.println("Enter Key Pressed");
			enterKeyDown = true;
		}
		else{
			System.out.println("Ignoring Key Press: " + e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(e.getKeyCode() == 10) enterKeyDown = false;		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//Do Nothing, we don't really care about this at all.
	}

	@Override
	public boolean isReady() {
		return true;
	}

}
