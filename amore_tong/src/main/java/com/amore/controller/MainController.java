package com.amore.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amore.domain.Evt_CodeVO;
import com.amore.service.PageServiceImpl;

@Controller
public class MainController {
	
	@Inject
	@Named("pageServiceImpl")
	private PageServiceImpl pageServiceImpl;
	

	@RequestMapping(value="/views/main.do", method=RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String brand = request.getParameter("brand");
		List<Evt_CodeVO>  seldata = pageServiceImpl.getSelectBoxInit(brand);		// 브랜드에 해당하는 evt_code 초기화하여 전달
	
		request.setAttribute("seldata", seldata);
		request.setAttribute("brand", brand);
		
		return "/views/" + brand + "_list.jsp";
	}	

}



