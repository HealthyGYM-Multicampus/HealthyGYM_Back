package com.mul.HealthyGYM.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mul.HealthyGYM.Dto.BbsParam;
import com.mul.HealthyGYM.Dto.SearchParam;
import com.mul.HealthyGYM.Service.TopicBestService;

@RestController
public class TopicBestController {
	
	@Autowired
	TopicBestService service;
	
	@GetMapping(value = "/findAllBest")
	public List<Map<String, Object>> findAllBest(BbsParam param){
		System.out.println("findAllBest 실행");
		
		// 글의 시작과 끝
		int page = param.getPage();			// 0 1 2 3 4 
		param.setStart(1 + (page * 10));	// 1 	11
		param.setEnd((page + 1) * 10);		// 10	20
		
		return service.findAllBest(param);
	}
	
	@GetMapping(value = "/findBbsByKeyword")
	public List<Map<String, Object>> findBbsByKeyword(SearchParam search){
		System.out.println("findBbsByKeyword 실행");
		
		// 글의 시작과 끝
		int page = search.getPage();			// 0 1 2 3 4 
		search.setStart(1 + (page * 10));	// 1 	11
		search.setEnd((page + 1) * 10);		// 10	20
		
		if(search.getSearch() == null || search.getSearch().equals("")) {
			search.setSearch("");
		}
		
		return service.findBbsByKeyword(search);
	}

}
