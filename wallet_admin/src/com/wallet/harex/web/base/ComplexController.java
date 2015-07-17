package com.wallet.harex.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.harex.common.JString;
import com.wallet.harex.model.ComplexInfo;
import com.wallet.harex.model.ComplexList;
import com.wallet.harex.model.ComplexShopHid;
import com.wallet.harex.model.ComplexUserList;
import com.wallet.harex.model.PromotionInfo;
import com.wallet.harex.model.PromotionList;
import com.wallet.harex.service.ComplexService;
import com.wallet.harex.service.PromotionService;

@Controller
public class ComplexController extends CommonController {
	
	private final String PAGE_CODE = "COMPLEX_SHOP";
	
	private Logger log = Log.getLogger("logs");
	
	/**
	 * ���հ��� �̿� ������ ��ȸ
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/complex_shop_list.hx")
	public String selectComplexShopList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexShopList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<ComplexList> list = null;
		ComplexService service = new ComplexService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String compId 			= checkStr(request.getParameter("compId"), "");  //���޻�ID
		String brandId 			= checkStr(request.getParameter("brandId"), "");  // �귣�� ID
		String regionType 	= checkStr(request.getParameter("regionType"), ""); // ����
		String shopId 			= checkStr(request.getParameter("shopId"), ""); // ������ID
		String shopName			= checkStr(request.getParameter("shopName"), ""); // ��������
	
		if("%".equals(compId)){
			compId = "";
		}
		if("%".equals(brandId)){
			brandId = "";
		}
		if("%".equals(regionType)){
			regionType = "";
		}
		if("%".equals(shopId)){
			shopId = "";
		}

		params.put("compId", compId);							
		params.put("brandId", brandId);
		params.put("regionType", regionType);
		params.put("shopId", shopId);
		params.put("shopName", shopName);
		
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
		list = service.selectComplexList(params);
		int ListCnt = service.selectComplexListCnt(params); 		// �� ��� ��
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("List", list);
		request.setAttribute("params", params);
		
		params.put("compId", compId == null || compId.equals("") ? "none":compId);							
		params.put("brandId", brandId == null || brandId.equals("") ? "none":brandId);
		params.put("regionType", regionType);
		params.put("shopId", shopId == null || shopId.equals("") ? "none":shopId);
		params.put("shopName", shopName);
		
		
		log.debug("### ComplexController ComplexShopList END ###");
		
		return "/harex/complex_shop_list";
	}
	
	/**
	 * ���հ��� �̿� ������ - ���޻� ������
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/complex_info.hx")
	public String selectComplexInfo(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexInfo START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		ComplexInfo complexInfo = null;
		ComplexService service = new ComplexService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		String mainNumber = "";		//��ǥ��ȭ
		String managerName = "";	//�����
		String mobilePhone = "";	//����� ����ó
		String email = "";				//����� �̸���
		
		String compId = checkStr(request.getParameter("compId"), "");

		params.put("compId", compId);							//���޻�ID
		
		//��ȸ
		complexInfo = service.selectComplexInfo(params);
		
		/**
		 *   ��ȣȭ ó��
		 */
		if(complexInfo != null){
			
			KTDBCipher dbCipher = new KTDBCipher();
			dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
			
			if (complexInfo.getMainNumber() != null && complexInfo.getMainNumber().length() > 15) {
				mainNumber = dbCipher.decoding(complexInfo.getMainNumber());	
			} else {
				mainNumber = complexInfo.getMainNumber();
			}
			
			if (complexInfo.getManagerName() != null && complexInfo.getManagerName().length() > 10) {
				managerName = dbCipher.decoding(complexInfo.getManagerName());	
			} else {
				managerName = complexInfo.getManagerName();
			}
			
			if (complexInfo.getMobilePhone() != null && complexInfo.getMobilePhone().length() > 15 ) {
				mobilePhone = dbCipher.decoding(complexInfo.getMobilePhone());	
			} else {
				mobilePhone = complexInfo.getMobilePhone();
			}
			
			// Email ������ ���� Ȯ�� 
			JString js = new JString(); 
			if (complexInfo.getEmail() != null && js.checkFormat("email", complexInfo.getEmail()) == false) {
				email = dbCipher.decoding(complexInfo.getEmail());	
			} else {
				email = complexInfo.getEmail();
			}
						
			complexInfo.setMainNumber(mainNumber);
			complexInfo.setManagerName(managerName);
			complexInfo.setMobilePhone(mobilePhone);
			
			// �̸��� ����ó�� ID �� 3���� '***' ó��
			complexInfo.setEmail(js.changeEmailTx(email)); 
			
		}
		
