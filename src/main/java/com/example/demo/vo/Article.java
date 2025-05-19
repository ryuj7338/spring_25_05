package com.example.demo.vo;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {

	
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int boardId;
	private String title;
	private String body;
//	조회수
	private int hit;
//	좋아요 & 싫어요
	private int like;
	private int dislike;
	
	private String extra__writer;
	private boolean userCanModify;
	public boolean userCanDelete;
	
	private String searchKeyword;
	private String searchType;
	
	
}
