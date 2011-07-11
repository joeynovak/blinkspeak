package com.joes.blinkspeak;

public class BlinkSpeakException extends Exception {

	public enum Type {
		NO_INPUT
	}
	
	private Type type;
	private String msg;
	
	public BlinkSpeakException(Type type, String msg) {
		this.type = type;
		this.msg = msg;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
