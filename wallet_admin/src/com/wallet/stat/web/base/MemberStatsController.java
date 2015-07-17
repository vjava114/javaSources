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
 * History	: 2012/08/23, psj, �۾����� : ������ ���
 * Comment	:
 */
@Controller
public class MemberStatsController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * ������������Ȳ (�Ǳ���:������������Ȳ:acm)
	 * @return	
	 */
	@RequestMapping(value="/stat/member_stats_acm_list.st")
	public String selectMemberStatsAcmList(HttpServletRequest request, HttpServletResponse response) {

		log.debug("### MemberStatsController selectMemberStatsAcmList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwStMemberStatsOs statsOs = null;
		MwStMemberStatsAge statsAge = null;
		MwStMemberStatsMocaPay mocaPay = null;
		MwStMemberStatsService service = new MwStMemberStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		//���ó�¥
		Date today = Calendar.getInstance().getTime();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String reg_day = formatter.format(today);
	  
	  String[] day = reg_day.split("-");
	  String yearMonthDay = day[0] + "�� " + day[1] + "�� " + day[2] + "��";
	  
		//OS/����纰 ���
		statsOs = service.selectMemberStatsAcmOs(params);
		//����/���� ���
		statsAge = service.selectMemberStatsAcmAge(params);
		//��ī���� ���
		mocaPay = service.selectMemberStatsAcmMocaPay(params);

		request.setAttribute("yearMonthDay", yearMonthDay);
		request.setAttribute("statsOs", statsOs);
		request.setAttribute("statsAge", statsAge);
		request.setAttribute("mocaPay", mocaPay);

		log.debug("### MemberStatsController selectMemberStatsAcmList END ###");
		
		return "/stat/member_stats_acm_list";
	}

	/**
	 * �ű԰�������Ȳ  ȭ�� ȣ��
	 */
	@RequestMapping(value="/stat/member_stats_new_list.st")
	public String selectMemberStatsNewView(HttpServletRequest request, HttpServletResponse response) {
		
		//���� ��¥
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.DATE, -1);
	  String bday = formatter.format(cal.getTime());
	  
	  request.setAttribute("bday", bday);
	  
		return "/stat/member_stats_new_list";
	}
	
	/**
	 * �ű԰�������Ȳ   - ��ȸ (�Ǳ���:�ű԰�������Ȳ:new)
	 * @param kind : os(�ܸ�����(OS, �����)) , age(�̿��ڱ���(����, ����)), moca(��ī���� ����)
	 * @param day(�Ϻ�), month(����), year(�⵵��)
	 * @return	
	 */
	@RequestMapping(value="/stat/member_stats_new_list.st", method=RequestMethod.POST)
	public String selectMemberStatsNewList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### MemberStatsController selectMemberStatsNewList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		
		List<MwStMemberStatsOs> os_list = null;
		List<MwStMemberStatsAge> age_list = null;
		List<MwStMemberStatsMocaPay> mocaPay = null;
		MwStSearchTerms searchTerms = null;
		MwStMemberStatsService service = new MwStMemberStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//�˻����� : ������
		String eday = checkStr(request.getParameter("eday"), "");			//�˻����� : ������
		String period = checkStr(request.getParameter("period"), "");	//�˻����� : �Ⱓ (�Ϻ�,����,������)
		String kind = checkStr(request.getParameter("kind"), "");			//�˻����� : �ܸ�����, �̿��ڱ���,��ī���� ��
		
		//�˻����� setting
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
	 * �ű԰�������Ȳ   - �������� (�Ǳ���:�ű԰�������Ȳ:new)
	 * @param kind : os(�ܸ�����(OS, �����)) , age(�̿��ڱ���(����, ����)), moca(��ī���� ����)
	 * @param day(�Ϻ�), month(����), year(�⵵��)
	 * @return	
	 */
	@RequestMapping(value="/stat/member_stats_new_list_excel.st", method=RequestMethod.POST)
	public String selectMemberStatsNewListExcel(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### MemberStatsController selectMemberStatsNewListExcel START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		
		List<MwStMemberStatsOs> os_list = null;
		List<MwStMemberStatsAge> age_list = null;
		List<MwStMemberStatsMocaPay> mocaPay = null;
		MwStSearchTerms searchTerms = null;
		MwStMemberStatsService service = new MwStMemberStatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String sday = checkStr(request.getParameter("sday"), "");			//�˻����� : ������
		String eday = checkStr(request.getParameter("eday"), "");			//�˻����� : ������
		String period = checkStr(request.getParameter("period"), "");	//�˻����� : �Ⱓ (�Ϻ�,����,������)
		String kind = checkStr(request.getParameter("kind"), "");			//�˻����� : �ܸ�����, �̿��ڱ���,��ī���� ��
		
		//�˻����� setting
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
