package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.CpnListDao;
import com.wallet.membership.model.CpnList;
import com.wallet.membership.model.CpnListExample;

public class CpnListService {
	private final CpnListDao dao;

	public CpnListService() {
		dao = new CpnListDao();
	}
	
	public void rollback(){
		dao.rollback();
	}
	
	public int CouponOrderByCount(CpnListExample example) {
		return dao.CouponOrderCount(example);
	}
	
	public List<CpnList> CouponOrderByExample(CpnListExample example) {
		List<CpnList> list = null;
		list = dao.CouponOrderByExample(example);

		return list;
	}

	public List<CpnList> CouponOrderByExamplePage(CpnListExample example,int pageNo, int rowsPerPage) {
			List<CpnList> result = null;
			example.setRowsPerPage(rowsPerPage * pageNo);
			System.out.println("example.rows = " + example.getRowsPerPage());
			result = dao.CouponOrderByExample(example);

			if (result != null) {
				if (result.size() < (pageNo - 1) * rowsPerPage) {
					Log.info("List is empty![result size: " + result.size() + ", pageNo: "
							+ pageNo + ", rowsPerPage: " + rowsPerPage + "]");
//					 result = null;
				} else {
					Log.info("result size: " + result.size());
					for (int inx = (((pageNo - 1) * rowsPerPage) - 1); inx >= 0; inx--) {
						result.remove(inx);
					}
				}
			}
			return result;
	}
	
	public List<CpnList> getByExample(CpnListExample example) {
		List<CpnList> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public CpnList getByExampleOnly(CpnListExample example) {
		List<CpnList> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(CpnListExample example) {
		return dao.countByExample(example);
	}

	public List<CpnList> getByExamplePage(CpnListExample example,
			int pageNo, int rowsPerPage) {
		List<CpnList> result = null;
		example.setRowsPerPage(rowsPerPage * pageNo);
		System.out.println("example.rows = " + example.getRowsPerPage());
		result = getByExample(example);

		if (result != null) {
			if (result.size() < (pageNo - 1) * rowsPerPage) {
				Log.info("List is empty![result size: " + result.size() + ", pageNo: "
						+ pageNo + ", rowsPerPage: " + rowsPerPage + "]");
//				 result = null;
			} else {
				Log.info("result size: " + result.size());
				for (int inx = (((pageNo - 1) * rowsPerPage) - 1); inx >= 0; inx--) {
					result.remove(inx);
				}
			}
		}
		return result;
	}

	public CpnList getByKey(String key) {
		CpnListExample example = new CpnListExample();
		example.or().andMembIdEqualTo(key);

		return getByExampleOnly(example);
	}

	public int update(CpnList record, CpnListExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(CpnList record) {
		return dao.insertSelective(record);
	}
	
	public int delete(CpnListExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
