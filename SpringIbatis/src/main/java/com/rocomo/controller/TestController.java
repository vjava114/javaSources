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
		System.out.println(" ==== TestController 실행 === ");
		
		
		// 그냥 디비에서 select count(*) from TABLE 한거임.. 디비 연동이 잘 되는지 확인만 하기위함.
		int cnt = service.get_cnt();		
		
		
		System.out.println("count 출력 : " + cnt);

		
		return "testJSP";
	}
	
}
