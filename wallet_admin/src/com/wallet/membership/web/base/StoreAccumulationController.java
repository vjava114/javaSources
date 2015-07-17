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

import com.wallet.membership.common.DateTime;
import com.wallet.membership.model.custom.CouponAccumulation;
import com.wallet.membership.model.custom.CpnShareRatio;
import com.wallet.membership.model.custom.MemberAccumulation;
import com.wallet.membership.model.custom.StoreAccumulation;
import com.wallet.membership.service.custom.CouponAccumulationService;
import com.wallet.membership.service.custom.MemberAccumulationService;
import com.wallet.membership.service.custom.StoreAccumulationService;

@Controller
public class StoreAccumulationController extends CommonController {
	private final String PAGE_CODE = "STORE_ACCUMULATION_LIST";
	private Logger log = Log.getLogger("logs");


	/**
	 * @Method Name : StoreAccumulationList
	 * @Description : �������� �������� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ������
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/store_accumulation_list.ms")
	public String StoreAccumulationList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<StoreAccumulation> storeAccumList = null;
		StoreAccumulationService storeAccumulationService = new StoreAccumulationService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
				
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.

		String today = today();
		//if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), DateTime.format("yyyy-MM-dd")));
			params.put("edate", checkStr(request.getParameter("edate"), DateTime.format("yyyy-MM-dd")));
		//}
		/*
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", checkStr(request.getParameter("sdate"), DateTime.format("")));
			params.put("edate", checkStr(request.getParameter("edate"), DateTime.format("")));
		}
		*/
		params.put("brandName", checkStr(request.getParameter("brandName"), "")); //-- �귣�� �� 
		params.put("storeName", checkStr(request.getParameter("storeName"), "")); //-- ������ �� 
		params.put("cpnName", checkStr(request.getParameter("cpnName"), "")); //-- ������ 
		params.put("cpn_id", checkStr(request.getParameter("cpn_id"), "")); //-- �������̵� 

		int count = 0;

		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); //-- �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); //-- ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		storeAccumList = storeAccumulationService.selectStoreAccumulationList(params); //-- �����ȸ
		count = storeAccumulationService.getStoreAccumulationListCountByExample(params);//--ī��Ʈ ��������
		
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, storeAccumList.size(), rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		/******* paging end *********/
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("count", count);
		request.setAttribute("storeAccumList", storeAccumList);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("cpnName",  params.get("cpnName"));
		request.setAttribute("brandName",  params.get("brandName"));
		request.setAttribute("storeName",  params.get("storeName"));
		
		params.clear();
		return "member/store_accumulation_list";
	}

	/**
	 * @Method Name : StoreAccumulationList
	 * @Description : �������� �������� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ������
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/store_accumulation_list_excel.ms")
	public String StoreAccumulationListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<StoreAccumulation> storeAccumList = null;
		StoreAccumulationService storeAccumulationService = new StoreAccumulationService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
				
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.

		String today = today();
		//if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), DateTime.format("yyyy-MM-dd")));
			params.put("edate", checkStr(request.getParameter("edate"), DateTime.format("yyyy-MM-dd")));
		//}
		/*
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", checkStr(request.getParameter("sdate"), DateTime.format("")));
			params.put("edate", checkStr(request.getParameter("edate"), DateTime.format("")));
		}
		*/
		
		params.put("brandName", checkStr(request.getParameter("brandName"), "")); //-- �귣�� �� 
		params.put("storeName", checkStr(request.getParameter("storeName"), "")); //-- ������ �� 
		params.put("cpnName", checkStr(request.getParameter("cpnName"), "")); //-- ������ 
		params.put("cpn_id", checkStr(request.getParameter("cpn_id"), "")); //-- �������̵� 

		int count = 0;

		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); //-- �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); //-- ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		storeAccumList = storeAccumulationService.selectStoreAccumulationList(params); //-- �����ȸ
		count = storeAccumulationService.getStoreAccumulationListCountByExample(params);//--ī��Ʈ ��������
		
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, storeAccumList.size(), rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		/******* paging end *********/
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("count", count);
		request.setAttribute("storeAccumList", storeAccumList);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("cpnName",  params.get("cpnName"));
		request.setAttribute("brandName",  params.get("brandName"));
		request.setAttribute("storeName",  params.get("storeName"));
		
		params.clear();
		return "member/store_accumulation_list_excel";
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