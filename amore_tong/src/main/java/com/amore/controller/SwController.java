package com.amore.controller;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.amore.service.SwServiceImpl;

@Controller
public class SwController {
	
	@Inject
	@Named("swServiceImpl")
	private SwServiceImpl swServiceImpl;

	@RequestMapping(value="/views/sw_list.do")
	public String userList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String result = swServiceImpl.Execute(request);
		
		if(result.equals("ok")){
			System.out.println("[CTL]  ========== swServiceImpl 성공!! ========== ");
		}
		
		return "/views/"+ request.getParameter("brand") +"_list.jsp";
		
	}

}
