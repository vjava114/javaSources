package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwCsCpnregHistDao;
import com.wallet.membership.model.MwCsCpnregHist;
import com.wallet.membership.model.MwCsCpnregHistExample;
import com.wallet.membership.model.MwCsCpnregHistKey;

public class MwCsCpnregHistService {
	private final MwCsCpnregHistDao dao;

	public MwCsCpnregHistService() {
		dao = new MwCsCpnregHistDao();
	}
	
	public void rollback(){
		dao.rollback();
	}	

	public List<MwCsCpnregHist> getByExample(MwCsCpnregHistExample example) {
		List<MwCsCpnregHist> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MwCsCpnregHist getByExampleOnly(MwCsCpnregHistExample example) {
		List<MwCsCpnregHist> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(MwCsCpnregHistExample example) {
		return dao.countByExample(example);
	}

	public List<MwCsCpnregHist> getByExamplePage(MwCsCpnregHistExample example,
			int pageNo, int rowsPerPage) {
		List<MwCsCpnregHist> result = null;
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

	public MwCsCpnregHist getByKey(MwCsCpnregHistKey key) {
		MwCsCpnregHistExample example = new MwCsCpnregHistExample();
		example.or().andCpnIdEqualTo(key.getCpnId()).andSeqNoEqualTo(key.getSeqNo());

		return getByExampleOnly(example);
	}

	public int update(MwCsCpnregHist record, MwCsCpnregHistExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwCsCpnregHist record) {
		return dao.insert(record);
	}

	public int delete(MwCsCpnregHistExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
