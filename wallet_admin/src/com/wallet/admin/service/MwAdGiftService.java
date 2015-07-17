package com.wallet.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.dao.MwAdGiftDao;
import com.wallet.admin.model.MwAdCard;
import com.wallet.admin.model.MwAdGift;
import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.util.StringUtil;


/*
 * Filename	: MwAdGiftService.java
 * Class	: com.wallet.admin.service.MwAdGiftService
 * History	: 2012/08/23, psj, �۾����� : ��ǰ�� ���� > ��ǰ��
 * Comment	:
 */
public class MwAdGiftService {
	private Logger log = Log.getLogger("logs");
	private final MwAdGiftDao dao;
	
	public MwAdGiftService() {
		dao = new MwAdGiftDao();
	}
	
	/**
	 * �ű� ��ǰ�� ��� insert
	 */
	public void insertGiftReg(HashMap<String,Object> params, MultipartHttpServletRequest mpRequest) {

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
	      		 if("l_img_i4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_list_image_file_path_i4");
		      		 params.put("l_img_i4", path + fileName); //����Ʈ-������4 �̹��� ���� ��� + ���ϸ�
		      	 } else if("l_img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_list_image_file_path_i3");
		      		 params.put("l_img_i3", path + fileName); //����Ʈ-������3 �̹��� ���� ��� + ���ϸ�
		      	 } else if("l_img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_list_image_file_path_r4");
		      		 params.put("l_img_r4", path + fileName); //����Ʈ-�ȵ���̵� �̹��� ���� ��� + ���ϸ�
		      	 } else if("d_img_i4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_card_image_file_path_i4");
		      		 params.put("d_img_i4", path + fileName); //����Ʈ-������4 �̹��� ���� ��� + ���ϸ�
		      	 } else if("d_img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_card_image_file_path_i3");
		      		 params.put("d_img_i3", path + fileName); //����Ʈ-������3 �̹��� ���� ��� + ���ϸ�
		      	 } else if("d_img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_card_image_file_path_r4");
		      		 params.put("d_img_r4", path + fileName); //����Ʈ-�ȵ���̵� �̹��� ���� ��� + ���ϸ�
		      	 }

		      	 FileUtil.writeFile(multiFile, path, fileName);
	      	 }
	      	 
	        }
	    }
	    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//max+1 main_idx ��ȸ
		MwAdGift mainIdx = null;
		mainIdx = dao.selectGiftMainIdx();
		
		params.put("main_idx", mainIdx.getMainIdx());
		dao.insertGiftReg(params);			//isnert

	}
	
	/**
	 * ��ǰ�� ���� ��ȸ select
	 */
	public List<MwAdGift> selectGiftList(HashMap<String,Object> params) {

		List<MwAdGift> result = null;
		
		result = dao.selectGiftList(params);

		return result;
	}
	
	/**
	 * ��ǰ�� ���� �� ��ȸ select
	 */
	public MwAdGift selectGiftListDtl(HashMap<String,Object> params) {

		MwAdGift result = null;

		result = dao.selectGiftListDtl(params);

		return result;
	}
	
	/**
	 * ī��� ���� ���� delete
	 */
	public void deleteGiftDtl(HashMap<String,Object> params)  {
		
		deleteFile(params);
		
		dao.deleteGiftDtl(params);
		dao.updateGiftIdx(params);
	}
	
	/**
	 * ���� ����
	 * - ��ȸ �� ���� ����
	 */
	public void deleteFile(HashMap<String,Object> params)  {
		MwAdGift result = null;

		params.put("view", "dtl");
		params.put("top", 1);
		result = dao.selectGiftListDtl(params);
		List<String> file_list = new ArrayList<String>();
		
		//����Ʈ�̹���
		if( !StringUtil.isNull(result.getlImgI3())) {
			file_list.add(result.getlImgI3());
		}
		if( !StringUtil.isNull(result.getlImgI4())) {
			file_list.add(result.getlImgI4());
		}
		if( !StringUtil.isNull(result.getlImgR4())) {
			file_list.add(result.getlImgR4());
		}

		//ī���̹���
		if( !StringUtil.isNull(result.getdImgI3())) {
			file_list.add(result.getdImgI3());
		}
		if( !StringUtil.isNull(result.getdImgI4())) {
			file_list.add(result.getdImgI4());
		}
		if( !StringUtil.isNull(result.getdImgR4())) {
			file_list.add(result.getdImgR4());
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
	
	/**
	 * �ű� ī�� update
	 */
	public void updateGiftDtl(HashMap<String,Object> params, MultipartHttpServletRequest mpRequest) {

		try {
			log.debug("### MwAdGiftService updateGiftDtl START ###");
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
		      		 if("l_img_i4".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_list_image_file_path_i4");
			      		 params.put("l_img_i4", path + fileName); //����Ʈ-������4 �̹��� ���� ��� + ���ϸ�
			      	 } else if("l_img_i3".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_list_image_file_path_i3");
			      		 params.put("l_img_i3", path + fileName); //����Ʈ-������3 �̹��� ���� ��� + ���ϸ�
			      	 } else if("l_img_r4".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_list_image_file_path_r4");
			      		 params.put("l_img_r4", path + fileName); //����Ʈ-�ȵ���̵� �̹��� ���� ��� + ���ϸ�
			      	 } else if("d_img_i4".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_card_image_file_path_i4");
			      		 params.put("d_img_i4", path + fileName); //����Ʈ-������4 �̹��� ���� ��� + ���ϸ�
			      	 } else if("d_img_i3".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_card_image_file_path_i3");
			      		 params.put("d_img_i3", path + fileName); //����Ʈ-������3 �̹��� ���� ��� + ���ϸ�
			      	 } else if("d_img_r4".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_card_image_file_path_r4");
			      		 params.put("d_img_r4", path + fileName); //����Ʈ-�ȵ���̵� �̹��� ���� ��� + ���ϸ�
			      	 }

			      	 FileUtil.writeFile(multiFile, path, fileName);
		      	 }
	      	 
	        }
	    }
	    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dao.updateGiftDtl(params);							//update
		log.debug("### MwAdGiftService updateGiftDtl END ###");
	}
	
	/**
	 * ��ǰ�� ���� ���� update
	 * - 1.������ ������ ������ ���� update
	 * - 2.���������� �ش� ������ update �Ѵ�.
	 */
	public List<MwAdGift> updateGiftIdx(HashMap<String,Object> params, String gift_id) {
		
		List<MwAdGift> result = null;
		
		try {
			
			//1.������ ������ ������ ���� update
			dao.updateGiftIdx(params);
			
			params.put("gift_id", gift_id);
			params.put("main_idx", params.get("targetIdx"));
			params.remove("thisIdx");
			params.remove("targetIdx");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//2.���������� �ش� ������ update �Ѵ�.
		dao.updateGiftIdx(params);

		return result;
	}
	
}
