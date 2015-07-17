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
import com.wallet.membership.model.MwCmPayCompany;
import com.wallet.membership.model.MwCmPayCompanyExample;
import com.wallet.membership.model.custom.PaymentStatic;
import com.wallet.membership.service.MwCmPayCompanyService;
import com.wallet.membership.service.custom.PaymentStaticService;

@Controller
public class PaymentStaticController extends CommonController {
	private final String PAGE_CODE = "PAYMENT_STATIC_LIST";
	private Logger log = Log.getLogger("logs");

	public PaymentStaticController() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @Method Name : FinanceStaticByDayList
	 * @Description : �����纰 �Ϻ����
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ������
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/coupon_payment_static_day_list.ms")
	@RequestMapping(value="/member/coupon_payment_static_day_list.st")
	public String PaymentStaticByDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<PaymentStatic> statictList = null;
		PaymentStaticService paymentStaticService = new PaymentStaticService();				
		MwCmPayCompanyService mwCmPayCompanyService = new MwCmPayCompanyService();

		MwCmPayCompanyExample mwCmPayCompanyExample = null;
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String payCompId = checkStr(request.getParameter("payCompId"), ""); //-- ������ID
		String name = checkStr(request.getParameter("name"), "");  //-- ������
		String ra_staticOpt = checkStr(request.getParameter("ra_staticOpt"), "01");
		//String paycompName = checkStr(request.getParameter("paycompName"), "");
		String se_DayMonth = checkStr(request.getParameter("se_DayMonth"), "D");
		List<MwCmPayCompany> mwCmPayCompanyList = mwCmPayCompanyService.getByExample(mwCmPayCompanyExample);

		String today = today();
		params.put("sdate", checkStr(request.getParameter("sdate"), today));
		params.put("edate", checkStr(request.getParameter("edate"), today));
		params.put("payCompId", payCompId);
		params.put("name", name);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
 		statictList = paymentStaticService.selectPaymentStaticDayList(params); //-- �����ȸ
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("statictList", statictList);
		request.setAttribute("name", name);
		request.setAttribute("payCompId", payCompId);
		//request.setAttribute("paycompName", paycompName);
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("mwCmPayCompanyList", mwCmPayCompanyList);
		
