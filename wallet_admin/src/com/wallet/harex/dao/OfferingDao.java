package com.wallet.harex.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.mapper.OfferingMapper;
import com.wallet.harex.model.OfferingCoupon;
import com.wallet.harex.model.OfferingDetail;
import com.wallet.harex.model.OfferingInfo;
import com.wallet.harex.model.OfferingList;
import com.wallet.harex.model.OfferingMembership;

/*
 * Filename	: OfferingDao.java
 * Class	: com.wallet.harex.dao.OfferingDao
 * History	: 2012/09/08, mhjang, 작업구분 : 가맹점별 오퍼링 관리
 * Comment	:
 */
public class OfferingDao extends MybatisCilent implements OfferingMapper {

	public void commit() {
		sqlMapper.commit();
	}

	public void rollback() {
		sqlMapper.rollback();
	}
	
	/**
	 * 가맹점별 오퍼링 유형조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingList> selectOfferingCaseList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseList", params);
		
	}
	
	/**
	 * 가맹점별 오퍼링 유형조회 cnt
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public Integer selectOfferingCaseListCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseListCnt", params);
		
	}
	
	/**
	 * 가맹점 오퍼링 유형 상세정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingList selectOfferingCaseDetailInfo(HashMap<String, Object> params) {
		
		return (OfferingList) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseDetailInfo", params);
		
	}
	
	/**
	 * 가맹점 오퍼링 유형 상세정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingDetail selectOfferingCaseDetailUseYN(HashMap<String, Object> params) {
		
		return (OfferingDetail) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseDetailUseYN", params);
		
	}
	
	/**
	 * 가맹점 오퍼링 유형 상세정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingDetail selectOfferingCaseDetailOrder(HashMap<String, Object> params) {
		
		return (OfferingDetail) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseDetailOrder", params);
		
	}
	
	/**
	 * 가맹점 오퍼링 유형 상세정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingDetail selectOfferingCaseDetailByRuleId(HashMap<String, Object> params) {
		
		return (OfferingDetail) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseDetailByRuleId", params);
		
	}
		
	/**
	 * 쿠폰 팝업 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingCoupon> selectOfferingCouponPopup(HashMap<String, Object> params) {
		
		return  sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingCouponPopup", params);
		
	}
	
	/**
	 * 쿠폰 팝업 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingMembership selectOfferingMembershipPopup(HashMap<String, Object> params) {
		
		return (OfferingMembership) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingMembershipPopup", params);
		
	}	
		
	/**
	 * 쿠폰목록 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingCoupon> selectOfferingCouponList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingCouponList", params);
		
	}
	
	/**
	 * 쿠폰상세정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingCoupon selectOfferingCouponDetail(HashMap<String, Object> params) {
		
		return (OfferingCoupon) sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingCouponDetail", params);
		
	}
	
	/**
	 * 멤버십목록 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingMembership> selectOfferingMembershipList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingMembershipList", params);
		
	}
	
	/**
	 * 멤버십상세정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingMembership selectOfferingMembershipDetail(HashMap<String, Object> params) {
		
		return (OfferingMembership) sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingMembershipDetail", params);
		
	}
	
	/**
	 * @Method Name : insertOfferingRuleMst
	 * @Description : 오퍼링 규칙 Master Insert
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer insertOfferingRuleMst(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.insertOfferingRuleMst", params));
		return result;
	}
	
	/**
	 * @Method Name : insertOfferingRuleCpn
	 * @Description : 쿠폰 오퍼링 규칙 정보 Insert
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer insertOfferingRuleCpn(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.insertOfferingRuleCpn", params));
		return result;
	}
	
	/**
	 * @Method Name : insertOfferingRuleMst
	 * @Description : 멤버십할인 규칙 Master Insert
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer insertOfferingRuleMsDc(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.insertOfferingRuleMsDc", params));
		return result;
	}
	
	/**
	 * @Method Name : insertOfferingRuleMsUse
	 * @Description : 멤버십사용 규칙 Master Insert
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer insertOfferingRuleMsUse(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.insertOfferingRuleMsUse", params));
		return result;
	}
	
	/**
	 * @Method Name : deleteOfferingRuleMst
	 * @Description : 오퍼링 규칙 Master Delete
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteOfferingRuleMst(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.deleteOfferingRuleMst", params));
		return result;
	}
	
	/**
	 * @Method Name : deleteOfferingRuleCpn
	 * @Description : 쿠폰 오퍼링 규칙 정보 Delete
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteOfferingRuleCpn(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.deleteOfferingRuleCpn", params));
		return result;
	}
	
	/**
	 * @Method Name : deleteOfferingRuleMsDc
	 * @Description : 멤버십할인 규칙 Delete
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteOfferingRuleMsDc(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.deleteOfferingRuleMsDc", params));
		return result;
	}
	
	/**
	 * @Method Name : deleteOfferingRuleMsUse
	 * @Description : 멤버십사용 규칙 Delete
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteOfferingRuleMsUse(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.deleteOfferingRuleMsUse", params));
		return result;
	}
	
	/**
	 * @Method Name : updateOfferingRuleMst
	 * @Description : 오퍼링 규칙 Master Update
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer updateOfferingRuleMst(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.updateOfferingRuleMst", params));
		return result;
	}
	
	/**
	 * @Method Name : updateOfferingRuleCpn
	 * @Description : 쿠폰 오퍼링 규칙 정보 Update
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer updateOfferingRuleCpn(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.updateOfferingRuleCpn", params));
		return result;
	}
	
	/**
	 * @Method Name : updateOfferingRuleMsDc
	 * @Description : 멤버십할인 규칙 Update
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer updateOfferingRuleMsDc(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.updateOfferingRuleMsDc", params));
		return result;
	}
	
	/**
	 * @Method Name : updateOfferingRuleMsUse
	 * @Description : 멤버십사용 규칙 Update
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author mhjang
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public Integer updateOfferingRuleMsUse(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.insert("com.wallet.harex.mapper.OfferingMapper.updateOfferingRuleMsUse", params));
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<OfferingInfo> selectShopList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectShopList", params);
		
	}
}
