package com.henede.admin.mapper;

import com.henede.admin.domain.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectUserRole(Integer id);
}