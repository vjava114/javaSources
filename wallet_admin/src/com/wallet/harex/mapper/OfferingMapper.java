package com.wallet.harex.mapper;

import com.wallet.harex.model.OfferingInfo;
import com.wallet.harex.model.OfferingList;
import com.wallet.harex.model.OfferingCoupon;
import com.wallet.harex.model.OfferingDetail;
import com.wallet.harex.model.OfferingMembership;

import java.util.HashMap;
import java.util.List;

public interface OfferingMapper {

	List<OfferingList> selectOfferingCaseList(HashMap<String, Object> params);
	Integer selectOfferingCaseListCnt(HashMap<String, Object> params);
	
	OfferingList selectOfferingCaseDetailInfo(HashMap<String, Object> params);
	OfferingDetail selectOfferingCaseDetailOrder(HashMap<String, Object> params);
	OfferingDetail selectOfferingCaseDetailUseYN(HashMap<String, Object> params);
	OfferingDetail selectOfferingCaseDetailByRuleId(HashMap<String, Object> params);
	
	List<OfferingCoupon> selectOfferingCouponPopup(HashMap<String, Object> params);
	OfferingMembership selectOfferingMembershipPopup(HashMap<String, Object> params);

	List<OfferingCoupon> selectOfferingCouponList(HashMap<String, Object> params);
	OfferingCoupon selectOfferingCouponDetail(HashMap<String, Object> params);

	List<OfferingMembership> selectOfferingMembershipList(HashMap<String, Object> params);
	OfferingMembership selectOfferingMembershipDetail(HashMap<String, Object> params);
	
	Integer insertOfferingRuleMst(HashMap<String ,Object> params);
	Integer insertOfferingRuleCpn(HashMap<String, Object> params);
	Integer insertOfferingRuleMsDc(HashMap<String, Object> params);
	Integer insertOfferingRuleMsUse(HashMap<String, Object> params);
	
	Integer deleteOfferingRuleMst(HashMap<String ,Object> params);
	Integer deleteOfferingRuleCpn(HashMap<String, Object> params);
	Integer deleteOfferingRuleMsDc(HashMap<String, Object> params);
	Integer deleteOfferingRuleMsUse(HashMap<String, Object> params);
	
	Integer updateOfferingRuleMst(HashMap<String ,Object> params);
	Integer updateOfferingRuleCpn(HashMap<String, Object> params);
	Integer updateOfferingRuleMsDc(HashMap<String, Object> params);
	Integer updateOfferingRuleMsUse(HashMap<String, Object> params);
	
	List<OfferingInfo> selectShopList(HashMap<String, Object> params);
}