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
import com.wallet.membership.model.custom.PartnerAccumulation;
import com.wallet.membership.service.custom.CouponAccumulationService;
import com.wallet.membership.service.custom.MemberAccumulationService;
import com.wallet.membership.service.custom.PartnerAccumulationService;

@Controller
public class PartnerAccumulationController extends CommonController {
	private final String PAGE_CODE = "PARTNER_ACCUMULATION_LIST";
	private Logger log = Log.getLogger("logs");


	/**
	 * @Method Name : PartnerAccumulationList
	 * @Description : ���޻纰 �������� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ������
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/partner_accumulation_list.ms")
	public String PartnerAccumulationList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<PartnerAccumulation> partnerAccumList = null;
		PartnerAccumulationService partnerAccumulationService = new PartnerAccumulationService();
		
		
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
		params.put("compName", checkStr(request.getParameter("compName"), "")); //-- ����ʸ� 
		params.put("cpnName", checkStr(request.getParameter("cpnName"), "")); //-- ������ 
		params.put("cpn_id", checkStr(request.getParameter("cpn_id"), "")); //-- ����ʸ� 

		int count = 0;

		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); //-- �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); //-- ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		partnerAccumList = partnerAccumulationService.selectPartnerAccumulationList(params); //-- �����ȸ
		count = partnerAccumulationService.getPartnerAccumulationListCountByExample(params);		
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, partnerAccumList.size(), rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		/******* paging end *********/
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("count", count);
		request.setAttribute("partnerAccumList", partnerAccumList);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("cpnName",  params.get("cpnName"));
		request.setAttribute("compName",  params.get("compName"));
		
		params.clear();
		return "member/partner_accumulation_list";
	}

	/**
	 * @Method Name : PartnerAccumulationList
	 * @Description : ���޻纰 �������� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ������
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/partner_accumulation_list_excel.ms")
	public String PartnerAccumulationListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<PartnerAccumulation> partnerAccumList = null;
		PartnerAccumulationService partnerAccumulationService = new PartnerAccumulationService();
		
		
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
		params.put("compName", checkStr(request.getParameter("compName"), "")); //-- ����ʸ� 
		params.put("cpnName", checkStr(request.getParameter("cpnName"), "")); //-- ������ 
		params.put("cpn_id", checkStr(request.getParameter("cpn_id"), "")); //-- ����ʸ� 

		int count = 0;

		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); //-- �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); //-- ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		partnerAccumList = partnerAccumulationService.selectPartnerAccumulationList(params); //-- �����ȸ
		count = partnerAccumulationService.getPartnerAccumulationListCountByExample(params);
		
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, partnerAccumList.size(), rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		/******* paging end *********/
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("count", count);
		request.setAttribute("partnerAccumList", partnerAccumList);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("cpnName",  params.get("cpnName"));
		request.setAttribute("compName",  params.get("compName"));
		
		params.clear();
		return "member/partner_accumulation_list_excel";
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
