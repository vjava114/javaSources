package com.amore.controller; 

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



import com.amore.service.AtServiceImpl;

@Controller
public class AtController {
	
	@Inject
	@Named("atServiceImpl")
	private AtServiceImpl atServiceImpl;
	
	@RequestMapping(value="/views/at_list.do")
	public String userList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String result = atServiceImpl.Execute(request);
		if(result.equals("ok")){
			System.out.println("[CTL] =========== atServiceImpl 성공!! =========== ");
		}
		
		return "/views/"+ request.getParameter("brand") +"_list.jsp";
	}
	
}
