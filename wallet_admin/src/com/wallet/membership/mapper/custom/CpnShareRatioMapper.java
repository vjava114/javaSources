package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CpnShareRatio;

public interface CpnShareRatioMapper {

	/**
	 * @Method Name : selectCouponAccumulationList
	 * @Description : ������ ���� �� ��ȸ(�д���)
	 * @param : HashMap<String ,Object>
	 * @return : List<CpnShareRatio>
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	List<CpnShareRatio> selectCouponAccumulationList(HashMap<String ,Object> params);

}
