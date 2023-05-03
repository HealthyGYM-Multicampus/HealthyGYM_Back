package com.mul.HealthyGYM.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MessageDto;
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
	public  List<MessageDto> getmessages(int target, int memberseq){
		
		List<MessageDto> dto = msgservice.getmessages(target, memberseq);
		
		return dto;
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

}
