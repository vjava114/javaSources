package com.wallet.membership.web.base;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.custom.MemberEvent;
import com.wallet.membership.service.custom.MemberEventService;

@Controller
public class MemberEventController extends CommonController{
	private final String PAGE_CODE = "MEMBEREvent_LIST";
	private Logger log = Log.getLogger("logs");
	KTDBCipher ktService = new KTDBCipher();
	
	/**
	 * @Method Name : MemberEventList
	 * @Description : 공지사항/이벤트를 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	@RequestMapping(value="/member/member_event_list.ms")
	public String MemberEventList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MemberEvent> memberEventList = null;
		MemberEventService memberEventService = new MemberEventService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("notiType", checkStr(request.getParameter("notiType"), "")); //-- 이벤트/공지사항 기본값 설정
		params.put("seMembName", checkStr(request.getParameter("seMembName"), "")); //-- 선택된 멤버십명
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		params.put("displayYn", checkStr(request.getParameter("displayYn"), ""));
		params.put("notiId", checkStr(request.getParameter("notiId"), "")); //-- 선택된 멤버쉽이 있을 때
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		
		int memberEventListCnt = memberEventService.selectMemberEventListCnt(params); //-- 총 목록 수
		Paging page = new Paging();
		page.makeWebPaging(nowPage, memberEventListCnt, rowsPerPage);
		
		if(page.getNowPage()>1){
			nowPage =  page.getNowPage();
			request.setAttribute("nowPage", nowPage);
			
		}else{
			nowPage = 1;
			request.setAttribute("nowPage", "1");
		}
		
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));

		log.debug("@@@@@@@@@@ MemberEventList params : "+ params); //##
		memberEventList = memberEventService.selectMemberEventList(params); //-- 목록조회
		
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("memberEventList", memberEventList);
		request.setAttribute("seMembName", params.get("seMembName"));
		request.setAttribute("notiType", params.get("notiType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		request.setAttribute("displayYn", params.get("displayYn"));
		params.clear();
		return "member/member_event_list";
	}
	
	
	/**
	 * @Method Name : MemberEventRegister
	 * @Description : 공지/이벤트 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.18
	 */
	@RequestMapping(value="/member/member_event_register.ms")
	public String MemberEventRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL");
		
		request.setAttribute("today", today());
		request.setAttribute("memberNotiUrl", memberNotiUrl);
		
