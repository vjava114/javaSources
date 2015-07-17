package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.DateTime;
import com.wallet.membership.common.ExcelContoller;
import com.wallet.membership.common.UpFile;
import com.wallet.membership.common.UpdateStore;
import com.wallet.membership.common.gpsAddressGetter;
import com.wallet.membership.model.MembCardlist;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwMsCompPayMentExample;
import com.wallet.membership.model.MwMsStarPayMent;
import com.wallet.membership.model.MwMsStarPayMentExample;
import com.wallet.membership.model.custom.Member;
import com.wallet.membership.model.custom.Store;
import com.wallet.membership.service.MembCardlistService;
import com.wallet.membership.service.MwCmCompanyService;
import com.wallet.membership.service.MwMsCompPayMentService;
import com.wallet.membership.service.custom.MemberService;
import com.wallet.membership.service.custom.MwMsStarPayMentService;
import com.wallet.membership.service.custom.StoreService;


@Controller
public class MemberStoreListController extends CommonController {
	private final String PAGE_CODE = "MEMBER_STORE_LIST";
	private Logger log = Log.getLogger("logs");
	private MwAdAccessLogService logSVC = new MwAdAccessLogService();
	KTDBCipher ktService = new KTDBCipher();
	/**
	 * 제휴사 목록조회
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/bulk_coupon_list"
	 */ 
	@RequestMapping(value="/member/member_store_list.ms")
	public String MemberStoreList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		//데이타 베이스 호출 변수 선언
		List<MwCmCompany> MwCmCompanyList = null;
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = null;
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		//페이징 처리를 위한 파라메타
		String pageNo = checkStr(request.getParameter("nowPage"), "1");
		String rowsPerPage = checkStr(request.getParameter("rowsPerPage"), "15");
		String Today = DateTime.format("yyyy-MM-dd");
		int page = Integer.parseInt(pageNo);
		int row = Integer.parseInt(rowsPerPage);
		//파라메타 받기
		String partner_name = checkStr(request.getParameter("partner_name"), "");
		String franchise_name = checkStr(request.getParameter("franchise_name"), "");
		String store_name = checkStr(request.getParameter("store_name"), "");
		String Sdate = checkStr(request.getParameter("Sdate"),"");
		String Edate = checkStr(request.getParameter("Edate"),"");
		String DateType = checkStr(request.getParameter("sh_type"), "01");
		String StoreTerm = checkStr(request.getParameter("store_date"), "01");
		
		params.put("partner_name", partner_name);
		params.put("franchise_name", franchise_name);
		params.put("store_name", store_name);
		params.put("Sdate", Sdate);
		params.put("Edate", Edate);
		params.put("DateType", DateType);
		params.put("StoreTerm", StoreTerm);

		if(StoreTerm.equals("01")){
			if(Sdate.equals("") && Edate.equals("")){
				Sdate = Today;
				Edate = Today;
			}
		}
			
		int count = 0;//전체 레코드 갯수
					
			mwCmCompanyExample = new MwCmCompanyExample();
			mwCmCompanyExample.or().andACompIdLike("SHP%");
			mwCmCompanyExample.setOrderByClause("A.REG_DTM DESC, A.COMP_NAME DESC");
			
			if(DateType.equals("01")){
				mwCmCompanyExample.or().andARegDtmIsNotNull();
			}else if(DateType.equals("02")){
				mwCmCompanyExample.or().andAChgDtmIsNotNull();
			}
			
			if(partner_name != null && !partner_name.equals("")){
				mwCmCompanyExample.or().andAPartnerNameLike("%"+partner_name+"%");
			}
			if(franchise_name != null && !franchise_name.equals("")){
				mwCmCompanyExample.or().andAStoreNameLike("%"+franchise_name+"%");
			}
			if(store_name != null && !store_name.equals("")){
				mwCmCompanyExample.or().andACompNameLike("%"+store_name+"%");
			}
			
			if(Sdate != null&&!Sdate.equals("")&&Edate != null&&!Edate.equals("")){
				if(DateType.equals("01")){
					try{
						mwCmCompanyExample.or().
						andARegDtmBetween(DateTime.parse(Sdate, "yyyy-MM-dd"), new Date(DateTime.parse(Edate, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24))); 
					}catch (Exception e) {
					}					
				}else if(DateType.equals("02")){
					try{
						mwCmCompanyExample.or().
						andAChgDtmBetween(DateTime.parse(Sdate, "yyyy-MM-dd"), new Date(DateTime.parse(Edate, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24)));
					}catch (Exception e) {
					}
				}
			}
			log.info(Sdate);
			log.info(Edate);
			
			MwCmCompanyList = mwCmCompanyService.getByMemberStoreExampleOnly(mwCmCompanyExample,page,row);
			count = mwCmCompanyService.getMemberStoreSelectByCount(mwCmCompanyExample);
			/******* paging start *********/
			Paging pageing = new Paging();
			pageing.makeWebPaging(page, count, row);
			request.setAttribute("nowPage", row);
			request.setAttribute("paging", pageing.getSb());
			request.setAttribute("rowsPerPage", rowsPerPage);
			/******* paging end *********/
			
			//검색 데이타
			request.setAttribute("partner_name", partner_name);
			request.setAttribute("franchise_name", franchise_name);
			request.setAttribute("store_name", store_name);
			request.setAttribute("s_Sdate", Sdate);
			request.setAttribute("s_Edate", Edate);
			request.setAttribute("s_DateType", DateType);
			
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", page);
			request.setAttribute("rows", row);
			
			request.setAttribute("pagesPerPage", row);
			request.setAttribute("mwCmCompanyList", MwCmCompanyList);
			request.setAttribute("storeTerm", StoreTerm);

			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/member_store_list.ms");
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

