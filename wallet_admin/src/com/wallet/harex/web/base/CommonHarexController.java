package com.wallet.harex.web.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;
import com.wallet.harex.model.CouponList;
import com.wallet.harex.service.CouponListService;

@Controller
public class CommonHarexController extends CommonController {
	
	private Logger log = Log.getLogger("logs");

	/**
	 * �޺� ��������Ʈ
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_coupon.hx")
	public String commonCouponList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### CommonHarexController commonCouponList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<CouponList> couponList = null;
		CouponListService couponListService = new CouponListService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		JSONObject jObj = new JSONObject();
		
		String shopSel = checkStr(request.getParameter("shopSel"), "");
		
		params.put("shopSel", shopSel);	//���޻�ID

		/*��� ��������*/
		couponList = couponListService.getByCouponList(params);

		Iterator<CouponList> it = couponList.iterator();
		
		while(it.hasNext()) {
			CouponList one = it.next();
			jObj.put(one.getCpnId(), one.getCpnName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("couponList", couponList);
		request.setAttribute("JSONObject", jObj);
		
		log.debug("### CommonHarexController commonCouponList END ###");

		return "/common/result_page";

	}//End CommonCouponList()
	
}//END class