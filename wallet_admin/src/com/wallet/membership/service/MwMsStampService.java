package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwMsStampDao;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwMsStamp;
import com.wallet.membership.model.MwMsStampExample;

public class MwMsStampService {
	private final MwMsStampDao dao;

	public MwMsStampService() {
		dao = new MwMsStampDao();
	}
	
	public void rollback(){
		dao.rollback();
	}
	
	
	public List<MwMsStamp> getByExample(MwMsStampExample example) {
		List<MwMsStamp> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MwMsStamp getByExampleOnly(MwMsStampExample example) {
		List<MwMsStamp> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	
	public MwMsStamp StampGetBySeq(MwMsStampExample example) {
		List<MwMsStamp> list = null;
		list = dao.StampGetBySeq(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public MwMsStamp getByStampExampleOnly(MwMsStampExample example) {
		List<MwMsStamp> list = null;
		list = dao.StampSelectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public List<MwMsStamp> getByStampExample(MwMsStampExample example) {
		List<MwMsStamp> list = null;
		list = dao.StampSelectByExample(example);

		return list;
	}


	public int getCountByExample(MwMsStampExample example) {
		return dao.countByExample(example);
	}
	
	public int getStampCountByExample(MwMsStampExample example) {
		return dao.StampSelectByCount(example);
	}

	public List<MwMsStamp> getByExamplePage(MwMsStampExample example,
			int pageNo, int rowsPerPage) {
		List<MwMsStamp> result = null;
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

	public List<MwMsStamp> getByStampExamplePage(MwMsStampExample example,
			int pageNo, int rowsPerPage) {
		List<MwMsStamp> result = null;
		example.setRowsPerPage(rowsPerPage * pageNo);
		System.out.println("example.rows = " + example.getRowsPerPage());
		result = dao.StampSelectByExample(example);

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

	public MwMsStamp getByKey(String key) {
		MwMsStampExample example = new MwMsStampExample();
		example.or().andMembIdEqualTo(key);

		return getByExampleOnly(example);
	}

	public int update(MwMsStamp record, MwMsStampExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwMsStamp record) {
		return dao.insertSelective(record);
	}

	public int delete(MwMsStampExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
