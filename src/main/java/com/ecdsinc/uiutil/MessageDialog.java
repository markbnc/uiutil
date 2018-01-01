package com.ecdsinc.uiutil;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MessageDialog implements Runnable {

    public MessageDialog(Component parent, String message, String caption,
						 int messageType) {
		m_Parent = parent;
		m_Message = message;
		m_Caption = caption;
		m_MessageType = messageType;
    }

	public void run() {
		show();
    }

	public void show() {
		JOptionPane.showMessageDialog(m_Parent, m_Message, m_Caption,
									  m_MessageType);
	}

	Component       m_Parent;
	String          m_Message;
	String          m_Caption;
	int             m_MessageType;
}