/*****************************************************************************
 *
 *	File: ModalDialog.java
 *
 *	Description:
 *      This file contains the ModalDialog class
 *
 *	Revision History
 *		$Log: ModalDialog.java $
 *		Revision 1.5  2005/11/26 17:17:08  markb
 *		added comments for deprecated methods
 *		Revision 1.4  2005/11/26 16:13:05  markb
 *		made DisposeDialogMethod public
 *		Revision 1.3  2003/02/02 07:21:00  MarkB
 *		added method initializeContols to allow derived classes to initialize the values of the dialog controls before the dialog is show
 *		Revision 1.2  2001/09/09 22:12:27  markb
 *		modified showDialog to set the size of the dialog to it's preferred size
 *		Revision 1.1  2001/03/26 05:34:45  markb
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
import javax.swing.*;


/**
 * This class is a base class for Modal dialogs.  It extends JDialog by adding
 * the showModal function.  Invoking the showModal funtion displays the dialog
 * and the calling function is blocked until the dialog is dismissed.  The value
 * returned from showModal indicates whether the changes made in the dialog
 * were accepted or canceled.
 */
@SuppressWarnings("serial")
public class ModalDialog extends JDialog {

	/**
	 * The dialog was dismissed and the changes were accepted
	 */
	public static final int DIALOG_ACCEPT = 1;

	/**
	 * The dialog was dismissed and the changes were canceled
	 */
	public static final int DIALOG_CANCEL = 0;

	/**
	 * The dialog was closed due to an error
	 */
	public static final int DIALOG_ERROR = -1;

	/**
	 * Constructs a modal dialog
	 *
	 * @param frame the parent frame for the dialog.  The parent frame may be null.
	 * @param title the string to display for the title of the dialog
	 *
	 */
    public ModalDialog(Frame frame, String title) {
        super(frame, title, true);
    }

	/**
	 * Cunstructs a modal dialog with no parent frame and no title
	 *
	 */
    public ModalDialog() {
        this(null, "");
    }

	/**
	 * display the dialog.  The calling function is blocked  until the dialog
	 * is dismissed.
	 *
	 * @return the dismiss status of the dialog.  It can be one of the following:
	 *  DIALOG_ACCEPT  - The changes to the dialog were accepted
	 *  DIALOG_CANCEL  - The changes to the dialog were canceled
	 *  DIALOG_ERROR   - The dialog was closed due to an error
	 *
	 */
	public int showDialog() {

		m_dismissStatus = DIALOG_CANCEL;

		pack();
		centerDialog();

		if (initializeControls()) {

			//  Make the dialog visible.  The show method has been deprecated in J2SE 1.5, but 
			//	J2SE 1.4 is still supported so leave this for now
		    super.setVisible(true);;

			//  This repaint is needed to fix a bug in JDK 1.3 and earlier where
			//  menus are not erased when covered by a dialog.
			//  See Sun Bug ID: 4189244
			getParent().repaint();
		}
		else {

			//  There was a problem initializing the controls
			m_dismissStatus = DIALOG_ERROR;
		}

		return m_dismissStatus;

	}

	/**
	 * display the dialog.  The calling function is blocked  until the dialog
	 * is dismissed.  This function does not return the dismiss status of the
	 * dialog.  Call getDismissStatus to get the dismiss status
	 *
	 */
	public void show () {
		showDialog();
	}

	/**
	 * returns the dismiss status of the dialog
	 *
	 * @return the dismiss status of the dialog.  It can be one of the following:
	 *  DIALOG_ACCEPT  - The changes to the dialog were accepted
	 *  DIALOG_CANCEL  - The changes to the dialog were canceled
	 *  DIALOG_ERROR   - The dialog was closed due to an error
	 *
	 */
	public int getDismissStatus() {
		return m_dismissStatus;
	}

	/**
	 * Specify whether the dialog should be centered about its parent when it
	 * is initially displayed
	 *
	 * @param centerDialog true if the dialog should be centered
	 *
	 */
    public void setCenterDialog(boolean centerDialog) {
        m_CenterDialog = centerDialog;
    }

	/**
	 * returns whether the dialog will be centered when intially displayed
	 *
	 * @return true if the dialog will be centered, false otherwise
	 *
	 */
    public boolean getCenterDialog() {
        return m_CenterDialog;
    }

	/**
	 * Sets the dialog dismiss status and disposes of the dialog.  Subclasses
	 * should call this function to close the dialog instead of calling the
	 * dispose or hide functions directly
	 *
	 * @param dismissStatus the dialog dismiss status.  It can be one of the following:
  	 *  DIALOG_ACCEPT  - The changes to the dialog were accepted
	 *  DIALOG_CANCEL  - The changes to the dialog were canceled
	 *  DIALOG_ERROR   - The dialog was closed due to an error
	 *
	 */
	public void disposeDialog(int dismissStatus) {
		m_dismissStatus = dismissStatus;
		dispose();
	}

	/**
	 * This method is called just before the dialog is made visible.  All of
	 * the controls have been constructed at this point.  Derivitive classes
	 * may override this method to set the initial values of the controls in
	 * the dialog.
	 *
	 * @returns true if the dialog should continue to be shown
	 *          false if the dialog should be dismissed with an error status
	 */
	protected boolean initializeControls() {

		return true;
	}

	protected void centerDialog() {

		if (!m_CenterDialog) {
			return;
		}

		Dimension       parentSize;
		Point           parentLocation;
		Dimension       dialogSize = getSize();
		Container       parent = getParent();

		if (parent == null) {
			parentSize = Toolkit.getDefaultToolkit().getScreenSize();
			parentLocation = new Point(0, 0);
		}
		else {
			parentSize = parent.getSize();
			parentLocation = parent.getLocation();
		}

		setLocation(parentLocation.x + ((parentSize.width - dialogSize.width) / 2),
					parentLocation.y + ((parentSize.height - dialogSize.height) / 2));

	}

	//  Private data members
	private int         m_dismissStatus;
    private boolean     m_CenterDialog = true;
}

