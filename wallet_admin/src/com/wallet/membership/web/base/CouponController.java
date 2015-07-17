package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.BARCODE_GEN;
import com.wallet.membership.common.BarcodeUtil;
import com.wallet.membership.common.ExcelContoller;
import com.wallet.membership.common.UpFile;
import com.wallet.membership.model.CpnListAll;
import com.wallet.membership.model.MwCmImage;
import com.wallet.membership.model.MwCmImageExample;
import com.wallet.membership.model.MwCsBulkCpn;
import com.wallet.membership.model.MwCsCpnregHist;
import com.wallet.membership.model.UserInfo;
import com.wallet.membership.model.custom.Coupon;
import com.wallet.membership.model.custom.CouponIssue;
import com.wallet.membership.service.MwCmImageService;
import com.wallet.membership.service.custom.ComplexCouponService;
import com.wallet.membership.service.custom.CouponService;
import com.wallet.membership.service.custom.MemberService;

@Controller
public class CouponController extends CommonController {
	private final String PAGE_CODE = "COUPON_PROCESS";
	private Logger log = Log.getLogger("logs");
	private MwAdAccessLogService logSVC = new MwAdAccessLogService();
	KTDBCipher ktService = new KTDBCipher();
	
	/**
	 * 쿠폰 리스트 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_list.ms")
	public String couponList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponList ##########");

		// 파라메터 초기화 
		String partV = checkStr(request.getParameter("partV"), "%");
		String compName = checkStr(request.getParameter("compName"), "");
		String cpnName = checkStr(request.getParameter("cpnName"), "");
		String barIssueType = checkStr(request.getParameter("barIssueType"), "%");
		String barConfType = checkStr(request.getParameter("barConfType"), "%");
		String cpnValidYn = checkStr(request.getParameter("cpnValidYn"), "%");
		String cpnIssueStat = checkStr(request.getParameter("cpnIssueStat"), "%");
		String mainDisplay = checkStr(request.getParameter("mainDisplay"), "%");
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("partV", partV);									//쿠폰구분
		params.put("compName", compName);						//제휴사명
		params.put("cpnName", cpnName);							//쿠폰명
		params.put("barIssueType", barIssueType);		//쿠폰번호유형
		params.put("barConfType", barConfType);			//쿠폰승인여부
		params.put("cpnValidYn", cpnValidYn);				//유효상태
		params.put("cpnIssueStat", cpnIssueStat);		//쿠폰발행상태
		params.put("mainDisplay", mainDisplay);			//쿠폰전시상태
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d"));
		
		String today = today();
		String sdate = checkStr(request.getParameter("sdate"), today);
		String edate = checkStr(request.getParameter("edate"), today);

		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", sdate.replace("-", ""));
			params.put("edate", edate.replace("-", ""));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// 목록 조회 //
		CouponService couponService = new CouponService();
		List<Coupon> couponList = couponService.selectCouponList(params); 	// 목록조회
		int couponListCnt = couponService.selectCouponListCnt(params); 		// 총 목록 수
		System.out.println(">> couponList : "+couponList);
		

		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, couponListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// 파라메타 변수 그대로 넘기기
		request.setAttribute("partV", partV);
		request.setAttribute("compName", compName);
		request.setAttribute("cpnName", cpnName);
		request.setAttribute("barIssueType", barIssueType);
		request.setAttribute("barConfType", barConfType);
		request.setAttribute("cpnValidYn", cpnValidYn);
		request.setAttribute("cpnIssueStat", cpnIssueStat);
		request.setAttribute("mainDisplay", mainDisplay);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		
		// 결과 넘기기
		request.setAttribute("dataList", couponList);
		
		
		return "member/coupon_list";
	}
	

	/**
	 * 쿠폰발행정보 [팝업]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_issue_pop.ms")
	public String couponIssuePop(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponIssuePop ##########");

		// 파라메터 초기화 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		String excel = checkStr(request.getParameter("excel"), "N");
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO 삭제 /////////////////////////////////////////////////////////////
		
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);									//쿠폰Id

		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1;
		// 엑셀 호출일 경우 페이징 처리 안함
		if(!"Y".equals(excel)) {
			params.put("startRow", String.valueOf(startRow));
			params.put("endRow", String.valueOf(endRow));
			params.put("rowsPerPage", String.valueOf(rowsPerPage));
		}
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		// 목록 조회 //
		CouponService couponService = new CouponService();
		List<CouponIssue> dataList = couponService.selectCouponIssueList(params); 	// 쿠폰발행정보 조회
		for(int i=0; i<dataList.size(); i++){
			String userNm = dataList.get(i).getUserName();
			userNm = ktService.decoding(userNm);
			dataList.get(i).setUserName(userNm);
		}
		int listCnt = couponService.selectCouponIssueListCnt(params); 		// 총 개수
		System.out.println(">> dataList : "+dataList);
		

		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// 파라메타 변수 그대로 넘기기
		request.setAttribute("cpnId", cpnId);
		
		// 결과 넘기기
		request.setAttribute("dataList", dataList);
		
		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/coupon_issue_pop.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		
		
		String target = "member/coupon_issue_pop";
		// 엑셀 저장일 경우.. 경로 변경 
		if("Y".equals(excel))
			target = "member/coupon_issue_excel";
		return target;
	}
	

	/**
	 * 멤버십 쿠폰 등록
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_regist.ms")
	public String couponRegist(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponRegist ##########");

		// 등록자 - TODO 세션 처리
		String regUser = getSessionMgrId(request);
		String regUserNm = getSessionName(request);
		Date regDtm = new Date();
		String regDtmStr = new SimpleDateFormat("yyyy-MM-dd").format(regDtm);

		String insert = checkStr(request.getParameter("insert"), "");
		if("Y".equals(insert)) {	//저장시
			// 파라메터 초기화 
			String partV = checkStr(request.getParameter("partV"), "");	// 쿠폰 대구분
			String compId = checkStr(request.getParameter("compId"), "");	// 제휴사ID
			String branId = checkStr(request.getParameter("branId"), "");	// 브랜드ID
			String membId = checkStr(request.getParameter("membId"), "");	// 멤버십ID

			String manualCpnId = checkStr(request.getParameter("manualCpnId"), "");	// 수동입력 쿠폰 ID
			String isDupCpnId = checkStr(request.getParameter("isDupCpnId"), "");	// 수동입력 쿠폰 ID
			
			String cpnName = checkStr(request.getParameter("cpnName"), "");	// 쿠폰명
			String useName = checkStr(request.getParameter("useName"), "");	// 사용처
			String payFreeYn = checkStr(request.getParameter("rpayFreeYn"), "");	// 쿠폰종류 (유무료구분)
			String usimMode = checkStr(request.getParameter("usimMode"), "");	// USIM쿠폰구분
			String usimId = checkStr(request.getParameter("usimId"), "");	// USIM ID
			String barConfType = checkStr(request.getParameter("rbarConfType"), "");	// 쿠폰승인구분
			String cpnIssueType = checkStr(request.getParameter("cpnIssueType"), "");	// 쿠폰발행유형
			String autoIssueTarget = checkStr(request.getParameter("autoIssueTarget"), "");	// 타겟발행유형설정
			
			String autoIssueSday = "";
			String autoIssueEday = "";
			if(cpnIssueType.equals("04")){ //--쿠폰 발행 유형(04:이벤트 쿠폰_
				autoIssueSday = checkStr(request.getParameter("eventIssueSday"), "").replaceAll("-", "");	// 다운로드 가능 신규가입기간 시작일
				autoIssueEday = checkStr(request.getParameter("eventIssueEday"), "").replaceAll("-", "");	// 다운로드 가능 신규가입기간 종료일
				autoIssueTarget = "";
			}else{
				autoIssueSday = checkStr(request.getParameter("autoIssueSday"), "").replaceAll("-", "");	// 자동등록기간 시작일
				autoIssueEday = checkStr(request.getParameter("autoIssueEday"), "").replaceAll("-", "");	// 자동등록기간 종료일
			}
			
			String barIssueType = checkStr(request.getParameter("barIssueType"), "");	// 쿠폰번호 발행구분
			String cmConfBarcd = checkStr(request.getParameter("cmConfBarcd"), "");	// 공통인증쿠폰번호
			String compPayYn = checkStr(request.getParameter("compPayYn"), "");	// 복합결제 사용구분
			String mainDisplay = checkStr(request.getParameter("mainDisplay"), "");	// 쿠폰전시구분
			if(cpnIssueType.equals("02") || cpnIssueType.equals("01")){  //--쿠폰 발행 유형(01:Admin 수동 발행, 02:자동 발행)
				mainDisplay = "N";
			}
			String sday = checkStr(request.getParameter("sday"), "").replaceAll("-", "");	// 쿠폰배포기간 시작일
			String eday = checkStr(request.getParameter("eday"), "").replaceAll("-", "");	// 쿠폰배포기간 종료일
			String valSday = checkStr(request.getParameter("valSday"), "").replaceAll("-", "");	// 쿠폰유효기간 시작일
			String valEday = checkStr(request.getParameter("valEday"), "").replaceAll("-", "");	// 쿠폰유효기간 종료일
			String info = checkStr(request.getParameter("info"), "");	// 쿠폰상세설명
			String cpnMaxIssueCnt = checkStr(request.getParameter("cpnMaxIssueCnt"), "");	// 쿠폰최대배포량
			String cpnMaxUseCnt = checkStr(request.getParameter("cpnMaxUseCnt"), "");	//쿠폰최대사용회수
			String usableCntYn = checkStr(request.getParameter("usableCntYn"), "");	// 사용회수 사용구분
			String yearUsableCnt = checkStr(request.getParameter("yearUsableCnt"), "");	// 연 사용 가능횟수
			String monthUsableCnt = checkStr(request.getParameter("monthUsableCnt"), "");	// 월 사용 가능횟수
			String dayUsableCnt = checkStr(request.getParameter("dayUsableCnt"), "");	// 일 사용 가능횟수
			String dayIssueYn = checkStr(request.getParameter("dayIssueYn"), "");	// 일배포수량 사용구분
			String dayIssueMaxCnt = checkStr(request.getParameter("dayIssueMaxCnt"), "");	// 일배포수량
			String dayIssueStime = checkStr(request.getParameter("dayIssueStime"), "");	// 일배포 시작범위 시작(시간)
			String dayIssueSmin = checkStr(request.getParameter("dayIssueSmin"), "");	// 일배포 시작범위 시작(분)
			String dayIssueEtime = checkStr(request.getParameter("dayIssueEtime"), "");	// 일배포 시작범위 종료(시간)
			String dayIssueEmin = checkStr(request.getParameter("dayIssueEmin"), "");	// 일배포 시작범위 종료(분)
			String onoffType = checkStr(request.getParameter("onoffType"), "");	// 쿠폰 온/오프라인 사용구분
			String onlineUrl = checkStr(request.getParameter("onlineUrl"), "");	//온라인URL
			String usimCompId = checkStr(request.getParameter("usimCompId"), ""); //USIM 제휴사ID
			String usimBranId = checkStr(request.getParameter("usimBranId"), ""); //USIM 계열사ID
			String membCompulsoryYn = checkStr(request.getParameter("membCompulsoryYn"), ""); // 멤버십 쿠폰 사용제한
			// 이미지 START
//			String i3gsList = checkStr(request.getParameter("i3gsList"), "");	// 쿠폰이미지 리스트 이미지(3GS)
//			String i4sList = checkStr(request.getParameter("i4sList"), "");	// 쿠폰이미지 리스트 이미지(4S)
//			String a1List = checkStr(request.getParameter("a1List"), "");	// 쿠폰이미지 리스트 이미지(안드로이드)
//			String i3gsDetail = checkStr(request.getParameter("i3gsDetail"), "");	// 쿠폰이미지 상세 이미지(3GS)
//			String i4sDetail = checkStr(request.getParameter("i4sDetail"), "");	// 쿠폰이미지 상세 이미지(4S)
//			String a1Detail = checkStr(request.getParameter("a1Detail"), "");	// 쿠폰이미지 상세 이미지(안드로이드)
//			String i3gsIntro = checkStr(request.getParameter("i3gsIntro"), "");	// 쿠폰이미지 쿠폰소개 이미지(3GS)
//			String i4sIntro = checkStr(request.getParameter("i4sIntro"), "");	// 쿠폰이미지 쿠폰소개 이미지(4S)
//			String a1Intro = checkStr(request.getParameter("a1Intro"), "");	// 쿠폰이미지 쿠폰소개 이미지(안드로이드)
			// 이미지 END
			String calculYn = checkStr(request.getParameter("calculYn"), "");	// 쿠폰정산여부
			String memo = checkStr(request.getParameter("memo"), "");	// 메모
			String recoverYn = checkStr(request.getParameter("recoverYn"), "");	// 복원가능여부
			
			String isTargetCpn = checkStr(request.getParameter("isTargetCpn"), "N"); // 타겟쿠폰 여부 , 추가되면서 기본 쿠폰의 경우 N 값이 들어가도록 설정함.
			
			// TODO 삭제 /////////////////////////////////////////////////////////////
			System.out.println("partV : "+ partV);	// 대구분
			System.out.println("compId : "+ compId);	// 제휴사ID
			System.out.println("branId : "+ branId);	// 브랜드ID
			System.out.println("membId : "+ membId);	// 멤버십ID
			System.out.println("cpnName : "+ cpnName);	// 쿠폰명
			
			System.out.println("manualCpnId : "+ manualCpnId);	// 수동입력쿠폰ID
			System.out.println("isDupCpnId : "+ isDupCpnId);	//쿠폰중복여부  0:중복아님, 1: 중복, "" : 수동쿠폰id입력쿠폰 아님
			
			System.out.println("useName : "+ useName);	// 사용처
			System.out.println("payFreeYn : "+ payFreeYn);	// 쿠폰종류 (유무료구분)
			System.out.println("usimMode : "+ usimMode);	// USIM쿠폰구분
			System.out.println("usimId : "+ usimId);	// USIM ID
			System.out.println("barConfType : "+ barConfType);	// 쿠폰승인구분
			System.out.println("cpnIssueType : "+ cpnIssueType);	// 쿠폰발행유형
			System.out.println("autoIssueTarget : "+ autoIssueTarget);	// 타겟발행유형설정
			System.out.println("autoIssueSday : "+ autoIssueSday);	// 자동등록기간 시작일
			System.out.println("autoIssueEday : "+ autoIssueEday);	// 자동등록기간 종료일
			System.out.println("barIssueType : "+ barIssueType);	// 쿠폰번호 발행구분
			System.out.println("cmConfBarcd : "+ cmConfBarcd);	// 공통인증쿠폰번호
			System.out.println("compPayYn : "+ compPayYn);	// 복합결제 사용구분
			System.out.println("mainDisplay : "+ mainDisplay);	// 쿠폰전시구분
			System.out.println("sday : "+ sday);	// 쿠폰배포기간 시작일
			System.out.println("eday : "+ eday);	// 쿠폰배포기간 종료일
			System.out.println("valSday : "+ valSday);	// 쿠폰유효기간 시작일
			System.out.println("valEday : "+ valEday);	// 쿠폰유효기간 종료일
			System.out.println("info : "+ info);	// 쿠폰상세설명
			System.out.println("cpnMaxIssueCnt : "+ cpnMaxIssueCnt);	// 쿠폰최대배포량
			System.out.println("usableCntYn : "+ usableCntYn);	// 사용회수 사용구분
			System.out.println("yearUsableCnt : "+ yearUsableCnt);	// 연 사용 가능횟수
			System.out.println("monthUsableCnt : "+ monthUsableCnt);	// 월 사용 가능횟수
			System.out.println("dayUsableCnt : "+ dayUsableCnt);	// 일 사용 가능횟수
			System.out.println("dayIssueYn : "+ dayIssueYn);	// 일배포수량 사용구분
			System.out.println("dayIssueMaxCnt : "+ dayIssueMaxCnt);	// 일배포수량
			System.out.println("dayIssueStime : "+ dayIssueStime);	// 일배포 시작범위 시작(시간)
			System.out.println("dayIssueSmin : "+ dayIssueSmin);	// 일배포 시작범위 시작(분)
			System.out.println("dayIssueEtime : "+ dayIssueEtime);	// 일배포 시작범위 종료(시간)
			System.out.println("dayIssueEmin : "+ dayIssueEmin);	// 일배포 시작범위 종료(분)
			System.out.println("onoffType : "+ onoffType);	// 쿠폰 온/오프라인 사용구분
			System.out.println("onlineUrl : "+ onlineUrl);	// 온라인URL
//			System.out.println("i3gsList : "+ i3gsList);	// 쿠폰이미지 리스트 이미지(3GS)
//			System.out.println("i4sList : "+ i4sList);	// 쿠폰이미지 리스트 이미지(4S)
//			System.out.println("a1List : "+ a1List);	// 쿠폰이미지 리스트 이미지(안드로이드)
//			System.out.println("i3gsDetail : "+ i3gsDetail);	// 쿠폰이미지 상세 이미지(3GS)
//			System.out.println("i4sDetail : "+ i4sDetail);	// 쿠폰이미지 상세 이미지(4S)
//			System.out.println("a1Detail : "+ a1Detail);	// 쿠폰이미지 상세 이미지(안드로이드)
//			System.out.println("i3gsIntro : "+ i3gsIntro);	// 쿠폰이미지 쿠폰소개 이미지(3GS)
//			System.out.println("i4sIntro : "+ i4sIntro);	// 쿠폰이미지 쿠폰소개 이미지(4S)
//			System.out.println("a1Intro : "+ a1Intro);	// 쿠폰이미지 쿠폰소개 이미지(안드로이드)
			System.out.println("calculYn : "+ calculYn);	// 쿠폰정산여부
			System.out.println("memo : "+ memo);	// 메모
			System.out.println("recoverYn : "+ recoverYn);	// 복원가능여부
			System.out.println("isTargetCpn : "+ isTargetCpn);	// 타겟쿠폰여부
			// TODO 삭제 /////////////////////////////////////////////////////////////
			
			
			// 멤버십&복합결제 또는 일반/이벤트쿠폰 등록시 멤버십ID
			if( "R".equals(partV)){ //-- 해외쿠폰인 경우
				membId = "prix";
			}
			else if( !"M".equals(partV) ) { //-- 이벤트쿠폰인경우, 
				membId = "wallet";
			}
			/* ==> [2012.11.23 trkim] 복합결제여부 필드에 A 코드가 추가되면서 ubpay로 변경하던 로직 뺌
			if( "M".equals(partV) && "Y".equals(compPayYn) ) {
				membId = "ubpay";
			}
			if( "E".equals(partV) && "Y".equals(compPayYn) ) {
				membId = "ubpay";
			}
			*/
			
