package com.amore.service;

import java.util.HashMap;
import java.util.List;

public interface PageService {
	


	//총 페이지 갯수 구해오기
	public int getTotalPage(HashMap<String, Object> param, int maxrow, int total)throws Exception;
	
	//페이지넘버 구해오기
	public List getPageNumber(int limitPage, int curPage, int totalPage);
	
	//셀렉트박스에 들어갈 값 구해오기
	public List getSelectBoxInit(String brand) throws Exception;
	
	//count(*) 가져오기
	public int getTotalcount(HashMap<String, Object> param) throws Exception;
	
	//한페이지에 들어갈 내용 구해오기
	public List getOnePageData(HashMap<String, Object> param, int maxrow) throws Exception;
	
	
	//count(*) 가져오기
	public int getNewTotalcount(HashMap<String, Object> param) throws Exception;
	
	//한페이지에 들어갈 내용 구해오기
	public List getNewOnePageData(HashMap<String, Object> param, int maxrow) throws Exception;
	
}
