package com.ecdsinc.security;

import javax.crypto.*;
import java.security.*;

public abstract class EcdsEncryptorDecryptor {

	Cipher      			cipher = null;
	AlgorithmParameters		algorithmParams;
	byte[]					saltBytes;
	SecureRandom			random;

	abstract void initCipher() throws EcdsSecurityException;

	public EcdsEncryptorDecryptor(int saltBytes) {
		
		this.saltBytes = new byte[saltBytes];
		if (saltBytes > 0) {
			
			random = new SecureRandom();
		}
	}
	
	public byte[] encrypt(String value, Key key)
		throws EcdsSecurityException {

		if (this.cipher == null) {

			initCipher();
		}

		try {

			// Initialize the cipher for encryption
			if (algorithmParams == null) {
	    	
				this.cipher.init(Cipher.ENCRYPT_MODE, key);
			}
			else {
				
				this.cipher.init(Cipher.ENCRYPT_MODE, key, algorithmParams);
			}

		    // Encrypt the value
			byte[] inputBytes;
			byte[] valueBytes = value.getBytes("UTF-8");
			if (saltBytes.length > 0) {
				
				random.nextBytes(saltBytes);
				inputBytes = new byte[saltBytes.length + valueBytes.length];
				System.arraycopy(saltBytes, 0, inputBytes, 0, saltBytes.length);
				System.arraycopy(valueBytes, 0, inputBytes, saltBytes.length, valueBytes.length);
			}
			else {
				inputBytes = valueBytes;
			}
			return this.cipher.doFinal(inputBytes);
		}
		catch (Exception except) {

			throw new EcdsSecurityException("Error encrypting value: " + except);
		}
	}

	public String decrypt(byte[] encryptedValue, Key key)
		throws EcdsSecurityException {

		if (this.cipher == null) {

			initCipher();
		}

		try {

			// Initialize the same cipher for decryption
			if (algorithmParams == null) {
				this.cipher.init(Cipher.DECRYPT_MODE, key);
			}
			else {
			
				this.cipher.init(Cipher.DECRYPT_MODE, key, algorithmParams);
			}

			// Decrypt the password
			byte[] decryptedValueBytes;
			byte[] decryptedBytes = this.cipher.doFinal(encryptedValue);
			if (saltBytes.length > 0) {
				
				decryptedValueBytes = new byte[decryptedBytes.length - saltBytes.length];
				System.arraycopy(decryptedBytes, saltBytes.length, decryptedValueBytes, 0, decryptedValueBytes.length);
			}
			else {
				
				decryptedValueBytes = decryptedBytes;
			}
			
			return new String (decryptedValueBytes, "UTF-8");
		}
		catch (Exception except) {

			throw new EcdsSecurityException("Error decrypting value: " + except);
		}
	}
}