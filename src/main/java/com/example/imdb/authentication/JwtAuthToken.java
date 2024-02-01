package com.example.imdb.authentication;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthToken extends AbstractAuthenticationToken{

	private static final long serialVersionUID = -3140110414797803970L;
	private final Object principal;
	private final Object credentials;
	
	public JwtAuthToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(false);
	}
	
	private JwtAuthToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}
	
	public static JwtAuthToken unauthenticated(Object credentials) {
		return new JwtAuthToken(null, credentials);
	}
	
	public static JwtAuthToken authenticated(Object principal) {
		return new JwtAuthToken(null,principal, null);
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return principal;
	}
}
