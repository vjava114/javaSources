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
 * History	: 2012/08/23, psj, 작업구분 : 관리자메뉴 > 사용자 계정 관리
 * Comment	:
 */
@Controller
public class UserController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");

	/**
	 * 사용자 계정관리 등록 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/user_reg.ad")
	public String userReg(HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String login_Id = checkStr(getSessionMgrId(request), "");			//로그인id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";

		try {
			//accessLog
			accesslog_msg = "method=/base/user_reg.ad";
			accesslog_msg += "&des=사용자 등록 화면 페이지 호출";
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
	 * 사용자 계정관리 조회
	 * @return	
	 */
	@RequestMapping(value="/base/user_list.ad")
	public String selectUserList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AdminUserController selectUserList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdUser> list = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		Map<String, String> result_map = null;
		List<Map<String,String>> result_map_list = new ArrayList<Map<String,String>>();
		
		String login_Id = checkStr(getSessionMgrId(request), "");			//로그인id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
		try {
			
			params.put("view", "list");
			list = service.selectUserList(params);

			//암호화된 정보 decoding하여 새로 담기
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
			accesslog_msg += "&des=사용자 계정 목록 조회";
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
	 * 사용자 계정관리 상세 조회
	 * @return	
	 */
	@RequestMapping(value="/base/user_list_dtl.ad")
	public String selectUserListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AdminUserController selectUserListDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdUser mwAdUser_ec = null;
		MwAdUser mwAdUser = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");			//id
		String login_Id = checkStr(getSessionMgrId(request), "");			//로그인id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
	
		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
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
			accesslog_msg += "&상세 조회된 id:"+mgr_id;
			accesslog_msg += "&des=사용자 계정 상세 조회";
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
	 * 사용자 계정 등록 insert
	 * @param 	
	 */
	@RequestMapping(value="/base/user_reg.ad", method=RequestMethod.POST)
	public String insertUserReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### UserController insertUserReg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
	
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");	//id
		String passwd = checkStr2(request.getParameter("passwd"), "");	//비밀번호
		String name = checkStr(request.getParameter("name"), "");			//이름
		String part = checkStr(request.getParameter("part"), "");			//소속
		String email = checkStr(request.getParameter("email"), "");		//email
		String phone = checkStr(request.getParameter("phone"), "");		//phone
		String lev = checkStr(request.getParameter("lev"), "");				//lev
		String login_Id = checkStr(getSessionMgrId(request), "");			//로그인id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
		try {
			
			//accessLog
			accesslog_msg = "method=/base/user_reg.ad";
			accesslog_msg += "&등록된id:"+mgr_id;
			accesslog_msg += "&des=사용자 계정 등록";
			
			//암호화
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
			accesslog_msg += "&result=사용자 계정 등록 실패";
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
    
    //list화면 이동
    response.sendRedirect("/base/user_list.ad");
    
		return "base/user_list";
	}
	
	/**
	 * 사용자 계정 수정 update
	 * @param 	
	 */
	@RequestMapping(value="/base/user_dtl_update.ad", method=RequestMethod.POST)
	public String updateUserDtl(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");						//id
		String mgr_id_org = checkStr(request.getParameter("mgr_id_org"), "");		//id
		String name = checkStr(request.getParameter("name"), "");					//이름
		String part = checkStr(request.getParameter("part"), "");					//소속
		String email = checkStr(request.getParameter("email"), "");				//email
		String phone = checkStr(request.getParameter("phone"), "");				//phone
		String stat = checkStr(request.getParameter("stat"), "");					//상태(사용중,중지)
		String lev = checkStr(request.getParameter("lev"), "");				//lev
		String login_Id = checkStr(getSessionMgrId(request), "");			//로그인id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED

		try {
			
			//accessLog
			accesslog_msg = "method=/base/user_dtl_update.ad";
			accesslog_msg += "&변경된id:"+mgr_id_org;
			accesslog_msg += "&des=사용자 계정 변경";
			
			//암호화
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
			accesslog_msg += "&result=사용자 계정 변경 실패";
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
		
		//list화면 이동
    response.sendRedirect("/base/user_list.ad");
    
		return "base/user_list";
	}
	
	/**
	 * 사용자 계정 정보 삭제
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/user_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteUserDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### UserController deleteUserDtl START ###");

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
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
			accesslog_msg += "&삭제된id:"+mgr_id;
			accesslog_msg += "&des=사용자 계정 삭제";
			
			params.put("mgr_id", mgr_id);

			//mgr_id가 없을경우 강제 Exception 발생
			if("".equals(mgr_id)) {
				throw new Exception("mgr_id가 없습니다. 강제 Exception 발생");
			}
			
			service.deleteUserDtl(params);
			service.commit();

		} catch (Exception e) {
			service.rollback();
			accesslog_msg += "&des=사용자 계정 삭제 실패";
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
	 * 사용자 계정 비밀번호 초기화
	 * - 비밀번호 초기화시 비밀번호 종료일자 update
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/user_passwd_init.ad", method=RequestMethod.POST)
	public String updateUserPasswdInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### UserController updateUserPasswdInit START ###");

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
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
		
		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
		try {
			//accessLog
			accesslog_msg = "method=/base/user_passwd_init.ad";
			accesslog_msg += "&초기화된id:"+mgr_id;
			accesslog_msg += "&des=사용자 계정 초기화";
			
			//mgr_id가 없을경우 강제 Exception 발생
			if("".equals(mgr_id)) {
				accesslog_msg += "&result=사용자 계정 초기화 실패";
				throw new Exception("mgr_id가 없습니다. 강제 Exception 발생");
			}

			//SMS 인증번호 호출
			 String sms_result = UrlConnection.connect(pwd_sms_url + "?phone=" + dbCipher.decoding(phone));
			 
			 //인증성공시
			 if("OK|".equals(sms_result.substring(0,3))) {
				 
				 log.debug("### PWD SMS SUCCESS ###");
				 	smsYn = "Y";
				 	
				 	params.put("mgr_id_org", mgr_id);
					params.put("passwd", Sha256Cipher.encryptSHA256(sms_result.substring(3,sms_result.length())));
					params.put("retry_cnt", "0");
					params.put("end_day", 10);
					
					service.updateUserDtl(params);
					service.commit();
				
					accesslog_msg += "&result=사용자 계정 초기화 성공";
					
			 } else {
				 log.debug("### PWD SMS FAIL ###");
			 }

		} catch (Exception e) {
			service.rollback();
			accesslog_msg += "&result=사용자 계정 초기화 실패";
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
	 * 사용자 계정 id 유무 체크
	 * @return	
	 */
	@RequestMapping(value="/base/user_id_check.ad")
	public String selectUserIdCheck(HttpServletRequest request, HttpServletResponse response) {

		log.debug("### UserController selectUserIdCheck START ###");
		
		MwAdUser mwAdUser = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		String userIdYn = "Y";	//Y:사용가능, N:사용 불가능
		
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
	 * 비밀번호 변경시 : 해당 사용자 id의 현재 비밀번호 체크
	 * @return	
	 */
	@RequestMapping(value="/base/user_pwd_check.ad")
	public String selectUserPwdCheck(HttpServletRequest request, HttpServletResponse response) {

		log.debug("### UserController selectUserPwdCheck START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdUser mwAdUser = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
	
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");	//id
		String passwd = checkStr2(request.getParameter("passwd"), "");	//비밀번호
		String userPwdYn = "N";	//Y:비밀번호 맞음, N:비밀번호 틀림
		
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
	 * 사용자 계정 비밀번호 변경 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/user_passwd.ad")
	public String userPasswd(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserController userPasswd START ###");
		
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		
		String login_Id = checkStr(getSessionMgrId(request), "");			//로그인id
		String name = checkStr(getSessionName(request), "");					//로그인이름
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
		try {
			//accessLog
			accesslog_msg = "method=/base/user_passwd.ad";
			accesslog_msg += "&des=사용자 계정 비밀번호 변경 화면 페이지 호출";
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
	 * 사용자 계정 비밀번호 변경 
	 * @param 	
	 */
	@RequestMapping(value="/base/user_passwd.ad", method=RequestMethod.POST)
	public String updateUserPasswd(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### UserController updateUserPasswd START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
	
		String mgr_id_org = checkStr(request.getParameter("mgr_id_org"), "");	//id
		String new_passwd = checkStr2(request.getParameter("new_passwd"), "");	//변경될 비밀번호
		String login_Id = checkStr(getSessionMgrId(request), "");			//로그인id
		String accesslog_part = checkStr(getSessionPart(request), "");//로그인 소속
		String accesslog_msg = "";
		String pwdResult = "SUCCESS";
		
		try {
			
			//accessLog
			accesslog_msg = "method=/base/user_passwd.ad";
			accesslog_msg += "&des=사용자 계정 비밀번호 변경";

			params.put("mgr_id", mgr_id_org);
			params.put("passwd", Sha256Cipher.encryptSHA256(new_passwd));
			params.put("ch_usid", login_Id);
			
			service.updateUserPasswd(params);
			service.commit();
			
		} catch (Exception e) {
			pwdResult = "FAIL";
			accesslog_msg += "&result=사용자 계정 비밀번호 변경 실패";
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
//### 사용자 계정관리 조회 - 초기화 가능 START ###
//////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 사용자 계정관리 조회 : 초기화 가능한 list 화면 조회
	 * @return	
	 */
	@RequestMapping(value="/base/user_list_init.ad")
	public String selectUserInitList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### AdminUserController selectUserInitList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdUser> list = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
		Map<String, String> result_map = null;
		List<Map<String,String>> result_map_list = new ArrayList<Map<String,String>>();
		
		String login_Id = checkStr(getSessionMgrId(request), "");			//로그인id
		String accesslog_part = checkStr(getSessionPart(request), "");
		String accesslog_msg = "";
		
		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
		try {
			
			params.put("view", "list");
			list = service.selectUserList(params);

			//암호화된 정보 decoding하여 새로 담기
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
			accesslog_msg += "&des=사용자 계정 목록 조회";
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
// ### 최초 관리자 계정 등록 START ###
//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 관리자 게정 등록 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/admin_reg.in")
	public String adminReg(HttpServletRequest request, HttpServletResponse response) {
		return "base/admin_reg";
	}
	
	/**
	 * 관리자 계정 등록 insert
	 * @param 	
	 */
	@RequestMapping(value="/base/admin_reg.in", method=RequestMethod.POST)
	public String insertAdminReg(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserController insertAdminReg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> accesslog_params = new HashMap<String,Object>();
	
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");	//id
		String passwd = checkStr2(request.getParameter("passwd"), "");	//비밀번호
		String name = checkStr(request.getParameter("name"), "");			//이름
		String part = checkStr(request.getParameter("part"), "");			//소속
		String email = checkStr(request.getParameter("email"), "");		//email
		String phone = checkStr(request.getParameter("phone"), "");		//phone
		String lev = checkStr(request.getParameter("lev"), "");				//lev
		String accesslog_msg = "";

		//한글깨짐현상
		try {
			name = URLDecoder.decode(name, "UTF-8");
			part = URLDecoder.decode(part, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
		try {
			
			//암호화
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
			accesslog_msg += "&등록된id:"+mgr_id;
			accesslog_msg += "&des=관리자 계정 등록";
			
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
	 * 관리자 계정 id 유무 체크
	 * @return	
	 */
	@RequestMapping(value="/base/admin_id_check.in")
	public String selectAdminIdCheck(HttpServletRequest request, HttpServletResponse response) {

		log.debug("### UserController selectAdminIdCheck START ###");
		
		MwAdUser mwAdUser = null;
		MwAdUserService service = new MwAdUserService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		String userIdYn = "Y";	//Y:사용가능, N:사용 불가능
		
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
// ### 최초 관리자 계정 등록 END ###
//////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
