package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponFinanceAccumulation;

public interface CouponFinanceAccumulationMapper {
	
	/**
	 * @Method Name : select CouponFinanceAccumulationList
	 * @Description : ±ÝÀ¶»ç Á¤»ê Á¶È¸
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ±è¿Ï¼·
	 * @since 2012.09.28
	 */
	List<CouponFinanceAccumulation> selectCouponFinanceAccumulationList(HashMap<String ,Object> params);

	/**
	 * @Method Name : select CouponFinanceAccumulationListCnt
	 * @Description : ±ÝÀ¶»ç Á¤»ê ¼ö Á¶È¸
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ±è¿Ï¼·
	 * @since 2012.09.28
	 */
	int selectCouponFinanceAccumulationListCnt(HashMap<String ,Object> params);
	
}
