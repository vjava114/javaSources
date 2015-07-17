package com.wallet.stat.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.stat.mapper.MwStMemberStatsMapper;
import com.wallet.stat.model.MwStMemberStatsMocaPay;
import com.wallet.stat.model.MwStMemberStatsOs;
import com.wallet.stat.model.MwStMemberStatsAge;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdMemberStatsDao.java
 * Class	: com.wallet.stat.dao.MwAdMemberStatsDao
 * History	: 2012/08/23, psj, �۾����� : ������ ���
 * Comment	:
 */
public class MwStMemberStatsDao extends MybatisCilent implements MwStMemberStatsMapper {

	
	/**
	 * ������������Ȳ ��ȸ : OS/����纰 ���
	 * @return	
	 */
		public MwStMemberStatsOs selectMemberStatsAcmOs(HashMap<String, Object> params) {
		return (MwStMemberStatsOs) sqlMapper.selectOne("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsAcmOs", params);
	}
	
	/**
	 * ������������Ȳ ��ȸ : ����/���� ���
	 * @return	
	 */
		public MwStMemberStatsAge selectMemberStatsAcmAge(HashMap<String, Object> params) {
		return (MwStMemberStatsAge) sqlMapper.selectOne("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsAcmAge", params);
	}
		
	/**
	 * ������������Ȳ ��ȸ : ��ī���� ���
	 * @return	
	 */
		public MwStMemberStatsMocaPay selectMemberStatsAcmMocaPay(HashMap<String, Object> params) {
		return (MwStMemberStatsMocaPay) sqlMapper.selectOne("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsAcmMocaPay", params);
	}

	
	/**
	 * �ű԰�������Ȳ ��ȸ : �ܸ����� / �Ϻ�
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMemberStatsOs> selectMemberStatsNewOsDayList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsNewOsDayList", params);
	}
	
	/**
	 * �ű԰�������Ȳ ��ȸ : �ܸ����� / ����
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMemberStatsOs> selectMemberStatsNewOsMonthList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsNewOsMonthList", params);
	}
	
	/**
	 * �ű԰�������Ȳ ��ȸ : �ܸ����� / �⵵��
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMemberStatsOs> selectMemberStatsNewOsYearList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsNewOsYearList", params);
	}
	
	/**
	 * �ű԰�������Ȳ ��ȸ : �̿��ڱ���(����, ����) �⵵��
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMemberStatsAge> selectMemberStatsNewAgeList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsNewAgeList", params);
	}
	
	/**
	 * �ű԰�������Ȳ ��ȸ : ��ī���� ���� /  �Ϻ�/����/�⵵��
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMemberStatsMocaPay> selectMemberStatsNewMocaPayList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsNewMocaPayList", params);
	}
	
}
