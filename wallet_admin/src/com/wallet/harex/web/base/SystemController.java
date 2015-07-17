package com.wallet.harex.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.harex.common.JString;
import com.wallet.harex.model.SystemM;
import com.wallet.harex.model.SystemStatusList;
import com.wallet.harex.service.SystemService;

@Controller
public class SystemController extends CommonController {

	private Logger log = Log.getLogger("logs");
	
	/**
	 * �ý��۸���͸���Ȳ
	 * @param 	
	 * @param 	
	 * @return		list			�ý��۸���͸���Ȳ
	 */
	@RequestMapping(value="/harex/system_status_info.hx")
	public String selectSystemStatusList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SystemController selectSystemStatusList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<SystemStatusList> list = null;
		SystemService service = new SystemService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		//��ȸ
		list = service.selectSystemStatusList(params);
		request.setAttribute("selectSystemStatusList", list);	
		request.setAttribute("params", params);
		log.debug("### SystemController selectSystemStatusList  END ###");
		
		return "harex/system_status_info";
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ��ȸ
	 * @param 	
	 * @param 	
	 * @return		list			�ý��۸���͸� ȯ�漳�� ��ȸ select
	 */
	@RequestMapping(value="/harex/system_setting.hx")
	public String selectSystemSettingList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SystemController selectSystemSettingList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		int ListCnt = 0;
		List<SystemM> list = null;
		SystemService service = new SystemService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		//����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		try{
			//��ȸ
			list = service.selectSystemSettingList(params);
			ListCnt = service.selectSystemSettingListCnt();
			
		} catch(Exception e){
			log.debug(e.getMessage());
		}
			
		request.setAttribute("List", list);	
		request.setAttribute("params", params);
		
	  //����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
	  // ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		log.debug("### SystemController selectSystemSettingList  END ###");
		
		return "harex/system_setting";
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ����ȸ
	 * @param 	
	 * @param 	
	 * @return		�ý��۸���͸� ȯ�漳�� ����ȸ select
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/system_setting_view.hx")
	public String selectSystemSettingView(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SystemController selectSystemSettingView START ###");
		
		String process_id = checkStr(request.getParameter("process_id"), "");
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		SystemM system = new SystemM();
		SystemService service = new SystemService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("process_id", process_id);
		
		system = service.selectSystemSettingView(params);
		
		/**
	 	 * ��ȣȭ ó��
	 	 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		//1. �޴���
		if(system.getPhoneNoOper() != null){
			if (system.getPhoneNoOper().length() > 14 ) {
				system.setPhoneNoOper(dbCipher.decoding(system.getPhoneNoOper())) ;	
			} else {
				system.setPhoneNoOper(system.getPhoneNoOper()) ;	
			}
		}
		
		//2. �������޴���
		if(system.getRcvPhoneNoOper() != null){
			if (system.getRcvPhoneNoOper().length() > 14 ) {
				system.setRcvPhoneNoOper(dbCipher.decoding(system.getRcvPhoneNoOper())) ;	
			} else {
				system.setRcvPhoneNoOper(system.getRcvPhoneNoOper()) ;	
			}
		}
		
		//3. �������̸���
		if(system.getRcvEmailOper() != null){
			
			JString js = new JString(); 
			// Email ������ ���� Ȯ�� 
			if (js.checkFormat("email",  system.getRcvEmailOper().replace("*", ""))) {
				system.setRcvEmailOper(system.getRcvEmailOper()) ;	
			} else {
				system.setRcvEmailOper(dbCipher.decoding(system.getRcvEmailOper())) ;	
			}
			
			// �̸��� ����ó�� ID �� 3���� '***' ó��
			system.setRcvEmailOper(js.changeEmailTx(system.getRcvEmailOper()));	
		}
		
		//4. ����ڸ�
		if(system.getChargeNameOper() != null){
			if (system.getChargeNameOper().length() > 23 ) {
				system.setChargeNameOper(dbCipher.decoding(system.getChargeNameOper())) ;	
			} else {
				system.setChargeNameOper(system.getChargeNameOper()) ;	
			}
		}
		
		//5. ȸ���ȣ
		if(system.getCompTelOper() != null){
			if (system.getCompTelOper().length() > 23 ) {
				system.setCompTelOper(dbCipher.decoding(system.getCompTelOper()).replace("-", "")) ;	
			} else {
				system.setCompTelOper(system.getCompTelOper().replace("-", "")) ;	
			}
		}
		
		request.setAttribute("result", system);
		request.setAttribute("nowPage", nowPage);
		
		log.debug("### SystemController selectSystemSettingView  END ###");
		
		return "harex/system_setting_view";
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ����
	 * @param 	
	 * @param 	
	 * @return		list			�ý��۸���͸� ȯ�漳�� ���� ȭ��
	 */
	@RequestMapping(value="/harex/system_setting_modify.hx")
	public String selectSystemSettingModify(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SystemController selectSystemSettingModify START ###");
		
		String process_id = checkStr(request.getParameter("process_id"), "");
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		SystemM system = new SystemM();
		SystemService service = new SystemService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("process_id", process_id);
		
		system = service.selectSystemSettingModifyView(params);
		
	 	/**
	 	 * ��ȣȭ ó��
	 	 */
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		//1. �޴���
		if(system.getPhoneNoOper() != null){
			if (system.getPhoneNoOper().length() > 14 ) {
				system.setPhoneNoOper(dbCipher.decoding(system.getPhoneNoOper())) ;	
			} else {
				system.setPhoneNoOper(system.getPhoneNoOper()) ;	
			}
		}
		
		//2. �������޴���
		if(system.getRcvPhoneNoOper() != null){
			if (system.getRcvPhoneNoOper().length() > 14 ) {
				system.setRcvPhoneNoOper(dbCipher.decoding(system.getRcvPhoneNoOper())) ;	
			} else {
				system.setRcvPhoneNoOper(system.getRcvPhoneNoOper()) ;	
			}
		}
		
		//3. �������̸���
		if(system.getRcvEmailOper() != null){
			if (system.getRcvEmailOper().length() > 23 ) {
				system.setRcvEmailOper(dbCipher.decoding(system.getRcvEmailOper())) ;	
			} else {
				system.setRcvEmailOper(system.getRcvEmailOper()) ;	
			}
		}
		
		//4. ����ڸ�
		if(system.getChargeNameOper() != null){
			if (system.getChargeNameOper().length() > 23 ) {
				system.setChargeNameOper(dbCipher.decoding(system.getChargeNameOper())) ;	
			} else {
				system.setChargeNameOper(system.getChargeNameOper()) ;	
			}
		}
		
		//5. ȸ���ȣ
		if(system.getCompTelOper() != null){
			if (system.getCompTelOper().length() > 23 ) {
				system.setCompTelOper(dbCipher.decoding(system.getCompTelOper())) ;	
			} else {
				system.setCompTelOper(system.getCompTelOper()) ;	
			}
		}
		
		request.setAttribute("result", system);
		request.setAttribute("nowPage", nowPage);
		
		log.debug("### SystemController selectSystemSettingModify  END ###");
		return "harex/system_setting_modify";
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ���� ó��
	 * @param 	
	 * @param 	
	 * @return		�ý��۸���͸� ȯ�漳�� ���� ó��
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="/harex/system_setting_modify_proc.hx")
	public String updateSystemSettingModifyProc(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### SystemController updateSystemSettingModifyProc START ###");
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		
		SystemService service = new SystemService();
		HashMap<String, Object> params = new HashMap<String, Object>();
		HashMap<String, Object> result = new HashMap<String, Object>();
		int resultInt = 0;
		
		String process_id          	= checkStr(request.getParameter("process_id"), "");        
		String process_name        = checkStr(request.getParameter("process_name"), "");        
		String if_method           	= checkStr(request.getParameter("if_method"), "");        
		String ssl_yn              		= checkStr(request.getParameter("ssl_yn"), "");        
		String ip                  		= checkStr(request.getParameter("ip"), "");        
		String port                		= checkStr(request.getParameter("port"), "");        
		String http_url            		= checkStr(request.getParameter("http_url"), "");        
		String mon_interval        	= checkStr(request.getParameter("mon_interval"), "");        
		String oper_comp_oper     = checkStr(request.getParameter("oper_comp_oper"), "");        
		String charge_dept_oper   = checkStr(request.getParameter("charge_dept_oper"), "");        
		String charge_name_oper  = checkStr(request.getParameter("charge_name_oper"), "");        
		String phone_no_oper       = checkStr(request.getParameter("phone_no_oper"), "");        
		String comp_tel_oper       	= checkStr(request.getParameter("comp_tel_oper"), "");        
		String msg_oper            	= checkStr(request.getParameter("msg_oper"), "");        
		String sms_yn_oper         	= checkStr(request.getParameter("sms_yn_oper"), "");        
		String rcv_phone_no_oper = checkStr(request.getParameter("rcv_phone_no_oper"), "");        
		String email_yn_oper       	= checkStr(request.getParameter("email_yn_oper"), "");        
		String rcv_email_oper      	= checkStr(request.getParameter("rcv_email_oper"), "");                                    
		String seq_if              		= checkStr(request.getParameter("seq_if"), "");        
		String if_module_name_if   = checkStr(request.getParameter("if_module_name_if"), "");        
		String target_host_name_if = checkStr(request.getParameter("target_host_name_if"), "");        
		String target_ip_if        	= checkStr(request.getParameter("target_ip_if"), "");        
		String target_port_if      	= checkStr(request.getParameter("target_port_if"), "");        
		String if_method_if        	= checkStr(request.getParameter("if_method_if"), "");        
		String ssl_yn_if           		= checkStr(request.getParameter("ssl_yn_if"), "");        
		String reg_dttm_if         	= checkStr(request.getParameter("reg_dttm_if"), "");        
		String last_dttm_if        	= checkStr(request.getParameter("last_dttm_if"), "");        
		
		// ��ȣȭ ó��
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		if(!"".equals(phone_no_oper)){
			phone_no_oper = dbCipher.encoding(phone_no_oper);
		}
		if(!"".equals(rcv_phone_no_oper)){
			rcv_phone_no_oper = dbCipher.encoding(rcv_phone_no_oper);
		}
		if(!"".equals(rcv_email_oper)){
			rcv_email_oper = dbCipher.encoding(rcv_email_oper);
		}
		if(!"".equals(charge_name_oper)){
			charge_name_oper = dbCipher.encoding(charge_name_oper);
		}
		if(!"".equals(comp_tel_oper)){
			comp_tel_oper = dbCipher.encoding(comp_tel_oper);
		}
		
		params.put("process_id",	process_id);          
		params.put("process_name", process_name);        
		params.put("if_method", if_method);           
		params.put("ssl_yn", ssl_yn);              
		params.put("ip", ip);                  
		params.put("port", port);                
		params.put("http_url", http_url);            
		params.put("mon_interval", mon_interval);        
		params.put("oper_comp_oper", oper_comp_oper);      
		params.put("charge_dept_oper", charge_dept_oper);    
		params.put("charge_name_oper", charge_name_oper);    
		params.put("phone_no_oper", phone_no_oper);       
		params.put("comp_tel_oper", comp_tel_oper);       
		params.put("msg_oper", msg_oper);            
		params.put("sms_yn_oper", sms_yn_oper);         
		params.put("rcv_phone_no_oper", rcv_phone_no_oper);   
		params.put("email_yn_oper", email_yn_oper);       
		params.put("rcv_email_oper", rcv_email_oper);      
		params.put("seq_if", seq_if);              
		params.put("if_module_name_if", if_module_name_if);   
		params.put("target_host_name_if", target_host_name_if);
		params.put("target_ip_if", target_ip_if);        
		params.put("target_port_if", target_port_if);      
		params.put("if_method_if", if_method_if);        
		params.put("ssl_yn_if", ssl_yn_if);           
		params.put("reg_dttm_if", reg_dttm_if);         
		params.put("last_dttm_if", last_dttm_if);  
		
		result.put("processId",	process_id);
		request.setAttribute("nowPage", nowPage);
		try{
			resultInt += service.updateSystemSettingSvr(params);
			resultInt += service.updateSystemSettingOper(params);
			resultInt += service.updateSystemSettingIf(params);
			
			if (resultInt == 3) {
				//commit
				service.commit();
				request.setAttribute("targetUrl", "/harex/system_setting.hx?nowPage="+nowPage);
			} else {
				//rollback
				service.rollback();
				request.setAttribute("targetUrl", "/harex/system_setting_modify.hx");
				
			}
			
		} catch(Exception e){
			//rollback
			service.rollback();
			e.printStackTrace();
			log.debug(e);
			request.setAttribute("targetUrl", "/harex/system_setting_modify.hx");
		} finally {
			request.setAttribute("actResult", resultInt + "");
			request.setAttribute("process_id", process_id);
			log.debug("### SystemController updateSystemSettingModifyProc END ###");
			return "common/result_message";
		}
		
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ��� ȭ��
	 * @param 	
	 * @param 	
	 * @return		�ý��۸���͸� ȯ�漳�� ��� ȭ��
	 */
	@RequestMapping(value="/harex/system_setting_regist.hx")
	public String insertSystemSetting(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("### SystemController insertSystemSetting START ###");

		
		log.debug("### SystemController insertSystemSetting END ###");
		return "harex/system_setting_regist";
		
	}
	/**
	 * �ý��۸���͸� ȯ�漳�� ���� ó��
	 * @param 	
	 * @param 	
	 * @return		�ý��۸���͸� ȯ�漳�� ���� ó��
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="/harex/system_setting_regist_proc.hx")
	public String insertSystemSettingProc(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("### SystemController insertSystemSettingProc START ###");
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		
		SystemService service = new SystemService();
		HashMap<String, Object> params = new HashMap<String, Object>();
		int resultInt = 0;
		
		String process_id      		  	= checkStr(request.getParameter("process_id"), "");        
		String host_name           		= checkStr(request.getParameter("host_name"), "");        
		String process_name         	= checkStr(request.getParameter("process_name"), "");        
		String if_method           		= checkStr(request.getParameter("if_method"), "");        
		String ssl_yn              			= checkStr(request.getParameter("ssl_yn"), "");        
		String ip                  			= checkStr(request.getParameter("ip"), "");        
		String port                			= checkStr(request.getParameter("port"), "");        
		String http_url            			= checkStr(request.getParameter("http_url"), "");        
		String mon_interval        		= checkStr(request.getParameter("mon_interval"), "");        
		String oper_comp_oper     		= checkStr(request.getParameter("oper_comp_oper"), "");        
		String charge_dept_oper   		= checkStr(request.getParameter("charge_dept_oper"), "");        
		String charge_name_oper     	= checkStr(request.getParameter("charge_name_oper"), "");        
		String phone_no_oper        	= checkStr(request.getParameter("phone_no_oper"), "");        
		String comp_tel_oper       		= checkStr(request.getParameter("comp_tel_oper"), "");        
		String msg_oper            		= checkStr(request.getParameter("msg_oper"), "");        
		String sms_yn_oper         		= checkStr(request.getParameter("sms_yn_oper"), "");        
		String rcv_phone_no_oper    	= checkStr(request.getParameter("rcv_phone_no_oper"), "");        
		String email_yn_oper       		= checkStr(request.getParameter("email_yn_oper"), "");        
		String rcv_email_oper      		= checkStr(request.getParameter("rcv_email_oper"), "");                                    
		String seq_if              			= checkStr(request.getParameter("seq_if"), "");        
		String if_module_name_if    	= checkStr(request.getParameter("if_module_name_if"), "");        
		String target_host_name_if  	= checkStr(request.getParameter("target_host_name_if"), "");        
		String target_ip_if        		= checkStr(request.getParameter("target_ip_if"), "");        
		String target_port_if      		= checkStr(request.getParameter("target_port_if"), "");        
		String if_method_if        		= checkStr(request.getParameter("if_method_if"), "");        
		String ssl_yn_if           			= checkStr(request.getParameter("ssl_yn_if"), "");        
		String reg_dttm_if         		= checkStr(request.getParameter("reg_dttm_if"), "");        
		String last_dttm_if        		= checkStr(request.getParameter("last_dttm_if"), "");     
		
		// ��ȣȭ ó��
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		if(!"".equals(phone_no_oper)){
			phone_no_oper = dbCipher.encoding(phone_no_oper);
		}
		if(!"".equals(rcv_phone_no_oper)){
			rcv_phone_no_oper = dbCipher.encoding(rcv_phone_no_oper);
		}
		if(!"".equals(rcv_email_oper)){
			rcv_email_oper = dbCipher.encoding(rcv_email_oper);
		}
		if(!"".equals(charge_name_oper)){
			charge_name_oper = dbCipher.encoding(charge_name_oper);
		}
		if(!"".equals(comp_tel_oper)){
			comp_tel_oper = dbCipher.encoding(comp_tel_oper);
		}
		
		params.put("process_id",	process_id);          
		params.put("host_name",   host_name);           
		params.put("process_name", process_name);        
		params.put("if_method", if_method);           
		params.put("ssl_yn", ssl_yn);              
		params.put("ip", ip);                  
		params.put("port", port);                
		params.put("http_url", http_url);            
		params.put("mon_interval", mon_interval);        
		params.put("oper_comp_oper", oper_comp_oper);      
		params.put("charge_dept_oper", charge_dept_oper);    
		params.put("charge_name_oper", charge_name_oper);    
		params.put("phone_no_oper", phone_no_oper);       
		params.put("comp_tel_oper", comp_tel_oper);       
		params.put("msg_oper", msg_oper);            
		params.put("sms_yn_oper", sms_yn_oper);         
		params.put("rcv_phone_no_oper", rcv_phone_no_oper);   
		params.put("email_yn_oper", email_yn_oper);       
		params.put("rcv_email_oper", rcv_email_oper);      
		params.put("seq_if", seq_if);              
		params.put("if_module_name_if", if_module_name_if);   
		params.put("target_host_name_if", target_host_name_if);
		params.put("target_ip_if", target_ip_if);        
		params.put("target_port_if", target_port_if);      
		params.put("if_method_if", if_method_if);        
		params.put("ssl_yn_if", ssl_yn_if);           
		params.put("reg_dttm_if", reg_dttm_if);         
		params.put("last_dttm_if", last_dttm_if);  
		
		request.setAttribute("nowPage", nowPage);
		
		try{
			resultInt += service.insertSystemSettingSvr(params);
			resultInt += service.insertSystemSettingOper(params);
			resultInt += service.insertSystemSettingIf(params);
			
			if (resultInt == 3) {
				//commit
				service.commit();
				request.setAttribute("targetUrl", "/harex/system_setting.hx");
			} else {
				//rollback
				service.rollback();
				request.setAttribute("targetUrl", "/harex/system_setting_regist.hx");
			}
			
			
		} catch(Exception e){
			//rollback
			service.rollback();
			e.printStackTrace();
			log.debug(e);
			request.setAttribute("targetUrl", "/harex/system_setting_regist.hx");
		} finally {
			request.setAttribute("actResult", resultInt + "");
			request.setAttribute("process_id", process_id);
			log.debug("### SystemController insertSystemSettingProc END ###");
			return "common/result_message";
		}
		
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ��Ͻ� ���μ��� ID �ߺ�Ȯ��
	 * @param 	
	 * @param 	
	 * @return		�ý��۸���͸� ȯ�漳�� ��Ͻ� ���μ��� ID �ߺ�Ȯ��
	 */
	@RequestMapping(value="/harex/system_setting_check_processId.hx")
	public String selectSystemSettingCheckProcessId(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("### SystemController selectSystemSettingCheckProcessId START ###");
		String process_id = checkStr(request.getParameter("process_id"), "");
		
		SystemService service = new SystemService();
		JSONObject jObj = new JSONObject();
		String result = "";
		
		result = service.selectCheckProcessIdYn(process_id);
		
		jObj.put("result", result);
		
		log.debug("### SystemController selectSystemSettingCheckProcessId END ###");
		
		/* SET ATTRIBUTEs */
		request.setAttribute("result", result);
		request.setAttribute("JSONObject", jObj);

		return "/common/result_page";
	}
}
