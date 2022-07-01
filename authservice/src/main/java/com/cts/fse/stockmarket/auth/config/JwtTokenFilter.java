package com.cts.fse.stockmarket.auth.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	JwtConfig jwt;

	@Autowired
	UserDetailsService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = request.getHeader("Authorization");
		String userName = null;
		if (token != null) {
			try {
				userName = jwt.getUserName(token);
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting username from token", e);
			} catch (ExpiredJwtException e) {
				logger.warn("the token is expired and not valid anymore", e);
			} catch (SignatureException e) {
				logger.error("Authentication Failed. Username or Password not valid.");
			}
			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailService.loadUserByUsername(userName);
				boolean validateToken = jwt.validateToken(token, userDetails.getUsername());
				if (validateToken) {
//					UsernamePasswordAuthenticationToken authToekn = new UsernamePasswordAuthenticationToken(userName,
//							userDetails.getPassword(), userDetails.getAuthorities());

					 UsernamePasswordAuthenticationToken authToekn =jwt.getAuthentication(token,
					 SecurityContextHolder.getContext().getAuthentication(), userDetails);

//					UsernamePasswordAuthenticationToken authToekn = new UsernamePasswordAuthenticationToken(userDetails,
//							null, userDetails.getAuthorities());

					authToekn.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authToekn);
				}
			}
		}

		filterChain.doFilter(request, response);
	}

}
