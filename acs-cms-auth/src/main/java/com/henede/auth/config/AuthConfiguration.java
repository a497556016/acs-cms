package com.henede.auth.config;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public class AuthConfiguration {
	
	private List<String> filterUrlPatterns;
	private List<String> ignorUrlPatterns;
	private List<String> staticResources;
	
	public AuthConfiguration setIgnorUrlPatterns(String... ignorUrlPatterns){
		if(null!=ignorUrlPatterns&&ignorUrlPatterns.length>0) {
			this.ignorUrlPatterns = Lists.newArrayList(ignorUrlPatterns);
		}else {
			this.ignorUrlPatterns = Lists.newArrayList();
		}
		
		return this;
	}

	public List<String> getIgnorUrlPatterns() {
		return ignorUrlPatterns;
	}

	public List<String> getFilterUrlPatterns() {
		return filterUrlPatterns;
	}

	public AuthConfiguration setFilterUrlPatterns(String... filterUrlPatterns) {
		if(null!=filterUrlPatterns&&filterUrlPatterns.length>0) {
			this.filterUrlPatterns = Lists.newArrayList(filterUrlPatterns);
		}else {
			this.filterUrlPatterns = Lists.newArrayList();
		}
		this.filterUrlPatterns.add("/*");
		
		return this;
	}

	public List<String> getStaticResources() {
		return staticResources;
	}

	public AuthConfiguration setStaticResources(String... staticResources) {
		if(null!=staticResources&&staticResources.length>0) {
			this.staticResources = Lists.newArrayList(staticResources);
		}else {
			this.staticResources = Lists.newArrayList();
		}
		this.staticResources.add("*.html");
		this.staticResources.add("*.js");
		this.staticResources.add("*.css");
		
		return this;
	}
}
