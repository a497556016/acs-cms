package com.henede.admin.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.henede.auth.config.AuthConfiguration;
import com.henede.auth.config.AuthFilterConfig;
import com.henede.admin.filters.CommonFilter;

@Configuration
public class FilterConfig extends AuthFilterConfig {

	@Override
	public void config(AuthConfiguration authConfiguration) {
		authConfiguration
		.setFilterUrlPatterns("/*")
		.setIgnorUrlPatterns("/user/login","/user/getVerifyCode","/user/refreshToken","/config/getSysConfig","/druid","/web/*")
		.setStaticResources("*.ico","*.json");
	}
	
	@Bean
	public FilterRegistrationBean getCommonFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		registrationBean.setOrder(2);
		registrationBean.setFilter(new CommonFilter());
		registrationBean.setUrlPatterns(Lists.newArrayList("/*"));
		
		return registrationBean;
	}
	
}
