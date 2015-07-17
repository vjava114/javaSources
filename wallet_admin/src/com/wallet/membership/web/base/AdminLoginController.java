/**
 * @author �̰���
 * @memo �������� ��� �� ���� ���� �����Ѵ�.
 * */
package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.cipher.Sha256Cipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.DateTime;
import com.wallet.membership.model.MwCmAdminInfo;
import com.wallet.membership.model.MwCmAdminInfoExample;
import com.wallet.membership.service.MwCmAdminInfoService;
import com.wallet.admin.service.MwAdAccessLogService;

@Controller
public class AdminLoginController extends CommonController{
	
	private MwAdAccessLogService logSVC = new MwAdAccessLogService();
	KTDBCipher ktService = new KTDBCipher();
	Sha256Cipher shaCipher = new Sha256Cipher();

	/**
	 * ��ü���� - ������ ��ϰ��� ����Ʈ
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/bulk_coupon_list"
	 */ 
	@RequestMapping(value="/member/admin_login_list.ms")
	public String AdminList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
			MwCmAdminInfoService mwCmAdminInfoService = new MwCmAdminInfoService();
			/*�䱸����
			 * ������ ���� ���� �˻�
			 * ���޻� ������ �˻�
			 * ������ ������ �˻�
			 * �Ⱓ �˻�
			 * 
			 * ����Ʈ : �������� ���޻� �귣�� ������ �α��� ���̵� ����� ������
			 * */
			String nowPage = checkStr(request.getParameter("nowPage"), "1");
			String rowsPerPage = checkStr(request.getParameter("rowsPerPage"), "15");
			String adminType = checkStr(request.getParameter("adminType"), "01");
			String compName = checkStr(request.getParameter("compName"), "");
			String shopName = checkStr(request.getParameter("shopName"), "");
			String Sdate = checkStr(request.getParameter("sdate"), DateTime.format("yyyy-MM-dd"));
			String Edate = checkStr(request.getParameter("edate"), DateTime.format("yyyy-MM-dd"));
			String partnerTerm = checkStr(request.getParameter("partnerTerm"), "01");
			
			if(partnerTerm.equals("04")){
				Sdate = "";
				Edate = "";
			}
			
			int page = Integer.parseInt(nowPage);
			int row = Integer.parseInt(rowsPerPage);
			int count = 0;
			
			Log.info("==========================================");
			Log.info("=                                      �˻�����                                        =");
			Log.info("==========================================");
			Log.info("�������� : " + adminType);
			Log.info("���޻�� : " + compName);
			Log.info("�������� : " + shopName);
			Log.info("���۳�¥ : " + Sdate);
			Log.info("���ᳯ¥ : " + Edate);
			Log.info("��¥Ÿ�� : " + partnerTerm);
			Log.info("==========================================");
			
			List<MwCmAdminInfo>  mwCmAdminInfo = null;
			try{
				MwCmAdminInfoExample mwCmAdminInfoExample = new MwCmAdminInfoExample();
				mwCmAdminInfoExample.setOrderByClause("A.REG_DTM DESC, B.COMP_NAME DESC");
				if(compName != null && !compName.equals("")){
					mwCmAdminInfoExample.or().andACompNameLike("%"+compName+"%");
				}
				if(shopName != null && !shopName.equals("")){
					mwCmAdminInfoExample.or().andASHPDtmLike("%"+shopName+"%");
				}
				
				mwCmAdminInfoExample.or().andAdminLevelEqualTo(adminType);
				
				if(Sdate != null&&!Sdate.equals("")&&Edate != null&&!Edate.equals("")){
					mwCmAdminInfoExample.or().					
					andARegDtmBetween(DateTime.parse(Sdate, "yyyy-MM-dd"), new Date(DateTime.parse(Edate, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24)));
				}				
					
				mwCmAdminInfo = mwCmAdminInfoService.getByAdminExamplePage(mwCmAdminInfoExample, page, row);
				count = mwCmAdminInfoService.getAdminCount(mwCmAdminInfoExample);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
			/******* paging start *********/
			Paging pageing = new Paging();
			pageing.makeWebPaging(page, count, row);
			request.setAttribute("nowPage", row);
			request.setAttribute("paging", pageing.getSb());
			request.setAttribute("rowsPerPage", rowsPerPage);
			/******* paging end *********/
			
			request.setAttribute("adminType", adminType);
			request.setAttribute("mwCmAdminInfo", mwCmAdminInfo);
			request.setAttribute("compName", compName);
			request.setAttribute("shopName", shopName);
			request.setAttribute("sdate", Sdate);
			request.setAttribute("edate", Edate);
			request.setAttribute("partnerTerm", partnerTerm);
			
			/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("pageURL", "/member/admin_login_list.ms");
			
			params.put("nowPage", nowPage);
			params.put("rowsPerPage", rowsPerPage );
			params.put("adminType", adminType);
			params.put("compName", compName);
			params.put("shopName", shopName);
			params.put("Sdate", Sdate);
			params.put("Edate", Edate);
			params.put("partnerTerm", partnerTerm );
			//params.put("mwCmAdminInfo", mwCmAdminInfo );
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
			
			
			return "member/admin_login_list";
	}
	
