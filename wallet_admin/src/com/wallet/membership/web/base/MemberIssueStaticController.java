package com.wallet.membership.web.base;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.DateTime;
import com.wallet.membership.model.MembCardlist;
import com.wallet.membership.model.MembCardlistExample;
import com.wallet.membership.model.custom.StaticMember;
import com.wallet.membership.model.custom.Member;
import com.wallet.membership.model.custom.MemberConnectStatic;
import com.wallet.membership.model.custom.MemberIssueStatic;
import com.wallet.membership.model.custom.MemberJoinStatic;
import com.wallet.membership.service.MembCardlistService;
import com.wallet.membership.service.custom.MemberConnectStaticService;
import com.wallet.membership.service.custom.MemberIssueStaticService;
import com.wallet.membership.service.custom.MemberJoinStaticService;
import com.wallet.membership.service.custom.StaticMemberService;
import com.wallet.membership.service.custom.MemberService;

@Controller
public class MemberIssueStaticController extends CommonController{
	private Logger log = Log.getLogger("logs");
	
	/**
	 * @Method Name : MemberIssueStaticDayList
	 * @Description : �߱����(�Ϻ�)�� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	//@RequestMapping(value="/member/member_issue_static_day_list.ms")
	@RequestMapping(value="/member/member_issue_static_day_list.st")
	public String MemberIssueStaticDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MemberIssueStatic> memberIssueStaticDayList = null;
		MemberIssueStaticService memberIssueStaticService = new MemberIssueStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
		log.debug("@@@@@@@@@@ MemberIssueStaticDayList params : "+ params); //##
		
 		memberIssueStaticDayList = memberIssueStaticService.selectMemberIssueStaticDayList(params); //-- �����ȸ
				
		
		request.setAttribute("memberIssueStaticDayList", memberIssueStaticDayList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("statDay", params.get("statDay"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/member_issue_static_day_list";
	}
	
	/**
	 * @Method Name : MemberIssueStaticDayExcel
	 * @Description : �߱������ȸ(�Ϻ�)����� EXCEL ���Ϸ� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	//@RequestMapping(value="/member/member_issue_static_day_list_excel.ms")
	@RequestMapping(value="/member/member_issue_static_day_list_excel.st")
	public String MemberIssueStaticDayExcel(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		List<MemberIssueStatic> memberIssueStaticDayList = null;
		MemberIssueStaticService memberIssueStaticService = new MemberIssueStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
		log.debug("@@@@@@@@@@ MemberIssueStaticDayList params : "+ params); //##
		
 		memberIssueStaticDayList = memberIssueStaticService.selectMemberIssueStaticDayList(params); //-- �����ȸ
		
		request.setAttribute("memberIssueStaticDayList", memberIssueStaticDayList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("statDay", params.get("statDay"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/member_issue_static_day_list_excel";
	}
	
	/**
	 * @Method Name : MemberIssueStaticMonthList
	 * @Description : �߱����(����)�� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	//@RequestMapping(value="/member/member_issue_static_month_list.ms")
	@RequestMapping(value="/member/member_issue_static_month_list.st")
	public String MemberIssueStaticMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<HashMap<String, Object>> memberIssueStaticMonthList = null;
		MemberIssueStaticService memberIssueStaticService = new MemberIssueStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statMonth", checkStr(request.getParameter("statMonth"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 
		
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
		
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}
		
		//��ȸ edate�� ���� ��������¥ ���ϱ�
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
				
		calendar.set(year, month, date);
				
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();
		
		params.put("sdate", sdate);
		params.put("edate", edate);
		
		String STAT_MONTH = checkStr(request.getParameter("statMonth"), sdate.substring(0,6));
				
		for(int i=0; i<=differMonths; i++){
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+"+i+",CONVERT(datetime,'"+STAT_MONTH+"'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS USER_CNT" + (i+1));
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+"+i+",CONVERT(datetime,'"+STAT_MONTH+"'+'01')),120),'-',''),1,6) THEN ISNULL(PRE_MONTH_RATIO,0) ELSE 0 END) AS PRE_MONTH_RATIO"+(i+1));
		}
		params.put("subQuery", subQuery.toString());
		
		log.debug("@@@@@@@@@@ MemberIssueStaticMonthList params : "+ params); //##
		
 		memberIssueStaticMonthList = memberIssueStaticService.selectMemberIssueStaticMonthList(params); //-- �����ȸ
		
 		String[] monthList = new String[differMonths];
 		monthList = getMonthList(sdate, differMonths);
 		
 		/* SET ATTRIBUTEs */
 		request.setAttribute("memberIssueStaticMonthList", memberIssueStaticMonthList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("monthList", monthList);
		request.setAttribute("statMonth", params.get("statMonth"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-01");
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +endDay);
		request.setAttribute("name", params.get("name"));
		request.setAttribute("diffMonths", differMonths);
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/member_issue_static_month_list";
	}
	
	
	/**
	 * @Method Name : MemberIssueStaticMonthExcel
	 * @Description : �߱������ȸ(�Ϻ�)����� EXCEL ���Ϸ� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	//@RequestMapping(value="/member/member_issue_static_month_list_excel.ms")
	@RequestMapping(value="/member/member_issue_static_month_list_excel.st")
	public String MemberIssueStaticMonthExcel(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<HashMap<String, Object>> memberIssueStaticMonthList = null;
		MemberIssueStaticService memberIssueStaticService = new MemberIssueStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 
		
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
		
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}		
		
		//��ȸ edate�� ���� ��������¥ ���ϱ�
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
				
		calendar.set(year, month, date);
				
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();
		
		params.put("sdate", sdate);
		params.put("edate", edate);
		
		String STAT_MONTH = checkStr(request.getParameter("statMonth"), sdate.substring(0,6));
				
		for(int i=0; i<=differMonths; i++){
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+"+i+",CONVERT(datetime,'"+STAT_MONTH+"'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS USER_CNT" + (i+1));
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+"+i+",CONVERT(datetime,'"+STAT_MONTH+"'+'01')),120),'-',''),1,6) THEN ISNULL(PRE_MONTH_RATIO,0) ELSE 0 END) AS PRE_MONTH_RATIO"+(i+1));
		}
		params.put("subQuery", subQuery.toString());
		
		log.debug("@@@@@@@@@@ MemberIssueStaticMonthExcel params : "+ params); //##
		
 		memberIssueStaticMonthList = memberIssueStaticService.selectMemberIssueStaticMonthList(params); //-- �����ȸ
		
		
 		String[] monthList = new String[differMonths];
 		monthList = getMonthList(sdate, differMonths);
 		
 		/* SET ATTRIBUTEs */
 		
 		request.setAttribute("memberIssueStaticMonthList", memberIssueStaticMonthList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("monthList", monthList);
		request.setAttribute("statMonth", params.get("statMonth"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-01");
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +endDay);
		request.setAttribute("name", params.get("name"));
		request.setAttribute("diffMonths", differMonths);
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/member_issue_static_month_list_excel";
	}
	
	/**
	 * @Method Name : MemberIssueCancelStaticDayList
	 * @Description : �߱�������(�Ϻ�)�� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	//@RequestMapping(value="/member/member_issue_cancel_static_day_list.ms")
	@RequestMapping(value="/member/member_issue_cancel_static_day_list.st")
	public String MemberIssueCancelStaticDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MemberIssueStatic> memberIssueCancelStaticDayList = null;
		MemberIssueStaticService memberIssueStaticService = new MemberIssueStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
		log.debug("@@@@@@@@@@ MemberIssueStaticDayList params : "+ params); //##
		
 		memberIssueCancelStaticDayList = memberIssueStaticService.selectMemberIssueCancelStaticDayList(params); //-- �����ȸ
		
		/* SET ATTRIBUTEs */
		request.setAttribute("memberIssueCancelStaticDayList", memberIssueCancelStaticDayList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("statDay", params.get("statDay"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/member_issue_cancel_static_day_list";
	}
	
	/**
	 * @Method Name : MemberIssueCancelStaticDayExcel
	 * @Description : �߱���������ȸ(�Ϻ�)����� EXCEL ���Ϸ� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	//@RequestMapping(value="/member/member_issue_cancel_static_day_list_excel.ms")
	@RequestMapping(value="/member/member_issue_cancel_static_day_list_excel.st")
	public String MemberIssueCancelStaticDayExcel(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		List<MemberIssueStatic> memberIssueCancelStaticDayList = null;
		MemberIssueStaticService memberIssueStaticService = new MemberIssueStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
		log.debug("@@@@@@@@@@ MemberIssueStaticDayList params : "+ params); //##
		
 		memberIssueCancelStaticDayList = memberIssueStaticService.selectMemberIssueCancelStaticDayList(params); //-- �����ȸ
		
		request.setAttribute("memberIssueCancelStaticDayList", memberIssueCancelStaticDayList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("statDay", params.get("statDay"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/member_issue_cancel_static_day_list_excel";
	}

	
	


	/**
	 * @Method Name : StaticMemberList
	 * @Description : ����� �߱� �� �߱���� ��迡 ���� ����� ȸ������ ��� ��ȸ
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.27
	 */
	//@RequestMapping(value="/member/static_member_list.ms")
	@RequestMapping(value="/member/static_member_list.st")
	public String StaticMemberList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<StaticMember> memberList = null;
		MemberService ms = new MemberService();
		StaticMemberService sms = new StaticMemberService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("menuOpt", checkStr(request.getParameter("menuOpt"), "")); //--01:�߱�, 02:�߱����
		String membId = checkStr(request.getParameter("membId"), "");
		params.put("membId", membId);
		Member member = (Member) ms.selectAMember(params);
		
		params.put("termInfo", checkStr(request.getParameter("termInfo"), "")); //-- ������ �Ⱓ����  ���� : yyyy-mm , �Ϻ� : yyyy-mm-dd
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "")); //-- dayDate : �Ϻ� , monthDate : ����
		params.put("name", checkStr(request.getParameter("name"), ""));
		
		String sdate = checkStr(request.getParameter("sdate").replace("-", ""), "");
		String edate = checkStr(request.getParameter("edate").replace("-", ""), "");
		
		params.put("sdate", sdate);
		params.put("edate", edate);
		
		log.debug("@@@@@@@@@@ MemberIssueStaticDayList params : "+ params); //##
		
		memberList = sms.selectMemberList(params); //-- �����ȸ

		request.setAttribute("menuOpt",  params.get("menuOpt"));

		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("memberList", memberList);
		request.setAttribute("termInfo", checkStr(request.getParameter("termInfo"), ""));
		request.setAttribute("menuOpt", checkStr(request.getParameter("menuOpt"), ""));
		request.setAttribute("sdate", sdate.substring(0, 4) + "-" + sdate.substring(4,6) + "-" + sdate.substring(6));
		request.setAttribute("edate", edate.substring(0, 4) + "-" + edate.substring(4,6) + "-" + edate.substring(6));
		request.setAttribute("membName", member.getName());
		request.setAttribute("membCnt", memberList.size());
		request.setAttribute("se_termOpt", checkStr(request.getParameter("se_termOpt"), ""));
		params.clear();
		
		return "member/member_static_list";
	}
	

	/**
	 * @Method Name : StaticMemberList
	 * @Description : ����� �߱� �� �߱���� ��迡 ���� ����� ȸ������ ��� ��ȸ
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.27
	 */
	//@RequestMapping(value="/member/member_static_list_excel.ms")
	@RequestMapping(value="/member/member_static_list_excel.st")
	public String StaticMemberListExcel(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<StaticMember> memberList = null;
		MemberService ms = new MemberService();
		StaticMemberService sms = new StaticMemberService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("menuOpt", checkStr(request.getParameter("menuOpt"), "")); //--01:�߱�, 02:�߱����
		String membId = checkStr(request.getParameter("membId"), "");
		params.put("membId", membId);
		Member member = (Member) ms.selectAMember(params);
		
		params.put("termInfo", checkStr(request.getParameter("termInfo"), "")); //-- ������ �Ⱓ����  ���� : yyyy-mm , �Ϻ� : yyyy-mm-dd
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "")); //-- dayDate : �Ϻ� , monthDate : ����
		params.put("name", checkStr(request.getParameter("name"), ""));
		
