package com.wallet.admin.web.base;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.admin.model.MwAdLogin;
import com.wallet.admin.service.MwAdLoginService;
import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.cipher.Sha256Cipher;
import com.wallet.common.util.UrlConnection;
import com.wallet.common.util.Log;
import com.wallet.common.util.PropertiesUtil;

import com.wallet.common.web.CommonController;

/*
 * Filename	: LoginController.java
 * Class	: com.wallet.commom.web.base.LoginController
 * History	: 
 * Comment	:
 */
@Controller
public class LoginController extends CommonController {
	private Logger log = Log.getLogger("logs");

	private final String SSEEION_MGR_ID = "SSEEION_MGR_ID";				//����� ����
	private final String SSEEION_NAME = "SSEEION_NAME";						//�̸�
	private final String SSEEION_PART = "SSEEION_PART";						//�Ҽ�
	private final String SSEEION_PHONE = "SSEEION_PHONE";					//����ó
	private final String SSEEION_EMAIL = "SSEEION_EMAIL";					//EMAIL
	private final String SESSION_RG_DAY = "SESSION_RG_DAY";				//�����
	private final String SSEEION_CH_USID = "SSEEION_CH_USID";			//������ ID
	private final String SSEEION_CH_DAY = "SSEEION_CH_DAY";				//������
	private final String SSEEION_STAT = "SSEEION_STAT";						//����(�����,����)
	private final String SSEEION_RETRY_CNT = "SSEEION_RETRY_CNT";	//��й�ȣ ���� Ƚ��
	private final String SSEEION_LEV = "SSEEION_LEV";							//����
	private final String LOGIN_DATE = "LOGIN_DATE";							//������ �α��� �ð�
	
	/**
	 * �α��� ȭ�� �̵�, �α׾ƿ� ó��
	 * - �α��� ������ �̵��� session ����
	 * @return	
	 */
	@RequestMapping(value="/base/login.in")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		
		//ip�� ������� Y, �ٸ���� N
		String ipYn = checkStr(request.getParameter("ipYn"), "Y");			//id
		
		if (session == null || session.getAttribute(SSEEION_MGR_ID) == null 
				|| ((String) session.getAttribute(SSEEION_MGR_ID)).length() == 0) {
		} else {
			session.removeAttribute(SSEEION_MGR_ID);
		}
		
