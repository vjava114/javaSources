//package com.wallet.membership.web.base;
//
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.wallet.common.util.Log;
//import com.wallet.common.web.CommonController;
//import com.wallet.membership.model.MwMembAgree;
//import com.wallet.membership.model.MwMembAgreeExample;
//import com.wallet.membership.service.MwMembAgreeService;
//
//@Controller 
//public class MemberMultiRegisterController extends CommonController {
//	private final String PAGE_CODE = "MEMBER_MULTI_REGSTER";
//	private Logger log = Log.getLogger("logs");
//	
//	/**
//	 * ��Ƽ������� ��ϵ��� ǥ���ϰ� �ű��߰� �� ������ ���� ������
//	 * 
//	 * @param locale
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return"member/member_multi_register"
//	 */ 
//	@RequestMapping(value="/member/member_multi_register.ms")
//	public String TermsList(Locale locale, Model model,
//			HttpServletRequest request, HttpServletResponse response) {
//		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
//		List<MwMembAgree> mwMembAgreeList = null;
//		MwMembAgreeService mwMembAgreeService = new MwMembAgreeService();
//		MwMembAgreeExample mwMembAgreeExample = null;
//		 
//		int count = 0;
//		
//		// �α��� üũ
//		if (isLogedin(request) || true) {
//			/* GET PARAMETERS */
//			String pageNo = checkStr(request.getParameter("pageNo"), "1");
//			String rowsPerPage = checkStr(request.getParameter("rows"), "10");
//			String shMsg = checkStr(request.getParameter("sh_msg"), "");
//			String DelId = checkStr(request.getParameter("del_id"), "");
//			int DelIdx = Integer.parseInt(checkStr(request.getParameter("del_idx"), "0"));
//			
//			int page = Integer.parseInt(pageNo);
//			int rows = Integer.parseInt(rowsPerPage);
//			/* Main logic */
//			
//			/* ���� */
//			try{
//				if(DelId != null && !DelId.equals("")&&DelIdx != 0){
//					mwMembAgreeExample = new MwMembAgreeExample();
//					mwMembAgreeExample.or().andMembIdEqualTo(DelId);
//					mwMembAgreeExample.or().andIdxEqualTo(DelIdx);
//					mwMembAgreeService.delete(mwMembAgreeExample);
//					mwMembAgreeService.commit();
//					response.sendRedirect("/member/member_multi_register.ms");
//				}
//			}catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//			/*��� ��������*/
//			mwMembAgreeExample = new MwMembAgreeExample();
//			if(shMsg != null && !shMsg.equals("")){				
//				mwMembAgreeExample.or().andMembIdEqualTo(shMsg);
//				mwMembAgreeList = mwMembAgreeService.getByExample(mwMembAgreeExample);
//			}else{				
//				mwMembAgreeList = mwMembAgreeService.getByExamplePage(mwMembAgreeExample, page, rows);
//			}			
//			count = mwMembAgreeService.getCountByExample(mwMembAgreeExample);
//			
//			/* SET ATTRIBUTE */
//			request.setAttribute("pageCode", PAGE_CODE);
//			request.setAttribute("mwMembAgreeList", mwMembAgreeList);
//			request.setAttribute("count", count);
//			request.setAttribute("pageNo", page);
//			request.setAttribute("rowsPerPage", rows);
//			request.setAttribute("pagesPerPage", 10);
//			request.setAttribute("shMsg", shMsg);
//			
//			return "member/member_multi_register";
//		}
//		
//		return "main/login";
//	}
//	
//	/**
//	 * ��Ƽ������� ����Ѵ�.
//	 * 
//	 * @param locale
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return"/member/member_multi_list.ms"
//	 */
//	
//	@RequestMapping(value="/member/member_multi_list.ms", method=RequestMethod.GET)
//	public String agreeReg(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
//		MwMembAgree mwMembAgree = new MwMembAgree();
//		model.put("MwMembAgree", mwMembAgree);
//		
//		
//		/* SET ATTRIBUTEs */
//		request.setAttribute("pageCode", PAGE_CODE);
//		return "/member/member_multi_list";
//	}
//	
//	@RequestMapping(value="/member/member_multi_list.ms", method=RequestMethod.POST)
//	public String agreeReg(@Valid MwMembAgree mwMembAgree, BindingResult result, Map model) {
//		if(result.hasErrors()){
//			return "/member/member_multi_list";
//		}
//		model.put("MwMembAgree", mwMembAgree);
//		
//		MwMembAgreeService mwMembAgreeService = new MwMembAgreeService();
//		mwMembAgreeService.insert(mwMembAgree);
//		mwMembAgreeService.commit();
//		return "base/mgr_success";
//	}
//	
//	/**
//	 * ��ϵ� ����� �����Ѵ�.
//	 * 
//	 * @param locale
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return member/member_terms_edit"
//	 */
///*	
//	@RequestMapping(value="/member/member_terms_edit.ms", method=RequestMethod.GET)
//	public String agreeEdit(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
//		MwMembAgree mwMembAgree = new MwMembAgree();
//		model.put("MwMembAgree", mwMembAgree);
//		Date today = new Date();
//		
//		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
//		MwMembAgreeService mwMembAgreeService = new MwMembAgreeService();
//		MwMembAgreeExample mwMembAgreeExample = null;
//		 
//		int count = 0;
//
//}
