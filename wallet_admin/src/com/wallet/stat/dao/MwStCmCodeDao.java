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
 * History	: 2012/08/23, psj, 작업구분 : 코드값 정의
 * Comment	:
 */
public class MwStCmCodeDao extends MybatisCilent implements MwStCmCodeMapper {

	/**
	 * 코드값 정의 리스트 조회
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStCmCode> selectCmCodeList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStCmCodeMapper.selectCmCodeList", params);
	}
	
	/**
	 *  코드값 정의 seq_no 조회
	 * @return	
	 */
		public int selectCmCodeSeqNo(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne("com.wallet.stat.mapper.MwStCmCodeMapper.selectCmCodeSeqNo", params);
	}
		
	/**
	 * 코드값 정의 리스트 등록
	 * @return	
	 */
		public int insertCmCodeReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.stat.mapper.MwStCmCodeMapper.insertCmCodeReg", params);
	}
		
	/**
	 * 코드값 삭제
	 * @return	
	 */
		public int deleteCmCode(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.stat.mapper.MwStCmCodeMapper.deleteCmCode", params);
	}
	
}
