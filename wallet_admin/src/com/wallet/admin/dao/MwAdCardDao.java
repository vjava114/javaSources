package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.wallet.admin.mapper.MwAdCardMapper;
import com.wallet.admin.model.MwAdCard;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdCardDao.java
 * Class	: com.wallet.admin.dao.MwAdCardDao
 * History	: 2012/08/23, psj, �۾����� : ī��� ���� > ī��
 * Comment	:
 */
public class MwAdCardDao extends MybatisCilent implements MwAdCardMapper {

	/**
	 * ī��� ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdCard> selectCardList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdCardMapper.selectCardList", params);
	}

	/**
	 * ī��� ���� ��ȸ select
	 * @return	
	 */
		public MwAdCard selectCardListDtl(HashMap<String, Object> params) {
		return (MwAdCard) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdCardMapper.selectCardList", params);
	}
		
	/**
	 * ī��� ���� :  max+1 idx ��ȸ
	 * @return	
	 */
		public MwAdCard selectCardIdx() {
		return (MwAdCard) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdCardMapper.selectCardIdx");
	}
			
	/**
	 * ī��� ���� ����  update
	 * @return	
	 */
	public int updateCardIdx(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdCardMapper.updateCardIdx", params);
	}
	
	/**
	 * ī��� ���� ����  update
	 * @return	
	 */
	public int updateCardDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdCardMapper.updateCardDtl", params);
	}
	
	/**
	 * �ű� ī�� ��� insert
	 * @return	
	 */
	public int insertCardReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdCardMapper.insertCardReg", params);
	}
	
	/**
	 * ī��� ���� ���� delete
	 * @return	
	 */
	public int deleteCardDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdCardMapper.deleteCardDtl", params);
	}

}
