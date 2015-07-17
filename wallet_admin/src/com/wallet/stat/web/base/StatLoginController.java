package com.wallet.stat.web.base;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.admin.model.MwAdLogin;
import com.wallet.admin.service.MwAdLoginService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.cipher.Sha256Cipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.web.CommonController;

/*
 * Filename	: LoginController.java
 * Class	: com.wallet.stat.web.base.LoginController
 * History	: 2012/08/23, psj, 작업구분 : 로그인
 * Comment	:
 */
@Controller
public class StatLoginController extends CommonController {
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
	
	
	/**
	 * 로그인 화면 이동, 로그아웃 처리
	 * - 로그인 패이지 이동시 session 끊김
	 * @return	
	 */
	@RequestMapping(value="/stat/login.in")
	public String login(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
			
		if (session == null || session.getAttribute(SSEEION_MGR_ID) == null 
				|| ((String) session.getAttribute(SSEEION_MGR_ID)).length() == 0) {
		} else {
			session.removeAttribute(SSEEION_MGR_ID);
		}
		 
		 return "/stat/login";
	}
		
	
	/**
	 * 로그인 ID/PW 체크
	 * - 비밀번호 3회 오류 체크
	 * - 비밀번호 3회 미만 오류시, 로그인 성공시 retry_cnt 초기화
	 * @return	
	 */
	@SuppressWarnings("null")
	@RequestMapping(value="/stat/login_check.in")
	public String loginCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(false);
		MwAdLogin mwAdLogin = null;
		MwAdLoginService service = new MwAdLoginService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String mgr_id = checkStr(request.getParameter("mgr_id"), "");		//id 
		String passwd = checkStr(request.getParameter("passwd"), "");		//pw
		
		String loginResult = "FAIL";
		String loginStat = "R";
		String returnUrl = "/stat/login.in";
		String pwThreeFail = "N";
				

		/* 암호화 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
		try {

				passwd = Sha256Cipher.encryptSHA256(passwd);
				
				params.put("mgr_id", mgr_id);
				params.put("passwd", passwd);
				params.put("top", 1);
	
				mwAdLogin = service.selectLoginIdCheck(params);
				
			 if(mwAdLogin != null) {
				 
					if(mgr_id.equals(mwAdLogin.getMgrId()) && "R".equals(mwAdLogin.getStat()) ) {
	
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
	
								loginResult = "SUCCESS";
								returnUrl = "/stat/member_stats_acm_list.st";
								
								//로그인 성공시 비밀번호 retry_cnt 초기화,인증번호 삭제
								params.put("retry_cnt", 0);
								service.updateRetryCnt(params);	//비밀번호 초기화
								
							} else {
								params.put("retry_cnt", mwAdLogin.getRetryCnt() + 1);
								service.updateRetryCnt(params);
							}
						} else {
							pwThreeFail = "Y";
						}
						
	
					} else {
						if(mgr_id.equals(mwAdLogin.getMgrId()) && !"R".equals(mwAdLogin.getStat())) {
							loginStat = "F";
						}
						if(session != null || ((String) session.getAttribute(SSEEION_MGR_ID)).length() > 0) {
							 session.removeAttribute(SSEEION_MGR_ID);
						 } 
						
					}
					
			 }
			
			 service.commit();
	
		} catch (Exception e) {
			service.rollback();
			e.printStackTrace();
		}

		JSONObject jObj = new JSONObject();
		jObj.put("loginResult", loginResult);
		jObj.put("returnUrl", returnUrl);
		jObj.put("loginStat", loginStat);
		jObj.put("pwThreeFail", pwThreeFail);

		request.setAttribute("JSONObject", jObj);
		return "/common/result_page";
				
	}
	
	
}
