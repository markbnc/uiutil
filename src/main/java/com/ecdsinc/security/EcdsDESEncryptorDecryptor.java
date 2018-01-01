package com.ecdsinc.security;

import javax.crypto.*;
import java.security.*;

public class EcdsDESEncryptorDecryptor extends EcdsEncryptorDecryptor {

	public EcdsDESEncryptorDecryptor(int saltBytes) {

		super(saltBytes);
	}

	void initCipher()
		throws EcdsSecurityException {

		try {
			if (this.cipher == null) {

	    		this.cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		    }
		}
		catch (GeneralSecurityException except) {

			throw new EcdsSecurityException("Error initializing Cipher: " + except);
		}
	}
}