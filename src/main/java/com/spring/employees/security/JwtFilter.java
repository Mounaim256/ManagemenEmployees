package com.spring.employees.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.employees.service.UserService;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JWTGenerated jwtGenerated;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        
		String username = null;
		String token = null;
		
		String header = request.getHeader(JWTConstant.TOKEN_HEADER);
		
		if(header != null)
		System.out.println(header.substring(JWTConstant.TOKEN_PREFIX.length()));
		if(header != null && header.startsWith(JWTConstant.TOKEN_PREFIX)) {
			 token = header.substring(JWTConstant.TOKEN_PREFIX.length());
   			 username = this.jwtGenerated.getUsernameFromToken(token);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userService.loadUserByUsername(username);
			
			if(this.jwtGenerated.isTokonValid(userDetails, token)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
