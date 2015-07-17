package filter.amore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("[filter] 지금부터 *.do, *.jsp 는 filter를 거쳐갑니다. web.xml 참조하세요! ");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res; 
		HttpSession session = request.getSession();					// 이 세개를 정확히 이해하지 못하겠다..
	
							/* 아래의 경로를 그때그때 맞게 설정해주세요. */
		
		
		
		//String path = "/index.go";
		String path = request.getContextPath() + "/index.go";
		
		if( session.getAttribute("id") == null )
		{
			System.out.println(path + "로 이동합니다.");
			response.sendRedirect(path);				   
			return;
		}
		// 세션이 null이 아닐경우는 아래를 실행
		else
		{
			System.out.println(session.getAttribute("id"));
			chain.doFilter( req, res );
		}
		
		
	}

	@Override
	public void destroy() {
		// TODO 자동 생성된 메소드 스텁
		
	}
	
}


