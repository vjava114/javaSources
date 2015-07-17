package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdFaqMapper;
import com.wallet.admin.model.MwAdFaq;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdFaqDao.java
 * Class	: com.wallet.admin.dao.MwAdFaqDao
 * History	: 2012/08/23, psj, �۾����� : ��Ÿ���� > FAQ ����
 * Comment	:
 */
public class MwAdFaqDao extends MybatisCilent implements MwAdFaqMapper {

	/**
	 * FAQ ���� ��ȸ select total count
	 * @return	
	 */
	public int selectFaqListTotalCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdFaqMapper.selectFaqListTotalCnt", params);
	}
	
	/**
	 * FAQ ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdFaq> selectFaqList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdFaqMapper.selectFaqList", params);
	}
	
	
	/**
	 * FAQ ���� �� ��ȸ select
	 * @return	
	 */
	public MwAdFaq selectFaqListDtl(HashMap<String, Object> params) {
		return (MwAdFaq) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdFaqMapper.selectFaqList", params);
	}
	
	/**
	 * FAQ ��� insert
	 * @return	
	 */
	public int insertFaqReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdFaqMapper.insertFaqReg", params);
	}
	
	/**
	 * FAQ ���� �� ���� delete
	 * @return	
	 */
	public int deleteFaqDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdFaqMapper.deleteFaqDtl", params);
	}
	
	/**
	 * FAQ �� ���� ���� update
	 * @return	
	 */
	public int updateFaqDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdFaqMapper.updateFaqDtl", params);
	}
	
}
