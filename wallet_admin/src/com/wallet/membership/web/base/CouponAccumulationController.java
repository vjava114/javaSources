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
	 * @Description : 쿠폰별 정산목록을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/coupon_accumulation_list.ms")
	public String CouponAccumulationList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponAccumulation> accumList = null;
		CouponAccumulationService caService = new CouponAccumulationService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		
		params.put("partV", checkStr(request.getParameter("partV"), "")); 
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
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
		
		params.put("cpnName", checkStr(request.getParameter("cpnName"), "")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		accumList = caService.couponAccumulationList(params); //-- 목록조회
		int accumListCnt = caService.couponAccumulationListCnt(params);
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, accumListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
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
	 * @Description : 쿠폰별 정산목록을 엑셀파일로 저장한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/coupon_accumulation_list_excel.ms")
	public String CouponAccumulationListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<CouponAccumulation> accumList = null;
		CouponAccumulationService caService = new CouponAccumulationService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		
		params.put("partV", checkStr(request.getParameter("partV"), "")); 
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		
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
		params.put("cpnName", checkStr(request.getParameter("cpnName"), "")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); // 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); // 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; // 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		accumList = caService.couponAccumulationList(params); //-- 목록조회
		int accumListCnt = caService.couponAccumulationListCnt(params);
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, accumListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
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
	 * @Description : 쿠폰별 정산목록을 엑셀파일로 저장한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
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
		
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); //-- 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); //-- 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		accumList = caService.selectCouponAccumulationDetail(params); //-- 목록조회
		
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
