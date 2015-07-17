package com.wallet.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.dao.MwAdPaymentDao;
import com.wallet.admin.model.MwAdGift;
import com.wallet.admin.model.MwAdPayment;

import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.util.StringUtil;


/*
 * Filename	: MwAdPaymentService.java
 * Class	: com.wallet.admin.service.MwAdPaymentService
 * History	: 2012/08/23, psj, �۾����� : �������� ���� > ����
 * Comment	:
 */
public class MwAdPaymentService {
	private Logger log = Log.getLogger("logs");
	private final MwAdPaymentDao dao;
	
	public MwAdPaymentService() {
		dao = new MwAdPaymentDao();
	}
	
	/**
	 * �ű� ���� ���� ��� insert
	 * - ���� ���� �� ���ε� ��, �̿��� �� ���� ���̺� insert
	 */
	public void insertPaymentReg(HashMap<String,Object> params, List<Map<String,String>> clause_list, MultipartHttpServletRequest mpRequest) {

		HashMap<String,Object> clause_params = new HashMap<String,Object>();
		List<String> file_list = new ArrayList<String>();
		
		try {
			
			String path = "";
			String fileName = "";
			boolean fileExt = false;
			//����̹���, ī���̹��� ���� ���ε�
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
		      		 path = PropertiesUtil.get("pay_list_image_file_path_i4");
		      		 params.put("l_img_i4", path + fileName); //����Ʈ-������4 �̹��� ���� ��� + ���ϸ�
		      	 } else if("l_img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("pay_list_image_file_path_i3");
		      		 params.put("l_img_i3", path + fileName); //����Ʈ-������3 �̹��� ���� ��� + ���ϸ�
		      	 } else if("l_img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("pay_list_image_file_path_r4");
		      		 params.put("l_img_r4", path + fileName); //����Ʈ-�ȵ���̵� �̹��� ���� ��� + ���ϸ�
		      	 } else if("d_img_i4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("pay_card_image_file_path_i4");
		      		 params.put("d_img_i4", path + fileName); //����Ʈ-������4 �̹��� ���� ��� + ���ϸ�
		      	 } else if("d_img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("pay_card_image_file_path_i3");
		      		 params.put("d_img_i3", path + fileName); //����Ʈ-������3 �̹��� ���� ��� + ���ϸ�
		      	 } else if("d_img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("pay_card_image_file_path_r4");
		      		 params.put("d_img_r4", path + fileName); //����Ʈ-�ȵ���̵� �̹��� ���� ��� + ���ϸ�
		      	 }
		      	 FileUtil.writeFile(multiFile, path, fileName);
	      	 }
	      	 
	        }
	    }
	    
		} catch (Exception e) {
			e.printStackTrace();
		}
			//���������  ���� �� ������ �ϰ�쿡�� �̿��� �� ����޴� ����
			if ("W".equals(params.get("link_mode"))) {
				
				//�̿��� ���� ���� �� ���ε�
				for(int i=0; i<clause_list.size(); i++) {
		
					file_list.add(clause_list.get(i).get("use_clause"));	//�̿��� ����
					
					try {
						FileUtil.writeFile(clause_list.get(i).get("use_clause_file_path"), file_list);
					} catch (Exception e) {
						e.printStackTrace();
					}

					file_list.clear();
			
				}

				//�̿��� db insert
				for(int i=0; i<clause_list.size(); i++) {
						
						clause_params = new HashMap<String,Object>();

						clause_params.put("memb_id", String.valueOf(clause_list.get(i).get("memb_id")));
						clause_params.put("idx", Integer.parseInt(clause_list.get(i).get("idx")));
						clause_params.put("title", String.valueOf(clause_list.get(i).get("title")));
						clause_params.put("info", String.valueOf(clause_list.get(i).get("info")));
						clause_params.put("chk", String.valueOf(clause_list.get(i).get("chk")));
						
						dao.insertUseClause(clause_params);
						
				}
				
				//sub �޴� ���� ����(���� ���̺� �̻���)
			}
			
			//pay_code ��ȸ, max+1 main_idx ��ȸ
			MwAdPayment mainIdx = null;
			mainIdx = dao.selectPaymentMainIdx();
			
			params.put("pay_code", mainIdx.getPayCode());
			params.put("main_idx", mainIdx.getMainIdx());
			
	    dao.insertPaymentReg(params);			//isnert
	    
	}
	
	/**
	 * �ű� ���� ���� ��  update
	 * - ���� ���� �� ���ε� ��, �̿��� �� ���� ���̺� insert
	 */
	public void updatePaymentDtl(HashMap<String,Object> params, List<Map<String,String>> clause_list, MultipartHttpServletRequest mpRequest) {

		HashMap<String,Object> clause_params = new HashMap<String,Object>();
		List<String> file_list = new ArrayList<String>();
		
		try {
			
			String path = "";
			String fileName = "";
			boolean fileExt = false;
			//����̹���, ī���̹��� ���� ���ε�
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
        		 path = PropertiesUtil.get("pay_list_image_file_path_i4");
        		 params.put("l_img_i4", path + fileName); //����Ʈ-������4 �̹��� ���� ��� + ���ϸ�
        	 } else if("l_img_i3".equals(multiFile.getName())) {
        		 path = PropertiesUtil.get("pay_list_image_file_path_i3");
        		 params.put("l_img_i3", path + fileName); //����Ʈ-������3 �̹��� ���� ��� + ���ϸ�
        	 } else if("l_img_r4".equals(multiFile.getName())) {
        		 path = PropertiesUtil.get("pay_list_image_file_path_r4");
        		 params.put("l_img_r4", path + fileName); //����Ʈ-�ȵ���̵� �̹��� ���� ��� + ���ϸ�
        	 } else if("d_img_i4".equals(multiFile.getName())) {
        		 path = PropertiesUtil.get("pay_card_image_file_path_i4");
        		 params.put("d_img_i4", path + fileName); //����Ʈ-������4 �̹��� ���� ��� + ���ϸ�
        	 } else if("d_img_i3".equals(multiFile.getName())) {
        		 path = PropertiesUtil.get("pay_card_image_file_path_i3");
        		 params.put("d_img_i3", path + fileName); //����Ʈ-������3 �̹��� ���� ��� + ���ϸ�
        	 } else if("d_img_r4".equals(multiFile.getName())) {
        		 path = PropertiesUtil.get("pay_card_image_file_path_r4");
        		 params.put("d_img_r4", path + fileName); //����Ʈ-�ȵ���̵� �̹��� ���� ��� + ���ϸ�
        	 }
        	 FileUtil.writeFile(multiFile, path, fileName);      		 
      	 }

      	 
        }
			}
	    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//���������  ���� �� ������ �ϰ�쿡�� �̿��� �� ����޴� ����
		if ("W".equals(params.get("link_mode"))) {
			
			//�̿��� ���� ���� �� ���ε�
			for(int i=0; i<clause_list.size(); i++) {
	
				file_list.add(clause_list.get(i).get("use_clause"));	//�̿��� ����
				try {
					FileUtil.writeFile(clause_list.get(i).get("use_clause_file_path"), file_list);
				} catch (Exception e) {
					e.printStackTrace();
				}

				file_list.clear();
		
			}
			
			clause_params.clear();
			clause_params.put("memb_id", params.get("memb_id"));
			dao.deleteUseClause(clause_params);
			
			//�̿��� db insert
			for(int i=0; i<clause_list.size(); i++) {
					
					clause_params = new HashMap<String,Object>();

					clause_params.put("memb_id", String.valueOf(clause_list.get(i).get("memb_id")));
					clause_params.put("idx", Integer.parseInt(clause_list.get(i).get("idx")));
					clause_params.put("title", String.valueOf(clause_list.get(i).get("title")));
					clause_params.put("info", String.valueOf(clause_list.get(i).get("info")));
					clause_params.put("chk", String.valueOf(clause_list.get(i).get("chk")));
					
					dao.insertUseClause(clause_params);

			}
			
			//sub �޴� ���� ����(���� ���̺� �̻���)
			
		} else {
			
			clause_params.clear();
			clause_params.put("memb_id", params.get("memb_id"));
			dao.deleteUseClause(clause_params);
			
		}

    dao.updatePaymentDtl(params);			//isnert

	}

	
	/**
	 * �ű� ���� ���� ��ȸ select
	 */
	public List<MwAdPayment> selectPaymentList(HashMap<String,Object> params) {

		List<MwAdPayment> result = null;
		
		result = dao.selectPaymentList(params);

		return result;
	}
	
	
	/**
	 * �ű� ���� ����  �� ��ȸ select
	 */
	public MwAdPayment selectPaymentListDtl(HashMap<String,Object> params) {

		MwAdPayment result = null;
		result = dao.selectPaymentListDtl(params);
		
		return result;
	}
	
	/**
	 * ���� ������ ���� / �̿��� ���� ����
	 * - ���� �Ϸ� ��, �ڽź��� ū ������ ���ؼ� -1 update
	 */
	public void deletePaymentDtl(HashMap<String,Object> params) {
		
		deleteFile(params);
		
		dao.deletePaymentDtl(params);
		dao.deleteUseClause(params);
		dao.updatePaymentIdx(params);
		
	}
	
	/**
	 * ���� ����
	 * - ��ȸ �� ���� ����
	 */
	public void deleteFile(HashMap<String,Object> params)  {
		MwAdPayment result = null;

		params.put("view", "dtl");
		params.put("top", 1);
		result = dao.selectPaymentListDtl(params);
		List<String> file_list = new ArrayList<String>();
		
		//����Ʈ�̹���
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
	 * �̿��� ��ȸ select
	 */
	public List<Map<String,String>> selectUseClauseList(HashMap<String,Object> params) {
		
		List<MwAdPayment> result = null;
		Map<String, String> result_map = null;
		List<Map<String,String>> result_map_list = new ArrayList<Map<String,String>>();
		String img_host = PropertiesUtil.get("img_host");
		
		result = dao.selectUseClauseList(params);

		String useClause = "";
		
		try {
			
			for(int i=0; i<result.size(); i++) {
				result_map = new HashMap<String, String>();

				useClause = FileUtil.fileReader(result.get(i).getInfo().replace(img_host, ""));
				
				result_map.put("membId", result.get(i).getMembId());
				result_map.put("idx", String.valueOf(result.get(i).getIdx()));
				result_map.put("title", result.get(i).getTitle());
				result_map.put("info", result.get(i).getInfo());
				result_map.put("useClause", String.valueOf(useClause));
				result_map_list.add(i, result_map);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result_map_list;
	}
	
	/**
	 * �������� ���� ���� update
	 * - 1.������ ������ ������ ���� update
	 * - 2.���������� �ش� ������ update �Ѵ�.
	 */
	public List<MwAdPayment> updatePaymentIdx(HashMap<String,Object> params, String memb_id) {
		
		List<MwAdPayment> result = null;
		
		try {
			log.debug("### params: " + params);
			//1.������ ������ ������ ���� update
			dao.updatePaymentIdx(params);
			
			params.put("memb_id", memb_id);
			params.put("main_idx", params.get("targetIdx"));
			params.remove("thisIdx");
			params.remove("targetIdx");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//2.���������� �ش� ������ update �Ѵ�.
		dao.updatePaymentIdx(params);

		return result;
	}
	
}
