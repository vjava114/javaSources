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

import com.wallet.stat.model.MwStCpaStats;
import com.wallet.stat.model.MwStSearchTerms;
import com.wallet.stat.service.MwStCpaStatsService;
import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;

/*
 * Filename	: CpaStatsController.java
 * Class	: com.wallet.stat.web.base.CpaStatsController
 * History	: 2012/08/23, psj, 작업구분 : CPA 가입통계
 * Comment	:
 */
@Controller
public class CpaStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * CPA 가입통계 - 화면 호출
	 * @return	
	 */
	@RequestMapping(value="/stat/cpa_stats_list.st")
	public String selectCpaStatsView(HttpServletRequest request, HttpServletResponse response) {
		
		//전일 날짜
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.DATE, -1);
	  String bday = formatter.format(cal.getTime());
	  
	  request.setAttribute("bday", bday);
	  
		return "/stat/cpa_stats_list";
	}
	
	/**
	 * CPA 가입통계 - 조회
	 * @return	
	 */
	@RequestMapping(value="/stat/cpa_stats_list.st", method=RequestMethod.POST)
	public String selectCpaStatsList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### CpaStatsController selectCpaStatsList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStCpaStats> title = null;
		MwStSearchTerms searchTerms = null;
		MwStCpaStatsService service = new MwStCpaStatsService();
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
		title = service.selectCpaStatsTitle();

		//타이틀별로 동적 쿼리 위해 list 담기
		List<String> title_list = new ArrayList<String>();
		for(int i=0; i<title.size(); i++) {
			title_list.add(title.get(i).getCode().toUpperCase());	
		}
		
		params.put("title_list", title_list);
		
		//타이틀별 목록 조회
		List<Map<String,String>> listmap = null;
		listmap = service.selectCpaStatsList(params);
		
		//cpa 리스트 html 생성
		String cpaHtml = cpaMakeHtml(title, title_list, listmap);
		
		request.setAttribute("searchTerms", searchTerms);		//검색조건
		request.setAttribute("title", title);				//title 헤더
		request.setAttribute("cpaHtml", cpaHtml);		//html

		log.debug("### PayStatsController selectCpaStatsList END ###");
		
		return "/stat/cpa_stats_list";
	}
	
	/**
	 * CPA 가입통계 - 엑셀
	 * @return	
	 */
	@RequestMapping(value="/stat/cpa_stats_list_excel.st", method=RequestMethod.POST)
	public String selectCpaStatsListExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### CpaStatsController selectCpaStatsListExcel START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwStCpaStats> title = null;
		MwStSearchTerms searchTerms = null;
		MwStCpaStatsService service = new MwStCpaStatsService();
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
		title = service.selectCpaStatsTitle();

		//타이틀별로 동적 쿼리 위해 list 담기
		List<String> title_list = new ArrayList<String>();
		for(int i=0; i<title.size(); i++) {
			title_list.add(title.get(i).getCode().toUpperCase());	
		}
		
		params.put("title_list", title_list);
		
		//타이틀별 목록 조회
		List<Map<String,String>> listmap = null;
		listmap = service.selectCpaStatsList(params);
		
		//cpa 리스트 html 생성
		String cpaHtml = cpaMakeHtml(title, title_list, listmap);
		
		request.setAttribute("searchTerms", searchTerms);		//검색조건
		request.setAttribute("title", title);				//title 헤더
		request.setAttribute("cpaHtml", cpaHtml);		//html

		log.debug("### PayStatsController selectCpaStatsListExcel END ###");
		
		return "/stat/cpa_stats_list_excel";
	}

	/**
	 * CPA 가입통계 - html 생성
	 * @return	
	 */
	public String cpaMakeHtml(List<MwStCpaStats> title, List<String> title_list, List<Map<String,String>> listmap) {

		DecimalFormat df = new DecimalFormat("#,##0");
		
		String[][] valArray = new String[listmap.size()][(title.size()*3)+4];
	
		int cnt = 0;
		Map<String,String> map = null;
		
		title_list.add("SUM");// 마지막에 섬을 표시하기위함.
		
		for(int j=0; j<listmap.size(); j++) {
			
			map = listmap.get(j);
			
			String cday = "";
			if (map.containsKey("CDAY")) {
				cday = map.get("CDAY");
			}

			valArray[j][cnt++] = cday;
			
			for(int i=0; i<title_list.size(); i++) {
				
				String val1 = "" ;
				String val2 = "";
				String valFail  = "";
				
				if( map.containsKey(title_list.get(i) + "_VAL1") ) {
					val1 = String.valueOf(map.get(title_list.get(i) + "_VAL1"));
				}
				if( map.containsKey(title_list.get(i) + "_VAL2") ) {
					val2 = String.valueOf(map.get(title_list.get(i) + "_VAL2"));
				}
				if( map.containsKey(title_list.get(i) + "_VAL3") ) {
					valFail = String.valueOf(map.get(title_list.get(i) + "_VAL3"));
				}

				if(val1.length() == 0) {
					val1 = "0";
				}
				if(val2.length() == 0) {
					val2 = "0";
				} 
				if(valFail.length() == 0) {
					valFail = "0";
				} 
				valArray[j][cnt++] = val1;
				valArray[j][cnt++] = val2;
				valArray[j][cnt++] = valFail;

			}
			cnt = 0;
		}

		String html  = "";
		
		html += "<table class='abody_list'>";
		html += "<colgroup>";
		html += "<col width='10%'/>";
		
		for(int i=0; i<title.size(); i++) {
			html += "<col width='5%'/>";	
		}
		
		html += "<col width='5%'/>";
		html += "<col width='5%'/>";
		html += "<col width='5%'/>";
		html += "</colgroup>";
		
		html += "<tr class='th'>";
		html += 	"<td rowspan='2'>기간</td>"; 
		
		for(int i=0; i<title.size(); i++) {
			html += 	"<td colspan='3'>"+ title.get(i).getCode() + "</td>";	
		}
		
		html += 	"<td colspan='3'>합 계</td>";
		html += "</tr>";
	
		html += "<tr class='th'>";
		
		for(int i=0; i<title.size(); i++) {
			html += 	"<td>조회건수</td>";
			html += 	"<td>성공</td>";
			html += 	"<td>실패</td>";	
		}
		
		html += 	"<td>조회건수 합</td>";
		html += 	"<td>성공건수 합</td>";
		html += 	"<td>실패건수 합</td>";
		html += "</tr>";
		
		
		for(int i=0; i<valArray.length; i++) {
			if("TOTAL".equals(valArray[i][0]) || "UACC".equals(valArray[i][0])) {
				html += "<tr class='th'>";
			} else {
				html += "<tr>";
			}
			for(int j=0; j<valArray[i].length; j++) {
				if("TOTAL".equals(valArray[i][j])) {
					html += 	"<td>합계</td>";
				} else if("UACC".equals(valArray[i][j])) {
					html += 	"<td>누적</td>";
				} else {
					
					if(j == 0) {
						//기간 td에 들어가는 항목은 그냥 출력
						html += 	"<td>"+valArray[i][j]+"</td>";
					} else {
						//기간 td외에 값들만 금액(,) 표시
						html += 	"<td>"+df.format(Integer.parseInt(valArray[i][j]))+"</td>";
					}
					
				}
			}
			html += "</tr>";
		}

		html += "</table>";
		
		return html;
		
	}
}
