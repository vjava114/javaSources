/**
 * 
 */
package com.wallet.membership.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.custom.SalesAgency;
import com.wallet.membership.service.custom.SalesAgencyService;
import com.wallet.admin.service.MwAdAccessLogService;

@Controller
public class SalesAgencyController extends CommonController {
	private final String PAGE_CODE = "SALESAGENCY_LIST";
	private Logger log = Log.getLogger("logs");
	private MwAdAccessLogService logSVC = new MwAdAccessLogService();
	KTDBCipher ktService = new KTDBCipher();
	/**
	 * @Method Name : SalesAgencyList
	 * @Description : 영업대행사 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.11
	 */
	@RequestMapping(value="/member/sales_agency_list.ms")
	public String SalesAgencyList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SalesAgency> salesAgencyList = null;
		SalesAgencyService salesAgencyService = new SalesAgencyService();
		//HashMap<String, Object> params = new HashMap<String, Object>((Map) request.getParameterMap());
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_allyStat", checkStr(request.getParameter("ra_allyStat"), "")); //-- 운영상태에 대한 기본값 설정
		params.put("sagCompName", checkStr(request.getParameter("sagCompName"), "")); //-- 선택된 결제사가 결제사명
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- 기간설정에 대한 기준값 설정(등록일/수정일). 
		
		params.put("sagCompId", checkStr(request.getParameter("sagCompId"), ""));
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int salesAgencyListCnt = salesAgencyService.selectSalesAgencyListCnt(params); //-- 총 목록 수
		Paging page = new Paging();
		page.makeWebPaging(nowPage, salesAgencyListCnt, rowsPerPage);
		
		if(page.getNowPage()>1){
			nowPage =  page.getNowPage();
			request.setAttribute("nowPage", nowPage);
			
		}else{
			nowPage = 1;
			request.setAttribute("nowPage", "1");
		}
		
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;

		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		log.debug("@@@@@@@@@@ SalesAgencyList params : "+ params); //##
		salesAgencyList = salesAgencyService.selectSalesAgencyList(params); //-- 목록조회
		
