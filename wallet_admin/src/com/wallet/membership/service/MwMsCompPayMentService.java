package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwMsCompPayMentDao;
import com.wallet.membership.model.MwMsCompPayMent;
import com.wallet.membership.model.MwMsCompPayMentExample;

public class MwMsCompPayMentService {
	private final MwMsCompPayMentDao dao;
	public MwMsCompPayMentService() {
		// TODO Auto-generated constructor stub
		dao = new MwMsCompPayMentDao();
	}
	
	public void rollback(){
		dao.rollback();
	}
	
	public List<MwMsCompPayMent> getByExample(MwMsCompPayMentExample example) {
		List<MwMsCompPayMent> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MwMsCompPayMent getByExampleOnly(MwMsCompPayMentExample example) {
		List<MwMsCompPayMent> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(MwMsCompPayMentExample example) {
		return dao.countByExample(example);
	}

	public List<MwMsCompPayMent> getByExamplePage(MwMsCompPayMentExample example,
			int pageNo, int rowsPerPage) {
		List<MwMsCompPayMent> result = null;
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

	public int update(MwMsCompPayMent record, MwMsCompPayMentExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwMsCompPayMent record) {
		return dao.insert(record);
	}

	public int delete(MwMsCompPayMentExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
