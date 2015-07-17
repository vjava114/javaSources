package com.amore.controller;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amore.service.HrhServiceImpl;

@Controller
public class HrhController {

	@Inject
	@Named("hrhServiceImpl")
	private HrhServiceImpl hrhServiceImpl;
	
	@RequestMapping(value="/views/hrh_list.do")
	public String userList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String brand = request.getParameter("brand");
		String result = hrhServiceImpl.Execute(request);
		
		if(result.equals("ok")){
			System.out.println("[CTL] =========== " + brand + "ServiceImpl 성공!! =========== ");
		}
		
		return "/views/ap_list.jsp";
	}
}
