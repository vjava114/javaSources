package com.wallet.harex.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.wallet.harex.model.PromotionInfo;
import com.wallet.harex.model.PromotionList;
import com.wallet.harex.service.CommonService;
import com.wallet.harex.service.PromotionService;

@Controller
public class PromotionController extends CommonController {
	
	private Logger log = Log.getLogger("logs");

	/**
	 * 결제기관별 (가맹점별) 프로모션 목록
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/promotion_list.hx")
	public String selectPromotionList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PromotionController PromotionList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<PromotionList> list = null;
		PromotionService service = new PromotionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String promoName	= checkStr(request.getParameter("promoName"), ""); // 가맹점명
		String shopId 			= checkStr(request.getParameter("shopId"), ""); // 가맹점ID

		params.put("promoName", promoName);
		params.put("shopId",    shopId);
		
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
		list = service.selectPromotionList(params);
		int ListCnt = service.selectPromotionListCnt(params); 		// 총 목록 수
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("params", params);
		request.setAttribute("List", list);

		log.debug("### PromotionController PromotionList END ###");
		
		return "/harex/promotion_list";
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 상세화면
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/promotion_info.hx")
	public String selectPromotionInfo(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PromotionController selectPromotionInfo START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<PromotionInfo> list = null;
		List<PromotionInfo> shopList = null;
		PromotionService service = new PromotionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String pCompId 		= checkStr(request.getParameter("comp_id"), "");
		String pCompNm 		= checkStr(request.getParameter("comp_nm"), "");
		String pBrandId 		= checkStr(request.getParameter("brand_id"), "");
		String pBrandNm 		= checkStr(request.getParameter("brand_nm"), "");
		String pShopId 		= checkStr(request.getParameter("shop_id"), "");
		String pShopNm 		= checkStr(request.getParameter("shop_nm"), "");
		String pPromoCd 		= checkStr(request.getParameter("promo_cd"), "");
		String pPromoNm 		= checkStr(request.getParameter("promo_nm"), "");
		
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		
		params.put("comp_id", 	pCompId);			//제휴사ID
		params.put("comp_nm", 	pCompNm);			//제휴사ID
		params.put("brand_id", 	pBrandId);			//브랜드ID
		params.put("brand_nm", 	pBrandNm);	//프로모션명
		params.put("shop_id", 	pShopId);				//가맹점ID
		params.put("shop_nm", 	pShopNm);	//프로모션명
		params.put("promo_cd", 	pPromoCd);		//프로모션CD
		params.put("promo_nm", 	pPromoNm);	//프로모션명
		params.put("shop_nm", 	pShopNm);	//프로모션명
		
		//브랜드별 가맹점 목록
		shopList = service.selectModifyShopList(params); 
		//조회
		list = service.selectPromotionInfo(params);
		
		params.put("period", 		list.get(0).getPromoDate());				//프로모션기간
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		request.setAttribute("shopList", shopList);
		request.setAttribute("nowPage", nowPage);

		log.debug("### PromotionController selectPromotionInfo END ###");
		
		return "/harex/promotion_info";
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보등록 정보
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/promotion_insert.hx")
	public String insertPromotionInfo(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("### PromotionController insertPromotionInfo START ###");
		
		List<PromotionInfo> payCompList = null;
		List<PromotionInfo> bankList = null;
		List<PromotionInfo> cardList = null;
		List<PromotionInfo> dcCntBasetList = null;
		List<PromotionInfo> dcUnitList = null;
		List<PromotionInfo> roundTypeList = null;
		
		PromotionService service = new PromotionService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String pSdate 		= checkStr(request.getParameter("sdate"), "");
		String pEdate   	= checkStr(request.getParameter("edate"), "");
		String pS1date 	= checkStr(request.getParameter("s1date"), "");
		String pE1date 	= checkStr(request.getParameter("e1date"), "");
		
		JDate tDate = new JDate();
		if("".equals(pSdate)){
			pSdate = tDate.getFormattedDate("yyyyMMdd");
		} else {
			pSdate = pSdate.toString().replaceAll("-", "");
		}
		if("".equals(pEdate)){
			pEdate = tDate.getFormattedDate("yyyyMMdd");
		} else {
			pEdate = pEdate.toString().replaceAll("-", "");
		}
		if("".equals(pS1date)){
			pS1date = tDate.getFormattedDate("yyyyMMdd");
		} else {
			pS1date = pS1date.toString().replaceAll("-", "");
		}
		if("".equals(pE1date)){
			pE1date = tDate.getFormattedDate("yyyyMMdd");
		} else {
			pE1date = pE1date.toString().replaceAll("-", "");
		}
		
		payCompList = service.selectPayCompList(); 
		bankList = service.selectBankList();
		cardList = service.selectCardList();
		dcCntBasetList = service.selectDcCntBaseList();
		dcUnitList = service.selectDcUnitList();
		roundTypeList  = service.selectRoundTypeList();
		
		request.setAttribute("sdate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));  // 프로모션 기간(시작일자)
		request.setAttribute("edate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));	 //프로모션 기간(종료일자)
		request.setAttribute("s1date", pS1date.substring(0, 4) + "-" + pS1date.substring(4, 6) + "-" + pS1date.substring(6, 8)); // 개별 프로모션 기간(시작일자)
		request.setAttribute("e1date", pE1date.substring(0, 4) + "-" + pE1date.substring(4, 6) + "-" + pE1date.substring(6, 8));	// 개별 프로모션 기간(종료일자)
		
		request.setAttribute("params", params);
		request.setAttribute("payCompList", payCompList);
		request.setAttribute("bankList", bankList);
		request.setAttribute("cardList", cardList);
		request.setAttribute("dcCntBaseList", dcCntBasetList);
		request.setAttribute("dcUnitList", dcUnitList);
		request.setAttribute("roundTypeList", roundTypeList);

		return "harex/promotion_insert";
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보등록 처리
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/harex/promotion_insert_proc.hx")
	public String insertPromotionProc(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("### PromotionController insertPromotionProc START ###");
		
		//조회조건값 세팅
		HashMap<String, Object> paramsPromo = new HashMap<String,Object>();
		HashMap<String, Object> paramsPromoDtl = new HashMap<String,Object>();
		HashMap<String, Object> paramsPromoShop = new HashMap<String,Object>();
		PromotionService service = new PromotionService();
		
		// 등록자 - TODO 세션 처리
		String regUser = getSessionMgrId(request);
		
		String promo_name         	= checkStr(request.getParameter("promo_name"), ""); 
		String sdate                  	= checkStr(request.getParameter("sdate"), "");
		String edate                  	= checkStr(request.getParameter("edate"), "");
		String service_id             	= checkStr(request.getParameter("service_id"), "");
		String comp_id               	= checkStr(request.getParameter("comp_id"), "");
		String brand_id               	= checkStr(request.getParameter("brand_id"), "");
		String shop_id[]             	= request.getParameterValues("shop_id");
		String payType               	= checkStr(request.getParameter("payType"), "");
		String cardSel                	= checkStr(request.getParameter("cardSel"), "");
		String bankSel                	= checkStr(request.getParameter("bankSel"), "");
		String dc_gubun              	= checkStr(request.getParameter("dc_gubun"), "");
		String dc_type                	= checkStr(request.getParameter("dc_type"), "");
		String dc_cnt_base          	= checkStr(request.getParameter("dc_cnt_base"), "");
		String promo_name_Individual	= checkStr(request.getParameter("promo_name_Individual"), "");
		String s1date                 	= checkStr(request.getParameter("s1date"), "");
		String e1date                 	= checkStr(request.getParameter("e1date"), "");
		String dc_unit                	= checkStr(request.getParameter("dc_unit"), "");  // 정율할인근사계산단위
		String round_type            	= checkStr(request.getParameter("round_type"), "");  // 정율할인근사계산
		
		String dc_rate               	= checkStr(request.getParameter("dc_rate"), "");
		String dc_price              	= checkStr(request.getParameter("dc_price"), "");
		String dc_cnt                	= checkStr(request.getParameter("dc_cnt"), "");
		String min_pay_price  		= checkStr(request.getParameter("min_pay_price"), "");
		String min_pay_price_prc 	= checkStr(request.getParameter("min_pay_price_prc"), "");
		String max_dc_price  		= checkStr(request.getParameter("max_dc_price"), "");
		
		int result = 0;
		CommonService commonService = new CommonService();
		String promoId = commonService.getPromoId().trim();
		
		try{
			
			if("card".equals(payType)){
				paramsPromo.put("bankCode", cardSel);
				
			} else if("bank".equals(payType)){
				paramsPromo.put("bankCode", bankSel);
			}
			paramsPromo.put("promoCd", 		promoId);
			paramsPromo.put("promoName",	promo_name);
			paramsPromo.put("startDt", 		sdate.toString().replaceAll("-", ""));
			paramsPromo.put("endDt", 			edate.toString().replaceAll("-", ""));
			paramsPromo.put("serviceId", 	service_id);
			paramsPromo.put("regId", 			regUser);
			paramsPromo.put("updateId", 		"");
			paramsPromo.put("compId", 		comp_id);
			paramsPromo.put("brdId", 			brand_id);
			
			
			/*정액:01 / 정률:02 에 따른 최소결제금액 처리*/
			if("01".equals(dc_type)){
				paramsPromoDtl.put("minPayPrice", min_pay_price_prc);		
				paramsPromoDtl.put("dcPrice", dc_price);
				
				// 정율데이터 "" 값처리
				paramsPromoDtl.put("dcRate", "");
				paramsPromoDtl.put("maxDcPrice", "");
				paramsPromoDtl.put("dcUnit", "");
				paramsPromoDtl.put("roundType", "");
				
			} else if("02".equals(dc_type)){
				paramsPromoDtl.put("minPayPrice", min_pay_price);				
				paramsPromoDtl.put("dcRate", dc_rate);
				paramsPromoDtl.put("maxDcPrice", max_dc_price);
				paramsPromoDtl.put("dcUnit", dc_unit);
				paramsPromoDtl.put("roundType", round_type);
				
				// 정액데이터 "" 값처리
				paramsPromoDtl.put("dcPrice", "");
			}
			paramsPromoDtl.put("dcType", dc_type);
			paramsPromoDtl.put("promoCd", promoId);
			paramsPromoDtl.put("dcGubun", dc_gubun);
			paramsPromoDtl.put("dcCntBase", dc_cnt_base);
			paramsPromoDtl.put("dcCnt", dc_cnt);
			paramsPromoDtl.put("promoName", promo_name_Individual);
			paramsPromoDtl.put("startDt", s1date.toString().replaceAll("-", ""));
			paramsPromoDtl.put("endDt", e1date.toString().replaceAll("-", ""));
			paramsPromoDtl.put("regId", regUser);
			
