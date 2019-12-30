package com.cctv.entity;

public class ResultValue {
	private String  statusCode;  // 结果code
    private String strResult;   // 返回字符串
	
    public ResultValue() {
		super();
	}
    
	public ResultValue(String statusCode, String strResult) {
		super();
		this.statusCode = statusCode;
		this.strResult = strResult;
	}

	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStrResult() {
		return strResult;
	}
	public void setStrResult(String strResult) {
		this.strResult = strResult;
	}
	@Override
	public String toString() {
		return "ResultVO [statusCode=" + statusCode + ", strResult="
				+ strResult + "]";
	}
}
