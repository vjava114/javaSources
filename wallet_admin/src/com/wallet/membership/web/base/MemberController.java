/**
 * 
 */
package com.wallet.membership.web.base;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.custom.Image;
import com.wallet.membership.model.custom.Member;
import com.wallet.membership.service.custom.ImageService;
import com.wallet.membership.service.custom.MemberService;

@Controller
public class MemberController extends CommonController {
	private final String PAGE_CODE = "MEMBER_LIST";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * @Method Name : MemberList
	 * @Description : 멤버십(카드정보) 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@RequestMapping(value="/member/member_list.ms")
	public String MemberList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Member> memberList = null;
		MemberService memberService = new MemberService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_stat", checkStr(request.getParameter("ra_stat"), "")); //-- 운영상태에 대한 기본값 설정
		params.put("seName", checkStr(request.getParameter("seName"), "")); //-- 선택된 멤버십(카드정보)가 멤버십(카드정보)명
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

//		params.put("membId", checkStr(request.getParameter("membId"), "")); //-- 선택된 멤버십(카드정보)가 있을 때
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int memberListCnt = memberService.selectMemberListCnt(params); //-- 총 목록 수
		Paging page = new Paging();
		page.makeWebPaging(nowPage, memberListCnt, rowsPerPage);
		
		if(page.getNowPage()>1){
			nowPage =  page.getNowPage();
			request.setAttribute("nowPage", nowPage);
			
		}else{
			nowPage = 1;
			request.setAttribute("nowPage", "1");
		}

		
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		
		log.debug("@@@@@@@@@@ MemberList params : "+ params); //##
		
