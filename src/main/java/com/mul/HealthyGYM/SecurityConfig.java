package com.mul.HealthyGYM;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.mul.HealthyGYM.jwt.JwtAccessDeniedHandler;
import com.mul.HealthyGYM.jwt.JwtAuthenticationEntryPoint;
import com.mul.HealthyGYM.jwt.JwtAuthenticationFilter;
import com.mul.HealthyGYM.jwt.JwtExceptionFilter;
import com.mul.HealthyGYM.jwt.TokenProvider;

//@PropertySource("classpath:/application-oauth.properties")
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private TokenProvider tokenProvider;
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	public SecurityConfig(TokenProvider tokenProvider,JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
		this.tokenProvider = tokenProvider;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Cross-Site-Request-Forgery 웹 브라우저가 신뢰할 수 없는 악성 사이트에서 사용자가 원치않는 작업을 수행하는 공격
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
		
		http.csrf().disable() // 쿠키에 의존하지 않고 OAuth2.0, JWT를 사용하는 REST API의 경우 CSRF 보호가 필요하지 않음
			
			.exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)
            
            .and()	// 세션을 생성하지 않고, 요청마다 새로운 인증을 수행하도록 구성하는 옵션으로 REST API와 같은 환경에서 사용
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
			.and()
			.formLogin().disable()
			.httpBasic().disable()
			.authorizeHttpRequests().anyRequest().permitAll(); // 모든 요청에 대해서 허용
			// 여기에 관리자페이지에 대한 권한 정의 .requestMatchers("/admin/**").hasRole("ADMIN")
		
			
		http.addFilterBefore(new JwtAuthenticationFilter(tokenProvider), 
								UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class);
		return http.build();
	}
}
