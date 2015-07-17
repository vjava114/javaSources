package com.wallet.admin.web.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.admin.model.MwAdAppVersion;
import com.wallet.admin.service.MwAdAppVersionService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;

import com.wallet.common.web.CommonController;

/*
 * Filename	: AppVersionController.java
 * Class	: com.wallet.admin.web.base.AppVersionController
 * History	: 2012/08/23, psj, 작업구분 : 기타관리 > 앱 버전 관리
 * Comment	:
 */
@Controller
public class AppVersionController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 앱 버전 관리 화면 호출
	 * @return	
	 */
	@RequestMapping(value="/base/app_version_list.ad")
	public String selectAppVersionList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AppVersionController selectAppVersionList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdAppVersion> list = null;
		MwAdAppVersionService service = new MwAdAppVersionService();
		HashMap<String,Object> params = new HashMap<String,Object>();

    String market = checkStr(request.getParameter("market"), "apple");			//제공os
    
    params.put("view", "list");
    params.put("market", market);
    
		list = service.selectAppVersionList(params);
	
		request.setAttribute("market", market);
		request.setAttribute("mwAdAppVersionList", list);
		
		log.debug("### AppVersionController selectAppVersionList END ###");
		return "base/app_version_list";

	}
	
	/**
	 * 앱 버전 관리 상세 조회
	 * @param 	idx 			idx
	 * @param 	market		제공마켓
	 * @return
	 */
	@RequestMapping(value="/base/app_version_list_dtl.ad")
	public String selectAppVersionListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AppVersionController selectAppVersionListDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdAppVersion mwAdappVersion = null;
		MwAdAppVersionService service = new MwAdAppVersionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");
		String market = checkStr(request.getParameter("market"), "apple");
		
		params.put("view", "dtl");
		params.put("idx", idx);
		params.put("market", market);

		//조회
		mwAdappVersion = service.selectAppVersionListDtl(params);

		request.setAttribute("mwAdappVersion", mwAdappVersion);

		log.debug("### AppVersionController selectAppVersionListDtl END ###");
		
		return "base/app_version_list_dtl";
	}
	
	/**
	 * 앱 버전 등록 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/app_version_reg.ad")
	public String appVersionReg(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AppVersionController appVersionReg START ###");
		
		String market = checkStr(request.getParameter("market"), "");		//제공마켓
	
		//오늘날짜
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);
	  
	  request.setAttribute("market", market);
	  request.setAttribute("reg_day", reg_day);
	
	  log.debug("### AppVersionController appVersionReg END ###");
	  
		return "base/app_version_reg";
	}
	
	/**
	 * 앱 버전 등록 insert
	 * @param 	market		제공마켓
	 * @param 	ver				버전
	 * @param 	mode			공지모드
	 * @param 	msg				메세지
	 */
	@RequestMapping(value="/base/app_version_reg.ad", method=RequestMethod.POST)
	public String insertAppVersionReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### AppVersionController insertAppVersionReg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdAppVersionService service = new MwAdAppVersionService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String market = checkStr(request.getParameter("market"), "");		//제공마켓
		String ver = checkStr(request.getParameter("ver"), "");					//버전
		String mode = checkStr(request.getParameter("mode"), "");				//공지모드
		String msg = checkStr(request.getParameter("msg"), "");					//메세지
		
		try {
			
			params.put("market", market);
			params.put("ver", ver);
			params.put("mode", mode);
			params.put("msg", msg);

			service.insertAppVersionReg(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

    log.debug("### AppVersionController insertAppVersionReg END ###");
    
    //list화면 이동
    response.sendRedirect("/base/app_version_list.ad?market="+market);
		return "base/app_version_list";
	}
	
	/**
	 * 앱 버전 상세 변경
	 * @param 	idx				idx
	 * @param 	market		제공마켓
	 * @param 	ver				버전
	 * @param 	mode			공지모드
	 * @param 	msg				메세지
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/app_version_dtl_update.ad")
	public String updateAppVersionDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### AppVersionController updateAppVersionDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdAppVersionService service = new MwAdAppVersionService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String idx = checkStr(request.getParameter("idx"), "");					//idx
		String market = checkStr(request.getParameter("market"), "");		//제공마켓
		String ver = checkStr(request.getParameter("ver"), "");					//버젼
		String mode = checkStr(request.getParameter("mode"), "");				//공지모드
		String msg = checkStr(request.getParameter("msg"), "");					//메세지

		try {

			params.put("idx", idx);
			params.put("market", market);
			params.put("ver", ver);
			params.put("mode", mode);
			params.put("msg", msg);

			service.updateAppVersionDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}
		
		log.debug("### AppVersionController updateAppVersionDtl END ###");
		
		//list화면 이동
		response.sendRedirect("/base/app_version_list.ad?market="+market);
		
		return "base/app_version_list";
	}
	
	/**
	 * 앱 버전 정보 삭제
	 * @param 	idx				idx
	 * @param 	market		제공마켓
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/app_version_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteAppVersionDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### AppVersionController deleteBannerDtl START ###");

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdAppVersionService service = new MwAdAppVersionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");					//idx
		String market = checkStr(request.getParameter("market"), "A");	//제공마켓
		
		try {
			
			params.put("idx", idx);
			params.put("market", market);

			//idx가 없을경우 강제 Exception 발생
			if("".equals(idx)) {
				throw new Exception("idx가 없습니다. 강제 Exception 발생");
			}
			
			service.deleteAppVersionDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### AppVersionController deleteBannerDtl END ###");
		
		return null;
		
	}
	
}
