package com.amore.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.amore.domain.Evt_CodeVO;
import com.amore.domain.UserVO;

@Service("swServiceImpl")
public class SwServiceImpl implements ListExecuteService {
	
	@Inject
	@Named("pageServiceImpl")
	private PageService pageServiceImpl;

	@Override
	public String Execute(HttpServletRequest request) throws Exception {
		
		
		System.out.println("[SVC] =========== SwServiceImpl.excute() 시작!! ===========");
		
		String sword = request.getParameter("sword");						if( sword ==null || sword.equals("") )	{ sword = null; }
		String s_name = request.getParameter("s_name");						if( s_name == null || s_name.equals("") )	{ s_name = null; }
		String evt_code = request.getParameter("evt_code");					if( evt_code == null || evt_code.equals("") )	{ evt_code = null; }
		String brand = request.getParameter("brand");						if( brand == null || brand.equals("") )	{ brand = null; }	
		String sdate = request.getParameter("sdate");						if( sdate == null || sdate.equals("") )	{ sdate = null; } 
		String edate = request.getParameter("edate");						if( edate == null || edate.equals("") )	{ edate = null; } 
		String strpage = request.getParameter("curpage");					if( strpage == null)	{ strpage = "1";}
		int curPage = Integer.parseInt(strpage);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		int maxrow = 20;
		int limitPage = 10;
//		brand = "sw";		
		
		
		param.put("sword", sword);
		param.put("s_name", s_name);
		param.put("evt_code", evt_code);
		param.put("brand", brand);
		param.put("sdate", sdate);
		param.put("edate", edate);
		param.put("curpage", curPage);
		

		
		String Other="";
		// evt_code 가 brand.xml 파일에 존재하는지에 따라 어떤 쿼리를 수행할지 결정된다. (존재하지 않을경우 Other 쿼리를 수행함)
		if(evt_code.equals("130227")
				||evt_code.equals("130404")
				||evt_code.equals("130131")
				
				||evt_code.equals("130308")
				)
		{
			Other = "";
		}
		else
		{
			Other ="Other";
		}
		param.put("Other", Other);		

		List<Evt_CodeVO>  seldata = pageServiceImpl.getSelectBoxInit(brand);		// 브랜드에 해당하는 evt_code 초기화
		List<UserVO> 	 	 list = pageServiceImpl.getOnePageData(param, maxrow);	// 한페이지에 들어갈 데이터 가져옴
		
		// 페이징
		int				 totalcount = pageServiceImpl.getTotalcount(param);							// count(*)
		int 			totalPage = pageServiceImpl.getTotalPage(param, maxrow, totalcount);		// 모든 페이지의 갯수
		List				 page = pageServiceImpl.getPageNumber(limitPage, curPage, totalPage);	// 하단에 뿌릴 페이지블록 10개
		
		
		
		request.setAttribute("list", list);
		request.setAttribute("brand", brand);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("page", page);			
		request.setAttribute("seldata", seldata);
		request.setAttribute("param", param);
		request.setAttribute("curpage", curPage);
		request.setAttribute("Other", Other);
		
		
		return "ok";
	}

}
