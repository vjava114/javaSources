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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.custom.Finance;
import com.wallet.membership.service.custom.FinanceService;
import com.wallet.admin.service.MwAdAccessLogService;

@Controller
public class FinanceController extends CommonController {
	private final String PAGE_CODE = "FINANCE_LIST";
	private Logger log = Log.getLogger("logs");
	private MwAdAccessLogService logSVC = new MwAdAccessLogService();
	KTDBCipher ktService = new KTDBCipher();
	/**
	 * @Method Name : FinanceList
	 * @Description : 금융사 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/finance_list.ms")
	public String FinanceList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Finance> financeList = null;
		FinanceService financeService = new FinanceService();
		//HashMap<String, Object> params = new HashMap<String, Object>((Map) request.getParameterMap());
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_allyStat", checkStr(request.getParameter("ra_allyStat"), "")); //-- 운영상태에 대한 기본값 설정
		params.put("seBankName", checkStr(request.getParameter("seBankName"), "")); //-- 선택된 금융사가 금융사명
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

		params.put("bankId", checkStr(request.getParameter("bankId"), "")); //-- 선택된 금융사가 있을 때
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int financeListCnt = financeService.selectFinanceListCnt(params); //-- 총 목록 수
		Paging page = new Paging();
		page.makeWebPaging(nowPage, financeListCnt, rowsPerPage);
		
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

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
 		financeList = financeService.selectFinanceList(params); //-- 목록조회
 		
 		/*##################### 복호화 S #####################*/
 		for(int i=0; i<financeList.size(); i++){
 			String managerNm = financeList.get(i).getManagerName();
 			String phone = financeList.get(i).getPhoneNo();
 			managerNm = ktService.decoding(managerNm);
 			phone = ktService.decoding(phone);
 			financeList.get(i).setManagerName(managerNm);
 			financeList.get(i).setPhoneNo(phone);
 		}
 		/*##################### 복호화 E #####################*/
 		
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("financeList", financeList);
		request.setAttribute("seBankName", params.get("seBankName"));
		request.setAttribute("ra_allyStat", params.get("ra_allyStat"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		
		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/finance_list.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		
		params.clear();
		return "member/finance_list";
	}
	
	
	/**
	 * @Method Name : FinanceRegister
	 * @Description : 금융사 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/finance_register.ms")
	public String FinanceRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "member/finance_register";
	}
	

	
	/**
	 * @Method Name : FinanceRegisterAct
	 * @Description : 금융사를 등록 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.06
	 */
	@RequestMapping(value="/member/finance_registerAct.ms")
	public String FinanceRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		
		try{
			FinanceService financeService = new FinanceService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			String regUser = getSessionMgrId(request);
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("regUser", regUser); //-- 등록자ID
			params.put("name",  checkStr(request.getParameter("name"), ""));
			params.put("businessNo",  checkStr(request.getParameter("businessNo"), ""));
			params.put("zipcode",  checkStr(request.getParameter("zipcode"), ""));
			params.put("addr",  checkStr(request.getParameter("addr"), ""));
			params.put("addrDetail",  checkStr(request.getParameter("addrDetail"), ""));
			params.put("mainNumber",  checkStr(request.getParameter("mainNumber"), ""));
//			params.put("managerName",  checkStr(request.getParameter("managerName"), ""));
//			params.put("phoneNo",  checkStr(request.getParameter("phoneNo"), ""));
//			params.put("mobilePhone",  checkStr(request.getParameter("mobilePhone"), ""));
//			params.put("email",  checkStr(request.getParameter("email"), ""));
			params.put("dcMethodInfo",  checkStr(request.getParameter("dcMethodInfo"), ""));
			params.put("allyStat",  checkStr(request.getParameter("allyStat"), ""));
			params.put("svc1",  checkStr(request.getParameter("svc1"), ""));
			params.put("svc2",  checkStr(request.getParameter("svc2"), ""));
			params.put("svc3",  checkStr(request.getParameter("svc3"), ""));
			params.put("svc4",  checkStr(request.getParameter("svc4"), ""));
			params.put("memo",  checkStr(request.getParameter("memo"), ""));
			
			/*##################### 암호화 S #####################*/
			params.put("managerName", ktService.encoding(checkStr(request.getParameter("managerName"), "")));
			params.put("phoneNo", ktService.encoding(checkStr(request.getParameter("phoneNo"), "")));
			params.put("mobilePhone", ktService.encoding(checkStr(request.getParameter("phoneNo"), "")));
			params.put("email", ktService.encoding(checkStr(request.getParameter("email"), "")));
			/*##################### 암호화 E #####################*/
			
			log.debug("@@@@@@@@@@ FinanceRegisterAct params : "+ params); //##
			
			result = financeService.insertFinance(params);
			financeService.commit();
			
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/finance_register.ms");
			}
			else{
				request.setAttribute("targetUrl", "/member/finance_list.ms");
			}

			
			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/finance_registerAct.ms");
		
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
			request.setAttribute("targetUrl", "/member/finance_register.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : FinanceEditor
	 * @Description : 금융사 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/finance_editor.ms")
	public String FinanceEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		FinanceService financeService = new FinanceService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		String bankId = checkStr(request.getParameter("bankId"), "");
		params.put("bankId", bankId); //--선택된 금융사 ID

		log.debug("@@@@@@@@@@ FinanceEditor params : "+ params); //##
		Finance aFinance = financeService.selectAFinance(params); //-- 목록조회
		
		/*##################### 복호화 S #####################*/
		String mainNumber = ktService.decoding(checkStr(aFinance.getMainNumber(), ""));
		String managerNm = ktService.decoding(checkStr(aFinance.getManagerName(), ""));
		String phoneNo = ktService.decoding(checkStr(aFinance.getPhoneNo(), ""));
		String mobilePhone = ktService.decoding(checkStr(aFinance.getMobilePhone(), ""));
		String email = ktService.decoding(checkStr(aFinance.getEmail(), ""));
		
		aFinance.setMainNumber(mainNumber);
		aFinance.setManagerName(managerNm);
		aFinance.setPhoneNo(phoneNo);
		aFinance.setMobilePhone(mobilePhone);
		aFinance.setEmail(email);
		/*##################### 복호화 E #####################*/
		
		
		request.setAttribute("aFinance", aFinance);
		//params.clear();

		
		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/finance_editor.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		
		return "member/finance_editor";
	}
	
	
	/**
	 * @Method Name : FinanceEditorAct
	 * @Description : 금융사를 수정 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.06
	 */
	@RequestMapping(value="/member/finance_editorAct.ms")
	public String FinanceEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		String bankId = checkStr(request.getParameter("bankId"), "");
		try{
			FinanceService financeService = new FinanceService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			String chgUser = getSessionMgrId(request);
			
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("chgUser", chgUser); //-- 수정자ID
			params.put("bankId", bankId);
			params.put("name",  checkStr(request.getParameter("name"), ""));
			params.put("businessNo",  checkStr(request.getParameter("businessNo"), ""));
			params.put("zipcode",  checkStr(request.getParameter("zipcode"), ""));
			params.put("addr",  checkStr(request.getParameter("addr"), ""));
			params.put("addrDetail",  checkStr(request.getParameter("addrDetail"), ""));
//			params.put("mainNumber",  checkStr(request.getParameter("mainNumber"), ""));
//			params.put("managerName",  checkStr(request.getParameter("managerName"), ""));
//			params.put("phoneNo",  checkStr(request.getParameter("phoneNo"), ""));
//			params.put("mobilePhone",  checkStr(request.getParameter("mobilePhone"), ""));
//			params.put("email",  checkStr(request.getParameter("email"), ""));
			params.put("dcMethodInfo",  checkStr(request.getParameter("dcMethodInfo"), ""));
			params.put("allyStat",  checkStr(request.getParameter("allyStat"), ""));
			params.put("svc1",  checkStr(request.getParameter("svc1"), ""));
			params.put("svc2",  checkStr(request.getParameter("svc2"), ""));
			params.put("svc3",  checkStr(request.getParameter("svc3"), ""));
			params.put("svc4",  checkStr(request.getParameter("svc4"), ""));
			params.put("memo",  checkStr(request.getParameter("memo"), ""));
			
			/*##################### 암호화 S #####################*/
			params.put("mainNumber", ktService.encoding(checkStr(request.getParameter("mainNumber"), "")));
			params.put("managerName", ktService.encoding(checkStr(request.getParameter("managerName"), "")));
			params.put("phoneNo", ktService.encoding(checkStr(request.getParameter("phoneNo"), "")));
			params.put("mobilePhone", ktService.encoding(checkStr(request.getParameter("mobilePhone"), "")));
			params.put("email", ktService.encoding(checkStr(request.getParameter("email"), "")));
			/*##################### 암호화 E #####################*/
			
			
			log.debug("@@@@@@@@@@ FinanceEditorAct params : "+ params); //##
			
			result = financeService.updateFinance(params);
			financeService.commit();
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 수정 실패이면,
				request.setAttribute("targetUrl", "/member/finance_editor.ms?bank_id=" + bankId);
			}
			else{
				request.setAttribute("targetUrl", "/member/finance_list.ms");
			}

			
			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/finance_editorAct.ms");
		
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
			request.setAttribute("targetUrl", "/member/finance_editor.ms?bank_id=" + bankId);
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : FinanceDeleteAct
	 * @Description : 금융사를 삭제한다.(실제삭제)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/finance_deleteAct.ms")
	public String FinanceDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공

		String bankId = checkStr(request.getParameter("bankId"), "");
		try{
			FinanceService financeService = new FinanceService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("bankId", bankId);

			log.debug("@@@@@@@@@@ FinanceDeleteAct params : "+ params); //##
			
			result = financeService.deleteFinance(params);
			financeService.commit();
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 삭제 실패이면,
				request.setAttribute("targetUrl", "/member/finance_editor.ms?bank_id="+bankId);
			}
			else{
				request.setAttribute("targetUrl", "/member/finance_list.ms");
			}

			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/finance_deleteAct.ms");
			
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
			request.setAttribute("targetUrl", "/member/finance_editor.ms?bank_id=" + bankId);
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}

	
	
	/**
	 * @Method Name : ComplexFinanceList
	 * @Description : 결제사목록을 조회한다.(복합결제에서 이용하는 메소드)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.24
	 */
	@RequestMapping(value="/member/complex_finance_select.ms")
	public String ComplexFinanceList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		List<Finance> financeList = null;
		FinanceService financeService = new FinanceService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
 		financeList = financeService.selectFinanceList(params); //-- 목록조회
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("financeList", financeList);
		
		return "member/complex_finance_select";
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
