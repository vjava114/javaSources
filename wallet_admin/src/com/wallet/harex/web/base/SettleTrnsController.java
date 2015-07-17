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

import com.wallet.harex.common.JDate;
import com.wallet.harex.model.SettleTrns;
import com.wallet.harex.service.SettleTrnsService;
import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;

@Controller
public class SettleTrnsController extends CommonController {

	private Logger log = Log.getLogger("logs");
	
	/**
	 * 정산관리 > 거래내역조회
	 * @param 	sdate					시작일
	 * @param 	edate					종료일
	 * @return	lis						거래내역목록
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/settle_trns_list.hx")
	public String selectSettleTrnsList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### SettleTrns selectSettleTrnsList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<SettleTrns> list = null;
		SettleTrnsService service = new SettleTrnsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String chk_date		= request.getParameter("chk_date");
		
		String pSdate		= request.getParameter("sdate"); // 시작일
		String pEdate	 	= request.getParameter("edate"); // 종료일
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // 엑셀출력 여부
		String pCompId 	= checkStr(request.getParameter("comp_id"), "");  // 제휴사
		String pBrandId 	= checkStr(request.getParameter("brand_id"), ""); // 브랜드
		String pRgType  	= checkStr(request.getParameter("region_type"), "");  // 지역
		String pKShopId 	= checkStr(request.getParameter("shopSel"), ""); // 가맹점
		String pAcposTid 	= checkStr(request.getParameter("acposTid"), ""); // 복합결제 거래번호
		String pPhoneNo  	= checkStr(request.getParameter("phoneNo"), ""); // 핸드폰번호
		String pStatus 	= checkStr(request.getParameter("status"), "ok"); // 승인결과
		
		
		JDate tDate = new JDate();
		if(pSdate == null || "".equals(pSdate)){
			pSdate = tDate.getFormattedDate("yyyyMMdd");
		} else {
			pSdate = pSdate.toString().replaceAll("-", "");
		}
		
		if(pEdate == null || "".equals(pEdate)){
			pEdate = tDate.getFormattedDate("yyyyMMdd");
		} else {
			pEdate = pEdate.toString().replaceAll("-", "");
		}
		
		params.put("sdate"    	, checkStr(pSdate, ""));
		params.put("edate"    	, checkStr(pEdate, ""));
		params.put("kShopId"  	, pKShopId); 
		params.put("brandId"  	, pBrandId);
		params.put("region_type", pRgType);
		params.put("acposTid" 	, pAcposTid);  
		params.put("compId"   	, pCompId);
		params.put("phoneNo"  	, pPhoneNo);
		params.put("status"   	, pStatus);
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int ListCnt = 0; // 총 목록수
		//조회
		list = service.selectSettleTrnsList(params);
		ListCnt = service.selectSettleTrnsListCnt(params);
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		params.put("chk_date", chk_date == null || chk_date.equals("") ? "1d":chk_date);
		params.put("compId", pCompId == null || pCompId.equals("") ? "none":pCompId);
		params.put("brandId", pBrandId == null || pBrandId.equals("") ? "none":pBrandId);
		params.put("shopSel", pKShopId == null || pKShopId.equals("") ? "none":pKShopId);
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		request.setAttribute("sdate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));
		request.setAttribute("edate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));

		//로그처리/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		HashMap<String,Object> logParams = new HashMap<String,Object>();
		MwAdAccessLogService logSvr = new MwAdAccessLogService();

		try {

			params.put("PageURL", request.getRequestURL());
			
			logParams.put("part", "HAREX");
			logParams.put("admin_id", getSessionMgrId(request));
			logParams.put("msg", params.toString());
			
			logSvr.insertAccessLogReg(logParams);
			
			logSvr.commit();
		} catch (Exception e) {
			logSvr.rollback();
		}
		//로그처리/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		log.debug("### SettleTrns selectSettleTrnsList  END ###");
		
		String target = "harex/settle_trns_list";
		// 엑셀 저장일 경우.. 경로 변경 
		if("Y".equals(excel))
			target = "harex/settle_trns_list_excel";
		return target;
	}
	
	/**
	 * 일별현황관리 리스트
	 * @param 	pMonth 	조회월
	 * @param 	status 	승인결과
	 * @return	list				일별현황관리 리스트
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/settle_daily_list.hx")
	public String selectSettleTrnsDaily(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### SettleTrns selectSettleTrnsDaily START ###");
		String pMonth = request.getParameter("month"); 	// 조회월
		String pStatus = request.getParameter("status");	// 정상: 'ok' / 취소: 'cancel'
		
		if(pMonth == null || "".equals(pMonth)){
			JDate tDate = new JDate();
			pMonth = tDate.getFormattedDate("yyyyMM");
			pStatus = "ok";
		}
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<SettleTrns> list = null;
		SettleTrnsService service = new SettleTrnsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("pMonth", checkStr(pMonth, ""));
		params.put("pStatus", checkStr(pStatus, ""));
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//조회
		list = service.selectSettleTrnsDaily(params);
		int ListCnt = service.selectSettleTrnsDailyCnt(params); 		// 총 목록 수
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());	
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		request.setAttribute("List", list);
		request.setAttribute("pMonth", pMonth);
		request.setAttribute("pStatus", pStatus);
		
		log.debug("### SettleTrns selectSettleTrnsDaily  END ###");
		
		return "harex/settle_daily_list";
	}
	
	/**
	 * 정산관리 > 거래내역조회 > 쿠폰상세정보 팝업
	 * @param 	custId 	고객번호
	 * @param 	cpnId 	쿠폰번호
	 * @return	list		쿠폰상세정보 팝업
	 */
	@RequestMapping(value="/harex/settle_trns_cou_pop.hx")
	public String selectSettleCouponPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SettleTrns selectSettleCouponPop START ###");
		String pUbpayId 	= checkStr(request.getParameter("custId"), ""); 	// ubpay_id = cust_id  
		String pCpnId 		= checkStr(request.getParameter("cpnId"), ""); 	// 쿠폰ID
		String pPhoneNo 	= checkStr(request.getParameter("phoneNo"), ""); 	// 핸드폰번호
		String pApprDate 	= checkStr(request.getParameter("apprDate"), ""); 	// 승인일시
    
