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
 * History	: 2012/08/23, psj, �۾����� : �̺�Ʈ/�������� ���� > �̺�Ʈ/�������� ���
 * Comment	:
 */
@Controller
public class EventController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	
	/**
	 * �̺�Ʈ/��������  ��� ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/event_reg.ad")
	public String eventReg(HttpServletRequest request, HttpServletResponse response) {

		//���ó�¥
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);

	  request.setAttribute("reg_day", reg_day);
	  
		return "base/event_reg";
	}
	
	/**
	 * �̺�Ʈ/�������� ���� ��ȸ select
	 * @return	
	 */
	@RequestMapping(value="/base/event_list.ad")
	public String selectEventList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### EventController selectEventList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdEvent> list = null;
		MwAdEventService service = new MwAdEventService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		int blockSize = 10;
		int nowPage = Integer.parseInt(checkStr(request.getParameter("now_page"), "1"));	//����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * blockSize + 1; 
    int endRow   = nowPage == 1 ? blockSize :startRow + blockSize -1 ;
		
		String os = checkStr(request.getParameter("os"), "apple");			//����os
		if("common".equals(os)) {os = "apple";}

		params.put("view", "list");
		params.put("start_row",  String.valueOf(startRow));
		params.put("end_row",    String.valueOf(endRow));
		params.put("os", os);
		
		//��ȸ
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
	 * �̺�Ʈ/�������� ���� �� ��ȸ ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/event_list_dtl.ad")
	public String selectEventListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### EventController selectEventDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdEvent mwAdEvent = null;
		MwAdEventService service = new MwAdEventService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		String os = checkStr(request.getParameter("os"), "");		//idx
		
		params.put("view", "dtl");
		params.put("idx", idx);
		params.put("os", os);
		params.put("top", 1);

		//��ȸ
		mwAdEvent = service.selectEventListDtl(params);
		
		request.setAttribute("mwAdEvent", mwAdEvent);

		log.debug("### EventController selectEventDtl END ###");
		
		return "base/event_list_dtl";
	}
	
	/**
	 * �̺�Ʈ/�������� ���� ���
	 * @param name			
	 *
	 * @return	
	 */
	@RequestMapping(value="/base/event_reg.ad", method=RequestMethod.POST)
	public String insertEventReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### EventController insertEventReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdEventService service = new MwAdEventService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String os = checkStr(request.getParameter("os"), "");							//����os
		String sday = checkStr(request.getParameter("sday"), "");					//����Ⱓ - ������
		String eday = checkStr(request.getParameter("eday"), "");					//����Ⱓ - ������
		String stat = checkStr(request.getParameter("stat"), "");					//�������
		String evt_mode = checkStr(request.getParameter("evt_mode"), "");	//�� ����
		String title = checkStr(request.getParameter("title"), "");				//�� ����
		String msg_mode = checkStr(request.getParameter("msg_mode"), "");	//�� type
		String url = checkStr(request.getParameter("url"), "");						//�˾�url
		String msg = checkStr(request.getParameter("msg"), "");						//�۳���
		String img_host = PropertiesUtil.get("img_host");
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//��� �̹���(������4,������3GS,������̵�)
		
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

    //listȭ�� �̵�
    response.sendRedirect("/base/event_list.ad?os="+os);
    
		return "base/event_list";
	}
	
	
	/**
	 * �̺�Ʈ/�������� �˾� �� ����
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/event_dtl_update.ad")
	public String updateEventDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### EventController updateEventDtl START ###");
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdEventService service = new MwAdEventService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");						//idx
		String os = checkStr(request.getParameter("os"), "");							//����os
		String os_org = checkStr(request.getParameter("os_org"), "");			//������ �������� ����os
		String sday = checkStr(request.getParameter("sday"), "");					//����Ⱓ - ������
		String eday = checkStr(request.getParameter("eday"), "");					//����Ⱓ - ������
		String stat = checkStr(request.getParameter("stat"), "");					//�������
		String evt_mode = checkStr(request.getParameter("evt_mode"), "");	//�� ����
		String title = checkStr(request.getParameter("title"), "");				//�� ����
		String msg_mode = checkStr(request.getParameter("msg_mode"), "");	//�� type
		String url = checkStr(request.getParameter("url"), "");						//�˾�url
		String msg = checkStr(request.getParameter("msg"), "");						//�۳���
		String img_host = PropertiesUtil.get("img_host");
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//��� �̹���(������4,������3GS,������̵�)
		
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
		
		//listȭ�� �̵�
		response.sendRedirect("/base/event_list.ad?os="+os);
		
		return "base/event_list";
	}
	
	/**
	 * �̺�Ʈ/�������� ���� ����
	 * @param 
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/event_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteEventDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### EventController deleteEventDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdEventService service = new MwAdEventService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		String os = checkStr(request.getParameter("os"), "apple");//����os
		
		try {
			
			params.put("idx", idx);
			params.put("os", os);

			//idx�� ������� ���� Exception �߻�
			if("".equals(idx)) {
				throw new Exception("idx�� �����ϴ�. ���� Exception �߻�");
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
	 * �̺�Ʈ/�������� ������/�̹��� ���ε�
	 * @param 
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/event_page_upload.ad")
	public String eventPageUpload(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		String radio_upload = checkStr(request.getParameter("radio_upload"));

		String path = "";				//���
		String fileName = "";		//���ϸ�
		boolean fileExt = false;
		String filePath = "";		//���ϰ�� (��� + ���ϸ�)
		String img_host = PropertiesUtil.get("img_host");	//�̺�Ʈ url ���
		boolean fileYN = true;	//���� ���� ����
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	// �̹���(������4,������3GS,������̵�)

		Iterator fileNameIterator = mpRequest.getFileNames();
		
    while (fileNameIterator.hasNext()) {

    	MultipartFile multiFile = mpRequest.getFile((String)fileNameIterator.next());
    	if (multiFile.getSize() > 0) {
    		
    		//�ش� ��ο� ���� �̹��� ���� ����
    		//System.out.println("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename());
    		
    		if("page".equals(radio_upload)) {
    			path = PropertiesUtil.get("event_page_file_path");
    		} else {
    			path = PropertiesUtil.get("event_image_file_path");
    		}
    		
    		fileName = multiFile.getOriginalFilename();
  			filePath = path + fileName;
  			
	  		//���� ���� ���� �Ǵ�
	  		fileYN = FileUtil.isFile(filePath);
	  		
	  		//���� Ȯ���� üũ - HTML, �̹���
	  		if("page".equals(radio_upload)) {
	  			fileExt = StringUtil.getFileHtmlExtCk(fileName);	//HTML
    		} else {
    			fileExt = StringUtil.getFileImageExtCk(fileName);	//�̹���
    		}
     	 
     		try {
    			//������ ������쿡�� ���ε�
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
		jObj.put("fileYN", fileYN);								//���̿���(Y:����,N:����X)
		jObj.put("upload_gb", radio_upload);			//���ε� ����(page, image)
		jObj.put("fileName", fileName);						//���ϸ�
		jObj.put("filePath", filePath);						//���� ���� ���
		jObj.put("event_url_path", img_host + filePath);//event url path

		request.setAttribute("JSONObject", jObj);
	
		return "/common/result_page";
	}
	
}
