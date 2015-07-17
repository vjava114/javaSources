package com.wallet.common.cipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Cipher {
	/** 
   * SHA256 암호화 문자열로 변환
   * @param value
   * @return
   * @throws NoSuchAlgorithmException
   */
   public static String encryptSHA256(String value) throws NoSuchAlgorithmException{
          String encryptData = "";
          
          MessageDigest sha = MessageDigest.getInstance("SHA-256");
          sha.update(value.getBytes());

          byte[] digest = sha.digest();
          for (int i=0; i<digest.length; i++) {
                  encryptData += Integer.toHexString(digest[i] & 0xFF).toUpperCase();
          }
          
          return encryptData;
   }
}
