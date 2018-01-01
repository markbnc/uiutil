package com.ecdsinc.security;

import com.ecdsinc.util.UtilException;

@SuppressWarnings("serial")
public class EcdsSecurityException extends UtilException {

    public EcdsSecurityException(String message) {

		super(message);
    }
    
    public EcdsSecurityException(String message, Throwable cause) {
    	
    	super(message, cause);
    }
}