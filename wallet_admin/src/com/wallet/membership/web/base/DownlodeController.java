package com.wallet.membership.web.base;

import java.io.File;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;

@Controller
public class DownlodeController extends CommonController implements ApplicationContextAware{
	private Logger log = Log.getLogger("logs");
	private WebApplicationContext context = null;
  
  @RequestMapping("member/download.ms")
  public ModelAndView download(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response){
  	String path = checkStr(request.getParameter("path"));
  	String fileName =checkStr(request.getParameter("fileName"));
  	 	
  	System.out.println(request.getParameter("fileName"));
  	  		
      //String fullPath = path + "\\" + fileName;
      String fullPath = path + fileName;
      String RealPath = request.getSession().getServletContext().getRealPath(fullPath);
      System.out.println("path : " + RealPath);
      File file = new File(RealPath);
      
      return new ModelAndView("download", "downloadFile", file);
  }
  
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		this.context = (WebApplicationContext)arg0;
	}
}