		try{
			
			//조회
			HashMap<String,Object> params = new HashMap<String,Object>(); // 검색조건 param
			
			SettleTrns settleTrns = null;
			
			if(!"".equals(pUbpayId) && !"".equals(pCpnId)){
				/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
				SettleTrnsService service = new SettleTrnsService();
				
				params.put("ubpayId", pUbpayId);
				params.put("cpnId", pCpnId);
				
				settleTrns = service.selectSettleCouponPop(params);
				
				/**
				 *   고객이름 복호화 처리 2012.10.25 userName 삭제처리
				 */

				if(!"".equals(pApprDate) && pApprDate.length() == 14){
					pApprDate = pApprDate.substring(0, 4) + "-" + pApprDate.substring(4, 6) + "-" + pApprDate.substring(6, 8) + " "
							       + pApprDate.substring(8, 10) + ":" + pApprDate.substring(10, 12) + ":" + pApprDate.substring(12, 14);
				}
			}
				
			request.setAttribute("params", params);
			request.setAttribute("phoneNo", pPhoneNo);
			request.setAttribute("apprDate", pApprDate); // 승인일시
			request.setAttribute("result", settleTrns); // 승인일시
				
		}catch(Exception e){
			log.debug("[ERROR]" + e.getMessage());
		}
		log.debug("### SettleTrns selectSettleCouponPop  END ###");
		
