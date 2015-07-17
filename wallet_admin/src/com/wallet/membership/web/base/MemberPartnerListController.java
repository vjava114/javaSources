package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.wallet.membership.common.PartnerComplexUpdate;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwMsCompPayMentExample;
import com.wallet.membership.service.MwCmCompanyService;
import com.wallet.membership.service.MwMsCompPayMentService;

@Controller
public class MemberPartnerListController extends CommonController {
	private final String PAGE_CODE = "MEMBER_PARTNER_LIST";
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
	@RequestMapping(value="/member/member_partner_list.ms")
	public String MemberPartnerList(Locale locale, Model model,
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
		  String MemberName = checkStr(request.getParameter("partner_name"), "");
			String MemberStat = checkStr(request.getParameter("partner_stat"),"");
			String Sdate = checkStr(request.getParameter("Sdate"),"");
			String Edate = checkStr(request.getParameter("Edate"), "");
			String DateType = checkStr(request.getParameter("sh_type"), "01");
			String PartnerTerm = checkStr(request.getParameter("partner_date"), "01");

			  params.put("MemberName", MemberName);
			  params.put("MemberStat", MemberStat);
			  params.put("Sdate", Sdate);
			  params.put("Edate", Edate);
			  params.put("DateType", DateType);
			  params.put("PartnerTerm", PartnerTerm);

			  
			if(PartnerTerm.equals("01")){
				if(Sdate.equals("") && Edate.equals("")){
					Sdate = Today;
					Edate = Today;
				}
			}
			System.out.println(Sdate);
			System.out.println(Edate);
			
			int count = 0;//전체 레코드 갯수
						
				mwCmCompanyExample = new MwCmCompanyExample();
				mwCmCompanyExample.or().andCompIdLike("CMP%");
				mwCmCompanyExample.setOrderByClause("REG_DTM DESC, COMP_NAME DESC");
				if(MemberName != null && !MemberName.equals("")){
					mwCmCompanyExample.or().andCompNameLike("%"+MemberName+"%");
				}
				if(MemberStat != null && !MemberStat.equals("")){
					mwCmCompanyExample.or().andAllyStatEqualTo(MemberStat);
				}
				
				if(DateType.equals("01")){
					mwCmCompanyExample.or().andRegDtmIsNotNull();
				}else if(DateType.equals("02")){
					mwCmCompanyExample.or().andChgDtmIsNotNull();
				}
				
				if(Sdate != null&&!Sdate.equals("")&&Edate != null&&!Edate.equals("")){
					if(DateType.equals("01")){
						try{
							mwCmCompanyExample.or().					
							andRegDtmBetween(DateTime.parse(Sdate, "yyyy-MM-dd"), new Date(DateTime.parse(Edate, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24)));
						}catch (Exception e) {
						}					
					}else if(DateType.equals("02")){
						try{
							mwCmCompanyExample.or().
							andChgDtmBetween(DateTime.parse(Sdate, "yyyy-MM-dd"), new Date(DateTime.parse(Edate, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24)));
						}catch (Exception e) {
							// TODO: handle exception
						}					
					}					
				}
			
			MwCmCompanyList = mwCmCompanyService.getByMemberPartnerExampleOnly(mwCmCompanyExample,page,row);

			/*##################### 복호화 S #####################*/
			for(int i=0; i<MwCmCompanyList.size(); i++){
				String manageNm = MwCmCompanyList.get(i).getManagerName();
				String mainNum = MwCmCompanyList.get(i).getMainNumber();
				manageNm = ktService.decoding(manageNm);
				mainNum = ktService.decoding(mainNum);
				MwCmCompanyList.get(i).setManagerName(manageNm);
				MwCmCompanyList.get(i).setMainNumber(mainNum);
			}
			/*##################### 복호화 E #####################*/
			
			count = mwCmCompanyService.getMemberPatnerSelectByCount(mwCmCompanyExample);
			
			/******* paging start *********/
			Paging pageing = new Paging();
			pageing.makeWebPaging(page, count, row);
			request.setAttribute("nowPage", row);
			request.setAttribute("paging", pageing.getSb());
			request.setAttribute("rowsPerPage", rowsPerPage);
			/******* paging end *********/
			
			//검색 데이타
			request.setAttribute("s_MemberName", MemberName);
			request.setAttribute("s_MemberStat", MemberStat);
			request.setAttribute("s_Sdate", Sdate);
			request.setAttribute("s_Edate", Edate);
			request.setAttribute("s_DateType", DateType);
			
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", page);
			request.setAttribute("rows", row);
			
			request.setAttribute("pagesPerPage", row);
			request.setAttribute("mwCmCompanyList", MwCmCompanyList);
			request.setAttribute("partnerTerm", PartnerTerm);
			
			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/member_partner_list.ms");
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
			
			return "member/member_partner_list";
	}
	
	
	/**
	 *제휴사 등록
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_detail"
	 */
	
