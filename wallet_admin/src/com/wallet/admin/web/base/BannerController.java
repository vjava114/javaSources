package com.wallet.admin.web.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.model.MwAdBanner;
import com.wallet.admin.service.MwAdBannerService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;

import com.wallet.common.web.CommonController;

/*
 * Filename	: BannerController.java
 * Class	: com.wallet.admin.web.base.BannerController
 * History	: 2012/08/23, psj, �۾����� : �˾�/��� ���� > ���
 * Comment	:
 */
@Controller
public class BannerController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");

	/**
	 * ��� ��� ȭ�� ������ ȣ��
	 * - selectbox ��ȸ (�������,������, �̺�Ʈ/��������)
	 * @return	
	 */
	@RequestMapping(value="/base/banner_reg.ad")
	public String bannerReg(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### BannerController bannerReg START ###");
		
		//���ó�¥
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);
	  
	  /* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdBannerService service = new MwAdBannerService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
	  request.setAttribute("reg_day", reg_day);
	 
	  log.debug("### BannerController bannerReg END ###");
	  
		return "base/banner_reg";
	}
	
	/**
	 * ��� �������

	 * @return	
	 */
	@RequestMapping(value="/base/banner_reg.ad", method=RequestMethod.POST)
	public String insertBannerReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### BannerController insertBannerReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdBanner> list = null;
		MwAdBannerService service = new MwAdBannerService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String name = checkStr(request.getParameter("name"), "");						//��ʸ�
		String stat = checkStr(request.getParameter("stat"), "");						//��������
		String os = checkStr(request.getParameter("os"), "");								//os
		String sday = checkStr(request.getParameter("sday"), "");						//����Ⱓ - ������
		String eday = checkStr(request.getParameter("eday"), "");						//����Ⱓ - ������
		String img_i3 = checkStr(request.getParameter("img_i3"), "");				//������3 ����
		String img_i4 = checkStr(request.getParameter("img_i4"), "");				//������4 ����
		String img_r4 = checkStr(request.getParameter("img_r4"), "");				//
		String img_r5 = checkStr(request.getParameter("img_r5"), "");				//
		String img_r6 = checkStr(request.getParameter("img_r6"), "");				//
		String img_r7 = checkStr(request.getParameter("img_r7"), "");				//
		String goto_mode = checkStr(request.getParameter("goto_mode"), "");	//��ʸ�ũ ����
		String goto_id = checkStr(request.getParameter("goto_id"), "");			//�����/����/�̺�Ʈ���� id
		String goto_info = checkStr(request.getParameter("goto_info"), "");	//�����/����/�̺�Ʈ���� ����
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//����Ʈ,ī�� �̹���(������4,������3GS,������̵�)
		
		try {
			
			params.put("name",name);
			params.put("stat",stat);
			params.put("os",os);
			params.put("sday",sday);
			params.put("eday",eday);
			params.put("img_i3",img_i3);
			params.put("img_i4",img_i4);
			params.put("img_r4",img_r4);
			params.put("img_r5",img_r5);
			params.put("img_r6",img_r6);
			params.put("img_r7",img_r7);
			params.put("goto_mode",goto_mode);
			params.put("goto_id",goto_id);
			params.put("goto_info",goto_info);
			params.put("img_host",PropertiesUtil.get("img_host"));

			service.insertBannerReg(params, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		params.clear();
		
		//listȭ�� �̵�
		response.sendRedirect("/base/banner_list.ad?os="+os);

    log.debug("### BannerController insertBannerReg END ###");
    
		return "base/banner_list";
	}
	
	/**
	 * ��� ��ȸ ȭ�� ������ ȣ��
	 * @return	
	 */
	@RequestMapping(value="/base/banner_list.ad")
	public String selectBannerList(HttpServletRequest request, HttpServletResponse response) {
		log.debug("### BannerController selectBannerList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdBanner> list = null;
		MwAdBannerService service = new MwAdBannerService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		int blockSize = 10;
		int nowPage = Integer.parseInt(checkStr(request.getParameter("now_page"), "1"));	//����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * blockSize + 1; 
    int endRow   = nowPage == 1 ? blockSize :startRow + blockSize -1 ;
    
    String os = checkStr(request.getParameter("os"), "apple");			//����os
    
    params.put("view", "list");
		params.put("start_row",  String.valueOf(startRow));
		params.put("end_row",    String.valueOf(endRow));
		params.put("os", os);

		int total_cnt = service.selectBannerListTotalCnt(params);
		list = service.selectBannerList(params);
		
		request.setAttribute("os", os);
		request.setAttribute("mwAdBannerList", list);
		
		/******* paging start *********/
		Paging page = new Paging();
		page.makeWebPaging(nowPage, total_cnt, blockSize);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb().toString());
		/******* paging end *********/
		
		log.debug("### BannerController selectBannerList END ###");
		return "base/banner_list";
	}
	
	/**
	 * ��� ���� �� ��ȸ
	 * @param 	os 			os
	 * @return	cidx		cidx
	 */
	@RequestMapping(value="/base/banner_list_dtl.ad")
	public String selectBannerListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### BannerController selectBannerListDtl START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdBanner mwAdBanner = null;
		List<MwAdBanner> selectboxEvent = null;
		MwAdBannerService service = new MwAdBannerService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");
		String os = checkStr(request.getParameter("os"), "apple");
		
		params.put("view", "dtl");
		params.put("idx", idx);
		params.put("os", os);
		params.put("top", 1);
		params.put("stat", "F");	//����� �̺�Ʈ�� ��ȸ�Ҽ� �����Ƿ� N
		
		mwAdBanner = service.selectBannerListDtl(params);

		request.setAttribute("mwAdBanner", mwAdBanner);

		log.debug("### BannerController selectBannerListDtl END ###");
		
		return "base/banner_list_dtl";
	}
	
	/**
	 * ��� �˾� �� ����
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/banner_dtl_update.ad")
	public String updateBannerDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### BannerController updateBannerDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdBannerService service = new MwAdBannerService();
		HashMap<String,Object> params = new HashMap<String,Object>();
    
    String idx = checkStr(request.getParameter("idx"), "");							//��ʸ�
    String name = checkStr(request.getParameter("name"), "");						//��ʸ�
		String stat = checkStr(request.getParameter("stat"), "");						//��������
		String os = checkStr(request.getParameter("os"), "");								//os
		String os_org = checkStr(request.getParameter("os_org"), "");				//������ �������� ����os
		String sday = checkStr(request.getParameter("sday"), "");						//����Ⱓ - ������
		String eday = checkStr(request.getParameter("eday"), "");						//����Ⱓ - ������
		String img_i3 = checkStr(request.getParameter("img_i3"), "");				//������3 ����
		String img_i4 = checkStr(request.getParameter("img_i4"), "");				//������4 ����
		String img_r4 = checkStr(request.getParameter("img_r4"), "");				//
		String img_r5 = checkStr(request.getParameter("img_r5"), "");				//
		String img_r6 = checkStr(request.getParameter("img_r6"), "");				//
		String img_r7 = checkStr(request.getParameter("img_r7"), "");				//
		String goto_mode = checkStr(request.getParameter("goto_mode"), "");	//��ʸ�ũ ����
		String goto_id = checkStr(request.getParameter("goto_id"), "");			//�����/����/�̺�Ʈ���� id
		String goto_info = checkStr(request.getParameter("goto_info"), "");	//�����/����/�̺�Ʈ���� ����
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//����Ʈ,ī�� �̹���(������4,������3GS,������̵�)
		
		try {
			
			params.put("idx",idx);
			params.put("name",name);
			params.put("stat",stat);
			params.put("os",os);
			params.put("os_org",os_org);
			params.put("sday",sday);
			params.put("eday",eday);
			params.put("img_i3",img_i3);
			params.put("img_i4",img_i4);
			params.put("img_r4",img_r4);
			params.put("img_r5",img_r5);
			params.put("img_r6",img_r6);
			params.put("img_r7",img_r7);
			params.put("goto_mode",goto_mode);
			params.put("goto_id",goto_id);
			params.put("goto_info",goto_info);
			params.put("img_host",PropertiesUtil.get("img_host"));
			
			service.updateBannerDtl(params, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### BannerController updateBannerDtl END ###");
		
		//listȭ�� �̵�
		response.sendRedirect("/base/banner_list.ad?os="+os);
		
		return "base/banner_list";
	}
	
	/**
	 * ��� ���� ����
	 * @param 
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/banner_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteBannerDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### BannerController deleteBannerDtl START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwAdBannerService service = new MwAdBannerService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String idx = checkStr(request.getParameter("idx"), "");		//idx
		String os = checkStr(request.getParameter("os"), "apple");		//����os
		
		try {
			
			params.put("idx", idx);
			params.put("os", os);
			
			//idx�� ������� ���� Exception �߻�
			if("".equals(idx)) {
				throw new Exception("idx�� �����ϴ�. ���� Exception �߻�");
			}
			
			service.deleteBannerDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}
		
		log.debug("### BannerController deleteBannerDtl END ###");
		
		return null;
		
	}
	
	@RequestMapping(value="/base/banner_json_selectbox.ad")
	public String jsonBannerSelectbox(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		log.debug("### BannerController jsonBannerSelectbox START ###");

	  
	  /* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwAdBanner> selectboxList = null;
		MwAdBannerService service = new MwAdBannerService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String goto_mode = checkStr(request.getParameter("goto_mode"), "");	//��ʸ�ũ ����
		String goto_mode_org = checkStr(request.getParameter("goto_mode_org"), "");	//��ʸ�ũ ����
		String os = checkStr(request.getParameter("os"), "");								//os
		String view = checkStr(request.getParameter("view"), "");						//���ȭ������ ��ȭ������ üũ
		String goto_id = checkStr(request.getParameter("goto_id"), "");	//��ʸ�ũ ����
		String evt_mode = "E";	//�̺�Ʈ : E, ��������:N
		
		//���� ��ȭ���� ��� ��ũ ���а� �ٸ���� bannerReg�� ��ȸ�Ѵ�.
		if(!goto_mode.equals(goto_mode_org)) {
			view = "bannerReg";
		}
		//���������ϰ�� N
		if("NOTICE".equals(goto_mode)) {
			evt_mode = "N";
		}
		
		params.put("goto_mode", goto_mode);
		params.put("goto_mode_org", goto_mode_org);
		params.put("evt_mode", evt_mode);
		params.put("os", os);
		params.put("view", view);
		params.put("goto_id", goto_id);
		
		log.debug("### params : " + params);
		
		selectboxList = service.jsonBannerSelectbox(params);
   
   JSONObject jObj = new JSONObject();
   jObj.put("selectboxList", selectboxList);
   
   request.setAttribute("JSONObject", jObj);

   log.debug("### BannerController jsonBannerSelectbox END ###");
 	
 		return "/common/result_page";
	}
	
}
