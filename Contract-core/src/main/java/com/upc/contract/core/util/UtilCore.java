package com.upc.contract.core.util;

import java.security.MessageDigest;
import java.util.List;

public class UtilCore {
	
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object valor) {
		if (valor == null) {
			return true;
		}

		if (valor instanceof String) {
			if (((String) valor).trim().length() == 0) {
				return true;
			}
		}
		if (valor instanceof List) {
			if (((List) valor).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static String generateSHA256(String message) throws Exception {
		return hashString(message, "SHA-256");
	}

	private static String hashString(String message, String algorithm) throws Exception {

		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

			return convertByteArrayToHexString(hashedBytes);
		} catch (Exception ex) {
			throw new Exception("Could not generate hash from String", ex);
		}
	}

	public static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}
}
