package com.wallet.harex.common;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class JString {
	
	public JString() {
		
	}

	public static final String BLANK = "";
	public static final char FS = 0x1C;
	public static final char GS = 0x1D;
	public static final String FS_STR = String.valueOf(FS);
	public static final String GS_STR = String.valueOf(GS);

	public static String getAddedString(String str, String delimeter, int pos) {
		int inx = str.indexOf(delimeter);
		if (inx > 0) {
			return str;
		} else {
			if (str.length() >= pos) {
				String f1 = str.substring(0, pos);
				String f2 = str.substring(pos);
				return f1 + delimeter + f2;
			} else
				return str;
		}
	}

	public static String toStringBytes(String str, int byteCount) {
		if (str == null)
			return null;

		byte[] buff = str.getBytes();
		if (buff.length <= byteCount)
			return str;

		byte[] ret = new byte[buff.length];

		for (int i = 0; i < byteCount; i++)
			ret[i] = buff[i];

		return new String(ret);
	}

	public static boolean isValidPhone(String phone) {
		try {
			String str = phone.replaceAll("-", "").trim();
			
			for (int i = 0; i < str.length(); i++) {
				if (Character.isDigit(str.charAt(i)) == false)
					return false;
			}
			
			if (str.length() < 7)
				return false;
			else
				return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isKorean(String str) {
		return Pattern.matches("[\uac00-\ud79f]+", str);
	}

	public static String getString(String value, int length) {
		String ret = value;
		
		if (value == null)
			value = "";
		
		try {
			
			int count = 0;
			int size = value.length();
			
			for (int i = 0; i < size; i++) {
				char ch = value.charAt(i);
				String str = String.valueOf(ch);
				
				if (isKorean(str))
					++count;
			}

			int sz = length - count * 2;
			
			for (int i = 0; i < sz; i++)
				ret += " ";

			return ret;
			
		} catch (Exception e) {
			return ret;
		}
	}

	public static StringBuilder replace(StringBuilder mesg, String pattern, String token) {
		int inx = mesg.indexOf(pattern);
		
		if (inx < 0)
			return mesg;

		return mesg.replace(inx, inx + pattern.length(), token);
	}

	public static String replaceAll(String mesg, String from, String to) {
		try {
			ArrayList<?> list = JStringTokenizer.getStrings(mesg, from);
			StringBuilder str = new StringBuilder();
			
			for (int i = 0; i < list.size(); i++) {
				if (i == 0)
					str.append(list.get(i));
				else
					str.append(to).append(list.get(i));
			}
			
			return str.toString();
			
		} catch (Exception e) {
			return "";
		}
	}

	public static String trim(String value, int size) {
		byte[] buff = new byte[size];
		byte[] data = value.getBytes();

		for (int i = 0; i < size; i++) {
			buff[i] = data[i];
		}

		return new String(buff);
	}

	public static int toInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String decode(String unicode) throws Exception {
		StringBuffer str = new StringBuffer();

		char ch = 0;
		
		for (int i = unicode.indexOf("\\u"); i > -1; i = unicode.indexOf("\\u")) {
			ch = (char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16);
			str.append(unicode.substring(0, i));
			str.append(String.valueOf(ch));
			unicode = unicode.substring(i + 6);
		}
		
		str.append(unicode);

		return str.toString();
	}

	public static String encode(String unicode) {
		StringBuffer str = new StringBuffer();

		for (int i = 0; i < unicode.length(); i++) {
			
			if (((int) unicode.charAt(i) == 32)) {
				str.append(" ");
				continue;
			}
			
			str.append("\\u");
			str.append(Integer.toHexString((int) unicode.charAt(i)));

		}

		return str.toString();
	}
	
	/**
	 * 정규형 validation 체크 (Email) 
	 * @param type : Email, 핸드폰등의 구분
	 * @param val   : 값
	 * @return boolean 
	 */
	public static boolean checkFormat(String type, String val){
		boolean chk = false;
		String typeChk = "";
		if("email".equals(type)){
			typeChk = "^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z-]+)*$";			
		}
		
		chk = Pattern.matches(typeChk, val);
		
		return chk;
	}
	
	/**
	 * Email 암호화 숨김처리 : 아이디 뒤 3자리 *** 처리 
	 * @param val   : 값
	 * @return String 
	 */
	public static String changeEmailTx(String val){
		String emailStr = "";
		
		emailStr = val;
		
		if (emailStr != null && !emailStr.equals("")) {
			StringBuffer sb = new StringBuffer(emailStr);
		  int idx = sb.indexOf("@");
		  
		  if (idx > 0 && idx >= 3) {
			  emailStr = emailStr.substring(0, (idx-3)) + "***" + emailStr.substring(idx, emailStr.length());
		  }
		}
		
		return emailStr;
	}
	
}
