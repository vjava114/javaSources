package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwCmCodeDao;
import com.wallet.membership.model.MwCmCode;
import com.wallet.membership.model.MwCmCodeExample;
import com.wallet.membership.model.MwCmCodeKey;
import com.wallet.membership.model.MwCmCodeWithBLOBs;

public class MwCmCodeService {
	private final MwCmCodeDao dao;

	public MwCmCodeService() {
		dao = new MwCmCodeDao();
	}
	
	public void rollback(){
		dao.rollback();
	}	

	public List<MwCmCode> getByExample(MwCmCodeExample example) {
		List<MwCmCode> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MwCmCode getByExampleOnly(MwCmCodeExample example) {
		List<MwCmCode> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(MwCmCodeExample example) {
		return dao.countByExample(example);
	}

	public List<MwCmCode> getByExamplePage(MwCmCodeExample example,
			int pageNo, int rowsPerPage) {
		List<MwCmCode> result = null;
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

	public MwCmCode getByKey(MwCmCodeKey key) {
		MwCmCodeExample example = new MwCmCodeExample();
		example.or().andGrpCodeEqualTo(key.getGrpCode());
		example.or().andComCdEqualTo(key.getComCd());

		return getByExampleOnly(example);
	}

	public int update(MwCmCodeWithBLOBs record, MwCmCodeExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwCmCodeWithBLOBs record) {
		return dao.insert(record);
	}

	public int delete(MwCmCodeExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
