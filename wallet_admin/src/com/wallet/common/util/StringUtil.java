package com.wallet.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {

	/**
	 * String���� Null���θ� üũ�Ѵ�.
	 */
	public static boolean isNull(String str) {
		if (str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * String���� Null���ο� ���� üũ�Ѵ�.
	 */
	public static boolean isNullTrim(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Byte �������� string �� �ڸ���. (�ѱ۱��� 2byte ����)
	 */
	public static String substring(String orginal, int start) {
		byte[] byte_body = orginal.getBytes();
		if (byte_body == null)
			return null;
		return substring(byte_body, start, byte_body.length);
	}

	/**
	 * Byte �������� string �� �ڸ���. (�ѱ۱��� 2byte ����)
	 */
	public static String substring(String orginal, int start, int end) {
		byte[] byte_body = orginal.getBytes();
		if (byte_body == null)
			return null;
		return substring(byte_body, start, end);
	}

	/**
	 * Byte Array �����͸� string ���� �ڸ���.
	 */
	public static String substring(byte[] byte_body, int start, int end) {
		int size = end - start;
		int length = byte_body.length - start;
		if (length < size)
			size = length;

		// byte[] tmp_body = new byte[end - start];
		byte[] tmp_body = new byte[size];
		System.arraycopy(byte_body, start, tmp_body, 0, tmp_body.length);
		return new String(tmp_body);
	}

	/**
	 * ��Ʈ���� ������ ���� �̻��϶� �߶���. (���ʺ���)
	 */
	public static String leftstring(String str, int end) {
		if (str.length() < (end + 1))
			return str;
		else
			return (substring(str, 0, end));
	}

	/**
	 * ��Ʈ���� ������ ���� �̻��϶� �߶���. (�����ʺ���)
	 */
	public static String rightstring(String str, int end) {
		if (str.length() < (end + 1))
			return str;
		else
			return (substring(str, str.length() - end, str.length()));
	}

	/**
	 * str1�� ���� null�̰ų� �����̸� str2�� ������ ��ü�Ѵ�.<br>
	 * str2�� ���� null�̰ų� �����̸� ������ ��ȯ�Ѵ�. ���� str1�� ���� �ִٸ� str1�� ���� ��ȯ�Ѵ�. <br>
	 */
	public static String nvl(String str1, String str2) {
		String returnValue = "";

		if (str1 == null || str1.equals("")) {
			if (str2 != null && !str2.equals("")) {
				returnValue = str2.trim();
			} else {
				returnValue = "";
			}
		} else {
			returnValue = str1.trim();
		}

		return returnValue;
	}

	/**
	 * FID Parser������ ����� ���� nvl�Լ����� trim()�� ������
	 */
	public static String nvl2(String str1, String str2) {
		String returnValue = "";

		if (str1 == null || str1.equals("")) {
			if (str2 != null && !str2.equals("")) {
				returnValue = str2;
			} else {
				returnValue = "";
			}
		} else {
			returnValue = str1;
		}

		return returnValue;
	}

	/**
	 * host���� ���� �´�.
	 */
	public static String getWasHostName() {
		Process proc = null;
		BufferedReader bReader = null;
		String readLine = "";
		String hostName = "";

		try {
			// Host ���� ���Ѵ�.
			proc = Runtime.getRuntime().exec("hostname");

			bReader = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));

			while ((readLine = bReader.readLine()) != null) {
				hostName = readLine;
			}

			if (bReader != null)
				bReader.close();

			proc.waitFor();
			proc.destroy();

		} catch (Exception e) {
			hostName = "";
		} finally {
			if (proc != null)
				proc = null;
			if (bReader != null)
				bReader = null;
		}

		return hostName;
	}

	/**
	 * ��� ���ڿ��� �������� �ƴ����� �Ǵ��Ѵ�. (���� ���ڷθ� �̷�� ������)
	 */
	public static boolean isNumber(String s) {
		if (s == null || "".equals(s.trim())) {
			return false;
		}

		s = s.replaceAll(",", "");
		s = s.replaceAll(".", "");

		for (int i = 0, len = s.length(); i < len; i++) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}

		return true;
	}

	/**
	 * ��¥�� ���ڿ��� (����)
	 */
	public static String toDateString(String format) {
		if (format == null) {
			format = "yyyy/MM/dd HH:mm:ss.SSS";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * ��¥�� ���ڿ��� (������� ��¥ ����,���� ����)
	 */
	public static String toDateString(int amount, String format) {
		return toDateString(Calendar.DATE, amount, format);
	}

	/**
	 * ��¥�� ���ڿ��� (������� ��¥ ����,���� ����)
	 */
	public static String toDateString(int field, int amount, String format) {
		if (format == null) {
			format = "yyyy/MM/dd HH:mm:ss.SSS";
		}
		if (amount == 0) {
			return toDateString(format); // ��������
		}

		Calendar c = Calendar.getInstance();
		c.add(field, amount); // ��¥������(�÷���,���̳ʽ��� ���� ����/��������)

		return toDateString(c.getTimeInMillis(), format);
	}

	/**
	 * ��¥�� ���ڿ���
	 */
	public static String toDateString(long dt, String format) {
		if (format == null) {
			format = "yyyy/MM/dd HH:mm:ss.SSS";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(dt));
	}

	/**
	 * ��¥�� ���ڿ���
	 */
	public static Date toDateString(String dt, String format) {
		if (format == null) {
			format = "yyyy/MM/dd HH:mm:ss.SSS";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date sDate = null;
		try {
			sDate = sdf.parse(dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sDate;
	}

	/**
	 * ��¥�� ���ڿ���
	 */
	public static String toDateString(String dt, String fromformat,
			String toformat) {
		if (fromformat == null)
			fromformat = "yyyyMMdd";
		if (toformat == null)
			toformat = "yyyy/MM/dd";
		SimpleDateFormat fromsdf = new SimpleDateFormat(fromformat);
		SimpleDateFormat tosdf = new SimpleDateFormat(toformat);
		Date sDate = null;
		try {
			sDate = fromsdf.parse(dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tosdf.format(sDate);
	}

	/**
	 * ��������� Long ����
	 */
	public static String toLongString() {
		return String.valueOf(new Date().getTime());
	}

	/**
	 * �� ��¥ ������ ����
	 */
	public static long day2Day(String startDate, String endDate, String format)
			throws Exception {
		if (format == null) {
			format = "yyyy/MM/dd HH:mm:ss.SSS";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date sDate;
		Date eDate;
		long day2day = 0;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
			day2day = (eDate.getTime() - sDate.getTime())
					/ (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			throw new Exception("wrong format string");
		}
		return day2day;
	}

	/**
	 * �� ��¥ ������ ����
	 */
	public static long day2Day(long startDate, long endDate) throws Exception {
		Date sDate;
		Date eDate;
		long day2day = 0;
		try {
			sDate = new Date(startDate);
			eDate = new Date(endDate);
			day2day = (eDate.getTime() - sDate.getTime())
					/ (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			throw new Exception("wrong format string");
		}
		return day2day;
	}

	public static String priceComma(long price) {
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setMinimumIntegerDigits(3);
		return nf.format(price);
	}

	/**
	 * �ѱ����� üũ (�ѱ��� �ƴѰ� �ϳ��� ������ false)
	 */
	public static boolean isKorean(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (0xAC00 <= c && c <= 0xD7A3) {
				// korean : �� ~ ? ���� üũ
				continue;
			} else if (0x3131 <= c && c <= 0x318E) {
				// korean : one character (consonant or vowel)
				return false;
			} else if ((0x61 <= c && c <= 0x7A) || (0x41 <= c && c <= 0x5A)) {
				// english
				return false;
			} else if (0x30 <= c && c <= 0x39) {
				// integer/decimal
				return false;
			} else if (c <= 0x20) {
				// space
				return false;
			} else {
				// unknown
				return false;
			}
		}
		return true;
	}
	
	/**
	 * �����̸�(�����̸�_����Ͻú�.Ȯ����)
	 */
	public static String fileNmAddDate(String fileNm) {
		
		//���ϸ� ���� : ����Ͻú���_i
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
	  String date = formatter.format(today);

	  String name = "";
	  String ext = "";
	  
	  
	  int index = fileNm.indexOf(".");
	  name = fileNm.substring(0, index);
	  ext = fileNm.substring(index+1);

		return name + "_" + date + "." + ext ;
		
	}
	
	/**
	 * ���� Ȯ���� ��������
	 */
	public static String getFileExt(String fileNm) {
		return fileNm.substring(fileNm.lastIndexOf(".")+1, fileNm.length());
	}
	
	/**
	 * ���� Ȯ���� üũ - �̹���
	 */
	public static boolean getFileImageExtCk(String fileNm) {
		String ext = getFileExt(fileNm);
		
		if (ext.equals("") || ext == null) {
			return false;
		} else {
			 
			 ext = ext.toUpperCase();
			 
		   if (ext.equals("JPG")  || ext.equals("JPEG") || ext.equals("GIF") || ext.equals("PNG") || ext.equals("BMP") ) {
		  	 return true;
		   } else {
		   		return false;
		   }

		}
	}
	
	/**
	 * ���� Ȯ���� üũ - htm,html
	 */
	public static boolean getFileHtmlExtCk(String fileNm) {
		
		String ext = getFileExt(fileNm);
		
		if (ext.equals("") || ext == null) {
			return false;
		} else {
			
			 ext = ext.toUpperCase();

		   if (ext.equals("HTML")  || ext.equals("HTM") ) {
		  	 return true;
		   } else {
		   		return false;
		   }

		}
	}
	
	/**
	 * �Է¹��� ���ڿ��� ���� ��ȿ�� �� ����üũ�� �ǽ��մϴ�.
	 * 
	 * @param checkValue	üũ �� ���ڿ�
	 * @param defaultValue	üũ ���н� ��ȯ �� �⺻ ���ڿ�
	 * @return	��ȿ�� �� ����üũ�� �Ϸ�� ���ڿ��� ��ȯ �Ѵ�.
	 */
	public static String checkStr(String checkValue, String defaultValue) {
		String result = "";

	   if(checkValue != null && checkValue.length() > 0) {

	  	//XSS
	  	checkValue = checkValue.replaceAll("\\^", "");
	  	checkValue = checkValue.replaceAll("\\{", "");
  	  checkValue = checkValue.replaceAll("\\}", "");
  	  checkValue = checkValue.replaceAll("\\(", "");
  	  checkValue = checkValue.replaceAll("\\)", "");
  	  checkValue = checkValue.replaceAll("\\[", "");
  	  checkValue = checkValue.replaceAll("\\]", "");
  	  checkValue = checkValue.replaceAll("\\<", "");
  	  checkValue = checkValue.replaceAll("\\>", "");
  	  checkValue = checkValue.replaceAll("\"", "");
			checkValue = checkValue.replaceAll("\'", "");
			
	  	result = checkValue.trim() ;
	  	
	   } else {
	  	 result = defaultValue ;
	   }
	   
		return result;
	}
	
}