		String sdate = checkStr(request.getParameter("sdate").replace("-", ""), "");
		String edate = checkStr(request.getParameter("edate").replace("-", ""), "");
		
		params.put("sdate", sdate);
		params.put("edate", edate);
		
		log.debug("@@@@@@@@@@ MemberIssueStaticDayList params : "+ params); //##
		
		memberList = sms.selectMemberList(params); //-- �����ȸ

		request.setAttribute("menuOpt",  params.get("menuOpt"));

		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("memberList", memberList);
		request.setAttribute("termInfo", checkStr(request.getParameter("termInfo"), ""));
		request.setAttribute("menuOpt", checkStr(request.getParameter("menuOpt"), ""));
		request.setAttribute("sdate", sdate.substring(0, 4) + "-" + sdate.substring(4,6) + "-" + sdate.substring(6));
		request.setAttribute("edate", edate.substring(0, 4) + "-" + edate.substring(4,6) + "-" + edate.substring(6));
		request.setAttribute("membName", member.getName());
		request.setAttribute("membCnt", memberList.size());
		request.setAttribute("se_termOpt", checkStr(request.getParameter("se_termOpt"), ""));
		params.clear();
		
		return "member/member_static_list_excel";
	}
	
	/**
	 * @Method Name : MemberIssueCancelStaticMonthList
	 * @Description : �߱�������(����)�� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	//@RequestMapping(value="/member/member_issue_cancel_static_month_list.ms")
	@RequestMapping(value="/member/member_issue_cancel_static_month_list.st")
	public String MemberIssueCancelStaticMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<HashMap<String, Object>> memberIssueCancelStaticMonthList = null;
		MemberIssueStaticService memberIssueStaticService = new MemberIssueStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statMonth", checkStr(request.getParameter("statMonth"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 
		
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		String edate = checkStr(request.getParameter("edate"), "");
				
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}	
		
		//��ȸ edate�� ���� ��������¥ ���ϱ�
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
				
		calendar.set(year, month, date);
			
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();
		
		params.put("sdate", sdate);
		params.put("edate", edate);
		
		String STAT_MONTH = checkStr(request.getParameter("statMonth"), sdate.substring(0,6));
				
		for(int i=0; i<=differMonths; i++){
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+"+i+",CONVERT(datetime,'"+STAT_MONTH+"'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS USER_CNT" + (i+1));
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+"+i+",CONVERT(datetime,'"+STAT_MONTH+"'+'01')),120),'-',''),1,6) THEN ISNULL(PRE_MONTH_RATIO,0) ELSE 0 END) AS PRE_MONTH_RATIO"+(i+1));
		}
		params.put("subQuery", subQuery.toString());
		
		log.debug("@@@@@@@@@@ MemberIssueCancelStaticMonthList params : "+ params); //##
		
 		memberIssueCancelStaticMonthList = memberIssueStaticService.selectMemberIssueCancelStaticMonthList(params); //-- �����ȸ
		
		
 		String[] monthList = new String[differMonths];
 		monthList = getMonthList(sdate, differMonths);
 		
 		/* SET ATTRIBUTEs */
		
 		
 		request.setAttribute("memberIssueCancelStaticMonthList", memberIssueCancelStaticMonthList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("monthList", monthList);
		request.setAttribute("statMonth", params.get("statMonth"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-01");
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +endDay);
		request.setAttribute("name", params.get("name"));
		request.setAttribute("diffMonths", differMonths);
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/member_issue_cancel_static_month_list";
	}
	
	
	/**
	 * @Method Name : MemberIssueCancelStaticMonthExcel
	 * @Description : �߱�������(����)�� Excel�� �����Ѵ�
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	//@RequestMapping(value="/member/member_issue_cancel_static_month_list_excel.ms")
	@RequestMapping(value="/member/member_issue_cancel_static_month_list_excel.st")
	public String MemberIssueCancelStaticMonthExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<HashMap<String, Object>> memberIssueCancelStaticMonthList = null;
		MemberIssueStaticService memberIssueStaticService = new MemberIssueStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 
		
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		String edate = checkStr(request.getParameter("edate"), "");
				
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}	
		
		//��ȸ edate�� ���� ��������¥ ���ϱ�
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
				
		calendar.set(year, month, date);
				
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();
		
		params.put("sdate", sdate);
		params.put("edate", edate);
		
		String STAT_MONTH = checkStr(request.getParameter("statMonth"), sdate.substring(0,6));
				
		for(int i=0; i<=differMonths; i++){
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+"+i+",CONVERT(datetime,'"+STAT_MONTH+"'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS USER_CNT" + (i+1));
			subQuery.append(", SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+"+i+",CONVERT(datetime,'"+STAT_MONTH+"'+'01')),120),'-',''),1,6) THEN ISNULL(PRE_MONTH_RATIO,0) ELSE 0 END) AS PRE_MONTH_RATIO"+(i+1));
		}
		params.put("subQuery", subQuery.toString());
		
		
		
		
		log.debug("@@@@@@@@@@ MemberIssueCancelStaticMonthExcel params : "+ params); //##
		
 		memberIssueCancelStaticMonthList = memberIssueStaticService.selectMemberIssueCancelStaticMonthList(params); //-- �����ȸ
		
		
 		String[] monthList = new String[differMonths];
 		monthList = getMonthList(sdate, differMonths);
 		
 		/* SET ATTRIBUTEs */
		
 		
 		request.setAttribute("memberIssueCancelStaticMonthList", memberIssueCancelStaticMonthList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("monthList", monthList);
		request.setAttribute("statMonth", params.get("statMonth"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-01");
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +endDay);
		request.setAttribute("name", params.get("name"));
		request.setAttribute("diffMonths", differMonths);
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		params.clear();
		return "member/member_issue_cancel_static_month_list_excel";
	}
	
	/**
	 * @Method Name : today
	 * @Description : ���� ��¥�� ��ȸ�Ѵ�.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2010.04.23
	 */
	public static String today() {
		java.sql.Date CurrDate = new java.sql.Date((new java.util.Date()).getTime());
		return CurrDate.toString();
	}
	
	
	
	/**
	 * @Method Name : getDiffMonths
	 * @Description : ���ۿ��� ������� ������ ���̸� ��ȯ�Ѵ�.
	 * @param : String, String 
	 * @return : int 
	 * @author ���¸�
	 * @since 2012.09.27
	 */
	public static int  getDiffMonths(String sMonth, String eMonth){
		int strtYear = Integer.parseInt(sMonth.replace("-", "").substring(0,4));
		int strtMonth = Integer.parseInt(sMonth.replace("-",  "").substring(4,6));
		int endYear = Integer.parseInt(eMonth.replace("-", "").substring(0,4));
		int endMonth = Integer.parseInt(eMonth.replace("-", "").substring(4,6)); 
		
		int month = (endYear - strtYear)* 12 + (endMonth - strtMonth) + 1;
		return month;
	}

	/**
	 * @Method Name : getMonthList
	 * @Description : ���ۿ��κ��� n���� ��ŭ�� �� ������ String �迭�� ��ȯ�Ѵ�. 
	 * @param : String, String 
	 * @return : String[] 
	 * @author ���¸�
	 * @since 2012.09.27
	 */
	public static String[] getMonthList(String startYYYYMMDD, int diffMonth){

		String[] monthList;
		if(diffMonth == 0){
			monthList = new String[1];
		}else{
			monthList = new String[diffMonth];
		}
		
		String tempMonth = "";
		
		int year = Integer.parseInt(startYYYYMMDD.substring(0, 4));
		int month = Integer.parseInt(startYYYYMMDD.substring(4, 6));
		
		monthList[0] = startYYYYMMDD.substring(0, 4) + "-" + startYYYYMMDD.substring(4, 6);
		for(int i=1; i<diffMonth; i++){
			if(month==12){
				month = 1;
				year = year++;
			}
			else{
				month++;
			}
			
			if(month<10){
				tempMonth =  year + "-0" + month ;
			}
			else{
				tempMonth =  year + "-" + month ;
			}
			
			monthList[i] = tempMonth;
		}
		
		return monthList;
	}

	/**
	 * @Method Name : lastMonth
	 * @Description : ���� ���� ��¥�� �� ��ȸ�Ѵ�.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2012.09.26
	 */
	public static String lastMonth() throws Exception{
		String today = today().replace("-", "");
		String lastMonth = "";
		
		int year = Integer.parseInt(today.substring(0, 4));
		int month = Integer.parseInt(today.substring(4, 6));
		String day = "01";
		
		if (month==1){
			year--;
			month = 12;
		}
		else{
			month--;
		}
		
		if(month<10){
			lastMonth =  year + "-0" + month + "-" + day;
		}
		else{
			lastMonth =  year + "-" + month + "-" + day;
		}
		
		return lastMonth;
	}	
	
/*******************************************************************************************************************************************************************************
 * 	@author ������ 
 *******************************************************************************************************************************************************************************/

	/**
	 *  ���� ��� �Ϲ� �Ϻ�
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_joinNomal_static_day_list.ms")
	@RequestMapping(value="/member/member_joinNomal_static_day_list.st")
	public String MemberJoinNomalStaticDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

			List<HashMap<String, Object>> memberJoinNomalStaticDayList = null;
			List<HashMap<String, Object>> memberJoinNomalStaticMembList = null;
			MemberJoinStaticService memberJoinNomalStaticDayService = new MemberJoinStaticService();
			HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
			
			String membId = checkStr(request.getParameter("membId"), ""); 
			String name = checkStr(request.getParameter("name"), ""); 
			
			params.put("membId", membId);
			params.put("name", name);
			
			memberJoinNomalStaticMembList = memberJoinNomalStaticDayService.getMemberJoinMemberShips(params);

			params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
			String today = today();
			if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
				params.put("sdate", checkStr(request.getParameter("sdate"), today));
				params.put("edate", checkStr(request.getParameter("edate"), today));
			}
			
			params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
			params.put("goStatic", checkStr(request.getParameter("goStatic"), "01")); //-- ��ȸ���� 
					

			String lastMonth = lastMonth();
			String sdate = checkStr(request.getParameter("sdate"), DateTime.format("yyyy-MM-dd"));
			String edate = checkStr(request.getParameter("edate"), DateTime.format("yyyy-MM-dd"));

			sdate = sdate.replace("-", "");
			edate = edate.replace("-", "");
			
			int differMonths = getDiffMonths(sdate, edate);
			StringBuffer subQuery = new StringBuffer();

			System.out.println("differMonths : " + differMonths);
			
			for(int i=0; i<memberJoinNomalStaticMembList.size(); i++){
				subQuery.append(",SUM(CASE WHEN MEMB_ID ='"+((Map)memberJoinNomalStaticMembList.get(i)).get("MEMB_ID")+"' THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
			}
			
			System.out.println("size : "+memberJoinNomalStaticMembList.size());
			
			params.put("subQuery", subQuery.toString());
			params.put("sdate", sdate);
			params.put("edate", edate);

			log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
			
			
			memberJoinNomalStaticDayList = memberJoinNomalStaticDayService.selectMemberJoinNomalStaticDayList(params); //�����ȸ
			
	 		String[] monthList = getMonthList(sdate, differMonths);
			
			/* SET ATTRIBUTEs */
			request.setAttribute("membId", params.get("membId"));
			request.setAttribute("name", params.get("name"));
			request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-" +params.get("sdate").toString().substring(6));
			request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +params.get("edate").toString().substring(6));
			request.setAttribute("differMonths", differMonths);
			request.setAttribute("monthList", monthList);
			request.setAttribute("membCardlistList", memberJoinNomalStaticMembList);
			request.setAttribute("memberJoinNomalStaticDayList", memberJoinNomalStaticDayList);
			
			params.clear();
			
		return "member/member_joinNomal_static_day_list";
	}

	/**
	 *  ���� ��� �Ϲ� �Ϻ� Excel
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_joinNomal_static_day_list_excel.ms")
	@RequestMapping(value="/member/member_joinNomal_static_day_list_excel.st")
	public String MemberJoinNomalStaticDayListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<HashMap<String, Object>> memberJoinNomalStaticDayList = null;
		List<HashMap<String, Object>> memberJoinNomalStaticMembList = null;
		MemberJoinStaticService memberJoinNomalStaticDayService = new MemberJoinStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
		
		String membId = checkStr(request.getParameter("membId"), ""); 
		String name = checkStr(request.getParameter("name"), ""); 
		
		params.put("membId", membId);
		params.put("name", name);
		
		memberJoinNomalStaticMembList = memberJoinNomalStaticDayService.getMemberJoinMemberShips(params);

		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		params.put("goStatic", checkStr(request.getParameter("goStatic"), "01")); //-- ��ȸ���� 
				

		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), DateTime.format("yyyy-MM-dd"));
		String edate = checkStr(request.getParameter("edate"), DateTime.format("yyyy-MM-dd"));

		sdate = sdate.replace("-", "");
		edate = edate.replace("-", "");
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();

		System.out.println("differMonths : " + differMonths);
		
		for(int i=0; i<memberJoinNomalStaticMembList.size(); i++){
			subQuery.append(",SUM(CASE WHEN MEMB_ID ='"+((Map)memberJoinNomalStaticMembList.get(i)).get("MEMB_ID")+"' THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}
		
		System.out.println("size : "+memberJoinNomalStaticMembList.size());
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
		
		memberJoinNomalStaticDayList = memberJoinNomalStaticDayService.selectMemberJoinNomalStaticDayList(params); //�����ȸ
		
 		String[] monthList = getMonthList(sdate, differMonths);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("differMonths", differMonths);
		request.setAttribute("monthList", monthList);
		request.setAttribute("membCardlistList", memberJoinNomalStaticMembList);
		request.setAttribute("memberJoinNomalStaticDayList", memberJoinNomalStaticDayList);
		
		params.clear();
			
		return "member/member_joinNomal_static_day_list_excel";
	}

	/**
	 *  ���� ��� �Ϲ� ����
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_joinNomal_static_month_list.ms")
	@RequestMapping(value="/member/member_joinNomal_static_month_list.st")
	public String MemberJoinNomalStaticMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
				
		List<HashMap<String, Object>> memberJoinNomalStaticMonthList = null;
		MemberJoinStaticService memberJoinNomalStaticService = new MemberJoinStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
		Calendar calendar = Calendar.getInstance();
		
		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 
		params.put("goStatic", checkStr(request.getParameter("goStatic"), "01")); //-- ��ȸ���� 

		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
				
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}
		
		//��ȸ edate�� ���� ��������¥ ���ϱ�
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
				
		calendar.set(year, month, date);
			
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();

		System.out.println("differMonths : " + differMonths);
		
		for(int i=0; i<=differMonths; i++){
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + + (i+1));
		}	
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params);
		
		memberJoinNomalStaticMonthList = memberJoinNomalStaticService.selectMemberJoinNomalStaticMonthList(params);  //�����ȸ
		
 		String[] monthList = new String[differMonths];
 		monthList = getMonthList(sdate, differMonths);
		System.out.println(monthList);
		/* SET ATTRIBUTEs */
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-01");
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +endDay);
		request.setAttribute("differMonths", differMonths);
		request.setAttribute("monthList", monthList);
		request.setAttribute("statMonth", params.get("statMonth"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		
		request.setAttribute("memberJoinNomalStaticMonthList", memberJoinNomalStaticMonthList);
		
		params.clear();
		
		
		return "member/member_joinNomal_static_month_list";
	}


	/**
	 *  ���� ��� �Ϲ� ���� Excel
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_joinNomal_static_month_list_excel.ms")
	@RequestMapping(value="/member/member_joinNomal_static_month_list_excel.st")
	public String MemberJoinNomalStaticMonthListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
				
		List<HashMap<String, Object>> memberJoinNomalStaticMonthList = null;
		MemberJoinStaticService memberJoinNomalStaticService = new MemberJoinStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
		Calendar calendar = Calendar.getInstance();
		
		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 
		params.put("goStatic", checkStr(request.getParameter("goStatic"), "01")); //-- ��ȸ���� 

		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
				
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}
		
		//��ȸ edate�� ���� ��������¥ ���ϱ�
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
			
		calendar.set(year, month, date);
		
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();

		System.out.println("differMonths : " + differMonths);
		
		for(int i=0; i<=differMonths; i++){
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}	
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
		memberJoinNomalStaticMonthList = memberJoinNomalStaticService.selectMemberJoinNomalStaticMonthList(params);  //�����ȸ
		
 		String[] monthList = new String[differMonths];
 		monthList = getMonthList(sdate, differMonths);
		System.out.println(monthList);
		/* SET ATTRIBUTEs */
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-01");
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +endDay);
		request.setAttribute("differMonths", differMonths);
		request.setAttribute("monthList", monthList);
		request.setAttribute("statMonth", params.get("statMonth"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		
		request.setAttribute("memberJoinNomalStaticMonthList", memberJoinNomalStaticMonthList);
		
		params.clear();
		
		
		return "member/member_joinNomal_static_month_list_excel";
	}


	/**
	 *  ���� ��� OS �Ϻ�
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_joinOS_static_day_list.ms")
	@RequestMapping(value="/member/member_joinOS_static_day_list.st")
	public String MemberJoinOSStaticDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<MemberJoinStatic> memberJoinStaticOSDayList = null;
		MemberJoinStaticService memberJoinStaticService = new MemberJoinStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("goStatic", checkStr(request.getParameter("goStatic"), "02")); //-- ��ȸ���� 
		
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
				
		memberJoinStaticOSDayList = memberJoinStaticService.selectMemberJoinStaticDayList(params); //-- �����ȸ
				
		/* SET ATTRIBUTEs */
		request.setAttribute("memberJoinStaticOSDayList", memberJoinStaticOSDayList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("statDay", params.get("statDay"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		request.setAttribute("android_kt_cnt", params.get("android_kt_cnt"));
		request.setAttribute("android_skt_cnt", params.get("android_skt_cnt"));
		request.setAttribute("android_lgu_cnt", params.get("android_lgu_cnt"));
		request.setAttribute("android_cnt", params.get("android_cnt"));
		request.setAttribute("ios_kt_cnt", params.get("ios_kt_cnt"));
		request.setAttribute("ios_skt_cnt", params.get("ios_skt_cnt"));
		request.setAttribute("ios_lgu_cnt", params.get("ios_lgu_cnt"));
		request.setAttribute("ios_cnt", params.get("ios_cnt"));
		request.setAttribute("total_cnt", params.get("total_cnt"));
		
		params.clear();

		return "member/member_joinOS_static_day_list";
	}

	
	/**
	 * @Method Name : MemberIssueStaticExcel
	 * @Description : ���������ȸ(�Ϻ�)����� EXCEL ���Ϸ� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ������
	 * @since 2012.09.24
	 */
	//@RequestMapping(value="/member/member_joinOS_static_day_list_excel.ms")
	@RequestMapping(value="/member/member_joinOS_static_day_list_excel.st")
	public String MemberJoinStaticExcel(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)  throws Exception{

		List<MemberJoinStatic> memberJoinStaticOSDayList = null;
		MemberJoinStaticService memberJoinStaticService = new MemberJoinStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("goStatic", checkStr(request.getParameter("goStatic"), "02")); //-- ��ȸ���� 

		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
				
		memberJoinStaticOSDayList = memberJoinStaticService.selectMemberJoinStaticDayList(params); //-- �����ȸ
				
		/* SET ATTRIBUTEs */
		request.setAttribute("memberJoinStaticOSDayList", memberJoinStaticOSDayList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("statDay", params.get("statDay"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		request.setAttribute("android_kt_cnt", params.get("android_kt_cnt"));
		request.setAttribute("android_skt_cnt", params.get("android_skt_cnt"));
		request.setAttribute("android_lgu_cnt", params.get("android_lgu_cnt"));
		request.setAttribute("android_cnt", params.get("android_cnt"));
		request.setAttribute("ios_kt_cnt", params.get("ios_kt_cnt"));
		request.setAttribute("ios_skt_cnt", params.get("ios_skt_cnt"));
		request.setAttribute("ios_lgu_cnt", params.get("ios_lgu_cnt"));
		request.setAttribute("ios_cnt", params.get("ios_cnt"));
		request.setAttribute("total_cnt", params.get("total_cnt"));
		
		params.clear();
		return "member/member_joinOS_static_day_list_excel";
	}

	/**
	 *  ���� ��� OS ����
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_joinOS_static_month_list.ms")
	@RequestMapping(value="/member/member_joinOS_static_month_list.st")
	public String MemberJoinOSStaticMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

			List<HashMap<String, Object>> memberJoinOSStaticMonthList = null;
			MemberJoinStaticService memberJoinOSStaticService = new MemberJoinStaticService();
			HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
			
			params.put("membId", checkStr(request.getParameter("membId"), ""));
			params.put("name", checkStr(request.getParameter("name"), ""));
			params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
			params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 
			params.put("statMonth", checkStr(request.getParameter("statMonth"), ""));
			params.put("statType", checkStr(request.getParameter("statType"), ""));
			
			String lastMonth = lastMonth();
			String sdate = checkStr(request.getParameter("sdate"), "");
			String edate = checkStr(request.getParameter("edate"), "");
					
			if (sdate == null || "".equals(sdate)){
				sdate = lastMonth.replace("-", "");
			}
			else{
				sdate = sdate.replace("-", "");
			}
			if (edate == null || "".equals(edate)){
				edate = lastMonth.replace("-", "");
			}
			else{
				edate = edate.replace("-", "");
			}

			int diffMonths = getDiffMonths(sdate, edate);
			StringBuffer subQuery = new StringBuffer(); //-- ���� ���̺� �� ������
			System.out.println("diffMonths : " + diffMonths);

			String STAT_MONTH = checkStr(request.getParameter("statMonth"), sdate.substring(0,6));

			for(int i=0; i<=diffMonths; i++){
				subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + STAT_MONTH + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
			}
			
			params.put("subQuery", subQuery.toString());
			params.put("sdate", sdate);
			params.put("edate", edate);

			log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##

			memberJoinOSStaticMonthList = memberJoinOSStaticService.selectMemberJoinOSStaticMonthList(params);  //�����ȸ
			
	 		String[] monthList = getMonthList(sdate, diffMonths);
			
			/* SET ATTRIBUTEs */
			request.setAttribute("membId", params.get("membId"));
			request.setAttribute("name", params.get("name"));
			request.setAttribute("sdate", params.get("sdate"));
			request.setAttribute("edate", params.get("edate"));
			request.setAttribute("diffMonths", diffMonths);
			request.setAttribute("monthList", monthList);
			
			request.setAttribute("memberJoinOSStaticMonthList", memberJoinOSStaticMonthList);
			
			params.clear();
			
		 
		 return "member/member_joinOS_static_month_list";
	}

		/**
		 *  ���� ��� OS ���� Excel
		 * @param model
		 * @param locale
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		//@RequestMapping(value="/member/member_joinOS_static_month_list_excel.ms")
		@RequestMapping(value="/member/member_joinOS_static_month_list_excel.st")
		public String MemberJoinOSStaticMonthListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

				List<HashMap<String, Object>> memberJoinOSStaticMonthList = null;
				MemberJoinStaticService memberJoinOSStaticService = new MemberJoinStaticService();
				HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
				
				params.put("membId", checkStr(request.getParameter("membId"), ""));
				params.put("name", checkStr(request.getParameter("name"), ""));
				params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
				params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 
				params.put("statMonth", checkStr(request.getParameter("statMonth"), ""));
				params.put("statType", checkStr(request.getParameter("statType"), ""));
				
				String lastMonth = lastMonth();
				String sdate = checkStr(request.getParameter("sdate"), "");
				String edate = checkStr(request.getParameter("edate"), "");
						
				if (sdate == null || "".equals(sdate)){
					sdate = lastMonth.replace("-", "");
				}
				else{
					sdate = sdate.replace("-", "");
				}
				if (edate == null || "".equals(edate)){
					edate = lastMonth.replace("-", "");
				}
				else{
					edate = edate.replace("-", "");
				}

				int diffMonths = getDiffMonths(sdate, edate);
				StringBuffer subQuery = new StringBuffer(); //-- ���� ���̺� �� ������
				System.out.println("diffMonths : " + diffMonths);

				String STAT_MONTH = checkStr(request.getParameter("statMonth"), sdate.substring(0,6));

				for(int i=0; i<=diffMonths; i++){
					subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + STAT_MONTH + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
				}
				
				params.put("subQuery", subQuery.toString());
				params.put("sdate", sdate);
				params.put("edate", edate);

				log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##

				memberJoinOSStaticMonthList = memberJoinOSStaticService.selectMemberJoinOSStaticMonthList(params);  //�����ȸ
				
		 		String[] monthList = getMonthList(sdate, diffMonths);
				
				/* SET ATTRIBUTEs */
				request.setAttribute("membId", params.get("membId"));
				request.setAttribute("name", params.get("name"));
				request.setAttribute("sdate", params.get("sdate"));
				request.setAttribute("edate", params.get("edate"));
				request.setAttribute("diffMonths", diffMonths);
				request.setAttribute("monthList", monthList);
				
				request.setAttribute("memberJoinOSStaticMonthList", memberJoinOSStaticMonthList);
				
				params.clear();
				
			 
			 return "member/member_joinOS_static_month_list_excel";
		}

		 
		 /**
		 *  ���� ��� ����/���� �Ϻ�
		 * @param model
		 * @param locale
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
	//@RequestMapping(value="/member/member_joinAge_static_day_list.ms")
	@RequestMapping(value="/member/member_joinAge_static_day_list.st")
	public String MemberJoinAgeStaticDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<MemberJoinStatic> memberJoinAgeStaticDayList = null;
		MemberJoinStaticService memberJoinStaticService = new MemberJoinStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("goStatic", checkStr(request.getParameter("goStatic"), "03")); //-- ��ȸ���� 

		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
 		memberJoinAgeStaticDayList = memberJoinStaticService.selectMemberJoinAgeStaticDayList(params); //-- �����ȸ
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("memberJoinAgeStaticDayList", memberJoinAgeStaticDayList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("statDay", params.get("statDay"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		request.setAttribute("android_kt_cnt", params.get("android_kt_cnt"));
		request.setAttribute("android_skt_cnt", params.get("android_skt_cnt"));
		request.setAttribute("android_lgu_cnt", params.get("android_lgu_cnt"));
		request.setAttribute("android_cnt", params.get("android_cnt"));
		request.setAttribute("ios_kt_cnt", params.get("ios_kt_cnt"));
		request.setAttribute("ios_skt_cnt", params.get("ios_skt_cnt"));
		request.setAttribute("ios_lgu_cnt", params.get("ios_lgu_cnt"));
		request.setAttribute("ios_cnt", params.get("ios_cnt"));
		request.setAttribute("total_cnt", params.get("total_cnt"));
		
		params.clear();

		return "member/member_joinAge_static_day_list";
	}

	/**
	 *  ���� ��� ����/���� �Ϻ� ����
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_joinAge_static_day_list_excel.ms")
	@RequestMapping(value="/member/member_joinAge_static_day_list_excel.st")
	public String MemberJoinAgeStaticExcel(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		List<MemberJoinStatic> memberJoinAgeStaticDayList = null;
		MemberJoinStaticService memberJoinStaticService = new MemberJoinStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("goStatic", checkStr(request.getParameter("goStatic"), "03")); //-- ��ȸ���� 

		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
 		memberJoinAgeStaticDayList = memberJoinStaticService.selectMemberJoinAgeStaticDayList(params); //-- �����ȸ
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("memberJoinAgeStaticDayList", memberJoinAgeStaticDayList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("statDay", params.get("statDay"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		request.setAttribute("android_kt_cnt", params.get("android_kt_cnt"));
		request.setAttribute("android_skt_cnt", params.get("android_skt_cnt"));
		request.setAttribute("android_lgu_cnt", params.get("android_lgu_cnt"));
		request.setAttribute("android_cnt", params.get("android_cnt"));
		request.setAttribute("ios_kt_cnt", params.get("ios_kt_cnt"));
		request.setAttribute("ios_skt_cnt", params.get("ios_skt_cnt"));
		request.setAttribute("ios_lgu_cnt", params.get("ios_lgu_cnt"));
		request.setAttribute("ios_cnt", params.get("ios_cnt"));
		request.setAttribute("total_cnt", params.get("total_cnt"));
		
		params.clear();

		return "member/member_joinAge_static_day_list_excel";
	}

	/**
	 *  ���� ��� ����/���� ����
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_joinAge_static_month_list.ms")
	@RequestMapping(value="/member/member_joinAge_static_month_list.st")
	public String MemberJoinAgeStaticMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<HashMap<String, Object>> statictList = null;
		MemberJoinStaticService memberJoinAgeStaticService = new MemberJoinStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
		
		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));

//		String lastMonth = lastMonth();
//		String sdate = checkStr(request.getParameter("sdate"), lastMonth.replace("-",  "").substring(0,6)+"01");
//		String edate = checkStr(request.getParameter("edate"), lastMonth.replace("-",  "").substring(0,6)+"01");
//		edate = "20121201";
//		System.out.println("lastMonth : " + lastMonth);
//		System.out.println("sdate : " + sdate);
//		System.out.println("edate : " + edate);
				
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
				
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}
		
		int diffMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer(); //-- ���� ���̺� �� ������
		System.out.println("diffMonths : " + diffMonths);
		
		for(int i=0; i<=diffMonths; i++){
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT"+ (i+1));
		}
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##

		statictList = memberJoinAgeStaticService.selectMemberJoinAgeStaticMonthList(params);  //�����ȸ
		
 		String[] monthList = getMonthList(sdate, diffMonths);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("diffMonths", diffMonths);
		request.setAttribute("monthList", monthList);
		
		request.setAttribute("statictList", statictList);
		
		params.clear();

		return "member/member_joinAge_static_month_list";
	}


	/**
	 *  ���� ��� ����/���� ���� Excel
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_joinAge_static_month_list_excel.ms")
	@RequestMapping(value="/member/member_joinAge_static_month_list_excel.st")
	public String MemberJoinAgeStaticMonthListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<HashMap<String, Object>> statictList = null;
		MemberJoinStaticService memberJoinAgeStaticService = new MemberJoinStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
		
		String membId = checkStr(request.getParameter("membId"), ""); 
		String name = checkStr(request.getParameter("name"), ""); 

//		String lastMonth = lastMonth();
//		String sdate = checkStr(request.getParameter("sdate"), lastMonth.replace("-",  "").substring(0,6)+"01");
//		String edate = checkStr(request.getParameter("edate"), lastMonth.replace("-",  "").substring(0,6)+"01");
//		edate = "20121201";
//		System.out.println("lastMonth : " + lastMonth);
//		System.out.println("sdate : " + sdate);
//		System.out.println("edate : " + edate);
				
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
				
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}
		
		int diffMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer(); //-- ���� ���̺� �� ������
		System.out.println("diffMonths : " + diffMonths);//##
		
		for(int i=0; i<=diffMonths; i++){
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##

		statictList = memberJoinAgeStaticService.selectMemberJoinAgeStaticMonthList(params);  //�����ȸ
		
 		String[] monthList = getMonthList(sdate, diffMonths);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("diffMonths", diffMonths);
		request.setAttribute("monthList", monthList);
		
		request.setAttribute("statictList", statictList);
		
		params.clear();

		return "member/member_joinAge_static_month_list_excel";
	}


	/**
	 *  ���� ��� �Ϲ� �Ϻ�
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_connectNomal_static_day_list.ms")
	@RequestMapping(value="/member/member_connectNomal_static_day_list.st")
	public String MemberConnectNomalStaticDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<HashMap<String, Object>> membConnectNomalDayList = null;
		MemberConnectStaticService memberConnectNomalStaticDayService = new MemberConnectStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����

		List<MembCardlist> membCardlistList = null;
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlistExample membCardlistExample = null;

		String membId = checkStr(request.getParameter("membId"), ""); 
		String name = checkStr(request.getParameter("name"), ""); 
		
		membCardlistExample = new MembCardlistExample();
		if(!membId.equals(""))
		membCardlistExample.or().andMembIdLike(membId+"%");

		if(!name.equals(""))
		membCardlistExample.or().andNameLike(name+"%");	
		
		membCardlistList = membCardlistService.getByExample(membCardlistExample);

		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), DateTime.format("yyyy-MM-dd"));
		String edate = checkStr(request.getParameter("edate"), DateTime.format("yyyy-MM-dd"));

		sdate = sdate.replace("-", "");
		edate = edate.replace("-", "");


		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();

		System.out.println("differMonths : " + differMonths);
		
		for(int i=0; i<membCardlistList.size(); i++){
			subQuery.append(",SUM(CASE WHEN MEMB_ID ='"+membCardlistList.get(i).getMembId()+"' THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##

		membConnectNomalDayList = memberConnectNomalStaticDayService.selectMemberConnectNomalStaticDayList(params); //�����ȸ
		
 		String[] monthList = getMonthList(sdate, differMonths);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-" +params.get("sdate").toString().substring(6));
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +params.get("edate").toString().substring(6));
		request.setAttribute("differMonths", differMonths);
		request.setAttribute("monthList", monthList);
		request.setAttribute("membCardlistList", membCardlistList);
		request.setAttribute("membConnectNomalDayList", membConnectNomalDayList);
		
		params.clear();
		
		return "member/member_connectNomal_static_day_list";
	}


	/**
	 *  ���� ��� �Ϲ� �Ϻ� ����
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_connectNomal_static_day_list_excel.ms")
	@RequestMapping(value="/member/member_connectNomal_static_day_list_excel.st")
	public String MemberConnectNomalStaticDayListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<HashMap<String, Object>> membConnectNomalDayList = null;
		MemberConnectStaticService memberConnectNomalStaticDayService = new MemberConnectStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����

		List<MembCardlist> membCardlistList = null;
		MembCardlistService membCardlistService = new MembCardlistService();
		MembCardlistExample membCardlistExample = null;

		String membId = checkStr(request.getParameter("membId"), ""); 
		String name = checkStr(request.getParameter("name"), ""); 
		
		membCardlistExample = new MembCardlistExample();
		if(!membId.equals(""))
		membCardlistExample.or().andMembIdLike(membId+"%");

		if(!name.equals(""))
		membCardlistExample.or().andNameLike(name+"%");	
		
		membCardlistList = membCardlistService.getByExample(membCardlistExample);

		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "dayDate")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), DateTime.format("yyyy-MM-dd"));
		String edate = checkStr(request.getParameter("edate"), DateTime.format("yyyy-MM-dd"));

		sdate = sdate.replace("-", "");
		edate = edate.replace("-", "");


		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();

		System.out.println("differMonths : " + differMonths);
		
		for(int i=0; i<membCardlistList.size(); i++){
			subQuery.append(",SUM(CASE WHEN MEMB_ID ='"+membCardlistList.get(i).getMembId()+"' THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##

		membConnectNomalDayList = memberConnectNomalStaticDayService.selectMemberConnectNomalStaticDayList(params); //�����ȸ
		
 		String[] monthList = getMonthList(sdate, differMonths);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-" +params.get("sdate").toString().substring(6));
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +params.get("edate").toString().substring(6));
		request.setAttribute("differMonths", differMonths);
		request.setAttribute("monthList", monthList);
		request.setAttribute("membCardlistList", membCardlistList);
		request.setAttribute("membConnectNomalDayList", membConnectNomalDayList);
		
		params.clear();
		
		return "member/member_connectNomal_static_day_list_excel";
	}

	/**
	 *  ���� ��� �Ϲ� ����
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_connectNomal_static_month_list.ms")
	@RequestMapping(value="/member/member_connectNomal_static_month_list.st")
	public String MemberConnectNomalStaticMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<HashMap<String, Object>> memberConnectNomalStaticMonthList = null;
		MemberConnectStaticService memberConnectNomalStaticMonthService = new MemberConnectStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
		Calendar calendar = Calendar.getInstance();
		
		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 
		
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
				
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}
		
		//��ȸ edate�� ���� ��������¥ ���ϱ�
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
				
		calendar.set(year, month, date);
		
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();

		System.out.println("differMonths : " + differMonths);
		
		for(int i=0; i<=differMonths; i++){
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}	
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
		memberConnectNomalStaticMonthList = memberConnectNomalStaticMonthService.selectMemberConnectNomalStaticMonthList(params);  //�����ȸ
		
 		String[] monthList = new String[differMonths];
 		monthList = getMonthList(sdate, differMonths);
		System.out.println(monthList);

		/* SET ATTRIBUTEs */
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-01");
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +endDay);
		request.setAttribute("differMonths", differMonths);
		request.setAttribute("monthList", monthList);
		request.setAttribute("statMonth", params.get("statMonth"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		
		request.setAttribute("memberConnectNomalStaticMonthList", memberConnectNomalStaticMonthList);
		
		params.clear();


		return "member/member_connectNomal_static_month_list";
	}


	/**
	 *  ���� ��� �Ϲ� ���� Excel
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_connectNomal_static_month_list_excel.ms")
	@RequestMapping(value="/member/member_connectNomal_static_month_list_excel.st")
	public String MemberConnectNomalStaticMonthListExcel(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<HashMap<String, Object>> memberConnectNomalStaticMonthList = null;
		MemberConnectStaticService memberConnectNomalStaticMonthService = new MemberConnectStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
		Calendar calendar = Calendar.getInstance();
		
		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 
		
		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
				
		if (sdate == null || "".equals(sdate)){
			sdate = lastMonth.replace("-", "");
		}
		else{
			sdate = sdate.replace("-", "");
		}
		if (edate == null || "".equals(edate)){
			edate = lastMonth.replace("-", "");
		}
		else{
			edate = edate.replace("-", "");
		}
		
		//��ȸ edate�� ���� ��������¥ ���ϱ�
		int year = new Integer(edate.substring(0, 4));
		int month = new Integer(edate.substring(4, 6))-1;
		int date = new Integer(edate.substring(6,8));
						
		calendar.set(year, month, date);
		
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int differMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer();

		System.out.println("differMonths : " + differMonths);
		
		for(int i=0; i<=differMonths; i++){
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}	
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##
		
		memberConnectNomalStaticMonthList = memberConnectNomalStaticMonthService.selectMemberConnectNomalStaticMonthList(params);  //�����ȸ
		
 		String[] monthList = new String[differMonths];
 		monthList = getMonthList(sdate, differMonths);
		System.out.println(monthList);

		/* SET ATTRIBUTEs */
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("sdate", params.get("sdate").toString().substring(0,4)+"-"+params.get("sdate").toString().substring(4,6) + "-01");
		request.setAttribute("edate", params.get("edate").toString().substring(0,4)+"-"+params.get("edate").toString().substring(4,6) + "-" +endDay);
		request.setAttribute("differMonths", differMonths);
		request.setAttribute("monthList", monthList);
		request.setAttribute("statMonth", params.get("statMonth"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		
		request.setAttribute("memberConnectNomalStaticMonthList", memberConnectNomalStaticMonthList);
		
		params.clear();

		return "member/member_connectNomal_static_month_list_excel";
	}


	/**
	 *  ���� ��� OS �Ϻ�
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception   MemberConnectStatic
	 */
	//@RequestMapping(value="/member/member_connectOS_static_day_list.ms")
	@RequestMapping(value="/member/member_connectOS_static_day_list.st")
	public String MemberConnectOSStaticDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<MemberConnectStatic> memberConnectOSStaticDayList = null;
		MemberConnectStaticService memberConnectStaticService = new MemberConnectStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
				
		memberConnectOSStaticDayList = memberConnectStaticService.selectMemberConnectOSStaticDayList(params);  //--�����ȸ
				
		/* SET ATTRIBUTEs */
		request.setAttribute("memberConnectOSStaticDayList", memberConnectOSStaticDayList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("statDay", params.get("statDay"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		request.setAttribute("android_kt_cnt", params.get("android_kt_cnt"));
		request.setAttribute("android_skt_cnt", params.get("android_skt_cnt"));
		request.setAttribute("android_lgu_cnt", params.get("android_lgu_cnt"));
		request.setAttribute("android_cnt", params.get("android_cnt"));
		request.setAttribute("ios_kt_cnt", params.get("ios_kt_cnt"));
		request.setAttribute("ios_skt_cnt", params.get("ios_skt_cnt"));
		request.setAttribute("ios_lgu_cnt", params.get("ios_lgu_cnt"));
		request.setAttribute("ios_cnt", params.get("ios_cnt"));
		request.setAttribute("total_cnt", params.get("total_cnt"));
		
		params.clear();


		return "member/member_connectOS_static_day_list";
	}
	
	/**
	 *  ���� ��� OS ����
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_connectOS_static_month_list.ms")
	@RequestMapping(value="/member/member_connectOS_static_month_list.st")
	public String MemberConnectOSStaticMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

			List<HashMap<String, Object>> memberConnectNomalStaticMonthList = null;
			MemberConnectStaticService memberConnectOSStaticMonthService = new MemberConnectStaticService();
			HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
			
			params.put("membId", checkStr(request.getParameter("membId"), ""));
			params.put("name", checkStr(request.getParameter("name"), ""));
			params.put("statType", checkStr(request.getParameter("statType"), ""));
			params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1m")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
			params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "monthDate")); 

			String lastMonth = lastMonth();
			String sdate = checkStr(request.getParameter("sdate"), "");
			String edate = checkStr(request.getParameter("edate"), "");
					
			if (sdate == null || "".equals(sdate)){
				sdate = lastMonth.replace("-", "");
			}
			else{
				sdate = sdate.replace("-", "");
			}
			if (edate == null || "".equals(edate)){
				edate = lastMonth.replace("-", "");
			}
			else{
				edate = edate.replace("-", "");
			}
			
			int differMonths = getDiffMonths(sdate, edate);
			StringBuffer subQuery = new StringBuffer();

			System.out.println("differMonths : " + differMonths);

			for(int i=0; i<=differMonths; i++){
				subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
			}	
			
			params.put("subQuery", subQuery.toString());
			params.put("sdate", sdate);
			params.put("edate", edate);

			log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##

			memberConnectNomalStaticMonthList = memberConnectOSStaticMonthService.selectMemberConnectOSStaticMonthList(params);  //�����ȸ
			
	 		String[] monthList = getMonthList(sdate, differMonths);
			
			/* SET ATTRIBUTEs */
			request.setAttribute("membId", params.get("membId"));
			request.setAttribute("name", params.get("name"));
			request.setAttribute("sdate", params.get("sdate"));
			request.setAttribute("edate", params.get("edate"));
			request.setAttribute("differMonths", differMonths);
			request.setAttribute("monthList", monthList);
			
			request.setAttribute("memberConnectNomalStaticMonthList", memberConnectNomalStaticMonthList);
			
			params.clear();

			return "member/member_connectOS_static_month_list";
	}

		/**
		 *  ���� ��� ����/���� �Ϻ�
		 * @param model
		 * @param locale
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
	//@RequestMapping(value="/member/member_connectAge_static_day_list.ms")
	@RequestMapping(value="/member/member_connectAge_static_day_list.st")
	public String MemberConnectAgeStaticDayList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<MemberConnectStatic> memberConnectAgeStaticDayList = null;
		MemberConnectStaticService memberConnectStaticService = new MemberConnectStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("membId", checkStr(request.getParameter("membId"), ""));
		params.put("statDay", checkStr(request.getParameter("statDay"), ""));
		params.put("statType", checkStr(request.getParameter("statType"), ""));
		params.put("name", checkStr(request.getParameter("name"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
		params.put("sdate", checkStr(request.getParameter("sdate"), today));
		params.put("edate", checkStr(request.getParameter("edate"), today));
		}
	
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
	
		memberConnectAgeStaticDayList = memberConnectStaticService.selectMemberConnectAgeCancelStaticDayList(params); //-- �����ȸ
	
		/* SET ATTRIBUTEs */
		request.setAttribute("memberConnectStaticDayList", memberConnectAgeStaticDayList);
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("statDay", params.get("statDay"));
		request.setAttribute("statType", params.get("statType"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		request.setAttribute("android_kt_cnt", params.get("android_kt_cnt"));
		request.setAttribute("android_skt_cnt", params.get("android_skt_cnt"));
		request.setAttribute("android_lgu_cnt", params.get("android_lgu_cnt"));
		request.setAttribute("android_cnt", params.get("android_cnt"));
		request.setAttribute("ios_kt_cnt", params.get("ios_kt_cnt"));
		request.setAttribute("ios_skt_cnt", params.get("ios_skt_cnt"));
		request.setAttribute("ios_lgu_cnt", params.get("ios_lgu_cnt"));
		request.setAttribute("ios_cnt", params.get("ios_cnt"));
		request.setAttribute("total_cnt", params.get("total_cnt"));
	
		params.clear();

		return "member/member_connectAge_static_day_list";
	}

	/**
	 *  ���� ��� ����/���� ����
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value="/member/member_connectAge_static_month_list.ms")
	@RequestMapping(value="/member/member_connectAge_static_month_list.st")
	public String MemberConnectAgeStaticMonthList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<HashMap<String, Object>> statictList = null;
		MemberConnectStaticService memberConnectAgeStaticMonthService = new MemberConnectStaticService();
		HashMap<String, Object> params = new HashMap<String,Object>();//�˻�����
		
		String membId = checkStr(request.getParameter("membId"), ""); 
		String name = checkStr(request.getParameter("name"), ""); 

		String lastMonth = lastMonth();
		String sdate = checkStr(request.getParameter("sdate"), lastMonth.replace("-",  "").substring(0,6)+"01");
		String edate = checkStr(request.getParameter("edate"), lastMonth.replace("-",  "").substring(0,6)+"01");
		edate = "20121201";
		System.out.println("lastMonth : " + lastMonth);
		System.out.println("sdate : " + sdate);
		System.out.println("edate : " + edate);
				
		int diffMonths = getDiffMonths(sdate, edate);
		StringBuffer subQuery = new StringBuffer(); //-- ���� ���̺� �� ������
		System.out.println("diffMonths : " + diffMonths);
		for(int i=0; i<=diffMonths; i++){
			subQuery.append(",SUM(CASE WHEN STAT_MONTH = SUBSTRING( REPLACE(CONVERT(VARCHAR(50),DATEADD(M,+" + i + ",CONVERT(datetime, '" + sdate.substring(0,6) + "'+'01')),120),'-',''),1,6) THEN ISNULL(USER_CNT,0) ELSE 0 END) AS CNT" + (i+1));
		}	
		
		params.put("subQuery", subQuery.toString());
		params.put("sdate", sdate);
		params.put("edate", edate);

		log.debug("@@@@@@@@@@ SettlementList params : "+ params); //##

		statictList = memberConnectAgeStaticMonthService.selectMemberConnectAgeStaticMonthList(params);  //�����ȸ
		
 		String[] monthList = getMonthList(sdate, diffMonths);
		
		/* SET ATTRIBUTEs */
		request.setAttribute("membId", params.get("membId"));
		request.setAttribute("name", params.get("name"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("diffMonths", diffMonths);
		request.setAttribute("monthList", monthList);
		
		request.setAttribute("statictList", statictList);
		
		params.clear();

		
		return "member/member_connectAge_static_month_list";
	}
}
