package com.wallet.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Load implements Filter {

	public void init(FilterConfig config) throws ServletException {
		String server = config.getInitParameter("server");
		String path = config.getInitParameter(new StringBuilder("path").append("_").append(server).toString());
		PropertiesUtil.init(path, server, "properties/base");
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
	}
}
