package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.CouponFinanceAccumulationMapper;
import com.wallet.membership.model.custom.CouponFinanceAccumulation;

public class CouponFinanceAccumulationDao extends MybatisCilent implements CouponFinanceAccumulationMapper{
	
	private String preMapperName = "com.wallet.membership.mapper.custom.CouponFinanceAccumulationMapper.";
	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	public void commit() {
		sqlMapper.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.14
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : selectCouponTotalAccumulationList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String, Object>
	 * @return : List<StaticMember>
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public List<CouponFinanceAccumulation> selectCouponFinanceAccumulationList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponFinanceAccumulationList", params);
	}
	
	/**
	 * @Method Name : selectCouponTotalAccumulationListCnt
	 * @Description : ������ ��� �� ��ȸ
	 * @param : HashMap<String, Object>
	 * @return : List<StaticMember>
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public int selectCouponFinanceAccumulationListCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne(preMapperName+"selectCouponFinanceAccumulationListCnt", params);
		
	}
	
}
