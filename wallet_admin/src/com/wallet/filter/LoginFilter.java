package com.wallet.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wallet.admin.model.MwAdUser;
import com.wallet.admin.model.MwAdMenu;
import com.wallet.admin.service.MwAdMenuService;
import com.wallet.admin.service.MwAdUserService;
import com.wallet.common.util.Log;
import com.wallet.common.util.StringUtil;

public class LoginFilter implements Filter {
	// TODO: 세션키 이름은 임의로 넣어둡니다. 로그인시 사용한 키 이름으로 변경해 주세요.
	private final String SSEEION_MGR_ID = "SSEEION_MGR_ID";
	// TODO: 로그인 URL을 임으로 넣어 둡니다. 실제 로그인 페이지로 변경해 주세요.
	private final String LOGIN_URL = "/base/login.in";
	private final String STAT_LOGIN_URL = "/stat/login.in";
		
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		
		//호출 url 정보
		String url = String.valueOf(request.getRequestURI());
		String urlExt = String.valueOf(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("."), request.getRequestURI().length()));

		if (session == null || session.getAttribute(SSEEION_MGR_ID) == null 
				|| ((String) session.getAttribute(SSEEION_MGR_ID)).length() == 0) {
			
			//.st 일경우 통계 로그인 페이지 이동, admin일경우 admin 로그인 페이지 이동
			if(".st".equals(urlExt)) {
				response.sendRedirect(STAT_LOGIN_URL);
			} else {
				response.sendRedirect(LOGIN_URL);
			}
			
			return;
		}
		
		
		//파라미터 정보		
		String mgr_id = (String) session.getAttribute(SSEEION_MGR_ID);
		String lev = String.valueOf(session.getAttribute("SSEEION_LEV"));
		
		if(!".st".equals(urlExt)) {
			
			//세션 체크 - 세션이 다를경우 로그인 페이지로 이돋
			MwAdUserService service = new MwAdUserService();
			MwAdUser mwAdUser = null;
			HashMap<String,Object> params = new HashMap<String,Object>();
			
			params.put("view", "dtl");
			params.put("mgr_id", mgr_id);
			params.put("top", 1);

			mwAdUser = service.selectUserListDtl(params);
			
			if(!session.getId().equals(mwAdUser.getIpaddress())) {
				response.sendRedirect(LOGIN_URL+"?ipYn=N");
				return;
			}

			MwAdMenuService menu = new MwAdMenuService();
			List<MwAdMenu> mwAdMenu = null;
			List<MwAdMenu> mwAdSubMenu = null;
			List<MwAdMenu> adminLevel = null;

			//admin 레벨이 아닐경우, 해당 서브메뉴 url에 접근권한이 없을경우 logout 처리
			if(!"admin".equals(lev)) {
	
				params.put("url", url);
				adminLevel = menu.selectSubMenuList(params);	//admin등 각 레벨별로 서브메뉴리스트가 조회됨
				
				//해당레벨과,url이 같지 않을경우, 권한이 없으므로 logOut 처리됨
				if(adminLevel != null && adminLevel.size() > 0 ) {
					int cnt = 0;
					for(int i=0; i<adminLevel.size(); i++) {
						if(lev.equals(adminLevel.get(i).getAdminLevel()) && url.equals(adminLevel.get(i).getUrl())) {
							++cnt;
						}
					}
					
					if(cnt == 0) {
						response.sendRedirect(LOGIN_URL);
						return;
					}
					
				}
			}
			
			//메뉴 리스트 조회
			params.clear();
			params.put("admin_level", lev);
			mwAdMenu = menu.selectMenuList(params);
			mwAdSubMenu = menu.selectSubMenuList(params);
			
			req.setAttribute("mwAdMenuList", mwAdMenu);
			req.setAttribute("mwAdSubMenuList", mwAdSubMenu);
		}
		
		
		chain.doFilter(req, res);
	}

	
	public void init(FilterConfig config) throws ServletException {
		
	}

}
