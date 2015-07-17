package com.wallet.membership.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * CLASS FUNCTION : 
 * 
 * <PRE>
 * <B>HISTORY</B>
 * 		Park Dong-su,1.0,2004. 10. 27. - First
 * </PRE>
 * 
 * @author Park Dong-su
 * @version 1.0, 2004. 10. 27.
 * @see NONE
 */
public class DateTime {
	/**
	 * Don't let anyone instantiate this class
	 */
	private DateTime() {
	}

	/**
	* get String which formmatted formatter that is given by user.
	* @param date java.util.Date Class instance
	* @param format String representation of the date format. For example, "yyyy-MM-dd"
	*/
	public static String format(Date date, String format) {
		SimpleDateFormat sdf = null;
		try{
			sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}catch (Exception e) {
			// TODO: handle exception
			return "";
		} 				
	}

	/**
	 * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
	 *
	 * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with  your pattern.
	 */
	public static String format(String format) {
		try{
			return format(new java.util.Date(), format);
		}catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		
	}
	
	/**
	 * check date string validation with an user defined format.
	 * @param s date string you want to check.
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return date java.util.Date
	 */
	public static java.util.Date parse(String s, String format)
		throws java.text.ParseException {
		if (s == null)
			throw new java.text.ParseException(
				"date string to check is null",
				0);
		if (format == null)
			throw new java.text.ParseException(
				"format string to check date is null",
				0);

		java.text.SimpleDateFormat formatter =
			new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = null;

		try {
			date = formatter.parse(s);
		} catch (java.text.ParseException e) {

			throw new java.text.ParseException(
				" wrong date:\"" + s + "\" with format \"" + format + "\"",
				0);
		}

		if (!format(date, format).equals(s))
			throw new java.text.ParseException(
				"Out of bound date:\""
					+ s
					+ "\" with format \""
					+ format
					+ "\"",
				0);
		return date;
	}

