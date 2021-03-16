package com.spring.employees.security;

import org.springframework.beans.factory.annotation.Value;

public final class JWTConstant {

	// Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
	@Value("${jwt.secret}")
    public static final String JWT_SECRET = "SecretKey012345678901234567890123456789012345678901234567890123456789";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    
    public static final String CLAIMS_SUBJECT = "sub";
    public static final String CLAIMS_CREATED = "created";
    
    public static final Long TOKEN_VALIDITY = 691200L;//691200 in s => 8 Days
    
    
	/*private JWTConstant() {
		throw new IllegalStateException("Impossible de créer une instance de la classe util statique");
	}*/
}
