package com.wallet.stat.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.stat.model.MwStCmCode;

public interface MwStCmCodeMapper {
	
	List<MwStCmCode> selectCmCodeList(HashMap<String, Object> params);
}