package com.amore.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	
	@RequestMapping(value="/index.go")
	public String loginPage(HttpServletRequest request){
		return "/index.jsp";
	}
	
	@RequestMapping(value="/login.go")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		String result ="false";
		
		if (id.equals("rocomo") || id.equals("kang"))
		{
			if( pw.equals("rcm123"))
			{
				result = "ok";
			}
		}
		
		
		if (result.equals("ok"))
		{
			request.getSession().setAttribute("id", id);
			response.sendRedirect("views/main.jsp");
		}
		else
		{
			response.sendRedirect("../index.jsp?loginOk=fail");	// 로그인 실패
		}
		
	}
	
	@RequestMapping(value="/logout.go")
	public String logout(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String id = request.getParameter("id");
		System.out.println("로그아웃 아이디 확인" + id);
		request.getSession().invalidate();
		
		return "/index.jsp";
	}
	


}
