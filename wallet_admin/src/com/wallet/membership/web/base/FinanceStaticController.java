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
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.custom.Finance;
import com.wallet.membership.model.custom.FinanceStatic;
import com.wallet.membership.service.custom.FinanceService;
import com.wallet.membership.service.custom.FinanceStaticService;

@Controller
public class FinanceStaticController extends CommonController {
	private final String PAGE_CODE = "FINANCE_STATIC_LIST";
	private Logger log = Log.getLogger("logs");

	public FinanceStaticController() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @Method Name : FinanceStaticByDayList
	 * @Description : 금융사별 일별통계
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/member_finance_static_day_list.ms")
	@RequestMapping(value="/member/member_finance_static_day_list.st")
	public String FinanceStaticByDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<FinanceStatic> financeStaticDayList = null;
		FinanceService financeService = new FinanceService();
		FinanceStaticService fStaticService = new FinanceStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		String bankId = checkStr(request.getParameter("bankId"), ""); //-- 금융사ID
		String name = checkStr(request.getParameter("name"), "");  //-- 쿠폰명
		
		List<Finance> financeList = financeService.selectFinanceList(params);
		
		String today = today();
		params.put("sdate", checkStr(request.getParameter("sdate"), today));
		params.put("edate", checkStr(request.getParameter("edate"), today));
		params.put("bankId", bankId);
		params.put("name", name);
		
		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
 		financeStaticDayList = fStaticService.selectFinanceStaticDayList(params); //-- 목록조회
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("name", name);
		request.setAttribute("bankId", bankId);
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("financeList", financeList);
		request.setAttribute("se_DayMonth", checkStr(request.getParameter("se_DayMonth"), ""));
		
		request.setAttribute("financeStaticDayList", financeStaticDayList);
		
		params.clear();
		return "member/member_finance_static_day_list";
	}

	
	/**
	 * @Method Name : FinanceStaticByDayListExcel
	 * @Description : 금융사별 일별통계
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/member_finance_static_day_list_excel.ms")
	@RequestMapping(value="/member/member_finance_static_day_list_excel.st")
	public String FinanceStaticByDayListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<FinanceStatic> financeStaticDayList = null;
		FinanceService financeService = new FinanceService();
		FinanceStaticService fStaticService = new FinanceStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		String bankId = checkStr(request.getParameter("bankId"), ""); //-- 금융사ID
		String name = checkStr(request.getParameter("name"), "");  //-- 쿠폰명
		params.put("bankId", bankId);
		params.put("name", name);
		
		List<Finance> financeList = financeService.selectFinanceList(params); //-- 금융사 목록(조회조건)
		
		String today = today();
		params.put("sdate", checkStr(request.getParameter("sdate"), today));
		params.put("edate", checkStr(request.getParameter("edate"), today));
		
		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
 		financeStaticDayList = fStaticService.selectFinanceStaticDayList(params); //-- 목록조회(통계데이터)
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("financeStaticDayList", financeStaticDayList);
		
		params.clear();
		return "member/member_finance_static_day_list_excel";
	}
	


	
	/**
	 * @Method Name : FinanceStaticByMonthList
	 * @Description : 금융사별 월별통계
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/member_finance_static_month_list.ms")
	@RequestMapping(value="/member/member_finance_static_month_list.st")
	public String FinanceStaticByMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<HashMap<String, Object>> financeStaticMonthList = null;
		FinanceService financeService = new FinanceService();
		FinanceStaticService fStaticService = new FinanceStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();

		String bankId = checkStr(request.getParameter("bankId"), ""); //-- 금융사ID
		String name = checkStr(request.getParameter("name"), "");  //-- 쿠폰명
		
		List<Finance> financeList = financeService.selectFinanceList(params);
		
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
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(A.USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate.substring(0, 6));
		params.put("edate", edate.substring(0, 6));
		params.put("bankId", bankId);
		params.put("name", name);
		
		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
		financeStaticMonthList = fStaticService.selectFinanceStaticMonthList(params);
		 //-- 목록조회
		
 		String[] monthList = getMonthList(sdate, diffMonths);
 		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("name", name);
		request.setAttribute("bankId", bankId);
		request.setAttribute("sdate", sdate.substring(0, 4) + "-" + sdate.substring(4, 6) + "-01");
		request.setAttribute("edate", edate.substring(0, 4) + "-" + edate.substring(4, 6) + "-"+endDay);
		request.setAttribute("financeList", financeList);
		request.setAttribute("financeStaticMonthList", financeStaticMonthList);
		request.setAttribute("diffMonths", diffMonths);
		request.setAttribute("monthList", monthList);
		request.setAttribute("se_DayMonth", checkStr(request.getParameter("se_DayMonth"), ""));
		
		params.clear();
		return "member/member_finance_static_month_list";
	}
	
	
	/**
	 * @Method Name : FinanceStaticByMonthListExcel
	 * @Description : 금융사별 월별통계
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/member_finance_static_month_list_excel.ms")
	@RequestMapping(value="/member/member_finance_static_month_list_excel.st")
	public String FinanceStaticByMonthListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<HashMap<String, Object>> financeStaticDayList = null;
		FinanceService financeService = new FinanceService();
		FinanceStaticService fStaticService = new FinanceStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		String bankId = checkStr(request.getParameter("bankId"), ""); //-- 금융사ID
		String name = checkStr(request.getParameter("name"), "");  //-- 쿠폰명
		
		List<Finance> financeList = financeService.selectFinanceList(params);
		
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
		
		int diffMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer(); //-- 동적 테이블에 들어갈 데이터
		
		for(int i=0; i<=diffMonths; i++){
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(A.USER_CNT,0) ELSE 0 END) AS CNT" + i+1);
		}
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate.substring(0, 6));
		params.put("edate", edate.substring(0, 6));
		
		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
 		financeStaticDayList = fStaticService.selectFinanceStaticMonthList(params); //-- 목록조회
		
 		String[] monthList = getMonthList(sdate, diffMonths);
 		
		/* SET ATTRIBUTEs */
		request.setAttribute("financeStaticDayList", financeStaticDayList);
		
		params.clear();
		return "member/member_finance_static_month_list_excel";
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
}
