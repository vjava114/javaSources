package com.wallet.admin.web.base;

import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.model.MwAdCard;
import com.wallet.admin.service.MwAdCardService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

/*
 * Filename	: CardController.java
 * Class	: com.wallet.admin.web.base.CardController
 * History	: 2012/08/23, psj, �۾����� : ī��� ���� > ī��
 * Comment	:
 */
@Controller
public class CardController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * �ű� ī��� ��� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/card_reg.ad")
	public String cardReg(HttpServletRequest request, HttpServletResponse response) {
		return "base/card_reg";
	}
	
	/**
	 * �ű� ī��� ���
	 *@param cname			ī����
	 * @param cidx			ī��纰 id
	 * @param os				os
	 * @param market		���� ����
	 * @param app_id		app_id
	 * @param app_pkg		app_pkg
	 * @param app_class	app_class
	 * @param app_url		�ٿ�ε� url
	 * @param img_i4		����̹���(������4)
	 * @param img_i3		����̹���(������3)
	 * @param img_r4		����̹���(�ȵ���̵�)
	 * @return	
	 */
	@RequestMapping(value="/base/card_reg.ad", method=RequestMethod.POST)
	public String insertCardReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### CardController insert_card_reg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/

		List<MwAdCard> list = null;
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> android_params = new HashMap<String,Object>();
		HashMap<String,Object> iphone_params = new HashMap<String,Object>();
		String os = "A";
		int result = 1;
		
		//����
		String cname = checkStr(request.getParameter("cname"), null);					//ī����
		String cidx = checkStr(request.getParameter("cidx"), null);						//ī��纰 id
		String stat = checkStr(request.getParameter("stat"), null);						//����
		String info_url = checkStr(request.getParameter("info_url"), null);		//�� url
		String phone = checkStr(request.getParameter("phone"), null);					//�ݼ��� ��ȭ��ȣ
		String img_host = PropertiesUtil.get("img_host");											//img_host

		//�ȵ���̵�
		String os_G = checkStr(request.getParameter("os_G"), null);														//App ����  os
		String android_market = checkStr(request.getParameter("android_market"), null);				//�ȵ���̵� ����
		String android_app_id = checkStr(request.getParameter("android_app_id"), null);				//ȣ������(app_id)
		String android_app_pkg = checkStr(request.getParameter("android_app_pkg"), null);			//ȣ������(package��)
		String android_app_class = checkStr(request.getParameter("android_app_class"), null);	//ȣ������(���� class ��)
		String android_app_url = checkStr(request.getParameter("android_app_url"), null);			//�ٿ�ε� url

		//������
		String os_A = checkStr(request.getParameter("os_A"), null);													//App ����  os
		String iphone_market = checkStr(request.getParameter("iphone_market"), null);				//������ ����
		String iphone_app_id = checkStr(request.getParameter("iphone_app_id"), null);				//ȣ������(app_id)
		String iphone_app_class = checkStr(request.getParameter("iphone_app_class"), null);	//ȣ������(���� class ��)
		String iphone_app_url = checkStr(request.getParameter("iphone_app_url"), null);			//�ٿ�ε� url
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//��� �̹���(������4,������3GS,������̵�)
		
		try {
			
			android_params.put("cname", cname);
			android_params.put("cidx", cidx);
			android_params.put("stat", stat);
			android_params.put("info_url", info_url);
			android_params.put("os", os_G);
			android_params.put("market", android_market);
			android_params.put("app_id", android_app_id);
			android_params.put("app_pkg", android_app_pkg);
			android_params.put("app_class", android_app_class);
			android_params.put("app_url", android_app_url);
			android_params.put("img_host", img_host);
			android_params.put("phone", phone);
			
			iphone_params.put("cname", cname);
			iphone_params.put("cidx", cidx);
			iphone_params.put("stat", stat);
			iphone_params.put("info_url", info_url);
			iphone_params.put("os", os_A);
			iphone_params.put("market", iphone_market);
			iphone_params.put("app_id", iphone_app_id);
			iphone_params.put("app_class", iphone_app_class);
			iphone_params.put("app_url", iphone_app_url);		
			iphone_params.put("img_host", img_host);
			iphone_params.put("phone", phone);
			
			//�ű� ī�� ���
			service.insertCardReg(android_params, iphone_params, mpRequest);
			
			//ī��� ������ȸ �������� �̵�(os:IOS default)
			if("G".equals(os_G)) {
				os = "G";
			}
			if("A".equals(os_A)) {
				os = "A";
			}
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}
		
		response.sendRedirect("/base/card_list.ad?os="+os);
		
		return "base/card_list";
	}
	
	/**
	 * ī��� ���� ��ȸ
	 * @param 	os 			os
	 * @return	cname		ī����
	 * @return	cidx		cidx
	 * @return  imgUrl	�̹��� ���
	 * @return  idx 		���� ����
	 */
	@RequestMapping(value="/base/card_list.ad")
	public String selectCardList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### CardController cardList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdCard> list = null;
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String os = checkStr(request.getParameter("os"), "A");
		
		params.put("view", "list");
		params.put("os", os);
		
		//��ȸ
		list = service.selectCardList(params);
		
		request.setAttribute("os", os);
		request.setAttribute("mwAdCardList", list);

		log.debug("### CardController cardList END ###");
		
		return "base/card_list";
	}
	
	/**
	 * ī��� ���� �� ��ȸ
	 * @param 	os 			os
	 * @return	cidx		cidx
	 */
	@RequestMapping(value="/base/card_list_dtl.ad")
	public String selectCardListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### CardController selectCardListDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdCard mwAdCard = null;
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String cidx = checkStr(request.getParameter("cidx"), "");
		String os = checkStr(request.getParameter("os"), "A");
		
		params.put("view", "dtl");
		params.put("cidx", cidx);
		params.put("os", os);
		params.put("top", 1);
		
		//��ȸ
		mwAdCard = service.selectCardListDtl(params);

		
		request.setAttribute("mwAdCard", mwAdCard);

		log.debug("### CardController selectCardListDtl END ###");
		
		return "base/card_list_dtl";
	}
	
	/**
	 * ī��� ���� ����
	 * @param cname			ī����
	 * @param cidx			ī��纰 id
	 * @param os				os
	 * @param 					���� ����
	 * @param app_id		app_id
	 * @param app_pkg		app_pkg
	 * @param app_class	app_class
	 * @param 					�ٿ�ε� url
	 * @param 					����̹���(������4)
	 * @param 					����̹���(������3)
	 * @param 					����̹���(�ȵ���̵�)
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/card_dtl_update.ad", method=RequestMethod.POST)
	public String updateCardDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### CardController updateCardDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdCard> list = null;
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		//����
		String cname = checkStr(request.getParameter("cname"), null);					//ī����
		String cidx = checkStr(request.getParameter("cidx"), null);						//ī��纰 id
		String stat = checkStr(request.getParameter("stat"), null);						//����
		String info_url = checkStr(request.getParameter("info_url"), null);		//�� url
		String cidx_org = checkStr(request.getParameter("cidx_org"), "");			//ī��纰 id ��������
		String os = checkStr(request.getParameter("os"), null);								//����  os
		String phone = checkStr(request.getParameter("phone"), "");						//�ݼ��� ��ȭ��ȣ
		String img_host = PropertiesUtil.get("img_host");											//img_host

		//�ȵ���̵�
		String android_market = checkStr(request.getParameter("android_market"), null);				//�ȵ���̵� ����
		String android_app_id = checkStr(request.getParameter("android_app_id"), null);				//ȣ������(app_id)
		String android_app_pkg = checkStr(request.getParameter("android_app_pkg"), null);			//ȣ������(package��)
		String android_app_class = checkStr(request.getParameter("android_app_class"), null);	//ȣ������(���� class ��)
		String android_app_url = checkStr(request.getParameter("android_app_url"), null);			//�ٿ�ε� url

		//������
		String iphone_market = checkStr(request.getParameter("iphone_market"), null);				//������ ����
		String iphone_app_id = checkStr(request.getParameter("iphone_app_id"), null);				//ȣ������(app_id)
		String iphone_app_class = checkStr(request.getParameter("iphone_app_class"), null);	//ȣ������(���� class ��)
		String iphone_app_url = checkStr(request.getParameter("iphone_app_url"), null);			//�ٿ�ε� url
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//��� �̹���(������4,������3GS,������̵�)
		
		try {
			
			params.put("cname", cname);
			params.put("cidx", cidx);
			params.put("cidx_org", cidx_org);
			params.put("stat", stat);
			params.put("info_url", info_url);
			params.put("os", os);
			params.put("img_host", img_host);
			params.put("phone", phone);
			
			if("A".equals(os)) {
				params.put("market", iphone_market);
				params.put("app_id", iphone_app_id);
				params.put("app_class", iphone_app_class);
				params.put("app_url", iphone_app_url);		
			} else {
				params.put("market", android_market);
				params.put("app_id", android_app_id);
				params.put("app_pkg", android_app_pkg);
				params.put("app_class", android_app_class);
				params.put("app_url", android_app_url);
			}

			service.updateCardDtl(params, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		} 

		response.sendRedirect("/base/card_list.ad?os="+os);
		
		log.debug("### CardController updateCardDtl END ###");
		
		return "base/card_list";
		
	}
	
	/**
	 * ī��� ���� ����
	 * @param cidx			cidx
	 * @param os				os
	 * @param thisIdx		������ ������  ���� idx
	 * @param targetidx ������ ������  ���� idx
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/card_idx_update.ad", method=RequestMethod.POST)
	public String updateCardIdx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### CardController updateCardIdx START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdCard> list = null;
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String cidx = checkStr(request.getParameter("cidx"), "1");
		String os = checkStr(request.getParameter("os"), "A");
		int thisIdx = Integer.parseInt(checkStr(request.getParameter("thisIdx"), "1"));
		int targetIdx = Integer.parseInt(checkStr(request.getParameter("targetIdx"), "1"));
		
		try {
			
			params.put("os", os);
			params.put("thisIdx", thisIdx);
			params.put("targetIdx", targetIdx);
			params.put("idxGb", "update");			//���������� ���� ���а�
			
			//���� ���� update
			service.updateCardIdx(params, cidx);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		} 

		//�ʱ�ȭ ��, os(IOS,Android) ��ȸ
		params.clear();
		response.sendRedirect("/base/card_list.ad?os="+os);

		log.debug("### CardController updateCardIdx END ###");
		
		return "base/card_list";
	}
	
	/**
	 * ī��� ���� ����
	 * @param cidx			ī��纰 id
	 * @param os				os
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/card_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteCardDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### CardController deleteCardDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String cidx = checkStr(request.getParameter("cidx"), "");		//ī��纰 id
		String os = checkStr(request.getParameter("os"), "");				//os
		String idx = checkStr(request.getParameter("idx"), "");			//idx
		
		try {
			
			params.put("cidx", cidx);
			params.put("os", os);
			params.put("idx", idx);
			params.put("idxGb", "delete");	//������ �������� ���� ���а�

			//cidx�� ������� ���� Exception �߻�
			if("".equals(cidx)) {
				throw new Exception("cidx�� �����ϴ�. ���� Exception �߻�");
			}
			//os�� ������� ���� Exception �߻�
			if("".equals(os)) {
				throw new Exception("os�� �����ϴ�. ���� Exception �߻�");
			}
			
			service.deleteCardDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		} 

		log.debug("### CardController deleteCardDtl END ###");
		return null;
		
	}
	
}
