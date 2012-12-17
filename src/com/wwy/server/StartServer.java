package com.wwy.server;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.wwy.server.gui.ServerGui;


public class StartServer {
	
	static Server server;

	public static void main(String[] s) throws UnsupportedLookAndFeelException {
		
		
//		Thread th2 = new Thread(serverr);
//		th2.start();
		Thread th = new Thread(gui);
		th.start();
		server = new Server();
		
		
	
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
