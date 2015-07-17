package com.wallet.membership.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.custom.MemberManual;
import com.wallet.membership.service.custom.ImageService;
import com.wallet.membership.service.custom.MemberManualService;

@Controller
public class MemberManualController extends CommonController {
	private final String PAGE_CODE = "MEMBER_MANUAL_LIST";
	private Logger log = Log.getLogger("logs");
	

	
	/**
	 * @Method Name : MemberManualList
	 * @Description : 멤버십(카드정보) 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_list.ms")
	public String MemberManualList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MemberManual> memberManualList = null;
		MemberManualService memberManualService = new MemberManualService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_showYN", checkStr(request.getParameter("ra_showYN"), "")); //-- 운영상태에 대한 기본값 설정
		params.put("membName", checkStr(request.getParameter("membName"), "")); //-- 선택된 멤버십(카드정보)가 멤버십(카드정보)명
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

		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int memberManualListCnt = memberManualService.selectMemberManualListCnt(params); //-- 총 목록 수
		Paging page = new Paging();
		page.makeWebPaging(nowPage, memberManualListCnt, rowsPerPage);
		
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
		
		log.debug("@@@@@@@@@@ MemberManualList params : "+ params); //##
		
 		memberManualList = memberManualService.selectMemberManualList(params); //-- 목록조회
		
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("memberManualList", memberManualList);
		request.setAttribute("membName", params.get("membName"));
		request.setAttribute("ra_showYN", params.get("ra_showYN"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt", params.get("se_termOpt"));
		
		params.clear();
		return "member/member_manual_list";
	}
	
	
	/**
	 * @Method Name : MemberManualRegister
	 * @Description : 직접등록 멤버십 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_register.ms")
	public String MemberManualRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "member/member_manual_register";
	}
	
	
	/**
	 * @Method Name : MemberManualRegisterAct
	 * @Description : 직접등록 멤버십를 등록 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_registerAct.ms")
	public String MemberManualRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0; //-- 이미지 등록의 실행결과 0:실패, 1:성공
		
		MemberManualService memberManualService = new MemberManualService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String selfMembId = "SEL"+ checkStr(request.getParameter("selfMembId"), "");
		String regUser = getSessionName(request);
		
		try{
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("regUser", regUser); //-- 등록자ID
			params.put("selfMembId", selfMembId) ;
			params.put("membName", checkStr(request.getParameter("membName"), ""));
			params.put("detMemo", checkStr(request.getParameter("detMemo"), ""));
			params.put("showYN", checkStr(request.getParameter("showYN"), "N"));
			
			/*이미지 등록 해야함.*/
			String listImg_i3GS = checkStr(request.getParameter("listImg_i3GS"), "");
			String listImg_i4S = checkStr(request.getParameter("listImg_i4S"), "");
			String listImg_android = checkStr(request.getParameter("listImg_android"), "");
			String detailImg_i3GS = checkStr(request.getParameter("detailImg_i3GS"), "");
			String detailImg_i4S = checkStr(request.getParameter("detailImg_i4S"), "");
			String detailImg_android = checkStr(request.getParameter("detailImg_android"), "");
			
			log.debug("@@@@@@@@@@ MemberManualRegisterAct params : "+ params); //##
			
			result = memberManualService.insertMemberManual(params); //-- 멤버십 등록
			
