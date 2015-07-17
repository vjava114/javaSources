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
 * History	: 2012/08/23, psj, �۾����� : ��Ÿ���� > FAQ ����
 * 						2013/04/18, lsb, �۾����� : FAQ ���� > OS�� �߰�, ���� �߰�
 * Comment	:
 */
@Controller
public class FaqController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * FAQ ���� ȭ�� ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/faq_list.ad")
	public String selectFaqList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### FaqController selectFaqList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdFaq> list = null;
		MwAdFaqService service = new MwAdFaqService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		int blockSize = 10;
		int nowPage = Integer.parseInt(checkStr(request.getParameter("now_page"), "1"));	//����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * blockSize + 1; 
		int endRow   = nowPage == 1 ? blockSize :startRow + blockSize -1 ;
    
		params.put("view", "list");
		params.put("start_row",  String.valueOf(startRow));
		params.put("end_row",    String.valueOf(endRow));
		
		//��ȸ
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
	 * FAQ ���� �� ��ȸ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/faq_list_dtl.ad")
	public String selectFaqListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### FaqController selectFaqListDtl START ###");
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdFaq mwAdFaq = null;
		MwAdFaqService service = new MwAdFaqService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");			//idx
		
		params.put("view", "dtl");
		params.put("idx", idx);
		params.put("top", 1);

		//��ȸ
		mwAdFaq = service.selectFaqListDtl(params);

		request.setAttribute("mwAdFaq", mwAdFaq);

		log.debug("### FaqController selectFaqListDtl END ###");
		
		return "base/faq_list_dtl";
	}
	
	/**
	 * FAQ ��� ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/faq_reg.ad")
	public String appFaqReg(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### FaqController appFaqReg START ###");
		
	//���ó�¥
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);
	  
	  request.setAttribute("reg_day", reg_day);
	
	  log.debug("### FaqController appFaqReg END ###");
	  
		return "base/faq_reg";
	}
	
	/**
	 * FAQ ��� insert
	 * @param part		����
	 * @param title		��������
	 * @param answer	�亯����
	 * @param 	
	 */
	@RequestMapping(value="/base/faq_reg.ad", method=RequestMethod.POST)
	public String insertFaqReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### FaqController insertFaqReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdFaqService service = new MwAdFaqService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String part = checkStr(request.getParameter("part"), "");			//����
		String title = checkStr(request.getParameter("title"), "");		//��������
		String answer = checkStr(request.getParameter("answer"), "");	//�亯����
		//os�� �߰�
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
    
    //listȭ�� �̵�
    response.sendRedirect("/base/faq_list.ad");
    
		return "base/faq_list";
	}
	
	/**
	 * FAQ ���� update
	 * @param idx			idx
	 * @param part		����
	 * @param title		��������
	 * @param answer	�亯����
	 * @param 	
	 */
	@RequestMapping(value="/base/faq_dtl_update.ad", method=RequestMethod.POST)
	public String updateFaqDtl(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### FaqController updateFaqReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdFaqService service = new MwAdFaqService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String idx = checkStr(request.getParameter("idx"), "");			//idx
		String part = checkStr(request.getParameter("part"), "");			//����
		String title = checkStr(request.getParameter("title"), "");		//��������
		String answer = checkStr(request.getParameter("answer"), "");	//�亯����
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
    
    //listȭ�� �̵�
    response.sendRedirect("/base/faq_list.ad");
    
		return "base/faq_list";
	}
	
	/**
	 * FAQ ���� ����
	 * @param idx			idx
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/faq_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteFaqDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### FaqController deleteFaqDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdFaqService service = new MwAdFaqService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		
		try {
			
			params.put("idx", idx);

			//idx�� ������� ���� Exception �߻�
			if("".equals(idx)) {
				throw new Exception("idx�� �����ϴ�. ���� Exception �߻�");
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
