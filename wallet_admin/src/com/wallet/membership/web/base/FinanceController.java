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
	 * @Description : ������ �˻�/���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/finance_list.ms")
	public String FinanceList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Finance> financeList = null;
		FinanceService financeService = new FinanceService();
		//HashMap<String, Object> params = new HashMap<String, Object>((Map) request.getParameterMap());
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_allyStat", checkStr(request.getParameter("ra_allyStat"), "")); //-- ����¿� ���� �⺻�� ����
		params.put("seBankName", checkStr(request.getParameter("seBankName"), "")); //-- ���õ� �����簡 �������
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 

		params.put("bankId", checkStr(request.getParameter("bankId"), "")); //-- ���õ� �����簡 ���� ��
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int financeListCnt = financeService.selectFinanceListCnt(params); //-- �� ��� ��
		Paging page = new Paging();
		page.makeWebPaging(nowPage, financeListCnt, rowsPerPage);
		
		if(page.getNowPage()>1){
			nowPage =  page.getNowPage();
			request.setAttribute("nowPage", nowPage);
			
		}else{
			nowPage = 1;
			request.setAttribute("nowPage", "1");
		}
		
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));

		log.debug("@@@@@@@@@@ FinanceList params : "+ params); //##
		
 		financeList = financeService.selectFinanceList(params); //-- �����ȸ
 		
 		/*##################### ��ȣȭ S #####################*/
 		for(int i=0; i<financeList.size(); i++){
 			String managerNm = financeList.get(i).getManagerName();
 			String phone = financeList.get(i).getPhoneNo();
 			managerNm = ktService.decoding(managerNm);
 			phone = ktService.decoding(phone);
 			financeList.get(i).setManagerName(managerNm);
 			financeList.get(i).setPhoneNo(phone);
 		}
 		/*##################### ��ȣȭ E #####################*/
 		
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("financeList", financeList);
		request.setAttribute("seBankName", params.get("seBankName"));
		request.setAttribute("ra_allyStat", params.get("ra_allyStat"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		
		/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
		params.put("pageURL", "/member/finance_list.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
		
		params.clear();
		return "member/finance_list";
	}
	
	
	/**
	 * @Method Name : FinanceRegister
	 * @Description : ������ ���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/finance_register.ms")
	public String FinanceRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "member/finance_register";
	}
	

	
	/**
	 * @Method Name : FinanceRegisterAct
	 * @Description : �����縦 ��� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.06
	 */
	@RequestMapping(value="/member/finance_registerAct.ms")
	public String FinanceRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		
		try{
			FinanceService financeService = new FinanceService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			String regUser = getSessionMgrId(request);
			
			//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)
			params.put("regUser", regUser); //-- �����ID
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
			
			/*##################### ��ȣȭ S #####################*/
			params.put("managerName", ktService.encoding(checkStr(request.getParameter("managerName"), "")));
			params.put("phoneNo", ktService.encoding(checkStr(request.getParameter("phoneNo"), "")));
			params.put("mobilePhone", ktService.encoding(checkStr(request.getParameter("phoneNo"), "")));
			params.put("email", ktService.encoding(checkStr(request.getParameter("email"), "")));
			/*##################### ��ȣȭ E #####################*/
			
			log.debug("@@@@@@@@@@ FinanceRegisterAct params : "+ params); //##
			
			result = financeService.insertFinance(params);
			financeService.commit();
			
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/member/finance_register.ms");
			}
			else{
				request.setAttribute("targetUrl", "/member/finance_list.ms");
			}

			
			/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
			params.put("pageURL", "/member/finance_registerAct.ms");
		
			params.put("result", result);
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
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
	 * @Description : ������ ����ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/finance_editor.ms")
	public String FinanceEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		FinanceService financeService = new FinanceService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		String bankId = checkStr(request.getParameter("bankId"), "");
		params.put("bankId", bankId); //--���õ� ������ ID

		log.debug("@@@@@@@@@@ FinanceEditor params : "+ params); //##
		Finance aFinance = financeService.selectAFinance(params); //-- �����ȸ
		
		/*##################### ��ȣȭ S #####################*/
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
		/*##################### ��ȣȭ E #####################*/
		
		
		request.setAttribute("aFinance", aFinance);
		//params.clear();

		
		/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
		params.put("pageURL", "/member/finance_editor.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
		
		return "member/finance_editor";
	}
	
	
	/**
	 * @Method Name : FinanceEditorAct
	 * @Description : �����縦 ���� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.06
	 */
	@RequestMapping(value="/member/finance_editorAct.ms")
	public String FinanceEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		String bankId = checkStr(request.getParameter("bankId"), "");
		try{
			FinanceService financeService = new FinanceService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			String chgUser = getSessionMgrId(request);
			
			//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)
			params.put("chgUser", chgUser); //-- ������ID
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
			
			/*##################### ��ȣȭ S #####################*/
			params.put("mainNumber", ktService.encoding(checkStr(request.getParameter("mainNumber"), "")));
			params.put("managerName", ktService.encoding(checkStr(request.getParameter("managerName"), "")));
			params.put("phoneNo", ktService.encoding(checkStr(request.getParameter("phoneNo"), "")));
			params.put("mobilePhone", ktService.encoding(checkStr(request.getParameter("mobilePhone"), "")));
			params.put("email", ktService.encoding(checkStr(request.getParameter("email"), "")));
			/*##################### ��ȣȭ E #####################*/
			
			
			log.debug("@@@@@@@@@@ FinanceEditorAct params : "+ params); //##
			
			result = financeService.updateFinance(params);
			financeService.commit();
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/member/finance_editor.ms?bank_id=" + bankId);
			}
			else{
				request.setAttribute("targetUrl", "/member/finance_list.ms");
			}

			
			/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
			params.put("pageURL", "/member/finance_editorAct.ms");
		
			params.put("result", result);
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
			
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
	 * @Description : �����縦 �����Ѵ�.(��������)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@RequestMapping(value="/member/finance_deleteAct.ms")
	public String FinanceDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����

		String bankId = checkStr(request.getParameter("bankId"), "");
		try{
			FinanceService financeService = new FinanceService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("bankId", bankId);

			log.debug("@@@@@@@@@@ FinanceDeleteAct params : "+ params); //##
			
			result = financeService.deleteFinance(params);
			financeService.commit();
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/member/finance_editor.ms?bank_id="+bankId);
			}
			else{
				request.setAttribute("targetUrl", "/member/finance_list.ms");
			}

			/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
			params.put("pageURL", "/member/finance_deleteAct.ms");
			
			params.put("result", result);
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
			
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
	 * @Description : ���������� ��ȸ�Ѵ�.(���հ������� �̿��ϴ� �޼ҵ�)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	@RequestMapping(value="/member/complex_finance_select.ms")
	public String ComplexFinanceList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		List<Finance> financeList = null;
		FinanceService financeService = new FinanceService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
 		financeList = financeService.selectFinanceList(params); //-- �����ȸ
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("financeList", financeList);
		
		return "member/complex_finance_select";
	}
	
	/**
	 * @Method Name : today
	 * @Description : ���� ��¥�� ��ȸ�Ѵ�.
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
