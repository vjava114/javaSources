package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.CpnBankInfoDao;
import com.wallet.membership.model.CpnBankInfo;
import com.wallet.membership.model.CpnBankInfoExample;
import com.wallet.membership.model.CpnBankInfoWithBLOBs;

public class CpnBankInfoService {
	private final CpnBankInfoDao dao;

	public CpnBankInfoService() {
		dao = new CpnBankInfoDao();
	}

	public List<CpnBankInfo> getByExample(CpnBankInfoExample example) {
		List<CpnBankInfo> list = null;
		list = dao.selectByExample(example);

		return list;
	}
	
	public void rollback(){
		dao.rollback();
	}

	public CpnBankInfo getByExampleOnly(CpnBankInfoExample example) {
		List<CpnBankInfo> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(CpnBankInfoExample example) {
		return dao.countByExample(example);
	}

	public List<CpnBankInfo> getByExamplePage(CpnBankInfoExample example,
			int pageNo, int rowsPerPage) {
		List<CpnBankInfo> result = null;
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

	public CpnBankInfo getByKey(String key) {
		CpnBankInfoExample example = new CpnBankInfoExample();
		example.or().andBankIdEqualTo(key);

		return getByExampleOnly(example);
	}

	public int update(CpnBankInfoWithBLOBs record, CpnBankInfoExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(CpnBankInfoWithBLOBs record) {
		return dao.insert(record);
	}

	public int delete(CpnBankInfoExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
