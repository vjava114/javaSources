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
 * History	: 2012/08/23, psj, 작업구분 : 이벤트/공지사항 관리 > 이벤트/공지사항 등록
 * Comment	:
 */
public class MwAdEventDao extends MybatisCilent implements MwAdEventMapper {

	/**
	 * 이벤트/공지사항 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdEvent> selectEventList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdEventMapper.selectEventList", params);
	}
	
	/**
	 * 이벤트/공지사항 정보 조회 select total count
	 * @return	
	 */
	public int selectEventListTotalCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdEventMapper.selectEventListTotalCnt", params);
	}
	
	/**
	 * 이벤트/공지사항 정보 조회 select
	 * @return	
	 */
	public MwAdEvent selectEventListDtl(HashMap<String, Object> params) {
		return (MwAdEvent) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdEventMapper.selectEventList", params);
	}
	
	/**
	 * 이벤트/공지사항 정보 insert
	 * @return	
	 */
	public int insertEventReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdEventMapper.insertEventReg", params);
	}
	
	/**
	 * 이벤트/공지사항 상세 정보 변경 update
	 * @return	
	 */
	public int updateEventDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdEventMapper.updateEventDtl", params);
	}
	
	/**
	 * 이벤트/공지사항 상세 정보 delete
	 * @return	
	 */
	public int deleteEventDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdEventMapper.deleteEventDtl", params);
	}
	
}