		return "harex/settle_trns_cou_pop";
	}
	
	/**
	 * 정산관리 > 거래내역조회 > 멤버십상세정보 팝업
	 * @param 	custId 	고객번호
	 * @param 	cpnId 	쿠폰번호
	 * @return		list			할인쿠폰상세정보 팝업
	 */
	@RequestMapping(value="/harex/settle_trns_mem_pop.hx")
	public String selectSettleMemberPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SettleTrns selectSettleMemberPop START ###");
		String pUbpayId 		= checkStr(request.getParameter("custId"), ""); 	// ubpay_id = cust_id  
		String pMembId 		= checkStr(request.getParameter("membId"), ""); 	// 쿠폰ID
		String pPhoneNo 		= checkStr(request.getParameter("phoneNo"), ""); 	// 핸드폰번호
		String pApprDate 		= checkStr(request.getParameter("apprDate"), ""); 	// 승인일시
		String pCardNo 		= checkStr(request.getParameter("cardNo"), ""); 	// 카드번호
	    
		try{
			//조회
			HashMap<String,Object> params = new HashMap<String,Object>(); // 검색조건 param
			SettleTrns settleTrns = null;
			
			if(!"".equals(pUbpayId) && !"".equals(pMembId)){
				/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
				SettleTrnsService service = new SettleTrnsService();
				
				params.put("ubpayId", pUbpayId);
				params.put("membId", pMembId);
				
				settleTrns = service.selectSettleMemberPop(params);
				
				/**
				 * TODO:고객이름 복호화 처리 -> 2012.10.16 - 복호화 변경후 다시 적용하기로 함. name 변수 변경하기 - by kma 
				 * 2012.10.25 userName 삭제처리
				 */
				
				if(!"".equals(pApprDate) && pApprDate.length() == 14){
					pApprDate = pApprDate.substring(0, 4) + "-" + pApprDate.substring(4, 6) + "-" + pApprDate.substring(6, 8) + " "
							       + pApprDate.substring(8, 10) + ":" + pApprDate.substring(10, 12) + ":" + pApprDate.substring(12, 14);
				}
			}

			request.setAttribute("resultMap", settleTrns);
			request.setAttribute("params", params);
			request.setAttribute("phoneNo", pPhoneNo);
			request.setAttribute("apprDate", pApprDate); // 승인일시
			request.setAttribute("cardNo", pCardNo); // 카드번호
			
		}catch(Exception e){
			log.debug(e.getMessage());
		}
		log.debug("### SettleTrns selectSettleMemberPop  END ###");
		
		return "harex/settle_trns_mem_pop";
	}
	
	/**
	 * 정산관리 > 정산DATA조회
	 * @param 	custId 	고객번호
	 * @param 	cpnId 	쿠폰번호
	 * @return		list			정산DATA조회
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/settle_data_list.hx")
	public String selectSettleDataList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("### SettleTrns selectSettleDataList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<SettleTrns> list = null;
		int ListCnt = 0;  // 목록 총 갯수
		SettleTrnsService service = new SettleTrnsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String chk_date	= request.getParameter("chk_date");
		String compId     	= checkStr(request.getParameter("compId"),"");
		String brandId    	= checkStr(request.getParameter("brandId"),"");
		String regionType	= checkStr(request.getParameter("regionType"), "");  // 지역
		String shopId		= checkStr(request.getParameter("shopId"), "");  // 가맹점ID
		String couponId 	= checkStr(request.getParameter("couponId"), ""); // 쿠폰ID
		
		String sDate		= checkStr(request.getParameter("sDate"), ""); // 시작일
		String eDate		= checkStr(request.getParameter("eDate"), ""); // 종료일
		String tempCpn	= checkStr(request.getParameter("couponId"), "");  // 쿠폰번호,쿠폰타입
		
		String excel			= checkStr(request.getParameter("excel"), "N");  // 엑셀출력 여부
		
		JDate date = new JDate();
		
		if("".equals(sDate)){
			sDate = date.getFormattedDate("yyyyMMdd");
		} else {
			sDate = sDate.toString().replaceAll("-", "");
		}
		if("".equals(eDate)){
			eDate = date.getFormattedDate("yyyyMMdd");
		} else {
			eDate = eDate.toString().replaceAll("-", "");
		}
		
		params.put("compId", compId);
		params.put("brandId", brandId);
		params.put("sDate", sDate);
		params.put("eDate", eDate);
		params.put("shopId", shopId);
		params.put("couponId", couponId);
		params.put("regType", regionType);
		
		//페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//조회
		list = service.selectSettleDataList(params);
		ListCnt = service.selectSettleDataListCnt(params); 		// 총 목록 수
		
		request.setAttribute("List", list);
			
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		request.setAttribute("chk_date",   chk_date == null || chk_date.equals("") ? "1d":chk_date);
		request.setAttribute("compId",     compId == null || compId.equals("") ? "none":compId);
		request.setAttribute("brandId",    brandId == null || brandId.equals("") ? "none":brandId);
		request.setAttribute("shopId",     shopId == null || shopId.equals("") ? "none":shopId);
		request.setAttribute("regionType", regionType);
		
		params.put("couponId", tempCpn); // 쿠폰콤보박스 - 요청한 값으로 원복하여 보내줌.
		request.setAttribute("sDate", sDate.substring(0, 4) + "-" + sDate.substring(4, 6) + "-" + sDate.substring(6, 8));
		request.setAttribute("eDate", eDate.substring(0, 4) + "-" + eDate.substring(4, 6) + "-" + eDate.substring(6, 8));
		request.setAttribute("params", params);
		
		log.debug("### SettleTrns selectSettleDataList  END ###" + list.size());
		
		String target = "harex/settle_data_list";
		// 엑셀 저장일 경우.. 경로 변경 
		if("Y".equals(excel))
			target = "harex/settle_data_list_excel";
		
		return target;
	}
	
	/**
	 * 정산관리 > 정산DATA조회 > 쿠폰상세정보
	 * @param 	custId 	고객번호
	 * @param 	cpnId 	쿠폰번호
	 * @return		list			정산DATA조회
	 */
	@RequestMapping(value="/harex/settle_data_cpn_pop.hx")
	public String selectSettleDataCpnInfoPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SettleTrns selectSettleDataCnpInfoPop START ###");
		String pCpnId = checkStr(request.getParameter("cpnId"));
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<SettleTrns> list = null;
		SettleTrnsService service = new SettleTrnsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		
		params.put("cpnId", pCpnId);

		//조회
		list = service.selectSettleDataCpnInfoPop(params);
		request.setAttribute("List", list);
		
		for(SettleTrns settleTrns:list){
			resultMap.put("cpnId", settleTrns.getCpnId()); 
			resultMap.put("compId", settleTrns.getCompId()); 
			resultMap.put("compName", settleTrns.getCompName()); 
			resultMap.put("pointDupUsable", settleTrns.getPointDupUsable()); 
			resultMap.put("cpnDupUsableYn", settleTrns.getCpnDupUsableYn()); 
			resultMap.put("cpnSort", settleTrns.getCpnSort()); 
			resultMap.put("minPayPrice", settleTrns.getMinPayPrice()); 
			resultMap.put("maxDicPrice", settleTrns.getMaxDicPrice()); 
			resultMap.put("brandId", settleTrns.getBrandId()); 
			resultMap.put("brandName", settleTrns.getBrandName()); 
			resultMap.put("payCompId", settleTrns.getPayComopId()); 
			resultMap.put("payCompName", settleTrns.getPayCompName()); 
		}
		request.setAttribute("resultMap", resultMap);		
		
		log.debug("### SettleTrns selectSettleDataCpnInfoPop  END ###");
		
		return "harex/settle_data_cpn_pop";
	}
	
	/**
	 * 정산관리 > 정산DATA조회 > 쿠폰결제금액 목록
	 * @param 	custId 	고객번호
	 * @param 	cpnId 	쿠폰번호
	 * @return		list			총 쿠폰결제금액 내역
	 */
	@RequestMapping(value="/harex/settle_data_tot_pop.hx")
	public String selectSettleDataCpnTotPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SettleTrns selectSettleDataCpnTotPop START ###");
		String pDate = checkStr(request.getParameter("date"));
		String pShopId = checkStr(request.getParameter("shopId"));
		String pCpnName = checkStr(request.getParameter("cpnName"));
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<SettleTrns> list = null;
		SettleTrnsService service = new SettleTrnsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("date", pDate);
		params.put("shopId", pShopId);
		
		//조회
		list = service.selectSettleDataCpnTotPop(params);
		request.setAttribute("List", list);
		request.setAttribute("cpnName", pCpnName);
		
		log.debug("### SettleTrns selectSettleDataCpnTotPop  END ###");
		
		return "harex/settle_data_tot_pop";
	}
}
