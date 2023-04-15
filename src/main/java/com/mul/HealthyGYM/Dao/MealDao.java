package com.mul.HealthyGYM.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MealDao {

	@Autowired
	SqlSession sqlSession;
	
	String ns = "Meal.";
	
	public int allGet() {
		return sqlSession.selectOne(ns + "allGet");
	}
}
