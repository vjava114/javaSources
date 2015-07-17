package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdBannerMapper;
import com.wallet.admin.model.MwAdBanner;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdBannerDao.java
 * Class	: com.wallet.admin.dao.MwAdBannerDao
 * History	: 2012/08/23, psj, 작업구분 : 팝업/배너 관리 > 배너
 * Comment	:
 */
public class MwAdBannerDao extends MybatisCilent implements MwAdBannerMapper {

	/**
	 * 배너 정보 조회 select total count
	 * @return	
	 */
	public int selectBannerListTotalCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdBannerMapper.selectBannerListTotalCnt", params);
	}
	
	/**
	 * 배너 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdBanner> selectBannerList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdBannerMapper.selectBannerList", params);
	}
	
	
	/**
	 * 배너 등록 insert
	 * @return	
	 */
		public int insertBannerReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdBannerMapper.insertBannerReg", params);
	}
	
	/**
	 * 배너 selectbox 조회 (멤버쉽상세,쿠폰상세, 이벤트/공지사항)
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdBanner> jsonBannerSelectbox(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdBannerMapper.jsonBannerSelectbox", params);
	}
	
	/**
	 * 배너 정보 상세 조회 select
	 * @return	
	 */
	public MwAdBanner selectBannerListDtl(HashMap<String, Object> params) {
		return (MwAdBanner) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdBannerMapper.selectBannerList", params);
	}
	
	/**
	 * 배너 정보 상세 정보 delete
	 * @return	
	 */
		public int deleteBannerDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdBannerMapper.deleteBannerDtl", params);
	}
	
	/**
	 * 배너 상세 정보 변경 update
	 * @return	
	 */
		public int updateBannerDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdBannerMapper.updateBannerDtl", params);
	}
}
