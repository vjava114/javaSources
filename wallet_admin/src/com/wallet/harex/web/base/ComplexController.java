package com.wallet.harex.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.harex.common.JString;
import com.wallet.harex.model.ComplexInfo;
import com.wallet.harex.model.ComplexList;
import com.wallet.harex.model.ComplexShopHid;
import com.wallet.harex.model.ComplexUserList;
import com.wallet.harex.model.PromotionInfo;
import com.wallet.harex.model.PromotionList;
import com.wallet.harex.service.ComplexService;
import com.wallet.harex.service.PromotionService;

@Controller
public class ComplexController extends CommonController {
	
	private final String PAGE_CODE = "COMPLEX_SHOP";
	
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 복합결제 이용 가맹점 조회
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/complex_shop_list.hx")
	public String selectComplexShopList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexShopList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<ComplexList> list = null;
		ComplexService service = new ComplexService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String compId 			= checkStr(request.getParameter("compId"), "");  //제휴사ID
		String brandId 			= checkStr(request.getParameter("brandId"), "");  // 브랜드 ID
		String regionType 	= checkStr(request.getParameter("regionType"), ""); // 지역
		String shopId 			= checkStr(request.getParameter("shopId"), ""); // 가맹점ID
		String shopName			= checkStr(request.getParameter("shopName"), ""); // 가맹점명
	
		if("%".equals(compId)){
			compId = "";
		}
		if("%".equals(brandId)){
			brandId = "";
		}
		if("%".equals(regionType)){
			regionType = "";
		}
		if("%".equals(shopId)){
			shopId = "";
		}

		params.put("compId", compId);							
		params.put("brandId", brandId);
		params.put("regionType", regionType);
		params.put("shopId", shopId);
		params.put("shopName", shopName);
		
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
		list = service.selectComplexList(params);
		int ListCnt = service.selectComplexListCnt(params); 		// 총 목록 수
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		params.put("compId", compId == null || compId.equals("") ? "none":compId);							
		params.put("brandId", brandId == null || brandId.equals("") ? "none":brandId);
		params.put("regionType", regionType);
		params.put("shopId", shopId == null || shopId.equals("") ? "none":shopId);
		params.put("shopName", shopName);
		
		
		log.debug("### ComplexController ComplexShopList END ###");
		
