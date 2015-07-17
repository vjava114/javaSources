/**
 * @author 이경훈
 * @Date 2012-08-14
 * */
package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.DateTime;
import com.wallet.membership.model.CpnList;
import com.wallet.membership.model.CpnListExample;
import com.wallet.membership.service.CpnListService;

@Controller
public class CouponOrderManagerController extends CommonController {
	private final String PAGE_CODE = "COUPON_ORDER_MANAGER";
	private Logger log = Log.getLogger("logs");
	
	/*파라메타 정보
	 * -----------------------------------------------------
	 * reCommendCpnList : 아래정보가 담긴 리스트
	 * count : 리스트의 총 갯수
	 * -----------------------------------------------------
	 * list.cpnId : 쿠폰아이디
	 * list.membId : 멤버아이디
	 * list.partV : 쿠폰 구분 코두
	 * list.part : 쿠폰 구분 한글
	 * list.compName : 제휴사 이름
	 * list.membName : 멤버쉽 이름
	 * list.recommYn 추천 전시 사용여부
	 * list.recommSeq 추천 전시 순위
	 * list.mainDisplay 멤버십, 해외 전시 사용여부
	 * list,dispOrder 멤버십, 해외 전시 순위
	 * */
	
	/*요청 주소정보
	 * =====================================
	 * /member/coupon_order_manager.ms : 추천 리스트 화면
	 * /member/coupon_memb_order_manager.ms : 맴버십 조회 화면
	 * /member/coupon_fore_order_manager.ms : 해외 쿠폰 화면
	 * -------------------------------------------------------------------
	 * /member/coupon_order_edit.ms : 추천 수정화면 <----- 추천에서 셀렉트 박스에서 옵션변경시 호출
	 * @param beforeNo  :  전시 순위 변환전의 순위
	 * @param afterNo     :  전시 순위 변환후의 순위
	 * @param cpnId        :  쿠폰아이디
	 * @param membId    :  멤버십 아이디
	 * @param part          :  구분
	 * --------------------------------------------------------------------
	 * /member/coupon_memb_order_edit.ms : 맴버십/해외 수정 <--- 맴버십 및 해외에서 셀렉트 박스 옵션변경시 호출
	 * @param beforeNo  :  전시 순위 변환전의 순위
	 * @param afterNo     :  전시 순위 변환후의 순위
	 * @param cpnId        :  쿠폰아이디
	 * @param membId    :  멤버십 아이디
	 * @param part          :  구분
	 * ---------------------------------------------------------------------
	 * /member/coupon_memb_order_yn.ms : 맴버십/해외 사용여부 <--- 체크박스 상태 변경시 호출
	 * @param beforeNo : 현재 순위
	 * @param displayYn : 사용여부 체크시 Y, 해제시 N
	 * @param cpnId       : 쿠폰아이디
	 * @param membId   : 멤버십 아이디
	 * @param part         : 구분
	 * */
	
	
	
	
	/**
	 * 쿠폰 전시 조회 추천 조회 화면
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/coupon_order_manager.ms")
	public String RecommendList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		
		CpnListService cpnListService = new CpnListService();
		List<CpnList> reCommendCpnList = new ArrayList<CpnList>();
		int count = 0;
		try{
			CpnListExample cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd")).andCpnStatequalTo("01")
			.andPartVNotEqualTo("R")//-- 해외쿠폰이아닌경우
			.andMainDisplayEqualTo("Y");
			
			count = cpnListService.CouponOrderByCount(cpnListExample);
			
			cpnListExample.setOrderByClause("RECOMM_SEQ ASC");
			reCommendCpnList = cpnListService.CouponOrderByExample(cpnListExample);
			
		}catch (Exception e) {
			// TODO: handle exception
		}	
		
		request.setAttribute("reCommendCpnList",reCommendCpnList);				
		request.setAttribute("count", count);
			
		return "member/coupon_order_manager";
		}
	
	
	/**
	 * 쿠폰 전시 조회 추천 수정 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/coupon_order_edit.ms")
	public String RecommendEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		
		String beforeNo =checkStr(request.getParameter("beforeNo"),"");
		String afterNo = checkStr(request.getParameter("afterNo"),"");
		
		String cpnId = checkStr(request.getParameter("cpnId"),"");
		String membId = checkStr(request.getParameter("membId"),"");
		String part = checkStr(request.getParameter("part"),"");
		
		log.info("beforeNo --->" + beforeNo);
		log.info("afterNo --->" + afterNo);
		log.info("cpnId --->" + cpnId);
		log.info("membId --->" + membId);
		log.info("part --->" + part);
		
		if(beforeNo.equals("")|| afterNo.equals("")||cpnId.equals("")){
			JsonErrMsg("err",response);
			return null;
		}
		
		int beforeNoInt = Integer.parseInt(beforeNo);
		int afterNoInt = Integer.parseInt(afterNo);
		
		if(beforeNoInt == 4){
			beforeNoInt = 0;
		}
		
		CpnListService cpnListService = new CpnListService();
		try{			
			//해당 순번 업데이트
			//업데이트 할 순위 셋
			CpnList updateData = new CpnList();			
			updateData.setRecommSeq(afterNoInt);
			updateData.setRecommYn("Y");
			CpnListExample cpnListExample = new CpnListExample();//업데이트 용 Example			
			cpnListExample.or().andCpnIdEqualTo(cpnId).andRecommSeqEqualTo(beforeNoInt);
			
			CpnListExample cpnAllExample = null;//전체 순위 조정용 Example
			CpnList updateAll = null;
			
			//순위가 올라감
			if(beforeNoInt<afterNoInt){				
				//업데이트 되는 순위의 다음 순위를 모두 +1
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				cpnAllExample.or().andRecommSeqGreaterThanOrEqualTo(afterNoInt);
				updateAll.setUpRecommPlus("Auto Add");//---값이 Null이 아니라면 자동으로 채워진다.
				cpnListService.update(updateAll, cpnAllExample);
				
				//해당 순위 변경
				cpnListService.update(updateData, cpnListExample);
				
				//추천이므로 3위 밖으로 나가는 수는 모두 0으로 리셋
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				cpnAllExample.or().andRecommSeqGreaterThan(3);
				updateAll.setRecommSeq(0);
				updateAll.setRecommYn("N");
				cpnListService.update(updateAll, cpnAllExample);
				
			//순위가 내려감
			}else if(beforeNoInt>afterNoInt){				
				//업데이트 되는 순위의 윗 순위를 모두 -1
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				cpnAllExample.or().andRecommSeqLessThanOrEqualTo(afterNoInt).andRecommSeqGreaterThan(1);
				updateAll.setUpRecommMu("Auto Add");//---값이 Null이 아니라면 자동으로 채워진다.
				cpnListService.update(updateAll, cpnAllExample);
				
				//해당 순위 변경
				cpnListService.update(updateData, cpnListExample);
				
				//추천이므로 3위 밖으로 나가는 수는 모두 0으로 리셋
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				cpnAllExample.or().andRecommSeqGreaterThan(3);
				updateAll.setRecommSeq(0);
				updateAll.setRecommYn("N");
			}
			cpnListService.commit();
		}catch (Exception e) {
			// TODO: handle exception
			cpnListService.rollback();
			JsonErrMsg("err",response);
			return null;
		}	
		JsonErrMsg("",response);
		return null;
		}
	
	/**
	 * 쿠폰 전시 조회 맴버십 조회 화면 ==> 전체 조회로 변경됨
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/coupon_memb_order_manager.ms")
	public String MemberShipList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		
		CpnListService cpnListService = new CpnListService();
		List<CpnList> cpnList = new ArrayList<CpnList>();
		/*
		List<CpnList> reCommendCpnList = new ArrayList<CpnList>();
		List<CpnList> reCommendZeroCpnList = new ArrayList<CpnList>();
		int count = 0;
		int zerocount = 0;
		*/
		int cpnListCnt = 0;
		try{
			/*
			//Zero List
			CpnListExample cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andPartVNotEqualTo("R") //-- 해외쿠폰이 아닌것만 보이도록 수정
			.andDispOrderEqualTo("0")
			.andCpnStatequalTo("01")
			.andMainDisplayEqualTo("Y");
			zerocount = cpnListService.CouponOrderByCount(cpnListExample);
			cpnListExample.setOrderByClause("disp_order ASC");
			reCommendZeroCpnList = cpnListService.CouponOrderByExample(cpnListExample);
		
			//Non Zero List
			cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andPartVNotEqualTo("R")//-- 해외쿠폰이 아닌것만 보이도록 수정
			.andDispOrderNotEqualTo("0")
			.andCpnStatequalTo("01") 
			.andMainDisplayEqualTo("Y");

			count = cpnListService.CouponOrderByCount(cpnListExample);
			cpnListExample.setOrderByClause("disp_order ASC");
			reCommendCpnList = cpnListService.CouponOrderByExample(cpnListExample);
			*/
			
			CpnListExample cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andPartVNotEqualTo("R") //-- 해외쿠폰이 아닌것만 보이도록 수정
			.andCpnStatequalTo("01")
			.andMainDisplayEqualTo("Y");
			cpnListCnt = cpnListService.CouponOrderByCount(cpnListExample);
			cpnListExample.setOrderByClause("disp_order ASC");
			cpnList = cpnListService.CouponOrderByExample(cpnListExample);
			
		}catch (Exception e) {
			// TODO: handle exception
		}	
		/*
		request.setAttribute("reCommendZeroCpnList",reCommendZeroCpnList);		
		request.setAttribute("reCommendCpnList",reCommendCpnList);
		request.setAttribute("count", count);
		request.setAttribute("zerocount", zerocount);
		*/
		request.setAttribute("cpnList", cpnList);
		request.setAttribute("cpnListCnt", cpnListCnt);

