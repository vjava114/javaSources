package com.wallet.admin.web.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.model.MwAdEvent;
import com.wallet.admin.service.MwAdEventService;
import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.util.StringUtil;
import com.wallet.common.web.CommonController;

/*
 * Filename	: EventController.java
 * Class	: com.wallet.admin.web.base.EventController
 * History	: 2012/08/23, psj, 작업구분 : 이벤트/공지사항 관리 > 이벤트/공지사항 등록
 * Comment	:
 */
@Controller
public class EventController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	
	/**
	 * 이벤트/공지사항  등록 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/event_reg.ad")
	public String eventReg(HttpServletRequest request, HttpServletResponse response) {

		//오늘날짜
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);

	  request.setAttribute("reg_day", reg_day);
	  
		return "base/event_reg";
	}
	
	/**
	 * 이벤트/공지사항 정보 조회 select
	 * @return	
	 */
	@RequestMapping(value="/base/event_list.ad")
	public String selectEventList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### EventController selectEventList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdEvent> list = null;
		MwAdEventService service = new MwAdEventService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		int blockSize = 10;
		int nowPage = Integer.parseInt(checkStr(request.getParameter("now_page"), "1"));	//현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * blockSize + 1; 
    int endRow   = nowPage == 1 ? blockSize :startRow + blockSize -1 ;
		
		String os = checkStr(request.getParameter("os"), "apple");			//제공os
		if("common".equals(os)) {os = "apple";}

		params.put("view", "list");
		params.put("start_row",  String.valueOf(startRow));
		params.put("end_row",    String.valueOf(endRow));
		params.put("os", os);
		
		//조회
		int total_cnt = service.selectEventListTotalCnt(params);
		list = service.selectEventList(params);
		
		request.setAttribute("os", os);
		request.setAttribute("mwAdEventList", list);
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, total_cnt, blockSize);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb().toString());
		/******* paging end *********/
		
		log.debug("### EventController selectEventList END ###");
		
		return "base/event_list";
	}
	
	/**
	 * 이벤트/공지사항 정보 상세 조회 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/event_list_dtl.ad")
	public String selectEventListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### EventController selectEventDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdEvent mwAdEvent = null;
		MwAdEventService service = new MwAdEventService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		String os = checkStr(request.getParameter("os"), "");		//idx
		
		params.put("view", "dtl");
		params.put("idx", idx);
		params.put("os", os);
		params.put("top", 1);

		//조회
		mwAdEvent = service.selectEventListDtl(params);
		
		request.setAttribute("mwAdEvent", mwAdEvent);

		log.debug("### EventController selectEventDtl END ###");
		
		return "base/event_list_dtl";
	}
	
	/**
	 * 이벤트/공지사항 정보 등록
	 * @param name			
	 *
	 * @return	
	 */
	@RequestMapping(value="/base/event_reg.ad", method=RequestMethod.POST)
	public String insertEventReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### EventController insertEventReg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdEventService service = new MwAdEventService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String os = checkStr(request.getParameter("os"), "");							//제공os
		String sday = checkStr(request.getParameter("sday"), "");					//게재기간 - 시작일
		String eday = checkStr(request.getParameter("eday"), "");					//게재기간 - 종료일
		String stat = checkStr(request.getParameter("stat"), "");					//게재상태
		String evt_mode = checkStr(request.getParameter("evt_mode"), "");	//글 구분
		String title = checkStr(request.getParameter("title"), "");				//글 제목
		String msg_mode = checkStr(request.getParameter("msg_mode"), "");	//글 type
		String url = checkStr(request.getParameter("url"), "");						//팝업url
		String msg = checkStr(request.getParameter("msg"), "");						//글내용
		String img_host = PropertiesUtil.get("img_host");
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//목록 이미지(아이폰4,아이폰3GS,만드로이드)
		
		try {
			
			params.put("os", os);
			params.put("sday", sday);
			params.put("eday", eday);
			params.put("stat", stat);
			params.put("evt_mode", evt_mode);
			params.put("title", title);
			params.put("msg_mode", msg_mode);
			params.put("url", url.replace(img_host, ""));
			params.put("msg", msg);
			params.put("img_host", img_host);
		
			service.insertEventReg(params, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

    log.debug("### EventController insertEventReg END ###");

    //list화면 이동
    response.sendRedirect("/base/event_list.ad?os="+os);
    
		return "base/event_list";
	}
	
	
	/**
	 * 이벤트/공지사항 팝업 상세 변경
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/event_dtl_update.ad")
	public String updateEventDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### EventController updateEventDtl START ###");
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdEventService service = new MwAdEventService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");						//idx
		String os = checkStr(request.getParameter("os"), "");							//제공os
		String os_org = checkStr(request.getParameter("os_org"), "");			//변경전 오리지널 제공os
		String sday = checkStr(request.getParameter("sday"), "");					//게재기간 - 시작일
		String eday = checkStr(request.getParameter("eday"), "");					//게재기간 - 종료일
		String stat = checkStr(request.getParameter("stat"), "");					//게재상태
		String evt_mode = checkStr(request.getParameter("evt_mode"), "");	//글 구분
		String title = checkStr(request.getParameter("title"), "");				//글 제목
		String msg_mode = checkStr(request.getParameter("msg_mode"), "");	//글 type
		String url = checkStr(request.getParameter("url"), "");						//팝업url
		String msg = checkStr(request.getParameter("msg"), "");						//글내용
		String img_host = PropertiesUtil.get("img_host");
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//목록 이미지(아이폰4,아이폰3GS,만드로이드)
		
		try {
			
			params.put("idx", idx);
			params.put("os", os);
			params.put("os_org", os_org);
			params.put("sday", sday);
			params.put("eday", eday);
			params.put("stat", stat);
			params.put("evt_mode", evt_mode);
			params.put("title", title);
			params.put("msg_mode", msg_mode);
			params.put("url", url.replace(img_host, ""));
			params.put("msg", msg);

			service.updateEventDtl(params, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### EventController updateEventDtl END ###");
		
		//list화면 이동
		response.sendRedirect("/base/event_list.ad?os="+os);
		
		return "base/event_list";
	}
	
	/**
	 * 이벤트/공지사항 정보 삭제
	 * @param 
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/event_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteEventDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### EventController deleteEventDtl START ###");

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdEventService service = new MwAdEventService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		String os = checkStr(request.getParameter("os"), "apple");//제공os
		
		try {
			
			params.put("idx", idx);
			params.put("os", os);

			//idx가 없을경우 강제 Exception 발생
			if("".equals(idx)) {
				throw new Exception("idx가 없습니다. 강제 Exception 발생");
			}
			
			service.deleteEventDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### EventController deleteEventDtl END ###");
		
		return null;
		
	}
	
	
	/**
	 * 이벤트/공지사항 페이지/이미지 업로드
	 * @param 
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/event_page_upload.ad")
	public String eventPageUpload(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		String radio_upload = checkStr(request.getParameter("radio_upload"));

		String path = "";				//경로
		String fileName = "";		//파일명
		boolean fileExt = false;
		String filePath = "";		//파일경로 (경로 + 파일명)
		String img_host = PropertiesUtil.get("img_host");	//이벤트 url 경로
		boolean fileYN = true;	//파일 존재 여부
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	// 이미지(아이폰4,아이폰3GS,만드로이드)

		Iterator fileNameIterator = mpRequest.getFileNames();
		
    while (fileNameIterator.hasNext()) {

    	MultipartFile multiFile = mpRequest.getFile((String)fileNameIterator.next());
    	if (multiFile.getSize() > 0) {
    		
    		//해당 경로에 따른 이미지 파일 저장
    		//System.out.println("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename());
    		
    		if("page".equals(radio_upload)) {
    			path = PropertiesUtil.get("event_page_file_path");
    		} else {
    			path = PropertiesUtil.get("event_image_file_path");
    		}
    		
    		fileName = multiFile.getOriginalFilename();
  			filePath = path + fileName;
  			
	  		//파일 존재 여부 판단
	  		fileYN = FileUtil.isFile(filePath);
	  		
	  		//파일 확장자 체크 - HTML, 이미지
	  		if("page".equals(radio_upload)) {
	  			fileExt = StringUtil.getFileHtmlExtCk(fileName);	//HTML
    		} else {
    			fileExt = StringUtil.getFileImageExtCk(fileName);	//이미지
    		}
     	 
     		try {
    			//파일이 없을경우에만 업로드
    			if(!fileYN && fileExt) {
    				FileUtil.writeFile(multiFile, path);
    			} 
    		} catch (Exception e) {
    			log.error("########### Upload Error ##############");
    			e.printStackTrace();
    		}
    	}
    	
    }
    
		JSONObject jObj = new JSONObject();
		jObj.put("fileYN", fileYN);								//파이여부(Y:존재,N:존재X)
		jObj.put("upload_gb", radio_upload);			//업로드 구분(page, image)
		jObj.put("fileName", fileName);						//파일명
		jObj.put("filePath", filePath);						//파일 저장 경로
		jObj.put("event_url_path", img_host + filePath);//event url path

		request.setAttribute("JSONObject", jObj);
	
		return "/common/result_page";
	}
	
}
