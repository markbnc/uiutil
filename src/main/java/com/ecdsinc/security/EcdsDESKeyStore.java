package com.ecdsinc.security;

import javax.crypto.*;
import java.security.*;

public class EcdsDESKeyStore extends EcdsKeyStore {

	KeyGenerator	keyGenerator;
	
	public Key createKey() throws EcdsSecurityException {

		if (keyGenerator == null) {

			try {
				keyGenerator = KeyGenerator.getInstance("DES");
			}
			catch (GeneralSecurityException except) {

				throw new EcdsSecurityException("Error creating key: " + except);
			}
		}

		return keyGenerator.generateKey();
	}

	@Override
	public Key createKey(KeySize size) throws EcdsSecurityException {

		throw new EcdsSecurityException("A key size can not be specified with DES encryption algorithm");
	}
}