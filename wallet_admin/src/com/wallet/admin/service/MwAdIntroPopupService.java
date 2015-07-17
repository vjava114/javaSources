package com.wallet.admin.service;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import com.wallet.admin.dao.MwAdIntroPopupDao;
import com.wallet.admin.model.MwAdIntroPopup;
import com.wallet.common.util.Log;



/*
 * Filename	: MwAdIntroPopupService.java
 * Class	: com.wallet.admin.service.MwAdIntroPopupService
 * History	: 2012/08/23, psj, �۾����� : �˾�/��� ���� > ��Ʈ�� �˾�
 * Comment	:
 */
public class MwAdIntroPopupService {
	private Logger log = Log.getLogger("logs");
	private final MwAdIntroPopupDao dao;
	
	public MwAdIntroPopupService() {
		dao = new MwAdIntroPopupDao();
	}
	
	
	/**
	 * ������ �˾� ��ȸ select
	 */
	public List<MwAdIntroPopup> selectIntroPopupList(HashMap<String,Object> params) {

		List<MwAdIntroPopup> result = null;
		
		result = dao.selectIntroPopupList(params);

		return result;
	}
	
	/**
	 * ������ �˾� ��ȸ select
	 */
	public int selectIntroPopupListTotalCnt(HashMap<String,Object> params) {
		
		int totalCount = dao.selectIntroPopupListTotalCnt(params);

		return totalCount;
	}
	
	/**
	 * ������ �˾� �� ��ȸ select
	 */
	public MwAdIntroPopup selectIntroPopupListDtl(HashMap<String,Object> params) {

		MwAdIntroPopup result = null;
		
		result = dao.selectIntroPopupListDtl(params);

		return result;
	}
	
	/**
	 * ������ �˾� ���
	 */
	public void insertIntroPopupReg(HashMap<String,Object> params) {

		HashMap<String,Object> updateParam = new HashMap<String,Object>();
			
		//������°� Y�� ��� �ش� os�� ��� ������� R -> F update
		if("R".equals(params.get("stat"))) {
			updateParam.put("os", params.get("os"));
			updateParam.put("stat", "F");
			updateParam.put("stat_update", "F");

			dao.updateIntroPopupStat(updateParam);	//update
		}

		dao.insertIntroPopupReg(params);					//isnert

	}
	
	/**
	 * ������ �˾� ������� ����
	 */
	public void updateIntroPopupStat(HashMap<String,Object> params) {

		HashMap<String,Object> updateParam = new HashMap<String,Object>();

		//R:�Խ��Ұ��, F:�Խ� �ߴ��Ұ��
		if("F".equals(params.get("stat"))) {
			//�ش� os id�� F update
			dao.updateIntroPopupStat(params);
		} else {
			
			//�ش� os�� ��� ������� R -> F update
			updateParam.put("os", params.get("os"));
			updateParam.put("stat", "F");
			updateParam.put("stat_update", "F");
			
			dao.updateIntroPopupStat(updateParam);
			
			//�ش� os�� id�� R update
			updateParam.clear();
			updateParam.put("idx", params.get("idx"));
			updateParam.put("os", params.get("os"));
			updateParam.put("stat", "R");
			
			dao.updateIntroPopupStat(updateParam);
		}


	}
	
	/**
	 * ������ �˾� �� ���� ����
	 */
	public void updateIntroPopupDtl(HashMap<String,Object> params) {

		HashMap<String,Object> updateParam = new HashMap<String,Object>();
	
		//R:�Խ��Ұ��
		if("R".equals(params.get("stat"))) {
	
			//�ش� os�� ��� ������� R -> F update
			updateParam.put("os", params.get("os"));
			updateParam.put("stat", "F");
			updateParam.put("stat_update", "F");
			
			dao.updateIntroPopupStat(updateParam);
		
		}
			
		dao.updateIntroPopupDtl(params);

	}
	
	/**
	 * ������ �˾� ������ ����
	 */
	public void deleteIntroPopupDtl(HashMap<String,Object> params) {

		dao.deleteIntroPopupDtl(params);

	}
	
}
