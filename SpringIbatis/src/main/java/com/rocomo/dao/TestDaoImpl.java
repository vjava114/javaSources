package com.rocomo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


@Repository
@SuppressWarnings("unchecked")   // 부적절한 컴파일러의 경고를 제거.. 라고는 하는데 어떻게 달라지는지 모르겠다.
public class TestDaoImpl implements TestDao
{
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	

	@Override
	public int get_count() throws Exception
	{				
		return (Integer) sqlMapClientTemplate.queryForObject("test.get");
	}

}
