package com.mul.HealthyGYM.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mul.HealthyGYM.Dto.FoodDto;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MessageDto;
import com.mul.HealthyGYM.Dto.MessageFoodDto;
import com.mul.HealthyGYM.Service.MessageService;

@RestController
public class MessageController {
	
	@Autowired
	MessageService msgservice;

	@PostMapping(value = "/getnotreadmsgcnt")
	public int getnotreadmsgcnt(int memberseq) {
		
		int n = msgservice.getnotreadmsgcnt(memberseq);
		
		return n;
	}
	
	@PostMapping(value = "/getmessages")
	public  MessageFoodDto getmessages(int target, int memberseq){
		
		List<MessageDto> dto = msgservice.getmessages(target, memberseq);
		
		List<FoodDto> dto2 = msgservice.recommendmealmsgrecv(target, memberseq);	// target이 memberseq인거 찾기
		
//		System.out.println(dto.toString());
//		System.out.println(dto2.toString());
		
		MessageFoodDto msgmealdto = new MessageFoodDto(dto, dto2);
		
//		System.out.println(msgmealdto.toString());
		
		
		
		return msgmealdto;
	}
	
	// 대화목록
	@PostMapping(value = "/talkingmemberlist")
	public List<MemberDto> talkingmemberlist(int memberseq){
		
		List<MemberDto> dto = msgservice.talkingmemberlist(memberseq);

		
		return dto;
	}
	
	@PostMapping(value="/readthemessage")
	public void readthemessage(int target, int memberseq) {
		// target이 보낸 메시지를 memberseq가 읽음.
		// 따라서 memberseq가 2이고 target이 1인 db를 읽음처리해야함.
//	  System.out.println("셀렉메시지 : " + target);
//	  System.out.println("멤버시퀀스 : " + memberseq);
		
		msgservice.readthemessage(target, memberseq);
	}
	
	@PostMapping(value="/sendmessage")
	public String sendmessage(int target, int memberseq, String writemessage) {
//		System.out.println("타겟: " + target);
//		System.out.println("멤버시퀀스: " + memberseq);
//		System.out.println(writemessage);
		
		if(msgservice.sendmessage(target, memberseq, writemessage)) {
			return "Success";
		}
		else {
			return "Fail";
		}
		

	}
	
	@PostMapping("/recommendmealmsgsend")
	public String recommendmealmsgsend(@RequestBody Map<String, Object> requestData) throws IllegalArgumentException, JsonMappingException, JsonProcessingException {
	    int memberseq = (int) requestData.get("memberseq");
	    String writemessage = (String) requestData.get("writemessage");
	    int target = (int) requestData.get("target");

	    
	    ObjectMapper objectMapper = new ObjectMapper();

	    List<FoodDto> selectedMorning = objectMapper.readValue(
	        objectMapper.writeValueAsString(requestData.get("selectedMorning")),
	        objectMapper.getTypeFactory().constructCollectionType(List.class, FoodDto.class));
//	    System.out.println(selectedMorning.size());

	    List<FoodDto> selectedLunch = objectMapper.readValue(
	        objectMapper.writeValueAsString(requestData.get("selectedLunch")),
	        objectMapper.getTypeFactory().constructCollectionType(List.class, FoodDto.class));
//	    System.out.println(selectedLunch.size());

	    List<FoodDto> selectedDinner = objectMapper.readValue(
	        objectMapper.writeValueAsString(requestData.get("selectedDinner")),
	        objectMapper.getTypeFactory().constructCollectionType(List.class, FoodDto.class));
//	    System.out.println(selectedDinner.size());

	    
	    
	    if(msgservice.recommendmealmsgsend(memberseq, writemessage, target, selectedMorning, selectedLunch, selectedDinner)) {
	    	return "쪽지가 전송되었습니다!";
	    }
	    else {
	    	return "쪽지 전송에 실패했습니다..";
	    }
	    
	}

}
