package com.henede.auth.token;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(ignoreUnknownFields=false,prefix="token")
public class TokenConfig {
	private Long expireTime;
	private Long refreshExpireTime;
	private String tokenHeadPrefix;
	private String tokenSecret;
	private String tokenHead;
	public Long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
	public Long getRefreshExpireTime() {
		return refreshExpireTime;
	}
	public void setRefreshExpireTime(Long refreshExpireTime) {
		this.refreshExpireTime = refreshExpireTime;
	}
	public String getTokenSecret() {
		return tokenSecret;
	}
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
	public String getTokenHead() {
		return tokenHead;
	}
	public void setTokenHead(String tokenHead) {
		this.tokenHead = tokenHead;
	}
	public String getTokenHeadPrefix() {
		return tokenHeadPrefix;
	}
	public void setTokenHeadPrefix(String tokenHeadPrefix) {
		this.tokenHeadPrefix = tokenHeadPrefix;
	}
	@Override
	public String toString() {
		return "TokenConfig [expireTime=" + expireTime + ", refreshExpireTime=" + refreshExpireTime
				+ ", tokenHeadPrefix=" + tokenHeadPrefix + ", tokenSecret=" + tokenSecret + ", tokenHead=" + tokenHead
				+ "]";
	}
}