			/*
			imgParams.put("imageHost", PropertiesUtil.get("MEMBERSHIP_DEFAULT_PATH")  +"02"+  "/" +"01"+ "/");
			imgParams.put("imageUrl", PropertiesUtil.get("MEMBERSHIP_DEFAULT_URL_PATH"));
			*/
			imgParams.put("regUser", regUser); //-- 등록자ID
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "01");
			//imgParams.put("imageHost", listImg_i3GS.substring(listImg_i3GS.lastIndexOf("/")));
			//imgParams.put("imageUrl", listImg_i3GS.substring(0, listImg_i3GS.lastIndexOf("/")));
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i3GS);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("regUser", regUser); //-- 등록자ID
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "02");
			//imgParams.put("imageHost", listImg_i4S.substring(listImg_i4S.lastIndexOf("/")));
			//imgParams.put("imageUrl", listImg_i4S.substring(0, listImg_i4S.lastIndexOf("/")));
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("regUser", regUser); //-- 등록자ID
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "11");
			//imgParams.put("imageHost", listImg_android.substring(listImg_android.lastIndexOf("/")));
			//imgParams.put("imageUrl", listImg_android.substring(0, listImg_android.lastIndexOf("/")));
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("regUser", regUser); //-- 등록자ID
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "01");
			//imgParams.put("imageHost", detailImg_i3GS.substring(detailImg_i3GS.lastIndexOf("/")));
			//imgParams.put("imageUrl", detailImg_i3GS.substring(0, detailImg_i3GS.lastIndexOf("/")));
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("regUser", regUser); //-- 등록자ID
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "02");
			//imgParams.put("imageHost", detailImg_i4S.substring(detailImg_i4S.lastIndexOf("/")));
			//imgParams.put("imageUrl", detailImg_i4S.substring(0, detailImg_i4S.lastIndexOf("/")));
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);

			imgParams.clear();
			imgParams.put("regUser", regUser); //-- 등록자ID
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "11");
			//imgParams.put("imageHost", detailImg_android.substring(detailImg_android.lastIndexOf("/")));
			//imgParams.put("imageUrl", detailImg_android.substring(0, detailImg_android.lastIndexOf("/")));
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 수정 실패이면,
				memberManualService.rollback();
				imgService.rollback();
				request.setAttribute("targetUrl", "/member/member_manual_register.ms");
			}
			else{
				memberManualService.commit();
				imgService.commit();
				request.setAttribute("targetUrl", "/member/member_manual_list.ms");
			}
			
			params.clear();
		}
		catch(Exception e){
			memberManualService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_manual_register.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : MemberManualEditor
	 * @Description : 직접등록 멤버십 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_editor.ms")
	public String MemberManualEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		MemberManualService memberManualService = new MemberManualService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		//-- 선택한 멤버십의 정보를 조회 해온다.(멤버십 명... 등)
		String selfMembId = checkStr(request.getParameter("selfMembId"), "");

		try{
			String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- 사용혜택 html url 경로
			
			params.put("selfMembId", selfMembId); //-- 선택된 직접등록 멤버십가 있을 때
	
			log.debug("@@@@@@@@@@ MemberManualEditor params : "+ params); //##
			
			MemberManual aMemberMenual = memberManualService.selectAMemberManual(params); //-- 멤버십조회
			
			request.setAttribute("selfMembId", selfMembId);
			
			request.setAttribute("aMemberMenual", aMemberMenual);
			request.setAttribute("memberNotiUrl", memberNotiUrl);
			
			params.clear();
			return "member/member_manual_editor";
		}
		catch(Exception e){
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/member/member_manual_list.ms?selfMembId=" + selfMembId);
			
			e.printStackTrace();
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : MemberManualEditorAct
	 * @Description : 직접등록 멤버십를 수정 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_editorAct.ms")
	public String MemberManualEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0;

		MemberManualService memberManualService = new MemberManualService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String selfMembId = checkStr(request.getParameter("selfMembId"), "");
		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String chgUser = getSessionName(request);
		try{
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("selfMembId", selfMembId);
			
			params.put("membName", checkStr(request.getParameter("membName"), ""));
			params.put("detMemo", checkStr(request.getParameter("detMemo"), ""));
			params.put("showYN", checkStr(request.getParameter("showYN"), "N"));
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("chgUser", chgUser); //-- 수정자ID
			
			/*이미지 등록 해야함.*/
			String listImg_i3GS = checkStr(request.getParameter("listImg_i3GS"), "");
			String listImg_i4S = checkStr(request.getParameter("listImg_i4S"), "");
			String listImg_android = checkStr(request.getParameter("listImg_android"), "");
			String detailImg_i3GS = checkStr(request.getParameter("detailImg_i3GS"), "");
			String detailImg_i4S = checkStr(request.getParameter("detailImg_i4S"), "");
			String detailImg_android = checkStr(request.getParameter("detailImg_android"), "");
			
			log.debug("@@@@@@@@@@ MemberManualEditorAct params : "+ params); //##
			
			result = memberManualService.updateMemberManual(params);

			
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "01");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", listImg_i3GS.substring(listImg_i3GS.lastIndexOf("/")));
			//imgParams.put("imageUrl", listImg_i3GS.substring(0, listImg_i3GS.lastIndexOf("/")));
			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i3GS);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", listImg_i4S.substring(listImg_i4S.lastIndexOf("/")));
			//imgParams.put("imageUrl", listImg_i4S.substring(0, listImg_i4S.lastIndexOf("/")));
			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", listImg_android.substring(listImg_android.lastIndexOf("/")));
			//imgParams.put("imageUrl", listImg_android.substring(0, listImg_android.lastIndexOf("/")));
			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "01");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", detailImg_i3GS.substring(detailImg_i3GS.lastIndexOf("/")));
			//imgParams.put("imageUrl", detailImg_i3GS.substring(0, detailImg_i3GS.lastIndexOf("/")));
			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", detailImg_i4S.substring(detailImg_i4S.lastIndexOf("/")));
			//imgParams.put("imageUrl", detailImg_i4S.substring(0, detailImg_i4S.lastIndexOf("/")));
			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);

			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", selfMembId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", detailImg_android.substring(detailImg_android.lastIndexOf("/")));
			//imgParams.put("imageUrl", detailImg_android.substring(0, detailImg_android.lastIndexOf("/")));
			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			request.setAttribute("selfMembId", selfMembId);
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/member_manual_editor.ms?selfMembId=" + selfMembId);
				memberManualService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/member/member_manual_list.ms");
				memberManualService.commit();
				imgService.commit();
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_manual_editor.ms?selfMembId=" + selfMembId);
			
			memberManualService.rollback();
			imgService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : MemberManualDeleteAct
	 * @Description : 직접등록 멤버십를 삭제한다.(실제삭제)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_deleteAct.ms")
	public String MemberManualDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0;
		
		MemberManualService memberManualService = new MemberManualService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String selfMembId = checkStr(request.getParameter("selfMembId"), "");
		
		try{
			params.put("selfMembId", selfMembId);
			imgParams.put("id", selfMembId);

			log.debug("@@@@@@@@@@ MemberManualDeleteAct params : "+ params); //##
			
			result = memberManualService.deleteMemberManual(params);
			imgResult = imgService.deleteImage(imgParams);
			
			request.setAttribute("selfMembId", selfMembId);
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 삭제 실패이면,
				request.setAttribute("targetUrl", "/member/member_manual_editor.ms?selfMembId=" + selfMembId);
				
				memberManualService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/member/member_manual_list.ms");
				
				memberManualService.commit();
				imgService.commit();
			}
		}
			
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_manual_editor.ms?selfMembId=" + selfMembId);
			
			memberManualService.rollback();
			imgService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : memberManualDupCheck
	 * @Description : 멤버십 ID의 등록 전, 중복여부를 확인한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 0/1 (0:중복아님, 1:중복)
	 * @author trkim
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_dupCheck.ms")
	public String memberManualDupCheck(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response){

		MemberManualService memberManualService = new MemberManualService();
		String selfMembId = checkStr(request.getParameter("selfMembId"));
		JSONObject jObj = new JSONObject();
		
		int isDup = 0;
		isDup = memberManualService.memberManualDupCheck(selfMembId);
		//System.out.println("isDup:::"+isDup); //##
		
		jObj.put("isDup", String.valueOf(isDup));
		
		request.setAttribute("JSONObject", jObj);
		
		return "common/result_page";
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
