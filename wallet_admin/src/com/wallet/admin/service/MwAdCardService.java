package com.wallet.admin.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.dao.MwAdCardDao;
import com.wallet.admin.model.MwAdCard;
import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.util.StringUtil;


/*
 * Filename	: MwAdCardService.java
 * Class	: com.wallet.admin.service.MwAdCardService
 * History	: 2012/08/23, psj, �۾����� : ī��� ���� > ī��
 * Comment	:
 */
public class MwAdCardService {
	private Logger log = Log.getLogger("logs");
	private final MwAdCardDao dao;
	
	public MwAdCardService() {
		dao = new MwAdCardDao();
	}
	
	/**
	 * ī��� ���� ��ȸ select
	 */
	public List<MwAdCard> selectCardList(HashMap<String,Object> params) {
		
		List<MwAdCard> result = null;

		result = dao.selectCardList(params);
		
		return result;
	}
	
	/**
	 * ī��� ���� �� ��ȸ select
	 */
	public MwAdCard selectCardListDtl(HashMap<String,Object> params) {
		
		MwAdCard result = null;

		result = dao.selectCardListDtl(params);
		
		return result;
	}
	
	/**
	 * ī��� ���� ���� update
	 * - 1.������ ������ ������ ���� update
	 * - 2.���������� �ش� ������ update �Ѵ�.
	 */
	public void updateCardIdx(HashMap<String,Object> params, String cidx)  {

			//1.������ ������ ������ ���� update
			dao.updateCardIdx(params);
			
			params.put("cidx", cidx);
			params.put("idx", params.get("targetIdx"));
			params.remove("thisIdx");
			params.remove("targetIdx");
		
			//2.���������� �ش� ������ update �Ѵ�.
			dao.updateCardIdx(params);

	}
	
	/**
	 * �ű� ī�� ��� insert
	 */
	public void insertCardReg(HashMap<String,Object> android_params, HashMap<String,Object> iphone_params,
			MultipartHttpServletRequest mpRequest)  {
		
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
	      		 
	      		 if("img_i4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_i4");
		      		 iphone_params.put("img_i4", path + fileName); //������4 �̹��� ���� ��� + ���ϸ�
		      	 } else if("img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_i3");
		      		 iphone_params.put("img_i3", path + fileName); //������3 �̹��� ���� ��� + ���ϸ�
		      	 } else if("img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_r4");
		      		 android_params.put("img_r4", path + fileName); //�ȵ���̵� �̹��� ���� ��� + ���ϸ�
		      	 }
		      	 FileUtil.writeFile(multiFile, path, fileName);
		      	 
	      	 }
	      	 
	        }
	    }

		} catch (Exception e) {
			e.printStackTrace();
		}

		//max+1 idx ��ȸ
		MwAdCard cardIdx = null;
		cardIdx = dao.selectCardIdx();

		//�ȵ���̵�, ������ insert
    if("G".equals(android_params.get("os"))) {
    	android_params.put("idx", cardIdx.getgIdx());
    	dao.insertCardReg(android_params);
    }
    
    if("A".equals(iphone_params.get("os"))) {
    	iphone_params.put("idx", cardIdx.getaIdx());
    	dao.insertCardReg(iphone_params);	
    }
   
	}
	
	/**
	 * ī��� ���� ���� update
	 */
	public void updateCardDtl(HashMap<String,Object> params, MultipartHttpServletRequest mpRequest) {
		
		try {
			
			String path = "";
			String fileName = "";
			boolean fileExt = false;
			Iterator fileNameIterator = mpRequest.getFileNames();
			
	    while (fileNameIterator.hasNext()) {
	        MultipartFile multiFile = mpRequest
	                .getFile((String) fileNameIterator.next());
	        
	       if (multiFile.getSize() > 0) {
	      	 //log.debug("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename() +", " + multiFile.getOriginalFilename().indexOf("."));
	      	 
	      	 //���ϸ� ���� => ���ϸ�_����Ͻú�.Ȯ����
	      	 fileName = StringUtil.fileNmAddDate(multiFile.getOriginalFilename());
	      	 
	      	 //���� Ȯ���� üũ - �̹���
	      	 fileExt = StringUtil.getFileImageExtCk(fileName);
	      	 
	      	 if(fileExt) {
	      		 
	      		 if("img_i4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_i4");
		      		 params.put("img_i4", path + fileName); //������4 �̹��� ���� ��� + ���ϸ�
		      	 } else if("img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_i3");
		      		 params.put("img_i3", path + fileName); //������3 �̹��� ���� ��� + ���ϸ�
		      	 } else if("img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_r4");
		      		 params.put("img_r4", path + fileName); //�ȵ���̵� �̹��� ���� ��� + ���ϸ�
		      	 }
		      	
		      	 FileUtil.writeFile(multiFile, path, fileName);
	      	 }
	      	 
	        }
	    }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dao.updateCardDtl(params);

	}
	
	/**
	 * ī��� ���� ���� delete
	 *  - ���� �Ϸ� ��, �ڽź��� ū ������ ���ؼ� -1 update
	 */
	public void deleteCardDtl(HashMap<String,Object> params)  {

		deleteFile(params);	//���� ���� ȣ��
		
		dao.deleteCardDtl(params);
		dao.updateCardIdx(params);

	}
	
	/**
	 * ���� ����
	 * - ��ȸ �� ���� ����
	 */
	public void deleteFile(HashMap<String,Object> params)  {
		MwAdCard result = null;

		params.put("view", "dtl");
		params.put("top", 1);
		result = dao.selectCardListDtl(params);
		List<String> file_list = new ArrayList<String>();
		
		//����Ʈ�� ���
		if( !StringUtil.isNull(result.getImgI3())) {
			file_list.add(result.getImgI3());
		}
		if( !StringUtil.isNull(result.getImgI4())) {
			file_list.add(result.getImgI4());
		}
		if( !StringUtil.isNull(result.getImgR4())) {
			file_list.add(result.getImgR4());
		}
		if( !StringUtil.isNull(result.getImgR5())) {
			file_list.add(result.getImgR5());
		}
		if( !StringUtil.isNull(result.getImgR6())) {
			file_list.add(result.getImgR6());
		}
		if( !StringUtil.isNull(result.getImgR7())) {
			file_list.add(result.getImgR7());
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