		System.out.println(cpnListCnt);

		return "member/coupon_memb_order_manager";
		}
	
	
	/**
	 * 쿠폰 전시 조회 해외쿠폰 화면
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/coupon_fore_order_manager.ms")
	public String ForeignList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		
		CpnListService cpnListService = new CpnListService();
		List<CpnList> reCommendCpnList = new ArrayList<CpnList>();
		List<CpnList> reCommendZeroCpnList = new ArrayList<CpnList>();
		
		int count = 0;
		int zerocount = 0;
		try{
			//Zero List
			CpnListExample cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd")).andPartVEqualTo("R").andDispOrderEqualTo("0").andCpnStatequalTo("01");
			zerocount = cpnListService.CouponOrderByCount(cpnListExample);
			cpnListExample.setOrderByClause("disp_order ASC");
			reCommendZeroCpnList = cpnListService.CouponOrderByExample(cpnListExample);
		  
			//Non Zero List
			cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd")).andPartVEqualTo("R").andDispOrderNotEqualTo("0").andCpnStatequalTo("01");
			count = cpnListService.CouponOrderByCount(cpnListExample);
			cpnListExample.setOrderByClause("disp_order ASC");
			reCommendCpnList = cpnListService.CouponOrderByExample(cpnListExample);
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}	
		
		request.setAttribute("reCommendZeroCpnList",reCommendZeroCpnList);		
		request.setAttribute("reCommendCpnList",reCommendCpnList);				
		request.setAttribute("count", count);
		request.setAttribute("zerocount", zerocount);

		System.out.println(count);
		System.out.println(zerocount);
			
		return "member/coupon_fore_order_manager";
		}
	
	
	/**
	 * 쿠폰 전시 조회 수정 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_memb_order_edit"
	 */ 
	@RequestMapping(value="/member/coupon_memb_order_edit.ms")
	public String MemberShipEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		
		String beforeNo =checkStr(request.getParameter("beforeNo"),"");
		String afterNo = checkStr(request.getParameter("afterNo"),"");
		
		String cpnId = checkStr(request.getParameter("cpnId"),"");
		String membId = checkStr(request.getParameter("membId"),"");
		String part = checkStr(request.getParameter("partV"),"");
				
		log.info("beforeNo --->" + beforeNo);
		log.info("afterNo --->" + afterNo);
		log.info("cpnId --->" + cpnId);
		log.info("membId --->" + membId);
		log.info("part --->" + part);
		
		if(beforeNo.equals("")|| afterNo.equals("")||cpnId.equals("")){
			JsonErrMsg("err",response);
			return null;
		}
		
		int beforeNoInt = Integer.parseInt(beforeNo);
		int afterNoInt = Integer.parseInt(afterNo);
		
		CpnListService cpnListService = new CpnListService();
		try{			
			//해당 순번 업데이트
			//업데이트 할 순위 셋
			CpnList updateData = new CpnList();			
			updateData.setDispOrder(""+afterNoInt);
			CpnListExample cpnListExample = new CpnListExample();//업데이트 용 Example			
			cpnListExample.or().andCpnIdEqualTo(cpnId);
			
			CpnListExample cpnAllExample = null;//전체 순위 조정용 Example
			CpnList updateAll = null;
			
			//순위가 올라감
			if(beforeNoInt>afterNoInt || beforeNoInt==0){				
				//업데이트 되는 순위의 다음 순위를 모두 +1
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				
				cpnAllExample.or().andDispOrderGreaterThanOrEqualTo(""+afterNoInt).andPartVEqualTo(part);
				updateAll.setUpMainPlus("Auto Add");//---값이 Null이 아니라면 자동으로 채워진다.
				cpnListService.update(updateAll, cpnAllExample);
				
				//해당 순위 변경
				cpnListService.update(updateData, cpnListExample);				
				
			//순위가 내려감
			}else if(beforeNoInt<afterNoInt){				
				//업데이트 되는 순위의 윗 순위를 모두 -1
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				cpnAllExample.or().andDispOrderLessThanOrEqualTo(""+afterNoInt).andDispOrderGreaterThan(""+beforeNoInt).andPartVEqualTo(part);
				updateAll.setUpMainMu("Auto Add");//---값이 Null이 아니라면 자동으로 채워진다.
				cpnListService.update(updateAll, cpnAllExample);
				
				//해당 순위 변경
				cpnListService.update(updateData, cpnListExample);								
			}
			cpnListService.commit();
			System.out.println(beforeNo);
		}catch (Exception e) {
			// TODO: handle exception
			cpnListService.rollback();
			JsonErrMsg("err",response);
			return null;
		}	
		JsonErrMsg("",response);
		return null;
		}
	
	
	/**
	 * 쿠폰 전시 조회 전시여부 수정 동작
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_memb_order_edit"
	 */ 
	@RequestMapping(value="/member/coupon_memb_order_yn.ms")
	public String MemberShipYn(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		
		String beforeNo =checkStr(request.getParameter("beforeNo"),"");
		String displayYn =checkStr(request.getParameter("displayYn"),"false");
		String cpnId = checkStr(request.getParameter("cpnId"),"");
		String membId = checkStr(request.getParameter("membId"),"");
		String part = checkStr(request.getParameter("part"),"");
		
		log.info("beforeNo --->" + beforeNo);
		log.info("displayYn --->" + displayYn);
		log.info("cpnId --->" + cpnId);
		log.info("membId --->" + membId);
		log.info("part --->" + part);
		
		if(displayYn.equals("true")){
			displayYn = "Y";
		}else{
			displayYn = "N";
		}
		
		if(beforeNo.equals("")|| cpnId.equals("")){
			return null;
		}
		
		CpnListService cpnListService = new CpnListService();
		try{			
			//해당 순번 업데이트
			//업데이트 할 순위 셋
			CpnList updateData = new CpnList();
			updateData.setMainDisplay(displayYn);
			CpnListExample cpnListExample = new CpnListExample();//업데이트 용 Example			
			cpnListExample.or().andCpnIdEqualTo(cpnId).andDispOrderEqualTo(beforeNo);
			cpnListService.update(updateData, cpnListExample);
			
			cpnListService.commit();
		}catch (Exception e) {
			// TODO: handle exception
			cpnListService.rollback();
			System.out.println(e.getMessage());
			JsonErrMsg("err",response);
			return null;
		}	
		JsonErrMsg("",response);
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