package com.mul.HealthyGYM.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mul.HealthyGYM.Dto.FoodDto;
import com.mul.HealthyGYM.Dto.MemberDto;
import com.mul.HealthyGYM.Dto.MessageDto;

@Mapper
@Repository
public interface MessageDao {

    int getnotreadmsgcnt(int memberseq);

    List<MessageDto> getmessages(int target, int memberseq);

    List<MemberDto> talkingmemberlist(int memberseq);

    int isreadcnt(int memberseq, int target);

    String lastletter(int memberseq, int target);

    void readthemessage(int target, int memberseq);

    String wdate(int target, int memberseq);

    int sendmessage(int target, int memberseq, String writemessage);

    Integer sendmessage2(MessageDto msgdto);
    
    int sendmessage3(FoodDto foodDto); // 수정

	int getlastmsgseq();

	List<FoodDto> recommendmealmsgrecv(int target, int memberseq);

	

	

	

}
