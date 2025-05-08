package com.example.demo.vo;

import lombok.Data;
import lombok.Getter;


public class ResultData {
	
	@Getter
	private String ResultCode;
	@Getter
	private Object msg;
	@Getter
	private Object data;
	
	
//	오류메세지(자세하게)
	public static ResultData from(String ResultCode, String msg) {
		return from(ResultCode, msg, null);
	}

	public static ResultData from(String ResultCode, String msg, Object data) {
		ResultData rd = new ResultData();
		rd.ResultCode = ResultCode;
		rd.msg = msg;
		rd.data = data;
		
		return rd;
	}
	
// 오류메세지(간단)
	
	public boolean isSuccess() {
		return ResultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}
	
}
