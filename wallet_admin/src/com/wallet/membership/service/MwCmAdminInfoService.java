package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwCmAdminInfoDao;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwCmAdminInfo;
import com.wallet.membership.model.MwCmAdminInfoExample;

public class MwCmAdminInfoService {
	private final MwCmAdminInfoDao dao;

	public MwCmAdminInfoService() {
		dao = new MwCmAdminInfoDao();
	}
	
	public void rollback(){
		dao.rollback();
	}
	
	
	public List<MwCmAdminInfo> getByExample(MwCmAdminInfoExample example) {
		List<MwCmAdminInfo> list = null;
		list = dao.selectByExample(example);

		return list;
	}
	
	public String AdminPass(String Pass){
		MwCmAdminInfo mwCmAdminInfo = new MwCmAdminInfo();
		mwCmAdminInfo.setAdminPass(Pass);
		return "0x" + dao.selectByAdminPass(mwCmAdminInfo);
	}

	public MwCmAdminInfo getByExampleOnly(MwCmAdminInfoExample example) {
		List<MwCmAdminInfo> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public int getCountByExample(MwCmAdminInfoExample example) {
		return dao.countByExample(example);
	}
	
	public int getAdminCount(MwCmAdminInfoExample example) {
		return dao.selectByAdminCount(example);
	}
	
	public MwCmAdminInfo getByAdminExampleOnly(MwCmAdminInfoExample example) {
		List<MwCmAdminInfo> list = null;
		list = dao.selectByAdmin(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public List<MwCmAdminInfo> getByAdminExamplePage(MwCmAdminInfoExample example,
			int pageNo, int rowsPerPage) {
		List<MwCmAdminInfo> result = null;
		example.setRowsPerPage(rowsPerPage * pageNo);
		System.out.println("example.rows = " + example.getRowsPerPage());
		result = dao.selectByAdmin(example);

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
	

	public List<MwCmAdminInfo> getByExamplePage(MwCmAdminInfoExample example,
			int pageNo, int rowsPerPage) {
		List<MwCmAdminInfo> result = null;
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
	
	public MwCmAdminInfo getByKey(String key) {
		MwCmAdminInfoExample example = new MwCmAdminInfoExample();
		example.or().andCompIdEqualTo(key);

		return getByExampleOnly(example);
	}

	public int update(MwCmAdminInfo record, MwCmAdminInfoExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwCmAdminInfo record) {
		return dao.insertSelective(record);
	}

	public int delete(MwCmAdminInfoExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
