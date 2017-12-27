package com.henede.auth.service;

import java.util.Map;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.henede.auth.bean.UserDetails;
import com.henede.auth.exception.UsernameNotFoundException;

public interface UserDetailsService {
	
	/**
	 * 该方法会进行缓存更新
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@CachePut(value="user-detail:user-auth",key="#p0")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	/**
	 * 该方法直接从缓存中取数据
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Cacheable(value="user-detail:user-auth",key="#p0")
	public UserDetails getUserByUsername(String username) throws UsernameNotFoundException;

	/**
	 * 当资源、权限、角色关系发生改变时，建议调用该方法进行缓存更新
	 * @return
	 */
	@CachePut(value="user-detail:auth-res",key="targetClass")
	public Map<String, String> loadAuthResource();

	/**
	 * 默认缓存，如果缓存有更新，需要进行@CachePut，或者建立定时任务去更新
	 * @return
	 */
	@Cacheable(value="user-detail:auth-res",key="targetClass")
	public Map<String, String> getAuthResource();
}