		return "member/member_event_register";
	}
	

	
	/**
	 * @Method Name : MemverEventRegisterAct
	 * @Description : 이벤트/공지 등록 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.18
	 */
	@RequestMapping(value="/member/member_event_registerAct.ms")
	public String MemberEventAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		
		try{
			MemberEventService memberEventService = new MemberEventService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			String regUser = getSessionMgrId(request);
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("regUser", regUser); //-- 등록자ID
			params.put("notiId",  checkStr(request.getParameter("notiId"), ""));
			params.put("notiTarget",  checkStr(request.getParameter("notiTarget"), ""));
			params.put("id",  checkStr(request.getParameter("id"), ""));
			params.put("notiType",  checkStr(request.getParameter("notiType"), ""));
			params.put("title",  checkStr(request.getParameter("title"), ""));
			params.put("content",  checkStr(request.getParameter("content"), ""));
			params.put("displayYn",  checkStr(request.getParameter("displayYn"), ""));
			params.put("membId",  checkStr(request.getParameter("membId"), ""));
			params.put("valSday", checkStr(request.getParameter("sdate"), today()));
			params.put("valEday", checkStr(request.getParameter("edate"), today()));
			
			
			
			
			log.debug("@@@@@@@@@@ MemberEvenyRegisterAct params : "+ params); //##
			
			result = memberEventService.insertMemberEvent(params);
			memberEventService.commit();
			
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/member_event_register.ms");
			}
			else{
				request.setAttribute("targetUrl", "/member/member_event_list.ms");
			}
			
			params.clear();
		}
		catch(Exception e){
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/member_event_register.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	
	/**
	 * @Method Name : MemverEventEditor
	 * @Description : 공지/이벤트 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.18
	 */
	@RequestMapping(value="/member/member_event_editor.ms")
	public String SettlementEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		MemberEventService memberEventService = new MemberEventService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL");
		params.put("notiId", checkStr(request.getParameter("notiId"), "")); //--선택된 결제사 ID
		params.put("membId",  checkStr(request.getParameter("membId"), ""));
		
		log.debug("@@@@@@@@@@ MemberEventEditor params : "+ params); //##
		MemberEvent aMemberEvent = memberEventService.selectAMemberEvent(params); //-- 목록조회
		
		/*#########################세션 유지 X##############################################*/
//		String regNm = ktService.decoding(checkStr(aMemberEvent.getRegUserNm(),""));
//		String chgNm = ktService.decoding(checkStr(aMemberEvent.getChgUserNm(),""));
//		
//		aMemberEvent.setRegUserNm(regNm);
//		aMemberEvent.setChgUserNm(chgNm);
		/*#################################################################################*/
		String content1 = "";
		if(aMemberEvent.getContent() != null && !aMemberEvent.getContent().equals("")){
			content1 = aMemberEvent.getContent().substring(aMemberEvent.getContent().lastIndexOf("/")+1, aMemberEvent.getContent().length());
		}
			
		request.setAttribute("content1", content1);
		request.setAttribute("aMemberEvent", aMemberEvent);
		request.setAttribute("memberNotiUrl", memberNotiUrl);
		params.clear();
		return "member/member_event_editor";
	}
	
	
	/**
	 * @Method Name : MemverEventEditorAct
	 * @Description : 이벤트/공지를 수정 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.18
	 */
	@RequestMapping(value="/member/member_event_editorAct.ms")
	public String SettlementEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		
		try{
			MemberEventService memberEventService = new MemberEventService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			String chgUser = getSessionMgrId(request);
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("chgUser", chgUser); //-- 수정자ID
			params.put("notiId",  checkStr(request.getParameter("notiId"), ""));
			params.put("membId",  checkStr(request.getParameter("membId"), ""));
			params.put("name",  checkStr(request.getParameter("name"), ""));
			params.put("title",  checkStr(request.getParameter("title"), ""));
			params.put("notiType",  checkStr(request.getParameter("notiType"), ""));
			params.put("valSday", checkStr(request.getParameter("sdate"), today()));
			params.put("valEday", checkStr(request.getParameter("edate"), today()));
			params.put("content",  checkStr(request.getParameter("content"), ""));
			params.put("displayYn",  checkStr(request.getParameter("displayYn"), ""));
			
			log.debug("@@@@@@@@@@ MemberEventEditorAct params : "+ params); //##
			
			result = memberEventService.updateMemberEvent(params);
			memberEventService.commit();
			request.setAttribute("actResult", result + "");
			
			params.clear();
			if(result == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/member_event_editor.ms?notiId=" + checkStr(request.getParameter("notiId"), "") );
			}
			else{
				request.setAttribute("targetUrl", "/member/member_event_list.ms");
			}
			
		}
		catch(Exception e){
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/member_event_editor.ms?notiId=" + checkStr(request.getParameter("notiId"), ""));
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : MemberEventDeleteAct
	 * @Description : 이벤트/공지를 삭제한다.(실제삭제)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.18
	 */
	@RequestMapping(value="/member/member_event_deleteAct.ms")
	public String SettlementDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		
		try{
			MemberEventService memberEventService = new MemberEventService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("notiId",  checkStr(request.getParameter("notiId"), ""));

			log.debug("@@@@@@@@@@ MemberEventDeleteAct params : "+ params); //##
			
			result = memberEventService.deleteMemberEvent(params);
			memberEventService.commit();
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 삭제 실패이면,
				request.setAttribute("targetUrl", "/member/member_event_editor.ms?notiId=" + checkStr(request.getParameter("notiId"), ""));
			}
			else{
				request.setAttribute("targetUrl", "/member/member_event_list.ms");
			}
			
			params.clear();
		}
		catch(Exception e){
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/member_event_editor.ms?notiId=" + checkStr(request.getParameter("notiId"), ""));
			
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

