package com.wallet.admin.web.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.admin.model.MwAdUser;
import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.admin.service.MwAdUserService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.cipher.Sha256Cipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.util.UrlConnection;
import com.wallet.common.util.Utility;
import com.wallet.common.web.CommonController;

/*
 * Filename	: UserController.java
 * Class	: com.wallet.admin.web.base.UserController
 * History	: 2012/08/23, psj, �۾����� : �����ڸ޴� > ����� ���� ����
 * Comment	:
 */
@Controller
public class UserController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");

	/**
	 * ����� �������� ��� ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/user_reg.ad")
	public String userReg(HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String login_Id = checkStr(getSessionMgrId(request), "");			//�α���id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";

		try {
			//accessLog
			accesslog_msg = "method=/base/user_reg.ad";
			accesslog_msg += "&des=����� ��� ȭ�� ������ ȣ��";
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", login_Id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return "base/user_reg";
	}
	
	/**
	 * ����� �������� ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/base/user_list.ad")
	public String selectUserList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AdminUserController selectUserList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdUser> list = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		Map<String, String> result_map = null;
		List<Map<String,String>> result_map_list = new ArrayList<Map<String,String>>();
		
		String login_Id = checkStr(getSessionMgrId(request), "");			//�α���id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		/* ��ȣȭ */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		try {
			
			params.put("view", "list");
			list = service.selectUserList(params);

			//��ȣȭ�� ���� decoding�Ͽ� ���� ���
			for(int i=0; i<list.size(); i++) {
				result_map = new HashMap<String, String>();
				
				result_map.put("mgrId", list.get(i).getMgrId());
				result_map.put("name", dbCipher.decoding(list.get(i).getName()));
				result_map.put("phone", list.get(i).getPhone());
				result_map.put("part", list.get(i).getPart());
				result_map.put("lev", list.get(i).getLev());
				result_map.put("stat", list.get(i).getStat());
				result_map_list.add(i, result_map);
			}
			
			//accessLog
			accesslog_msg = "method=/base/user_list.ad";
			accesslog_msg += "&des=����� ���� ��� ��ȸ";
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", login_Id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

		request.setAttribute("mwAdUserList", result_map_list);
		
		log.debug("### AdminUserController selectUserList END ###");
		return "base/user_list";

	}
	
	/**
	 * ����� �������� �� ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/base/user_list_dtl.ad")
	public String selectUserListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AdminUserController selectUserListDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdUser mwAdUser_ec = null;
		MwAdUser mwAdUser = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");			//id
		String login_Id = checkStr(getSessionMgrId(request), "");			//�α���id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
	
		/* ��ȣȭ */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		try {
			
			params.put("view", "dtl");
			params.put("mgr_id", mgr_id);
			params.put("top", 1);
	   
			mwAdUser = service.selectUserListDtl(params);

			mwAdUser.setName(dbCipher.decoding(mwAdUser.getName()));
			mwAdUser.setEmail(dbCipher.decoding(mwAdUser.getEmail()));
			mwAdUser.setPhone(dbCipher.decoding(mwAdUser.getPhone()));
			
			log.debug("### mwAdUser : " + mwAdUser);
			
			//accessLog
			accesslog_msg = "method=/base/user_list_dtl.ad";
			accesslog_msg += "&�� ��ȸ�� id:"+mgr_id;
			accesslog_msg += "&des=����� ���� �� ��ȸ";
			accesslog_msg += "&ip="+request.getRemoteAddr();

			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", login_Id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("mwAdUser", mwAdUser);
		
		log.debug("### AdminUserController selectUserListDtl END ###");
		return "base/user_dtl";

	}
	
	/**
	 * ����� ���� ��� insert
	 * @param 	
	 */
	@RequestMapping(value="/base/user_reg.ad", method=RequestMethod.POST)
	public String insertUserReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### UserController insertUserReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
	
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");	//id
		String passwd = checkStr2(request.getParameter("passwd"), "");	//��й�ȣ
		String name = checkStr(request.getParameter("name"), "");			//�̸�
		String part = checkStr(request.getParameter("part"), "");			//�Ҽ�
		String email = checkStr(request.getParameter("email"), "");		//email
		String phone = checkStr(request.getParameter("phone"), "");		//phone
		String lev = checkStr(request.getParameter("lev"), "");				//lev
		String login_Id = checkStr(getSessionMgrId(request), "");			//�α���id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		/* ��ȣȭ */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		try {
			
			//accessLog
			accesslog_msg = "method=/base/user_reg.ad";
			accesslog_msg += "&��ϵ�id:"+mgr_id;
			accesslog_msg += "&des=����� ���� ���";
			
			//��ȣȭ
			passwd = Sha256Cipher.encryptSHA256(passwd);
			name = dbCipher.encoding(name);
			email = dbCipher.encoding(email);
			phone = dbCipher.encoding(phone);

			params.put("mgr_id", mgr_id);
			params.put("passwd", passwd);
			params.put("name", name);
			params.put("part", part);
			params.put("email", email);
			params.put("phone", phone);
			params.put("stat", "R");
			params.put("ch_usid", login_Id);
			params.put("lev", lev);

			log.debug("### params : " + params);
			
			service.insertUserReg(params);
			service.commit();

		} catch (Exception e) {
			service.rollback();
			accesslog_msg += "&result=����� ���� ��� ����";
			e.printStackTrace();
		} finally {
			//accessLog
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", login_Id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert
		}
		
    log.debug("### UserController insertUserReg END ###");
    
    //listȭ�� �̵�
    response.sendRedirect("/base/user_list.ad");
    
		return "base/user_list";
	}
	
	/**
	 * ����� ���� ���� update
	 * @param 	
	 */
	@RequestMapping(value="/base/user_dtl_update.ad", method=RequestMethod.POST)
	public String updateUserDtl(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");						//id
		String mgr_id_org = checkStr(request.getParameter("mgr_id_org"), "");		//id
		String name = checkStr(request.getParameter("name"), "");					//�̸�
		String part = checkStr(request.getParameter("part"), "");					//�Ҽ�
		String email = checkStr(request.getParameter("email"), "");				//email
		String phone = checkStr(request.getParameter("phone"), "");				//phone
		String stat = checkStr(request.getParameter("stat"), "");					//����(�����,����)
		String lev = checkStr(request.getParameter("lev"), "");				//lev
		String login_Id = checkStr(getSessionMgrId(request), "");			//�α���id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		/* ��ȣȭ */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED

		try {
			
			//accessLog
			accesslog_msg = "method=/base/user_dtl_update.ad";
			accesslog_msg += "&�����id:"+mgr_id_org;
			accesslog_msg += "&des=����� ���� ����";
			
			//��ȣȭ
			name = dbCipher.encoding(name);
			email = dbCipher.encoding(email);
			phone = dbCipher.encoding(phone);
			
			params.put("mgr_id", mgr_id);
			params.put("mgr_id_org", mgr_id_org);
			params.put("name", name);
			params.put("part", part);
			params.put("email", email);
			params.put("phone", phone);
			params.put("stat", stat);
			params.put("ch_usid", login_Id);
			params.put("lev", lev);

			service.updateUserDtl(params);
			service.commit();

		} catch (Exception e) {
			service.rollback();
			accesslog_msg += "&result=����� ���� ���� ����";
			e.printStackTrace();
		} finally {
			//accessLog
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", login_Id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert			
		}
		
		log.debug("### UserController updateUserDtl START ###");
		
		//listȭ�� �̵�
    response.sendRedirect("/base/user_list.ad");
    
		return "base/user_list";
	}
	
	/**
	 * ����� ���� ���� ����
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/user_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteUserDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### UserController deleteUserDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");		//mgr_id
		String login_Id = checkStr(getSessionMgrId(request), "");
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		try {
			//accessLog
			accesslog_msg = "method=/base/user_dtl_delete.ad";
			accesslog_msg += "&������id:"+mgr_id;
			accesslog_msg += "&des=����� ���� ����";
			
			params.put("mgr_id", mgr_id);

			//mgr_id�� ������� ���� Exception �߻�
			if("".equals(mgr_id)) {
				throw new Exception("mgr_id�� �����ϴ�. ���� Exception �߻�");
			}
			
			service.deleteUserDtl(params);
			service.commit();

		} catch (Exception e) {
			service.rollback();
			accesslog_msg += "&des=����� ���� ���� ����";
			e.printStackTrace();
		} finally {
			//accessLog
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", login_Id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert
		}

		log.debug("### UserController deleteUserDtl END ###");
		
		return null;
		
	}
	
	/**
	 * ����� ���� ��й�ȣ �ʱ�ȭ
	 * - ��й�ȣ �ʱ�ȭ�� ��й�ȣ �������� update
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/user_passwd_init.ad", method=RequestMethod.POST)
	public String updateUserPasswdInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### UserController updateUserPasswdInit START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");		//mgr_id
		String phone = checkStr(request.getParameter("phone"), "");			//phoneb
		String login_Id = checkStr(getSessionMgrId(request), "");
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		String pwd_sms_url = PropertiesUtil.get("pwd_sms_url");
		String smsYn = "N";
		
		/* ��ȣȭ */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		try {
			//accessLog
			accesslog_msg = "method=/base/user_passwd_init.ad";
			accesslog_msg += "&�ʱ�ȭ��id:"+mgr_id;
			accesslog_msg += "&des=����� ���� �ʱ�ȭ";
			
			//mgr_id�� ������� ���� Exception �߻�
			if("".equals(mgr_id)) {
				accesslog_msg += "&result=����� ���� �ʱ�ȭ ����";
				throw new Exception("mgr_id�� �����ϴ�. ���� Exception �߻�");
			}

			//SMS ������ȣ ȣ��
			 String sms_result = UrlConnection.connect(pwd_sms_url + "?phone=" + dbCipher.decoding(phone));
			 
			 //����������
			 if("OK|".equals(sms_result.substring(0,3))) {
				 
				 log.debug("### PWD SMS SUCCESS ###");
				 	smsYn = "Y";
				 	
				 	params.put("mgr_id_org", mgr_id);
					params.put("passwd", Sha256Cipher.encryptSHA256(sms_result.substring(3,sms_result.length())));
					params.put("retry_cnt", "0");
					params.put("end_day", 10);
					
					service.updateUserDtl(params);
					service.commit();
				
					accesslog_msg += "&result=����� ���� �ʱ�ȭ ����";
					
			 } else {
				 log.debug("### PWD SMS FAIL ###");
			 }

		} catch (Exception e) {
			service.rollback();
			accesslog_msg += "&result=����� ���� �ʱ�ȭ ����";
			e.printStackTrace();
		} finally {
			//accessLog
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", login_Id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert
		}

		JSONObject jObj = new JSONObject();
		jObj.put("smsYn", smsYn);
		request.setAttribute("JSONObject", jObj);
		
		log.debug("### UserController updateUserPasswdInit END ###");
		
		return "/common/result_page";
		
	}
	
	/**
	 * ����� ���� id ���� üũ
	 * @return	
	 */
	@RequestMapping(value="/base/user_id_check.ad")
	public String selectUserIdCheck(HttpServletRequest request, HttpServletResponse response) {

		log.debug("### UserController selectUserIdCheck START ###");
		
		MwAdUser mwAdUser = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		String userIdYn = "Y";	//Y:��밡��, N:��� �Ұ���
		
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");		//mgr_id
		
		params.put("view", "dtl");
		params.put("mgr_id", mgr_id);
		params.put("top", 1);
		
		mwAdUser = service.selectUserListDtl(params);
		
		if(mwAdUser != null) {
			userIdYn = "N";
		}
		
		JSONObject jObj = new JSONObject();
		jObj.put("userIdYn", userIdYn);

		request.setAttribute("JSONObject", jObj);
		
		log.debug("### UserController selectUserIdCheck END ###");
		
		return "/common/result_page";
				
	}
	
	/**
	 * ��й�ȣ ����� : �ش� ����� id�� ���� ��й�ȣ üũ
	 * @return	
	 */
	@RequestMapping(value="/base/user_pwd_check.ad")
	public String selectUserPwdCheck(HttpServletRequest request, HttpServletResponse response) {

		log.debug("### UserController selectUserPwdCheck START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdUser mwAdUser = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
	
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");	//id
		String passwd = checkStr2(request.getParameter("passwd"), "");	//��й�ȣ
		String userPwdYn = "N";	//Y:��й�ȣ ����, N:��й�ȣ Ʋ��
		
		try {
			
			params.put("view", "dtl");
			params.put("mgr_id", mgr_id);
			params.put("passwd", Sha256Cipher.encryptSHA256(passwd));
			params.put("top", 1);
			
			mwAdUser = service.selectUserListDtl(params);
			
			if(mwAdUser != null) {
				userPwdYn = "Y";
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		JSONObject jObj = new JSONObject();
		jObj.put("userPwdYn", userPwdYn);

		request.setAttribute("JSONObject", jObj);
		
		log.debug("### userPwdYn : " + userPwdYn);
		log.debug("### UserController selectUserPwdCheck END ###");
		
		return "/common/result_page";
				
	}
	
	/**
	 * ����� ���� ��й�ȣ ���� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/user_passwd.ad")
	public String userPasswd(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserController userPasswd START ###");
		
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String login_Id = checkStr(getSessionMgrId(request), "");			//�α���id
		String name = checkStr(getSessionName(request), "");					//�α����̸�
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		/* ��ȣȭ */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		try {
			//accessLog
			accesslog_msg = "method=/base/user_passwd.ad";
			accesslog_msg += "&des=����� ���� ��й�ȣ ���� ȭ�� ������ ȣ��";
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", login_Id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		request.setAttribute("id", login_Id);
		request.setAttribute("name", name);
		
		log.debug("### UserController userPasswd END ###");
		
		return "base/user_passwd";
	}
	
	/**
	 * ����� ���� ��й�ȣ ���� 
	 * @param 	
	 */
	@RequestMapping(value="/base/user_passwd.ad", method=RequestMethod.POST)
	public String updateUserPasswd(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### UserController updateUserPasswd START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
	
		String mgr_id_org = checkStr(request.getParameter("mgr_id_org"), "");	//id
		String new_passwd = checkStr2(request.getParameter("new_passwd"), "");	//����� ��й�ȣ
		String login_Id = checkStr(getSessionMgrId(request), "");			//�α���id
		String accesslog_part = checkStr(getSessionPart(request), "");//�α��� �Ҽ�
		String accesslog_msg = "";
		String pwdResult = "SUCCESS";
		
		try {
			
			//accessLog
			accesslog_msg = "method=/base/user_passwd.ad";
			accesslog_msg += "&des=����� ���� ��й�ȣ ����";

			params.put("mgr_id", mgr_id_org);
			params.put("passwd", Sha256Cipher.encryptSHA256(new_passwd));
			params.put("ch_usid", login_Id);
			
			service.updateUserPasswd(params);
			service.commit();
			
		} catch (Exception e) {
			pwdResult = "FAIL";
			accesslog_msg += "&result=����� ���� ��й�ȣ ���� ����";
			service.rollback();
			e.printStackTrace();
		} finally {
			//accessLog
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", login_Id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert
		}
		
		JSONObject jObj = new JSONObject();
		jObj.put("pwdResult", pwdResult);

		request.setAttribute("JSONObject", jObj);
		
		log.debug("### pwdResult : " + pwdResult);
		log.debug("### UserController updateUserPasswd END ###");
		
		return "/common/result_page";

	}
	
	/**
	 * AccessLog insert
	 * @return	
	 */
	public void insertAccessLogReg(HashMap<String,Object> accesslog_params) throws Exception {

		MwAdAccessLogService service = new MwAdAccessLogService();
		
		try {
			service.insertAccessLogReg(accesslog_params);
			service.commit();
		} catch (Exception e) {
			service.rollback();
			e.printStackTrace();
		}
		
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////
//### ����� �������� ��ȸ - �ʱ�ȭ ���� START ###
//////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ����� �������� ��ȸ : �ʱ�ȭ ������ list ȭ�� ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/base/user_list_init.ad")
	public String selectUserInitList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AdminUserController selectUserInitList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdUser> list = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		Map<String, String> result_map = null;
		List<Map<String,String>> result_map_list = new ArrayList<Map<String,String>>();
		
		String login_Id = checkStr(getSessionMgrId(request), "");			//�α���id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		/* ��ȣȭ */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		try {
			
			params.put("view", "list");
			list = service.selectUserList(params);

			//��ȣȭ�� ���� decoding�Ͽ� ���� ���
			for(int i=0; i<list.size(); i++) {
				result_map = new HashMap<String, String>();
				
				result_map.put("mgrId", list.get(i).getMgrId());
				result_map.put("name", dbCipher.decoding(list.get(i).getName()));
				result_map.put("phone", list.get(i).getPhone());
				result_map.put("part", list.get(i).getPart());
				result_map.put("lev", list.get(i).getLev());
				result_map.put("stat", list.get(i).getStat());
				result_map_list.add(i, result_map);
			}
			
			//accessLog
			accesslog_msg = "method=/base/user_list.ad";
			accesslog_msg += "&des=����� ���� ��� ��ȸ";
			accesslog_msg += "&ip="+request.getRemoteAddr();
			
			accesslog_params.put("part", accesslog_part);
			accesslog_params.put("admin_id", login_Id);
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

		request.setAttribute("mwAdUserList", result_map_list);
		
		log.debug("### AdminUserController selectUserInitList END ###");
		return "base/user_list_init";

	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////
// ### ���� ������ ���� ��� START ###
//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * ������ ���� ��� ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/admin_reg.in")
	public String adminReg(HttpServletRequest request, HttpServletResponse response) {
		return "base/admin_reg";
	}
	
	/**
	 * ������ ���� ��� insert
	 * @param 	
	 */
	@RequestMapping(value="/base/admin_reg.in", method=RequestMethod.POST)
	public String insertAdminReg(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserController insertAdminReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
	
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");	//id
		String passwd = checkStr2(request.getParameter("passwd"), "");	//��й�ȣ
		String name = checkStr(request.getParameter("name"), "");			//�̸�
		String part = checkStr(request.getParameter("part"), "");			//�Ҽ�
		String email = checkStr(request.getParameter("email"), "");		//email
		String phone = checkStr(request.getParameter("phone"), "");		//phone
		String lev = checkStr(request.getParameter("lev"), "");				//lev
		String accesslog_msg = "";

		//�ѱ۱�������
		try {
			name = URLDecoder.decode(name, "UTF-8");
			part = URLDecoder.decode(part, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
		/* ��ȣȭ */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		try {
			
			//��ȣȭ
			passwd = Sha256Cipher.encryptSHA256(passwd);
			name = dbCipher.encoding(name);
			email = dbCipher.encoding(email);
			phone = dbCipher.encoding(phone);

			params.put("mgr_id", mgr_id);
			params.put("passwd", passwd);
			params.put("name", name);
			params.put("part", part);
			params.put("email", email);
			params.put("phone", phone);
			params.put("stat", "R");
			params.put("ch_usid", mgr_id);
			params.put("lev", lev);

			log.debug("### params : " + params);
			
			service.insertUserReg(params);
			service.commit();

			//accessLog
			accesslog_msg = "method=/base/user_reg.ad";
			accesslog_msg += "&��ϵ�id:"+mgr_id;
			accesslog_msg += "&des=������ ���� ���";
			
			accesslog_msg += "&ip="+request.getRemoteAddr();
			accesslog_params.put("msg", accesslog_msg);
			
			insertAccessLogReg(accesslog_params);	//accesslog insert

		} catch (Exception e) {
			service.rollback();
		}
		
    log.debug("### UserController insertAdminReg END ###");
    
    JSONObject jObj = new JSONObject();
    
		return "/common/result_page";
	}

	/**
	 * ������ ���� id ���� üũ
	 * @return	
	 */
	@RequestMapping(value="/base/admin_id_check.in")
	public String selectAdminIdCheck(HttpServletRequest request, HttpServletResponse response) {

		log.debug("### UserController selectAdminIdCheck START ###");
		
		MwAdUser mwAdUser = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		String userIdYn = "Y";	//Y:��밡��, N:��� �Ұ���
		
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");		//mgr_id
		
		params.put("view", "dtl");
		params.put("mgr_id", mgr_id);
		params.put("top", 1);
		
		mwAdUser = service.selectUserListDtl(params);
		
		if(mwAdUser != null) {
			userIdYn = "N";
		}
		
		JSONObject jObj = new JSONObject();
		jObj.put("userIdYn", userIdYn);

		request.setAttribute("JSONObject", jObj);
		
		log.debug("### UserController selectAdminIdCheck END ###");
		
		return "/common/result_page";
				
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////
// ### ���� ������ ���� ��� END ###
//////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
