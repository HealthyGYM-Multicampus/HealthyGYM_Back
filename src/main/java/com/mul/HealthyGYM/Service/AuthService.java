package com.mul.HealthyGYM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.MemberDao;
import com.mul.HealthyGYM.Dao.RefreshTokenDao;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.RefreshTokenDto;
import com.mul.HealthyGYM.Dto.TokenDto;
import com.mul.HealthyGYM.jwt.TokenProvider;

@Service
@Transactional
public class AuthService {
	
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	private MemberDao memberDao;
	private PasswordEncoder passwordEncoder;
	private TokenProvider tokenProvider;
	private RefreshTokenDao refreshTokenDao;
	
	@Autowired
	public AuthService(AuthenticationManagerBuilder authenticationManagerBuilder, MemberDao memberDao, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, RefreshTokenDao refreshTokenDao) {
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.memberDao = memberDao;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
		this.refreshTokenDao = refreshTokenDao;
	}
	
	public boolean signup(MemberDto dto) {
		if(memberDao.existsByEmail(dto.getEmail()) > 0) {
			//throw new RuntimeException("이미 가입되어 있는 유저입니다");
			return false;
		}
		
		dto.setPwd(passwordEncoder.encode(dto.getPwd()));
		dto.setAuthority("ROLE_USER");
		
		return memberDao.signup(dto) > 0;
	}
	
	public TokenDto login(MemberDto dto) {
		// 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPwd());
        System.out.println("1 AuthService login");
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("2");
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        System.out.println("3");
        // 4. RefreshToken 저장
        RefreshTokenDto refreshToken = new RefreshTokenDto(authentication.getName(), tokenDto.getRefreshToken());
        refreshTokenDao.delete(dto.getEmail());
        refreshTokenDao.save(refreshToken);
        
        System.out.println("4");
        // 5. 토큰 발급
        return tokenDto;
	}
	
	public TokenDto reissue(TokenDto tokenDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshTokenDto refreshToken = refreshTokenDao.findByEmail(authentication.getName());
        		
        if (refreshToken == null) {
        	throw new RuntimeException("로그아웃 된 사용자입니다.");
        }
        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getRvalue().equals(tokenDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성 & 기존 Refresh Token 삭제
        TokenDto newTokenDto = tokenProvider.generateTokenDto(authentication);
        refreshTokenDao.delete(refreshToken.getEmail());

        // 6. 저장소 정보 업데이트
        RefreshTokenDto newRefreshToken = new RefreshTokenDto(refreshToken.getEmail(), newTokenDto.getRefreshToken());
        refreshTokenDao.save(newRefreshToken);
        // 토큰 발급
        return newTokenDto;
    }
}
