package com.rocomo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocomo.dao.TestDao;


@Service	
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao dao;
	
	@Override
	public int get_cnt() throws Exception {
		int count = dao.get_count();
		return count;
	}

}
