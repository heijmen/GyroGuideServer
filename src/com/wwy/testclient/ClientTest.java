package com.wwy.testclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sun.corba.se.impl.orbutil.ObjectWriter;
import com.wwy.server.Message;
import com.wwy.server.MessageAssembler;

public class ClientTest {
	private static PrintWriter out;
	private static BufferedReader in;
	public static void main(String[] s) throws Exception {
		PrintWriter out2 = null;
		BufferedReader in2 = null;

		out = out2;
		in = in2;
			InetAddress address = InetAddress.getByName("localhost");
			System.out.println(address);
			echoSocket = new Socket(address, 1337);

			//     Message m = new Message(1, new int[] {1, 2 , 3});
			//       String st = MessageAssembler.assembleMessageJson(m);
			out = new PrintWriter(echoSocket.getOutputStream(), true);

			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
			Thread Listentothis = new Thread(listenToMessages);
			Listentothis.start();
			

			
		
	}
	
	private static Runnable listenToMessages = new Runnable() {

		@Override
		public void run() {
			try {
				listenToMessages();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};
	private static Socket echoSocket;
	
	private static void exit() throws IOException {
		out.close();
		in.close();
		echoSocket.close();
	}
	
	private synchronized static void listenToMessages() throws IOException {
		while(true) {
			if(in != null) {
				String si = in.readLine();
				if(si != null && !si.equals("")) {
					System.out.println(in.readLine());
				} 
			}
		}
	}
}
