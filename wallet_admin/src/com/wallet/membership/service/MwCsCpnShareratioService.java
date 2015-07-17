package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwCsCpnShareratioDao;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwCsCpnShareratio;
import com.wallet.membership.model.MwCsCpnShareratioExample;

public class MwCsCpnShareratioService {
	private final MwCsCpnShareratioDao dao;

	public MwCsCpnShareratioService() {
		dao = new MwCsCpnShareratioDao();
	}
	
	public void rollback(){
		dao.rollback();
	}
	
	
	public List<MwCsCpnShareratio> getByExample(MwCsCpnShareratioExample example) {
		List<MwCsCpnShareratio> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MwCsCpnShareratio getByExampleOnly(MwCsCpnShareratioExample example) {
		List<MwCsCpnShareratio> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public int getCountByExample(MwCsCpnShareratioExample example) {
		return dao.countByExample(example);
	}

	public List<MwCsCpnShareratio> getByExamplePage(MwCsCpnShareratioExample example,
			int pageNo, int rowsPerPage) {
		List<MwCsCpnShareratio> result = null;
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

	public MwCsCpnShareratio getByKey(String key) {
		MwCsCpnShareratioExample example = new MwCsCpnShareratioExample();
		example.or().andCompIdEqualTo(key);

		return getByExampleOnly(example);
	}

	public int update(MwCsCpnShareratio record, MwCsCpnShareratioExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwCsCpnShareratio record) {
		return dao.insertSelective(record);
	}

	public int delete(MwCsCpnShareratioExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
