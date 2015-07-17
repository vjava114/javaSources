package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.CouponSettlementAccumulationMapper;
import com.wallet.membership.model.custom.CouponSettlementAccumulation;

public class CouponSettlementAccumulationDao extends MybatisCilent implements CouponSettlementAccumulationMapper{
	
	private String preMapperName = "com.wallet.membership.mapper.custom.CouponSettlementAccumulationMapper.";
	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ±èÅÂ¸®
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
	 * @author ±èÅÂ¸®
	 * @since 2012.09.14
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : selectCouponSettlementAccumulationList
	 * @Description : °áÁ¦»ç ¸ñ·Ï Á¶È¸
	 * @param : HashMap<String, Object>
	 * @return : List<StaticMember>
	 * @author ±è¿Ï¼·
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public List<CouponSettlementAccumulation> selectCouponSettlementAccumulationList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponSettlementAccumulationList", params);
	}

	/**
	 * @Method Name : selectCouponSettlementAccumulationListCnt
	 * @Description : °áÁ¦»ç ¸ñ·Ï ¼ö Á¶È¸
	 * @param : HashMap<String, Object>
	 * @return : List<StaticMember>
	 * @author ±è¿Ï¼·
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public int selectCouponSettlementAccumulationListCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne(preMapperName+"selectCouponSettlementAccumulationListCnt", params);
		
	}
}
