/**
 * 
 */
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

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

import com.wallet.membership.model.custom.Member;
import com.wallet.membership.model.custom.MemberClass;
import com.wallet.membership.service.custom.ImageService;
import com.wallet.membership.service.custom.MemberService;
import com.wallet.membership.service.custom.MemberClassService;

@Controller
public class MemberClassController extends CommonController {
	private final String PAGE_CODE = "MEMBER_CLASS_LIST";
	private Logger log = Log.getLogger("logs");

	
	/**
	 * @Method Name : MemberClassList
	 * @Description : ����� ������� �˻�/���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@RequestMapping(value="/member/member_class_list.ms")
	public String MemberClassList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MemberClass> memberClassList= null;
		Member member = null;
		
		MemberService memberService = new MemberService();
		MemberClassService memberClassService = new MemberClassService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		//-- ������ ������� ������ ��ȸ �ؿ´�.(����� ��... ��)
		String membId = checkStr(request.getParameter("membId"), "");
		String motherPage = checkStr(request.getParameter("motherPage"), "");
		request.setAttribute("upperMembId", checkStr(request.getParameter("upperMembId"), ""));
		
		params.put("membId", membId); //-- ���õ� ����� ��������� ���� ��
		member = memberService.selectAMember(params);
		request.setAttribute("member", member);
		request.setAttribute("motherPage", checkStr(request.getParameter("motherPage"), "mem"));
		
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); //-- �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); //-- ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; //-- ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));

		log.debug("@@@@@@@@@@ MemberClassList params : "+ params); //##
		
		memberClassList= memberClassService.selectMemberClassList(params); //-- �����ȸ
		int memberClassListCnt = memberClassService.selectMemberClassListCnt(params); //-- �� ��� ��
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, memberClassListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		/******* paging end *********/
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("membId", membId);
		request.setAttribute("memberClassList", memberClassList);
		request.setAttribute("motherPage", motherPage);
		
