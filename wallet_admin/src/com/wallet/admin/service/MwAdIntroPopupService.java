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
 * History	: 2012/08/23, psj, 작업구분 : 팝업/배너 관리 > 인트로 팝업
 * Comment	:
 */
public class MwAdIntroPopupService {
	private Logger log = Log.getLogger("logs");
	private final MwAdIntroPopupDao dao;
	
	public MwAdIntroPopupService() {
		dao = new MwAdIntroPopupDao();
	}
	
	
	/**
	 * 인프로 팝업 조회 select
	 */
	public List<MwAdIntroPopup> selectIntroPopupList(HashMap<String,Object> params) {

		List<MwAdIntroPopup> result = null;
		
		result = dao.selectIntroPopupList(params);

		return result;
	}
	
	/**
	 * 인프로 팝업 조회 select
	 */
	public int selectIntroPopupListTotalCnt(HashMap<String,Object> params) {
		
		int totalCount = dao.selectIntroPopupListTotalCnt(params);

		return totalCount;
	}
	
	/**
	 * 인프로 팝업 상세 조회 select
	 */
	public MwAdIntroPopup selectIntroPopupListDtl(HashMap<String,Object> params) {

		MwAdIntroPopup result = null;
		
		result = dao.selectIntroPopupListDtl(params);

		return result;
	}
	
	/**
	 * 인프로 팝업 등록
	 */
	public void insertIntroPopupReg(HashMap<String,Object> params) {

		HashMap<String,Object> updateParam = new HashMap<String,Object>();
			
		//게재상태가 Y일 경우 해당 os의 모든 게재상태 R -> F update
		if("R".equals(params.get("stat"))) {
			updateParam.put("os", params.get("os"));
			updateParam.put("stat", "F");
			updateParam.put("stat_update", "F");

			dao.updateIntroPopupStat(updateParam);	//update
		}

		dao.insertIntroPopupReg(params);					//isnert

	}
	
	/**
	 * 인프로 팝업 게재상태 변경
	 */
	public void updateIntroPopupStat(HashMap<String,Object> params) {

		HashMap<String,Object> updateParam = new HashMap<String,Object>();

		//R:게시할경우, F:게시 중단할경우
		if("F".equals(params.get("stat"))) {
			//해당 os id만 F update
			dao.updateIntroPopupStat(params);
		} else {
			
			//해당 os의 모든 게재상태 R -> F update
			updateParam.put("os", params.get("os"));
			updateParam.put("stat", "F");
			updateParam.put("stat_update", "F");
			
			dao.updateIntroPopupStat(updateParam);
			
			//해당 os의 id만 R update
			updateParam.clear();
			updateParam.put("idx", params.get("idx"));
			updateParam.put("os", params.get("os"));
			updateParam.put("stat", "R");
			
			dao.updateIntroPopupStat(updateParam);
		}


	}
	
	/**
	 * 인프로 팝업 상세 정보 변경
	 */
	public void updateIntroPopupDtl(HashMap<String,Object> params) {

		HashMap<String,Object> updateParam = new HashMap<String,Object>();
	
		//R:게시할경우
		if("R".equals(params.get("stat"))) {
	
			//해당 os의 모든 게재상태 R -> F update
			updateParam.put("os", params.get("os"));
			updateParam.put("stat", "F");
			updateParam.put("stat_update", "F");
			
			dao.updateIntroPopupStat(updateParam);
		
		}
			
		dao.updateIntroPopupDtl(params);

	}
	
	/**
	 * 인프로 팝업 상세정보 삭제
	 */
	public void deleteIntroPopupDtl(HashMap<String,Object> params) {

		dao.deleteIntroPopupDtl(params);

	}
	
}
