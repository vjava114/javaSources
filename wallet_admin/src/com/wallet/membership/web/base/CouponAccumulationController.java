package com.wallet.membership.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;

import com.wallet.membership.model.custom.CouponAccumulation;
import com.wallet.membership.model.custom.CpnShareRatio;
import com.wallet.membership.service.custom.CouponAccumulationService;

@Controller
public class CouponAccumulationController extends CommonController {
	private final String PAGE_CODE = "COUPON_ACCUMULATION_LIST";
	private Logger log = Log.getLogger("logs");


	/**
	 * @Method Name : CouponAccumulationList
	 * @Description : ������ �������� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/coupon_accumulation_list.ms")
	public String CouponAccumulationList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponAccumulation> accumList = null;
		CouponAccumulationService caService = new CouponAccumulationService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		
		params.put("partV", checkStr(request.getParameter("partV"), "")); 
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		
		//if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		//}
		/*
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}*/
		
		params.put("cpnName", checkStr(request.getParameter("cpnName"), "")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		accumList = caService.couponAccumulationList(params); //-- �����ȸ
		int accumListCnt = caService.couponAccumulationListCnt(params);
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, accumListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("accumList", accumList);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("partV",  params.get("partV"));
		request.setAttribute("cpnName",  params.get("cpnName"));
		
		params.clear();
		return "member/coupon_accumulation_list";
	}


	/**
	 * @Method Name : CouponAccumulationListExcel
	 * @Description : ������ �������� �������Ϸ� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/coupon_accumulation_list_excel.ms")
	public String CouponAccumulationListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponAccumulation> accumList = null;
		CouponAccumulationService caService = new CouponAccumulationService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		
		params.put("partV", checkStr(request.getParameter("partV"), "")); 
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		
		String today = today();
		//if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		//}
		/*
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		*/
		params.put("cpnName", checkStr(request.getParameter("cpnName"), "")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); // �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); // ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; // ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		accumList = caService.couponAccumulationList(params); //-- �����ȸ
		int accumListCnt = caService.couponAccumulationListCnt(params);
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, accumListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("accumList", accumList);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("partV",  params.get("partV"));
		request.setAttribute("cpnName",  params.get("cpnName"));
		
		params.clear();
		return "member/coupon_accumulation_list_excel";
	}



	/**
	 * @Method Name : CouponAccumulationListExcel
	 * @Description : ������ �������� �������Ϸ� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/coupon_accumulation_detail.ms")
	public String CouponAccumulationDetail(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CpnShareRatio> accumList = null;
		CouponAccumulationService caService = new CouponAccumulationService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("sdate", checkStr(request.getParameter("sdate"), ""));
		params.put("edate", checkStr(request.getParameter("edate"), ""));
		params.put("cpnId", checkStr(request.getParameter("cpnId"), ""));
		
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); //-- �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); //-- ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		accumList = caService.selectCouponAccumulationDetail(params); //-- �����ȸ
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("accumList", accumList);
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("cpnId", params.get("cpnId"));
		
		params.clear();
		return "member/coupon_accumulation_detail";
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
