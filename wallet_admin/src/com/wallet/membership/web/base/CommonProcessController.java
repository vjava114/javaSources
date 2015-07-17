package com.wallet.membership.web.base;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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

import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;
import com.wallet.membership.model.CpnBankInfo;
import com.wallet.membership.model.CpnBankInfoExample;
import com.wallet.membership.model.MembCardlist;
import com.wallet.membership.model.MembCardlistExample;
import com.wallet.membership.model.MwCmCode;
import com.wallet.membership.model.MwCmCodeExample;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwCmImage;
import com.wallet.membership.model.MwCmPayCompany;
import com.wallet.membership.model.MwCmPayCompanyExample;
import com.wallet.membership.service.CpnBankInfoService;
import com.wallet.membership.service.MembCardlistService;
import com.wallet.membership.service.MwCmCodeService;
import com.wallet.membership.service.MwCmCompanyService;
import com.wallet.membership.service.MwCmImageService;
import com.wallet.membership.service.MwCmPayCompanyService;

@Controller
public class CommonProcessController extends CommonController {
	private final String PAGE_CODE = "COMMON_PROCESS";
	private Logger log = Log.getLogger("logs");
	private final String DOWNLOADABLE_IMG_EXT = "gif, bmp, jpg, jpge, png";
	private final String DOWNLOADABLE_HTML_EXT = "html, htm";
	private final String DOWNLOADABLE_XLS_EXT = "xls, xlsx";

