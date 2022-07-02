package com.cts.fse.stockmarket.auth.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtConfig {

	@Value("${app.jwt.key}")
	public String secretKey;

	public String generateToken(String userName) {

//		String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//				.collect(Collectors.joining(","));

		return Jwts.builder().setSubject(userName)
//				.claim("roles", authorities).setId("")
				.setIssuer("ram")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
	}
	
	public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", authorities)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5*60*60*1000))
                .compact();
    }

	public Claims getClaims(String token) {

		return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
	}

	public Date getExpDate(String token) {

		return getClaims(token).getExpiration();
	}

	public String getUserName(String token) {

		return getClaims(token).getSubject();
	}

	public boolean isTokeneExp(String token) {

		return getExpDate(token).before(new Date(System.currentTimeMillis()));
	}

	public boolean validateToken(String token, String userName) {

		return (getUserName(token).equals(userName) && !isTokeneExp(token));
	}

	UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth,
			final UserDetails userDetails) {

		final Claims claims = getClaims(token);
		final Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get("roles").toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	} 
}
