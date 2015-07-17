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
 * History	: 2012/08/23, psj, �۾����� : ��������� ���� ���
 * Comment	:
 */
@Controller
public class HubStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * ��������� ���� ��� ȭ�� ȣ��
	 * @return	
	 */
	@RequestMapping(value="/stat/hub_stats_list.st")
	public String selectHubStatsView(HttpServletRequest request, HttpServletResponse response) {
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
		
		return "/stat/hub_stats_list";
	}
	
	/**
	 * ��������� ���� ���- ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/stat/hub_stats_list.st", method=RequestMethod.POST)
	public String selectHubStatsList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### HubStatsController selectHubStatsList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStMarketStats> title = null;
		MwStMarketStatsService market_service = new MwStMarketStatsService();
		List<MwStHubStats> list = null;
		MwStSearchTerms searchTerms = null;
		MwStHubStatsService service = new MwStHubStatsService();
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
		title = market_service.selectMarketStatsTitle();
		
		//Ÿ��Ʋ���� ���� ���� ���� list ���
		List<String> title_list = new ArrayList<String>();
		for(int i=0; i<title.size(); i++) {
			title_list.add(title.get(i).getCode().toUpperCase());	
		}
		
		params.put("title_list", title_list);
		params.put("title_list2", title_list);
		
		log.debug("### title : " + title);
		
		//Ÿ��Ʋ�� ��� ��ȸ
		List<Map<String,String>> listmap = null;
		listmap = service.selectHubStatsList(params);
		
		log.debug("### list : " + listmap);

		String hubHtml = hubMakeHtml(title, title_list, listmap);
	
		request.setAttribute("searchTerms", searchTerms);	//�˻�����
		request.setAttribute("title", title);							//title ���
		request.setAttribute("hubHtml", hubHtml);					//html
		
		log.debug("### HubStatsController selectHubStatsList END ###");
		
		return "/stat/hub_stats_list";
	}
	
	/**
	 * ��������� ���� ���- ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/stat/hub_stats_list_excel.st", method=RequestMethod.POST)
	public String selectHubStatsListExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### HubStatsController selectHubStatsListExcel START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStMarketStats> title = null;
		MwStMarketStatsService market_service = new MwStMarketStatsService();
		List<MwStHubStats> list = null;
		MwStSearchTerms searchTerms = null;
		MwStHubStatsService service = new MwStHubStatsService();
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
		title = market_service.selectMarketStatsTitle();
		
		//Ÿ��Ʋ���� ���� ���� ���� list ���
		List<String> title_list = new ArrayList<String>();
		for(int i=0; i<title.size(); i++) {
			title_list.add(title.get(i).getCode().toUpperCase());	
		}
		
		params.put("title_list", title_list);
		params.put("title_list2", title_list);
		
		log.debug("### title : " + title);
		
		//Ÿ��Ʋ�� ��� ��ȸ
		List<Map<String,String>> listmap = null;
		listmap = service.selectHubStatsList(params);
		
		log.debug("### list : " + listmap);

		String hubHtml = hubMakeHtml(title, title_list, listmap);
	
		request.setAttribute("searchTerms", searchTerms);	//�˻�����
		request.setAttribute("title", title);							//title ���
		request.setAttribute("hubHtml", hubHtml);					//html
		
		log.debug("### HubStatsController selectHubStatsListExcel END ###");
		
		return "/stat/hub_stats_list_excel";
	}

	/**
	 * �����̵� - html ����
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
				valArray[j][valArray[j].length-1] = sum;	//���� ������ ��ġ�� �� setting
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
		html += 	"<td>�Ⱓ</td>"; 
		
		for(int i=0; i<title.size(); i++) {
			html += 	"<td>"+ title.get(i).getCode() + "</td>";
		}
		html += 	"<td>��</td>";
		html += "</tr>";
	
		
		
		
		for(int i=0; i<valArray.length; i++) {
			if("TOTAL".equals(valArray[i][0]) || "UACC".equals(valArray[i][0])) {
				html += "<tr class='th'>";
			} else {
				html += "<tr>";
			}
			for(int j=0; j<valArray[i].length; j++) {
				if("TOTAL".equals(valArray[i][j])) {
					html += "<td>�հ�</td>";
				} else if("UACC".equals(valArray[i][j])) {
					html += "<td>����</td>";
				} else {
					
					if(j == 0) {
						//�Ⱓ td�� ���� �׸��� �׳� ���
						html += "<td>"+valArray[i][j]+"</td>";
					} else {
						//�Ⱓ td�ܿ� ���鸸 �ݾ�(,) ǥ��
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
