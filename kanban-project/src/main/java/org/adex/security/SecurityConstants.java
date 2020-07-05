package org.adex.security;

public class SecurityConstants {

	public static final String SIGN_UP_URLS = "/api/users/**";
	public static final String H2_DB = "h2-console/**";
	public static final String SECRET_KEY = "theSecretKeyToGenerateJWTs";
	public static final String JWT_PREFIX = "Bearer ";
	public static final String HEADER_AUTH = "Authorization";
	public static final long JWT_EXPIRATION_TIME = 24 * 60 * 60 * 60; // 30 minutes day

}
