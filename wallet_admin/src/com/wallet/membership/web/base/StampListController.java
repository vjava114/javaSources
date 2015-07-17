/**
 * @author 이경훈
 * @Date 2012-08-14
 * */
package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.DateTime;
import com.wallet.membership.model.MembCardlist;
import com.wallet.membership.model.MembCardlistExample;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwMsStamp;
import com.wallet.membership.model.MwMsStampExample;
import com.wallet.membership.model.custom.Image;
import com.wallet.membership.service.MembCardlistService;
import com.wallet.membership.service.MwCmCompanyService;
import com.wallet.membership.service.MwMsStampService;
import com.wallet.membership.service.custom.ImageService;

@Controller
public class StampListController extends CommonController {
	private final String PAGE_CODE = "STAMP_LIST";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 스탬프 목록 조회
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/stamp_list.ms"
	 */ 
	@RequestMapping(value="/member/stamp_list.ms")
	public String TermsList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		//데이타 베이스 호출 변수 선언
		List<MwMsStamp> MwMsStampList = null;
		MwMsStampService mwMsStampService = new MwMsStampService();
		MwMsStampExample mwMsStampExample = null;
				
		//페이징 처리를 위한 파라메타
		String pageNo = checkStr(request.getParameter("nowPage"), "1");
		String rowsPerPage = checkStr(request.getParameter("rowsPerPage"), "15");
		String Today = DateTime.format("yyyy-MM-dd");
		
		int page = Integer.parseInt(pageNo);
		int row = Integer.parseInt(rowsPerPage);
		//파라메타 받기
			String CompName = checkStr(request.getParameter("comp_name"),"");
		    String MemberName = checkStr(request.getParameter("member_name"), "");
			String Sdate = checkStr(request.getParameter("Sdate"), Today);
			String Edate = checkStr(request.getParameter("Edate"), Today);
			String DateType = checkStr(request.getParameter("sh_type"), "01");
			String StampTerm = checkStr(request.getParameter("stamp_date"), "01");
			if(StampTerm.equals("04")){
				Sdate = "";
				Edate = "";
				DateType = "";
			}
			
			System.out.println(CompName);
			System.out.println(MemberName);
			System.out.println(Sdate);
			System.out.println(Edate);
			System.out.println(DateType);
			System.out.println(StampTerm);

			int count = 0;//전체 레코드 갯수
						
				mwMsStampExample = new MwMsStampExample();
				mwMsStampExample.or().andCCompIdLike("CMP%");
				mwMsStampExample.setOrderByClause("A.REG_DTM DESC, C.COMP_NAME DESC");

				if(CompName != null && !CompName.equals("")){
					mwMsStampExample.or().andCCompNameLike("%"+CompName+"%");
				}
				if(MemberName != null && !MemberName.equals("")){
					mwMsStampExample.or().andBMembNameLike("%"+MemberName+"%");
				}

				if(DateType.equals("01")){
					mwMsStampExample.or().andARegDtmIsNotNull();
				}else if(DateType.equals("02")){
					mwMsStampExample.or().andAChgDtmIsNotNull();
				}

				if(Sdate != null&&!Sdate.equals("")&&Edate != null&&!Edate.equals("")){
					if(DateType.equals("01")){
						try{
							mwMsStampExample.or().					
							andARegDtmBetween(DateTime.parse(Sdate, "yyyy-MM-dd"), new Date(DateTime.parse(Edate, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24)));
						}catch (Exception e) {
						}					
					}else if(DateType.equals("02")){
						try{
							mwMsStampExample.or().
							andAChgDtmBetween(DateTime.parse(Sdate, "yyyy-MM-dd"), new Date(DateTime.parse(Edate, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24)));
						}catch (Exception e) {
							// TODO: handle exception
						}
					}
				}

			MwMsStampList = mwMsStampService.getByStampExamplePage(mwMsStampExample,page,row);
			count = mwMsStampService.getStampCountByExample(mwMsStampExample);
			/******* paging start *********/
			Paging pageing = new Paging();
			pageing.makeWebPaging(Integer.parseInt(pageNo), count, Integer.parseInt(rowsPerPage));
			request.setAttribute("nowPage", Integer.parseInt(pageNo));
			request.setAttribute("paging", pageing.getSb());
			request.setAttribute("rowsPerPage", rowsPerPage);
			/******* paging end *********/

