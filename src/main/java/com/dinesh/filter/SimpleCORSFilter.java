package com.dinesh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Filter to allow Cross-Origin requests (e.g. by front-end). Without the filter
 * the requests are blocked.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter implements Filter {

	/**
     * This method checks on the validity of the session upon direct request. If the session is not valid, the user is forwarded to login page. When This
     * method is done, execute the other filters in the chain.
     * 
     * @param req
     *            servlet request.
     * @param res
     *            servlet response.
     * @param chain
     *            any other filters configured to run.
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("X-Requested-With", "XMLHttpRequest");
		response.setHeader("Access-Control-Allow-Headers",
				"x-auth-token, x-requested-with, Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Origin, Accept, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, token, authorization, offset, isDST");
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}
