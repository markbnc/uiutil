/*****************************************************************************
 *
 *	File: KeyStoreNotFoundException.java
 *
 *	Description:
 *      Exception which is generated when the file containing keystore
 * 		information can not be located
 *
 *	Revision History
 *		$Log: KeyStoreNotFoundException.java $
 *		Revision 1.1  2004/01/04 22:12:53  MarkB
 *		Initial revision
 *
 *		Copyright (c) 2004, East Coast Dealer Services Inc. All rights
 *		reserved worldwide.  This program or documentation is supplied
 *      pursuant to and its use is governed by a license agreement from
 *      East Coast Dealer Services and contains confidential information
 *      of East Coast Dealer Services Inc. Disclosure and copying are
 *      subject to the terms of the license agreement
 *
 *****************************************************************************/
package com.ecdsinc.security;

@SuppressWarnings("serial")
public class KeyStoreNotFoundException 
	extends EcdsSecurityException {

		public KeyStoreNotFoundException(String message) {

			super(message);
		}
    
		public KeyStoreNotFoundException(String message, Throwable cause) {
    	
			super(message, cause);
		}
}
