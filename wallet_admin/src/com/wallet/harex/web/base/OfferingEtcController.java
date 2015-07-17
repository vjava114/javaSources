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

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.harex.model.OfferingCpn;
import com.wallet.harex.model.OfferingDtl;
import com.wallet.harex.model.OfferingMsDc;
import com.wallet.harex.model.OfferingMsSave;
import com.wallet.harex.model.OfferingMsStamp;
import com.wallet.harex.model.OfferingMsUse;
import com.wallet.harex.model.OfferingOrder;
import com.wallet.harex.model.OfferingPromo;
import com.wallet.harex.model.OfferingS;
import com.wallet.harex.service.OfferingEtcService;

@Controller
public class OfferingEtcController extends CommonController {

	private Logger log = Log.getLogger("logs");
	
	/**
	 * ���հ��� ���۸� ����
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_s_list.st")
	public String selectOfferingS(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingS START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingS> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String acpos_tid 		= checkStr(request.getParameter("acpos_tid"), "");  //�ŷ���ȣ
		String mocapay_tid 	= checkStr(request.getParameter("mocapay_tid"), "");  //�ŷ���ȣ
		String excel 	 		= checkStr(request.getParameter("excel"), "N"); // ������� ����
		
		params.put("acpos_tid", acpos_tid);	
		params.put("mocapay_tid", mocapay_tid);	
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
			
		//��ȸ
		String target = "/harex/offering_s_list"; // ��� ������
		
		if("Y".equals(excel)){
			target = "/harex/offering_s_list_excel"; // �������� ���
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		
		try{
			//��ȸ
			list = service.selectOfferingS(params);
			listCnt= service.selectOfferingSCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		log.debug("### OfferingEtcController selectOfferingS END ###");
		
		return target;
	}
	
	/**
	 * ���հ��� ���۸� ���� ������
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_cpn_list.st")
	public String selectOfferingCpn(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingCpn START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingCpn> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //���۸� Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // ������� ����
		
		params.put("offer_id", offer_id);	
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		

		String target = "/harex/offering_cpn_list"; // ��� ������
				
		if("Y".equals(excel)){
			target = "/harex/offering_cpn_list_excel"; // �������� ���
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//��ȸ
			list = service.selectOfferingCpn(params);
			listCnt= service.selectOfferingCpnCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		}
			
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		log.debug("### OfferingEtcController selectOfferingCpn END ###");
		
		return target;
	}
	
	/**
	 * ���հ��� ���۸� ������
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_dtl_list.st")
	public String selectOfferingDtl(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingDtl> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String acpos_tid 	= checkStr(request.getParameter("acpos_tid"), "");  //���հ��� �ŷ���ȣ
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //���۸� Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // ������� ����
		
		params.put("acpos_tid", acpos_tid);
		params.put("offer_id", offer_id);	
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		
		String target = "/harex/offering_dtl_list"; // ��� ������
		
		if("Y".equals(excel)){
			target = "/harex/offering_dtl_list_excel"; // �������� ���
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//��ȸ
			list = service.selectOfferingDtl(params);
			listCnt= service.selectOfferingDtlCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		log.debug("### OfferingEtcController selectOfferingDtl END ###");
		
		return target;
	}
	
	/**
	 * ���հ��� ���۸� ������
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */	
	@RequestMapping(value="/harex/offering_ms_dc_list.st")
	public String selectOfferingMsDc(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingMsDc START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingMsDc> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //���۸� Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // ������� ����
		
		params.put("offer_id", offer_id);	
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		//��ȸ
		
		String target = "/harex/offering_ms_dc_list"; // ��� ������
		
		if("Y".equals(excel)){
			target = "/harex/offering_ms_dc_list_excel"; // �������� ���
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//��ȸ
			list = service.selectOfferingMsDc(params);
			listCnt= service.selectOfferingMsDcCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 				
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		log.debug("### OfferingEtcController selectOfferingMsDc END ###");
		
		return target;
	}
	
	/**
	 * ���հ��� ���۸� ����� ��������
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_ms_save_list.st")
	public String selectOfferingMsSave(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingMsSave START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingMsSave> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //���۸� Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // ������� ����
		params.put("offer_id", offer_id);	
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		//��ȸ
		
		String target = "/harex/offering_ms_save_list"; // ��� ������
		
		if("Y".equals(excel)){
			target = "/harex/offering_ms_save_list_excel"; // �������� ���
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//��ȸ
			list = service.selectOfferingMsSave(params);
			listCnt= service.selectOfferingMsSaveCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 
		
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		log.debug("### OfferingEtcController selectOfferingMsSave END ###");
		
		return target;
	}
	
	/**
	 * ���հ��� ���۸� ����� ����������
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_ms_stamp_list.st")
	public String selectOfferingMsStamp(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingMsStamp START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingMsStamp> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //���۸� Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // ������� ����
		
		params.put("offer_id", offer_id);	
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		String target = "/harex/offering_ms_stamp_list"; // ��� ������
		
		if("Y".equals(excel)){
			target = "/harex/offering_ms_stamp_list_excel"; // �������� ���
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//��ȸ
			list = service.selectOfferingMsStamp(params);
			listCnt= service.selectOfferingMsStampCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		}
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		log.debug("### OfferingEtcController selectOfferingMsStamp END ###");
		
		return target;
	}
	
	/**
	 * ���հ��� ���۸� ����� ����� �������
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_ms_use_list.st")
	public String selectOfferingMsUse(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingMsUse START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingMsUse> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String offer_id 		= checkStr(request.getParameter("offer_id"), "");  //���۸� Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // ������� ����
		
		params.put("offer_id", offer_id);	
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		String target = "/harex/offering_ms_use_list"; // ��� ������
		
		if("Y".equals(excel)){
			target = "/harex/offering_ms_use_list_excel"; // �������� ���
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//��ȸ
			list = service.selectOfferingMsUse(params);
			listCnt= service.selectOfferingMsUseCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 
		
		
		//��ȸ
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		log.debug("### OfferingEtcController selectOfferingMsUse END ###");
		
		return target;
	}
	
	/**
	 * ���հ��� ���۸� ���� ����
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_order_list.st")
	public String selectOfferingOrder(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingOrder START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingOrder> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String acpos_tid 	= checkStr(request.getParameter("acpos_tid"), "");  	//���հ��� �ŷ���ȣ
		String offer_id 		= checkStr(request.getParameter("offer_id"), ""); 		//���۸� Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // ������� ����
		
		params.put("acpos_tid", acpos_tid);	
		params.put("offer_id", offer_id);	
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		//��ȸ
		
		String target = "/harex/offering_order_list"; // ��� ������
		
		if("Y".equals(excel)){
			target = "/harex/offering_order_list_excel"; // �������� ���
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//��ȸ
			list = service.selectOfferingOrder(params);
			listCnt= service.selectOfferingOrderCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		log.debug("### OfferingEtcController selectOfferingOrder END ###");
		
		return target;
	}
	
	/**
	 * ���հ��� ���۸� ���θ������
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_promotion_list.st")
	public String selectOfferingPromo(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingEtcController selectOfferingPromo START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingPromo> list = null;
		OfferingEtcService service = new OfferingEtcService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String promo_cd 	= checkStr(request.getParameter("promo_cd"), "");  	//����� Id
		String offer_id 		= checkStr(request.getParameter("offer_id"), ""); 		//���۸� Id
		String excel 	 	= checkStr(request.getParameter("excel"), "N"); // ������� ����
		
		params.put("promo_cd", promo_cd);	
		params.put("offer_id", offer_id);	
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 				// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 								// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0;
		
		//��ȸ
		
		String target = "/harex/offering_promotion_list"; // ��� ������
		
		if("Y".equals(excel)){
			target = "/harex/offering_promotion_list_excel"; // �������� ���
			params.remove("startRow");
			params.remove("endRow");
			params.remove("rowsPerPage");
		} 
		try{
			//��ȸ
			list = service.selectOfferingPromo(params);
			listCnt= service.selectOfferingPromoCnt(params);
		} catch(Exception e){
			log.debug("[ERROR] : " + e);
		} 	
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		log.debug("### OfferingEtcController selectOfferingPromo END ###");
		
		return target;
	}
	
}
