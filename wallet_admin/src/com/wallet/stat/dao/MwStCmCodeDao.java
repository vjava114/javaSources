package com.wallet.stat.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.stat.mapper.MwStCmCodeMapper;
import com.wallet.stat.model.MwStCmCode;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdCmCodeDao.java
 * Class	: com.wallet.stat.dao.MwAdCmCodeDao
 * History	: 2012/08/23, psj, �۾����� : �ڵ尪 ����
 * Comment	:
 */
public class MwStCmCodeDao extends MybatisCilent implements MwStCmCodeMapper {

	/**
	 * �ڵ尪 ���� ����Ʈ ��ȸ
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStCmCode> selectCmCodeList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStCmCodeMapper.selectCmCodeList", params);
	}
	
	/**
	 *  �ڵ尪 ���� seq_no ��ȸ
	 * @return	
	 */
		public int selectCmCodeSeqNo(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne("com.wallet.stat.mapper.MwStCmCodeMapper.selectCmCodeSeqNo", params);
	}
		
	/**
	 * �ڵ尪 ���� ����Ʈ ���
	 * @return	
	 */
		public int insertCmCodeReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.stat.mapper.MwStCmCodeMapper.insertCmCodeReg", params);
	}
		
	/**
	 * �ڵ尪 ����
	 * @return	
	 */
		public int deleteCmCode(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.stat.mapper.MwStCmCodeMapper.deleteCmCode", params);
	}
	
}
