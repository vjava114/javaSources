package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponOsStatic;

public interface CouponOsStaticMapper {
	/**
	 * @Method Name : selectCouponOsStaticDayList
	 * @Description : ���������>�ܸ���OS/��Ż纰�ٿ�ε�(�Ϻ�) ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponOsStatic>
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	List<CouponOsStatic> selectCouponOsStaticDayList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectCouponOsStaticMonthList
	 * @Description : ���������>�ܸ���OS/��Ż纰�ٿ�ε�(����) ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponOsStatic>
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	List<HashMap<String, Object>> selectCouponOsStaticMonthList(HashMap<String ,Object> params);
	
	
}
