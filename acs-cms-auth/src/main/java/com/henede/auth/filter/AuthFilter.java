package com.henede.auth.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.henede.auth.bean.UserDetails;
import com.henede.auth.service.UserManager;
import com.henede.auth.token.ResponseStatus;
import com.henede.auth.token.ResponseStatusType;
import com.henede.auth.token.TokenMgr;

import io.jsonwebtoken.Claims;

public class AuthFilter implements Filter {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private TokenMgr tokenMgr;
	private UserManager userManager;
	private Gson gson = new Gson();
	private List<String> ignorUrlPatterns;
	private List<String> staticResources;
	
	public AuthFilter(UserManager userManager) {
		this.userManager = userManager;
		this.tokenMgr = this.userManager.getTokenMgr();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String tokenHead = tokenMgr.getTokenConfig().getTokenHead();
		String tokenHeadPrefix = tokenMgr.getTokenConfig().getTokenHeadPrefix();
		
		httpServletResponse.setHeader("Access-Control-Allow-Origin",httpServletRequest.getHeader("Origin"));  
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");  
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");  
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "content-type,x-requested-with,"+tokenHead);  
		
		String requestMethod = httpServletRequest.getMethod();
		if("OPTIONS".equals(requestMethod)) {
			return;
		}
		
		String path = httpServletRequest.getServletPath();
		logger.info("------->>请求地址："+path);
		long ignorCount = ignorUrlPatterns.stream().filter(pattern -> {
			pattern = pattern.replace("*", "[^\\s]+");
			if(path.matches(pattern)) {
				return true;
			}
			return false;
		}).count();
		if(ignorCount>0) {
			logger.info("------->>不需要访问权限控制过滤："+path);
			arg2.doFilter(request, response);
			return;
		}
		long matchCount = staticResources.stream().filter(pattern -> {
			pattern = pattern.replace("*", "/[^\\s]+");
			if(path.matches(pattern)) {
				return true;
			}
			return false;
		}).count();
		if(matchCount>0) {
			logger.info("------->>静态资源过滤："+path);
			arg2.doFilter(request, response);
			return;
		}
		logger.info("------->>开始验证请求地址权限："+path);
		
		
		String token = httpServletRequest.getHeader(tokenHead);
		if(StringUtils.isNotEmpty(token)&&token.startsWith(tokenHeadPrefix+" ")) {
			String tokenValue = token.split(" ")[1];
			ResponseStatus responseStatus = tokenMgr.validateJWT(tokenValue);
			if(responseStatus.getCode()!=ResponseStatusType.AUTH_SUCCESS.getCode()) {
				responseWrite(httpServletResponse, responseStatus);
				return;
			}else {
				//资源权限判断
				Claims claims = responseStatus.getClaims();
				String subject = claims.getSubject();
				UserDetails userDetails = gson.fromJson(subject, UserDetails.class);
				boolean f = this.userManager.hasAuth(userDetails.getUsername(), path);
				if(!f) {
					ResponseStatus responseStatus1 = new ResponseStatus();
					responseStatus1.set(ResponseStatusType.AUTH_NOT_HAVE.getCode(), ResponseStatusType.AUTH_NOT_HAVE.getMsg());
					responseWrite(httpServletResponse, responseStatus1);
					return;
				}
			}
		}else {
			ResponseStatus responseStatus = new ResponseStatus();
			responseStatus.set(ResponseStatusType.AUTH_TOKEN_EMPTY.getCode(), ResponseStatusType.AUTH_TOKEN_EMPTY.getMsg());
			responseWrite(httpServletResponse, responseStatus);
			return;
		}
		logger.info("<<-------权限验证通过："+path);
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	private void responseWrite(HttpServletResponse httpServletResponse,Object obj) {
		httpServletResponse.setHeader("Content-type", "application/Json;charset=UTF-8");  
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		try {
			String msg = gson.toJson(obj);
			logger.info(msg);
			httpServletResponse.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AuthFilter setIgnorUrlPattern(List<String> ignorUrlPatterns) {
		this.ignorUrlPatterns = ignorUrlPatterns;
		return this;
	}

	public AuthFilter setStaticResources(List<String> staticResources) {
		this.staticResources = staticResources;
		return this;
	}

}
