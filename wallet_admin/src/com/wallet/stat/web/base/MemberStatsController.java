package com.wallet.stat.web.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rocomo.util.FormatUtil;
import com.wallet.stat.model.MwStSearchTerms;
import com.wallet.stat.model.MwStMemberStatsMocaPay;
import com.wallet.stat.model.MwStMemberStatsOs;
import com.wallet.stat.model.MwStMemberStatsAge;
import com.wallet.stat.service.MwStMemberStatsService;
import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;

/*
 * Filename	: MemberController.java
 * Class	: com.wallet.stat.web.base.MemberController
 * History	: 2012/08/23, psj, 작업구분 : 가입자 통계
 * Comment	:
 */
@Controller
public class MemberStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 누적가입자현황 (탭구분:누적가입자현황:acm)
	 * @return	
	 */
	@RequestMapping(value="/stat/member_stats_acm_list.st")
	public String selectMemberStatsAcmList(HttpServletRequest request, HttpServletResponse response) {

		log.debug("### MemberStatsController selectMemberStatsAcmList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwStMemberStatsOs statsOs = null;
		MwStMemberStatsAge statsAge = null;
		MwStMemberStatsMocaPay mocaPay = null;
		MwStMemberStatsService service = new MwStMemberStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		//오늘날짜
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);
	  
	  String[] day = reg_day.split("-");
	  String yearMonthDay = day[0] + "년 " + day[1] + "월 " + day[2] + "일";
	  
		//OS/이통사별 통계
		statsOs = service.selectMemberStatsAcmOs(params);
		//연령/성별 통계
		statsAge = service.selectMemberStatsAcmAge(params);
		//모카페이 통계
		mocaPay = service.selectMemberStatsAcmMocaPay(params);

		request.setAttribute("yearMonthDay", yearMonthDay);
		request.setAttribute("statsOs", statsOs);
		request.setAttribute("statsAge", statsAge);
		request.setAttribute("mocaPay", mocaPay);

		log.debug("### MemberStatsController selectMemberStatsAcmList END ###");
		
		return "/stat/member_stats_acm_list";
	}

	/**
	 * 신규가입자현황  화면 호출
	 */
	@RequestMapping(value="/stat/member_stats_new_list.st")
	public String selectMemberStatsNewView(HttpServletRequest request, HttpServletResponse response) {
		
		//전일 날짜
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.DATE, -1);
	  String bday = formatter.format(cal.getTime());
	  
	  request.setAttribute("bday", bday);
	  
		return "/stat/member_stats_new_list";
	}
	
	/**
	 * 신규가입자현황   - 조회 (탭구분:신규가입자현황:new)
	 * @param kind : os(단말기준(OS, 이통사)) , age(이용자기준(성별, 연령)), moca(모카페이 유입)
	 * @param day(일별), month(월별), year(년도별)
	 * @return	
	 */
	@RequestMapping(value="/stat/member_stats_new_list.st", method=RequestMethod.POST)
	public String selectMemberStatsNewList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### MemberStatsController selectMemberStatsNewList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		
		List<MwStMemberStatsOs> os_list = null;
		List<MwStMemberStatsAge> age_list = null;
		List<MwStMemberStatsMocaPay> mocaPay = null;
		MwStSearchTerms searchTerms = null;
		MwStMemberStatsService service = new MwStMemberStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//검색조건 : 시작일
		String eday = checkStr(request.getParameter("eday"), "");			//검색조건 : 종료일
		String period = checkStr(request.getParameter("period"), "");	//검색조건 : 기간 (일별,월병,연도별)
		String kind = checkStr(request.getParameter("kind"), "");			//검색조건 : 단말기준, 이용자기준,모카페이 유
		
		//검색조건 setting
		searchTerms = new MwStSearchTerms();
		searchTerms.setSday(sday);
		searchTerms.setEday(eday);
		searchTerms.setPeriod(period);
		searchTerms.setKind(kind);
		
		params.put("sday", sday);
		params.put("eday", eday);
		params.put("period", period);
		
		if("os".equals(kind)) {
			
				if("day".equals(period)) {
					os_list = service.selectMemberStatsNewOsDayList(params);
				} else if("month".equals(period)) {
					os_list = service.selectMemberStatsNewOsMonthList(params);
				} else  {
					os_list = service.selectMemberStatsNewOsYearList(params);
				}
			
			request.setAttribute("memberStatsNewList", os_list);
		} else if("age".equals(kind)) {
			age_list = service.selectMemberStatsNewAgeList(params);
			request.setAttribute("memberStatsNewList", age_list);
		} if("moca".equals(kind)) {
			mocaPay = service.selectMemberStatsNewMocaPayList(params);
			request.setAttribute("memberStatsNewList", mocaPay);
		}

		request.setAttribute("searchTerms", searchTerms);
		
		
		log.debug("### MemberStatsController selectMemberStatsNewList END ###");
		
		return "/stat/member_stats_new_list";
	}
	
	/**
	 * 신규가입자현황   - 엑셀저장 (탭구분:신규가입자현황:new)
	 * @param kind : os(단말기준(OS, 이통사)) , age(이용자기준(성별, 연령)), moca(모카페이 유입)
	 * @param day(일별), month(월별), year(년도별)
	 * @return	
	 */
	@RequestMapping(value="/stat/member_stats_new_list_excel.st", method=RequestMethod.POST)
	public String selectMemberStatsNewListExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### MemberStatsController selectMemberStatsNewListExcel START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		
		List<MwStMemberStatsOs> os_list = null;
		List<MwStMemberStatsAge> age_list = null;
		List<MwStMemberStatsMocaPay> mocaPay = null;
		MwStSearchTerms searchTerms = null;
		MwStMemberStatsService service = new MwStMemberStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//검색조건 : 시작일
		String eday = checkStr(request.getParameter("eday"), "");			//검색조건 : 종료일
		String period = checkStr(request.getParameter("period"), "");	//검색조건 : 기간 (일별,월병,연도별)
		String kind = checkStr(request.getParameter("kind"), "");			//검색조건 : 단말기준, 이용자기준,모카페이 유
		
		//검색조건 setting
		searchTerms = new MwStSearchTerms();
		searchTerms.setSday(sday);
		searchTerms.setEday(eday);
		searchTerms.setPeriod(period);
		searchTerms.setKind(kind);
		
		params.put("sday", sday);
		params.put("eday", eday);
		params.put("period", period);
		
		if("os".equals(kind)) {
			
				if("day".equals(period)) {
					os_list = service.selectMemberStatsNewOsDayList(params);
				} else if("month".equals(period)) {
					os_list = service.selectMemberStatsNewOsMonthList(params);
				} else  {
					os_list = service.selectMemberStatsNewOsYearList(params);
				}
			
			request.setAttribute("memberStatsNewList", os_list);
		} else if("age".equals(kind)) {
			age_list = service.selectMemberStatsNewAgeList(params);
			request.setAttribute("memberStatsNewList", age_list);
		} if("moca".equals(kind)) {
			mocaPay = service.selectMemberStatsNewMocaPayList(params);
			request.setAttribute("memberStatsNewList", mocaPay);
		}

		request.setAttribute("searchTerms", searchTerms);
		
		
		log.debug("### MemberStatsController selectMemberStatsNewListExcel END ###");
		
		return "/stat/member_stats_new_list_excel";
	}
}