		params.clear();
		return "member/coupon_payment_static_day_list";
	}

	
	/**
	 * @Method Name : FinanceStaticByDayListExcel
	 * @Description : �����纰 �Ϻ����(����)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/coupon_payment_static_day_list_excel.ms")
	@RequestMapping(value="/member/coupon_payment_static_day_list_excel.st")
	public String FinanceStaticByDayListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<PaymentStatic> statictList = null;
		PaymentStaticService paymentStaticService = new PaymentStaticService();				
		MwCmPayCompanyService mwCmPayCompanyService = new MwCmPayCompanyService();

		MwCmPayCompanyExample mwCmPayCompanyExample = null;
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String payCompId = checkStr(request.getParameter("payCompId"), ""); //-- ������ID
		String name = checkStr(request.getParameter("name"), "");  //-- ������
		String paycompName = checkStr(request.getParameter("paycompName"), "");
		List<MwCmPayCompany> mwCmPayCompanyList = mwCmPayCompanyService.getByExample(mwCmPayCompanyExample);

		String today = today();
		params.put("sdate", checkStr(request.getParameter("sdate"), today));
		params.put("edate", checkStr(request.getParameter("edate"), today));
		params.put("payCompId", payCompId);
		params.put("name", name);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
 		statictList = paymentStaticService.selectPaymentStaticDayList(params); //-- �����ȸ
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("statictList", statictList);
		request.setAttribute("name", name);
		request.setAttribute("payCompId", payCompId);
		request.setAttribute("paycompName", paycompName);
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("mwCmPayCompanyList", mwCmPayCompanyList);
		
		params.clear();
		return "member/coupon_payment_static_day_list_excel";
	}
	
	/**
	 * @Method Name : FinanceStaticByMonthList
	 * @Description : �����纰 �������
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/coupon_payment_static_month_list.ms")
	@RequestMapping(value="/member/coupon_payment_static_month_list.st")
	public String FinanceStaticByMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<HashMap<String, Object>> paymentStaticMonthList = null;
		MwCmPayCompanyService mwCmPayCompanyService = new MwCmPayCompanyService();
		MwCmPayCompanyExample mwCmPayCompanyExample = null;
		PaymentStaticService paymentStaticService = new PaymentStaticService();
		Calendar calendar = Calendar.getInstance();
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String payCompId = checkStr(request.getParameter("payCompId"), ""); //-- ������ID
		String name = checkStr(request.getParameter("name"), "");  //-- ������
		String paycompName = checkStr(request.getParameter("paycompName"), "");
		
		List<MwCmPayCompany> mwCmPayCompanyList = mwCmPayCompanyService.getByExample(mwCmPayCompanyExample);
		
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
		
		//��ȸ edate�� ���� ��������¥ ���ϱ�
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
		
		calendar.set(year, month, date);
		
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		
		int diffMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer(); //-- ���� ���̺� �� ������
		
		for(int i=0; i<=diffMonths; i++){
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(A.USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}                    
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);
		params.put("payCompId", payCompId);
		params.put("name", name);
		
		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
		paymentStaticMonthList = paymentStaticService.selectPaymentStaticMonthList(params); //-- �����ȸ
		
 		String[] monthList = getMonthList(sdate, diffMonths);
 		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("name", name);
		request.setAttribute("payCompId", payCompId);
		request.setAttribute("sdate", sdate.substring(0, 4) + "-" + sdate.substring(4, 6) + "-01");
		request.setAttribute("edate", edate.substring(0, 4) + "-" + edate.substring(4, 6) + "-" +endDay);
		request.setAttribute("mwCmPayCompanyList", mwCmPayCompanyList);
		request.setAttribute("diffMonths", diffMonths);
		request.setAttribute("monthList", monthList);
		request.setAttribute("se_DayMonth", checkStr(request.getParameter("se_DayMonth"), ""));
		
		request.setAttribute("paymentStaticMonthList", paymentStaticMonthList);
		
		params.clear();
		return "member/coupon_payment_static_month_list";
	}
	

	

	/**
	 * @Method Name : FinanceStaticByMonthList
	 * @Description : �����纰 �������
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/coupon_payment_static_month_list_excel.ms")
	@RequestMapping(value="/member/coupon_payment_static_month_list_excel.st")
	public String FinanceStaticByMonthListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<HashMap<String, Object>> paymentStaticMonthList = null;
		MwCmPayCompanyService mwCmPayCompanyService = new MwCmPayCompanyService();
		MwCmPayCompanyExample mwCmPayCompanyExample = null;
		PaymentStaticService paymentStaticService = new PaymentStaticService();
		Calendar calendar = Calendar.getInstance();
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String payCompId = checkStr(request.getParameter("payCompId"), ""); //-- ������ID
		String name = checkStr(request.getParameter("name"), "");  //-- ������
		String paycompName = checkStr(request.getParameter("paycompName"), "");
		
		List<MwCmPayCompany> mwCmPayCompanyList = mwCmPayCompanyService.getByExample(mwCmPayCompanyExample);
		
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
		
		//��ȸ edate�� ���� ��������¥ ���ϱ�
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
		
		calendar.set(year, month, date);
		
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int diffMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer(); //-- ���� ���̺� �� ������
		
		for(int i=0; i<=diffMonths; i++){
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(A.USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}                    
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);
		params.put("payCompId", payCompId);
		params.put("name", name);
		
		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
		paymentStaticMonthList = paymentStaticService.selectPaymentStaticMonthList(params); //-- �����ȸ
		
 		String[] monthList = getMonthList(sdate, diffMonths);
 		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("name", name);
		request.setAttribute("payCompId", payCompId);
		request.setAttribute("sdate", sdate.substring(0, 4) + "-" + sdate.substring(4, 6) + "-01");
		request.setAttribute("edate", edate.substring(0, 4) + "-" + edate.substring(4, 6) + "-" + endDay);
		request.setAttribute("mwCmPayCompanyList", mwCmPayCompanyList);
		request.setAttribute("diffMonths", diffMonths);
		request.setAttribute("monthList", monthList);
		request.setAttribute("se_DayMonth", checkStr(request.getParameter("se_DayMonth"), ""));
		
		request.setAttribute("paymentStaticMonthList", paymentStaticMonthList);
		
		params.clear();
		return "member/coupon_payment_static_month_list_excel";
	}

	
	/**
	 * @Method Name : today
	 * @Description : ���� ��¥�� ��ȸ�Ѵ�.
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
	 * @Description : ���� ���� ��¥�� �� ��ȸ�Ѵ�.
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
	
	/**
	 * @Method Name : getDiffMonths
	 * @Description : ���ۿ��� ������� ������ ���̸� ��ȯ�Ѵ�.
	 * @param : String, String 
	 * @return : int 
	 * @author ���¸�
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
	 * @Description : ���ۿ��κ��� n���� ��ŭ�� �� ������ String �迭�� ��ȯ�Ѵ�. 
	 * @param : String, String 
	 * @return : String[] 
	 * @author ���¸�
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
}

