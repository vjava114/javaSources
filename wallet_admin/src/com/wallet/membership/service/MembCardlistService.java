package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MembCardlistDao;
import com.wallet.membership.model.MembCardlist;
import com.wallet.membership.model.MembCardlistExample;

public class MembCardlistService {
	private final MembCardlistDao dao;

	public MembCardlistService() {
		dao = new MembCardlistDao();
	}
	
	public void rollback(){
		dao.rollback();
	}

	public List<MembCardlist> getByExample(MembCardlistExample example) {
		List<MembCardlist> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MembCardlist getByExampleOnly(MembCardlistExample example) {
		List<MembCardlist> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(MembCardlistExample example) {
		return dao.countByExample(example);
	}

	public List<MembCardlist> getByExamplePage(MembCardlistExample example,
			int pageNo, int rowsPerPage) {
		List<MembCardlist> result = null;
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

	public MembCardlist getByKey(String key) {
		MembCardlistExample example = new MembCardlistExample();
		example.or().andMembIdEqualTo(key);

		return getByExampleOnly(example);
	}

	public int update(MembCardlist record, MembCardlistExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MembCardlist record) {
		return dao.insert(record);
	}
	
	public int delete(MembCardlistExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