	/**
	 * ��ü���� - ������ ��ϰ��� ���
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/bulk_coupon_list"
	 */ 
	@RequestMapping(value="/member/admin_login_add.ms")
	public String AdminAddStart(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("adminType", "01");
		return "member/admin_login_add";
	}
	
	/**
	 * ��ü���� - ������ ��ϰ��� ��� ����
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/bulk_coupon_list"
	 */ 
	@RequestMapping(value="/member/admin_login_add_move.ms")
	public String AdminAddMove(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		String adminType = checkStr(request.getParameter("adminType"), "");
		String compName = checkStr(request.getParameter("compName"), "");
		String compId = checkStr(request.getParameter("compId"), "");
		String brdId =checkStr(request.getParameter("brdName"), "");
		String shopName = checkStr(request.getParameter("shopName"), "");
		String shopId = checkStr(request.getParameter("shopId"), "");
		String loginId = checkStr(request.getParameter("loginId"), "");
		String name = checkStr(request.getParameter("name"), "");
		String phone = checkStr(request.getParameter("phone"), "");
		String email = checkStr(request.getParameter("email"), "");
		String ip = checkStr(request.getParameter("ip"), "");
		String adminPass = checkStr(request.getParameter("loginPass"), "");
		String stat = checkStr(request.getParameter("stat"), "");
		
		MwCmAdminInfoService mwCmAdminInfoService = new MwCmAdminInfoService();
		MwCmAdminInfoExample mwCmAdminInfoExample = new MwCmAdminInfoExample();
		mwCmAdminInfoExample.or().andAdminIdEqualTo(loginId);
		int count = mwCmAdminInfoService.getCountByExample(mwCmAdminInfoExample);
		if(count>0){
			JsonErrMsg("non", response);
			return null;
		}		
		try{
			MwCmAdminInfo mwCmAdminInfo = new MwCmAdminInfo();
			mwCmAdminInfo.setAdminId(loginId);
			mwCmAdminInfo.setAdminLevel(adminType);
			mwCmAdminInfo.setRegDtm(new Date());
			mwCmAdminInfo.setRegUser(getSessionMgrId(request));
//			mwCmAdminInfo.setMobilePhone(phone);
//			mwCmAdminInfo.setEmail(email);
//			mwCmAdminInfo.setAdminName(name);
			mwCmAdminInfo.setCompId(compId);
			mwCmAdminInfo.setIp(ip);
			mwCmAdminInfo.setStat(stat);
//			mwCmAdminInfo.setAdminPass(adminPass);
			
			/*##################### ��ȣȭ S #####################*/
			mwCmAdminInfo.setMobilePhone(ktService.encoding(phone));
			mwCmAdminInfo.setEmail(ktService.encoding(email));
			mwCmAdminInfo.setAdminName(ktService.encoding(name));
			mwCmAdminInfo.setAdminPass(shaCipher.encryptSHA256(adminPass));
			/*##################### ��ȣȭ E #####################*/
			
			if(!brdId.equals(""))mwCmAdminInfo.setBranId(brdId);
			if(!shopId.equals(""))mwCmAdminInfo.setShopId(shopId);
			
			
			/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("pageURL", "/member/admin_login_add_move.ms");
			
			params.put("adminType", adminType);
			params.put("compId", compId );
			params.put("shopId", shopId);
			params.put("brdId", brdId);
			params.put("loginId", loginId);
//			params.put("name", name);
//			params.put("phone", phone);
//			params.put("email", email);
			params.put("addrIP", ip );
			params.put("stat", stat);
			params.put("retryCnt", "0");
//			params.put("adminPass", adminPass);
			
			/*##################### ��ȣȭ S #####################*/
			params.put("name", ktService.encoding(name));
			params.put("phone", ktService.encoding(phone));
			params.put("email", ktService.encoding(email));
			params.put("adminPass", shaCipher.encryptSHA256(adminPass));
			/*##################### ��ȣȭ E #####################*/
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
			
			mwCmAdminInfoService.insert(mwCmAdminInfo);
			mwCmAdminInfoService.commit();
		}catch (Exception e) {
			// TODO: handle exception
			mwCmAdminInfoService.rollback();
			Log.error(e.getMessage());
			JsonErrMsg("err", response);
			return null;
		}
		
			JsonErrMsg("", response);
			return null;
	}
	