			// 조회조건값 세팅
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("partV", partV);	// 쿠폰 대구분 
			params.put("compId", compId);	// 제휴사ID
			params.put("branId", branId);	// 브랜드ID
			params.put("membId", membId);	// 멤버십ID
			
			params.put("manualCpnId", manualCpnId); // 수동id입력쿠폰
			params.put("isDupCpnId", isDupCpnId); // 수동id입력쿠폰 중복여부 
			
			params.put("cpnName", cpnName);	// 쿠폰명
			params.put("useName", useName);	// 사용처
			params.put("payFreeYn", payFreeYn);	// 쿠폰종류 (유무료구분)
			params.put("usimMode", usimMode);	// USIM쿠폰구분
			params.put("usimId", usimId);	// USIM ID
			params.put("barConfType", barConfType);	// 쿠폰승인구분
			params.put("cpnIssueType", cpnIssueType);	// 쿠폰발행유형
			params.put("autoIssueTarget", autoIssueTarget);	// 타겟발행유형설정
			params.put("autoIssueSday", autoIssueSday);	// 자동등록기간 시작일
			params.put("autoIssueEday", autoIssueEday);	// 자동등록기간 종료일
			if(partV.equals("R")){
				params.put("barIssueType", "01");	// 해외쿠폰 발행시 실시간자체발행(01)로 설정
			}else{
				params.put("barIssueType", barIssueType);	// 쿠폰번호 발행구분
			}
			params.put("cmConfBarcd", cmConfBarcd);	// 공통인증쿠폰번호
			params.put("compPayYn", compPayYn);	// 복합결제 사용구분
			params.put("mainDisplay", mainDisplay);	// 쿠폰전시구분
			params.put("sday", sday);	// 쿠폰배포기간 시작일
			params.put("eday", eday);	// 쿠폰배포기간 종료일
			params.put("valSday", valSday);	// 쿠폰유효기간 시작일
			params.put("valEday", valEday);	// 쿠폰유효기간 종료일
			params.put("info", info);	// 쿠폰상세설명
			params.put("cpnMaxIssueCnt", cpnMaxIssueCnt);	// 쿠폰최대배포량
			params.put("cpnMaxUseCnt", cpnMaxUseCnt); // 쿠폰최대사용회수
			params.put("usableCntYn", usableCntYn);	// 사용회수 사용구분
			params.put("yearUsableCnt", yearUsableCnt);	// 연 사용 가능횟수
			params.put("monthUsableCnt", monthUsableCnt);	// 월 사용 가능횟수
			params.put("dayUsableCnt", dayUsableCnt);	// 일 사용 가능횟수
			params.put("dayIssueYn", dayIssueYn);	// 일배포수량 사용구분
			params.put("dayIssueMaxCnt", dayIssueMaxCnt);	// 일배포수량
			params.put("dayIssueStime", dayIssueStime+dayIssueSmin);	// 일배포 시작범위 시작(시간)
//			params.put("dayIssueSmin", dayIssueSmin);	// 일배포 시작범위 시작(분)
			params.put("dayIssueEtime", dayIssueEtime+dayIssueEmin);	// 일배포 시작범위 종료(시간)
//			params.put("dayIssueEmin", dayIssueEmin);	// 일배포 시작범위 종료(분)
			params.put("onoffType", onoffType);	// 쿠폰 온/오프라인 사용구분
			params.put("onlineUrl", onlineUrl);	// 온라인URL
//			params.put("i3gsList", i3gsList);	// 쿠폰이미지 리스트 이미지(3GS)
//			params.put("i4sList", i4sList);	// 쿠폰이미지 리스트 이미지(4S)
//			params.put("a1List", a1List);	// 쿠폰이미지 리스트 이미지(안드로이드)
//			params.put("i3gsDetail", i3gsDetail);	// 쿠폰이미지 상세 이미지(3GS)
//			params.put("i4sDetail", i4sDetail);	// 쿠폰이미지 상세 이미지(4S)
//			params.put("a1Detail", a1Detail);	// 쿠폰이미지 상세 이미지(안드로이드)
//			params.put("i3gsIntro", i3gsIntro);	// 쿠폰이미지 쿠폰소개 이미지(3GS)
//			params.put("i4sIntro", i4sIntro);	// 쿠폰이미지 쿠폰소개 이미지(4S)
//			params.put("a1Intro", a1Intro);	// 쿠폰이미지 쿠폰소개 이미지(안드로이드)
			params.put("calculYn", calculYn);	// 쿠폰정산여부
			params.put("memo", memo);	// 메모
			params.put("regUser", regUser);	//등록자
			params.put("recoverYn", recoverYn);	//복원가능여부
			params.put("usimCompId", usimCompId);
			params.put("usimBranId", usimBranId);
			params.put("membCompulsoryYn", membCompulsoryYn);
			params.put("isTargetCpn", isTargetCpn);
			
			
			CouponService couponService = null;
			try {
				
			// DB insert //
			couponService = new CouponService();
			int result = 0;
			// cpn_list 입력
			result = couponService.insertCouponList(params);
			System.out.println(">> 1 step result cnt : "+result);
			
			// mw_cm_cpn_list 입력 
			result = couponService.insertMwCsCouponList(params);
			System.out.println(">> 2 step result cnt : "+result);
			
			
			log.debug("@@@@@@@@@@@@@@@@@@@@@@ CouponRegisterAct params : "+ params);
			// 쿠폰ID 조회
			String cpnId = "";
			if(manualCpnId.equals("")){
				cpnId = couponService.selectLastCpnId();
			}
			else{ //-- 수동으로 쿠폰 id를 입력한 경우,
				cpnId = manualCpnId;
			}
			
			// 이미지 mw_cm_image 입력
			MwCmImageService mwCmImageService = new MwCmImageService();
			String[] imageArr = request.getParameterValues("_upImgList");
			for(int i=0; imageArr!=null && i<imageArr.length; i++) {
				String one = imageArr[i];
				String oneSplit[] = one.split("[|]");
				
				String os = oneSplit[0];
				String part = oneSplit[1];
				String urlPath = oneSplit[2];
				String id = cpnId;
				String level = "";
				String imageHost = PropertiesUtil.get("img_host");
				
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
				mwCmImage.setUseYn("Y");
				mwCmImage.setRegUser(regUser);
				mwCmImage.setRegDtm(regDtm);
				// DB insert
				log.debug("@@@@@@@@@@@@@@@@@@@@@@@@ CouponImageRegist  : "+ mwCmImage.toString());
				mwCmImageService.insert(mwCmImage);
			}
			
			
			// commit
			couponService.commit();
			

			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);
			
