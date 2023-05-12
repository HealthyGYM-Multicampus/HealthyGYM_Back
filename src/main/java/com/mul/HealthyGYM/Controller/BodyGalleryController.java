package com.mul.HealthyGYM.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.BbsParam;
import com.mul.HealthyGYM.Service.BodyGalleryService;

@RestController
public class BodyGalleryController {
	
	@Autowired
	BodyGalleryService service;
	
	// 전체 게시글 조회
	@GetMapping(value = "/findAllBody")
	public List<Map<String, Object>> findAllBody(BbsParam param) {
		System.out.println("findAllBody 실행");
		
		// 글의 시작과 끝
		int page = param.getPage();			// 0 1 2 3 4 
		param.setStart(1 + (page * 10));	// 1 	11
		param.setEnd((page + 1) * 10);		// 10	20
		
		return service.findAllBody(param);
	}
	
	// 게시글 등록
	@PostMapping(value = "/saveBody")
	public String saveBody(BbsDto dto) {
		System.out.println("saveBody 실행");
		boolean b = service.saveBody(dto);
		if(b) {
			return "OK";
		}
		return "NO";
	}
	
	// 게시글 수정
	@PostMapping(value = "/updateBody")
	public String updateBody(int bbsseq, BbsDto dto) {
		System.out.println("updateBody 실행");
		boolean b = service.updateBody(dto);
		if(b) {
			return "OK";
		}
		return "NO";
	}
	
	// 게시글 삭제
	@GetMapping(value = "/deleteBody")
	public String deleteBody(int bbsseq) {
		System.out.println("deleteBody 실행");
		boolean b = service.deleteBodyById(bbsseq);
		if(b) {
			return "OK";
		}
		return "NO";
	}
	
	// 상세 게시글 조회 : 조회수 증가되는 지 확인 필요
	@GetMapping(value = "/findBodyById")
	public Map<String, Object> findBodyById(int bbsseq) {
		System.out.println("findBodyById 실행");
		service.updateBodyReadcount(bbsseq);
		return service.findBodyById(bbsseq);
	}
	
}
