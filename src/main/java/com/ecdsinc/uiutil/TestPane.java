/*****************************************************************************
 *
 *	File: TestPane.java
 *
 *	Description:
 *     This file contains the TestPane Java Bean class
 *
 *	Revision History
 *		$Log: TestPane.java $
 *		Revision 1.1  2001/03/26 05:34:51  markb
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
import javax.swing.border.*;

/**
 * A Java Bean class that implements a scrollable text pain suitable for use
 * in simple test applications.
 */
@SuppressWarnings("serial")
public class TestPane extends JPanel {

	//  Internal components
	private BorderLayout borderLayout = new BorderLayout();
    private JScrollPane jScrollPane = new JScrollPane();
    private JTextArea jTextArea = new JTextArea();

	/**
	 * Test pane constructor
	 */
    public TestPane() {
        try {
            jbInit();
        }
        catch(Exception ex) {

//          REVISIT -   Use ErrorDialog once it is implemented
//          ErrorDialog.showException (this, "Error initializing Test Pane", e);
		    JOptionPane.showMessageDialog(null, "Error Initializing Test Pane",
										  "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	/**
	 * Displays the string specified by message at the end of the text
	 * currently in the TestPane.  A newline is added to the end of the
	 * string.
	 *
	 * @param message the string containing the message to display.
	 *
	 */
	public void displayMessage(String message) {
		jTextArea.append(message + "\n");
	}

	/**
	 * Displays the string specified by message at the end of the text
	 * currently in the TestPane.  If newLine is true, a newline is added to
	 * the end of the string.
	 *
	 * @param message the string containing the message to display.
	 * @param newLine if true a newLine is added to the end of the message to be displayed
	 *
	 */
	public void displayMessage(String message, boolean newLine) {

		if (newLine) {
			jTextArea.append(message + "\n");
		}
		else {
			jTextArea.append(message);
		}
	}

	/**
	 * Displays Exception information.  A new message string is composed by
	 * concatonating the stack trace extracted from the exception specified by
	 * except to the string specified by message
	 *
	 * @param message the string containing the beginning of the message to display
	 * @param except the exception to extract the stack trace from
	 *
	 */
	public void displayException(String message, Exception except) {

		String composedMsg = message;

		// REVISIT - Add stack trace to end of composed message
		displayMessage(composedMsg);

	}

	/**
	 * Clears all of the text in the TestPane
	 *
	 */
	public void clear() {
		jTextArea.setText("");
	}

	/**
	 * Print the contents of the Test Pane
	 * Currently not implemented
	 *
	 */
	public void print() {
	}

	/**
	 * set the TestPane border
	 *
	 * @param newBorder the new border setting.
	 *
	 */
    public void setBorder(Border newBorder) {
		if (jTextArea == null) {
			super.setBorder(newBorder);
		}
		else {
		    jTextArea.setBorder(newBorder);
		}
    }

	/**
	 * get the current TestPane border setting
	 *
	 * @return the current border setting.
	 *
	 */
    public Border getBorder() {
		if (jTextArea == null) {
		    return super.getBorder();
		}
		else {
			return jTextArea.getBorder();
		}
    }

	/**
	 * set the font of the TestPane
	 *
	 * @param newFont the new font for the TestPane
	 *
	 */
    public void setFont(Font newFont) {
		if (jTextArea == null) {
			super.setFont(newFont);
		}
		else {
            jTextArea.setFont(newFont);
		}
    }

	/**
	 * get the current font of the TestPane
	 *
	 * @return the current font.
	 *
	 */
    public Font getFont() {
		if (jTextArea == null) {
			return super.getFont();
		}
		else {
            return jTextArea.getFont();
		}
    }

	/**
	 * set the background color
	 *
	 * @param newBackground the new color for the background
	 *
	 */
    public void setBackground(Color newBackground) {
		if (jTextArea == null) {
			super.setBackground(newBackground);
		}
		else {
            jTextArea.setBackground(newBackground);
		}
    }

	/**
	 * return the current background color
	 *
	 * @return the current background color.
	 *
	 */
    public Color getBackground() {
		if (jTextArea == null) {
			return super.getBackground();
		}
		else {
            return jTextArea.getBackground();
		}
    }

	/**
	 * set whether the TestPane is editable
	 *
	 * @param newEditable true to make the test pane editable, false to make it read only.
	 *
	 */
    public void setEditable(boolean newEditable) {
        jTextArea.setEditable(newEditable);
    }

	/**
	 * return whether the TestPane is editable
	 *
	 * @return true if the TestPane is editable, false otherwise.
	 *
	 */
    public boolean isEditable() {
        return jTextArea.isEditable();
    }

	/**
	 * set the margins
	 *
	 * @param newMargin the values for the top, bottom, left and right margins in pixels
	 *
	 */
    public void setMargin(Insets newMargin) {
		jTextArea.setMargin(newMargin);
    }

	/**
	 * get the current margin settings
	 *
	 * @return the values for the top, bottom, left and right margins in pixels.
	 *
	 */
    public Insets getMargin() {
        return jTextArea.getMargin();
    }

	/**
	 * set the text color
	 *
	 * @param newForeground the new text color.
	 *
	 */
    public void setForeground(Color newForeground) {
		if (jTextArea == null) {
			super.setForeground(newForeground);
		}
		else {
            jTextArea.setForeground(newForeground);
		}
    }

	/**
	 * return the current text color
	 *
	 * @return the current text color.
	 *
	 */
    public Color getForeground() {
		if (jTextArea == null) {
			return super.getForeground();
		}
		else {
            return jTextArea.getForeground();
		}
    }

	/**
	 * set the color of selected text
	 *
	 * @param newSelectedTextColor the new color for selected text.
	 *
	 */
    public void setSelectedTextColor(Color newSelectedTextColor) {
        jTextArea.setSelectedTextColor(newSelectedTextColor);
    }

	/**
	 * get the current color of selected text
	 *
	 * @return the current color of selected text.
	 *
	 */
    public Color getSelectedTextColor() {
        return jTextArea.getSelectedTextColor();
    }

	/**
	 * Set the selection color
	 *
	 * @param newSelectionColor the new selection color.
	 *
	 */
    public void setSelectionColor(Color newSelectionColor) {
        jTextArea.setSelectionColor(newSelectionColor);
    }

	/**
	 * get the current selection color
	 *
	 * @return the current selection color.
	 *
	 */
    public Color getSelectionColor() {
        return jTextArea.getSelectionColor();
    }

	/**
	 * Get the currently selected text
	 *
	 * @return the currently selected text.
	 *
	 */
	public String getSelectedText() {
		return jTextArea.getSelectedText();
	}

	/**
	 * Set the selection starting point
	 *
	 * @param selectionStart The position of the new selection starting point.
	 *      The new selection starting point must be before or at the selection
	 *      ending point.
	 *
	 */
	public void setSelectionStart(int selectionStart) {
		jTextArea.setSelectionStart(selectionStart);
	}

	/**
	 * Retuns the starting point of the current selection
	 *
	 * @return the position of the starting point of the current selection.
	 *
	 */
	public int getSelectionStart() {
		return jTextArea.getSelectionStart();
	}

	/**
	 * Set the selection ending point
	 *
	 * @param selectionEnd The position of the new selection ending point.
	 *      The new selection ending point must be at or after the selection
	 *      starting point.
	 *
	 */
	public void setSelectionEnd(int selectionEnd) {
		jTextArea.setSelectionEnd(selectionEnd);
	}

	/**
	 * Retuns the ending point of the current selection
	 *
	 * @return the position of the ending point of the current selection.
	 *
	 */
	public int getSelectionEnd() {
		return jTextArea.getSelectionEnd();
	}

	/**
	 * set the text contained in the TestPane
	 *
	 * @param newText the new text to display
	 *
	 */
    public void setText(String newText) {
        jTextArea.setText(newText);
    }

	/**
	 * get the text contained in the TestPane
	 *
	 * @return the current text in the TestPane.
	 *
	 */
    public String getText() {
        return jTextArea.getText();
    }

	/**
	 * set the tool tip text for the TestPane
	 *
	 * @param newToolTipText the new tool tip text string.
	 *
	 */
    public void setToolTipText(String newToolTipText) {
        jTextArea.setToolTipText(newToolTipText);
    }

	/**
	 * get the current tool tip text
	 *
	 * @return the current tool tip text.
	 *
	 */
    public String getToolTipText() {
        return jTextArea.getToolTipText();
    }

	/**
	 * Initializes the components of the Test Pane
	 *
	 * @param paramName paramDescrip.
	 *
	 * @return returnVal.
	 *
	 * @throws Exception
	 */
    private void jbInit() throws Exception {
        this.setLayout(borderLayout);
        this.add(jScrollPane, BorderLayout.CENTER);
        jScrollPane.getViewport().add(jTextArea, null);
    }


}