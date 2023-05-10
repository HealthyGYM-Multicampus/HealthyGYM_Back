package com.mul.HealthyGYM.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.BbsParam;
import com.mul.HealthyGYM.Dto.MateDto;
import com.mul.HealthyGYM.Dto.MateParam;
import com.mul.HealthyGYM.Service.HealthService;

@RestController
@RequestMapping("/mate")
public class HealthController {
	
	@Autowired
	HealthService service;
	
	@GetMapping("/getlist")
	public List<Map<String, Object>> getList(MateParam param, boolean[] bodypart) {
		System.out.println("mate getList " + new Date());
		
		String[] body = {"back", "chest", "shoulder", "arm", "abs", "leg", "run"};
		
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < bodypart.length; i++) {
			if(bodypart[i]) list.add(body[i]);
		}
		param.setBodypart(list.toArray(new String[0]));
		
		int page = param.getPage();
		param.setStart(1 + (page * 3));
		param.setEnd((page + 1) * 3);
		
		System.out.println(param.toString());
		return service.getList(param);
	}
	
	@GetMapping("/getdetail")
	public List<Map<String, Object>> getDetail(int bbsseq) {
		System.out.println("mate getDetail ");
		
		return service.getDetail(bbsseq);
	}
	
	@PostMapping("/write")
	public boolean writeMate(BbsDto bbsdto, MateDto dto, boolean[] bodypart) {
		System.out.println("writeMate ");
		
		MateDto matedto = new MateDto(0, dto.getAddressfirst(), dto.getAddresssecond(), 
									dto.getCenter(), dto.getMdate(), dto.getMtime(), 
									bodypart[0], bodypart[1], bodypart[2], bodypart[3], 
									bodypart[4], bodypart[5], bodypart[6]);
		
		return service.writeMatebbs(bbsdto, matedto);
	}
}
