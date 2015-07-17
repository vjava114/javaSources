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

import com.wallet.admin.model.MwAdFaq;
import com.wallet.admin.service.MwAdFaqService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;

/*
 * Filename	: AppVersionController.java
 * Class	: com.wallet.admin.web.base.AppVersionController
 * History	: 2012/08/23, psj, 작업구분 : 기타관리 > FAQ 관리
 * 						2013/04/18, lsb, 작업구분 : FAQ 관리 > OS별 추가, 쇼핑 추가
 * Comment	:
 */
@Controller
public class FaqController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * FAQ 관리 화면 호출
	 * @return	
	 */
	@RequestMapping(value="/base/faq_list.ad")
	public String selectFaqList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### FaqController selectFaqList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdFaq> list = null;
		MwAdFaqService service = new MwAdFaqService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		int blockSize = 10;
		int nowPage = Integer.parseInt(checkStr(request.getParameter("now_page"), "1"));	//현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * blockSize + 1; 
		int endRow   = nowPage == 1 ? blockSize :startRow + blockSize -1 ;
    
		params.put("view", "list");
		params.put("start_row",  String.valueOf(startRow));
		params.put("end_row",    String.valueOf(endRow));
		
		//조회
		int total_cnt = service.selectFaqListTotalCnt(params);
		list = service.selectFaqList(params);
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, total_cnt, blockSize);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb().toString());
		/******* paging end *********/
	
		request.setAttribute("mwAdFaqList", list);
		
		log.debug("### FaqController selectFaqList END ###");
		
		return "base/faq_list";
	}
	
	/**
	 * FAQ 관리 상세 조회 호출
	 * @return	
	 */
	@RequestMapping(value="/base/faq_list_dtl.ad")
	public String selectFaqListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### FaqController selectFaqListDtl START ###");
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdFaq mwAdFaq = null;
		MwAdFaqService service = new MwAdFaqService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");			//idx
		
		params.put("view", "dtl");
		params.put("idx", idx);
		params.put("top", 1);

		//조회
		mwAdFaq = service.selectFaqListDtl(params);

		request.setAttribute("mwAdFaq", mwAdFaq);

		log.debug("### FaqController selectFaqListDtl END ###");
		
		return "base/faq_list_dtl";
	}
	
	/**
	 * FAQ 등록 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/faq_reg.ad")
	public String appFaqReg(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### FaqController appFaqReg START ###");
		
	//오늘날짜
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);
	  
	  request.setAttribute("reg_day", reg_day);
	
	  log.debug("### FaqController appFaqReg END ###");
	  
		return "base/faq_reg";
	}
	
	/**
	 * FAQ 등록 insert
	 * @param part		구분
	 * @param title		질문내용
	 * @param answer	답변내용
	 * @param 	
	 */
	@RequestMapping(value="/base/faq_reg.ad", method=RequestMethod.POST)
	public String insertFaqReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### FaqController insertFaqReg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdFaqService service = new MwAdFaqService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String part = checkStr(request.getParameter("part"), "");			//구분
		String title = checkStr(request.getParameter("title"), "");		//질문내용
		String answer = checkStr(request.getParameter("answer"), "");	//답변내용
		//os별 추가
		String os = checkStr(request.getParameter("os"), "");
		
		try {
			
			params.put("part", part);
			params.put("title", title);
			params.put("answer", answer);
			params.put("os", os);

			service.insertFaqReg(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

    log.debug("### FaqController insertFaqReg END ###");
    
    //list화면 이동
    response.sendRedirect("/base/faq_list.ad");
    
		return "base/faq_list";
	}
	
	/**
	 * FAQ 수정 update
	 * @param idx			idx
	 * @param part		구분
	 * @param title		질문내용
	 * @param answer	답변내용
	 * @param 	
	 */
	@RequestMapping(value="/base/faq_dtl_update.ad", method=RequestMethod.POST)
	public String updateFaqDtl(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### FaqController updateFaqReg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdFaqService service = new MwAdFaqService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String idx = checkStr(request.getParameter("idx"), "");			//idx
		String part = checkStr(request.getParameter("part"), "");			//구분
		String title = checkStr(request.getParameter("title"), "");		//질문내용
		String answer = checkStr(request.getParameter("answer"), "");	//답변내용
		String os = checkStr(request.getParameter("os"), ""); 				//os

		try {

			params.put("idx", idx);
			params.put("part", part);
			params.put("title", title);
			params.put("answer", answer);
			params.put("os", os);

			service.updateFaqDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

    log.debug("### FaqController updateFaqReg END ###");
    
    //list화면 이동
    response.sendRedirect("/base/faq_list.ad");
    
		return "base/faq_list";
	}
	
	/**
	 * FAQ 정보 삭제
	 * @param idx			idx
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/faq_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteFaqDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### FaqController deleteFaqDtl START ###");

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdFaqService service = new MwAdFaqService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		
		try {
			
			params.put("idx", idx);

			//idx가 없을경우 강제 Exception 발생
			if("".equals(idx)) {
				throw new Exception("idx가 없습니다. 강제 Exception 발생");
			}
			
			service.deleteFaqDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### FaqController deleteFaqDtl END ###");
		
		return null;
		
	}
	
}
