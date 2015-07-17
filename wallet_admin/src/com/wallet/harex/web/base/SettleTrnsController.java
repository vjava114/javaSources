package com.wallet.harex.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.harex.common.JDate;
import com.wallet.harex.model.SettleTrns;
import com.wallet.harex.service.SettleTrnsService;
import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;

@Controller
public class SettleTrnsController extends CommonController {

	private Logger log = Log.getLogger("logs");
	
	/**
	 * ������� > �ŷ�������ȸ
	 * @param 	sdate					������
	 * @param 	edate					������
	 * @return	lis						�ŷ��������
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/settle_trns_list.hx")
	public String selectSettleTrnsList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### SettleTrns selectSettleTrnsList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<SettleTrns> list = null;
		SettleTrnsService service = new SettleTrnsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String chk_date		= request.getParameter("chk_date");
		
		String pSdate		= request.getParameter("sdate"); // ������
		String pEdate	 	= request.getParameter("edate"); // ������
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // ������� ����
		String pCompId 	= checkStr(request.getParameter("comp_id"), "");  // ���޻�
		String pBrandId 	= checkStr(request.getParameter("brand_id"), ""); // �귣��
		String pRgType  	= checkStr(request.getParameter("region_type"), "");  // ����
		String pKShopId 	= checkStr(request.getParameter("shopSel"), ""); // ������
		String pAcposTid 	= checkStr(request.getParameter("acposTid"), ""); // ���հ��� �ŷ���ȣ
		String pPhoneNo  	= checkStr(request.getParameter("phoneNo"), ""); // �ڵ�����ȣ
		String pStatus 	= checkStr(request.getParameter("status"), "ok"); // ���ΰ��
		
		
		JDate tDate = new JDate();
		if(pSdate == null || "".equals(pSdate)){
			pSdate = tDate.getFormattedDate("yyyyMMdd");
		} else {
			pSdate = pSdate.toString().replaceAll("-", "");
		}
		
		if(pEdate == null || "".equals(pEdate)){
			pEdate = tDate.getFormattedDate("yyyyMMdd");
		} else {
			pEdate = pEdate.toString().replaceAll("-", "");
		}
		
		params.put("sdate"    	, checkStr(pSdate, ""));
		params.put("edate"    	, checkStr(pEdate, ""));
		params.put("kShopId"  	, pKShopId); 
		params.put("brandId"  	, pBrandId);
		params.put("region_type", pRgType);
		params.put("acposTid" 	, pAcposTid);  
		params.put("compId"   	, pCompId);
		params.put("phoneNo"  	, pPhoneNo);
		params.put("status"   	, pStatus);
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int ListCnt = 0; // �� ��ϼ�
		//��ȸ
		list = service.selectSettleTrnsList(params);
		ListCnt = service.selectSettleTrnsListCnt(params);
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		params.put("chk_date", chk_date == null || chk_date.equals("") ? "1d":chk_date);
		params.put("compId", pCompId == null || pCompId.equals("") ? "none":pCompId);
		params.put("brandId", pBrandId == null || pBrandId.equals("") ? "none":pBrandId);
		params.put("shopSel", pKShopId == null || pKShopId.equals("") ? "none":pKShopId);
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		request.setAttribute("sdate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));
		request.setAttribute("edate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));

		//�α�ó��/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		HashMap<String,Object> logParams = new HashMap<String,Object>();
		MwAdAccessLogService logSvr = new MwAdAccessLogService();

		try {

			params.put("PageURL", request.getRequestURL());
			
			logParams.put("part", "HAREX");
			logParams.put("admin_id", getSessionMgrId(request));
			logParams.put("msg", params.toString());
			
			logSvr.insertAccessLogReg(logParams);
			
			logSvr.commit();
		} catch (Exception e) {
			logSvr.rollback();
		}
		//�α�ó��/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		log.debug("### SettleTrns selectSettleTrnsList  END ###");
		
		String target = "harex/settle_trns_list";
		// ���� ������ ���.. ��� ���� 
		if("Y".equals(excel))
			target = "harex/settle_trns_list_excel";
		return target;
	}
	
	/**
	 * �Ϻ���Ȳ���� ����Ʈ
	 * @param 	pMonth 	��ȸ��
	 * @param 	status 	���ΰ��
	 * @return	list				�Ϻ���Ȳ���� ����Ʈ
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/settle_daily_list.hx")
	public String selectSettleTrnsDaily(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### SettleTrns selectSettleTrnsDaily START ###");
		String pMonth = request.getParameter("month"); 	// ��ȸ��
		String pStatus = request.getParameter("status");	// ����: 'ok' / ���: 'cancel'
		
		if(pMonth == null || "".equals(pMonth)){
			JDate tDate = new JDate();
			pMonth = tDate.getFormattedDate("yyyyMM");
			pStatus = "ok";
		}
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<SettleTrns> list = null;
		SettleTrnsService service = new SettleTrnsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("pMonth", checkStr(pMonth, ""));
		params.put("pStatus", checkStr(pStatus, ""));
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//��ȸ
		list = service.selectSettleTrnsDaily(params);
		int ListCnt = service.selectSettleTrnsDailyCnt(params); 		// �� ��� ��
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());	
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		request.setAttribute("List", list);
		request.setAttribute("pMonth", pMonth);
		request.setAttribute("pStatus", pStatus);
		
		log.debug("### SettleTrns selectSettleTrnsDaily  END ###");
		
		return "harex/settle_daily_list";
	}
	
	/**
	 * ������� > �ŷ�������ȸ > ���������� �˾�
	 * @param 	custId 	����ȣ
	 * @param 	cpnId 	������ȣ
	 * @return	list		���������� �˾�
	 */
	@RequestMapping(value="/harex/settle_trns_cou_pop.hx")
	public String selectSettleCouponPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SettleTrns selectSettleCouponPop START ###");
		String pUbpayId 	= checkStr(request.getParameter("custId"), ""); 	// ubpay_id = cust_id  
		String pCpnId 		= checkStr(request.getParameter("cpnId"), ""); 	// ����ID
		String pPhoneNo 	= checkStr(request.getParameter("phoneNo"), ""); 	// �ڵ�����ȣ
		String pApprDate 	= checkStr(request.getParameter("apprDate"), ""); 	// �����Ͻ�
    
