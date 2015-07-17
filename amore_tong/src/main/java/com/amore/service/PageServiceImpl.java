package com.amore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import com.amore.dao.ListDao;
import com.amore.dao.AtListDaoImpl;
import com.amore.domain.UserVO;
import com.amore.domain.Evt_CodeVO;


@Service("pageServiceImpl")
public class PageServiceImpl implements PageService {
	
	@Inject
	@Named("listDao")
	private ListDao listDao;
	

	
	@Override
	public int getTotalPage(HashMap<String, Object> param, int maxrow, int totalcount) throws Exception {
		
		
		int totalpage = (int) Math.ceil((double) totalcount/ maxrow);
		return totalpage;
	}

	@Override
	public List getPageNumber(int limitPage, int curPage, int totalPage) {
		int block = (int) Math.ceil( (double)curPage / limitPage );		// 몇번째 블록인지?? 	ex: 35 / 10 = 4
		int startPage =  ( (block-1) * limitPage )  + 1;				// 블럭의 시작번호	 	ex: 4 * 10 + 1 = 41
		int endPage = startPage +  limitPage - 1;						// 블럭의 끝나는 번호	ex: 41 + 10 - 1 = 50
		
		List pageNumbers = new ArrayList();
		for(int i = startPage; i <= endPage; i++)						// ex: 41 ~ 50
		{		
			if(i>totalPage)
			{
				break;
			}
			pageNumbers.add(i);											//  ex : [ 41, 42, 43, 44, 45, 46, 47, 48, 49, 50]
		}
		
		return pageNumbers;
	}

	
	@Override
	public List<Evt_CodeVO> getSelectBoxInit(String brand) throws Exception {
		List<Evt_CodeVO> list = listDao.getSelboxData(brand);
		return list;
	}
	
	
	@Override
	public int getTotalcount(HashMap<String, Object> param) throws Exception
	{
		int total = (int)listDao.boardMaxCount(param);
		return total;
		
	}
	
	@Override
	public List getOnePageData(HashMap<String, Object> param, int maxrow) throws Exception
	{
		
		int curpage = (Integer) param.get("curpage");
	
		int startNo = (curpage-1)*maxrow;		  
		int endNo = maxrow;			
		
		param.put("startNo", startNo);		// ex: startNo = 0
		param.put("endNo", endNo);			// ex: endNo = 20
		
		System.out.println("PageServiceImpl.getOnePageData : " + param.toString());
		
		List<UserVO> list = listDao.onePageData(param);
		System.out.println("getOnePageData 완료!!");
		return list;
	}
	

	
	
	
	/* 
	 * 		[New!!!]
	 * Old와 New 의 차이점?
	 * AtListDao 클래스에서 찾는 메소드들이 다르다.   new_boardMaxCount, new_onePageData	
	 */
	
	@Override
	public int getNewTotalcount(HashMap<String, Object> param) throws Exception
	{
		int total = (int)listDao.new_boardMaxCount(param);
		return total;
		
	}
	
	@Override
	public List getNewOnePageData(HashMap<String, Object> param, int maxrow) throws Exception
	{
		
		int curpage = (Integer) param.get("curpage");
	
		int startNo = (curpage-1)*maxrow;		  
		int endNo = maxrow;			
		
		param.put("startNo", startNo);		// ex: startNo = 0
		param.put("endNo", endNo);			// ex: endNo = 20
		
		System.out.println("PageServiceImpl.getOnePageData : " + param.toString());
		
		List<UserVO> list = listDao.new_onePageData(param);
		System.out.println("getOnePageData 완료!!");
		return list;
	}


		

}
