package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwCsBulkCpnDao;
import com.wallet.membership.model.MwCsBulkCpn;
import com.wallet.membership.model.MwCsBulkCpnExample;

public class MwCsBulkCpnService {
	private final MwCsBulkCpnDao dao;

	public MwCsBulkCpnService() {
		dao = new MwCsBulkCpnDao();
	}
	
	public void rollback(){
		dao.rollback();
	}

	public List<MwCsBulkCpn> getByExample(MwCsBulkCpnExample example) {
		List<MwCsBulkCpn> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MwCsBulkCpn getByExampleOnly(MwCsBulkCpnExample example) {
		List<MwCsBulkCpn> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(MwCsBulkCpnExample example) {
		return dao.countByExample(example);
	}

	public List<MwCsBulkCpn> getByExamplePage(MwCsBulkCpnExample example,
			int pageNo, int rowsPerPage) {
		List<MwCsBulkCpn> result = null;
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

	public MwCsBulkCpn getByKey(String key) {
		MwCsBulkCpnExample example = new MwCsBulkCpnExample();
		example.or().andCpnIdEqualTo(key);

		return getByExampleOnly(example);
	}

	public int update(MwCsBulkCpn record, MwCsBulkCpnExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwCsBulkCpn record) {
		return dao.insertSelective(record);
	}
	
	public int delete(MwCsBulkCpnExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
