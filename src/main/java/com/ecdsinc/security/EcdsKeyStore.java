/*****************************************************************************
 *
 *	File: EcdsKeyStore.java
 *
 *	Description:
 *      This class manages the opening, updating and closing of a java
 *      cyptography api (JCA) keystore.  This class can be used to fetch
 * 		keys from the keystore and create and store new keys in the
 * 		keystore
 *
 *	Revision History
 *		$Log: EcdsKeyStore.java $
 *		Revision 1.4  2004/01/04 22:16:28  MarkB
 *		Added method createKeyStore
 *		Methods open and store now throw a KeyStoreNotFoundException
 *		if the keystore file is not found
 *
 *		Copyright (c) 2003, East Coast Dealer Services Inc. All rights
 *		reserved worldwide.  This program or documentation is supplied
 *      pursuant to and its use is governed by a license agreement from
 *      East Coast Dealer Services and contains confidential information
 *      of East Coast Dealer Services Inc. Disclosure and copying are
 *      subject to the terms of the license agreement
 *
 *****************************************************************************/
package com.ecdsinc.security;

import java.security.*;
import java.io.*;

public abstract class EcdsKeyStore {

	KeyStore        keyStore = null;
	String          keyStoreName;
	String          keyStorePassword;

	public abstract Key createKey() throws EcdsSecurityException;
	public abstract Key createKey(KeySize size) throws EcdsSecurityException;

	public void createKeyStore(String keyStoreName, String keyStorePassword)
		throws EcdsSecurityException {

		open(null, keyStorePassword);	
		this.keyStoreName = keyStoreName;
		this.keyStorePassword = keyStorePassword;
	}

	public void open(String keyStoreName, String keyStorePassword)
		throws EcdsSecurityException {

		FileInputStream     inStream = null;

		if (this.keyStore == null) {

			this.keyStoreName = keyStoreName;
			this.keyStorePassword = keyStorePassword;

		    //  Locate the keystore file
			try {

				if (keyStoreName != null) {
					
					inStream = new FileInputStream (keyStoreName);
				}
			}
			catch (IOException except) {

				throw new KeyStoreNotFoundException("Could not locate the keystore file " + keyStoreName);
			}

			//  load the keystore
			try {

				this.keyStore = KeyStore.getInstance("JCEKS");
//				this.keyStore = KeyStore.getInstance("JKS");
				char[] pwdChars = new char[keyStorePassword.length()];
				keyStorePassword.getChars(0, keyStorePassword.length(), pwdChars, 0);
				this.keyStore.load(inStream, pwdChars);
			}
			catch (GeneralSecurityException except) {

				throw new EcdsSecurityException("Error loading KeyStore: " + except);
			}
			catch (IOException except) {

				throw new EcdsSecurityException("Error loading KeyStore: " + except);
			}
			finally {

				try {
					if (inStream != null) {

		    			inStream.close();
			    	}
				}
				catch (IOException except) {
				}
			}
		}
	}

	public void close() throws EcdsSecurityException {

		store();
		this.keyStore = null;
		this.keyStoreName = null;
		this.keyStorePassword = null;
	}

	public void store() throws EcdsSecurityException {

		FileOutputStream    outStream = null;

		if (this.keyStore != null) {

			try {
				outStream = new FileOutputStream(keyStoreName);
			}
			catch (IOException except) {

				throw new KeyStoreNotFoundException("Could not locate the keystore file " + keyStoreName);
			}

			try {
				char[] pwdChars = new char[keyStorePassword.length()];
	    		keyStorePassword.getChars(0, keyStorePassword.length(), pwdChars, 0);
		    	this.keyStore.store(outStream, pwdChars);
			}
			catch (GeneralSecurityException except) {

				throw new EcdsSecurityException("Error updating keystore: " + except);
			}
			catch (IOException except) {

				throw new EcdsSecurityException("Error updating keystore: " + except);
			}
			finally {

				try {
					if (outStream != null) {

		    			outStream.close();
			    	}
				}
				catch (IOException except) {
				}
			}
		}
	}

	public Key fetchKey(String userId, String password)
		throws EcdsSecurityException {

		if (this.keyStore == null) {

			throw new EcdsSecurityException("Must open KeyStore before fetching keys");
		}

		try {
			char[] pwdChars = new char[password.length()];
	    	password.getChars(0, password.length(), pwdChars, 0);
		    return this.keyStore.getKey(userId, pwdChars);
		}
		catch (GeneralSecurityException except) {

			throw new EcdsSecurityException("Error fetching key from the KeyStore\nVerify that the Vault Name and Password are correct", 
										    except);
		}
	}

	public void storeKey(Key key, String userId, String password)
		throws EcdsSecurityException {

		if (this.keyStore == null) {

			throw new EcdsSecurityException("Must open KeyStore before storing keys");
		}

		try {
			char[] pwdChars = new char[password.length()];
	    	password.getChars(0, password.length(), pwdChars, 0);
		    this.keyStore.setKeyEntry(userId, key, pwdChars, null);
		}
		catch (GeneralSecurityException except) {

			throw new EcdsSecurityException("Error storing key in the KeyStore: " + except);
		}
	}
	
	public boolean keyExists(String userId)
	throws EcdsSecurityException {
		
		try {
		
			return keyStore.isKeyEntry(userId);
		}
		catch (KeyStoreException except) {
			
			throw new EcdsSecurityException("Error checking whether key " + userId + " exists - Keystor not initialized");
		}
	}
	
	public enum KeySize {
		
		KEY128(128),
		KEY256(256);
		
		private int size;
		
		private KeySize(int size) {
			
			this.size = size;
		}

		public int getValue() {
			return size;
		}
	}
}