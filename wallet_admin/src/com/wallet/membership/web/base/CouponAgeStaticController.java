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
import com.wallet.membership.model.custom.CouponAgeStatic;
import com.wallet.membership.service.custom.CouponAgeStaticService;

@Controller
public class CouponAgeStaticController extends CommonController{
	private Logger log = Log.getLogger("logs");
	
	/**
	 * @Method Name : CouponAgeStaticDayList
	 * @Description : 발급통계(일별)을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/coupon_age_static_day_list.ms")
	@RequestMapping(value="/member/coupon_age_static_day_list.st")
	public String CouponAgeStaticDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponAgeStatic> couponAgeStaticDayList = null;
		CouponAgeStaticService couponAgeStaticService = new CouponAgeStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("partV", checkStr(request.getParameter("partV"), ""));
		params.put("partVNm", checkStr(request.getParameter("partVNm"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("compName", checkStr(request.getParameter("compName"), ""));
		params.put("compId", checkStr(request.getParameter("compId"), ""));
		params.put("cpnId", checkStr(request.getParameter("cpnId"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d"));
		
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		
		log.debug("@@@@@@@@@@ CouponAgeStaticDayList params : "+ params); //##
		
 		couponAgeStaticDayList = couponAgeStaticService.selectCouponAgeStaticDayList(params); //-- 목록조회
		
		/* SET ATTRIBUTEs */
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

