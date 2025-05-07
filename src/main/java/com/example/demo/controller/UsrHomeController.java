package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
