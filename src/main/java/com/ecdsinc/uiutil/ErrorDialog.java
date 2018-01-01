/*****************************************************************************
 *
 *	File: ErrorDialog.java
 *
 *	Description:
 *      This file contains the ErrorDialog class
 *
 *	Revision History
 *		$Log: ErrorDialog.java $
 *		Revision 1.2  2003/12/07 07:06:53  markb
 *		Fixed warnings
 *		Revision 1.1  2001/03/26 05:34:41  markb
 *		Initial revision
 *
 *		Copyright (c) 2000, East Coast Dealer Services Inc. All rights
 *		reserved worldwide.  This program or documentation is supplied
 *      pursuant to and its use is governed by a license agreement from
 *      East Coast Dealer Services and contains confidential information
 *      of East Coast Dealer Services Inc. Disclosure and copying are
 *      subject to the terms of the license agreement
 *
 *****************************************************************************/

package com.ecdsinc.uiutil;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ResourceBundle;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 * The ErrorDialog class is used to display an Error or Warning dialog.  If
 * and exception is passed to the ErrorDialog the message and stack trace
 * associated with the exception are displayed in addition to the message
 * passed into the error dialog.
 *
 */
@SuppressWarnings("serial")
public class ErrorDialog extends ModalDialog {

	/**
	 * Display an error message
	 */
    public static final int ERROR_MSG = 0;

	/**
	 * Display a warning message
	 */
	public static final int WARNING_MSG = 1;

	/**
	 * Construct an ErrorDialog.
	 *
	 * @param frame         the parent frame of the dialog
	 * @param message       the error or warning message to display
	 * @param messageType   the message type.  It may be one of the following
	 *  ERROR_MSG
	 *  WARNING_MSG
	 *
	 */
    public ErrorDialog(Frame frame, String message, int messageType) {
        super(frame, "");

		init(message, messageType, null);

		//  Disable and hide the More button since there is no exception
		//  information using this form
		moreBtn.setVisible(false);
    }

	/**
	 * Construct an ErrorDialog.  The message type is set to ERROR_MSG
	 *
	 * @param frame     the parent frame of the dialog
	 * @param message   the error or warning message to display
	 * @param except    the exception from which to extract the stack trace
	 *  and exception message to display
	 *
	 */
	public ErrorDialog(Frame frame, String message, Exception except) {
		super(frame, "");

		init(message, ERROR_MSG, except);
	}

	/**
	 * Display an exception dialog
	 *
	 * @param frame     the parent frame of the dialog
	 * @param message   the message to display with the exception info
	 * @param except    the exception from which to extract the stack trace
	 *  and exception message to display
	 *
	 */
	public static void showException(Frame frame, String message,
								     Exception except) {

		ErrorDialog     dlg = new ErrorDialog(frame, message, except);
		dlg.showDialog();

	}

	/**
	 * Display an error dialog
	 *
	 * @param frame the parent frame of the dialog
	 * @param message the error message to display
	 *
	 */
	public static void showError(Frame frame, String message) {

		ErrorDialog     dlg = new ErrorDialog(frame, message,
											  ErrorDialog.ERROR_MSG);
		dlg.showDialog();

	}

	/**
	 * Display a warning dialog
	 *
	 * @param frame     the parent frame of the dialog
	 * @param message   the warning message to display
	 *
	 */
	public static void showWarning(Frame frame, String message) {

		ErrorDialog     dlg = new ErrorDialog(frame, message,
											  ErrorDialog.WARNING_MSG);
		dlg.showDialog();

	}
	/**
	 * Initializes the GUI components of the dialog
	 *
	 * @throws Exception
	 *
	 */
    void jbInit() throws Exception {
        panel1.setLayout(gridBagLayout1);
        messageCtrl.setLineWrap(true);
        messageCtrl.setText("messageCtrl");
        messageCtrl.setBackground(UIManager.getColor(SystemColor.control));
        messageCtrl.setEditable(false);
        messageCtrl.setFont(m_Font);
        stackTraceCtrl.setText("stackTraceCtrl");
		stackTraceCtrl.setFont(m_Font);
        okBtn.setMaximumSize(new Dimension(79, 27));
        okBtn.setMinimumSize(new Dimension(79, 27));
        okBtn.setPreferredSize(new Dimension(79, 27));
        okBtn.setText("OK");
        okBtn.addActionListener(new ErrorDialog_okBtn_actionAdapter(this));
        moreBtn.setText("More >>");
        moreBtn.addActionListener(new ErrorDialog_moreBtn_actionAdapter(this));
        iconCtrl.setMaximumSize(new Dimension(32, 32));
        iconCtrl.setMinimumSize(new Dimension(32, 32));
        iconCtrl.setPreferredSize(new Dimension(32, 32));
        buttonPane.setLayout(gridLayout1);
        jScrollPane1.setPreferredSize(new Dimension(102, 120));
        gridLayout1.setRows(3);
        gridLayout1.setColumns(1);
        gridLayout1.setVgap(5);
        getContentPane().add(panel1);
        panel1.add(iconCtrl, new GridBagConstraints(0, 0, 1, 1, 0.3, 0.4
            ,GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(8, 25, 0, 25), 0, 0));
        panel1.add(messageCtrl, new GridBagConstraints(1, 0, 1, 1, 0.7, 0.4
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(8, 8, 0, 8), 0, 0));
        panel1.add(buttonPane, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.4
            ,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(8, 8, 8, 8), 0, 0));
        panel1.add(jScrollPane1, new GridBagConstraints(0, 1, 3, 1, 0.3, 0.6
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(8, 8, 8, 8), 0, 0));
        jScrollPane1.getViewport().add(stackTraceCtrl, null);
		buttonPane.add(okBtn);
        buttonPane.add(moreBtn);

