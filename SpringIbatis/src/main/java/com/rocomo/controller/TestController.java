package com.rocomo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rocomo.service.TestService;

@Controller
public class TestController {

	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired	
	private TestService service;
	
	@RequestMapping(value = "/test1.do")
	public String TestContoller() throws Exception{
		System.out.println(" ==== TestController ���� === ");
		
		
		// �׳� ��񿡼� select count(*) from TABLE �Ѱ���.. ��� ������ �� �Ǵ��� Ȯ�θ� �ϱ�����.
		int cnt = service.get_cnt();		
		
		
		System.out.println("count ��� : " + cnt);

		
		return "testJSP";
	}
	
}
