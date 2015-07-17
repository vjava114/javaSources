package com.wallet.harex.common;

import java.util.*;
import java.text.*;
import javax.swing.text.MaskFormatter;

public class JDate {
	
	public JDate() {
		
	}

	public static String getYear() {
		Date date = new Date();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy");
		String ret = dd.format(date);
		return ret;
	}

	public static String getValidYear() {
		Date date = new Date();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy");
		String ret = dd.format(date);

		dd = new SimpleDateFormat("MM");
		String month = dd.format(date);
		
		if (month.equals("01"))
			ret = String.valueOf(Integer.parseInt(ret) - 1);
		
		return ret;
	}

	public static String getYearString(String year) {
		if (year.equals(""))
			return getYear();
		else
			return year;
	}

	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy:MM:dd:HH:00:00");
		String ret = dd.format(date);
		return ret;
	}

	public static String getFormatted(String date) {
		if (date == null || date.trim().length() == 0)
			return "";

		String str = date.replaceAll("-", "").trim();
		str = JString.replaceAll(str, ".", "");
		if (date.startsWith("20") == false && date.startsWith("19") == false)
			str = "20" + str;

		int length = str.length();

		try {
			MaskFormatter format = new MaskFormatter();
			
			switch (length) {
				case 8:
					format.setMask("####-##-##");
					break;
				case 7:
					format.setMask("####-##-#");
					break;
				case 6:
					format.setMask("####-##-01");
					break;
			}
			
			format.setValueClass(String.class);
			format.setValueContainsLiteralCharacters(false);
			String ret = format.valueToString(str);
			return ret;
		} catch (Exception ee) {
			return "";
		}
	}

	public static String getFormatted(String date, String form) {
		if (date == null || date.trim().length() == 0)
			return "";

		String str = date.replaceAll("-", "").trim();
		str = JString.replaceAll(str, ".", "");

		try {
			MaskFormatter format = new MaskFormatter();
			format.setMask(form);
			format.setValueClass(String.class);
			format.setValueContainsLiteralCharacters(false);
			String ret = format.valueToString(str);
			return ret;
		} catch (Exception ee) {
			return date;
		}
	}

	public static String getFormattedDate(String format) {
		Date date = new Date();
		SimpleDateFormat dd = new SimpleDateFormat(format);
		String ret = dd.format(date);
		return ret;
	}

	public static String getFormattedDate(Date date, String format) {
		SimpleDateFormat dd = new SimpleDateFormat(format);
		String ret = dd.format(date);
		return ret;
	}

	public static String getDate(Date date) {
		if (date == null)
			return "";
		
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		String ret = dd.format(date);
		return ret;
	}

	public static String getDate(String str) {
		if (str.equals(""))
			return "0000-00-00";
		else
			return str;
	}

	public static String getDate(long interval) {
		Date date = new Date();
		long tt = date.getTime() - interval;
		Date ndate = new Date(tt);
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		String ret = dd.format(ndate);
		return ret;
	}

	public static final int ODD_YEAR = 1;
	public static final int EVEN_YEAR = 0;

	public static String getYearStrings(String oddEven) {
		String str = getYear();
		int curYear = Integer.parseInt(str);
		int fromYear = curYear - 65;
		int toYear = curYear - 17;
		String list = null;

		int kind = EVEN_YEAR;
		
		if (oddEven.equalsIgnoreCase("ODD"))
			kind = ODD_YEAR;
		else if (oddEven.equalsIgnoreCase("EVEN"))
			kind = EVEN_YEAR;
		else
			return "";

		for (int i = fromYear; i < toYear; i++) {
			int mod = i % 2;
			if (mod == kind) {
				list = (list == null) ? String.valueOf(i) : list + ","
						+ String.valueOf(i);
			}
		}
		return list;
	}

	public static Date getYear(int year) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}

	public static Date getYear(Date start, int year) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(start);
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}

	public static Date getDate(int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date getMonth(int month) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	public static Date getDay(Date start, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(start);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date getMonth(Date start, int month) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(start);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	public static int getAgeWithJumin(String jumin) {
		try {
			String birthdate = jumin.substring(0, 2);

			int num = Integer.parseInt(birthdate);

			if (num < 20)
				birthdate = "20" + jumin;
			else
				birthdate = "19" + jumin;

			String year = birthdate.substring(0, 4);

			Date today = new Date();
			SimpleDateFormat dd = new SimpleDateFormat("yyyyMMdd");

			String todayStr = dd.format(today);
			String tyear = todayStr.substring(0, 4);

			int nyear = Integer.parseInt(year);
			int ntyear = Integer.parseInt(tyear);
			int term = ntyear - nyear;

			return term;
		} catch (Exception e) {
			return 0;
		}
	}

	public static Date toDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
		Date dd = null;
		
		try {
			dd = format.parse(str);
		} catch (ParseException ex) {
			return new Date();
		}
		
		return dd;
	}

	public static Date toDate(String str, String format) {
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Date dd = null;
		
		try {
			dd = sformat.parse(str);
		} catch (ParseException ex) {
			return new Date();
		}
		
		return dd;
	}

	public static String getYear(String age) {
		int fromAge = Integer.parseInt(age);

		Date today = new Date();
		SimpleDateFormat dd = new SimpleDateFormat("yyyyMMdd");
		String todayStr = dd.format(today);
		String tyear = todayStr.substring(0, 4);
		int nyear = Integer.parseInt(tyear);
		int fromYear = nyear - fromAge;
		return String.valueOf(fromYear);
	}

	public static String[] getYears(String age) {
		int fromAge = Integer.parseInt(age);

		Date today = new Date();
		SimpleDateFormat dd = new SimpleDateFormat("yyyyMMdd");
		String todayStr = dd.format(today);
		String tyear = todayStr.substring(0, 4);

		int nyear = Integer.parseInt(tyear);
		int toYear = nyear - fromAge;
		int fromYear = toYear - 9;
		String[] ret = new String[2];
		ret[0] = String.valueOf(fromYear);
		ret[1] = String.valueOf(toYear);
		return ret;
	}

	public static String[] getDayRange(String from, String to, int previous) {
		String[] res = new String[2];
		res[0] = from;
		res[1] = to;
		try {
			Date date = new Date();
			
			if (from == null || from.trim().length() < 6) {
				Date fdate = JDate.getDay(date, previous);
				res[0] = JDate.getFormattedDate(fdate, "yyyyMMdd");
			}

			if (to == null || to.trim().length() < 6) {
				res[1] = JDate.getFormattedDate(date, "yyyyMMdd");
			}
			
		} catch (Exception e) {
		}

		return res;
	}

	public static String[] getMonthRange(String from, String to, int previous) {
		String[] res = new String[2];
		res[0] = from;
		res[1] = to;
		try {
			Date date = new Date();
			
			if (from == null || from.trim().length() < 6) {
				Date fdate = JDate.getMonth(date, previous);
				res[0] = JDate.getFormattedDate(fdate, "yyyyMM01");
			}

			if (to == null || to.trim().length() < 6) {
				res[1] = JDate.getFormattedDate(date, "yyyyMMdd");
			}
			
		} catch (Exception e) {
		}

		return res;
	}
}
