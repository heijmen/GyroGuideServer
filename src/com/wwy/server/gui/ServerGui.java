package com.wwy.server.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.wwy.server.Message;
import com.wwy.server.MessageAssembler;
import com.wwy.server.Server;

public class ServerGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private Message message;

	public ServerGui() {
		try {
			this.setLayout(new GridLayout(0,2, 5, 5));
			JButton b0 = new JButton("BackupStartup");
			b0.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					message = new Message(0x3, new int[] {150});
					Server.sendMessage(message);
				}
			});
			
			this.add(b0);
			
			JButton b1 = new JButton("Actie 1 = Scannen groenkitlampje, wiggle, hogetoeren");
			b1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						message = new Message(0x7, new int[] {1});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0x5, new int[] {2, 150});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0x2, new int[] {150});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0x3, new int[] {150});
						Server.sendMessage(message);
					} catch (InterruptedException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			});
			JButton b2 = new JButton("Actie 2 = progressbar rood daarma grpen");
			b2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						message = new Message(0xC, new int[] {0});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0xC, new int[] {255/7});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0xC, new int[] {255/7*2});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0xC, new int[] {255/7*3});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0xC, new int[] {255/7*4});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0xC, new int[] {255/7*5});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0xC, new int[] {255/7*6});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0xC, new int[] {255});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0xD, new int[] {255});
						Server.sendMessage(message);
					} catch (InterruptedException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			});

			JButton b3 = new JButton("Actie 3 = Lopen links rood richting");
			b3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						message = new Message(0x0, new int[] {175});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0xA, new int[] {175});
						Server.sendMessage(message);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			JButton b4 = new JButton("A4 Herrineringspunt aanmaken");
			b4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						message = new Message(0x6, new int[] {1});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0x8, new int[] {2});
						Server.sendMessage(message);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			JButton b5 = new JButton("Actie 5 =  Richard Lopen -> naar rechts " );
			b5.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						message = new Message(0x1, new int[] {254});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0xB, new int[] {254});
						Server.sendMessage(message);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			JButton b6 = new JButton("Actie 6 =  Richard eindpunt" );
			b6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						message = new Message(0x9, new int[] {2});
						Server.sendMessage(message);
						Thread.sleep(500);
						message = new Message(0x3, new int[] {100});
						Server.sendMessage(message);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			this.add(b1);
			this.add(b2);
			this.add(b3);
			this.add(b4);
			this.add(b5);
			this.add(b6);
			this.setSize(800, 600);

			this.setTitle(InetAddress.getLocalHost().toString() + ", Port: 1337");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	Runnable sendMessageRunnable = new Runnable() {
		@Override
		public void run() {
			JOptionPane.showMessageDialog(null, MessageAssembler.assembleMessageJson(message));
			Server.sendMessage(message);
		}
	};

	public void onConnectedToClient(String clientIp) {
		try {
			this.setTitle(InetAddress.getLocalHost().toString() + ", Port: 1337; Client: " + clientIp);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
