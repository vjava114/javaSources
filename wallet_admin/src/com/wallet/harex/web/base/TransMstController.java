package com.wallet.harex.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.harex.common.JDate;
import com.wallet.harex.model.StatsListComp;
import com.wallet.harex.model.TransMst;
import com.wallet.harex.model.TransMstCpn;
import com.wallet.harex.model.TransMstMsDc;
import com.wallet.harex.model.TransMstMsUse;
import com.wallet.harex.model.TransMstSave;
import com.wallet.harex.model.TransMstStamp;
import com.wallet.harex.service.StatsService;
import com.wallet.harex.service.TransMstService;

@Controller
public class TransMstController extends CommonController {

	private Logger log = Log.getLogger("logs");
	
	/**
	 * 거래내역조회
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/trans_mst_s_list.st")
	public String selectTransMst(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### TransMstController selectTransMst START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<TransMst> list = null;
		TransMstService service = new TransMstService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String pSdate		= request.getParameter("sdate"); // 시작일
		String pEdate	 	= request.getParameter("edate"); // 종료일
		String chk_date	= request.getParameter("chk_date");
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		String acpos_tid 		= checkStr(request.getParameter("acpos_tid"), "");  //거래번호
		
		if(pSdate == null || "".equals(pSdate)){
			pSdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pSdate = pSdate.toString().replaceAll("-", "");
		}
		
		if(pEdate == null || "".equals(pEdate)){
			pEdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pEdate = pEdate.toString().replaceAll("-", "");
		}
		
		params.put("sdate"    , checkStr(pSdate, ""));
		params.put("edate"    , checkStr(pEdate, ""));
		
		params.put("acpos_tid", acpos_tid);	
		params.put("chk_date", chk_date == null || chk_date.equals("") ? "1d":chk_date);
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		String target = "/harex/trans_mst_s_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/trans_mst_s_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectTransMst(params);
			listCnt= service.selectTransMstCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 	
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		request.setAttribute("sdate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));
		request.setAttribute("edate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));
		
		log.debug("### TransMstController selectTransMst END ###");
		
		
		
		return target;
	}
	
	/**
	 * 거래내역조회_쿠폰
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/trans_mst_cpn_list.st")
	public String selectTransMstCpn(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### TransMstController selectTransMstCpn START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<TransMst> list = null;
		TransMstService service = new TransMstService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String pSdate		= request.getParameter("sdate"); // 시작일
		String pEdate	 	= request.getParameter("edate"); // 종료일
		String chk_date	= request.getParameter("chk_date");
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		String acpos_tid 	= checkStr(request.getParameter("acpos_tid"), "");  //거래번호
		
		if(pSdate == null || "".equals(pSdate)){
			pSdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pSdate = pSdate.toString().replaceAll("-", "");
		}
		
		if(pEdate == null || "".equals(pEdate)){
			pEdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pEdate = pEdate.toString().replaceAll("-", "");
		}
		
		params.put("sdate"    , checkStr(pSdate, ""));
		params.put("edate"    , checkStr(pEdate, ""));
		
		params.put("acpos_tid", acpos_tid);	
		params.put("chk_date", chk_date == null || chk_date.equals("") ? "1d":chk_date);
		
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		String target = "/harex/trans_mst_cpn_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/trans_mst_cpn_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectTransMstCpn(params);
			listCnt= service.selectTransMstCpnCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 
		
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		request.setAttribute("sdate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));
		request.setAttribute("edate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));
		
		log.debug("### TransMstController selectTransMstCpn END ###");
		
		return target;
	}
	
	/**
	 * 거래내역조회_멤버십할인
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/trans_mst_ms_dc_list.st")
	public String selectTransMstMsDc(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### TransMstController selectTransMstMsDc START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<TransMst> list = null;
		TransMstService service = new TransMstService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String pSdate		= request.getParameter("sdate"); // 시작일
		String pEdate	 	= request.getParameter("edate"); // 종료일
		String chk_date	= request.getParameter("chk_date");
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		String acpos_tid 		= checkStr(request.getParameter("acpos_tid"), "");  //거래번호
		
		if(pSdate == null || "".equals(pSdate)){
			pSdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pSdate = pSdate.toString().replaceAll("-", "");
		}
		
		if(pEdate == null || "".equals(pEdate)){
			pEdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pEdate = pEdate.toString().replaceAll("-", "");
		}
		
		params.put("sdate"    , checkStr(pSdate, ""));
		params.put("edate"    , checkStr(pEdate, ""));
		
		params.put("acpos_tid", acpos_tid);	
		params.put("chk_date", chk_date == null || chk_date.equals("") ? "1d":chk_date);
		
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		String target = "/harex/trans_mst_ms_dc_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/trans_mst_ms_dc_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectTransMstMsDc(params);
			listCnt= service.selectTransMstMsDcCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		request.setAttribute("sdate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));
		request.setAttribute("edate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));
		
		log.debug("### TransMstController selectTransMstMsDc END ###");
		
		return target;
	}
	
	/**
	 * 거래내역조회_멤버십포인트
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/trans_mst_ms_use_list.st")
	public String selectTransMstMsUse(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### TransMstController selectTransMstMsUse START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<TransMst> list = null;
		TransMstService service = new TransMstService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String pSdate		= request.getParameter("sdate"); // 시작일
		String pEdate	 	= request.getParameter("edate"); // 종료일
		String chk_date	= request.getParameter("chk_date");
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		String acpos_tid 		= checkStr(request.getParameter("acpos_tid"), "");  //거래번호
		
		if(pSdate == null || "".equals(pSdate)){
			pSdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pSdate = pSdate.toString().replaceAll("-", "");
		}
		
		if(pEdate == null || "".equals(pEdate)){
			pEdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pEdate = pEdate.toString().replaceAll("-", "");
		}
		
		params.put("sdate"    , checkStr(pSdate, ""));
		params.put("edate"    , checkStr(pEdate, ""));
		
		params.put("acpos_tid", acpos_tid);	
		params.put("chk_date", chk_date == null || chk_date.equals("") ? "1d":chk_date);
		
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		String target = "/harex/trans_mst_ms_use_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/trans_mst_ms_use_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectTransMstMsUse(params);
			listCnt= service.selectTransMstMsUseCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		request.setAttribute("sdate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));
		request.setAttribute("edate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));
		
		log.debug("### TransMstController selectTransMstMsUse END ###");
		
		return target;
	}
	
	/**
	 * 거래내역조회_멤버십적립
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/trans_mst_ms_save_list.st")
	public String selectTransMstMsSave(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### TransMstController selectTransMstMsSave START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<TransMst> list = null;
		TransMstService service = new TransMstService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String pSdate		= request.getParameter("sdate"); // 시작일
		String pEdate	 	= request.getParameter("edate"); // 종료일
		String chk_date	= request.getParameter("chk_date");
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		String acpos_tid 		= checkStr(request.getParameter("acpos_tid"), "");  //거래번호
		
		if(pSdate == null || "".equals(pSdate)){
			pSdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pSdate = pSdate.toString().replaceAll("-", "");
		}
		
		if(pEdate == null || "".equals(pEdate)){
			pEdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pEdate = pEdate.toString().replaceAll("-", "");
		}
		
		params.put("sdate"    , checkStr(pSdate, ""));
		params.put("edate"    , checkStr(pEdate, ""));
		
		params.put("acpos_tid", acpos_tid);	
		params.put("chk_date", chk_date == null || chk_date.equals("") ? "1d":chk_date);
		
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		String target = "/harex/trans_mst_ms_save_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/trans_mst_ms_save_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectTransMstMsSave(params);
			listCnt= service.selectTransMstMsSaveCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		}
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		request.setAttribute("sdate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));
		request.setAttribute("edate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));
		
		log.debug("### TransMstController selectTransMstMsSave END ###");
		
		return target;
	}
	
	/**
	 * 거래내역조회_스탬프
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/trans_mst_stamp_list.st")
	public String selectTransMstStamp(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### TransMstController selectTransMstStamp START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<TransMst> list = null;
		TransMstService service = new TransMstService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String pSdate		= request.getParameter("sdate"); // 시작일
		String pEdate	 	= request.getParameter("edate"); // 종료일
		String chk_date	= request.getParameter("chk_date");
		String acpos_tid 	= checkStr(request.getParameter("acpos_tid"), "");  //거래번호
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		if(pSdate == null || "".equals(pSdate)){
			pSdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pSdate = pSdate.toString().replaceAll("-", "");
		}
		
		if(pEdate == null || "".equals(pEdate)){
			pEdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pEdate = pEdate.toString().replaceAll("-", "");
		}
		
		params.put("sdate"    , checkStr(pSdate, ""));
		params.put("edate"    , checkStr(pEdate, ""));
		
		params.put("acpos_tid", acpos_tid);	
		params.put("chk_date", chk_date == null || chk_date.equals("") ? "1d":chk_date);
		
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		String target = "/harex/trans_mst_stamp_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/trans_mst_stamp_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectTransMstStamp(params);
			listCnt= service.selectTransMstStampCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		request.setAttribute("sdate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));
		request.setAttribute("edate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));
		
		log.debug("### TransMstController selectTransMstStamp END ###");
		
		return target;
	}
	
	/**
	 * 거래내역조회 - 팝업
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/trans_mst_s_list_pop.st")
	public String selectTransMstPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### TransMstController selectTransMstPop START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		TransMstService service = new TransMstService(); 
		
		TransMstCpn cpn 		= new TransMstCpn();	  // 거래내역조회_쿠폰 
		TransMstMsDc msDc 	= new TransMstMsDc();  // 거래내역조회_멤버십할인
		TransMstMsUse msUse 	= new TransMstMsUse(); // 거래내역조회_멤버십포인트
		TransMstSave msSave 	= new TransMstSave();   // 거래내역조회_멤버십적립
		TransMstStamp stamp 	= new TransMstStamp(); // 거래내역조회_스탬프
		
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String pTrDate		= checkStr(request.getParameter("tr_date"), ""); // 거래일자
		String acpos_tid 	= checkStr(request.getParameter("acpos_tid"), "");  //거래번호
		
		if(pTrDate == null || "".equals(pTrDate)){
			pTrDate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pTrDate = pTrDate.toString().replaceAll("-", "");
		}
		
		params.put("sdate"    , checkStr(pTrDate, ""));
		params.put("edate"    , checkStr(pTrDate, ""));
		
		params.put("acpos_tid", acpos_tid);	
		
		//조회
		if(!"".equals(pTrDate) && !"".equals(acpos_tid)){
			cpn 		= (TransMstCpn) service.selectTransMstCpnPop(params);
			msDc 	= (TransMstMsDc)service.selectTransMstMsDcPop(params);
			msUse 	= (TransMstMsUse)service.selectTransMstMsUsePop(params);
			msSave 	= (TransMstSave)service.selectTransMstMsSavePop(params);
			stamp 	= (TransMstStamp) service.selectTransMstStampPop(params);
		}

		request.setAttribute("params", params);
		request.setAttribute("cpn", cpn);
		request.setAttribute("msDc", msDc);
		request.setAttribute("msUse", msUse);
		request.setAttribute("msSave", msSave);
		request.setAttribute("stamp", stamp);
		
		request.setAttribute("tr_date", pTrDate);
		
		log.debug("### TransMstController selectTransMstPop END ###");
		
		return "/harex/trans_mst_s_list_pop";
	}
}
