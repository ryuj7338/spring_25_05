package com.example.demo.vo;


import lombok.Getter;


public class ResultData<DT> {
	
	@Getter
	private String ResultCode;
	@Getter
	private String msg;
	@Getter
	private DT data;
	
	
//	오류메세지(자세하게)
	public static <DT> ResultData<DT> from(String ResultCode, String msg) {
		return from(ResultCode, msg, null);
	}

	public static <DT> ResultData<DT> from(String ResultCode, String msg, Object data) {
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
	
	public static <DT> ResultData<DT> newData(ResultData rd, Object newData) {
		return from(rd.getResultCode(), rd.getMsg(), newData);
	}
}
