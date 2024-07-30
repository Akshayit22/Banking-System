package com.akshay.bankSystem.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.akshay.bankSystem.configs.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtTokenHelper {
	
	public static final long Jwt_Token_Validity = 1000 * 60 * 60 * 10; // 10 minutes
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token){
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims ,T> claimsResolver){
		final Claims claims= getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	
	private Claims getAllClaimsFromToken(String token){
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Boolean isTokenExpired(String token){
		return getExpirationDateFromToken(token).before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) throws Exception{
		Map<String, Object> claims=new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject)throws Exception{
        return Jwts
        		.builder()
        		.setClaims(claims)
        		.setSubject(subject)
        		.setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Jwt_Token_Validity))
                .signWith(getSignInKey()).compact();
    }
	
	public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
	
	private Key getSignInKey() {
	    byte[] keyBytes = Decoders.BASE64.decode(Constants.SECRET);
	    return Keys.hmacShaKeyFor(keyBytes);
	}
	
}
