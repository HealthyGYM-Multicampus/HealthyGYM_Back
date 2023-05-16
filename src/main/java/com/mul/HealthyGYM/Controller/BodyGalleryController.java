package com.mul.HealthyGYM.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mul.HealthyGYM.Dto.BbsCommentDto;
import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.BbsParam;
import com.mul.HealthyGYM.Dto.LikeDto;
import com.mul.HealthyGYM.Service.BodyGalleryService;

@RestController
@RequestMapping("/BodyGallery")
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
	
	@PostMapping(value = "/saveBody")
	public ResponseEntity<String> saveBody(@RequestBody BbsDto dto) {
		System.out.println("saveBody 게시글 저장");
		service.saveBody(dto);
		return ResponseEntity.ok("Successfully saved");
	}

	@PostMapping(value = "/updateBody")
	public ResponseEntity<String> updateBody(@RequestBody BbsDto dto) {
		System.out.println("updateBody 게시글 수정");
		service.updateBody(dto);
		return ResponseEntity.ok("Successfully updated");
	}

	@PostMapping(value = "/deleteBodyById/{bbsseq}")
	public ResponseEntity<String> deleteBodyById(@PathVariable Integer bbsseq) {
		System.out.println("deleteBodyById 게시글 삭제");
		service.deleteBodyById(bbsseq);
		return ResponseEntity.ok("Successfully deleted");
	}
	
	@GetMapping(value = "/findBodyById/{bbsseq}")
	public Map<String, Object> findBodyById(@PathVariable Integer bbsseq) {
        System.out.println("findBodyById 상세 게시글 조회");
        service.updateBodyReadcount(bbsseq);
        return service.findBodyById(bbsseq);
	}

	@GetMapping(value = "/findBodyLike/{bbsseq}")
	public int findBodyLike(@RequestParam(name = "memberseq") Integer memberseq, @PathVariable Integer bbsseq) {
		System.out.println("findBodyLike 좋아요 확인");
		LikeDto likeDto = new LikeDto();
		likeDto.setMemberseq(memberseq);
		likeDto.setBbsseq(bbsseq);
		return service.findBodyLike(likeDto);
	}

	@PostMapping(value = "/saveBodyLike/{bbsseq}")
	public ResponseEntity<String> saveBodyLike(@RequestBody LikeDto likeDto) {
		System.out.println("saveBodyLike 좋아요 등록");
		service.saveBodyLike(likeDto);
		return ResponseEntity.ok("Successfully saved");
	}

	@PostMapping(value = "/deleteBodyLike/{bbsseq}")
	public ResponseEntity<String> deleteBodyLike(@RequestParam(name = "memberseq") Integer memberseq, @PathVariable Integer bbsseq) {
	    System.out.println("deleteBodyLike 좋아요 삭제");
	    LikeDto likeDto = new LikeDto();
	    likeDto.setMemberseq(memberseq);
	    likeDto.setBbsseq(bbsseq);
	    service.deleteBodyLike(likeDto);
	    return ResponseEntity.ok("Successfully deleted");
	}

	@PostMapping(value = "/updateBodyReport/{bbsseq}")
	public ResponseEntity<String> updateBodyReport(@PathVariable Integer bbsseq) {
		System.out.println("updateBodyReport 게시글 신고");
		service.updateBodyReport(bbsseq);
		return ResponseEntity.ok("Successfully updated");
	}
	
	@PostMapping(value = "/saveBodyComment")
    public ResponseEntity<String> saveBodyComment(@RequestBody BbsCommentDto commentDto) {
		System.out.println("saveBodyComment 댓글 작성");
		service.saveBodyComment(commentDto);
        return ResponseEntity.ok("Successfully Comment saved");
    }

    @PostMapping(value = "/saveBodyReply")
    public ResponseEntity<String> saveBodyReply(@RequestBody BbsCommentDto commentDto) {
    	System.out.println("saveBodyReply 대댓글 작성");
    	service.saveBodyComment(commentDto);
        return ResponseEntity.ok("Successfully Reply saved");
    }

    @GetMapping(value = "/findAllBodyComment/{bbsseq}")
    public ResponseEntity<List<Map<String, Object>>> findAllBodyComment(@PathVariable int bbsseq) {
    	System.out.println("findAllBodyComment 댓글 리스트 조회");
    	List<Map<String, Object>> comments = service.findAllBodyComment(bbsseq);
        return ResponseEntity.ok(comments);
    }

    @PostMapping(value = "/updateBodyComment")
    public ResponseEntity<String> updateBodyComment(@RequestBody BbsCommentDto commentDto) {
    	System.out.println("updateBodyComment 댓글 수정");
    	service.updateBodyComment(commentDto);
        return ResponseEntity.ok("Successfully Comment updated");
    }
    
    @PostMapping(value = "/updateComment/{commentseq}")
    public ResponseEntity<String> updateComment(@PathVariable int commentseq, @RequestBody BbsCommentDto commentDto) {
    	System.out.println("updateComment 대댓글이 있는 경우 댓글 수정");
        service.updateComment(commentDto);
        return ResponseEntity.ok("Successfully Comment updated");
    }

    @PostMapping(value = "/deleteCommentWithoutReply/{commentseq}")
    public ResponseEntity<String> deleteCommentWithoutReply(@PathVariable int commentseq) {
    	System.out.println("deleteCommentWithoutReply 대댓글이 없는 경우 댓글 삭제");
    	service.deleteCommentWithoutReply(commentseq);
        return ResponseEntity.ok("Successfully Comment deleted");
    }

    @PostMapping(value = "/updateReply")
    public ResponseEntity<String> updateReply(@PathVariable int replyseq, @RequestBody BbsCommentDto commentDto) {
    	System.out.println("updateReply 부모댓글이 있는 경우 대댓글 수정");
        service.updateReply(commentDto);
        return ResponseEntity.ok("Successfully Reply updated");
    }

    @PostMapping(value = "/deleteReplies/{commentseq}")
    public ResponseEntity<String> deleteReplies(@PathVariable int commentseq) {
    	System.out.println("deleteReplies 댓글과 대댓글 일괄 삭제");
    	service.deleteAllReplies(commentseq);
        return ResponseEntity.ok("Successfully Replies deleted");
    }
	
}
