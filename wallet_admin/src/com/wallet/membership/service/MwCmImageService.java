package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwCmImageDao;
import com.wallet.membership.model.MwCmImage;
import com.wallet.membership.model.MwCmImageExample;
import com.wallet.membership.model.MwCmImageKey;

public class MwCmImageService {
	private final MwCmImageDao dao;

	public MwCmImageService() {
		dao = new MwCmImageDao();
	}
	
	public void rollback(){
		dao.rollback();
	}	

	public List<MwCmImage> getByExample(MwCmImageExample example) {
		List<MwCmImage> list = null;
		list = dao.selectByExample(example);

		return list;
	}

	public MwCmImage getByExampleOnly(MwCmImageExample example) {
		List<MwCmImage> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public int getCountByExample(MwCmImageExample example) {
		return dao.countByExample(example);
	}

	public List<MwCmImage> getByExamplePage(MwCmImageExample example,
			int pageNo, int rowsPerPage) {
		List<MwCmImage> result = null;
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

	public MwCmImage getByKey(MwCmImageKey key) {
		MwCmImageExample example = new MwCmImageExample();
		example.or().andIdEqualTo(key.getId())
			.andOsTypeEqualTo(key.getOsType())
			.andUseTypeEqualTo(key.getUseType())
			.andLevelEqualTo(key.getLevel());

		return getByExampleOnly(example);
	}

	public int update(MwCmImage record, MwCmImageExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwCmImage record) {
		return dao.insert(record);
	}

	public int delete(MwCmImageExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
