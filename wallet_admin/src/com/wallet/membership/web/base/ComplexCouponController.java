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

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.custom.ComplexCoupon;
import com.wallet.membership.service.custom.ComplexCouponService;

@Controller
public class ComplexCouponController extends CommonController {
	private final String PAGE_CODE = "COMPLEX_COUPON_LIST";
	private Logger log = Log.getLogger("logs");

	public ComplexCouponController() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @Method Name : ComplexCouponList
	 * @Description : �����(ī������) �˻�/���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	@RequestMapping(value="/member/complex_coupon_list.ms")
	public String ComplexCouponList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ComplexCoupon> complexCouponList = null;
		ComplexCouponService complexCouponService = new ComplexCouponService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("compNm", checkStr(request.getParameter("compNm"), "")); //-- ���õ� ��������
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || "".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 

		String compName = checkStr(request.getParameter("compName"), "");
		String cpnName = checkStr(request.getParameter("cpnName"), "");
		String partV = checkStr(request.getParameter("partV"), "");

		params.put("compName", compName); //���޻��
		params.put("cpnName", cpnName); //������
		params.put("partV", partV); 
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		log.debug("@@@@@@@@@@ ComplexCouponList params : "+ params); //##
		
		complexCouponList = complexCouponService.selectComplexCouponList(params); //-- �����ȸ
		int complexCouponListCnt = complexCouponService.selectComplexCouponListCnt(params); //-- �� ��� ��
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, complexCouponListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("complexCouponList", complexCouponList);
		request.setAttribute("compName", compName); //���޻��
		request.setAttribute("cpnName", cpnName); //������
		request.setAttribute("partV", partV); //������
		request.setAttribute("ra_stat", params.get("ra_stat"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt", params.get("se_termOpt"));
		
		params.clear();
		return "member/complex_coupon_list";
	}


	
	/**
	 * @Method Name : ComplexCouponEditor
	 * @Description : ���հ��� ���� ����ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	@RequestMapping(value="/member/complex_coupon_editor.ms")
	public String ComplexCouponEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ComplexCouponService complexCouponService = new ComplexCouponService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("cpnId", checkStr(request.getParameter("cpnId"), "")); 
		
		List<ComplexCoupon> complexStoreList = complexCouponService.selectComplexStore(params);
		List<ComplexCoupon> complexBankList = complexCouponService.selectComplexBank(params);
		List<ComplexCoupon> complexPayCompList = complexCouponService.selectComplexPayComp(params);

		
		log.debug("@@@@@@@@@@ ComplexCouponList params : "+ params); //##
		
		ComplexCoupon aComplexCoupon = (ComplexCoupon) complexCouponService.selectAComplexCoupon(params);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("aComplexCoupon", aComplexCoupon);
		request.setAttribute("complexStoreList", complexStoreList);
		request.setAttribute("complexBankList", complexBankList);
		request.setAttribute("complexPayCompList", complexPayCompList);
		
		params.clear();
		return "member/complex_coupon_editor";
	}
	
	
	/**
	 * @Method Name : ComplexCouponEditorAct
	 * @Description : ���հ��� ������ �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	@RequestMapping(value="/member/complex_coupon_editorAct.ms")
	public String ComplexCouponEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int result = 1; //-- �� �޼ҵ��� ������ 0:����, 1:����
		
		ComplexCouponService complexCouponService = new ComplexCouponService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		String regUser = getSessionMgrId(request);
		
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		try{
			
			params.put("cpnId", cpnId); 
			params.put("partV", checkStr(request.getParameter("partV"), "")); 
			params.put("membId", checkStr(request.getParameter("membId"), "")); 
			params.put("compId", checkStr(request.getParameter("compId"), "")); 
			params.put("branId", checkStr(request.getParameter("branId"), "")); 
			params.put("pointDupUsableYN", checkStr(request.getParameter("pointDupUsableYN"), "N")); 
			params.put("cpnDupUsableYN", checkStr(request.getParameter("cpnDupUsableYN"), "N"));
			
			String cpnDiscType =  checkStr(request.getParameter("cpnDiscType"), "01");
			params.put("cpnDiscType", cpnDiscType);
			if(cpnDiscType.equals("01")){ //-- �������������� �������� ������ ���,
				params.put("cpnDiscAmount", checkStr(request.getParameter("cpnDiscAmount"), ""));
			}
			else if(cpnDiscType.equals("02")){ //-- �������������� ��������ũ���� ��� 
				params.put("cpnDiscAmount", checkStr(request.getParameter("cpnDiscRate"), ""));
				params.put("maxDicPrice", checkStr(request.getParameter("maxDicPrice"), ""));
				params.put("roundType", checkStr(request.getParameter("roundType"), ""));
				params.put("dcUnit", checkStr(request.getParameter("dcUnit"), ""));
			}

			params.put("minPayPrice", checkStr(request.getParameter("minPayPrice"), ""));
			params.put("dcNotice", checkStr(request.getParameter("dcNotice"), ""));
			
			//-- ������� ������ ���
			params.put("shopSvcList", checkStr(request.getParameter("shopSvcList"), ""));
			String[] tmpStoreList = request.getParameterValues("stores");
			
			//-- ������ ���
			params.put("bankSvcList", checkStr(request.getParameter("bankSvcList"), ""));
			String[] tmpFinanceList = request.getParameterValues("finances");
			
			//-- ������ ���
			params.put("paycomSvcList",checkStr(request.getParameter("paycomSvcList"), ""));
			String[] tmpSettlementList = request.getParameterValues("settlements");
			
		
			log.debug("@@@@@@@@@@ ComplexCouponList params : "+ params); //##

			params.put("chgUser", regUser); //-- ������ID
			result = complexCouponService.updateComplexCoupon(params); //-- ������ ����
			result = result * complexCouponService.updateCouponList(params);
			
			
			params.clear();
			params.put("cpnId", cpnId);
			complexCouponService.deleteComplexStore(params);
			
			if(!"A".equals(checkStr(request.getParameter("ra_ShopOpt"), "")) && tmpStoreList != null){
				for(int i=0; i<tmpStoreList.length; i++){
					params.clear();
					
					params.put("cpnId", cpnId);
					params.put("compId", tmpStoreList[i].substring(tmpStoreList[i].lastIndexOf(":")+1));
					params.put("regUser", regUser); //-- ������ID
					result = result * complexCouponService.insertComplexStore(params);
				}
			}
			
			params.clear();
			params.put("cpnId", cpnId);
			complexCouponService.deleteComplexBank(params);
						
			if(!"A".equals(checkStr(request.getParameter("ra_FinanceOpt"), "")) && tmpFinanceList != null){
				for(int i=0; i<tmpFinanceList.length; i++){
					params.clear();
					
					params.put("cpnId", cpnId);
					params.put("bankId", tmpFinanceList[i].substring(tmpFinanceList[i].lastIndexOf(":")+1));
					params.put("regUser", regUser); //-- ������ID
					result = result * complexCouponService.insertComplexBank(params);
				}
			}

			
			params.clear();
			params.put("cpnId", cpnId);
			complexCouponService.deleteComplexPayComp(params);
			

			if(!"A".equals(checkStr(request.getParameter("ra_SettlementOpt"), "")) && tmpSettlementList != null){
				for(int i=0; i<tmpSettlementList.length; i++){
					params.clear();
					
					params.put("cpnId", cpnId);
					params.put("payCompId", tmpSettlementList[i].substring(tmpSettlementList[i].lastIndexOf(":")+1));
					params.put("regUser", regUser); //-- ������ID
					result = result * complexCouponService.insertComplexPayComp(params);
				}
			}
			
			
			
//			for(int i=0; i<tmpFinanceList.length; i++){
//				params.clear();
//				
//				params.put("cpnId", cpnId);
//				params.put("bankId", tmpFinanceList[i]);
//				params.put("regUser", "SESSION_ID"); //-- ������ID
//				
//				result = result * complexCouponService.insertComplexBank(params);
//			}
//
//
//			params.clear();
//			params.put("cpnId", cpnId);
//			complexCouponService.deleteComplexPayComp(params);
//			
//			for(int i=0; i<tmpSettlementList.length; i++){
//				params.clear();
//				
//				params.put("cpnId", cpnId);
//				params.put("payCompId", tmpSettlementList[i]);
//				params.put("regUser", "SESSION_ID"); //-- ������ID
//				
//				result = result * complexCouponService.insertComplexPayComp(params);
//			}
			
			
			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("actResult", result + "");
			
			if(result == 0){
				complexCouponService.rollback();
				request.setAttribute("targetUrl", "/member/complex_coupon_editor.ms?cpnId=" + cpnId);
			}
			else{
				complexCouponService.commit();
				request.setAttribute("targetUrl", "/member/complex_coupon_list.ms");
			}
		}
		catch(Exception e){
			complexCouponService.rollback();
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/complex_coupon_editor.ms?cpnId=" + cpnId);
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
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
