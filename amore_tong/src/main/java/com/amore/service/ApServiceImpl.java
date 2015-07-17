package com.amore.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.amore.domain.UserVO;
import com.amore.domain.Evt_CodeVO;

@Service("apServiceImpl")
public class ApServiceImpl implements ListExecuteService {
	
	@Inject
	@Named("pageServiceImpl")
	private PageService pageServiceImpl;
	
	@Override
	public String Execute(HttpServletRequest request) throws Exception {
	
		
		System.out.println("[SVC] =========== ApServiceImpl.excute() 시작!! ===========");
		
		String sword = request.getParameter("sword");						if( sword ==null || sword.equals("") )	{ sword = null; }
		String s_name = request.getParameter("s_name");						if( s_name == null || s_name.equals("") )	{ s_name = null; }
		String evt_code = request.getParameter("evt_code");					if( evt_code == null || evt_code.equals("") )	{ evt_code = null; }
		String brand = request.getParameter("brand");						if( brand == null || brand.equals("") )	{ brand = null; }	
		String sdate = request.getParameter("sdate");						if( sdate == null || sdate.equals("") )	{ sdate = null; } 
		String edate = request.getParameter("edate");						if( edate == null || edate.equals("") )	{ edate = null; } 
		String strpage = request.getParameter("curpage");					if( strpage == null)	{ strpage = "1";}
		int curPage = Integer.parseInt(strpage);
		
		
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		
		
		
	//	brand = "ap";				// 여기를 해당 브랜드에 맞게 고쳐주세
		int maxrow = 20;
		int limitPage = 10;
		
		
		
		String Other = "";
		if(evt_code.equals("130216")
				||evt_code.equals("130301")
				||evt_code.equals("130401")
				||evt_code.equals("130102")
				||evt_code.equals("130118")
				
				||evt_code.equals("130208")		// 130208 은 amore에 쿠폰테이블 정보가 존재하지 않음
				)
		{
			Other = "";
		}
		else
		{
			Other ="Other";
		}
		param.put("Other", Other);
		
		param.put("sword", sword);
		param.put("s_name", s_name);
		param.put("evt_code", evt_code);
		param.put("brand", brand);
		param.put("sdate", sdate);
		param.put("edate", edate);
		param.put("curpage", curPage);
		
		
		
		
		
		List<UserVO> 	 	 list = pageServiceImpl.getOnePageData(param, maxrow);	// 한페이지에 들어갈 데이터 가져옴
		
		/* 페이징 */
		int				 totalcount = pageServiceImpl.getTotalcount(param);				// count(*)
		int 			totalPage = pageServiceImpl.getTotalPage(param, maxrow, totalcount);	// 검색조건과 한페이지에 들어갈 maxrow값을 넣고 totalpage를 가져옴
		List				 page = pageServiceImpl.getPageNumber(limitPage, curPage, totalPage);	// 하단페이지 번호들 초기화.

		List<Evt_CodeVO>  seldata = pageServiceImpl.getSelectBoxInit(brand);		// 브랜드에 해당하는 evt_code 초기화
		
		
		request.setAttribute("list", list);
		request.setAttribute("brand", brand);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("page", page);			
		request.setAttribute("seldata", seldata);
		request.setAttribute("curpage", curPage);
		request.setAttribute("param", param);
		request.setAttribute("Other", Other);
	
		
		return "ok";
	}

}
