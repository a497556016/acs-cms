package com.henede.admin.mapper;

import com.henede.admin.domain.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.henede.admin.dto.MenuDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuDto> selectAllMenus(Map<String, Object> param);
}