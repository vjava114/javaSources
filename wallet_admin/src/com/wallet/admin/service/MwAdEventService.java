package com.wallet.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.dao.MwAdEventDao;
import com.wallet.admin.model.MwAdCard;
import com.wallet.admin.model.MwAdEvent;
import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.util.StringUtil;


/*
 * Filename	: MwAdEventService.java
 * Class	: com.wallet.admin.web.base.MwAdEventService
 * History	: 2012/08/23, psj, �۾����� : �̺�Ʈ/�������� ���� > �̺�Ʈ/�������� ���
 * Comment	:
 */
public class MwAdEventService {
	private Logger log = Log.getLogger("logs");
	private final MwAdEventDao dao;
	
	public MwAdEventService() {
		dao = new MwAdEventDao();
	}
	
	/**
	 * �̺�Ʈ/�������� ���� ��ȸ select
	 */
	public List<MwAdEvent> selectEventList(HashMap<String,Object> params) {

		List<MwAdEvent> result = null;
		
		result = dao.selectEventList(params);
		
		return result;
	}
	
	/**
	 * �̺�Ʈ/�������� ���� ��ȸ select total count
	 */
	public int selectEventListTotalCnt(HashMap<String,Object> params) {

		int totalCount = dao.selectEventListTotalCnt(params);
		
		return totalCount;
	}
	
	/**
	 * �̺�Ʈ/�������� ���� �� ��ȸ select
	 */
	public MwAdEvent selectEventListDtl(HashMap<String,Object> params) {

		MwAdEvent result = null;
		
		result = dao.selectEventListDtl(params);
		
		return result;
	}
	
	/**
	 * �̺�Ʈ/�������� �� ���� ����
	 */
	public void updateEventDtl(HashMap<String,Object> params, MultipartHttpServletRequest mpRequest) {

try {
			
			String path = "";
			String fileName = "";
			boolean fileExt = false;
			Iterator fileNameIterator = mpRequest.getFileNames();
			
	    while (fileNameIterator.hasNext()) {
	        MultipartFile multiFile = mpRequest
	                .getFile((String) fileNameIterator.next());

	       if (multiFile.getSize() > 0) {
	      	 //log.debug("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename());
	      	
	      	 //���ϸ� ���� => ���ϸ�_����Ͻú�.Ȯ����
	      	 fileName = StringUtil.fileNmAddDate(multiFile.getOriginalFilename());
	      	 
	      	//���� Ȯ���� üũ - �̹���	      	 
	      	 fileExt = StringUtil.getFileImageExtCk(fileName);
	      	 
	      	 if(fileExt) {
	      		 if("evt_img".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("event_evtImg_file_path");
		      		 params.put("evt_img", path + fileName); //�̺�Ʈ �̹��� ���� ��� + ���ϸ�
		      	 }
		      	 FileUtil.writeFile(multiFile, path, fileName);
		      	 
	      	 }
	      	 
	        }
	    }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//�� type�� text�� ��� �̸� url ����, Web�̸� �۳��� ����
		if("T".equals(params.get("msg_mode"))) {
			params.remove("url");
		} else {
			params.remove("msg");
		}
		
		dao.updateEventDtl(params);
			
	}
	
	/**
	 * �̺�Ʈ/�������� ���� ��� insert
	 */
	public void insertEventReg(HashMap<String,Object> params, MultipartHttpServletRequest mpRequest) {
		
		try {
			
			String path = "";
			String fileName = "";
			boolean fileExt = false;
			Iterator fileNameIterator = mpRequest.getFileNames();
			
	    while (fileNameIterator.hasNext()) {
	        MultipartFile multiFile = mpRequest
	                .getFile((String) fileNameIterator.next());

	       if (multiFile.getSize() > 0) {
	      	 //log.debug("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename());
	      	
	      	 //���ϸ� ���� => ���ϸ�_����Ͻú�.Ȯ����
	      	 fileName = StringUtil.fileNmAddDate(multiFile.getOriginalFilename());
	      	 
	      	//���� Ȯ���� üũ - �̹���	      	 
	      	 fileExt = StringUtil.getFileImageExtCk(fileName);
	      	 
	      	 if(fileExt) {
	      		 if("evt_img".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("event_evtImg_file_path");
		      		 params.put("evt_img", path + fileName); //�̺�Ʈ �̹��� ���� ��� + ���ϸ�
		      	 }
		      	 FileUtil.writeFile(multiFile, path, fileName);
	      	 }
	        }
	    }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//�� type�� text�� ��� �̸� url ����, Web�̸� �۳��� ����
		if("T".equals(params.get("msg_mode"))) {
			params.remove("url");
		} else {
			params.remove("msg");
		}
		
		dao.insertEventReg(params);
	
	}
	
	/**
	 * �̺�Ʈ/�������� ������ ����
	 */
	public void deleteEventDtl(HashMap<String,Object> params) {
		
		deleteFile(params);	//���� ���� ȣ��
		
		dao.deleteEventDtl(params);

	}

	/**
	 * ���� ����
	 * - ��ȸ �� ���� ����
	 */
	public void deleteFile(HashMap<String,Object> params)  {
		MwAdEvent result = null;
		
		params.put("view", "dtl");
		params.put("top", 1);
		result = dao.selectEventListDtl(params);
		List<String> file_list = new ArrayList<String>();
		
		//����Ʈ�� ���
		if( !StringUtil.isNull(result.getEvtImg())) {
			file_list.add(result.getEvtImg());
		}
		

		//���ϻ���
		for(int i=0; i<file_list.size(); i++) {
			try {
				FileUtil.deleteFile(String.valueOf(file_list.get(i)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
