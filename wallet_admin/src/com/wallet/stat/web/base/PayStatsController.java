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

import com.wallet.stat.model.MwStPayStats;
import com.wallet.stat.model.MwStSearchTerms;
import com.wallet.stat.service.MwStPayStatsService;
import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;

/*
 * Filename	: PayStatsController.java
 * Class	: com.wallet.stat.web.base.PayStatsController
 * History	: 2012/08/23, psj, 작업구분 : 결제 통계
 * Comment	:
 */
@Controller
public class PayStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 결제 통계 화면 호출
	 * @return	
	 */
	@RequestMapping(value="/stat/pay_stats_list.st")
	public String selectPayStatsView(HttpServletRequest request, HttpServletResponse response) {
		
		//전일 날짜
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.DATE, -1);
	  String bday = formatter.format(cal.getTime());
	  
	  request.setAttribute("bday", bday);
	  
		return "/stat/pay_stats_list";
		
	}
	
	/**
	 * 결제 통계 - 조회
	 * @return	
	 */
	@RequestMapping(value="/stat/pay_stats_list.st", method=RequestMethod.POST)
	public String selectPayStatsList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PayStatsController selectPayStatsList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStPayStats> list = null;
		MwStPayStats mwAdPayStats = null;
		MwStSearchTerms searchTerms = null;
		MwStPayStatsService service = new MwStPayStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//검색조건 : 시작일
		String eday = checkStr(request.getParameter("eday"), "");			//검색조건 : 종료일
		String period = checkStr(request.getParameter("period"), "");	//검색조건 : 기간 (일별,월병,연도별,누적)
		
		//검색조건 setting
		searchTerms = new MwStSearchTerms();
		searchTerms.setSday(sday);
		searchTerms.setEday(eday);
		searchTerms.setPeriod(period);
	
		params.put("sday", sday);
		params.put("eday", eday);
		params.put("period", period);
		
		//list = service.selectPayStatsList(params);
		
		if("day".equals(period)) {
			//일별
			list = service.selectPayStatsDayList(params);
		} else if( ("month".equals(period)) || ("year".equals(period)) ) {
			//월별, 년도별
			list = service.selectPayStatsMonthYearList(params);
		} else  {
			//누적
			mwAdPayStats = service.selectPayStatsAcc(params);
		}

		request.setAttribute("searchTerms", searchTerms);		//검색조건
		request.setAttribute("MwAdPayStatsList", list);			//일/월/년
		request.setAttribute("mwAdPayStats", mwAdPayStats);	//누적
		
		log.debug("### PayStatsController selectPayStatsList END ###");
		
		return "/stat/pay_stats_list";
		
	}
	
	/**
	 * 결제 통계 - 엑셀
	 * @return	
	 */
	@RequestMapping(value="/stat/pay_stats_list_excel.st", method=RequestMethod.POST)
	public String selectPayStatsListExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PayStatsController selectPayStatsListExcel START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStPayStats> list = null;
		MwStPayStats mwAdPayStats = null;
		MwStSearchTerms searchTerms = null;
		MwStPayStatsService service = new MwStPayStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//검색조건 : 시작일
		String eday = checkStr(request.getParameter("eday"), "");			//검색조건 : 종료일
		String period = checkStr(request.getParameter("period"), "");	//검색조건 : 기간 (일별,월병,연도별,누적)
		
		//검색조건 setting
		searchTerms = new MwStSearchTerms();
		searchTerms.setSday(sday);
		searchTerms.setEday(eday);
		searchTerms.setPeriod(period);
	
		params.put("sday", sday);
		params.put("eday", eday);
		params.put("period", period);
		
		//list = service.selectPayStatsList(params);
		
		if("day".equals(period)) {
			//일별
			list = service.selectPayStatsDayList(params);
		} else if( ("month".equals(period)) || ("year".equals(period)) ) {
			//월별, 년도별
			list = service.selectPayStatsMonthYearList(params);
		} else  {
			//누적
			mwAdPayStats = service.selectPayStatsAcc(params);
		}

		request.setAttribute("searchTerms", searchTerms);		//검색조건
		request.setAttribute("MwAdPayStatsList", list);			//일/월/년
		request.setAttribute("mwAdPayStats", mwAdPayStats);	//누적
		
		log.debug("### PayStatsController selectPayStatsListExcel END ###");
		
		return "/stat/pay_stats_list_excel";
		
	}

	
}
