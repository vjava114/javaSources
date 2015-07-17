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

import com.wallet.membership.model.custom.Image;
import com.wallet.membership.service.custom.ImageService;

import com.wallet.shop.model.custom.ShopNotice;
import com.wallet.shop.service.custom.ShopNoticeService;

@Controller
public class ShopNoticeController extends CommonController {
	private final String PAGE_CODE = "SHOP_NOTICE_LIST";
	private Logger log = Log.getLogger("logs");


	/**
	 * @Method Name : ShopNoticeList
	 * @Description : 공지사항 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.15
	 */
	@RequestMapping(value="/shop/notice_list.ms")
	public String ShopNoticeList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ShopNotice> noticeList = null;
		ShopNoticeService noticeService = new ShopNoticeService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("bbsType", checkStr(request.getParameter("bbsType"), "N")); //-- B:Banner, N:Notice
		params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- 운영상태에 대한 기본값 설정
		params.put("noticeTitle", checkStr(request.getParameter("noticeTitle"), "")); //-- 이벤트/공지사항  Title
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "all")); //-- 조회기간설정
		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), ""));
			params.put("edate", checkStr(request.getParameter("edate"), ""));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		params.put("noticeId", checkStr(request.getParameter("noticeId"), "")); //-- 선택된 모카샵 이벤트/공지사항가 있을 때

		log.debug("@@@@@@@@@@ ShopNoticeList params : "+ params); //##
		Integer showNoticeCnt = noticeService.selectShowNoticeListCnt(params); //-- 노출중인 이벤트/공지사항 수
		
		noticeList = noticeService.selectShopNoticeList(params); //-- 대분류 목록조회
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("showNoticeCnt", showNoticeCnt);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("noticeListCnt", noticeList.size());
		request.setAttribute("noticeTitle", params.get("noticeTitle"));
		request.setAttribute("showYN", params.get("showYN"));
		request.setAttribute("bbsType", params.get("bbsType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt", params.get("se_termOpt"));
		
		params.clear();
		
		return "shop/notice_list";
	}

	/**
	 * @Method Name : ShopNoticeRegister
	 * @Description : 공지사항 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.15
	 */
	@RequestMapping(value="/shop/notice_register.ms")
	public String ShopNoticeRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		ShopNoticeService noticeService = new ShopNoticeService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		
		params.put("bbsType", "N");
		Integer showNoticeCntN = noticeService.selectShowNoticeListCnt(params); //-- 노출중인 이벤트/공지사항 수
		params.put("bbsType", "B");
		Integer showNoticeCntB = noticeService.selectShowNoticeListCnt(params);
		
		
		String bbsType = checkStr(request.getParameter("bbsType"), "N");

		
		
		
		request.setAttribute("bbsType", bbsType);
		request.setAttribute("showNoticeCntN", showNoticeCntN);
		request.setAttribute("showNoticeCntB", showNoticeCntB);
		
		return "shop/notice_register";
	}

	/**
	 * @Method Name : ShopNoticeRegisterAct
	 * @Description : 공지사항을 등록한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.17
	 */
	@RequestMapping(value="/shop/notice_registerAct.ms")
	public String ShopNoticeRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0; //-- 이미지 등록의 실행결과 0:실패, 1:성공

		ShopNoticeService noticeService = new ShopNoticeService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();

		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String regUser = getSessionName(request);
		String bbsType = "";
		
		List<ShopNotice> noticeList = null;
		
		try{
			bbsType = checkStr(request.getParameter("bbsType"), "N");
			params.put("bbsType", bbsType); //-- B:Banner, N:Notice
			params.put("topYN", checkStr(request.getParameter("topYN"), "N")); 
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- 운영상태에 대한 기본값 설정
			params.put("noticeTitle", checkStr(request.getParameter("noticeTitle"), "")); //-- 이벤트/공지사항  Title
			
			params.put("contType", checkStr(request.getParameter("contType"), ""));
			params.put("noticeURL", checkStr(request.getParameter("noticeURL"), ""));
			params.put("noticeHtml", checkStr(request.getParameter("noticeHtml"), ""));
			params.put("noticeLink", checkStr(request.getParameter("noticeLink"), ""));
			
			String today = today();
			params.put("sDay", checkStr(request.getParameter("sDay").replace("-", ""), today));
			params.put("eDay", checkStr(request.getParameter("eDay").replace("-", ""), today));
			params.put("regUser", regUser);
			
			
			log.debug("@@@@@@@@@@ ShopNoticeList params : "+ params); //##
			
			result = noticeService.insertShopNotice(params);
			
			String noticeImg_commOs = checkStr(request.getParameter("noticeImg_commOs"), "");
			String noticeId = noticeService.selectLastNoticeId(params);

			imgParams.put("level", "");
			imgParams.put("id", noticeId);
			imgParams.put("useType", "16"); //-- 통합이미지 사용구분 (공통그룹코드 0016)
			imgParams.put("osType", "00"); //-- 단말기구분 (공통그룹코드 0015)

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", noticeImg_commOs);
			imgResult = imgService.insertImage(imgParams);
			
			/*
			Integer showNoticeCnt = noticeService.selectShowNoticeListCnt(params); //-- 노출중인 이벤트/공지사항 수
			
			noticeList = noticeService.selectShopNoticeList(params); //-- 대분류 목록조회
			*/
			/* SET ATTRIBUTEs */
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/shop/notice_register.ms?bbsType=" + bbsType);
				noticeService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/notice_list.ms?bbsType=" + bbsType );
				noticeService.commit();
				imgService.commit();
			}
			params.clear();
		}
		catch(Exception e){
			noticeService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult)+"");
			request.setAttribute("targetUrl", "/shop/notice_register.ms?bbsType" + bbsType);
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}


	/**
	 * @Method Name : ShopNoticeEditor
	 * @Description : 공지사항 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.15
	 */
	@RequestMapping(value="/shop/notice_editor.ms")
	public String ShopNoticeEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		ShopNoticeService noticeService = new ShopNoticeService();
		String noticeURL1 = "";
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("bbsType", "N");
		Integer showNoticeCntN = noticeService.selectShowNoticeListCnt(params); //-- 노출중인 이벤트/공지사항 수
		params.put("bbsType", "B");
		Integer showNoticeCntB = noticeService.selectShowNoticeListCnt(params);
		
		String bbsType = checkStr(request.getParameter("bbsType"), "B");
				
		
		
		//params.put("bbsType", bbsType); //-- B:Banner, N:Notice
		params.put("noticeId", checkStr(request.getParameter("noticeId"), "")); 
		
		ShopNotice shopNoticeInfo = noticeService.selectAShopNoticeInfo(params);

		
		if(shopNoticeInfo.getContType().equals("N")){ //-- 파일이 첨부된 경우,
			request.setAttribute("noticeURL1", shopNoticeInfo.getNoticeURL().substring(shopNoticeInfo.getNoticeURL().lastIndexOf("/")+1, shopNoticeInfo.getNoticeURL().length()));
		}
		else{//-- 외부 URL을 사용하는 경우,
			request.setAttribute("noticeURL1", "");
		}
		
		//request.setAttribute("bbsType", bbsType);
		request.setAttribute("shopNoticeInfo", shopNoticeInfo);
		request.setAttribute("showNoticeCntN", showNoticeCntN);
		request.setAttribute("showNoticeCntB", showNoticeCntB);
		
		return "shop/notice_editor";
	}
	
	/**
	 * @Method Name : ShopNoticeEditorAct
	 * @Description : 공지사항을 수정한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.17
	 */
	@RequestMapping(value="/shop/notice_editorAct.ms")
	public String ShopNoticeEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0; //-- 이미지 등록의 실행결과 0:실패, 1:성공

		ShopNoticeService noticeService = new ShopNoticeService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();

		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String chgUser = getSessionName(request);
		String bbsType = "";
		String noticeId = "";
		
		List<ShopNotice> noticeList = null;
		
		try{
			bbsType = checkStr(request.getParameter("bbsType"), "B");
			params.put("topYN", checkStr(request.getParameter("topYN"), "N")); 
			noticeId = checkStr(request.getParameter("noticeId"), "");
			params.put("noticeId", noticeId);
			params.put("bbsType", bbsType); //-- B:Banner, N:Notice
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- 운영상태에 대한 기본값 설정
			params.put("noticeTitle", checkStr(request.getParameter("noticeTitle"), "")); //-- 이벤트/공지사항  Title
			
			params.put("contType", checkStr(request.getParameter("contType"), ""));
			params.put("noticeURL", checkStr(request.getParameter("noticeURL"), ""));
			params.put("noticeHtml", checkStr(request.getParameter("noticeHtml"), ""));
			params.put("noticeLink", checkStr(request.getParameter("noticeLink"), ""));
			
			String today = today();
			params.put("sDay", checkStr(request.getParameter("sDay").replace("-", ""), today));
			params.put("eDay", checkStr(request.getParameter("eDay").replace("-", ""), today));
			params.put("chgUser", chgUser);
			
			
			log.debug("@@@@@@@@@@ ShopNoticeList params : "+ params); //##
			
			result = noticeService.updateShopNotice(params);
			
			String noticeImg_commOs = checkStr(request.getParameter("noticeImg_commOs"), "");
			
			imgParams.put("level", "");
			imgParams.put("id", noticeId);
			imgParams.put("useType", "16"); //-- 통합이미지 사용구분 (공통그룹코드 0016)
			imgParams.put("osType", "00"); //-- 단말기구분 (공통그룹코드 0015)
			imgService.deleteImage(imgParams); //-- 선 삭제 후, 이미지 재등록

			imgParams.put("chgUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", noticeImg_commOs);
			imgResult = imgService.insertImage(imgParams);
			
			/* SET ATTRIBUTEs */
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/shop/notice_editor.ms?bbsType=" + bbsType + "&noticeId=" + noticeId);
				noticeService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/notice_list.ms?bbsType=" + bbsType);
				noticeService.commit();
				imgService.commit();
			}
			params.clear();
		}
		catch(Exception e){
			noticeService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult)+"");
			request.setAttribute("targetUrl", "/shop/notice_editor.ms?bbsType" + bbsType + "&noticeId=" + noticeId);
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : ShopNoticeEditorAct
	 * @Description : 공지사항을 수정한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.17
	 */
	@RequestMapping(value="/shop/notice_deleteAct.ms")
	public String ShopNoticeDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0; //-- 이미지 등록의 실행결과 0:실패, 1:성공

		ShopNoticeService noticeService = new ShopNoticeService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();

		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String regUser = getSessionName(request);
		String bbsType = "";
		String noticeId = "";
		
		try{
			bbsType = checkStr(request.getParameter("bbsType"), "B");
			noticeId = checkStr(request.getParameter("noticeId"), "");
			
			params.put("noticeId", noticeId);
			params.put("bbsType", bbsType); //-- B:Banner, N:Notice
			
			log.debug("@@@@@@@@@@ ShopNoticeDeleteAct params : "+ params); //##
			
			result = noticeService.deleteShopNotice(params); //-- 실제 DB에서 삭제
			
			imgParams.put("id", noticeId);
			imgParams.put("level", "");
			imgParams.put("useType", "16"); //-- 통합이미지 사용구분 (공통그룹코드 0016)
			imgParams.put("osType", "00"); //-- 단말기구분 (공통그룹코드 0015)
			imgResult = imgService.deleteImage(imgParams);
			
			/* SET ATTRIBUTEs */
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/shop/notice_editor.ms?bbsType=" + bbsType + "&noticeId=" + noticeId);
				noticeService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/notice_list.ms?bbsType=" + bbsType);
				noticeService.commit();
				imgService.commit();
			}
			params.clear();
		}
		catch(Exception e){
			noticeService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult)+"");
			request.setAttribute("targetUrl", "/shop/notice_editor.ms?bbsType" + bbsType + "&noticeId=" + noticeId);
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
//	/**
//	 * @Method Name : ShopEventRegister
//	 * @Description : 공지사항 검색/목록화면을 로딩한다.
//	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
//	 * @return : String 
//	 * @author 김태리
//	 * @since 2013.04.15
//	 */
//	@RequestMapping(value="/shop/event_register.ms")
//	public String ShopEventRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
//		List<ShopNotice> noticeList = null;
//		ShopNoticeService noticeService = new ShopNoticeService();
//		HashMap<String, Object> params = new HashMap<String,Object>();
//		
//		params.put("bbsType", checkStr(request.getParameter("bbsType"), "B")); //-- B:Banner, N:Notice
//		params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- 운영상태에 대한 기본값 설정
//		params.put("noticeTitle", checkStr(request.getParameter("noticeTitle"), "")); //-- 이벤트/공지사항  Title
//		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "all")); //-- 조회기간설정
//		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
//		String today = today();
//		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
//			params.put("sDay", checkStr(request.getParameter("sDay"), today));
//			params.put("eDay", checkStr(request.getParameter("eDay"), today));
//		}
//		
//		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
//		params.put("noticeId", checkStr(request.getParameter("noticeId"), "")); //-- 선택된 모카샵 이벤트/공지사항가 있을 때
//
//		log.debug("@@@@@@@@@@ ShopNoticeList params : "+ params); //##
//		Integer showNoticeCnt = noticeService.selectShowNoticeListCnt(params); //-- 노출중인 이벤트/공지사항 수
//		
//		noticeList = noticeService.selectShopNoticeList(params); //-- 대분류 목록조회
//		
//		/* SET ATTRIBUTEs */
//		request.setAttribute("pageCode", PAGE_CODE);
//		request.setAttribute("showNoticeCnt", showNoticeCnt);
//		request.setAttribute("noticeList", noticeList);
//		request.setAttribute("noticeListCnt", noticeList.size());
//		request.setAttribute("noticeTitle", params.get("noticeTitle"));
//		request.setAttribute("showYN", params.get("showYN"));
//		request.setAttribute("bbsType", params.get("bbsType"));
//		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
//		request.setAttribute("sDay", params.get("sDay"));
//		request.setAttribute("eDay", params.get("eDay"));
//		request.setAttribute("se_termOpt", params.get("se_termOpt"));
//		
//		params.clear();
//		
//		return "shop/event_register";
//	}
	


	/**
	 * @Method Name : ShopEventList
	 * @Description : 이벤트 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2013.04.15
	 */
