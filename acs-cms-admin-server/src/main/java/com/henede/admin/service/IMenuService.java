package com.henede.admin.service;

import com.henede.admin.domain.Menu;
import com.baomidou.mybatisplus.service.IService;
import com.henede.admin.dto.MenuDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
public interface IMenuService extends IService<Menu> {

    List<MenuDto> selectAllMenus(Map<String, Object> param);
}
