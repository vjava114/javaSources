package com.amore.controller;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amore.service.HeraServiceImpl;

@Controller
public class HeraController {

	@Inject
	@Named("heraServiceImpl")
	private HeraServiceImpl heraServiceImpl;
	
	@RequestMapping(value="/views/hera_list.do")
	public String userList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String brand = request.getParameter("brand");
		//brand = "hera";
		String result = heraServiceImpl.Execute(request);
		
		if(result.equals("ok")){
			System.out.println("[CTL] =========== " + brand + "ServiceImpl 성공!! =========== ");
		}
		
		return "/views/hera_list.jsp";
	}
}
