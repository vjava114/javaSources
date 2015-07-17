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
 * History	: 2012/08/23, psj, �۾����� : �˾�/��� ���� > ��Ʈ�� �˾�
 * Comment	:
 */
@Controller
public class IntroPopupController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * ��Ʈ�� �˾� ��� ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/intro_popup_reg.ad")
	public String introPopupReg(HttpServletRequest request, HttpServletResponse response) {

		//���ó�¥
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);

	  request.setAttribute("reg_day", reg_day);
	  
		return "base/intro_popup_reg";
	}
	
	/**
	 * ��Ʈ�� �˾� ���
	 * @param os			����os
	 * @param sday		����Ⱓ - ������
	 * @param eday		����Ⱓ - ������
	 * @param stat		�������
	 * @param url			�˾�url
	 * @return	
	 */
	@RequestMapping(value="/base/intro_popup_reg.ad", method=RequestMethod.POST)
	public String insertIntroPopupReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### IntroPopupController insertIntroPopupReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdIntroPopup> list = null;
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String os = checkStr(request.getParameter("os"), "");			//����os
		String sday = checkStr(request.getParameter("sday"), "");	//����Ⱓ - ������
		String eday = checkStr(request.getParameter("eday"), "");	//����Ⱓ - ������
		String stat = checkStr(request.getParameter("stat"), "");	//�������
		String url = checkStr(request.getParameter("url"), "");		//�˾�url
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

    //listȭ�� �̵�
    response.sendRedirect("/base/intro_popup_list.ad?os="+os);
    
    log.debug("### IntroPopupController insertIntroPopupReg END ###");
    
		return "base/intro_popup_list";
	}
	
	/**
	 * ��Ʈ�� �˾� ��ȸ ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/intro_popup_list.ad")
	public String selectIntroPopupList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### IntroPopupController selectIntroPopupList START ###");
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdIntroPopup> list = null;
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		int blockSize = 10;
		int nowPage = Integer.parseInt(checkStr(request.getParameter("now_page"), "1"));	//����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * blockSize + 1; 
    int endRow   = nowPage == 1 ? blockSize :startRow + blockSize -1 ;

		String os = checkStr(request.getParameter("os"), "apple");			//����os

		params.put("view", "list");
		params.put("start_row",  String.valueOf(startRow));
		params.put("end_row",    String.valueOf(endRow));
		params.put("os", os);
		
		//��ȸ
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
	 * ��Ʈ�� �˾� ��ȸ ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/intro_popup_list_dtl.ad")
	public String selectIntroPopupListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### IntroPopupController selectIntroPopupListDtl START ###");
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdIntroPopup mwAdIntroPopup = null;
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");			//idx
		String os = checkStr(request.getParameter("os"), "apple");	//����os
		
		params.put("view", "dtl");
		params.put("idx", idx);
		params.put("os", os);
		params.put("top", 1);

		//��ȸ
		mwAdIntroPopup = service.selectIntroPopupListDtl(params);

		request.setAttribute("mwAdIntroPopup", mwAdIntroPopup);

		log.debug("### IntroPopupController selectIntroPopupListDtl END ###");
		
		return "base/intro_popup_dtl";
	}
	
	
	/**
	 * ��Ʈ�� �˾� ������� ����
	 * @param idx			idx
	 * @param os			����os
	 * @param stat		�������
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/intro_popup_stat_update.ad")
	public String updateIntroPopupStat(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### IntroPopupController updateIntroPopupStat START ###");
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		String os = checkStr(request.getParameter("os"), "");			//����os
		String stat = checkStr(request.getParameter("stat"), "");	//��������
		
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

		//listȭ�� �̵�
		response.sendRedirect("/base/intro_popup_list.ad?os="+os);
		
		log.debug("### IntroPopupController updateIntroPopupStat END ###");
		
		return "base/intro_popup_list";
	}
	
	/**
	 * ��Ʈ�� �˾� �� ����
	 * @param idx			idx
	 * @param os			����os - ������ ����os
	 * @param os			����os - ������ �������� ����os
	 * @param sday		����Ⱓ - ������
	 * @param eday		����Ⱓ - ������
	 * @param stat		�������
	 * @param url			�˾�url
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/intro_popup_dtl_update.ad")
	public String updateIntroPopupDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### IntroPopupController updateIntroPopupDtl START ###");
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");				//idx
		String os = checkStr(request.getParameter("os"), "");					//������ ����os
		String os_org = checkStr(request.getParameter("os_org"), "");	//������ �������� ����os
		String stat = checkStr(request.getParameter("stat"), "");	//�������
		String sday = checkStr(request.getParameter("sday"), "");	//����Ⱓ - ������
		String eday = checkStr(request.getParameter("eday"), "");	//����Ⱓ - ������
		String url = checkStr(request.getParameter("url"), "");		//�˾�url
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
	
		//listȭ�� �̵�
		response.sendRedirect("/base/intro_popup_list.ad?os="+os);
		
		log.debug("### IntroPopupController updateIntroPopupDtl END ###");
		
		return "base/intro_popup_list";
	}
	
	/**
	 * ��Ʈ�� �˾� ���� ����
	 * @param cidx			ī��纰 id
	 * @param os				os
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/intro_popup_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteIntroPopupDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### IntroPopupController deleteIntroPopupDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdIntroPopupService service = new MwAdIntroPopupService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		String os = checkStr(request.getParameter("os"), "");		//����os
		
		try {
			
			params.put("idx", idx);
			params.put("os", os);

			//idx�� ������� ���� Exception �߻�
			if("".equals(idx)) {
				throw new Exception("idx�� �����ϴ�. ���� Exception �߻�");
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
