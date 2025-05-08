package com.example.demo.vo;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
	
	public Article(String title, String body) {
		this.title = title;
		this.body = body;
	}
	
	private int id;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private String title;
	private String body;
}