		/*##################### 복호화 S #####################*/
		for(int i=0; i<salesAgencyList.size(); i++){
			String manageNm = salesAgencyList.get(i).getManagerName();
			String phoneNo = salesAgencyList.get(i).getPhoneNo();
			manageNm = ktService.decoding(manageNm);
			phoneNo = ktService.decoding(phoneNo);
			salesAgencyList.get(i).setManagerName(manageNm);
			salesAgencyList.get(i).setPhoneNo(phoneNo);
		}
		/*##################### 복호화 E #####################*/
		
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("salesAgencyList", salesAgencyList);
		request.setAttribute("sagCompName", params.get("sagCompName"));
		request.setAttribute("ra_allyStat", params.get("ra_allyStat"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));

		
		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/sales_agency_list.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		
		params.clear();
		return "member/sales_agency_list";
	}
	
	
	/**
	 * @Method Name : SalesAgencyRegister
	 * @Description : 영업대행사 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.11
	 */
	@RequestMapping(value="/member/sales_agency_register.ms")
	public String SalesAgencyRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "member/sales_agency_register";
	}
	

	
	/**
	 * @Method Name : SalesAgencyRegisterAct
	 * @Description : 영업대행사 등록 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.11
	 */
	@RequestMapping(value="/member/sales_agency_registerAct.ms")
	public String SalesAgencyRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		
		try{
			SalesAgencyService salesAgencyService = new SalesAgencyService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			String regUser = getSessionMgrId(request);
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("regUser", regUser); //-- 등록자ID
			params.put("sagCompName",  checkStr(request.getParameter("sagCompName"), ""));
			params.put("businessNo",  checkStr(request.getParameter("businessNo"), ""));
			params.put("zipcode",  checkStr(request.getParameter("zipcode"), ""));
			params.put("addr",  checkStr(request.getParameter("addr"), ""));
			params.put("addrDetail",  checkStr(request.getParameter("addrDetail"), ""));
//			params.put("mainNumber",  checkStr(request.getParameter("mainNumber"), ""));
//			params.put("managerName",  checkStr(request.getParameter("managerName"), ""));
//			params.put("phoneNo",  checkStr(request.getParameter("phoneNo"), ""));
//			params.put("mobilePhone",  checkStr(request.getParameter("mobilePhone"), ""));
//			params.put("email",  checkStr(request.getParameter("email"), ""));
			params.put("limitAmountDay",  checkStr(request.getParameter("limitAmountDay"), ""));
			params.put("limitAmountWeek",  checkStr(request.getParameter("limitAmountWeek"), ""));
			params.put("limitAmountMonth",  checkStr(request.getParameter("limitAmountMonth"), ""));
			params.put("limitAmountYear",  checkStr(request.getParameter("limitAmountYear"), ""));
			params.put("allyStat",  checkStr(request.getParameter("allyStat"), ""));
			params.put("memo",  checkStr(request.getParameter("memo"), ""));
			
			/*##################### 암호화 S #####################*/
			params.put("mainNumber", ktService.encoding(checkStr(request.getParameter("mainNumber"), "")));
			params.put("managerName",  ktService.encoding(checkStr(request.getParameter("managerName"), "")));
			params.put("phoneNo", ktService.encoding(checkStr(request.getParameter("phoneNo"), "")));
			params.put("mobilePhone", ktService.encoding(checkStr(request.getParameter("mobilePhone"), "")));
			params.put("email", ktService.encoding(checkStr(request.getParameter("email"), "")));
			/*##################### 암호화 E #####################*/
			
			log.debug("@@@@@@@@@@ SalesAgencyRegisterAct params : "+ params); //##
			
			result = salesAgencyService.insertSalesAgency(params);
			salesAgencyService.commit();
			
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/sales_agency_register.ms");
			}
			else{
				request.setAttribute("targetUrl", "/member/sales_agency_list.ms");
			}

			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/sales_agency_registerAct.ms");
		
			params.put("result", result);
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
			params.clear();
		}
		catch(Exception e){
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/sales_agency_register.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : SalesAgencyEditor
	 * @Description : 영업대행사 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.12
	 */
	@RequestMapping(value="/member/sales_agency_editor.ms")
	public String SalesAgencyEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
	
		SalesAgencyService salesAgencyService = new SalesAgencyService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("sltdsagCompId", checkStr(request.getParameter("sltdsagCompId"), "")); //--선택된 영업대행사 ID

		log.debug("@@@@@@@@@@ SalesAgencyEditor params : "+ params); //##
		SalesAgency aSalesAgency = salesAgencyService.selectASalesAgency(params);	//-- 목록조회
		
		/*##################### 복호화 S #####################*/
		String mainNum = ktService.decoding(aSalesAgency.getMainNumber());
		String manageNm = ktService.decoding(aSalesAgency.getManagerName());
		String phone = ktService.decoding(aSalesAgency.getPhoneNo());
		String mobilePhone = ktService.decoding(aSalesAgency.getMobilePhone());
		String email = ktService.decoding(aSalesAgency.getEmail());
		
		aSalesAgency.setMainNumber(mainNum);
		aSalesAgency.setManagerName(manageNm);
		aSalesAgency.setPhoneNo(phone);
		aSalesAgency.setMobilePhone(mobilePhone);
		aSalesAgency.setEmail(email);
		/*##################### 복호화 E #####################*/
		
		request.setAttribute("aSalesAgency", aSalesAgency);
		
		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/sales_agency_editor.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		
		params.clear();
		return "member/sales_agency_editor";
	}
	
	
	/**
	 * @Method Name : SalesAgencyEditorAct
	 * @Description : 결제사를 수정 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.12
	 */
	@RequestMapping(value="/member/sales_agency_editorAct.ms")
	public String SalesAgencyEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		
		try{
			SalesAgencyService salesAgencyService = new SalesAgencyService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			String chgUser = getSessionMgrId(request);
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("chgUser", chgUser); //-- 수정자ID
			params.put("sagCompId",  checkStr(request.getParameter("sagCompId"), ""));
			params.put("sagCompName",  checkStr(request.getParameter("sagCompName"), ""));
			params.put("businessNo",  checkStr(request.getParameter("businessNo"), ""));
			params.put("zipcode",  checkStr(request.getParameter("zipcode"), ""));
			params.put("addr",  checkStr(request.getParameter("addr"), ""));
			params.put("addrDetail",  checkStr(request.getParameter("addrDetail"), ""));
//			params.put("mainNumber",  checkStr(request.getParameter("mainNumber"), ""));
//			params.put("managerName",  checkStr(request.getParameter("managerName"), ""));
//			params.put("phoneNo",  checkStr(request.getParameter("phoneNo"), ""));
//			params.put("mobilePhone",  checkStr(request.getParameter("mobilePhone"), ""));
//			params.put("email",  checkStr(request.getParameter("email"), ""));
			params.put("allyStat",  checkStr(request.getParameter("allyStat"), ""));
			params.put("limitAmountDay",  checkStr(request.getParameter("limitAmountDay"), ""));
			params.put("limitAmountWeek",  checkStr(request.getParameter("limitAmountWeek"), ""));
			params.put("limitAmountMonth",  checkStr(request.getParameter("limitAmountMonth"), ""));
			params.put("limitAmountYear",  checkStr(request.getParameter("limitAmountYear"), ""));
			params.put("memo",  checkStr(request.getParameter("memo"), ""));

			
			/*##################### 암호화 S #####################*/
			params.put("mainNumber",  ktService.encoding(checkStr(request.getParameter("mainNumber"), "")));
			params.put("managerName",  ktService.encoding(checkStr(request.getParameter("managerName"), "")));
			params.put("phoneNo",  ktService.encoding(checkStr(request.getParameter("phoneNo"), "")));
			params.put("mobilePhone",  ktService.encoding(checkStr(request.getParameter("mobilePhone"), "")));
			params.put("email",  ktService.encoding(checkStr(request.getParameter("email"), "")));
			/*##################### 암호화 E #####################*/
			
			log.debug("@@@@@@@@@@ SalesAgencyEditorAct params : "+ params); //##
			
			result = salesAgencyService.updateSalesAgency(params);
			salesAgencyService.commit();
			request.setAttribute("actResult", result + "");
			
			
			if(result == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/sales_agency_editor.ms");
			}
			else{
				request.setAttribute("targetUrl", "/member/sales_agency_list.ms");
			}

			
			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/sales_agency_editorAct.ms");
		
			params.put("result", result);
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		}
		catch(Exception e){
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/sales_agency_editor.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : SalesAgencyDeleteAct
	 * @Description : 영업대행사 삭제한다.(실제삭제)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김완섭
	 * @since 2012.09.12
	 */
	@RequestMapping(value="/member/sales_agency_deleteAct.ms")
	public String SalesAgencyDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		
		try{
			SalesAgencyService salesAgencyService = new SalesAgencyService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("sagCompId",  checkStr(request.getParameter("sagCompId"), ""));

			log.debug("@@@@@@@@@@ SalesAgencyDeleteAct params : "+ params); //##
			
			result = salesAgencyService.deleteSalesAgency(params);
			salesAgencyService.commit();
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 삭제 실패이면,
				request.setAttribute("targetUrl", "/member/sales_agency_editor.ms");
			}
			else{
				request.setAttribute("targetUrl", "/member/sales_agency_list.ms");
			}

			
			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/sales_agency_deleteAct.ms");
			
			params.put("result", result);
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		}
		catch(Exception e){
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/sales_agency_editor.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
		
	/**
	 * @Method Name : today
	 * @Description : 오늘 날짜를 조회한다.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2010.04.23
	 */
	public static String today() {
		java.sql.Date CurrDate = new java.sql.Date((new java.util.Date()).getTime());
		return CurrDate.toString();
	}
}
