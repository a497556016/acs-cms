package com.henede.auth.token;

import io.jsonwebtoken.Claims;

public class ResponseStatus {
	private int code;
	private String msg;
	private Claims claims;
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
	
	public Claims getClaims() {
		return claims;
	}
	public void setClaims(Claims claims) {
		this.claims = claims;
	}
	public void set(int code,String msg) {
		this.setCode(code);
		this.setMsg(msg);
	}
	@Override
	public String toString() {
		return "ResponseStatus [code=" + code + ", msg=" + msg + "]";
	}

}
