package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdBannerMapper;
import com.wallet.admin.model.MwAdBanner;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdBannerDao.java
 * Class	: com.wallet.admin.dao.MwAdBannerDao
 * History	: 2012/08/23, psj, �۾����� : �˾�/��� ���� > ���
 * Comment	:
 */
public class MwAdBannerDao extends MybatisCilent implements MwAdBannerMapper {

	/**
	 * ��� ���� ��ȸ select total count
	 * @return	
	 */
	public int selectBannerListTotalCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdBannerMapper.selectBannerListTotalCnt", params);
	}
	
	/**
	 * ��� ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdBanner> selectBannerList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdBannerMapper.selectBannerList", params);
	}
	
	
	/**
	 * ��� ��� insert
	 * @return	
	 */
		public int insertBannerReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdBannerMapper.insertBannerReg", params);
	}
	
	/**
	 * ��� selectbox ��ȸ (�������,������, �̺�Ʈ/��������)
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdBanner> jsonBannerSelectbox(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdBannerMapper.jsonBannerSelectbox", params);
	}
	
	/**
	 * ��� ���� �� ��ȸ select
	 * @return	
	 */
	public MwAdBanner selectBannerListDtl(HashMap<String, Object> params) {
		return (MwAdBanner) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdBannerMapper.selectBannerList", params);
	}
	
	/**
	 * ��� ���� �� ���� delete
	 * @return	
	 */
		public int deleteBannerDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdBannerMapper.deleteBannerDtl", params);
	}
	
	/**
	 * ��� �� ���� ���� update
	 * @return	
	 */
		public int updateBannerDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdBannerMapper.updateBannerDtl", params);
	}
}
