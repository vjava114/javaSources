package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdMenuIdCheckMapper;
import com.wallet.admin.model.MwAdMenuIdCheck;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MenuIdCheckDao.java
 * Class	: com.wallet.admin.dao.MenuIdCheckDao
 * History	: 2012/08/23, psj, �۾����� : �� �ۼ��� id �ߺ�üũ(ī��/��ǰ��/����)
 * Comment	:
 */
public class MwAdMenuIdCheckDao extends MybatisCilent implements MwAdMenuIdCheckMapper {

	/**
	 * ī��,��ǰ��,���� TB id �ߺ� üũ
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdMenuIdCheck> selectIdCheck(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdMenuIdCheckMapper.selectIdCheck", params);
	}
	
}
