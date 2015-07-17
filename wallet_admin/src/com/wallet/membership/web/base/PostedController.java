/**
 * @author 이경훈
 * @Date 2012-08-14
 * */
package com.wallet.membership.web.base;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.Postcd;
import com.wallet.membership.common.PostcdGetter;

@Controller
public class PostedController extends CommonController {
	private final String PAGE_CODE = "POSTED_POP";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 우편번호 찾기 페이지
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"common/posted"
	 */ 
	@RequestMapping(value="/common/posted_pop.ms")
	public String BulkCouponList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		String Adress = checkStr(request.getParameter("se_dong"), "");
		
		if(!Adress.equals("")){
			List<Postcd> postceList = PostcdGetter.getPostcd(Adress);		
			request.setAttribute("postceList", postceList);
			request.setAttribute("address", Adress);
		}		
		return "common/posted";
	}
	
	
}