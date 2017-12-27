package com.henede.admin.bean;

public class Result {
	private int code;
	private String msg;
	private Object data;
	
	public static Result success(String msg,Object data) {
		Result result = new Result();
		result.setCode(1);
		result.setData(data);
		result.setMsg(null==msg?"执行成功":msg);
		return result;
	}
	
	public static Result success(String msg) {
		return Result.success(msg, null);
	}
	
	public static Result success(Object data) {
		return Result.success(null, data);
	}
	
	public static Result success() {
		return Result.success(null, null);
	}
	
	public static Result error(String msg) {
		Result result = new Result();
		result.setCode(0);
		result.setMsg("执行失败"+(null==msg?"！":"，"+msg+"！"));
		return result;
	}
	
	public static Result error() {
		return Result.error(null);
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
