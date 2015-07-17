package com.wallet.admin.web.base;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.admin.service.MwAdMenuIdCheckService;
import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;

/*
 * Filename	: MenuIdCheckController.java
 * Class	: com.wallet.admin.web.base.MenuIdCheckController
 * History	: 2012/08/23, psj, 작업구분 : 글 작성시 id 중복체크(카드/상품권/결재)
 * Comment	:
 */
@Controller
public class MenuIdCheckController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");

	/**
	 * 글작성시 - 메뉴별 테이블 id 유무 체크
	 * @param 
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/menu_id_check.ad", method=RequestMethod.POST)
	public String selectMenuIdCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdMenuIdCheckService service = new MwAdMenuIdCheckService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String menu = checkStr(request.getParameter("menu"), "");	//menu
		String id = checkStr(request.getParameter("id"), "");			//id
		String sid = checkStr(request.getParameter("sid"), "");			//id
		String os = checkStr(request.getParameter("os"), "");			//os

		params.put("menu", menu);
		params.put("id", id);
		params.put("sid", sid);
		params.put("os", os);

		//id가 없을경우 강제 Exception 발생
		if("".equals(id)) {
			throw new Exception("id 없습니다. 강제 Exception 발생");
		}
		
		//idYn => Y: 사용가능 , N:사용불가능
		String idYn = service.selectIdCheck(params);
		
		JSONObject jObj = new JSONObject();
		jObj.put("menu", menu);
		jObj.put("idYn", idYn);								//파이여부(Y:존재,N:존재X)
		
		request.setAttribute("JSONObject", jObj);
		
		return "/common/result_page";
		
	}
	
}
