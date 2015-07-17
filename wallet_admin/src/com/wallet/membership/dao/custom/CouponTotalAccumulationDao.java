package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.model.custom.CouponTotalAccumulation;
import com.wallet.membership.mapper.custom.CouponTotalAccumulationMapper;

public class CouponTotalAccumulationDao extends MybatisCilent implements CouponTotalAccumulationMapper {
	
	private String preMapperName = "com.wallet.membership.mapper.custom.CouponTotalAccumulationMapper.";
	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
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
	 * @author 김태리
	 * @since 2012.09.14
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : selectCouponTotalAccumulationList
	 * @Description : 결제사 목록 조회
	 * @param : HashMap<String, Object>
	 * @return : List<StaticMember>
	 * @author 김태리
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public List<CouponTotalAccumulation> selectCouponTotalAccumulationList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponTotalAccumulationList", params);
	}
}
