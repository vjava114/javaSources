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
 * History	: 2012/08/23, psj, �۾����� : �̿��� ���
 * Comment	:
 */
@Controller
public class UserStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * �̿��� ��� ȭ�� ȣ��
	 * @return	
	 */
	@RequestMapping(value="/stat/user_stats_list.st")
	public String selectUserStatsView(HttpServletRequest request, HttpServletResponse response) {

		//���� ��¥
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.DATE, -1);
	  String bday = formatter.format(cal.getTime());
	  
	  request.setAttribute("bday", bday);
	  
		return "/stat/user_stats_list";
	}
	
	/**
	 * �̿��� ��� - ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/stat/user_stats_list.st", method=RequestMethod.POST)
	public String selectUserStatsList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserStatsController selectUserStatsList STAT ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStUserStats> list = null;
		MwStSearchTerms searchTerms = null;
		MwStUserStatsService service = new MwStUserStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//�˻����� : ������
		String eday = checkStr(request.getParameter("eday"), "");			//�˻����� : ������
		String period = checkStr(request.getParameter("period"), "");	//�˻����� : �Ⱓ (�Ϻ�,����,������)

		//�˻����� setting
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
	 * ��������ü��� - ��ȸ
	 * @return	
	 */
	
	@RequestMapping(value="/stat/user_stats_list_all.st")
	public String selectUserStatsListAll(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserStatsController selectUserStatsListAll START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStUserStats> list = null;
		MwStUserStatsService service = new MwStUserStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		list = service.selectUserStatsListAll(params);
		
		request.setAttribute("MwAdUserStatsList", list);
		
		log.debug("### UserStatsController selectUserStatsListAll END ###");
		
		return "/stat/user_stats_list_all";
	}
	
	
	/**
	 * �̿��� ��� - ����
	 * @return	
	 */
	@RequestMapping(value="/stat/user_stats_list_excel.st", method=RequestMethod.POST)
	public String selectUserStatsListExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserStatsController selectUserStatsListExcel STAT ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStUserStats> list = null;
		MwStSearchTerms searchTerms = null;
		MwStUserStatsService service = new MwStUserStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//�˻����� : ������
		String eday = checkStr(request.getParameter("eday"), "");			//�˻����� : ������
		String period = checkStr(request.getParameter("period"), "");	//�˻����� : �Ⱓ (�Ϻ�,����,������)

		//�˻����� setting
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
	 * ��������ü��� - ����
	 * @return	
	 */
	
	@RequestMapping(value="/stat/user_stats_list_all_excel.st")
	public String selectUserStatsListAllExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### UserStatsController selectUserStatsListAllExcel START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStUserStats> list = null;
		MwStUserStatsService service = new MwStUserStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		list = service.selectUserStatsListAll(params);
		
		request.setAttribute("MwAdUserStatsList", list);
		
		log.debug("### UserStatsController selectUserStatsListAllExcel END ###");
		
		return "/stat/user_stats_list_all_excel";
	}
}
