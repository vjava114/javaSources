package com.wallet.stat.web.base;

import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.stat.model.MwStHubStats;
import com.wallet.stat.model.MwStMarketStats;
import com.wallet.stat.model.MwStSearchTerms;
import com.wallet.stat.service.MwStHubStatsService;
import com.wallet.stat.service.MwStMarketStatsService;
import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;

/*
 * Filename	: HubStatsController.java
 * Class	: com.wallet.stat.web.base.HubStatsController
 * History	: 2012/08/23, psj, 작업구분 : 허브페이지 유입 통계
 * Comment	:
 */
@Controller
public class HubStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 허브페이지 유입 통계 화면 호출
	 * @return	
	 */
	@RequestMapping(value="/stat/hub_stats_list.st")
	public String selectHubStatsView(HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStMarketStats> title = null;
		MwStMarketStatsService service = new MwStMarketStatsService();
		
		//전일 날짜
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.DATE, -1);
	  String bday = formatter.format(cal.getTime());

		//공통코드 : title 조회
		title = service.selectMarketStatsTitle();
	
		request.setAttribute("bday", bday);
		request.setAttribute("title", title);				//title 헤더
		
		return "/stat/hub_stats_list";
	}
	
	/**
	 * 허브페이지 유입 통계- 조회
	 * @return	
	 */
	@RequestMapping(value="/stat/hub_stats_list.st", method=RequestMethod.POST)
	public String selectHubStatsList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### HubStatsController selectHubStatsList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStMarketStats> title = null;
		MwStMarketStatsService market_service = new MwStMarketStatsService();
		List<MwStHubStats> list = null;
		MwStSearchTerms searchTerms = null;
		MwStHubStatsService service = new MwStHubStatsService();
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
		
		//공통코드 : title 조회
		title = market_service.selectMarketStatsTitle();
		
		//타이틀별로 동적 쿼리 위해 list 담기
		List<String> title_list = new ArrayList<String>();
		for(int i=0; i<title.size(); i++) {
			title_list.add(title.get(i).getCode().toUpperCase());	
		}
		
		params.put("title_list", title_list);
		params.put("title_list2", title_list);
		
		log.debug("### title : " + title);
		
		//타이틀별 목록 조회
		List<Map<String,String>> listmap = null;
		listmap = service.selectHubStatsList(params);
		
		log.debug("### list : " + listmap);

		String hubHtml = hubMakeHtml(title, title_list, listmap);
	
		request.setAttribute("searchTerms", searchTerms);	//검색조건
		request.setAttribute("title", title);							//title 헤더
		request.setAttribute("hubHtml", hubHtml);					//html
		
		log.debug("### HubStatsController selectHubStatsList END ###");
		
		return "/stat/hub_stats_list";
	}
	
	/**
	 * 허브페이지 유입 통계- 조회
	 * @return	
	 */
	@RequestMapping(value="/stat/hub_stats_list_excel.st", method=RequestMethod.POST)
	public String selectHubStatsListExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### HubStatsController selectHubStatsListExcel START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStMarketStats> title = null;
		MwStMarketStatsService market_service = new MwStMarketStatsService();
		List<MwStHubStats> list = null;
		MwStSearchTerms searchTerms = null;
		MwStHubStatsService service = new MwStHubStatsService();
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
		
		//공통코드 : title 조회
		title = market_service.selectMarketStatsTitle();
		
		//타이틀별로 동적 쿼리 위해 list 담기
		List<String> title_list = new ArrayList<String>();
		for(int i=0; i<title.size(); i++) {
			title_list.add(title.get(i).getCode().toUpperCase());	
		}
		
		params.put("title_list", title_list);
		params.put("title_list2", title_list);
		
		log.debug("### title : " + title);
		
		//타이틀별 목록 조회
		List<Map<String,String>> listmap = null;
		listmap = service.selectHubStatsList(params);
		
		log.debug("### list : " + listmap);

		String hubHtml = hubMakeHtml(title, title_list, listmap);
	
		request.setAttribute("searchTerms", searchTerms);	//검색조건
		request.setAttribute("title", title);							//title 헤더
		request.setAttribute("hubHtml", hubHtml);					//html
		
		log.debug("### HubStatsController selectHubStatsListExcel END ###");
		
		return "/stat/hub_stats_list_excel";
	}

	/**
	 * 마켓이동 - html 생성
	 * @return	
	 */
	public String hubMakeHtml(List<MwStMarketStats> title, List<String> title_list, List<Map<String,String>> listmap) {

		DecimalFormat df = new DecimalFormat("#,##0");
		String[][] valArray = new String[listmap.size()][(title.size())+2];
		
		int cnt = 0;
		Map<String,String> map = null;
		
		for(int j=0; j<listmap.size(); j++) {
			
			map = listmap.get(j);
			
			String cday = "";
			if (map.containsKey("CDAY")) {
				cday = map.get("CDAY");
			}

			valArray[j][cnt++] = cday;
			
			for(int i=0; i<title_list.size(); i++) {
				
				String val6 = "" ;
				String sum = "" ;
				
				if( map.containsKey(title_list.get(i) + "_VAL6") ) {
					val6 = String.valueOf(map.get(title_list.get(i) + "_VAL6"));
				}
				
				if( map.containsKey("SUM") ) {
					sum = String.valueOf(map.get("SUM"));
				}

				if(val6.length() == 0) {
					val6 = "0";
				}
				
				valArray[j][cnt++] = val6;
				valArray[j][valArray[j].length-1] = sum;	//제일 마지막 위치에 값 setting
			}
			cnt = 0;
		}

		String html  = "";
		
		html += "<table class='abody_list'>";
		html += "<colgroup>";
		html += "<col width='6%'/>";
		
		for(int i=0; i<title.size(); i++) {
			html += "<col width='5%'/>";	
		}
		html += "<col width='5%'/>";
		html += "</colgroup>";
		
		html += "<tr class='th'>";
		html += 	"<td>기간</td>"; 
		
		for(int i=0; i<title.size(); i++) {
			html += 	"<td>"+ title.get(i).getCode() + "</td>";
		}
		html += 	"<td>계</td>";
		html += "</tr>";
	
		
		
		
		for(int i=0; i<valArray.length; i++) {
			if("TOTAL".equals(valArray[i][0]) || "UACC".equals(valArray[i][0])) {
				html += "<tr class='th'>";
			} else {
				html += "<tr>";
			}
			for(int j=0; j<valArray[i].length; j++) {
				if("TOTAL".equals(valArray[i][j])) {
					html += "<td>합계</td>";
				} else if("UACC".equals(valArray[i][j])) {
					html += "<td>누적</td>";
				} else {
					
					if(j == 0) {
						//기간 td에 들어가는 항목은 그냥 출력
						html += "<td>"+valArray[i][j]+"</td>";
					} else {
						//기간 td외에 값들만 금액(,) 표시
						html += "<td>"+df.format(Integer.parseInt(valArray[i][j]))+"</td>";
					}
						
				}
			}
			html += "</tr>";
		}

		html += "</table>";
		
		return html;
		
	}
	
}
