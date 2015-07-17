package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwCmPayCompanyDao;
import com.wallet.membership.model.MwCmPayCompany;
import com.wallet.membership.model.MwCmPayCompanyExample;
import com.wallet.membership.model.MwCmPayCompanyWithBLOBs;

public class MwCmPayCompanyService {
	private final MwCmPayCompanyDao dao;

	public MwCmPayCompanyService() {
		dao = new MwCmPayCompanyDao();
	}
	
	public void rollback(){
		dao.rollback();
	}

	public List<MwCmPayCompany> getByExample(MwCmPayCompanyExample example) {
		List<MwCmPayCompany> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MwCmPayCompany getByExampleOnly(MwCmPayCompanyExample example) {
		List<MwCmPayCompany> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(MwCmPayCompanyExample example) {
		return dao.countByExample(example);
	}

	public List<MwCmPayCompany> getByExamplePage(MwCmPayCompanyExample example,
			int pageNo, int rowsPerPage) {
		List<MwCmPayCompany> result = null;
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

	public MwCmPayCompany getByKey(String key) {
		MwCmPayCompanyExample example = new MwCmPayCompanyExample();
		example.or().andPaycompIdEqualTo(key);

		return getByExampleOnly(example);
	}

	public int update(MwCmPayCompanyWithBLOBs record, MwCmPayCompanyExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwCmPayCompanyWithBLOBs record) {
		return dao.insert(record);
	}

	public int delete(MwCmPayCompanyExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