	@RequestMapping(value="/member/member_partner_add.ms")
	public String MemberPartnerRegister(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> params = new HashMap<String,Object>();

		String regDay = checkStr(request.getParameter("regDay"), "");
		String regUser = checkStr(request.getParameter("regUser"), "");

		  params.put("regDay", regDay);
		  params.put("regUser", regUser);

		  MwCmCompany mwCmCompany= new MwCmCompany();
		if(!regDay.equals("")){
			//날짜 형식으로 변화하는 부분
			Date dateRegDay = DateTime.parseDate(regDay,"yyyy-MM-dd");
			if(!regDay.equals("")){mwCmCompany.setRegDtm(dateRegDay);}
		}
		if(!regUser.equals("")){
			
		}

		//날짜 형식을 문자열로 변화하는 부분
		request.setAttribute("regDtm", DateTime.format("yyyy-MM-dd"));
		request.setAttribute("regUser", getSessionMgrId(request));

		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/member_partner_add.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

		return "member/member_partner_register";
	}
	
	/**
	 *제휴사 업로드 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_detail"
	 */
	
	@RequestMapping(value="/member/member_partner_up.ms")
	public String MemberPartnerUplode(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, Object> params = new HashMap<String,Object>();

		String result = "";
		String partner_name = checkStr(request.getParameter("partner_name"), "");
		String partner_stat = checkStr(request.getParameter("partner_stat"),"");
		String partner_type = checkStr(request.getParameter("partner_type"),"");
		String partner_num = checkStr(request.getParameter("partner_num"),"");
		String partner_zipcode = checkStr(request.getParameter("partner_zipcode"),"");
		String partner_address1 = checkStr(request.getParameter("partner_address1"),"");
		String partner_address2 = checkStr(request.getParameter("partner_address2"),"");
//		String partner_p_phone = checkStr(request.getParameter("partner_p_phone"),"");
//		String partner_d_name = checkStr(request.getParameter("partner_d_name"),"");
//		String partner_d_number = checkStr(request.getParameter("partner_d_number"),"");
//		String partner_d_email = checkStr(request.getParameter("partner_d_email") ,"");
//		String partner_d_email2 = checkStr(request.getParameter("partner_d_email2"),"");
//		String partner_d_phone = checkStr(request.getParameter("partner_d_phone"),"");
		String partner_memo = checkStr(request.getParameter("memo"),"");
		String complex_yn = checkStr(request.getParameter("complex_yn"),"");
		String membId = checkStr(request.getParameter("membId"),"");
		
//		String Emails =  partner_d_email + "@" + partner_d_email2;
		
		/*##################### 암호화 S #####################*/
		String partner_p_phone = ktService.encoding(checkStr(request.getParameter("partner_p_phone"),""));
		String partner_d_name = ktService.encoding(checkStr(request.getParameter("partner_d_name"),""));
		String partner_d_number = ktService.encoding(checkStr(request.getParameter("partner_d_number"),""));
		String partner_d_email = ktService.encoding(checkStr(request.getParameter("partner_d_email") ,""));
//		String partner_d_email2 = ktService.encoding(checkStr(request.getParameter("partner_d_email2"),""));
		String partner_d_phone = ktService.encoding(checkStr(request.getParameter("partner_d_phone"),""));
		
		/*##################### 암호화 E #####################*/

		  params.put("partner_name", partner_name);
		  params.put("partner_stat", partner_stat);
		  params.put("partner_type", partner_type);
		  params.put("partner_num", partner_num);
		  params.put("partner_zipcode", partner_zipcode);
		  params.put("partner_address1", partner_address1);
		  params.put("partner_address2", partner_address2);
		  params.put("partner_p_phone", partner_p_phone);
		  params.put("partner_d_name", partner_d_name);
		  params.put("partner_d_number", partner_d_number);
		  params.put("partner_d_email", partner_d_email);
//		  params.put("partner_d_email2", partner_d_email2);
		  params.put("partner_d_phone", partner_d_phone);
		  params.put("partner_memo", partner_memo);
		  params.put("complex_yn", complex_yn);
		  params.put("membId", membId);


			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/member_partner_up.ms");
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		try{
			MwCmCompany mwCmCompany = new MwCmCompany();
			if(!partner_address1.equals(""))mwCmCompany.setAddr(partner_address1);
			if(!partner_address2.equals(""))mwCmCompany.setAddrDetail(partner_address2);
			mwCmCompany.setRegDtm(new Date());
			mwCmCompany.setRegUser(getSessionMgrId(request));
			if(!partner_name.equals(""))mwCmCompany.setCompName(partner_name);
			if(!partner_d_email.equals(""))mwCmCompany.setEmail(partner_d_email);
			if(!partner_zipcode.equals(""))mwCmCompany.setZipcode(partner_zipcode);
			if(!partner_p_phone.equals(""))mwCmCompany.setMainNumber(partner_p_phone);
			if(!partner_memo.equals(""))mwCmCompany.setMemo(partner_memo);
			if(!partner_d_phone.equals(""))mwCmCompany.setMobilePhone(partner_d_phone);
			if(!partner_d_number.equals(""))mwCmCompany.setPhoneNo(partner_d_number);
			if(!partner_stat.equals(""))mwCmCompany.setAllyStat(partner_stat);
			if(!partner_d_name.equals(""))mwCmCompany.setManagerName(partner_d_name);
			if(!partner_type.equals(""))mwCmCompany.setCompType(partner_type);
			if(!partner_num.equals(""))mwCmCompany.setBusinessNo(partner_num);
			if(!complex_yn.equals(""))mwCmCompany.setComplexPaymentYn(complex_yn);
			if(!membId.equals(""))mwCmCompany.setMembId(membId);
			mwCmCompany.setCompLevelType("01");
			
			mwCmCompanyService.PartnerInsert(mwCmCompany);
			mwCmCompanyService.commit();
		}catch (Exception e) {
			// TODO: handle exception
			mwCmCompanyService.rollback();
			System.out.println(e.getMessage());
			result = "err";
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
	
	@RequestMapping(value="/member/member_partner_edit.ms")
	public String MemberPartnerInfo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String partnerID = checkStr(request.getParameter("partnerID"), null);
		  params.put("partnerID", partnerID);
				
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
		
		if(partnerID != null && !partnerID.equals("")){
			mwCmCompanyExample.or().andCompIdEqualTo(partnerID);		
			MwCmCompany mwCmCompany = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
			String Email = mwCmCompany.getEmail();
//			String Email1 = "";
//			String Email2 = "";
//			if(Email!=null && !Email.equals("")){
//				String[] mails = StrCut(Email, "@");
//				Email1 = mails[0];
//				Email2 = mails[1];
//			}			
			String RegDtm = DateTime.format(mwCmCompany.getRegDtm(), "yyyy-MM-dd");
			String ChgDtm = DateTime.format(mwCmCompany.getChgDtm(), "yyyy-MM-dd");	
			
		request.setAttribute("compName", mwCmCompany.getCompName());//제휴사 명
		request.setAttribute("compId", mwCmCompany.getCompId());//제휴사 아이디
		request.setAttribute("allyStat", mwCmCompany.getAllyStat());//제휴 상태
		request.setAttribute("compType", mwCmCompany.getCompType());//제휴 구분
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
		request.setAttribute("memo", mwCmCompany.getMemo());//메모
		request.setAttribute("regDtm", RegDtm);//등록일
		request.setAttribute("regUser", mwCmCompany.getRegUser());//등록자
		request.setAttribute("chgDtm", ChgDtm);//수정일
		request.setAttribute("chgUser", mwCmCompany.getChgUser());//수정자
		request.setAttribute("complex_yn", mwCmCompany.getComplexPaymentYn());
		
		/*##################### 복호화 S #####################*/
		request.setAttribute("mainNumber", ktService.decoding(mwCmCompany.getMainNumber()));//제휴사 대표 연락처
		request.setAttribute("name", ktService.decoding(mwCmCompany.getManagerName()));//담당자 
		request.setAttribute("phoneNo", ktService.decoding(mwCmCompany.getPhoneNo()));//일반전화
		request.setAttribute("mobilePhone", ktService.decoding(mwCmCompany.getMobilePhone()));//휴대폰 번호
		request.setAttribute("email", ktService.decoding(Email));//이메일1
//		request.setAttribute("email2", ktService.decoding(Email2));//이메일2		
		/*##################### 복호화 E#####################*/
		
		}
		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/member_partner_edit.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
	
			return "member/member_partner_edit";	
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
	
	@RequestMapping(value="/member/member_partner_edit_up.ms")
	public String MemberPartnerEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String, Object> params = new HashMap<String,Object>();

		String result="";		
		String partner_name = checkStr(request.getParameter("partner_name"), null);
		String partner_stat = checkStr(request.getParameter("partner_stat"),null);
		String partner_type = checkStr(request.getParameter("partner_type"),null);		
		String partner_zipcode = checkStr(request.getParameter("partner_zipcode"),null);
		String partner_address1 = checkStr(request.getParameter("partner_address1"),null);
		String partner_address2 = checkStr(request.getParameter("partner_address2"),null);
//		String partner_p_phone = checkStr(request.getParameter("partner_p_phone"),null);
//		String partner_d_name = checkStr(request.getParameter("partner_d_name"),null);
//		String partner_d_number = checkStr(request.getParameter("partner_d_number"),null);
//		String partner_d_email = checkStr(request.getParameter("partner_d_email"),null);
//		String partner_d_email2 = checkStr(request.getParameter("partner_d_email2"),null);
//		String partner_d_phone = checkStr(request.getParameter("partner_d_phone"),null);
		String partner_memo = checkStr(request.getParameter("memo"),null);
		String partner_id = checkStr(request.getParameter("partner_id"),null);
		String complex_yn = checkStr(request.getParameter("complex_yn"),null);
//		String Emails =  partner_d_email + "@" + partner_d_email2;
		String partner_num = checkStr(request.getParameter("partner_num"),"");
		
		/*##################### 암호화 S #####################*/
		String partner_d_phone = ktService.encoding(checkStr(request.getParameter("partner_d_phone"),""));
		String partner_p_phone = ktService.encoding(checkStr(request.getParameter("partner_p_phone"),null));
		String partner_d_name = ktService.encoding(checkStr(request.getParameter("partner_d_name"),null));
		String partner_d_number = ktService.encoding(checkStr(request.getParameter("partner_d_number"),null));
		String partner_d_email = ktService.encoding(checkStr(request.getParameter("partner_d_email"),null));
//		String partner_d_email2 = ktService.encoding(checkStr(request.getParameter("partner_d_email2"),null));

		/*##################### 암호화 E #####################*/
		
		  params.put("partner_name", partner_name);
		  params.put("partner_stat", partner_stat);
		  params.put("partner_type", partner_type);
		  params.put("partner_num", partner_num);
		  
		  params.put("partner_zipcode", partner_zipcode);
		  params.put("partner_address1", partner_address1);
		  params.put("partner_address2", partner_address2);
		  
		  params.put("partner_p_phone", partner_p_phone);
		  params.put("partner_d_name", partner_d_name);
		  params.put("partner_d_number", partner_d_number);
		  params.put("partner_d_email", partner_d_email);
//		  params.put("partner_d_email2", partner_d_email2);
		  params.put("partner_d_phone", partner_d_phone);
		  
		  params.put("partner_memo", partner_memo);
		  params.put("complex_yn", complex_yn);
		  params.put("partner_id", partner_id);

			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/member_partner_edit_up.ms");
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/

			
		  //수정시 복합결제를 사용하지 않는다면
		
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		
		mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
		if(partner_id != null && !partner_id.equals("")){
			mwCmCompanyExample.or().andCompIdEqualTo(partner_id);
			
		try{
			MwCmCompany mwCmCompany = new MwCmCompany();
			mwCmCompany.setAddr(partner_address1);
			mwCmCompany.setAddrDetail(partner_address2);
			mwCmCompany.setChgDtm(new Date());
			mwCmCompany.setChgUser(getSessionMgrId(request));
			mwCmCompany.setCompName(partner_name);
			mwCmCompany.setEmail(partner_d_email);
			mwCmCompany.setZipcode(partner_zipcode);
			mwCmCompany.setMainNumber(partner_p_phone);
			mwCmCompany.setMemo(partner_memo);
			mwCmCompany.setMobilePhone(partner_d_phone);
			mwCmCompany.setPhoneNo(partner_d_number);
			mwCmCompany.setAllyStat(partner_stat);
			mwCmCompany.setManagerName(partner_d_name);
			mwCmCompany.setCompType(partner_type);
			mwCmCompany.setComplexPaymentYn(complex_yn);
			mwCmCompany.setBusinessNo(partner_num);
			
			System.out.println(partner_address1);
			System.out.println(partner_zipcode);
			
			mwCmCompanyService.update(mwCmCompany, mwCmCompanyExample);
			params.clear();
			mwCmCompanyService.commit();
			}catch (Exception e) {
				// TODO: handle exception
				mwCmCompanyService.rollback();
				log.info(e.getMessage());
				JsonErrMsg("err",response);
				return null;
			}
		}		
		
		if(complex_yn.equals("N")){
			new PartnerComplexUpdate(partner_id, "N");
		}else if(complex_yn.equals("Y")){
			new PartnerComplexUpdate(partner_id, "Y");
		}
		JsonErrMsg(result,response);
		return null;
	
	}
	
	/**
	 *제휴사 삭제
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return null
	 */
	
	@RequestMapping(value="/member/member_partner_delete.ms")
	public String MemberPartnerDelete(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> params = new HashMap<String,Object>();

		String result = "";
		String partnerID = checkStr(request.getParameter("partner_id"), null);
		
		  params.put("partnerID", partnerID);

		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		try{
			if(partnerID != null && !partnerID.equals("")){			
			MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();			
			mwCmCompanyExample.or().andCompIdEqualTo(partnerID);
			mwCmCompanyService.delete(mwCmCompanyExample);
			mwCmCompanyService.commit();
			}				
		}catch (Exception e) {
			// TODO: handle exception		
			mwCmCompanyService.rollback();
			result="err";
		}

		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/member_partner_delete.ms");
		
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
}