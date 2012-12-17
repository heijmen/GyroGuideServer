package com.wwy.server.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.wwy.server.Message;
import com.wwy.server.MessageAssembler;
import com.wwy.server.Server;

public class ServerGui extends JFrame {

	private Command selectedCommand;
	private static final String nameString = "<html><b>Geselecteerde command: %s</b></html>";
	private GridBagConstraints gc;
	private Server server;
	private Message message;
	
	private List<JTextField> fields = new ArrayList<JTextField>();
	
	public ServerGui(final Server server) {
		this.server = server; 
		JSplitPane jsPlitPanel = new JSplitPane();
		this.add(jsPlitPanel, BorderLayout.CENTER);
		final JScrollPane commandScrollPane = new JScrollPane();
		commandScrollPane.setBorder(new TitledBorder("CommandList"));
		jsPlitPanel.setLeftComponent(commandScrollPane);
		this.setSize(new Dimension(800, 600));
		final JList<Command> commandList = new JList<Command>();
		commandScrollPane.setViewportView(commandList);
		DefaultListModel<Command> listModel = new DefaultListModel<Command>();
		for(Command command : Command.values()) {
			listModel.addElement(command);
		}
		commandList.setModel(listModel);

		final JPanel thepanel = new JPanel();
		thepanel.setBorder(new TitledBorder("Command: "));
		jsPlitPanel.setRightComponent(thepanel);
		
		thepanel.setLayout(new GridBagLayout());

		final JButton bj = new JButton("Verstuur");
		
		
		commandList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(arg0.getValueIsAdjusting()) 
					return;
				fields.clear();
				thepanel.removeAll();
				selectedCommand = commandList.getSelectedValue();
				thepanel.setBorder(new TitledBorder("Command: " + selectedCommand.name));
				final JLabel namelabel = new JLabel(String.format(nameString, selectedCommand.name));
				gc = new GridBagConstraints();
				gc.gridx = 0;
				gc.gridy = 0;
				gc.gridwidth = 2;
				
				thepanel.add(namelabel, gc);
				
//				thepanel.add(new JLabel());
				int row = 0;
				for(String param : selectedCommand.params) {
					gc = new GridBagConstraints();
					row++;
					JLabel l = new JLabel(param);
					JTextField f = new JTextField();
					gc.gridx = 0;
					gc.gridy = row;
					thepanel.add(l, gc);
					gc = new GridBagConstraints();
					gc.gridx = 1;
					gc.gridy = row;
					gc.fill = GridBagConstraints.HORIZONTAL;
					thepanel.add(f, gc);
					fields.add(f);
				}
				gc = new GridBagConstraints();
				gc.gridx = 0;
				gc.gridy = row+ 1;
				gc.gridwidth = 2;
				gc.fill = GridBagConstraints.HORIZONTAL;
				thepanel.add(bj, gc);
				thepanel.revalidate();
				
			}
		});
		
		bj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int[] params = new int[fields.size()];
				int i = 0;
				for(JTextField jTextField : fields) {
					params[i] = Integer.parseInt(jTextField.getText());
					i++;
				}
				Message m = new Message(selectedCommand.command, params); //send this to le server
				message = m;
				Thread th = new Thread(sendMessageRunnable);
				th.start();
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	Runnable sendMessageRunnable = new Runnable() {
		@Override
		public void run() {
			JOptionPane.showMessageDialog(null, MessageAssembler.assembleMessageJson(message));
			sendMessage(message);
		}
	};
	
	public synchronized void sendMessage(Message message) {
		Server.sendMessage(message);
	}
	
	
}
