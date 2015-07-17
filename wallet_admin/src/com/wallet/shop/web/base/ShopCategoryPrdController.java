package com.wallet.shop.web.base;

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
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;
import com.wallet.membership.service.custom.ImageService;
import com.wallet.shop.model.custom.ShopCategory;
import com.wallet.shop.model.custom.ShopCategoryPrd;
import com.wallet.shop.service.custom.ShopCategoryPrdService;
import com.wallet.shop.service.custom.ShopCategoryService;



@Controller
public class ShopCategoryPrdController extends CommonController {
	private final String PAGE_CODE = "CATEGORY_PRD_LIST";
	private Logger log = Log.getLogger("logs");

	/**
	 * @Method Name : ShopCategoryPrdList
	 * @Description : 상품 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.08
	 */
	@RequestMapping(value="/shop/product_list.ms")
	public String ShopCategoryPrdList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ShopCategoryPrd> PrdCategoryList = null;
		
		
		ShopCategoryService categoryService = new ShopCategoryService();
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();
				
		HashMap<String, Object> params = new HashMap<String,Object>(); //-- 상품이 속한 카테고리 정보 조회용
		HashMap<String, Object> prdParams = new HashMap<String,Object>(); //-- 상품 조회용

		String cateId = checkStr(request.getParameter("cateId"), "");
		
		params.put("cateId", cateId);
		
		ShopCategory upperCateInfo = categoryService.selectAShopCategory(params);
		
		log.debug("@@@@@@@@@@ ShopCategoryPrdList params : "+ params); //##
		
		prdParams.put("cateId", cateId);
		
