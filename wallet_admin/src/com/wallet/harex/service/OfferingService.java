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
 * History	: 2012/09/08, mhjang, 작업구분 : 오퍼링 관리
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
	 * @Description : 가맹점별 오퍼링 유형조회 select
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
	 * @Description : 가맹점별 오퍼링 유형조회 count
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
	 * @Description : 가맹점별 오퍼링 유형 상세정보 select
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
	 * @Description : 가맹점별 오퍼링 유형 상세정보 select
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
	 * @Description : 가맹점별 오퍼링 유형 상세정보 select
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
	 * @Description : 가맹점별 오퍼링 유형 상세정보 select
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
	 * @Description : 쿠폰 팝업 select
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
	 * @Description : 멤버십 팝업 select
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
	 * @Description : 쿠폰목록 select
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
	 * @Description : 쿠폰 상세 정보 select
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
	 * @Description : 멤버십목록 select
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
	 * @Description : 멤버십 상세 정보 select
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
	 * @Description : 오퍼링 규칙 Master Insert
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
	 * @Description : 쿠폰 오퍼링 규칙 정보 Insert
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
	 * @Description : 멤버십할인 오퍼링 규칙 Insert
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
	 * @Description : 멤버십사용 오퍼링 규칙 Insert
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
	 * @Description : 오퍼링 규칙 Master update
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
	 * @Description : 쿠폰 오퍼링 규칙 정보 Update
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
	 * @Description : 멤버십할인 오퍼링 규칙 Update
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
	 * @Description : 멤버십사용 오퍼링 규칙 Update
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
	 * @Description : 오퍼링 규칙 Master delete
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
	 * @Description : 쿠폰 오퍼링 규칙 정보 delete
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
	 * @Description : 멤버십할인 오퍼링 규칙 delete
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
	 * @Description : 멤버십사용 오퍼링 규칙 delete
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
	 * @Description : 오퍼링 케이스 등록 - 가맹점 목록
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