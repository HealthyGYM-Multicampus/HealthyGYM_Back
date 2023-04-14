package com.mul.HealthyGYM.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Service.FreeBbsService;
import com.mul.HealthyGYM.Util.FileUtility;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FreeBbsController {
	
	@Autowired
	FreeBbsService service;
	
	@GetMapping("/freebbsdetail")
	public BbsDto freebbsdetail(int bbs_seq) {
		System.out.println("freebbsdetail " + new Date());
		return service.freeBbsDetail(bbs_seq);
	}
	
	@PostMapping("/writefreebbs")
	public String writefreebbs(BbsDto dto) {
		System.out.println("writefreebbs " + new Date());
		if(service.writeBbs(dto)) {
			return "OK";
		} else {
			return "NO";
		}
	}
	
	// 사용 안함 (파이어베이스로 대체함)
	@PostMapping("/uploadfile")
	public String uploadfile(@RequestParam(value = "imgfile", required = false) MultipartFile imgfile, HttpServletRequest req) throws IOException {
		
		System.out.println("uploadfile " + new Date());
		
		// filename 취득
		String filename = imgfile.getOriginalFilename();
		String filecheck = filename.substring(filename.indexOf('.')); // 확장자 제한
		int filesize = imgfile.getBytes().length; // 파일크기 제한
		
		if (filecheck.equals(".png") || filecheck.equals(".jpg")) {
			if (filesize < 1048577) { // 1MB
				
				// String fupload = req.getServletContext().getRealPath("/upload");
				//System.out.println("fupload: " + fupload);
				
				// 파일명을 충돌하지 않는 이름(Date)으로 변경
				String newfilename = FileUtility.getNewFileName(filename);
				String filepath = "C:/upload/" + newfilename;
				File file = new File(filepath);
				
				try {
					// 실제 파일 생성 + 기입 = 업로드
					FileUtils.writeByteArrayToFile(file, imgfile.getBytes());
					
					// db에 저장
					service.uploadFile(newfilename);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
				return "/upload/" + newfilename;
				
			} else {
				return "toobig";
			}
			
		} else {
			return "notimage";
		}
		
	}

}
