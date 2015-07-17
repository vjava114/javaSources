package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdPayment;


public interface MwAdPaymentMapper {
	
	List<MwAdPayment> selectPaymentList(HashMap<String, Object> params);
	
	List<MwAdPayment> selectUseClauseList(HashMap<String, Object> params);
	
	int insertPaymentReg(HashMap<String, Object> params);

	int deletePaymentDtl(HashMap<String, Object> params);
	
	int updatePaymentDtl(HashMap<String, Object> params);

	int insertUseClause(HashMap<String, Object> params);

	int deleteUseClause(HashMap<String, Object> params);

	int updateUseClause(HashMap<String, Object> params);
	
}