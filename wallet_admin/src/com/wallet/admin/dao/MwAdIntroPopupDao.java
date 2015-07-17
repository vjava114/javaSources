package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdIntroPopupMapper;
import com.wallet.admin.model.MwAdIntroPopup;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdIntroPopupDao.java
 * Class	: com.wallet.admin.dao.MwAdIntroPopupDao
 * History	: 2012/08/23, psj, 작업구분 : 팝업/배너 관리 > 인트로 팝업
 * Comment	:
 */
public class MwAdIntroPopupDao extends MybatisCilent implements MwAdIntroPopupMapper {

	/**
	 * 인트로 팝업 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdIntroPopup> selectIntroPopupList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdIntroPopupMapper.selectIntroPopupList", params);
	}
	
	/**
	 * 인트로 팝업 정보 조회 select total count
	 * @return	
	 */
	public int selectIntroPopupListTotalCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdIntroPopupMapper.selectIntroPopupListTotalCnt", params);
	}
	
	/**
	 * 인트로 팝업 정보 상세 조회 select
	 * @return	
	 */
	public MwAdIntroPopup selectIntroPopupListDtl(HashMap<String, Object> params) {
		return (MwAdIntroPopup) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdIntroPopupMapper.selectIntroPopupList", params);
	}
	
	/**
	 * 인트로 팝업 등록 insert
	 * @return	
	 */
	public int insertIntroPopupReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdIntroPopupMapper.insertIntroPopupReg", params);
	}
	
	
	/**
	 * 인트로 팝업 게재상태 update
	 * @return	
	 */
	public int updateIntroPopupStat(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdIntroPopupMapper.updateIntroPopupStat", params);
	}
	
	/**
	 * 인프로 팝업 상세 정보 변경 update
	 * @return	
	 */
	public int updateIntroPopupDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdIntroPopupMapper.updateIntroPopupDtl", params);
	}
	
	/**
	 * 인트로 팝업 상세 정보 delete
	 * @return	
	 */
	public int deleteIntroPopupDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdIntroPopupMapper.deleteIntroPopupDtl", params);
	}
	
}
