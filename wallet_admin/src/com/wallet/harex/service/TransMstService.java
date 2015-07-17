package com.wallet.harex.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.harex.dao.OfferingDao;
import com.wallet.harex.dao.StatsDao;
import com.wallet.harex.dao.TransMstDao;
import com.wallet.harex.model.OfferingList;
import com.wallet.harex.model.OfferingDetail;
import com.wallet.harex.model.StatsReport;
import com.wallet.harex.model.StatsListComp;
import com.wallet.harex.model.SystemM;
import com.wallet.harex.model.TransMst;
import com.wallet.harex.model.TransMstCpn;
import com.wallet.harex.model.TransMstMsDc;
import com.wallet.harex.model.TransMstMsUse;
import com.wallet.harex.model.TransMstSave;
import com.wallet.harex.model.TransMstStamp;

public class TransMstService {
	private Logger log = Log.getLogger("logs");
	private final TransMstDao dao;
	
	public TransMstService() {
		dao = new TransMstDao();
	}
	
	/**
	 * 거래내역조회 cnt
	 */
	public int selectTransMstCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectTransMstCnt(params).toString());
	}
	
	/**
	 * 거래내역조회 select
	 */
	public List<TransMst> selectTransMst(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMst(params);
		
		return result;
	}
	
	/**
	 * 거래내역조회 - 쿠폰 select
	 * @return	
	 */
	public List<TransMst> selectTransMstCpn(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMstCpn(params);
		
		return result;
	}
	
	/**
	 * 거래내역조회 - 쿠폰 CNT
	 * @return	
	 */
	public Integer selectTransMstCpnCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectTransMstCpnCnt(params).toString());
		
	}
	
	/**
	 * 거래내역조회_멤버십할인 select
	 * @return	
	 */
	public List<TransMst> selectTransMstMsDc(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMstMsDc(params);
		
		return result;
	}
	
	/**
	 * 거래내역조회_멤버십할인 CNT
	 * @return	
	 */
	public Integer selectTransMstMsDcCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectTransMstMsDcCnt(params).toString());
		
	}
	
	/**
	 * 거래내역조회_멤버십포인트 select
	 * @return	
	 */
	public List<TransMst> selectTransMstMsUse(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMstMsUse(params);
		
		return result;
	}
	
	/**
	 * 거래내역조회_멤버십포인트 CNT
	 * @return	
	 */
	public Integer selectTransMstMsUseCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectTransMstMsUseCnt(params).toString());
		
	}
	
	/**
	 * 거래내역조회_멤버십적립 select
	 * @return	
	 */
	public List<TransMst> selectTransMstMsSave(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMstMsSave(params);
		
		return result;
	}
	
	/**
	 * 거래내역조회_멤버십적립 CNT
	 * @return	
	 */
	public Integer selectTransMstMsSaveCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectTransMstMsSaveCnt(params).toString());
		
	}
	
	/**
	 * 거래내역조회_스탬프 select
	 * @return	
	 */
	public List<TransMst> selectTransMstStamp(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMstStamp(params);
		
		return result;
	}
	
	/**
	 * 거래내역조회_스탬프 CNT
	 * @return	
	 */
	public Integer selectTransMstStampCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectTransMstStampCnt(params).toString());
		
	}
	
	/**
	 * 거래내역조회 popup_쿠폰
	 * @return	
	 */
	public TransMstCpn selectTransMstCpnPop(HashMap<String, Object> params) {
		
		TransMstCpn result = null;
		result = dao.selectTransMstCpnPop(params);
		return result;
	}
	
	/**
	 * 거래내역조회 popup_멤버십할인
	 * @return	
	 */
	public TransMstMsDc selectTransMstMsDcPop(HashMap<String, Object> params) {
		
		TransMstMsDc result = null;
		result = dao.selectTransMstMsDcPop(params);
		return result;
	}
	
	/**
	 * 거래내역조회 popup_멤버십포인트
	 * @return	
	 */
	public TransMstMsUse selectTransMstMsUsePop(HashMap<String, Object> params) {
		
		TransMstMsUse result = null;
		result = dao.selectTransMstMsUsePop(params);
		return result;
	}
	
	/**
	 * 거래내역조회 popup_멤버십적립
	 * @return	
	 */
	public TransMstSave selectTransMstMsSavePop(HashMap<String, Object> params) {
		
		TransMstSave result = null;
		result = dao.selectTransMstMsSavePop(params);
		return result;
	}
	
	/**
	 * 거래내역조회 popup_스탬프
	 * @return	
	 */
	public TransMstStamp selectTransMstStampPop(HashMap<String, Object> params) {
		
		TransMstStamp result = null;
		result = dao.selectTransMstStampPop(params);
		return result;
	}
}