			// 파라메타 변수 그대로 넘기기
//			request.setAttribute("membId", membId);
			
			// 결과 넘기기
//			request.setAttribute("dataList", dataList);
			
			}
			catch(Exception e) {
				// rollback
				couponService.rollback();
				e.printStackTrace();
				log.debug(e);
			}
			finally {
				
			}
			
			return "/common/result_page";//등록후 이동 경로.. 리스트?			
		}
		
		
		request.setAttribute("regDtmStr", regDtmStr);
		request.setAttribute("regUser", regUser);
		request.setAttribute("regUserNm", regUserNm);
		
		
		return "member/coupon_regist";
	}
	
	/**
	 * 쿠폰상세정보 조회 [수정/삭제 가능화면]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_detail.ms")
	public String couponDetail(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponDetail ##########");

		// 파라메터 초기화 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO 삭제 /////////////////////////////////////////////////////////////
		
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	//쿠폰Id

		
		// 목록 조회 //
		CouponService couponService = new CouponService();
		CpnListAll cpnListAll = couponService.selectCpnListAll(params); 	// 쿠폰상세정보 
		String regNm = ktService.decoding(cpnListAll.getRegUserNm());
		String chgNm = ktService.decoding(cpnListAll.getChgUserNm());
		cpnListAll.setRegUserNm(regNm);
		cpnListAll.setChgUserNm(chgNm);
		System.out.println(">> cpnListAll : "+cpnListAll);
		
		//-- 쿠폰 파일명을 화면에 뿌리기 위한 꼼수~!!
		String info1 =  "";
		
		if(cpnListAll.getInfo() != null && !cpnListAll.getInfo().equals("")){
			info1 = cpnListAll.getInfo().substring(cpnListAll.getInfo().lastIndexOf("/") + 1);
		}
		
		request.setAttribute("info1", info1);
		

		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// 파라메타 변수 그대로 넘기기
		request.setAttribute("cpnId", cpnId);
		
		// 결과 넘기기
		request.setAttribute("cpnListAll", cpnListAll);
		
		return "member/coupon_detail";
	}
	

	/**
	 * 쿠폰상세정보 수정 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_modify.ms")
	public String couponModify(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponModify ##########");

		String chgUser = getSessionMgrId(request);//TODO 세션처리 
		Date chgDtm = new Date();
		
		// 파라메터 초기화 
		String cpnId = checkStr(request.getParameter("cpnId"), "");	// 쿠폰ID 
		String compId = checkStr(request.getParameter("compId"), "");	// 제휴사ID
		String branId = checkStr(request.getParameter("branId"), "");	// 브랜드ID
		String partV = checkStr(request.getParameter("partV"), "");	// 쿠폰 대구분
		String membId = checkStr(request.getParameter("membId"), "");	// 멤버십ID
		String cpnName = checkStr(request.getParameter("cpnName"), "");	// 쿠폰명
		String useName = checkStr(request.getParameter("useName"), "");	// 사용처
		String payFreeYn = checkStr(request.getParameter("rpayFreeYn"), "");	// 쿠폰종류 (유무료구분)
		String usimMode = checkStr(request.getParameter("usimMode"), "");	// USIM쿠폰구분
		String usimId = checkStr(request.getParameter("usimId"), "");	// USIM ID
		String barConfType = checkStr(request.getParameter("rbarConfType"), "");	// 쿠폰승인구분
		String cpnIssueType = checkStr(request.getParameter("cpnIssueType"), "");	// 쿠폰발행유형
		String autoIssueTarget = checkStr(request.getParameter("autoIssueTarget"), "");	// 타겟발행유형설정
		
		String autoIssueSday = "";
		String autoIssueEday = "";
		if(cpnIssueType.equals("04")){
			autoIssueSday = checkStr(request.getParameter("eventIssueSday"), "").replaceAll("-", "");	// 다운로드 가능 신규가입기간 시작일
			autoIssueEday = checkStr(request.getParameter("eventIssueEday"), "").replaceAll("-", "");	// 다운로드 가능 신규가입기간 종료일
		}else{
			autoIssueSday = checkStr(request.getParameter("autoIssueSday"), "").replaceAll("-", "");	// 자동등록기간 시작일
			autoIssueEday = checkStr(request.getParameter("autoIssueEday"), "").replaceAll("-", "");	// 자동등록기간 종료일
		}
		
		String barIssueType = checkStr(request.getParameter("barIssueType"), "");	// 쿠폰번호 발행구분
		String cmConfBarcd = checkStr(request.getParameter("cmConfBarcd"), "");	// 공통인증쿠폰번호
		String compPayYn = checkStr(request.getParameter("compPayYn"), "");	// 복합결제 사용구분
		String mainDisplay = checkStr(request.getParameter("mainDisplay"), "");	// 쿠폰전시구분
		String sday = checkStr(request.getParameter("sday"), "").replaceAll("-", "");	// 쿠폰배포기간 시작일
		String eday = checkStr(request.getParameter("eday"), "").replaceAll("-", "");	// 쿠폰배포기간 종료일
		String valSday = checkStr(request.getParameter("valSday"), "").replaceAll("-", "");	// 쿠폰유효기간 시작일
		String valEday = checkStr(request.getParameter("valEday"), "").replaceAll("-", "");	// 쿠폰유효기간 종료일
		String info = checkStr(request.getParameter("info"), "");	// 쿠폰상세설명
		String cpnMaxIssueCnt = checkStr(request.getParameter("cpnMaxIssueCnt"), "");	// 쿠폰최대배포량
		String cpnMaxUseCnt = checkStr(request.getParameter("cpnMaxUseCnt"), "");
		String usableCntYn = checkStr(request.getParameter("usableCntYn"), "");	// 사용회수 사용구분
		String yearUsableCnt = checkStr(request.getParameter("yearUsableCnt"), "");	// 연 사용 가능횟수
		String monthUsableCnt = checkStr(request.getParameter("monthUsableCnt"), "");	// 월 사용 가능횟수
		String dayUsableCnt = checkStr(request.getParameter("dayUsableCnt"), "");	// 일 사용 가능횟수
		String dayIssueYn = checkStr(request.getParameter("dayIssueYn"), "");	// 일배포수량 사용구분
		String dayIssueMaxCnt = checkStr(request.getParameter("dayIssueMaxCnt"), "");	// 일배포수량
		String dayIssueStime = checkStr(request.getParameter("dayIssueStime"), "");	// 일배포 시작범위 시작(시간)
		String dayIssueSmin = checkStr(request.getParameter("dayIssueSmin"), "");	// 일배포 시작범위 시작(분)
		String dayIssueEtime = checkStr(request.getParameter("dayIssueEtime"), "");	// 일배포 시작범위 종료(시간)
		String dayIssueEmin = checkStr(request.getParameter("dayIssueEmin"), "");	// 일배포 시작범위 종료(분)
		String onoffType = checkStr(request.getParameter("onoffType"), "");	// 쿠폰 온/오프라인 사용구분
		String onlineUrl = checkStr(request.getParameter("onlineUrl"), "");	//온라인URL
		String calculYn = checkStr(request.getParameter("calculYn"), "");	// 쿠폰정산여부
		String memo = checkStr(request.getParameter("memo"), "");	// 메모
		String cpnIssueStat = checkStr(request.getParameter("cpnIssueStat"), "");	//쿠폰발행여부 
		String recoverYn = checkStr(request.getParameter("recoverYn"), "");	//복원가능여부
		String usimCompId = checkStr(request.getParameter("usimCompId"), ""); //USIM 제휴사ID
		String usimBranId = checkStr(request.getParameter("usimBranId"), ""); //USIM 계열사ID
		String membCompulsoryYn = checkStr(request.getParameter("membCompulsoryYn"), ""); // 멤버십 쿠폰 사용제한
		
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);	// 쿠폰ID
		System.out.println("compId : "+ compId);	// 제휴사ID
		System.out.println("branId : "+ branId);	// 브랜드ID
		System.out.println("partV : "+ partV);	// 대구분
		System.out.println("membId : "+ membId);	// 멤버십ID
		System.out.println("cpnName : "+ cpnName);	// 쿠폰명
		System.out.println("useName : "+ useName);	// 사용처
		System.out.println("payFreeYn : "+ payFreeYn);	// 쿠폰종류 (유무료구분)
		System.out.println("usimMode : "+ usimMode);	// USIM쿠폰구분
		System.out.println("usimId : "+ usimId);	// USIM ID
		System.out.println("barConfType : "+ barConfType);	// 쿠폰승인구분
		System.out.println("cpnIssueType : "+ cpnIssueType);	// 쿠폰발행유형
		System.out.println("autoIssueTarget : "+ autoIssueTarget);	// 타겟발행유형설정
		System.out.println("autoIssueSday : "+ autoIssueSday);	// 자동등록기간 시작일
		System.out.println("autoIssueEday : "+ autoIssueEday);	// 자동등록기간 종료일
		System.out.println("barIssueType : "+ barIssueType);	// 쿠폰번호 발행구분
		System.out.println("cmConfBarcd : "+ cmConfBarcd);	// 공통인증쿠폰번호
		System.out.println("compPayYn : "+ compPayYn);	// 복합결제 사용구분
		System.out.println("mainDisplay : "+ mainDisplay);	// 쿠폰전시구분
		System.out.println("sday : "+ sday);	// 쿠폰배포기간 시작일
		System.out.println("eday : "+ eday);	// 쿠폰배포기간 종료일
		System.out.println("valSday : "+ valSday);	// 쿠폰유효기간 시작일
		System.out.println("valEday : "+ valEday);	// 쿠폰유효기간 종료일
		System.out.println("info : "+ info);	// 쿠폰상세설명
		System.out.println("cpnMaxIssueCnt : "+ cpnMaxIssueCnt);	// 쿠폰최대배포량
		System.out.println("usableCntYn : "+ usableCntYn);	// 사용회수 사용구분
		System.out.println("yearUsableCnt : "+ yearUsableCnt);	// 연 사용 가능횟수
		System.out.println("monthUsableCnt : "+ monthUsableCnt);	// 월 사용 가능횟수
		System.out.println("dayUsableCnt : "+ dayUsableCnt);	// 일 사용 가능횟수
		System.out.println("dayIssueYn : "+ dayIssueYn);	// 일배포수량 사용구분
		System.out.println("dayIssueMaxCnt : "+ dayIssueMaxCnt);	// 일배포수량
		System.out.println("dayIssueStime : "+ dayIssueStime);	// 일배포 시작범위 시작(시간)
		System.out.println("dayIssueSmin : "+ dayIssueSmin);	// 일배포 시작범위 시작(분)
		System.out.println("dayIssueEtime : "+ dayIssueEtime);	// 일배포 시작범위 종료(시간)
		System.out.println("dayIssueEmin : "+ dayIssueEmin);	// 일배포 시작범위 종료(분)
		System.out.println("onoffType : "+ onoffType);	// 쿠폰 온/오프라인 사용구분
		System.out.println("onlineUrl : "+ onlineUrl);	// 온라인URL
		System.out.println("calculYn : "+ calculYn);	// 쿠폰정산여부
		System.out.println("memo : "+ memo);	// 메모
		System.out.println("cpnIssueStat : "+ cpnIssueStat);	//쿠폰발행여부 
		System.out.println("chgUser : "+ chgUser);	// 수정자  
		System.out.println("chgDtm : "+ chgDtm);	// 수정일시  
		System.out.println("recoverYn : "+ recoverYn);	// 복원가능여부
		// TODO 삭제 /////////////////////////////////////////////////////////////

		
		// 멤버십&복합결제 또는 일반/이벤트쿠폰 등록시 멤버십ID
		if( "R".equals(partV)){ //-- 해외쿠폰인 경우
			membId = "prix";
		}
		else if( !"M".equals(partV) ) { //-- 이벤트쿠폰인경우, 
			membId = "wallet";
		}
		/* ==> [2012.11.23 trkim] 복합결제여부 필드에 A 코드가 추가되면서 ubpay로 변경하던 로직 뺌
		if( "M".equals(partV) && "Y".equals(compPayYn) ) {
			membId = "ubpay";
		}
		if( "E".equals(partV) && "Y".equals(compPayYn) ) {
			membId = "ubpay";
		}
		*/
		
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	// 쿠폰ID
		params.put("compId", compId);	// 제휴사ID
		params.put("branId", branId);	// 브랜드ID
		params.put("partV", partV);	// 쿠폰 대구분 
		params.put("membId", membId);	// 멤버십ID
		params.put("cpnName", cpnName);	// 쿠폰명
		params.put("useName", useName);	// 사용처
		params.put("payFreeYn", payFreeYn);	// 쿠폰종류 (유무료구분)
		params.put("usimMode", usimMode);	// USIM쿠폰구분
		params.put("usimId", usimId);	// USIM ID
		params.put("barConfType", barConfType);	// 쿠폰승인구분
		params.put("cpnIssueType", cpnIssueType);	// 쿠폰발행유형
		params.put("autoIssueTarget", autoIssueTarget);	// 타겟발행유형설정
		params.put("autoIssueSday", autoIssueSday);	// 자동등록기간 시작일
		params.put("autoIssueEday", autoIssueEday);	// 자동등록기간 종료일
		if(partV.equals("R")){
			params.put("barIssueType", "01");	// 해외쿠폰 발행시 실시간자체발행(01)로 설정
		}else{
			params.put("barIssueType", barIssueType);	// 쿠폰번호 발행구분
		}
		params.put("cmConfBarcd", cmConfBarcd);	// 공통인증쿠폰번호
		params.put("compPayYn", compPayYn);	// 복합결제 사용구분
		params.put("mainDisplay", mainDisplay);	// 쿠폰전시구분
		params.put("sday", sday);	// 쿠폰배포기간 시작일
		params.put("eday", eday);	// 쿠폰배포기간 종료일
		params.put("valSday", valSday);	// 쿠폰유효기간 시작일
		params.put("valEday", valEday);	// 쿠폰유효기간 종료일
		params.put("info", info);	// 쿠폰상세설명
		params.put("cpnMaxIssueCnt", cpnMaxIssueCnt);	// 쿠폰최대배포량
		params.put("cpnMaxUseCnt", cpnMaxUseCnt);
		params.put("usableCntYn", usableCntYn);	// 사용회수 사용구분
		params.put("yearUsableCnt", yearUsableCnt);	// 연 사용 가능횟수
		params.put("monthUsableCnt", monthUsableCnt);	// 월 사용 가능횟수
		params.put("dayUsableCnt", dayUsableCnt);	// 일 사용 가능횟수
		params.put("dayIssueYn", dayIssueYn);	// 일배포수량 사용구분
		params.put("dayIssueMaxCnt", dayIssueMaxCnt);	// 일배포수량
		params.put("dayIssueStime", dayIssueStime+dayIssueSmin);	// 일배포 시작범위 시작(시간)
