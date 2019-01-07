package org.javaweb.codereview.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yz
 */
@WebFilter(filterName = "TestFilter", urlPatterns = {"/TestServlet"})
public class TestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String str = request.getParameter("password");

		if ("023".equals(str)) {
			chain.doFilter(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.println("Login error password error!");
			out.flush();
			out.close();
		}
	}

	@Override
	public void destroy() {

	}

}
