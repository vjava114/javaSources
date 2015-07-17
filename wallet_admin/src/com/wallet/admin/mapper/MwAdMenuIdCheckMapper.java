package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdMenuIdCheck;

public interface MwAdMenuIdCheckMapper {
	
	List<MwAdMenuIdCheck> selectIdCheck(HashMap<String, Object> params);
	
}