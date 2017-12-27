package com.henede.auth.bean;

import java.io.Serializable;

public class SimpleGrantedAuthority implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1267320598714896400L;
	private String auth;
	
	public SimpleGrantedAuthority(String auth) {
		this.auth = auth;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
}