		try{
			
			//��ȸ
			HashMap<String,Object> params = new HashMap<String,Object>(); // �˻����� param
			
			SettleTrns settleTrns = null;
			
			if(!"".equals(pUbpayId) && !"".equals(pCpnId)){
				/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
				SettleTrnsService service = new SettleTrnsService();
				
				params.put("ubpayId", pUbpayId);
				params.put("cpnId", pCpnId);
				
				settleTrns = service.selectSettleCouponPop(params);
				
				/**
				 *   ���̸� ��ȣȭ ó�� 2012.10.25 userName ����ó��
				 */

				if(!"".equals(pApprDate) && pApprDate.length() == 14){
					pApprDate = pApprDate.substring(0, 4) + "-" + pApprDate.substring(4, 6) + "-" + pApprDate.substring(6, 8) + " "
							       + pApprDate.substring(8, 10) + ":" + pApprDate.substring(10, 12) + ":" + pApprDate.substring(12, 14);
				}
			}
				
			request.setAttribute("params", params);
			request.setAttribute("phoneNo", pPhoneNo);
			request.setAttribute("apprDate", pApprDate); // �����Ͻ�
			request.setAttribute("result", settleTrns); // �����Ͻ�
				
		}catch(Exception e){
			log.debug("[ERROR]" + e.getMessage());
		}
		log.debug("### SettleTrns selectSettleCouponPop  END ###");
		
