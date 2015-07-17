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
 * History	: 2012/09/08, mhjang, �۾����� : �������� ���۸� ����
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
	 * �������� ���۸� ������ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingList> selectOfferingCaseList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseList", params);
		
	}
	
	/**
	 * �������� ���۸� ������ȸ cnt
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public Integer selectOfferingCaseListCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseListCnt", params);
		
	}
	
	/**
	 * ������ ���۸� ���� ������ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingList selectOfferingCaseDetailInfo(HashMap<String, Object> params) {
		
		return (OfferingList) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseDetailInfo", params);
		
	}
	
	/**
	 * ������ ���۸� ���� ������ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingDetail selectOfferingCaseDetailUseYN(HashMap<String, Object> params) {
		
		return (OfferingDetail) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseDetailUseYN", params);
		
	}
	
	/**
	 * ������ ���۸� ���� ������ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingDetail selectOfferingCaseDetailOrder(HashMap<String, Object> params) {
		
		return (OfferingDetail) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseDetailOrder", params);
		
	}
	
	/**
	 * ������ ���۸� ���� ������ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingDetail selectOfferingCaseDetailByRuleId(HashMap<String, Object> params) {
		
		return (OfferingDetail) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingCaseDetailByRuleId", params);
		
	}
		
	/**
	 * ���� �˾� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingCoupon> selectOfferingCouponPopup(HashMap<String, Object> params) {
		
		return  sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingCouponPopup", params);
		
	}
	
	/**
	 * ���� �˾� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingMembership selectOfferingMembershipPopup(HashMap<String, Object> params) {
		
		return (OfferingMembership) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingMapper.selectOfferingMembershipPopup", params);
		
	}	
		
	/**
	 * ������� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingCoupon> selectOfferingCouponList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingCouponList", params);
		
	}
	
	/**
	 * ���������� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingCoupon selectOfferingCouponDetail(HashMap<String, Object> params) {
		
		return (OfferingCoupon) sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingCouponDetail", params);
		
	}
	
	/**
	 * ����ʸ�� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingMembership> selectOfferingMembershipList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingMembershipList", params);
		
	}
	
	/**
	 * ����ʻ����� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public OfferingMembership selectOfferingMembershipDetail(HashMap<String, Object> params) {
		
		return (OfferingMembership) sqlMapper.selectList("com.wallet.harex.mapper.OfferingMapper.selectOfferingMembershipDetail", params);
		
	}
	
	/**
	 * @Method Name : insertOfferingRuleMst
	 * @Description : ���۸� ��Ģ Master Insert
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
	 * @Description : ���� ���۸� ��Ģ ���� Insert
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
	 * @Description : ��������� ��Ģ Master Insert
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
	 * @Description : ����ʻ�� ��Ģ Master Insert
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
	 * @Description : ���۸� ��Ģ Master Delete
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
	 * @Description : ���� ���۸� ��Ģ ���� Delete
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
	 * @Description : ��������� ��Ģ Delete
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
	 * @Description : ����ʻ�� ��Ģ Delete
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
	 * @Description : ���۸� ��Ģ Master Update
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
	 * @Description : ���� ���۸� ��Ģ ���� Update
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
	 * @Description : ��������� ��Ģ Update
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
	 * @Description : ����ʻ�� ��Ģ Update
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
