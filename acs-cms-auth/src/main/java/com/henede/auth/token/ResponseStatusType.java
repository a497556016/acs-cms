package com.henede.auth.token;

public enum ResponseStatusType {
	AUTH_SUCCESS(1,"权限验证通过"),
	AUTH_TIME_FAIL(601,"权限验证失败，token已过期"),
	AUTH_TOKEN_FAIL(602,"权限验证失败，token签名异常"),
	AUTH_TOKEN_ERROR(603,"权限验证失败，解析token值失败"),
	AUTH_TOKEN_EMPTY(604,"权限验证失败，token为空"),
	AUTH_NOT_HAVE(605,"权限验证失败，没有访问权限");
	
	
	
	private int code;
	private String msg;
	private ResponseStatusType(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
