package com.amore.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.amore.domain.UserVO;
import com.amore.domain.Evt_CodeVO;


public class AtListDaoImpl extends SqlMapClientDaoSupport implements ListDao {
	


	
	@Override
	public List<Evt_CodeVO> getSelboxData(String brand) throws Exception 
	{
		System.out.println(" ~~~ [3] getSellboxData ~~~");
		return getSqlMapClient().queryForList( brand + ".getSelboxData", brand);
	}


	@Override
	public List<UserVO> getAllData(HashMap<String, Object> param) throws Exception {
		System.out.println(" ~~~ [4] getAllData ~~~");
		String nameSpace = param.get("brand").toString();		// 브랜드에 맞는 nameSpace를 찾아서 쿼리 조회함
		return getSqlMapClient().queryForList( nameSpace + ".allData", param );
	}
	
	
	@Override
	public List<UserVO> onePageData(HashMap<String, Object> param) throws Exception 
	{
		System.out.println(" ~~~ [1] onePageData ~~~");
		String nameSpace = param.get("brand").toString();		// 브랜드에 맞는 nameSpace를 찾아서 쿼리 조회함
		String Other = param.get("Other").toString();
		String evt_code = param.get("evt_code").toString();
		if ( Other.equals("Other") )
		{
			return getSqlMapClient().queryForList( nameSpace + ".onePageData-Other", param );
		}
		else
		{
			return getSqlMapClient().queryForList( nameSpace + ".onePageData-" + evt_code, param );
		}
		
	}

	@Override
	public int boardMaxCount(HashMap<String, Object> param) throws Exception 
	{
		System.out.println(" ~~~ [2] boardMaxCount ~~~");
		String nameSpace = param.get("brand").toString();		// 브랜드에 맞는 nameSpace를 찾아서 쿼리 조회함
		String Other = param.get("Other").toString();
		String evt_code = param.get("evt_code").toString();
		if ( Other.equals("Other") )
		{
			return (Integer) getSqlMapClient().queryForObject( nameSpace + ".boardMaxCount-Other", param );
		}
		else
		{
			return (Integer) getSqlMapClient().queryForObject( nameSpace + ".boardMaxCount-" + evt_code, param );
		}
	}
	
	
	
	
	
	
	
	/* 
	 * 		[New!!!]	
	 * Old와의 사용시 차이점은, queryForList 사용시, 
	 * 이름뒤에 evt_code를 안붙인다.
	 */
	@Override
	public List<UserVO> new_onePageData(HashMap<String, Object> param) throws Exception 
	{
		System.out.println(" ~~~ [11] oneNewPageData ~~~");
		String nameSpace = param.get("brand").toString();		// 브랜드에 맞는 nameSpace를 찾아서 쿼리 조회함
		String Other = param.get("Other").toString();
		String evt_code = param.get("evt_code").toString();
		if ( Other.equals("Other") )
		{
			return getSqlMapClient().queryForList( nameSpace + ".onePageData-Other", param );
		}
		else
		{
			return getSqlMapClient().queryForList( nameSpace + ".onePageData", param );
		}
		
	}

	@Override
	public int new_boardMaxCount(HashMap<String, Object> param) throws Exception 
	{
		System.out.println(" ~~~ [12] boardNewMaxCount ~~~");
		String nameSpace = param.get("brand").toString();		// 브랜드에 맞는 nameSpace를 찾아서 쿼리 조회함
		String Other = param.get("Other").toString();
		String evt_code = param.get("evt_code").toString();
		if ( Other.equals("Other") )
		{
			return (Integer) getSqlMapClient().queryForObject( nameSpace + ".boardMaxCount-Other", param );
		}
		else
		{
			return (Integer) getSqlMapClient().queryForObject( nameSpace + ".boardMaxCount", param );
		}
	}


}
