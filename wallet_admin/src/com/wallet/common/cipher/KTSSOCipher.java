package com.wallet.common.cipher;

import java.net.URLDecoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import JKTFCrypto.JKTFCrypto;

public class KTSSOCipher {
	private JKTFCrypto crypto = null;
	private String algoID = "1";//1: SEED,2 : 3DES, 3 : DES, 4 : AES128
	private String KeyID = "";
	private String enc_key,enc_msg;
	protected final Log log = LogFactory.getLog(getClass());
	/**
	 * 생성자
	 * 현재 OS클라이언트가 가지고 있는 키를 가져온다.
	 * */
	public KTSSOCipher() {		
		// TODO Auto-generated constructor stub
		try{
			crypto = new JKTFCrypto();
		}catch (Exception e) {
			// TODO: handle exception
			log.info(e.toString());
		}		
		try{
			KeyID = crypto.getKeyId();
			enc_key = crypto.CreateSessionKey();
		}catch (Exception e) {
			// TODO: handle exception
			log.info("KTCipher : 키 관리 클라이언트에서 키가 설정 되어 있지 않습니다.");
		}
	}
	
	/**
	 * Key를 암호화 한다. SSO
	 * */
	public String encoding(String Message){		
		String isMessage = Message;		
		
	    if (enc_key == null || enc_key.length() == 0) {
	    	log.info("[JKTFCrypto]CreateSessionKey : " + crypto.getErrorCode());
	  		return "";
	    }
	    
	    crypto.setCipherAlgorithm(Integer.parseInt(algoID));
	    
	    enc_msg = crypto.EncryptData(isMessage.getBytes());
	    if (enc_msg == null || enc_msg.length() == 0) {
	    	return "";	    	
	    }
	  	return  enc_msg;
	}
	
	/**
	 * 키를 복호화 한다. SSO
	 * */  
	public String decoding(String Message,String EncKey){
		String isMessage = null;
		String isEncKey = null;
		
		isMessage = URLDecoder.decode(Message);		
		isEncKey = URLDecoder.decode(EncKey);		
		
		byte[] dec_data;
		JKTFCrypto crypto = null;		
		try {
			crypto = new JKTFCrypto();
	  	} catch (Exception e) {
	  		log.info("[ERROR]" + e.toString());
	  		return "";
	  	}
	  	crypto.setKeyId(KeyID);	  	
	  	crypto.DecryptSessionKey(isEncKey);
		if (isEncKey == null || isEncKey.length() == 0) {
	    	return "";
	    }

		crypto.setCipherAlgorithm(Integer.parseInt(algoID));
    dec_data = crypto.DecryptData(isMessage);
		if (dec_data == null) {
	    	return "";
	    }
		String msg = "";
		try{
			msg = new String(dec_data,"EUC-KR");
			System.out.println(msg);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	    
		
		return msg;
	}
	
	public String hash(String Message){
		String isMessage = "";
		crypto.setCipherAlgorithm(Integer.parseInt(algoID));
		
		isMessage = crypto.Hash(Message.getBytes());
		if (isMessage == null || isMessage.length() == 0) {
	    	return "";
	    }
		return isMessage;
	}

	public JKTFCrypto getCrypto() {
		return crypto;
	}

	public void setCrypto(JKTFCrypto crypto) {
		this.crypto = crypto;
	}

	public String getAlgoID() {
		return algoID;
	}

	public void setAlgoID(String algoID) {
		this.algoID = algoID;
	}

	public String getKeyID() {
		return KeyID;
	}

	public void setKeyID(String keyID) {
		KeyID = keyID;
	}

	public String getEnc_key() {
		return enc_key;
	}

	public void setEnc_key(String enc_key) {
		this.enc_key = enc_key;
	}

	public String getEnc_msg() {
		return enc_msg;
	}

	public void setEnc_msg(String enc_msg) {
		this.enc_msg = enc_msg;
	}
}
