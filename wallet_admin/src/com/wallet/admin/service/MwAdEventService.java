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
 * History	: 2012/08/23, psj, 작업구분 : 이벤트/공지사항 관리 > 이벤트/공지사항 등록
 * Comment	:
 */
public class MwAdEventService {
	private Logger log = Log.getLogger("logs");
	private final MwAdEventDao dao;
	
	public MwAdEventService() {
		dao = new MwAdEventDao();
	}
	
	/**
	 * 이벤트/공지사항 정보 조회 select
	 */
	public List<MwAdEvent> selectEventList(HashMap<String,Object> params) {

		List<MwAdEvent> result = null;
		
		result = dao.selectEventList(params);
		
		return result;
	}
	
	/**
	 * 이벤트/공지사항 정보 조회 select total count
	 */
	public int selectEventListTotalCnt(HashMap<String,Object> params) {

		int totalCount = dao.selectEventListTotalCnt(params);
		
		return totalCount;
	}
	
	/**
	 * 이벤트/공지사항 정보 상세 조회 select
	 */
	public MwAdEvent selectEventListDtl(HashMap<String,Object> params) {

		MwAdEvent result = null;
		
		result = dao.selectEventListDtl(params);
		
		return result;
	}
	
	/**
	 * 이벤트/공지사항 상세 정보 변경
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
	      	
	      	 //파일명 변경 => 파일명_년월일시분.확장자
	      	 fileName = StringUtil.fileNmAddDate(multiFile.getOriginalFilename());
	      	 
	      	//파일 확장자 체크 - 이미지	      	 
	      	 fileExt = StringUtil.getFileImageExtCk(fileName);
	      	 
	      	 if(fileExt) {
	      		 if("evt_img".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("event_evtImg_file_path");
		      		 params.put("evt_img", path + fileName); //이벤트 이미지 저장 경로 + 파일명
		      	 }
		      	 FileUtil.writeFile(multiFile, path, fileName);
		      	 
	      	 }
	      	 
	        }
	    }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//글 type이 text만 등록 이면 url 삭제, Web이면 글내용 삭제
		if("T".equals(params.get("msg_mode"))) {
			params.remove("url");
		} else {
			params.remove("msg");
		}
		
		dao.updateEventDtl(params);
			
	}
	
	/**
	 * 이벤트/공지사항 정보 등록 insert
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
	      	
	      	 //파일명 변경 => 파일명_년월일시분.확장자
	      	 fileName = StringUtil.fileNmAddDate(multiFile.getOriginalFilename());
	      	 
	      	//파일 확장자 체크 - 이미지	      	 
	      	 fileExt = StringUtil.getFileImageExtCk(fileName);
	      	 
	      	 if(fileExt) {
	      		 if("evt_img".equals(multiFile.getName())) {
		      		 path = PropertiesUtil.get("event_evtImg_file_path");
		      		 params.put("evt_img", path + fileName); //이벤트 이미지 저장 경로 + 파일명
		      	 }
		      	 FileUtil.writeFile(multiFile, path, fileName);
	      	 }
	        }
	    }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//글 type이 text만 등록 이면 url 삭제, Web이면 글내용 삭제
		if("T".equals(params.get("msg_mode"))) {
			params.remove("url");
		} else {
			params.remove("msg");
		}
		
		dao.insertEventReg(params);
	
	}
	
	/**
	 * 이벤트/공지사항 상세정보 삭제
	 */
	public void deleteEventDtl(HashMap<String,Object> params) {
		
		deleteFile(params);	//파일 삭제 호출
		
		dao.deleteEventDtl(params);

	}

	/**
	 * 파일 삭제
	 * - 조회 후 파일 삭제
	 */
	public void deleteFile(HashMap<String,Object> params)  {
		MwAdEvent result = null;
		
		params.put("view", "dtl");
		params.put("top", 1);
		result = dao.selectEventListDtl(params);
		List<String> file_list = new ArrayList<String>();
		
		//리스트에 담기
		if( !StringUtil.isNull(result.getEvtImg())) {
			file_list.add(result.getEvtImg());
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
