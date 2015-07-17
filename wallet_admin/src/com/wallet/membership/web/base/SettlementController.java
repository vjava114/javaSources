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
import com.wallet.membership.model.custom.Settlement;
import com.wallet.membership.service.custom.SettlementService;
import com.wallet.admin.service.MwAdAccessLogService;

@Controller
public class SettlementController extends CommonController {
	private final String PAGE_CODE = "SETTLEMENT_LIST";
	private Logger log = Log.getLogger("logs");
	private MwAdAccessLogService logSVC = new MwAdAccessLogService();
	KTDBCipher ktService = new KTDBCipher();
	/**
	 * @Method Name : SettlementList
	 * @Description : 결제사 검색/목록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.05
	 */
	@RequestMapping(value="/member/settlement_list.ms")
	public String SettlementList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Settlement> settlementList = null;
		SettlementService settlementService = new SettlementService();
		//HashMap<String, Object> params = new HashMap<String, Object>((Map) request.getParameterMap());
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_allyStat", checkStr(request.getParameter("ra_allyStat"), "")); //-- 운영상태에 대한 기본값 설정
		params.put("sePayCompName", checkStr(request.getParameter("sePayCompName"), "")); //-- 선택된 결제사가 결제사명
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

		params.put("sePayCompId", checkStr(request.getParameter("sePayCompId"), "")); //-- 선택된 결제사가 있을 때
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int settlementListCnt = settlementService.selectSettlementListCnt(params); //-- 총 목록 수
		Paging page = new Paging();
		page.makeWebPaging(nowPage, settlementListCnt, rowsPerPage);
		
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
		
		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		settlementList = settlementService.selectSettlementList(params); //-- 목록조회
 		
 		/*##################### 복호화 S #####################*/
 		for(int i=0; i<settlementList.size(); i++){
 			String manageNm = settlementList.get(i).getManagerName();
 			String phone = settlementList.get(i).getPhoneNo();
 			manageNm = ktService.decoding(manageNm);
 			phone = ktService.decoding(phone);
 			settlementList.get(i).setManagerName(manageNm);
 			settlementList.get(i).setPhoneNo(phone);
 		}
 		/*##################### 복호화 E #####################*/
 		
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("settlementList", settlementList);
		request.setAttribute("sePayCompName", params.get("sePayCompName"));
		request.setAttribute("ra_allyStat", params.get("ra_allyStat"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		
		
		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/settlement_list.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		
		params.clear();
		return "member/settlement_list";
	}
	
	
	/**
	 * @Method Name : SettlementRegister
	 * @Description : 결제사 등록화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.05
	 */
	@RequestMapping(value="/member/settlement_register.ms")
	public String SettlementRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "member/settlement_register";
	}
	

	
	/**
	 * @Method Name : SettlementRegisterAct
	 * @Description : 결제사를 등록 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.06
	 */
	@RequestMapping(value="/member/settlement_registerAct.ms")
	public String SettlementRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공

		SettlementService settlementService = new SettlementService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		String regUser = getSessionMgrId(request);
		
		try{
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("regUser", regUser); //-- 등록자ID
			params.put("payCompName",  checkStr(request.getParameter("payCompName"), ""));
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
			params.put("memo",  checkStr(request.getParameter("memo"), ""));
			
			/*##################### 암호화 S #####################*/
			params.put("mainNumber",  ktService.encoding(checkStr(request.getParameter("mainNumber"), "")));
			params.put("managerName",  ktService.encoding(checkStr(request.getParameter("managerName"), "")));
			params.put("phoneNo",  ktService.encoding(checkStr(request.getParameter("phoneNo"), "")));
			params.put("mobilePhone",  ktService.encoding(checkStr(request.getParameter("mobilePhone"), "")));
			params.put("email",  ktService.encoding(checkStr(request.getParameter("email"), "")));
			/*##################### 암호화 E #####################*/
			
			log.debug("@@@@@@@@@@ SettlementRegisterAct params : "+ params); //##
			
			result = settlementService.insertSettlement(params);
			
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 수정 실패이면,
				settlementService.rollback();
				request.setAttribute("targetUrl", "/member/settlement_register.ms");
			}
			else{
				settlementService.commit();
				request.setAttribute("targetUrl", "/member/settlement_list.ms");
			}
			
			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/settlement_registerAct.ms");
		
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

			settlementService.rollback();
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/settlement_register.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : SettlementEditor
	 * @Description : 결제사 수정화면을 로딩한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.05
	 */
	@RequestMapping(value="/member/settlement_editor.ms")
	public String SettlementEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		SettlementService settlementService = new SettlementService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("payCompId", checkStr(request.getParameter("payCompId"), "")); //--선택된 결제사 ID

