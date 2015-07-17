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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

import com.wallet.membership.model.custom.Member;
import com.wallet.membership.model.custom.MemberAgree;

import com.wallet.membership.service.custom.MemberService;
import com.wallet.membership.service.custom.MemberAgreeService;


@Controller
public class MemberAgreeController extends CommonController {
	private final String PAGE_CODE = "MEMBER_AGREE_LIST";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * @Method Name : MemberAgreeList
	 * @Description : 결제사 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.05
	 */
	@RequestMapping(value="/member/member_agree_list.ms")
	public String MemberAgreeList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Member member = null;
		List<MemberAgree> memberAgreeList = null;
		String memberAgreeUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- 사용혜택 html url 경로
		
		MemberService memberService = new MemberService();
		MemberAgreeService memberAgreeService = new MemberAgreeService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String membId = checkStr(request.getParameter("membId"), "");
		String motherPage = checkStr(request.getParameter("motherPage"), "");
		request.setAttribute("upperMembId", checkStr(request.getParameter("upperMembId"), ""));
		
		params.put("membId", membId);
		
		request.setAttribute("motherPage", checkStr(request.getParameter("motherPage"), "mem"));
		
		
		log.debug("@@@@@@@@@@ MemberAgreeList params : "+ params); //##
		
		member = memberService.selectAMember(params);	
		memberAgreeList = memberAgreeService.selectMemberAgreeList(params); //-- 목록조회
		String[] agreeContent1 = new String[memberAgreeList.size()];
		for(int i=0; i<memberAgreeList.size(); i++){
			String tmp = memberAgreeList.get(i).getAgreeContent();
			
			 agreeContent1[i] = tmp.substring(tmp.lastIndexOf("/")+1);//, memberAgreeList.get(i).getAgreeContent().length()
			 System.out.print("i : " + tmp);//##
			 System.out.print("tmp : " + tmp);//##
			 System.out.print("tmp.lastIndexOf(/) + 1 : " + tmp.lastIndexOf("/") + 1);//##
		}
		
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("agreeContent1", agreeContent1);
		request.setAttribute("memberAgreeUrl", memberAgreeUrl);
		request.setAttribute("memberAgreeList", memberAgreeList);
		request.setAttribute("member", member);
		request.setAttribute("motherPage", motherPage);
		
		
		params.clear();
		return "member/member_agree_list";
	}
	
	/**
	 * @Method Name : MemberAgreeList
	 * @Description : 결제사 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.05
	 */
	@RequestMapping(value="/member/member_agree_manage.ms")
	public String MemberAgreeManage(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String memberAgreeUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- 사용혜택 html url 경로
		
		Member member = null;
		int result = 1;
		List<MemberAgree> memberAgreeList = null;
		
		MemberService memberService = new MemberService();
		MemberAgreeService memberAgreeService = new MemberAgreeService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String membId = checkStr(request.getParameter("membId"), "");
		String upperMembId = checkStr(request.getParameter("upperMembId"), "");
		String motherPage = checkStr(request.getParameter("motherPage"), "mem");
		
		String agreeCode[] = request.getParameterValues("agreeCode");
		String agreeVer[] = request.getParameterValues("agreeVer");
		String agreeTitle[] = request.getParameterValues("agreeTitle");
		String agreeContent[] = request.getParameterValues("agreeContent");
		String agreeDpSeq[] = request.getParameterValues("agreeDpSeq");
		String agreeDispDtm[] = request.getParameterValues("agreeDispDtm");
		String regDtm[] = request.getParameterValues("regDtm");
		String seqNo[] = request.getParameterValues("seqNo");
		String[] agreeContent1 = request.getParameterValues("agreeContent1"); //DE
		
		int varLength = agreeCode.length;

		memberAgreeList = memberAgreeService.selectMemberAgreeList(params); //-- 목록조회
		/*
		for(int i=0; i<memberAgreeList.size(); i++){
			String tmp = memberAgreeList.get(i).getAgreeContent();
			
			 agreeContent1[i] = tmp.substring(tmp.lastIndexOf("/")+1);//, memberAgreeList.get(i).getAgreeContent().length()
			 System.out.print("i : " + tmp);//##
			 System.out.print("tmp : " + tmp);//##
			 System.out.print("tmp.lastIndexOf(/) + 1 : " + tmp.lastIndexOf("/") + 1);//##
		}
		*/
		params.put("membId", membId);
		memberAgreeService.deleteMemberAgree(params); //-- 수정 시, 등록된 모든 약관을 삭제 후 다시 insert 하도록 한다.
		params.clear();
		
		for(int i=0; i<varLength; i++){
			//agreeCompulsoryYN[i] = checkStr(request.getParameter("agreeCompulsoryYN_" + i), "");
			//useYN[i] = checkStr(request.getParameter("useYN_" + i), "");
			params.put("membId", membId);
			params.put("agreeCode", agreeCode[i]);
			params.put("agreeVer", agreeVer[i]);
			params.put("agreeTitle", agreeTitle[i]);
			params.put("agreeContent", agreeContent[i]);
			params.put("agreeDpSeq", agreeDpSeq[i]);
			params.put("agreeDispDtm", agreeDispDtm[i]);
			params.put("useYN", checkStr(request.getParameter("useYN_" + i), "N"));
			params.put("agreeCompulsoryYN", checkStr(request.getParameter("agreeCompulsoryYN_" + i), "N"));

			params.put("agreeContent1", agreeContent1[i]);
			
			//if("".equals(checkStr(regDtm[i], "")) && "".equals(checkStr(seqNo[i], ""))){ //-- 등록일이 없으면 insert
				result = result * memberAgreeService.insertMemberAgree(params);
			/*}
			else{ //-- 등록일이 있으면 update
				params.put("regDtm", regDtm);
				params.put("seqNo", checkStr(seqNo[i], ""));
				result = result * memberAgreeService.updateMemberAgree(params);
			}
			*/
			params.clear();
		}
		
		if(result == 1){
			memberAgreeService.commit();
		}else{
			memberAgreeService.rollback();
		}
		
		log.debug("@@@@@@@@@@ MemberAgreeManage params : "+ params); //##
		
		params.put("membId", membId);
		member = memberService.selectAMember(params);	
		memberAgreeList = memberAgreeService.selectMemberAgreeList(params); //-- 목록조회
		
		/* SET ATTRIBUTEs */
		request.setAttribute("agreeContent1", agreeContent1);
		request.setAttribute("memberAgreeUrl", memberAgreeUrl);
		request.setAttribute("memberAgreeList", memberAgreeList);
		request.setAttribute("member", member);
		request.setAttribute("motherPage", motherPage);
		request.setAttribute("upperMembId", upperMembId);
		
		return "member/member_agree_list";
		//MemberAgreeList(model, locale, request, response);
	}
}
