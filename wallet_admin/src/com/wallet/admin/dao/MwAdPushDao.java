package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdPushMapper;
import com.wallet.admin.model.MwAdPush;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdPushDao.java
 * Class	: com.wallet.admin.dao.MwAdPushDao
 * History	: 2012/08/23, psj, 작업구분 : 기타관리 > Push 발송
 * Comment	:
 */
public class MwAdPushDao extends MybatisCilent implements MwAdPushMapper {
    
	/**
	 * Push 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdPush> selectPushList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdPushMapper.selectPushList", params);
	}
	
	/**
	 * Push 정보 상세 조회 select
	 * @return	
	 */
	public MwAdPush selectPushListDtl(HashMap<String, Object> params) {
		return (MwAdPush) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdPushMapper.selectPushList", params);
	}
	
	/**
	 * Push 정보 등록 insert
	 * @return	
	 */
	public int insertPushReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdPushMapper.insertPushReg", params);
	}
	
	/**
	 * Push 상세 정보 변경 update
	 * @return	
	 */
	public int updatePushDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdPushMapper.updatePushDtl", params);
	}
	
	/**
	 * Push 정보 delete
	 * @return	
	 */
	public int deletePushDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdPushMapper.deletePushDtl", params);
	}
	
}
