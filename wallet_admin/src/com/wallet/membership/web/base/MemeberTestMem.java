package com.wallet.membership.web.base;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.MwMembAgree;
import com.wallet.membership.model.MwMembAgreeExample;
import com.wallet.membership.service.MwMembAgreeService;

@Controller
public class MemeberTestMem extends CommonController {
	private final String PAGE_CODE = "TESTMEM";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 업체관리 테스트 페이지
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/member_terms_list"
	 */ 
	@RequestMapping(value="/member/TestMem.ms")
	public String TermsList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwMembAgree> mwMembAgreeList = null;
		MwMembAgreeService mwMembAgreeService = new MwMembAgreeService();
		MwMembAgreeExample mwMembAgreeExample = null;
		 
		int count = 0;
		
		// 로그인 체크
		if (isLogedin(request) || true) {
			/* GET PARAMETERS */
			String pageNo = checkStr(request.getParameter("pageNo"), "1");
			String rowsPerPage = checkStr(request.getParameter("rows"), "10");
			String shMsg = checkStr(request.getParameter("sh_msg"), "");
			String DelId = checkStr(request.getParameter("del_id"), "");
			int DelIdx = Integer.parseInt(checkStr(request.getParameter("del_idx"), "0"));
			
			int page = Integer.parseInt(pageNo);
			int rows = Integer.parseInt(rowsPerPage);
			
			/* Main logic */
			if(DelId != null && !DelId.equals("")&&DelIdx != 0){
				mwMembAgreeExample = new MwMembAgreeExample();
				mwMembAgreeExample.or().andMembIdEqualTo(DelId);
				mwMembAgreeExample.or().andIdxEqualTo(DelIdx);
				mwMembAgreeService.delete(mwMembAgreeExample);
			}
			
			mwMembAgreeExample = new MwMembAgreeExample();
			if(shMsg != null && !shMsg.equals("")){				
				mwMembAgreeExample.or().andMembIdEqualTo(shMsg);
				mwMembAgreeList = mwMembAgreeService.getByExample(mwMembAgreeExample);
			}else{				
				mwMembAgreeList = mwMembAgreeService.getByExamplePage(mwMembAgreeExample, page, rows);
			}			
			count = mwMembAgreeService.getCountByExample(mwMembAgreeExample);
			
			/* SET ATTRIBUTE */
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("mwMembAgreeList", mwMembAgreeList);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", page);
			request.setAttribute("rowsPerPage", rows);
			request.setAttribute("pagesPerPage", 10);
			request.setAttribute("shMsg", shMsg);
			
			return "member/TestMem";
		}
		
		return "main/login";
	}
	
	
	/**
	 * 멤버십의 신규등록을 한다.
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"/member/TestMemregist.ms"
	 */
	
	@RequestMapping(value="/member/TestMemregist.ms", method=RequestMethod.GET)
	public String agreeReg(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		MwMembAgree mwMembAgree = new MwMembAgree();
		model.put("MwMembAgree", mwMembAgree);
		Date today = new Date();
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", "MENU_RIGHT");
		request.setAttribute("today", today);
		request.setAttribute("chUsid", "sessionUsId");
		return "member/TestMemregist";
	}
	
	@RequestMapping(value="/member/TestMemregist.ms", method=RequestMethod.POST)
	public String agreeReg(@Valid MwMembAgree mwMembAgree, BindingResult result, Map model) {
		if(result.hasErrors()){
			return "/member/member_terms_register.ms";
		}
		model.put("MwMembAgree", mwMembAgree);
		return "member/TestMemregist";
	}
	
	
	/**
	 * 조회버튼을 누르면 띄는 팝업 창.
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"/member/Querypop.ms"
	 */
	
	@RequestMapping(value="/member/Querypop.ms", method=RequestMethod.GET)
	public String stampimage(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		MwMembAgree mwMembAgree = new MwMembAgree();
		model.put("MwMembAgree", mwMembAgree);
		Date today = new Date();
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", "MENU_RIGHT");
		request.setAttribute("today", today);
		request.setAttribute("chUsid", "sessionUsId");
		return "member/Querypop";
	}
	
	@RequestMapping(value="/member/Querypop.ms", method=RequestMethod.POST)
	public String stampimage(@Valid MwMembAgree mwMembAgree, BindingResult result, Map model) {
		if(result.hasErrors()){
			return "/member/member_terms_register.ms";
		}
		model.put("MwMembAgree", mwMembAgree);
		return "member/Querypop";
	}
	
	
	/**
	 * 등급설정을 하기 위해 띄는 팝업 창.
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"/member/gradePopup.ms"
	 */
	
	@RequestMapping(value="/member/gradePopup.ms", method=RequestMethod.GET)
	public String gradesetting(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		MwMembAgree mwMembAgree = new MwMembAgree();
		model.put("MwMembAgree", mwMembAgree);
		Date today = new Date();
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", "MENU_RIGHT");
		request.setAttribute("today", today);
		request.setAttribute("chUsid", "sessionUsId");
		return "member/gradePopup";
	}
	
	@RequestMapping(value="/member/gradePopup.ms", method=RequestMethod.POST)
	public String gradesetting(@Valid MwMembAgree mwMembAgree, BindingResult result, Map model) {
		if(result.hasErrors()){
			return "/member/member_terms_register.ms";
		}
		model.put("MwMembAgree", mwMembAgree);
		return "member/gradePopup";
	}
	


}