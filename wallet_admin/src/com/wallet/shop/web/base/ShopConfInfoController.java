package com.wallet.shop.web.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

import com.wallet.shop.model.custom.ShopConfInfo;
import com.wallet.shop.service.custom.ShopConfInfoService;

@Controller
public class ShopConfInfoController extends CommonController {
	private final String PAGE_CODE = "SHOP_CONF_LIST";
	private Logger log = Log.getLogger("logs");

	/**
	 * @Method Name : ShopConfInfoList
	 * @Description : 설정정보 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.15
	 */
	@RequestMapping(value="/shop/confInfo_list.ms")
	public String ShopConfInfoList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ShopConfInfo> confList = null;
		List<ShopConfInfo> upperConfList = null;
		ShopConfInfoService confInfoService = new ShopConfInfoService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("confId", checkStr(request.getParameter("confId"), "")); 
		params.put("confKey", checkStr(request.getParameter("confKey"), "")); 
		params.put("confValue", checkStr(request.getParameter("confValue"), "")); 
		params.put("upperConfId", checkStr(request.getParameter("upperConfId"), "NONE")); 
		
		log.debug("@@@@@@@@@@ ShopConfInfoList params : "+ params); //##
		
		upperConfList = confInfoService.selectUpperConfIdList(params);
		confList = confInfoService.selectShopConfInfoList(params);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("confList", confList);
		request.setAttribute("confListCnt", confList.size());
		request.setAttribute("confId", params.get("confId"));
		request.setAttribute("upperConfId", params.get("upperConfId"));
		request.setAttribute("confKey", params.get("confKey"));
		request.setAttribute("confValue", params.get("confValue"));
		request.setAttribute("upperConfList", upperConfList);
		
		params.clear();
		
