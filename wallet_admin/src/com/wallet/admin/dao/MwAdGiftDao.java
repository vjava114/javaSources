package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdGiftMapper;
import com.wallet.admin.model.MwAdGift;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdGiftDao.java
 * Class	: com.wallet.admin.dao.MwAdGiftDao
 * History	: 2012/08/23, psj, �۾����� : ��ǰ�� ���� > ��ǰ��
 * Comment	:
 */
public class MwAdGiftDao extends MybatisCilent implements MwAdGiftMapper {

	/**
	 * ��ǰ�� ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdGift> selectGiftList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdGiftMapper.selectGiftList", params);
	}
	
	/**
	 * ��ǰ�� ���� ��ȸ select
	 * @return	
	 */
		public MwAdGift selectGiftListDtl(HashMap<String, Object> params) {
		return (MwAdGift) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdGiftMapper.selectGiftList", params);
	}

	/**
	 * ��ǰ�� ���� ��ȸ main_idx max+1 ��ȸ
	 * @return	
	 */
		public MwAdGift selectGiftMainIdx() {
		return (MwAdGift) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdGiftMapper.selectGiftMainIdx");
	}
			
	/**
	 * ��ǰ�� ���� ��� insert
	 * @return	
	 */
	public int insertGiftReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdGiftMapper.insertGiftReg", params);
	}
	
	/**
	 * ��ǰ�� ���� ���� delete
	 * @return	
	 */
	public int deleteGiftDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdGiftMapper.deleteGiftDtl", params);
	}
	
	/**
	 * ��ǰ�� ���� ����  update
	 * @return	
	 */
	public int updateGiftDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdGiftMapper.updateGiftDtl", params);
	}
	
	/**
	 * ��ǰ�� ���� ����  update
	 * @return	
	 */
	public int updateGiftIdx(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdGiftMapper.updateGiftIdx", params);
	}
}