		memberList = memberService.selectMemberList(params); //-- 목록조회
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("memberList", memberList);
		request.setAttribute("seName", params.get("seName"));
		request.setAttribute("ra_stat", params.get("ra_stat"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt", params.get("se_termOpt"));
		
		params.clear();
		return "member/member_list";
	}
	
	
	/**
	 * @Method Name : MemberRegister
	 * @Description : 멤버십(카드정보) 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@RequestMapping(value="/member/member_register.ms")
	public String MemberRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- 사용혜택 html url 경로
		
		request.setAttribute("today", today());
		request.setAttribute("memberNotiUrl", memberNotiUrl);
		return "member/member_register";
	}
	

	
	/**
	 * @Method Name : MemberRegisterAct
	 * @Description : 멤버십(카드정보)를 등록 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@RequestMapping(value="/member/member_registerAct.ms")
	public String MemberRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0; //-- 이미지 등록의 실행결과 0:실패, 1:성공
		
		MemberService memberService = new MemberService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String membId = checkStr(request.getParameter("membId"), "");
		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String regUser = getSessionName(request);
		
		try{
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("regUser", regUser); //-- 등록자ID
			
			params.put("jehyuId", checkStr(request.getParameter("jehyuId"), ""));
			params.put("branId", checkStr(request.getParameter("branId"), ""));
			params.put("membCode", checkStr(request.getParameter("membCode"), ""));
			params.put("membId", membId);
			params.put("name", checkStr(request.getParameter("membName"), ""));
			params.put("info", checkStr(request.getParameter("info"), ""));
			params.put("engNameYN", checkStr(request.getParameter("engNameYN"), "N"));
			params.put("passYN", checkStr(request.getParameter("passYN"), "N"));
			params.put("pntType", checkStr(request.getParameter("pntType"), "1"));
			params.put("pointType", checkStr(request.getParameter("pointType"), "01"));
			
			String tmpUsimMode = checkStr(request.getParameter("usimMode"), "bar");
			params.put("usimMode", tmpUsimMode);
			if(tmpUsimMode.equals("sim") || tmpUsimMode.equals("all")){ //-- 유심카드 사용일 경우,
				params.put("usimId", checkStr(request.getParameter("usimId"), ""));
			}
			
			params.put("barType", checkStr(request.getParameter("barType"), "01"));
			params.put("classYN", checkStr(request.getParameter("classYN"), "N"));
			
			String tmpStat = checkStr(request.getParameter("stat"), "R");
			params.put("stat", tmpStat);
			if(tmpStat.equals("R")){ //-- 제휴상태가 정상(제휴)일 때
				params.put("disSday", checkStr(request.getParameter("sdate"), today()));
				params.put("disEday", checkStr(request.getParameter("edate"), today()));
			}
			
			params.put("multiYN", checkStr(request.getParameter("multiYN"), "N"));
			params.put("cateId", checkStr(request.getParameter("cateId"), ""));
			params.put("notiUrl", checkStr(request.getParameter("notiUrl"), "N"));
			params.put("shopinfoYN", checkStr(request.getParameter("shopinfoYN"), "N"));
			params.put("shopinfoUrl", checkStr(request.getParameter("shopinfoUrl"), ""));
			params.put("smsYN", checkStr(request.getParameter("smsYN"), "N"));
			params.put("emailYN", checkStr(request.getParameter("emailYN"), "N"));
			
			params.put("membJoinYN", checkStr(request.getParameter("membJoinYN"), "Y")); //-- 멤버십 가입 진행여부.  Y:멤버십가입진행가능, N:멤버십가입진행불가 (주민등록번호를 CI로 변경하는 제휴사의 진행일정이 서로 달라 관리하는 항목이 추가됨)
			params.put("membJoinNMsg", checkStr(request.getParameter("membJoinNMsg"), "")); //-- 멤버십 가입불가 메세지 (가입진행불가시, 등록될 수 있는 메세지)
			
			String listImg_i3GS = checkStr(request.getParameter("listImg_i3GS"), "");
			String listImg_i4S = checkStr(request.getParameter("listImg_i4S"), "");
			String listImg_android = checkStr(request.getParameter("listImg_android"), "");
			String detailImg_i3GS = checkStr(request.getParameter("detailImg_i3GS"), "");
			String detailImg_i4S = checkStr(request.getParameter("detailImg_i4S"), "");
			String detailImg_android = checkStr(request.getParameter("detailImg_android"), "");
			String memberImg_i3GS = checkStr(request.getParameter("memberImg_i3GS"), "");
			String memberImg_i4S = checkStr(request.getParameter("memberImg_i4S"), "");
			String memberImg_android = checkStr(request.getParameter("memberImg_android"), "");
			
			params.put("subYN", "N");
			
			
			log.debug("@@@@@@@@@@ MemberRegisterAct params : "+ params); //##
			
			result = memberService.insertMember(params); //-- 멤버십 등록
			
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "01");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i3GS);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "02");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "11");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "01");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "02");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);

			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "11");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "01");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "02");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "11");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/member_register.ms");
				memberService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/member/member_list.ms");
				memberService.commit();
				imgService.commit();
			}
			
			params.clear();
		}
		catch(Exception e){
			memberService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_register.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : MemberEditor
	 * @Description : 멤버십(카드정보) 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@RequestMapping(value="/member/member_editor.ms")
	public String MemberEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		MemberService memberService = new MemberService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		String membId = checkStr(request.getParameter("membId"), "");
		String classCode = checkStr(request.getParameter("classCode"), "");
		
		try{
			String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- 사용혜택 html url 경로
			String today = today();
			
			params.put("membId", membId); //--선택된 멤버십(카드정보) ID
			params.put("classCode", classCode);

			log.debug("@@@@@@@@@@ MemberEditor params : "+ params); //##
			
			Member aMember = memberService.selectAMember(params); //-- 멤버십조회
			
			String notiUrl1 =  "";
			
			if(aMember.getNotiUrl() != null && !aMember.getNotiUrl().equals("")){
				notiUrl1 = aMember.getNotiUrl().substring(aMember.getNotiUrl().lastIndexOf("/")+1, aMember.getNotiUrl().length());
			}
			
			request.setAttribute("notiUrl1", notiUrl1);
			request.setAttribute("today", today);
			request.setAttribute("aMember", aMember);
			request.setAttribute("memberNotiUrl", memberNotiUrl);
			
			params.clear();
			return "member/member_editor";
		}
		catch(Exception e){
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/member/member_list.ms");
			
			e.printStackTrace();
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : MemberEditorAct
	 * @Description : 멤버십(카드정보)를 수정 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@RequestMapping(value="/member/member_editorAct.ms")
	public String MemberEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0;
		
		MemberService memberService = new MemberService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String membId = checkStr(request.getParameter("membId"), "");
		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String chgUser = getSessionName(request);
		
		try{
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("chgUser", chgUser); //-- 수정자IDparams.put("jehyuId", checkStr(request.getParameter("jehyuId"), ""));
			
			params.put("branId", checkStr(request.getParameter("branId"), ""));
			params.put("membCode", checkStr(request.getParameter("membCode"), ""));
			params.put("name", checkStr(request.getParameter("membName"), ""));
			params.put("membId", membId);
			//params.put("name", checkStr(request.getParameter("name"), ""));
			params.put("info", checkStr(request.getParameter("info"), ""));
			params.put("engNameYN", checkStr(request.getParameter("engNameYN"), "N"));
			params.put("passYN", checkStr(request.getParameter("passYN"), "N"));
			params.put("pntType", checkStr(request.getParameter("pntType"), "1"));
			params.put("pointType", checkStr(request.getParameter("pointType"), "01"));
			
			String tmpUsimMode = checkStr(request.getParameter("usimMode"), "bar");
			params.put("usimMode", tmpUsimMode);
			if(tmpUsimMode.equals("sim") || tmpUsimMode.equals("all")){ //-- 유심카드 사용일 경우,
				params.put("usimId", checkStr(request.getParameter("usimId"), ""));
			}
			
			params.put("barType", checkStr(request.getParameter("barType"), "01"));
			params.put("classYN", checkStr(request.getParameter("classYN"), "N"));
			
			String tmpStat = checkStr(request.getParameter("stat"), "R");
			params.put("stat", tmpStat);
			if(tmpStat.equals("R")){ //-- 제휴상태가 정상(제휴)일 때
				params.put("disSday", checkStr(request.getParameter("sdate"), today()));
				params.put("disEday", checkStr(request.getParameter("edate"), today()));
			}
			else{
				params.put("maindisYN", ""); //-- 제휴상태 중단 시, 전시순서 및 전시여부 값을 null 로 DB에 초기화 하기 위한 조치. 2012.10.17
				params.put("mainIdx", "");
			}
			
			params.put("multiYN", checkStr(request.getParameter("multiYN"), "N"));
			params.put("cateId", checkStr(request.getParameter("cateId"), ""));
			params.put("notiUrl", checkStr(request.getParameter("notiUrl"), "N"));
			params.put("shopinfoYN", checkStr(request.getParameter("shopinfoYN"), "N"));
			params.put("shopinfoUrl", checkStr(request.getParameter("shopinfoUrl"), ""));
			params.put("smsYN", checkStr(request.getParameter("smsYN"), "N"));
			params.put("emailYN", checkStr(request.getParameter("emailYN"), "N"));
			
			params.put("membJoinYN", checkStr(request.getParameter("membJoinYN"), "Y")); //-- 멤버십 가입 진행여부.  Y:멤버십가입진행가능, N:멤버십가입진행불가 (주민등록번호를 CI로 변경하는 제휴사의 진행일정이 서로 달라 관리하는 항목이 추가됨)
			params.put("membJoinNMsg", checkStr(request.getParameter("membJoinNMsg"), "")); //-- 멤버십 가입불가 메세지 (가입진행불가시, 등록될 수 있는 메세지)
			
			String listImg_i3GS = checkStr(request.getParameter("listImg_i3GS"), "");
			String listImg_i4S = checkStr(request.getParameter("listImg_i4S"), "");
			String listImg_android = checkStr(request.getParameter("listImg_android"), "");
			String detailImg_i3GS = checkStr(request.getParameter("detailImg_i3GS"), "");
			String detailImg_i4S = checkStr(request.getParameter("detailImg_i4S"), "");
			String detailImg_android = checkStr(request.getParameter("detailImg_android"), "");
			String memberImg_i3GS = checkStr(request.getParameter("memberImg_i3GS"), "");
			String memberImg_i4S = checkStr(request.getParameter("memberImg_i4S"), "");
			String memberImg_android = checkStr(request.getParameter("memberImg_android"), "");
			
			params.put("subYN", "N");
			
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "01");
			imgService.deleteImage(imgParams);
			
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i3GS);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);
			
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "01");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);

			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "01");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);

			log.debug("@@@@@@@@@@ MemberEditorAct params : "+ params); //##
			
			result = memberService.updateMember(params);

			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/member_editor.ms?membId=" + membId);
				memberService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/member/member_list.ms");
				memberService.commit();
				imgService.commit();
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_editor.ms?membId=" + membId);

			memberService.rollback();
			imgService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : MemberDeleteAct
	 * @Description : 멤버십(카드정보)를 삭제한다.(실제삭제)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@RequestMapping(value="/member/member_deleteAct.ms")
	public String MemberDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0;
		
		MemberService memberService = new MemberService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String membId = checkStr(request.getParameter("membId"), "");
		try{
			params.put("membId", membId);
			imgParams.put("id", membId);

			log.debug("@@@@@@@@@@ MemberDeleteAct params : "+ params); //##
			
			result = memberService.deleteMember(params);
			imgResult = imgService.deleteImage(imgParams);
			
			request.setAttribute("membId", membId);
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 삭제 실패이면,				
				memberService.rollback();
				imgService.rollback();
				
				request.setAttribute("targetUrl", "/member/member_editor.ms?membId=" + membId);
			}
			else{
				memberService.commit();
				imgService.commit();
				
				request.setAttribute("targetUrl", "/member/member_list.ms");
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_editor.ms?membId=" + membId);
			
			imgService.rollback();
			memberService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : memberDupCheck
	 * @Description : 멤버십 ID의 등록 전, 중복여부를 확인한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 0/1 (0:중복아님, 1:중복)
	 * @author trkim
	 * @since 2012.09.11
	 */
	@RequestMapping(value="/member/member_dupCheck.ms")
	public String memberDupCheck(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response){

		MemberService memberService = new MemberService();
		String membId = checkStr(request.getParameter("membId"));
		JSONObject jObj = new JSONObject();
		
		int isDup = 0;
		isDup = memberService.memberDupCheck(membId);
		//System.out.println("isDup:::"+isDup); //##
		
		jObj.put("isDup", String.valueOf(isDup));
		
		request.setAttribute("JSONObject", jObj);
		
		return "common/result_page";
	}
	
	
	/**
	 * @Method Name : MemberNotiHtmlUpload
	 * @Description : 멤버십의 사용안내(사용처/혜택안내) 소개 html 및 images를 upload 한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@RequestMapping(value="/member/html_upload.ms")
	public String MemberNotiHtmlUpload(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.imageUpload ##########");

		System.out.println("######content-type: " + request.getContentType());
		System.out.println("######length: " + request.getContentLength());

		String os_memNotiHtmlUp = checkStr(request.getParameter("os_memNotiHtmlUp"));
		String part_memNotiHtmlUp = checkStr(request.getParameter("part_memNotiHtmlUp"));
		String radio_upload = checkStr(request.getParameter("radio_upload"));
		
		System.out.println("###### os_memNotiHtmlUp:" + os_memNotiHtmlUp);
		System.out.println("###### part_memNotiHtmlUp:" + part_memNotiHtmlUp);

		String path = "";	// 이미지 경로
		String filePath = "";
		String fileName = "";
		boolean fileYN = true;
		String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- 사용혜택 html url 경로
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	// 이미지(아이폰4,아이폰3GS,만드로이드)

		Iterator fileNameIterator = mpRequest.getFileNames();
		while (fileNameIterator.hasNext()) {
	
			MultipartFile multiFile = mpRequest.getFile((String)fileNameIterator.next());
			if (multiFile.getSize() > 0) {
	
				System.out.println("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename()); //##
	
				//해당 경로에 따른 이미지 파일 저장
				//path = "C:/kt_mywallet/upload_image/";
				if("page".equals("radio_upload")){
					path = PropertiesUtil.get("MEMBERSHIP_HTML_DEFAULT_URL_PATH");
				}else{
					path = PropertiesUtil.get("MEMBERSHIP_HTML_IMG_URL_PATH");
				}
				
				path += part_memNotiHtmlUp + "/";
				path += os_memNotiHtmlUp + "/";
	
				fileName = multiFile.getOriginalFilename();
				filePath = path + fileName;
	
				fileYN = FileUtil.isFile(filePath); //-- 파일 존재여부 판단
	
				try {
					System.out.println("### path : " + path); //##
					
					if(!fileYN){
						FileUtil.writeFile(multiFile, path);
					}
				} catch (Exception e) {
					System.out.println("########### Upload Error ##############"); //##
					e.printStackTrace();
				}
			}
		}
	
		//이미지 저장 경로
		JSONObject jObj = new JSONObject();
		jObj.put("fileYN", fileYN);
		jObj.put("fileName", fileName);
		jObj.put("upload_gb", radio_upload);
		jObj.put("filePath", filePath);
		jObj.put("memberNotiUrl", memberNotiUrl);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}
	


	/**
	 * @Method Name : MultiMemberList
	 * @Description : 멀티멤버십 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.17
	 */
	@RequestMapping(value="/member/member_multi_list.ms")
	public String MultiMemberList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Member> multiMemberList = null;
		MemberService memberService = new MemberService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		String upperMembId = checkStr(request.getParameter("upperMembId"), ""); //-- 선택된 멤버십(카드정보)가 멤버십(카드정보)명
		params.put("membId", upperMembId);
		Member upperMember = (Member) memberService.selectAMember(params);
		
		request.setAttribute("upperMember", upperMember);
		params.clear();
		
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); //-- 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); //-- 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("upperMembId", upperMembId);		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));

