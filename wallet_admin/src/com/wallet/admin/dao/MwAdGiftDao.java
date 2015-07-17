package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdGiftMapper;
import com.wallet.admin.model.MwAdGift;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdGiftDao.java
 * Class	: com.wallet.admin.dao.MwAdGiftDao
 * History	: 2012/08/23, psj, 작업구분 : 상품권 관리 > 상품권
 * Comment	:
 */
public class MwAdGiftDao extends MybatisCilent implements MwAdGiftMapper {

	/**
	 * 상품권 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdGift> selectGiftList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdGiftMapper.selectGiftList", params);
	}
	
	/**
	 * 상품권 정보 조회 select
	 * @return	
	 */
		public MwAdGift selectGiftListDtl(HashMap<String, Object> params) {
		return (MwAdGift) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdGiftMapper.selectGiftList", params);
	}

	/**
	 * 상품권 정보 조회 main_idx max+1 조회
	 * @return	
	 */
		public MwAdGift selectGiftMainIdx() {
		return (MwAdGift) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdGiftMapper.selectGiftMainIdx");
	}
			
	/**
	 * 상품권 정보 등록 insert
	 * @return	
	 */
	public int insertGiftReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdGiftMapper.insertGiftReg", params);
	}
	
	/**
	 * 상품권 정보 삭제 delete
	 * @return	
	 */
	public int deleteGiftDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdGiftMapper.deleteGiftDtl", params);
	}
	
	/**
	 * 상품권 정보 수정  update
	 * @return	
	 */
	public int updateGiftDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdGiftMapper.updateGiftDtl", params);
	}
	
	/**
	 * 상품권 순서 조정  update
	 * @return	
	 */
	public int updateGiftIdx(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdGiftMapper.updateGiftIdx", params);
	}
}