		prdParams.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- 운영상태에 대한 기본값 설정
		prdParams.put("prdName", checkStr(request.getParameter("prdName"), "")); //-- 선택된 카테고리가 카테고리명
		prdParams.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "all")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		
		
			prdParams.put("sdate", checkStr(request.getParameter("sdate")));
			prdParams.put("edate", checkStr(request.getParameter("edate")));
		
		
		prdParams.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 

		
		log.debug("@@@@@@@@@@ ShopCategoryPrdList prdParams : "+ prdParams); //##
		Integer productTotCnt = categoryPrdService.selectShopCategoryPrdListCnt(prdParams); //-- 총 상품 수(조회조건 상관 없는)
		
		PrdCategoryList = categoryPrdService.selectShopPrdList(prdParams); 
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("prdName", prdParams.get("prdName"));
		request.setAttribute("productTotCnt", productTotCnt);
		request.setAttribute("upperCateInfo", upperCateInfo);
		request.setAttribute("PrdCategoryList", PrdCategoryList);
		request.setAttribute("PrdCategoryListCnt", PrdCategoryList.size());
		//request.setAttribute("cateName", params.get("cateName"));
		request.setAttribute("showYN", prdParams.get("showYN"));
		request.setAttribute("ra_searchTerm", prdParams.get("ra_searchTerm"));
		request.setAttribute("sdate", prdParams.get("sdate"));
		request.setAttribute("edate", prdParams.get("edate"));
		request.setAttribute("se_termOpt", prdParams.get("se_termOpt"));
		
		params.clear();
		prdParams.clear();
		
		return "shop/product_list";
	}
	

	/**
	 * @Method Name : ShopProductRegister
	 * @Description : 상품 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.14
	 */
	@RequestMapping(value="/shop/product_register.ms")
	public String ShopProductRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ShopCategoryService categoryService = new ShopCategoryService();
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();

		HashMap<String, Object> params = new HashMap<String,Object>();
		
		ShopCategory upperCateInfo = null;

		String cateId = checkStr(request.getParameter("cateId"), "");
				
			params.put("cateId", cateId); //--여기가 upperCateId로 들어가면.. CAT00000000로 조회가 되니까 그렇게 되면 안됨. cateId가 들어가는 게 맞음.
			Integer prdShowCnt = categoryPrdService.selectShowCategoryPrdListCnt(params);
			
			upperCateInfo = categoryService.selectAShopCategory(params); //-- 대분루정보를 먼저 조회
			
			request.setAttribute("prdShowCnt", prdShowCnt);
			request.setAttribute("upperCateInfo", upperCateInfo);
			request.setAttribute("today", today());
			
			return "shop/product_register";
		
	}

	/**
	 * @Method Name : productCategoryRegisterAct
	 * @Description : 상품을 등록 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.09
	 */
	@RequestMapping(value="/shop/product_registerAct.ms")
	public String productCategoryRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0; //-- 이미지 등록의 실행결과 0:실패, 1:성공
		
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String regUser = getSessionName(request);
		String cateId = checkStr(request.getParameter("cateId"), "");
		try{
			//-- 세션 처리를 통한 등록자 정보 setting
			
			params.put("regUser", regUser); //-- 등록자ID
			params.put("cateId", cateId); 
			params.put("prdName", checkStr(request.getParameter("prdName"), ""));
			params.put("delYN", checkStr(request.getParameter("delYN"), "N")); //-- 삭제여부
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- 노출여부
			params.put("prdURL", checkStr(request.getParameter("prdURL"), ""));
			params.put("bigo", checkStr(request.getParameter("bigo"), ""));

			String cateImg_commOs = checkStr(request.getParameter("prdImg_commOs"), "");
			/*
			String cateImg_i3GS = checkStr(request.getParameter("cateImg_i3GS"), "");
			String cateImg_i4S = checkStr(request.getParameter("cateImg_i4S"), "");
			String cateImg_android = checkStr(request.getParameter("cateImg_android"), "");
			*/
			log.debug("@@@@@@@@@@ ShopCategoryPrdRegisterAct params : "+ params); //##
			
			result = categoryPrdService.insertShopCategoryPrd(params); //-- 상품 등록
			
			String prdId = categoryPrdService.selectLastPrdId(params);
			
			params.put("prdId", checkStr(request.getParameter("prdId"), ""));

			imgParams.put("level", "");
			imgParams.put("id", prdId);
			imgParams.put("useType", "15"); //-- 통합이미지 사용구분 (공통그룹코드 0016)
			imgParams.put("osType", "00"); //-- 단말기구분 (공통그룹코드 0015)

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_commOs);
			imgResult = imgService.insertImage(imgParams);
			
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/shop/product_register.ms?cateId=" + cateId);
				categoryPrdService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/product_list.ms?cateId=" + cateId);
				categoryPrdService.commit();
				imgService.commit();
			}
			
			params.clear();
		}
		catch(Exception e){
			categoryPrdService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/product_register.ms?cateId=" + cateId);
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	////////////////////////ShopSecondCategoryEditor기반으로 prduct editor로 수정할것
	
	/**
	 * @Method Name : ProductEditor
	 * @Description : 상품 정보 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.10
	 */
	@RequestMapping(value="/shop/product_editor.ms")
	public String productEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		ShopCategoryService categoryService = new ShopCategoryService();
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();
		//ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> prdParams = new HashMap<String,Object>();
		
		ShopCategoryPrd PrdInfo = null;
		ShopCategory upperCateInfo = null;
		
		String prdId = checkStr(request.getParameter("prdId"), "");
		String cateId = checkStr(request.getParameter("cateId"), "");
		params.put("cateId", cateId);
		Integer prdShowCnt = categoryPrdService.selectShowCategoryPrdListCnt(params);
		request.setAttribute("prdShowCnt", prdShowCnt);
		
		try{
			String today = today();
			
			prdParams.put("prdId", prdId);
			PrdInfo = categoryPrdService.selectAShopCategoryPrdInfo(prdParams);
			
			log.debug("@@@@@@@@@@ ProductEditor params : "+ prdParams); //##
			params.put("cateId", cateId);
			upperCateInfo = categoryService.selectAShopCategory(params);
			
			request.setAttribute("today", today);
			request.setAttribute("prdId", prdId);
			request.setAttribute("upperCateInfo", upperCateInfo);
			request.setAttribute("PrdInfo", PrdInfo);
			
			
			params.clear();
			prdParams.clear();
			return "shop/product_editor";
		}
		catch(Exception e){
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/shop/product_list.ms?cateId=" + cateId);
			
			e.printStackTrace();
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : ShopSecondCategoryEditorAct
	 * @Description : 카테고리를 수정 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.09
	 */
	@RequestMapping(value="/shop/product_editorAct.ms")
	public String productEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0;
		
		ShopCategoryService categoryService = new ShopCategoryService();
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String cateId = checkStr(request.getParameter("cateId"), "");
		String prdId = checkStr(request.getParameter("prdId"), "");
		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String chgUser = getSessionName(request);
		
		
		try{
			
			//-- 세션 처리를 통한 등록자 정보 setting 
			params.put("chgUser", chgUser); //-- 수정자ID
			params.put("prdId", prdId);
			params.put("delYN", checkStr(request.getParameter("delYN"), "N")); //-- 삭제여부
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- 노출여부
			
			//-- 노출여부가 비노출로 변경되는 경우, dispOrder 값을 0으로수정해줌
			params.put("prdName", checkStr(request.getParameter("prdName"), ""));
			params.put("prdURL", checkStr(request.getParameter("prdURL"), ""));
			params.put("bigo", checkStr(request.getParameter("bigo"), ""));
			
			String prdImg_commOs = checkStr(request.getParameter("prdImg_commOs"), "");
			
			imgParams.put("level", "");
			imgParams.put("id", prdId);
			imgParams.put("useType", "15"); //-- 통합이미지 사용구분 (공통그룹코드 0016)
			imgParams.put("osType", "00"); //-- 단말기구분 (공통그룹코드 0015)
			imgService.deleteImage(imgParams);
			
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", prdImg_commOs);
			imgResult = imgService.insertImage(imgParams);

			log.debug("@@@@@@@@@@ ShopCategoryPrdEditorAct params : "+ params); //##
			
			result = categoryPrdService.updateShopCategoryPrd(params);

			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/shop/product_editor.ms?cateId=" + cateId);
				categoryPrdService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/product_list.ms?cateId=" + cateId);
				categoryPrdService.commit();
				imgService.commit();
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/product_editor.ms?cateId=" + cateId);

			categoryPrdService.rollback();
			imgService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : ShopSecondCategoryDeleteAct
	 * @Description : 모카샵  중분류 카테고리를 삭제한다.(실제삭제)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.12
	 */
	@RequestMapping(value="/shop/product_deleteAct.ms")
	public String productDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0;
		
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		
		String cateId = checkStr(request.getParameter("cateId"), "");
		String prdId = checkStr(request.getParameter("prdId"), "");
		try{
			params.put("cateId", cateId);
			params.put("prdId", prdId);
			
			imgParams.put("id", prdId);
			imgParams.put("level", "");

			log.debug("@@@@@@@@@@ ShopProductDeleteAct params : "+ params); //##
			
			result = categoryPrdService.deleteShopCategoryPrd(params); //-- 실제 DB에서 삭제할 때
			params.clear();
			
			imgResult = imgService.deleteImage(imgParams);
			
			request.setAttribute("cateId", cateId);
			request.setAttribute("prdId", prdId);
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 삭제 실패이면,
				categoryPrdService.rollback();
				imgService.rollback();
				
				request.setAttribute("targetUrl", "/shop/product_editor.ms?cateId=" + cateId);
			}
			else{
				categoryPrdService.commit();
				imgService.commit();
				
				request.setAttribute("targetUrl", "/shop/product_list.ms?cateId=" + cateId);
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/product_editor.ms?cateId=" + cateId);
			
			imgService.rollback();
			categoryPrdService.rollback();
			
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
