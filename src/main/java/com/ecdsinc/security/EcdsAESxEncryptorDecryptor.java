package com.ecdsinc.security;

import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;

public class EcdsAESxEncryptorDecryptor 
	extends EcdsEncryptorDecryptor {

	public EcdsAESxEncryptorDecryptor(int saltBytes) {
	
		super(saltBytes);
	}
	
	void initCipher() throws EcdsSecurityException {

		// TODO: Look at specifying initialization parameters?
		try {
			if (this.cipher == null) {

				this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		    }
		}
		catch (GeneralSecurityException except) {

			throw new EcdsSecurityException("Error initializing Cipher: " + except);
		}
	}

	AlgorithmParameters getAlgorithmParameters() throws EcdsSecurityException {
		return null;
	}
}
