package com.mul.HealthyGYM.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Service.HomeService;

@RestController
public class HomeController {
	
	@Autowired
	HomeService service;
	
	@GetMapping("homebest")
	public List<BbsDto> homeBest() {
		System.out.println("homeBest ");
		
		return service.homeBest();
	}
	
	@GetMapping("homebbs")
	public List<BbsDto> homeBbs(int bbstag) {
		System.out.println("homeBbs " + bbstag);
		
		return service.homeBbs(bbstag);
	}
}
