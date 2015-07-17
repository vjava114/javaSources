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
	 * @since 2012.09.28
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : selectCouponAccumulationList
	 * @Description : ������ ���� ���
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAccumulation>
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public List<CouponAccumulation> selectCouponAccumulationList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponAccumulationList", params);
	}
	
	/**
	 * @Method Name : selectCouponAccumulationListCnt
	 * @Description : ������ ���� �� ���
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAccumulation>
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public int selectCouponAccumulationListCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne(preMapperName+"selectCouponAccumulationListCnt", params);
	}


	/**
	 * @Method Name : selectCouponAccumulationDetail
	 * @Description : ������ ���� �󼼸��(�д���)
	 * @param : HashMap<String ,Object>
	 * @return : List<CpnShareRatio>
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	@SuppressWarnings("unchecked")
	public List<CpnShareRatio> selectCouponAccumulationDetail(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName2 + "selectCouponAccumulationDetail", params);
	}
	
}
