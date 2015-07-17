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
	 * 샘플 페이지
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
	 * 콤보 공통코드
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

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwCmCode> mwCmCodeList = null;
		MwCmCodeService mwCmCodeService = new MwCmCodeService();
		MwCmCodeExample mwCmCodeExample = null;


		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * 콤보 제휴사(브랜드/가맹점)
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

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwCmCompany> mwCmCompanyList = null;
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = null;


		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * 팝업 제휴사
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

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwCmCompany> mwCmCompanyList = null;
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = null;


		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * 팝업 멤버십
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

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MembCardlist> membCardlistList = null;
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlistExample membCardlistExample = null;


		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * 팝업 금융사
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

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		CpnBankInfoService cpnBankInfoService = new CpnBankInfoService();
		CpnBankInfoExample cpnBankInfoExample = new CpnBankInfoExample();
		cpnBankInfoExample.or().andNameLike("%"+searchText+"%");
		List<CpnBankInfo> cpnBankInfo = cpnBankInfoService.getByExample(cpnBankInfoExample);		

		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * 팝업 결제사
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

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
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
	 * 콤보 멤버십
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


		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MembCardlist> membCardlistList = null;
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlistExample membCardlistExample = null;


		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * 콤보 멤버십 검색
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
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MembCardlist> membCardlistList = null;
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlistExample membCardlistExample = new MembCardlistExample();
		
		if(brandId.matches("CMP.*")){
			membCardlistExample.or().andCompIdEqualTo(brandId);
		}else if(brandId.matches("BRD.*")){
			membCardlistExample.or().andBrandIdEqualTo(brandId);
		}
		
		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * 콤보 대표 멤버십 검색
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
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MembCardlist> membCardlistList = null;
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlistExample membCardlistExample = new MembCardlistExample();
		
		if(brandId.matches("CMP.*")){
			membCardlistExample.or().andCompIdEqualTo(brandId).andUpperMembIdIsNull();
		}else if(brandId.matches("BRD.*")){
			membCardlistExample.or().andBrandIdEqualTo(brandId).andUpperMembIdIsNull();
		}
		
		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * 이미지 업로드
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
		
		boolean fileYN = true; //-- 동일 파일명 존재여부 체크
		boolean extYN = true; //-- 파일 확장자 유효성 체크

		String os = checkStr(request.getParameter("os"));
		String part = checkStr(request.getParameter("part"));
		String addId = checkStr(request.getParameter("addImgId"));
		System.out.println("###### os:" + os);
		System.out.println("###### part:" + part);
		System.out.println("###### addId:" + addId);

		String host = "";
		String path = "";	// 이미지 경로
		String filePath = "";
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	// 이미지(아이폰4,아이폰3GS,만드로이드)

		Iterator fileNameIterator = mpRequest.getFileNames();
		while (fileNameIterator.hasNext()) {
	
			MultipartFile multiFile = mpRequest.getFile((String)fileNameIterator.next());
			if (multiFile.getSize() > 0) {

				String ext = multiFile.getOriginalFilename().substring(multiFile.getOriginalFilename().lastIndexOf(".")+1); 
				
				if(DOWNLOADABLE_IMG_EXT.indexOf(ext.toLowerCase()) <0){
					extYN = false; //-- 파일이 html, htm 파일이 아닐 때
				}
				System.out.println("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename());
	
				//해당 경로에 따른 이미지 파일 저장
//				host = PropertiesUtil.get("img_host");
				path = getImagePath(os, part, addId);
				filePath = path + multiFile.getOriginalFilename();
	
				//TODO 파일명 중복처리
	
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
	
		//이미지 저장 경로
		JSONObject jObj = new JSONObject();
		jObj.put("extYN", extYN);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}

	/**
	 * 각 구분에 따라 이미지 저잗될 경로 리턴
	 * 
	 * @param os	단말기구분
	 * @param part	사용구분
	 * @return
	 */
	private String getImagePath(String os, String part, String addId) {
		String path = "";
		
		if("01".equals(part)) {
			// 브랜드
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("brand_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("brand_image_path_i4");
			else if( "03".equals(os) )	//아이폰5
				path = PropertiesUtil.get("brand_image_path_i5");
			else if( "11".equals(os) )	//안드로이드 480*800 (160)
				path = PropertiesUtil.get("brand_image_path_a1");
			else if( "12".equals(os) )	//안드로이드 480*800 (240)
				path = PropertiesUtil.get("brand_image_path_a2");
			else if( "13".equals(os) )	//안드로이드 540*960
				path = PropertiesUtil.get("brand_image_path_a3");
			else if( "14".equals(os) )	//안드로이드 800*1280
				path = PropertiesUtil.get("brand_image_path_a4");
			else if( "15".equals(os) )	//안드로이드 720*1280
				path = PropertiesUtil.get("brand_image_path_a5");
			else if( "16".equals(os) )	//안드로이드 768*1280
				path = PropertiesUtil.get("brand_image_path_a6");
			else if( "17".equals(os) )	//안드로이드 768*1024
				path = PropertiesUtil.get("brand_image_path_a7");
		} 
		else if("02".equals(part)) {
			// 멤버십 리스트용
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("memb_list_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("memb_list_image_path_i4");
			else if( "03".equals(os) )	//아이폰5
				path = PropertiesUtil.get("memb_list_image_path_i5");
			else if( "11".equals(os) )	//안드로이드 480*800 (160)
				path = PropertiesUtil.get("memb_list_image_path_a1");
			else if( "12".equals(os) )	//안드로이드 480*800 (240)
				path = PropertiesUtil.get("memb_list_image_path_a2");
			else if( "13".equals(os) )	//안드로이드 540*960
				path = PropertiesUtil.get("memb_list_image_path_a3");
			else if( "14".equals(os) )	//안드로이드 800*1280
				path = PropertiesUtil.get("memb_list_image_path_a4");
			else if( "15".equals(os) )	//안드로이드 720*1280
				path = PropertiesUtil.get("memb_list_image_path_a5");
			else if( "16".equals(os) )	//안드로이드 768*1280
				path = PropertiesUtil.get("memb_list_image_path_a6");
			else if( "17".equals(os) )	//안드로이드 768*1024
				path = PropertiesUtil.get("memb_list_image_path_a7");
		}
		else if("03".equals(part)) {
			// 멤버십 상세용
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("memb_detail_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("memb_detail_image_path_i4");
			else if( "03".equals(os) )	//아이폰5
				path = PropertiesUtil.get("memb_detail_image_path_i5");
			else if( "11".equals(os) )	//안드로이드 480*800 (160)
				path = PropertiesUtil.get("memb_detail_image_path_a1");
			else if( "12".equals(os) )	//안드로이드 480*800 (240)
				path = PropertiesUtil.get("memb_detail_image_path_a2");
			else if( "13".equals(os) )	//안드로이드 540*960
				path = PropertiesUtil.get("memb_detail_image_path_a3");
			else if( "14".equals(os) )	//안드로이드 800*1280
				path = PropertiesUtil.get("memb_detail_image_path_a4");
			else if( "15".equals(os) )	//안드로이드 720*1280
				path = PropertiesUtil.get("memb_detail_image_path_a5");
			else if( "16".equals(os) )	//안드로이드 768*1280
				path = PropertiesUtil.get("memb_detail_image_path_a6");
			else if( "17".equals(os) )	//안드로이드 768*1024
				path = PropertiesUtil.get("memb_detail_image_path_a7");
		}
		else if("04".equals(part)) {
			// 멤버십 등록용
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("memb_regist_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("memb_regist_image_path_i4");
			else if( "03".equals(os) )	//아이폰5
				path = PropertiesUtil.get("memb_regist_image_path_i5");
			else if( "11".equals(os) )	//안드로이드 480*800 (160)
				path = PropertiesUtil.get("memb_regist_image_path_a1");
			else if( "12".equals(os) )	//안드로이드 480*800 (240)
				path = PropertiesUtil.get("memb_regist_image_path_a2");
			else if( "13".equals(os) )	//안드로이드 540*960
				path = PropertiesUtil.get("memb_regist_image_path_a3");
			else if( "14".equals(os) )	//안드로이드 800*1280
				path = PropertiesUtil.get("memb_regist_image_path_a4");
			else if( "15".equals(os) )	//안드로이드 720*1280
				path = PropertiesUtil.get("memb_regist_image_path_a5");
			else if( "16".equals(os) )	//안드로이드 768*1280
				path = PropertiesUtil.get("memb_regist_image_path_a6");
			else if( "17".equals(os) )	//안드로이드 768*1024
				path = PropertiesUtil.get("memb_regist_image_path_a7");
		}
		else if("05".equals(part)) {
			// 스탬프
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("stamp_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("stamp_image_path_i4");
			else if( "03".equals(os) )	//아이폰5
				path = PropertiesUtil.get("stamp_image_path_i5");
			else if( "11".equals(os) )	//안드로이드 480*800 (160)
				path = PropertiesUtil.get("stamp_image_path_a1");
			else if( "12".equals(os) )	//안드로이드 480*800 (240)
				path = PropertiesUtil.get("stamp_image_path_a2");
			else if( "13".equals(os) )	//안드로이드 540*960
				path = PropertiesUtil.get("stamp_image_path_a3");
			else if( "14".equals(os) )	//안드로이드 800*1280
				path = PropertiesUtil.get("stamp_image_path_a4");
			else if( "15".equals(os) )	//안드로이드 720*1280
				path = PropertiesUtil.get("stamp_image_path_a5");
			else if( "16".equals(os) )	//안드로이드 768*1280
				path = PropertiesUtil.get("stamp_image_path_a6");
			else if( "17".equals(os) )	//안드로이드 768*1024
				path = PropertiesUtil.get("stamp_image_path_a7");
		}
		else if("06".equals(part)) {
			// 이벤트/공지
			// HTML 업로드 프로세스 사용
		}
		else if("07".equals(part)) {
			// 쿠폰 리스트
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("cpn_list_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("cpn_list_image_path_i4");
			else if( "03".equals(os) )	//아이폰5
				path = PropertiesUtil.get("cpn_list_image_path_i5");
			else if( "11".equals(os) )	//안드로이드 480*800 (160)
				path = PropertiesUtil.get("cpn_list_image_path_a1");
			else if( "12".equals(os) )	//안드로이드 480*800 (240)
				path = PropertiesUtil.get("cpn_list_image_path_a2");
			else if( "13".equals(os) )	//안드로이드 540*960
				path = PropertiesUtil.get("cpn_list_image_path_a3");
			else if( "14".equals(os) )	//안드로이드 800*1280
				path = PropertiesUtil.get("cpn_list_image_path_a4");
			else if( "15".equals(os) )	//안드로이드 720*1280
				path = PropertiesUtil.get("cpn_list_image_path_a5");
			else if( "16".equals(os) )	//안드로이드 768*1280
				path = PropertiesUtil.get("cpn_list_image_path_a6");
			else if( "17".equals(os) )	//안드로이드 768*1024
				path = PropertiesUtil.get("cpn_list_image_path_a7");
		}
		else if("08".equals(part)) {
			// 쿠폰 상세
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("cpn_detail_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("cpn_detail_image_path_i4");
			else if( "03".equals(os) )	//아이폰5
				path = PropertiesUtil.get("cpn_detail_image_path_i5");
			else if( "11".equals(os) )	//안드로이드 480*800 (160)
				path = PropertiesUtil.get("cpn_detail_image_path_a1");
			else if( "12".equals(os) )	//안드로이드 480*800 (240)
				path = PropertiesUtil.get("cpn_detail_image_path_a2");
			else if( "13".equals(os) )	//안드로이드 540*960
				path = PropertiesUtil.get("cpn_detail_image_path_a3");
			else if( "14".equals(os) )	//안드로이드 800*1280
				path = PropertiesUtil.get("cpn_detail_image_path_a4");
			else if( "15".equals(os) )	//안드로이드 720*1280
				path = PropertiesUtil.get("cpn_detail_image_path_a5");
			else if( "16".equals(os) )	//안드로이드 768*1280
				path = PropertiesUtil.get("cpn_detail_image_path_a6");
			else if( "17".equals(os) )	//안드로이드 768*1024
				path = PropertiesUtil.get("cpn_detail_image_path_a7");
		}
		else if("09".equals(part)) {
			// 쿠폰 소개이미지(해외)
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("cpn_overseas_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("cpn_overseas_image_path_i4");
			else if( "03".equals(os) )	//아이폰5
				path = PropertiesUtil.get("cpn_overseas_image_path_i5");
			else if( "11".equals(os) )	//안드로이드 480*800 (160)
				path = PropertiesUtil.get("cpn_overseas_image_path_a1");
			else if( "12".equals(os) )	//안드로이드 480*800 (240)
				path = PropertiesUtil.get("cpn_overseas_image_path_a2");
			else if( "13".equals(os) )	//안드로이드 540*960
				path = PropertiesUtil.get("cpn_overseas_image_path_a3");
			else if( "14".equals(os) )	//안드로이드 800*1280
				path = PropertiesUtil.get("cpn_overseas_image_path_a4");
			else if( "15".equals(os) )	//안드로이드 720*1280
				path = PropertiesUtil.get("cpn_overseas_image_path_a5");
			else if( "16".equals(os) )	//안드로이드 768*1280
				path = PropertiesUtil.get("cpn_overseas_image_path_a6");
			else if( "17".equals(os) )	//안드로이드 768*1024
				path = PropertiesUtil.get("cpn_overseas_image_path_a7");
		}
		else if("10".equals(part)) {
			// 매장정보 아이콘 
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("shop_icon_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("shop_icon_image_path_i4");
			else if( "03".equals(os) )	//아이폰5
				path = PropertiesUtil.get("shop_icon_image_path_i5");
			else if( "11".equals(os) )	//안드로이드 480*800 (160)
				path = PropertiesUtil.get("shop_icon_image_path_a1");
			else if( "12".equals(os) )	//안드로이드 480*800 (240)
				path = PropertiesUtil.get("shop_icon_image_path_a2");
			else if( "13".equals(os) )	//안드로이드 540*960
				path = PropertiesUtil.get("shop_icon_image_path_a3");
			else if( "14".equals(os) )	//안드로이드 800*1280
				path = PropertiesUtil.get("shop_icon_image_path_a4");
			else if( "15".equals(os) )	//안드로이드 720*1280
				path = PropertiesUtil.get("shop_icon_image_path_a5");
			else if( "16".equals(os) )	//안드로이드 768*1280
				path = PropertiesUtil.get("shop_icon_image_path_a6");
			else if( "17".equals(os) )	//안드로이드 768*1024
				path = PropertiesUtil.get("shop_icon_image_path_a7");
		}
		else if("11".equals(part)) {
			// 멤버십 카드등급 리스트용 
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("memb_class_list_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("memb_class_list_image_path_i4");
			else if( "11".equals(os) )	//안드로이드 480*800 
				path = PropertiesUtil.get("memb_class_list_image_path_a1");
		}
		else if("12".equals(part)) {
			// 멤버십 카드등급 상세
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("memb_class_detail_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("memb_class_detail_image_path_i4");
			else if( "11".equals(os) )	//안드로이드 480*800 
				path = PropertiesUtil.get("memb_class_detail_image_path_a1");
		}
		else if("13".equals(part)) {
			// 브랜드 카드이미지(멀티멤버십 용)
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("brand_card_image_path_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("brand_card_image_path_i4");
			else if( "11".equals(os) )	//안드로이드 480*800 
				path = PropertiesUtil.get("brand_card_image_path_a1");
		}
		else if("14".equals(part)) {
			// shop_대분류이미지
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("MOCA_SHOP_CATEGORY_IMG_PATH_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("MOCA_SHOP_CATEGORY_IMG_PATH_i4");
			else if( "11".equals(os) )	//안드로이드 480*800 
				path = PropertiesUtil.get("MOCA_SHOP_CATEGORY_IMG_PATH_a1");
			else
				path = PropertiesUtil.get("MOCA_SHOP_CATEGORY_IMG_PATH");
		}
		else if("15".equals(part)) {
			// shop_대분류상품이미지
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("MOCA_SHOP_PRODUCTS_IMG_PATH_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("MOCA_SHOP_PRODUCTS_IMG_PATH_i4");
			else if( "11".equals(os) )	//안드로이드 480*800 
				path = PropertiesUtil.get("MOCA_SHOP_PRODUCTS_IMG_PATH_a1");
			else
				path = PropertiesUtil.get("MOCA_SHOP_PRODUCTS_IMG_PATH");
		}
		else if("16".equals(part)) {
			// shop_이벤트배너
			if("01".equals(os))	//아이폰3GS
				path = PropertiesUtil.get("MOCA_SHOP_EVNET_IMG_PATH_i3");
			else if( "02".equals(os) )	//아이폰4S
				path = PropertiesUtil.get("MOCA_SHOP_EVNET_IMG_PATH_i4");
			else if( "11".equals(os) )	//안드로이드 480*800 
				path = PropertiesUtil.get("MOCA_SHOP_EVNET_IMG_PATH_a1");
			else
				path = PropertiesUtil.get("MOCA_SHOP_EVNET_IMG_PATH");
		}
		
		// 추가 식별 ID 설정시 세분화 관리 
		if(!"".equals(addId)) {
			path += addId +"/";
		}
		
		return path;
	}

	
	
	/**
	 * 이미지 미리보기 
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
		
		// 파일 사이즈
		if( file.exists() ) 
			size = file.length();

		// 확장자
		ext = imgPath.substring(imgPath.lastIndexOf(".")+1);
		
		request.setAttribute("imgPath", imgPath);
		request.setAttribute("size", size);
		request.setAttribute("ext", ext);

		return "/member/image_preview_pop";
	}
	
	/**
	 * imageFileUpload : 이미지 업로드 김태리 테스트용
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
		
		boolean fileYN = true; //-- 동일 파일명 존재여부 체크
		boolean extYN = true; //-- 파일 확장자 유효성 체크

		String os = checkStr(request.getParameter("os"));
		String part = checkStr(request.getParameter("part"));
		String addId = checkStr(request.getParameter("addId"));
		System.out.println("###### os:" + os);
		System.out.println("###### part:" + part);
		System.out.println("###### addId:" + addId);

		String path = "";	//-- 서버의 이미지 실 경로  예) c:\~~~~~
		String fileUrl = ""; //-- 웹 상에 보여질 이미지 URL (파일명은 제외)
		String fileName = ""; //-- 이미지 파일 명  예)test.gif
		String filePath = ""; //-- path + fileName
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	// 이미지(아이폰4,아이폰3GS,만드로이드)

		Iterator fileNameIterator = mpRequest.getFileNames();
		while (fileNameIterator.hasNext()) {
	
			MultipartFile multiFile = mpRequest.getFile((String)fileNameIterator.next());
			String ext = multiFile.getOriginalFilename().substring(multiFile.getOriginalFilename().lastIndexOf(".")+1); 
			
			if(DOWNLOADABLE_IMG_EXT.indexOf(ext.toLowerCase()) <0){
				extYN = false; //-- 파일이 html, htm 파일이 아닐 때
			}
			
			if (multiFile.getSize() > 0) {
	
				System.out.println("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename());
	
				path = getImagePath(os, part, addId);
	
				//-- fileUrl = "http://localhost:8080/upload_image/"
				fileUrl = PropertiesUtil.get("MEMBERSHIP_DEFAULT_URL_PATH");
				fileUrl += part + "/";
				fileUrl += os + "/";
				
				//-- 이미지 파일명
				fileName = multiFile.getOriginalFilename();
				
				filePath = path + fileName; //-- path + fileName

				//-- 파일명 중복처리
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
	
		//이미지 저장 경로
		JSONObject jObj = new JSONObject();
		jObj.put("path", path);
		jObj.put("fileYN", fileYN);
		jObj.put("fileUrl", fileUrl);
		jObj.put("fileName", fileName);
		jObj.put("filePath", filePath);
		jObj.put("extYN", extYN); //-- 파일 확장자 유효성 여부 체크
		request.setAttribute("JSONObject", jObj);
		
		return "/common/result_page";
	}
	
	/**
	 * 이미지 저장 [저장 처리시 참고용 로직]
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
	 * 콤보 제휴사(브랜드/가맹점)
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

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwCmPayCompany> mwCmPayCompanyList = null;
		MwCmPayCompanyService mwCmPayCompanyService = new MwCmPayCompanyService();
		MwCmPayCompanyExample mwCmPayCompanyExample = null;


		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * 콤보 제휴사(브랜드/가맹점)
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
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<CpnBankInfo> cpnBankInfoList = null;
		CpnBankInfoService cpnBankInfoService = new CpnBankInfoService();
		CpnBankInfoExample cpnBankInfoExample = null;


		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * HTML 업로드 
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

		// 파마메터 받기 
		String partHtml = checkStr(request.getParameter("partHtml"));
		String addId = checkStr(request.getParameter("addId"));
		String radioUpload = checkStr(request.getParameter("radio_upload"));

		System.out.println("###### partHtml:" + partHtml);
		System.out.println("###### addId:" + addId);
		System.out.println("###### radioUpload:" + radioUpload);
		
		
		String path = "";	// 이미지 경로
		String filePath = "";
		String fileName = "";
		long fileSize = 0;
		boolean fileYN = true; //-- 동일 파일명 존재여부 체크
		boolean extYN = true; //-- 파일 확장자 유효성 체크
		String memberNotiUrl = PropertiesUtil.get("img_host"); //-- 사용혜택 html url 경로
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
		Iterator fileNameIterator = mpRequest.getFileNames();
		try {
			while (fileNameIterator.hasNext()) {

				MultipartFile multiFile = mpRequest.getFile((String)fileNameIterator.next());
				String ext = multiFile.getOriginalFilename().substring(multiFile.getOriginalFilename().lastIndexOf(".")+1); 
				
				if(DOWNLOADABLE_HTML_EXT.indexOf(ext.toLowerCase()) <0 && DOWNLOADABLE_IMG_EXT.indexOf(ext.toLowerCase()) <0){
					extYN = false; //-- 파일이 html, htm 파일이 아닐 때
				}
				
				if (multiFile.getSize() > 0) {
					System.out.println("### multiFile : " + multiFile.getName() +", " + multiFile.getSize() +", " + multiFile.getOriginalFilename()); //##
					
					fileSize = multiFile.getSize();
							
					// HTML or 이미지 구분해서 저장
					path = PropertiesUtil.get("member_html_root_path");
	
					// 사용구분 
					if( "01".equals(partHtml) ) {//01: 멤버십 사용혜택
						path += "member/";
					} else if( "02".equals(partHtml) ) {//02: 쿠폰 상세설명
						path += "coupon/";
					} else if( "03".equals(partHtml) ) {//03: 이벤트/공지
						path += "event/";
					} else if( "04".equals(partHtml) ) {//04: 약관
						path += "agree/";
					} else if( "05".equals(partHtml) ) {//05: 모카샵 이벤트/공지사항
						path += "shop/notice/";
					}else if( "06".equals(partHtml) ) {//06: 모카샵 어플 화면
						path += "shop/screen/";
					}
					
					
					if(!"".equals(addId))
						path += addId+"/";

					// 이미지 업로드시 
					if("image".equals(radioUpload)) {
						path += "images/";
					}
					
					fileName = multiFile.getOriginalFilename();
					filePath = path + fileName;
		
					//fileYN = FileUtil.isFile(filePath); //-- 파일 존재여부 판단
					fileYN = false; //-- 파일 존재여부 판단
				
					System.out.println("### path : " + path); //##
					
					if(extYN){ //-- 확장자 문제가 없으면~
						//if(!fileYN){ //-- 동일한 파일명이 존재하지 않으면...
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
	
		//이미지 저장 경로
		JSONObject jObj = new JSONObject();
		jObj.put("fileYN", fileYN);
		jObj.put("fileName", fileName);
		jObj.put("upload_gb", radioUpload);
		jObj.put("filePath", filePath);
		jObj.put("memberNotiUrl", memberNotiUrl+path);
		jObj.put("fileSize", fileSize);
		jObj.put("extYN", extYN); //-- 파일 확장자 유효성 여부 체크
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}

	/**
	 * 콤보 제휴사(브랜드/가맹점) _ 통계 .st
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

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwCmCompany> mwCmCompanyList = null;
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = null;


		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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
	 * 콤보 공통코드 _ 통계 .st
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

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwCmCode> mwCmCodeList = null;
		MwCmCodeService mwCmCodeService = new MwCmCodeService();
		MwCmCodeExample mwCmCodeExample = null;


		JSONObject jObj = new JSONObject();

		/*목록 가져오기*/
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

