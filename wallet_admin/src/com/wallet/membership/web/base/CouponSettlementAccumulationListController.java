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
	 * @Description : 결제사별 정산
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.28
	 */
	@RequestMapping(value="/member/coupon_settlement_accumulation_list.ms")
	public String CouponSettlementAccumulatonList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponSettlementAccumulation>  accumList = null;
		
		CouponSettlementAccumulationService ctaService = new CouponSettlementAccumulationService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		String today = today();
		
		//if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		//}
		/*if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}*/
		
		params.put("compName", checkStr(request.getParameter("compName"), "")); //-- 결제사명
		params.put("name", checkStr(request.getParameter("name"), "")); //-- 쿠폰명
		
		
		log.debug("@@@@@@@@@@ CouponSettlementAccumulation params : "+ params); //##
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		accumList = ctaService.selectCouponSettlementAccumulationList(params); //-- 목록조회
		int accumListCnt = ctaService.selectCouponSettlementAccumulationListCnt(params);
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, accumListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		
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
	 * @Description : 결제사별 정산 Excel
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.28
	 */
	@RequestMapping(value="/member/coupon_settlement_accumulation_list_excel.ms")
	public String CouponSettlementAccumulatonExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponSettlementAccumulation>  accumList = null;
		
		CouponSettlementAccumulationService ctaService = new CouponSettlementAccumulationService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
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
			
		params.put("compName", checkStr(request.getParameter("compName"), "")); //-- 결제사명
		params.put("name", checkStr(request.getParameter("name"), "")); //-- 쿠폰명
			
		log.debug("@@@@@@@@@@ CouponSettlementAccumulation params : "+ params); //##
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		accumList = ctaService.selectCouponSettlementAccumulationList(params); //-- 목록조회
		int accumListCnt = ctaService.selectCouponSettlementAccumulationListCnt(params);
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, accumListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		
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
