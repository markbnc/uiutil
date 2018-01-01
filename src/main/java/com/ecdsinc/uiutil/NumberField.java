package com.ecdsinc.uiutil;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;

@SuppressWarnings("serial")
public class NumberField extends JTextField {

    public NumberField() {
    }

    public void setMaxDigits(int newMaxDigits) {
		getNumberDocument().setMaxDigits(newMaxDigits);
    }

    public int getMaxDigits() {
        return getNumberDocument().getMaxDigits();
    }

    public void setAllowNegative(boolean newAllowNegative) {
        getNumberDocument().setAllowNegative(newAllowNegative);
    }

    public boolean getAllowNegative() {
        return getNumberDocument().getAllowNegative();
    }

	protected Document createDefaultModel() {
		return new NumberDocument();
	}

	private NumberDocument getNumberDocument() {
	    return (NumberDocument) getDocument();
	}

}

@SuppressWarnings("serial")
class NumberDocument extends PlainDocument {

	public NumberDocument() {
	}

	public void setMaxDigits (int maxDigits) {
		m_MaxDigits = maxDigits;
	}

	public int getMaxDigits () {
		return m_MaxDigits;
	}

	public void setAllowNegative(boolean allowNegative) {
		m_AllowNegative = allowNegative;
	}

	public boolean getAllowNegative() {
		return m_AllowNegative;
	}

	public void insertString(int offset, String str, AttributeSet attrSet)
		    throws BadLocationException {

		if ((str == null) || (
		str.length() == 0)) {
		    return;
		}

		//  Handle inserting a single minus sign character.  It must be the
		//  first chararcter and negative numbers must be allowed
		if ((str.length() == 1) && str.equals("-")) {

			if ((offset != 0) || !m_AllowNegative || hasMinusSign()) {
				Toolkit.getDefaultToolkit().beep();
			}
			else {
				super.insertString(offset, str, attrSet);
			}
			return;
		}

		//  If the string being added will make the document length greater
		//  than the maximum number of digits then throw a NumberLength
		//  exception, otherwise call the superclass to add the new string
		if ((getDigits() + str.length()) > m_MaxDigits) {
			Toolkit.getDefaultToolkit().beep();
			return;
		}

		//  Make sure the string being added is a number
		try {
			Integer.parseInt(str);
		}
		catch (NumberFormatException except) {
			Toolkit.getDefaultToolkit().beep();
			return;
		}

		//  String being inserted is valid.  Call superclass to insert it
		super.insertString(offset, str, attrSet);

	}

	public int getDigits() {

		int length = getLength();
		if (hasMinusSign()) {
			length--;
		}
		return length;
	}

	private boolean hasMinusSign() {

		try {
		    String  contents = getText(0, getLength());
		    int     index = contents.indexOf('-');
		    return (index >= 0) ? true : false;
		}
		catch (BadLocationException except) {
			System.out.println("hasMinusSign: Error getting document text");
			return false;
		}

	}

    private int         m_MaxDigits = 19;
	private boolean     m_AllowNegative = true;

}