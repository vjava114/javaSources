package com.wallet.admin.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.admin.dao.MwAdMenuIdCheckDao;
import com.wallet.admin.model.MwAdMenuIdCheck;

import com.wallet.common.util.Log;


/*
 * Filename	: MwAdMenuIdCheckService.java
 * Class	: com.wallet.admin.service.MwAdMenuIdCheckService
 * History	: 2012/08/23, psj, 작업구분 : 글 작성시 id 중복체크(카드/상품권/결재)
 * Comment	:
 */
public class MwAdMenuIdCheckService {
	private Logger log = Log.getLogger("logs");
	private final MwAdMenuIdCheckDao dao;
	
	public MwAdMenuIdCheckService() {
		dao = new MwAdMenuIdCheckDao();
	}
	
	/**
	 * 카드,상품권,결제 TB id 중복 체크
	 */
	public String selectIdCheck(HashMap<String,Object> params) {

		String idYn = "";
		String sid = "";
		List<MwAdMenuIdCheck> result = null;
		
		if("CARD".equals(params.get("menu"))) {
			
			result = dao.selectIdCheck(params);
			idYn = cardIdYnCheck(params, result);
			
		} else if("GIFT".equals(params.get("menu"))) {
			sid = String.valueOf(params.get("sid"));
			params.remove("sid");
			//상품권 id체크
			result = dao.selectIdCheck(params);
			idYn = idYnCheck(params, result);
			
			//상품권 id 사용가능할경우, sid 체크
			if("Y".equals(idYn)) {
				result = null;
				idYn = "";
				params.remove("id");
				params.put("sid", sid);
				
				result = dao.selectIdCheck(params);
				idYn = idYnCheck(params, result);
			}
			
		} else if("PAY".equals(params.get("menu"))) {
			
			result = dao.selectIdCheck(params);
			idYn = idYnCheck(params, result);
			
		}
		
		return idYn;
	}

	/**
	 * 카드 id 중복 체크
	 */
	public String cardIdYnCheck(HashMap<String,Object> params, List<MwAdMenuIdCheck> result) {
	
		String idYn = "Y";	//Y:사용가능, N:불가능
		
		//C:안드로이드,아이폰, G:안드로이드, A:아이폰 - 일경우 사용가능한지 id 체크
		if("C".equals(params.get("os"))) {
			if(result.size() > 0) {
				idYn = "N";
			}
		} else if("G".equals(params.get("os"))) {
			
			for(int i=0; i<result.size(); i++) {
				if("G".equals(result.get(i).getOs())) {
					idYn = "N";
				} 
			}
			
		} else if("A".equals(params.get("os"))) {
			
			for(int i=0; i<result.size(); i++) {
				if("A".equals(result.get(i).getOs())) {
					idYn = "N";
				} 
			}
			
		}
		
		return idYn;
		
	}
	
	/**
	 * 상품권,결제 TB id 중복 체크
	 */
	public String idYnCheck(HashMap<String,Object> params, List<MwAdMenuIdCheck> result) {
			
			String idYn = "Y";	//Y:사용가능, N:불가능
			
			if(result.size() > 0) {
				idYn = "N";
			}

			return idYn;
			
		}
	
	
}