		return "harex/settle_trns_cou_pop";
	}
	
	/**
	 * ������� > �ŷ�������ȸ > ����ʻ����� �˾�
	 * @param 	custId 	����ȣ
	 * @param 	cpnId 	������ȣ
	 * @return		list			�������������� �˾�
	 */
	@RequestMapping(value="/harex/settle_trns_mem_pop.hx")
	public String selectSettleMemberPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SettleTrns selectSettleMemberPop START ###");
		String pUbpayId 		= checkStr(request.getParameter("custId"), ""); 	// ubpay_id = cust_id  
		String pMembId 		= checkStr(request.getParameter("membId"), ""); 	// ����ID
		String pPhoneNo 		= checkStr(request.getParameter("phoneNo"), ""); 	// �ڵ�����ȣ
		String pApprDate 		= checkStr(request.getParameter("apprDate"), ""); 	// �����Ͻ�
		String pCardNo 		= checkStr(request.getParameter("cardNo"), ""); 	// ī���ȣ
	    
		try{
			//��ȸ
			HashMap<String,Object> params = new HashMap<String,Object>(); // �˻����� param
			SettleTrns settleTrns = null;
			
			if(!"".equals(pUbpayId) && !"".equals(pMembId)){
				/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
				SettleTrnsService service = new SettleTrnsService();
				
				params.put("ubpayId", pUbpayId);
				params.put("membId", pMembId);
				
				settleTrns = service.selectSettleMemberPop(params);
				
				/**
				 * TODO:���̸� ��ȣȭ ó�� -> 2012.10.16 - ��ȣȭ ������ �ٽ� �����ϱ�� ��. name ���� �����ϱ� - by kma 
				 * 2012.10.25 userName ����ó��
				 */
				
				if(!"".equals(pApprDate) && pApprDate.length() == 14){
					pApprDate = pApprDate.substring(0, 4) + "-" + pApprDate.substring(4, 6) + "-" + pApprDate.substring(6, 8) + " "
							       + pApprDate.substring(8, 10) + ":" + pApprDate.substring(10, 12) + ":" + pApprDate.substring(12, 14);
				}
			}

			request.setAttribute("resultMap", settleTrns);
			request.setAttribute("params", params);
			request.setAttribute("phoneNo", pPhoneNo);
			request.setAttribute("apprDate", pApprDate); // �����Ͻ�
			request.setAttribute("cardNo", pCardNo); // ī���ȣ
			
		}catch(Exception e){
			log.debug(e.getMessage());
		}
		log.debug("### SettleTrns selectSettleMemberPop  END ###");
		
		return "harex/settle_trns_mem_pop";
	}
	
	/**
	 * ������� > ����DATA��ȸ
	 * @param 	custId 	����ȣ
	 * @param 	cpnId 	������ȣ
	 * @return		list			����DATA��ȸ
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/settle_data_list.hx")
	public String selectSettleDataList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("### SettleTrns selectSettleDataList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<SettleTrns> list = null;
		int ListCnt = 0;  // ��� �� ����
		SettleTrnsService service = new SettleTrnsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String chk_date	= request.getParameter("chk_date");
		String compId     	= checkStr(request.getParameter("compId"),"");
		String brandId    	= checkStr(request.getParameter("brandId"),"");
		String regionType	= checkStr(request.getParameter("regionType"), "");  // ����
		String shopId		= checkStr(request.getParameter("shopId"), "");  // ������ID
		String couponId 	= checkStr(request.getParameter("couponId"), ""); // ����ID
		
		String sDate		= checkStr(request.getParameter("sDate"), ""); // ������
		String eDate		= checkStr(request.getParameter("eDate"), ""); // ������
		String tempCpn	= checkStr(request.getParameter("couponId"), "");  // ������ȣ,����Ÿ��
		
		String excel			= checkStr(request.getParameter("excel"), "N");  // ������� ����
		
		JDate date = new JDate();
		
		if("".equals(sDate)){
			sDate = date.getFormattedDate("yyyyMMdd");
		} else {
			sDate = sDate.toString().replaceAll("-", "");
		}
		if("".equals(eDate)){
			eDate = date.getFormattedDate("yyyyMMdd");
		} else {
			eDate = eDate.toString().replaceAll("-", "");
		}
		
		params.put("compId", compId);
		params.put("brandId", brandId);
		params.put("sDate", sDate);
		params.put("eDate", eDate);
		params.put("shopId", shopId);
		params.put("couponId", couponId);
		params.put("regType", regionType);
		
		//����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//��ȸ
		list = service.selectSettleDataList(params);
		ListCnt = service.selectSettleDataListCnt(params); 		// �� ��� ��
		
		request.setAttribute("List", list);
			
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		request.setAttribute("chk_date",   chk_date == null || chk_date.equals("") ? "1d":chk_date);
		request.setAttribute("compId",     compId == null || compId.equals("") ? "none":compId);
		request.setAttribute("brandId",    brandId == null || brandId.equals("") ? "none":brandId);
		request.setAttribute("shopId",     shopId == null || shopId.equals("") ? "none":shopId);
		request.setAttribute("regionType", regionType);
		
		params.put("couponId", tempCpn); // �����޺��ڽ� - ��û�� ������ �����Ͽ� ������.
		request.setAttribute("sDate", sDate.substring(0, 4) + "-" + sDate.substring(4, 6) + "-" + sDate.substring(6, 8));
		request.setAttribute("eDate", eDate.substring(0, 4) + "-" + eDate.substring(4, 6) + "-" + eDate.substring(6, 8));
		request.setAttribute("params", params);
		
		log.debug("### SettleTrns selectSettleDataList  END ###" + list.size());
		
		String target = "harex/settle_data_list";
		// ���� ������ ���.. ��� ���� 
		if("Y".equals(excel))
			target = "harex/settle_data_list_excel";
		
		return target;
	}
	
	/**
	 * ������� > ����DATA��ȸ > ����������
	 * @param 	custId 	����ȣ
	 * @param 	cpnId 	������ȣ
	 * @return		list			����DATA��ȸ
	 */
	@RequestMapping(value="/harex/settle_data_cpn_pop.hx")
	public String selectSettleDataCpnInfoPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SettleTrns selectSettleDataCnpInfoPop START ###");
		String pCpnId = checkStr(request.getParameter("cpnId"));
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<SettleTrns> list = null;
		SettleTrnsService service = new SettleTrnsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		
		params.put("cpnId", pCpnId);

		//��ȸ
		list = service.selectSettleDataCpnInfoPop(params);
		request.setAttribute("List", list);
		
		for(SettleTrns settleTrns:list){
			resultMap.put("cpnId", settleTrns.getCpnId()); 
			resultMap.put("compId", settleTrns.getCompId()); 
			resultMap.put("compName", settleTrns.getCompName()); 
			resultMap.put("pointDupUsable", settleTrns.getPointDupUsable()); 
			resultMap.put("cpnDupUsableYn", settleTrns.getCpnDupUsableYn()); 
			resultMap.put("cpnSort", settleTrns.getCpnSort()); 
			resultMap.put("minPayPrice", settleTrns.getMinPayPrice()); 
			resultMap.put("maxDicPrice", settleTrns.getMaxDicPrice()); 
			resultMap.put("brandId", settleTrns.getBrandId()); 
			resultMap.put("brandName", settleTrns.getBrandName()); 
			resultMap.put("payCompId", settleTrns.getPayComopId()); 
			resultMap.put("payCompName", settleTrns.getPayCompName()); 
		}
		request.setAttribute("resultMap", resultMap);		
		
		log.debug("### SettleTrns selectSettleDataCpnInfoPop  END ###");
		
		return "harex/settle_data_cpn_pop";
	}
	
	/**
	 * ������� > ����DATA��ȸ > ���������ݾ� ���
	 * @param 	custId 	����ȣ
	 * @param 	cpnId 	������ȣ
	 * @return		list			�� ���������ݾ� ����
	 */
	@RequestMapping(value="/harex/settle_data_tot_pop.hx")
	public String selectSettleDataCpnTotPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SettleTrns selectSettleDataCpnTotPop START ###");
		String pDate = checkStr(request.getParameter("date"));
		String pShopId = checkStr(request.getParameter("shopId"));
		String pCpnName = checkStr(request.getParameter("cpnName"));
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<SettleTrns> list = null;
		SettleTrnsService service = new SettleTrnsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("date", pDate);
		params.put("shopId", pShopId);
		
		//��ȸ
		list = service.selectSettleDataCpnTotPop(params);
		request.setAttribute("List", list);
		request.setAttribute("cpnName", pCpnName);
		
		log.debug("### SettleTrns selectSettleDataCpnTotPop  END ###");
		
		return "harex/settle_data_tot_pop";
	}
}
