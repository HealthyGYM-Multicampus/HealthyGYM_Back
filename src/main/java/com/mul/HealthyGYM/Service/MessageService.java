package com.mul.HealthyGYM.Service;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.MessageDao;
import com.mul.HealthyGYM.Dto.FoodDto;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MessageDto;

@Service
@Transactional
public class MessageService {
	
	@Autowired
	private MessageDao msgdao;

	public int getnotreadmsgcnt(int memberseq) {

		return msgdao.getnotreadmsgcnt(memberseq);
	}

	public List<MessageDto> getmessages(int target, int memberseq) {
		
		return msgdao.getmessages(target, memberseq);
	}

	public List<MemberDto> talkingmemberlist(int memberseq) {
		List<MemberDto> dto = msgdao.talkingmemberlist(memberseq);
	    for (MemberDto memberDto : dto) {
	        // memberDto의 isreadcnt 및 lastletter 값을 설정한다.
	    	// memberseq = 로그인한 사람의 memberseq, getter는 해당 for문의 대화목록대상자
	        memberDto.setIsreadcnt(msgdao.isreadcnt(memberseq, memberDto.getMemberseq()));
	        memberDto.setLastletter(msgdao.lastletter(memberseq, memberDto.getMemberseq()));
	        memberDto.setWdate(msgdao.wdate(memberseq, memberDto.getMemberseq()));
	        // System.out.println(memberDto.toString());
	    }
		
		return dto;
	}
	
	public List<FoodDto> recommendmealmsgrecv(int target, int memberseq) {
		List<FoodDto> dto = msgdao.recommendmealmsgrecv(target, memberseq);
		// memberseq => 현재 로그인한사람, 즉 target = #{memberseq}
		
		return dto;
	}

	public void readthemessage(int target, int memberseq) {
		msgdao.readthemessage(target, memberseq);
		
	}

	public boolean sendmessage(int target, int memberseq, String writemessage) {

		int count = msgdao.sendmessage(target, memberseq, writemessage);
		return count > 0 ? true:false;
	}





	public boolean recommendmealmsgsend(int memberseq, String writemessage, int target, List<FoodDto> selectedMorning,
	        List<FoodDto> selectedLunch, List<FoodDto> selectedDinner) {
		// 객체로 만들어서
		MessageDto msgdto = new MessageDto();
		msgdto.setMemberseq(memberseq);
		msgdto.setMessage(writemessage);
		msgdto.setTarget(target);
		
		// mybatis에 접근한다음
		msgdao.sendmessage2(msgdto);
		
		// useGeneratedKeys로 자동증가된 주요키값 가져오기.
		int msgseq = msgdto.getMsgseq();
	    System.out.println("recom의 msgseq: " + msgseq);
	    int n = 0;

	    
	   
	    
	    if(selectedMorning.size() > 0) {
	    for (FoodDto foodDto : selectedMorning) {
	        foodDto.setMsgseq(msgseq);
	        foodDto.setWhenmeal(1);
	        n += msgdao.sendmessage3(foodDto); 
	        System.out.println(foodDto.toString());
	    }
	    }
	    
	    if(selectedLunch.size() > 0) {
	    for (FoodDto foodDto : selectedLunch) { 
	        foodDto.setMsgseq(msgseq);
	        foodDto.setWhenmeal(2);
	        n += msgdao.sendmessage3(foodDto); 
	        System.out.println(foodDto.toString());
	    }
	    }
	    
	    if(selectedDinner.size() > 0) {
	    for (FoodDto foodDto : selectedDinner) {
	        foodDto.setMsgseq(msgseq);
	        foodDto.setWhenmeal(3);
	        n += msgdao.sendmessage3(foodDto);
	        System.out.println(foodDto.toString());
	    }
	    }
	    
	    return n>0?true:false;
	}

	


}