		 request.setAttribute("ipYn", ipYn);
		 return "/base/login";
	}
	
	
	/**
	 * �α��� ȭ�� �̵�, �α׾ƿ� ó��
	 * - �α��� ������ �̵��� session ����
	 * @return	
	 */
	@RequestMapping(value="/base/login2.in")
	public String login2(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute(SSEEION_MGR_ID) == null 
				|| ((String) session.getAttribute(SSEEION_MGR_ID)).length() == 0) {
		} else {
			session.removeAttribute(SSEEION_MGR_ID);
		}
		request.setAttribute("ipYn", "N");
		 return "/base/login";
	}
	
	/**
	 * SMS ����
	 * - ������ id,pw,��й�ȣ Ƚ�� üũ
	 * - id,pw,�ڵ��� ��ȣ�� ��ġ�ϸ� sms�߼�
	 * @return	
	 */
	@RequestMapping(value="/base/login_sms_check.in")
	public String loginSmsCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("### LoginController loginSmsCheck START ###");
		MwAdLogin mwAdLogin = null;
		MwAdLoginService service = new MwAdLoginService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");			//id
		String phone = checkStr(request.getParameter("phone"), "");			//phone
		String sms_url = PropertiesUtil.get("sms_url");

		String smsYn = "N";			//sms�߼� ��������
		String sms_result = "";	//sms���� ���
		String result = "Y";		//id ��ȸ���, �����Ͱ� �������, N

		String accesslog_part = "";
		String accesslog_msg = "";

		/* ��ȣȭ */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		try {
			
			phone = dbCipher.encoding(phone);
			
			params.put("mgr_id", mgr_id);
			params.put("phone", phone);
			params.put("top", 1);
			
			log.debug("### params : " + params);
		
			//accessLog
			accesslog_msg = "method=/base/login_sms_check.in";
			accesslog_msg += "&des=SMS����üũ";
			accesslog_msg += "&���=";		//accesslog
			
			mwAdLogin = service.selectLoginIdCheck(params);
			
			log.debug("### mwAdLogin : " + mwAdLogin);
			log.debug("### phone : " + phone);
			log.debug("### mwAdLogin.getPhone() : " + mwAdLogin.getPhone());
			
			if((mwAdLogin != null) && (phone.equals(mwAdLogin.getPhone())) ) {

				 //SMS ������ȣ ȣ��
				 sms_result = UrlConnection.connect(sms_url + "?phone=" + dbCipher.decoding(mwAdLogin.getPhone()));
				 
				 //����������
				 if("OK".equals(sms_result)) {
					 smsYn = "Y";
					 accesslog_msg += "SMS��������:SMS�߼�";		//accesslog
				 }

				 accesslog_part = mwAdLogin.getPart();	//accesslog
				 
			} else {
				result = "N";
			}

		} catch (Exception e) {
			log.debug("### loginSmsCheck Exception ###");
			e.printStackTrace();
		} finally {
			//accessLog
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", mgr_id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);
		}
		
		JSONObject jObj = new JSONObject();
		jObj.put("smsYn", smsYn);
		jObj.put("result", result);
			
		log.debug("### LoginController loginSmsCheck END ###");
		
		request.setAttribute("JSONObject", jObj);
		return "/common/result_page";
				
	}
	
	
	/**
	 * �α��� ID/PW üũ
	 * - ������ȣ üũ, üũ ������ ����
	 * - ��й�ȣ 3ȸ ���� üũ
	 * - ��й�ȣ 3ȸ �̸� ������, �α��� ������ retry_cnt �ʱ�ȭ
	 * @return	
	 */
	@SuppressWarnings("null")
	@RequestMapping(value="/base/login_check.in")
	public String loginCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("### LoginController loginCheck START ###");
		
		HttpSession session = request.getSession(false);
		MwAdLogin mwAdLogin = null;
		MwAdLogin mwAdSms = null;
		MwAdLoginService service = new MwAdLoginService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> sms_params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");		//id 
		String passwd = checkStr2(request.getParameter("passwd"), "");		//pw
		String phone = checkStr(request.getParameter("phone"), "");			//pw
		String sms = checkStr(request.getParameter("sms"), "");					//sms
		String loginResult = "FAIL";
		String loginStat = "R";
		String returnUrl = "/base/login.in";
		String pwThreeFail = "N";
		String smsPhoneStat = "R";
		String phoneStat = "R";
		String smsResult = "R";
		String accesslog_part = "";
		String accesslog_msg = "";
		int pwdEndDay = 100;	//��й�ȣ �������� default 100��
		
		/* ��ȣȭ */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		try {
	
			passwd = Sha256Cipher.encryptSHA256(passwd);
			phone = dbCipher.encoding(phone);
			
			params.put("mgr_id", mgr_id);
			params.put("top", 1);
			
			sms_params.put("sms", sms);
			sms_params.put("top", 1);
			
			log.debug("### passwd : " + passwd);
			log.debug("### params : " + params);
			
			//accessLog
			accesslog_msg = "method=/base/login_check.in";
			accesslog_msg += "&des=�α��� ID/PW üũ";
			accesslog_msg += "&���=";
			
			mwAdSms = service.selectSmsCheck(sms_params);
			
		if(mwAdSms != null) {
			
			if(dbCipher.decoding(phone).equals(mwAdSms.getPhone())) {
				
				mwAdLogin = service.selectLoginIdCheck(params);
				
				 if(mwAdLogin != null) {
					 
					 accesslog_part = mwAdLogin.getPart();	//accesslog

					//��й�ȣ ��������
					pwdEndDay = Integer.parseInt(mwAdLogin.getPwdEndDay());
						
						if(mgr_id.equals(mwAdLogin.getMgrId()) && "R".equals(mwAdLogin.getStat())
								&& phone.equals(mwAdLogin.getPhone())	&& pwdEndDay >= 0) {

							//��й�ȣ 3ȸ ���� üũ
							if(3 > mwAdLogin.getRetryCnt()) {
								if(passwd.equals(mwAdLogin.getPasswd())) {
									
									//�α��� ������ ���� ���� ���
									session.setAttribute(SSEEION_MGR_ID, mwAdLogin.getMgrId());
									session.setAttribute(SSEEION_NAME, mwAdLogin.getName());
									session.setAttribute(SSEEION_PART, mwAdLogin.getPart());
									session.setAttribute(SSEEION_PHONE, mwAdLogin.getPhone());
									session.setAttribute(SSEEION_EMAIL, mwAdLogin.getEmail());
									session.setAttribute(SESSION_RG_DAY, mwAdLogin.getRgDay());
									session.setAttribute(SSEEION_CH_USID, mwAdLogin.getChUsid());
									session.setAttribute(SSEEION_CH_DAY, mwAdLogin.getChDay());
									session.setAttribute(SSEEION_STAT, mwAdLogin.getStat());
									session.setAttribute(SSEEION_RETRY_CNT, mwAdLogin.getRetryCnt());
									session.setAttribute(SSEEION_LEV, mwAdLogin.getLev());
									session.setAttribute(LOGIN_DATE, mwAdLogin.getLoginDate().substring(0, mwAdLogin.getLoginDate().lastIndexOf(".")));

									loginResult = "SUCCESS";
									returnUrl = "/base/main_index.ad";
									log.debug("### session.getId() : " + session.getId());
									//�α��� ������ ��й�ȣ retry_cnt �ʱ�ȭ,������ȣ ����
									params.put("retry_cnt", 0);
									params.put("ipaddress", session.getId());
									service.updateRetryCnt(params);	//��й�ȣ ����Ƚ�� �ʱ�ȭ
									service.deleteSms(params);			//������ȣ ����
									
									accesslog_msg += "�α��μ���";
									
								} else {
									params.put("retry_cnt", mwAdLogin.getRetryCnt() + 1);
									service.updateRetryCnt(params);
									accesslog_msg += "�α��ν���:��й�ȣ����";
								}
							} else {
								pwThreeFail = "Y";
								accesslog_msg += "�α��ν���:��й�ȣ3ȸ����";
							}

						} else {
							if(mgr_id.equals(mwAdLogin.getMgrId()) && !"R".equals(mwAdLogin.getStat())) {
								loginStat = "F";
								accesslog_msg += "�α��ν���:���������";
							}
							if(!phone.equals(mwAdLogin.getPhone())) {
								 phoneStat = "F"; 
								 accesslog_msg += "�α��ν���:�޴�����ȣ����";
							 }
							if(session != null || ((String) session.getAttribute(SSEEION_MGR_ID)).length() > 0) {
								 session.removeAttribute(SSEEION_MGR_ID);
								 accesslog_msg += "�α��ν���:���Ǿ���";
							 } 
							
						}
						
				 } else {
					 accesslog_msg += "�α��ν���:id��ȸ�������";
				 }
				
			} else {
				smsPhoneStat = "F";
				accesslog_msg += "�α��ν���:SMS�޴�����ȣ����";
			}

			
		} else {
			smsResult = "F";
			accesslog_msg += "�α��ν���:SMS������ȣ����";
		}
		
		service.commit();
		
		} catch (Exception e) {
			service.rollback();
			e.printStackTrace();
		} finally {
			//accessLog	
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", mgr_id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert
		}

		JSONObject jObj = new JSONObject();
		jObj.put("loginResult", loginResult);
		jObj.put("returnUrl", returnUrl);
		jObj.put("loginStat", loginStat);
		jObj.put("smsPhoneStat", smsPhoneStat);
		jObj.put("phoneStat", phoneStat);
		jObj.put("pwThreeFail", pwThreeFail);
		jObj.put("smsResult", smsResult);
		jObj.put("pwdEndDay", pwdEndDay);
		

		request.setAttribute("JSONObject", jObj);
		
		log.debug("### LoginController loginCheck END ###");
		
		return "/common/result_page";
				
	}
	
	/**
	 * AccessLog insert
	 * @return	
	 */
	public void insertAccessLogReg(HashMap<String,Object> accesslog_params) throws Exception {

		MwAdAccessLogService accessLogService = new MwAdAccessLogService();
		
		try {
			accessLogService.insertAccessLogReg(accesslog_params);
			accessLogService.commit();
		} catch (Exception e) {
			accessLogService.rollback();
			e.printStackTrace();
		}
		
	}
	
	/**
	 * �������� ��޹�� ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/privacy.in")
	public String selectAppVersionList(HttpServletRequest request, HttpServletResponse response) {
		return "/base/privacy";

	}
	
	/**
	 * ���� ȭ�� �̵� (��ȭ��)
	 * @return	
	 */
	@RequestMapping(value="/base/main_index.ad")
	public String mainIndex(HttpServletRequest request, HttpServletResponse response) {
		 return "/base/main_index";
	}
	
}
