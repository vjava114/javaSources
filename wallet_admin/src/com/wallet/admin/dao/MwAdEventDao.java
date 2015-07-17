package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.wallet.admin.mapper.MwAdEventMapper;
import com.wallet.admin.model.MwAdEvent;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdEventDao.java
 * Class	: com.wallet.admin.web.base.MwAdEventDao
 * History	: 2012/08/23, psj, �۾����� : �̺�Ʈ/�������� ���� > �̺�Ʈ/�������� ���
 * Comment	:
 */
public class MwAdEventDao extends MybatisCilent implements MwAdEventMapper {

	/**
	 * �̺�Ʈ/�������� ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdEvent> selectEventList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdEventMapper.selectEventList", params);
	}
	
	/**
	 * �̺�Ʈ/�������� ���� ��ȸ select total count
	 * @return	
	 */
	public int selectEventListTotalCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdEventMapper.selectEventListTotalCnt", params);
	}
	
	/**
	 * �̺�Ʈ/�������� ���� ��ȸ select
	 * @return	
	 */
	public MwAdEvent selectEventListDtl(HashMap<String, Object> params) {
		return (MwAdEvent) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdEventMapper.selectEventList", params);
	}
	
	/**
	 * �̺�Ʈ/�������� ���� insert
	 * @return	
	 */
	public int insertEventReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdEventMapper.insertEventReg", params);
	}
	
	/**
	 * �̺�Ʈ/�������� �� ���� ���� update
	 * @return	
	 */
	public int updateEventDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdEventMapper.updateEventDtl", params);
	}
	
	/**
	 * �̺�Ʈ/�������� �� ���� delete
	 * @return	
	 */
	public int deleteEventDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdEventMapper.deleteEventDtl", params);
	}
	
}
