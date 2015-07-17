package com.amore.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.amore.domain.UserVO;
import com.amore.domain.Evt_CodeVO;

@Service("lkServiceImpl")
public class LkServiceImpl implements ListExecuteService {
	
	@Inject
	@Named("pageServiceImpl")
	private PageService pageServiceImpl;
	
	@Override
	public String Execute(HttpServletRequest request) throws Exception {
	
		
		
		System.out.println("[SVC] =========== LkServiceImpl.excute() 시작!! ===========");
		
		String sword = request.getParameter("sword");						if( sword ==null || sword.equals("") )	{ sword = null; }
		String s_name = request.getParameter("s_name");						if( s_name == null || s_name.equals("") )	{ s_name = null; }
		String evt_code = request.getParameter("evt_code");					if( evt_code == null || evt_code.equals("") )	{ evt_code = null; }
		String brand = request.getParameter("brand");						if( brand == null || brand.equals("") )	{ brand = null; }	
		String sdate = request.getParameter("sdate");						if( sdate == null || sdate.equals("") )	{ sdate = null; } 
		String edate = request.getParameter("edate");						if( edate == null || edate.equals("") )	{ edate = null; } 
		String strpage = request.getParameter("curpage");					if( strpage == null)	{ strpage = "1";}
		int curPage = Integer.parseInt(strpage);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		
		
	//	brand = "lk";				// 여기를 해당 브랜드에 맞게 고쳐주세
		int maxrow = 20;
		int limitPage = 10;
		
		
		param.put("sword", sword);
		param.put("s_name", s_name);
		param.put("evt_code", evt_code);
		param.put("brand", brand);
		param.put("sdate", sdate);
		param.put("edate", edate);
		param.put("curpage", curPage);
		
		String Other ="";
		// evt_code 가 brand.xml 파일에 존재하는지에 따라 어떤 쿼리를 수행할지 결정된다. (존재하지 않을경우 Other 쿼리를 수행함)
		if(evt_code.equals("130103")
				||evt_code.equals("130110")
				||evt_code.equals("130109")
				||evt_code.equals("130114")
				||evt_code.equals("130205")
				||evt_code.equals("130206")
				||evt_code.equals("130314")
				
				||evt_code.equals("130101")	// 이건 다행히도 vb_coupon 에 존재함.
				||evt_code.equals("130102")	// amore에 vb_coupon 으로 되어있긴 하지만 실제로 테이블에 130102가 존재하진않음
				||evt_code.equals("130123") // amore에 쿠폰테이블 정보없음			//
				||evt_code.equals("130312") // amore에 쿠폰테이블 정보없음
				

				
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
		
		/* 페이징 */
		int				 totalcount = pageServiceImpl.getTotalcount(param);				// count(*)
		int 			totalPage = pageServiceImpl.getTotalPage(param, maxrow, totalcount);	// 검색조건과 한페이지에 들어갈 maxrow값을 넣고 totalpage를 가져옴
		List				 page = pageServiceImpl.getPageNumber(limitPage, curPage, totalPage);	// 하단페이지 번호들 초기화.
		
		
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
