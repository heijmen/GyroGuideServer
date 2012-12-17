package com.wwy.server;

public class Message {
	
	private int Command;
	private int[] params;
	
	public Message(int command, int[] params) {
		Command = command;
		this.params = params;
	}
	public int getCommand() {
		return Command;
	}
	public void setCommand(int command) {
		Command = command;
	}
	public int[] getParams() {
		return params;
	}
	public void setParams(int[] params) {
		this.params = params;
	}
	
}
