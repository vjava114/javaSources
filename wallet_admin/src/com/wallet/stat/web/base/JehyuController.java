package com.wallet.stat.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;

/*
 * Filename	: JehyuController.java
 * Class	: com.wallet.stat.web.base.JehyuController
 * History	: 2012/08/23, psj, �۾����� : ���޻� ���� admin
 * Comment	:
 */
@Controller
public class JehyuController extends CommonController {
	private Logger log = Log.getLogger("logs");
	
	/**
	 * ���޻� ���� admin
	 * @return	
	 */
	@RequestMapping(value="/stat/jehyu_list.st")
	public String selectCpaList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### JehyuController selectCpaList ###");
		
		return "/stat/jehyu_list";
	}

	
}
