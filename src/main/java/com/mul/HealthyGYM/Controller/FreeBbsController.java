package com.mul.HealthyGYM.Controller;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mul.HealthyGYM.Dto.BbsCommentDto;
import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.BbsParam;
import com.mul.HealthyGYM.Service.FreeBbsService;
import com.mul.HealthyGYM.Util.FileUtility;


@RestController
public class FreeBbsController {
	
	@Autowired
	FreeBbsService service;
	
	@GetMapping("/bbslist")
	public List<Map<String, Object>> bbslist(BbsParam param) {
		System.out.println("bbslist " + new Date());
		
		int page = param.getPage();
		param.setStart(1 + (page * 3));
		param.setEnd((page + 1) * 3);
		
		return service.bbsList(param);
	}
	
	@GetMapping("/freebbsdetail")
	public List<Map<String, Object>> bbsdetail(BbsDto dto) {
		System.out.println("freebbsdetail " + dto.getBbsseq() + " "+ dto.getMemberseq()+" " + new Date());
		
		// 게시글 상세 정보
		List<Map<String, Object>> detail = service.bbsDetail(dto.getBbsseq());

		// 로그인한 유저의 좋아요 여부
		boolean liking = false;
		if(dto.getMemberseq() != 0) {
			liking = service.checkLiking(dto);
		}
		detail.get(0).put("liking", liking);
		
		return detail;
	}
	
	@GetMapping("/freebbscomment")
	public List<Map<String, Object>> bbscomment(int bbsseq) {
		System.out.println("freebbscomment "+ bbsseq + " " + new Date());
		
		return service.bbsComment(bbsseq);
	}
	
	
	@PostMapping("/writefreebbs")
	public String writefreebbs(BbsDto dto) {
		System.out.println("writefreebbs " + new Date());
		System.out.println(dto.toString());
		
		if(service.writeBbs(dto)) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	@PostMapping("/writebbscomment")
	public String writebbscomment(BbsCommentDto dto) {
		System.out.println("writebbscomment " + new Date());
		
		if(service.writeBbsComment(dto)) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	@PostMapping("/writebbsreply")
	public String writebbsreply(BbsCommentDto dto) {
		System.out.println("writebbsreply " + new Date());
		
		if(service.writeBbsReply(dto)) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	@PostMapping("/likebbs")
	public String likebbs(BbsDto dto) {
		System.out.println("likebbs " + new Date());
		
		if(service.likeBbs(dto) & service.likecountUp(dto.getBbsseq())) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	@PostMapping("/unlikebbs")
	public String unlikebbs(BbsDto dto) {
		System.out.println("unlikebbs " + new Date());
		
		if(service.unlikeBbs(dto) & service.likecountDown(dto.getBbsseq())) {
			return "OK";
		} else {
			return "NO";
		}
	}

	@PostMapping("/deletebbs")
	public String deletebbs(int bbsseq) {
		System.out.println("deletebbs  " + new Date());
		
		if(service.deleteBbs(bbsseq)) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	@PostMapping("/updatefreebbs")
	public String updatebbs(BbsDto dto) {
		System.out.println("updatefreebbs  " + new Date());
		
		if(service.updateBbs(dto)) {
			return "OK";
		} else {
			return "NO";
		}
	}

}