		return "shop/confInfo_list";
	}

	/**
	 * @Method Name : ShopConfInfoRegister
	 * @Description : 설정정보 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.15
	 */
	@RequestMapping(value="/shop/confInfo_register.ms")
	public String ShopConfInfoRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		ShopConfInfoService confInfoService = new ShopConfInfoService();
		List<ShopConfInfo> upperConfList = null;
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("upperConfId", checkStr(request.getParameter("upperConfId"), "NONE")); 

		
		upperConfList = confInfoService.selectUpperConfIdList(params);
		
		request.setAttribute("upperConfList", upperConfList);
		request.setAttribute("upperConfListCnt", upperConfList.size());
		
		return "shop/confInfo_register";
	}

	/**
	 * @Method Name : ShopConfInfoRegisterAct
	 * @Description : 설정정보을 등록한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.17
	 */
	@RequestMapping(value="/shop/confInfo_registerAct.ms")
	public String ShopConfInfoRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공

		ShopConfInfoService confInfoService = new ShopConfInfoService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String regUser = getSessionName(request);
		String upperConfId = checkStr(request.getParameter("upperConfId1"), request.getParameter("upperConfId"));
		
		try{
			params.put("confKey", checkStr(request.getParameter("confKey"), ""));
			params.put("confValue", checkStr(request.getParameter("confValue"), "")); 
			params.put("upperConfId", upperConfId); //-- 운영상태에 대한 기본값 설정
			params.put("bigo", checkStr(request.getParameter("bigo"), "")); //-- 이벤트/설정정보  Title
			params.put("regUser", regUser);
			
			log.debug("@@@@@@@@@@ ShopConfInfoList params : "+ params); //##
			
			result = confInfoService.insertShopConfInfo(params);
			
			/* SET ATTRIBUTEs */
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/shop/confInfo_register.ms");
				confInfoService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/confInfo_list.ms?upperConfId="+upperConfId );
				confInfoService.commit();
			}
			params.clear();
		}
		catch(Exception e){
			confInfoService.rollback();
			
			request.setAttribute("actResult", result +"");
			request.setAttribute("targetUrl", "/shop/confInfo_register.ms");
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}


	/**
	 * @Method Name : ShopConfInfoEditor
	 * @Description : 설정정보 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.15
	 */
	@RequestMapping(value="/shop/confInfo_editor.ms")
	public String ShopConfInfoEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ShopConfInfoService confInfoService = new ShopConfInfoService();
		String confId = checkStr(request.getParameter("confId"), "");

		List<ShopConfInfo> upperConfList = null;
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("confId", confId); 
		params.put("upperConfId", checkStr(request.getParameter("upperConfId"), "NONE")); 

		upperConfList = confInfoService.selectUpperConfIdList(params);
		ShopConfInfo confInfo = (ShopConfInfo) confInfoService.selectShopConfInfoList(params).get(0);
		
		request.setAttribute("confInfo", confInfo);
		request.setAttribute("upperConfList", upperConfList);
		request.setAttribute("upperConfListCnt", upperConfList.size());
		
		return "shop/confInfo_editor";
	}
	
	/**
	 * @Method Name : ShopConfInfoEditorAct
	 * @Description : 설정정보을 수정한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.17
	 */
	@RequestMapping(value="/shop/confInfo_editorAct.ms")
	public String ShopConfInfoEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		
		ShopConfInfoService confInfoService = new ShopConfInfoService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		String chgUser = getSessionName(request);
		String confId = checkStr(request.getParameter("confId"), "");
		String upperConfId = checkStr(request.getParameter("upperConfId"), "NONE");
		
		try{
			params.put("confId", confId);
			params.put("upperConfId", upperConfId);
			params.put("confKey", checkStr(request.getParameter("confKey"), ""));
			params.put("confValue", checkStr(request.getParameter("confValue"), "")); 
			params.put("bigo", checkStr(request.getParameter("bigo"), ""));
			params.put("chgUser", chgUser);
			
			log.debug("@@@@@@@@@@ ShopConfInfoList params : "+ params); //##
			
			result = confInfoService.updateShopConfInfo(params);
			
			/* SET ATTRIBUTEs */
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/shop/confInfo_editor.ms?confId" + confId);
				confInfoService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/confInfo_list.ms?upperConfId="+upperConfId );
				confInfoService.commit();
			}
			params.clear();
		}
		catch(Exception e){
			confInfoService.rollback();
			
			request.setAttribute("actResult", result +"");
			request.setAttribute("targetUrl", "/shop/confInfo_editor.ms?confId" + confId);
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : ShopConfInfoEditorAct
	 * @Description : 설정정보을 수정한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.17
	 */
	@RequestMapping(value="/shop/confInfo_deleteAct.ms")
	public String ShopConfInfoDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공

		ShopConfInfoService confInfoService = new ShopConfInfoService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String regUser = getSessionName(request);
		String bbsType = "";
		String confId = "";
		String upperConfId = "";
		
		try{
			confId = checkStr(request.getParameter("confId"), "");
			upperConfId = checkStr(request.getParameter("upperConfId"), "NONE");
			
			params.put("confId", confId);
			params.put("upperConfId", upperConfId); 
			
			log.debug("@@@@@@@@@@ ShopConfInfoDeleteAct params : "+ params); //##
			
			result = confInfoService.deleteShopConfInfo(params); //-- 실제 DB에서 삭제
			
			/* SET ATTRIBUTEs */
			request.setAttribute("actResult", result  + "");
			
			if(result == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/shop/confInfo_editor.ms?upperConfId=" + upperConfId + "&confId=" + confId);
				confInfoService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/confInfo_list.ms?upperConfId="+upperConfId );
				confInfoService.commit();
			}
			params.clear();
		}
		catch(Exception e){
			confInfoService.rollback();
			
			request.setAttribute("actResult", result +"");
			request.setAttribute("targetUrl", "/shop/confInfo_editor.ms?upperConfId=" + upperConfId + "&confId=" + confId);
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	

	/**
	 * @Method Name : ScreenRegister
	 * @Description : 설정정보에서 세팅한 화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.18
	 */
	@RequestMapping(value="/shop/screen_register.ms")
	public String ScreenRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ShopConfInfoService confInfoService = new ShopConfInfoService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("upperConfId", "MOCA_SHOP_APP_SCREEN");

		List<ShopConfInfo> confList = confInfoService.selectShopConfInfoList(params); //-- 앱 화면을 등록하는 화면의 selectbox를 위한 조회
		
		request.setAttribute("confList", confList);
		
		return "shop/screen_register";
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
