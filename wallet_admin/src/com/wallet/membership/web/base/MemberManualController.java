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
	 * @Description : �����(ī������) �˻�/���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_list.ms")
	public String MemberManualList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MemberManual> memberManualList = null;
		MemberManualService memberManualService = new MemberManualService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_showYN", checkStr(request.getParameter("ra_showYN"), "")); //-- ����¿� ���� �⺻�� ����
		params.put("membName", checkStr(request.getParameter("membName"), "")); //-- ���õ� �����(ī������)�� �����(ī������)��
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 

		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int memberManualListCnt = memberManualService.selectMemberManualListCnt(params); //-- �� ��� ��
		Paging page = new Paging();
		page.makeWebPaging(nowPage, memberManualListCnt, rowsPerPage);
		
		if(page.getNowPage()>1){
			nowPage =  page.getNowPage();
			request.setAttribute("nowPage", nowPage);
			
		}else{
			nowPage = 1;
			request.setAttribute("nowPage", "1");
		}
		
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		
		log.debug("@@@@@@@@@@ MemberManualList params : "+ params); //##
		
 		memberManualList = memberManualService.selectMemberManualList(params); //-- �����ȸ
		
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
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
	 * @Description : ������� ����� ���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_register.ms")
	public String MemberManualRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "member/member_manual_register";
	}
	
	
	/**
	 * @Method Name : MemberManualRegisterAct
	 * @Description : ������� ����ʸ� ��� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_registerAct.ms")
	public String MemberManualRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0; //-- �̹��� ����� ������ 0:����, 1:����
		
		MemberManualService memberManualService = new MemberManualService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		String selfMembId = "SEL"+ checkStr(request.getParameter("selfMembId"), "");
		String regUser = getSessionName(request);
		
		try{
			
			//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)
			params.put("regUser", regUser); //-- �����ID
			params.put("selfMembId", selfMembId) ;
			params.put("membName", checkStr(request.getParameter("membName"), ""));
			params.put("detMemo", checkStr(request.getParameter("detMemo"), ""));
			params.put("showYN", checkStr(request.getParameter("showYN"), "N"));
			
			/*�̹��� ��� �ؾ���.*/
			String listImg_i3GS = checkStr(request.getParameter("listImg_i3GS"), "");
			String listImg_i4S = checkStr(request.getParameter("listImg_i4S"), "");
			String listImg_android = checkStr(request.getParameter("listImg_android"), "");
			String detailImg_i3GS = checkStr(request.getParameter("detailImg_i3GS"), "");
			String detailImg_i4S = checkStr(request.getParameter("detailImg_i4S"), "");
			String detailImg_android = checkStr(request.getParameter("detailImg_android"), "");
			
			log.debug("@@@@@@@@@@ MemberManualRegisterAct params : "+ params); //##
			
			result = memberManualService.insertMemberManual(params); //-- ����� ���
			
			/*
			imgParams.put("imageHost", PropertiesUtil.get("MEMBERSHIP_DEFAULT_PATH")  +"02"+  "/" +"01"+ "/");
			imgParams.put("imageUrl", PropertiesUtil.get("MEMBERSHIP_DEFAULT_URL_PATH"));
			*/
			imgParams.put("regUser", regUser); //-- �����ID
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
			imgParams.put("regUser", regUser); //-- �����ID
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
			imgParams.put("regUser", regUser); //-- �����ID
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
			imgParams.put("regUser", regUser); //-- �����ID
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
			imgParams.put("regUser", regUser); //-- �����ID
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
			imgParams.put("regUser", regUser); //-- �����ID
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
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
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
	 * @Description : ������� ����� ����ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_editor.ms")
	public String MemberManualEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		MemberManualService memberManualService = new MemberManualService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		//-- ������ ������� ������ ��ȸ �ؿ´�.(����� ��... ��)
		String selfMembId = checkStr(request.getParameter("selfMembId"), "");

		try{
			String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- ������� html url ���
			
			params.put("selfMembId", selfMembId); //-- ���õ� ������� ����ʰ� ���� ��
	
			log.debug("@@@@@@@@@@ MemberManualEditor params : "+ params); //##
			
			MemberManual aMemberMenual = memberManualService.selectAMemberManual(params); //-- �������ȸ
			
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
	 * @Description : ������� ����ʸ� ���� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_editorAct.ms")
	public String MemberManualEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0;

		MemberManualService memberManualService = new MemberManualService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String selfMembId = checkStr(request.getParameter("selfMembId"), "");
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		String chgUser = getSessionName(request);
		try{
			
			//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)
			params.put("selfMembId", selfMembId);
			
			params.put("membName", checkStr(request.getParameter("membName"), ""));
			params.put("detMemo", checkStr(request.getParameter("detMemo"), ""));
			params.put("showYN", checkStr(request.getParameter("showYN"), "N"));
			
			//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)
			params.put("chgUser", chgUser); //-- ������ID
			
			/*�̹��� ��� �ؾ���.*/
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
			imgParams.put("regUser", chgUser); //-- �����ID
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
			imgParams.put("regUser", chgUser); //-- �����ID
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
			imgParams.put("regUser", chgUser); //-- �����ID
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
			imgParams.put("regUser", chgUser); //-- �����ID
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
			imgParams.put("regUser", chgUser); //-- �����ID
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
			imgParams.put("regUser", chgUser); //-- �����ID
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", detailImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			request.setAttribute("selfMembId", selfMembId);
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
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
	 * @Description : ������� ����ʸ� �����Ѵ�.(��������)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@RequestMapping(value="/member/member_manual_deleteAct.ms")
	public String MemberManualDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
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
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
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
	 * @Description : ����� ID�� ��� ��, �ߺ����θ� Ȯ���Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 0/1 (0:�ߺ��ƴ�, 1:�ߺ�)
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
	 * @Description : ���� ��¥�� ��ȸ�Ѵ�.
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
