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
 * History	: 2012/08/23, psj, �۾����� : �� �ۼ��� id �ߺ�üũ(ī��/��ǰ��/����)
 * Comment	:
 */
public class MwAdMenuIdCheckService {
	private Logger log = Log.getLogger("logs");
	private final MwAdMenuIdCheckDao dao;
	
	public MwAdMenuIdCheckService() {
		dao = new MwAdMenuIdCheckDao();
	}
	
	/**
	 * ī��,��ǰ��,���� TB id �ߺ� üũ
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
			//��ǰ�� idüũ
			result = dao.selectIdCheck(params);
			idYn = idYnCheck(params, result);
			
			//��ǰ�� id ��밡���Ұ��, sid üũ
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
	 * ī�� id �ߺ� üũ
	 */
	public String cardIdYnCheck(HashMap<String,Object> params, List<MwAdMenuIdCheck> result) {
	
		String idYn = "Y";	//Y:��밡��, N:�Ұ���
		
		//C:�ȵ���̵�,������, G:�ȵ���̵�, A:������ - �ϰ�� ��밡������ id üũ
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
	 * ��ǰ��,���� TB id �ߺ� üũ
	 */
	public String idYnCheck(HashMap<String,Object> params, List<MwAdMenuIdCheck> result) {
			
			String idYn = "Y";	//Y:��밡��, N:�Ұ���
			
			if(result.size() > 0) {
				idYn = "N";
			}

			return idYn;
			
		}
	
	
}
