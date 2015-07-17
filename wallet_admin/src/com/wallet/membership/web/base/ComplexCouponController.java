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
import com.wallet.membership.model.custom.ComplexCoupon;
import com.wallet.membership.service.custom.ComplexCouponService;

@Controller
public class ComplexCouponController extends CommonController {
	private final String PAGE_CODE = "COMPLEX_COUPON_LIST";
	private Logger log = Log.getLogger("logs");

	public ComplexCouponController() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @Method Name : ComplexCouponList
	 * @Description : 멤버십(카드정보) 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.21
	 */
	@RequestMapping(value="/member/complex_coupon_list.ms")
	public String ComplexCouponList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ComplexCoupon> complexCouponList = null;
		ComplexCouponService complexCouponService = new ComplexCouponService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("compNm", checkStr(request.getParameter("compNm"), "")); //-- 선택된 쿠폰구분
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || "".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 

		String compName = checkStr(request.getParameter("compName"), "");
		String cpnName = checkStr(request.getParameter("cpnName"), "");
		String partV = checkStr(request.getParameter("partV"), "");

		params.put("compName", compName); //제휴사명
		params.put("cpnName", cpnName); //쿠폰명
		params.put("partV", partV); 
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		log.debug("@@@@@@@@@@ ComplexCouponList params : "+ params); //##
		
		complexCouponList = complexCouponService.selectComplexCouponList(params); //-- 목록조회
		int complexCouponListCnt = complexCouponService.selectComplexCouponListCnt(params); //-- 총 목록 수
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, complexCouponListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("complexCouponList", complexCouponList);
		request.setAttribute("compName", compName); //제휴사명
		request.setAttribute("cpnName", cpnName); //쿠폰명
		request.setAttribute("partV", partV); //쿠폰명
		request.setAttribute("ra_stat", params.get("ra_stat"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt", params.get("se_termOpt"));
		
		params.clear();
		return "member/complex_coupon_list";
	}


	
	/**
	 * @Method Name : ComplexCouponEditor
	 * @Description : 복합결제 정보 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.21
	 */
	@RequestMapping(value="/member/complex_coupon_editor.ms")
	public String ComplexCouponEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ComplexCouponService complexCouponService = new ComplexCouponService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("cpnId", checkStr(request.getParameter("cpnId"), "")); 
		
		List<ComplexCoupon> complexStoreList = complexCouponService.selectComplexStore(params);
		List<ComplexCoupon> complexBankList = complexCouponService.selectComplexBank(params);
		List<ComplexCoupon> complexPayCompList = complexCouponService.selectComplexPayComp(params);

		
		log.debug("@@@@@@@@@@ ComplexCouponList params : "+ params); //##
		
		ComplexCoupon aComplexCoupon = (ComplexCoupon) complexCouponService.selectAComplexCoupon(params);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("aComplexCoupon", aComplexCoupon);
		request.setAttribute("complexStoreList", complexStoreList);
		request.setAttribute("complexBankList", complexBankList);
		request.setAttribute("complexPayCompList", complexPayCompList);
		
		params.clear();
		return "member/complex_coupon_editor";
	}
	
	
	/**
	 * @Method Name : ComplexCouponEditorAct
	 * @Description : 복합결제 정보를 수정한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.24
	 */
	@RequestMapping(value="/member/complex_coupon_editorAct.ms")
	public String ComplexCouponEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int result = 1; //-- 이 메소드의 실행결과 0:실패, 1:성공
		