	/**
	 * check date string validation with an user defined format.
	 * @param s date string you want to check.
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return boolean true ??? ?????ë§??, ì¡´ì???? ???????
	 *                 false ??? ?????ë§?? ??±°?? ì¡´ì???? ??? ???????
	 */
	public static boolean isValid(String s, String format) {
		java.util.Date date = null;
		try {
			date = parse(s, format);
		} catch (java.text.ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 *
	 * ?¸ì?ë¡?ì£¼ì?ì§?java.util.Date???¼ì? ?«ì?ë¡?ë¦¬í????.
	 *
	 * @param java.util.Date  
	 * @return int 
	 */
	public static int getDay(Date date) {
		return getNumber(date, "dd");
	}
	
	
	/**
	 * ?¤ë???ëª???¸ì? ?«ì?ë¡?ë¦¬í????.
	 * @return int 
	 */
	public static int getDay(){
		return getNumber(new java.util.Date(), "dd");
	}

	/**
	 *
	 * ?¸ì?ë¡?ì£¼ì?ì§?java.util.Date????? ?«ì?ë¡?ë¦¬í????.
	 *
	 * @param java.util.Date  
	 * @return int 
	 */
	public static int getYear(Date date) {
		return getNumber(date, "yyyy");
	}
	
	/**
	 * ?¤ë???ëª?????ì§?ë¦¬í????.
	 * @return int
	 */
	public static int getYear(){
		return getNumber(new java.util.Date(), "yyyy");
	}

	/**
	 *
	 * ?¸ì?ë¡?ì£¼ì?ì§?java.util.Date????? ?«ì?ë¡?ë¦¬í????.
	 *
	 * @param java.util.Date  
	 * @return int 
	 */
	public static int getMonth(Date date) {
		return getNumber(date, "MM");
	}
	
	/**
	 * ?¤ë???ëª???¸ì? ë¦¬í????.
	 * @return int
	 */
	public static int getMonth(){
		return getNumber(new java.util.Date(), "MM");
	}
	
	/**
	 * ?¸ì?ë¡?ì£¼ì?ì§?java.util.Date ë¥??«ì?ë¡?parsing ???.
	 * 
	 * @param date
	 * @param format ?«ì?ë¡?parsing ??? ?«ì??????format?´ì??????("yyyyMMdd")
	 * @return int 
	 */
	public static int getNumber(Date date, String format) {
		String dateString = format(date, format);
		return Integer.parseInt(dateString);
	}
	
	
	/**
	 * ?¤ë???????????ë¡??«ì?ë¡?ë¦¬í????.(20030408)
	 * @return int
	 */
	public static int getNumber(){
		return Integer.parseInt(format("yyyyMMdd"));
	}
	
	
	/**
	 * ?¤ë???????¼ì?ë¶??ê¹?? ?«ì?ë¡?ë¦¬í????.(20030408160912)
	 * @return
	 */
	public static double getLongNumber(){
		return Double.parseDouble(format("yyyyMMddHHmmss"));
	}

	/**
	 * return days between two date strings with user defined format.
	 * @param s date string you want to check.
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int ??? ?????ë§??, ì¡´ì???? ????????????ë¦¬í?
	 *           ???????ª» ???ê±°ë? ì¡´ì???? ??? ???: java.text.ParseException ë°??
	 *          0: ?¼ì???(java.util.Calendar.SUNDAY ??ë¹??)
	 *          1: ?????(java.util.Calendar.MONDAY ??ë¹??)
	 *          2: ?????(java.util.Calendar.TUESDAY ??ë¹??)
	 *          3: ?????(java.util.Calendar.WENDESDAY ??ë¹??)
	 *          4: ëª©ì???(java.util.Calendar.THURSDAY ??ë¹??)
	 *          5: ê¸????(java.util.Calendar.FRIDAY ??ë¹??)
	 *          6: ?????(java.util.Calendar.SATURDAY ??ë¹??)
	 * ?? String s = "2000-05-29";
	 *  int dayOfWeek = whichDay(s, "yyyy-MM-dd");
	 *  if (dayOfWeek == java.util.Calendar.MONDAY)
	 *      System.out.println(" ????? " + dayOfWeek);
	 *  if (dayOfWeek == java.util.Calendar.TUESDAY)
	 *      System.out.println(" ????? " + dayOfWeek);
	 */
	public static int whichDay(String s, String format)
		throws java.text.ParseException {
		java.text.SimpleDateFormat formatter =
			new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = parse(s, format);

		java.util.Calendar calendar = formatter.getCalendar();
		calendar.setTime(date);
		return calendar.get(java.util.Calendar.DAY_OF_WEEK);
	}

	/**
	 * ?´ë??¼ì? ?????ë¬´ì? ????¸ì? ???ë³¸ë?.
	 * ë¦¬í????? ?? ?? ëª?....?¼ë? ???.
	 * @param java.util.Date
	 * @return String
	 */
	public static String getDayOfTheWeek(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("E", Locale.KOREA);
		return sdf.format(date);
	}

	/**
	 * ?¤ë???ë¬´ì? ????¸ì? ???ë³¸ë?.
	 * ë¦¬í????? ?? ?? ëª?....?¼ë? ???.
	 * @param java.util.Date
	 * @return String
	 */
	public static String getTodayOfTheWeek() {
		return getDayOfTheWeek(new Date());
	}

	/**
	 * return add day to date strings with user defined format.
	 * @param String date string
	 * @param int ??? ?¼ì?
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int ??? ?????ë§??, ì¡´ì???? ????????¼ì? ???ê¸?
	 *           ???????ª» ???ê±°ë? ì¡´ì???? ??? ???: java.text.ParseException ë°??
	 */
	public static String addDays(String s, int day, String format)
		throws java.text.ParseException {

		java.util.Date date = parse(s, format);

		int yyyy = Integer.parseInt(format(date, "yyyy"));
		int MM = Integer.parseInt(format(date, "MM"));
		int dd = Integer.parseInt(format(date, "dd"));
		Calendar c = new GregorianCalendar(yyyy, MM - 1, dd + day);
		return format(c.getTime(), format);
	}

	/**
	 * return add month to date strings with user defined format.
	 * @param String date string
	 * @param int ??? ???
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int ??? ?????ë§??, ì¡´ì???? ?????????? ???ê¸?
	 *           ???????ª» ???ê±°ë? ì¡´ì???? ??? ???: java.text.ParseException ë°??
	 */
	public static String addMonths(String s, int addMonth, String format)
		throws java.text.ParseException {
		String month = "";
		try {
			java.util.Date date = parse(s, format);
	
			int yyyy = Integer.parseInt(format(date, "yyyy"));
			int MM = Integer.parseInt(format(date, "MM"));
			int dd = Integer.parseInt(format(date, "dd"));
			Calendar c = new GregorianCalendar(yyyy, MM - 1 + addMonth, dd);
			month = format(c.getTime(), format);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return month;
	}
	
	/**
	 * return add month to date strings with user defined format.
	 * @param String date string
	 * @param int ??? ???
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int ??? ?????ë§??, ì¡´ì???? ?????????? ???ê¸?
	 *           ???????ª» ???ê±°ë? ì¡´ì???? ??? ???: java.text.ParseException ë°??
	 */
	public static String addYears(String s, int year, String format)
		throws java.text.ParseException {

		java.util.Date date = parse(s, format);

		int yyyy = Integer.parseInt(format(date, "yyyy"));
		int MM = Integer.parseInt(format(date, "MM"));
		int dd = Integer.parseInt(format(date, "dd"));
		Calendar c = new GregorianCalendar(yyyy + year, MM - 1, dd);
		return format(c.getTime(), format);
	}

	/**
	 * ???ì§?? ì°¨ì?ë¥?ê³??
	 */
	public static long getDifferenceDate(Date from, Date to) {
		return from.getTime() - to.getTime();
	}

	/**
	 * return days between two date strings with user defined format.
	 * @param String from date string
	 * @param String to date string
	 * @return int ??? ?????ë§??, ì¡´ì???? ???????2ê°??¼ì? ?¬ì????¼ì? ë¦¬í?
	 *           ???????ª» ???ê±°ë? ì¡´ì???? ??? ???: java.text.ParseException ë°??
	 */
	public static int daysBetween(String from, String to, String format)
		throws java.text.ParseException {

		java.util.Date d1 = parse(from, format);
		java.util.Date d2 = parse(to, format);

		long duration = getDifferenceDate(d1, d2);

		return (int) (duration / (1000 * 60 * 60 * 24));
		// seconds in 1 day
	}

	/**
	 * return age between two date strings with user defined format.
	 * @param String from date string
	 * @param String to date string
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int ??? ?????ë§??, ì¡´ì???? ???????2ê°??¼ì? ?¬ì?????? ë¦¬í?
	 *           ???????ª» ???ê±°ë? ì¡´ì???? ??? ???: java.text.ParseException ë°??
	 */
	public static int ageBetween(String from, String to, String format)
		throws java.text.ParseException {
		return (int) (daysBetween(from, to, format) / 365);
	}

	public static int monthsBetween(String from, String to, String format)
		throws java.text.ParseException {
		return (int) (daysBetween(from, to, format) / 30);
	}

	/**
	* @return formatted string representation of current day with  "yyyyMMdd".
	*/
	public static String getShortDateString() {
		return format(new java.util.Date(), "yyyyMMdd");
	}

	/**
	 * @return formatted string representation of current time with  "HHmmss".
	 */
	public static String getShortTimeString() {
		return format(new java.util.Date(), "HHmmss");
	}
	
	public static Date parseDate(String value, String format) {
		Date date;
		try {
			date = DateTime.parse(value, format);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}
	
	public static Date parseDate(String value) {
		Date date = parseDate(value, "yyyy-MM-dd");
		return date;
	}
	
	public static String getDate(String sdate, String format) {
		Date date = parseDate(sdate);
		return DateTime.format(date, format);
	}
	
	public static String getDate(String sdate, String informat, String outformat) {
			Date date = parseDate(sdate, informat);
			return DateTime.format(date, outformat);
	}
}
