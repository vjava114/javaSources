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
 * History	: 2012/08/23, psj, 작업구분 : 기타관리 > Push 발송
 * Comment	:
 */
@Controller
public class PushController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");

	/**
	 * Push 등록 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/push_reg.ad")
	public String pushReg(HttpServletRequest request, HttpServletResponse response) {
		return "base/push_reg";
	}
	
	/**
	 * Push 조회
	 * @return	
	 */
	@RequestMapping(value="/base/push_list.ad")
	public String selectPushList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PushController selectPushList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
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
	 * Push 상세 조회
	 * @return	
	 */
	@RequestMapping(value="/base/push_list_dtl.ad")
	public String selectPushListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PushController selectPushListDtl START ###");
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
	  String today_time = formatter.format(today);	//현재시간

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
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
	 * Push 등록 insert
	 * @param 	
	 */
	@RequestMapping(value="/base/push_reg.ad", method=RequestMethod.POST)
	public String insertPushReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### PushController insertPushReg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdPushService service = new MwAdPushService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String send_type = checkStr(request.getParameter("send_type"), "");				//발송 종류
		String title = checkStr(request.getParameter("title"), "");								//발송 제목
		String send_mode = checkStr(request.getParameter("send_mode_org"), "");		//발송 조건(전체)
		String age_checkbox = checkStr(request.getParameter("age_checkbox"), "");	//연령선택
		String[] age = request.getParameterValues("age");													//연령(10대~60대이상)
		String sex_checkbox = checkStr(request.getParameter("sex_checkbox"), "");	//남녀선택
		String sex = checkStr(request.getParameter("sex"), "");										//남녀선택
		String os_checkbox = checkStr(request.getParameter("os_checkbox"), "");		//OS선택
		String os = checkStr(request.getParameter("os"), "");											//OS선택
		String memb_checkbox = checkStr(request.getParameter("memb_checkbox"), "");	//멤버십 선택
		String memb_id = checkStr(request.getParameter("memb_id"), "");							//멤버십 id
		
		String msg = checkStr(request.getParameter("msg"), "");							//발솔내용
		String res_date = checkStr(request.getParameter("res_date"), "");		//발솔일시
		String age_add = "";
		
		try {
		
			params.put("send_type", send_type);
			params.put("title", title);
			params.put("send_mode", send_mode);
			params.put("msg", msg);
			params.put("res_date", res_date);
			params.put("stat", "R");
			
			//발송조건이 전체선택이 아닐경우
			if("P".equals(send_mode)) {
				//연령이 선택되어 있을경우
				if(("Y".equals(age_checkbox)) && (age != null)) {
					for(int i=0; i<age.length; i++) {
						age_add += age[i] + "|";
					}
					params.put("age", age_add.substring(0, age_add.lastIndexOf("|")));
				}
				
				//남녀선택 선택되어 있을경우
				if("Y".equals(sex_checkbox)) {
					params.put("sex", sex);
				}
				
				//OS선택 선택되어 있을경우
				if("Y".equals(os_checkbox)) {
					params.put("os", os);
				}
				
				//멤버십선택 선택되어 있을경우
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
    
    //list화면 이동
    response.sendRedirect("/base/push_list.ad");
    
		return "base/push_list";
	}
	
	/**
	 * Push 수정 update
	 * @param 	
	 */
	@RequestMapping(value="/base/push_dtl_update.ad", method=RequestMethod.POST)
	public String updatePushDtl(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdPushService service = new MwAdPushService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");				//발송 종류
		String send_type = checkStr(request.getParameter("send_type"), "");				//발송 종류
		String title = checkStr(request.getParameter("title"), "");								//발송 제목
		String send_mode = checkStr(request.getParameter("send_mode_org"), "");		//발송 조건(전체)
		String age_checkbox = checkStr(request.getParameter("age_checkbox"), "");	//연령선택
		String[] age = request.getParameterValues("age");													//연령(10대~60대이상)
		String sex_checkbox = checkStr(request.getParameter("sex_checkbox"), "");	//남녀선택
		String sex = checkStr(request.getParameter("sex"), "");										//남녀선택
		String os_checkbox = checkStr(request.getParameter("os_checkbox"), "");		//OS선택
		String os = checkStr(request.getParameter("os"), "");											//OS선택
		String memb_checkbox = checkStr(request.getParameter("memb_checkbox"), "");	//멤버십 선택
		String memb_id = checkStr(request.getParameter("memb_id"), "");							//멤버십 id
		
		String msg = checkStr(request.getParameter("msg"), "");							//발솔내용
		String res_date = checkStr(request.getParameter("res_date"), "");		//발솔일시
		String age_add = "";
		
		try {
			params.put("idx", idx);
			params.put("send_type", send_type);
			params.put("title", title);
			params.put("send_mode", send_mode);
			params.put("msg", msg);
			params.put("res_date", res_date);
			
			//발송조건이 전체선택이 아닐경우
			if("P".equals(send_mode)) {
				//연령이 선택되어 있을경우
				if(("Y".equals(age_checkbox)) && (age != null)) {
					for(int i=0; i<age.length; i++) {
						age_add += age[i] + "|";
					}
					params.put("age", age_add.substring(0, age_add.lastIndexOf("|")));
				}
				
				//남녀선택 선택되어 있을경우
				if("Y".equals(sex_checkbox)) {
					params.put("sex", sex);
				}
				
				//OS선택 선택되어 있을경우
				if("Y".equals(os_checkbox)) {
					params.put("os", os);
				}
				
				//멤버십선택 선택되어 있을경우
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
		
		//list화면 이동
    response.sendRedirect("/base/push_list.ad");
    
		return "base/user_list";
	}
	
	/**
	 * Push 정보 삭제
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/push_dtl_delete.ad", method=RequestMethod.POST)
	public String deletePushDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### PushController deletePushDtl START ###");

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdPushService service = new MwAdPushService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//mgr_id
		
		try {
			
			params.put("idx", idx);

			//mgr_id가 없을경우 강제 Exception 발생
			if("".equals(idx)) {
				throw new Exception("mgr_id가 없습니다. 강제 Exception 발생");
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
