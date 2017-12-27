package com.henede.auth.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.henede.auth.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.henede.auth.bean.SimpleGrantedAuthority;
import com.henede.auth.bean.UserDetails;
import com.henede.auth.exception.PasswordNotMatchsException;
import com.henede.auth.exception.UsernameNotFoundException;
import com.henede.auth.token.ResponseStatus;
import com.henede.auth.token.TokenData;
import com.henede.auth.token.TokenMgr;

import io.jsonwebtoken.Claims;

@Service
public class UserManager {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenMgr tokenMgr;
	
	public UserDetails login(String username,String password) throws PasswordNotMatchsException, UsernameNotFoundException {
		UserDetails userDetail = userDetailsService.loadUserByUsername(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(passwordEncoder.matches(password, userDetail.getPassword())) {
			TokenData tokenData = tokenMgr.createTokenData(UUID.randomUUID().toString(), userDetail);
			userDetail.setTokenData(tokenData);
			return userDetail;
		}else {
			
			throw new PasswordNotMatchsException("密码错误");
		}
	} 
	
	public TokenData refreshToken(String refreshToken) {
		Gson gson = new Gson();
		
		UserDetails userDetails = null;
		try {
			Claims claims = this.tokenMgr.parseJWT(refreshToken);
			String subject = claims.getSubject();
			userDetails = gson.fromJson(subject, UserDetails.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null==userDetails) {
			return null;
		}
		return this.tokenMgr.createTokenData(UUID.randomUUID().toString(), userDetails);
	}
	
	public boolean hasAuth(String username,String path) {
		try {
			UserDetails userDetails = userDetailsService.getUserByUsername(username);
			List<SimpleGrantedAuthority> authorities = userDetails.getAuthorities();
			Map<String, String> authResMap = userDetailsService.getAuthResource();
			long c = authorities.stream().filter(sga -> null!=authResMap.get(sga.getAuth())&&authResMap.get(sga.getAuth()).contains(path)).count();
			if(c>0) {
				return true;
			}
		} catch (UsernameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public TokenMgr getTokenMgr() {
		return tokenMgr;
	}
}
