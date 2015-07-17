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
import com.wallet.membership.service.custom.CouponAccumulationService;
import com.wallet.membership.service.custom.MemberAccumulationService;

@Controller
public class MemberAccumulationController extends CommonController {
	private final String PAGE_CODE = "MEMBER_ACCUMULATION_LIST";
	private Logger log = Log.getLogger("logs");


	/**
	 * @Method Name : MemberAccumulationList
	 * @Description : 멤버십별 정산목록을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 이정인
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/member_accumulation_list.ms")
	public String MemberAccumulationList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MemberAccumulation> memberAccumList = null;
		MemberAccumulationService memberAccumulationService = new MemberAccumulationService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
				
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.

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
		
		params.put("membName", checkStr(request.getParameter("membName"), "")); //-- 멤버십명 
		params.put("cpnName", checkStr(request.getParameter("cpnName"), "")); //-- 쿠폰명 
		params.put("cpn_id", checkStr(request.getParameter("cpn_id"), "")); //-- 멤버십명 

		int count = 0;

		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); //-- 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); //-- 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		memberAccumList = memberAccumulationService.selectMemberAccumulationList(params); //-- 목록조회
		count = memberAccumulationService.getMemberAccumulationListCountByExample(params);
		
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, memberAccumList.size(), rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		/******* paging end *********/
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("count", count);
		request.setAttribute("memberAccumList", memberAccumList);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("cpnName",  params.get("cpnName"));
		request.setAttribute("membName",  params.get("membName"));
		
		params.clear();
		return "member/member_accumulation_list";
	}

	/**
	 * @Method Name : MemberAccumulationList
	 * @Description : 멤버십별 정산목록을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 이정인
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/member_accumulation_list_excel.ms")
	public String MemberAccumulationListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MemberAccumulation> memberAccumList = null;
		MemberAccumulationService memberAccumulationService = new MemberAccumulationService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
				
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.

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
		params.put("membName", checkStr(request.getParameter("membName"), "")); //-- 멤버십명 
		params.put("cpnName", checkStr(request.getParameter("cpnName"), "")); //-- 쿠폰명 
		params.put("cpn_id", checkStr(request.getParameter("cpn_id"), "")); //-- 멤버십명 

		int count = 0;

		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); //-- 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); //-- 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
		memberAccumList = memberAccumulationService.selectMemberAccumulationList(params); //-- 목록조회
		count = memberAccumulationService.getMemberAccumulationListCountByExample(params);
		
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, memberAccumList.size(), rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		/******* paging end *********/
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("count", count);
		request.setAttribute("memberAccumList", memberAccumList);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-" +params.get("sdate").toString().substring(6));
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +params.get("edate").toString().substring(6));
		request.setAttribute("cpnName",  params.get("cpnName"));
		request.setAttribute("membName",  params.get("membName"));
		
		System.out.println(count);
		System.out.println(memberAccumList);
		
		params.clear();
		return "member/member_accumulation_list_excel";
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
