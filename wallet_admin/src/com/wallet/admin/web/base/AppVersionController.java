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

import com.wallet.admin.model.MwAdAppVersion;
import com.wallet.admin.service.MwAdAppVersionService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;

import com.wallet.common.web.CommonController;

/*
 * Filename	: AppVersionController.java
 * Class	: com.wallet.admin.web.base.AppVersionController
 * History	: 2012/08/23, psj, �۾����� : ��Ÿ���� > �� ���� ����
 * Comment	:
 */
@Controller
public class AppVersionController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * �� ���� ���� ȭ�� ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/app_version_list.ad")
	public String selectAppVersionList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AppVersionController selectAppVersionList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdAppVersion> list = null;
		MwAdAppVersionService service = new MwAdAppVersionService();
		HashMap<String,Object> params = new HashMap<String,Object>();

    String market = checkStr(request.getParameter("market"), "apple");			//����os
    
    params.put("view", "list");
    params.put("market", market);
    
		list = service.selectAppVersionList(params);
	
		request.setAttribute("market", market);
		request.setAttribute("mwAdAppVersionList", list);
		
		log.debug("### AppVersionController selectAppVersionList END ###");
		return "base/app_version_list";

	}
	
	/**
	 * �� ���� ���� �� ��ȸ
	 * @param 	idx 			idx
	 * @param 	market		��������
	 * @return
	 */
	@RequestMapping(value="/base/app_version_list_dtl.ad")
	public String selectAppVersionListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AppVersionController selectAppVersionListDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdAppVersion mwAdappVersion = null;
		MwAdAppVersionService service = new MwAdAppVersionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");
		String market = checkStr(request.getParameter("market"), "apple");
		
		params.put("view", "dtl");
		params.put("idx", idx);
		params.put("market", market);

		//��ȸ
		mwAdappVersion = service.selectAppVersionListDtl(params);

		request.setAttribute("mwAdappVersion", mwAdappVersion);

		log.debug("### AppVersionController selectAppVersionListDtl END ###");
		
		return "base/app_version_list_dtl";
	}
	
	/**
	 * �� ���� ��� ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/app_version_reg.ad")
	public String appVersionReg(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AppVersionController appVersionReg START ###");
		
		String market = checkStr(request.getParameter("market"), "");		//��������
	
		//���ó�¥
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);
	  
	  request.setAttribute("market", market);
	  request.setAttribute("reg_day", reg_day);
	
	  log.debug("### AppVersionController appVersionReg END ###");
	  
		return "base/app_version_reg";
	}
	
	/**
	 * �� ���� ��� insert
	 * @param 	market		��������
	 * @param 	ver				����
	 * @param 	mode			�������
	 * @param 	msg				�޼���
	 */
	@RequestMapping(value="/base/app_version_reg.ad", method=RequestMethod.POST)
	public String insertAppVersionReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### AppVersionController insertAppVersionReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdAppVersionService service = new MwAdAppVersionService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String market = checkStr(request.getParameter("market"), "");		//��������
		String ver = checkStr(request.getParameter("ver"), "");					//����
		String mode = checkStr(request.getParameter("mode"), "");				//�������
		String msg = checkStr(request.getParameter("msg"), "");					//�޼���
		
		try {
			
			params.put("market", market);
			params.put("ver", ver);
			params.put("mode", mode);
			params.put("msg", msg);

			service.insertAppVersionReg(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

    log.debug("### AppVersionController insertAppVersionReg END ###");
    
    //listȭ�� �̵�
    response.sendRedirect("/base/app_version_list.ad?market="+market);
		return "base/app_version_list";
	}
	
	/**
	 * �� ���� �� ����
	 * @param 	idx				idx
	 * @param 	market		��������
	 * @param 	ver				����
	 * @param 	mode			�������
	 * @param 	msg				�޼���
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/app_version_dtl_update.ad")
	public String updateAppVersionDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### AppVersionController updateAppVersionDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdAppVersionService service = new MwAdAppVersionService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String idx = checkStr(request.getParameter("idx"), "");					//idx
		String market = checkStr(request.getParameter("market"), "");		//��������
		String ver = checkStr(request.getParameter("ver"), "");					//����
		String mode = checkStr(request.getParameter("mode"), "");				//�������
		String msg = checkStr(request.getParameter("msg"), "");					//�޼���

		try {

			params.put("idx", idx);
			params.put("market", market);
			params.put("ver", ver);
			params.put("mode", mode);
			params.put("msg", msg);

			service.updateAppVersionDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}
		
		log.debug("### AppVersionController updateAppVersionDtl END ###");
		
		//listȭ�� �̵�
		response.sendRedirect("/base/app_version_list.ad?market="+market);
		
		return "base/app_version_list";
	}
	
	/**
	 * �� ���� ���� ����
	 * @param 	idx				idx
	 * @param 	market		��������
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/app_version_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteAppVersionDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### AppVersionController deleteBannerDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdAppVersionService service = new MwAdAppVersionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");					//idx
		String market = checkStr(request.getParameter("market"), "A");	//��������
		
		try {
			
			params.put("idx", idx);
			params.put("market", market);

			//idx�� ������� ���� Exception �߻�
			if("".equals(idx)) {
				throw new Exception("idx�� �����ϴ�. ���� Exception �߻�");
			}
			
			service.deleteAppVersionDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### AppVersionController deleteBannerDtl END ###");
		
		return null;
		
	}
	
}
