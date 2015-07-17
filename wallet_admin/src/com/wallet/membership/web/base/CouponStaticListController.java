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
import com.wallet.membership.model.MwMembAgree;
import com.wallet.membership.model.MwMembAgreeExample;
import com.wallet.membership.service.MwMembAgreeService;

@Controller
public class CouponStaticListController extends CommonController {
	private final String PAGE_CODE = "COUPON_STATIC_LIST";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 통 페이지
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_static_list"
	 */ 
	//@RequestMapping(value="/member/coupon_static_list.ms")
	@RequestMapping(value="/member/coupon_static_list.st")
	public String TermsList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwMembAgree> mwMembAgreeList = null;
		MwMembAgreeService mwMembAgreeService = new MwMembAgreeService();
		MwMembAgreeExample mwMembAgreeExample = null;
		 
		int count = 0;
		
		// 로그인 체크
		if (isLogedin(request) || true) {
			/* GET PARAMETERS */
			String pageNo = checkStr(request.getParameter("pageNo"), "1");
			String rowsPerPage = checkStr(request.getParameter("rows"), "10");
			String shMsg = checkStr(request.getParameter("sh_msg"), "");
			String DelId = checkStr(request.getParameter("del_id"), "");
			int DelIdx = Integer.parseInt(checkStr(request.getParameter("del_idx"), "0"));
			
			int page = Integer.parseInt(pageNo);
			int rows = Integer.parseInt(rowsPerPage);
			/* Main logic */
			
			/* 삭제 */
			try{
				if(DelId != null && !DelId.equals("")&&DelIdx != 0){
					mwMembAgreeExample = new MwMembAgreeExample();
					mwMembAgreeExample.or().andMembIdEqualTo(DelId);
					mwMembAgreeExample.or().andIdxEqualTo(DelIdx);
					mwMembAgreeService.delete(mwMembAgreeExample);
					mwMembAgreeService.commit();
					response.sendRedirect("/member/coupon_static_list.st");
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			/*목록 가져오기*/
			mwMembAgreeExample = new MwMembAgreeExample();
			if(shMsg != null && !shMsg.equals("")){				
				mwMembAgreeExample.or().andMembIdEqualTo(shMsg);
				mwMembAgreeList = mwMembAgreeService.getByExample(mwMembAgreeExample);
			}else{				
				mwMembAgreeList = mwMembAgreeService.getByExamplePage(mwMembAgreeExample, page, rows);
			}			
			count = mwMembAgreeService.getCountByExample(mwMembAgreeExample);
			
			/* SET ATTRIBUTE */
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("mwMembAgreeList", mwMembAgreeList);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", page);
			request.setAttribute("rowsPerPage", rows);
			request.setAttribute("pagesPerPage", 10);
			request.setAttribute("shMsg", shMsg);
			
			return "member/coupon_static_list";
		}
		
		return "main/login";
	}
	
}