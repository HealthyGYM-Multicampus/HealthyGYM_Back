package com.mul.HealthyGYM.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mul.HealthyGYM.Dto.BbsCommentDto;
import com.mul.HealthyGYM.Dto.BbsDto;
import com.mul.HealthyGYM.Dto.BbsFoodDto;
import com.mul.HealthyGYM.Dto.FoodDto;
import com.mul.HealthyGYM.Dto.MealCommentMemberDto;
import com.mul.HealthyGYM.Service.MealService;



@RestController
public class MealController {

	@Autowired
	MealService mealservice;
	
	@GetMapping(value = "allGet")
	public int allGet() {
		return mealservice.allGet();
	}
	
	@GetMapping(value = "FindMealList")
	public List<FoodDto> FindMealList(String search, int pageNo) throws IOException{
		
		// System.out.println(search);
		// System.out.println(pageNo);
		String pageNoStr = String.valueOf(pageNo);
		System.out.println(pageNoStr);
		
		
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); //URL
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=aod5N32JX4OSOPRMyQPTw8VUZxR9zvbrkDLsrM%2BdNRtHP%2BuVlr31Np4fWwVVYm131hApK%2FB4auwWMKzrMmqMhg%3D%3D"); //Service Key
        urlBuilder.append("&" + URLEncoder.encode("desc_kor","UTF-8") + "=" + URLEncoder.encode(search, "UTF-8")); //식품이름
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNoStr, "UTF-8")); //페이지번호
        
        
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); //한 페이지 결과 수
        urlBuilder.append("&" + URLEncoder.encode("bgn_year","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); //구축년도
        urlBuilder.append("&" + URLEncoder.encode("animal_plant","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); //가공업체
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); //응답데이터 형식(xml/json) Default: xml
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        System.out.println(sb.toString());
        
        String jsonString = sb.toString(); // replace with actual JSON string
        
        List<FoodDto> FoodDtoList = new ArrayList<>();
        
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject body = jsonObject.getJSONObject("body");
        
        
        if(body.has("items")) {
        	JSONArray items = body.getJSONArray("items");
            for(int i=0; i<items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                FoodDto dto = new FoodDto();
                dto.setDesckor(item.getString("DESC_KOR"));
                dto.setServingwt(item.getString("SERVING_WT"));
                dto.setNutrcont1(item.getString("NUTR_CONT1"));
                dto.setNutrcont2(item.getString("NUTR_CONT2"));
                dto.setNutrcont3(item.getString("NUTR_CONT3"));
                dto.setNutrcont4(item.getString("NUTR_CONT4"));
                dto.setNutrcont5(item.getString("NUTR_CONT5"));
                dto.setNutrcont6(item.getString("NUTR_CONT6"));
                dto.setNutrcont7(item.getString("NUTR_CONT7"));
                dto.setNutrcont8(item.getString("NUTR_CONT8"));
                dto.setNutrcont9(item.getString("NUTR_CONT9"));
                dto.setBgnyear(item.getString("BGN_YEAR"));
                dto.setAnimalplant(item.getString("ANIMAL_PLANT"));
                FoodDtoList.add(dto); // 리스트에 Dto 추가
            }
        }
        else {
        	return FoodDtoList;
        }
        

        
//        for (FoodDto foodDto : FoodDtoList) {
//			System.out.println(foodDto);
//		}
        
        return FoodDtoList;
	}
	
	@PostMapping(value = "/writemeal1")
	public int writemeal1(BbsDto bbsdto) {
		System.out.println("writemeal1 " + new Date());
		// System.out.println(bbsdto.toString());
		
		
		return mealservice.writemeal1(bbsdto);
			
		
		
		
		
	}
	
	@PostMapping(value = "/writemeal2", consumes = "application/json")
	public String writemeal2(@RequestBody String selectedItemsJson, int bbsseq) throws IOException {
		
		
		System.out.println("writemeal2 " + new Date());
		
		ObjectMapper objectMapper = new ObjectMapper();
        List<FoodDto> foodDtoList = objectMapper.readValue(selectedItemsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, FoodDto.class));
        System.out.println("writemeal2 : "+foodDtoList.toString());
        
        /*
        for(FoodDto food : foodDtoList) {
            System.out.println("desckor: " + food.getDesckor());
            System.out.println("servingwt: " + food.getServingwt());
            System.out.println("nutrcont1: " + food.getNutrcont1());
            System.out.println("nutrcont2: " + food.getNutrcont2());
            System.out.println("nutrcont3: " + food.getNutrcont3());
            System.out.println("nutrcont4: " + food.getNutrcont4());
            System.out.println("nutrcont5: " + food.getNutrcont5());
            System.out.println("nutrcont6: " + food.getNutrcont6());
            System.out.println("nutrcont7: " + food.getNutrcont7());
            System.out.println("nutrcont8: " + food.getNutrcont8());
            System.out.println("nutrcont9: " + food.getNutrcont9());
            System.out.println("bgnyear: " + food.getBgnyear());
            System.out.println("animalplant: " + food.getAnimalplant());
            System.out.println("------------------------------");
        }
        */
        
        if(mealservice.writemeal2(foodDtoList, bbsseq)) {
        	return "OK";
        } else {
        	return "FALSE";
        }
	}
	
	@GetMapping("/posts")
	public List<BbsFoodDto> getPosts(@RequestParam int page, @RequestParam int limit, @RequestParam int memberseq, @RequestParam String search, @RequestParam String select){
		
		// System.out.println("page : " + page + " limit : " + limit);
		// List<BbsDto>
		
		//	System.out.println(search);
		//	System.out.println(select);
		
		
		
		return mealservice.getPosts(page, limit, memberseq, search, select);
		
	}
	
	@PostMapping("/likemealpost")
	public String likemealpost(int bbsseq, int memberseq) {
	
		if(mealservice.likemealpost(bbsseq, memberseq)) {
			return "Success";
		}
		else {
			return "Fail";
		}
		
		
	}
	
	//updatemeal post 추가 해야..
	
	
	@PostMapping("/deletemealpost")
	public String deletemealpost(int bbsseq) {
		
		if(mealservice.deletemealpost(bbsseq)) {
			return "Success";
		}
		else {
			return "Fail";
		}
	}
	
	
	
	// 댓글 작성
	@PostMapping("/wrtiemealcomment")
	public String wrtiemealcomment(@RequestBody Map<String, Object> requestBody) {
	    int bbsseq = (Integer) requestBody.get("bbsseq");
	    int memberseq = (Integer) requestBody.get("memberseq");
	    String commentcontent = (String) requestBody.get("commentcontent");
	    
	    // System.out.println("bbsseq: " + bbsseq + " memberseq: " + memberseq + " comtcnt: " + commentcontent);
	    
	    if (mealservice.wrtiemealcomment(bbsseq, memberseq, commentcontent)) {
	        return "Success";
	    } else {
	        return "Fail";
	    }
	}
	
	// 대댓글 작성
	@PostMapping("/wrtiemealcomment2")
	public String wrtiemealcomment2(@RequestBody Map<String, Object> requestBody) {
	    int bbsseq = (Integer) requestBody.get("bbsseq");
	    int memberseq = (Integer) requestBody.get("memberseq");
	    String commentcontent = (String) requestBody.get("commentcontent");
	    int ref = (Integer) requestBody.get("ref");
	    
	    System.out.println("bbsseq: " + bbsseq + " memberseq: " + memberseq + " comtcnt: " + commentcontent + " ref: " + ref);
	    
	    if (mealservice.wrtiemealcomment2(bbsseq, memberseq, commentcontent, ref)) {
	        return "Success";
	    } else {
	        return "Fail";
	    }
	}
	
	
	@GetMapping("/getmealcomments")
	public List<MealCommentMemberDto> getmealcomments(int bbsseq, int memberseq) {
		// 현재 BbsCommentDto를 반환중. 이미지와 같이 반환하도록 나중에 수정해야함.
		
		return mealservice.getmealcomments(bbsseq, memberseq);
	}
	





	

	
	
}


