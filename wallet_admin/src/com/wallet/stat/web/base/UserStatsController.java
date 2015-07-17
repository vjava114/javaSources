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

import com.wallet.stat.model.MwStSearchTerms;
import com.wallet.stat.model.MwStUserStats;
import com.wallet.stat.service.MwStUserStatsService;
import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;

/*
 * Filename	: UserStatsController.java
 * Class	: com.wallet.stat.web.base.UserStatsController
 * History	: 2012/08/23, psj, 작업구분 : 이용자 통계
 * Comment	:
 */
@Controller
public class UserStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 이용자 통계 화면 호출
	 * @return	
	 */
	@RequestMapping(value="/stat/user_stats_list.st")
	public String selectUserStatsView(HttpServletRequest request, HttpServletResponse response) {

		//전일 날짜
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.DATE, -1);
	  String bday = formatter.format(cal.getTime());
	  
	  request.setAttribute("bday", bday);
	  
		return "/stat/user_stats_list";
	}
	
	/**
	 * 이용자 통계 - 조회
	 * @return	
	 */
	@RequestMapping(value="/stat/user_stats_list.st", method=RequestMethod.POST)
	public String selectUserStatsList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserStatsController selectUserStatsList STAT ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStUserStats> list = null;
		MwStSearchTerms searchTerms = null;
		MwStUserStatsService service = new MwStUserStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//검색조건 : 시작일
		String eday = checkStr(request.getParameter("eday"), "");			//검색조건 : 종료일
		String period = checkStr(request.getParameter("period"), "");	//검색조건 : 기간 (일별,월병,연도별)

		//검색조건 setting
		searchTerms = new MwStSearchTerms();
		searchTerms.setSday(sday);
		searchTerms.setEday(eday);
		searchTerms.setPeriod(period);
		
		params.put("sday", sday);
		params.put("eday", eday);
		params.put("period", period);
		
		list = service.selectUserStatsList(params);
		
		request.setAttribute("searchTerms", searchTerms);
		request.setAttribute("MwAdUserStatsList", list);
		
		log.debug("### UserStatsController selectUserStatsList END ###");
		
		return "/stat/user_stats_list";
	}
	/**
	 * 가입자전체통계 - 조회
	 * @return	
	 */
	
	@RequestMapping(value="/stat/user_stats_list_all.st")
	public String selectUserStatsListAll(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserStatsController selectUserStatsListAll START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStUserStats> list = null;
		MwStUserStatsService service = new MwStUserStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		list = service.selectUserStatsListAll(params);
		
		request.setAttribute("MwAdUserStatsList", list);
		
		log.debug("### UserStatsController selectUserStatsListAll END ###");
		
		return "/stat/user_stats_list_all";
	}
	
	
	/**
	 * 이용자 통계 - 엑셀
	 * @return	
	 */
	@RequestMapping(value="/stat/user_stats_list_excel.st", method=RequestMethod.POST)
	public String selectUserStatsListExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserStatsController selectUserStatsListExcel STAT ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStUserStats> list = null;
		MwStSearchTerms searchTerms = null;
		MwStUserStatsService service = new MwStUserStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//검색조건 : 시작일
		String eday = checkStr(request.getParameter("eday"), "");			//검색조건 : 종료일
		String period = checkStr(request.getParameter("period"), "");	//검색조건 : 기간 (일별,월병,연도별)

		//검색조건 setting
		searchTerms = new MwStSearchTerms();
		searchTerms.setSday(sday);
		searchTerms.setEday(eday);
		searchTerms.setPeriod(period);
		
		params.put("sday", sday);
		params.put("eday", eday);
		params.put("period", period);
		
		list = service.selectUserStatsList(params);
		
		request.setAttribute("searchTerms", searchTerms);
		request.setAttribute("MwAdUserStatsList", list);
		
		log.debug("### UserStatsController selectUserStatsListExcel END ###");
		
		return "/stat/user_stats_list_excel";
	}
	
	/**
	 * 가입자전체통계 - 엑셀
	 * @return	
	 */
	
	@RequestMapping(value="/stat/user_stats_list_all_excel.st")
	public String selectUserStatsListAllExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserStatsController selectUserStatsListAllExcel START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStUserStats> list = null;
		MwStUserStatsService service = new MwStUserStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		list = service.selectUserStatsListAll(params);
		
		request.setAttribute("MwAdUserStatsList", list);
		
		log.debug("### UserStatsController selectUserStatsListAllExcel END ###");
		
		return "/stat/user_stats_list_all_excel";
	}
}
