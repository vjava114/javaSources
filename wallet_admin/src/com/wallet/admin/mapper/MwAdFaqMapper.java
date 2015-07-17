package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdFaq;

public interface MwAdFaqMapper {
	
	List<MwAdFaq> selectFaqList(HashMap<String, Object> params);

	int insertFaqReg(HashMap<String, Object> params);

	int deleteFaqDtl(HashMap<String, Object> params);

	int updateFaqDtl(HashMap<String, Object> params);


}