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
 * History	: 2012/08/23, psj, �۾����� : ���� ���
 * Comment	:
 */
@Controller
public class PayStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * ���� ��� ȭ�� ȣ��
	 * @return	
	 */
	@RequestMapping(value="/stat/pay_stats_list.st")
	public String selectPayStatsView(HttpServletRequest request, HttpServletResponse response) {
		
		//���� ��¥
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.DATE, -1);
	  String bday = formatter.format(cal.getTime());
	  
	  request.setAttribute("bday", bday);
	  
		return "/stat/pay_stats_list";
		
	}
	
	/**
	 * ���� ��� - ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/stat/pay_stats_list.st", method=RequestMethod.POST)
	public String selectPayStatsList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PayStatsController selectPayStatsList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStPayStats> list = null;
		MwStPayStats mwAdPayStats = null;
		MwStSearchTerms searchTerms = null;
		MwStPayStatsService service = new MwStPayStatsService();
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
		
		//list = service.selectPayStatsList(params);
		
		if("day".equals(period)) {
			//�Ϻ�
			list = service.selectPayStatsDayList(params);
		} else if( ("month".equals(period)) || ("year".equals(period)) ) {
			//����, �⵵��
			list = service.selectPayStatsMonthYearList(params);
		} else  {
			//����
			mwAdPayStats = service.selectPayStatsAcc(params);
		}

		request.setAttribute("searchTerms", searchTerms);		//�˻�����
		request.setAttribute("MwAdPayStatsList", list);			//��/��/��
		request.setAttribute("mwAdPayStats", mwAdPayStats);	//����
		
		log.debug("### PayStatsController selectPayStatsList END ###");
		
		return "/stat/pay_stats_list";
		
	}
	
	/**
	 * ���� ��� - ����
	 * @return	
	 */
	@RequestMapping(value="/stat/pay_stats_list_excel.st", method=RequestMethod.POST)
	public String selectPayStatsListExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### PayStatsController selectPayStatsListExcel START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStPayStats> list = null;
		MwStPayStats mwAdPayStats = null;
		MwStSearchTerms searchTerms = null;
		MwStPayStatsService service = new MwStPayStatsService();
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
		
		//list = service.selectPayStatsList(params);
		
		if("day".equals(period)) {
			//�Ϻ�
			list = service.selectPayStatsDayList(params);
		} else if( ("month".equals(period)) || ("year".equals(period)) ) {
			//����, �⵵��
			list = service.selectPayStatsMonthYearList(params);
		} else  {
			//����
			mwAdPayStats = service.selectPayStatsAcc(params);
		}

		request.setAttribute("searchTerms", searchTerms);		//�˻�����
		request.setAttribute("MwAdPayStatsList", list);			//��/��/��
		request.setAttribute("mwAdPayStats", mwAdPayStats);	//����
		
		log.debug("### PayStatsController selectPayStatsListExcel END ###");
		
		return "/stat/pay_stats_list_excel";
		
	}

	
}
