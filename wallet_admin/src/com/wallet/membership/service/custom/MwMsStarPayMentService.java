package com.wallet.membership.service.custom;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwMsStarPayMentDao;
import com.wallet.membership.model.MwMsStarPayMentExample;
import com.wallet.membership.model.MwMsStarPayMent;

public class MwMsStarPayMentService {
	private final MwMsStarPayMentDao dao;
	public MwMsStarPayMentService() {
		// TODO Auto-generated constructor stub
		dao = new MwMsStarPayMentDao();
	}
	
	public void rollback(){
		dao.rollback();
	}
	
	public List<MwMsStarPayMent> getByExample(MwMsStarPayMentExample example) {
		List<MwMsStarPayMent> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MwMsStarPayMent getByExampleOnly(MwMsStarPayMentExample example) {
		List<MwMsStarPayMent> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(MwMsStarPayMentExample example) {
		return dao.countByExample(example);
	}

	public List<MwMsStarPayMent> getByExamplePage(MwMsStarPayMentExample example,
			int pageNo, int rowsPerPage) {
		List<MwMsStarPayMent> result = null;
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

	public int update(MwMsStarPayMent record, MwMsStarPayMentExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwMsStarPayMent record) {
		return dao.insert(record);
	}

	public int delete(MwMsStarPayMentExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
