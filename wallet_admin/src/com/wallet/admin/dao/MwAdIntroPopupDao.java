package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdIntroPopupMapper;
import com.wallet.admin.model.MwAdIntroPopup;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdIntroPopupDao.java
 * Class	: com.wallet.admin.dao.MwAdIntroPopupDao
 * History	: 2012/08/23, psj, �۾����� : �˾�/��� ���� > ��Ʈ�� �˾�
 * Comment	:
 */
public class MwAdIntroPopupDao extends MybatisCilent implements MwAdIntroPopupMapper {

	/**
	 * ��Ʈ�� �˾� ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdIntroPopup> selectIntroPopupList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdIntroPopupMapper.selectIntroPopupList", params);
	}
	
	/**
	 * ��Ʈ�� �˾� ���� ��ȸ select total count
	 * @return	
	 */
	public int selectIntroPopupListTotalCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdIntroPopupMapper.selectIntroPopupListTotalCnt", params);
	}
	
	/**
	 * ��Ʈ�� �˾� ���� �� ��ȸ select
	 * @return	
	 */
	public MwAdIntroPopup selectIntroPopupListDtl(HashMap<String, Object> params) {
		return (MwAdIntroPopup) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdIntroPopupMapper.selectIntroPopupList", params);
	}
	
	/**
	 * ��Ʈ�� �˾� ��� insert
	 * @return	
	 */
	public int insertIntroPopupReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdIntroPopupMapper.insertIntroPopupReg", params);
	}
	
	
	/**
	 * ��Ʈ�� �˾� ������� update
	 * @return	
	 */
	public int updateIntroPopupStat(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdIntroPopupMapper.updateIntroPopupStat", params);
	}
	
	/**
	 * ������ �˾� �� ���� ���� update
	 * @return	
	 */
	public int updateIntroPopupDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdIntroPopupMapper.updateIntroPopupDtl", params);
	}
	
	/**
	 * ��Ʈ�� �˾� �� ���� delete
	 * @return	
	 */
	public int deleteIntroPopupDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdIntroPopupMapper.deleteIntroPopupDtl", params);
	}
	
}
