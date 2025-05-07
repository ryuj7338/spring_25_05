package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrHomeController {
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String ShowMain() {
		return "집가고 싶다";
	}
	
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String ShowMain2() {
		return "집 가려면 6시간 20분 남았다..";
	}
	
	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public int ShowMain3() {
		
		return 10+8;
	}
	
	int a = 0;
	@RequestMapping("/usr/home/getCount")
	@ResponseBody
	public int getCount() {
//		int a = 0; 여기다 선언x
		return a++;
		
	}
	
	
	@RequestMapping("/usr/home/setCount")
	@ResponseBody
	public int setCount() {
		a = 0;
		return a;
	}
	
	@RequestMapping("/usr/home/setCountValue")
	@ResponseBody
	public String setCountValue(int value) {
		this.a = value;
		return "a의 값을"+ value+"(으)로 초기화";
	}
	
	@RequestMapping("/usr/home/getInt")
	@ResponseBody
	public int getInt() {
		
		return 11;
	}
	
	@RequestMapping("/usr/home/getString")
	@ResponseBody
	public String getString() {
		
		return "kk";
	}
	
	
	@RequestMapping("/usr/home/getMap")
	@ResponseBody
	public Map<String, Object> getMap() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("탄지로 나이", 15);
		map.put("젠이츠 나이", 16);		
		return map;
	}
	
	
	@RequestMapping("/usr/home/getDouble")
	@ResponseBody
	public double getDouble() {
		
		return 4656.654;
	}
	
	
	@RequestMapping("/usr/home/getBoolean")
	@ResponseBody
	public Boolean getBoolean() {
		
		return false;
	}
	
	@RequestMapping("/usr/home/getList")
	@ResponseBody
	public List<String> getList() {
		
		List<String> list = new ArrayList<>();
		list.add("히노카미 카구라");
		return list;
	
	}
	
	@RequestMapping("/usr/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		
		Article article = new Article("탄지로", 15, "해의 호흡"); 
		return article;
	
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Article {
	
	String name;
	int age;
	String content;
	
}
