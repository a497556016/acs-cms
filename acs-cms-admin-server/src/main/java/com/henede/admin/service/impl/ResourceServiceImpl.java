package com.henede.admin.service.impl;

import com.henede.admin.domain.Resource;
import com.henede.admin.mapper.ResourceMapper;
import com.henede.admin.service.IResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Override
    public List<Map<String, String>> selectResourceAuths() {
        return this.baseMapper.selectResourceAuths();
    }

}
