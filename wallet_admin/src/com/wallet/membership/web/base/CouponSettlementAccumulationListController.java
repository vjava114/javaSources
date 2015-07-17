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

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.custom.CouponSettlementAccumulation;
import com.wallet.membership.service.custom.CouponSettlementAccumulationService;

@Controller
public class CouponSettlementAccumulationListController extends CommonController{
	private final String PAGE_CODE = "COUPON_SETTLEMENT_ACCUMULATION_LIST";
	private Logger log = Log.getLogger("logs");
	
	
	/**
	 * @Method Name : CouponFinanceAccumulatonList
	 * @Description : �����纰 ����
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	@RequestMapping(value="/member/coupon_settlement_accumulation_list.ms")
	public String CouponSettlementAccumulatonList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponSettlementAccumulation>  accumList = null;
		
		CouponSettlementAccumulationService ctaService = new CouponSettlementAccumulationService();
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
		}*/
		
		params.put("compName", checkStr(request.getParameter("compName"), "")); //-- �������
		params.put("name", checkStr(request.getParameter("name"), "")); //-- ������
		
		
		log.debug("@@@@@@@@@@ CouponSettlementAccumulation params : "+ params); //##
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		accumList = ctaService.selectCouponSettlementAccumulationList(params); //-- �����ȸ
		int accumListCnt = ctaService.selectCouponSettlementAccumulationListCnt(params);
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, accumListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("accumList", accumList);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("compName", params.get("compName"));
		request.setAttribute("name", params.get("name"));
		
		params.clear();
		return "member/coupon_settlement_accumulation_list";
	}
	
	/**
	 * @Method Name : CouponSettlementAccumulatonExcel
	 * @Description : �����纰 ���� Excel
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	@RequestMapping(value="/member/coupon_settlement_accumulation_list_excel.ms")
	public String CouponSettlementAccumulatonExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponSettlementAccumulation>  accumList = null;
		
		CouponSettlementAccumulationService ctaService = new CouponSettlementAccumulationService();
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
			
		params.put("compName", checkStr(request.getParameter("compName"), "")); //-- �������
		params.put("name", checkStr(request.getParameter("name"), "")); //-- ������
			
		log.debug("@@@@@@@@@@ CouponSettlementAccumulation params : "+ params); //##
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		accumList = ctaService.selectCouponSettlementAccumulationList(params); //-- �����ȸ
		int accumListCnt = ctaService.selectCouponSettlementAccumulationListCnt(params);
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, accumListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("accumList", accumList);
		request.setAttribute("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), ""));
		request.setAttribute("compName", params.get("compName"));
		request.setAttribute("name", params.get("name"));
		
		params.clear();
		return "member/coupon_settlement_accumulation_list_excel";
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
