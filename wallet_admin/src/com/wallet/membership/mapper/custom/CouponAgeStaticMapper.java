package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponAgeStatic;

public interface CouponAgeStaticMapper {
	/**
	 * @Method Name : selectCouponAgeStaticDayList
	 * @Description : ���������>����/���� �ٿ�ε�(�Ϻ�) ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAgeStatic>
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	List<CouponAgeStatic> selectCouponAgeStaticDayList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectCouponAgeStaticMonthList
	 * @Description : ���������>����/���� �ٿ�ε�(����) ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	List<HashMap<String, Object>> selectCouponAgeStaticMonthList(HashMap<String ,Object> params);
	
	
}
