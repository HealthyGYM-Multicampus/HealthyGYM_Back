package com.mul.HealthyGYM.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
	/*
	 * 인증 오류가 아닌, JWT 관련 오류는 이 필터에서 따로 잡아낸다. 이를 통해 JWT 만료 에러와 인증 에러를 따로 잡아낼 수 있다.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			chain.doFilter(request, response); // JwtAuthenticationFilter로 이동
		} catch (JwtException ex) {
			// JwtAuthenticationFilter에서 예외 발생하면 바로 setErrorResponse 호출
			setErrorResponse(HttpStatus.UNAUTHORIZED, request, response, ex);
		}
	}

	public void setErrorResponse(HttpStatus status, HttpServletRequest req, HttpServletResponse res, Throwable ex)
			throws IOException {

		res.setStatus(status.value());
		res.setContentType("application/json; charset=UTF-8");
		
		Map<String, Object> map = new HashMap<>();
		map.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		map.put("error", "Unauthorized");
		map.put("message", ex.getMessage());
		map.put("path", req.getServletPath()); 
		String json = new ObjectMapper().writeValueAsString(map);
		res.getWriter().write(json);
	}
}
