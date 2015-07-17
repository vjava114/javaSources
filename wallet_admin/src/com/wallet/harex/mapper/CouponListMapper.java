package com.wallet.harex.mapper;

import com.wallet.harex.model.CouponList;

import java.util.HashMap;
import java.util.List;

public interface CouponListMapper {
	
	List<CouponList> selectByCoupon(HashMap<String, Object> params);
	
}