package com.example.SecureApp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MyBasicCustomAuthenticationFilter implements Filter {

	@Autowired
	AuthenticationManager manager;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// based upon request
		HttpServletRequest request = (HttpServletRequest) req;
		String authObj = request.getHeader("auth_key");
		// create authentication object
		MyCustomerAuthenticationToken objToken = new MyCustomerAuthenticationToken(authObj, null);
		// delegate obj to AuthenticationManager --> AuthenticationProvider

		try {
			Authentication authPrincipal = manager.authenticate(objToken);

			// for future use save pricipal into security context

			if (authPrincipal.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authPrincipal);
				chain.doFilter(req, res);
			}
		} catch (org.springframework.security.core.AuthenticationException e) {

			new BadCredentialsException("invalid principle");
		}

	}

}
