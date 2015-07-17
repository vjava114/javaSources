package com.wallet.membership.web.base;
/**
 * 
 */


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.ExcelContoller;
import com.wallet.membership.common.UpFile;
import com.wallet.membership.model.custom.Pos;
import com.wallet.membership.service.custom.PosService;

@Controller
public class PosController extends CommonController {
	private final String PAGE_CODE = "POS_LIST";
	private Logger log = Log.getLogger("logs");
	private MwAdAccessLogService logSVC = new MwAdAccessLogService();
	KTDBCipher ktService = new KTDBCipher();
	
	/**
	 * @Method Name : PosList
	 * @Description : POS �˻�/���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@RequestMapping(value="/member/pos_list.ms")
	public String PosList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Pos> posList = null;
		PosService posService = new PosService();
		//HashMap<String, Object> params = new HashMap<String, Object>((Map) request.getParameterMap());
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("ra_allyStat", checkStr(request.getParameter("ra_allyStat"), "")); //-- ����¿� ���� �⺻�� ����
		params.put("seShpCompName", checkStr(request.getParameter("seShpCompName"), "")); 
		params.put("seCmpCompName", checkStr(request.getParameter("seCmpCompName"), ""));
		params.put("seBrdCompName", checkStr(request.getParameter("seBrdCompName"), ""));
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int posListCnt = posService.selectPosListCnt(params); //-- �� ��� ��
		Paging page = new Paging();
		page.makeWebPaging(nowPage, posListCnt, rowsPerPage);
		
		if(page.getNowPage()>1){
			nowPage =  page.getNowPage();
			request.setAttribute("nowPage", nowPage);
			
		}else{
			nowPage = 1;
			request.setAttribute("nowPage", "1");
		}
		
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		log.debug("@@@@@@@@@@ PosList params : "+ params); //##
		
		posList = posService.selectPosList(params); //-- �����ȸ
		for(int i=0; i<posList.size(); i++){
			String manageNm = posList.get(i).getManagerName();
			String phone = posList.get(i).getPhoneNo();
			
			manageNm = ktService.decoding(manageNm);
			phone = ktService.decoding(phone);
			
			posList.get(i).setManagerName(manageNm);
			posList.get(i).setPhoneNo(phone);
		}
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("posList", posList);
		request.setAttribute("seCmpCompName", params.get("seCmpCompName"));
		request.setAttribute("seShpCompName", params.get("seShpCompName"));
		request.setAttribute("seBrdCompName", params.get("seBrdCompName"));
		request.setAttribute("ra_allyStat", params.get("ra_allyStat"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt",  params.get("se_termOpt"));
		
		/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
		params.put("pageURL", "/member/pos_list.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB�� ����� E #####################*/

		
		params.clear();
		return "member/pos_list";
	}
	
	
	/**
	 * @Method Name : PosRegister
	 * @Description : POS ���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@RequestMapping(value="/member/pos_register.ms")
	public String PosRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "member/pos_register";
	}
	

	
	/**
	 * @Method Name : PosRegisterAct
	 * @Description : POS ��� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@RequestMapping(value="/member/pos_registerAct.ms")
	public String PosRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int num = 1;
		try{
			PosService posService = new PosService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			
			String regUser = getSessionMgrId(request);
			//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)
			params.put("regUser", regUser); //-- �����ID
			params.put("shpCompId",  checkStr(request.getParameter("shpCompId"), ""));
			
			String posDetCode[] = request.getParameterValues("posDetCode");
			String memo[] = request.getParameterValues("memo");
			for (int i=0; i<posDetCode.length; i++){
				String tmpPosDetCode = checkStr(posDetCode[i], "");
				String tmpMemo = checkStr(memo[i], "");
				params.put("memo",  tmpMemo);
				if(!"".equals(tmpPosDetCode)){
					params.put("posDetCode", tmpPosDetCode);
					
					log.debug("@@@@@@@@@@ PosRegisterAct params : "+ params); //##
					result = posService.insertPos(params);
					System.out.println(result);
					num = num*result;
				}
			}
			request.setAttribute("actResult", result + "");	
			if(num == 0) {
				posService.rollback();
				request.setAttribute("targetUrl", "/member/pos_register.ms");
			}else{
				posService.commit();
				request.setAttribute("targetUrl", "/member/pos_list.ms");
				
			}
			/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
			params.put("pageURL", "/member/pos_registerAct.ms.ms");
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
			
			params.clear();
		}
		catch(Exception e){
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/pos_register.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : PosFRegisterAct
	 * @Description : POS �ϰ����.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.14
	 */
	@RequestMapping(value="/member/pos_fregisterAct.ms")
	public String PosFRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		
		PosService posService = new PosService();
		UpFile upFile = null;
		try{
			HashMap<String, Object> params = new HashMap<String,Object>();
			String shpCompId = request.getParameter("shpCompId");
			String regUser = getSessionMgrId(request);
			int count = 0;
			upFile = new UpFile(request, "/excelfile/");//���� ���ε�
			/* 
			 * */
			ExcelContoller excels = new ExcelContoller(upFile.getFiles(),4);//���� ���� �Ľ�
			excels.setSheet(0);//���������� �ִ� ��Ʈ

			if(excels.ChkTitle("������_POS ����")){
							
				for(int i=0;true;i++){
					String[] str = excels.getString(i);
					if(str[0].equals("false")){
		    			break;
		    		}
					count++;
				}
			}

		    if(excels.ChkTitle("������_POS ����")){//������ Ÿ��Ʋ�� üũ
		    	
		    	String[] pos = new String[count];
		    	for(int i=0; i<count;i++){
		    		String[] str = excels.getString(i);
		    		
		    		
		    		if(str[0].equals("false")){
		    			break;
		    		}
		    		
		    		System.out.println(str[i].toString());
		    		if(!shpCompId.equals(str[2])){
		    			log.info("������ ID�� �ٸ��ϴ�.");
		    			upFile.getFiles().delete();//���н� ���� ����
		    			posService.rollback();
		    			request.setAttribute("actResult", "0");
		    			request.setAttribute("targetUrl", "/member/pos_register.ms");
		    			break;
		    		}
		    		
		    		params.put("shpCompId", str[2]);
		    		params.put("posDetCode", str[3]);
		    		pos[i] = str[3]; //request.getParameter("posDetCode");
		    		System.out.println(pos[i].toString());
		    		params.put("memo", str[4]);
		         	params.put("regUser", regUser);
		    		for(int j=0; j<i; j++){
		    			if(i>0){
			    			if(pos[j].equals(pos[i])){
			    				log.info("POS ID�� �ߺ��ƽ��ϴ�.");
					    		upFile.getFiles().delete();//���н� ���� ����
					    		posService.rollback();
					    		request.setAttribute("actResult", "0");
					    		request.setAttribute("targetUrl", "/member/pos_register.ms");
					    		break;
			    			}else{
			    				log.debug("@@@@@@@@@@ PosFRegisterAct params : "+ params);
					         	result=posService.insertPos(params);
								/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
								params.put("pageURL", "/member/pos_fregisterAct.ms");
							
								params.put("part", "MEMBERSHIP");
								params.put("admin_id", getSessionMgrId(request));
								params.put("ip", request.getRemoteAddr());
								params.put("msg", params.toString());
								
								logSVC.insertAccessLogReg(params);
								logSVC.commit();
								/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
					         	request.setAttribute("actResult", result + "");
					         	params.clear();
			    			}
		    			}
		    		}
		        }
		    	posService.commit();
		    	request.setAttribute("targetUrl", "/member/pos_list.ms");
			}else{
		    	upFile.getFiles().delete();//���н� ���� ����
		    	posService.rollback();
		    }
		}catch (Exception e) {
			// TODO: handle exception
			log.info("������ �����ϴ�.");
			upFile.getFiles().delete();//���н� ���� ����
			posService.rollback();
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/pos_register.ms");
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}

	
	/**
	 * @Method Name : PosEditor
	 * @Description : POS ����ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@RequestMapping(value="/member/pos_editor.ms")
	public String PosEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		
		PosService posService = new PosService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		params.put("posId", checkStr(request.getParameter("posId"), "")); //--���õ� POS ID
		
		log.debug("@@@@@@@@@@ PosEditor params : "+ params); //##
		Pos aPos = posService.selectAPos(params);	//-- �����ȸ
		/*##################### ��ȣȭ S ######################*/
		String manageNm = ktService.decoding(aPos.getManagerName());
		String phoneNo = ktService.decoding(aPos.getPhoneNo());
		String mobilePhone = ktService.decoding(aPos.getMobilePhone());
		String email = ktService.decoding(aPos.getEmail());
		
		aPos.setManagerName(manageNm);
		aPos.setPhoneNo(phoneNo);
		aPos.setMobilePhone(mobilePhone);
		aPos.setEmail(email);
		/*##################### ��ȣȭ E #######################*/
		
		request.setAttribute("aPos", aPos);
		
		/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
		params.put("pageURL", "/member/pos_editor.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
		params.clear();
		return "member/pos_editor";
	}
	
	
	/**
	 * @Method Name : PosEditorAct
	 * @Description : POS�� ���� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@RequestMapping(value="/member/pos_editorAct.ms")
	public String PosEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		
		try{
			PosService posService = new PosService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			
			String chgUser = getSessionMgrId(request);
			
			//-- ���� ó���� ���� ����� ���� setting �ϱ� (�����߰�)
			params.put("chgUser", chgUser);
			params.put("posId",  checkStr(request.getParameter("posId"), ""));
			params.put("memo",  checkStr(request.getParameter("memo"), ""));
			params.put("posDetCode",  checkStr(request.getParameter("posDetCode"), ""));
			

			log.debug("@@@@@@@@@@ PosEditorAct params : "+ params); //##
			
			result = posService.updatePos(params);
			posService.commit();
			request.setAttribute("actResult", result + "");
			
		
			if(result == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/member/pos_editor.ms");
			}
			else{
				request.setAttribute("targetUrl", "/member/pos_list.ms");
			}
			/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
			params.put("pageURL", "/member/pos_editor.ms");
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
			
		}
		catch(Exception e){
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/pos_editor.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	
	/**
	 * @Method Name : PosDeleteAct
	 * @Description : POS �����Ѵ�.(��������)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@RequestMapping(value="/member/pos_deleteAct.ms")
	public String PosDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		
		try{
			PosService posService = new PosService();
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("posId",  checkStr(request.getParameter("posId"), ""));

			log.debug("@@@@@@@@@@ PosDeleteAct params : "+ params); //##
			
			result = posService.deletePos(params);
			posService.commit();
			request.setAttribute("actResult", result + "");
			
			if(result == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/member/pos_editor.ms");
			}
			else{
				request.setAttribute("targetUrl", "/member/pos_list.ms");
			}
			/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
			params.put("pageURL", "/member/pos_editor.ms");
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
		}
		catch(Exception e){
			request.setAttribute("actResult", result + "");
			request.setAttribute("targetUrl", "/member/pos_editor.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
		
	/**
	 * @Method Name : today
	 * @Description : ���� ��¥�� ��ȸ�Ѵ�.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2010.04.23
	 */
	public static String today() {
		java.sql.Date CurrDate = new java.sql.Date((new java.util.Date()).getTime());
		return CurrDate.toString();
	}
}
