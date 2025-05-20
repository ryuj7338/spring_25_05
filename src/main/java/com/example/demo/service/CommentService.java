package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository replyRepository;
	
	@Autowired
	private ArticleService articleServivce;
	
	public CommentService(CommentRepository replyRepository) {
		this.replyRepository = replyRepository;
	}
	
}
