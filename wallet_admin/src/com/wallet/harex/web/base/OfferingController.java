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

import com.wallet.harex.model.OfferingCoupon;
import com.wallet.harex.model.OfferingInfo;
import com.wallet.harex.model.OfferingList;
import com.wallet.harex.model.OfferingDetail;
import com.wallet.harex.model.OfferingMembership;
import com.wallet.harex.model.PromotionInfo;
import com.wallet.harex.service.CommonService;
import com.wallet.harex.service.OfferingService;
import com.wallet.harex.service.PromotionService;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;

@Controller
public class OfferingController extends CommonController {
	
	private final String PAGE_CODE = "OFFERING_CASE";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * ������ ���۸� ���� ����Ʈ
	 * @param 	comp_id					���޻� ID
	 * @param 	upper_comp_id		�귣�� ID
	 * @param 	comp_name				��������
	 * @param 	region_type			�����ڵ�
	 * @param 	service_id			������ ���񽺸�
	 * @return	list						������ ���۸� ���� ����Ʈ
	 */
	@RequestMapping(value="/harex/offering_case_list.hx")
	public String selectOfferingCaseList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingController offeringCaseList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingList> list = null;
		HashMap<String,Object> params = new HashMap<String,Object>();
		OfferingService service = new OfferingService();
		PromotionService payService = new PromotionService();
		List<PromotionInfo> payCompList = null;
		payCompList = payService.selectPayCompList();
		
		String comp_id 			= checkStr(request.getParameter("comp_id"), "");
		String brand_id			= checkStr(request.getParameter("brand_id"), "");
		String region_type		= checkStr(request.getParameter("region_type"), "");
		String comp_name		= checkStr(request.getParameter("comp_name"), "");
		String service_id 			= checkStr(request.getParameter("service_id"), "");
		
		

		if("%".equals(comp_id)){
			comp_id = "";
		}
		if("%".equals(brand_id)){
			brand_id = "";
		}
		if("%".equals(region_type)){
			region_type = "";
		}
		if("".equals(service_id) && payCompList != null){
			service_id = payCompList.get(0).getCode();
		}

		params.put("comp_id", comp_id);					//���޻�ID
		params.put("brand_id", brand_id);				//�귣��ID
		params.put("region_type", region_type);			//����ID
		params.put("comp_name", comp_name);				//��������
		params.put("service_id", service_id);				//������ ����
		
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
		list = service.selectOfferingCaseList(params);
		int offeringCaseListCnt = service.selectOfferingCaseListCnt(params); 		// �� ��� ��
		
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, offeringCaseListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("comp_id", comp_id == null || comp_id.equals("") ? "none":comp_id);
		request.setAttribute("brand_id", brand_id == null || brand_id.equals("") ? "none":brand_id);
		request.setAttribute("region_type", region_type);
		request.setAttribute("comp_name", comp_name);
		request.setAttribute("service_id", service_id);
		request.setAttribute("payCompList", payCompList);

		request.setAttribute("OfferingCaseList", list);
		
		log.debug("### OfferingController offeringCaseList END ###");
		
