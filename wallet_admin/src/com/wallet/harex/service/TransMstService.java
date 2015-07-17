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
	 * �ŷ�������ȸ cnt
	 */
	public int selectTransMstCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectTransMstCnt(params).toString());
	}
	
	/**
	 * �ŷ�������ȸ select
	 */
	public List<TransMst> selectTransMst(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMst(params);
		
		return result;
	}
	
	/**
	 * �ŷ�������ȸ - ���� select
	 * @return	
	 */
	public List<TransMst> selectTransMstCpn(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMstCpn(params);
		
		return result;
	}
	
	/**
	 * �ŷ�������ȸ - ���� CNT
	 * @return	
	 */
	public Integer selectTransMstCpnCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectTransMstCpnCnt(params).toString());
		
	}
	
	/**
	 * �ŷ�������ȸ_��������� select
	 * @return	
	 */
	public List<TransMst> selectTransMstMsDc(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMstMsDc(params);
		
		return result;
	}
	
	/**
	 * �ŷ�������ȸ_��������� CNT
	 * @return	
	 */
	public Integer selectTransMstMsDcCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectTransMstMsDcCnt(params).toString());
		
	}
	
	/**
	 * �ŷ�������ȸ_���������Ʈ select
	 * @return	
	 */
	public List<TransMst> selectTransMstMsUse(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMstMsUse(params);
		
		return result;
	}
	
	/**
	 * �ŷ�������ȸ_���������Ʈ CNT
	 * @return	
	 */
	public Integer selectTransMstMsUseCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectTransMstMsUseCnt(params).toString());
		
	}
	
	/**
	 * �ŷ�������ȸ_��������� select
	 * @return	
	 */
	public List<TransMst> selectTransMstMsSave(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMstMsSave(params);
		
		return result;
	}
	
	/**
	 * �ŷ�������ȸ_��������� CNT
	 * @return	
	 */
	public Integer selectTransMstMsSaveCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectTransMstMsSaveCnt(params).toString());
		
	}
	
	/**
	 * �ŷ�������ȸ_������ select
	 * @return	
	 */
	public List<TransMst> selectTransMstStamp(HashMap<String, Object> params) {
		List<TransMst> result = null;
		
		result = dao.selectTransMstStamp(params);
		
		return result;
	}
	
	/**
	 * �ŷ�������ȸ_������ CNT
	 * @return	
	 */
	public Integer selectTransMstStampCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectTransMstStampCnt(params).toString());
		
	}
	
	/**
	 * �ŷ�������ȸ popup_����
	 * @return	
	 */
	public TransMstCpn selectTransMstCpnPop(HashMap<String, Object> params) {
		
		TransMstCpn result = null;
		result = dao.selectTransMstCpnPop(params);
		return result;
	}
	
	/**
	 * �ŷ�������ȸ popup_���������
	 * @return	
	 */
	public TransMstMsDc selectTransMstMsDcPop(HashMap<String, Object> params) {
		
		TransMstMsDc result = null;
		result = dao.selectTransMstMsDcPop(params);
		return result;
	}
	
	/**
	 * �ŷ�������ȸ popup_���������Ʈ
	 * @return	
	 */
	public TransMstMsUse selectTransMstMsUsePop(HashMap<String, Object> params) {
		
		TransMstMsUse result = null;
		result = dao.selectTransMstMsUsePop(params);
		return result;
	}
	
	/**
	 * �ŷ�������ȸ popup_���������
	 * @return	
	 */
	public TransMstSave selectTransMstMsSavePop(HashMap<String, Object> params) {
		
		TransMstSave result = null;
		result = dao.selectTransMstMsSavePop(params);
		return result;
	}
	
	/**
	 * �ŷ�������ȸ popup_������
	 * @return	
	 */
	public TransMstStamp selectTransMstStampPop(HashMap<String, Object> params) {
		
		TransMstStamp result = null;
		result = dao.selectTransMstStampPop(params);
		return result;
	}
}