		ComplexCouponService complexCouponService = new ComplexCouponService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		String regUser = getSessionMgrId(request);
		
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		try{
			
			params.put("cpnId", cpnId); 
			params.put("partV", checkStr(request.getParameter("partV"), "")); 
			params.put("membId", checkStr(request.getParameter("membId"), "")); 
			params.put("compId", checkStr(request.getParameter("compId"), "")); 
			params.put("branId", checkStr(request.getParameter("branId"), "")); 
			params.put("pointDupUsableYN", checkStr(request.getParameter("pointDupUsableYN"), "N")); 
			params.put("cpnDupUsableYN", checkStr(request.getParameter("cpnDupUsableYN"), "N"));
			
			String cpnDiscType =  checkStr(request.getParameter("cpnDiscType"), "01");
			params.put("cpnDiscType", cpnDiscType);
			if(cpnDiscType.equals("01")){ //-- 쿠폰할인유형이 정액할인 쿠폰인 경우,
				params.put("cpnDiscAmount", checkStr(request.getParameter("cpnDiscAmount"), ""));
			}
			else if(cpnDiscType.equals("02")){ //-- 쿠폰할인유형이 정율할인크폰인 경우 
				params.put("cpnDiscAmount", checkStr(request.getParameter("cpnDiscRate"), ""));
				params.put("maxDicPrice", checkStr(request.getParameter("maxDicPrice"), ""));
				params.put("roundType", checkStr(request.getParameter("roundType"), ""));
				params.put("dcUnit", checkStr(request.getParameter("dcUnit"), ""));
			}

			params.put("minPayPrice", checkStr(request.getParameter("minPayPrice"), ""));
			params.put("dcNotice", checkStr(request.getParameter("dcNotice"), ""));
			
			//-- 쿠폰사용 가맹점 목록
			params.put("shopSvcList", checkStr(request.getParameter("shopSvcList"), ""));
			String[] tmpStoreList = request.getParameterValues("stores");
			
			//-- 금융사 목록
			params.put("bankSvcList", checkStr(request.getParameter("bankSvcList"), ""));
			String[] tmpFinanceList = request.getParameterValues("finances");
			
			//-- 결제사 목록
			params.put("paycomSvcList",checkStr(request.getParameter("paycomSvcList"), ""));
			String[] tmpSettlementList = request.getParameterValues("settlements");
			
		
			log.debug("@@@@@@@@@@ ComplexCouponList params : "+ params); //##

			params.put("chgUser", regUser); //-- 수정자ID
			result = complexCouponService.updateComplexCoupon(params); //-- 쿠폰값 변경
			result = result * complexCouponService.updateCouponList(params);
			
			
			params.clear();
			params.put("cpnId", cpnId);
			complexCouponService.deleteComplexStore(params);
			
			if(!"A".equals(checkStr(request.getParameter("ra_ShopOpt"), "")) && tmpStoreList != null){
				for(int i=0; i<tmpStoreList.length; i++){
					params.clear();
					
					params.put("cpnId", cpnId);
					params.put("compId", tmpStoreList[i].substring(tmpStoreList[i].lastIndexOf(":")+1));
					params.put("regUser", regUser); //-- 수정자ID
					result = result * complexCouponService.insertComplexStore(params);
				}
			}
			
			params.clear();
			params.put("cpnId", cpnId);
			complexCouponService.deleteComplexBank(params);
						
			if(!"A".equals(checkStr(request.getParameter("ra_FinanceOpt"), "")) && tmpFinanceList != null){
				for(int i=0; i<tmpFinanceList.length; i++){
					params.clear();
					
					params.put("cpnId", cpnId);
					params.put("bankId", tmpFinanceList[i].substring(tmpFinanceList[i].lastIndexOf(":")+1));
					params.put("regUser", regUser); //-- 수정자ID
					result = result * complexCouponService.insertComplexBank(params);
				}
			}

			
			params.clear();
			params.put("cpnId", cpnId);
			complexCouponService.deleteComplexPayComp(params);
			

			if(!"A".equals(checkStr(request.getParameter("ra_SettlementOpt"), "")) && tmpSettlementList != null){
				for(int i=0; i<tmpSettlementList.length; i++){
					params.clear();
					
					params.put("cpnId", cpnId);
					params.put("payCompId", tmpSettlementList[i].substring(tmpSettlementList[i].lastIndexOf(":")+1));
					params.put("regUser", regUser); //-- 수정자ID
					result = result * complexCouponService.insertComplexPayComp(params);
				}
			}
			
			
			
//			for(int i=0; i<tmpFinanceList.length; i++){
//				params.clear();
//				
//				params.put("cpnId", cpnId);
//				params.put("bankId", tmpFinanceList[i]);
//				params.put("regUser", "SESSION_ID"); //-- 수정자ID
//				
//				result = result * complexCouponService.insertComplexBank(params);
//			}
//
//
//			params.clear();
//			params.put("cpnId", cpnId);
//			complexCouponService.deleteComplexPayComp(params);
//			
//			for(int i=0; i<tmpSettlementList.length; i++){
//				params.clear();
//				
//				params.put("cpnId", cpnId);
//				params.put("payCompId", tmpSettlementList[i]);
//				params.put("regUser", "SESSION_ID"); //-- 수정자ID
//				
//				result = result * complexCouponService.insertComplexPayComp(params);
//			}
			
			
			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("actResult", result + "");
			
			if(result == 0){
				complexCouponService.rollback();
				request.setAttribute("targetUrl", "/member/complex_coupon_editor.ms?cpnId=" + cpnId);
			}
			else{
				complexCouponService.commit();
				request.setAttribute("targetUrl", "/member/complex_coupon_list.ms");
			}
		}
		catch(Exception e){
			complexCouponService.rollback();
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/complex_coupon_editor.ms?cpnId=" + cpnId);
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
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
