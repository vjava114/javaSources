package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.CouponAccumulationMapper;
import com.wallet.membership.model.custom.CouponAccumulation;
import com.wallet.membership.model.custom.CpnShareRatio;

public class CouponAccumulationDao extends MybatisCilent implements CouponAccumulationMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.CouponAccumulationMapper.";
	private String preMapperName2 = "com.wallet.membership.mapper.custom.CpnShareRatioMapper.";
	

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
	 * @since 2012.09.28
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : selectCouponAccumulationList
	 * @Description : 쿠폰별 정산 목록
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAccumulation>
	 * @author 김태리
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public List<CouponAccumulation> selectCouponAccumulationList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponAccumulationList", params);
	}
	
	/**
	 * @Method Name : selectCouponAccumulationListCnt
	 * @Description : 쿠폰별 정산 수 목록
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAccumulation>
	 * @author 김태리
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public int selectCouponAccumulationListCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne(preMapperName+"selectCouponAccumulationListCnt", params);
	}


	/**
	 * @Method Name : selectCouponAccumulationDetail
	 * @Description : 쿠폰별 정산 상세목록(분담율)
	 * @param : HashMap<String ,Object>
	 * @return : List<CpnShareRatio>
	 * @author 김태리
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public List<CpnShareRatio> selectCouponAccumulationDetail(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName2 + "selectCouponAccumulationDetail", params);
	}
	
}