//		params.put("dayIssueSmin", dayIssueSmin);	// 일배포 시작범위 시작(분)
		params.put("dayIssueEtime", dayIssueEtime+dayIssueEmin);	// 일배포 시작범위 종료(시간)
//		params.put("dayIssueEmin", dayIssueEmin);	// 일배포 시작범위 종료(분)
		params.put("onoffType", onoffType);	// 쿠폰 온/오프라인 사용구분
		params.put("onlineUrl", onlineUrl);	// 온라인URL
		params.put("calculYn", calculYn);	// 쿠폰정산여부
		params.put("memo", memo);	// 메모
		params.put("cpnIssueStat", cpnIssueStat);	//쿠폰발행여부 
		params.put("chgUser", chgUser);	//수정자 
		params.put("chgDtm", chgDtm);	//수정자 
		params.put("recoverYn", recoverYn);	//복원가능여부 
		params.put("usimCompId", usimCompId);
		params.put("usimBranId", usimBranId);
		params.put("membCompulsoryYn", membCompulsoryYn);
		
		
		CouponService couponService = null;
		try {
				
			// DB insert //
			couponService = new CouponService();
			int result = 0;
			// cpn_list 수정
			result = couponService.updateCouponList(params);
			System.out.println(">> 1 step result cnt : "+result);
			
			// mw_cm_cpn_list 수정
			result = couponService.updateMwCsCouponList(params);
			System.out.println(">> 2 step result cnt : "+result);
			
			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@ CouponUpdateAct params : "+ params);
			
			// 이미지 mw_cm_image 입력
			MwCmImageService mwCmImageService = new MwCmImageService();
			String[] imageArr = request.getParameterValues("_upImgList");
			for(int i=0; imageArr!=null && i<imageArr.length; i++) {
				String one = imageArr[i];
				String oneSplit[] = one.split("[|]");
				
				String os = oneSplit[0];
				String part = oneSplit[1];
				String urlPath = oneSplit[2];
				String id = cpnId;
				String level = "";
				String imageHost = PropertiesUtil.get("img_host");
				
				System.out.println("os : " + os);
				System.out.println("part : " + part);
				System.out.println("urlPath : " + urlPath);
				System.out.println("id : " + id);
				System.out.println("level : " + level);
				System.out.println("imageHost : " + imageHost);
				System.out.println("chgUser : " + chgUser);
				
				MwCmImage mwCmImage = new MwCmImage();
				mwCmImage.setId(id);
				mwCmImage.setOsType(os);
				mwCmImage.setUseType(part);
				mwCmImage.setLevel(level);
				mwCmImage.setImageHost(imageHost);
				mwCmImage.setUseYn("Y");
				mwCmImage.setImageUrl(urlPath);
				mwCmImage.setRegUser(chgUser);
				mwCmImage.setRegDtm(chgDtm);
				
				// DB update
				MwCmImageExample mwCmImageExample = new MwCmImageExample();
				mwCmImageExample.or().andIdEqualTo(id).andOsTypeEqualTo(os).andUseTypeEqualTo(part).andLevelEqualTo(level);
			
				log.debug("@@@@@@@@@@@@@@@@@@@@@@@@ CouponImgUpdate params : "+ mwCmImage.toString());
//				mwCmImageService.update(mwCmImage,mwCmImageExample);
				mwCmImageService.delete(mwCmImageExample);
				mwCmImageService.insert(mwCmImage);
			}
			
			
			// commit
			couponService.commit();
			
	
			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);
			

		}
		catch(Exception e) {
			// rollback
			couponService.rollback();
			e.printStackTrace();
			log.debug(e);
		}
		finally {
			
		}
		
		return "/common/result_page";//등록후 이동 경로.. 리스트?			
	}
	
	
	
	/**
	 * 쿠폰상세정보 삭제 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_delete.ms")
	public String couponDelete(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponDelete ##########");

		String chgUser = getSessionMgrId(request);//TODO 세션처리 
		Date chgDtm = new Date();
		
		// 파라메터 초기화 
		String cpnId = checkStr(request.getParameter("cpnId"), "");	// 쿠폰ID
		
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);	// 쿠폰ID
		System.out.println("chgUser : "+ chgUser);	// 수정자  
		System.out.println("chgDtm : "+ chgDtm);	// 수정일시
		// TODO 삭제 /////////////////////////////////////////////////////////////
		
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	// 쿠폰ID
		params.put("chgUser", chgUser);	//수정자 
		params.put("chgDtm", chgDtm);	//수정자 
		
		
		CouponService couponService = null;
		ComplexCouponService complexCouponService = null;
		try {
				
			// DB insert //
			couponService = new CouponService();
			int result = 0;
			// cpn_list 수정
			result = couponService.deleteCouponList(params);
			System.out.println(">> 1 step result cnt : "+result);
			
			// mw_cm_cpn_list 수정
			result = couponService.deleteMwCsCouponList(params);
			System.out.println(">> 2 step result cnt : "+result);
			
			//-- 복합결제 쿠폰의 경우, 금융사, 결제사, 가맹점 정보가 등록되어 있는 경우, 삭제 필요함. //-- by trkim. 2013.03.18
			complexCouponService = new ComplexCouponService();
			complexCouponService.deleteComplexBank(params);
			complexCouponService.deleteComplexPayComp(params);
			complexCouponService.deleteComplexStore(params);
			
			// DB delete (해당 쿠폰 이미지 삭제)
			MwCmImageService mwCmImageService = new MwCmImageService();
			MwCmImageExample mwCmImageExample = new MwCmImageExample();
			mwCmImageExample.or().andIdEqualTo(cpnId);
			mwCmImageService.delete(mwCmImageExample);

			
			// commit
			couponService.commit();
			complexCouponService.commit();
			
	
			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);

		}
		catch(Exception e) {
			// rollback
			couponService.rollback();
			e.printStackTrace();
			log.debug(e);
		}
		finally {
			
		}
		
		return "/common/result_page";//등록후 이동 경로.. 리스트?			
	}

	/**
	 * 쿠폰 승인 리스트 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_approval_list.ms")
	public String couponApprovalList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponApprovalList ##########");

		// 파라메터 초기화 
		String partV = checkStr(request.getParameter("partV"), "%");
		String compName = checkStr(request.getParameter("compName"), "");
		String cpnName = checkStr(request.getParameter("cpnName"), "");
		String cpnStat = checkStr(request.getParameter("cpnStat"), "all");
//		String barIssueType = checkStr(request.getParameter("barIssueType"), "%");
//		String barConfType = checkStr(request.getParameter("barConfType"), "%");
//		String cpnValidYn = checkStr(request.getParameter("cpnValidYn"), "%");
//		String cpnIssueStat = checkStr(request.getParameter("cpnIssueStat"), "%");
//		String mainDisplay = checkStr(request.getParameter("mainDisplay"), "%");
		String rSearchTerm = checkStr(request.getParameter("rSearchTerm"), "all");
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("partV: "+partV);
		System.out.println("compName: "+compName);
		System.out.println("cpnName: "+cpnName);
		System.out.println("cpnStat: "+cpnStat);
//		System.out.println("barIssueType: "+barIssueType);
//		System.out.println("barConfType: "+barConfType);
//		System.out.println("cpnValidYn: "+cpnValidYn);
//		System.out.println("cpnIssueStat: "+cpnIssueStat);
//		System.out.println("mainDisplay: "+mainDisplay);
		System.out.println("rSearchTerm: "+rSearchTerm);
		System.out.println("sdate: "+sdate);
		System.out.println("edate: "+edate);
		// TODO 삭제 /////////////////////////////////////////////////////////////

		String sdateNoHyphen = sdate.replaceAll("-","");
		String edateNoHyphen = edate.replaceAll("-","");
		log.debug("sdateNoHyphen: "+sdateNoHyphen);
		log.debug("edateNoHyphen: "+edateNoHyphen);
		
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("partV", partV);									//쿠폰구분
		params.put("compName", compName);						//제휴사명
		params.put("cpnName", cpnName);							//쿠폰명
		params.put("cpnStat", cpnStat);							//승인상태
//		params.put("barIssueType", barIssueType);		//쿠폰번호유형
//		params.put("barConfType", barConfType);			//쿠폰승인여부
//		params.put("cpnValidYn", cpnValidYn);				//유효상태
//		params.put("cpnIssueStat", cpnIssueStat);		//쿠폰발행상태
//		params.put("mainDisplay", mainDisplay);			//쿠폰전시상태
		params.put("rSearchTerm", rSearchTerm);			//기간(발행일)
		params.put("sdate", sdateNoHyphen);					//기간 시작일
		params.put("edate", edateNoHyphen);					//기간 종료일

		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// 목록 조회 //
		CouponService couponService = new CouponService();
		List<Coupon> couponList = couponService.selectCouponApprList(params); 	// 목록조회
		int couponListCnt = couponService.selectCouponApprCnt(params); 		// 총 목록 수
		System.out.println(">> couponList : "+couponList);
		

		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, couponListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// 파라메타 변수 그대로 넘기기
		request.setAttribute("partV", partV);
		request.setAttribute("compName", compName);
		request.setAttribute("cpnName", cpnName);
		request.setAttribute("cpnStat", cpnStat);
//		request.setAttribute("barIssueType", barIssueType);
//		request.setAttribute("barConfType", barConfType);
//		request.setAttribute("cpnValidYn", cpnValidYn);
//		request.setAttribute("cpnIssueStat", cpnIssueStat);
//		request.setAttribute("mainDisplay", mainDisplay);
		request.setAttribute("rSearchTerm", rSearchTerm);
		request.setAttribute("sdate", sdate);
		request.setAttribute("edate", edate);
		
		// 결과 넘기기
		request.setAttribute("dataList", couponList);
		
		
		return "member/coupon_approval_list";
	}


	/**
	 * 보류/반려 사유 확인 [팝업]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_stat_comment_pop.ms")
	public String couponStatCommentPop(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponStatCommentPop ##########");

		// 파라메터 초기화 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO 삭제 /////////////////////////////////////////////////////////////
		
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	//쿠폰Id

		
		// 목록 조회 //
		CouponService couponService = new CouponService();
		MwCsCpnregHist hist = couponService.selectCpnregHist(params); 	// 쿠폰상세정보 
		String statComment = hist==null?"":hist.getStatComment();
		
		System.out.println(">> statComment : "+statComment);
		

		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// 파라메타 변수 그대로 넘기기
		request.setAttribute("cpnId", cpnId);
		
		// 결과 넘기기
		request.setAttribute("statComment", statComment);
		
		return "member/coupon_stat_comment_pop";
	}
	
	
	/**
	 * 쿠폰상세정보 조회 [수정/삭제 가능화면]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_approval_detail.ms")
	public String couponAppDetail(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponAppDetail ##########");

		// 파라메터 초기화 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO 삭제 /////////////////////////////////////////////////////////////
		
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	//쿠폰Id

		
		// 목록 조회 //
		CouponService couponService = new CouponService();
		CpnListAll cpnListAll = couponService.selectCpnListAll(params); 	// 쿠폰상세정보 
		System.out.println(">> cpnListAll : "+cpnListAll);
		
		String info1 = "";
		
		if(cpnListAll.getInfo() != null && !cpnListAll.getInfo().equals("")){
			info1 = cpnListAll.getInfo().substring(cpnListAll.getInfo().lastIndexOf("/")+1);
		}
		
		request.setAttribute("info1", info1);
		

		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// 파라메타 변수 그대로 넘기기
		request.setAttribute("cpnId", cpnId);
		
		// 결과 넘기기
		request.setAttribute("cpnListAll", cpnListAll);
		
		return "member/coupon_approval_detail";
	}

	
	/**
	 * 쿠폰상세정보 삭제 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_approval.ms")
	public String couponApproval(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponDelete ##########");

		String regUser = getSessionMgrId(request);//TODO 세션처리 
		Date regDtm = new Date();
		
		// 파라메터 초기화 
		String cpnId = checkStr(request.getParameter("cpnId"), "");	// 쿠폰ID
		String cpnStat = checkStr(request.getParameter("cpnStat"), "");	// 쿠폰승인여부
		String statComment = checkStr(request.getParameter("statComment"), "");	// 보류/반려사유
		
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);	// 쿠폰ID
		System.out.println("cpnStat: "+cpnStat);	// 쿠폰승인여부
		System.out.println("statComment: "+statComment);	// 보류/반려사유
		System.out.println("regUser : "+ regUser);	// 등록자  
		System.out.println("regDtm : "+ regDtm);	// 등록일시
		// TODO 삭제 /////////////////////////////////////////////////////////////
		
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	// 쿠폰ID
		params.put("cpnStat", cpnStat);	// 쿠폰승인여부
		params.put("statComment", statComment);	// 보류/반려사유
		params.put("regUser", regUser);	//등록자 
		params.put("regDtm", regDtm);	//등록일시  
		
		
		CouponService couponService = null;
		try {
				
			// DB insert //
			couponService = new CouponService();
			int result = 0;

			// mw_cm_cpn_list 수정
			result = couponService.updateMwCsCouponList(params);
			System.out.println(">> 1 step result cnt : "+result);
			
			// cpn_list 수정
			result = couponService.insertCouponRegHist(params);
			System.out.println(">> 2 step result cnt : "+result);
						
			// commit
			couponService.commit();
			
			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);

		}
		catch(Exception e) {
			// rollback
			couponService.rollback();
			e.printStackTrace();
			log.debug(e);
		}
		finally {
			
		}
		
		return "/common/result_page";//등록후 이동 경로.. 리스트?			
	}

	
	/**
	 * 수동발행쿠폰 리스트  
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_hand_issue_list.ms")
	public String couponHandIssueList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponHandIssueList ##########");

		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		// 파라메터 초기화 
		String partV = checkStr(request.getParameter("partV"), "%");
		String compName = checkStr(request.getParameter("compName"), "");
		String cpnName = checkStr(request.getParameter("cpnName"), "");
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- 조회기간설정		//-- 조회기간 기본설정, list 페이지가 처음으로 로딩 될 때는 parameter가 넘어오지 않아 초기 값 설정이 필요함.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
	
		params.put("partV", partV);									//쿠폰구분
		params.put("compName", compName);						//제휴사명
		params.put("cpnName", cpnName);							//쿠폰명

		params.put("cpnIssueType","01");	// Admin수동발행(0018)
		
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		
		CouponService couponService = new CouponService();
		int couponListCnt = couponService.selectCouponListCnt(params); 		// 총 목록 수
		Paging page = new Paging();
		page.makeWebPaging(nowPage, couponListCnt, rowsPerPage);
		
		if(page.getNowPage()>1){
			nowPage =  page.getNowPage();
			request.setAttribute("nowPage", nowPage);
			
		}else{
			nowPage = 1;
			request.setAttribute("nowPage", "1");
		}

		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		
		List<Coupon> couponList = couponService.selectCouponList(params); 	// 목록조회
		System.out.println(">> couponList : "+couponList);
		
		
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// 파라메타 변수 그대로 넘기기
		request.setAttribute("partV", partV);
		request.setAttribute("compName", compName);
		request.setAttribute("cpnName", cpnName);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		
		// 결과 넘기기
		request.setAttribute("dataList", couponList);
		
		
		return "member/coupon_hand_issue_list";
	}
	

	/**
	 * 쿠폰 개별 발행 [팝업]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_hand_issue_pop.ms")
	public String couponHandIssuePop(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponHandIssuePop ##########");

		String regUser = getSessionMgrId(request);//TODO 세션처리 

		// 파라메터 초기화 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		String issueStat = checkStr(request.getParameter("issueStat"), "N");
		String phone = checkStr(request.getParameter("phone"), "");
		String optMode = checkStr(request.getParameter("optMode"), "search"); //-- search:조회, issue:발행
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		System.out.println("issueStat: "+issueStat);
		System.out.println("phone: "+phone);
		System.out.println("optMode: "+optMode);
		// TODO 삭제 /////////////////////////////////////////////////////////////
		
		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		String params3 = ""; //-- DB로그를 남기기 위해 하단 params2 의 값을 저장할 String
		params.put("cpnId", cpnId);									//쿠폰Id
		params.put("issueStat", issueStat);
		params.put("phone", phone);

		
		
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// 한 페이지에서 보여지는 게시믈 목록 수
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// 현재페이지
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// 첫 목록
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		// 목록 조회 //
		CouponService couponService = new CouponService();
		int result = 0;
		
		// 쿠폰정보 조회 
		CpnListAll cpnListAll = couponService.selectCpnListAll(params); 	// 쿠폰상세정보 
		
		// 발행 처리 
		if("issue".equals(optMode)){
			String[] chkSeqArr = request.getParameterValues("chkSeq");
			for(int i=0; chkSeqArr!=null && i<chkSeqArr.length; i++) {
				String userId = chkSeqArr[i];
				System.out.println("userId : " + userId); //##
				
	
				String membId = cpnListAll.getMembId();//멤버십id -DB
				String cpart = "p";
				String barcode = "";	//자체인증번호 -생성 / 공통인증번호-DB / POOL관리쿠폰?? -DB
				String regDay = "";//CONVERT(VARCHAR(8),GETDATE(),112)
				String stat = "R";
				String sday = cpnListAll.getValSday();//유효시작일	-DB
				String eday = cpnListAll.getValEday();//유효종료일	-DB
				String imgHost = "";//이미지 경로 -DB
				String imgUrl = "";//이미지 파일명 -DB
				String chgDay = "";//CONVERT(VARCHAR(8),GETDATE(),112)
				String useCnt = "";//쿠폰최대사용회수 -DB
				String usimData = "";//??
	
				String regDt = "";//CONVERT(VARCHAR(8),GETDATE(),112)
				String regTm = "";//REPLACE(CONVERT(VARCHAR(8),GETDATE(),108),':','')
				
				try {
	
					// 쿠폰번호발행구분에 따라 쿠폰번호 발행 
					barcode = getBarcode(cpnListAll, couponService);
	
					
					// 사용자 정보 : os 
					HashMap<String, Object> params2 = new HashMap<String,Object>();
					params2.put("userId", userId);
					UserInfo userInfo = couponService.getUserInfo(params2);
					
					
					// 바코드 이미지 생성
					// BarcodeUtil(String Os,String MembId, String CardLevel, String Name, String ValSday, String ValEday)
					String os = userInfo.getOs();
					String level = "";
					String name = "";
					

					
					/*==>mcserver 로직. 이로직이 추가됨에 따라, 아래 소스 추가됨.
					if (CompulsoryYn.equals("Y")) {
						barcodeUtil = new BarcodeUtil(Integer.toString(UserID));
					}
					else {
						//[20121108][inoky@2bsolution.com] coupon barcode image widh값을 가져오기위한 userid 추가
						barcodeUtil = new BarcodeUtil(Integer.toString(UserID), Os, "", "", Name, ValSday, ValEday, "");
						barcodeUtil.Make(Barcode, 2);
					}
					*/
					
					BarcodeUtil barcodeUtil = null;
					
					//--복합결제이면, 
					if (cpnListAll.getCompPayYn().equals("Y")){
						barcodeUtil = new BarcodeUtil(userId);
					}
					else{ //-- 복합결재용 + 멤버십 사용시 필요한 바코드 생성
						//[20121108][inoky@2bsolution.com] coupon barcode image widh값을 가져오기위한 userid 추가
						barcodeUtil = new BarcodeUtil(userId, os, membId, level, name, sday, eday, "01", true);
						barcodeUtil.Make(barcode, 2);
					}
					
					/*
					BarcodeUtil barcodeUtil = new BarcodeUtil(os, membId, level, name, sday, eday, "01"); // 01 : barcode type
					barcodeUtil.Make(barcode, 2);
					*/
					imgHost = barcodeUtil.getHost();
					//imgUrl = barcodeUtil.getB_BarcodePath(); //-- 큰 이미지
					imgUrl = barcodeUtil.getS_BarcodePath(); //-- 작은 이미지
					// 바코드 이미지 생성 END
					
					
					// my_cpn 입력
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//쿠폰Id
					params2.put("userId", userId);	//사용자Id
					params2.put("barcode", barcode);
					params2.put("cpart", cpart);
					params2.put("stat", stat);
					params2.put("imgHost", imgHost);
					params2.put("imgUrl", imgUrl);
					result = couponService.insertMyCpn(params2);
					
					
					// cpn_gen_his 입력
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//쿠폰Id
					params2.put("custId", userId);	//사용자Id
					params2.put("barcode", barcode);
					params2.put("stat", stat);
					result = couponService.insertCpnGenHis(params2);
					
					
					// MW_CS_ISSUE_CPN 입력
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//쿠폰Id
					params2.put("userId", userId);	//사용자Id
					params2.put("barcode", barcode);
					params2.put("cmConfBarcdYn", "02".equals(cpnListAll.getBarIssueType())?"Y":"N");
					params2.put("regUser", regUser);
					result = couponService.insertMwCsIssueCpn(params2);
					
					
					params3 = params3 + params2.toString(); //--DB 로그 등록을 위한 코드 입니다.
					
					couponService.commit();
					
				} catch(Exception e) {
					couponService.rollback();
					e.printStackTrace();
				}
			}
		}
		
		List<CouponIssue> dataList = couponService.selectCouponHandIssuePopList(params); 	// 발행쿠폰승인정보 조회
		String[] userNm = new String[dataList.size()];
		String[] phoneList = new String[dataList.size()];
		for(int i=0; i<dataList.size(); i++){
			String temp = dataList.get(i).getUserName();
			temp = ktService.decoding(temp);
			if(temp != null && !"".equals(temp)){
				userNm[i] = temp.substring(0, temp.length()-1)+"*";
			} else userNm[i] = "*";
			
			// 전화번호 리스트//list.phone.substring(0,1)}**${list.phone.substring(3,10)}*
			String phone2 = dataList.get(i).getPhone();
			if(phone2 != null && !"".equals(phone2)){
				phoneList[i] = phone2.substring(0, 1)+"**"+phone2.substring(3,phone2.length()-1)+"*";
			} else phoneList[i] = "*";
			
		}
		int listCnt = couponService.selectCouponHandIssuePopListCnt(params); 		// 총 개수
		System.out.println(">> dataList : "+dataList);
		

		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// 페이징 처리 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// 파라메타 변수 그대로 넘기기
		request.setAttribute("phoneList", phoneList);
		request.setAttribute("userNm", userNm);
		request.setAttribute("cpnId", cpnId);
		request.setAttribute("issueStat", issueStat);
		request.setAttribute("phone", phone);
		
		// 결과 넘기기
		request.setAttribute("dataList", dataList);
		
		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		params.put("pageURL", "/member/coupon_hand_issue_pop.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString() + params3.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		
		
		return "member/coupon_hand_issue_pop";
	}

	/**
	 * 쿠폰번호 발행 
	 * 
	 * @param cpnId
	 * @param cpnListAll
	 * @return
	 */
	private String getBarcode(CpnListAll cpnListAll, CouponService couponService) {
		String barcode = "", barIssueType = cpnListAll.getBarIssueType();
		if("00".equals(barIssueType)) {	//쿠폰번호없음
			barcode = "No Coupon Number";
		} else if("01".equals(barIssueType)) {	//실시간 자체발행
			barcode = BARCODE_GEN.LOCAL_BARCODE_NUM(cpnListAll.getCpnId());
		} else if("02".equals(barIssueType)) {	//등록된 공통인증쿠폰번호 
			barcode = cpnListAll.getCmConfBarcd();
		} else if("03".equals(barIssueType)) {	//실시간 제휴사 연동 발행
			barcode = ""; //TODO 인터페이스 호출 
		} else if("04".equals(barIssueType)) {	//BULK 관리쿠폰 발행
			// MW_CS_BULK_CPN : BARCODE 가져오기
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("cpnId", cpnListAll.getCpnId());
			MwCsBulkCpn mwCsBulkCpn = couponService.getMwCsBulkCpnLast(params);
			// MW_CS_BULK_CPN : set ISSUE_YN='Y'
			params.put("issueSeq", mwCsBulkCpn.getIssueSeq());
			int result = couponService.updateMwCsBulkCpnIssueYn(params);
			barcode = mwCsBulkCpn.getBarcode();
		}
		return barcode;
	}
	
	private String getBarcode(CpnListAll cpnListAll, CouponService couponService, String targetCpnNo) {
		String barcode = "", barIssueType = cpnListAll.getBarIssueType();
		if("04".equals(barIssueType)) {	//BULK 관리쿠폰 발행
			// MW_CS_BULK_CPN : BARCODE 가져오기
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("cpnId", cpnListAll.getCpnId());
			params.put("barcode", targetCpnNo); //-- 타겟쿠폰인 경우, 해당 쿠폰 번호가 발급되지 않은 경우에 대하여 barcode를 세팅하도록 하기위함.
			
			MwCsBulkCpn mwCsBulkCpn = couponService.getMwCsBulkCpnLast(params);
			// MW_CS_BULK_CPN : set ISSUE_YN='Y'
			params.put("issueSeq", mwCsBulkCpn.getIssueSeq());
			int result = couponService.updateMwCsBulkCpnIssueYn(params);
			barcode = mwCsBulkCpn.getBarcode();
		}
		return barcode;
	}
	/**
	 * 쿠폰 일괄 발행 [팝업]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_hand_issue_file_pop.ms")
	public String couponHandIssueFilePop(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponHandIssueFilePop ##########");

		// 파라메터 초기화 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO 삭제 /////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// 파라메타 변수 그대로 넘기기
		request.setAttribute("cpnId", cpnId);
		
		/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("pageURL", "/member/coupon_hand_issue_file_pop.ms");
	
		params.put("cpnId", cpnId);
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString() );
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
		
		return "member/coupon_hand_issue_file_pop";
	}
	
	
	/**
	 * 쿠폰 일괄 발행
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_hand_issue_file.ms")
	public String couponHandIssueFile(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponHandIssueFile ##########");

		String regUser = getSessionMgrId(request); 
		
		// 파라메터 초기화 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		
		// TODO 삭제 /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO 삭제 /////////////////////////////////////////////////////////////
		
		if( "".equals(cpnId) ) { 
			JsonErrMsg("err",response);
			return null;
		}

		
		// 조회조건값 세팅
		HashMap<String, Object> params = new HashMap<String,Object>();
		String params3 = ""; //-- DB로그를 남기기 위해 하단 params2 의 값을 저장할 String
		params.put("cpnId", cpnId);	//쿠폰Id
		
		
		CouponService couponService = new CouponService();
		int result = 0;
		String resultMsg = "";
		
		// 쿠폰정보 조회 
		CpnListAll cpnListAll = couponService.selectCpnListAll(params); 	// 쿠폰상세정보 
		
		
		UpFile upFile = null;
		try{
			upFile = new UpFile(request, "/excelfile/");//파일 업로드
			ExcelContoller excels = null;
			
			if(cpnListAll.getIsTargetCpn().equals("N")){
				excels = new ExcelContoller(upFile.getFiles(),3);//엑셀 파일 파싱
			}
			else{
				excels = new ExcelContoller(upFile.getFiles(),4);//엑셀 파일 파싱
			}
			excels.setSheet(0);//엑셀파일이 있는 시트
		
//		if(excels.ChkTitle("쿠폰 일괄 발행")){//문서의 타이틀명 체크
			for(int i=0;true;i++){
				/*
				 * excels.getString("가지고 올 라인");
				 * 결과 Str 중 str[0]은 결과 코드를 의미한다. true/false
				 * str[1]~str[n] 문서에 있는 항목의 순서대로 가져온다.
				**/
					String[] str = excels.getString(i); 
					if(str[0].equals("false")){
						break;
					}
					
					//DB =======================================
					String userName = str[2];
					String phone = str[3];
					String couponNo = "";
					if(cpnListAll.getIsTargetCpn().equals("Y")){
						couponNo = str[4];
					}
					
					/*##################### 암호화 S #####################*/
					String authName = ktService.encoding(userName);
