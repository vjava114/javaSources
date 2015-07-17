package com.wallet.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.dao.MwAdBannerDao;
import com.wallet.admin.model.MwAdBanner;
import com.wallet.admin.model.MwAdGift;

import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.util.StringUtil;


/*
 * Filename	: MwAdBannerService.java
 * Class	: com.wallet.admin.service.MwAdBannerService
 * History	: 2012/08/23, psj, 작업구분 : 팝업/배너 관리 > 배너
 * Comment	:
 */
public class MwAdBannerService {
	private Logger log = Log.getLogger("logs");
	private final MwAdBannerDao dao;
	
	public MwAdBannerService() {
		dao = new MwAdBannerDao();
	}
	
	/**
	 * 배너 등록 insert
	 */
	public void insertBannerReg(HashMap<String,Object> params, MultipartHttpServletRequest mpRequest) {

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
		      		 path = PropertiesUtil.get("banner_image_file_path_i4");
		      		 params.put("img_i4", path + fileName); //아이폰4 이미지 저장 경로 + 파일명
		      	 } else if("img_i3".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("banner_image_file_path_i3");
		      		 params.put("img_i3", path + fileName); //아이폰3 이미지 저장 경로 + 파일명
		      	 } else if("img_r4".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("banner_image_file_path_r4");
		      		 params.put("img_r4", path + fileName); //안드로이드 이미지 저장 경로 + 파일명
		      	 }
		      	 
		      	 FileUtil.writeFile(multiFile, path, fileName);
	      	 }
	        }
	    }
	   
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dao.insertBannerReg(params);			//isnert

	}
	
	/**
	 * 배너 정보 조회 select total count
	 */
	public int selectBannerListTotalCnt(HashMap<String,Object> params) {
		
		int totalCount = dao.selectBannerListTotalCnt(params);

		return totalCount;
	}
	
	/**
	 * 배너 정보 조회 select
	 */
	public List<MwAdBanner> selectBannerList(HashMap<String,Object> params) {

		List<MwAdBanner> result = null;
		
		result = dao.selectBannerList(params);

		return result;
	}
	
	/**
	 * 배너 selectbox 조회 (멤버쉽상세,쿠폰상세, 이벤트, 공지사항)
	 */
	public List<MwAdBanner> jsonBannerSelectbox(HashMap<String,Object> params) {

		List<MwAdBanner> result = null;
		
		result = dao.jsonBannerSelectbox(params);

		return result;
	}
	
	/**
	 * 배너  상세 조회 select
	 */
	public MwAdBanner selectBannerListDtl(HashMap<String,Object> params) {

		MwAdBanner result = null;
		
		result = dao.selectBannerListDtl(params);

		return result;
	}
	
	/**
	 * 배너 팝업 상세 변경
	 */
	public void updateBannerDtl(HashMap<String,Object> params, MultipartHttpServletRequest mpRequest) {

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
			      		 path = PropertiesUtil.get("banner_image_file_path_i4");
			      		 params.put("img_i4", path + fileName); //아이폰4 이미지 저장 경로 + 파일명
			      	 } else if("img_i3".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("banner_image_file_path_i3");
			      		 params.put("img_i3", path + fileName); //아이폰3 이미지 저장 경로 + 파일명
			      	 } else if("img_r4".equals(multiFile.getName())) {
			      		 path = PropertiesUtil.get("banner_image_file_path_r4");
			      		 params.put("img_r4", path + fileName); //안드로이드 이미지 저장 경로 + 파일명
			      	 }

			      	 FileUtil.writeFile(multiFile, path, fileName);

		      	 }
		      	 
	        }
	    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dao.updateBannerDtl(params);

	}
	
	/**
	 * 배너 상세정보 삭제
	 */
	public void deleteBannerDtl(HashMap<String,Object> params) {
		
		deleteFile(params);
		
		dao.deleteBannerDtl(params);

	}
	
	/**
	 * 파일 삭제
	 * - 조회 후 파일 삭제
	 */
	public void deleteFile(HashMap<String,Object> params)  {
		
		MwAdBanner result = null;

		params.put("view", "dtl");
		params.put("top", 1);
		result = dao.selectBannerListDtl(params);
		List<String> file_list = new ArrayList<String>();
		
		//파일 담기
		if( !StringUtil.isNull(result.getImgI3())) {
			file_list.add(result.getImgI3());
		}
		if( !StringUtil.isNull(result.getImgI4())) {
			file_list.add(result.getImgI4());
		}
		if( !StringUtil.isNull(result.getImgR4())) {
			file_list.add(result.getImgR4());
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
