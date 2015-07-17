package com.wallet.common.cipher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import JKTFCrypto.JKTFSymmetricKey;

public class KTDBCipher {
	private JKTFSymmetricKey crypto = null;
	private String algoID = "1";//1: SEED,2 : 3DES, 3 : DES, 4 : AES128
	private String enc_msg;
	protected final Log log = LogFactory.getLog(getClass());
	/** 
	 * 생성자
	 * crypto 초기화
	 * */
	public KTDBCipher() {
		// TODO Auto-generated constructor stub
		
		try {
	  		crypto = new JKTFSymmetricKey();
	  	} catch (Exception e) {
	  		log.info("[ERROR] JKTFSymmetricKey::" + e.toString());
	  		return;
	  	}
	}
	
	public String encoding(String Message){
		String isMessage = Message;
		
		crypto.InitSecretKey();
	  	if ( crypto.getErrorCode() < 0 )
	  	{
	  		log.info("[ERROR] JKTFSymmetricKey::InitSecretKey" + crypto.getErrorCode());
	    	return "";
	  	}
	  	
	 // Encrypt
	  	crypto.setCipherAlgorithm(Integer.parseInt(algoID));
	  	try{
	  		enc_msg = crypto.EncryptData(isMessage.getBytes("EUC-KR"));
	  	}catch (Exception e) {
				// TODO: handle exception
			}
	    if (enc_msg == null || enc_msg.length() == 0) {
	    	log.info("[ERROR] JKTFSymmetricKey::EncryptData" + crypto.getErrorCode());
	    	return "";
	    }		
		return enc_msg;
	}
	
	public String decoding(String Message){
		String isMessage = Message;
		JKTFSymmetricKey crypto = null;
		try{
			crypto = new JKTFSymmetricKey();
		}catch (Exception e) {
			// TODO: handle exception
			log.info("[ERROR] JKTFSymmetricKey::" + e.toString());
		}
		
		// Get SecretKey
	  	crypto.InitSecretKey();
	  	if ( crypto.getErrorCode() < 0 )
	  	{
	  		log.info("[ERROR] JKTFSymmetricKey::InitSecretKey" + crypto.getErrorCode());
	    	return "";
	  	}
	  	
	  	// Decrypt
	  	crypto.setCipherAlgorithm(Integer.parseInt(algoID));		
	  	
	  	byte[] dec_data;
	    dec_data = crypto.DecryptData(isMessage);
	    if (dec_data == null) {
	    	log.info("isMessage : " + isMessage);
	    	log.info("[ERROR] JKTFSymmetricKey::DecryptData" + crypto.getErrorCode());
	    	
	  		return "";
	    }
	    
	    try{
	    	isMessage = new String(dec_data,"EUC-KR");
	    }catch (Exception e) {
				// TODO: handle exception
			}
	    crypto = null;
	  	
		return isMessage;
	}
	
	public String hash(String Message){
		crypto.InitSecretKey();
	  	if ( crypto.getErrorCode() < 0 )
	  	{
	  		log.info("[ERROR] JKTFSymmetricKey::InitSecretKey" + crypto.getErrorCode());
	    	return "";
	  	}
	  	
	  	String hashed_msg = crypto.Hash(Message.getBytes());
	    if (hashed_msg == null || hashed_msg.length() == 0) {
	    	log.info("[ERROR] JKTFSymmetricKey::Hash" + crypto.getErrorCode());
	    	return "";
	    }
	    return hashed_msg;
	}

	public JKTFSymmetricKey getCrypto() {
		return crypto;
	}

	public void setCrypto(JKTFSymmetricKey crypto) {
		this.crypto = crypto;
	}

	public String getAlgoID() {
		return algoID;
	}

	public void setAlgoID(String algoID) {
		this.algoID = algoID;
	}

	public String getEnc_msg() {
		return enc_msg;
	}
}
