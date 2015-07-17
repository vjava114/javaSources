/**
 * @author 이경훈
 * @Date 2012-08-14
 * */
package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.DateTime;
import com.wallet.membership.model.MwCsCpnShareratio;
import com.wallet.membership.model.MwCsCpnShareratioExample;
import com.wallet.membership.model.custom.CouponShare;
import com.wallet.membership.service.MwCsCpnShareratioService;
import com.wallet.membership.service.custom.CouponShareService;

@Controller
public class CouponShareListController extends CommonController {
	private final String PAGE_CODE = "COUPON_SHARE_LIST";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 쿠폰 분담율 조회
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_share_list"
	 */ 
	@RequestMapping(value="/member/coupon_share_list.ms")
	public String CouponShareList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<CouponShare> CouponShareList = null;
		CouponShareService couponShareService = new CouponShareService();
		 
		int count = 0;

		String pageNo = checkStr(request.getParameter("nowPage"), "1");
		String rowsPerPage = checkStr(request.getParameter("rowsPerPage"), "15");

		int page = Integer.parseInt(pageNo);
		int rows = Integer.parseInt(rowsPerPage);		
		String Today = DateTime.format("yyyy-MM-dd");

		//파라메타 받기
		String CuponPart = checkStr(request.getParameter("part"), "");
		String MembName = checkStr(request.getParameter("membName"), "");
		String cpnName = checkStr(request.getParameter("cpnName"), "");
		String ShStartDays = checkStr(request.getParameter("sdate"), Today);
		String ShEndDays = checkStr(request.getParameter("edate"), Today);
		String PartnerTerm = checkStr(request.getParameter("bulkdate"), "01");
		String partnerName = checkStr(request.getParameter("partnerName"),"");

		if(PartnerTerm.equals("04")){
			ShStartDays = "";
			ShEndDays = "";
		}
		
		
		/*목록 가져오기*/
		String sdate = "";
		String edate = "";
		try{
			Date Dates = DateTime.parse(ShStartDays, "yyyy-MM-dd");
			sdate = DateTime.format(Dates, "yyyyMMdd");
			Dates = new Date(DateTime.parse(ShEndDays, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24));
			edate = DateTime.format(Dates, "yyyyMMdd");
			
			System.out.println(sdate);
			System.out.println(edate);
			
		}catch (Exception e){
			
		}
		
		CouponShare couponShare = new CouponShare();
		if(!CuponPart.equals("")){couponShare.setCuponPart(CuponPart);}
		if(!MembName.equals("")){couponShare.setMembName(MembName);}
		if(!cpnName.equals("")){couponShare.setCpnName(cpnName);}
		if(!ShStartDays.equals("")){couponShare.setShStartDays(sdate);}
		if(!ShEndDays.equals("")){couponShare.setShEndDays(edate);}
		if(!partnerName.equals("")){couponShare.setPartnerName(partnerName);}
		
		CouponShareList = couponShareService.getByExamplePage(couponShare, page, rows);
		/*목록 리텃 갯수 가져오기*/
		count = couponShareService.getCountByExample(couponShare);
		
