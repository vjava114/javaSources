package com.wallet.harex.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.harex.dao.OfferingDao;
import com.wallet.harex.dao.OfferingEtcDao;
import com.wallet.harex.dao.StatsDao;
import com.wallet.harex.dao.TransMstDao;
import com.wallet.harex.model.OfferingCpn;
import com.wallet.harex.model.OfferingDtl;
import com.wallet.harex.model.OfferingList;
import com.wallet.harex.model.OfferingDetail;
import com.wallet.harex.model.OfferingMsDc;
import com.wallet.harex.model.OfferingMsSave;
import com.wallet.harex.model.OfferingMsStamp;
import com.wallet.harex.model.OfferingMsUse;
import com.wallet.harex.model.OfferingOrder;
import com.wallet.harex.model.OfferingPromo;
import com.wallet.harex.model.OfferingS;
import com.wallet.harex.model.StatsReport;
import com.wallet.harex.model.StatsListComp;
import com.wallet.harex.model.SystemM;
import com.wallet.harex.model.TransMst;
import com.wallet.harex.model.TransMstCpn;
import com.wallet.harex.model.TransMstMsDc;
import com.wallet.harex.model.TransMstMsUse;
import com.wallet.harex.model.TransMstSave;
import com.wallet.harex.model.TransMstStamp;

public class OfferingEtcService {
	private Logger log = Log.getLogger("logs");
	private final OfferingEtcDao dao;
	
	public OfferingEtcService() {
		dao = new OfferingEtcDao();
	}
	
	/**
	 * 복합결제 오퍼링 정보 cnt
	 */
	public int selectOfferingSCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectOfferingSCnt(params).toString());
	}
	
	/**
	 * 복합결제 오퍼링 정보 select
	 */
	public List<OfferingS> selectOfferingS(HashMap<String, Object> params) {
		List<OfferingS> result = null;
		
		result = dao.selectOfferingS(params);
		
		return result;
	}
	
	/**
	 * 복합결제 오퍼링 쿠폰 상세정보 cnt
	 */
	public int selectOfferingCpnCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectOfferingCpnCnt(params).toString());
	}
	
	/**
	 * 복합결제 오퍼링 쿠폰 상세정보 select
	 */
	public List<OfferingCpn> selectOfferingCpn(HashMap<String, Object> params) {
		List<OfferingCpn> result = null;
		
		result = dao.selectOfferingCpn(params);
		
		return result;
	}
	
	/**
	 * 복합결제 오퍼링 상세정보 cnt
	 */
	public int selectOfferingDtlCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectOfferingDtlCnt(params).toString());
	}
	
	/**
	 * 복합결제 오퍼링 상세정보 select
	 */
	public List<OfferingDtl> selectOfferingDtl(HashMap<String, Object> params) {
		List<OfferingDtl> result = null;
		
		result = dao.selectOfferingDtl(params);
		
		return result;
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽할인 상세정보 cnt
	 */
	public int selectOfferingMsDcCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectOfferingMsDcCnt(params).toString());
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽할인 상세정보 select
	 */
	public List<OfferingMsDc> selectOfferingMsDc(HashMap<String, Object> params) {
		List<OfferingMsDc> result = null;
		
		result = dao.selectOfferingMsDc(params);
		
		return result;
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 적립정보 cnt
	 */
	public int selectOfferingMsSaveCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectOfferingMsSaveCnt(params).toString());
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 적립정보 select
	 */
	public List<OfferingMsSave> selectOfferingMsSave(HashMap<String, Object> params) {
		List<OfferingMsSave> result = null;
		
		result = dao.selectOfferingMsSave(params);
		
		return result;
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 스탬프정보 cnt
	 */
	public int selectOfferingMsStampCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectOfferingMsStampCnt(params).toString());
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 스탬프정보 select
	 */
	public List<OfferingMsStamp> selectOfferingMsStamp(HashMap<String, Object> params) {
		List<OfferingMsStamp> result = null;
		
		result = dao.selectOfferingMsStamp(params);
		
		return result;
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 사용정보 cnt
	 */
	public int selectOfferingMsUseCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectOfferingMsUseCnt(params).toString());
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 사용정보 select
	 */
	public List<OfferingMsUse> selectOfferingMsUse(HashMap<String, Object> params) {
		List<OfferingMsUse> result = null;
		
		result = dao.selectOfferingMsUse(params);
		
		return result;
	}
	
	/**
	 * 복합결제 오퍼링 순위 정보 cnt
	 */
	public int selectOfferingOrderCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectOfferingOrderCnt(params).toString());
	}
	
	/**
	 * 복합결제 오퍼링 순위 정보 select
	 */
	public List<OfferingOrder> selectOfferingOrder(HashMap<String, Object> params) {
		List<OfferingOrder> result = null;
		
		result = dao.selectOfferingOrder(params);
		
		return result;
	}
	
	/**
	 * 복합결제 오퍼링 프로모션정보 cnt
	 */
	public int selectOfferingPromoCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectOfferingPromoCnt(params).toString());
	}
	
	/**
	 * 복합결제 오퍼링 프로모션정보 select
	 */
	public List<OfferingPromo> selectOfferingPromo(HashMap<String, Object> params) {
		List<OfferingPromo> result = null;
		
		result = dao.selectOfferingPromo(params);
		
		return result;
	}
	
	
}
