package com.rocomo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


@Repository
@SuppressWarnings("unchecked")   // �������� �����Ϸ��� ��� ����.. ���� �ϴµ� ��� �޶������� �𸣰ڴ�.
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
