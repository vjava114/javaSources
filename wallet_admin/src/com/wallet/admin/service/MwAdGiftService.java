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
 * History	: 2012/08/23, psj, 작업구분 : 상품권 관리 > 상품권
 * Comment	:
 */
public class MwAdGiftService {
	private Logger log = Log.getLogger("logs");
	private final MwAdGiftDao dao;
	
	public MwAdGiftService() {
		dao = new MwAdGiftDao();
	}
	
	/**
	 * 신규 상품권 등록 insert
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
	      	 
	      	 //파일명 변경 => 파일명_년월일시분.확장자
	      	 fileName = StringUtil.fileNmAddDate(multiFile.getOriginalFilename());
	      	 
	      	//파일 확장자 체크 - 이미지	      	 
	      	 fileExt = StringUtil.getFileImageExtCk(fileName);
	      	 
	      	 if(fileExt) {
	      		 if("l_img_i4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_list_image_file_path_i4");
		      		 params.put("l_img_i4", path + fileName); //리스트-아이폰4 이미지 저장 경로 + 파일명
		      	 } else if("l_img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_list_image_file_path_i3");
		      		 params.put("l_img_i3", path + fileName); //리스트-아이폰3 이미지 저장 경로 + 파일명
		      	 } else if("l_img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_list_image_file_path_r4");
		      		 params.put("l_img_r4", path + fileName); //리스트-안드로이드 이미지 저장 경로 + 파일명
		      	 } else if("d_img_i4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_card_image_file_path_i4");
		      		 params.put("d_img_i4", path + fileName); //리스트-아이폰4 이미지 저장 경로 + 파일명
		      	 } else if("d_img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_card_image_file_path_i3");
		      		 params.put("d_img_i3", path + fileName); //리스트-아이폰3 이미지 저장 경로 + 파일명
		      	 } else if("d_img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("gift_card_image_file_path_r4");
		      		 params.put("d_img_r4", path + fileName); //리스트-안드로이드 이미지 저장 경로 + 파일명
		      	 }

		      	 FileUtil.writeFile(multiFile, path, fileName);
	      	 }
	      	 
	        }
	    }
	    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//max+1 main_idx 조회
		MwAdGift mainIdx = null;
		mainIdx = dao.selectGiftMainIdx();
		
		params.put("main_idx", mainIdx.getMainIdx());
		dao.insertGiftReg(params);			//isnert

	}
	
	/**
	 * 상품권 정보 조회 select
	 */
	public List<MwAdGift> selectGiftList(HashMap<String,Object> params) {

		List<MwAdGift> result = null;
		
		result = dao.selectGiftList(params);

		return result;
	}
	
	/**
	 * 상품권 정보 상세 조회 select
	 */
	public MwAdGift selectGiftListDtl(HashMap<String,Object> params) {

		MwAdGift result = null;

		result = dao.selectGiftListDtl(params);

		return result;
	}
	
	/**
	 * 카드사 정보 삭제 delete
	 */
	public void deleteGiftDtl(HashMap<String,Object> params)  {
		
		deleteFile(params);
		
		dao.deleteGiftDtl(params);
		dao.updateGiftIdx(params);
	}
	
	/**
	 * 파일 삭제
	 * - 조회 후 파일 삭제
	 */
	public void deleteFile(HashMap<String,Object> params)  {
		MwAdGift result = null;

		params.put("view", "dtl");
		params.put("top", 1);
		result = dao.selectGiftListDtl(params);
		List<String> file_list = new ArrayList<String>();
		
		//리스트이미지
		if( !StringUtil.isNull(result.getlImgI3())) {
			file_list.add(result.getlImgI3());
		}
		if( !StringUtil.isNull(result.getlImgI4())) {
			file_list.add(result.getlImgI4());
		}
		if( !StringUtil.isNull(result.getlImgR4())) {
			file_list.add(result.getlImgR4());
		}

		//카드이미지
		if( !StringUtil.isNull(result.getdImgI3())) {
			file_list.add(result.getdImgI3());
		}
		if( !StringUtil.isNull(result.getdImgI4())) {
			file_list.add(result.getdImgI4());
		}
		if( !StringUtil.isNull(result.getdImgR4())) {
			file_list.add(result.getdImgR4());
		}

		//파일삭제
		for(int i=0; i<file_list.size(); i++) {
			try {
				FileUtil.deleteFile(String.valueOf(file_list.get(i)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 신규 카드 update
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
	        	
	        	//파일명 변경 => 파일명_년월일시분.확장자
		      	 fileName = StringUtil.fileNmAddDate(multiFile.getOriginalFilename());
		      	 
		      	//파일 확장자 체크 - 이미지	      	 
		      	 fileExt = StringUtil.getFileImageExtCk(fileName);
		      	 
		      	 if(fileExt) {
		      		 if("l_img_i4".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_list_image_file_path_i4");
			      		 params.put("l_img_i4", path + fileName); //리스트-아이폰4 이미지 저장 경로 + 파일명
			      	 } else if("l_img_i3".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_list_image_file_path_i3");
			      		 params.put("l_img_i3", path + fileName); //리스트-아이폰3 이미지 저장 경로 + 파일명
			      	 } else if("l_img_r4".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_list_image_file_path_r4");
			      		 params.put("l_img_r4", path + fileName); //리스트-안드로이드 이미지 저장 경로 + 파일명
			      	 } else if("d_img_i4".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_card_image_file_path_i4");
			      		 params.put("d_img_i4", path + fileName); //리스트-아이폰4 이미지 저장 경로 + 파일명
			      	 } else if("d_img_i3".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_card_image_file_path_i3");
			      		 params.put("d_img_i3", path + fileName); //리스트-아이폰3 이미지 저장 경로 + 파일명
			      	 } else if("d_img_r4".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("gift_card_image_file_path_r4");
			      		 params.put("d_img_r4", path + fileName); //리스트-안드로이드 이미지 저장 경로 + 파일명
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
	 * 상품권 순서 조정 update
	 * - 1.순서를 변경할 범위를 먼저 update
	 * - 2.마지막으로 해당 순서를 update 한다.
	 */
	public List<MwAdGift> updateGiftIdx(HashMap<String,Object> params, String gift_id) {
		
		List<MwAdGift> result = null;
		
		try {
			
			//1.순서를 변경할 범위를 먼저 update
			dao.updateGiftIdx(params);
			
			params.put("gift_id", gift_id);
			params.put("main_idx", params.get("targetIdx"));
			params.remove("thisIdx");
			params.remove("targetIdx");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//2.마지막으로 해당 순서를 update 한다.
		dao.updateGiftIdx(params);

		return result;
	}
	
}
