package com.wwy.server;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.wwy.server.gui.ServerGui;


public class StartServer {
	
	static Server server;

	public static void main(String[] s) throws UnsupportedLookAndFeelException, MalformedURLException, IOException {
		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());;
		ServerGui gui = new ServerGui();
		gui.setVisible(true);
		server = new Server(gui);
	}
}
