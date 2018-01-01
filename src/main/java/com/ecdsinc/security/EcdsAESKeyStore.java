package com.ecdsinc.security;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.EnumMap;

import javax.crypto.KeyGenerator;

public class EcdsAESKeyStore extends EcdsKeyStore {

	EnumMap<KeySize, KeyGenerator> keyGenerators = new EnumMap<>(KeySize.class);
	
	@Override
	public Key createKey(KeySize keySize) throws EcdsSecurityException {

		KeyGenerator	keyGenerator = keyGenerators.get(keySize);
		if (keyGenerator == null) {

			try {
				keyGenerator = KeyGenerator.getInstance("AES");
				keyGenerator.init(keySize.getValue());
				keyGenerators.put(keySize, keyGenerator);
			}
			catch (GeneralSecurityException except) {

				throw new EcdsSecurityException("Error creating AES key with size " + keySize.toString() + ": " + except);
			}
		}

		return keyGenerator.generateKey();
	}

	@Override
	public Key createKey() throws EcdsSecurityException {
		
		throw new EcdsSecurityException("AES encryption algorithms require a key size be specified.  Use createKey(EcdsKeyStore.KeySize) instead");
	}

}