		request.setAttribute("ComplexInfo", complexInfo);
		
		log.debug("### ComplexController ComplexInfo END ###");
		
		return "/harex/complex_info";
	}
	
	
	/**
	 * �귣�庰 ������� ���θ�� ������ �˾�
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/complex_promotion_pop.hx")
	public String selectComplexPromotionPop(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexPromotionPop START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<PromotionList> list = null;
		PromotionService service = new PromotionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String shopId 				= checkStr(request.getParameter("pShopId"), "");
		String promoName 			= checkStr(request.getParameter("promoName"), "");
		
		params.put("shopId", shopId);
		params.put("promoName", promoName);
		
		//��ȸ
		list = service.selectPromotionList(params);
		
		request.setAttribute("ComplexPromotionPop", list);
		
		log.debug("### ComplexController ComplexPromotionPop END ###");
		
		return "/harex/complex_promotion_pop";
	}
	
	/**
	 * �����纰 ���հ��� ������ SHOP_ID ���ΰ���
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/complex_shop_hid_list.hx")
	public String selectComplexShopHidList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexShopHIDList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<ComplexShopHid> list = null;
		List<PromotionInfo> payCompList = null;
		
		ComplexService service = new ComplexService();
		PromotionService payService = new PromotionService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		payCompList = payService.selectPayCompList();
		
		
		String comp_id 				= checkStr(request.getParameter("comp_id"), "");
		String brand_id				= checkStr(request.getParameter("brand_id"), "");
		String region_type		= checkStr(request.getParameter("region_type"), "");
		String service_id			= checkStr(request.getParameter("service_id"), "");
		String comp_name			= checkStr(request.getParameter("comp_name"), "");
		
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
		
		params.put("comp_id", comp_id);							//���޻�ID
		params.put("brand_id", brand_id);						//�귣��ID
		params.put("region_type", region_type);			//����ID
		params.put("service_id", service_id);				//����ID
		params.put("comp_name", comp_name);					//��������
		
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
		list = service.selectComplexShopHidList(params);
		int ListCnt = service.selectComplexShopHidListCnt(params); 		// �� ��� ��
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		request.setAttribute("pageCode", PAGE_CODE);

		request.setAttribute("comp_id", comp_id == null || comp_id.equals("") ? "none":comp_id);
		request.setAttribute("brand_id", brand_id == null || brand_id.equals("") ? "none":brand_id);
		request.setAttribute("region_type", region_type);
		request.setAttribute("service_id", service_id);
		request.setAttribute("comp_name", comp_name);
		
		request.setAttribute("ComplexShopHidList", list);
		request.setAttribute("payCompList", payCompList);
		
		log.debug("### ComplexController ComplexShopHIDList END ###");
		
		return "/harex/complex_shop_hid_list";
	}
	
	/**
	 * ���հ��� ȸ����������
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/complex_user_list.hx")
	public String selectComplexUserList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController selectComplexUserList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<ComplexUserList> list = null;
		ComplexService service = new ComplexService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String tCode 			= checkStr(request.getParameter("tCode"), "");  //���޻�ID
		String phoneNo    = checkStr(request.getParameter("phoneNo"), "");  //���޻�ID
		
		if("%".equals(tCode)){
			tCode = "";
		}
		
		params.put("tCode", tCode);					// ��Ż� �ڵ�
		params.put("phoneNo", phoneNo);				// ��ȭ��ȣ
		
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
		list = service.selectComplexUserList(params);
		int ListCnt = service.selectComplexUserListCnt(params); 		// �� ��� ��
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		request.setAttribute("selectComplexUserList", list);
		request.setAttribute("params", params);
		
		HashMap<String,Object> logParams = new HashMap<String,Object>();
		MwAdAccessLogService logSvr = new MwAdAccessLogService();
		
		try {
			//�α�ó��/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
		
		log.debug("### ComplexController selectComplexUserList END ###");
		
		return "/harex/complex_user_list";
	}
	
	/**
	 * SHOP_ID - H_ID ���� �˾�
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return
	 */
	@RequestMapping(value="/harex/complex_shop_hid_register.hx")
	public String updateComplexShopHidRegister(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexShopHidRegister START ###");
		
		String k_shop_id 				= checkStr(request.getParameter("k_shop_id"), "");
		String k_shop_name			= checkStr(request.getParameter("k_shop_name"), "");
		String pk_shop_id				= checkStr(request.getParameter("pk_shop_id"), "");
		String pk_shop_name			= checkStr(request.getParameter("pk_shop_name"), "");
		String service_id				= checkStr(request.getParameter("service_id"), "");
		String nowPage					= request.getParameter("nowPage");

		request.setAttribute("k_shop_id", k_shop_id);
		request.setAttribute("k_shop_name", k_shop_name);
		request.setAttribute("pk_shop_id", pk_shop_id);
		request.setAttribute("pk_shop_name", pk_shop_name);
		request.setAttribute("service_id", service_id);
		request.setAttribute("nowPage", nowPage);
		
		log.debug("### ComplexController ComplexShopHidRegister END ###");
		
		return "/harex/complex_shop_hid_register";
	}
	
	/**
	 * SHOP_ID - H_ID ���� ó��
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return
	 */
	@RequestMapping(value="/harex/complex_shop_hid_register_action.hx")
	public String updateComplexShopHidRegisterAction(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### ComplexController ComplexShopHidRegisterAction START ###");
		
		//����� - TODO ���� ó��
		String regUser	= "tester";
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		ComplexService service = new ComplexService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String h_shop_id 			= checkStr(request.getParameter("h_shop_id"), "");
		String k_shop_id 			= checkStr(request.getParameter("k_shop_id"), "");
		String pk_shop_id			= checkStr(request.getParameter("pk_shop_id"), "");
		String service_id			= checkStr(request.getParameter("service_id"), "");
		String nowPage				= request.getParameter("nowPage");
		
		request.setAttribute("h_shop_id", h_shop_id);
		request.setAttribute("k_shop_id", k_shop_id);
		request.setAttribute("pk_shop_id", pk_shop_id);
		
		params.put("h_shop_id", h_shop_id);
		params.put("k_shop_id", k_shop_id);
		params.put("pk_shop_id", pk_shop_id);
		params.put("regUser", regUser);
		
		try {
			//H-ID ���
			int result = 0;
			result = service.updateComplexShopHid(params);
			
			if (result == 0) {
				//rollback
				service.rollback();
				
				//����ó��
				request.setAttribute("actResult", "0");
				request.setAttribute("targetUrl", "/harex/complex_shop_hid_register_action.hx");
				
				return "common/result_message";
			} else {
				//commit
				service.commit();
				
				request.setAttribute("actResult", "1");
				request.setAttribute("targetUrl", "/harex/complex_shop_hid_list.hx?service_id="+service_id+"&nowPage="+nowPage);

				return "common/result_message_popup";
			}
		} catch (Exception e) {
		//rollback
			service.rollback();
			e.printStackTrace();
			log.debug(e);
			
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/harex/complex_shop_hid_register_action.hx");
			
			return "common/result_message";
		}
	}
	
	/**
	 * SHOP_ID - H_ID �ߺ�Ȯ��
	 * @param 	
	 * @param 	
	 * @return		SHOP_ID - H_ID �ߺ�Ȯ��
	 */
	@RequestMapping(value="/harex/complex_shop_hid_register_check.hx")
	public String selectComplexShopHidRegisterCheck(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SystemController selectComplexShopHidRegisterCheck START ###");
		
		String h_shop_id = checkStr(request.getParameter("h_shop_id"), "");
		
		ComplexService service = new ComplexService();
		JSONObject jObj = new JSONObject();
		String result = "";
		
		result = service.selectComplexShopHidRegisterCheck(h_shop_id);
		
		jObj.put("result", result);
		
		log.debug("### SystemController selectComplexShopHidRegisterCheck END ###");
		
		/* SET ATTRIBUTEs */
		request.setAttribute("result", result);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}
}
