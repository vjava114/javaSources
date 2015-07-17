package com.wallet.admin.web.base;

import java.io.PrintWriter;
import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.model.MwAdGift;
import com.wallet.admin.service.MwAdGiftService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

/*
 * Filename	: GiftController.java
 * Class	: com.wallet.admin.web.base.GiftController
 * History	: 2012/08/23, psj, �۾����� : ��ǰ�� ���� > ��ǰ��
 * Comment	:
 */
@Controller
public class GiftController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * ��ǰ�� ���� ��� ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/gift_reg.ad")
	public String giftReg(HttpServletRequest request, HttpServletResponse response) {
		return "/base/gift_reg";
	}
	
	/**
	 * ��ǰ�� �������
	 * @param name			��ǰ�Ǹ�
	 * @param gift_id		��ǰ�� id
	 * @param memo			�Ұ�����
	 * @param operater	���			
	 * @param stat			����				
	 * @param os				����os			
	 * @param l_img_i4	����Ʈ�̹���(������4)
	 * @param l_img_i3	����Ʈ�̹���(������3)
	 * @param l_img_r4	����Ʈ�̹���(�ȵ���̵�)
	 * @param d_img_i4	ī���̹���(������4)
	 * @param d_img_i3	ī���̹���(������3)
	 * @param d_img_r4	ī���̹���(�ȵ���̵�)
	 * @return	
	 */
	@RequestMapping(value="/base/gift_reg.ad", method=RequestMethod.POST)
	public String insertGiftReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### GiftController insertGiftReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdGift> list = null;
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String name = checkStr(request.getParameter("name"), "");					//��ǰ�Ǹ�
		String gift_id = checkStr(request.getParameter("gift_id"), "");		//��ǰ��ID
		String gift_sid = checkStr(request.getParameter("gift_sid"), "");		//��ǰ��SID
		String memo = checkStr(request.getParameter("memo"), "");					//�Ұ�����
		String operater = checkStr(request.getParameter("operater"), "");	//���
		String stat = checkStr(request.getParameter("stat"), "");					//����
		String os = checkStr(request.getParameter("os"), "");							//����OS
		String mgr_id = checkStr(getSessionMgrId(request), "");						//�α���id
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//����Ʈ,ī�� �̹���(������4,������3GS,������̵�)
	
		try {
			
			params.put("name", name);
			params.put("gift_id", gift_id);
			params.put("gift_sid", gift_sid);
			params.put("memo", memo);
			params.put("operater", operater);
			params.put("stat", stat);
			params.put("os", os);
			params.put("display_yn", "Y");
			params.put("img_host", PropertiesUtil.get("img_host"));
			params.put("admin_id",mgr_id);
			
			service.insertGiftReg(params, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		} 

    //listȭ�� �̵�
		response.sendRedirect("/base/gift_list.ad");
		
		log.debug("### GiftController insertGiftReg END ###");
    
		return "base/gift_list";
	}
	
	/**
	 * ��ǰ�� ���� ��ȸ ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/gift_list.ad")
	public String selectGiftList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### GiftController selectGiftList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdGift> list = null;
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("view", "list");
		
		//��ȸ
		list = service.selectGiftList(params);
		
		request.setAttribute("mwAdGiftList", list);
		
		log.debug("### GiftController selectGiftList END ###");
		
		return "base/gift_list";
	}
	
	
	/**
	 * ��ǰ�� ���� �� ��ȸ ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/gift_list_dtl.ad")
	public String selectGiftListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### GiftController selectGiftListDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdGift mwAdGift = null;
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String gift_id = checkStr(request.getParameter("gift_id"), "");		//��ǰ��ID
		
		params.put("view", "dtl");
		params.put("gift_id", gift_id);
		params.put("top", 1);
		
		//��ȸ
		mwAdGift = service.selectGiftListDtl(params);
		
		request.setAttribute("mwAdGift", mwAdGift);
		
		log.debug("### GiftController selectGiftListDtl END ###");
		
		return "base/gift_list_dtl";
	}
	
	/**
	 * ī��� ���� ����
	 * @param cidx			ī��纰 id
	 * @param os				os
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/gift_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteGiftDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### GiftController deleteGiftDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String gift_id = checkStr(request.getParameter("gift_id"), "");	//��ǰ�� id
		String main_idx = checkStr(request.getParameter("main_idx"), "");		//main_idx
		
		try {
			
			params.put("gift_id", gift_id);
			params.put("main_idx", main_idx);
			params.put("idxGb", "delete");		//������ �������� ���� ���а�
			
			//��ǰ�� id�� ������� ���� Exception �߻�
			if("".equals(gift_id)) {
				throw new Exception("��ǰ�� id�� �����ϴ�. ���� Exception �߻�");
			}

			service.deleteGiftDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### GiftController deleteGiftDtl END ###");
		
		return null;
		
	}
	
	/**
	 * ī��� ���� ����
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/gift_dtl_update.ad", method=RequestMethod.POST)
	public String updateGiftDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### GiftController updateGiftDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdGift> list = null;
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String name = checkStr(request.getParameter("name"), "");					//��ǰ�Ǹ�
		String gift_id = checkStr(request.getParameter("gift_id"), "");		//��ǰ��ID
		String gift_sid = checkStr(request.getParameter("gift_sid"), "");		//��ǰ��ID
		String gift_id_org = checkStr(request.getParameter("gift_id_org"), "");		//��ǰ��ID ��������
		String memo = checkStr(request.getParameter("memo"), "");					//�Ұ�����
		String operater = checkStr(request.getParameter("operater"), "");	//���
		String stat = checkStr(request.getParameter("stat"), "");					//����
		String os = checkStr(request.getParameter("os"), "");							//����OS
		String l_img_i4 = checkStr(request.getParameter("l_img_i4"), "");							//����Ʈ ������4
		String l_img_i3 = checkStr(request.getParameter("l_img_i3"), "");							//����Ʈ ������3
		String l_img_r4 = checkStr(request.getParameter("l_img_r4"), "");							//����Ʈ �ȵ���̵�
		String d_img_i4 = checkStr(request.getParameter("d_img_i4"), "");							//ī�� ������4
		String d_img_i3 = checkStr(request.getParameter("d_img_i3"), "");							//ī�� ������3
		String d_img_r4 = checkStr(request.getParameter("d_img_r4"), "");							//ī�� �ȵ���̵�
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//����Ʈ,ī�� �̹���(������4,������3GS,������̵�)
	
		try {

			params.put("name", name);
			params.put("gift_id", gift_id);
			params.put("gift_id_org", gift_id_org);
			params.put("gift_sid", gift_sid);
			params.put("memo", memo);
			params.put("operater", operater);
			params.put("stat", stat);
			params.put("os", os);
			params.put("img_host", PropertiesUtil.get("img_host"));

			service.updateGiftDtl(params, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}
		
		//listȭ�� �̵�
		response.sendRedirect("/base/gift_list.ad");
		
		log.debug("### GiftController updateGiftDtl END ###");
		
		return "base/gift_list";
		
	}
	
	/**
	 * ��ǰ�� ���� ����
	 * @param gift_id		gift_id
	 * @param os				os
	 * @param thisIdx		������ ������  ���� idx
	 * @param targetidx ������ ������  ���� idx
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/gift_idx_update.ad", method=RequestMethod.POST)
	public String updateGiftIdx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### GiftController updateGiftDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdGift> list = null;
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String gift_id = checkStr(request.getParameter("gift_id"), "");
		int thisIdx = Integer.parseInt(checkStr(request.getParameter("thisIdx"), "1"));
		int targetIdx = Integer.parseInt(checkStr(request.getParameter("targetIdx"), "1"));
		
		try {
			
			params.put("thisIdx", thisIdx);
			params.put("targetIdx", targetIdx);
			params.put("idxGb", "update");	//���������� ���� ���а�
			
			//���� ���� update
			service.updateGiftIdx(params, gift_id);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		//��ȸ
		params.clear();
		response.sendRedirect("/base/gift_list.ad");
		
		log.debug("### GiftController updateGiftDtl END ###");
		
		return "base/gift_list";
	}
}
