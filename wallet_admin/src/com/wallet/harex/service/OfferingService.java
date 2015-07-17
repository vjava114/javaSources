package com.wallet.harex.service;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.harex.dao.OfferingDao;
import com.wallet.harex.model.OfferingInfo;
import com.wallet.harex.model.OfferingList;
import com.wallet.harex.model.OfferingCoupon;
import com.wallet.harex.model.OfferingDetail;
import com.wallet.harex.model.OfferingMembership;
import com.wallet.harex.model.PromotionInfo;


/*
 * Filename	: OfferingService.java
 * Class	: com.harex.offering.service.OfferingService
 * History	: 2012/09/08, mhjang, �۾����� : ���۸� ����
 * Comment	:
 */
public class OfferingService {
	
	private Logger log = Log.getLogger("logs");
	private final OfferingDao dao;
	
	public OfferingService() {
		dao = new OfferingDao();
	}
	
	public void commit(){
		dao.commit();
	}

	public void rollback() {
		dao.rollback();
	}
	
	/**
	 * @Method Name : selectOfferingCaseList
	 * @Description : �������� ���۸� ������ȸ select
	 * @param : HashMap<String ,Object>
	 * @return : List
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public List<OfferingList> selectOfferingCaseList(HashMap<String,Object> params) {
		
		List<OfferingList> result = null;

		result = dao.selectOfferingCaseList(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectOfferingCaseListCnt
	 * @Description : �������� ���۸� ������ȸ count
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public int selectOfferingCaseListCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectOfferingCaseListCnt(params).toString());
		
	}
	
	/**
	 * @Method Name : selectOfferingCaseDetailInfo
	 * @Description : �������� ���۸� ���� ������ select
	 * @param : HashMap<String ,Object>
	 * @return : List
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public OfferingList selectOfferingCaseDetailInfo(HashMap<String,Object> params) {
		
		OfferingList result = null;
		
		result = dao.selectOfferingCaseDetailInfo(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectOfferingCaseDetailUseYN
	 * @Description : �������� ���۸� ���� ������ select
	 * @param : HashMap<String ,Object>
	 * @return : List
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public OfferingDetail selectOfferingCaseDetailUseYN(HashMap<String,Object> params) {
		
		OfferingDetail result = null;
		
		result = dao.selectOfferingCaseDetailUseYN(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectOfferingCaseDetailOrder
	 * @Description : �������� ���۸� ���� ������ select
	 * @param : HashMap<String ,Object>
	 * @return : List
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public OfferingDetail selectOfferingCaseDetailOrder(HashMap<String,Object> params) {
		
		OfferingDetail result = null;
		
		result = dao.selectOfferingCaseDetailOrder(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectOfferingCaseDetailOrder
	 * @Description : �������� ���۸� ���� ������ select
	 * @param : HashMap<String ,Object>
	 * @return : List
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public OfferingDetail selectOfferingCaseDetailByRuleId(HashMap<String,Object> params) {
		
		OfferingDetail result = null;
		
		result = dao.selectOfferingCaseDetailByRuleId(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectOfferingCouponPopup
	 * @Description : ���� �˾� select
	 * @param : HashMap<String ,Object>
	 * @return : List
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public List<OfferingCoupon> selectOfferingCouponPopup(HashMap<String,Object> params) {
		
		List<OfferingCoupon> result = null;
		
		result = dao.selectOfferingCouponPopup(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectOfferingMembershipPopup
	 * @Description : ����� �˾� select
	 * @param : HashMap<String ,Object>
	 * @return : List
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public OfferingMembership selectOfferingMembershipPopup(HashMap<String,Object> params) {
		
		OfferingMembership result = null;
		
		result = dao.selectOfferingMembershipPopup(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectOfferingCouponList
	 * @Description : ������� select
	 * @param : HashMap<String ,Object>
	 * @return : List
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public List<OfferingCoupon> selectOfferingCouponList(HashMap<String,Object> params) {
		
		List<OfferingCoupon> result = null;
		
		result = dao.selectOfferingCouponList(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectOfferingCouponDetail
	 * @Description : ���� �� ���� select
	 * @param : HashMap<String ,Object>
	 * @return : OfferingCoupon
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public OfferingCoupon selectOfferingCouponDetail(HashMap<String,Object> params) {
		
		OfferingCoupon result = null;
		
		result = dao.selectOfferingCouponDetail(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectOfferingMembershipList
	 * @Description : ����ʸ�� select
	 * @param : HashMap<String ,Object>
	 * @return : List
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public List<OfferingMembership> selectOfferingMembershipList(HashMap<String,Object> params) {
		
		List<OfferingMembership> result = null;
		
		result = dao.selectOfferingMembershipList(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectOfferingMembershipDetail
	 * @Description : ����� �� ���� select
	 * @param : HashMap<String ,Object>
	 * @return : OfferingMembership
	 * @author mhjang
	 * @since 2012.09.19
	 */
	public OfferingMembership selectOfferingMembershipDetail(HashMap<String,Object> params) {
		
		OfferingMembership result = null;
		
		result = dao.selectOfferingMembershipDetail(params);
		
		return result;
	}
	
