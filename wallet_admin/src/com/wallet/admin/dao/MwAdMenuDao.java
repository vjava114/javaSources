package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdMenuMapper;
import com.wallet.admin.model.MwAdMenu;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdMenuDao.java
 * Class	: com.wallet.admin.dao.MwAdMenuDao
 * History	: 2012/08/23, psj, 작업구분 : 메뉴
 * Comment	:
 */
public class MwAdMenuDao extends MybatisCilent implements MwAdMenuMapper {
    
	/**
	 * 메뉴 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdMenu> selectMenuList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdMenuMapper.selectMenuList", params);
	}
	
	/**
	 * SUB 메뉴 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdMenu> selectSubMenuList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdMenuMapper.selectSubMenuList", params);
	}
	
}
