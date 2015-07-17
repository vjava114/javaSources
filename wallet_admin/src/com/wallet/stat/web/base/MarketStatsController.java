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

import com.wallet.stat.model.MwStMarketStats;
import com.wallet.stat.model.MwStSearchTerms;
import com.wallet.stat.service.MwStMarketStatsService;
import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;

/*
 * Filename	: MarketStatsController.java
 * Class	: com.wallet.stat.web.base.MarketStatsController
 * History	: 2012/08/23, psj, �۾����� : �����̵� ��ġ
 * Comment	:
 */
@Controller
public class MarketStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * �����̵� ��ġ - ȭ�� ȣ��
	 * @return	
	 */
	@RequestMapping(value="/stat/market_stats_list.st")
	public String selectMarketStatsView(HttpServletRequest request, HttpServletResponse response) {
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStMarketStats> title = null;
		MwStMarketStatsService service = new MwStMarketStatsService();
		
		//���� ��¥
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.DATE, -1);
	  String bday = formatter.format(cal.getTime());
	  
		//�����ڵ� : title ��ȸ
		title = service.selectMarketStatsTitle();
	
		request.setAttribute("bday", bday);
		request.setAttribute("title", title);				//title ���

		return "/stat/market_stats_list";
	}
	
	/**
	 * �����̵� ��ġ - ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/stat/market_stats_list.st", method=RequestMethod.POST)
	public String selectMarketStatsList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### MarketStatsController selectMarketStatsList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStMarketStats> title = null;
		MwStSearchTerms searchTerms = null;
		MwStMarketStatsService service = new MwStMarketStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//�˻����� : ������
		String eday = checkStr(request.getParameter("eday"), "");			//�˻����� : ������
		String period = checkStr(request.getParameter("period"), "");	//�˻����� : �Ⱓ (�Ϻ�,����,������,����)
		
		//�˻����� setting
		searchTerms = new MwStSearchTerms();
		searchTerms.setSday(sday);
		searchTerms.setEday(eday);
		searchTerms.setPeriod(period);
	
		params.put("sday", sday);
		params.put("eday", eday);
		params.put("period", period);
		
		//�����ڵ� : title ��ȸ
		title = service.selectMarketStatsTitle();
		
		//Ÿ��Ʋ���� ���� ���� ���� list ���
		List<String> title_list = new ArrayList<String>();
		for(int i=0; i<title.size(); i++) {
			title_list.add(title.get(i).getCode().toUpperCase());	
		}
		
		params.put("title_list", title_list);
		
		log.debug("### title : " + title);
		
		//Ÿ��Ʋ�� ��� ��ȸ
		List<Map<String,String>> listmap = null;
		listmap =  service.selectMarketStatsList(params);
		
		log.debug("### list : " + listmap);
		
		//market ����Ʈ html ����
		String marketHtml = maekrtMakeHtml(title, title_list, listmap);
		
		
		request.setAttribute("searchTerms", searchTerms);		//�˻�����
		request.setAttribute("title", title);				//title ���
		request.setAttribute("marketHtml", marketHtml);		//html
		
		log.debug("### MarketStatsController selectMarketStatsList END ###");
		
		return "/stat/market_stats_list";
	}
	
	/**
	 * �����̵� ��ġ - ����
	 * @return	
	 */
	@RequestMapping(value="/stat/market_stats_list_excel.st", method=RequestMethod.POST)
	public String selectMarketStatsListExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### MarketStatsController selectMarketStatsListExcel START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStMarketStats> title = null;
		MwStSearchTerms searchTerms = null;
		MwStMarketStatsService service = new MwStMarketStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//�˻����� : ������
		String eday = checkStr(request.getParameter("eday"), "");			//�˻����� : ������
		String period = checkStr(request.getParameter("period"), "");	//�˻����� : �Ⱓ (�Ϻ�,����,������,����)
		
		//�˻����� setting
		searchTerms = new MwStSearchTerms();
		searchTerms.setSday(sday);
		searchTerms.setEday(eday);
		searchTerms.setPeriod(period);
	
		params.put("sday", sday);
		params.put("eday", eday);
		params.put("period", period);
		
		//�����ڵ� : title ��ȸ
		title = service.selectMarketStatsTitle();
		
		//Ÿ��Ʋ���� ���� ���� ���� list ���
		List<String> title_list = new ArrayList<String>();
		for(int i=0; i<title.size(); i++) {
			title_list.add(title.get(i).getCode().toUpperCase());	
		}
		
		params.put("title_list", title_list);
		
		log.debug("### title : " + title);
		
		//Ÿ��Ʋ�� ��� ��ȸ
		List<Map<String,String>> listmap = null;
		listmap =  service.selectMarketStatsList(params);
		
		log.debug("### list : " + listmap);
		
		//market ����Ʈ html ����
		String marketHtml = maekrtMakeHtml(title, title_list, listmap);
		
		
		request.setAttribute("searchTerms", searchTerms);		//�˻�����
		request.setAttribute("title", title);				//title ���
		request.setAttribute("marketHtml", marketHtml);		//html
		
		log.debug("### MarketStatsController selectMarketStatsListExcel END ###");
		
		return "/stat/market_stats_list_excel";
	}

	/**
	 * �����̵� - html ����
	 * @return	
	 */
	public String maekrtMakeHtml(List<MwStMarketStats> title, List<String> title_list, List<Map<String,String>> listmap) {

		DecimalFormat df = new DecimalFormat("#,##0");
		String[][] valArray = new String[listmap.size()][(title.size()*3)+1];
		
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
				
				String val1 = "" ;
				String val2 = "";
				String valFail  = "";
				
				if( map.containsKey(title_list.get(i) + "_VAL1") ) {
					val1 = String.valueOf(map.get(title_list.get(i) + "_VAL1"));
				}
				if( map.containsKey(title_list.get(i) + "_VAL2") ) {
					val2 = String.valueOf(map.get(title_list.get(i) + "_VAL2"));
				}
				
				
				if("CLUB_IOS".equals(title_list.get(i))) {
					valFail = "NOT_VIEW";
				} else {
					if( map.containsKey(title_list.get(i) + "_VAL3") ) {
						valFail = String.valueOf(map.get(title_list.get(i) + "_VAL3"));
					}
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
		html += "<col width='5%'/>";
		
		for(int i=0; i<title.size(); i++) {
			//���߿����� 4% ���� ����� 10.5%
			html += "<col width='10.5%'/>";	
		}
		html += "</colgroup>";
		
		html += "<tr class='th'>";
		html += 	"<td rowspan='2'>�Ⱓ</td>"; 
		
		for(int i=0; i<title.size(); i++) {
			if("CLUB_iOS".equals(title.get(i).getCode())) {
				html += 	"<td colspan='2'>"+ title.get(i).getCode() + "</td>";
			} else {
				html += 	"<td colspan='3'>"+ title.get(i).getCode() + "</td>";
			}
				
		}
		html += "</tr>";
	
		html += "<tr class='th'>";
		
		for(int i=0; i<title.size(); i++) {
			if("CLUB_iOS".equals(title.get(i).getCode())) {
				html += 	"<td>�ٿ�ε�</td>";
				html += 	"<td>����</td>";
			} else {
				html += 	"<td>�÷�����</td>";
				html += 	"<td>������</td>";
				html += 	"<td>�����÷���</td>";
			}
		}

		html += "</tr>";
		
		
		for(int i=0; i<valArray.length; i++) {
			if("TOTAL".equals(valArray[i][0]) || "UACC".equals(valArray[i][0])) {
				html += "<tr class='th'>";
			} else {
				html += "<tr>";
			}
			for(int j=0; j<valArray[i].length; j++) {
				if("TOTAL".equals(valArray[i][j])) {
					html += 	"<td>�հ�</td>";
				} else if("UACC".equals(valArray[i][j])) {
					html += 	"<td>����</td>";
				} else {

					if(!"NOT_VIEW".equals(valArray[i][j])) {

						if(j == 0) {
							//�Ⱓ td�� ���� �׸��� �׳� ���
							html += 	"<td>"+valArray[i][j]+"</td>";
						} else {
							//�Ⱓ td�ܿ� ���鸸 �ݾ�(,) ǥ��
							html += 	"<td>"+df.format(Integer.parseInt(valArray[i][j]))+"</td>";
						}
						
					}
				}
			}
			html += "</tr>";
		}

		html += "</table>";
		
		return html;
		
	}
	
	
}