	/**
	 * ��ü���� - ������ ��ϰ��� ����Ʈ ����ȭ��
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/bulk_coupon_list"
	 */ 
	@RequestMapping(value="/member/admin_login_edit.ms")
	public String AdminEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
			
		String adminId = checkStr(request.getParameter("adminId"), "");
		
		
		MwCmAdminInfoService mwCmAdminInfoService = new MwCmAdminInfoService();
		MwCmAdminInfo 	mwCmAdminInfo = new MwCmAdminInfo();
		if(!adminId.equals("")){
			MwCmAdminInfoExample mwCmAdminInfoExample = new MwCmAdminInfoExample();
			mwCmAdminInfoExample.or().andAdminIdEqualTo(adminId);
			mwCmAdminInfo = mwCmAdminInfoService.getByAdminExampleOnly(mwCmAdminInfoExample);
		}else{
			Log.info("adminId is Null : " + adminId);
		}
		String adminType = mwCmAdminInfo.getAdminLevel();
		String compId = mwCmAdminInfo.getCompId();
		String compName = mwCmAdminInfo.getCompName();
		String braindName = mwCmAdminInfo.getBranName();
		String StortName = mwCmAdminInfo.getShopName();
//		String name = mwCmAdminInfo.getAdminName();
//		String phone = mwCmAdminInfo.getMobilePhone();
//		String email = mwCmAdminInfo.getEmail();
		String regDtm = mwCmAdminInfo.getRegDtmStr();
		String regUser = mwCmAdminInfo.getRegUser();
		String stortId = mwCmAdminInfo.getShopId();
		String ip = mwCmAdminInfo.getIp();
		String adminPass = mwCmAdminInfo.getAdminPass();
		String stat = mwCmAdminInfo.getStat();
		int retryCnt = mwCmAdminInfo.getRetryCnt();
		
		/*##################### ��ȣȭ S #####################*/
		String name = ktService.decoding(mwCmAdminInfo.getAdminName());
		String phone = ktService.decoding(mwCmAdminInfo.getMobilePhone());
		String email = ktService.decoding(mwCmAdminInfo.getEmail());
		/*##################### ��ȣȭ E #####################*/
		
		request.setAttribute("adminId", adminId);
		request.setAttribute("adminPass", adminPass);
		request.setAttribute("adminType", adminType);
		request.setAttribute("compId", compId);
		request.setAttribute("compName", compName);
		request.setAttribute("braindName", braindName);
		request.setAttribute("stortName", StortName);
		request.setAttribute("stortId", stortId);
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		request.setAttribute("email", email);
		request.setAttribute("regDtm", regDtm);
		request.setAttribute("regUser", regUser);
		request.setAttribute("stat", stat);
		request.setAttribute("retryCnt", retryCnt);
		request.setAttribute("ip", ip);
			
