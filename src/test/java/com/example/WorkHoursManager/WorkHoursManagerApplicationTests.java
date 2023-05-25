package com.example.WorkHoursManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class WorkHoursManagerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
	//獲取API資料
	@SuppressWarnings("unchecked")
	@Test
	public void getApiTest() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> res = restTemplate.getForEntity(
				"https://opendata.hccg.gov.tw/API/v3/Rest/OpenData/704FC0B2EE7500E4?take=10&skip=0", String.class);
		
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody());
		String resStr = res.getBody();
		List<Map<String, String>> mapList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			mapList = objectMapper.readValue(resStr, List.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		for(Map<String, String>item : mapList) {
			for(Entry<String, String> map : item.entrySet()) {
				System.out.println("key: " + map.getKey());
				System.out.println("value: " + map.getValue());
				System.out.println();
			}
			System.out.println("===========");
		}
	}
	
	//獲取API資料
	@Test
	public void postApiTest() {
		RestTemplate restTemplate = new RestTemplate();
		//set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//set reqBody
		Map<String, String> map = new HashMap<>();
		map.put("account", "AAA9487");
		map.put("pwd", "麥當勞歡樂送");
		//將map轉字串
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String reqStr = objectMapper.writeValueAsString(map);
			HttpEntity<String> reqBody = new HttpEntity<String>(reqStr, headers);
			ResponseEntity<String> res = restTemplate.postForEntity(
					"http://192.168.8.163:8080/api/register",reqBody, String.class);
			System.out.println(res.getStatusCode());
			System.out.println(res.getBody());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
			
	}

}
