package com.wallet.stat.web.base;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.stat.model.MwStCmCode;
import com.wallet.stat.service.MwStCmCodeService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.web.CommonController;

/*
 * Filename	: CmCodeController.java
 * Class	: com.wallet.stat.web.base.CmCodeController
 * History	: 2012/08/23, psj, �۾����� : �ڵ尪 ����
 * Comment	:
 */
@Controller
public class CmCodeController extends CommonController {
	private Logger log = Log.getLogger("logs");

	
	/**
	 * �ڵ尪 ���� ����Ʈ ��ȸ
	 * @return	
	 */
	@RequestMapping(value="/stat/cm_code_list.st")
	public String selectCmCodeList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### CmCodeController selectCmCodeList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<MwStCmCode> list = null;
		MwStCmCodeService service = new MwStCmCodeService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String grp_code = checkStr(request.getParameter("grp_code"), "");
		
		params.put("grp_code", grp_code);
		list = service.selectCmCodeList(params);
		
		
		JSONObject jObj = new JSONObject();
		jObj.put("list", list);
		
		request.setAttribute("JSONObject", jObj);
		
		log.debug("### CmCodeController selectCmCodeList END ###");
		
		return "/common/result_page";
	}
	
	/**
	 * �ڵ尪 ���� ����Ʈ ���
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/stat/cm_code_reg.st")
	public String insertCmCodeReg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### CmCodeController selectComCodeReg START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwStCmCodeService service = new MwStCmCodeService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		int seq_no = 0;
		
		String grp_code = checkStr(request.getParameter("grp_code"), "");
		String com_cd = checkStr(request.getParameter("com_cd"), "");
		String com_cd_val = checkStr(request.getParameter("com_cd_val"), "");
		String com_cd_desc = checkStr(request.getParameter("com_cd_desc"), "");

		try {
			
			if("".equals(grp_code)) {
				throw new Exception("grp_code�� �����ϴ�. ���� Exception �߻�");
			}
			if("".equals(com_cd)) {
				throw new Exception("com_cd �� �����ϴ�. ���� Exception �߻�");
			}
			
			//�ѱ۱��� ó��
			com_cd_desc = URLDecoder.decode(com_cd_desc, "UTF-8");
			
			params.put("grp_code", grp_code);
			params.put("com_cd", com_cd);
			params.put("com_cd_val", com_cd_val);
			params.put("com_cd_desc", com_cd_desc);

			params.put("use_yn", "Y");
			params.put("reg_user", "");
			
			seq_no = service.selectCmCodeSeqNo(params);
			
			log.debug("### seq_no : " + seq_no);
			params.put("seq_no", seq_no);
			
			
			log.debug("### params : " + params);
			
			service.insertCmCodeReg(params);

			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		} 

		log.debug("### CmCodeController selectComCodeReg END ###");
		
		return null;
	}
	
	/**
	 * �ڵ尪 ����
	 * @param 
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/stat/cm_code_delete.st", method=RequestMethod.POST)
	public String deleteCmCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### CmCodeController deleteCmCode START ###");

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		MwStCmCodeService service = new MwStCmCodeService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String grp_code = checkStr(request.getParameter("grp_code"), "");
		String com_cd = checkStr(request.getParameter("com_cd"), "");
		
		try {
			
			params.put("grp_code", grp_code);
			params.put("com_cd", com_cd);

			//grp_code ������� ���� Exception �߻�
			if("".equals(grp_code)) {
				throw new Exception("grp_code�� �����ϴ�. ���� Exception �߻�");
			}
			if("".equals(com_cd)) {
				throw new Exception("com_cd�� �����ϴ�. ���� Exception �߻�");
			}
			
			log.debug("### params : " + params);
			service.deleteCmCode(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### CmCodeController deleteCmCode END ###");
		
		return null;
		
	}
	
	
}
