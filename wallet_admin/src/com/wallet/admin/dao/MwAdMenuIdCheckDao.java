package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdMenuIdCheckMapper;
import com.wallet.admin.model.MwAdMenuIdCheck;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MenuIdCheckDao.java
 * Class	: com.wallet.admin.dao.MenuIdCheckDao
 * History	: 2012/08/23, psj, 작업구분 : 글 작성시 id 중복체크(카드/상품권/결재)
 * Comment	:
 */
public class MwAdMenuIdCheckDao extends MybatisCilent implements MwAdMenuIdCheckMapper {

	/**
	 * 카드,상품권,결제 TB id 중복 체크
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdMenuIdCheck> selectIdCheck(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdMenuIdCheckMapper.selectIdCheck", params);
	}
	
}
