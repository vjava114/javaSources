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
	// TODO: ����Ű �̸��� ���Ƿ� �־�Ӵϴ�. �α��ν� ����� Ű �̸����� ������ �ּ���.
	private final String SSEEION_MGR_ID = "SSEEION_MGR_ID";
	// TODO: �α��� URL�� ������ �־� �Ӵϴ�. ���� �α��� �������� ������ �ּ���.
	private final String LOGIN_URL = "/base/login.in";
	private final String STAT_LOGIN_URL = "/stat/login.in";
		
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		
		//ȣ�� url ����
		String url = String.valueOf(request.getRequestURI());
		String urlExt = String.valueOf(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("."), request.getRequestURI().length()));

		if (session == null || session.getAttribute(SSEEION_MGR_ID) == null 
				|| ((String) session.getAttribute(SSEEION_MGR_ID)).length() == 0) {
			
			//.st �ϰ�� ��� �α��� ������ �̵�, admin�ϰ�� admin �α��� ������ �̵�
			if(".st".equals(urlExt)) {
				response.sendRedirect(STAT_LOGIN_URL);
			} else {
				response.sendRedirect(LOGIN_URL);
			}
			
			return;
		}
		
		
		//�Ķ���� ����		
		String mgr_id = (String) session.getAttribute(SSEEION_MGR_ID);
		String lev = String.valueOf(session.getAttribute("SSEEION_LEV"));
		
		if(!".st".equals(urlExt)) {
			
			//���� üũ - ������ �ٸ���� �α��� �������� �̵�
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

			//admin ������ �ƴҰ��, �ش� ����޴� url�� ���ٱ����� ������� logout ó��
			if(!"admin".equals(lev)) {
	
				params.put("url", url);
				adminLevel = menu.selectSubMenuList(params);	//admin�� �� �������� ����޴�����Ʈ�� ��ȸ��
				
				//�ش緹����,url�� ���� �������, ������ �����Ƿ� logOut ó����
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
			
			//�޴� ����Ʈ ��ȸ
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
