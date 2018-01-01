package com.ecdsinc.security;

import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

public class EcdsAESEncryptorDecryptor 
	extends EcdsEncryptorDecryptor {

	private static final byte[]		params = {
			(byte) 0xfe, (byte) 0x38, (byte) 0xe1, (byte) 0x55, (byte) 0xc0, (byte) 0x95, (byte) 0x25, (byte) 0xc2,
			(byte) 0x44, (byte) 0xfe, (byte) 0x5b, (byte) 0xa8, (byte) 0x4c, (byte) 0xcd, (byte) 0xbc, (byte) 0xad
		};
		
		private static final String KEY_ALGORITHM = "AES";
		private static final String CIPHER_ALGORITHM = KEY_ALGORITHM + "/CBC/PKCS5Padding";

	public EcdsAESEncryptorDecryptor(int saltBytes) {
	
		super(saltBytes);	
	}

	void initCipher() throws EcdsSecurityException {

		try {
			cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		}
		catch (GeneralSecurityException except) {
			
			throw new EcdsSecurityException("Error getting cipher instance for algorithm " + CIPHER_ALGORITHM + " Error: " + except);
		}
		try {
			algorithmParams = AlgorithmParameters.getInstance(KEY_ALGORITHM);
		}
		catch (GeneralSecurityException except) {

			throw new EcdsSecurityException("Error getting algorithm parameters for algorithm " + CIPHER_ALGORITHM + " Error: " + except);
		}

		try {
			algorithmParams.init(new IvParameterSpec(params));
		}
		catch (Exception except) {
			
			throw new EcdsSecurityException("Error initializing algorithm parameters for algorithm " + CIPHER_ALGORITHM + " Error: " + except);
		}
	}
}
