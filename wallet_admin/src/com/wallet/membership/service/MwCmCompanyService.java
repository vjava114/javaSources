package com.wallet.membership.service;

import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.MwCmCompanyDao;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;

public class MwCmCompanyService {
	private final MwCmCompanyDao dao;

	public void rollback(){
		dao.rollback();
	}
	public MwCmCompanyService() {
		dao = new MwCmCompanyDao();
	}
	
	

	public List<MwCmCompany> getByExample(MwCmCompanyExample example) {
		List<MwCmCompany> list = null;
		list = dao.selectByExample(example);

		return list;
	}
	
	public MwCmCompany getByExampleOnly(MwCmCompanyExample example) {
		List<MwCmCompany> list = null;
		list = dao.selectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public MwCmCompany getByShopIdExampleOnly(MwCmCompanyExample example) {
		List<MwCmCompany> list = null;
		list = dao.nextShopId(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public MwCmCompany getByBrandExampleOnly(MwCmCompanyExample example) {
		List<MwCmCompany> list = null;
		list = dao.nextBrandId(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public MwCmCompany getByStoreExampleOnly(MwCmCompanyExample example) {
		List<MwCmCompany> list = null;
		list = dao.MemberStoreSelectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public MwCmCompany getByFranchiseExampleOnly(MwCmCompanyExample example) {
		List<MwCmCompany> list = null;
		list = dao.FranchiseSelectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public MwCmCompany getByPartnerExampleOnly(MwCmCompanyExample example) {
		List<MwCmCompany> list = null;
		list = dao.MemberPatnerSelectByExample(example);

		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public List<MwCmCompany> getByMemberPartnerExampleOnly(MwCmCompanyExample example,int pageNo, int rowsPerPage) {
			List<MwCmCompany> result = null;
			example.setRowsPerPage(rowsPerPage * pageNo);
			System.out.println("example.rows = " + example.getRowsPerPage());
			result = dao.MemberPatnerSelectByExample(example);

			if (result != null) {
				if (result.size() < (pageNo - 1) * rowsPerPage) {
					Log.info("List is empty![result size: " + result.size() + ", pageNo: "
							+ pageNo + ", rowsPerPage: " + rowsPerPage + "]");
//					 result = null;
				} else {
					Log.info("result size: " + result.size());
					for (int inx = (((pageNo - 1) * rowsPerPage) - 1); inx >= 0; inx--) {
						result.remove(inx);
					}
				}
			}
			return result;
	}
	
	public List<MwCmCompany> getByCompId(MwCmCompanyExample example){
		return dao.MemberStoreSelectByExample(example);
	}
	
	public List<MwCmCompany> getByMemberStoreExampleOnly(MwCmCompanyExample example,int pageNo, int rowsPerPage) {
		List<MwCmCompany> result = null;
		example.setRowsPerPage(rowsPerPage * pageNo);
		System.out.println("example.rows = " + example.getRowsPerPage());
		result = dao.MemberStoreSelectByExample(example);

		if (result != null) {
			if (result.size() < (pageNo - 1) * rowsPerPage) {
				Log.info("List is empty![result size: " + result.size() + ", pageNo: " + pageNo + ", rowsPerPage: " + rowsPerPage + "]");
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
	
	public List<MwCmCompany> getByFranchiseExampleOnly(MwCmCompanyExample example,int pageNo, int rowsPerPage) {
		List<MwCmCompany> result = null;
		example.setRowsPerPage(rowsPerPage * pageNo);
		System.out.println("example.rows = " + example.getRowsPerPage());
		result = dao.FranchiseSelectByExample(example);

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

	public int getFranchiseSelectByCount(MwCmCompanyExample example) {
		return dao.FranchiseSelectByCount(example);
	}
	public int getMemberStoreSelectByCount(MwCmCompanyExample example) {
		return dao.MemberStoreSelectByCount(example);
	}
	public int getMemberPatnerSelectByCount(MwCmCompanyExample example) {
		return dao.MemberPatnerSelectByCount(example);
	}
	public int getCountByExample(MwCmCompanyExample example) {
		return dao.countByExample(example);
	}

	public List<MwCmCompany> getByExamplePage(MwCmCompanyExample example,
			int pageNo, int rowsPerPage) {
		List<MwCmCompany> result = null;
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

	public MwCmCompany getByKey(String key) {
		MwCmCompanyExample example = new MwCmCompanyExample();
		example.or().andCompIdEqualTo(key);

		return getByExampleOnly(example);
	}

	public int update(MwCmCompany record, MwCmCompanyExample example) {
		return dao.updateByExampleSelective(record, example);
	}

	public int insert(MwCmCompany record) {
		return dao.insertSelective(record);
	}
	
	public int PartnerInsert(MwCmCompany record) {
		return dao.partnerInsertSelective(record);
	}
	
	public int StoreInsert(MwCmCompany record) {
		return dao.storeInsertSelective(record);
	}
	
	public int FranchiseInsert(MwCmCompany record) {
		return dao.FranchiseInsertSelective(record);
	}

	public int delete(MwCmCompanyExample example) {
		return dao.deleteByExample(example);
	}
	
	public void commit() {
		dao.commit();
	}
}
