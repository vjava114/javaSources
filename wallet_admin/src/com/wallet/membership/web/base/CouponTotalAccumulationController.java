package com.wallet.membership.web.base;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.custom.CouponTotalAccumulation;
import com.wallet.membership.service.custom.CouponTotalAccumulationService;

@Controller
public class CouponTotalAccumulationController extends CommonController {
	private final String PAGE_CODE = "COUPON_TOTAL_ACCUMULATION_LIST";
	private Logger log = Log.getLogger("logs");

	public CouponTotalAccumulationController() {
		// TODO Auto-generated constructor stub
	}



	
	/**
	 * @Method Name : CouponTotalAccumulatonList
	 * @Description : ��������
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	@RequestMapping(value="/member/coupon_total_accumulation_list.ms")
	public String CouponTotalAccumulatonList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponTotalAccumulation>  accumList = null;
		
		CouponTotalAccumulationService ctaService = new CouponTotalAccumulationService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		//if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		//}
		/*if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		*/
		
		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
		accumList = ctaService.selectCouponTotalAccumulationList(params); //-- �����ȸ
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("accumList", accumList);
		request.setAttribute("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), ""));
		
		params.clear();
		return "member/coupon_total_accumulation_list";
	}

	/**
	 * @Method Name : CouponTotalAccumulatonListExcel
	 * @Description : ��������
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	@RequestMapping(value="/member/coupon_total_accumulation_list_excel.ms")
	public String CouponTotalAccumulatonListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponTotalAccumulation>  accumList = null;
		
		CouponTotalAccumulationService ctaService = new CouponTotalAccumulationService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����
		String today = today();
		//if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		//}
		
		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
		accumList = ctaService.selectCouponTotalAccumulationList(params); //-- �����ȸ
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("accumList", accumList);
		request.setAttribute("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), ""));
		
		params.clear();
		return "member/coupon_total_accumulation_list_excel";
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
}
