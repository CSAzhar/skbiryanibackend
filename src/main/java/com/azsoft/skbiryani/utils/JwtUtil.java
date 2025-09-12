package com.azsoft.skbiryani.utils;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.azsoft.skbiryani.serviceImpl.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret.key}")
	private String SECRET_KEY;
	
//	public String generateJwtToken(UserDetails userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//		
//		claims.put("roles", userDetails.getAuthorities()
//	            .stream()
//	            .map(auth -> auth.getAuthority())   // e.g., ROLE_USER, ROLE_ADMIN
//	            .toList());
//		System.out.println(">>> Generating JWT for user: " + userDetails.getUsername());
//	    System.out.println(">>> Roles added to JWT: " + claims.get("roles"));
//		return createToken(claims, userDetails.getUsername());
//	}
//	
	private SecretKey getSigningKey() {
	    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	@Autowired
    private CustomUserDetailsService userDetailsService;

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        if (userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty()) {
            UserDetails freshUser = userDetailsService.loadUserByUsername(userDetails.getUsername());
            claims.put("roles", freshUser.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList());
        } else {
            claims.put("roles", userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList());
        }

        return createToken(claims, userDetails.getUsername());
    }

//	Jwts.builder()
//	.setClaims(claims)
//	.setSubject(subject)
//	.setIssuedAt(new Date(System.currentTimeMillis()))
//	.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000 ))  // 10 hours
//	.signWith(getSigningKey(), SignatureAlgorithm.HS256)
//	.compact();
//	
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
			.claims(claims)
			.subject(subject)
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000 ))
			.signWith(getSigningKey(), Jwts.SIG.HS256)
			.compact();
	}
	
	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}


	private Claims extractAllClaims(String token) {
//		SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
		return Jwts.parser()
				.verifyWith(getSigningKey())
//				.setSigningKey(getSigningKey())
				.build()
				.parseSignedClaims(token)
//				.parseClaimsJws(token)
//				.getBody();
				.getPayload();
				
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token, UserDetails userdetails) {
		final String username = extractUsername(token);
		return (username.equals(userdetails.getUsername())) && !isTokenExpired(token);
	}

}