			return "member/member_store_list";
	}
		
	/**
	 *가맹점 일괄등록
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_detail"
	 */
	
	@RequestMapping(value="/member/member_store_multy_add.ms")
	public String MemberStoreMultyRegister(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
				
		return "member/member_store_multy_register";
	}
	
	/**
	 *가맹점 일괄등록 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_detail"
	 */
	
	@RequestMapping(value="/member/member_store_multy_up.ms")
	public String MemberStoreMultyUp(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> params = new HashMap<String,Object>();

		String result = "1";
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwMsStarPayMentService mwMsStarPayMentService = new MwMsStarPayMentService();
		
		String partner_id = checkStr(request.getParameter("partner_id"), null);
		String partner_name = checkStr(request.getParameter("partner_name"), null);
		String franchise_id = checkStr(request.getParameter("franchise_name"), null);
		String membId = checkStr(request.getParameter("membId"), null);
//		String comp_level_type = checkStr(request.getParameter("comp_level_type"),"03");

		  params.put("partner_id", partner_id);
		  params.put("partner_name", partner_name);
		  params.put("franchise_id", franchise_id);
		  params.put("membId", membId);
		  
		log.info("partner_name : " + partner_name);
		log.info("franchise_id : " + franchise_id);
		
		String ComplexYn = "";
		String allyStat = "";
		String compType = "";
		
		String ollehUseYn="";
		String ollehDcYn="";
		String ollehSaveYn="";

		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
		MwMsStarPayMentExample mwMsStarPayMentExample = new MwMsStarPayMentExample();
		try{
			mwCmCompanyExample.or().andCompIdEqualTo(franchise_id);
			MwCmCompany getByComplexYn = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
			 mwCmCompanyExample = new MwCmCompanyExample();
			 mwMsStarPayMentExample = new MwMsStarPayMentExample();
			 System.out.println("CompId : "+getByComplexYn.getUpperCompId());
			 mwCmCompanyExample.or().andCompIdEqualTo(getByComplexYn.getUpperCompId());
			 getByComplexYn = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
			MwMsStarPayMent getByOllehUseYn = mwMsStarPayMentService.getByExampleOnly(mwMsStarPayMentExample);
			ComplexYn = getByComplexYn.getComplexPaymentYn();
			allyStat = getByComplexYn.getAllyStat(); //@@
			compType = getByComplexYn.getCompType(); //@@
			
			ollehDcYn = getByOllehUseYn.getStarPointDc();
			ollehUseYn = getByOllehUseYn.getStarPointUse();
			ollehSaveYn = getByOllehUseYn.getStarPointSave();
			
			
			
			System.out.println("ComplexYn : " + ComplexYn); //##
			System.out.println("allyStat : " + allyStat); //##
			System.out.println("compType : " + compType); //##
			System.out.println("ollehUseYn : " + ollehUseYn); //##
			System.out.println("ollehDcYn : " + ollehDcYn); //##
			System.out.println("ollehSaveYn : " + ollehSaveYn); //##
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			ComplexYn = "N";
		}
		
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlist membCardlist = membCardlistService.getByKey(membId);
		String UpperMembId = membCardlist.getUpperMembId();
		
		
		UpFile upFile = null;
		try{
			upFile = new UpFile(request, "/excelfile/");//파일 업로드
			/* 
			 * */
			ExcelContoller excels = new ExcelContoller(upFile.getFiles(),11);//엑셀 파일 파싱
			excels.setSheet(0);//엑셀파일이 있는 시트
			
/*			엑셀파일 배열 순서
			str[0]번: 결과코드
			str[1]번: NO
			str[2]번: 가맹점명
			str[3]번: 제휴사 가맹점ID
			str[4]번: 우편번호
			str[5]번: 시,도
			str[6]번: 군,구
			str[7]번: 읍,면,동
			str[8]번: 리,건물명
			str[9]번: 번지
			str[10]번: 전화번호
			str[11]번: 별 포인트 가맹점 ID*/
			
			ArrayList<String> StoreIds = new ArrayList<String>();
			if(excels.ChkTitle("제휴사명_가맹점 정보")){//문서의 타이틀명 체크
				for(int i=0;true;i++){
					/*
					 * excels.getString("가지고 올 라인");
					 * 결과 Str 중 str[0]은 결과 코드를 의미한다. true/false
					 * str[1]~str[n] 문서에 있는 항목의 순서대로 가져온다.
					 * 
					 * 엑셀 내부의 컬럼 추가로 인하여 [4][5][6]번이었던 address를 한자리씩 미룸
					 * 제휴사 가맹점 ID는 엑셀 내부의 [4]번 컬럼으로 할당 됨
					 * */
					String[] str = excels.getString(i); 
					if(str[0].equals("false")){
						break;
					}
					//DB에 넣기 동작및 주소 Gps가져오기=======================================
					 String Address = str[5] + "" + str[6] + "" + str[7];
					 String AddressDetail = str[8] + "" + str[9];
					 
					 //Gps가져오기
					 gpsAddressGetter GpsAddressGetter = new gpsAddressGetter(Address+" "+AddressDetail);
					 String GpsX = GpsAddressGetter.getGpsX();
					 String GpsY = GpsAddressGetter.getGpsY();
					 
	//				mwCmCompanyExample = new MwCmCompanyExample();
	//				mwCmCompanyExample.or().andCompIdEqualTo(franchise_id);
	//				MwCmCompany getIdList = mwCmCompanyService.getByShopIdExampleOnly(mwCmCompanyExample);
	//				String ShopId = getIdList.getCompId();
					String ShopId = "SHP" + DateTime.format("HHmmssSSS");
					
					MwCmCompany mwCmCompany = new MwCmCompany();
					mwCmCompany.setCompId(ShopId);
					mwCmCompany.setUpperCompId(franchise_id);
					mwCmCompany.setAddr(Address);
					mwCmCompany.setAddrDetail(AddressDetail);
					mwCmCompany.setRegDtm(new Date());
					mwCmCompany.setRegUser(getSessionMgrId(request));
					mwCmCompany.setCompName(str[2]);
					mwCmCompany.setZipcode(str[4]);
					mwCmCompany.setCompShopId(str[3]);
					mwCmCompany.setMidShopId(str[11]);
					
	//				mwCmCompany.setPhoneNo(str[9]);		
					
					/*##################### 암호화 S #####################*/
					mwCmCompany.setPhoneNo(ktService.encoding(str[10]));
					/*##################### 암호화 E #####################*/
					
					mwCmCompany.setGpsX(GpsX);
					mwCmCompany.setGpsY(GpsY);
					mwCmCompany.setComplexPaymentYn(ComplexYn);
					mwCmCompany.setCompLevelType("03"); //-- 01:제휴사, 02:브랜드, 03:가맹점, 10:영업대행사
					mwCmCompany.setMembId(membId); //-- 멤버십ID
					mwCmCompany.setAllyStat(allyStat);
					mwCmCompany.setCompType(compType);
					
					
					if(str[5].indexOf("서울특별시")>=0 || str[5].indexOf("서울")>=0){
						mwCmCompany.setRegionType("01");
					}
					else if(str[5].indexOf("부산광역시")>=0 || str[5].indexOf("부산")>=0){
						mwCmCompany.setRegionType("02");
					}
					else if(str[5].indexOf("대구광역시")>=0 || str[5].indexOf("대구")>=0){
						mwCmCompany.setRegionType("03");
					}
					else if(str[5].indexOf("인천광역시")>=0 || str[5].indexOf("인천")>=0){
						mwCmCompany.setRegionType("04");
					}
					else if(str[5].indexOf("광주광역시")>=0 || str[5].indexOf("광주")>=0){
						mwCmCompany.setRegionType("05");
					}
					else if(str[5].indexOf("대전광역시")>=0 || str[5].indexOf("대전")>=0){
						mwCmCompany.setRegionType("06");
					}
					else if(str[5].indexOf("울산광역시")>=0 || str[5].indexOf("울산")>=0){
						mwCmCompany.setRegionType("07");
					}
					else if(str[5].indexOf("세종특별자치시")>=0 || str[5].indexOf("세종")>=0){
						mwCmCompany.setRegionType("08");
					}
					else if(str[5].indexOf("경기도")>=0 || str[5].indexOf("경기")>=0){
						mwCmCompany.setRegionType("09");
					}
					else if(str[5].indexOf("강원도")>=0 || str[5].indexOf("강원")>=0){
						mwCmCompany.setRegionType("10");
					}
					else if(str[5].indexOf("충청북도")>=0 || str[5].indexOf("충북")>=0){
						mwCmCompany.setRegionType("11");
					}
					else if(str[5].indexOf("충청남도")>=0 || str[5].indexOf("충남")>=0){
						mwCmCompany.setRegionType("12");
					}
					else if(str[5].indexOf("전라북도")>=0 || str[5].indexOf("전북")>=0){
						mwCmCompany.setRegionType("13");
					}
					else if(str[5].indexOf("전라남도")>=0 || str[5].indexOf("전남")>=0){
						mwCmCompany.setRegionType("14");
					}
					else if(str[5].indexOf("경상북도")>=0 || str[5].indexOf("경북")>=0){
						mwCmCompany.setRegionType("15");
					}
					else if(str[5].indexOf("경상남도")>=0 || str[5].indexOf("경남")>=0){
						mwCmCompany.setRegionType("16");
					}
					else if(str[5].indexOf("제주")>=0 || str[5].indexOf("제주특별자치도")>=0){
						mwCmCompany.setRegionType("17");
					}

					
					System.out.println(mwCmCompanyService.StoreInsert(mwCmCompany));
					
					StoreIds.add(ShopId);
	
					//==================정보확인=====================
					//		   log.info("No : "+str[1]);
					//		   log.info("가맹점명 : "+str[2]);
					//		   log.info("우편번호 : "+str[3]);
					//		   log.info("시,도 : "+str[4]);
					//		   log.info("군,구 : "+str[5]);
					//		   log.info("읍,면,동 : "+str[6]);
					//		   log.info("리,건물명 : "+str[7]);
					//		   log.info("번지 : "+str[8]);
					//		   log.info("전화번호 : "+str[9]);
					//=============================================
					
				}
					
				mwCmCompanyService.commit();
			}else{
				upFile.getFiles().delete();//실패시 파일 삭제
				result = "0";
			}
		
			//가맹점 등록시 복합결제 연동 모듈
			if(ComplexYn.equals("Y")){
				if(UpperMembId==null||UpperMembId.equals("")){
					//복합결제 연동
					UpdateStore updating = new UpdateStore();
					if(!updating.update(StoreIds, franchise_id, ollehUseYn, ollehDcYn,ollehSaveYn,  "I")){
						mwCmCompanyService.rollback();
						
						request.setAttribute("actResult", "0");
						request.setAttribute("targetUrl", "/member/member_store_multy_add.ms");
						return "common/result_message";
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			log.info("파일이 없습니다.");
			upFile.getFiles().delete();//실패시 파일 삭제
//			mwCmCompanyService.rollback();
			result = "0";
		}
		
		request.setAttribute("actResult", result + "");

		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/member_store_multy_up.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

		
		if(result.equals("1")){
			request.setAttribute("targetUrl", "/member/member_store_list.ms");
		}
		else{
			request.setAttribute("targetUrl", "/member/member_store_multy_add.ms");
		}
		return "common/result_message";
	}
	
	
	/**
	 *가맹점 등록
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_detail"
	 */
	
	@RequestMapping(value="/member/member_store_add.ms")
	public String MemberStoreRegister(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		List<MwCmCompany> PartnerList = null;
		List<MwCmCompany> FranchiseList = null;
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
		mwCmCompanyExample.or().andCompIdLike("CMP%");		
		PartnerList = mwCmCompanyService.getByExample(mwCmCompanyExample);
		
		mwCmCompanyExample = new MwCmCompanyExample();
		mwCmCompanyExample.or().andCompIdLike("BRD%");		
		FranchiseList = mwCmCompanyService.getByExample(mwCmCompanyExample);
		
		String regDay = checkStr(request.getParameter("regDay"), "");
		String regUser = checkStr(request.getParameter("regUser"), "");

		MwCmCompany mwCmCompany= new MwCmCompany();
		if(!regDay.equals("")){
			//날짜 형식으로 변화하는 부분
			Date dateRegDay = DateTime.parseDate(regDay,"yyyy-MM-dd");
			if(!regDay.equals("")){mwCmCompany.setRegDtm(dateRegDay);}
		}
		
		//날짜 형식을 문자열로 변화하는 부분
		request.setAttribute("regDtm", DateTime.format("yyyy-MM-dd"));
		request.setAttribute("regUser", getSessionMgrId(request));

		
		request.setAttribute("PartnerList", PartnerList);//제휴사 리스트
		request.setAttribute("FranchiseList", FranchiseList);//브랜드 리스트
		
		return "member/member_store_register";
	}
	
	/**
	 *가맹점 업로드 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_detail"
	 */
	
	@RequestMapping(value="/member/member_store_up.ms")
	public String MemberStoreUplode(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String result = "";
		String store_name = checkStr(request.getParameter("store_name"), "");
		String franchise_id = checkStr(request.getParameter("franchise_name"), "");
		String partner_num = checkStr(request.getParameter("partner_num"),"");
		String partner_id = checkStr(request.getParameter("compId"),"");//제휴사 아이디
		String partner_name = checkStr(request.getParameter("compName"),"");//제휴사 이름
		String partner_zipcode = checkStr(request.getParameter("partner_zipcode"),"");
		String partner_address1 = checkStr(request.getParameter("partner_address1"),"");
		String partner_address2 = checkStr(request.getParameter("partner_address2"),"");
		String partner_p_phone = checkStr(request.getParameter("partner_p_phone"),"");
		String partner_d_name = checkStr(request.getParameter("partner_d_name"),"");
		String partner_d_number = checkStr(request.getParameter("partner_d_number"),"");
		String partner_d_email = checkStr(request.getParameter("partner_d_email"),"");
//		String partner_d_email2 = checkStr(request.getParameter("partner_d_email2"),"");
		String partner_d_phone = checkStr(request.getParameter("partner_d_phone"),"");
		String partner_memo = checkStr(request.getParameter("memo"),"");
		String membId = checkStr(request.getParameter("membId"),"");
		String store_id = checkStr(request.getParameter("store_id"),"");
		String complex_yn = checkStr(request.getParameter("complex_yn"),"");
		String comp_store_id = checkStr(request.getParameter("comp_store_id"),"");
		String partner_stat = checkStr(request.getParameter("partner_stat"), "");
		String midShopId = checkStr(request.getParameter("midShopId"),"");
		
		String LimitAmountDay = checkStr(request.getParameter("limitAmountDay"),"0");
		String LimitAmountWeek = checkStr(request.getParameter("limitAmountWeek"),"0");
		String LimitAmountMonth = checkStr(request.getParameter("limitAmountMonth"),"0");
		String LimitAmountYear = checkStr(request.getParameter("limitAmountYear"),"0");

		String moveId = checkStr(request.getParameter("moveId"),"");
		
		  params.put("store_name", store_name);
		  params.put("franchise_id", franchise_id);
		  params.put("partner_num", partner_num);
		  params.put("partner_id", partner_id);
		  params.put("partner_name", partner_name);
		  params.put("partner_zipcode", partner_zipcode);
		  params.put("partner_address1", partner_address1);
		  params.put("partner_address2", partner_address2);
//		  params.put("partner_p_phone", partner_p_phone);
//		  params.put("partner_d_name", partner_d_name);
//		  params.put("partner_d_number", partner_d_number);
//		  params.put("partner_d_email", partner_d_email);
//		  params.put("partner_d_email2", partner_d_email2);
//		  params.put("partner_d_phone", partner_d_phone);
		  params.put("partner_memo", partner_memo);
		  params.put("membId", membId);
		  params.put("store_id", store_id);
		  params.put("complex_yn", complex_yn);
		  params.put("comp_store_id", comp_store_id);
		  params.put("partner_stat", partner_stat);
		  params.put("LimitAmountDay", LimitAmountDay);
		  params.put("LimitAmountWeek", LimitAmountWeek);
		  params.put("LimitAmountMonth", LimitAmountMonth);
		  params.put("LimitAmountYear", LimitAmountYear);
		  params.put("moveId", moveId);
//		  params.put("Emails", Emails);
		  params.put("midShopId", midShopId);
		  /*##################### 암호화 S #####################*/
		  params.put("partner_p_phone", ktService.encoding(partner_p_phone));
		  params.put("partner_d_name", ktService.encoding(partner_d_name));
		  params.put("partner_d_number", ktService.encoding(partner_d_number));
		  params.put("partner_d_email", ktService.encoding(partner_d_email));
//		  params.put("partner_d_email2", ktService.encoding(partner_d_email2));
		  params.put("partner_d_phone", ktService.encoding(partner_d_phone));
		  /*##################### 암호화 E #####################*/
		  
		  /*##################### 암호화 S #####################*/
			partner_p_phone = ktService.encoding(partner_p_phone);
			partner_d_name = ktService.encoding(partner_d_name);
			partner_d_number = ktService.encoding(partner_d_number);
			partner_d_email = ktService.encoding(partner_d_email);
//			String partner_d_email2 = ktService.encoding(checkStr(request.getParameter("partner_d_email2"),""));
			partner_d_phone = ktService.encoding(partner_d_phone);
			/*##################### 암호화 E #####################*/

			System.out.println("partner_p_phone - " + partner_p_phone);
			System.out.println("partner_d_name - " + partner_d_name);
			System.out.println("partner_d_number - " + partner_d_number);
			System.out.println("partner_d_email - " + partner_d_email);
		  
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
		
		MwMsStarPayMentService mwStarPayMentService = new MwMsStarPayMentService();
		MwMsStarPayMentExample mwMsStarPayMentExample = new MwMsStarPayMentExample();
		
		mwCmCompanyExample.or().andCompIdEqualTo(partner_id);
		MwCmCompany getMwCmCompany = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
		
		complex_yn = getMwCmCompany.getComplexPaymentYn();
		String AllyStat = getMwCmCompany.getAllyStat();
		String CompType = getMwCmCompany.getCompType();
		
		String ShopId = "SHP" + DateTime.format("HHmmssSSS");
		
		 //Gps가져오기
		 gpsAddressGetter GpsAddressGetter = new gpsAddressGetter(partner_address1+" "+partner_address2);
		 String GpsX = GpsAddressGetter.getGpsX();
		 String GpsY = GpsAddressGetter.getGpsY();
		
		MwMsStarPayMent getByOllehUseYn = mwStarPayMentService.getByExampleOnly(mwMsStarPayMentExample);
		String ollehUseYn=getByOllehUseYn.getStarPointUse();
		String ollehDcYn=getByOllehUseYn.getStarPointDc();
		String ollehSaveYn=getByOllehUseYn.getStarPointSave();

		 try{
			 
			 {
			 MwCmCompany mwCmCompany = new MwCmCompany();
			 mwCmCompany.setGpsX(GpsX);
			 mwCmCompany.setGpsY(GpsY);
			 
			 if(partner_address1.indexOf("서울특별시")>=0 || partner_address1.indexOf("서울")>=0){
					mwCmCompany.setRegionType("01");
				}
				else if(partner_address1.indexOf("부산광역시")>=0 || partner_address1.indexOf("부산")>=0){
					mwCmCompany.setRegionType("02");
				}
				else if(partner_address1.indexOf("대구광역시")>=0 || partner_address1.indexOf("대구")>=0){
					mwCmCompany.setRegionType("03");
				}
				else if(partner_address1.indexOf("인천광역시")>=0 || partner_address1.indexOf("인천")>=0){
					mwCmCompany.setRegionType("04");
				}
				else if(partner_address1.indexOf("광주광역시")>=0 || partner_address1.indexOf("광주")>=0){
					mwCmCompany.setRegionType("05");
				}
				else if(partner_address1.indexOf("대전광역시")>=0 || partner_address1.indexOf("대전")>=0){
					mwCmCompany.setRegionType("06");
				}
				else if(partner_address1.indexOf("울산광역시")>=0 || partner_address1.indexOf("울산")>=0){
					mwCmCompany.setRegionType("07");
				}
				else if(partner_address1.indexOf("세종특별자치시")>=0 || partner_address1.indexOf("세종")>=0){
					mwCmCompany.setRegionType("08");
				}
				else if(partner_address1.indexOf("경기도")>=0 || partner_address1.indexOf("경기")>=0){
					mwCmCompany.setRegionType("09");
				}
				else if(partner_address1.indexOf("강원도")>=0 || partner_address1.indexOf("강원")>=0){
					mwCmCompany.setRegionType("10");
				}
				else if(partner_address1.indexOf("충청북도")>=0 || partner_address1.indexOf("충북")>=0){
					mwCmCompany.setRegionType("11");
				}
				else if(partner_address1.indexOf("충청남도")>=0 || partner_address1.indexOf("충남")>=0){
					mwCmCompany.setRegionType("12");
				}
				else if(partner_address1.indexOf("전라북도")>=0 || partner_address1.indexOf("전북")>=0){
					mwCmCompany.setRegionType("13");
				}
				else if(partner_address1.indexOf("전라남도")>=0 || partner_address1.indexOf("전남")>=0){
					mwCmCompany.setRegionType("14");
				}
				else if(partner_address1.indexOf("경상북도")>=0 || partner_address1.indexOf("경북")>=0){
					mwCmCompany.setRegionType("15");
				}
				else if(partner_address1.indexOf("경상남도")>=0 || partner_address1.indexOf("경남")>=0){
					mwCmCompany.setRegionType("16");
				}
				else if(partner_address1.indexOf("제주")>=0 || partner_address1.indexOf("제주특별자치도")>=0){
					mwCmCompany.setRegionType("17");
				}
			 
		  	if(!ShopId.equals(""))mwCmCompany.setCompId(ShopId);
				if(!franchise_id.equals(""))mwCmCompany.setUpperCompId(franchise_id);
				if(!partner_address1.equals(""))mwCmCompany.setAddr(partner_address1);
				if(!partner_address2.equals(""))mwCmCompany.setAddrDetail(partner_address2);
				mwCmCompany.setRegDtm(new Date());
				mwCmCompany.setRegUser(getSessionMgrId(request));
				if(!store_name.equals(""))mwCmCompany.setCompName(store_name);
				if(!partner_d_email.equals(""))mwCmCompany.setEmail(partner_d_email);
				if(!partner_zipcode.equals(""))mwCmCompany.setZipcode(partner_zipcode);
				if(!partner_p_phone.equals(""))mwCmCompany.setMainNumber(partner_p_phone);
				if(!partner_memo.equals(""))mwCmCompany.setMemo(partner_memo);
				if(!partner_d_phone.equals(""))mwCmCompany.setMobilePhone(partner_d_phone);
				if(!partner_d_number.equals(""))mwCmCompany.setPhoneNo(partner_d_number);
				if(!partner_d_name.equals(""))mwCmCompany.setManagerName(partner_d_name);
				if(!partner_num.equals(""))mwCmCompany.setBusinessNo(partner_num);
				if(!complex_yn.equals(""))mwCmCompany.setComplexPaymentYn(complex_yn);
				if(!membId.equals(""))mwCmCompany.setMembId(membId);
				if(!moveId.equals(""))mwCmCompany.setMoveId(moveId);
				if(!comp_store_id.equals(""))mwCmCompany.setCompShopId(comp_store_id);
				if(!partner_stat.equals(""))mwCmCompany.setAllyStat(partner_stat);
				if(!midShopId.equals(""))mwCmCompany.setMidShopId(midShopId);
				mwCmCompany.setAllyStat(AllyStat);
				mwCmCompany.setCompType(CompType);
				
				mwCmCompany.setLimitAmountDay(Integer.parseInt(LimitAmountDay));
				mwCmCompany.setLimitAmountMonth(Integer.parseInt(LimitAmountMonth));
				mwCmCompany.setLimitAmountWeek(Integer.parseInt(LimitAmountWeek));
				mwCmCompany.setLimitAmountYear(Integer.parseInt(LimitAmountYear));
				mwCmCompany.setCompLevelType("03");

				mwCmCompanyService.insert(mwCmCompany);				
				mwCmCompanyService.commit();
			 }
	
				/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
				params.put("pageURL", "/member/member_store_up.ms");
			
				params.put("part", "MEMBERSHIP");
				params.put("admin_id", getSessionMgrId(request));
				params.put("ip", request.getRemoteAddr());
				params.put("msg", params.toString());
				
				logSVC.insertAccessLogReg(params);
				logSVC.commit();
				/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

				MembCardlistService membCardlistService = new MembCardlistService();
				
				//가맹점 등록시 복합결제 연동 
				 if(complex_yn.equals("Y")){
					 MembCardlist membCardlist = membCardlistService.getByKey(membId);
					 String UpperMembId = membCardlist.getUpperMembId();
					 
					 if(UpperMembId==null||UpperMembId.equals("")){
						 
						 UpdateStore updating = new UpdateStore();
						 if(!updating.update(ShopId, franchise_id,  ollehUseYn, ollehDcYn, ollehSaveYn,  "I")){
								mwCmCompanyService.rollback();
								JsonErrMsg("err",response);
								return null;
							}
					}
				}
				
		  }catch (Exception e) {
			// TODO: handle exception
			 mwCmCompanyService.rollback();
		  }
		 JsonErrMsg(result,response);
		 return null;
	}
	
	
	/**
	 *제휴사 수정
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/member_partner_edit"
	 */
	
	@RequestMapping(value="/member/member_store_edit.ms")
	public String MemberPartnerInfo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String partnerID = checkStr(request.getParameter("partnerID"), null);
		log.info("partnerID : " + partnerID);
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();

		  params.put("partnerID", partnerID);

		if(partnerID != null && !partnerID.equals("")){
			mwCmCompanyExample.or().andACompIdEqualTo(partnerID);		
			MwCmCompany mwCmCompany = mwCmCompanyService.getByStoreExampleOnly(mwCmCompanyExample);
			
			String Email = mwCmCompany.getEmail();
			
			String RegDtm="";
			String ChgDtm = "";
			try{
				RegDtm = DateTime.format(mwCmCompany.getRegDtm(), "yyyy-MM-dd");
				ChgDtm = DateTime.format(mwCmCompany.getChgDtm(), "yyyy-MM-dd");
			}catch (Exception e) {
				// TODO: handle exception
				log.info("날짜 정보가 없습니다.");
			}
		
		request.setAttribute("compId", mwCmCompany.getCompId());//가맹점 아이디
		request.setAttribute("compName", mwCmCompany.getCompName());//가맹점 명
		request.setAttribute("partner_compName", mwCmCompany.getStorePartnerName());//제휴사 이름
		request.setAttribute("franchise_compName", mwCmCompany.getStoreFranchiseName());//브랜드 이름
		request.setAttribute("businessNo", mwCmCompany.getBusinessNo());//사업자 등록번호
		request.setAttribute("zipcode", mwCmCompany.getZipcode());//제휴사 우편번호
		request.setAttribute("addr", mwCmCompany.getAddr());//주소1
		request.setAttribute("addrDetail", mwCmCompany.getAddrDetail());//주소2
//		request.setAttribute("mainNumber", mwCmCompany.getMainNumber());//제휴사 대표 연락처
//		request.setAttribute("name", mwCmCompany.getManagerName());//담당자 
//		request.setAttribute("phoneNo", mwCmCompany.getPhoneNo());//일반전화
//		request.setAttribute("mobilePhone", mwCmCompany.getMobilePhone());//휴대폰 번호
//		request.setAttribute("email1", Email1);//이메일1
//		request.setAttribute("email2", Email2);//이메일2
		request.setAttribute("allyStat", mwCmCompany.getAllyStat());//운영상태
		request.setAttribute("memo", mwCmCompany.getMemo());//메모
		request.setAttribute("regDtm", RegDtm);//등록일
		request.setAttribute("regUser", mwCmCompany.getRegUser());//등록자
		request.setAttribute("chgDtm", ChgDtm);//수정일
		request.setAttribute("chgUser", mwCmCompany.getChgUser());//수정자
		request.setAttribute("complex_yn", mwCmCompany.getComplexPaymentYn());//수정자
		request.setAttribute("membId", mwCmCompany.getMembId()); //멤버십 아이디
		request.setAttribute("membName", mwCmCompany.getMembName()); //멤버십 명
		request.setAttribute("comp_store_id", mwCmCompany.getCompShopId()); //제휴사 가맹점 아이디
		request.setAttribute("midShopId", mwCmCompany.getMidShopId());//별 포인트 가맹점 ID
		/*##################### 복호화 S #####################*/
		request.setAttribute("mainNumber", ktService.decoding(mwCmCompany.getMainNumber()));//제휴사 대표 연락처
		request.setAttribute("name", ktService.decoding(mwCmCompany.getManagerName()));//담당자 
		request.setAttribute("phoneNo", ktService.decoding(mwCmCompany.getPhoneNo()));//일반전화
		request.setAttribute("mobilePhone", ktService.decoding(mwCmCompany.getMobilePhone()));//휴대폰 번호
		request.setAttribute("email1", ktService.decoding(Email));//이메일1
		/*##################### 복호화 E #####################*/
		
		int LimitAmountDay =  NullNumberCheck(mwCmCompany.getLimitAmountDay());
		int LimitAmountWeek = NullNumberCheck(mwCmCompany.getLimitAmountWeek());
		int LimitAmountMonth = NullNumberCheck(mwCmCompany.getLimitAmountMonth());
		int LimitAmountYear = NullNumberCheck(mwCmCompany.getLimitAmountYear());
		
		request.setAttribute("limitAmountDay",LimitAmountDay);//일별
		request.setAttribute("limitAmountWeek", LimitAmountWeek);//주별
		request.setAttribute("limitAmountMonth", LimitAmountMonth);//월별
		request.setAttribute("limitAmountYear", LimitAmountYear);//연별		
		
		String LimitAmountDayYn = "N";
		String LimitAmountWeekYn = "N";
		String LimitAmountMonthYn = "N";
		String LimitAmountYeaYnr = "N";
		
		if(LimitAmountDay>0)LimitAmountDayYn="Y";
		if(LimitAmountWeek>0)LimitAmountWeekYn="Y";		
		if(LimitAmountMonth>0)LimitAmountMonthYn="Y";
		if(LimitAmountYear>0)LimitAmountYeaYnr="Y";
		
		request.setAttribute("limitAmountDayYn", LimitAmountDayYn);//일별
		request.setAttribute("limitAmountWeekYn", LimitAmountWeekYn);//주별
		request.setAttribute("limitAmountMonthYn", LimitAmountMonthYn);//월별
		request.setAttribute("limitAmountYearYn", LimitAmountYeaYnr);//연별
		
		log.info(LimitAmountDay);
		log.info(LimitAmountWeek);
		log.info(LimitAmountMonth);
		log.info(LimitAmountYear);

		log.info(LimitAmountDayYn);
		log.info(LimitAmountWeekYn);
		log.info(LimitAmountMonthYn);
		log.info(LimitAmountYeaYnr);
}

		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/member_store_edit.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

			return "member/member_store_edit";	
	}
	
	/**
	 *제휴사 수정 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_list_popup"
	 */
	
	@RequestMapping(value="/member/member_store_edit_up.ms")
	public String MemberPartnerEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, Object> params = new HashMap<String,Object>();

		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
		
		MwMsStarPayMentService mwMsStarPayMentService = new MwMsStarPayMentService();
		MwMsStarPayMentExample mwMsStarPayMentExample = new MwMsStarPayMentExample();
		
		String result = "";
		String store_name = checkStr(request.getParameter("store_name"), "");
		String partner_zipcode = checkStr(request.getParameter("partner_zipcode"),"");
		String partner_address1 = checkStr(request.getParameter("partner_address1"),"");
		String partner_address2 = checkStr(request.getParameter("partner_address2"),"");
//		String partner_p_phone = checkStr(request.getParameter("partner_p_phone"),"");
//		String partner_d_name = checkStr(request.getParameter("partner_d_name"),"");
//		String partner_d_number = checkStr(request.getParameter("partner_d_number"),"");
//		String partner_d_email = checkStr(request.getParameter("partner_d_email"),"");
//		String partner_d_email2 = checkStr(request.getParameter("partner_d_email2"),"");
//		String partner_d_phone = checkStr(request.getParameter("partner_d_phone"),"");
		String partner_memo = checkStr(request.getParameter("memo"),"");
		String store_id = checkStr(request.getParameter("store_id"),"");
		String complex_yn = checkStr(request.getParameter("complex_yn"),"");
		String LimitAmountDay = checkStr(request.getParameter("limitAmountDay"),"0");
		String LimitAmountWeek = checkStr(request.getParameter("limitAmountWeek"),"0");
		String LimitAmountMonth = checkStr(request.getParameter("limitAmountMonth"),"0");
		String LimitAmountYear = checkStr(request.getParameter("limitAmountYear"),"0");
		String comp_store_id = checkStr(request.getParameter("comp_store_id"),"");
		String partner_stat = checkStr(request.getParameter("partner_stat"), "");
		String partner_num = checkStr(request.getParameter("partner_num"), "");
		String midShopId = checkStr(request.getParameter("midShopId"),"");
		/*##################### 암호화 S #####################*/
		String partner_p_phone = ktService.encoding(checkStr(request.getParameter("partner_p_phone"),""));
		String partner_d_name = ktService.encoding(checkStr(request.getParameter("partner_d_name"),""));
		String partner_d_number = ktService.encoding(checkStr(request.getParameter("partner_d_number"),""));
		String partner_d_email = ktService.encoding(checkStr(request.getParameter("partner_d_email"),""));
//		String partner_d_email2 = ktService.encoding(checkStr(request.getParameter("partner_d_email2"),""));
		String partner_d_phone = ktService.encoding(checkStr(request.getParameter("partner_d_phone"),""));
		/*##################### 암호화 E #####################*/
		
		String LimitAmountDayYn = checkStr(request.getParameter("checkDay"),"N");
		String LimitAmountWeekYn = checkStr(request.getParameter("checkWeek"),"N");
		String LimitAmountMonthYn = checkStr(request.getParameter("checkMonth"),"N");
		String LimitAmountYearYn = checkStr(request.getParameter("checkYear"),"N");

		  params.put("store_name", store_name);
		  params.put("partner_zipcode", partner_zipcode);
		  params.put("partner_address1", partner_address1);
		  params.put("partner_address2", partner_address2);
		  params.put("partner_num", partner_num);
		  params.put("partner_p_phone", partner_p_phone);
		  params.put("partner_d_name", partner_d_name);
		  params.put("partner_d_number", partner_d_number);
		  params.put("partner_d_email", partner_d_email);
//		  params.put("partner_d_email2", partner_d_email2);
		  params.put("partner_d_phone", partner_d_phone);
		  params.put("partner_memo", partner_memo);
		  params.put("store_id", store_id);
		  params.put("complex_yn", complex_yn);
		  params.put("comp_store_id", comp_store_id);
		  params.put("partner_stat", partner_stat);
		  params.put("LimitAmountDay", LimitAmountDay);
		  params.put("LimitAmountWeek", LimitAmountWeek);
		  params.put("LimitAmountMonth", LimitAmountMonth);
		  params.put("LimitAmountYear", LimitAmountYear);
		  params.put("LimitAmountDayYn", LimitAmountDay);
		  params.put("LimitAmountWeekYn", LimitAmountWeek);
		  params.put("LimitAmountMonthYn", LimitAmountMonth);
		  params.put("LimitAmountYearYn", LimitAmountYear);
		  params.put("midShopId", midShopId);
		 
		  
		String Emails =  partner_d_email;
		log.info("store_id : " + store_id);
		if(store_id != null && !store_id.equals("")){
			mwCmCompanyExample.or().andCompIdEqualTo(store_id);
		try{
			MwCmCompany mwCmCompany = new MwCmCompany();
			mwCmCompany.setAddr(partner_address1);
			mwCmCompany.setAddrDetail(partner_address2);
			mwCmCompany.setChgDtm(new Date());
			mwCmCompany.setChgUser(getSessionMgrId(request));
			mwCmCompany.setCompName(store_name);
			mwCmCompany.setEmail(Emails);
			mwCmCompany.setZipcode(partner_zipcode);
			mwCmCompany.setMainNumber(partner_p_phone);
			mwCmCompany.setMemo(partner_memo);
			mwCmCompany.setMobilePhone(partner_d_phone);
			mwCmCompany.setPhoneNo(partner_d_number);
			mwCmCompany.setManagerName(partner_d_name);
			mwCmCompany.setComplexPaymentYn(complex_yn);
			mwCmCompany.setCompShopId(comp_store_id);
			mwCmCompany.setAllyStat(partner_stat);
			mwCmCompany.setBusinessNo(partner_num);
			mwCmCompany.setMidShopId(midShopId);
			if(partner_address1.indexOf("서울특별시")>=0 || partner_address1.indexOf("서울")>=0){
				mwCmCompany.setRegionType("01");
			}
			else if(partner_address1.indexOf("부산광역시")>=0 || partner_address1.indexOf("부산")>=0){
				mwCmCompany.setRegionType("02");
			}
			else if(partner_address1.indexOf("대구광역시")>=0 || partner_address1.indexOf("대구")>=0){
				mwCmCompany.setRegionType("03");
			}
			else if(partner_address1.indexOf("인천광역시")>=0 || partner_address1.indexOf("인천")>=0){
				mwCmCompany.setRegionType("04");
			}
			else if(partner_address1.indexOf("광주광역시")>=0 || partner_address1.indexOf("광주")>=0){
				mwCmCompany.setRegionType("05");
			}
			else if(partner_address1.indexOf("대전광역시")>=0 || partner_address1.indexOf("대전")>=0){
				mwCmCompany.setRegionType("06");
			}
			else if(partner_address1.indexOf("울산광역시")>=0 || partner_address1.indexOf("울산")>=0){
				mwCmCompany.setRegionType("07");
			}
			else if(partner_address1.indexOf("세종특별자치시")>=0 || partner_address1.indexOf("세종")>=0){
				mwCmCompany.setRegionType("08");
			}
			else if(partner_address1.indexOf("경기도")>=0 || partner_address1.indexOf("경기")>=0){
				mwCmCompany.setRegionType("09");
			}
			else if(partner_address1.indexOf("강원도")>=0 || partner_address1.indexOf("강원")>=0){
				mwCmCompany.setRegionType("10");
			}
			else if(partner_address1.indexOf("충청북도")>=0 || partner_address1.indexOf("충북")>=0){
				mwCmCompany.setRegionType("11");
			}
			else if(partner_address1.indexOf("충청남도")>=0 || partner_address1.indexOf("충남")>=0){
				mwCmCompany.setRegionType("12");
			}
			else if(partner_address1.indexOf("전라북도")>=0 || partner_address1.indexOf("전북")>=0){
				mwCmCompany.setRegionType("13");
			}
			else if(partner_address1.indexOf("전라남도")>=0 || partner_address1.indexOf("전남")>=0){
				mwCmCompany.setRegionType("14");
			}
			else if(partner_address1.indexOf("경상북도")>=0 || partner_address1.indexOf("경북")>=0){
				mwCmCompany.setRegionType("15");
			}
			else if(partner_address1.indexOf("경상남도")>=0 || partner_address1.indexOf("경남")>=0){
				mwCmCompany.setRegionType("16");
			}
			else if(partner_address1.indexOf("제주")>=0 || partner_address1.indexOf("제주특별자치도")>=0){
				mwCmCompany.setRegionType("17");
			}
			
			
			if(!LimitAmountDay.equals("")){
					mwCmCompany.setLimitAmountDay(Integer.parseInt(LimitAmountDay));
			}
			if(!LimitAmountMonth.equals("")){
					mwCmCompany.setLimitAmountMonth(Integer.parseInt(LimitAmountMonth));
			}
			if(!LimitAmountWeek.equals("")){
					mwCmCompany.setLimitAmountWeek(Integer.parseInt(LimitAmountWeek));
			}
			if(!LimitAmountYear.equals("")){
					mwCmCompany.setLimitAmountYear(Integer.parseInt(LimitAmountYear));
			}
			
			MwCmCompany getBraindId = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
			String Brand_Id = getBraindId.getUpperCompId();
			String OleComplexYn = getBraindId.getComplexPaymentYn();
			MwMsStarPayMent mwMsStarPayMent = new MwMsStarPayMent();
			
			if(midShopId.equals("")){
				mwMsStarPayMentExample.or().andCompIdEqualTo(store_id);
				mwMsStarPayMent.setStarPointDc("N");
				mwMsStarPayMent.setStarPointSave("N");
				mwMsStarPayMent.setStarPointUse("N");
				mwMsStarPayMent.setMidShopId("");
				mwMsStarPayMentService.update(mwMsStarPayMent, mwMsStarPayMentExample);
			}
			
			mwCmCompanyService.update(mwCmCompany, mwCmCompanyExample);
			

			mwCmCompanyService.commit();
			mwMsStarPayMentService.commit();
			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/member_store_edit_up.ms");
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

			UpdateStore updating = new UpdateStore();
			log.info("update OleComplexYn - " + OleComplexYn);
			log.info("update complex_yn - " + complex_yn);
			//Y에서 N으로 변경되는 경우
			MwMsStarPayMent getByOllehUseYn = mwMsStarPayMentService.getByExampleOnly(mwMsStarPayMentExample);
			String ollehUseYn=getByOllehUseYn.getStarPointUse();
			String ollehDcYn=getByOllehUseYn.getStarPointDc();
			String ollehSaveYn=getByOllehUseYn.getStarPointSave();

			
			
			if(OleComplexYn.equals("Y")&&complex_yn.equals("N")){
				
				mwCmCompanyExample = new MwCmCompanyExample();
				mwCmCompanyExample.or().andCompIdEqualTo(Brand_Id);
				MwCmCompany getPartnerId = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
				String Comp_Id = getPartnerId.getUpperCompId();
				
				MwMsCompPayMentExample mwMsCompPayMentExample = new MwMsCompPayMentExample();
				mwMsCompPayMentExample.or().andCompIdEqualTo(Comp_Id);
				mwMsCompPayMentExample.or().andCompIdEqualTo(store_id);
				MwMsCompPayMentService mwMsCompPayMentService = new MwMsCompPayMentService();
				int conts = mwMsCompPayMentService.getCountByExample(mwMsCompPayMentExample);
//				if(conts>0){
					if(!updating.update(store_id, Brand_Id,ollehUseYn,ollehDcYn,ollehSaveYn, "D")){
						mwCmCompanyService.rollback();
						mwMsCompPayMentService.rollback();
						JsonErrMsg("err",response);
						return null;
					}
//				}
			}
			//N에서 Y로 변경되는 경우
			else if(OleComplexYn.equals("N")&&complex_yn.equals("Y")){
				
				mwCmCompanyExample = new MwCmCompanyExample();
				mwCmCompanyExample.or().andCompIdEqualTo(Brand_Id);
				MwCmCompany getPartnerId = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
				String Comp_Id = getPartnerId.getUpperCompId();
				
				MwMsCompPayMentExample mwMsCompPayMentExample = new MwMsCompPayMentExample();
				mwMsCompPayMentExample.or().andCompIdEqualTo(Comp_Id);
				mwMsCompPayMentExample.or().andCompIdEqualTo(store_id);
				MwMsCompPayMentService mwMsCompPayMentService = new MwMsCompPayMentService();
				
				
				int conts = mwMsCompPayMentService.getCountByExample(mwMsCompPayMentExample);
//				if(conts>0){
					if(!updating.update(store_id, Brand_Id,ollehUseYn,ollehDcYn,ollehSaveYn, "I")){
						mwCmCompanyService.rollback();
						JsonErrMsg("err",response);
						return null;
					}
//				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			mwCmCompanyService.rollback();
			JsonErrMsg("err",response);
			return null;
		}
		
		}	
		JsonErrMsg("",response);
		return null;
	
	}
	
	/**
	 *가맹점 삭제
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return null
	 */
	
	@RequestMapping(value="/member/member_store_delete.ms")
	public String MemberPartnerDelete(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> params = new HashMap<String,Object>();

		String result = "";
		String store_id = checkStr(request.getParameter("store_id"),null);
		String Brand_Id = "";
			
		  MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		
		  try{
		  	
		  	MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();			
				mwCmCompanyExample.or().andCompIdEqualTo(store_id);			
				
				MwCmCompany mwCmCompany = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
				Brand_Id = mwCmCompany.getUpperCompId();
				String ComplexYn = mwCmCompany.getComplexPaymentYn();			
			
				if(store_id != null && !store_id.equals("")){
				
				mwCmCompanyExample = new MwCmCompanyExample();
				mwCmCompanyExample.or().andCompIdEqualTo(Brand_Id);
				MwCmCompany getPartnerId = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
				String Comp_Id = getPartnerId.getUpperCompId();
				
				MwMsCompPayMentExample mwMsCompPayMentExample = new MwMsCompPayMentExample();
				mwMsCompPayMentExample.or().andCompIdEqualTo(Comp_Id);
				mwMsCompPayMentExample.or().andCompIdEqualTo(store_id);
//				MwMsCompPayMentService mwMsCompPayMentService = new MwMsCompPayMentService();
//				int conts = mwMsCompPayMentService.getCountByExample(mwMsCompPayMentExample);
				
				MwMsStarPayMentService mwStarPayMentService = new MwMsStarPayMentService();
				MwMsStarPayMentExample mwMsStarPayMentExample = new MwMsStarPayMentExample();
				MwMsStarPayMent getByOllehUseYn = mwStarPayMentService.getByExampleOnly(mwMsStarPayMentExample);
				String ollehUseYn=getByOllehUseYn.getStarPointUse();
				String ollehDcYn=getByOllehUseYn.getStarPointDc();
				String ollehSaveYn=getByOllehUseYn.getStarPointSave();

			//가맹점이 삭제 되기 이전에 호출한다.
				if(ComplexYn.equals("Y")){
					UpdateStore updating = new UpdateStore();
					if(!updating.update(store_id, Brand_Id,ollehUseYn,ollehDcYn,ollehSaveYn, "D")){
						mwCmCompanyService.rollback();
						JsonErrMsg("err",response);
						return null;
					}
				}
				mwCmCompanyExample = new MwCmCompanyExample();
				mwCmCompanyExample.or().andCompIdEqualTo(store_id);
				
			mwCmCompanyService.delete(mwCmCompanyExample);
			mwCmCompanyService.commit();
			}			
			
		}catch (Exception e) {
			// TODO: handle exception		
			mwCmCompanyService.rollback();
			JsonErrMsg("err",response);
			return null;
		}

		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/member_store_delete.ms");
		params.put("store_id", store_id);
		params.put("result", result);
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

		JsonErrMsg(result,response);
		return null;
	}	
	
	/**
	 * 이메일 주소 앞뒤로 자르기
	 * @MAKEBY 이경훈
	 * */
	private String[] StrCut(String Str, String CutPoint){
		String[] cutString = new String[2];		
		StringTokenizer st = new StringTokenizer(Str,CutPoint);
		int count = 0;
		while(st.hasMoreTokens()){
			if(count>1)break;
			cutString[count] = st.nextToken();
			count++;
		}
		return cutString;
	}
	
	private int NullNumberCheck(Integer integer){
		int result = 0;
		try{
			result = integer;
		}catch (Exception e) {
			result = 0;
		}
		return result;
	}
	
	/**
	 * Ajax 결과 리턴
	 * @MakeBy 이경훈
	 * */
	private void JsonErrMsg(String Err, HttpServletResponse response){
		try{
			PrintWriter writer = response.getWriter();
			writer.write("{\"err\":\""+Err+"\"}");
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	


	
	
	/**
	 * @Method Name : ComplexStoreList
	 * @Description : 가맹점목록을 조회한다.(복합결제에서 이용하는 메소드)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @throws UnsupportedEncodingException 
	 * @since 2012.09.24
	 */
	@RequestMapping(value="/member/complex_store_select.ms")
	public String ComplexStoreList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response){
		List<Store> storeList = null;
		Member member = null;
		StoreService storeService = new StoreService();
		MemberService memberService = new MemberService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		String result = "";
		
		String compId = checkStr(request.getParameter("compId"), ""); //-- 제휴사 코드
		String compName = checkStr(request.getParameter("compName"), ""); 
		String branId = checkStr(request.getParameter("branSel"), ""); //-- 브랜드 코드
		String branName = checkStr(request.getParameter("branName")); //-- 브랜드 명
		String membId = checkStr(request.getParameter("membId"), ""); //-- 멤버십 코드
		String membNm = checkStr(request.getParameter("membNm"), ""); //-- 멤버십 명
		String storeName = checkStr(request.getParameter("storeName"), ""); //-- 가맹점 명
		
		
		params.put("compId", compId);
		params.put("branId", branId);
		params.put("membId", membId);
		params.put("storeName", storeName);
		
		member = memberService.selectAMember(params);
		membNm = member.getName();
		
		if(!"".equals(storeName)){
			storeList = storeService.selectStoreList(params); //-- 목록조회
		}
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("storeList", storeList);
		
		request.setAttribute("compId", compId);
		request.setAttribute("compName", compName);
		
		request.setAttribute("branId", branId);
		request.setAttribute("branSel", branId);
		request.setAttribute("branName", branName);
		
		request.setAttribute("membId", membId);
		request.setAttribute("membNm", membNm);
		
		request.setAttribute("storeName", storeName);

		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/complex_store_select.ms");
		
		params.put("result", result);
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

		return "member/complex_store_select";
	}
	
	/**
	 * @Method Name : getComplexStoreList
	 * @Description : 가맹점목록을 조회한다.(복합결제에서 이용하는 메소드)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @throws UnsupportedEncodingException 
	 * @since 2012.09.24
	 */
	@RequestMapping(value="/member/complex_store_select_ifr.ms")
	public String getComplexStoreList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response){
		List<Store> storeList = null;
		Member member = null;
		StoreService storeService = new StoreService();
		MemberService memberService = new MemberService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		String result = "";
		
		String compId = checkStr(request.getParameter("compId"), ""); //-- 제휴사 코드
		//String compName = checkStr(request.getParameter("compName"), ""); 
		String branId = checkStr(request.getParameter("branSel"), ""); //-- 브랜드 코드
		//String branName = checkStr(request.getParameter("branName")); //-- 브랜드 명
		String membId = checkStr(request.getParameter("membId"), ""); //-- 멤버십 코드
		//String membNm = checkStr(request.getParameter("membNm"), ""); //-- 멤버십 명
		String storeName = checkStr(request.getParameter("storeName"), ""); //-- 가맹점 명
		
		
		params.put("compId", compId);
		params.put("branId", branId);
		params.put("membId", membId);
		params.put("storeName", storeName);
		
		member = memberService.selectAMember(params);
		//membNm = member.getName();
		
		if(!"".equals(storeName)){
			storeList = storeService.selectStoreList(params); //-- 목록조회
		}
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("storeList", storeList);
		
		request.setAttribute("compId", compId);
		//request.setAttribute("compName", compName);
		
		request.setAttribute("branId", branId);
		request.setAttribute("branSel", branId);
		//request.setAttribute("branName", branName);
		
		request.setAttribute("membId", membId);
		//request.setAttribute("membNm", membNm);
		
		request.setAttribute("storeName", storeName);

		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/complex_store_select_ifr.ms");
		
		params.put("result", result);
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

		return "member/complex_store_select_ifr";
	}
}