package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponTotalAccumulation;

public interface CouponTotalAccumulationMapper {

	/**
	 * @Method Name : selectCouponTotalAccumulationList
	 * @Description : ������� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	List<CouponTotalAccumulation> selectCouponTotalAccumulationList(HashMap<String ,Object> params);


}
