package com.henede.admin.service.impl;

import com.henede.admin.domain.Config;
import com.henede.admin.mapper.ConfigMapper;
import com.henede.admin.service.IConfigService;
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
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {
	
}
