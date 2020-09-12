package com.example.SecureApp.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.SecureApp.filter.MyCustomerAuthenticationToken;

@Component
public class MyAuthenticationprovider implements AuthenticationProvider {

	@Value("${secret_key}")
	String secretKey;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		if (secretKey.equals(authentication.getName())) {
			return new MyCustomerAuthenticationToken(null, null, null);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return MyCustomerAuthenticationToken.class.equals(authentication);
	}

}
