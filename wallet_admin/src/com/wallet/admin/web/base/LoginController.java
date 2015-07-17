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

	private final String SSEEION_MGR_ID = "SSEEION_MGR_ID";				//사용자 계정
	private final String SSEEION_NAME = "SSEEION_NAME";						//이름
	private final String SSEEION_PART = "SSEEION_PART";						//소속
	private final String SSEEION_PHONE = "SSEEION_PHONE";					//연락처
	private final String SSEEION_EMAIL = "SSEEION_EMAIL";					//EMAIL
	private final String SESSION_RG_DAY = "SESSION_RG_DAY";				//등록일
	private final String SSEEION_CH_USID = "SSEEION_CH_USID";			//변경자 ID
	private final String SSEEION_CH_DAY = "SSEEION_CH_DAY";				//변경일
	private final String SSEEION_STAT = "SSEEION_STAT";						//상태(사용중,중지)
	private final String SSEEION_RETRY_CNT = "SSEEION_RETRY_CNT";	//비밀번호 오류 횟수
	private final String SSEEION_LEV = "SSEEION_LEV";							//레벨
	private final String LOGIN_DATE = "LOGIN_DATE";							//마지막 로그인 시간
	
	/**
	 * 로그인 화면 이동, 로그아웃 처리
	 * - 로그인 패이지 이동시 session 끊김
	 * @return	
	 */
	@RequestMapping(value="/base/login.in")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		
		//ip가 같을경우 Y, 다를경우 N
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
	 * 로그인 화면 이동, 로그아웃 처리
	 * - 로그인 패이지 이동시 session 끊김
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
	 * SMS 인증
	 * - 인증시 id,pw,비밀번호 횟수 체크
	 * - id,pw,핸드폰 번호가 일치하면 sms발송
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

		String smsYn = "N";			//sms발송 성공여부
		String sms_result = "";	//sms전송 결과
		String result = "Y";		//id 조회결과, 데이터가 없을경우, N

		String accesslog_part = "";
		String accesslog_msg = "";

		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
		try {
			
			phone = dbCipher.encoding(phone);
			
			params.put("mgr_id", mgr_id);
			params.put("phone", phone);
			params.put("top", 1);
			
			log.debug("### params : " + params);
		
			//accessLog
			accesslog_msg = "method=/base/login_sms_check.in";
			accesslog_msg += "&des=SMS인증체크";
			accesslog_msg += "&결과=";		//accesslog
			
			mwAdLogin = service.selectLoginIdCheck(params);
			
			log.debug("### mwAdLogin : " + mwAdLogin);
			log.debug("### phone : " + phone);
			log.debug("### mwAdLogin.getPhone() : " + mwAdLogin.getPhone());
			
			if((mwAdLogin != null) && (phone.equals(mwAdLogin.getPhone())) ) {

				 //SMS 인증번호 호출
				 sms_result = UrlConnection.connect(sms_url + "?phone=" + dbCipher.decoding(mwAdLogin.getPhone()));
				 
				 //인증성공시
				 if("OK".equals(sms_result)) {
					 smsYn = "Y";
					 accesslog_msg += "SMS인증성공:SMS발송";		//accesslog
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
	 * 로그인 ID/PW 체크
	 * - 인증번호 체크, 체크 성공시 삭제
	 * - 비밀번호 3회 오류 체크
	 * - 비밀번호 3회 미만 오류시, 로그인 성공시 retry_cnt 초기화
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
		int pwdEndDay = 100;	//비밀번호 종료일자 default 100일
		
		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
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
			accesslog_msg += "&des=로그인 ID/PW 체크";
			accesslog_msg += "&결과=";
			
			mwAdSms = service.selectSmsCheck(sms_params);
			
		if(mwAdSms != null) {
			
			if(dbCipher.decoding(phone).equals(mwAdSms.getPhone())) {
				
				mwAdLogin = service.selectLoginIdCheck(params);
				
				 if(mwAdLogin != null) {
					 
					 accesslog_part = mwAdLogin.getPart();	//accesslog

					//비밀번호 만료일자
					pwdEndDay = Integer.parseInt(mwAdLogin.getPwdEndDay());
						
						if(mgr_id.equals(mwAdLogin.getMgrId()) && "R".equals(mwAdLogin.getStat())
								&& phone.equals(mwAdLogin.getPhone())	&& pwdEndDay >= 0) {

							//비밀번호 3회 오류 체크
							if(3 > mwAdLogin.getRetryCnt()) {
								if(passwd.equals(mwAdLogin.getPasswd())) {
									
									//로그인 성공시 세션 정보 담기
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
									//로그인 성공시 비밀번호 retry_cnt 초기화,인증번호 삭제
									params.put("retry_cnt", 0);
									params.put("ipaddress", session.getId());
									service.updateRetryCnt(params);	//비밀번호 오류횟수 초기화
									service.deleteSms(params);			//인증번호 삭제
									
									accesslog_msg += "로그인성공";
									
								} else {
									params.put("retry_cnt", mwAdLogin.getRetryCnt() + 1);
									service.updateRetryCnt(params);
									accesslog_msg += "로그인실패:비밀번호오류";
								}
							} else {
								pwThreeFail = "Y";
								accesslog_msg += "로그인실패:비밀번호3회오류";
							}

						} else {
							if(mgr_id.equals(mwAdLogin.getMgrId()) && !"R".equals(mwAdLogin.getStat())) {
								loginStat = "F";
								accesslog_msg += "로그인실패:사용중지중";
							}
							if(!phone.equals(mwAdLogin.getPhone())) {
								 phoneStat = "F"; 
								 accesslog_msg += "로그인실패:휴대폰번호없음";
							 }
							if(session != null || ((String) session.getAttribute(SSEEION_MGR_ID)).length() > 0) {
								 session.removeAttribute(SSEEION_MGR_ID);
								 accesslog_msg += "로그인실패:세션없음";
							 } 
							
						}
						
				 } else {
					 accesslog_msg += "로그인실패:id조회결과없음";
				 }
				
			} else {
				smsPhoneStat = "F";
				accesslog_msg += "로그인실패:SMS휴대폰번호없음";
			}

			
		} else {
			smsResult = "F";
			accesslog_msg += "로그인실패:SMS인증번호없음";
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
	 * 개인정보 취급방안 호출
	 * @return	
	 */
	@RequestMapping(value="/base/privacy.in")
	public String selectAppVersionList(HttpServletRequest request, HttpServletResponse response) {
		return "/base/privacy";

	}
	
	/**
	 * 메인 화면 이동 (빈화면)
	 * @return	
	 */
	@RequestMapping(value="/base/main_index.ad")
	public String mainIndex(HttpServletRequest request, HttpServletResponse response) {
		 return "/base/main_index";
	}
	
}
