package com.amore.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amore.dao.ListDao;
import com.amore.domain.UserVO;
import com.amore.service.PageService;



@Controller
public class ExcelController {

	@Inject
	@Named("listDao")
	private ListDao listDao;
	
	@Inject
	@Named("pageServiceImpl")
	private PageService pageServiceImpl;

	@RequestMapping(value="/views/excel.do")
	public String userList(HttpServletRequest request, HttpServletResponse response)throws Exception{
	
		// 검색 조건 select.. 이름, 전화번호, 샵코드, 샵명
		String sword = request.getParameter("sword");						if( sword ==null || sword.equals("") )	{ sword = null; }
		// 검색 조건의 실제 value값
		String s_name = request.getParameter("s_name");						if( s_name == null || s_name.equals("") )	{ s_name = null; }
		//evt_code
		String evt_code = request.getParameter("evt_code");					if( evt_code == null || evt_code.equals("") )	{ evt_code = null; }
		// BRAND 선택.
		String brand = request.getParameter("brand");						if( brand == null || brand.equals("") )	{ brand = null; }	
		// sdate 
		String sdate = request.getParameter("sdate");						if( sdate == null || sdate.equals("") )	{ sdate = null; } 
		// edate 
		String edate = request.getParameter("edate");						if( edate == null || edate.equals("") )	{ edate = null; } 
		
		String Other = request.getParameter("Other");						
		String userTb = request.getParameter("userTb");						if( userTb == null || userTb.equals("") )	{ userTb = null; }
		String userCol = request.getParameter("userCol");					if( userCol == null || userCol.equals("") )	{ userCol = null; }
		String couponTb = request.getParameter("couponTb");					if( couponTb == null || couponTb.equals("") )	{ couponTb = null; }
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		param.put("sword", sword);
		param.put("s_name", s_name);
		param.put("evt_code", evt_code);
		param.put("brand", brand);
		param.put("sdate", sdate);
		param.put("edate", edate);
		param.put("Other", Other);
		param.put("userTb", userTb);
		param.put("userCol", userCol);
		param.put("couponTb", couponTb);
		

		
		List<UserVO> list = null;
		int count = 0;
		
		if ( userTb == null && userCol == null )
		{
			System.out.println("[old 버전 엑셀 출력] : " + param.toString());
			list = listDao.onePageData(param);
			
		}
		else
		{
			System.out.println("[new 버전 엑셀 출력] : " + param.toString());
			list = listDao.new_onePageData(param);
		}
			
		count= list.size();
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		
		return "/views/excelDown.jsp";
	}
	
}
