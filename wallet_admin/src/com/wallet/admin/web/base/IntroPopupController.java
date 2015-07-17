package com.wallet.admin.web.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.admin.model.MwAdIntroPopup;
import com.wallet.admin.service.MwAdIntroPopupService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;

/*
 * Filename	: IntroPopupController.java
 * Class	: com.wallet.admin.web.base.IntroPopupController
 * History	: 2012/08/23, psj, 작업구분 : 팝업/배너 관리 > 인트로 팝업
 * Comment	:
 */
@Controller
public class IntroPopupController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 인트로 팝업 등록 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/intro_popup_reg.ad")
	public String introPopupReg(HttpServletRequest request, HttpServletResponse response) {

		//오늘날짜
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);

	  request.setAttribute("reg_day", reg_day);
	  
		return "base/intro_popup_reg";
	}
	
	/**
	 * 인트로 팝업 등록
	 * @param os			제공os
	 * @param sday		게재기간 - 시작일
	 * @param eday		게재기간 - 종료일
	 * @param stat		게재상태
	 * @param url			팝업url
	 * @return	
	 */
	@RequestMapping(value="/base/intro_popup_reg.ad", method=RequestMethod.POST)
	public String insertIntroPopupReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### IntroPopupController insertIntroPopupReg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdIntroPopup> list = null;
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String os = checkStr(request.getParameter("os"), "");			//제공os
		String sday = checkStr(request.getParameter("sday"), "");	//게재기간 - 시작일
		String eday = checkStr(request.getParameter("eday"), "");	//게재기간 - 종료일
		String stat = checkStr(request.getParameter("stat"), "");	//게재상태
		String url = checkStr(request.getParameter("url"), "");		//팝업url
		String event_type = checkStr(request.getParameter("event_type"), "");		//event_type
	
		try {
			
			params.put("os", os);
			params.put("sday", sday);
			params.put("eday", eday);
			params.put("stat", stat);
			params.put("url", url);
			params.put("event_type", event_type);
		
			service.insertIntroPopupReg(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

    //list화면 이동
    response.sendRedirect("/base/intro_popup_list.ad?os="+os);
    
    log.debug("### IntroPopupController insertIntroPopupReg END ###");
    
		return "base/intro_popup_list";
	}
	
	/**
	 * 인트로 팝업 조회 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/intro_popup_list.ad")
	public String selectIntroPopupList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### IntroPopupController selectIntroPopupList START ###");
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdIntroPopup> list = null;
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		int blockSize = 10;
		int nowPage = Integer.parseInt(checkStr(request.getParameter("now_page"), "1"));	//현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * blockSize + 1; 
    int endRow   = nowPage == 1 ? blockSize :startRow + blockSize -1 ;

		String os = checkStr(request.getParameter("os"), "apple");			//제공os

		params.put("view", "list");
		params.put("start_row",  String.valueOf(startRow));
		params.put("end_row",    String.valueOf(endRow));
		params.put("os", os);
		
		//조회
		int total_cnt = service.selectIntroPopupListTotalCnt(params);
		list = service.selectIntroPopupList(params);
		
		request.setAttribute("os", os);
		request.setAttribute("mwAdIntroPopupList", list);
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, total_cnt, blockSize);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb().toString());
		/******* paging end *********/
		log.debug("### page.getSb().toString() : " + page.getSb().toString());
		log.debug("### IntroPopupController selectIntroPopupList END ###");
		
		return "base/intro_popup_list";
	}
	
	/**
	 * 인트로 팝업 조회 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/intro_popup_list_dtl.ad")
	public String selectIntroPopupListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### IntroPopupController selectIntroPopupListDtl START ###");
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdIntroPopup mwAdIntroPopup = null;
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");			//idx
		String os = checkStr(request.getParameter("os"), "apple");	//제공os
		
		params.put("view", "dtl");
		params.put("idx", idx);
		params.put("os", os);
		params.put("top", 1);

		//조회
		mwAdIntroPopup = service.selectIntroPopupListDtl(params);

		request.setAttribute("mwAdIntroPopup", mwAdIntroPopup);

		log.debug("### IntroPopupController selectIntroPopupListDtl END ###");
		
		return "base/intro_popup_dtl";
	}
	
	
	/**
	 * 인트로 팝업 게재상태 변경
	 * @param idx			idx
	 * @param os			제공os
	 * @param stat		게재상태
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/intro_popup_stat_update.ad")
	public String updateIntroPopupStat(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### IntroPopupController updateIntroPopupStat START ###");
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		String os = checkStr(request.getParameter("os"), "");			//제공os
		String stat = checkStr(request.getParameter("stat"), "");	//제공상태
		
		try {
			
			params.put("idx", idx);
			params.put("os", os);
			params.put("stat", stat);
			
			service.updateIntroPopupStat(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		//list화면 이동
		response.sendRedirect("/base/intro_popup_list.ad?os="+os);
		
		log.debug("### IntroPopupController updateIntroPopupStat END ###");
		
		return "base/intro_popup_list";
	}
	
	/**
	 * 인트로 팝업 상세 변경
	 * @param idx			idx
	 * @param os			제공os - 변경할 제공os
	 * @param os			제공os - 변경전 오리지널 제공os
	 * @param sday		게재기간 - 시작일
	 * @param eday		게재기간 - 종료일
	 * @param stat		게재상태
	 * @param url			팝업url
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/intro_popup_dtl_update.ad")
	public String updateIntroPopupDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### IntroPopupController updateIntroPopupDtl START ###");
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");				//idx
		String os = checkStr(request.getParameter("os"), "");					//변경할 제공os
		String os_org = checkStr(request.getParameter("os_org"), "");	//변경전 오리지널 제공os
		String stat = checkStr(request.getParameter("stat"), "");	//게재상태
		String sday = checkStr(request.getParameter("sday"), "");	//게재기간 - 시작일
		String eday = checkStr(request.getParameter("eday"), "");	//게재기간 - 종료일
		String url = checkStr(request.getParameter("url"), "");		//팝업url
		String event_type = checkStr(request.getParameter("event_type"), "");		//event_type

		try {
			
			params.put("idx", idx);
			params.put("os", os);
			params.put("os_org", os_org);
			params.put("stat", stat);
			params.put("sday", sday);
			params.put("eday", eday);
			params.put("url", url);
			params.put("event_type", event_type);

			service.updateIntroPopupDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}
	
		//list화면 이동
		response.sendRedirect("/base/intro_popup_list.ad?os="+os);
		
		log.debug("### IntroPopupController updateIntroPopupDtl END ###");
		
		return "base/intro_popup_list";
	}
	
	/**
	 * 인트로 팝업 정보 삭제
	 * @param cidx			카드사별 id
	 * @param os				os
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/intro_popup_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteIntroPopupDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### IntroPopupController deleteIntroPopupDtl START ###");

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		String os = checkStr(request.getParameter("os"), "");		//제공os
		
		try {
			
			params.put("idx", idx);
			params.put("os", os);

			//idx가 없을경우 강제 Exception 발생
			if("".equals(idx)) {
				throw new Exception("idx가 없습니다. 강제 Exception 발생");
			}
			
			service.deleteIntroPopupDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}
		
		log.debug("### IntroPopupController deleteIntroPopupDtl END ###");
		
		return null;
		
	}

	
}