		/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("pageURL", "/member/admin_login_edit.ms");
		
		params.put("adminId", adminId);
		params.put("adminPass", adminPass );
		params.put("adminType", adminType);
		params.put("compId", compId);
		params.put("compName", compName);
		params.put("braindName", braindName);
		params.put("StortName", StortName);
		params.put("stortId", stortId );
//		params.put("name", name);
//		params.put("phone", phone);
//		params.put("email", email);
		params.put("regDtm", regDtm);
		params.put("regUser", regUser);
		params.put("addrIP", ip);
		params.put("stat", stat);
		params.put("retryCnt", retryCnt);
		
		/*##################### ��ȣȭ S #####################*/
		params.put("name", ktService.encoding(name));
		params.put("phone", ktService.encoding(phone));
		params.put("email", ktService.encoding(email));
		/*##################### ��ȣȭ E #####################*/
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
		
			return "member/admin_login_edit";
	}
	
	/**
	 * ��ü���� - ������ ��ϰ��� ����Ʈ ���� ����
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/bulk_coupon_list"
	 */ 
	@RequestMapping(value="/member/admin_login_edit_move.ms")
	public String AdminEditMove(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		String LoginPass = checkStr(request.getParameter("loginPass"), "");
		String adminId = checkStr(request.getParameter("adminId"), "");
		String adminType = checkStr(request.getParameter("adminType"), "");
		String compName = checkStr(request.getParameter("compName"), "");
		String compId = checkStr(request.getParameter("compId"), "");
		String brdId =checkStr(request.getParameter("brdName"), "");
		String shopName = checkStr(request.getParameter("shopName"), "");
		String shopId = checkStr(request.getParameter("shopId"), "");
		String loginId = checkStr(request.getParameter("loginId"), "");
		String name = checkStr(request.getParameter("name"), "");
		String phone = checkStr(request.getParameter("phone"), "");
		String email = checkStr(request.getParameter("email"), "");
		String ip = checkStr(request.getParameter("ip"), "");
		String stat = checkStr(request.getParameter("stat"), "");
		String retryCnt = checkStr(request.getParameter("retryCnt"), "0");
		
		
		MwCmAdminInfoService mwCmAdminInfoService = new MwCmAdminInfoService();
		
		MwCmAdminInfoExample mwCmAdminInfoExample = new MwCmAdminInfoExample();
		mwCmAdminInfoExample.or().andAdminIdEqualTo(loginId);
		if(!loginId.equals(adminId)){
			int count = mwCmAdminInfoService.getCountByExample(mwCmAdminInfoExample);
			if(count>0){
				JsonErrMsg("non", response);
				return null;
			}
		}
		try{
			MwCmAdminInfo mwCmAdminInfo = new MwCmAdminInfo();
			mwCmAdminInfo.setAdminLevel(adminType);
			mwCmAdminInfo.setAdminId(loginId);
			mwCmAdminInfo.setChgDtm(new Date());
			mwCmAdminInfo.setChgUser(getSessionMgrId(request));
			mwCmAdminInfo.setIp(ip);
//			mwCmAdminInfo.setMobilePhone(phone);
//			mwCmAdminInfo.setEmail(email);
//			mwCmAdminInfo.setAdminName(name);
			mwCmAdminInfo.setCompId(compId);
			mwCmAdminInfo.setBranId(brdId);
			mwCmAdminInfo.setShopId(shopId);
			mwCmAdminInfo.setStat(stat);
			mwCmAdminInfo.setRetryCnt(new Integer(retryCnt));
//			if(!LoginPass.equals("")){
//				mwCmAdminInfo.setAdminPass(LoginPass);
//			}
			
			/*##################### ��ȣȭ S #####################*/
			mwCmAdminInfo.setMobilePhone(ktService.encoding(phone));
			mwCmAdminInfo.setEmail(ktService.encoding(email));
			mwCmAdminInfo.setAdminName(ktService.encoding(name));
			if(!LoginPass.equals("")){
				mwCmAdminInfo.setAdminPass(shaCipher.encryptSHA256(LoginPass));
			}
			/*##################### ��ȣȭ E #####################*/
			
			mwCmAdminInfoExample = new MwCmAdminInfoExample();
			mwCmAdminInfoExample.or().andAdminIdEqualTo(adminId);
			mwCmAdminInfoService.update(mwCmAdminInfo,mwCmAdminInfoExample);
			mwCmAdminInfoService.commit();
			
			
			/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("pageURL", "/member/admin_login_edit_move.ms");
			
			params.put("adminId", adminId);
			params.put("adminType", adminType);
			params.put("compId", compId);
			params.put("compName", compName);
			params.put("brdId", brdId);
			params.put("shopName", shopName);
			params.put("shopId", shopId );
//			params.put("name", name);
//			params.put("phone", phone);
//			params.put("email", email);
			params.put("addrIP", ip);
			params.put("stat", stat);
			params.put("retryCnt", retryCnt);
			
			/*##################### ��ȣȭ S #####################*/
			params.put("name", ktService.encoding(name));
			params.put("phone", ktService.encoding(phone));
			params.put("email", ktService.encoding(email));
			/*##################### ��ȣȭ E #####################*/
		
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString());
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
			
			
		}catch (Exception e) {
			// TODO: handle exception
			mwCmAdminInfoService.rollback();
			Log.error(e.getMessage());
			JsonErrMsg("err", response);
			return null;
		}
		Log.info("�����Ϸ� =========");
		JsonErrMsg("", response);
		return null;
		
	}
	
	
	/**
	 * ��ü���� - ������ ��ϰ��� ����Ʈ ���� ����
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/bulk_coupon_list"
	 */ 
	@RequestMapping(value="/member/admin_login_del_move.ms")
	public String AdminEditDel(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
			String adminId = checkStr(request.getParameter("adminId"), "");
			MwCmAdminInfoService mwCmAdminInfoService = new MwCmAdminInfoService();
			try{
				MwCmAdminInfoExample mwCmAdminInfoExample = new MwCmAdminInfoExample();
				mwCmAdminInfoExample.or().andAdminIdEqualTo(adminId);
				mwCmAdminInfoService.delete(mwCmAdminInfoExample);

				/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
				HashMap<String, Object> params = new HashMap<String,Object>();
				params.put("pageURL", "/member/admin_login_del_move.ms");
				
				params.put("mwCmAdminInfoExample", mwCmAdminInfoExample);
		
				params.put("part", "MEMBERSHIP");
				params.put("admin_id", getSessionMgrId(request));
				params.put("ip", request.getRemoteAddr());
				params.put("msg", params.toString());
				
				logSVC.insertAccessLogReg(params);
				logSVC.commit();
				/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
				
			}catch (Exception e) {
			// TODO: handle exception
				mwCmAdminInfoService.rollback();
				JsonErrMsg("err", response);
				return null;
			}
			
			JsonErrMsg("", response);
			return null;
	}
	
	/**
	 * Ajax ��� ����
	 * @MakeBy �̰���
	 * */
	private void JsonErrMsg(String Err, HttpServletResponse response){
		try{
			PrintWriter writer = response.getWriter();
			writer.write("{\"err\":\""+Err+"\"}");
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/**
	 * �̸��� �ּ� �յڷ� �ڸ���
	 * @MAKEBY �̰���
	 * */
	private String[] StrCut(String Str, String CutPoint){
		String[] cutString = new String[2];		
		StringTokenizer st = new StringTokenizer(Str,CutPoint);
		int count = 0;
	   while(st.hasMoreTokens()){
	  	 if(count>1)break;
	  	 cutString[count] = st.nextToken();
	  	 count++;	  	 
	   }
		return cutString;
	}	
	
	private int NullNumberCheck(Integer integer){
		int result = 0;
		try{
			result = integer;
		}catch (Exception e) {
			result = 0;
		}
		return result;
	}
	
}