				//검색 데이타
			request.setAttribute("s_MemberName", MemberName);
			request.setAttribute("s_CompName", CompName);
			request.setAttribute("s_Sdate", Sdate);
			request.setAttribute("s_Edate", Edate);
			request.setAttribute("s_DateType", DateType);
			
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", page);
			request.setAttribute("rows", row);
			request.setAttribute("pagesPerPage", row);
			request.setAttribute("stampTerm", StampTerm);
			request.setAttribute("mwMsStampList", MwMsStampList);
			
			return "member/stamp_list";
	}
	
	
	/**
	 * 스탭프 등록
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/stamp_register"
	 */
	
	@RequestMapping(value="/member/stamp_add.ms")
	public String StampRegister(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		String regDay = checkStr(request.getParameter("regDay"), "");
		String regUser = checkStr(request.getParameter("regUser"), "");
		
		MwMsStamp mwMsStamp = new MwMsStamp();
		if(!regDay.equals("")){
			//날짜 형식으로 변화하는 부분
			Date dateRegDay = DateTime.parseDate(regDay,"yyyy-MM-dd");
			if(!regDay.equals("")){mwMsStamp.setRegDtm(dateRegDay);}
		}
		if(!regUser.equals("")){
			
		}

		//날짜 형식을 문자열로 변화하는 부분
		request.setAttribute("regDtm", DateTime.format("yyyy-MM-dd"));
		request.setAttribute("regUser", getSessionMgrId(request));

		return "member/stamp_register";
	}

	
	/**
	 * 스탬프 업로드 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/stamp_register"
	 */
	
	@RequestMapping(value="/member/stamp_up.ms")
	public String MemberPartnerUplode(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		int imgResult = 0; //-- 이미지 등록의 실행결과 0:실패, 1:성공
		String result = "";
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> imgParams = new HashMap<String, Object>();

		String membId = checkStr(request.getParameter("membId"),"");
		String stampMaxno = checkStr(request.getParameter("stampMaxno"),"");
		String stampUnit = checkStr(request.getParameter("stampUnit"),"");
		String benefitNoti = checkStr(request.getParameter("benefitNoti"),"");
		String Sdate = checkStr(request.getParameter("Sdate"),"");
		String Edate = checkStr(request.getParameter("Edate"),"");
		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로

		String stamp_img = checkStr(request.getParameter("stamp_img"),"N");
		
		//이미지 관련들 
		String ios3_img = checkStr(request.getParameter("ios3_img"), "");
		String ios4s_img = checkStr(request.getParameter("ios4s_img"), "");
		String android_img = checkStr(request.getParameter("android_img"), "");

		MwMsStampService mwMsStampService = new MwMsStampService();
		try{
			MwMsStampExample mwMsStampExample = new MwMsStampExample();
			mwMsStampExample.or().andMembIdEqualTo(membId);
			MwMsStamp getSeq = mwMsStampService.StampGetBySeq(mwMsStampExample);

			if(stamp_img.equals("N")){
				System.out.println("N값이 들어왔어요");
				imgParams.put("level", "");
				imgParams.put("id", membId);
				imgParams.put("useType", "05");
				imgParams.put("osType", "01");
				imgResult = imgResult * imgService.deleteImage(imgParams);
				imgParams.put("level", "");
				imgParams.put("id", membId);
				imgParams.put("useType", "05");
				imgParams.put("osType", "02");
				imgResult = imgResult * imgService.deleteImage(imgParams);
				imgParams.put("level", "");
				imgParams.put("id", membId);
				imgParams.put("useType", "05");
				imgParams.put("osType", "11");
				imgResult = imgResult * imgService.deleteImage(imgParams);
				
			}

			
			MwMsStamp mwMsStamp = new MwMsStamp();
			
			if(!membId.equals(""))mwMsStamp.setMembId(membId);
			mwMsStamp.setStampSeq(getSeq.getStampSeq());
			if(!stampMaxno.equals(""))mwMsStamp.setStampMaxNo(Integer.parseInt(stampMaxno));
			if(!stampUnit.equals(""))mwMsStamp.setStampUnit(Integer.parseInt(stampUnit));
			if(!benefitNoti.equals(""))mwMsStamp.setBenefitNotice(benefitNoti);
			if(!Sdate.equals(""))mwMsStamp.setExpireSday(Sdate.replace("-", ""));
			if(!Edate.equals(""))mwMsStamp.setExpireEday(Edate.replace("-", ""));

			mwMsStamp.setRegDtm(new Date());
			mwMsStamp.setRegUser(getSessionMgrId(request));
			mwMsStamp.setStampImage(stamp_img);
			
			mwMsStampService.insert(mwMsStamp);
			mwMsStampService.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			mwMsStampService.rollback();
			System.out.println(e.getMessage());
			return null;
		}

		if(stamp_img.equals("Y")){
		try{
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "05");
			imgParams.put("osType", "01");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", ios3_img);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "05");
			imgParams.put("osType", "02");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", ios4s_img);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "05");
			imgParams.put("osType", "11");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", android_img);
			imgResult = imgService.insertImage(imgParams);
	
			if(imgResult == 0){
				imgService.rollback();
			}
			else{
				imgService.commit();
			}			
			
		}catch (Exception e) {
			// TODO: handle exception
			imgService.rollback();
			result = "err";
		}
		}
		return null;
	}
	
	/**
	 * 스템프 수정
	 * 
	 * @param local
	 * @param model
	 * @param request
	 * @param response
	 * @return member/stamp_edit
	 */
	
	@RequestMapping(value="/member/stamp_edit.ms")
	public String StampInfo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