			result += service.insertPromotion(paramsPromo);
			result += service.insertPromotionDtl(paramsPromoDtl);
			
			for(int i=0; i < shop_id.length; i++){
				paramsPromoShop.clear();
				paramsPromoShop.put("shopId", shop_id[i]);
				paramsPromoShop.put("promoCd", promoId);
				paramsPromoShop.put("regId", regUser);
				result += service.insertPromotionShop(paramsPromoShop);
			}
			request.setAttribute("actResult", result + "");
			if (result == 0) {
				//rollback
				service.rollback();
				request.setAttribute("targetUrl", "/harex/promotion_insert.hx");
			} else {
				//commit
				service.commit();
				request.setAttribute("targetUrl", "/harex/promotion_list.hx");
			}
			
		} catch(Exception e){
			//rollback
			service.rollback();
			e.printStackTrace();
			log.debug(e);
			request.setAttribute("targetUrl", "/harex/promotion_insert.hx");
		} finally {
			return "common/result_message";
		}
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보수정
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/harex/promotion_modify.hx")
	public String selectPromotionUpdate(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("### PromotionController updatePromotionInfo START ###");
		
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		String promo_cd = checkStr(request.getParameter("promo_cd"), "");
		
		List<PromotionInfo> list = null;  // 수정화면 조회
		List<PromotionInfo> payCompList = null;
		List<PromotionInfo> bankList = null;
		List<PromotionInfo> cardList = null;
		List<PromotionInfo> dcCntBasetList = null;
		List<PromotionInfo> dcUnitList = null;
		List<PromotionInfo> roundTypeList = null;
		List<PromotionInfo> shopList = null;
		
		PromotionService service = new PromotionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		params.put("promo_cd", promo_cd);
		try{
			payCompList = service.selectPayCompList();  
			bankList = service.selectBankList();
			cardList = service.selectCardList();
			dcCntBasetList = service.selectDcCntBaseList();
			dcUnitList = service.selectDcUnitList();
			roundTypeList  = service.selectRoundTypeList();
			shopList = service.selectModifyShopList(params);
			
			list = service.selectPromotionUpdate(params);
			
			for(PromotionInfo info:list){
				result.put("promo_cd", info.getPromoCode());		  
				result.put("bank_code", info.getBankCode()); 		  
				result.put("promo_name", info.getPromoName()); 	  
				result.put("sdate", info.getStartDt().substring(0, 4) + "-" + info.getStartDt().substring(4, 6) + "-" + info.getStartDt().substring(6, 8));  		  
				result.put("edate", info.getEndDt().substring(0, 4) + "-" + info.getEndDt().substring(4, 6) + "-" + info.getEndDt().substring(6, 8)); 			  
				result.put("service_id", info.getServiceId()); 	  
				result.put("dc_gubun", info.getDcGubunCode()); 		  
				result.put("dc_type", info.getDcTypeCode());			  
				result.put("dc_rate", info.getDcRate()); 			  
				result.put("dc_price", info.getDcPrice()); 		  
				result.put("dc_unit", info.getDcUnit()); 			  
				result.put("round_type", info.getRoundType()); 	  
				result.put("min_pay_price", info.getMinPayPrice());;  
				result.put("max_dc_price", info.getMaxDcPrice());   
				result.put("dc_cnt_base", info.getDcCntBaseCode()); 	  
				result.put("dc_cnt", info.getDcCnt()); 			  
				result.put("promo_name_ind", info.getPromoNameInd());  
				result.put("s1date", info.getStartDtInd().substring(0, 4) + "-" + info.getStartDtInd().substring(4, 6) + "-" + info.getStartDtInd().substring(6, 8));	  
				result.put("e1date", info.getEndDtInd().substring(0, 4) + "-" + info.getEndDtInd().substring(4, 6) + "-" + info.getEndDtInd().substring(6, 8));
				result.put("comp_id", info.getCompId()); 			  
				result.put("comp_name", info.getCompName()); 			  
				result.put("brand_id", info.getBrandId()); 			  
				result.put("brand_name", info.getBrandName()); 			  
				result.put("service_name", info.getServiceName()); 			  
				
				request.setAttribute("result", result);
				request.setAttribute("payCompList", payCompList);
				request.setAttribute("bankList", bankList);
				request.setAttribute("cardList", cardList);
				request.setAttribute("dcCntBasetList", dcCntBasetList);
				request.setAttribute("dcUnitList", dcUnitList);
				request.setAttribute("roundTypeList", roundTypeList);
				request.setAttribute("shopList", shopList);
			}
			
			request.setAttribute("nowPage", nowPage);
		} catch (Exception e){
			e.printStackTrace();
			log.debug(e);
		}
		return "harex/promotion_modify";
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보등록 처리
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="/harex/promotion_modify_proc.hx")
	public String updatePromotionProc(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("### PromotionController updatePromotionProc START ###");
		
		//조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> paramsPromo = new HashMap<String,Object>();
		HashMap<String, Object> paramsPromoDtl = new HashMap<String,Object>();
		HashMap<String, Object> paramsPromoShop = new HashMap<String,Object>();
		PromotionService service = new PromotionService();
		
		// 등록자 - TODO 세션 처리
		String regUser = getSessionMgrId(request);
		
		String promo_cd        		= checkStr(request.getParameter("promo_cd"), ""); 
		String promo_name       	= checkStr(request.getParameter("promo_name"), ""); 
		String sdate                  	= checkStr(request.getParameter("sdate"), "");
		String edate                  	= checkStr(request.getParameter("edate"), "");
		String shop_id[]              	= request.getParameterValues("shop_id");
		String payType               	= checkStr(request.getParameter("payType"), "");
		String cardSel                	= checkStr(request.getParameter("cardSel"), "");
		String bankSel                	= checkStr(request.getParameter("bankSel"), "");
		String dc_gubun              	= checkStr(request.getParameter("dc_gubun"), "");
		String dc_type                	= checkStr(request.getParameter("dc_type"), "");
		String dc_cnt_base          	= checkStr(request.getParameter("dc_cnt_base"), "");
		String promo_name_Individual  = checkStr(request.getParameter("promo_name_Individual"), "");
		String s1date                 	= checkStr(request.getParameter("s1date"), "");
		String e1date                 	= checkStr(request.getParameter("e1date"), "");
		String dc_unit                	= checkStr(request.getParameter("dc_unit"), "");  // 정율할인근사계산단위
		String round_type            	= checkStr(request.getParameter("round_type"), "");  // 정율할인근사계산
		
		String dc_rate               	= checkStr(request.getParameter("dc_rate"), "");
		String dc_price              	= checkStr(request.getParameter("dc_price"), "");
		String dc_cnt                	= checkStr(request.getParameter("dc_cnt"), "");
		String min_pay_price  		= checkStr(request.getParameter("min_pay_price"), "");
		String min_pay_price_prc	= checkStr(request.getParameter("min_pay_price_prc"), "");
		String max_dc_price			= checkStr(request.getParameter("max_dc_price"), "");
		
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		
		int result = 0;

		try{
			
			if("card".equals(payType)){
				paramsPromo.put("bankCode", cardSel);
				
			} else if("bank".equals(payType)){
				paramsPromo.put("bankCode", bankSel);
			}
			
			paramsPromo.put("promoCd", 		promo_cd);
			paramsPromo.put("promoName",	promo_name);
			paramsPromo.put("startDt", 		sdate.toString().replaceAll("-", ""));
			paramsPromo.put("endDt", 			edate.toString().replaceAll("-", ""));
			paramsPromo.put("updateId", 		regUser);
			
			
			/*정액:01 / 정률:02 에 따른 최소결제금액 처리*/
			if("01".equals(dc_type)){
				paramsPromoDtl.put("minPayPrice", min_pay_price_prc);		
				paramsPromoDtl.put("dcPrice", dc_price);
				
			} else if("02".equals(dc_type)){
				paramsPromoDtl.put("minPayPrice", min_pay_price);				
				paramsPromoDtl.put("dcRate", dc_rate);
				paramsPromoDtl.put("maxDcPrice", max_dc_price);
				paramsPromoDtl.put("dcUnit", dc_unit);
				paramsPromoDtl.put("roundType", round_type);
			}
			
			paramsPromoDtl.put("dcType", dc_type);
			paramsPromoDtl.put("promoCd", promo_cd);
			paramsPromoDtl.put("dcGubun", dc_gubun);
			paramsPromoDtl.put("dcCntBase", dc_cnt_base);
			paramsPromoDtl.put("dcCnt", dc_cnt);
			paramsPromoDtl.put("promoName", promo_name_Individual);
			paramsPromoDtl.put("startDt", s1date.toString().replaceAll("-", ""));
			paramsPromoDtl.put("endDt", e1date.toString().replaceAll("-", ""));
			paramsPromoDtl.put("regId", regUser);
			
			
			result += service.updatePromotionPromo(paramsPromo);
			result += service.updatePromotionPromoDtl(paramsPromoDtl);
			
			params.put("promoCd", promo_cd);
			result += service.deletePromotion(params);
			
			
			for(int i=0; i < shop_id.length; i++){
				paramsPromoShop.clear();
				paramsPromoShop.put("shopId", shop_id[i]);
				paramsPromoShop.put("promoCd", promo_cd);
				paramsPromoShop.put("regId", regUser);
				result += service.insertPromotionShop(paramsPromoShop);
			}
			request.setAttribute("actResult", result + "");
			
			if (result == 0) {
				//rollback
				service.rollback();
				request.setAttribute("targetUrl", "/harex/promotion_modify.hx");
			} else {
				//commit
				service.commit();
				request.setAttribute("targetUrl", "/harex/promotion_list.hx?nowPage="+nowPage);
				
			}
			
		} catch(Exception e){
			//rollback
			service.rollback();
			e.printStackTrace();
			log.debug(e);
			request.setAttribute("targetUrl", "/harex/promotion_modify.hx");
		} finally {
			log.debug("### PromotionController updatePromotionProc END ###");
			return "common/result_message";
		}
	}
