package com.henede.auth.token;

import java.io.Serializable;

public class TokenData {
	private String accessToken;
	private Long expireTime; 
	private String refreshToken;
	private Long refreshTokenValidTime;
	private Serializable key;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public Long getRefreshTokenValidTime() {
		return refreshTokenValidTime;
	}
	public void setRefreshTokenValidTime(Long refreshTokenValidTime) {
		this.refreshTokenValidTime = refreshTokenValidTime;
	}
	public Serializable getKey() {
		return key;
	}
	public void setKey(Serializable key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "TokenData [accessToken=" + accessToken + ", expireTime=" + expireTime + ", refreshToken=" + refreshToken
				+ ", refreshTokenValidTime=" + refreshTokenValidTime + ", key=" + key + "]";
	}
}
