package com.wallet.membership.service.custom;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.custom.BulkCouponDao;
import com.wallet.membership.model.custom.BulkCoupon;

public class BulkCouponService {
	private final BulkCouponDao dao;

	public BulkCouponService() {
		dao = new BulkCouponDao();
	}
	
	public void rollback(){
		dao.rollback();
	}
	
	public void commit(){
		dao.commit();
	}

	public List<BulkCoupon> getByExample(BulkCoupon bulkCoupon) {
		List<BulkCoupon> list = null;
		list = dao.selectByExample(bulkCoupon);

		return list;
	}

	public int getCountByExample(BulkCoupon bulkCoupon) {
		return dao.countByExample(bulkCoupon);
	}

	public List<BulkCoupon> getPopupByExample(BulkCoupon bulkCoupon) {
		List<BulkCoupon> list = null;
		list = dao.popupByExample(bulkCoupon);

		return list;
	}

	public int getPopupcountByExample(BulkCoupon bulkCoupon) {
		return dao.PopupcountByExample(bulkCoupon);
	}

	public List<BulkCoupon> getMemberListByExample(BulkCoupon bulkCoupon) {
		List<BulkCoupon> list = null;
		list = dao.selectByMemberExample(bulkCoupon);

		return list;
	}
	
	public List<BulkCoupon> getPopupByExamplePage(BulkCoupon bulkCoupon,
			int pageNo, int rowsPerPage) {
		List<BulkCoupon> result = null;
		bulkCoupon.setRowsPerPage(rowsPerPage * pageNo);
		System.out.println("example.rows = " + bulkCoupon.getRowsPerPage());
		result = getPopupByExample(bulkCoupon);

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
	
	public List<BulkCoupon> getByExamplePage(BulkCoupon bulkCoupon,
			int pageNo, int rowsPerPage) {
		List<BulkCoupon> result = null;
		bulkCoupon.setRowsPerPage(rowsPerPage * pageNo);
		System.out.println("example.rows = " + bulkCoupon.getRowsPerPage());
		result = getByExample(bulkCoupon);

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

	public void UpdateBulkCoupon(BulkCoupon bulkCoupon){
//		dao.updateByCpnlistSelective(bulkCoupon);
//		dao.commit();
		dao.updateByMwCsBulkCpnSelective(bulkCoupon);
		dao.commit();
		dao.updateByMwCsCpnlistSelective(bulkCoupon);
		dao.commit();
	}
	
	public void DeleteBulkCoupon(BulkCoupon bulkCoupon){
		dao.deleteByCpnlistSelective(bulkCoupon);
		dao.commit();
		dao.deleteByMwCsBulkCpnSelective(bulkCoupon);
		dao.commit();
		dao.deleteByMwCsCpnlistSelective(bulkCoupon);
		dao.commit();
	}
}
