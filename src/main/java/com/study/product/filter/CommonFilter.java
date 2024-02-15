package com.study.product.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class CommonFilter extends HttpFilter implements Filter {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public CommonFilter() {
        super();

    }


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		//cors 설정
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		// 문자열 필터
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		chain.doFilter(request, response);
	}

}
