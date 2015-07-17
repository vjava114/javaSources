package com.wallet.admin.web.base;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.model.MwAdPayment;
import com.wallet.admin.service.MwAdPaymentService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

/*
 * Filename	: PaymentController.java
 * Class	: com.wallet.admin.web.base.PaymentController
 * History	: 2012/08/23, psj, 작업구분 : 결제서비스 관리 > 결재
 * Comment	:
 */
@Controller
public class PaymentController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 신규 결제 서비스 등록 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/payment_reg.ad")
	public String paymentReg(HttpServletRequest request, HttpServletResponse response) {
		return "base/payment_reg";
	}
	
	/**
	 * 신규 결제 서비스 정보등록

	 * @return	
	 */
	@RequestMapping(value="/base/payment_reg.ad", method=RequestMethod.POST)
	public String insertPaymentReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### PaymentController insertPaymentReg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdPayment> list = null;
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		List<Map<String,String>> clause_list = new ArrayList<Map<String,String>>();
		
		//파일명 생성 : 년월일시분초_i
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	  String date = formatter.format(today);
		
	  String img_host = PropertiesUtil.get("img_host");
	  String use_clause_path = PropertiesUtil.get("pay_memb_agree_url");
	  String use_clause_info = "";			//이용약관 TB info 항목에 저장될 전체 경로
	  String use_clause_file_path = "";	//서버에 저장될 경로 (경로 + 파일명)

		String pay_type = checkStr(request.getParameter("pay_type"), "");		//서비스구분
		String name = checkStr(request.getParameter("name"), "");						//결제서비스명
		String memb_id = checkStr(request.getParameter("memb_id"), "");		//결제서비스id
		String stat = checkStr(request.getParameter("stat"), "");						//제휴상태
		String company_nm = checkStr(request.getParameter("company_nm"), "");	//제휴사명
		String sday = checkStr(request.getParameter("sday"), "");						//게재기간 - 시작일
		String eday = checkStr(request.getParameter("eday"), "");						//게재기간 - 종료일
		String os = checkStr(request.getParameter("os"), "");								//제공os
		String link_mode = checkStr(request.getParameter("link_mode"), "");	//제공방식
		String info = checkStr(request.getParameter("info"), "");						//서비스url
		String market = checkStr(request.getParameter("market"), "");						//서비스url
		String olleh_id = checkStr(request.getParameter("olleh_id"), "");					//app id
		String olleh_pkg = checkStr(request.getParameter("olleh_pkg"), "");				//package명
		String olleh_class = checkStr(request.getParameter("olleh_class"), "");		//실행 class명
		String olleh_down = checkStr(request.getParameter("olleh_down"), "");			//다운로드 url
		String google_pkg = checkStr(request.getParameter("google_pkg"), "");			//package명
		String google_class = checkStr(request.getParameter("google_class"), "");	//실행 class명
		String google_down = checkStr(request.getParameter("google_down"), "");		//다운로드 url
		String apple_id = checkStr(request.getParameter("apple_id"), "");					//app id
		String apple_url = checkStr(request.getParameter("apple_url"), "");				//Custom url
		String apple_down = checkStr(request.getParameter("apple_down"), "");			//다운로드 url
		String[] title = request.getParameterValues("title");											//이용약관 tile
		String[] use_clause = request.getParameterValues("use_clause");						//이용약관 내용등록
		String[] chk = request.getParameterValues("chk");													//이용약관 필수/선택
		String[] menu_nm = request.getParameterValues("menu_nm");									//sub메뉴 - 메뉴명
		String[] url = request.getParameterValues("url");													//sub메뉴 - url
		String mgr_id = checkStr(getSessionMgrId(request), "");						//로그인id
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//리스트,카드 이미지(아이폰4,아이폰3GS,만드로이드)
		
		try {
			
			params.put("memb_id",memb_id);
			params.put("pay_type",pay_type);
			params.put("name",name);
			params.put("stat",stat);
			params.put("company_nm",company_nm);
			params.put("dis_sday",sday);
			params.put("dis_eday",eday);
			params.put("os",os);
			params.put("link_mode",link_mode);
			params.put("info",info);
			params.put("market",market);
			params.put("olleh_id",olleh_id);
			params.put("olleh_pkg",olleh_pkg);
			params.put("olleh_class",olleh_class);
			params.put("olleh_down",olleh_down);
			params.put("google_pkg",google_pkg);
			params.put("google_class",google_class);
			params.put("google_down",google_down);
			params.put("apple_id",apple_id);
			params.put("apple_url",apple_url);
			params.put("apple_down",apple_down);
			params.put("display_yn","Y");
			params.put("img_host",img_host);
			params.put("reg_user",mgr_id);//등록자id(로그인id)
			
			if ("W".equals(params.get("link_mode"))) {
				if(title != null) {
					//이용약관 테이블에 저장할 정보
					for(int i=0; i <title.length; i++){
						Map<String, String> resultReturn = new HashMap<String, String>();
						use_clause_file_path = "";
						use_clause_file_path = use_clause_path + date+ "_" + (i+1) + ".html" ;	//파일명 생성 YYYYMMDDHHSS_i.html
						use_clause_info = img_host + use_clause_file_path ;	//파일명 생성 YYYYMMDDHHSS_i.html
						
						resultReturn.put("memb_id", memb_id);
						resultReturn.put("idx", String.valueOf(i+1));
						resultReturn.put("title", title[i]);
						resultReturn.put("use_clause", use_clause[i]);
						resultReturn.put("info", use_clause_info);
						resultReturn.put("use_clause_file_path", use_clause_file_path);
						resultReturn.put("chk", chk[i]);
						clause_list.add(resultReturn);
					}
				}
			}

			service.insertPaymentReg(params, clause_list, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		params.clear();
		
		//list화면 이동
		response.sendRedirect("/base/payment_list.ad");

    log.debug("### PaymentController insertPaymentReg END ###");
    
		return "base/payment_list";
	}
	
	/**
	 * 신규 결제 서비스 조회 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/payment_list.ad")
	public String selectPaymentList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PaymentController selectPaymentList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdPayment> list = null;
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("view", "list");
		list = service.selectPaymentList(params);
		
		request.setAttribute("mwAdPaymentList", list);
		
		log.debug("### PaymentController selectPaymentList END ###");
		
		return "base/payment_list";
	}
	
	/**
	 *  신규 결제 서비스  상세 조회
	 * @return	
	 */
	@RequestMapping(value="/base/payment_list_dtl.ad")
	public String selectPaymentListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PaymentController selectPaymentListDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdPayment mwAdPayment = null;
		List<Map<String,String>> useClauseList = null;
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String memb_id = checkStr(request.getParameter("memb_id"), "");			//idx
		
		params.put("view", "dtl");
		params.put("memb_id", memb_id);
		params.put("top", 1);
		
		mwAdPayment = service.selectPaymentListDtl(params);
		useClauseList = service.selectUseClauseList(params);
		
		request.setAttribute("mwAdPayment", mwAdPayment);
		request.setAttribute("useClauseList", useClauseList);
		request.setAttribute("useClauseCount", useClauseList.size());
		
		log.debug("### PaymentController selectPaymentListDtl END ###");
		
		return "base/payment_list_dtl";
	}
	
	/**
	 * 결제 서비스 정보 수정
	 * @param 
	
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/payment_dtl_update.ad", method=RequestMethod.POST)
	public String updatePaymentDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### PaymentController updatePaymentDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdPayment> list = null;
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		List<Map<String,String>> clause_list = new ArrayList<Map<String,String>>();
		
		//파일명 생성 : 년월일시분초_i
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	  String date = formatter.format(today);
		
	  String img_host = PropertiesUtil.get("img_host");
	  String use_clause_path = PropertiesUtil.get("pay_memb_agree_url");
	  String use_clause_info = "";			//이용약관 TB info 항목에 저장될 전체 경로
	  String use_clause_file_path = "";	//서버에 저장될 경로 (경로 + 파일명)

		String pay_type = checkStr(request.getParameter("pay_type"), "");		//서비스구분
		String name = checkStr(request.getParameter("name"), "");						//결제서비스명
		String memb_id = checkStr(request.getParameter("memb_id"), "");		//결제서비스id
		String stat = checkStr(request.getParameter("stat"), "");						//제휴상태
		String company_nm = checkStr(request.getParameter("company_nm"), "");	//제휴사명
		String sday = checkStr(request.getParameter("sday"), "");						//게재기간 - 시작일
		String eday = checkStr(request.getParameter("eday"), "");						//게재기간 - 종료일
		String os = checkStr(request.getParameter("os"), "");								//제공os
		String link_mode = checkStr(request.getParameter("link_mode"), "");	//제공방식
		String info = checkStr(request.getParameter("info"), "");						//서비스url
		String market = checkStr(request.getParameter("market"), "");						//서비스url
		String olleh_id = checkStr(request.getParameter("olleh_id"), "");					//app id
		String olleh_pkg = checkStr(request.getParameter("olleh_pkg"), "");				//package명
		String olleh_class = checkStr(request.getParameter("olleh_class"), "");		//실행 class명
		String olleh_down = checkStr(request.getParameter("olleh_down"), "");			//다운로드 url
		String google_pkg = checkStr(request.getParameter("google_pkg"), "");			//package명
		String google_class = checkStr(request.getParameter("google_class"), "");	//실행 class명
		String google_down = checkStr(request.getParameter("google_down"), "");		//다운로드 url
		String apple_id = checkStr(request.getParameter("apple_id"), "");					//app id
		String apple_url = checkStr(request.getParameter("apple_url"), "");				//Custom url
		String apple_down = checkStr(request.getParameter("apple_down"), "");			//다운로드 url
		String[] title = request.getParameterValues("title");											//이용약관 tile
		String[] use_clause = request.getParameterValues("use_clause");		//이용약관 내용등록
		String[] chk = request.getParameterValues("chk");		//이용약관 내용등록
		String[] use_clause_text_hidden = request.getParameterValues("use_clause_text_hidden");		//이용약관 내용등록
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//리스트,카드 이미지(아이폰4,아이폰3GS,만드로이드)

		try {
			
			params.put("memb_id",memb_id);
			params.put("pay_type",pay_type);
			params.put("name",name);
			params.put("stat",stat);
			params.put("company_nm",company_nm);
			params.put("dis_sday",sday);
			params.put("dis_eday",eday);
			params.put("os",os);
			params.put("link_mode",link_mode);
			params.put("info",info);
			params.put("market",market);
			params.put("olleh_id",olleh_id);
			params.put("olleh_pkg",olleh_pkg);
			params.put("olleh_class",olleh_class);
			params.put("olleh_down",olleh_down);
			params.put("google_pkg",google_pkg);
			params.put("google_class",google_class);
			params.put("google_down",google_down);
			params.put("apple_id",apple_id);
			params.put("apple_url",apple_url);
			params.put("apple_down",apple_down);
			params.put("display_yn","Y");
			params.put("img_host",img_host);
			
			if ("W".equals(params.get("link_mode"))) {
				if(title != null) {
				//이용약관 테이블에 저장할 정보
					for(int i=0; i <title.length; i++){
						Map<String, String> resultReturn = new HashMap<String, String>();
						
						use_clause_file_path = "";
						use_clause_file_path = use_clause_path + date+ "_" + (i+1) + ".html" ;	//경로+파일명 생성 YYYYMMDDHHSS_i.html
						use_clause_info = img_host + use_clause_file_path ;	//http://211.216.47.161 + 경로+파일명 생성 YYYYMMDDHHSS_i.html
						
						resultReturn.put("memb_id", memb_id);
						resultReturn.put("idx", String.valueOf(i+1));
						resultReturn.put("title", title[i]);
						resultReturn.put("use_clause", use_clause[i]);
						resultReturn.put("info", use_clause_info);
						resultReturn.put("use_clause_file_path", use_clause_file_path);
						resultReturn.put("chk", chk[i]);
						clause_list.add(resultReturn);
						
					}
				}
				 
			}

			service.updatePaymentDtl(params, clause_list, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		response.sendRedirect("/base/payment_list.ad");
		
		log.debug("### CardController updateCardDtl END ###");
		
		return "base/card_list";
		
	}
	
	/**
	 * 결제 정보 삭제
	 * @param memb_id			memb_id
	 * @param pay_code		pay_code
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/payment_dtl_delete.ad", method=RequestMethod.POST)
	public String deletePaymentDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### PaymentController deletePaymentDtl START ###");

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String memb_id = checkStr(request.getParameter("memb_id"), "");		//memb_id
		String main_idx = checkStr(request.getParameter("main_idx"), "");		//main_idx
		
		try {
			
			params.put("memb_id", memb_id);
			params.put("main_idx", main_idx);
			params.put("idxGb", "delete");	//삭제시 순위조정 변경 구분값
			
			//memb_id 없을경우 강제 Exception 발생
			if("".equals(memb_id)) {
				throw new Exception("memb_id가 없습니다. 강제 Exception 발생");
			}

			service.deletePaymentDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### PaymentController deletePaymentDtl END ###");
		return null;
		
	}
	
	/**
	 * 결제관리 순서 조정
	 * @param memb_id		memb_id
	 * @param thisIdx		순서를 변경할  범위 idx
	 * @param targetidx 순서를 변경할  범위 idx
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/payment_idx_update.ad", method=RequestMethod.POST)
	public String updatePaymentIdx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### PaymentController updatePaymentIdx START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdPaymentService service = new MwAdPaymentService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String memb_id = checkStr(request.getParameter("memb_id"), "");
		int thisIdx = Integer.parseInt(checkStr(request.getParameter("thisIdx"), "1"));
		int targetIdx = Integer.parseInt(checkStr(request.getParameter("targetIdx"), "1"));
		
		try {
			
			params.put("thisIdx", thisIdx);
			params.put("targetIdx", targetIdx);
			params.put("idxGb", "update");			//순위조정시 변경 구분값

			//순서 조정 update
			service.updatePaymentIdx(params, memb_id);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		//조회
		params.clear();
		response.sendRedirect("/base/payment_list.ad");
		
		log.debug("### PaymentController updatePaymentIdx END ###");
		
		return "base/payment_list";
	}
}
