package com.henede.auth.bean;

import java.io.Serializable;
import java.util.List;

import com.henede.auth.token.TokenData;

public class UserDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7875245729062714774L;
	private String username;
	private String password;
	private String avatar;
	private String email;
	private TokenData tokenData;
	private List<SimpleGrantedAuthority> authorities;
	
	public UserDetails(String username,String password,List<SimpleGrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<SimpleGrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public TokenData getTokenData() {
		return tokenData;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTokenData(TokenData tokenData) {
		this.tokenData = tokenData;
	}

	@Override
	public String toString() {
		return "UserDetails [username=" + username + ", password=" + password + ", avatar=" + avatar + ", email="
				+ email + ", tokenData=" + tokenData + ", authorities=" + authorities + "]";
	}
}
