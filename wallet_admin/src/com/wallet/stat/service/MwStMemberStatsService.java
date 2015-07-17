package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStMemberStatsDao;
import com.wallet.stat.model.MwStMemberStatsMocaPay;
import com.wallet.stat.model.MwStMemberStatsOs;
import com.wallet.stat.model.MwStMemberStatsAge;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdMemberStatsService.java
 * Class	: com.wallet.stat.service.MwAdMemberStatsService
 * History	: 2012/08/23, psj, �۾����� : ������ ���
 * Comment	:
 */
public class MwStMemberStatsService {
	private Logger log = Log.getLogger("logs");
	private final MwStMemberStatsDao dao;
	
	public MwStMemberStatsService() {
		dao = new MwStMemberStatsDao();
	}

	/**
	 * ������������Ȳ : OS/����纰 ���
	 */
	public MwStMemberStatsOs selectMemberStatsAcmOs(HashMap<String,Object> params) {

		MwStMemberStatsOs result = null;
		
		result = dao.selectMemberStatsAcmOs(params);

		return result;
	}

	/**
	 * ������������Ȳ : ����/���� ���
	 */
	public MwStMemberStatsAge selectMemberStatsAcmAge(HashMap<String,Object> params) {

		MwStMemberStatsAge result = null;
		
		result = dao.selectMemberStatsAcmAge(params);

		return result;
	}
	
	/**
	 * ������������Ȳ : ��ī���� ���
	 */
	public MwStMemberStatsMocaPay selectMemberStatsAcmMocaPay(HashMap<String,Object> params) {

		MwStMemberStatsMocaPay result = null;
		
		result = dao.selectMemberStatsAcmMocaPay(params);

		return result;
	}
	
	/**
	 * �ű԰�������Ȳ ��ȸ : �ܸ����� / �Ϻ�
	 */
	public List<MwStMemberStatsOs> selectMemberStatsNewOsDayList(HashMap<String,Object> params) {

		List<MwStMemberStatsOs> result = null;
		
		result = dao.selectMemberStatsNewOsDayList(params);

		return result;
	}
	
	/**
	 * �ű԰�������Ȳ ��ȸ : �ܸ����� / ����
	 */
	public List<MwStMemberStatsOs> selectMemberStatsNewOsMonthList(HashMap<String,Object> params) {

		List<MwStMemberStatsOs> result = null;
		
		result = dao.selectMemberStatsNewOsMonthList(params);

		return result;
	}
	
	/**
	 * �ű԰�������Ȳ ��ȸ : �ܸ����� / �⵵��
	 */
	public List<MwStMemberStatsOs> selectMemberStatsNewOsYearList(HashMap<String,Object> params) {

		List<MwStMemberStatsOs> result = null;
		
		result = dao.selectMemberStatsNewOsYearList(params);

		return result;
	}
	
	/**
	 * �ű԰�������Ȳ ��ȸ : �̿��ڱ���(����, ����) / �Ϻ�/����/�⵵��
	 */
	public List<MwStMemberStatsAge> selectMemberStatsNewAgeList(HashMap<String,Object> params) {

		List<MwStMemberStatsAge> result = null;
		
		result = dao.selectMemberStatsNewAgeList(params);

		return result;
	}
	
	/**
	 * �ű԰�������Ȳ ��ȸ : ��ī���� ���� / �Ϻ�/����/�⵵��
	 */
	public List<MwStMemberStatsMocaPay> selectMemberStatsNewMocaPayList(HashMap<String,Object> params) {

		List<MwStMemberStatsMocaPay> result = null;
		
		result = dao.selectMemberStatsNewMocaPayList(params);

		return result;
	}
	
}
