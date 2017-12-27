package com.henede.admin.service;

import com.henede.admin.domain.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
public interface IRoleService extends IService<Role> {
    List<Role> selectUserRole(Integer id);
}
