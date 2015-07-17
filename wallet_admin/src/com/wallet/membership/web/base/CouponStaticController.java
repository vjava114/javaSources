package com.wallet.membership.web.base;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.custom.CouponStatic;
import com.wallet.membership.service.custom.CouponStaticService;

@Controller
public class CouponStaticController extends CommonController{
	private Logger log = Log.getLogger("logs");
	
	/**
	 * @Method Name : CouponStaticDayList
	 * @Description : 쿠폰별통계 사용통계(일별)을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/coupon_static_day_list.ms")
	@RequestMapping(value="/member/coupon_static_day_list.st")
	public String CouponStaticDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponStatic> couponStaticDayList = null;
		CouponStaticService couponStaticService = new CouponStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("partV", checkStr(request.getParameter("partV"), ""));
		params.put("partVNm", checkStr(request.getParameter("partVNm"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("compName", checkStr(request.getParameter("compName"), ""));
		params.put("compId", checkStr(request.getParameter("compId"), ""));
		params.put("cpnId", checkStr(request.getParameter("cpnId"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		
		log.debug("@@@@@@@@@@ CouponStaticDayList params : "+ params); //##
		
 		couponStaticDayList = couponStaticService.selectCouponStaticDayList(params); //-- 목록조회
		
		/* SET ATTRIBUTEs */
		request.setAttribute("couponStaticDayList", couponStaticDayList);
		request.setAttribute("partV", params.get("partV"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("compName", params.get("compName"));
		request.setAttribute("partVNm", params.get("partVNm"));
		request.setAttribute("cpnId", params.get("cpnId"));
		request.setAttribute("compId", params.get("compId"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/coupon_static_day_list";
	}
	
	
	/**
	 * @Method Name : CouponStaticExcel
	 * @Description : 쿠폰별통계 사용통계(일별)결과를 EXCEL 파일로 저장한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.26
	 */
	//@RequestMapping(value="/member/coupon_static_day_list_excel.ms")
	@RequestMapping(value="/member/coupon_static_day_list_excel.st")
	public String CouponStaticExcel(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		List<CouponStatic> couponStaticDayList = null;
		CouponStaticService couponStaticService = new CouponStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("partV", checkStr(request.getParameter("partV"), ""));
		params.put("partVNm", checkStr(request.getParameter("partVNm"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("compName", checkStr(request.getParameter("compName"), ""));
		params.put("compId", checkStr(request.getParameter("compId"), ""));
		params.put("cpnId", checkStr(request.getParameter("cpnId"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		
		log.debug("@@@@@@@@@@ CouponStaticDayList params : "+ params); //##
		
 		couponStaticDayList = couponStaticService.selectCouponStaticDayList(params); //-- 목록조회
		
		/* SET ATTRIBUTEs */
		request.setAttribute("couponStaticDayList", couponStaticDayList);
		request.setAttribute("partV", params.get("partV"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("compName", params.get("compName"));
		request.setAttribute("partVNm", params.get("partVNm"));
		request.setAttribute("cpnId", params.get("cpnId"));
		request.setAttribute("compId", params.get("compId"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/coupon_static_day_list_excel";
	}

	/**
	 * @Method Name : CouponStaticMonthList
	 * @Description : 쿠폰별통계 사용통계(월별)을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.26
	 */

	//@RequestMapping(value="/member/coupon_static_month_list.ms")
	@RequestMapping(value="/member/coupon_static_month_list.st")
	public String CouponStaticMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<HashMap<String, Object>> couponStaticMonthList = null;
		CouponStaticService couponStaticService = new CouponStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();

		params.put("partV", checkStr(request.getParameter("partV"), ""));
		params.put("partVNm", checkStr(request.getParameter("partVNm"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("compName", checkStr(request.getParameter("compName"), ""));
		params.put("compId", checkStr(request.getParameter("compId"), ""));
		params.put("cpnId", checkStr(request.getParameter("cpnId"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		String edate = checkStr(request.getParameter("edate"), "");
				
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}		
		
		//조회 edate의 달의 마지막날짜 구하기
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
				
		calendar.set(year, month, date);
			
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();
		
		params.put("sdate", sdate);
		params.put("edate", edate);
		
		String STAT_MONTH = checkStr(request.getParameter("statMonth"), sdate.substring(0,6));

		for(int i=0; i<=differMonths; i++){
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+"+i+",CONVERT(datetime,'"+STAT_MONTH+"'+'01')),120),'-',''),1,6) THEN ISNULL(A.USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}
		params.put("subQuery", subQuery.toString());
		
		log.debug("@@@@@@@@@@ CouponStaticMonthList params : "+ params); //##
		
 		couponStaticMonthList = couponStaticService.selectCouponStaticMonthList(params); //-- 목록조회
		
 		String[] monthList = new String[differMonths];
 		monthList = getMonthList(sdate, differMonths);
 		
		/* SET ATTRIBUTEs */
		request.setAttribute("couponStaticMonthList", couponStaticMonthList);
		request.setAttribute("partV", params.get("partV"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("monthList", monthList);
		request.setAttribute("compName", params.get("compName"));
		request.setAttribute("partVNm", params.get("partVNm"));
		request.setAttribute("cpnId", params.get("cpnId"));
		request.setAttribute("compId", params.get("compId"));
		request.setAttribute("diffMonths", differMonths);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-01");
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +endDay);
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/coupon_static_month_list";
	}
	
	
	/**
	 * @Method Name : CouponStaticMonthExcel
	 * @Description : 쿠폰별통계 사용통계(월별)을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.26
	 */

	//@RequestMapping(value="/member/coupon_static_month_list_excel.ms")
	@RequestMapping(value="/member/coupon_static_month_list_excel.st")
	public String CouponStaticMonthExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<HashMap<String, Object>> couponStaticMonthList = null;
		CouponStaticService couponStaticService = new CouponStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();
		params.put("partV", checkStr(request.getParameter("partV"), ""));
		params.put("partVNm", checkStr(request.getParameter("partVNm"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("compName", checkStr(request.getParameter("compName"), ""));
		params.put("compId", checkStr(request.getParameter("compId"), ""));
		params.put("cpnId", checkStr(request.getParameter("cpnId"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		String edate = checkStr(request.getParameter("edate"), "");
				
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}
		
		//조회 edate의 달의 마지막날짜 구하기
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
				
		calendar.set(year, month, date);
				
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();
		
		params.put("sdate", sdate);
		params.put("edate", edate);
		
		String STAT_MONTH = checkStr(request.getParameter("statMonth"), sdate.substring(0,6));

		for(int i=0; i<=differMonths; i++){
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+"+i+",CONVERT(datetime,'"+STAT_MONTH+"'+'01')),120),'-',''),1,6) THEN ISNULL(A.USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}
		params.put("subQuery", subQuery.toString());
		
		log.debug("@@@@@@@@@@ CouponStaticMonthExcel params : "+ params); //##
		
 		couponStaticMonthList = couponStaticService.selectCouponStaticMonthList(params); //-- 목록조회
		
 		String[] monthList = new String[differMonths];
 		monthList = getMonthList(sdate, differMonths);
 		
		/* SET ATTRIBUTEs */
		request.setAttribute("couponStaticMonthList", couponStaticMonthList);
		request.setAttribute("partV", params.get("partV"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("monthList", monthList);
		request.setAttribute("compName", params.get("compName"));
		request.setAttribute("partVNm", params.get("partVNm"));
		request.setAttribute("cpnId", params.get("cpnId"));
		request.setAttribute("compId", params.get("compId"));
		request.setAttribute("diffMonths", differMonths);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-01");
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +endDay);
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/coupon_static_month_list_excel";
	}
	
	
	public static String today() {
		java.sql.Date CurrDate = new java.sql.Date((new java.util.Date()).getTime());
		return CurrDate.toString();
	}
	
	/**
	 * 두 날짜의 차이를 구한다.
	 * */
	public static int  getDiffMonths(String sMonth, String eMonth){
		int strtYear = Integer.parseInt(sMonth.replace("-", "").substring(0,4));
		int strtMonth = Integer.parseInt(sMonth.replace("-",  "").substring(4,6));
		int endYear = Integer.parseInt(eMonth.replace("-", "").substring(0,4));
		int endMonth = Integer.parseInt(eMonth.replace("-", "").substring(4,6)); 
		
		int month = (endYear - strtYear)* 12 + (endMonth - strtMonth) + 1;
		return month;
	}


	public static String[] getMonthList(String startYYYYMMDD, int diffMonth){

		String[] monthList;
		if(diffMonth == 0){
			monthList = new String[1];
		}else{
			monthList = new String[diffMonth];
		}
		
		String tempMonth = "";
		
		int year = Integer.parseInt(startYYYYMMDD.substring(0, 4));
		int month = Integer.parseInt(startYYYYMMDD.substring(4, 6));
		
		monthList[0] = startYYYYMMDD.substring(0, 4) + "-" + startYYYYMMDD.substring(4, 6);
		for(int i=1; i<diffMonth; i++){
			if(month==12){
				month = 1;
				year = year++;
			}
			else{
				month++;
			}
			
			if(month<10){
				tempMonth =  year + "-0" + month ;
			}
			else{
				tempMonth =  year + "-" + month ;
			}
			
			monthList[i] = tempMonth;
		}
		
		return monthList;
	}

	/**
	 * @Method Name : lastMonth
	 * @Description : 지난 달의 날짜를 를 조회한다.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2010.04.23
	 */
	public static String lastMonth() throws Exception{
		String today = today().replace("-", "");
		String lastMonth = "";
		
		int year = Integer.parseInt(today.substring(0, 4));
		int month = Integer.parseInt(today.substring(4, 6));
		String day = "01";
		
		if (month==1){
			year--;
			month = 12;
		}
		else{
			month--;
		}
		
		if(month<10){
			lastMonth =  year + "-0" + month + "-" + day;
		}
		else{
			lastMonth =  year + "-" + month + "-" + day;
		}
		
		return lastMonth;
	}
}