		return "/harex/complex_shop_list";
	}
	
	/**
	 * 복합결제 이용 가맹점 - 제휴사 상세정보
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/complex_info.hx")
	public String selectComplexInfo(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexInfo START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		ComplexInfo complexInfo = null;
		ComplexService service = new ComplexService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		String mainNumber = "";		//대표전화
		String managerName = "";	//담당자
		String mobilePhone = "";	//담당자 연락처
		String email = "";				//담당자 이메일
		
		String compId = checkStr(request.getParameter("compId"), "");

		params.put("compId", compId);							//제휴사ID
		
		//조회
		complexInfo = service.selectComplexInfo(params);
		
		/**
		 *   복호화 처리
		 */
		if(complexInfo != null){
			
			KTDBCipher dbCipher = new KTDBCipher();
			dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
			
			if (complexInfo.getMainNumber() != null && complexInfo.getMainNumber().length() > 15) {
				mainNumber = dbCipher.decoding(complexInfo.getMainNumber());	
			} else {
				mainNumber = complexInfo.getMainNumber();
			}
			
			if (complexInfo.getManagerName() != null && complexInfo.getManagerName().length() > 10) {
				managerName = dbCipher.decoding(complexInfo.getManagerName());	
			} else {
				managerName = complexInfo.getManagerName();
			}
			
			if (complexInfo.getMobilePhone() != null && complexInfo.getMobilePhone().length() > 15 ) {
				mobilePhone = dbCipher.decoding(complexInfo.getMobilePhone());	
			} else {
				mobilePhone = complexInfo.getMobilePhone();
			}
			
			// Email 정규형 여부 확인 
			JString js = new JString(); 
			if (complexInfo.getEmail() != null && js.checkFormat("email", complexInfo.getEmail()) == false) {
				email = dbCipher.decoding(complexInfo.getEmail());	
			} else {
				email = complexInfo.getEmail();
			}
						
			complexInfo.setMainNumber(mainNumber);
			complexInfo.setManagerName(managerName);
			complexInfo.setMobilePhone(mobilePhone);
			
			// 이메일 숨김처리 ID 뒤 3글자 '***' 처리
			complexInfo.setEmail(js.changeEmailTx(email)); 
			
		}
		
		request.setAttribute("ComplexInfo", complexInfo);
		
		log.debug("### ComplexController ComplexInfo END ###");
		
		return "/harex/complex_info";
	}
	
	
	/**
	 * 브랜드별 결제기관 프로모션 상세정보 팝업
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/complex_promotion_pop.hx")
	public String selectComplexPromotionPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexPromotionPop START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<PromotionList> list = null;
		PromotionService service = new PromotionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String shopId 				= checkStr(request.getParameter("pShopId"), "");
		String promoName 			= checkStr(request.getParameter("promoName"), "");
		
		params.put("shopId", shopId);
		params.put("promoName", promoName);
		
		//조회
		list = service.selectPromotionList(params);
		
		request.setAttribute("ComplexPromotionPop", list);
		
		log.debug("### ComplexController ComplexPromotionPop END ###");
		
		return "/harex/complex_promotion_pop";
	}
	
	/**
	 * 결제사별 복합결제 가맹점 SHOP_ID 매핑관리
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/complex_shop_hid_list.hx")
	public String selectComplexShopHidList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexShopHIDList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<ComplexShopHid> list = null;
		List<PromotionInfo> payCompList = null;
		
		ComplexService service = new ComplexService();
		PromotionService payService = new PromotionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		payCompList = payService.selectPayCompList();
		
		
		String comp_id 				= checkStr(request.getParameter("comp_id"), "");
		String brand_id				= checkStr(request.getParameter("brand_id"), "");
		String region_type		= checkStr(request.getParameter("region_type"), "");
		String service_id			= checkStr(request.getParameter("service_id"), "");
		String comp_name			= checkStr(request.getParameter("comp_name"), "");
		
		if("%".equals(comp_id)){
			comp_id = "";
		}
		if("%".equals(brand_id)){
			brand_id = "";
		}
		if("%".equals(region_type)){
			region_type = "";
		}
		if("".equals(service_id) && payCompList != null){
			service_id = payCompList.get(0).getCode();
		}
		
		params.put("comp_id", comp_id);							//제휴사ID
		params.put("brand_id", brand_id);						//브랜드ID
		params.put("region_type", region_type);			//지역ID
		params.put("service_id", service_id);				//서비스ID
		params.put("comp_name", comp_name);					//가맹점명
		
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
		list = service.selectComplexShopHidList(params);
		int ListCnt = service.selectComplexShopHidListCnt(params); 		// 총 목록 수
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("comp_id", comp_id == null || comp_id.equals("") ? "none":comp_id);
		request.setAttribute("brand_id", brand_id == null || brand_id.equals("") ? "none":brand_id);
		request.setAttribute("region_type", region_type);
		request.setAttribute("service_id", service_id);
		request.setAttribute("comp_name", comp_name);
		
		request.setAttribute("ComplexShopHidList", list);
		request.setAttribute("payCompList", payCompList);
		
		log.debug("### ComplexController ComplexShopHIDList END ###");
		
		return "/harex/complex_shop_hid_list";
	}
	
	/**
	 * 복합결제 회원정보관리
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/complex_user_list.hx")
	public String selectComplexUserList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController selectComplexUserList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<ComplexUserList> list = null;
		ComplexService service = new ComplexService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String tCode 			= checkStr(request.getParameter("tCode"), "");  //제휴사ID
		String phoneNo    = checkStr(request.getParameter("phoneNo"), "");  //제휴사ID
		
		if("%".equals(tCode)){
			tCode = "";
		}
		
		params.put("tCode", tCode);					// 통신사 코드
		params.put("phoneNo", phoneNo);				// 전화번호
		
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
		list = service.selectComplexUserList(params);
		int ListCnt = service.selectComplexUserListCnt(params); 		// 총 목록 수
		
		////페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		request.setAttribute("selectComplexUserList", list);
		request.setAttribute("params", params);
		
		HashMap<String,Object> logParams = new HashMap<String,Object>();
		MwAdAccessLogService logSvr = new MwAdAccessLogService();
		
		try {
			//로그처리/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
		
		log.debug("### ComplexController selectComplexUserList END ###");
		
		return "/harex/complex_user_list";
	}
	
	/**
	 * SHOP_ID - H_ID 맵핑 팝업
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return
	 */
	@RequestMapping(value="/harex/complex_shop_hid_register.hx")
	public String updateComplexShopHidRegister(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexShopHidRegister START ###");
		
		String k_shop_id 				= checkStr(request.getParameter("k_shop_id"), "");
		String k_shop_name			= checkStr(request.getParameter("k_shop_name"), "");
		String pk_shop_id				= checkStr(request.getParameter("pk_shop_id"), "");
		String pk_shop_name			= checkStr(request.getParameter("pk_shop_name"), "");
		String service_id				= checkStr(request.getParameter("service_id"), "");
		String nowPage					= request.getParameter("nowPage");

		request.setAttribute("k_shop_id", k_shop_id);
		request.setAttribute("k_shop_name", k_shop_name);
		request.setAttribute("pk_shop_id", pk_shop_id);
		request.setAttribute("pk_shop_name", pk_shop_name);
		request.setAttribute("service_id", service_id);
		request.setAttribute("nowPage", nowPage);
		
		log.debug("### ComplexController ComplexShopHidRegister END ###");
		
		return "/harex/complex_shop_hid_register";
	}
	
	/**
	 * SHOP_ID - H_ID 맵핑 처리
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return
	 */
	@RequestMapping(value="/harex/complex_shop_hid_register_action.hx")
	public String updateComplexShopHidRegisterAction(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexShopHidRegisterAction START ###");
		
		//등록자 - TODO 세션 처리
		String regUser	= "tester";
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		ComplexService service = new ComplexService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String h_shop_id 			= checkStr(request.getParameter("h_shop_id"), "");
		String k_shop_id 			= checkStr(request.getParameter("k_shop_id"), "");
		String pk_shop_id			= checkStr(request.getParameter("pk_shop_id"), "");
		String service_id			= checkStr(request.getParameter("service_id"), "");
		String nowPage				= request.getParameter("nowPage");
		
		request.setAttribute("h_shop_id", h_shop_id);
		request.setAttribute("k_shop_id", k_shop_id);
		request.setAttribute("pk_shop_id", pk_shop_id);
		
		params.put("h_shop_id", h_shop_id);
		params.put("k_shop_id", k_shop_id);
		params.put("pk_shop_id", pk_shop_id);
		params.put("regUser", regUser);
		
		try {
			//H-ID 등록
			int result = 0;
			result = service.updateComplexShopHid(params);
			
			if (result == 0) {
				//rollback
				service.rollback();
				
				//오류처리
				request.setAttribute("actResult", "0");
				request.setAttribute("targetUrl", "/harex/complex_shop_hid_register_action.hx");
				
				return "common/result_message";
			} else {
				//commit
				service.commit();
				
				request.setAttribute("actResult", "1");
				request.setAttribute("targetUrl", "/harex/complex_shop_hid_list.hx?service_id="+service_id+"&nowPage="+nowPage);

				return "common/result_message_popup";
			}
		} catch (Exception e) {
		//rollback
			service.rollback();
			e.printStackTrace();
			log.debug(e);
			
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/harex/complex_shop_hid_register_action.hx");
			
			return "common/result_message";
		}
	}
	
	/**
	 * SHOP_ID - H_ID 중복확인
	 * @param 	
	 * @param 	
	 * @return		SHOP_ID - H_ID 중복확인
	 */
	@RequestMapping(value="/harex/complex_shop_hid_register_check.hx")
	public String selectComplexShopHidRegisterCheck(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SystemController selectComplexShopHidRegisterCheck START ###");
		
		String h_shop_id = checkStr(request.getParameter("h_shop_id"), "");
		
		ComplexService service = new ComplexService();
		JSONObject jObj = new JSONObject();
		String result = "";
		
		result = service.selectComplexShopHidRegisterCheck(h_shop_id);
		
		jObj.put("result", result);
		
		log.debug("### SystemController selectComplexShopHidRegisterCheck END ###");
		
		/* SET ATTRIBUTEs */
		request.setAttribute("result", result);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}
}
