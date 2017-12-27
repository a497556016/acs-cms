package com.henede.admin.service.impl;

import com.henede.admin.domain.Role;
import com.henede.admin.mapper.RoleMapper;
import com.henede.admin.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public List<Role> selectUserRole(Integer id) {
        List<Role> roles = this.baseMapper.selectUserRole(id);
        return roles;
    }
}
