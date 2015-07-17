package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdMenu;


public interface MwAdMenuMapper {
	
	List<MwAdMenu> selectMenuList(HashMap<String, Object> params);
	
	List<MwAdMenu> selectSubMenuList(HashMap<String, Object> params);
	
}