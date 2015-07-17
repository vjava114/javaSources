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
 * History	: 2012/08/23, psj, 작업구분 : 카드사 관리 > 카드
 * Comment	:
 */
public class MwAdCardService {
	private Logger log = Log.getLogger("logs");
	private final MwAdCardDao dao;
	
	public MwAdCardService() {
		dao = new MwAdCardDao();
	}
	
	/**
	 * 카드사 정보 조회 select
	 */
	public List<MwAdCard> selectCardList(HashMap<String,Object> params) {
		
		List<MwAdCard> result = null;

		result = dao.selectCardList(params);
		
		return result;
	}
	
	/**
	 * 카드사 정보 상세 조회 select
	 */
	public MwAdCard selectCardListDtl(HashMap<String,Object> params) {
		
		MwAdCard result = null;

		result = dao.selectCardListDtl(params);
		
		return result;
	}
	
	/**
	 * 카드사 순서 조정 update
	 * - 1.순서를 변경할 범위를 먼저 update
	 * - 2.마지막으로 해당 순서를 update 한다.
	 */
	public void updateCardIdx(HashMap<String,Object> params, String cidx)  {

			//1.순서를 변경할 범위를 먼저 update
			dao.updateCardIdx(params);
			
			params.put("cidx", cidx);
			params.put("idx", params.get("targetIdx"));
			params.remove("thisIdx");
			params.remove("targetIdx");
		
			//2.마지막으로 해당 순서를 update 한다.
			dao.updateCardIdx(params);

	}
	
	/**
	 * 신규 카드 등록 insert
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
	      	
	      	 //파일명 변경 => 파일명_년월일시분.확장자
	      	 fileName = StringUtil.fileNmAddDate(multiFile.getOriginalFilename());
	      	 
	      	 //파일 확장자 체크 - 이미지
	      	 fileExt = StringUtil.getFileImageExtCk(fileName);
	      	 
	      	 if(fileExt) {
	      		 
	      		 if("img_i4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_i4");
		      		 iphone_params.put("img_i4", path + fileName); //아이폰4 이미지 저장 경로 + 파일명
		      	 } else if("img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_i3");
		      		 iphone_params.put("img_i3", path + fileName); //아이폰3 이미지 저장 경로 + 파일명
		      	 } else if("img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_r4");
		      		 android_params.put("img_r4", path + fileName); //안드로이드 이미지 저장 경로 + 파일명
		      	 }
		      	 FileUtil.writeFile(multiFile, path, fileName);
		      	 
	      	 }
	      	 
	        }
	    }

		} catch (Exception e) {
			e.printStackTrace();
		}

		//max+1 idx 조회
		MwAdCard cardIdx = null;
		cardIdx = dao.selectCardIdx();

		//안드로이드, 아이폰 insert
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
	 * 카드사 정보 수정 update
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
	      	 
	      	 //파일명 변경 => 파일명_년월일시분.확장자
	      	 fileName = StringUtil.fileNmAddDate(multiFile.getOriginalFilename());
	      	 
	      	 //파일 확장자 체크 - 이미지
	      	 fileExt = StringUtil.getFileImageExtCk(fileName);
	      	 
	      	 if(fileExt) {
	      		 
	      		 if("img_i4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_i4");
		      		 params.put("img_i4", path + fileName); //아이폰4 이미지 저장 경로 + 파일명
		      	 } else if("img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_i3");
		      		 params.put("img_i3", path + fileName); //아이폰3 이미지 저장 경로 + 파일명
		      	 } else if("img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("card_image_file_path_r4");
		      		 params.put("img_r4", path + fileName); //안드로이드 이미지 저장 경로 + 파일명
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
	 * 카드사 정보 삭제 delete
	 *  - 삭제 완료 후, 자신보다 큰 순위에 대해서 -1 update
	 */
	public void deleteCardDtl(HashMap<String,Object> params)  {

		deleteFile(params);	//파일 삭제 호출
		
		dao.deleteCardDtl(params);
		dao.updateCardIdx(params);

	}
	
	/**
	 * 파일 삭제
	 * - 조회 후 파일 삭제
	 */
	public void deleteFile(HashMap<String,Object> params)  {
		MwAdCard result = null;

		params.put("view", "dtl");
		params.put("top", 1);
		result = dao.selectCardListDtl(params);
		List<String> file_list = new ArrayList<String>();
		
		//리스트에 담기
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

		//파일삭제
		for(int i=0; i<file_list.size(); i++) {
			try {
				FileUtil.deleteFile(String.valueOf(file_list.get(i)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
