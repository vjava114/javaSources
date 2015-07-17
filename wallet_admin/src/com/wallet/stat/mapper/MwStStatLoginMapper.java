package com.wallet.stat.mapper;


import java.util.HashMap;

import com.wallet.stat.model.MwStStatLogin;


public interface MwStStatLoginMapper {
	
	MwStStatLogin selectLoginIdCheck(HashMap<String, Object> params);
	
	

}