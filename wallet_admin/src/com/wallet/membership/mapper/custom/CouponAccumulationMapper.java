package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponAccumulation;
import com.wallet.membership.model.custom.CpnShareRatio;

public interface CouponAccumulationMapper {

	/**
	 * @Method Name : selectCouponAccumulationList
	 * @Description : ���������� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	List<CouponAccumulation> selectCouponAccumulationList(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectCouponAccumulationListCnt
	 * @Description : ���������� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	int selectCouponAccumulationListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectCouponStaticDayList
	 * @Description : ���������>������(�Ϻ�) ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	List<CpnShareRatio> selectCouponAccumulationDetail(HashMap<String, Object> params);
}
