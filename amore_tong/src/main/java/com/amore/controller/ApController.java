package com.amore.controller;


import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amore.service.ApServiceImpl;



@Controller
public class ApController {


	@Inject
	@Named("apServiceImpl")
	private ApServiceImpl apServiceImpl;

	
	@RequestMapping(value="/views/ap_list.do")
	public String userList(HttpServletRequest request, HttpServletResponse response)throws Exception{
	
		
		String result = apServiceImpl.Execute(request);
		
		if(result.equals("ok")){
			System.out.println("[CTL] =========== apServiceImpl 성공!! ===========");
		}
		return "/views/"+ request.getParameter("brand") +"_list.jsp";
	}
}
