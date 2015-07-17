package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdFaqMapper;
import com.wallet.admin.model.MwAdFaq;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdFaqDao.java
 * Class	: com.wallet.admin.dao.MwAdFaqDao
 * History	: 2012/08/23, psj, 작업구분 : 기타관리 > FAQ 관리
 * Comment	:
 */
public class MwAdFaqDao extends MybatisCilent implements MwAdFaqMapper {

	/**
	 * FAQ 정보 조회 select total count
	 * @return	
	 */
	public int selectFaqListTotalCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdFaqMapper.selectFaqListTotalCnt", params);
	}
	
	/**
	 * FAQ 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdFaq> selectFaqList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdFaqMapper.selectFaqList", params);
	}
	
	
	/**
	 * FAQ 정보 상세 조회 select
	 * @return	
	 */
	public MwAdFaq selectFaqListDtl(HashMap<String, Object> params) {
		return (MwAdFaq) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdFaqMapper.selectFaqList", params);
	}
	
	/**
	 * FAQ 등록 insert
	 * @return	
	 */
	public int insertFaqReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdFaqMapper.insertFaqReg", params);
	}
	
	/**
	 * FAQ 정보 상세 정보 delete
	 * @return	
	 */
	public int deleteFaqDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdFaqMapper.deleteFaqDtl", params);
	}
	
	/**
	 * FAQ 상세 정보 변경 update
	 * @return	
	 */
	public int updateFaqDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdFaqMapper.updateFaqDtl", params);
	}
	
}