		params.clear();
		return "member/member_class_list";
	}
	
	
	/**
	 * @Method Name : MemberClassRegister
	 * @Description : ����� ������� ���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@RequestMapping(value="/member/member_class_register.ms")
	public String MemberClassRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		Member member = null;
		MemberService memberService = new MemberService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		//-- ������ ������� ������ ��ȸ �ؿ´�.(����� ��... ��)
		String membId = checkStr(request.getParameter("membId"), "");
		String upperMembId = checkStr(request.getParameter("upperMembId"), "");
		params.put("membId", membId); //-- ���õ� ����� ��������� ���� ��
		member = memberService.selectAMember(params);
		

		request.setAttribute("motherPage", checkStr(request.getParameter("motherPage"), "mem"));
		request.setAttribute("membId", membId);
		request.setAttribute("upperMembId", upperMembId);
		request.setAttribute("member", member);
		
		return "member/member_class_register";
	}
	

	
	/**
	 * @Method Name : MemberClassRegisterAct
	 * @Description : ����� ��������� ��� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@RequestMapping(value="/member/member_class_registerAct.ms")
	public String MemberClassRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0; //-- �̹��� ����� ������ 0:����, 1:����
		
		Member member = null;
		
		MemberService memberService = new MemberService();
		MemberClassService memberClassService = new MemberClassService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		

		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		
		String membId = checkStr(request.getParameter("membId"), "");
		String upperMembId = checkStr(request.getParameter("upperMembId"), "");
		String classCode = checkStr(request.getParameter("classCode"), "");

		String regUser = getSessionName(request);
		String motherPage = checkStr(request.getParameter("motherPage"), "mem");
		
		//-- ������ ������� ������ ��ȸ �ؿ´�.(����� ��... ��)
		params.put("membId", checkStr(request.getParameter("membId"), "")); //-- ���õ� ����� ��������� ���� ��
		
		member = memberService.selectAMember(params);
		request.setAttribute("member", member);
		
		try{
			
			//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)
			params.put("regUser", regUser); //-- �����ID
			params.put("membId", membId);
			
			params.put("className", checkStr(request.getParameter("className"), ""));
			params.put("classCode", checkStr(request.getParameter("classCode"), ""));
			params.put("addDcRate", checkStr(request.getParameter("addDcRate"), "0"));
			params.put("addSaveRate", checkStr(request.getParameter("addSaveRate"), "0"));
			params.put("addUseRate", checkStr(request.getParameter("addUseRate"), "0"));
			
			/*�̹��� ��� �ؾ���.*/
			String listImg_i3GS = checkStr(request.getParameter("listImg_i3GS"), "");
			String listImg_i4S = checkStr(request.getParameter("listImg_i4S"), "");
			String listImg_android = checkStr(request.getParameter("listImg_android"), "");
			String detailImg_i3GS = checkStr(request.getParameter("detailImg_i3GS"), "");
			String detailImg_i4S = checkStr(request.getParameter("detailImg_i4S"), "");
			String detailImg_android = checkStr(request.getParameter("detailImg_android"), "");
			
			log.debug("@@@@@@@@@@ MemberClassRegisterAct params : "+ params); //##
			
			memberClassService.deleteMemberClass(params);//-- ���� ���� �ȵ�����, ���� �߸� �� ������ ����� ���� ����ϱ� ���� ������.
			result = memberClassService.insertMemberClass(params); //-- ����� ���
			
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "01");
			imgService.deleteImage(imgParams);
			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i3GS);
			imgResult = imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);
			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);
			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "01");
			imgService.deleteImage(imgParams);
			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);
			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);

			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);
			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ��� �����̸�,
				request.setAttribute("targetUrl", "/member/member_class_register.ms?membId="+membId+"&motherPage=" + motherPage+ "&upperMembId=" + upperMembId);

				memberService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/member/member_class_list.ms?membId="+membId+"&motherPage=" + motherPage+ "&upperMembId=" + upperMembId);

				memberService.commit();
				imgService.commit();
			}
			
			params.clear();
		}
		catch(Exception e){
			memberService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_class_register.ms?membId="+membId+"&motherPage=" + motherPage+ "&upperMembId=" + upperMembId);
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : MemberClassEditor
	 * @Description : ����� ������� ����ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@RequestMapping(value="/member/member_class_editor.ms")
	public String MemberClassEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		MemberClassService memberClassService = new MemberClassService();
		MemberService memberService = new MemberService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		//-- ������ ������� ������ ��ȸ �ؿ´�.(����� ��... ��)
		String membId = checkStr(request.getParameter("membId"), "");
		
		params.put("membId", membId); //-- ���õ� ����� ��������� ���� ��
		String upperMembId = checkStr(request.getParameter("upperMembId"), "");
		
		Member member = memberService.selectAMember(params);
		
		request.setAttribute("member", member);
		request.setAttribute("membId", membId);

		String motherPage = checkStr(request.getParameter("motherPage"), "mem");
		String classCode = checkStr(request.getParameter("classCode"), "");
		
		try{
			String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- ������� html url ���
			
			params.put("membId", membId); //--���õ� ����� ������� ID
			params.put("classCode", classCode); //--���õ� ����� ������� ID
	
			log.debug("@@@@@@@@@@ MemberClassEditor params : "+ params); //##
			MemberClass aMemberClass = memberClassService.selectAMemberClass(params); //-- �����ȸ
			
			request.setAttribute("membId", membId);
			request.setAttribute("upperMembId", upperMembId);
			request.setAttribute("classCode", classCode);
			request.setAttribute("aMemberClass", aMemberClass);
			request.setAttribute("memberNotiUrl", memberNotiUrl);
			request.setAttribute("motherPage", motherPage);
			params.clear();
			return "member/member_class_editor";
		}
		catch(Exception e){
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/member/member_class_list.ms?membId=" + membId+"&motherPage=" + motherPage+ "&upperMembId=" + upperMembId);
			
			e.printStackTrace();
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : MemberClassEditorAct
	 * @Description : ����� ��������� ���� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@RequestMapping(value="/member/member_class_editorAct.ms")
	public String MemberClassEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0;

		MemberClassService memberClassService = new MemberClassService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String membId = checkStr(request.getParameter("membId"), "");
		String upperMembId = checkStr(request.getParameter("upperMembId"), "");
		String classCode = checkStr(request.getParameter("classCode"), "");
		String motherPage = checkStr(request.getParameter("motherPage"), "mem");
		String chgUser = getSessionName(request);
		
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		
		/*�̹��� ��� �ؾ���.*/
		String listImg_i3GS = checkStr(request.getParameter("listImg_i3GS"), "");
		String listImg_i4S = checkStr(request.getParameter("listImg_i4S"), "");
		String listImg_android = checkStr(request.getParameter("listImg_android"), "");
		String detailImg_i3GS = checkStr(request.getParameter("detailImg_i3GS"), "");
		String detailImg_i4S = checkStr(request.getParameter("detailImg_i4S"), "");
		String detailImg_android = checkStr(request.getParameter("detailImg_android"), "");
		
		try{
			
			//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)
			params.put("membId", membId);
			params.put("classCode", classCode);
			
			params.put("className",checkStr(request.getParameter("className"), ""));
			params.put("classCode",checkStr(request.getParameter("classCode"), ""));
			params.put("addDcRate",checkStr(request.getParameter("addDcRate"), ""));
			params.put("addSaveRate",checkStr(request.getParameter("addSaveRate"), ""));
			params.put("addUseRate", checkStr(request.getParameter("addUseRate"),""));
			/*�̹��� ��� �ؾ���.*/
			/*
			String cardClassImg_i3GS = checkStr(request.getParameter("cardClassImg_i3GS"), "");
			String cardClassImg_i4S = checkStr(request.getParameter("cardClassImg_i4S"), "");
			String cardClassImg_android = checkStr(request.getParameter("cardClassImg_android"), "");
			*/
			//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)
			params.put("chgUser", chgUser); //-- ������ID

			log.debug("@@@@@@@@@@ MemberClassEditorAct params : "+ params); //##
			
			result = memberClassService.updateMemberClass(params);

			
			/*
			imgParams.put("imageHost", PropertiesUtil.get("MEMBERSHIP_DEFAULT_PATH")  +"02"+  "/" +"01"+ "/");
			imgParams.put("imageUrl", PropertiesUtil.get("MEMBERSHIP_DEFAULT_URL_PATH"));
			*/
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "01");
			imgResult = imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", listImg_i3GS.substring(listImg_i3GS.lastIndexOf("/")));
			//imgParams.put("imageUrl", listImg_i3GS.substring(0, listImg_i3GS.lastIndexOf("/")));
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i3GS);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", listImg_i4S.substring(listImg_i4S.lastIndexOf("/")));
			//imgParams.put("imageUrl", listImg_i4S.substring(0, listImg_i4S.lastIndexOf("/")));
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", listImg_android.substring(listImg_android.lastIndexOf("/")));
			//imgParams.put("imageUrl", listImg_android.substring(0, listImg_android.lastIndexOf("/")));
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", listImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "01");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", detailImg_i3GS.substring(detailImg_i3GS.lastIndexOf("/")));
			//imgParams.put("imageUrl", detailImg_i3GS.substring(0, detailImg_i3GS.lastIndexOf("/")));
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i3GS);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", detailImg_i4S.substring(detailImg_i4S.lastIndexOf("/")));
			//imgParams.put("imageUrl", detailImg_i4S.substring(0, detailImg_i4S.lastIndexOf("/")));
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);

			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);
			//imgParams.put("imageHost", detailImg_android.substring(detailImg_android.lastIndexOf("/")));
			//imgParams.put("imageUrl", detailImg_android.substring(0, detailImg_android.lastIndexOf("/")));
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			request.setAttribute("membId", membId);
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/member/member_class_editor.ms?membId=" + membId +"&classCode=" + classCode+"&motherPage=" + motherPage+ "&upperMembId=" + upperMembId);
				memberClassService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/member/member_class_list.ms?membId=" + membId+"&motherPage=" + motherPage+ "&upperMembId=" + upperMembId);
				memberClassService.commit();
				imgService.commit();
			}
			params.clear();
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/member/member_class_editor.ms?membId=" + membId+"&motherPage=" + motherPage+ "&upperMembId=" + upperMembId);
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : MemberClassDeleteAct
	 * @Description : ����� ��������� �����Ѵ�.(��������)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@RequestMapping(value="/member/member_class_deleteAct.ms")
	public String MemberClassDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0;
		
		MemberClassService memberClassService = new MemberClassService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String membId = checkStr(request.getParameter("membId"), "");
		String upperMembId = checkStr(request.getParameter("upperMembId"), "");
		String classCode = checkStr(request.getParameter("classCode"), "");
		String motherPage = checkStr(request.getParameter("motherPage"), "mem");
		
		try{
			params.put("membId", membId);
			params.put("classCode", classCode);

			log.debug("@@@@@@@@@@ MemberClassDeleteAct params : "+ params); //##
			
			result = memberClassService.deleteMemberClass(params);
			
			
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "01");
			imgResult = imgService.deleteImage(imgParams);

			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "02");
			imgResult = imgService.deleteImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "11");
			imgParams.put("osType", "11");
			imgResult =  imgService.deleteImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "01");
			imgResult =  imgService.deleteImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "02");
			imgResult = imgService.deleteImage(imgParams);

			imgParams.clear();
			imgParams.put("level", classCode);
			imgParams.put("id", membId);
			imgParams.put("useType", "12");
			imgParams.put("osType", "11");
			imgResult = imgService.deleteImage(imgParams);
			
			request.setAttribute("membId", membId);
			request.setAttribute("classCode", classCode);
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/member/member_class_editor.ms?membId=" + membId + "&classCode=" + classCode+"&motherPage=" + motherPage+ "&upperMembId=" + upperMembId);
			}
			else{
				request.setAttribute("targetUrl", "/member/member_class_list.ms?membId=" + membId+"&motherPage=" + motherPage+ "&upperMembId=" + upperMembId);
				
				memberClassService.commit();
				imgService.commit();
			}
		}
			
		catch(Exception e){
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/member_class_editor.ms?membId=" + membId + "&classCode=" + classCode+ "&upperMembId=" + upperMembId);
			memberClassService.rollback();
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
}