		log.debug("@@@@@@@@@@ SettlementEditor params : "+ params); //##
		Settlement aSettlement = settlementService.selectASettlement(params); //-- 목록조회
		
		/*##################### 복호화 S #####################*/
		String mainNum = ktService.decoding(aSettlement.getMainNumber());
		String manageNm = ktService.decoding(aSettlement.getManagerName());
		String phone = ktService.decoding(aSettlement.getPhoneNo());
		String mobilePhone = ktService.decoding(aSettlement.getMobilePhone());
		String email = ktService.decoding(aSettlement.getEmail());
		
		aSettlement.setMainNumber(mainNum);
		aSettlement.setManagerName(manageNm);
		aSettlement.setPhoneNo(phone);
		aSettlement.setMobilePhone(mobilePhone);
		aSettlement.setEmail(email);
		/*##################### 복호화 E #####################*/

		request.setAttribute("aSettlement", aSettlement);
		
		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/settlement_editor.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		
		params.clear();
		return "member/settlement_editor";
	}
	
	
	/**
	 * @Method Name : SettlementEditorAct
	 * @Description : 결제사를 수정 후 결과를 전달한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.06
	 */
	@RequestMapping(value="/member/settlement_editorAct.ms")
	public String SettlementEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공

		SettlementService settlementService = new SettlementService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		String chgUser = getSessionMgrId(request);
		
		try{
			//-- 세션 처리를 통한 등록자 정보 setting 하기 (추후추가)
			params.put("chgUser", chgUser); //-- 수정자ID
			params.put("payCompId",  checkStr(request.getParameter("payCompId"), ""));
			params.put("payCompName",  checkStr(request.getParameter("payCompName"), ""));
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
			params.put("memo",  checkStr(request.getParameter("memo"), ""));

			log.debug("@@@@@@@@@@ SettlementEditorAct params : "+ params); //##
			
			/*##################### 암호화 S #####################*/
			params.put("mainNumber",  ktService.encoding(checkStr(request.getParameter("mainNumber"), "")));
			params.put("managerName",  ktService.encoding(checkStr(request.getParameter("managerName"), "")));
			params.put("phoneNo",  ktService.encoding(checkStr(request.getParameter("phoneNo"), "")));
			params.put("mobilePhone",  ktService.encoding(checkStr(request.getParameter("mobilePhone"), "")));
			params.put("email",  ktService.encoding(checkStr(request.getParameter("email"), "")));
			/*##################### 암호화 E #####################*/
			
			result = settlementService.updateSettlement(params);
			request.setAttribute("actResult", result + "");
			

			if(result == 0){//-- 수정 실패이면,
				settlementService.rollback();
				request.setAttribute("targetUrl", "/member/settlement_editor.ms?payCompId=" + checkStr(request.getParameter("payCompId"), "") );
			}
			else{
				settlementService.commit();
				request.setAttribute("targetUrl", "/member/settlement_list.ms");
			}
			
			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/settlement_editorAct.ms");
			
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
			settlementService.rollback();
			
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/settlement_editor.ms?payCompId=" + checkStr(request.getParameter("payCompId"), ""));
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : SettlemetDeleteAct
	 * @Description : 결제사를 삭제한다.(실제삭제)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/settlement_deleteAct.ms")
	public String SettlementDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--이 메소드의 실행결과 0:실패, 1:성공
		
		try{
			SettlementService settlementService = new SettlementService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("payCompId",  checkStr(request.getParameter("payCompId"), ""));

			log.debug("@@@@@@@@@@ SettlementDeleteAct params : "+ params); //##
			
			result = settlementService.deleteSettlement(params);
			settlementService.commit();
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- 삭제 실패이면,
				request.setAttribute("targetUrl", "/member/settlement_editor.ms?payCompId=" + checkStr(request.getParameter("payCompId"), ""));
			}
			else{
				request.setAttribute("targetUrl", "/member/settlement_list.ms");
			}

			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/settlement_deleteAct.ms");
			
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
			request.setAttribute("targetUrl", "/member/settlement_editor.ms?payCompId=" + checkStr(request.getParameter("payCompId"), ""));
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}

	
	
	/**
	 * @Method Name : ComplexSettlementList
	 * @Description : 결제사목록을 조회한다.(복합결제에서 이용하는 메소드)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.24
	 */
	@RequestMapping(value="/member/complex_settlement_select.ms")
	public String ComplexSettlementList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		List<Settlement> settlementList = null;
		SettlementService settlementService = new SettlementService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
 		settlementList = settlementService.selectSettlementList(params); //-- 목록조회
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("settlementList", settlementList);
		
		params.clear();
		return "member/complex_settlement_select";
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
