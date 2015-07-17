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
	 * String값의 Null여부를 체크한다.
	 */
	public static boolean isNull(String str) {
		if (str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * String값의 Null여부와 빈값을 체크한다.
	 */
	public static boolean isNullTrim(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Byte 기준으로 string 을 자른다. (한글기준 2byte 적용)
	 */
	public static String substring(String orginal, int start) {
		byte[] byte_body = orginal.getBytes();
		if (byte_body == null)
			return null;
		return substring(byte_body, start, byte_body.length);
	}

	/**
	 * Byte 기준으로 string 을 자른다. (한글기준 2byte 적용)
	 */
	public static String substring(String orginal, int start, int end) {
		byte[] byte_body = orginal.getBytes();
		if (byte_body == null)
			return null;
		return substring(byte_body, start, end);
	}

	/**
	 * Byte Array 데이터를 string 으로 자른다.
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
	 * 스트링을 일정한 길이 이상일때 잘라줌. (왼쪽부터)
	 */
	public static String leftstring(String str, int end) {
		if (str.length() < (end + 1))
			return str;
		else
			return (substring(str, 0, end));
	}

	/**
	 * 스트링을 일정한 길이 이상일때 잘라줌. (오른쪽부터)
	 */
	public static String rightstring(String str, int end) {
		if (str.length() < (end + 1))
			return str;
		else
			return (substring(str, str.length() - end, str.length()));
	}

	/**
	 * str1의 값이 null이거나 공백이면 str2의 값으로 대체한다.<br>
	 * str2의 값도 null이거나 공백이면 공백을 반환한다. 만약 str1이 값이 있다면 str1의 값을 반환한다. <br>
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
	 * FID Parser에서만 사용함 위의 nvl함수에서 trim()만 제외함
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
	 * host명을 가져 온다.
	 */
	public static String getWasHostName() {
		Process proc = null;
		BufferedReader bReader = null;
		String readLine = "";
		String hostName = "";

		try {
			// Host 명을 구한다.
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
	 * 대상 문자열이 숫자인지 아닌지를 판단한다. (오직 숫자로만 이루어 져야함)
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
	 * 날짜를 문자열로 (현재)
	 */
	public static String toDateString(String format) {
		if (format == null) {
			format = "yyyy/MM/dd HH:mm:ss.SSS";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * 날짜를 문자열로 (현재기준 날짜 이전,이후 지정)
	 */
	public static String toDateString(int amount, String format) {
		return toDateString(Calendar.DATE, amount, format);
	}

	/**
	 * 날짜를 문자열로 (현재기준 날짜 이전,이후 지정)
	 */
	public static String toDateString(int field, int amount, String format) {
		if (format == null) {
			format = "yyyy/MM/dd HH:mm:ss.SSS";
		}
		if (amount == 0) {
			return toDateString(format); // 현재일자
		}

		Calendar c = Calendar.getInstance();
		c.add(field, amount); // 날짜데이터(플러스,마이너스에 따라 이전/이후일자)

		return toDateString(c.getTimeInMillis(), format);
	}

	/**
	 * 날짜를 문자열로
	 */
	public static String toDateString(long dt, String format) {
		if (format == null) {
			format = "yyyy/MM/dd HH:mm:ss.SSS";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(dt));
	}

	/**
	 * 날짜를 문자열로
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
	 * 날짜를 문자열로
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
	 * 현재시점을 Long 으로
	 */
	public static String toLongString() {
		return String.valueOf(new Date().getTime());
	}

	/**
	 * 두 날짜 사이의 차이
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
	 * 두 날짜 사이의 차이
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
	 * 한글인지 체크 (한글이 아닌게 하나라도 있으면 false)
	 */
	public static boolean isKorean(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (0xAC00 <= c && c <= 0xD7A3) {
				// korean : 가 ~ ? 범위 체크
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
	 * 파일이름(파일이름_년월일시분.확장자)
	 */
	public static String fileNmAddDate(String fileNm) {
		
		//파일명 생성 : 년월일시분초_i
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
	 * 파일 확장자 가져오기
	 */
	public static String getFileExt(String fileNm) {
		return fileNm.substring(fileNm.lastIndexOf(".")+1, fileNm.length());
	}
	
	/**
	 * 파일 확장자 체크 - 이미지
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
	 * 파일 확장자 체크 - htm,html
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
	 * 입력받은 문자열에 대해 유효성 및 보안체크를 실시합니다.
	 * 
	 * @param checkValue	체크 할 문자열
	 * @param defaultValue	체크 실패시 반환 할 기본 문자열
	 * @return	유효성 및 보안체크가 완료된 문자열을 반환 한다.
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
