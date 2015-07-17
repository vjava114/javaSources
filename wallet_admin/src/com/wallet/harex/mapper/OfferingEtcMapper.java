package com.wallet.harex.mapper;

import java.util.HashMap;
import java.util.List;

import com.wallet.harex.model.OfferingCpn;
import com.wallet.harex.model.OfferingDtl;
import com.wallet.harex.model.OfferingMsDc;
import com.wallet.harex.model.OfferingMsSave;
import com.wallet.harex.model.OfferingMsStamp;
import com.wallet.harex.model.OfferingMsUse;
import com.wallet.harex.model.OfferingOrder;
import com.wallet.harex.model.OfferingPromo;
import com.wallet.harex.model.OfferingS;

public interface OfferingEtcMapper {
	
	Integer selectOfferingSCnt(HashMap<String, Object> params);
	List<OfferingS> selectOfferingS(HashMap<String, Object> params);
	
	Integer selectOfferingCpnCnt(HashMap<String, Object> params);
	List<OfferingCpn> selectOfferingCpn(HashMap<String, Object> params);
	
	Integer selectOfferingDtlCnt(HashMap<String, Object> params);
	List<OfferingDtl> selectOfferingDtl(HashMap<String, Object> params);
	
	Integer selectOfferingMsDcCnt(HashMap<String, Object> params);
	List<OfferingMsDc> selectOfferingMsDc(HashMap<String, Object> params);
	
	Integer selectOfferingMsSaveCnt(HashMap<String, Object> params);
	List<OfferingMsSave> selectOfferingMsSave(HashMap<String, Object> params);
	
	Integer selectOfferingMsStampCnt(HashMap<String, Object> params);
	List<OfferingMsStamp> selectOfferingMsStamp(HashMap<String, Object> params);
	
	Integer selectOfferingMsUseCnt(HashMap<String, Object> params);
	List<OfferingMsUse> selectOfferingMsUse(HashMap<String, Object> params);
	
	Integer selectOfferingOrderCnt(HashMap<String, Object> params);
	List<OfferingOrder> selectOfferingOrder(HashMap<String, Object> params);
	
	Integer selectOfferingPromoCnt(HashMap<String, Object> params);
	List<OfferingPromo> selectOfferingPromo(HashMap<String, Object> params);
}
