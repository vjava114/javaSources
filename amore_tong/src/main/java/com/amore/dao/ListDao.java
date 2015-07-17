package com.amore.dao;

import java.util.HashMap;
import java.util.List;

import com.amore.domain.Evt_CodeVO;
import com.amore.domain.UserVO;

public interface ListDao {
	

	
	// 셀렉트박스에 들어갈 데이터들을 가져온다.
	public List<Evt_CodeVO> getSelboxData(String brand) throws Exception;
	
	// 엑셀로 데이터 출력을 위해 모든 데이터를 가져온다.
	public List<UserVO> getAllData(HashMap<String, Object> param) throws Exception;

	//  param [ sname,   evt_code, 		brand,	 	sdate,	 	edate,	 	startNo,  	endNo ]
	public List<UserVO> onePageData(HashMap<String, Object> param) throws Exception;
	
	//  param [ sname,   evt_code, 		brand,	 	sdate,	 	edate	]
	public int boardMaxCount(HashMap<String, Object> param) throws Exception;
	
	
	
	
	
	/*    						New     							 */
	
	//  param [ sname,   evt_code, 		brand,	 	sdate,	 	edate,	 	startNo,  	endNo ]
	public List<UserVO> new_onePageData(HashMap<String, Object> param) throws Exception;
	
	//  param [ sname,   evt_code, 		brand,	 	sdate,	 	edate	]
	public int new_boardMaxCount(HashMap<String, Object> param) throws Exception;
}
