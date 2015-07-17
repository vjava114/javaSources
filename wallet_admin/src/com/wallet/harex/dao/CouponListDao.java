package com.wallet.harex.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.mapper.CouponListMapper;
import com.wallet.harex.model.CouponList;

public class CouponListDao extends MybatisCilent implements CouponListMapper {

	@SuppressWarnings("unchecked")
	public List<CouponList> selectByCoupon(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.CouponListMapper.selectByCoupon", params);
		
	}
}