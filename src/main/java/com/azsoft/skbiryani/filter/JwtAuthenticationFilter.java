package com.azsoft.skbiryani.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.azsoft.skbiryani.utils.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		final String authHeader = request.getHeader("Authorization");
		
		System.out.println(">>> JWT Filter triggered for: " + request.getRequestURI());
		
		if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) 
		{
			String token = authHeader.substring(7);
			String email = jwtUtil.extractUsername(token);
			
			System.out.println(">>> Extracted email from token: " + email);
			
			if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) 
			{
				UserDetails userDetails = userDetailsService.loadUserByUsername(email);
				
				if(jwtUtil.validateToken(token, userDetails)) {
					
					 
					
					List<String> roles = jwtUtil.extractClaims(token, claims -> claims.get("roles", List.class));
					
					System.out.println(">>> Raw roles from JWT claims: " + roles);
					 // Convert roles into Spring Security authorities
				    List<SimpleGrantedAuthority> authorities = roles.stream()
				            .map(SimpleGrantedAuthority::new)   // e.g. "ROLE_USER"
				            .toList();
				    
				    System.out.println(">>> Converted authorities: " + authorities);
				    
					UsernamePasswordAuthenticationToken authenticationToken = 
																				new UsernamePasswordAuthenticationToken(
																						userDetails,
																						null,
																						authorities);
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					System.out.println(">>> Authentication set in SecurityContext: " + authenticationToken);
				}
				
			}
			
		}
		filterChain.doFilter(request, response);
		
	}
	
	

}



















