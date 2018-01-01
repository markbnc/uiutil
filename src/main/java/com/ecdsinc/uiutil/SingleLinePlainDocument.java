package com.ecdsinc.uiutil;

import javax.swing.text.*;

@SuppressWarnings("serial")
public class SingleLinePlainDocument extends PlainDocument {

    public SingleLinePlainDocument() {
    }
    public void insertString(int offs, String str, AttributeSet a)
		throws BadLocationException {

		String  strippedString = stripNewLines(str);
        super.insertString( offs,  strippedString,  a);

    }

	private String stripNewLines(String str) {

		int             index;
		int             strLen = str.length();
		StringBuffer    newStr = new StringBuffer(strLen);
		char            curChar;

		for (index = 0; index < strLen; index++) {

			curChar = str.charAt(index);

			switch (curChar) {

				case '\n':
					newStr.append("\\n");
					break;

				case '\r':
					newStr.append("\\r");
					break;

				default:
					newStr.append(curChar);
			}
		}
		return newStr.toString();
	}

	public String getTextExpand(int offset, int length)
		    throws javax.swing.text.BadLocationException {

		String          origStr = super.getText( offset,  length);
		StringBuffer    resultStr = new StringBuffer(length);
		int             index;
		int             origLen = origStr.length();
		char            curChar;
		char            nextChar;

		for (index = 0; index < origLen; index++)
		{

			curChar = origStr.charAt(index);
			if (curChar == '\\') {
				index++;
				nextChar = origStr.charAt(index);
				if (nextChar == 'n') {
					resultStr.append('\n');
				}
				else if (nextChar == 'r') {
					resultStr.append('\r');
				}
				else {
					resultStr.append(curChar);
					resultStr.append(nextChar);
				}
			}
			else {
				resultStr.append(curChar);
			}
		}
		return resultStr.toString();
    }

}