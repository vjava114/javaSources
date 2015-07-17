package com.wallet.admin.web.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.admin.model.MwAdPush;
import com.wallet.admin.service.MwAdPushService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.web.CommonController;

/*
 * Filename	: PushController.java
 * Class	: com.wallet.admin.web.base.PushController
 * History	: 2012/08/23, psj, �۾����� : ��Ÿ���� > Push �߼�
 * Comment	:
 */
@Controller
public class PushController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");

	/**
	 * Push ��� ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/push_reg.ad")
	public String pushReg(HttpServletRequest request, HttpServletResponse response) {
		return "base/push_reg";
	}
	
	/**
	 * Push ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/base/push_list.ad")
	public String selectPushList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PushController selectPushList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdPush> list = null;
		MwAdPushService service = new MwAdPushService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		params.put("view", "list");
		list = service.selectPushList(params);
	
		request.setAttribute("mwAdPushList", list);
		
		log.debug("### PushController selectPushList END ###");
		return "base/push_list";

	}
	
	/**
	 * Push �� ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/base/push_list_dtl.ad")
	public String selectPushListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PushController selectPushListDtl START ###");
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
	  String today_time = formatter.format(today);	//����ð�

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdPush mwAdPush = null;
		MwAdPushService service = new MwAdPushService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String idx = checkStr(request.getParameter("idx"), "");				//idx
		
		params.put("view", "dtl");
		params.put("idx", idx);
		params.put("top", 1);
		
		mwAdPush = service.selectPushListDtl(params);
		
		request.setAttribute("mwAdPush", mwAdPush);
		request.setAttribute("todayTime", today_time);
		
		log.debug("### PushController selectPushListDtl END ###");
		return "base/push_dtl";

	}
	
	/**
	 * Push ��� insert
	 * @param 	
	 */
	@RequestMapping(value="/base/push_reg.ad", method=RequestMethod.POST)
	public String insertPushReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### PushController insertPushReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdPushService service = new MwAdPushService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String send_type = checkStr(request.getParameter("send_type"), "");				//�߼� ����
		String title = checkStr(request.getParameter("title"), "");								//�߼� ����
		String send_mode = checkStr(request.getParameter("send_mode_org"), "");		//�߼� ����(��ü)
		String age_checkbox = checkStr(request.getParameter("age_checkbox"), "");	//���ɼ���
		String[] age = request.getParameterValues("age");													//����(10��~60���̻�)
		String sex_checkbox = checkStr(request.getParameter("sex_checkbox"), "");	//���༱��
		String sex = checkStr(request.getParameter("sex"), "");										//���༱��
		String os_checkbox = checkStr(request.getParameter("os_checkbox"), "");		//OS����
		String os = checkStr(request.getParameter("os"), "");											//OS����
		String memb_checkbox = checkStr(request.getParameter("memb_checkbox"), "");	//����� ����
		String memb_id = checkStr(request.getParameter("memb_id"), "");							//����� id
		
		String msg = checkStr(request.getParameter("msg"), "");							//�߼ֳ���
		String res_date = checkStr(request.getParameter("res_date"), "");		//�߼��Ͻ�
		String age_add = "";
		
		try {
		
			params.put("send_type", send_type);
			params.put("title", title);
			params.put("send_mode", send_mode);
			params.put("msg", msg);
			params.put("res_date", res_date);
			params.put("stat", "R");
			
			//�߼������� ��ü������ �ƴҰ��
			if("P".equals(send_mode)) {
				//������ ���õǾ� �������
				if(("Y".equals(age_checkbox)) && (age != null)) {
					for(int i=0; i<age.length; i++) {
						age_add += age[i] + "|";
					}
					params.put("age", age_add.substring(0, age_add.lastIndexOf("|")));
				}
				
				//���༱�� ���õǾ� �������
				if("Y".equals(sex_checkbox)) {
					params.put("sex", sex);
				}
				
				//OS���� ���õǾ� �������
				if("Y".equals(os_checkbox)) {
					params.put("os", os);
				}
				
				//����ʼ��� ���õǾ� �������
				if("Y".equals(memb_checkbox)) {
					params.put("memb_id", memb_id);
				}
			}
	
			service.insertPushReg(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

    log.debug("### UPushController insertPushReg END ###");
    
    //listȭ�� �̵�
    response.sendRedirect("/base/push_list.ad");
    
		return "base/push_list";
	}
	
	/**
	 * Push ���� update
	 * @param 	
	 */
	@RequestMapping(value="/base/push_dtl_update.ad", method=RequestMethod.POST)
	public String updatePushDtl(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdPushService service = new MwAdPushService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");				//�߼� ����
		String send_type = checkStr(request.getParameter("send_type"), "");				//�߼� ����
		String title = checkStr(request.getParameter("title"), "");								//�߼� ����
		String send_mode = checkStr(request.getParameter("send_mode_org"), "");		//�߼� ����(��ü)
		String age_checkbox = checkStr(request.getParameter("age_checkbox"), "");	//���ɼ���
		String[] age = request.getParameterValues("age");													//����(10��~60���̻�)
		String sex_checkbox = checkStr(request.getParameter("sex_checkbox"), "");	//���༱��
		String sex = checkStr(request.getParameter("sex"), "");										//���༱��
		String os_checkbox = checkStr(request.getParameter("os_checkbox"), "");		//OS����
		String os = checkStr(request.getParameter("os"), "");											//OS����
		String memb_checkbox = checkStr(request.getParameter("memb_checkbox"), "");	//����� ����
		String memb_id = checkStr(request.getParameter("memb_id"), "");							//����� id
		
		String msg = checkStr(request.getParameter("msg"), "");							//�߼ֳ���
		String res_date = checkStr(request.getParameter("res_date"), "");		//�߼��Ͻ�
		String age_add = "";
		
		try {
			params.put("idx", idx);
			params.put("send_type", send_type);
			params.put("title", title);
			params.put("send_mode", send_mode);
			params.put("msg", msg);
			params.put("res_date", res_date);
			
			//�߼������� ��ü������ �ƴҰ��
			if("P".equals(send_mode)) {
				//������ ���õǾ� �������
				if(("Y".equals(age_checkbox)) && (age != null)) {
					for(int i=0; i<age.length; i++) {
						age_add += age[i] + "|";
					}
					params.put("age", age_add.substring(0, age_add.lastIndexOf("|")));
				}
				
				//���༱�� ���õǾ� �������
				if("Y".equals(sex_checkbox)) {
					params.put("sex", sex);
				}
				
				//OS���� ���õǾ� �������
				if("Y".equals(os_checkbox)) {
					params.put("os", os);
				}
				
				//����ʼ��� ���õǾ� �������
				if("Y".equals(memb_checkbox)) {
					params.put("memb_id", memb_id);
				}
			}
			
			service.updatePushDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}
		
		log.debug("### PushController updatePushDtl START ###");
		
		//listȭ�� �̵�
    response.sendRedirect("/base/push_list.ad");
    
		return "base/user_list";
	}
	
	/**
	 * Push ���� ����
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/push_dtl_delete.ad", method=RequestMethod.POST)
	public String deletePushDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### PushController deletePushDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdPushService service = new MwAdPushService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//mgr_id
		
		try {
			
			params.put("idx", idx);

			//mgr_id�� ������� ���� Exception �߻�
			if("".equals(idx)) {
				throw new Exception("mgr_id�� �����ϴ�. ���� Exception �߻�");
			}
			
			service.deletePushDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### PushController deletePushDtl END ###");
		
		return null;
		
	}
	
}