//		String partnerID = checkStr(request.getParameter("partnerID"), null);
//		String franchiseID = checkStr(request.getParameter("franchiseID"), null);		
//		String franchiseName = checkStr(request.getParameter("franchiseName"),null);
		String membId = checkStr(request.getParameter("membId"),null);
		int stampSeq = Integer.parseInt(checkStr(request.getParameter("stampSeq"), "0"));
		System.out.println(membId);
		System.out.println(stampSeq);

		MwMsStampService mwMsStampService = new MwMsStampService();
		MwMsStampExample mwMsStampExample = new MwMsStampExample();
		ImageService imgService = new ImageService();

		HashMap<String, Object> imgParams = new HashMap<String, Object>();

		if(membId != null && !membId.equals("")){
			mwMsStampExample.or().andAMembIdEqualTo(membId).andAStampSeqEqualTo(stampSeq);

			MwMsStamp mwMsStamp = mwMsStampService.getByStampExampleOnly(mwMsStampExample);
			String RegDtm = DateTime.format(mwMsStamp.getRegDtm(), "yyyy-MM-dd");
			String ChgDtm = DateTime.format(mwMsStamp.getChgDtm(), "yyyy-MM-dd");	
//			String ExpireSday = DateTime.format(mwMsStamp.getExpireSday(), "yyyy-MM-dd");	
//			String ExpireEday = DateTime.format(mwMsStamp.getExpireEday(), "yyyy-MM-dd");	
			
			imgParams.put("id", membId);
			List<Image> imgList = imgService.selectImageList(imgParams);

			request.setAttribute("compName",mwMsStamp.getCompName()); //제휴사명
			request.setAttribute("membName", mwMsStamp.getMembName()); //멤버십 명
			request.setAttribute("stampMaxno", mwMsStamp.getStampMaxNo()); //스템프 구간 최대갯수
			request.setAttribute("stampUnit", mwMsStamp.getStampUnit()); //스템프판 허용개수
			request.setAttribute("benefitNoti", mwMsStamp.getBenefitNotice()); //혜택내용
			request.setAttribute("stamp_img", mwMsStamp.getImg_Yn());
			request.setAttribute("regDtm", RegDtm);//등록일
			request.setAttribute("regUser", mwMsStamp.getRegUser());//등록자
			request.setAttribute("chgDtm", ChgDtm);//수정일
			request.setAttribute("chgUser", mwMsStamp.getChgUser());//수정자
//			request.setAttribute("imgList", imgList);
			request.setAttribute("ios3_img", mwMsStamp.getIos3_img());//아이폰 3S 이미지
			request.setAttribute("ios4s_img", mwMsStamp.getIos4s_img());//아이폰 4GS 이미지
			request.setAttribute("android_img", mwMsStamp.getAndroid_img());//안드로이드 이미지
			request.setAttribute("s_Sdate", mwMsStamp.getExpireSday());
			request.setAttribute("e_Edate", mwMsStamp.getExpireEday());
			request.setAttribute("stampSeq", mwMsStamp.getStampSeq());
			request.setAttribute("membId", mwMsStamp.getMembId());
			
		
		}
		
			return "member/stamp_edit";			
	}

	/**
	 *스템프 수정 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/stamp_edit_up.ms"
	 */
	
	@RequestMapping(value="/member/stamp_edit_up.ms")
	public String MemberFranchiseEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		int imgResult = 0;
		String result = "";
		ImageService imgService = new ImageService();

		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String imgHost = PropertiesUtil.get("img_host"); //-- 기종별/사용유형별 이미지 경로
		String membId = checkStr(request.getParameter("membId"),null);
		int stampSeq = Integer.parseInt(checkStr(request.getParameter("stampSeq"), "0"));

		String stampMaxno = checkStr(request.getParameter("stampMaxno"),null);
		String stampUnit = checkStr(request.getParameter("stampUnit"),null);
		String benefitNoti = checkStr(request.getParameter("benefitNoti"),"");
		String Sdate = checkStr(request.getParameter("Sdate"),"");
		String Edate = checkStr(request.getParameter("Edate"),"");
		String stamp_img = checkStr(request.getParameter("stamp_img"),"N");

		System.out.println(membId);
		System.out.println(stampSeq);
		
		//이미지 관련들 
		String ios3_img = checkStr(request.getParameter("ios3_img"), "");
		String ios4s_img = checkStr(request.getParameter("ios4s_img"), "");
		String android_img = checkStr(request.getParameter("android_img"), "");

		MwMsStampService mwMsStampService = new MwMsStampService();
		try{
			MwMsStampExample mwMsStampExample = new MwMsStampExample();
			
			if(membId != null && !membId.equals("")){
				mwMsStampExample.or().andMembIdEqualTo(membId).andStampSeqEqualTo(stampSeq);

				if(stamp_img.equals("N")){
					imgParams.put("level", "");
					imgParams.put("id", membId);
					imgParams.put("useType", "05");
					imgParams.put("osType", "01");
					imgResult = imgResult * imgService.deleteImage(imgParams);
					imgParams.put("level", "");
					imgParams.put("id", membId);
					imgParams.put("useType", "05");
					imgParams.put("osType", "02");
					imgResult = imgResult * imgService.deleteImage(imgParams);
					imgParams.put("level", "");
					imgParams.put("id", membId);
					imgParams.put("useType", "05");
					imgParams.put("osType", "11");
					imgResult = imgResult * imgService.deleteImage(imgParams);
					
				}
				
				MwMsStamp mwMsStamp = new MwMsStamp();

				mwMsStamp.setChgDtm(new Date());
				mwMsStamp.setChgUser(getSessionMgrId(request));
				mwMsStamp.setStampMaxNo(Integer.parseInt(stampMaxno));
				mwMsStamp.setStampUnit(Integer.parseInt(stampUnit));
				mwMsStamp.setBenefitNotice(benefitNoti);
				mwMsStamp.setExpireEday(Edate.replace("-", ""));
				mwMsStamp.setExpireSday(Sdate.replace("-", ""));
				
				System.out.println(stampMaxno);
				System.out.println(stampUnit);
				System.out.println(benefitNoti);
				
				mwMsStampService.update(mwMsStamp, mwMsStampExample);
				mwMsStampService.commit();

			}
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				mwMsStampService.rollback();
				JsonErrMsg("err",response);
				return null;
			} 		
		if(stamp_img.equals("Y")){
			try{
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "05");
			imgParams.put("osType", "01");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", ios3_img);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "05");
			imgParams.put("osType", "02");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", ios4s_img);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", membId);
			imgParams.put("useType", "05");
			imgParams.put("osType", "11");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", android_img);
			imgResult = imgService.insertImage(imgParams);
			
			if(imgResult == 0){
				System.out.println(imgResult);
				imgService.rollback();
			}
			else{
				System.out.println(imgResult);
				imgService.commit();
			}			
			
		}catch (Exception e) {
			// TODO: handle exception
			imgService.rollback();
			result = "err";
			System.out.println(e.getMessage());
		} 
		}

		JsonErrMsg(result,response);
		return null;	
	}

	/**
	 *스템프 삭제
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return null
	 */
	
	@RequestMapping(value="/member/stamp_delete.ms")
	public String StampDelete(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		String result = "";		
		String membId = checkStr(request.getParameter("membId"),null);
		int stampSeq = Integer.parseInt(checkStr(request.getParameter("stampSeq"), "0"));

		MwMsStampService mwMsStampService = new MwMsStampService();

		
		try{
			if(membId != null && !membId.equals("")){		
				
			MwMsStampExample mwMsStampExample = new MwMsStampExample();
			
			mwMsStampExample.or().andMembIdEqualTo(membId).andStampSeqEqualTo(stampSeq);
			mwMsStampService.delete(mwMsStampExample);
			mwMsStampService.commit();
			}
		}catch (Exception e) {
			// TODO: handle exception		
			mwMsStampService.rollback();
			result = "err";
		}
		JsonErrMsg(result,response);
		return null;
	
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