		request.setAttribute("couponAgeStaticDayList", couponAgeStaticDayList);
		params.clear();
		return "member/coupon_age_static_day_list";
	}
	
	
	/**
	 * @Method Name : CouponAgeStaticExcel
	 * @Description : 쿠폰별통계 단말기OS/통신사별(일별)결과를 EXCEL 파일로 저장한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.26
	 */
	//@RequestMapping(value="/member/coupon_age_static_day_list_excel.ms")
	@RequestMapping(value="/member/coupon_age_static_day_list_excel.st")
	public String CouponAgeStaticExcel(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		List<CouponAgeStatic> couponAgeStaticDayList = null;
		CouponAgeStaticService couponAgeStaticService = new CouponAgeStaticService();
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
		
		log.debug("@@@@@@@@@@ CouponAgeStaticDayList params : "+ params); //##
		
 		couponAgeStaticDayList = couponAgeStaticService.selectCouponAgeStaticDayList(params); //-- 목록조회
		
		/* SET ATTRIBUTEs */
		request.setAttribute("couponAgeStaticDayList", couponAgeStaticDayList);
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
		return "member/coupon_age_static_day_list_excel";
	}


	
	/**
	 * @Method Name : CouponAgeStaticMonthList
	 * @Description : 쿠폰별 통계 연령/성별(월별)을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.28
	 */
	//@RequestMapping(value="/member/coupon_age_static_month_list.ms")
	@RequestMapping(value="/member/coupon_age_static_month_list.st")
	public String CouponAgeStaticMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<HashMap<String, Object>> couponAgeStaticMonthList = null;
		CouponAgeStaticService couponAgeStaticService = new CouponAgeStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();

		params.put("partV", checkStr(request.getParameter("partV"), ""));
		params.put("partVNm", checkStr(request.getParameter("partVNm"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("compName", checkStr(request.getParameter("compName"), ""));
		params.put("compId", checkStr(request.getParameter("compId"), ""));
		params.put("cpnId", checkStr(request.getParameter("cpnId"), ""));
		
		String lastMonth = lastMonth(); //-- 'YYYY-MM-01'
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
		
		if(sdate== null || "".equals(sdate)){
			sdate = lastMonth.replace("-",  "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		if(edate== null || "".equals(edate)){
			edate = lastMonth.replace("-",  "");
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
		
		int diffMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer(); //-- 동적 테이블에 들어갈 데이터
		for(int i=0; i<=diffMonths; i++){
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime,'" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT"+ (i+1));
			subQuery.append(", SUM(ISNULL(USER_CNT,0)) AS SUM_CNT");
		}
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate.substring(0, 6));
		params.put("edate", edate.substring(0, 6));
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		
		log.debug("@@@@@@@@@@ CouponAgeStaticDayList params : "+ params); //##
		
		couponAgeStaticMonthList = couponAgeStaticService.selectCouponAgeStaticMonthList(params); //-- 목록조회
		
 		String[] monthList = new String[diffMonths];
 		monthList = getMonthList(sdate, diffMonths);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("partV", params.get("partV"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("monthList", monthList);
		request.setAttribute("compName", params.get("compName"));
		request.setAttribute("partVNm", params.get("partVNm"));
		request.setAttribute("cpnId", params.get("cpnId"));
		request.setAttribute("compId", params.get("compId"));
		request.setAttribute("diffMonths", diffMonths);
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		request.setAttribute("sdate", sdate.substring(0, 4) + "-" + sdate.substring(4, 6) + "-01");
		request.setAttribute("edate", edate.substring(0, 4) + "-" + edate.substring(4, 6) + "-" +endDay);

		request.setAttribute("couponAgeStaticMonthList", couponAgeStaticMonthList);
		
		params.clear();
		return "member/coupon_age_static_month_list";
	}
	
	
	/**
	 * @Method Name : CouponAgeStaticMonthListExcel
	 * @Description : 쿠폰별 통계 연령/성별(월별)을  엑셀로 저장한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.28
	 */
	//@RequestMapping(value="/member/coupon_age_static_month_list_excel.ms")
	@RequestMapping(value="/member/coupon_age_static_month_list_excel.st")
	public String CouponAgeStaticMonthListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<HashMap<String, Object>> couponAgeStaticMonthList = null;
		CouponAgeStaticService couponAgeStaticService = new CouponAgeStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();

		params.put("partV", checkStr(request.getParameter("partV"), ""));
		params.put("partVNm", checkStr(request.getParameter("partVNm"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("compName", checkStr(request.getParameter("compName"), ""));
		params.put("compId", checkStr(request.getParameter("compId"), ""));
		params.put("cpnId", checkStr(request.getParameter("cpnId"), ""));
		
		String lastMonth = lastMonth(); //-- 'YYYY-MM-01'
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
		
		if(sdate== null || "".equals(sdate)){
			sdate = lastMonth.replace("-",  "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		if(edate== null || "".equals(edate)){
			edate = lastMonth.replace("-",  "");
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
		
		int diffMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer(); //-- 동적 테이블에 들어갈 데이터
		for(int i=0; i<=diffMonths; i++){
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime,'" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT"+ (i+1));
			subQuery.append(", SUM(ISNULL(USER_CNT,0)) AS SUM_CNT");
		}
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate.substring(0, 6));
		params.put("edate", edate.substring(0, 6));
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		
		log.debug("@@@@@@@@@@ CouponAgeStaticDayList params : "+ params); //##
		
		couponAgeStaticMonthList = couponAgeStaticService.selectCouponAgeStaticMonthList(params); //-- 목록조회
		
 		String[] monthList = new String[diffMonths];
 		monthList = getMonthList(sdate, diffMonths);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("partV", params.get("partV"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("monthList", monthList);
		request.setAttribute("compName", params.get("compName"));
		request.setAttribute("partVNm", params.get("partVNm"));
		request.setAttribute("cpnId", params.get("cpnId"));
		request.setAttribute("compId", params.get("compId"));
		request.setAttribute("diffMonths", diffMonths);
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		request.setAttribute("sdate", sdate.substring(0, 4) + "-" + sdate.substring(4, 6) + "-01");
		request.setAttribute("edate", edate.substring(0, 4) + "-" + edate.substring(4, 6) + "-" +endDay);

		request.setAttribute("couponAgeStaticMonthList", couponAgeStaticMonthList);
		
		params.clear();
		return "member/coupon_age_static_month_list_excel";
	}
	
	
	/**
	 * @Method Name : lastMonth
	 * @Description : 지난 달의 날짜를 를 조회한다.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2012.09.26
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
	
	/**
	 * @Method Name : getDiffMonths
	 * @Description : 시작월과 종료월의 개월수 차이를 반환한다.
	 * @param : String, String 
	 * @return : int 
	 * @author 김태리
	 * @since 2012.09.27
	 */
	public int getDiffMonths(String sMonth, String eMonth){
		int strtYear = Integer.parseInt(sMonth.replace("-", "").substring(0,4));
		int strtMonth = Integer.parseInt(sMonth.replace("-",  "").substring(4,6));
		
		int endYear = Integer.parseInt(eMonth.replace("-", "").substring(0,4));
		int endMonth = Integer.parseInt(eMonth.replace("-", "").substring(4,6)); 
		
		int month = (endYear - strtYear)* 12 + (endMonth - strtMonth) + 1;
		
		return month;
	}
	
	/**
	 * @Method Name : getMonthList
	 * @Description : 시작월로부터 n개월 만큼의 달 정보를 String 배열로 반환한다. 
	 * @param : String, String 
	 * @return : String[] 
	 * @author 김태리
	 * @since 2012.09.27
	 */
	public String[] getMonthList(String startYYYYMMDD, int diffMonth){

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
	 * @Method Name : today
	 * @Description : 오늘 날짜를 조회한다.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2010.04.23
	 */
	public static String today() {
		java.sql.Date CurrDate = new java.sql.Date((new java.util.Date()).getTime());
		return CurrDate.toString();
	}
}
