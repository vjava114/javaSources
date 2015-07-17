package com.wallet.admin.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.admin.dao.MwAdMenuDao;
import com.wallet.admin.model.MwAdMenu;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdMenuService.java
 * Class	: com.wallet.admin.service.MwAdMenuService
 * History	: 2012/08/23, psj, 작업구분 : 메뉴
 * Comment	:
 */
public class MwAdMenuService {
	private Logger log = Log.getLogger("logs");
	private final MwAdMenuDao dao;
	
	public MwAdMenuService() {
		dao = new MwAdMenuDao();
	}
	
	/**
	 * 메뉴 정보 조회 select
	 */
	public List<MwAdMenu> selectMenuList(HashMap<String,Object> params) {

		List<MwAdMenu> result = null;
		
		params.put("use_yn", "R");
		params.put("menu_dph", "1");
		
		result = dao.selectMenuList(params);

		return result;
	}
	
	/**
	 * SUB 메뉴 정보 조회 select
	 */
	public List<MwAdMenu> selectSubMenuList(HashMap<String,Object> params) {

		List<MwAdMenu> result = null;
		
		params.put("use_yn", "R");
		params.put("menu_dph", "1");
		
		result = dao.selectSubMenuList(params);

		return result;
	}

	
}
