package com.upc.contract.adm.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.upc.contract.adm.util.Constantes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			if (this.checkJWTToken(request, response)) {
				Claims claims = this.validateToken(request);
				if (claims.get(Constantes.AUTHORITIES) != null) {
					this.setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			} else {
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}	

	/**
	 * 
	 * @param request
	 * @return
	 */
	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(Constantes.HEADER).replace(Constantes.PREFIX, "");
		return Jwts.parser().setSigningKey(Constantes.SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
	}

	/**
	 * Authentication method in Spring flow
	 * 
	 * @param claims
	 */
	@SuppressWarnings("unchecked")
	private void setUpSpringAuthentication(Claims claims) {
		List<String> authorities = (List<String>) claims.get(Constantes.AUTHORITIES);

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	/**
	 * 
	 * @param request
	 * @param res
	 * @return
	 */
	private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(Constantes.HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(Constantes.PREFIX)) {
			return false;
		}
		return true;
	}

}