		//  Initially the stackTrace control is not visible.  The stack trace
		//  control is contained in the jScrollPane1, so hide jScrollPane1.
		jScrollPane1.setVisible(false);
    }

	void moreBtn_actionPerformed(ActionEvent e) {

		//  Expand the height of the dialog and make the stackTrace TextArea
		//  visible.  Disable the More button
		jScrollPane1.setVisible(true);
		moreBtn.setEnabled(false);
		setSize(INITIAL_WIDTH, EXPAND_HEIGHT);
		validate();

		//  Scroll to the top of the stackTrace TextArea
		JScrollBar vScroll = jScrollPane1.getVerticalScrollBar();
		vScroll.setValue(vScroll.getMinimum());

    }

    void okBtn_actionPerformed(ActionEvent e) {
		super.disposeDialog(ModalDialog.DIALOG_ACCEPT);
    }

	/*
	 * Initializes the Error dialog.  This function is the common initializer
	 * funciton called by all constructors
	 *
	 * @param message       the error or warning message to display
	 * @param messageType   the message type.  It may be one of the following
	 * @param except        the exception from which to extract the stack trace
	 *
	 */
	private void init(String message, int messageType, Exception except) {

        try {

			//  Get the font based on the current look and feel
			m_Font = UIManager.getFont("OptionPane.font");

			//  Initialize the components
            jbInit();
            pack();

			//  Set the title and the icon based on the message type
			switch (messageType) {

				case WARNING_MSG:
					setTitle(res.getString("Warning"));
					setIcon(WARNING_ICON);
					break;

				case ERROR_MSG:
				default:
 				    setTitle(res.getString("Error"));
					setIcon(ERROR_ICON);
			}

			//  now set text for the message and stackTrace controls
			setMessage (message);
			setStackTrace(except);

			setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
			setResizable(false);

       }
        catch(Exception ex) {
            ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
										  res.getString("Err_ErrorDialogInit"),
										  res.getString("Error"), JOptionPane.ERROR_MESSAGE);
        }
    }

	private void setMessage(String message) {
		messageCtrl.setText(message);
	}

	private void setIcon(String iconName) {
	    m_Icon = UIManager.getIcon(iconName);
		iconCtrl.setIcon(m_Icon);
	}

	private void setStackTrace (Exception except) {

		ByteArrayOutputStream   byteStrm = new ByteArrayOutputStream(256);
		PrintWriter             printWrtr = new PrintWriter(byteStrm);
		except.printStackTrace(printWrtr);
		printWrtr.flush();

		stackTraceCtrl.setText(byteStrm.toString());
	}

	//  Resource Bundle
	static ResourceBundle res = ResourceBundle.getBundle("com.ecdsinc.uiutil.Res");

	//  Misc private constants
	private static final String     ERROR_ICON = "OptionPane.errorIcon";
	private static final String     WARNING_ICON = "OptionPane.warningIcon";
//	private static final String     INFO_ICON = "OptionPane.informationIcon";
//	private static final String     QUESTION_ICON = "OptionPane.questionIcon";

	private static final int        INITIAL_WIDTH = 400;
	private static final int        INITIAL_HEIGHT = 125;
	private static final int        EXPAND_HEIGHT = 300;



	//  private data members
	Icon   m_Icon = null;
	Font   m_Font = null;

	//  Dialog components
    JPanel panel1 = new JPanel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JLabel iconCtrl = new JLabel();
    JTextArea messageCtrl = new JTextArea();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea stackTraceCtrl = new JTextArea();
    JButton okBtn = new JButton();
    JButton moreBtn = new JButton();
    JPanel buttonPane = new JPanel();
    GridLayout gridLayout1 = new GridLayout();

}

class ErrorDialog_moreBtn_actionAdapter implements java.awt.event.ActionListener {
    ErrorDialog adaptee;

    ErrorDialog_moreBtn_actionAdapter(ErrorDialog adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.moreBtn_actionPerformed(e);
    }
}

class ErrorDialog_okBtn_actionAdapter implements java.awt.event.ActionListener {
    ErrorDialog adaptee;

    ErrorDialog_okBtn_actionAdapter(ErrorDialog adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.okBtn_actionPerformed(e);
    }
}