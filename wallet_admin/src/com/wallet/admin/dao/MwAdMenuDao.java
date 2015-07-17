package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdMenuMapper;
import com.wallet.admin.model.MwAdMenu;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdMenuDao.java
 * Class	: com.wallet.admin.dao.MwAdMenuDao
 * History	: 2012/08/23, psj, �۾����� : �޴�
 * Comment	:
 */
public class MwAdMenuDao extends MybatisCilent implements MwAdMenuMapper {
    
	/**
	 * �޴� ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdMenu> selectMenuList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdMenuMapper.selectMenuList", params);
	}
	
	/**
	 * SUB �޴� ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdMenu> selectSubMenuList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdMenuMapper.selectSubMenuList", params);
	}
	
}
