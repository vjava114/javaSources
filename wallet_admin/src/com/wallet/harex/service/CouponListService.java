package com.wallet.harex.service;

import java.util.HashMap;
import java.util.List;

import com.wallet.harex.dao.CouponListDao;
import com.wallet.harex.model.CouponList;

public class CouponListService {
	private final CouponListDao dao;

	public CouponListService() {
		dao = new CouponListDao();
	}

	public List<CouponList> getByCouponList(HashMap<String,Object> params) {
		
		List<CouponList> list = null;
		
		list = dao.selectByCoupon(params);

		return list;
	}
}
