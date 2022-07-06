//package com.cts.fse.stockmarket.apigateway;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomHeaderFilter implements Filter{
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//		final HttpServletResponse response = (HttpServletResponse) res;
//	    response.setHeader("Access-Control-Allow-Origin", "*");
//	    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//	    response.setHeader("Access-Control-Max-Age", "3600");
//	    response.setHeader("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
//	            "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
//	    chain.doFilter(req, res);
//		
//	}
//
//}
