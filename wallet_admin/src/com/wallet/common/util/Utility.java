package com.wallet.common.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class Utility {

	public static final Logger log = Logger.getLogger(Utility.class);
	
	private Utility() {}
	
	
	/**
	 * Multipart-Request 사용시 request에서 파라미터를 받아오는 메서드
	 * 한글처리를 지원한다.
	 * @param request
	 * @param paramName
	 * @return
	 * @throws Exception
	 */
	public static String getParam(MultipartHttpServletRequest request, String paramName)throws Exception{
		String paramData = request.getParameter(paramName) == null ? null : new String(request.getParameter(paramName).getBytes("8859_1"), "UTF-8");
		return setChckUrl(paramData);
	}
	
	public static String getParam(HttpServletRequest request, String paramName)throws Exception{
		String paramData = request.getParameter(paramName) == null ? null : new String(request.getParameter(paramName).getBytes("8859_1"), "UTF-8");
		//String paramData = request.getParameter(paramName) == null ? null : request.getParameter(paramName);
		return setChckUrl(paramData);
	}
	
	/**
	 * request에서 파라미터를 받아오는 메서드
	 * 값이 null일 경우 입력받은 값을 반환한다.
	 * @param request
	 * @param paramName
	 * @param rtnVal
	 * @return
	 * @throws Exception
	 */
	public static String getParam(HttpServletRequest request, String paramName, String rtnVal)throws Exception{
		String paramData = request.getParameter(paramName) == null ? rtnVal : new String(request.getParameter(paramName).getBytes("8859_1"), "UTF-8");
		return setChckUrl(paramData);
	}
	
	public static String getParamCk(HttpServletRequest request, String paramName, String rtnVal)throws Exception{
		String paramData = (request.getParameter(paramName) == null) || (request.getParameter(paramName).equals("")) ? rtnVal : new String(request.getParameter(paramName).getBytes("8859_1"), "UTF-8");
		return setChckUrl(paramData);
	}
	
	public static String setChckUrl(String paramData){
		int InjectCnt = 0;
		if (paramData != null){ 
			String temp_paramData = paramData.toUpperCase();
			String data_val [] = {"DROP", "CREATE", "DECLARE", "@A", "IS_SRVROLEMEMBER", "IS_MEMBER", "DB_NAME()", "CHAR(", "ISNULL", "VARCHAR",  "XP_CMD", "XP_STARTMAIL", "XP_SENDMAIL", "SP_MAKEWEBTASK", "SP_MAKEWEBTASK", "XP_REGREAD", "XP_REGDELETEKEY", "XP_REGENUMVALUES", "XP_DIRTREE", "SP_ADDUSER", "DELETE", "UPDATE" , "SYSOBJECTS", "SCRIPT", "ALERT(", "##", "--" , "%20%61%6E%64%20%31%3D%31", "%201=1%20"};
			for(InjectCnt = 0; InjectCnt < data_val.length; InjectCnt++){
				if(temp_paramData.indexOf(data_val[InjectCnt]) > -1){
					paramData = paramData.substring(0, temp_paramData.indexOf(data_val[InjectCnt])) + paramData.substring((temp_paramData.indexOf(data_val[InjectCnt])+data_val[InjectCnt].length()), temp_paramData.length());
					System.out.println("* 사용자 입력값 필터링 되었습니다. : 입력 값이 필터링 되었습니다. (전체값 : "+paramData+"),(필터값 : "+data_val[InjectCnt]+"),(출력된 값 : "+paramData+")");
					temp_paramData = paramData;
				}
			}
		}
		return paramData;
	}

	
	/**
	 * 객체가 가지고 있는 값이 공백인지 아닌지 판별하여 true, false를 리턴
	 */
	public static boolean isEmpty(Object obj){         
		if( obj instanceof String ) return obj==null || "".equals(obj.toString().trim());         
		else if( obj instanceof List ) return obj==null || ((List)obj).isEmpty();         
		else if( obj instanceof Map ) return obj==null || ((Map)obj).isEmpty();         
		else if( obj instanceof Object[] ) return obj==null || Array.getLength(obj)==0;         
		else return obj==null;     
	}
	
	public static boolean isNotEmpty(String s){
		return !isEmpty(s);
	}
	
	
	/**
	 * r_trim
	 */
	public static String r_trim(String str)	{
		int i;
		for(i=str.length() -1; i>=0; i--)
		{
			if(str.charAt(i) != ' ')
				break;
		}
		return str.substring(0, i+1);
	}

	
	
	/**
	 * l_trim
	 */
	public static String l_trim(String str)	{
		int i;
		for(i=0; i<str.length(); i++)
		{
			if(str.charAt(i) != ' ')
				break;
		}
		return str.substring(i);
	}

	
	
	/**
	 * 이메일체크
	 * @param s
	 * @return true, false
	 */
	public static boolean validEmail(String s){
		int size = s.length();
		String SpecialTxt[]={"!","#","$","%","^","&","*","(",")","<",">"};

		if(size > 50)
			return false;

		int i = s.indexOf('@');
		if(i < 0)
			return false;
		i = s.indexOf('.');
		if(i < 0)
			return false;
		for(int j =0; j<SpecialTxt.length; j++){
			i = s.indexOf(SpecialTxt[j]);
			if(i >= 0)
			return false;
		}
		return true;
	}

	
	/**
	 * String이 null이나 공백인 경우 지정한 문자열로 변경한다.
	 */  
	public static String null2String(String str, String def) {
		if (str == null || str.equals("") || str.equals("null"))
			str = def;
		return str;
	}
	
	
	
	public static String printSelectBox(Map options, String key){
		StringBuffer HTML = new StringBuffer();
		HTML.append( "");
		return HTML.toString();
	}

	public static String setDbInjection(String paramData){
		int InjectCnt = 0;
		if (paramData != null){ 
			String temp_paramData = paramData.toUpperCase();

			String data_val [] = {"<", ">", "+or", "%", "'", "\""};
			for(InjectCnt = 0; InjectCnt < data_val.length; InjectCnt++) {
				if(temp_paramData.indexOf(data_val[InjectCnt]) > -1) {
					paramData = paramData.substring(0, temp_paramData.indexOf(data_val[InjectCnt])) + paramData.substring((temp_paramData.indexOf(data_val[InjectCnt])+data_val[InjectCnt].length()), temp_paramData.length());
					temp_paramData = paramData;
				}
				if(temp_paramData.toUpperCase().indexOf(data_val[InjectCnt]) > -1){
					paramData = setDbInjection(temp_paramData);
				}
			}
		}
		return paramData;
	}
	
	
	public static String inputHtmlTag(String s) {
		String rtnString = s.replaceAll("\\\"", "&quot;");
		return rtnString;
	}
	
	
	/**
	 * yyyy.MM.dd HH:mm:ss 포맷을 받아 날짜만 분리시키고 구분자를 변환시켜준다.
	 * @param date
	 * @param gubun
	 * @return
	 */
	public static String formatOnlyDate(String date, char gubun){
		String[] tempDate = date.split(" ");
		date = tempDate[0];
		date = date.replace('.', gubun);
		return date;
	}
	
	/**
	 * yyyy.MM.dd HH:mm:ss 포맷을 받아 날짜만 분리시키고 반환한다.
	 * @param date
	 * @return
	 */
	public static String formatDateTime(String date){
		String[] dateTime = date.split(" ");
		
		return dateTime[0] + " (" + dateTime[1] + ")";
	}
	
	/**
	 * 파일명을 받아 확장자를 기준으로 해당 파일 종류에 해당하는 아이콘 URL을 리턴한다.
	 * @param fileName
	 * @return
	 */
	public static String getDocIcon(String fileName){
		if(fileName.length() == 0){
			return "";
		}
		
		String[] fileArray = fileName.split("\\.");
		String extention = fileArray[ fileArray.length - 1 ];
		return getDocIconOfExtention(extention);
	}
	
	/**
	 * 확장자를 받아 해당 파일 종류에 해당하는 아이콘 URL을 리턴한다.
	 * @param extention
	 * @return
	 */
	public static String getDocIconOfExtention(String extention){
		extention = extention.toUpperCase();
		if(extention.equals("DOC")){
			return "ico_doc.gif";
		}else if(extention.equals("HWP")){
			return "ico_hwp.gif";
		}else if(extention.equals("PDF")){
			return "ico_pdf.gif";
		}else if(extention.equals("PPT")){
			return "ico_ppt.gif";
		}else if(extention.equals("XLS")){
			return "ico_xls.gif";
		}else if(extention.equals("ZIP")){
			return "ico_zip.gif";
		}else if(extention.equals("ACC")){
			return "ico_acc.gif";
		}else{
			return "ico_txt.gif";
		}
	}
	
	
	/**
	 * 현재 날짜 시간을 yyyy/MM/dd HH:mm:ss 포맷으로 반환한다.
	 * @return
	 */
	public static String getNowDateTime(){
		Calendar calendar = Calendar.getInstance();
		return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(calendar.getTime()));
	}
	
	
	/**
	 * 사용자가 지정한 형식의 현재 날짜 시간을 반환한다.
	 * @param format
	 * @return
	 */
	public static String getNowDateTime(String format){
		Calendar calendar = Calendar.getInstance();
		return (new SimpleDateFormat(format).format(calendar.getTime()));
	}
	
	
	/**
	 * yyyy/MM/dd HH:mm:ss 형식에서 날짜만을 분리시켜 반환한다.
	 * @param date
	 * @return
	 */
	public static String parseDate(String date){
		return date.split(" ")[0];
	}
	
	
	/**
	 * 게시판 본문내용에 개행문자가 있을경우 해당 문자를 <br>태그로 변환한다.
	 * @param content
	 * @return
	 */
	public static String parseContent(String content){
		content = content.replaceAll("\r\n", "<br>");
		content = content.replaceAll(" ", " ");
		content = content.replaceAll("  ", "&nbsp;&nbsp;");
		return content;
	}
	

	public static String parseContentEdit(String content){
		content = content.replaceAll("<br>", "\r\n");
		content = content.replaceAll("\\\"", "&quot;");
		return content;
	}


	/**
	 * 입력받은 날짜를 yyyy년 MM월 형식으로 리턴한다.
	 * @param date
	 * @return
	 */
	public static String formatYearMonth(String date){
		if(date == null) return "Wrong Date";
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월";
	}
	
	
	/**
	 * 시작일 종료일이 있는지 상시인지를 구분하여 해당 값을 리턴한다.
	 * @param sangsiYN
	 * @param s_date
	 * @param e_date
	 * @return
	 */
	public static String formatSDate2Edate(String sangsiYN, Object s_date, Object e_date){
		if(sangsiYN.equals("Y")) return "상시";
		return s_date + " ~ " + e_date.toString().substring(5);
	}
	
	
	/**
	 * long형 숫자에 컴마를 찍기위한 메서드..
	 */
	public static String formatComma(long number){
		String numString = String.valueOf(number);
		return formatComma(numString);
	}
	
	/**
	 * String형 숫자에 컴마를 찍기위한 메서드..
	 */
	public static String formatComma(String numString){
		
		if(numString == null || numString.length() == 0)
			return "0";
		
		String _temp = "";
		int count = 0;
		for(int i = numString.length() - 1; i >= 0; i--){
			char imsi = numString.charAt(i);
			_temp += imsi;
			
			count++;
			
			if(count%3 == 0 && count != numString.length()){
				_temp += ",";
			}
		}
		numString = "";
		for(int i = _temp.length() - 1; i >= 0; i--){
			char imsi = _temp.charAt(i);
			numString += imsi;
		}
		return numString;
	}
	
	
	/**
	 * int형 숫자에 컴마를 찍기위한 메서드..
	 */
	public static String formatComma(int number){
		String numString = String.valueOf(number);
		return formatComma(numString);
	}
	
	
	/**
	 * null일 경우 공백처리 해주는 메서드
	 * null이 아니면 해당 값을 그대로 반환한다.
	 * @param args
	 */
	public static String isNull(Object o){
		return o == null ? "" : o.toString();
	}
	
	
	/**
	 * 날짜 기간을 표현해주는 메서드..
	 * @param s
	 * @param e
	 * @return
	 */
	public static String formatSEDate(Object s, Object e){
		return s.toString().replaceAll("-", ".") + "~" + e.toString().replaceAll("-", ".");
	}


	/**
	 * 한글화 처리
	 * @param str
	 * @return
	 */
	public static String toUnicode(String str){
		try{
			byte[] b = str.getBytes("ISO-8859-1");
			return new String(b);
		} catch(java.io.UnsupportedEncodingException uee) {
			uee.printStackTrace();
			return null;
		}
	}
	

	public static String toLatin(String str){
		try{
			byte[] b = str.getBytes();
			return new String(b, "ISO-8859-1");
		} catch(java.io.UnsupportedEncodingException uee) {
			uee.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 쿠키생성 및 호출
	 * @param response
	 * @param name
	 * @param value
	 * @throws Exception
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) throws Exception {
		value = java.net.URLEncoder.encode(value.toString(),"UTF-8");
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain("blogmusic.show.co.kr"); 
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	
	public static String getCookie(HttpServletRequest request, String CookieName) throws Exception {
		Cookie [] cookies = request.getCookies();
		if(cookies==null) return null;
		String value = "";
		for(int i=0;i<cookies.length;i++) {
			if(CookieName.equals(cookies[i].getName())) {
				value = java.net.URLDecoder.decode(cookies[i].getValue(),"UTF-8");
				break;
			}
		}
		return value;
	}

	
	public static String decode(String s, String enc) throws UnsupportedEncodingException{
	
		boolean needToChange = false;
		StringBuffer sb = new StringBuffer();
		int numChars = s.length();
		int i = 0;
	
		if (enc.length() == 0) {
		    throw new UnsupportedEncodingException ("URLDecoder: empty string enc parameter");
		}
	
		while (i < numChars) {
	            char c = s.charAt(i);
	            switch (c) {
		    case '+':
			sb.append(' ');
			i++;
			needToChange = true;
			break;
		    case '%':
			try {
				byte[] bytes = new byte[(numChars-i)/3];
			    int pos = 0;
			    
			    while ( ((i+2) < numChars) && 
				    (c=='%')) {
				bytes[pos++] = 
				    (byte)Integer.parseInt(s.substring(i+1,i+3),16);
				i+= 3;
				if (i < numChars)
				    c = s.charAt(i);
			    }
	
			    // A trailing, incomplete byte encoding such as
			    // "%x" will cause an exception to be thrown
	
			    if ((i < numChars) && (c=='%'))
				throw new IllegalArgumentException(
			         "URLDecoder: Incomplete trailing escape (%) pattern");
			    
			    sb.append(new String(bytes, 0, pos, enc));
			} catch (NumberFormatException e) {
			    throw new IllegalArgumentException(
	                    "URLDecoder: Illegal hex characters in escape (%) pattern - " 
			    + e.getMessage());
			}
			needToChange = true;
			break;
		    default: 
			sb.append(c); 
			i++;
			break; 
	            }
	        }
        return (needToChange? sb.toString() : s);
    }
	
	
   /**
    * 원하는 만큼 str을 잘라 리턴함
    * @param orig
    * @param length
    * @return
    */
    public static String getShortString(String orig, int length) {
        byte[] byteString = orig.getBytes();

        if (byteString.length <= length) {
            return orig;
        } else {
            int minusByteCount = 0;
            for (int i = 0; i < length; i++) {
                minusByteCount += (byteString[i] < 0) ? 1 : 0;
            }

            if (minusByteCount % 2 != 0) {
                length--;
            }

            return new String(byteString, 0, length) + "...";
        }
    }



    /**
	 * 파라미터값 MAP 셋팅
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		// 파라미터 이름 
        Enumeration<String> paramNames = request.getParameterNames(); 

        // 저장할 맵 
        Map<String, String> paramMap = new HashMap<String, String>();

       // 맵에 저장 
       while (paramNames.hasMoreElements()) { 
           String name = paramNames.nextElement().toString(); 
           String value = request.getParameter(name) != null ? request.getParameter(name) : "";
           paramMap.put(name, value);
       } 

       // 결과 반환 
       return paramMap;
	}
	
	
	public static String getHangle(String value){
		String hangle = "";
		try {
			hangle = new String(value.getBytes("8859_1"),"KSC5601");
		} catch (Exception e) {
			hangle = "";
			// TODO: handle exception
		}
		return setChckUrl(hangle);
	}

	
	public static String setReplaceWrite(String contents) {
		// TODO Auto-generated method stub
		contents = contents.replaceAll("'","''");
		//contents = contents.replaceAll("--","");
		return contents;
	}

	
	public static String htmlTagUse( String s ) {
		s = s.replaceAll( "&lt;" , "<" );
		s = s.replaceAll( "&gt;", ">" );
		return s;
	}
	
	
	
	/** 
	 * 오늘날짜를 format 의 형식대로 리턴한다.
	 * @param format YYYYMMDD
	 * @return
	 * */
	public static String getToday(String format) {
		StringBuffer dateResult = new StringBuffer();
		try {
			Date date = new Date();
			SimpleDateFormat simpleDate = new SimpleDateFormat(format);
	
			simpleDate.format(date, dateResult, new FieldPosition(1));
		} catch(Exception e) {
			log.error(e);
		}
		return dateResult.toString();
	}

	/** 
	 * 날짜를 입력하면 구분자(/) 을 붙여서 리턴한다.
	 * @param date
	 * @return
	 * */
	public static String getDateFormat(String date) {
		if(date==null || date.equals("") || date.equals("00000000")) return "";
		else return getDateFormat(date, "/");
	}
	
	/** 
	 * 8자리의 날짜값을 token 값에 따라 날짜포맷으로 리턴한다.
	 * data: 20080101   token : "-" 
	 * result: 2008-01-01
	 * @param data
	 * @return
	 * */
	public static String getDateFormat(String date, String token) {
		StringBuffer ret = new StringBuffer();
		try {
			date = date.trim();
			
			if (date != null && date.length() == 8) { 
				ret.append(date.substring(0, 4)).append(token).append(
						date.substring(4, 6)).append(token).append(
						date.substring(6));
			} else {
				ret.append("-");
			}
		} catch (Exception e) {
			log.error(e);
		}
		return ret.toString();
	}
	
	/** 
	 * 6자리의 시간값(150801)을 시간 포맷(15:08:01)으로 리턴한다.
	 * 시간이 6자리보다 짧으면 입력한 시간값(strTime)을 그대로 리턴한다.
	 * @param strTime
	 * @return
	 * */
	public static String getTimeFormat(String strTime, String token) {
		StringBuilder strResult = new StringBuilder();
		try {
			if (strTime != null && strTime.trim().length() >= 6) {
				strTime = strTime.trim();
				strResult.append(strTime.substring(0, 2));
				strResult.append(token);
				strResult.append(strTime.substring(2, 4));
				strResult.append(token);
				strResult.append(strTime.substring(4, 6));
			} else {
				strResult.append(strTime);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return strResult.toString();
	}

	/** 
	 * 입력한 날짜에 달수을 더한 날짜를 리턴한다.
	 * @param date
	 * @param month
	 * @return
	 * */
	public static Date addDateByMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.MONTH, month);
		} catch(Exception e) {
			log.error(e);
		}
		return cal.getTime();
	}

	/** 
	 * 입력한 날짜에 일수를 더한 날짜를 리턴한다.
	 * @param date
	 * @param day
	 * @return
	 * */
	public static Date addDateByDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_YEAR, day);
		} catch(Exception e) {
			log.error(e);
		}
		return cal.getTime();
	}
	
	/** 
	 * Date 타입의 date 를 기본포맷인 yyyy/MM/dd로 
	 * 포맷팅하여 리턴한다.
	 * @param date
	 * @return
	 * */
	public static String getStringFromDate(Date date) {
		return getStringFromDate(date, "yyyy/MM/dd");
	}
	
	
	/** <PRE>
	 * Date  타입의 date 를 포맷문자열(format) 형식의 문자열로 리턴한다.
	 * @param date
	 * @param format
	 * @return
	 * </PRE> */
	public static String getStringFromDate(Date date, String format) {
		StringBuffer dateResult = new StringBuffer();
		SimpleDateFormat simpleDate = new SimpleDateFormat(format);
		simpleDate.format(date, dateResult, new FieldPosition(1));
		return dateResult.toString();
	}
	
	
	/**
	 * 문자열이 숫자로 구성되어 있는지 확인
	 */
	public static boolean isNumber(String str){
		for(int i=0; i<str.length(); i++){
			if(isNumber(str.charAt(i))==false) return false;
		}
		return true;
	}
	public static boolean isNumber(char c){
		if(c>='0' && c <='9') return true;
		else return false;
	}


	//map에 담긴 value를 String 형태로 변환하여 반환
	public static void DataTypeToString(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		Set<String> st = map.keySet();
		Iterator<String> it = st.iterator();
		while(it.hasNext()){
			String key = it.next();
			map.put(key, String.valueOf(map.get(key)));
		}
	}
}

