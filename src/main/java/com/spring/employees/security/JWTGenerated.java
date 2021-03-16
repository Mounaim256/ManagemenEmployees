package com.spring.employees.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerated {

	public String generatToken(UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(JWTConstant.CLAIMS_SUBJECT, userDetails.getUsername());
		claims.put(JWTConstant.CLAIMS_CREATED, new Date());
		
		return Jwts.builder()
				   .setClaims(claims)
				   .setExpiration(getExpiredDate())
				   .signWith(SignatureAlgorithm.HS512, JWTConstant.JWT_SECRET)
				   .compact();
	}
	
	
	public Date getExpiredDate() {
		return new Date(System.currentTimeMillis() + (JWTConstant.TOKEN_VALIDITY * 1000) );
	}
	
	
	
	public String getUsernameFromToken(String token) {
		return getlaimsFromToken(token).getSubject();
	}
	
	
	public Date getExpiredDateFromToken(String token) {
		return getlaimsFromToken(token).getExpiration();
	}
	
	public Claims getlaimsFromToken(String token) {
		return Jwts.parser()
				   .setSigningKey(JWTConstant.JWT_SECRET)
				   .parseClaimsJws(token)
				   .getBody();
	}
	
	public boolean isTokonValid(UserDetails userDetails,String token) {
		String username = getUsernameFromToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	
	public boolean isTokenExpired(String token) {
		final Date date = getExpiredDateFromToken(token);
		return date.before(new Date());
	}
}