//	@RequestMapping(value="/shop/event_list.ms")
//	public String ShopEventList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
//		List<ShopNotice> noticeList = null;
//		ShopNoticeService noticeService = new ShopNoticeService();
//		HashMap<String, Object> params = new HashMap<String,Object>();
//		
//		params.put("bbsType", checkStr(request.getParameter("bbsType"), "B")); //-- B:Banner, N:Notice
//		params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- 운영상태에 대한 기본값 설정
//		params.put("noticeTitle", checkStr(request.getParameter("noticeTitle"), "")); //-- 이벤트/공지사항  Title
//		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "all")); //-- 조회기간설정
//		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
//		String today = today();
//		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
//			params.put("sDay", checkStr(request.getParameter("sDay"), today));
//			params.put("eDay", checkStr(request.getParameter("eDay"), today));
//		}
//		
//		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
//		params.put("noticeId", checkStr(request.getParameter("noticeId"), "")); //-- 선택된 모카샵 이벤트/공지사항가 있을 때
//
//		log.debug("@@@@@@@@@@ ShopNoticeList params : "+ params); //##
//		Integer showNoticeCnt = noticeService.selectShowNoticeListCnt(params); //-- 노출중인 이벤트/공지사항 수
//		
//		noticeList = noticeService.selectShopNoticeList(params); //-- 대분류 목록조회
//		
//		/* SET ATTRIBUTEs */
//		request.setAttribute("pageCode", PAGE_CODE);
//		request.setAttribute("showNoticeCnt", showNoticeCnt);
//		request.setAttribute("noticeList", noticeList);
//		request.setAttribute("noticeListCnt", noticeList.size());
//		request.setAttribute("noticeTitle", params.get("noticeTitle"));
//		request.setAttribute("showYN", params.get("showYN"));
//		request.setAttribute("bbsType", params.get("bbsType"));
//		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
//		request.setAttribute("sDay", params.get("sDay"));
//		request.setAttribute("eDay", params.get("eDay"));
//		request.setAttribute("se_termOpt", params.get("se_termOpt"));
//		
//		params.clear();
//		
//		return "shop/event_list";
//	}
	
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