//					phone = ktService.encoding(phone);
					/*##################### 암호화 E #####################*/
					
					HashMap<String, Object> params2 = new HashMap<String,Object>();
//					String authName = userName;//TODO 암호화 처리 필요
					params2.put("authName", authName);
					params2.put("phone", phone);
					params2.put("couponNo", couponNo);
					

					// TODO 삭제 /////////////////////////////////////////////////////////////
					System.out.println("userName : " + userName);
					System.out.println("phone : " + phone);
					System.out.println("couponNo : " + couponNo);
					// TODO 삭제 /////////////////////////////////////////////////////////////
					
					//DB 에서 user_id 가져오기
					UserInfo userInfo = couponService.getUserInfo(params2);
					if( userInfo==null || "".equals(userInfo.getUserId()) ) {
						String msg = userName+"("+phone+") 는 등록되어있지않은 사용자정보입니다.";
						resultMsg += msg;
						log.debug(msg);
						
						// TODO 삭제 /////////////////////////////////////////////////////////////
						System.out.println(msg);
						// TODO 삭제 /////////////////////////////////////////////////////////////
						
						continue;
					}

					String userId = userInfo.getUserId();
					String os = userInfo.getOs();

					// TODO 삭제 /////////////////////////////////////////////////////////////
					System.out.println("userId : " + userId);
					// TODO 삭제 /////////////////////////////////////////////////////////////

					//쿠폰 다운로드 여부 확인 my_cpn
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//쿠폰Id
					params2.put("userId", userId);	//사용자Id
					
					String myCpnId = couponService.getMyCpn(params2);
					if( myCpnId != null ) {	//이미 등록되어 있는 경우 에러처리
						resultMsg = userName+"("+phone+") 는 이미 쿠폰을 발행하였습니다. 확인하고 다시 발행해주세요.";
						throw new Exception(resultMsg);
					}
					

					String membId = cpnListAll.getMembId();//멤버십id -DB
					String cpart = "p";
					String barcode = "";	//자체인증번호 -생성 / 공통인증번호-DB / POOL관리쿠폰?? -DB
					String regDay = "";//CONVERT(VARCHAR(8),GETDATE(),112)
					String stat = "R";
					String sday = cpnListAll.getValSday();//유효시작일	-DB
					String eday = cpnListAll.getValEday();//유효종료일	-DB
					String imgHost = "";//이미지 경로 -DB
					String imgUrl = "";//이미지 파일명 -DB
					String chgDay = "";//CONVERT(VARCHAR(8),GETDATE(),112)
					String useCnt = "";//쿠폰최대사용회수 -DB
					String usimData = "";//??
					 
					String regDt = "";//CONVERT(VARCHAR(8),GETDATE(),112)
					String regTm = "";//REPLACE(CONVERT(VARCHAR(8),GETDATE(),108),':','')

					
					if(cpnListAll.getIsTargetCpn().equals("N")){ //-- 타겟쿠폰이 아닌 경우,
						// 쿠폰번호발행구분에 따라 쿠폰번호 발행 
						barcode = getBarcode(cpnListAll, couponService);
					}
					else{ //-- 타겟쿠폰인 경우
						barcode = getBarcode(cpnListAll, couponService, couponNo);
					}

					// 바코드 이미지 생성
					// BarcodeUtil(String Os,String MembId, String CardLevel, String Name, String ValSday, String ValEday)
					String level = "";
					String name = "";


					//BarcodeUtil barcodeUtil = new BarcodeUtil(os, membId, level, name, sday, eday, "01"); //-- 01:BARCODE TYPE
					BarcodeUtil barcodeUtil = null;//--복합결제이면, 
					if (cpnListAll.getCompPayYn().equals("Y")){
						barcodeUtil = new BarcodeUtil(userId);
					}
					else{ //-- 복합결재용 + 멤버십 사용시 필요한 바코드 생성
						//[20121108][inoky@2bsolution.com] coupon barcode image widh값을 가져오기위한 userid 추가
						barcodeUtil = new BarcodeUtil(userId, os, membId, level, name, sday, eday, "01", true);
						barcodeUtil.Make(barcode, 2);
					}

					imgHost = barcodeUtil.getHost();
					//imgUrl = barcodeUtil.getB_BarcodePath(); //-- 큰 이미지
					imgUrl = barcodeUtil.getS_BarcodePath(); //-- 작은 이미지
					
					
					// my_cpn 입력
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//쿠폰Id
					params2.put("userId", userId);	//사용자Id
					params2.put("barcode", barcode);
					params2.put("cpart", cpart);
					params2.put("stat", stat);
					params2.put("imgHost", imgHost);
					params2.put("imgUrl", imgUrl);
					result = couponService.insertMyCpn(params2);
					
					
					// cpn_gen_his 입력
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//쿠폰Id
					params2.put("custId", userId);	//사용자Id
					params2.put("barcode", barcode);
					params2.put("stat", stat);
					result = couponService.insertCpnGenHis(params2);
					
					
					// MW_CS_ISSUE_CPN 입력
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//쿠폰Id
					params2.put("userId", userId);	//사용자Id
					params2.put("barcode", barcode);
					params2.put("cmConfBarcdYn", "02".equals(cpnListAll.getBarIssueType())?"Y":"N");
					params2.put("regUser", regUser);
					result = couponService.insertMwCsIssueCpn(params2);
					
					
					params3 = params3 + params2.toString(); //--DB 로그 등록을 위한 코드 입니다.
						
				}// END for
				
			couponService.commit();
			
			result = 1;
			
			/*##################### DATA ACCESS LOG DB에 남기기 S #####################*/
			params.put("pageURL", "/member/coupon_hand_issue_file.ms");
		
			params.put("cpnId", cpnId);
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString() + params3 );
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB에 남기기 E #####################*/
			
			
//		}else{
//			upFile.getFiles().delete();// 파일 삭제
////			resultMsg = "err";
//		}
		}catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			//rollback
			couponService.rollback();
			// 파일 삭제
			upFile.getFiles().delete();
			//resultMsg = "err";
			result = 0;
		}

		//JsonErrMsg(resultMsg,response);
		request.setAttribute("targetUrl", "/member/coupon_hand_issue_file_pop.ms");
		request.setAttribute("actResult", result);
		request.setAttribute("actResultMsg", resultMsg);
		return "common/result_message";
	}

	/**
	 * @Method Name : cpnIdDupCheck
	 * @Description : 쿠폰 등록 시, 쿠폰 id를 수동으로 넣는 경우, 쿠폰 id 중복여부를 체크한다.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 0/1 (0:중복아님, 1:중복)
	 * @author trkim
	 * @since 2012.12.14
	 */
	@RequestMapping(value="/member/cpnId_dupCheck.ms")
	public String cpnIdDupCheck(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		CouponService couponService = new CouponService();
		String manualCpnId = checkStr(request.getParameter("manualCpnId"));
		JSONObject jObj = new JSONObject();
		
		int isDupCpnId = 0;
		isDupCpnId = couponService.cpnIdDupCheck(manualCpnId);
		
		jObj.put("isDupCpnId", String.valueOf(isDupCpnId));
		
		request.setAttribute("JSONObject", jObj);
		
		return "common/result_page";
	}

	
	private void JsonErrMsg(String Err, HttpServletResponse response){
		try{
			PrintWriter writer = response.getWriter();
			writer.write("{\"err\":\""+Err+"\"}");
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	/**
	 * @Method Name : today
	 * @Description : 오늘 날짜를 조회한다.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2010.04.23
	 */
	public static String today() {
		java.sql.Date CurrDate = new java.sql.Date((new java.util.Date()).getTime());
		return CurrDate.toString();
	}
}