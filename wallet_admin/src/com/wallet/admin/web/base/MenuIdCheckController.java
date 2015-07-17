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
 * History	: 2012/08/23, psj, �۾����� : �� �ۼ��� id �ߺ�üũ(ī��/��ǰ��/����)
 * Comment	:
 */
@Controller
public class MenuIdCheckController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");

	/**
	 * ���ۼ��� - �޴��� ���̺� id ���� üũ
	 * @param 
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/menu_id_check.ad", method=RequestMethod.POST)
	public String selectMenuIdCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
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

		//id�� ������� ���� Exception �߻�
		if("".equals(id)) {
			throw new Exception("id �����ϴ�. ���� Exception �߻�");
		}
		
		//idYn => Y: ��밡�� , N:���Ұ���
		String idYn = service.selectIdCheck(params);
		
		JSONObject jObj = new JSONObject();
		jObj.put("menu", menu);
		jObj.put("idYn", idYn);								//���̿���(Y:����,N:����X)
		
		request.setAttribute("JSONObject", jObj);
		
		return "/common/result_page";
		
	}
	
}
