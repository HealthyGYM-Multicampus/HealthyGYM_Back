package com.mul.HealthyGYM.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.MemberDao;
import com.mul.HealthyGYM.Dto.MemberDto;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	MemberDao dao;

	@Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("customUserDetails " + username);
		return createUserDetails(dao.findByEmail(username));
    }
	
	 // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(MemberDto member) {
    	//System.out.println(member.getAuthority());
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority());

        return new User(
                String.valueOf(member.getEmail()),
                member.getPwd(),
                Collections.singleton(grantedAuthority)
        );
    }
}