/*
	* 프로모션 가맹점 선택
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/promotion_sel_shop_pop.hx")
	public String selectPromotionShopSel(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PromotionController selectPromotionInfo START ###");
		
		HashMap<String,Object> params = new HashMap<String,Object>();
		PromotionService service = new PromotionService();
		List<PromotionInfo> list = null;
		
		String pCompId 			= checkStr(request.getParameter("comp_sel_id"), "");
		String pCompNm 			= checkStr(request.getParameter("comp_sel_nm"), "");
		String pBrandId 			= checkStr(request.getParameter("brand_sel_id"), "");
		String pBrandNm 			= checkStr(request.getParameter("brand_sel_nm"), "");
		String pShopNm 			= checkStr(request.getParameter("shop_nm"), "");
		String pRegionType 		= checkStr(request.getParameter("region_type"), "");
		
		params.put("comp_id", 	pCompId);			//제휴사ID
		params.put("comp_nm", pCompNm);			//제휴사명
		params.put("brand_id",	pBrandId);			//브랜드ID
		params.put("brand_nm", pBrandNm);			//브랜드명
		params.put("shop_nm", 	pShopNm);			//가맹점명
		
		if("%".equals(pRegionType))  pRegionType = "";
		params.put("region_type", 	pRegionType);		//지역코드
		
		list = service.selectShopList(params);
		
		request.setAttribute("params", params);
		request.setAttribute("List", list);

		log.debug("### PromotionController selectPromotionInfo END ###");
		
		return "/harex/promotion_sel_shop_pop";
	}
}
