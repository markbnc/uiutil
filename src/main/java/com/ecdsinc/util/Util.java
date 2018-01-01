package com.ecdsinc.util;

import java.io.File;
import java.util.StringTokenizer;


public class Util {

	public static final String EMPTY_STRING = "";
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");

	static public String stripExtension(String filename) {

		String  strippedFilename = filename;

		int extIndex = filename.lastIndexOf(".");
		if (extIndex >= 0) {

			strippedFilename = filename.substring(0, extIndex);
		}

		return strippedFilename;
	}


	static public String getExtension(File pathname) {

		return getExtension(pathname.getAbsolutePath());
	}

	static public String getExtension(String pathname) {

		int extIndex = pathname.lastIndexOf(".");
		if (extIndex >= 0) {

			return pathname.substring(extIndex+1);
		}
		else {

			return EMPTY_STRING;
		}
	}

	static public String getFilename(File pathname) {

		return getFilename(pathname.getAbsolutePath());
	}

	static public String getFilename(String pathname) {

		int filenameStart = pathname.lastIndexOf(FILE_SEPARATOR);
		int filenameEnd = pathname.indexOf(".", filenameStart);
		String filename = null;

		if (filenameStart >= 0) {

			if (filenameEnd > filenameStart) {

				filename = pathname.substring(filenameStart + 1, filenameEnd);
			}
			else {

				filename = pathname.substring(filenameStart);
			}
		}

		return filename;
	}

	static public String getPath(File pathname) {

		return getPath(pathname.getAbsolutePath());
	}

	static public String getPath(String pathname) {

		String  path = null;
		int     pathEnd = pathname.lastIndexOf(FILE_SEPARATOR);

		if (pathEnd > 0) {

			path = pathname.substring(0, pathEnd);
		}

		return path;
	}

	static public String convertPathnameToClassName (String pathname) {

		String      fromChars = "\\/";
		String      toChar = ".";

		return convert(pathname, fromChars, toChar);
	}

	static public String convertClassNameToPathName (String className) {

		String      fromChars = ".";
		String      toChar = FILE_SEPARATOR;

		return convert(className, fromChars, toChar);
	}

	static public String convert(
		String fromString,
		String fromChars,
		String toChar) {

		StringTokenizer     tokens = new StringTokenizer(fromString, fromChars);
		StringBuffer        toString = new StringBuffer(fromString.length());

		//  Add the first token
		if (tokens.hasMoreTokens()) {

			toString.append(tokens.nextToken());
		}
		else {

			//  There were no delimiters as specified in fromChars.  Return the
			//  original string
			return fromString;
		}

		//  Add the rest of the tokens
		while (tokens.hasMoreTokens()) {

			toString.append(toChar);
			toString.append(tokens.nextToken());
		}

		return toString.toString();
	}
	
	static public String bytesToHexString(byte[] bytes) 
		throws UtilException {
		
		StringBuffer	buf = new StringBuffer(bytes.length * 2);
		
		try {
		
			for (int index = 0; index < bytes.length; index++) {
				
				int val = (bytes[index] >> 4) & 0xf;
				buf.append(lookupHexChar(val));
				
				val = bytes[index] & 0xf;
				buf.append(lookupHexChar(val));
			}
		} catch (UtilException except) {
			
			throw new UtilException("Error converting bytes to hex string", except);
		}
		
		return buf.toString();
	}
	
	static public byte[] HexStringToBytes(String str) 
		throws UtilException {
		
		int		numBytes = str.length() / 2;
		byte[]	bytes = new byte[numBytes];
		
		try {
		
			for (int index = 0, charIndex = 0; index < numBytes; index++) {
				
				char val = str.charAt(charIndex++);
				bytes[index] = (byte)(lookupByteVal(val) << 4);
				
				val = str.charAt(charIndex++);
				bytes[index] += (byte)(lookupByteVal(val));
			}
		} catch (UtilException except) {
			
			throw new UtilException("Error converting hex string to bytes", except);
		}
		
		return bytes;
	}
	
	static public char lookupHexChar(int val) 
		throws UtilException {
		
		switch (val) {
		
			case 0x0:		return '0';
			case 0x1:		return '1';
			case 0x2:		return '2';
			case 0x3:		return '3';
			case 0x4:		return '4';
			case 0x5:		return '5';
			case 0x6:		return '6';
			case 0x7:		return '7';
			case 0x8:		return '8';
			case 0x9:		return '9';
			case 0xa:		return 'a';
			case 0xb:		return 'b';
			case 0xc:		return 'c';
			case 0xd:		return 'd';
			case 0xe:		return 'e';
			case 0xf:		return 'f';
			default:		throw new UtilException ("Value " + val + " is not a valid hex digit");
		}
	}
	
	static public byte lookupByteVal(char val) 
		throws UtilException {
		
		switch (val) {
		
			case '0':		return 0x0;
			case '1':		return 0x1;
			case '2':		return 0x2;
			case '3':		return 0x3;
			case '4':		return 0x4;
			case '5':		return 0x5;
			case '6':		return 0x6;
			case '7':		return 0x7;
			case '8':		return 0x8;
			case '9':		return 0x9;
			case 'a':		return 0xa;
			case 'b':		return 0xb;
			case 'c':		return 0xc;
			case 'd':		return 0xd;
			case 'e':		return 0xe;
			case 'f':		return 0xf;
			default:		throw new UtilException("Character " + val + " is not a valid hex character");
		}
	}
}