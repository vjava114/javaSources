package com.wallet.admin.mapper;


import java.util.HashMap;

import com.wallet.admin.model.MwAdLogin;


public interface MwAdLoginMapper {
	
	MwAdLogin selectLoginIdCheck(HashMap<String, Object> params);
	
	

}