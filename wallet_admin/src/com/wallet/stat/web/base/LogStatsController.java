package com.wallet.stat.web.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;
import com.wallet.stat.model.MwStLogStats;
import com.wallet.stat.model.MwStSearchTerms;
import com.wallet.stat.service.MwStLogStatsService;

/*
 * Filename	: PayStatsController.java
 * Class	: com.wallet.stat.web.base.PayStatsController
 * History	: 2012/08/23, psj, �۾����� : ���� ���
 * Comment	:
 */
@Controller
public class LogStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * ���� ��� ȭ�� ȣ��
	 * @return	
	 */
	@RequestMapping(value="/stat/Log_stat_list.st")
	public String selectLogStatsView(HttpServletRequest request, HttpServletResponse response) {
		
		//���� ��¥
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.DATE, -1);
	  String bday = formatter.format(cal.getTime());
	  
	  request.setAttribute("bday", bday);
	  
		return "/stat/Log_stat_list";
		
	}
	
	/**
	 * ���� ��� - ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/stat/Log_stat_list.st", method=RequestMethod.POST)
	public String selectLogStatsList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### LogStatsController selectLogStatsList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStLogStats> list = null;
		MwStSearchTerms searchTerms = null;
		MwStLogStatsService service = new MwStLogStatsService();
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
		
		list = service.selectAppLogStatsList(params);
		
//		if("day".equals(period)) {
//			//�Ϻ�
//			list = service.selectAppLogStatsList(params);
//		} 

		request.setAttribute("searchTerms", searchTerms);		//�˻�����
		request.setAttribute("MwStLogStats", list);			//��/��/��
		System.out.println(list);
		log.debug("### LogStatsController selectLogStatsList END ###");
		
		return "/stat/Log_stat_list";
		
	}
	
}