		for(int i=0;i<CouponShareList.size();i++){
			try{					

			Date Dates = new Date(DateTime.parse( CouponShareList.get(i).getValEndDay(), "yyyyMMdd").getTime()+ (1000 * 60 * 60 * 24));
			CouponShareList.get(i).setValEndDay(DateTime.format(Dates, "yyyy-MM-dd"));
			
			Dates = DateTime.parse( CouponShareList.get(i).getValStartDay(), "yyyyMMdd");
			CouponShareList.get(i).setValStartDay(DateTime.format(Dates, "yyyy-MM-dd"));
			
			Dates = DateTime.parse( CouponShareList.get(i).getStartDay(), "yyyyMMdd");
			CouponShareList.get(i).setStartDay(DateTime.format(Dates, "yyyy-MM-dd"));
			
			Dates = new Date(DateTime.parse( CouponShareList.get(i).getEndDay(), "yyyyMMdd").getTime()+ (1000 * 60 * 60 * 24));
			CouponShareList.get(i).setEndDay(DateTime.format(Dates, "yyyy-MM-dd"));
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		/******* paging start *********/
		Paging pageing = new Paging();
		pageing.makeWebPaging(page, count, rows);
		request.setAttribute("nowPage", page);
		request.setAttribute("paging", pageing.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		/******* paging end *********/
	
		/* SET ATTRIBUTE */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("CouponShareList", CouponShareList);
		request.setAttribute("count", count);
		request.setAttribute("pageNo", page);
		request.setAttribute("rows", rows);
		
		request.setAttribute("ShStartDays", ShStartDays);
		request.setAttribute("ShEndDays", ShEndDays);
		request.setAttribute("part", CuponPart);
		request.setAttribute("membName", MembName);
		request.setAttribute("cpnName", cpnName);
		request.setAttribute("rows", rowsPerPage);		
		request.setAttribute("partnerTerm", PartnerTerm);
		request.setAttribute("partnerName", partnerName);
		return "member/coupon_share_list";
}

	/**
	 * 쿠폰 분담율의 상세페이지 등록이 가능하다.
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/coupon_share_insert.ms"
	 */
	@RequestMapping(value="/member/coupon_share_detail_reg.ms")
	public String couponShareReg(Locale locale, Model model,HttpServletRequest request, HttpServletResponse response) {
		String CpnID = checkStr(request.getParameter("cpnID"), "");
		/* GET PARAMETERS */		
		Log.info("request cpnId : " + CpnID);
		CouponShareService couponShareService = new CouponShareService();
		CouponShare couponShare = new CouponShare();
		couponShare.setCpnID(CpnID);
		CouponShare Result = couponShareService.getByExampleOnly(couponShare);
		
		request.setAttribute("cpnID", Result.getCpnID());
		request.setAttribute("cpnName", Result.getCpnName());
		request.setAttribute("compId", Result.getPartnerId());
		request.setAttribute("partnerName", Result.getPartnerName());
		request.setAttribute("brandName", Result.getBrandName());
		request.setAttribute("membName", Result.getMembName());
		request.setAttribute("part", Result.getPart());
		
		//날짜 형식을 문자열로 변화하는 부분
		request.setAttribute("regDtm", DateTime.format("yyyy-MM-dd"));
		request.setAttribute("regUser", getSessionName(request));
		
		return "/member/coupon_share_detail_reg";
	
	}
	
	/**
	 * 쿠폰 분담율 등록 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/coupon_share_detail.ms"
	 */	
		@RequestMapping(value="/member/coupon_share_up.ms")
		public String CouponShareUplode(Locale locale, Model model,
				HttpServletRequest request, HttpServletResponse response) {
			/* GET PARAMETERS */		
			String CpnID = checkStr(request.getParameter("cpnID"), "");
			String[] CompDtailId = request.getParameterValues("CompDtailId");
			String[] Ratio = request.getParameterValues("Ratio");
			String[] CompType = request.getParameterValues("CompType");
			
			System.out.println("CpnID : " + CpnID);
			System.out.println("CompDtailId : " + CompDtailId.length);
			System.out.println("Ratio : " + Ratio.length);
			System.out.println("CompType : " + CompType.length);
			
			if(!CpnID.equals("")&&CompType!=null&&Ratio!=null&&CompDtailId!=null){
				MwCsCpnShareratioService mwCsCpnShareratioService = new MwCsCpnShareratioService();
				try{
					for(int i=0;i<CompDtailId.length;i++){
						if(CompDtailId[i]==null||CompDtailId[i].equals("")||Ratio[i]==null||Ratio[i].equals("")||CompType[i]==null||CompType[i].equals("")){
							continue;
						}
						MwCsCpnShareratio UpdateData = new MwCsCpnShareratio();
						UpdateData.setCompId(CompDtailId[i]);
						UpdateData.setCompShareRatio(Integer.parseInt(Ratio[i]));
						UpdateData.setCompType(CompType[i]);
						UpdateData.setCpnId(CpnID);
						UpdateData.setRegDtm(new Date());
						UpdateData.setRegUser(getSessionMgrId(request));
						UpdateData.setSeqNo(i+1);
						mwCsCpnShareratioService.insert(UpdateData);
					}
					mwCsCpnShareratioService.commit();
				}catch (Exception e) {
					// TODO: handle exception
					mwCsCpnShareratioService.rollback();
					JsonErrMsg("err", response);
					return null;
				}
			}else{
				JsonErrMsg("err", response);
				return null;
			}	
			JsonErrMsg("", response);
			return null;
		}
		
	
	/**
	 * 쿠폰 분담율의 상세페이지 수정이 가능하다.
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/coupon_share_detail"
	 */
	
	@RequestMapping(value="/member/coupon_share_detail.ms")
	public String CouponShareDetail(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		String CpnID = checkStr(request.getParameter("cpnID"), "");
		log.info("=====================요청 아이디");
		log.info("CpnID"+CpnID);
		
			/* GET PARAMETERS */		
		MwCsCpnShareratioService mwCsCpnShareratioService = new MwCsCpnShareratioService();
		CouponShareService couponShareService = new CouponShareService();
		CouponShare couponShare = new CouponShare();
		couponShare.setCpnID(CpnID);
		MwCsCpnShareratioExample mwCsCpnShareratioExample = new MwCsCpnShareratioExample();
		mwCsCpnShareratioExample.or().andCpnIdEqualTo(CpnID);
		List<MwCsCpnShareratio> mwCsCpnShareratioList = mwCsCpnShareratioService.getByExample(mwCsCpnShareratioExample);
		for(int i=0;i<mwCsCpnShareratioList.size();i++){
			mwCsCpnShareratioList.get(i).setCompName();
		}
		
		int count = mwCsCpnShareratioService.getCountByExample(mwCsCpnShareratioExample);
		CouponShare Result = couponShareService.getByExampleOnly(couponShare);
		
		request.setAttribute("cpnID", CpnID);
		request.setAttribute("cpnName", Result.getCpnName());
		request.setAttribute("compId", Result.getPartnerId());
		request.setAttribute("partnerName", Result.getPartnerName());
		request.setAttribute("brandName", Result.getBrandName());
		request.setAttribute("membName", Result.getMembName());
		request.setAttribute("part", Result.getPart());
		request.setAttribute("mwCsCpnShareratioList", mwCsCpnShareratioList);
		request.setAttribute("count", count);
		request.setAttribute("regDtm", Result.getRegDtmStr());
		request.setAttribute("regUser", Result.getShareRegUser());
		request.setAttribute("chgDtm", DateTime.format("yyyy-MM-dd"));
		request.setAttribute("chgUser", getSessionMgrId(request));
		
		return "/member/coupon_share_detail";
	}
	
	/**
	 * 쿠폰 분담율의 상세페이지 수정동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/coupon_share_detail"
	 */
	
	@RequestMapping(value="/member/coupon_share_edit.ms")
	public String CouponShareEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
			
		/* GET PARAMETERS */		
		String CpnID = checkStr(request.getParameter("cpnID"), "");
		String[] CompDtailId = request.getParameterValues("CompDtailId");
		String[] Ratio = request.getParameterValues("Ratio");
		String[] CompType = request.getParameterValues("CompType");
		
		System.out.println("CpnID : " + CpnID);
		System.out.println("CompDtailId : " + CompDtailId.length);
		System.out.println("Ratio : " + Ratio.length);
		System.out.println("CompType : " + CompType.length);
		
		for(int i=0;i<CompDtailId.length;i++){
			System.out.println("검증 : " +CompDtailId[i] );
		}
		
		if(!CpnID.equals("")&&CompType!=null&&Ratio!=null&&CompDtailId!=null){
			MwCsCpnShareratioService mwCsCpnShareratioService = new MwCsCpnShareratioService();
			try{
				MwCsCpnShareratioExample mwCsCpnShareratioExample = new MwCsCpnShareratioExample();
				mwCsCpnShareratioExample.or().andCpnIdEqualTo(CpnID);
				mwCsCpnShareratioService.delete(mwCsCpnShareratioExample);
				for(int i=0;i<CompDtailId.length;i++){
					if(CompDtailId[i]==null||CompDtailId[i].equals("")||Ratio[i]==null||Ratio[i].equals("")||CompType[i]==null||CompType[i].equals("")){
						continue;
					}
					MwCsCpnShareratio UpdateData = new MwCsCpnShareratio();
					UpdateData.setCompId(CompDtailId[i]);
					UpdateData.setCompShareRatio(Integer.parseInt(Ratio[i]));
					UpdateData.setCompType(CompType[i]);
					UpdateData.setCpnId(CpnID);
					UpdateData.setRegDtm(new Date());
					UpdateData.setRegUser(getSessionMgrId(request));
					UpdateData.setSeqNo(i+1);
					mwCsCpnShareratioService.insert(UpdateData);
				}
				mwCsCpnShareratioService.commit();
			}catch (Exception e) {
				// TODO: handle exception
				mwCsCpnShareratioService.rollback();
				JsonErrMsg("err", response);
				return null;
			}
		}else{
			JsonErrMsg("err", response);
			return null;
		}	
		JsonErrMsg("", response);
		return null;
	}
	
	/**
	 * 쿠폰 분담율의 상세페이지 삭제동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/coupon_share_detail"
	 */
	
	@RequestMapping(value="/member/coupon_share_del.ms")
	public String CouponShareDel(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
			
			/* GET PARAMETERS */		
			String CpnID = checkStr(request.getParameter("cpnID"), "");
			
			if(!CpnID.equals("")){
				MwCsCpnShareratioService mwCsCpnShareratioService = new MwCsCpnShareratioService();
				try{
					MwCsCpnShareratioExample mwCsCpnShareratioExample = new MwCsCpnShareratioExample();
					mwCsCpnShareratioExample.or().andCpnIdEqualTo(CpnID);
					mwCsCpnShareratioService.delete(mwCsCpnShareratioExample);
					mwCsCpnShareratioService.commit();
				}catch (Exception e) {
					// TODO: handle exception
					mwCsCpnShareratioService.rollback();
					JsonErrMsg("err", response);
					return null;
				}
			}else{
				JsonErrMsg("err", response);
				return null;
			}	
			JsonErrMsg("", response);
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