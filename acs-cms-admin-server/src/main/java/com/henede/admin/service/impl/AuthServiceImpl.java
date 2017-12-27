package com.henede.admin.service.impl;

import com.henede.admin.domain.Auth;
import com.henede.admin.mapper.AuthMapper;
import com.henede.admin.service.IAuthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements IAuthService {
	
}
