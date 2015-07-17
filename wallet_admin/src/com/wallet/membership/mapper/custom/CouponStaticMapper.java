package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponStatic;

public interface CouponStaticMapper {
	/**
	 * @Method Name : selectCouponStaticDayList
	 * @Description : ���������>������(�Ϻ�) ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	List<CouponStatic> selectCouponStaticDayList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectCouponStaticMonthList
	 * @Description : ���������>������(����) ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	List<HashMap<String, Object>> selectCouponStaticMonthList(HashMap<String ,Object> params);
	
	
}
