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
import com.wallet.harex.model.OfferingCpn;
import com.wallet.harex.model.OfferingDtl;
import com.wallet.harex.model.OfferingMsDc;
import com.wallet.harex.model.OfferingMsSave;
import com.wallet.harex.model.OfferingMsStamp;
import com.wallet.harex.model.OfferingMsUse;
import com.wallet.harex.model.OfferingOrder;
import com.wallet.harex.model.OfferingPromo;
import com.wallet.harex.model.OfferingS;
import com.wallet.harex.service.OfferingEtcService;

@Controller
public class OfferingEtcController extends CommonController {

	private Logger log = Log.getLogger("logs");
	
	/**
	 * 복합결제 오퍼링 정보
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_s_list.st")
	public String selectOfferingS(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingS START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<OfferingS> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String acpos_tid 		= checkStr(request.getParameter("acpos_tid"), "");  //거래번호
		String mocapay_tid 	= checkStr(request.getParameter("mocapay_tid"), "");  //거래번호
		String excel 	 		= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		params.put("acpos_tid", acpos_tid);	
		params.put("mocapay_tid", mocapay_tid);	
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
			
		//조회
		String target = "/harex/offering_s_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/offering_s_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		
		try{
			//조회
			list = service.selectOfferingS(params);
			listCnt= service.selectOfferingSCnt(params);
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
		
		log.debug("### OfferingEtcController selectOfferingS END ###");
		
		return target;
	}
	
	/**
	 * 복합결제 오퍼링 쿠폰 상세정보
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_cpn_list.st")
	public String selectOfferingCpn(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingCpn START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<OfferingCpn> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //오퍼링 Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		params.put("offer_id", offer_id);	
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		

		String target = "/harex/offering_cpn_list"; // 결과 페이지
				
		if("Y".equals(excel)){
			target = "/harex/offering_cpn_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectOfferingCpn(params);
			listCnt= service.selectOfferingCpnCnt(params);
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
		
		log.debug("### OfferingEtcController selectOfferingCpn END ###");
		
		return target;
	}
	
	/**
	 * 복합결제 오퍼링 상세정보
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_dtl_list.st")
	public String selectOfferingDtl(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<OfferingDtl> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String acpos_tid 	= checkStr(request.getParameter("acpos_tid"), "");  //복합결제 거래번호
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //오퍼링 Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		params.put("acpos_tid", acpos_tid);
		params.put("offer_id", offer_id);	
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		
		String target = "/harex/offering_dtl_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/offering_dtl_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectOfferingDtl(params);
			listCnt= service.selectOfferingDtlCnt(params);
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
		
		log.debug("### OfferingEtcController selectOfferingDtl END ###");
		
		return target;
	}
	
	/**
	 * 복합결제 오퍼링 상세정보
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */	
	@RequestMapping(value="/harex/offering_ms_dc_list.st")
	public String selectOfferingMsDc(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingMsDc START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<OfferingMsDc> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //오퍼링 Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		params.put("offer_id", offer_id);	
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		//조회
		
		String target = "/harex/offering_ms_dc_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/offering_ms_dc_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectOfferingMsDc(params);
			listCnt= service.selectOfferingMsDcCnt(params);
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
		
		log.debug("### OfferingEtcController selectOfferingMsDc END ###");
		
		return target;
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 적립정보
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_ms_save_list.st")
	public String selectOfferingMsSave(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingMsSave START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<OfferingMsSave> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //오퍼링 Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		params.put("offer_id", offer_id);	
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		//조회
		
		String target = "/harex/offering_ms_save_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/offering_ms_save_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectOfferingMsSave(params);
			listCnt= service.selectOfferingMsSaveCnt(params);
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
		
		log.debug("### OfferingEtcController selectOfferingMsSave END ###");
		
		return target;
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 스탬프정보
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_ms_stamp_list.st")
	public String selectOfferingMsStamp(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingMsStamp START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<OfferingMsStamp> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //오퍼링 Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		params.put("offer_id", offer_id);	
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		String target = "/harex/offering_ms_stamp_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/offering_ms_stamp_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectOfferingMsStamp(params);
			listCnt= service.selectOfferingMsStampCnt(params);
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
		
		log.debug("### OfferingEtcController selectOfferingMsStamp END ###");
		
		return target;
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 멤버쉽 사용정보
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_ms_use_list.st")
	public String selectOfferingMsUse(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingMsUse START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<OfferingMsUse> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //오퍼링 Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		params.put("offer_id", offer_id);	
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		String target = "/harex/offering_ms_use_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/offering_ms_use_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectOfferingMsUse(params);
			listCnt= service.selectOfferingMsUseCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 
		
		
		//조회
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		log.debug("### OfferingEtcController selectOfferingMsUse END ###");
		
		return target;
	}
	
	/**
	 * 복합결제 오퍼링 순위 정보
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_order_list.st")
	public String selectOfferingOrder(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingOrder START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<OfferingOrder> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String acpos_tid 	= checkStr(request.getParameter("acpos_tid"), "");  	//복합결제 거래번호
		String offer_id 		= checkStr(request.getParameter("offer_id"), ""); 		//오퍼링 Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		params.put("acpos_tid", acpos_tid);	
		params.put("offer_id", offer_id);	
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		//조회
		
		String target = "/harex/offering_order_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/offering_order_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectOfferingOrder(params);
			listCnt= service.selectOfferingOrderCnt(params);
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
		
		log.debug("### OfferingEtcController selectOfferingOrder END ###");
		
		return target;
	}
	
	/**
	 * 복합결제 오퍼링 프로모션정보
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_promotion_list.st")
	public String selectOfferingPromo(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingPromo START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<OfferingPromo> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String promo_cd 	= checkStr(request.getParameter("promo_cd"), "");  	//멤버십 Id
		String offer_id 		= checkStr(request.getParameter("offer_id"), ""); 		//오퍼링 Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		
		params.put("promo_cd", promo_cd);	
		params.put("offer_id", offer_id);	
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		//조회
		
		String target = "/harex/offering_promotion_list"; // 결과 페이지
		
		if("Y".equals(excel)){
			target = "/harex/offering_promotion_list_excel"; // 엑셀파일 출력
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//조회
			list = service.selectOfferingPromo(params);
			listCnt= service.selectOfferingPromoCnt(params);
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
		
		log.debug("### OfferingEtcController selectOfferingPromo END ###");
		
		return target;
	}
	
}