		return "harex/offering_case_list";
	}
	
	/**
	 * ������ ���۸� ���� ������
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list						������ ���۸� ���� ������
	 */
	@RequestMapping(value="/harex/offering_case_detail.hx")
	public String selectOfferingCaseDetail(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingController offeringCaseDetail START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		OfferingList info = null;
		OfferingDetail useDetail = null;
		OfferingDetail orderDetail = null;
		OfferingService service = new OfferingService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String shopId 		= checkStr(request.getParameter("shopId"), "");
		String serviceId 	= checkStr(request.getParameter("serviceId"), "");
		int nowPage 		= Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		
		params.put("shopId", shopId);
		params.put("serviceId", serviceId);
		
		//��ȸ
		info = service.selectOfferingCaseDetailInfo(params);
		useDetail = service.selectOfferingCaseDetailUseYN(params);
		orderDetail = service.selectOfferingCaseDetailOrder(params);
		
		request.setAttribute("OfferingCaseDetailInfo", info);
		request.setAttribute("OfferingCaseDetailUseYN", useDetail);
		request.setAttribute("OfferingCaseDetailOrder", orderDetail);
		request.setAttribute("nowPage", nowPage);

		log.debug("### OfferingController offeringCaseDetail END ###");
		
		return "harex/offering_case_detail";
	}

	/**
	 * ������ ���۸� ���� ���
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return
	 */
	@RequestMapping(value="/harex/offering_case_detail_register.hx")
	public String insertOfferingCaseDetailRegister(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		return "harex/offering_case_detail_register";
	}

	/**
	 * ������ ���۸� ���� ���
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return
	 */
	@RequestMapping(value="/harex/offering_case_detail_register_action.hx")
	public String insertOfferingCaseDetailRegisterAction(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingController insertOfferingCaseDetailRegisterAction START ###");
		
		// ����� - TODO ���� ó��
		String regUser	= getSessionMgrId(request);
		
		// �Ķ���� �ʱ�ȭ 
		String[] k_shop_id = request.getParameterValues("k_shop_id");
		String cpn_order = checkStr(request.getParameter("cpn_order"), "");
		String calc_base_cd1 = checkStr(request.getParameter("calc_base_cd1"), "");
		String fix_rate_priority = checkStr(request.getParameter("fix_rate_priority"), "%");
		String max_dup_cnt = checkStr(request.getParameter("max_dup_cnt"), "%");
		
		String ms_dc_order = checkStr(request.getParameter("ms_dc_order"), "%");
		String ms_use_order = request.getParameter("ms_use_order");
		String calc_order = request.getParameter("calc_order");
		
		String calc_base_cd2 = checkStr(request.getParameter("calc_base_cd2"), "%");
		String calc_base_cd3 = checkStr(request.getParameter("calc_base_cd3"), "%");
		String olleh_ms_use_yn = checkStr(request.getParameter("olleh_ms_use_yn"), "");
		String ms_save_self_yn = checkStr(request.getParameter("ms_save_self_yn"), "");
		
		//��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		OfferingService service = new OfferingService();

		int result = 0;

		CommonService commonService = new CommonService();
		String ruleId = commonService.getRuleId().trim();
		
		try {
			for(int i=0; i<k_shop_id.length; i++) {
				params.clear();
				params.put("k_shop_id", k_shop_id[i]);				// ���������̵�
					
				params.put("rule_id", ruleId);						// ���۸���ID
				params.put("cpn_order", cpn_order);					// ���� ���۸� ����
				params.put("calc_base_cd1", calc_base_cd1);			// ���� �����رݾ��ڵ�
				params.put("fix_rate_priority", fix_rate_priority);	// ����/���� �켱����
				params.put("max_dup_cnt", max_dup_cnt);				// ���� �ߺ����� �ִ밹��
				
				params.put("ms_dc_order", ms_dc_order);				// ����� ���� ���۸� ����
				params.put("ms_use_order", ms_use_order);			// ����� ��� ���۸� ����
				params.put("calc_order", calc_order);				// 
				
				params.put("calc_base_cd2", calc_base_cd2);			// ��������� �����رݾ��ڵ�
				params.put("calc_base_cd3", calc_base_cd3);			// �������� �����رݾ��ڵ�
				params.put("olleh_ms_use_yn", olleh_ms_use_yn);		// �÷� ����� ��뿩��
				params.put("ms_save_self_yn", ms_save_self_yn);		// ��������� ��ü ���� ����
				params.put("regUser", regUser);
				
				result += service.insertOfferingRuleMst(params);
				result += service.insertOfferingRuleCpn(params);
				result += service.insertOfferingRuleMsDc(params);
				result += service.insertOfferingRuleMsUse(params);
			}
			
			if (result == 0) {
				//rollback
				service.rollback();
				
				//����ó��
				request.setAttribute("actResult", "0");
				request.setAttribute("targetUrl", "/harex/offering_case_detail_register.hx");
				
				return "common/result_message";
			} else {
				//commit
				service.commit();
				
				request.setAttribute("actResult", "1");
				request.setAttribute("targetUrl", "/harex/offering_case_list.hx");
				
				return "common/result_message";
			}
			
		} catch(Exception e) {
			//rollback
			service.rollback();
			e.printStackTrace();
			log.debug(e);
			
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/harex/offering_case_detail_register.hx");
			
			return "common/result_message";
		}
	}

	/**
	 * ������ ���۸� ���� ���� (���� ��ȸ)
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list, couponDetail		�������, ���հ��� ���� ������
	 */
	@RequestMapping(value="/harex/offering_case_detail_update.hx")
	public String selectOfferingCaseDetailUpdate(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingController selectOfferingCaseDetailUpdate START ###");
		
		int nowPage 		= Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		OfferingList info = null;
		OfferingDetail orderDetail = null;
		OfferingService service = new OfferingService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String shopId = checkStr(request.getParameter("pShopId"), "");
		String serviceId = checkStr(request.getParameter("serviceId"), "");
		params.put("shopId", shopId);
		params.put("serviceId", serviceId);
		
		//��ȸ
		info = service.selectOfferingCaseDetailInfo(params);
		orderDetail = service.selectOfferingCaseDetailOrder(params);
		
		request.setAttribute("OfferingCaseDetailInfo", info);
		request.setAttribute("OfferingCaseDetailOrder", orderDetail);
		request.setAttribute("nowPage", nowPage);
		
		log.debug("### OfferingController selectOfferingCaseDetailUpdate END ###");
		
		return "harex/offering_case_detail_update";
	}
	
	/**
	 * ������ ���۸� ���� ���� ó�� (������ ����)
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list, couponDetail		�������, ���հ��� ���� ������
	 */
	@RequestMapping(value="/harex/offering_case_detail_update_action.hx")
	public String updateOfferingCaseDetailAction(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingController updateOfferingCaseDetailAction START ###");
		
		int nowPage 		= Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		
		//����� - TODO ���� ó��
		String regUser	= getSessionMgrId(request);
			
		// �Ķ���� �ʱ�ȭ
		String serviceId = checkStr(request.getParameter("serviceId"), "");
		String k_shop_id = checkStr(request.getParameter("k_shop_id"), "");
		String cpn_order = checkStr(request.getParameter("cpn_order"), "");
		String calc_base_cd1 = checkStr(request.getParameter("calc_base_cd1"), "");
		String fix_rate_priority = checkStr(request.getParameter("fix_rate_priority"), "%");
		String max_dup_cnt = checkStr(request.getParameter("max_dup_cnt"), "%");
		
		String ms_dc_order = checkStr(request.getParameter("ms_dc_order"), "%");
		String ms_use_order = request.getParameter("ms_use_order");
		String calc_order = request.getParameter("calc_order");
		
		String calc_base_cd2 = checkStr(request.getParameter("calc_base_cd2"), "%");
		String calc_base_cd3 = checkStr(request.getParameter("calc_base_cd3"), "%");
		String olleh_ms_use_yn = checkStr(request.getParameter("olleh_ms_use_yn"), "");
		String ms_save_self_yn = checkStr(request.getParameter("ms_save_self_yn"), "");
		String use_yn = checkStr(request.getParameter("use_yn"), "");
		
		if( olleh_ms_use_yn == null || "".equals(olleh_ms_use_yn)) {
			olleh_ms_use_yn = "N";
		}
		if( ms_save_self_yn == null || "".equals(ms_save_self_yn)) {
			ms_save_self_yn = "N";
		}
		
		//��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		OfferingService service = new OfferingService();

		int result = 0;

		CommonService commonService = new CommonService();
		String ruleId = commonService.getRuleId().trim();
		
		try {
			params.put("k_shop_id", k_shop_id);
			
			params.put("rule_id", ruleId);	// ���۸���ID
			params.put("cpn_order", cpn_order);	// ���޻�ID
			params.put("calc_base_cd1", calc_base_cd1);	// �귣��ID
			params.put("fix_rate_priority", fix_rate_priority);	// �����ID
			params.put("max_dup_cnt", max_dup_cnt);	// ������
			
			params.put("ms_dc_order", ms_dc_order);	// �������� (�����ᱸ��)
			params.put("ms_use_order", ms_use_order);
			params.put("calc_order", calc_order);
			
			params.put("calc_base_cd2", calc_base_cd2);	// USIM��������
			params.put("calc_base_cd3", calc_base_cd3);	// USIM ID
			params.put("olleh_ms_use_yn", olleh_ms_use_yn);	// �������α���
			params.put("ms_save_self_yn", ms_save_self_yn);	// ������������
			params.put("use_yn", use_yn);	//��뿩��
			
			params.put("regUser", regUser);
			
			result =+ service.updateOfferingRuleMst(params);
			result =+ service.updateOfferingRuleCpn(params);
			result =+ service.updateOfferingRuleMsDc(params);
			result =+ service.updateOfferingRuleMsUse(params);
			
			if (result == 0) {
				//rollback
				service.rollback();
				
				//����ó��
				request.setAttribute("actResult", "0");
				request.setAttribute("targetUrl", "/harex/offering_case_detail_update.hx?nowPage="+nowPage+"&service_id="+serviceId);
				
				return "common/result_message";
			} else {
				//commit
				service.commit();
				
				request.setAttribute("actResult", "1");
				request.setAttribute("targetUrl", "/harex/offering_case_list.hx?nowPage="+nowPage+"&service_id="+serviceId);
				
				return "common/result_message";
			}
			
		} catch(Exception e) {
			//rollback
			service.rollback();
			e.printStackTrace();
			log.debug(e);
			
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/harex/offering_case_detail_update.hx?nowPage="+nowPage+"&service_id="+serviceId);
			
			return "common/result_message";
		}
	}
	
	/**
	 * ������ ���۸� ���� ����
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list, couponDetail		�������, ���հ��� ���� ������
	 */
	@RequestMapping(value="/harex/offering_case_detail_update_all.hx")
	public String updateAllOfferingCaseDetail(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingController updateAllOfferingCaseDetail START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		OfferingList info = null;
		OfferingDetail orderDetail = null;
		
		OfferingService service = new OfferingService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String rule_id = checkStr(request.getParameter("rule_id"), "");
		params.put("ruleId", rule_id);
		
		//��ȸ
		info = service.selectOfferingCaseDetailInfo(params);
		orderDetail = service.selectOfferingCaseDetailByRuleId(params);
		
		request.setAttribute("OfferingCaseDetailInfo", info);
		request.setAttribute("OfferingCaseDetailOrder", orderDetail);
		
		log.debug("### OfferingController updateAllOfferingCaseDetail END ###");
		
		return "harex/offering_case_detail_update_all";
	}
	
	/**
	 * ������ ���۸� ���� ���� (RULE_ID ����-������ ��ü)
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return
	 */
	@RequestMapping(value="/harex/offering_case_detail_update_all_action.hx")
	public String updateAllOfferingCaseDetailAction(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		// ����� - TODO ���� ó��
		String regUser	= getSessionMgrId(request);
		
		// �Ķ���� �ʱ�ȭ 
		String[] k_shop_id				= request.getParameterValues("k_shop_id");
		String cpn_order					= checkStr(request.getParameter("cpn_order"), "");
		String calc_base_cd1			= checkStr(request.getParameter("calc_base_cd1"), "");
		String fix_rate_priority	= checkStr(request.getParameter("fix_rate_priority"), "%");
		String max_dup_cnt = checkStr(request.getParameter("max_dup_cnt"), "%");
		
		String ms_dc_order = checkStr(request.getParameter("ms_dc_order"), "%");
		String ms_use_order = request.getParameter("ms_use_order");
		String calc_order = request.getParameter("calc_order");
		
		String calc_base_cd2 = checkStr(request.getParameter("calc_base_cd2"), "%");
		String calc_base_cd3 = checkStr(request.getParameter("calc_base_cd3"), "%");
		String olleh_ms_use_yn = checkStr(request.getParameter("olleh_ms_use_yn"), "");
		String ms_save_self_yn = checkStr(request.getParameter("ms_save_self_yn"), "");
		
		String rule_id = checkStr(request.getParameter("rule_id"), "");
		
		//��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		OfferingService service = new OfferingService();

		int result = 0;

		CommonService commonService = new CommonService();
		String ruleId = commonService.getRuleId().trim();
		
		try {
			for(int i=0; i<k_shop_id.length; i++) {
				params.clear();
				params.put("k_shop_id", k_shop_id[i]);
				
				params.put("rule_id", ruleId);	// ���۸���ID
				params.put("cpn_order", cpn_order);	// ���޻�ID
				params.put("calc_base_cd1", calc_base_cd1);	// �귣��ID
				params.put("fix_rate_priority", fix_rate_priority);	// �����ID
				params.put("max_dup_cnt", max_dup_cnt);	// ������
				
				params.put("ms_dc_order", ms_dc_order);	// �������� (�����ᱸ��)
				params.put("ms_use_order", ms_use_order);
				params.put("calc_order", calc_order);
				
				params.put("calc_base_cd2", calc_base_cd2);	// USIM��������
				params.put("calc_base_cd3", calc_base_cd3);	// USIM ID
				params.put("olleh_ms_use_yn", olleh_ms_use_yn);	// �������α���
				params.put("ms_save_self_yn", ms_save_self_yn);	// ������������
				params.put("rule_id", rule_id);	// ���۸��� ID
				
				params.put("regUser", regUser);
				
				result += service.deleteOfferingRuleMst(params);
				result += service.deleteOfferingRuleCpn(params);
				result += service.deleteOfferingRuleMsDc(params);
				result += service.deleteOfferingRuleMsUse(params);
				
				result += service.insertOfferingRuleMst(params);
				result += service.insertOfferingRuleCpn(params);
				result += service.insertOfferingRuleMsDc(params);
				result += service.insertOfferingRuleMsUse(params);
			}
			
			if (result == 0) {
				//rollback
				service.rollback();
				
				//����ó��
				request.setAttribute("actResult", "0");
				request.setAttribute("targetUrl", "/harex/offering_case_detail_update_all.hx");
				
				return "common/result_message";
			} else {
				//commit
				service.commit();
				
				request.setAttribute("actResult", "1");
				request.setAttribute("targetUrl", "/harex/offering_case_list.hx");
				
				return "common/result_message";
			}
			
		} catch(Exception e) {
			//rollback
			service.rollback();
			e.printStackTrace();
			log.debug(e);
			
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/harex/offering_case_detail_update_all.hx");
			
			return "common/result_message";
		}
	}
	
	/**
	 * ���� ���� �˾�
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	couponPopup		���� ���� ��ȸ
	 */
	@RequestMapping(value="/harex/offering_coupon_popup.hx")
	public String selectOfferingCouponPopup(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingController offeringCouponPopup START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<OfferingCoupon> couponPopup = null;
		OfferingService service = new OfferingService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String shopId	= checkStr(request.getParameter("pShopId"), "");
		params.put("shopId", shopId);
		
		//��ȸ
		couponPopup = service.selectOfferingCouponPopup(params);
		
		request.setAttribute("OfferingCouponPopup", couponPopup);
		
		log.debug("### OfferingController offeringCouponPopup END ###");
		
		return "harex/offering_coupon_popup";
	}
	
	/**
	 * ����� �������� popup
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	membershipPopup
	 */
	@RequestMapping(value="/harex/offering_membership_popup.hx")
	public String selectOfferingMembershipPopup(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingController offeringMembershipPopup START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		OfferingMembership membershipPopup = null;
		OfferingService service = new OfferingService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String shopId	= checkStr(request.getParameter("pShopId"), "");

		params.put("shopId", shopId);
		
		//��ȸ
		membershipPopup = service.selectOfferingMembershipPopup(params);
		
		request.setAttribute("OfferingMembershipPopup", membershipPopup);
		
		log.debug("### OfferingController offeringMembershipPopup END ###");
		
		return "harex/offering_membership_popup";
	}
	
	/**
	* ���۸� ������ ����
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/offering_sel_shop_pop.hx")
	public String selectOfferingShopSel(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### OfferingController selectOfferingShopSel START ###");
		
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		OfferingService service = new OfferingService();
		List<OfferingInfo> list = null;
		
		String pCompId 			= checkStr(request.getParameter("comp_sel_id"), "");
		String pCompNm 			= checkStr(request.getParameter("comp_sel_nm"), "");
		String pBrandId 		= checkStr(request.getParameter("brand_sel_id"), "");
		String pBrandNm 		= checkStr(request.getParameter("brand_sel_nm"), "");
		String pShopNm 			= checkStr(request.getParameter("shop_nm"), "");
		String pRegionType 	= checkStr(request.getParameter("region_type"), "");
		
		params.put("comp_id", 	pCompId);			//���޻�ID
		params.put("comp_nm",   pCompNm);			//���޻��
		params.put("brand_id",	pBrandId);		//�귣��ID
		params.put("brand_nm",  pBrandNm);		//�귣���
		params.put("shop_nm", 	pShopNm);			//��������
		
		if("%".equals(pRegionType))  pRegionType = "";
		params.put("region_type", 	pRegionType);		//�����ڵ�
		
		list = service.selectShopList(params);
		
		request.setAttribute("params", params);
		request.setAttribute("List", list);

		log.debug("### OfferingController selectOfferingShopSel END ###");
		
		return "/harex/offering_sel_shop_pop";
	}
}
