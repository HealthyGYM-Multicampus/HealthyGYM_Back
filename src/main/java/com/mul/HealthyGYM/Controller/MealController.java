package com.mul.HealthyGYM.Controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

import com.mul.HealthyGYM.Dto.FoodDto;
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
	public List<FoodDto> FindMealList(String search) throws IOException{
		
		// System.out.println(search);
		
		
		
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=aod5N32JX4OSOPRMyQPTw8VUZxR9zvbrkDLsrM%2BdNRtHP%2BuVlr31Np4fWwVVYm131hApK%2FB4auwWMKzrMmqMhg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("desc_kor","UTF-8") + "=" + URLEncoder.encode(search, "UTF-8")); /*식품이름*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        // 당장은 100개까지만 보이도록 했습니다. 100개가 넘어가는 경우결과 창에서 페이지 total count를 가공하여 페이징 해주면 될거같습니다.
        
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("bgn_year","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*구축년도*/
        urlBuilder.append("&" + URLEncoder.encode("animal_plant","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*가공업체*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) Default: xml*/
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
        JSONArray items = body.getJSONArray("items");
        
        for(int i=0; i<items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            FoodDto dto = new FoodDto();
            dto.setDESC_KOR(item.getString("DESC_KOR"));
            dto.setSERVING_WT(item.getString("SERVING_WT"));
            dto.setNUTR_CONT1(item.getString("NUTR_CONT1"));
            dto.setNUTR_CONT2(item.getString("NUTR_CONT2"));
            dto.setNUTR_CONT3(item.getString("NUTR_CONT3"));
            dto.setNUTR_CONT4(item.getString("NUTR_CONT4"));
            dto.setNUTR_CONT5(item.getString("NUTR_CONT5"));
            dto.setNUTR_CONT6(item.getString("NUTR_CONT6"));
            dto.setNUTR_CONT7(item.getString("NUTR_CONT7"));
            dto.setNUTR_CONT8(item.getString("NUTR_CONT8"));
            dto.setNUTR_CONT9(item.getString("NUTR_CONT9"));
            dto.setBGN_YEAR(item.getString("BGN_YEAR"));
            dto.setANIMAL_PLANT(item.getString("ANIMAL_PLANT"));
            FoodDtoList.add(dto); // 리스트에 Dto 추가
        }
        
//        for (FoodDto foodDto : FoodDtoList) {
//			System.out.println(foodDto);
//		}
        
        return FoodDtoList;
	}
	
}