		log.debug("@@@@@@@@@@ MemberList params : "+ params); //##
		
 		multiMemberList = memberService.selectMultiMemberList(params); //-- 목록조회
		int multiMemberListCnt = memberService.selectMultiMemberListCnt(params); //-- 총 목록 수
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, multiMemberListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		/******* paging end *********/
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("multiMemberList", multiMemberList);
		
		params.clear();
		return "member/member_multi_list";
	}
	
	
	/**
	 * @Method Name : MultiMemberRegister
	 * @Description : 멤버십(카드정보) 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.17
	 */
	@RequestMapping(value="/member/member_multi_register.ms")
	public String MultiMemberRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- 사용혜택 html url 경로
		
		MemberService memberService = new MemberService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		String upperMembId = checkStr(request.getParameter("upperMembId"), ""); //-- 선택된 멤버십(카드정보)가 멤버십(카드정보)명
		params.put("membId", upperMembId);
		Member upperMember = (Member) memberService.selectAMember(params);
		
		request.setAttribute("upperMember", upperMember);
		request.setAttribute("memberNotiUrl", memberNotiUrl);
		
		request.setAttribute("today", today());
		
		return "member/member_multi_register";
	}

	/**
	 * @Method Name : MultiMemberRegisterAct
	 * @Description : 멤버십(카드정보) 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.17
	 */
	@RequestMapping(value="/member/member_multi_registerAct.ms")
	public String MultiMemberRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0; //-- 이미지 등록의 실행결과 0:실패, 1:성공
		
		MemberService memberService = new MemberService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String regUser = getSessionName(request);
		
		String membId = checkStr(request.getParameter("membId"), "");
		String upperMembId = checkStr(request.getParameter("upperMembId"), ""); //-- 선택된 멤버십(카드정보)가 멤버십(카드정보)명
		
		request.setAttribute("today", today());
		params.clear();
		
		try{
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("regUser", regUser); //-- 등록자ID
			
			params.put("jehyuId", checkStr(request.getParameter("jehyuId"), ""));
			params.put("branId", checkStr(request.getParameter("branId"), ""));
			params.put("membCode", checkStr(request.getParameter("membCode"), ""));
			params.put("membId", membId);
			params.put("upperMembId", upperMembId);
			params.put("name", checkStr(request.getParameter("membName"), ""));
			params.put("info", checkStr(request.getParameter("info"), ""));
			params.put("engNameYN", checkStr(request.getParameter("engNameYN"), "N"));
			params.put("passYN", checkStr(request.getParameter("passYN"), "N"));
			params.put("pntType", checkStr(request.getParameter("pntType"), "1"));
			params.put("pointType", checkStr(request.getParameter("pointType"), "01"));
			
			String tmpUsimMode = checkStr(request.getParameter("usimMode"), "bar");
			params.put("usimMode", tmpUsimMode);
			if(tmpUsimMode.equals("sim") || tmpUsimMode.equals("all")){ //-- 유심카드 사용일 경우,
				params.put("usimId", checkStr(request.getParameter("usimId"), ""));
			}
			
			params.put("barType", checkStr(request.getParameter("barType"), "01"));
			params.put("classYN", checkStr(request.getParameter("classYN"), "N"));
			
			String tmpStat = checkStr(request.getParameter("stat"), "R");
			params.put("stat", tmpStat);
			if(tmpStat.equals("R")){ //-- 제휴상태가 정상(제휴)일 때
				params.put("disSday", checkStr(request.getParameter("sdate"), today()));
				params.put("disEday", checkStr(request.getParameter("edate"), today()));
			}
			
			params.put("multiYN", checkStr(request.getParameter("multiYN"), "N"));
			params.put("cateId", checkStr(request.getParameter("cateId"), ""));
			params.put("notiUrl", checkStr(request.getParameter("notiUrl"), "N"));
			params.put("shopinfoYN", checkStr(request.getParameter("shopinfoYN"), "N"));
			params.put("shopinfoUrl", checkStr(request.getParameter("shopinfoUrl"), ""));
			params.put("smsYN", checkStr(request.getParameter("smsYN"), "N"));
			params.put("emailYN", checkStr(request.getParameter("emailYN"), "N"));
			
			params.put("membJoinYN", checkStr(request.getParameter("membJoinYN"), "Y")); //-- 멤버십 가입 진행여부.  Y:멤버십가입진행가능, N:멤버십가입진행불가 (주민등록번호를 CI로 변경하는 제휴사의 진행일정이 서로 달라 관리하는 항목이 추가됨)
			params.put("membJoinNMsg", checkStr(request.getParameter("membJoinNMsg"), "")); //-- 멤버십 가입불가 메세지 (가입진행불가시, 등록될 수 있는 메세지)
			
			
			String listImg_i3GS = checkStr(request.getParameter("listImg_i3GS"), "");
			String listImg_i4S = checkStr(request.getParameter("listImg_i4S"), "");
			String listImg_android = checkStr(request.getParameter("listImg_android"), "");
			String detailImg_i3GS = checkStr(request.getParameter("detailImg_i3GS"), "");
			String detailImg_i4S = checkStr(request.getParameter("detailImg_i4S"), "");
			String detailImg_android = checkStr(request.getParameter("detailImg_android"), "");
			String memberImg_i3GS = checkStr(request.getParameter("memberImg_i3GS"), "");
			String memberImg_i4S = checkStr(request.getParameter("memberImg_i4S"), "");
			String memberImg_android = checkStr(request.getParameter("memberImg_android"), "");
			
			params.put("subYN", "N");
			
			//--######
			/*사용안내(혜택안내), 가맹점정보 제공/미제공/url제공 부분.. 처리 안됨.*/
			
			log.debug("@@@@@@@@@@ MemberRegisterAct params : "+ params); //##
			
			result = memberService.insertMember(params); //-- 멤버십 등록
			

			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "01");

			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i3GS);
			imgParams.put("regUser", regUser);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "02");

			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i4S);
			imgParams.put("regUser", regUser);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "11");

			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_android);
			imgParams.put("regUser", regUser);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "01");

			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i3GS);
			imgParams.put("regUser", regUser);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "02");

			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i4S);
			imgParams.put("regUser", regUser);
			imgResult = imgResult * imgService.insertImage(imgParams);

			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "11");

			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_android);
			imgParams.put("regUser", regUser);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "01");

			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_i3GS);
			imgParams.put("regUser", regUser);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "02");

			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_i4S);
			imgParams.put("regUser", regUser);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "11");

			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_android);
			imgParams.put("regUser", regUser);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			if((result * imgResult) == 0){
				memberService.rollback();
				imgService.rollback();
			}
			else{
				memberService.commit();
				imgService.commit();
			}
			
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if(result == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/member_multi_register.ms?upperMembId=" + upperMembId);
			}
			else{
				request.setAttribute("targetUrl", "/member/member_multi_list.ms?upperMembId=" + upperMembId);
			}
			
			params.clear();
		}
		catch(Exception e){
			memberService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_multi_register.ms?upperMembId=" + upperMembId);
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : MultiMemberEditor
	 * @Description : 멀티멤버십(카드정보) 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.17
	 */
	@RequestMapping(value="/member/member_multi_editor.ms")
	public String MultiMemberEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		MemberService memberService = new MemberService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		

		String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- 사용혜택 html url 경로
		
		String membId = checkStr(request.getParameter("membId"), "");
		String upperMembId = checkStr(request.getParameter("upperMembId"), ""); //-- 선택된 멤버십(카드정보)가 멤버십(카드정보)명
		
		params.put("membId", upperMembId);
		Member upperMember = (Member) memberService.selectAMember(params);
		
		String notiUrl1 ="";
		if(upperMember.getNotiUrl() != null && !upperMember.getNotiUrl().equals("")){
			notiUrl1 = upperMember.getNotiUrl().substring(upperMember.getNotiUrl().lastIndexOf("/")+1, upperMember.getNotiUrl().length());
		}
		
		params.clear();
		request.setAttribute("notiUrl1", notiUrl1);
		request.setAttribute("upperMember", upperMember);
		request.setAttribute("memberNotiUrl", memberNotiUrl);
		
		try{
			String today = today();
			
			params.put("membId", membId); //--선택된 멤버십(카드정보) ID
			imgParams.put("id", membId); 

			log.debug("@@@@@@@@@@ MemberEditor params : "+ params); //##
			
			Member aMember = memberService.selectAMember(params); //-- 멤버십조회
			List<Image> imgList = imgService.selectImageList(imgParams);
			
			request.setAttribute("today", today);
			request.setAttribute("aMember", aMember);
			request.setAttribute("imgList", imgList);
			
			params.clear();
			return "member/member_multi_editor";
		}
		catch(Exception e){
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/member/member_multi_list.ms?membId="+membId+"&upperMembId="+upperMembId);
			
			e.printStackTrace();
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : MultiMemberEditorAct
	 * @Description : 멀티멤버십(카드정보)를 수정 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.17
	 */
	@RequestMapping(value="/member/member_multi_editorAct.ms")
	public String MultiMemberEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0;
		
		MemberService memberService = new MemberService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String membId = checkStr(request.getParameter("membId"), "");
		String upperMembId = checkStr(request.getParameter("upperMembId"), "");
		String chgUser = getSessionName(request);
		
		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		
		try{
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("chgUser", chgUser); //-- 수정자IDparams.put("jehyuId", checkStr(request.getParameter("jehyuId"), ""));
			
			params.put("branId", checkStr(request.getParameter("branId"), ""));
			params.put("membCode", checkStr(request.getParameter("membCode"), ""));
			params.put("membId", membId);
			params.put("upperMembId", upperMembId);
			params.put("name", checkStr(request.getParameter("name"), ""));
			params.put("info", checkStr(request.getParameter("info"), ""));
			params.put("engNameYN", checkStr(request.getParameter("engNameYN"), "N"));
			params.put("passYN", checkStr(request.getParameter("passYN"), "N"));
			params.put("pntType", checkStr(request.getParameter("pntType"), "1"));
			params.put("pointType", checkStr(request.getParameter("pointType"), "01"));
			
			String tmpUsimMode = checkStr(request.getParameter("usimMode"), "bar");
			params.put("usimMode", tmpUsimMode);
			if(tmpUsimMode.equals("sim") || tmpUsimMode.equals("all")){ //-- 유심카드 사용일 경우,
				params.put("usimId", checkStr(request.getParameter("usimId"), ""));
			}
			
			params.put("barType", checkStr(request.getParameter("barType"), "01"));
			params.put("classYN", checkStr(request.getParameter("classYN"), "N"));
			
			String tmpStat = checkStr(request.getParameter("stat"), "R");
			params.put("stat", tmpStat);
			if(tmpStat.equals("R")){ //-- 제휴상태가 정상(제휴)일 때
				params.put("disSday", checkStr(request.getParameter("sdate"), today()));
				params.put("disEday", checkStr(request.getParameter("edate"), today()));
			}
			
			params.put("multiYN", checkStr(request.getParameter("multiYN"), "N"));
			params.put("cateId", checkStr(request.getParameter("cateId"), ""));
			params.put("notiUrl", checkStr(request.getParameter("notiUrl"), "N"));
			params.put("shopinfoYN", checkStr(request.getParameter("shopinfoYN"), "N"));
			params.put("shopinfoUrl", checkStr(request.getParameter("shopinfoUrl"), ""));
			params.put("smsYN", checkStr(request.getParameter("smsYN"), "N"));
			params.put("emailYN", checkStr(request.getParameter("emailYN"), "N"));
			
			params.put("membJoinYN", checkStr(request.getParameter("membJoinYN"), "Y")); //-- 멤버십 가입 진행여부.  Y:멤버십가입진행가능, N:멤버십가입진행불가 (주민등록번호를 CI로 변경하는 제휴사의 진행일정이 서로 달라 관리하는 항목이 추가됨)
			params.put("membJoinNMsg", checkStr(request.getParameter("membJoinNMsg"), "")); //-- 멤버십 가입불가 메세지 (가입진행불가시, 등록될 수 있는 메세지)
			
			String listImg_i3GS = checkStr(request.getParameter("listImg_i3GS"), "");
			String listImg_i4S = checkStr(request.getParameter("listImg_i4S"), "");
			String listImg_android = checkStr(request.getParameter("listImg_android"), "");
			String detailImg_i3GS = checkStr(request.getParameter("detailImg_i3GS"), "");
			String detailImg_i4S = checkStr(request.getParameter("detailImg_i4S"), "");
			String detailImg_android = checkStr(request.getParameter("detailImg_android"), "");
			String memberImg_i3GS = checkStr(request.getParameter("memberImg_i3GS"), "");
			String memberImg_i4S = checkStr(request.getParameter("memberImg_i4S"), "");
			String memberImg_android = checkStr(request.getParameter("memberImg_android"), "");
			
			params.put("subYN", "N");
			
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "01");
			imgResult = imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "02");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "01");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);

			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "03");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "01");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "04");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser); //-- 등록자ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", memberImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("chgUser", "SESSION_ID"); //-- 수정자ID

			log.debug("@@@@@@@@@@ MemberEditorAct params : "+ params); //##
			
			result = memberService.updateMember(params);

			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/member_editor.ms?upperMembId=" + upperMembId);
				memberService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/member/member_multi_list.ms?upperMembId="+upperMembId);
				memberService.commit();
				imgService.commit();
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_multi_editor.ms?upperMembId=" + upperMembId);

			memberService.rollback();
			imgService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}

	
	
	/**
	 * @Method Name : MultiMemberDeleteAct
	 * @Description : 멤버십(카드정보)를 삭제한다.(실제삭제)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@RequestMapping(value="/member/member_multi_deleteAct.ms")
	public String MultiMemberDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		int imgResult = 0;
		
		MemberService memberService = new MemberService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String membId = checkStr(request.getParameter("membId"), "");
		String upperMembId = checkStr(request.getParameter("upperMembId"), "");
		
		try{
			params.put("membId", membId);
			imgParams.put("id", membId);

			log.debug("@@@@@@@@@@ MemberDeleteAct params : "+ params); //##
			
			result = memberService.deleteMember(params);
			imgResult = imgService.deleteImage(imgParams);
			
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- 삭제 실패이면,
				request.setAttribute("targetUrl", "/member/member_multi_editor.ms?membId=" + membId + "&upperMembId=" + upperMembId);
				memberService.rollback();
				imgService.rollback();
			}
			else{
				memberService.commit();
				imgService.commit();
				
				request.setAttribute("targetUrl", "/member/member_multi_list.ms?membId=" + membId + "&upperMembId=" + upperMembId);
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_multi_editor.ms?membId=" + membId + "&upperMembId=" + upperMembId);
			
			memberService.rollback();
			imgService.rollback();
			
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
	
	

	/**
	 * 멤버십 전시 순서 리스트 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/member_order_list.ms")
	public String memberOrderList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## MemberController.memberOrderList ##########");

		// 파라메터 초기화 
		String rSearchType = checkStr(request.getParameter("rSearchType"), "all");
		// 순서변경 관련 
		String membId = checkStr(request.getParameter("membId"), "");
		String bfVal = checkStr(request.getParameter("bfVal"), "");
		String afVal = checkStr(request.getParameter("afVal"), "");
		String chkVal = checkStr(request.getParameter("chkVal"), "");

		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("rSearchType: "+rSearchType);
		System.out.println("membId: "+membId);
		System.out.println("bfVal: "+bfVal);
		System.out.println("afVal: "+afVal);
		System.out.println("chkVal: "+chkVal);
		// TODO 삭제 /////////////////////////////////////////////////////////////
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("rSearchType", rSearchType);									//쿠폰구분
		params.put("membId", membId);
		params.put("bfVal", bfVal);
		params.put("afVal", afVal);
		params.put("chkVal", chkVal);


		MemberService memberService = new MemberService();
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		
		try {
			
			// 순서 조정
			if(!"".equals(membId) && !"".equals(bfVal) && !"".equals(afVal) ) {

				if( Integer.parseInt(bfVal) == 0 ) {
					// 신규등록된 멤버십 순위 선택 
					result = memberService.updateMemberOrderListIncrease(params);
					
				} else {
					// 
					if( Integer.parseInt(bfVal) < Integer.parseInt(afVal) )
						result = memberService.updateMemberOrderListGt(params);
					else
						result = memberService.updateMemberOrderListLt(params);
					
				}
				result = memberService.updateMemberOrder(params);
			

				// 추천 전시여부 초기화
				if("top5".equals(rSearchType))
					result = memberService.updateMembRecommInitAll(params);
				
			}
			
			// 전시여부 변경
			if( !"".equals(chkVal) && !"".equals(membId) ) {
				result = memberService.updateMemberShowYn(params);
			}
			
			
			memberService.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			memberService.rollback();
		}
		

		// 목록 조회 //
		List<Member> memberOrderList = memberService.selectMemberOrderList(params); 	// 목록조회
		System.out.println(">> memberOrderList : "+memberOrderList);
		

		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// 파라메타 변수 그대로 넘기기
		request.setAttribute("rSearchType", rSearchType);
		
		// 결과 넘기기
		request.setAttribute("dataList", memberOrderList);
		request.setAttribute("totcnt", memberOrderList.size());
		
		
		return "member/member_order_list";
	}
	
}
