package com.wwy.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.wwy.server.gui.ServerGui;


public class StartServer {
	
	static Server server;

	public static void main(String[] s) throws UnsupportedLookAndFeelException, MalformedURLException, IOException {
		
		
//		Thread th2 = new Thread(serverr);
//		th2.start();
		Server	server = null;
		
		UIManager.setLookAndFeel(new NimbusLookAndFeel());;
		
		ServerGui gui = new ServerGui(server);
		gui.setVisible(true);
		server = new Server(gui);
	}
	
	static Runnable gui = new Runnable() {
		@Override
		public void run() {
			System.setProperty(
		            "Quaqua.tabLayoutPolicy","wrap"

		         );
		 System.setProperty("Quaqua.Debug.crossPlatform", "");
		 try {
			 // UIManager.setLookAndFeel(new InfoNodeLookAndFeel();
		//	  UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
			 UIManager.setLookAndFeel(new NimbusLookAndFeel());;
          
          
     } catch (Exception e) {
     }
		new ServerGui(server).setVisible(true);
		}
	};
	
//	static Runnable serverr = new Runnable() {
//		@Override
//		public void run() {
//			server = new Server();
//		}
//	};
}
