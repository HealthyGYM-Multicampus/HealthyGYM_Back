package com.mul.HealthyGYM.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.RefreshTokenDto;

@Mapper
@Repository
public interface RefreshTokenDao {
	
	RefreshTokenDto findByEmail(String email);
	int save(RefreshTokenDto dto);
	int delete(String email);
}
