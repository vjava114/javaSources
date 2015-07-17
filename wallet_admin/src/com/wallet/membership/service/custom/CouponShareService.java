package com.wallet.membership.service.custom;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.custom.CouponShareDao;
import com.wallet.membership.model.custom.CouponShare;

public class CouponShareService {
	private final CouponShareDao dao;

	public CouponShareService() {
		dao = new CouponShareDao();
	}
	
	public void commit(){
		dao.commit();
	}
	
	public void rollback(){
		dao.rollback();
	}

	public CouponShare getByExampleOnly(CouponShare Recode) {
		List<CouponShare> list = null;
		list = dao.selectByExample(Recode);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public List<CouponShare> getByExample(CouponShare couponShare) {
		List<CouponShare> list = null;
		list = dao.selectByExample(couponShare);

		return list;
	}

	public int getCountByExample(CouponShare couponShare) {
		return dao.countByExample(couponShare);
	}
	
	public List<CouponShare> getByExamplePage(CouponShare couponShare,
			int pageNo, int rowsPerPage) {
		List<CouponShare> result = null;
		couponShare.setRowsPerPage(rowsPerPage * pageNo);
		System.out.println("example.rows = " + couponShare.getRowsPerPage());
		result = getByExample(couponShare);

		if (result != null) {
			if (result.size() < (pageNo - 1) * rowsPerPage) {
				Log.info("List is empty![result size: " + result.size() + ", pageNo: "
						+ pageNo + ", rowsPerPage: " + rowsPerPage + "]");
				// result = null;
			} else {
				Log.info("result size: " + result.size());
				for (int inx = (((pageNo - 1) * rowsPerPage) - 1); inx >= 0; inx--) {
					result.remove(inx);
				}
			}
		}
		return result;
	}

	public void UpdateCouponShare(CouponShare couponShare) {
		// TODO Auto-generated method stub
//		dao.updateBycpnlistSelective(couponShare);
//		dao.commit();
//		dao.updateBymembcardlistSelective(couponShare);
//		dao.commit();
		dao.updateByMwCsCpnlistSelective(couponShare);
		dao.commit();
//		dao.updateByMWCSCPNSHARERATIOSelective(couponShare);
//		dao.commit();
		
	}

	public int insert(com.wallet.membership.model.custom.CouponShare couponShare) {
		// TODO Auto-generated method stub
		return dao.insertSelective(couponShare);
	}

}
