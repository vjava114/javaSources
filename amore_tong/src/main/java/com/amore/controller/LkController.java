package com.amore.controller;


import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.amore.service.LkServiceImpl;

@Controller
public class LkController {

	@Inject
	@Named("lkServiceImpl")
	private LkServiceImpl lkServiceImpl;
	
	@RequestMapping(value="/views/lk_list.do")
	public String userList(HttpServletRequest request, HttpServletResponse response)throws Exception{
	
		String result = lkServiceImpl.Execute(request);
		
		if(result.equals("ok")){
			System.out.println("[CTL] ========== lkServiceImpl 성공!! ========== ");
		}
		
		return "/views/"+ request.getParameter("brand") +"_list.jsp";
		
	}
	
}