	/**
	 * @Method Name : insertOfferingRuleMst
	 * @Description : ���۸� ��Ģ Master Insert
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int insertOfferingRuleMst(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.insertOfferingRuleMst(params);
		return result;
	}
	
	/**
	 * @Method Name : insertOfferingRuleCpn
	 * @Description : ���� ���۸� ��Ģ ���� Insert
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int insertOfferingRuleCpn(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.insertOfferingRuleCpn(params);
		return result;
	}
	
	/**
	 * @Method Name : insertOfferingRuleMsDc
	 * @Description : ��������� ���۸� ��Ģ Insert
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int insertOfferingRuleMsDc(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.insertOfferingRuleMsDc(params);
		return result;
	}
	
	/**
	 * @Method Name : insertOfferingRuleMsUse
	 * @Description : ����ʻ�� ���۸� ��Ģ Insert
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int insertOfferingRuleMsUse(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.insertOfferingRuleMsUse(params);
		return result;
	}
	
	/**
	 * @Method Name : updateOfferingRuleMst
	 * @Description : ���۸� ��Ģ Master update
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int updateOfferingRuleMst(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.updateOfferingRuleMst(params);
		return result;
	}
	
	/**
	 * @Method Name : updateOfferingRuleCpn
	 * @Description : ���� ���۸� ��Ģ ���� Update
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int updateOfferingRuleCpn(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.updateOfferingRuleCpn(params);
		return result;
	}
	
	/**
	 * @Method Name : updateOfferingRuleMsDc
	 * @Description : ��������� ���۸� ��Ģ Update
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int updateOfferingRuleMsDc(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.updateOfferingRuleMsDc(params);
		return result;
	}
	
	/**
	 * @Method Name : updateOfferingRuleMsUse
	 * @Description : ����ʻ�� ���۸� ��Ģ Update
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int updateOfferingRuleMsUse(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.updateOfferingRuleMsUse(params);
		return result;
	}
	
	/**
	 * @Method Name : deleteOfferingRuleMst
	 * @Description : ���۸� ��Ģ Master delete
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int deleteOfferingRuleMst(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.deleteOfferingRuleMst(params);
		return result;
	}
	
	/**
	 * @Method Name : deleteOfferingRuleCpn
	 * @Description : ���� ���۸� ��Ģ ���� delete
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int deleteOfferingRuleCpn(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.deleteOfferingRuleCpn(params);
		return result;
	}
	
	/**
	 * @Method Name : deleteOfferingRuleMsDc
	 * @Description : ��������� ���۸� ��Ģ delete
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int deleteOfferingRuleMsDc(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.deleteOfferingRuleMsDc(params);
		return result;
	}
	
	/**
	 * @Method Name : deleteOfferingRuleMsUse
	 * @Description : ����ʻ�� ���۸� ��Ģ delete
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public int deleteOfferingRuleMsUse(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.deleteOfferingRuleMsUse(params);
		return result;
	}
	
	/**
	 * @Method Name : selectShopList
	 * @Description : ���۸� ���̽� ��� - ������ ���
	 * @param : HashMap<String ,Object>
	 * @return : List
	 * @author mhjang
	 * @since 2012.09.25
	 */
	public List<OfferingInfo> selectShopList(HashMap<String, Object>params) {
		
		List<OfferingInfo> result = null;
		result = dao.selectShopList(params);
		return result;
	}
}