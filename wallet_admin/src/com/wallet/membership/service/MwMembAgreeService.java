package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwMembAgreeDao;
import com.wallet.membership.model.MwMembAgree;
import com.wallet.membership.model.MwMembAgreeExample;

public class MwMembAgreeService {
	private final MwMembAgreeDao dao;

	public MwMembAgreeService() {
		dao = new MwMembAgreeDao();
	}
	
	public void rollback(){
		dao.rollback();
	}

	public List<MwMembAgree> getByExample(MwMembAgreeExample example) {
		List<MwMembAgree> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MwMembAgree getByExampleOnly(MwMembAgreeExample example) {
		List<MwMembAgree> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(MwMembAgreeExample example) {
		return dao.countByExample(example);
	}

	public List<MwMembAgree> getByExamplePage(MwMembAgreeExample example,
			int pageNo, int rowsPerPage) {
		List<MwMembAgree> result = null;
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

	public MwMembAgree getByKey(String key) {
		MwMembAgreeExample example = new MwMembAgreeExample();
		example.or().andMembIdEqualTo(key);

		return getByExampleOnly(example);
	}

	public int update(MwMembAgree record, MwMembAgreeExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwMembAgree record) {
		return dao.insert(record);
	}

	public int delete(MwMembAgreeExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