	/**
	 * ���� ������
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_sample.ms", method=RequestMethod.GET)
	public String samplePage(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);

		return "common/common_sample";
	}

	/**
	 * �޺� �����ڵ�
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_code.ms")
	public String commonCode(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.commonCode ##########");

		String grpCode = checkStr(request.getParameter("grpCode"), "9999");;
		System.out.println("grpCode: "+grpCode);

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwCmCode> mwCmCodeList = null;
		MwCmCodeService mwCmCodeService = new MwCmCodeService();
		MwCmCodeExample mwCmCodeExample = null;


		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		mwCmCodeExample = new MwCmCodeExample();
		mwCmCodeExample.or().andGrpCodeEqualTo(grpCode);
		mwCmCodeList = mwCmCodeService.getByExample(mwCmCodeExample);


		Iterator<MwCmCode> it = mwCmCodeList.iterator();
		while(it.hasNext()) {
			MwCmCode one = it.next();
			jObj.put(one.getComCd(), one.getComCdVal());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("mwCmCodeList", mwCmCodeList);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}

	/**
	 * �޺� ���޻�(�귣��/������)
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_company.ms")
	public String commonCompany(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.commonCompany ##########");

		String compId = checkStr(request.getParameter("compId"), "CMP000000000");
		System.out.println("compId: "+compId);

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwCmCompany> mwCmCompanyList = null;
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = null;


		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		mwCmCompanyExample = new MwCmCompanyExample();
		mwCmCompanyExample.or().andUpperCompIdEqualTo(compId);
		mwCmCompanyList = mwCmCompanyService.getByExample(mwCmCompanyExample);


		Iterator<MwCmCompany> it = mwCmCompanyList.iterator();
		while(it.hasNext()) {
			MwCmCompany one = it.next();
			jObj.put(one.getCompId(), one.getCompName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("mwCmCompanyList", mwCmCompanyList);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}

	/**
	 * �˾� ���޻�
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/pop_company.ms")
	public String popCompany(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.popCompany ##########");

		String compId = checkStr(request.getParameter("compId"), "CMP000000000");
		String searchText = checkStr(request.getParameter("searchText"), "");
		System.out.println("compId: "+compId);
		System.out.println("searchText: "+searchText);

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwCmCompany> mwCmCompanyList = null;
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = null;


		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		mwCmCompanyExample = new MwCmCompanyExample();
		if("BRAND_ALL".equals(compId)) {
			mwCmCompanyExample.or().andCompIdLike("BRD%").andCompNameLike("%"+searchText+"%");
		}else if("SHP_ALL".equals(compId)) {
			mwCmCompanyExample.or().andCompIdLike("SHP%").andCompNameLike("%"+searchText+"%");
		}else {
			mwCmCompanyExample.or().andUpperCompIdEqualTo(compId).andCompNameLike("%"+searchText+"%");
		}

		mwCmCompanyList = mwCmCompanyService.getByExample(mwCmCompanyExample);


		Iterator<MwCmCompany> it = mwCmCompanyList.iterator();
		while(it.hasNext()) {
			MwCmCompany one = it.next();
			jObj.put(one.getCompId(), one.getCompName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("mwCmCompanyList", mwCmCompanyList);
		request.setAttribute("JSONObject", jObj);

		return "/common/pop_company";
	}
			

	/**
	 * �˾� �����
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/pop_member.ms")
	public String popMember(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.popMember ##########");

		String searchText = checkStr(request.getParameter("searchText"), "");
		System.out.println("searchText: "+searchText);

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MembCardlist> membCardlistList = null;
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlistExample membCardlistExample = null;


		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		membCardlistExample = new MembCardlistExample();
		membCardlistExample.or().andNameLike("%"+searchText+"%");

		membCardlistList = membCardlistService.getByExample(membCardlistExample);


		Iterator<MembCardlist> it = membCardlistList.iterator();
		while(it.hasNext()) {
			MembCardlist one = it.next();
			jObj.put(one.getMembId(), one.getName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("dataList", membCardlistList);
		request.setAttribute("JSONObject", jObj);

		return "/common/pop_member";
	}
	
	/**
	 * �˾� ������
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/pop_bank.ms")
	public String popBank(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.popMember ##########");

		String searchText = checkStr(request.getParameter("searchText"), "");
		System.out.println("searchText: "+searchText);

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		CpnBankInfoService cpnBankInfoService = new CpnBankInfoService();
		CpnBankInfoExample cpnBankInfoExample = new CpnBankInfoExample();
		cpnBankInfoExample.or().andNameLike("%"+searchText+"%");
		List<CpnBankInfo> cpnBankInfo = cpnBankInfoService.getByExample(cpnBankInfoExample);		

		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		Iterator<CpnBankInfo> it = cpnBankInfo.iterator();
		while(it.hasNext()) {
			CpnBankInfo one = it.next();
			jObj.put(one.getBankId(), one.getName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("dataList", cpnBankInfo);
		request.setAttribute("JSONObject", jObj);

		return "/common/pop_bank";
	}
	
	
	/**
	 * �˾� ������
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/pop_paycomp.ms")
	public String popPaycomp(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.popMember ##########");

		String searchText = checkStr(request.getParameter("searchText"), "");
		System.out.println("searchText: "+searchText);

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwCmPayCompanyService mwCmPayCompanyService = new MwCmPayCompanyService();
		MwCmPayCompanyExample mwCmPayCompanyExample = new MwCmPayCompanyExample();
		mwCmPayCompanyExample.or().andPaycompNameLike("%"+searchText+"%");
		List<MwCmPayCompany> mwCmPayCompany = mwCmPayCompanyService.getByExample(mwCmPayCompanyExample);
		
		JSONObject jObj = new JSONObject();

		Iterator<MwCmPayCompany> it = mwCmPayCompany.iterator();
		while(it.hasNext()) {
			MwCmPayCompany one = it.next();
			jObj.put(one.getPaycompId(), one.getPaycompName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("dataList", mwCmPayCompany);
		request.setAttribute("JSONObject", jObj);

		return "/common/pop_paycomp";
	}
	
	

	/**
	 * �޺� �����
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_member.ms")
	public String commonMember(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.commonMember ##########");


		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MembCardlist> membCardlistList = null;
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlistExample membCardlistExample = null;


		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		membCardlistExample = new MembCardlistExample();
		membCardlistList = membCardlistService.getByExample(membCardlistExample);


		Iterator<MembCardlist> it = membCardlistList.iterator();
		while(it.hasNext()) {
			MembCardlist one = it.next();
			jObj.put(one.getMembId(), one.getName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("dataList", membCardlistList);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}
	
	/**
	 * �޺� ����� �˻�
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_member_sh.ms")
	public String commonMemberSh(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.commonMember ##########");

		String brandId = checkStr(request.getParameter("brandId"));
		System.out.println("brandId : " + brandId);
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MembCardlist> membCardlistList = null;
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlistExample membCardlistExample = new MembCardlistExample();
		
		if(brandId.matches("CMP.*")){
			membCardlistExample.or().andCompIdEqualTo(brandId);
		}else if(brandId.matches("BRD.*")){
			membCardlistExample.or().andBrandIdEqualTo(brandId);
		}
		
		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		membCardlistList = membCardlistService.getByExample(membCardlistExample);


		Iterator<MembCardlist> it = membCardlistList.iterator();
		while(it.hasNext()) {
			MembCardlist one = it.next();
			jObj.put(one.getMembId(), one.getName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("dataList", membCardlistList);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}
	
	
	/**
	 * �޺� ��ǥ ����� �˻�
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_no_member_sh.ms")
	public String commonNoOneMemberSh(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.commonMember ##########");

		String brandId = checkStr(request.getParameter("brandId"));
		System.out.println("brandId : " + brandId);
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MembCardlist> membCardlistList = null;
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlistExample membCardlistExample = new MembCardlistExample();
		
		if(brandId.matches("CMP.*")){
			membCardlistExample.or().andCompIdEqualTo(brandId).andUpperMembIdIsNull();
		}else if(brandId.matches("BRD.*")){
			membCardlistExample.or().andBrandIdEqualTo(brandId).andUpperMembIdIsNull();
		}
		
		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		membCardlistList = membCardlistService.getByExample(membCardlistExample);


		Iterator<MembCardlist> it = membCardlistList.iterator();
		while(it.hasNext()) {
			MembCardlist one = it.next();
			jObj.put(one.getMembId(), one.getName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("dataList", membCardlistList);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}
	
	

	/**
	 * �̹��� ���ε�
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/image_upload.ms")
	public String imageUpload(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.imageUpload ##########");

		System.out.println("######content-type: " + request.getContentType());
		System.out.println("######length: " + request.getContentLength());
		
		boolean fileYN = true; //-- ���� ���ϸ� ���翩�� üũ
		boolean extYN = true; //-- ���� Ȯ���� ��ȿ�� üũ

		String os = checkStr(request.getParameter("os"));
		String part = checkStr(request.getParameter("part"));
		String addId = checkStr(request.getParameter("addImgId"));
		System.out.println("###### os:" + os);
		System.out.println("###### part:" + part);
		System.out.println("###### addId:" + addId);

		String host = "";
		String path = "";	// �̹��� ���
		String filePath = "";
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	// �̹���(������4,������3GS,������̵�)

		Iterator fileNameIterator = mpRequest.getFileNames();
		while (fileNameIterator.hasNext()) {
	
			MultipartFile multiFile = mpRequest.getFile((String)fileNameIterator.next());
			if (multiFile.getSize() > 0) {

				String ext = multiFile.getOriginalFilename().substring(multiFile.getOriginalFilename().lastIndexOf(".")+1); 
				
				if(DOWNLOADABLE_IMG_EXT.indexOf(ext.toLowerCase()) <0){
					extYN = false; //-- ������ html, htm ������ �ƴ� ��
				}
				System.out.println("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename());
	
				//�ش� ��ο� ���� �̹��� ���� ����
//				host = PropertiesUtil.get("img_host");
				path = getImagePath(os, part, addId);
				filePath = path + multiFile.getOriginalFilename();
	
				//TODO ���ϸ� �ߺ�ó��
	
				try {
					System.out.println("### path : " + path);
					System.out.println("### filePath : " + filePath);
					System.out.println("### extYN : " + extYN);
					
					FileUtil.writeFile(multiFile, path);
					
				} catch (Exception e) {
					System.out.println("########### Upload Error ##############");
					e.printStackTrace();
				}
			}
		}
	
		//�̹��� ���� ���
		JSONObject jObj = new JSONObject();
		jObj.put("extYN", extYN);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}

	/**
	 * �� ���п� ���� �̹��� ���޵� ��� ����
	 * 
	 * @param os	�ܸ��ⱸ��
	 * @param part	��뱸��
	 * @return
	 */
	private String getImagePath(String os, String part, String addId) {
		String path = "";
		
		if("01".equals(part)) {
			// �귣��
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("brand_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("brand_image_path_i4");
			else if( "03".equals(os) )	//������5
				path = PropertiesUtil.get("brand_image_path_i5");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 (160)
				path = PropertiesUtil.get("brand_image_path_a1");
			else if( "12".equals(os) )	//�ȵ���̵� 480*800 (240)
				path = PropertiesUtil.get("brand_image_path_a2");
			else if( "13".equals(os) )	//�ȵ���̵� 540*960
				path = PropertiesUtil.get("brand_image_path_a3");
			else if( "14".equals(os) )	//�ȵ���̵� 800*1280
				path = PropertiesUtil.get("brand_image_path_a4");
			else if( "15".equals(os) )	//�ȵ���̵� 720*1280
				path = PropertiesUtil.get("brand_image_path_a5");
			else if( "16".equals(os) )	//�ȵ���̵� 768*1280
				path = PropertiesUtil.get("brand_image_path_a6");
			else if( "17".equals(os) )	//�ȵ���̵� 768*1024
				path = PropertiesUtil.get("brand_image_path_a7");
		} 
		else if("02".equals(part)) {
			// ����� ����Ʈ��
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("memb_list_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("memb_list_image_path_i4");
			else if( "03".equals(os) )	//������5
				path = PropertiesUtil.get("memb_list_image_path_i5");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 (160)
				path = PropertiesUtil.get("memb_list_image_path_a1");
			else if( "12".equals(os) )	//�ȵ���̵� 480*800 (240)
				path = PropertiesUtil.get("memb_list_image_path_a2");
			else if( "13".equals(os) )	//�ȵ���̵� 540*960
				path = PropertiesUtil.get("memb_list_image_path_a3");
			else if( "14".equals(os) )	//�ȵ���̵� 800*1280
				path = PropertiesUtil.get("memb_list_image_path_a4");
			else if( "15".equals(os) )	//�ȵ���̵� 720*1280
				path = PropertiesUtil.get("memb_list_image_path_a5");
			else if( "16".equals(os) )	//�ȵ���̵� 768*1280
				path = PropertiesUtil.get("memb_list_image_path_a6");
			else if( "17".equals(os) )	//�ȵ���̵� 768*1024
				path = PropertiesUtil.get("memb_list_image_path_a7");
		}
		else if("03".equals(part)) {
			// ����� �󼼿�
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("memb_detail_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("memb_detail_image_path_i4");
			else if( "03".equals(os) )	//������5
				path = PropertiesUtil.get("memb_detail_image_path_i5");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 (160)
				path = PropertiesUtil.get("memb_detail_image_path_a1");
			else if( "12".equals(os) )	//�ȵ���̵� 480*800 (240)
				path = PropertiesUtil.get("memb_detail_image_path_a2");
			else if( "13".equals(os) )	//�ȵ���̵� 540*960
				path = PropertiesUtil.get("memb_detail_image_path_a3");
			else if( "14".equals(os) )	//�ȵ���̵� 800*1280
				path = PropertiesUtil.get("memb_detail_image_path_a4");
			else if( "15".equals(os) )	//�ȵ���̵� 720*1280
				path = PropertiesUtil.get("memb_detail_image_path_a5");
			else if( "16".equals(os) )	//�ȵ���̵� 768*1280
				path = PropertiesUtil.get("memb_detail_image_path_a6");
			else if( "17".equals(os) )	//�ȵ���̵� 768*1024
				path = PropertiesUtil.get("memb_detail_image_path_a7");
		}
		else if("04".equals(part)) {
			// ����� ��Ͽ�
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("memb_regist_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("memb_regist_image_path_i4");
			else if( "03".equals(os) )	//������5
				path = PropertiesUtil.get("memb_regist_image_path_i5");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 (160)
				path = PropertiesUtil.get("memb_regist_image_path_a1");
			else if( "12".equals(os) )	//�ȵ���̵� 480*800 (240)
				path = PropertiesUtil.get("memb_regist_image_path_a2");
			else if( "13".equals(os) )	//�ȵ���̵� 540*960
				path = PropertiesUtil.get("memb_regist_image_path_a3");
			else if( "14".equals(os) )	//�ȵ���̵� 800*1280
				path = PropertiesUtil.get("memb_regist_image_path_a4");
			else if( "15".equals(os) )	//�ȵ���̵� 720*1280
				path = PropertiesUtil.get("memb_regist_image_path_a5");
			else if( "16".equals(os) )	//�ȵ���̵� 768*1280
				path = PropertiesUtil.get("memb_regist_image_path_a6");
			else if( "17".equals(os) )	//�ȵ���̵� 768*1024
				path = PropertiesUtil.get("memb_regist_image_path_a7");
		}
		else if("05".equals(part)) {
			// ������
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("stamp_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("stamp_image_path_i4");
			else if( "03".equals(os) )	//������5
				path = PropertiesUtil.get("stamp_image_path_i5");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 (160)
				path = PropertiesUtil.get("stamp_image_path_a1");
			else if( "12".equals(os) )	//�ȵ���̵� 480*800 (240)
				path = PropertiesUtil.get("stamp_image_path_a2");
			else if( "13".equals(os) )	//�ȵ���̵� 540*960
				path = PropertiesUtil.get("stamp_image_path_a3");
			else if( "14".equals(os) )	//�ȵ���̵� 800*1280
				path = PropertiesUtil.get("stamp_image_path_a4");
			else if( "15".equals(os) )	//�ȵ���̵� 720*1280
				path = PropertiesUtil.get("stamp_image_path_a5");
			else if( "16".equals(os) )	//�ȵ���̵� 768*1280
				path = PropertiesUtil.get("stamp_image_path_a6");
			else if( "17".equals(os) )	//�ȵ���̵� 768*1024
				path = PropertiesUtil.get("stamp_image_path_a7");
		}
		else if("06".equals(part)) {
			// �̺�Ʈ/����
			// HTML ���ε� ���μ��� ���
		}
		else if("07".equals(part)) {
			// ���� ����Ʈ
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("cpn_list_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("cpn_list_image_path_i4");
			else if( "03".equals(os) )	//������5
				path = PropertiesUtil.get("cpn_list_image_path_i5");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 (160)
				path = PropertiesUtil.get("cpn_list_image_path_a1");
			else if( "12".equals(os) )	//�ȵ���̵� 480*800 (240)
				path = PropertiesUtil.get("cpn_list_image_path_a2");
			else if( "13".equals(os) )	//�ȵ���̵� 540*960
				path = PropertiesUtil.get("cpn_list_image_path_a3");
			else if( "14".equals(os) )	//�ȵ���̵� 800*1280
				path = PropertiesUtil.get("cpn_list_image_path_a4");
			else if( "15".equals(os) )	//�ȵ���̵� 720*1280
				path = PropertiesUtil.get("cpn_list_image_path_a5");
			else if( "16".equals(os) )	//�ȵ���̵� 768*1280
				path = PropertiesUtil.get("cpn_list_image_path_a6");
			else if( "17".equals(os) )	//�ȵ���̵� 768*1024
				path = PropertiesUtil.get("cpn_list_image_path_a7");
		}
		else if("08".equals(part)) {
			// ���� ��
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("cpn_detail_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("cpn_detail_image_path_i4");
			else if( "03".equals(os) )	//������5
				path = PropertiesUtil.get("cpn_detail_image_path_i5");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 (160)
				path = PropertiesUtil.get("cpn_detail_image_path_a1");
			else if( "12".equals(os) )	//�ȵ���̵� 480*800 (240)
				path = PropertiesUtil.get("cpn_detail_image_path_a2");
			else if( "13".equals(os) )	//�ȵ���̵� 540*960
				path = PropertiesUtil.get("cpn_detail_image_path_a3");
			else if( "14".equals(os) )	//�ȵ���̵� 800*1280
				path = PropertiesUtil.get("cpn_detail_image_path_a4");
			else if( "15".equals(os) )	//�ȵ���̵� 720*1280
				path = PropertiesUtil.get("cpn_detail_image_path_a5");
			else if( "16".equals(os) )	//�ȵ���̵� 768*1280
				path = PropertiesUtil.get("cpn_detail_image_path_a6");
			else if( "17".equals(os) )	//�ȵ���̵� 768*1024
				path = PropertiesUtil.get("cpn_detail_image_path_a7");
		}
		else if("09".equals(part)) {
			// ���� �Ұ��̹���(�ؿ�)
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("cpn_overseas_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("cpn_overseas_image_path_i4");
			else if( "03".equals(os) )	//������5
				path = PropertiesUtil.get("cpn_overseas_image_path_i5");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 (160)
				path = PropertiesUtil.get("cpn_overseas_image_path_a1");
			else if( "12".equals(os) )	//�ȵ���̵� 480*800 (240)
				path = PropertiesUtil.get("cpn_overseas_image_path_a2");
			else if( "13".equals(os) )	//�ȵ���̵� 540*960
				path = PropertiesUtil.get("cpn_overseas_image_path_a3");
			else if( "14".equals(os) )	//�ȵ���̵� 800*1280
				path = PropertiesUtil.get("cpn_overseas_image_path_a4");
			else if( "15".equals(os) )	//�ȵ���̵� 720*1280
				path = PropertiesUtil.get("cpn_overseas_image_path_a5");
			else if( "16".equals(os) )	//�ȵ���̵� 768*1280
				path = PropertiesUtil.get("cpn_overseas_image_path_a6");
			else if( "17".equals(os) )	//�ȵ���̵� 768*1024
				path = PropertiesUtil.get("cpn_overseas_image_path_a7");
		}
		else if("10".equals(part)) {
			// �������� ������ 
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("shop_icon_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("shop_icon_image_path_i4");
			else if( "03".equals(os) )	//������5
				path = PropertiesUtil.get("shop_icon_image_path_i5");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 (160)
				path = PropertiesUtil.get("shop_icon_image_path_a1");
			else if( "12".equals(os) )	//�ȵ���̵� 480*800 (240)
				path = PropertiesUtil.get("shop_icon_image_path_a2");
			else if( "13".equals(os) )	//�ȵ���̵� 540*960
				path = PropertiesUtil.get("shop_icon_image_path_a3");
			else if( "14".equals(os) )	//�ȵ���̵� 800*1280
				path = PropertiesUtil.get("shop_icon_image_path_a4");
			else if( "15".equals(os) )	//�ȵ���̵� 720*1280
				path = PropertiesUtil.get("shop_icon_image_path_a5");
			else if( "16".equals(os) )	//�ȵ���̵� 768*1280
				path = PropertiesUtil.get("shop_icon_image_path_a6");
			else if( "17".equals(os) )	//�ȵ���̵� 768*1024
				path = PropertiesUtil.get("shop_icon_image_path_a7");
		}
		else if("11".equals(part)) {
			// ����� ī���� ����Ʈ�� 
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("memb_class_list_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("memb_class_list_image_path_i4");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 
				path = PropertiesUtil.get("memb_class_list_image_path_a1");
		}
		else if("12".equals(part)) {
			// ����� ī���� ��
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("memb_class_detail_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("memb_class_detail_image_path_i4");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 
				path = PropertiesUtil.get("memb_class_detail_image_path_a1");
		}
		else if("13".equals(part)) {
			// �귣�� ī���̹���(��Ƽ����� ��)
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("brand_card_image_path_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("brand_card_image_path_i4");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 
				path = PropertiesUtil.get("brand_card_image_path_a1");
		}
		else if("14".equals(part)) {
			// shop_��з��̹���
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("MOCA_SHOP_CATEGORY_IMG_PATH_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("MOCA_SHOP_CATEGORY_IMG_PATH_i4");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 
				path = PropertiesUtil.get("MOCA_SHOP_CATEGORY_IMG_PATH_a1");
			else
				path = PropertiesUtil.get("MOCA_SHOP_CATEGORY_IMG_PATH");
		}
		else if("15".equals(part)) {
			// shop_��з���ǰ�̹���
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("MOCA_SHOP_PRODUCTS_IMG_PATH_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("MOCA_SHOP_PRODUCTS_IMG_PATH_i4");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 
				path = PropertiesUtil.get("MOCA_SHOP_PRODUCTS_IMG_PATH_a1");
			else
				path = PropertiesUtil.get("MOCA_SHOP_PRODUCTS_IMG_PATH");
		}
		else if("16".equals(part)) {
			// shop_�̺�Ʈ���
			if("01".equals(os))	//������3GS
				path = PropertiesUtil.get("MOCA_SHOP_EVNET_IMG_PATH_i3");
			else if( "02".equals(os) )	//������4S
				path = PropertiesUtil.get("MOCA_SHOP_EVNET_IMG_PATH_i4");
			else if( "11".equals(os) )	//�ȵ���̵� 480*800 
				path = PropertiesUtil.get("MOCA_SHOP_EVNET_IMG_PATH_a1");
			else
				path = PropertiesUtil.get("MOCA_SHOP_EVNET_IMG_PATH");
		}
		
		// �߰� �ĺ� ID ������ ����ȭ ���� 
		if(!"".equals(addId)) {
			path += addId +"/";
		}
		
		return path;
	}

	
	
	/**
	 * �̹��� �̸����� 
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/image_preview_pop.ms")
	public String imagePreview(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.imagePreview ##########");

		String imgPath = checkStr(request.getParameter("imgPath"),"");
		String cpnId = checkStr(request.getParameter("cpnId"),"");
		String os = checkStr(request.getParameter("os"),"");
		String part = checkStr(request.getParameter("part"),"");
		
		System.out.println("###### imgPath:" + imgPath);
		System.out.println("###### cpnId:" + cpnId);
		System.out.println("###### os:" + os);
		System.out.println("###### part:" + part);

		File file = new File(imgPath);
		long size = 0;
		String ext = "";
		
		// ���� ������
		if( file.exists() ) 
			size = file.length();

		// Ȯ����
		ext = imgPath.substring(imgPath.lastIndexOf(".")+1);
		
		request.setAttribute("imgPath", imgPath);
		request.setAttribute("size", size);
		request.setAttribute("ext", ext);

		return "/member/image_preview_pop";
	}
	
	/**
	 * imageFileUpload : �̹��� ���ε� ���¸� �׽�Ʈ��
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/image_file_upload.ms")
	public String imageFileUpload(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.imageUpload ##########");

		System.out.println("######content-type: " + request.getContentType());
		System.out.println("######length: " + request.getContentLength());
		
		boolean fileYN = true; //-- ���� ���ϸ� ���翩�� üũ
		boolean extYN = true; //-- ���� Ȯ���� ��ȿ�� üũ

		String os = checkStr(request.getParameter("os"));
		String part = checkStr(request.getParameter("part"));
		String addId = checkStr(request.getParameter("addId"));
		System.out.println("###### os:" + os);
		System.out.println("###### part:" + part);
		System.out.println("###### addId:" + addId);

		String path = "";	//-- ������ �̹��� �� ���  ��) c:\~~~~~
		String fileUrl = ""; //-- �� �� ������ �̹��� URL (���ϸ��� ����)
		String fileName = ""; //-- �̹��� ���� ��  ��)test.gif
		String filePath = ""; //-- path + fileName
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	// �̹���(������4,������3GS,������̵�)

		Iterator fileNameIterator = mpRequest.getFileNames();
		while (fileNameIterator.hasNext()) {
	
			MultipartFile multiFile = mpRequest.getFile((String)fileNameIterator.next());
			String ext = multiFile.getOriginalFilename().substring(multiFile.getOriginalFilename().lastIndexOf(".")+1); 
			
			if(DOWNLOADABLE_IMG_EXT.indexOf(ext.toLowerCase()) <0){
				extYN = false; //-- ������ html, htm ������ �ƴ� ��
			}
			
			if (multiFile.getSize() > 0) {
	
				System.out.println("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename());
	
				path = getImagePath(os, part, addId);
	
				//-- fileUrl = "http://localhost:8080/upload_image/"
				fileUrl = PropertiesUtil.get("MEMBERSHIP_DEFAULT_URL_PATH");
				fileUrl += part + "/";
				fileUrl += os + "/";
				
				//-- �̹��� ���ϸ�
				fileName = multiFile.getOriginalFilename();
				
				filePath = path + fileName; //-- path + fileName

				//-- ���ϸ� �ߺ�ó��
				try {
					System.out.println("### path : " + path);
					System.out.println("### fileName : " + fileName);
					System.out.println("### fileUrl : " + fileUrl);
					System.out.println("### filePath : " + filePath);
					System.out.println("### extYN : " + extYN);
					
					if(extYN){
						FileUtil.writeFile(multiFile, path);
					}
					
				} catch (Exception e) {
					System.out.println("########### Upload Error ##############");
					e.printStackTrace();
				}
			}
		}
	
		//�̹��� ���� ���
		JSONObject jObj = new JSONObject();
		jObj.put("path", path);
		jObj.put("fileYN", fileYN);
		jObj.put("fileUrl", fileUrl);
		jObj.put("fileName", fileName);
		jObj.put("filePath", filePath);
		jObj.put("extYN", extYN); //-- ���� Ȯ���� ��ȿ�� ���� üũ
		request.setAttribute("JSONObject", jObj);
		
		return "/common/result_page";
	}
	
	/**
	 * �̹��� ���� [���� ó���� ����� ����]
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/save_image.ms")
	public String saveImage(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.saveImage ##########");

		MwCmImageService mwCmImageService = new MwCmImageService();
		try {
				
			String[] arr = request.getParameterValues("_upImgList");
			for(int i=0; i<arr.length; i++) {
				String one = arr[i];
				String oneSplit[] = one.split("[|]");
				
				String os = oneSplit[0];
				String part = oneSplit[1];
				String urlPath = oneSplit[2];
				String id = "test0003";
				String level = "";
				String imageHost = PropertiesUtil.get("img_host");
				String regUser = "tester";
				
				System.out.println("os : " + os);
				System.out.println("part : " + part);
				System.out.println("urlPath : " + urlPath);
				System.out.println("id : " + id);
				System.out.println("level : " + level);
				System.out.println("imageHost : " + imageHost);
				System.out.println("regUser : " + regUser);
				
				
				MwCmImage mwCmImage = new MwCmImage();
				mwCmImage.setId(id);
				mwCmImage.setOsType(os);
				mwCmImage.setUseType(part);
				mwCmImage.setLevel(level);
				mwCmImage.setImageHost(imageHost);
				mwCmImage.setImageUrl(urlPath);
				mwCmImage.setRegUser(regUser);
				mwCmImage.setRegDtm(new Date());
				// DB insert
				mwCmImageService.insert(mwCmImage);
			}
			// DB commit
			mwCmImageService.commit();
		}
		catch(Exception e) {
			// DB rollback
			mwCmImageService.rollback();
			e.printStackTrace();
		}
 
		return "/common/common_sample";
	}


	/**
	 * �޺� ���޻�(�귣��/������)
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_paycomp.ms")
	public String commonPayComp(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.commonPayComp ##########");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwCmPayCompany> mwCmPayCompanyList = null;
		MwCmPayCompanyService mwCmPayCompanyService = new MwCmPayCompanyService();
		MwCmPayCompanyExample mwCmPayCompanyExample = null;


		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		mwCmPayCompanyExample = new MwCmPayCompanyExample();
		mwCmPayCompanyList = mwCmPayCompanyService.getByExample(mwCmPayCompanyExample);


		Iterator<MwCmPayCompany> it = mwCmPayCompanyList.iterator();
		while(it.hasNext()) {
			MwCmPayCompany one = it.next();
			jObj.put(one.getPaycompId(), one.getPaycompName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("mwCmPayCompanyList", mwCmPayCompanyList);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";

	}

	/**
	 * �޺� ���޻�(�귣��/������)
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_bank.ms")
	public String commonBank(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.commonBank ##########");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<CpnBankInfo> cpnBankInfoList = null;
		CpnBankInfoService cpnBankInfoService = new CpnBankInfoService();
		CpnBankInfoExample cpnBankInfoExample = null;


		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		cpnBankInfoExample = new CpnBankInfoExample();
		cpnBankInfoList = cpnBankInfoService.getByExample(cpnBankInfoExample);


		Iterator<CpnBankInfo> it = cpnBankInfoList.iterator();
		while(it.hasNext()) {
			CpnBankInfo one = it.next();
			jObj.put(one.getBankId(), one.getName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("cpnBankInfoList", cpnBankInfoList);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";

	}//End commonBank()
	
	

	/**
	 * HTML ���ε� 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/html_upload.ms")
	public String htmlUpload(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.htmlUpload ##########");
		System.out.println("######content-type: " + request.getContentType());
		System.out.println("######length: " + request.getContentLength());

		// �ĸ����� �ޱ� 
		String partHtml = checkStr(request.getParameter("partHtml"));
		String addId = checkStr(request.getParameter("addId"));
		String radioUpload = checkStr(request.getParameter("radio_upload"));

		System.out.println("###### partHtml:" + partHtml);
		System.out.println("###### addId:" + addId);
		System.out.println("###### radioUpload:" + radioUpload);
		
		
		String path = "";	// �̹��� ���
		String filePath = "";
		String fileName = "";
		long fileSize = 0;
		boolean fileYN = true; //-- ���� ���ϸ� ���翩�� üũ
		boolean extYN = true; //-- ���� Ȯ���� ��ȿ�� üũ
		String memberNotiUrl = PropertiesUtil.get("img_host"); //-- ������� html url ���
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
		Iterator fileNameIterator = mpRequest.getFileNames();
		try {
			while (fileNameIterator.hasNext()) {

				MultipartFile multiFile = mpRequest.getFile((String)fileNameIterator.next());
				String ext = multiFile.getOriginalFilename().substring(multiFile.getOriginalFilename().lastIndexOf(".")+1); 
				
				if(DOWNLOADABLE_HTML_EXT.indexOf(ext.toLowerCase()) <0 && DOWNLOADABLE_IMG_EXT.indexOf(ext.toLowerCase()) <0){
					extYN = false; //-- ������ html, htm ������ �ƴ� ��
				}
				
				if (multiFile.getSize() > 0) {
					System.out.println("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename()); //##
					
					fileSize = multiFile.getSize();
							
					// HTML or �̹��� �����ؼ� ����
					path = PropertiesUtil.get("member_html_root_path");
	
					// ��뱸�� 
					if( "01".equals(partHtml) ) {//01: ����� �������
						path += "member/";
					} else if( "02".equals(partHtml) ) {//02: ���� �󼼼���
						path += "coupon/";
					} else if( "03".equals(partHtml) ) {//03: �̺�Ʈ/����
						path += "event/";
					} else if( "04".equals(partHtml) ) {//04: ���
						path += "agree/";
					} else if( "05".equals(partHtml) ) {//05: ��ī�� �̺�Ʈ/��������
						path += "shop/notice/";
					}else if( "06".equals(partHtml) ) {//06: ��ī�� ���� ȭ��
						path += "shop/screen/";
					}
					
					
					if(!"".equals(addId))
						path += addId+"/";

					// �̹��� ���ε�� 
					if("image".equals(radioUpload)) {
						path += "images/";
					}
					
					fileName = multiFile.getOriginalFilename();
					filePath = path + fileName;
		
					//fileYN = FileUtil.isFile(filePath); //-- ���� ���翩�� �Ǵ�
					fileYN = false; //-- ���� ���翩�� �Ǵ�
				
					System.out.println("### path : " + path); //##
					
					if(extYN){ //-- Ȯ���� ������ ������~
						//if(!fileYN){ //-- ������ ���ϸ��� �������� ������...
							File tempFile = FileUtil.writeFileNewHtmlName(multiFile, path);
						//}
						fileName = tempFile.getName();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("########### Upload Error ##############"); //##
			e.printStackTrace();
		}
	
		//�̹��� ���� ���
		JSONObject jObj = new JSONObject();
		jObj.put("fileYN", fileYN);
		jObj.put("fileName", fileName);
		jObj.put("upload_gb", radioUpload);
		jObj.put("filePath", filePath);
		jObj.put("memberNotiUrl", memberNotiUrl+path);
		jObj.put("fileSize", fileSize);
		jObj.put("extYN", extYN); //-- ���� Ȯ���� ��ȿ�� ���� üũ
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}

	/**
	 * �޺� ���޻�(�귣��/������) _ ��� .st
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_company.st")
	public String commonCompanyStat(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.commonCompany ##########");

		String compId = checkStr(request.getParameter("compId"), "CMP000000000");
		System.out.println("compId: "+compId);

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwCmCompany> mwCmCompanyList = null;
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = null;


		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		mwCmCompanyExample = new MwCmCompanyExample();
		mwCmCompanyExample.or().andUpperCompIdEqualTo(compId);
		mwCmCompanyList = mwCmCompanyService.getByExample(mwCmCompanyExample);


		Iterator<MwCmCompany> it = mwCmCompanyList.iterator();
		while(it.hasNext()) {
			MwCmCompany one = it.next();
			jObj.put(one.getCompId(), one.getCompName());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("mwCmCompanyList", mwCmCompanyList);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}
	
	/**
	 * �޺� �����ڵ� _ ��� .st
	 *
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/common/common_code.st")
	public String commonCodeStat(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("########## CommonProcessController.commonCode ##########");

		String grpCode = checkStr(request.getParameter("grpCode"), "9999");;
		System.out.println("grpCode: "+grpCode);

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwCmCode> mwCmCodeList = null;
		MwCmCodeService mwCmCodeService = new MwCmCodeService();
		MwCmCodeExample mwCmCodeExample = null;


		JSONObject jObj = new JSONObject();

		/*��� ��������*/
		mwCmCodeExample = new MwCmCodeExample();
		mwCmCodeExample.or().andGrpCodeEqualTo(grpCode);
		mwCmCodeList = mwCmCodeService.getByExample(mwCmCodeExample);


		Iterator<MwCmCode> it = mwCmCodeList.iterator();
		while(it.hasNext()) {
			MwCmCode one = it.next();
			jObj.put(one.getComCd(), one.getComCdVal());
		}

		/* SET ATTRIBUTEs */
		request.setAttribute("mwCmCodeList", mwCmCodeList);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}
}//END class

