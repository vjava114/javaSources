package com.amore.controller;


import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amore.service.PmServiceImpl;


@Controller
public class PmController {

	@Inject
	@Named("pmServiceImpl")
	private PmServiceImpl pmServiceImpl;
	
	@RequestMapping(value="/views/pm_list.do")
	public String userList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String result = pmServiceImpl.Execute(request);
		
		if(result.equals("ok")){
			System.out.println("[CTL] ========== pmServiceImpl 성공!! ========== ");
		}
		
		return "/views/"+ request.getParameter("brand") +"_list.jsp";
		
	}
	
}
