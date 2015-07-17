package com.wallet.admin.web.base;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.model.MwAdPayment;
import com.wallet.admin.service.MwAdPaymentService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

/*
 * Filename	: PaymentController.java
 * Class	: com.wallet.admin.web.base.PaymentController
 * History	: 2012/08/23, psj, �۾����� : �������� ���� > ����
 * Comment	:
 */
@Controller
public class PaymentController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * �ű� ���� ���� ��� ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/payment_reg.ad")
	public String paymentReg(HttpServletRequest request, HttpServletResponse response) {
		return "base/payment_reg";
	}
	
	/**
	 * �ű� ���� ���� �������

	 * @return	
	 */
	@RequestMapping(value="/base/payment_reg.ad", method=RequestMethod.POST)
	public String insertPaymentReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### PaymentController insertPaymentReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdPayment> list = null;
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		List<Map<String,String>> clause_list = new ArrayList<Map<String,String>>();
		
		//���ϸ� ���� : ����Ͻú���_i
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	  String date = formatter.format(today);
		
	  String img_host = PropertiesUtil.get("img_host");
	  String use_clause_path = PropertiesUtil.get("pay_memb_agree_url");
	  String use_clause_info = "";			//�̿��� TB info �׸� ����� ��ü ���
	  String use_clause_file_path = "";	//������ ����� ��� (��� + ���ϸ�)

		String pay_type = checkStr(request.getParameter("pay_type"), "");		//���񽺱���
		String name = checkStr(request.getParameter("name"), "");						//�������񽺸�
		String memb_id = checkStr(request.getParameter("memb_id"), "");		//��������id
		String stat = checkStr(request.getParameter("stat"), "");						//���޻���
		String company_nm = checkStr(request.getParameter("company_nm"), "");	//���޻��
		String sday = checkStr(request.getParameter("sday"), "");						//����Ⱓ - ������
		String eday = checkStr(request.getParameter("eday"), "");						//����Ⱓ - ������
		String os = checkStr(request.getParameter("os"), "");								//����os
		String link_mode = checkStr(request.getParameter("link_mode"), "");	//�������
		String info = checkStr(request.getParameter("info"), "");						//����url
		String market = checkStr(request.getParameter("market"), "");						//����url
		String olleh_id = checkStr(request.getParameter("olleh_id"), "");					//app id
		String olleh_pkg = checkStr(request.getParameter("olleh_pkg"), "");				//package��
		String olleh_class = checkStr(request.getParameter("olleh_class"), "");		//���� class��
		String olleh_down = checkStr(request.getParameter("olleh_down"), "");			//�ٿ�ε� url
		String google_pkg = checkStr(request.getParameter("google_pkg"), "");			//package��
		String google_class = checkStr(request.getParameter("google_class"), "");	//���� class��
		String google_down = checkStr(request.getParameter("google_down"), "");		//�ٿ�ε� url
		String apple_id = checkStr(request.getParameter("apple_id"), "");					//app id
		String apple_url = checkStr(request.getParameter("apple_url"), "");				//Custom url
		String apple_down = checkStr(request.getParameter("apple_down"), "");			//�ٿ�ε� url
		String[] title = request.getParameterValues("title");											//�̿��� tile
		String[] use_clause = request.getParameterValues("use_clause");						//�̿��� ������
		String[] chk = request.getParameterValues("chk");													//�̿��� �ʼ�/����
		String[] menu_nm = request.getParameterValues("menu_nm");									//sub�޴� - �޴���
		String[] url = request.getParameterValues("url");													//sub�޴� - url
		String mgr_id = checkStr(getSessionMgrId(request), "");						//�α���id
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//����Ʈ,ī�� �̹���(������4,������3GS,������̵�)
		
		try {
			
			params.put("memb_id",memb_id);
			params.put("pay_type",pay_type);
			params.put("name",name);
			params.put("stat",stat);
			params.put("company_nm",company_nm);
			params.put("dis_sday",sday);
			params.put("dis_eday",eday);
			params.put("os",os);
			params.put("link_mode",link_mode);
			params.put("info",info);
			params.put("market",market);
			params.put("olleh_id",olleh_id);
			params.put("olleh_pkg",olleh_pkg);
			params.put("olleh_class",olleh_class);
			params.put("olleh_down",olleh_down);
			params.put("google_pkg",google_pkg);
			params.put("google_class",google_class);
			params.put("google_down",google_down);
			params.put("apple_id",apple_id);
			params.put("apple_url",apple_url);
			params.put("apple_down",apple_down);
			params.put("display_yn","Y");
			params.put("img_host",img_host);
			params.put("reg_user",mgr_id);//�����id(�α���id)
			
			if ("W".equals(params.get("link_mode"))) {
				if(title != null) {
					//�̿��� ���̺� ������ ����
					for(int i=0; i <title.length; i++){
						Map<String, String> resultReturn = new HashMap<String, String>();
						use_clause_file_path = "";
						use_clause_file_path = use_clause_path + date+ "_" + (i+1) + ".html" ;	//���ϸ� ���� YYYYMMDDHHSS_i.html
						use_clause_info = img_host + use_clause_file_path ;	//���ϸ� ���� YYYYMMDDHHSS_i.html
						
						resultReturn.put("memb_id", memb_id);
						resultReturn.put("idx", String.valueOf(i+1));
						resultReturn.put("title", title[i]);
						resultReturn.put("use_clause", use_clause[i]);
						resultReturn.put("info", use_clause_info);
						resultReturn.put("use_clause_file_path", use_clause_file_path);
						resultReturn.put("chk", chk[i]);
						clause_list.add(resultReturn);
					}
				}
			}

			service.insertPaymentReg(params, clause_list, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		params.clear();
		
		//listȭ�� �̵�
		response.sendRedirect("/base/payment_list.ad");

    log.debug("### PaymentController insertPaymentReg END ###");
    
		return "base/payment_list";
	}
	
	/**
	 * �ű� ���� ���� ��ȸ ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/payment_list.ad")
	public String selectPaymentList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PaymentController selectPaymentList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdPayment> list = null;
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("view", "list");
		list = service.selectPaymentList(params);
		
		request.setAttribute("mwAdPaymentList", list);
		
		log.debug("### PaymentController selectPaymentList END ###");
		
		return "base/payment_list";
	}
	
	/**
	 *  �ű� ���� ����  �� ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/base/payment_list_dtl.ad")
	public String selectPaymentListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PaymentController selectPaymentListDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdPayment mwAdPayment = null;
		List<Map<String,String>> useClauseList = null;
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String memb_id = checkStr(request.getParameter("memb_id"), "");			//idx
		
		params.put("view", "dtl");
		params.put("memb_id", memb_id);
		params.put("top", 1);
		
		mwAdPayment = service.selectPaymentListDtl(params);
		useClauseList = service.selectUseClauseList(params);
		
		request.setAttribute("mwAdPayment", mwAdPayment);
		request.setAttribute("useClauseList", useClauseList);
		request.setAttribute("useClauseCount", useClauseList.size());
		
		log.debug("### PaymentController selectPaymentListDtl END ###");
		
		return "base/payment_list_dtl";
	}
	
	/**
	 * ���� ���� ���� ����
	 * @param 
	
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/payment_dtl_update.ad", method=RequestMethod.POST)
	public String updatePaymentDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### PaymentController updatePaymentDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdPayment> list = null;
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		List<Map<String,String>> clause_list = new ArrayList<Map<String,String>>();
		
		//���ϸ� ���� : ����Ͻú���_i
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	  String date = formatter.format(today);
		
	  String img_host = PropertiesUtil.get("img_host");
	  String use_clause_path = PropertiesUtil.get("pay_memb_agree_url");
	  String use_clause_info = "";			//�̿��� TB info �׸� ����� ��ü ���
	  String use_clause_file_path = "";	//������ ����� ��� (��� + ���ϸ�)

		String pay_type = checkStr(request.getParameter("pay_type"), "");		//���񽺱���
		String name = checkStr(request.getParameter("name"), "");						//�������񽺸�
		String memb_id = checkStr(request.getParameter("memb_id"), "");		//��������id
		String stat = checkStr(request.getParameter("stat"), "");						//���޻���
		String company_nm = checkStr(request.getParameter("company_nm"), "");	//���޻��
		String sday = checkStr(request.getParameter("sday"), "");						//����Ⱓ - ������
		String eday = checkStr(request.getParameter("eday"), "");						//����Ⱓ - ������
		String os = checkStr(request.getParameter("os"), "");								//����os
		String link_mode = checkStr(request.getParameter("link_mode"), "");	//�������
		String info = checkStr(request.getParameter("info"), "");						//����url
		String market = checkStr(request.getParameter("market"), "");						//����url
		String olleh_id = checkStr(request.getParameter("olleh_id"), "");					//app id
		String olleh_pkg = checkStr(request.getParameter("olleh_pkg"), "");				//package��
		String olleh_class = checkStr(request.getParameter("olleh_class"), "");		//���� class��
		String olleh_down = checkStr(request.getParameter("olleh_down"), "");			//�ٿ�ε� url
		String google_pkg = checkStr(request.getParameter("google_pkg"), "");			//package��
		String google_class = checkStr(request.getParameter("google_class"), "");	//���� class��
		String google_down = checkStr(request.getParameter("google_down"), "");		//�ٿ�ε� url
		String apple_id = checkStr(request.getParameter("apple_id"), "");					//app id
		String apple_url = checkStr(request.getParameter("apple_url"), "");				//Custom url
		String apple_down = checkStr(request.getParameter("apple_down"), "");			//�ٿ�ε� url
		String[] title = request.getParameterValues("title");											//�̿��� tile
		String[] use_clause = request.getParameterValues("use_clause");		//�̿��� ������
		String[] chk = request.getParameterValues("chk");		//�̿��� ������
		String[] use_clause_text_hidden = request.getParameterValues("use_clause_text_hidden");		//�̿��� ������
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//����Ʈ,ī�� �̹���(������4,������3GS,������̵�)

		try {
			
			params.put("memb_id",memb_id);
			params.put("pay_type",pay_type);
			params.put("name",name);
			params.put("stat",stat);
			params.put("company_nm",company_nm);
			params.put("dis_sday",sday);
			params.put("dis_eday",eday);
			params.put("os",os);
			params.put("link_mode",link_mode);
			params.put("info",info);
			params.put("market",market);
			params.put("olleh_id",olleh_id);
			params.put("olleh_pkg",olleh_pkg);
			params.put("olleh_class",olleh_class);
			params.put("olleh_down",olleh_down);
			params.put("google_pkg",google_pkg);
			params.put("google_class",google_class);
			params.put("google_down",google_down);
			params.put("apple_id",apple_id);
			params.put("apple_url",apple_url);
			params.put("apple_down",apple_down);
			params.put("display_yn","Y");
			params.put("img_host",img_host);
			
			if ("W".equals(params.get("link_mode"))) {
				if(title != null) {
				//�̿��� ���̺� ������ ����
					for(int i=0; i <title.length; i++){
						Map<String, String> resultReturn = new HashMap<String, String>();
						
						use_clause_file_path = "";
						use_clause_file_path = use_clause_path + date+ "_" + (i+1) + ".html" ;	//���+���ϸ� ���� YYYYMMDDHHSS_i.html
						use_clause_info = img_host + use_clause_file_path ;	//http://211.216.47.161 + ���+���ϸ� ���� YYYYMMDDHHSS_i.html
						
						resultReturn.put("memb_id", memb_id);
						resultReturn.put("idx", String.valueOf(i+1));
						resultReturn.put("title", title[i]);
						resultReturn.put("use_clause", use_clause[i]);
						resultReturn.put("info", use_clause_info);
						resultReturn.put("use_clause_file_path", use_clause_file_path);
						resultReturn.put("chk", chk[i]);
						clause_list.add(resultReturn);
						
					}
				}
				 
			}

			service.updatePaymentDtl(params, clause_list, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		response.sendRedirect("/base/payment_list.ad");
		
		log.debug("### CardController updateCardDtl END ###");
		
		return "base/card_list";
		
	}
	
	/**
	 * ���� ���� ����
	 * @param memb_id			memb_id
	 * @param pay_code		pay_code
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/payment_dtl_delete.ad", method=RequestMethod.POST)
	public String deletePaymentDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### PaymentController deletePaymentDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String memb_id = checkStr(request.getParameter("memb_id"), "");		//memb_id
		String main_idx = checkStr(request.getParameter("main_idx"), "");		//main_idx
		
		try {
			
			params.put("memb_id", memb_id);
			params.put("main_idx", main_idx);
			params.put("idxGb", "delete");	//������ �������� ���� ���а�
			
			//memb_id ������� ���� Exception �߻�
			if("".equals(memb_id)) {
				throw new Exception("memb_id�� �����ϴ�. ���� Exception �߻�");
			}

			service.deletePaymentDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### PaymentController deletePaymentDtl END ###");
		return null;
		
	}
	
	/**
	 * �������� ���� ����
	 * @param memb_id		memb_id
	 * @param thisIdx		������ ������  ���� idx
	 * @param targetidx ������ ������  ���� idx
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/payment_idx_update.ad", method=RequestMethod.POST)
	public String updatePaymentIdx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### PaymentController updatePaymentIdx START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String memb_id = checkStr(request.getParameter("memb_id"), "");
		int thisIdx = Integer.parseInt(checkStr(request.getParameter("thisIdx"), "1"));
		int targetIdx = Integer.parseInt(checkStr(request.getParameter("targetIdx"), "1"));
		
		try {
			
			params.put("thisIdx", thisIdx);
			params.put("targetIdx", targetIdx);
			params.put("idxGb", "update");			//���������� ���� ���а�

			//���� ���� update
			service.updatePaymentIdx(params, memb_id);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		//��ȸ
		params.clear();
		response.sendRedirect("/base/payment_list.ad");
		
		log.debug("### PaymentController updatePaymentIdx END ###");
		
		return "base/payment_list";
	}
}
