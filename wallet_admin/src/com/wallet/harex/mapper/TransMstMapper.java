package com.wallet.harex.mapper;

import java.util.HashMap;
import java.util.List;

import com.wallet.harex.model.TransMst;
import com.wallet.harex.model.TransMstCpn;
import com.wallet.harex.model.TransMstMsDc;
import com.wallet.harex.model.TransMstMsUse;
import com.wallet.harex.model.TransMstSave;
import com.wallet.harex.model.TransMstStamp;

public interface TransMstMapper {
	
	Integer selectTransMstCnt(HashMap<String, Object> params);
	List<TransMst> selectTransMst(HashMap<String, Object> params);
	
	Integer selectTransMstCpnCnt(HashMap<String, Object> params);
	List<TransMst> selectTransMstCpn(HashMap<String, Object> params);
	
	Integer selectTransMstMsDcCnt(HashMap<String, Object> params);
	List<TransMst> selectTransMstMsDc(HashMap<String, Object> params);
	
	Integer selectTransMstMsUseCnt(HashMap<String, Object> params);
	List<TransMst> selectTransMstMsUse(HashMap<String, Object> params);
	
	Integer selectTransMstMsSaveCnt(HashMap<String, Object> params);
	List<TransMst> selectTransMstMsSave(HashMap<String, Object> params);
	
	Integer selectTransMstStampCnt(HashMap<String, Object> params);
	List<TransMst> selectTransMstStamp(HashMap<String, Object> params);
	
	/*
	 *  복합결제 거래내역 팝업 목록 조회
	 * */
	TransMstCpn selectTransMstCpnPop(HashMap<String, Object> params);
	TransMstMsDc selectTransMstMsDcPop(HashMap<String, Object> params);
	TransMstMsUse selectTransMstMsUsePop(HashMap<String, Object> params);
	TransMstSave selectTransMstMsSavePop(HashMap<String, Object> params);
	TransMstStamp selectTransMstStampPop(HashMap<String, Object> params);
}
