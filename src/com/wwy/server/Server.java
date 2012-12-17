package com.wwy.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import sun.security.krb5.internal.crypto.CksumType;

import com.wwy.server.gui.ServerGui;

public class Server {
	private ServerSocket serverSocket;
	private Socket clientSocket;

	private BufferedReader in;
	private static PrintWriter out;

	public Server(ServerGui gui) {
		try {
			serverSocket = new ServerSocket(1337);
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			gui.onConnectedToClient(clientSocket.getInetAddress().getHostAddress().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized void sendMessage(Message message) {
		String st = MessageAssembler.assembleMessageJson(message);
		out.println(st);
	}
}
