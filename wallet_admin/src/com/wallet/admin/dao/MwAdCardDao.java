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
 * History	: 2012/08/23, psj, 작업구분 : 카드사 관리 > 카드
 * Comment	:
 */
public class MwAdCardDao extends MybatisCilent implements MwAdCardMapper {

	/**
	 * 카드사 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdCard> selectCardList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdCardMapper.selectCardList", params);
	}

	/**
	 * 카드사 정보 조회 select
	 * @return	
	 */
		public MwAdCard selectCardListDtl(HashMap<String, Object> params) {
		return (MwAdCard) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdCardMapper.selectCardList", params);
	}
		
	/**
	 * 카드사 정보 :  max+1 idx 조회
	 * @return	
	 */
		public MwAdCard selectCardIdx() {
		return (MwAdCard) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdCardMapper.selectCardIdx");
	}
			
	/**
	 * 카드사 순서 조정  update
	 * @return	
	 */
	public int updateCardIdx(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdCardMapper.updateCardIdx", params);
	}
	
	/**
	 * 카드사 정보 수정  update
	 * @return	
	 */
	public int updateCardDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdCardMapper.updateCardDtl", params);
	}
	
	/**
	 * 신규 카드 등록 insert
	 * @return	
	 */
	public int insertCardReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdCardMapper.insertCardReg", params);
	}
	
	/**
	 * 카드사 정보 삭제 delete
	 * @return	
	 */
	public int deleteCardDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdCardMapper.deleteCardDtl", params);
	}

}
