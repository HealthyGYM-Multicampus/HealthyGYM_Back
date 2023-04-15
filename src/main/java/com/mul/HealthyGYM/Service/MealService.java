package com.mul.HealthyGYM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mul.HealthyGYM.Dao.MealDao;

@Service
public class MealService {

	@Autowired
	private MealDao MealDao;
	
	public int allGet() {
		return MealDao.allGet();
	}
	
}
