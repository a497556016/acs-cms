package com.henede.auth.config;

import java.util.List;
import java.util.Map;

import com.henede.auth.config.AuthConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.henede.auth.filter.AuthFilter;
import com.henede.auth.service.UserDetailsService;
import com.henede.auth.service.UserManager;


public abstract class AuthFilterConfig {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserManager userManager;
	
	private AuthConfiguration authConfiguration = new AuthConfiguration();
	
	@Bean
	public FilterRegistrationBean getAuthFilterBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		this.config(authConfiguration);
		registrationBean.setOrder(1);
		registrationBean.setFilter(new AuthFilter(userManager)
				.setIgnorUrlPattern(authConfiguration.getIgnorUrlPatterns())
				.setStaticResources(authConfiguration.getStaticResources()));
		registrationBean.setUrlPatterns(authConfiguration.getFilterUrlPatterns());
		
		return registrationBean;
	}
	
	public abstract void config(AuthConfiguration authConfiguration);
	
}
