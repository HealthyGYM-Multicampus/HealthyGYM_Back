package com.mul.HealthyGYM.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mul.HealthyGYM.Dao.MessageDao;
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

	public void readthemessage(int target, int memberseq) {
		msgdao.readthemessage(target, memberseq);
		
	}

	public boolean sendmessage(int target, int memberseq, String writemessage) {

		int count = msgdao.sendmessage(target, memberseq, writemessage);
		return count > 0 ? true:false;
	}

}
