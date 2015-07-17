package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.PartnerComplexUpdate;
import com.wallet.membership.common.UpdateStore;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwMsCompPayMent;
import com.wallet.membership.model.MwMsCompPayMentExample;
import com.wallet.membership.model.MwMsStarPayMent;
import com.wallet.membership.model.MwMsStarPayMentExample;
import com.wallet.membership.service.MwCmCompanyService;
import com.wallet.membership.service.MwMsCompPayMentService;
import com.wallet.membership.service.custom.MwMsStarPayMentService;

@Controller
public class IsComplexController extends CommonController{
	private final String PAGE_CODE = "COUPON_ORDER_MANAGER";
	private Logger log = Log.getLogger("logs");
			
	/**
	 * 복합결제 등록화면
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/complex_register.ms")
	public String ComplexAdd(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		String compId = checkStr(request.getParameter("compId"), null);
				
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
		mwCmCompanyExample.or().andCompIdEqualTo(compId);
		
		MwCmCompany mwCmCompany = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
		
		String CompName="";
		String CompCode="";
		String MidShopId = "";
		
		if(mwCmCompany!=null){
			CompName = mwCmCompany.getCompName();
		}
		if(compId.matches("CMP.*")){
			CompCode = "01";
		}else if(compId.matches("BRD.*")){
			CompCode = "02";
		}else if(compId.matches("SHP.*")){
			CompCode = "03";
			MidShopId = mwCmCompany.getMidShopId()==null || "".equals(mwCmCompany.getMidShopId())? "" : mwCmCompany.getMidShopId(); //-- 복합결제 제휴사의 가맹점인 경우 중 별포인트 연동정보를 이용할 경우만 가지고 있을 정보임.
		}else if(compId.matches("SAG.*")){
			CompCode = "04";
		}
		
		System.out.println("CompCode : " + CompCode);
		/* SET ATTRIBUTE */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("compId", compId);
		request.setAttribute("compName", CompName);
		request.setAttribute("compCode", CompCode);
		request.setAttribute("midShopId", MidShopId);
		return "member/complex_register";	
	}
	
	
	/**
	 * 복합결제 등록 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/complex_register_up.ms")
	public String ComplexAdd_Move(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		String compId = checkStr(request.getParameter("compId"), "");
		String midShopId = checkStr(request.getParameter("midShopId"),"");
		String compCode = checkStr(request.getParameter("compCode"), null);
		MwMsCompPayMentService mwCmCompPayMentService = new MwMsCompPayMentService();
		MwMsStarPayMentService mwMsStarPayMentService = new MwMsStarPayMentService();
		
		String result = "";
		System.out.println("compId : " + compId);
		System.out.println("compCode : " + compCode);
		
		String setCompPayCpnYn = checkStr(request.getParameter("compPayCpnYn"), "");
		String setCompPayStampSaveYn = checkStr(request.getParameter("compPayStampSaveYn"), "");
		String setCompPayStampYn = checkStr(request.getParameter("compPayStampYn"), "");
		
		String setCompPayMembDc = checkStr(request.getParameter("compPayMembDc"), "");
		String setCompPayMembUse =checkStr(request.getParameter("compPayMembUse"), "");
		String setCompPayMembSave = checkStr(request.getParameter("compPayMembSave"), "");	
		
		String setStarPayMembDc = checkStr(request.getParameter("starPointDc"), "");
		String setStarPayMembUse =checkStr(request.getParameter("starPointUseYn"), "");
		String setStarPayMembSave = checkStr(request.getParameter("starcompPayMembSave"), "");	
		
		
		//맴버십 적립여부 Y선택시
		String setSaveNotice = "";
		String setCashSaveRate = "";
		String setCompSaveRate = "";
		String setCardSaveRate = "";
		String setEtcSaveRate = "";	
		String setSaveRoundType = "";
		String setDcSavePriceType = "";
		String setSaveMinPayPrice = "";
		String setSaveMaxPayPrice = "";	
		String setMaxSavePoint = "";
		String setDebitSaveRate = "";
		String setSaveRateFixedYn = "";
		String setSaveUnit = "";
		System.out.println("setCompPayMembSave : " + setCompPayMembSave);
		
		if(setCompPayMembSave.equals("Y")){
			setSaveRateFixedYn = checkStr(request.getParameter("saveRateFixedYn"), "");
			setSaveNotice = checkStr(request.getParameter("saveNotice"), "");
			setCashSaveRate = checkStr(request.getParameter("CashSaveRate"), "");
			setCompSaveRate = checkStr(request.getParameter("compSaveRate"), "");
			setCardSaveRate = checkStr(request.getParameter("CardSaveRate"), "");
			setDebitSaveRate = checkStr(request.getParameter("debitSveRate"), "");
			setEtcSaveRate = checkStr(request.getParameter("etcSaveRate"), "");	
			setSaveRoundType = checkStr(request.getParameter("saveRoundType"), "");
			setDcSavePriceType = checkStr(request.getParameter("dcSavePriceType"), "");
			setSaveMinPayPrice = checkStr(request.getParameter("saveMinPayPrice"), "");
			setSaveMaxPayPrice = checkStr(request.getParameter("saveMaxPayPrice"), "");	
			setMaxSavePoint = checkStr(request.getParameter("maxSavePoint"), "");
			setSaveUnit = checkStr(request.getParameter("saveUnit"), "");
		}
		
		//맴버십 할인여부 Y 선택시
		String setDcRate = "";
		String setDcRoundType = "";
		String setDcUnit = "";
		String setDcMinPayPrice = "";
		String setMaxDcPrice = "";
		
		if(setCompPayMembDc.equals("Y")){
			setDcRate = checkStr(request.getParameter("dcRate"), "");
			setDcRoundType = checkStr(request.getParameter("dcRoundType"), "");
			setDcUnit = checkStr(request.getParameter("dcUnit"), "");
			setDcMinPayPrice = checkStr(request.getParameter("dcMinPayPrice"), "");
			setMaxDcPrice = checkStr(request.getParameter("maxDcPrice"), "");
		}
		

		
		//맴버십 사용여부 Y 선택시
		String setPointUseType = "";
		String setFixedPoint = "";
		String setFixedRate = "";
		String setMaxUsePoint = "";
		String setMinPayPrice = "";
		String setMinUsePoint = "";
		String setPointUseUnit = "";
		String setUseRoundType = "";
		if(setCompPayMembUse.equals("Y")){
			setPointUseType = checkStr(request.getParameter("pointUseType"), "");
			setFixedPoint = checkStr(request.getParameter("fixedPoint"), "");
			setFixedRate = checkStr(request.getParameter("fixedRate"), "");
			setMaxUsePoint = checkStr(request.getParameter("maxUsePoint"), "");
			setMinPayPrice = checkStr(request.getParameter("minPayPrice"), "");
			setMinUsePoint = checkStr(request.getParameter("minUsePoint"), "");
			setPointUseUnit = checkStr(request.getParameter("pointUseUnit"), "");
			setUseRoundType = checkStr(request.getParameter("useRoundType"), "");
		}
		//별 포인트 적립여부 Y선택시
		String setStarSaveNotice = "";
		String setStarCashSaveRate = "";
		String setStarCompSaveRate = "";
		String setStarCardSaveRate = "";
		String setStarEtcSaveRate = "";
		String setStarSaveRoundType = "";
		String setStarDcSavePriceType = "";
		String setStarSaveMinPayPrice = "";
		String setStarSaveMaxPayPrice = "";	
		String setStarMaxSavePoint = "";
		String setStarDebitSaveRate = "";
		String setStarSaveRateFixedYn = "";
		String setStarSaveUnit = "";
		String setSuperStarAddSaveRate = "";
		String setRoyalStarAddSaveRate = "";
		String setMagicStarAddSaveRate = "";
		String setHappyStarAddSaveRate = "";
		System.out.println("setStarPayMembSave : " + setStarPayMembSave);
		
		if(setStarPayMembSave.equals("Y")){
			setStarSaveRateFixedYn = checkStr(request.getParameter("starsaveRateFixedYn"),"");
			setStarSaveNotice = checkStr(request.getParameter("starsaveNotice"),"");
			setStarCashSaveRate = checkStr(request.getParameter("starCashSaveRate"),"");
			setStarCompSaveRate = checkStr(request.getParameter("starcompSaveRate"), "");
			setStarCardSaveRate = checkStr(request.getParameter("starCardSaveRate"), "");
			setStarEtcSaveRate = checkStr(request.getParameter("staretcSaveRate"),"");
			setStarSaveRoundType = checkStr(request.getParameter("starsaveRoundType"), "");
			setStarDcSavePriceType = checkStr(request.getParameter("stardcSavePriceType"), "");
			setStarSaveMinPayPrice = checkStr(request.getParameter("starsaveMinPayPrice"), "");
			setStarSaveMaxPayPrice = checkStr(request.getParameter("starsaveMaxPayPrice"), "");	
			setStarMaxSavePoint = checkStr(request.getParameter("starmaxSavePoint"), "");
			setStarDebitSaveRate = checkStr(request.getParameter("stardebitSaveRate"),"");
			System.out.println("1:"+setStarDebitSaveRate);
			setStarSaveUnit = checkStr(request.getParameter("starsaveUnit"), "");
			setSuperStarAddSaveRate = checkStr(request.getParameter("superStarAddSaveRate"), "");
			setRoyalStarAddSaveRate = checkStr(request.getParameter("royalStarAddSaveRate"), "");
			setMagicStarAddSaveRate = checkStr(request.getParameter("magicStarAddSaveRate"), "");
			setHappyStarAddSaveRate = checkStr(request.getParameter("happyStarAddSaveRate"), "");
		}
		
		//별 포인트 할인여부 Y 선택시
		String setStarDcRate = "";
		String setStarDcRoundType = "";
		String setStarDcUnit = "";
		String setStarDcMinPayPrice = "";
		String setStarMaxDcPrice = "";
		String setSuperStarAddDcRate="";
		String setRoyalStarAddDcRate="";
		String setMagicStarAddDcRate="";
		String setHappyStarAddDcRate="";
		
		if(setStarPayMembDc.equals("Y")){
			setStarDcRate = checkStr(request.getParameter("stardcRate"), "");
			setStarDcRoundType = checkStr(request.getParameter("stardcRoundType"), "");
			setStarDcUnit = checkStr(request.getParameter("stardcUnit"), "");
			setStarDcMinPayPrice = checkStr(request.getParameter("stardcMinPayPrice"), "");
			setStarMaxDcPrice = checkStr(request.getParameter("starmaxDcPrice"), "");
			setSuperStarAddDcRate = checkStr(request.getParameter("superStarAddDcRate"),"");
			setRoyalStarAddDcRate = checkStr(request.getParameter("royalStarAddDcRate"),"");
			setMagicStarAddDcRate = checkStr(request.getParameter("magicStarAddDcRate"),"");
			setHappyStarAddDcRate = checkStr(request.getParameter("happyStarAddDcRate"),"");
		}
		
		//별 포인트 사용여부 Y 선택시
		String setStarPointUseType = "";
		String setStarFixedPoint = "";
		String setStarFixedRate = "";
		String setStarMaxUsePoint = "";
		String setStarMinPayPrice = "";
		String setStarMinUsePoint = "";
		String setStarPointUseUnit = "";
		String setStarUseRoundType = "";
		String setSuperStarAddUseRate = "";
		String setRoyalStarAddUseRate = "";
		String setMagicStarAddUseRate = "";
		String setHappyStarAddUseRate = "";
		
		if(setStarPayMembUse.equals("Y")){
			setStarPointUseType = checkStr(request.getParameter("starpointUseType"), "");
			setStarFixedPoint = checkStr(request.getParameter("starfixedPoint"), "");
			setStarFixedRate = checkStr(request.getParameter("starfixedRate"), "");
			setStarUseRoundType = checkStr(request.getParameter("staruseRoundType"), "");
			setStarPointUseUnit = checkStr(request.getParameter("starpointUseUnit"), "");
			setStarMinPayPrice = checkStr(request.getParameter("starminPayPrice"), "");
			setStarMinUsePoint = checkStr(request.getParameter("starminUsePoint"), "");
			setStarMaxUsePoint = checkStr(request.getParameter("starmaxUsePoint"), "");
			setSuperStarAddUseRate = checkStr(request.getParameter("superStarAddUseRate"),"");
			setRoyalStarAddUseRate = checkStr(request.getParameter("royalStarAddUseRate"),"");
			setMagicStarAddUseRate = checkStr(request.getParameter("magicStarAddUseRate"),"");
			setHappyStarAddUseRate = checkStr(request.getParameter("happyStarAddUseRate"),"");
		}

		try {
			if(compId != null && !compId.equals("")){			
				System.out.println("시작");
				MwMsCompPayMent mwCmCompPayMent = new MwMsCompPayMent();
								
				mwCmCompPayMent.setCardSaveRate(getInteger(setCardSaveRate));//카드적립률
				mwCmCompPayMent.setCashSaveRate(getInteger(setCashSaveRate));//현금적립률
				mwCmCompPayMent.setCompPayCpnYn(setCompPayCpnYn);//복합결제 쿠폰 사용여부
				mwCmCompPayMent.setCompPayMembDc(setCompPayMembDc);//복합결제 멤버십 할인여부
				mwCmCompPayMent.setCompPayMembUse(setCompPayMembUse);//복합결제 맴버십 사용여부
				mwCmCompPayMent.setCompPayMembSave(setCompPayMembSave);//복합결제 맴버십 적립여부				
				mwCmCompPayMent.setCompPayStampSaveYn(setCompPayStampSaveYn);//복합결제 스탬프 적립여부
				mwCmCompPayMent.setCompPayStampYn(setCompPayStampYn);//복합결제 스탬프 사용여부
				mwCmCompPayMent.setCompSaveRate(getInteger(setCompSaveRate));//자사적립률
				mwCmCompPayMent.setDcMinPayPrice(getInteger(setDcMinPayPrice));//할인 가능 최저결제금액
				mwCmCompPayMent.setDcRate(getInteger(setDcRate));//할인률
				mwCmCompPayMent.setDcRoundType(setDcRoundType);//할인근사계산식
				mwCmCompPayMent.setDcSavePriceType(setDcSavePriceType);//할인적립금액구분
				mwCmCompPayMent.setDcUnit(setDcUnit);//할인단위
				
				mwCmCompPayMent.setDebitSaveRate(getInteger(setDebitSaveRate));//직불적립률
				mwCmCompPayMent.setEtcSaveRate(getInteger(setEtcSaveRate));//기타적립률				
				mwCmCompPayMent.setFixedPoint(getInteger(setFixedPoint));//정액포인트
				mwCmCompPayMent.setFixedRate(getInteger(setFixedRate));//정률
				mwCmCompPayMent.setMaxDcPrice(getInteger(setMaxDcPrice));//최대할인금액
				mwCmCompPayMent.setMaxSavePoint(getInteger(setMaxSavePoint));//최대적립가능포인트
				mwCmCompPayMent.setMaxUsePoint(getInteger(setMaxUsePoint));//사용가능최대포인트
				mwCmCompPayMent.setMinPayPrice(getInteger(setMinPayPrice));//포인트사용가능 최저 결제금액
				mwCmCompPayMent.setMinUsePoint(getInteger(setMinUsePoint));//사용가능 최저포인트
				mwCmCompPayMent.setPointUseType(setPointUseType);//포인트 사용구분
				mwCmCompPayMent.setPointUseUnit(setPointUseUnit);//포인트 사용단위
				mwCmCompPayMent.setSaveMaxPayPrice(getInteger(setSaveMaxPayPrice));//적립가능 최대 결제 금액
				mwCmCompPayMent.setSaveMinPayPrice(getInteger(setSaveMinPayPrice));//적립가능 최저 결제 금액
				mwCmCompPayMent.setSaveNotice(setSaveNotice);//포인트 적립안내문
				mwCmCompPayMent.setSaveRateFixedYn(setSaveRateFixedYn);//적립률 고정 여부
				mwCmCompPayMent.setSaveRoundType(setSaveRoundType);//근사계산식
				mwCmCompPayMent.setSaveUnit(setSaveUnit);//적립단위
				mwCmCompPayMent.setUseRoundType(setUseRoundType);//사용근사계산식
				mwCmCompPayMent.setChgDtm(new Date()); // 수정일자
				mwCmCompPayMent.setChgUser(getSessionName(request));//수정 유저
				mwCmCompPayMent.setCompId(compId);//제휴사 아이디
				mwCmCompPayMent.setRegDtm(new Date());//
				mwCmCompPayMent.setRegUser(getSessionName(request));//
				
				System.out.println("별 포인트 등록 시작");
				MwMsStarPayMent mwMsStarPayMent = new MwMsStarPayMent();
				MwMsStarPayMentExample mwMsStarPayMentExample = new MwMsStarPayMentExample();
				mwMsStarPayMentExample.or().andCompIdEqualTo(compId);
				
				mwMsStarPayMent.setStarPointSave(setStarPayMembSave);
				mwMsStarPayMent.setStarPointDc(setStarPayMembDc);
				mwMsStarPayMent.setStarPointUse(setStarPayMembUse);
				mwMsStarPayMent.setSaveRateFixedYn(setStarSaveRateFixedYn);
				mwMsStarPayMent.setCashSaveRate(getInteger(setStarCashSaveRate));
				mwMsStarPayMent.setCardSaveRate(getInteger(setStarCardSaveRate));
				mwMsStarPayMent.setCompSaveRate(getInteger(setStarCompSaveRate));
				mwMsStarPayMent.setDebitSaveRate(getInteger(setStarDebitSaveRate));
				mwMsStarPayMent.setEtcSaveRate(getInteger(setStarEtcSaveRate));
				mwMsStarPayMent.setSaveUnit(setStarSaveUnit);
				mwMsStarPayMent.setSaveRoundType(setStarSaveRoundType);
				mwMsStarPayMent.setDcSavePriceType(setStarDcSavePriceType);
				mwMsStarPayMent.setSaveNotice(setStarSaveNotice);
				mwMsStarPayMent.setSaveMinPayPrice(getInteger(setStarSaveMinPayPrice));
				mwMsStarPayMent.setSaveMaxPayPrice(getInteger(setStarSaveMaxPayPrice));
				mwMsStarPayMent.setMaxSavePoint(getInteger(setStarMaxSavePoint));
				mwMsStarPayMent.setPointUseType(setStarPointUseType);
				mwMsStarPayMent.setPointUseUnit(setStarPointUseUnit);
				mwMsStarPayMent.setUseRoundType(setStarUseRoundType);
				mwMsStarPayMent.setFixedPoint(getInteger(setStarFixedPoint));
				mwMsStarPayMent.setFixedRate(getInteger(setStarFixedRate));
				mwMsStarPayMent.setMinPayPrice(getInteger(setStarMinPayPrice));
				mwMsStarPayMent.setMinUsePoint(getInteger(setStarMinUsePoint));
				mwMsStarPayMent.setMaxUsePoint(getInteger(setStarMaxUsePoint));
				mwMsStarPayMent.setDcRate(getInteger(setStarDcRate));
				mwMsStarPayMent.setDcUnit(setStarDcUnit);
				mwMsStarPayMent.setDcRoundType(setStarDcRoundType);
				mwMsStarPayMent.setDcMinPayPrice(getInteger(setStarDcMinPayPrice));
				mwMsStarPayMent.setMaxDcPrice(getInteger(setStarMaxDcPrice));
				mwMsStarPayMent.setMidShopId(midShopId);
				mwMsStarPayMent.setCompId(compId);
				mwMsStarPayMent.setSuperstarAddUseRate(getInteger(setSuperStarAddUseRate));
				mwMsStarPayMent.setSuperstarAddDcRate(getInteger(setSuperStarAddDcRate));
				mwMsStarPayMent.setSuperstarAddSaveRate(getInteger(setSuperStarAddSaveRate));
				mwMsStarPayMent.setRoyalstarAddUseRate(getInteger(setRoyalStarAddUseRate));
				mwMsStarPayMent.setRoyalstarAddDcRate(getInteger(setRoyalStarAddDcRate));
				mwMsStarPayMent.setRoyalstarAddSaveRate(getInteger(setRoyalStarAddSaveRate));
				mwMsStarPayMent.setMagicstarAddUseRate(getInteger(setMagicStarAddUseRate));
				mwMsStarPayMent.setMagicstarAddDcRate(getInteger(setMagicStarAddDcRate));
				mwMsStarPayMent.setMagicstarAddSaveRate(getInteger(setMagicStarAddSaveRate));
				mwMsStarPayMent.setHappystarAddUseRate(getInteger(setHappyStarAddUseRate));
				mwMsStarPayMent.setHappystarAddDcRate(getInteger(setHappyStarAddDcRate));
				mwMsStarPayMent.setHappystarAddSaveRate(getInteger(setHappyStarAddSaveRate));
				mwMsStarPayMent.setRegUser(getSessionName(request));
				mwMsStarPayMent.setRegDtm(new Date());
				mwMsStarPayMent.setChgUser(getSessionName(request));
				mwMsStarPayMent.setChgDtm(new Date());
				
				System.out.println(setStarPointUseType);
				System.out.println(setStarPointUseType);
				System.out.println(setStarPointUseType);
				System.out.println(setStarPointUseType);
				System.out.println(setStarPointUseType);
				
				mwCmCompPayMentService.insert(mwCmCompPayMent);
				mwCmCompPayMentService.commit();
				
				mwMsStarPayMentService.insert(mwMsStarPayMent);
				mwMsStarPayMentService.commit();

				if(compCode.equals("01")){
					new PartnerComplexUpdate(mwCmCompPayMent, compId);
				}
				 
			}
			
		} catch (Exception ex) {
			mwCmCompPayMentService.rollback();
			mwMsStarPayMentService.rollback();
			System.out.println(ex.getMessage());
			result = "err";
		}
		JsonErrMsg(result,response);
		return null;
	}
	 
	
	/**
	 * 복합결제 수정화면
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/complex_edit.ms")
	public String ComplexEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		String compId = checkStr(request.getParameter("compId"), "");
		String moveId = checkStr(request.getParameter("moveCompId"), "");
		
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
		mwCmCompanyExample.or().andCompIdEqualTo(compId);
		MwCmCompany mwCmCompany = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
		String upperCompId = mwCmCompany.getUpperCompId();
		String CompName="";
		String CompCode="";
		String MidShopId ="";
		if(mwCmCompany!=null){
			CompName = mwCmCompany.getCompName();
		}
		if(compId.matches("CMP.*")){
			CompCode = "01";
			moveId = compId;
		}else if(compId.matches("BRD.*")){
			CompCode = "02";
		}else if(compId.matches("SHP.*")){
			CompCode = "03";
			MidShopId = mwCmCompany.getMidShopId()==null || "".equals(mwCmCompany.getMidShopId())? "" : mwCmCompany.getMidShopId(); //-- 복합결제 제휴사의 가맹점인 경우 중 별포인트 연동정보를 이용할 경우만 가지고 있을 정보임.
		}else if(compId.matches("SAG.*")){
			CompCode = "04";
		}
		
		log.info("CompCode : " + CompCode);
		
		MwMsCompPayMentService mwCmCompPayMentService = new MwMsCompPayMentService();
		MwMsCompPayMentExample mwCmCompPayMentExample = new MwMsCompPayMentExample();
		mwCmCompPayMentExample.or().andCompIdEqualTo(moveId);
		MwMsCompPayMent mwCmCompPayMent = mwCmCompPayMentService.getByExampleOnly(mwCmCompPayMentExample);
		
		MwMsStarPayMentService mwMsStarPayMentService = new MwMsStarPayMentService();
		MwMsStarPayMentExample mwMsStarPayMentExaple = new MwMsStarPayMentExample();
		mwMsStarPayMentExaple.or().andCompIdEqualTo(moveId);
		MwMsStarPayMent mwMsStarPayMent = mwMsStarPayMentService.getByExampleOnly(mwMsStarPayMentExaple);
		/* SET ATTRIBUTE */
		//화면정보
		request.setAttribute("midShopId", MidShopId);
		request.setAttribute("compPayMembSave", mwCmCompPayMent.getCompPayMembSave());//맴버십 적립 여부
		request.setAttribute("compPayMembUse", mwCmCompPayMent.getCompPayMembUse());//
		request.setAttribute("compPayMembDc", mwCmCompPayMent.getCompPayMembDc());//
		request.setAttribute("compPayCpnYn", mwCmCompPayMent.getCompPayCpnYn());
		request.setAttribute("compPayStampYn", mwCmCompPayMent.getCompPayStampYn());
		request.setAttribute("compPayStampSaveYn", mwCmCompPayMent.getCompPayStampSaveYn());
		request.setAttribute("saveRateFixedYn", mwCmCompPayMent.getSaveRateFixedYn());
		request.setAttribute("saveNotice", mwCmCompPayMent.getSaveNotice());
		request.setAttribute("CashSaveRate", mwCmCompPayMent.getCashSaveRate());
		request.setAttribute("compSaveRate", mwCmCompPayMent.getCompSaveRate());
		request.setAttribute("CardSaveRate", mwCmCompPayMent.getCardSaveRate());
		request.setAttribute("debitSveRate", mwCmCompPayMent.getDebitSaveRate());
		request.setAttribute("etcSaveRate", mwCmCompPayMent.getEtcSaveRate());
		request.setAttribute("saveRoundType", mwCmCompPayMent.getSaveRoundType());
		request.setAttribute("dcSavePriceType", mwCmCompPayMent.getDcSavePriceType());
		request.setAttribute("saveMinPayPrice", mwCmCompPayMent.getSaveMinPayPrice());
		request.setAttribute("saveMaxPayPrice", mwCmCompPayMent.getSaveMaxPayPrice());
		request.setAttribute("maxSavePoint", mwCmCompPayMent.getMaxSavePoint());
		
		request.setAttribute("dcRate", mwCmCompPayMent.getDcRate());
		request.setAttribute("dcRoundType", mwCmCompPayMent.getDcRoundType());
		request.setAttribute("dcUnit", mwCmCompPayMent.getDcUnit());			
		request.setAttribute("dcMinPayPrice", mwCmCompPayMent.getDcMinPayPrice());
		request.setAttribute("maxDcPrice", mwCmCompPayMent.getMaxDcPrice());
		
		request.setAttribute("pointUseType", mwCmCompPayMent.getPointUseType());
		request.setAttribute("fixedPoint", mwCmCompPayMent.getFixedPoint());
		request.setAttribute("fixedRate", mwCmCompPayMent.getFixedRate());
		request.setAttribute("useRoundType", mwCmCompPayMent.getUseRoundType());
		request.setAttribute("pointUseUnit", mwCmCompPayMent.getPointUseUnit());
		request.setAttribute("minPayPrice", mwCmCompPayMent.getMinPayPrice());
		request.setAttribute("minUsePoint", mwCmCompPayMent.getMinUsePoint());
		request.setAttribute("maxUsePoint", mwCmCompPayMent.getMaxUsePoint());
		request.setAttribute("saveUnit", mwCmCompPayMent.getSaveUnit());
		
		if(mwMsStarPayMent == null){
			request.setAttribute("starcompPayMembSave", "N");//별 포인트 적립 여부
			request.setAttribute("starPointUse", "N");//
			request.setAttribute("starPointDc", "N");//
		}
		else{
			request.setAttribute("starcompPayMembSave", mwMsStarPayMent.getStarPointSave());//별 포인트 적립 여부
			request.setAttribute("starPointUse", mwMsStarPayMent.getStarPointUse());//
			request.setAttribute("starPointDc", mwMsStarPayMent.getStarPointDc());//
			
			request.setAttribute("starsaveRateFixedYn", mwMsStarPayMent.getSaveRateFixedYn());
			request.setAttribute("starsaveNotice", mwMsStarPayMent.getSaveNotice());
			request.setAttribute("starCashSaveRate", mwMsStarPayMent.getCashSaveRate());
			request.setAttribute("starcompSaveRate", mwMsStarPayMent.getCompSaveRate());
			request.setAttribute("starCardSaveRate", mwMsStarPayMent.getCardSaveRate());
			request.setAttribute("stardebitSaveRate", mwMsStarPayMent.getDebitSaveRate());
			request.setAttribute("staretcSaveRate", mwMsStarPayMent.getEtcSaveRate());
			request.setAttribute("starsaveRoundType", mwMsStarPayMent.getSaveRoundType());
			request.setAttribute("stardcSavePriceType", mwMsStarPayMent.getDcSavePriceType());
			request.setAttribute("starsaveMinPayPrice", mwMsStarPayMent.getSaveMinPayPrice());
			request.setAttribute("starsaveMaxPayPrice", mwMsStarPayMent.getSaveMaxPayPrice());
			request.setAttribute("starmaxSavePoint", mwMsStarPayMent.getMaxSavePoint());
			
			request.setAttribute("superStarAddSaveRate", mwMsStarPayMent.getSuperstarAddSaveRate());
			request.setAttribute("royalStarAddSaveRate", mwMsStarPayMent.getRoyalstarAddSaveRate());
			request.setAttribute("magicStarAddSaveRate", mwMsStarPayMent.getMagicstarAddSaveRate());
			request.setAttribute("happyStarAddSaveRate", mwMsStarPayMent.getHappystarAddSaveRate());
	
			request.setAttribute("stardcRate", mwMsStarPayMent.getDcRate());
			request.setAttribute("stardcRoundType", mwMsStarPayMent.getDcRoundType());
			request.setAttribute("stardcUnit", mwMsStarPayMent.getDcUnit());
			request.setAttribute("stardcMinPayPrice", mwMsStarPayMent.getDcMinPayPrice());
			request.setAttribute("starmaxDcPrice", mwMsStarPayMent.getMaxDcPrice());
			request.setAttribute("superStarAddDcRate", mwMsStarPayMent.getSuperstarAddDcRate());
			request.setAttribute("royalStarAddDcRate", mwMsStarPayMent.getRoyalstarAddDcRate());
			request.setAttribute("magicStarAddDcRate", mwMsStarPayMent.getMagicstarAddDcRate());
			request.setAttribute("happyStarAddDcRate", mwMsStarPayMent.getHappystarAddDcRate());
	
			request.setAttribute("starpointUseType", mwMsStarPayMent.getPointUseType());
			request.setAttribute("starfixedPoint", mwMsStarPayMent.getFixedPoint());
			request.setAttribute("starfixedRate", mwMsStarPayMent.getFixedRate());
			request.setAttribute("staruseRoundType", mwMsStarPayMent.getUseRoundType());
			request.setAttribute("starpointUseUnit", mwMsStarPayMent.getPointUseUnit());
			request.setAttribute("starminPayPrice", mwMsStarPayMent.getMinPayPrice());
			request.setAttribute("starminUsePoint", mwMsStarPayMent.getMinUsePoint());
			request.setAttribute("starmaxUsePoint", mwMsStarPayMent.getMaxUsePoint());
			request.setAttribute("superStarAddUseRate", mwMsStarPayMent.getSuperstarAddUseRate());
			request.setAttribute("royalStarAddUseRate", mwMsStarPayMent.getRoyalstarAddUseRate());
			request.setAttribute("magicStarAddUseRate", mwMsStarPayMent.getMagicstarAddUseRate());
			request.setAttribute("happyStarAddUseRate", mwMsStarPayMent.getHappystarAddUseRate());
			
			request.setAttribute("starsaveUnit", mwMsStarPayMent.getSaveUnit());
		}
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("compId", compId);
			request.setAttribute("moveId", moveId);
			request.setAttribute("compName", CompName);
			request.setAttribute("compCode", CompCode);
			request.setAttribute("upperCompId", upperCompId);
			
			return "member/complex_edit";	
	}
	
	/**
	 * 복합결제 수정 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/complex_edit_up.ms")
	public String ComplexEdit_Move(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		String compId = checkStr(request.getParameter("compId"), "");
		String compCode = checkStr(request.getParameter("compCode"), "");
		String moveId = checkStr(request.getParameter("moveId"), "");
		String midShopId = checkStr(request.getParameter("midShopId"),"");
		String result = "";//결과 메시지
		
		String setCompPayCpnYn = checkStr(request.getParameter("compPayCpnYn"), "");
		String setCompPayStampSaveYn = checkStr(request.getParameter("compPayStampSaveYn"), "");
		String setCompPayStampYn = checkStr(request.getParameter("compPayStampYn"), "");
		
		String setCompPayMembDc = checkStr(request.getParameter("compPayMembDc"), "");
		String setCompPayMembUse =checkStr(request.getParameter("compPayMembUse"), "");
		String setCompPayMembSave = checkStr(request.getParameter("compPayMembSave"), "");	
		
		String setStarPayMembDc = checkStr(request.getParameter("starPointDc"), "");
		String setStarPayMembUse =checkStr(request.getParameter("starPointUseYn"), "");
		String setStarPayMembSave = checkStr(request.getParameter("starcompPayMembSave"), "");	
		
		String setSaveNotice = "";         //포인트 적립 안내문
		String setCashSaveRate = "";       //현금 적립률
		String setCompSaveRate = "";       //자사 적립률
		String setCardSaveRate = "";       //카드 적립률
		String setEtcSaveRate = "";	       //기타 적립률    
		String setSaveRoundType = "01";    //근사 계산식
		String setDcSavePriceType = "01";  //할인 적립 금액 구분 
		String setSaveMinPayPrice = "";    //적립가능 최저 결제 금액
		String setSaveMaxPayPrice = "";	   //적립가능 최대 결제 금액
		String setMaxSavePoint = "";       //최대적립 가능포인트
		String setDebitSaveRate = "";      //직불 적립률
		String setSaveRateFixedYn = "Y";   //적립률 고정 여부
		String setSaveUnit = "01";
		System.out.println("setCompPayMembSave : " + setCompPayMembSave);
		
		//맴버십 적립여부 Y선택시
		if(setCompPayMembSave.equals("Y")){
			setSaveRateFixedYn = checkStr(request.getParameter("saveRateFixedYn"), "");
			setSaveNotice = checkStr(request.getParameter("saveNotice"), "");
			setCashSaveRate = checkStr(request.getParameter("CashSaveRate"), "");
			setCompSaveRate = checkStr(request.getParameter("compSaveRate"), "");
			setCardSaveRate = checkStr(request.getParameter("CardSaveRate"), "");
			setDebitSaveRate = checkStr(request.getParameter("debitSveRate"), "");
			setEtcSaveRate = checkStr(request.getParameter("etcSaveRate"), "");	
			setSaveRoundType = checkStr(request.getParameter("saveRoundType"), "");
			setDcSavePriceType = checkStr(request.getParameter("dcSavePriceType"), "");
			setSaveMinPayPrice = checkStr(request.getParameter("saveMinPayPrice"), "");
			setSaveMaxPayPrice = checkStr(request.getParameter("saveMaxPayPrice"), "");	
			setMaxSavePoint = checkStr(request.getParameter("maxSavePoint"), "");
			setSaveUnit = checkStr(request.getParameter("saveUnit"), "");
		}
		
		//맴버십 할인여부 Y 선택시
		String setDcRate = "";             //할인율
		String setDcRoundType = "01";      //근사 계산식
		String setDcUnit = "01";           //할인단위
		String setDcMinPayPrice = "";      //최저 결제 금액
		String setMaxDcPrice = "";         //최대 할인 금액
		
		if(setCompPayMembDc.equals("Y")){
			setDcRate = checkStr(request.getParameter("dcRate"), "");
			setDcRoundType = checkStr(request.getParameter("dcRoundType"), "");
			setDcUnit = checkStr(request.getParameter("dcUnit"), "");
			setDcMinPayPrice = checkStr(request.getParameter("dcMinPayPrice"), "");
			setMaxDcPrice = checkStr(request.getParameter("maxDcPrice"), "");
		}

		
		//맴버십 사용여부 Y 선택시
		String setPointUseType = "01";     //사용구분
		String setFixedPoint = "";         //정액 포인트
		String setFixedRate = "";          //정률
		String setMaxUsePoint = "";        //사용가능 최대 포인트
		String setMinPayPrice = "";        //사용가능 최저결제 금액
		String setMinUsePoint = "";        //사용가능 최저결제 포인트
		String setPointUseUnit = "01";     //사용단위
		String setUseRoundType = "01";     //근사 계산식
		if(setCompPayMembUse.equals("Y")){
			setPointUseType = checkStr(request.getParameter("pointUseType"), "");
			setFixedPoint = checkStr(request.getParameter("fixedPoint"), "");
			setFixedRate = checkStr(request.getParameter("fixedRate"), "");
			setMaxUsePoint = checkStr(request.getParameter("maxUsePoint"), "");
			setMinPayPrice = checkStr(request.getParameter("minPayPrice"), "");
			setMinUsePoint = checkStr(request.getParameter("minUsePoint"), "");
			setPointUseUnit = checkStr(request.getParameter("pointUseUnit"), "");			
			setUseRoundType = checkStr(request.getParameter("useRoundType"), "");
		}
		
		//별 포인트 적립여부 Y선택시
		String setStarSaveNotice = "";
		String setStarCashSaveRate = "";
		String setStarCompSaveRate = "";
		String setStarCardSaveRate = "";
		String setStarEtcSaveRate = "";
		String setStarSaveRoundType = "";
		String setStarDcSavePriceType = "";
		String setStarSaveMinPayPrice = "";
		String setStarSaveMaxPayPrice = "";	
		String setStarMaxSavePoint = "";
		String setStarDebitSaveRate = "";
		String setStarSaveRateFixedYn = "";
		String setStarSaveUnit = "";
		String setSuperStarAddSaveRate = "";
		String setRoyalStarAddSaveRate = "";
		String setMagicStarAddSaveRate = "";
		String setHappyStarAddSaveRate = "";
		System.out.println("setStarPayMembSave : " + setStarPayMembSave);
		
		if(setStarPayMembSave.equals("Y")){
			setStarSaveRateFixedYn = checkStr(request.getParameter("starsaveRateFixedYn"),"");
			setStarSaveNotice = checkStr(request.getParameter("starsaveNotice"),"");
			setStarCashSaveRate = checkStr(request.getParameter("starCashSaveRate"),"");
			setStarCompSaveRate = checkStr(request.getParameter("starcompSaveRate"), "");
			setStarCardSaveRate = checkStr(request.getParameter("starCardSaveRate"), "");
			setStarEtcSaveRate = checkStr(request.getParameter("staretcSaveRate"),"");
			setStarSaveRoundType = checkStr(request.getParameter("starsaveRoundType"), "");
			setStarDcSavePriceType = checkStr(request.getParameter("stardcSavePriceType"), "");
			setStarSaveMinPayPrice = checkStr(request.getParameter("starsaveMinPayPrice"), "");
			setStarSaveMaxPayPrice = checkStr(request.getParameter("starsaveMaxPayPrice"), "");	
			setStarMaxSavePoint = checkStr(request.getParameter("starmaxSavePoint"), "");
			setStarDebitSaveRate = checkStr(request.getParameter("stardebitSaveRate"),"");
			System.out.println("1:"+setStarDebitSaveRate);
			setStarSaveUnit = checkStr(request.getParameter("starsaveUnit"), "");
			setSuperStarAddSaveRate = checkStr(request.getParameter("superStarAddSaveRate"), "");
			setRoyalStarAddSaveRate = checkStr(request.getParameter("royalStarAddSaveRate"), "");
			setMagicStarAddSaveRate = checkStr(request.getParameter("magicStarAddSaveRate"), "");
			setHappyStarAddSaveRate = checkStr(request.getParameter("happyStarAddSaveRate"), "");
		}
		
		//별 포인트 할인여부 Y 선택시
		String setStarDcRate = "";
		String setStarDcRoundType = "";
		String setStarDcUnit = "";
		String setStarDcMinPayPrice = "";
		String setStarMaxDcPrice = "";
		String setSuperStarAddDcRate="";
		String setRoyalStarAddDcRate="";
		String setMagicStarAddDcRate="";
		String setHappyStarAddDcRate="";
		
		if(setStarPayMembDc.equals("Y")){
			setStarDcRate = checkStr(request.getParameter("stardcRate"), "");
			setStarDcRoundType = checkStr(request.getParameter("stardcRoundType"), "");
			setStarDcUnit = checkStr(request.getParameter("stardcUnit"), "");
			setStarDcMinPayPrice = checkStr(request.getParameter("stardcMinPayPrice"), "");
			setStarMaxDcPrice = checkStr(request.getParameter("starmaxDcPrice"), "");
			setSuperStarAddDcRate = checkStr(request.getParameter("superStarAddDcRate"),"");
			setRoyalStarAddDcRate = checkStr(request.getParameter("royalStarAddDcRate"),"");
			setMagicStarAddDcRate = checkStr(request.getParameter("magicStarAddDcRate"),"");
			setHappyStarAddDcRate = checkStr(request.getParameter("happyStarAddDcRate"),"");
		}
		
		//별 포인트 사용여부 Y 선택시
		String setStarPointUseType = "";
		String setStarFixedPoint = "";
		String setStarFixedRate = "";
		String setStarMaxUsePoint = "";
		String setStarMinPayPrice = "";
		String setStarMinUsePoint = "";
		String setStarPointUseUnit = "";
		String setStarUseRoundType = "";
		String setSuperStarAddUseRate = "";
		String setRoyalStarAddUseRate = "";
		String setMagicStarAddUseRate = "";
		String setHappyStarAddUseRate = "";
		
		if(setStarPayMembUse.equals("Y")){
			setStarPointUseType = checkStr(request.getParameter("starpointUseType"), "");
			setStarFixedPoint = checkStr(request.getParameter("starfixedPoint"), "");
			setStarFixedRate = checkStr(request.getParameter("starfixedRate"), "");
			setStarMaxUsePoint = checkStr(request.getParameter("starmaxUsePoint"), "");
			setStarMinPayPrice = checkStr(request.getParameter("starminPayPrice"), "");
			setStarMinUsePoint = checkStr(request.getParameter("starminUsePoint"), "");
			setStarPointUseUnit = checkStr(request.getParameter("starpointUseUnit"), "");
			setStarUseRoundType = checkStr(request.getParameter("staruseRoundType"), "");
			setSuperStarAddUseRate = checkStr(request.getParameter("superStarAddUseRate"),"");
			setRoyalStarAddUseRate = checkStr(request.getParameter("royalStarAddUseRate"),"");
			setMagicStarAddUseRate = checkStr(request.getParameter("magicStarAddUseRate"),"");
			setHappyStarAddUseRate = checkStr(request.getParameter("happyStarAddUseRate"),"");
		}
		

		
		
		System.out.println(setSaveNotice);
		System.out.println(setStarSaveNotice);
		
		MwMsCompPayMentService mwCmCompPayMentService = new MwMsCompPayMentService();
		MwMsStarPayMentService mwMsStarPayMentService = new MwMsStarPayMentService();
		
		try{
			if(compId != null && !compId.equals("")){			
				MwMsCompPayMentExample mwCmCompPayMentExample = new MwMsCompPayMentExample();
				mwCmCompPayMentExample.or().andCompIdEqualTo(compId);
				
				MwMsCompPayMent mwCmCompPayMent = new MwMsCompPayMent();
				
				mwCmCompPayMent.setCardSaveRate(getInteger(setCardSaveRate));//카드적립률
				mwCmCompPayMent.setCashSaveRate(getInteger(setCashSaveRate));//현금적립률
				mwCmCompPayMent.setCompPayCpnYn(setCompPayCpnYn);//복합결제 쿠폰 사용여부
				mwCmCompPayMent.setCompPayMembDc(setCompPayMembDc);//복합결제 멤버십 할인여부
				mwCmCompPayMent.setCompPayMembUse(setCompPayMembUse);//복합결제 맴버십 사용여부
				mwCmCompPayMent.setCompPayMembSave(setCompPayMembSave);//복합결제 맴버십 적립여부				
				mwCmCompPayMent.setCompPayStampSaveYn(setCompPayStampSaveYn);//복합결제 스탬프 적립여부
				mwCmCompPayMent.setCompPayStampYn(setCompPayStampYn);//복합결제 스탬프 사용여부
				mwCmCompPayMent.setCompSaveRate(getInteger(setCompSaveRate));//자사적립률
				mwCmCompPayMent.setDcMinPayPrice(getInteger(setDcMinPayPrice));//할인 가능 최저결제금액
				mwCmCompPayMent.setDcRate(getInteger(setDcRate));//할인률
				mwCmCompPayMent.setDcRoundType(setDcRoundType);//할인근사계산식
				mwCmCompPayMent.setDcSavePriceType(setDcSavePriceType);//할인적립금액구분
				mwCmCompPayMent.setDcUnit(setDcUnit);//할인단위
				mwCmCompPayMent.setDebitSaveRate(getInteger(setDebitSaveRate));//직불적립률
				mwCmCompPayMent.setEtcSaveRate(getInteger(setEtcSaveRate));//기타적립률				
				mwCmCompPayMent.setFixedPoint(getInteger(setFixedPoint));//정액포인트
				mwCmCompPayMent.setFixedRate(getInteger(setFixedRate));//정률
				mwCmCompPayMent.setMaxDcPrice(getInteger(setMaxDcPrice));//최대할인금액
				mwCmCompPayMent.setMaxSavePoint(getInteger(setMaxSavePoint));//최대적립가능포인트
				mwCmCompPayMent.setMaxUsePoint(getInteger(setMaxUsePoint));//사용가능최대포인트
				mwCmCompPayMent.setMinPayPrice(getInteger(setMinPayPrice));//포인트사용가능 최저 결제금액
				mwCmCompPayMent.setMinUsePoint(getInteger(setMinUsePoint));//사용가능 최저포인트
				mwCmCompPayMent.setPointUseType(setPointUseType);//포인트 사용구분
				mwCmCompPayMent.setPointUseUnit(setPointUseUnit);//포인트 사용단위
				mwCmCompPayMent.setSaveMaxPayPrice(getInteger(setSaveMaxPayPrice));//적립가능 최대 결제 금액
				mwCmCompPayMent.setSaveMinPayPrice(getInteger(setSaveMinPayPrice));//적립가능 최저 결제 금액
				mwCmCompPayMent.setSaveNotice(setSaveNotice);//포인트 적립안내문
				mwCmCompPayMent.setSaveRateFixedYn(setSaveRateFixedYn);//적립률 고정 여부
				mwCmCompPayMent.setSaveRoundType(setSaveRoundType);//근사계산식
				mwCmCompPayMent.setSaveUnit(setSaveUnit);//적립단위
				mwCmCompPayMent.setUseRoundType(setUseRoundType);//사용근사계산식
				
				System.out.println(setSaveNotice);
				
				System.out.println("별 포인트 수정 시작");
				MwMsStarPayMentExample mwMsStarPayMentExample = new MwMsStarPayMentExample();
				//MwMsStarPayMentService mwMsStarPayMentService = new MwMsStarPayMentService();
				mwMsStarPayMentExample.or().andCompIdEqualTo(compId);
				MwMsStarPayMent mwMsStarPayMent = mwMsStarPayMentService.getByExampleOnly(mwMsStarPayMentExample);
				
				if( !compId.equals("") && mwMsStarPayMent == null) {
					mwMsStarPayMent = new MwMsStarPayMent();
					mwMsStarPayMent.setCompId(compId);
					mwMsStarPayMent.setStarPointSave(setStarPayMembSave);
					mwMsStarPayMent.setStarPointDc(setStarPayMembDc);
					mwMsStarPayMent.setStarPointUse(setStarPayMembUse);
					mwMsStarPayMent.setSaveRateFixedYn(setStarSaveRateFixedYn);
					mwMsStarPayMent.setCashSaveRate(getInteger(setStarCashSaveRate));
					mwMsStarPayMent.setCardSaveRate(getInteger(setStarCardSaveRate));
					mwMsStarPayMent.setCompSaveRate(getInteger(setStarCompSaveRate));
					mwMsStarPayMent.setDebitSaveRate(getInteger(setStarDebitSaveRate));
					mwMsStarPayMent.setEtcSaveRate(getInteger(setStarEtcSaveRate));
					mwMsStarPayMent.setSaveUnit(setStarSaveUnit);
					mwMsStarPayMent.setSaveRoundType(setStarSaveRoundType);
					mwMsStarPayMent.setDcSavePriceType(setStarDcSavePriceType);
					mwMsStarPayMent.setSaveNotice(setStarSaveNotice);
					mwMsStarPayMent.setSaveMinPayPrice(getInteger(setStarSaveMinPayPrice));
					mwMsStarPayMent.setSaveMaxPayPrice(getInteger(setStarSaveMaxPayPrice));
					mwMsStarPayMent.setMaxSavePoint(getInteger(setStarMaxSavePoint));
					mwMsStarPayMent.setPointUseType(setStarPointUseType);
					mwMsStarPayMent.setPointUseUnit(setStarPointUseUnit);
					mwMsStarPayMent.setUseRoundType(setStarUseRoundType);
					mwMsStarPayMent.setFixedPoint(getInteger(setStarFixedPoint));
					mwMsStarPayMent.setFixedRate(getInteger(setStarFixedRate));
					mwMsStarPayMent.setMinPayPrice(getInteger(setStarMinPayPrice));
					mwMsStarPayMent.setMinUsePoint(getInteger(setStarMinUsePoint));
					mwMsStarPayMent.setMaxUsePoint(getInteger(setStarMaxUsePoint));
					mwMsStarPayMent.setDcRate(getInteger(setStarDcRate));
					mwMsStarPayMent.setDcUnit(setStarDcUnit);
					mwMsStarPayMent.setDcRoundType(setStarDcRoundType);
					mwMsStarPayMent.setDcMinPayPrice(getInteger(setStarDcMinPayPrice));
					mwMsStarPayMent.setMaxDcPrice(getInteger(setStarMaxDcPrice));
					mwMsStarPayMent.setMidShopId(midShopId);
					mwMsStarPayMent.setCompId(compId);
					mwMsStarPayMent.setSuperstarAddUseRate(getInteger(setSuperStarAddUseRate));
					mwMsStarPayMent.setSuperstarAddDcRate(getInteger(setSuperStarAddDcRate));
					mwMsStarPayMent.setSuperstarAddSaveRate(getInteger(setSuperStarAddSaveRate));
					mwMsStarPayMent.setRoyalstarAddUseRate(getInteger(setRoyalStarAddUseRate));
					mwMsStarPayMent.setRoyalstarAddDcRate(getInteger(setRoyalStarAddDcRate));
					mwMsStarPayMent.setRoyalstarAddSaveRate(getInteger(setRoyalStarAddSaveRate));
					mwMsStarPayMent.setMagicstarAddUseRate(getInteger(setMagicStarAddUseRate));
					mwMsStarPayMent.setMagicstarAddDcRate(getInteger(setMagicStarAddDcRate));
					mwMsStarPayMent.setMagicstarAddSaveRate(getInteger(setMagicStarAddSaveRate));
					mwMsStarPayMent.setHappystarAddUseRate(getInteger(setHappyStarAddUseRate));
					mwMsStarPayMent.setHappystarAddDcRate(getInteger(setHappyStarAddDcRate));
					mwMsStarPayMent.setHappystarAddSaveRate(getInteger(setHappyStarAddSaveRate));
					mwMsStarPayMent.setRegUser(getSessionName(request));
					mwMsStarPayMent.setRegDtm(new Date());
					
					mwMsStarPayMentService.insert(mwMsStarPayMent);
					
				} else if(!compId.equals("")  && mwMsStarPayMent.getCompId() != null){
					mwMsStarPayMent.setStarPointSave(setStarPayMembSave);
					mwMsStarPayMent.setStarPointDc(setStarPayMembDc);
					mwMsStarPayMent.setStarPointUse(setStarPayMembUse);
					mwMsStarPayMent.setSaveRateFixedYn(setStarSaveRateFixedYn);
					mwMsStarPayMent.setCashSaveRate(getInteger(setStarCashSaveRate));
					mwMsStarPayMent.setCardSaveRate(getInteger(setStarCardSaveRate));
					mwMsStarPayMent.setCompSaveRate(getInteger(setStarCompSaveRate));
					mwMsStarPayMent.setDebitSaveRate(getInteger(setStarDebitSaveRate));
					mwMsStarPayMent.setEtcSaveRate(getInteger(setStarEtcSaveRate));
					mwMsStarPayMent.setSaveUnit(setStarSaveUnit);
					mwMsStarPayMent.setSaveRoundType(setStarSaveRoundType);
					mwMsStarPayMent.setDcSavePriceType(setStarDcSavePriceType);
					mwMsStarPayMent.setSaveNotice(setStarSaveNotice);
					mwMsStarPayMent.setSaveMinPayPrice(getInteger(setStarSaveMinPayPrice));
					mwMsStarPayMent.setSaveMaxPayPrice(getInteger(setStarSaveMaxPayPrice));
					mwMsStarPayMent.setMaxSavePoint(getInteger(setStarMaxSavePoint));
					mwMsStarPayMent.setPointUseType(setStarPointUseType);
					mwMsStarPayMent.setPointUseUnit(setStarPointUseUnit);
					mwMsStarPayMent.setUseRoundType(setStarUseRoundType);
					mwMsStarPayMent.setFixedPoint(getInteger(setStarFixedPoint));
					mwMsStarPayMent.setFixedRate(getInteger(setStarFixedRate));
					mwMsStarPayMent.setMinPayPrice(getInteger(setStarMinPayPrice));
					mwMsStarPayMent.setMinUsePoint(getInteger(setStarMinUsePoint));
					mwMsStarPayMent.setMaxUsePoint(getInteger(setStarMaxUsePoint));
					mwMsStarPayMent.setDcRate(getInteger(setStarDcRate));
					mwMsStarPayMent.setDcUnit(setStarDcUnit);
					mwMsStarPayMent.setDcRoundType(setStarDcRoundType);
					mwMsStarPayMent.setDcMinPayPrice(getInteger(setStarDcMinPayPrice));
					mwMsStarPayMent.setMaxDcPrice(getInteger(setStarMaxDcPrice));
					mwMsStarPayMent.setMidShopId(midShopId);
					mwMsStarPayMent.setCompId(compId);
					mwMsStarPayMent.setSuperstarAddUseRate(getInteger(setSuperStarAddUseRate));
					mwMsStarPayMent.setSuperstarAddDcRate(getInteger(setSuperStarAddDcRate));
					mwMsStarPayMent.setSuperstarAddSaveRate(getInteger(setSuperStarAddSaveRate));
					mwMsStarPayMent.setRoyalstarAddUseRate(getInteger(setRoyalStarAddUseRate));
					mwMsStarPayMent.setRoyalstarAddDcRate(getInteger(setRoyalStarAddDcRate));
					mwMsStarPayMent.setRoyalstarAddSaveRate(getInteger(setRoyalStarAddSaveRate));
					mwMsStarPayMent.setMagicstarAddUseRate(getInteger(setMagicStarAddUseRate));
					mwMsStarPayMent.setMagicstarAddDcRate(getInteger(setMagicStarAddDcRate));
					mwMsStarPayMent.setMagicstarAddSaveRate(getInteger(setMagicStarAddSaveRate));
					mwMsStarPayMent.setHappystarAddUseRate(getInteger(setHappyStarAddUseRate));
					mwMsStarPayMent.setHappystarAddDcRate(getInteger(setHappyStarAddDcRate));
					mwMsStarPayMent.setHappystarAddSaveRate(getInteger(setHappyStarAddSaveRate));
					
					mwMsStarPayMent.setChgUser(getSessionName(request));
					mwMsStarPayMent.setChgDtm(new Date());
					mwMsStarPayMentService.update(mwMsStarPayMent,mwMsStarPayMentExample); 

				}
				
				System.out.println(setStarSaveNotice);
				if(moveId.equals(compId)){
					mwCmCompPayMent.setChgDtm(new Date()); // 수정일자
					mwCmCompPayMent.setChgUser(getSessionName(request));//수정 유저
					mwCmCompPayMentService.update(mwCmCompPayMent,mwCmCompPayMentExample);
					
				}else{
					mwCmCompPayMent.setCompId(compId);
					mwCmCompPayMent.setRegDtm(new Date());//
					mwCmCompPayMent.setRegUser(getSessionName(request));//
					mwCmCompPayMentService.insert(mwCmCompPayMent);
				}
				
				mwCmCompPayMentService.commit();
				mwMsStarPayMentService.commit();
				
				if(compCode.equals("01")){
					new PartnerComplexUpdate(mwCmCompPayMent, compId);
					
				}else if(compCode.equals("03")){
					MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
					MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
					mwCmCompanyExample.or().andACompIdEqualTo(compId);		
					MwCmCompany getBraindId = mwCmCompanyService.getByStoreExampleOnly(mwCmCompanyExample);
					MwMsStarPayMent getOllehStar = mwMsStarPayMentService.getByExampleOnly(mwMsStarPayMentExample);
					
					String Brand_Id = getBraindId.getFranchiseId();
					String ollehUseYn = getOllehStar.getStarPointUse();
					String ollehDcYn = getOllehStar.getStarPointDc();
					String ollehSaveYn = getOllehStar.getStarPointSave();
					
						UpdateStore updating = new UpdateStore();
						if(!updating.update(compId, Brand_Id, ollehDcYn,ollehUseYn,ollehSaveYn, "U")){
							mwCmCompanyService.rollback();
							mwMsStarPayMentService.rollback();
							JsonErrMsg("err",response);
							return null;
						}			
				}
			}		
		}catch (Exception e) {
			// TODO: handle exception
			mwCmCompPayMentService.rollback();
			mwMsStarPayMentService.rollback();
			result = "err";
		}

	JsonErrMsg(result,response);
	return null;
	}
	
	private Integer getInteger(String Number){
		Integer result = 0;
		try{
			result=Integer.parseInt(Number);
		}catch (Exception e) {
			result = 0;
		}
		return result;
	}

	private String covInteger(int Number){
		String result = "";

		if(Number != 0){
			result = ""+Number;
			
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
}
